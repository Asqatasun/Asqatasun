/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tgol.controller;

import org.opens.tgol.action.voter.ActionHandler;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.presentation.factory.TestResultFactory;
import org.opens.tgol.util.TgolHighlighter;
import org.opens.tgol.util.TgolKeyStore;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tgol.command.AuditResultSortCommand;
import org.opens.tgol.command.factory.AuditResultSortCommandFactory;
import org.opens.tgol.exception.ForbiddenUserException;
import org.opens.tgol.form.FormField;
import org.opens.tgol.form.builder.FormFieldBuilder;
import org.opens.tgol.presentation.data.AuditStatistics;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/** 
 *
 * @author jkowalczyk
 */
@Controller
public class AuditResultController extends AuditDataHandlerController {

    private static final Logger LOGGER = Logger.getLogger(AuditResultController.class);

    private boolean hasSourceCodeWithDoctype = false;

    List<FormFieldBuilder> sortFormFieldBuilderList;
    public final void setFormFieldBuilderList(final List<FormFieldBuilder> formFieldBuilderList) {
        this.sortFormFieldBuilderList = formFieldBuilderList;
    }

    private ActionHandler actionHandler;
    public ActionHandler getActionHandler() {
        return actionHandler;
    }

    public void setActionHandler(ActionHandler contractActionHandler) {
        this.actionHandler = contractActionHandler;
    }

    private String themeSortKey;
    public String getThemeSortKey() {
        return themeSortKey;
    }

    public void setThemeSortKey(String themeSortKey) {
        this.themeSortKey = themeSortKey;
    }

    private String testResultSortKey;
    public String getTestResultSortKey() {
        return testResultSortKey;
    }

    public void setTestResultSortKey(String testResultSortKey) {
        this.testResultSortKey = testResultSortKey;
    }

    public AuditResultController() {
        super();
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value=TgolKeyStore.AUDIT_RESULT_CONTRACT_URL, method=RequestMethod.GET)
    @Secured(TgolKeyStore.ROLE_USER_KEY)
    public String displayAuditResultFromContract(
            @RequestParam(TgolKeyStore.WEBRESOURCE_ID_KEY) String webresourceId,
            HttpServletRequest request,
            Model model) {
        return dispatchDisplayResultRequest(Long.valueOf(webresourceId), null, model, request);
    }

    @RequestMapping(method = RequestMethod.POST)
    @Secured(TgolKeyStore.ROLE_USER_KEY)
    protected String submitForm(
            @ModelAttribute(TgolKeyStore.AUDIT_RESULT_SORT_COMMAND_KEY) AuditResultSortCommand auditResultSortCommand,
            BindingResult result,
            Model model,
            HttpServletRequest request) {

        return dispatchDisplayResultRequest(
                auditResultSortCommand.getWebResourceId(),
                auditResultSortCommand,
                model,
                request);
    }

    @RequestMapping(value=TgolKeyStore.SOURCE_CODE_CONTRACT_URL, method=RequestMethod.GET)
    @Secured(TgolKeyStore.ROLE_USER_KEY)
    public String displaySourceCodeFromContract(
            @RequestParam(TgolKeyStore.WEBRESOURCE_ID_KEY) String webresourceId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        User user = getCurrentUser();
        model.addAttribute(TgolKeyStore.AUTHENTICATED_USER_KEY,user);
        WebResource webResource = getWebResourceDataService().ligthRead(Long.valueOf(webresourceId));
        if (isUserAllowedToDisplayResult(user,webResource) && webResource instanceof Page) {
            Page page = (Page)webResource;
            hasSourceCodeWithDoctype = false;
            boolean hasSSP = true;
            SSP ssp = null;
            try {
                ssp = getContentDataService().findSSP(page, page.getURL());
            } catch (NoResultException nre) {
                hasSSP = false;
            }
            if (hasSSP) {
                model.addAttribute(TgolKeyStore.SOURCE_CODE_KEY,highlightSourceCode(ssp));
                addAuditStatisticsToModel(webResource, model);
            }
            return TgolKeyStore.SOURCE_CODE_PAGE_VIEW_NAME;
        } else {
            throw new ForbiddenUserException(getCurrentUser());
        }
    }

    private String dispatchDisplayResultRequest(
            Long webResourceId,
            AuditResultSortCommand auditResultSortCommand,
            Model model,
            HttpServletRequest request) {
        User user = getCurrentUser();
        model.addAttribute(TgolKeyStore.AUTHENTICATED_USER_KEY,user);
        //We first check that the current user is allowed to display the result
        //of this audit
        WebResource webResource = getWebResourceDataService().ligthRead(webResourceId);
        if (isUserAllowedToDisplayResult(user,webResource)) {
            this.callGc(webResource);
            // If the Id given in argument correspond to a webResource,
            // data are retrieved to be prepared and displayed
            addAuditStatisticsToModel(webResource, model);
            AuditStatistics auditStatistics = ((AuditStatistics)model.asMap().get(TgolKeyStore.STATISTICS_KEY));
            String referentialParameter = auditStatistics.getParametersMap().get("referential");
            AuditResultSortCommand asuc;
            List<FormField> formFieldList;
            if (auditResultSortCommand == null) {
                formFieldList = AuditResultSortCommandFactory.getInstance().
                    getFormFieldBuilderCopy(referentialParameter, sortFormFieldBuilderList);
                asuc = AuditResultSortCommandFactory.getInstance().getInitialisedAuditResultSortCommand(
                            webResourceId,
                            formFieldList);
            } else {
                formFieldList = AuditResultSortCommandFactory.getInstance().
                    getFormFieldBuilderCopy(referentialParameter, sortFormFieldBuilderList, auditResultSortCommand);
                asuc = auditResultSortCommand;
            }
            model.addAttribute(TgolKeyStore.AUDIT_RESULT_SORT_FIELD_LIST_KEY, formFieldList);
            model.addAttribute(TgolKeyStore.AUDIT_RESULT_SORT_COMMAND_KEY, asuc);
            return prepareSuccessfullAuditData(
                    webResource,
                    model,
                    getLocaleResolver().resolveLocale(request));
        } else {
            throw new ForbiddenUserException(getCurrentUser());
        }
    }

    /**
     * 
     * @param webResource
     * @param model
     * @param locale
     * @param exportFormat
     * @return
     * @throws IOException
     */
    private String prepareSuccessfullAuditData(
            WebResource webResource,
            Model model,
            Locale locale) {
        model.addAttribute(TgolKeyStore.LOCALE_KEY,locale);
        if (webResource instanceof Site) {
            return prepareSuccessfullSiteData((Site)webResource, model);
        } else if (webResource instanceof Page) {
            //In case of display page result page, we need all data related with
            // the page, that's why a deepRead is needed
            WebResource fullWebResource =
                    getWebResourceDataService().deepRead(webResource.getId());
            return prepareSuccessfullPageData((Page)fullWebResource, model);
        }
        return TgolKeyStore.OUPS_VIEW_NAME;
    }

    /**
     * This methods handles audit data in case of the audit is
     * @param site
     * @param model
     * @return
     * @throws IOException
     */
    private String prepareSuccessfullSiteData(Site site, Model model) {
        hasSourceCodeWithDoctype = false;
        AuditResultSortCommand asuc = ((AuditResultSortCommand)model.asMap().get(TgolKeyStore.AUDIT_RESULT_SORT_COMMAND_KEY));
        model.addAttribute(TgolKeyStore.TEST_RESULT_LIST_KEY,
                TestResultFactory.getInstance().getTestResultSortedByThemeMap(
                            site,
                            getSiteScope(),
                            hasSourceCodeWithDoctype,
                            true,
                            asuc.getSortOptionMap().get(themeSortKey),
                            asuc.getSortOptionMap().get(testResultSortKey)));
        // Attributes for breadcrumb
        Contract contract = retrieveContractFromWebResource(site);
        model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, contract.getId());
        model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
        model.addAttribute(TgolKeyStore.RESULT_ACTION_LIST_KEY, actionHandler.getActionList("EXPORT"));
        return TgolKeyStore.RESULT_SITE_VIEW_NAME;
    }

    /**
     *
     * @param page
     * @param model
     * @return
     * @throws IOException
     */
    private String prepareSuccessfullPageData(Page page, Model model) {
        Audit audit = page.getAudit();
        if (audit == null && page.getParent() != null) {
            audit = page.getParent().getAudit();
        }
        if (audit == null) {
            return TgolKeyStore.OUPS_VIEW_REDIRECT_NAME;
        }
        Contract contract = retrieveContractFromWebResource(page);
        if (!audit.getStatus().equals(AuditStatus.COMPLETED)) {
            return prepareFailedAuditData(audit, model);
        }
        hasSourceCodeWithDoctype = false;
        boolean hasSSP = true;
        // The source code has to be hightlighted before the processResult are
        // computed. We need to know if a doctype is present in the page. If true,
        // the line of each process remark has to be increased by 1.
        SSP ssp = null;
        try {
            ssp = getContentDataService().findSSP(page, page.getURL());
        } catch (NoResultException nre) {
            hasSSP = false;
        }

        if (hasSSP) {
            model.addAttribute(TgolKeyStore.SOURCE_CODE_KEY,highlightSourceCode(ssp));
            AuditResultSortCommand asuc = ((AuditResultSortCommand)model.asMap().get(TgolKeyStore.AUDIT_RESULT_SORT_COMMAND_KEY));
            model.addAttribute(TgolKeyStore.TEST_RESULT_LIST_KEY,
                TestResultFactory.getInstance().getTestResultSortedByThemeMap(
                    page,
                    getPageScope(),
                    hasSourceCodeWithDoctype,
                    true,
                    asuc.getSortOptionMap().get(themeSortKey),
                    asuc.getSortOptionMap().get(testResultSortKey)));
        }
        model.addAttribute(TgolKeyStore.STATUS_KEY, computeAuditStatus(audit));
        model.addAttribute(TgolKeyStore.RESULT_ACTION_LIST_KEY, actionHandler.getActionList("EXPORT"));
        // Attributes for breadcrumb
        model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, contract.getId());
        model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
        Long parentWrId= getWebResourceDataService().getParentWebResourceId(page.getId());
        boolean isAuthorizedScopeForPageList = false;
        if (parentWrId != null) {
            isAuthorizedScopeForPageList= isAuthorizedScopeForPageList(getWebResourceDataService().ligthRead(parentWrId));
            model.addAttribute(TgolKeyStore.PARENT_WEBRESOURCE_ID_KEY, parentWrId);
        }
        model.addAttribute(TgolKeyStore.AUTHORIZED_SCOPE_FOR_PAGE_LIST, isAuthorizedScopeForPageList);
        return TgolKeyStore.RESULT_PAGE_VIEW_NAME;
    }

    /**
     * This methods enforces the call of the Garbarge collector.
     * @param webresource
     */
    private void callGc(WebResource webresource){
        if (webresource != null && (webresource.getId() % 10) == 0) {
            System.gc();
        }
    }

    /**
     * This methods makes a distant call to the highlighter service and returns
     * the highlighted code.
     * @param ssp
     * @return
     * @throws IOException
     */
    private String highlightSourceCode(SSP ssp) {
        if (ssp.getDoctype() != null && !ssp.getDoctype().trim().isEmpty()) {
            hasSourceCodeWithDoctype = true;
        } else {
            hasSourceCodeWithDoctype = false;
        }
        try {
            return TgolHighlighter.getInstance().
                    highlightSourceCode(ssp.getDoctype(), ssp.getAdaptedContent());
        } catch (IOException ex) {
            LOGGER.warn(ex.getMessage());
            return "";
        }
    }

}