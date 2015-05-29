/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.opens.tgol.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.service.reference.CriterionDataService;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tgol.command.AuditResultSortCommand;
import org.opens.tgol.entity.contract.Act;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.ScopeEnum;
import org.opens.tgol.exception.ForbiddenPageException;
import org.opens.tgol.exception.ForbiddenUserException;
import org.opens.tgol.presentation.factory.TestResultFactory;
import org.opens.tgol.presentation.highlighter.HtmlHighlighter;
import org.opens.tgol.util.HttpStatusCodeFamily;
import org.opens.tgol.util.TgolKeyStore;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AuditResultController extends AbstractAuditResultController {

    private CriterionDataService criterionDataService;
    public CriterionDataService getCriterionDataService() {
        return criterionDataService;
    }

    @Autowired
    public void setCriterionDataService(
            CriterionDataService criterionDataService) {
        this.criterionDataService = criterionDataService;
    }

    /**
     * The Html hightlighter.
     */
    private HtmlHighlighter highlighter;

    @Autowired
    public void setHtmlHighlighter(HtmlHighlighter highlighter) {
        this.highlighter = highlighter;
    }

    public AuditResultController() {
        super();
    }

    /**
     * General router when receive audit-result request. Regarding the scope of
     * the audit, the returned page may differ.
     *
     * @param auditId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = TgolKeyStore.AUDIT_RESULT_CONTRACT_URL, method = RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayAuditResultFromContract(
            @RequestParam(TgolKeyStore.AUDIT_ID_KEY) String auditId,
            HttpServletRequest request,
            Model model) {
        try {
            Audit audit = getAuditDataService().read(Long.valueOf(auditId));
            Act act = getActDataService().getActFromAudit(audit);
            switch (act.getScope().getCode()) {
                case FILE:
                case PAGE:
                    model.addAttribute(TgolKeyStore.WEBRESOURCE_ID_KEY, 
                            audit.getSubject().getId());
                    return TgolKeyStore.RESULT_PAGE_VIEW_REDIRECT_NAME;

                case DOMAIN:
                case SCENARIO:
                    model.addAttribute(TgolKeyStore.AUDIT_ID_KEY, auditId);
                    return TgolKeyStore.SYNTHESIS_SITE_VIEW_REDIRECT_NAME;
                case GROUPOFFILES:
                case GROUPOFPAGES:
                    model.addAttribute(TgolKeyStore.AUDIT_ID_KEY, auditId);
                    model.addAttribute(TgolKeyStore.STATUS_KEY,
                            HttpStatusCodeFamily.f2xx.name());
                    return TgolKeyStore.PAGE_LIST_XXX_VIEW_REDIRECT_NAME;
                default:
                    throw new ForbiddenPageException();
            }
        } catch (NumberFormatException nfe) {
            throw new ForbiddenPageException();
        }
    }

    /**
     * @param webresourceId
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = {TgolKeyStore.PAGE_RESULT_CONTRACT_URL,
        TgolKeyStore.SITE_RESULT_CONTRACT_URL}, method = RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayPageResultFromContract(
            @RequestParam(TgolKeyStore.WEBRESOURCE_ID_KEY) String webresourceId,
            HttpServletRequest request,
            Model model) {
        Long webResourceIdValue;
        try {
            webResourceIdValue = Long.valueOf(webresourceId);
        } catch (NumberFormatException nfe) {
            throw new ForbiddenPageException();
        }

        return dispatchDisplayResultRequest(
                webResourceIdValue,
                null,
                model,
                request,
                false,
                null);
    }

    /**
     *
     * @param auditResultSortCommand
     * @param webresourceId
     * @param result
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = {TgolKeyStore.CONTRACT_VIEW_NAME_REDIRECT, TgolKeyStore.PAGE_RESULT_CONTRACT_URL}, method = RequestMethod.POST)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    protected String submitPageResultSorter(
            @ModelAttribute(TgolKeyStore.AUDIT_RESULT_SORT_COMMAND_KEY) AuditResultSortCommand auditResultSortCommand,
            @RequestParam(TgolKeyStore.WEBRESOURCE_ID_KEY) String webresourceId,
            BindingResult result,
            Model model,
            HttpServletRequest request) {
        return dispatchDisplayResultRequest(
                auditResultSortCommand.getWebResourceId(),
                auditResultSortCommand,
                model,
                request,
                false,
                null);
    }

    /**
     *
     * @param webresourceId
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = TgolKeyStore.SOURCE_CODE_CONTRACT_URL, method = RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displaySourceCodeFromContract(
            @RequestParam(TgolKeyStore.WEBRESOURCE_ID_KEY) String webresourceId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        WebResource webResource;
        try {
            webResource = getWebResourceDataService().ligthRead(
                    Long.valueOf(webresourceId));
        } catch (NumberFormatException nfe) {
            throw new ForbiddenPageException();
        }
        if (webResource instanceof Site) {
            throw new ForbiddenPageException();
        }
        Audit audit = getAuditFromWebResource(webResource);
        if (isUserAllowedToDisplayResult(audit)) {
            Page page = (Page) webResource;

            SSP ssp = getContentDataService().findSSP(page, page.getURL());
            model.addAttribute(TgolKeyStore.SOURCE_CODE_KEY,
                    highlightSourceCode(ssp));

            ScopeEnum scope = getActDataService().getActFromAudit(audit)
                    .getScope().getCode();
            if (scope.equals(ScopeEnum.GROUPOFPAGES)
                    || scope.equals(ScopeEnum.PAGE)) {
                model.addAttribute(TgolKeyStore.IS_GENERATED_HTML_KEY, true);
            }
            return TgolKeyStore.SOURCE_CODE_PAGE_VIEW_NAME;
        } else {
            throw new ForbiddenUserException(getCurrentUser());
        }
    }

    /**
     *
     * @param webresourceId
     * @param criterionId
     * @param model
     * @return the test-result view name
     */
    @RequestMapping(value = TgolKeyStore.CRITERION_RESULT_CONTRACT_URL, method = RequestMethod.GET)
    public String displayCriterionResult(
            @RequestParam(TgolKeyStore.WEBRESOURCE_ID_KEY) String webresourceId,
            @RequestParam(TgolKeyStore.CRITERION_CODE_KEY) String criterionId,
            Model model) {
        Long wrId;
        Long critId;
        try {
            wrId = Long.valueOf(webresourceId);
            critId = Long.valueOf(criterionId);
        } catch (NumberFormatException nfe) {
            throw new ForbiddenUserException(getCurrentUser());
        }

        WebResource webResource = getWebResourceDataService().ligthRead(wrId);
        if (webResource == null || webResource instanceof Site) {
            throw new ForbiddenPageException();
        }
        Audit audit = getAuditFromWebResource(webResource);
        if (isUserAllowedToDisplayResult(audit)) {
            Contract contract = retrieveContractFromAudit(audit);
            // Attributes for breadcrumb
            model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, contract.getId());
            model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY,
                    contract.getLabel());
            model.addAttribute(TgolKeyStore.URL_KEY, webResource.getURL());
            Criterion crit = criterionDataService.read(critId);
            model.addAttribute(TgolKeyStore.CRITERION_LABEL_KEY,
                    crit.getLabel());
            model.addAttribute(TgolKeyStore.AUDIT_ID_KEY, audit.getId());

            // Add a boolean used to display the breadcrumb.
            model.addAttribute(TgolKeyStore.AUTHORIZED_SCOPE_FOR_PAGE_LIST,
                    isAuthorizedScopeForPageList(audit));

            model.addAttribute(TgolKeyStore.TEST_RESULT_LIST_KEY,
                    TestResultFactory.getInstance()
                    .getTestResultListFromCriterion(webResource, crit));
            return TgolKeyStore.CRITERION_RESULT_VIEW_NAME;
        } else {
            throw new ForbiddenPageException();
        }
    }

    /**
     *
     * @param webresourceId
     * @param testId
     * @param model
     * @return the test-result view name
     */
    @RequestMapping(value = TgolKeyStore.TEST_RESULT_CONTRACT_URL, method = RequestMethod.GET)
    public String displayTestResult(
            @RequestParam(TgolKeyStore.WEBRESOURCE_ID_KEY) String webresourceId,
            @RequestParam(TgolKeyStore.TEST_CODE_KEY) String testId,
            Model model) {
        Long wrId;
        Long tstId;
        try {
            wrId = Long.valueOf(webresourceId);
            tstId = Long.valueOf(testId);
        } catch (NumberFormatException nfe) {
            throw new ForbiddenUserException(getCurrentUser());
        }

        WebResource webResource = getWebResourceDataService().ligthRead(wrId);
        if (webResource == null) {
            throw new ForbiddenPageException();
        }
        Audit audit = getAuditFromWebResource(webResource);
        if (isUserAllowedToDisplayResult(audit)) {
            Contract contract = retrieveContractFromAudit(audit);
            // Attributes for breadcrumb
            model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, contract.getId());
            model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY,
                    contract.getLabel());
            model.addAttribute(TgolKeyStore.URL_KEY, webResource.getURL());
            Test test = getTestDataService().read(tstId);

            model.addAttribute(TgolKeyStore.TEST_LABEL_KEY, test.getLabel());
            model.addAttribute(TgolKeyStore.AUDIT_ID_KEY, audit.getId());

            if (!test.getScope().equals(getPageScope())) {
                model.addAttribute(TgolKeyStore.SITE_SCOPE_TEST_DETAILS_KEY,
                        true);
            } else {
                // Add a boolean used to display the breadcrumb.
                model.addAttribute(TgolKeyStore.AUTHORIZED_SCOPE_FOR_PAGE_LIST,
                        isAuthorizedScopeForPageList(audit));
            }

            model.addAttribute(
                    TgolKeyStore.TEST_RESULT_LIST_KEY,
                    TestResultFactory.getInstance().getTestResultListFromTest(
                            webResource, test));
            return TgolKeyStore.TEST_RESULT_VIEW_NAME;
        } else {
            throw new ForbiddenPageException();
        }
    }

    /**
     * This methods call the highlighter service and returns the highlighted
     * code.
     *
     * @param ssp
     * @return
     * @throws IOException
     */
    private String highlightSourceCode(SSP ssp) {
        if (ssp != null && StringUtils.isNotBlank(ssp.getDoctype())) {
            return highlighter.highlightSourceCode(ssp.getDoctype(),
                    ssp.getAdaptedContent());
        } else {
            return highlighter.highlightSourceCode(ssp.getAdaptedContent());
        }
    }

}