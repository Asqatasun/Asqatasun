/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.webapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.AuditStatus;
import org.tanaguru.entity.service.statistics.CriterionStatisticsDataService;
import org.tanaguru.entity.subject.Page;
import org.tanaguru.entity.subject.Site;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.webapp.action.voter.ActionHandler;
import org.tanaguru.webapp.command.AuditResultSortCommand;
import org.tanaguru.webapp.command.ManualAuditCommand;
import org.tanaguru.webapp.command.factory.AuditResultSortCommandFactory;
import org.tanaguru.webapp.command.factory.AuditSetUpCommandFactory;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.exception.ForbiddenPageException;
import org.tanaguru.webapp.exception.ForbiddenUserException;
import org.tanaguru.webapp.exception.LostInSpaceException;
import org.tanaguru.webapp.form.CheckboxElement;
import org.tanaguru.webapp.form.CheckboxFormFieldImpl;
import org.tanaguru.webapp.form.FormField;
import org.tanaguru.webapp.form.builder.FormFieldBuilder;
import org.tanaguru.webapp.presentation.data.AuditStatistics;
import org.tanaguru.webapp.presentation.factory.CriterionResultFactory;
import org.tanaguru.webapp.presentation.factory.TestResultFactory;
import org.tanaguru.webapp.util.TgolKeyStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

/**
 *
 * @author jkowalczyk
 */
@Controller
public class AbstractAuditResultController extends AbstractAuditDataHandlerController {

    private final List<FormFieldBuilder> sortFormFieldBuilderList = new ArrayList();
    public final void setFormFieldBuilderList(final List<FormFieldBuilder> formFieldBuilderList) {
        this.sortFormFieldBuilderList.addAll(formFieldBuilderList);
    }

    /**
     *
     * @param formFieldBuilder
     */
    public final void addFormFieldBuilder(final FormFieldBuilder formFieldBuilder) {
        this.sortFormFieldBuilderList.add(formFieldBuilder);
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

    private List<String> authorizedRefForCriterionViewList;
    public List<String> getAuthorizedRefForCriterionViewList() {
        return authorizedRefForCriterionViewList;
    }

    public void setAuthorizedRefForCriterionViewList(List<String> authorizedRefForCriterionViewList) {
        this.authorizedRefForCriterionViewList = authorizedRefForCriterionViewList;
    }

    private CriterionStatisticsDataService criterionStatisticsDataService;
    public CriterionStatisticsDataService getCriterionStatisticsDataService() {
        return criterionStatisticsDataService;
    }

    @Autowired
    public void setCriterionStatisticsDataService(CriterionStatisticsDataService criterionStatisticsDataService) {
        this.criterionStatisticsDataService = criterionStatisticsDataService;
    }

    /**
     * Regarding the page type, this method collects data, set them up and
     * display the appropriate result page.
     *
     * @param webResourceId
     * @param auditResultSortCommand
     * @param model
     * @param request
     * @param isManualAudit
     * @param manualAuditCommand
     * @return
     */
    protected String dispatchDisplayResultRequest(
            Long webResourceId,
            AuditResultSortCommand auditResultSortCommand,
            Model model,
            HttpServletRequest request,
            boolean isManualAudit,
            ManualAuditCommand manualAuditCommand) {
        // We first check that the current user is allowed to display the result
        // of this audit

        WebResource webResource = getWebResourceDataService().ligthRead(
                webResourceId);
        if (webResource == null) {
            throw new ForbiddenPageException();
        }
        Audit audit = getAuditFromWebResource(webResource);
        // If the Id given in argument correspond to a webResource,
        // data are retrieved to be prepared and displayed
        if (isUserAllowedToDisplayResult(audit)) {
            this.callGc(webResource);

            String displayScope = computeDisplayScope(
                    request,
                    auditResultSortCommand);

            addAuditStatisticsToModel(
                    webResource,
                    model,
                    displayScope);

            // The page is displayed with sort option. Form needs to be set up
            prepareDataForSortConsole(
                    webResourceId,
                    displayScope,
                    auditResultSortCommand,
                    model,
                    isManualAudit);

            // Data need to be prepared regarding the audit type
            return prepareSuccessfullAuditData(
                    webResource,
                    audit,
                    model,
                    displayScope,
                    getLocaleResolver().resolveLocale(request),
                    isManualAudit,
                    manualAuditCommand);
        } else {
            throw new ForbiddenUserException(getCurrentUser());
        }
    }

    /**
     * This method prepares the data to be displayed in the sort (referential,
     * theme, result types) console of the result page.
     *
     * @param webResourceId
     * @param displayScope
     * @param auditResultSortCommand
     * @param model
     * @param isManualAudit
     */
    private void prepareDataForSortConsole(
            Long webResourceId,
            String displayScope,
            AuditResultSortCommand auditResultSortCommand,
            Model model,
            boolean isManualAudit) {
        // Meta-statistics have been added to the method previously
        String referentialParameter = ((AuditStatistics) model.asMap().get(
                TgolKeyStore.STATISTICS_KEY)).getParametersMap().get(TgolKeyStore.REFERENTIAL_PARAM_KEY);

        AuditResultSortCommand asuc;
        List<FormField> formFieldList;
        if (auditResultSortCommand == null) {
            formFieldList = AuditResultSortCommandFactory.getInstance()
                    .getFormFieldBuilderCopy(referentialParameter,
                            sortFormFieldBuilderList);
            if (isManualAudit) {
                CheckboxFormFieldImpl ObjectList = (CheckboxFormFieldImpl) formFieldList
                        .get(1);
                List<CheckboxElement> checkboxElementList = ObjectList
                        .getCheckboxElementList();
                for (CheckboxElement checkboxElement : checkboxElementList) {
                    if (checkboxElement.getI18nKey().equals("nt")) {
                        checkboxElement.setSelected(true);
                    }
                }
            }
            asuc = AuditResultSortCommandFactory.getInstance()
                    .getInitialisedAuditResultSortCommand(
                            webResourceId,
                            displayScope,
                            isCriterionViewAccessible(webResourceId,
                                    referentialParameter), formFieldList);
        } else {
            formFieldList = AuditResultSortCommandFactory.getInstance().
                    getFormFieldBuilderCopy(referentialParameter, sortFormFieldBuilderList, auditResultSortCommand);
            asuc = auditResultSortCommand;
        }
        model.addAttribute(TgolKeyStore.AUDIT_RESULT_SORT_FIELD_LIST_KEY, formFieldList);
        model.addAttribute(TgolKeyStore.AUDIT_RESULT_SORT_COMMAND_KEY, asuc);
    }

    /**
     *
     * @param webResourceId
     * @param referentialParameter
     * @return whether the criterion view is accessible for the given
     * referential
     */
    protected boolean isCriterionViewAccessible(Long webResourceId, String referentialParameter) {
        return authorizedRefForCriterionViewList.contains(referentialParameter)
                && criterionStatisticsDataService.getCriterionStatisticsCountByWebResource(webResourceId) > 0;
    }

    /**
     * Regarding the audit type, collect data needed by the view.
     *
     * @param webResource
     * @param audit
     * @param model
     * @param displayScope
     * @param locale
     * @param isManualAudit
     * @return
     */
    private String prepareSuccessfullAuditData(
            WebResource webResource,
            Audit audit,
            Model model,
            String displayScope,
            Locale locale,
            boolean isManualAudit,
            ManualAuditCommand manualAuditCommand) {
        model.addAttribute(TgolKeyStore.LOCALE_KEY, locale);
        if (webResource instanceof Site) {
            return prepareSuccessfullSiteData((Site) webResource, audit, model);
        } else if (webResource instanceof Page) {
            // In case of display page result page, we need all data related
            // with the page, that's why a deepRead is needed
            return prepareSuccessfullPageData(
                    (Page) webResource,
                    audit,
                    model,
                    displayScope,
                    isManualAudit,
                    manualAuditCommand);
        }
        throw new LostInSpaceException();
    }

    /**
     * This methods handles audit data in case of the audit is of site type
     *
     * @param site
     * @param audit
     * @param model
     * @return
     * @throws IOException
     */
    private String prepareSuccessfullSiteData(Site site, Audit audit,
            Model model) {
        AuditResultSortCommand asuc = ((AuditResultSortCommand) model.asMap()
                .get(TgolKeyStore.AUDIT_RESULT_SORT_COMMAND_KEY));
        model.addAttribute(
                TgolKeyStore.TEST_RESULT_LIST_KEY,
                TestResultFactory.getInstance().getTestResultSortedByThemeMap(
                        site, getSiteScope(),
                        asuc.getSortOptionMap().get(themeSortKey).toString(),
                        getTestResultSortSelection(asuc)));

        // Attributes for breadcrumb
        Contract contract = retrieveContractFromAudit(audit);
        model.addAttribute(TgolKeyStore.AUDIT_ID_KEY, audit.getId());
        model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, contract.getId());
        model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
        model.addAttribute(TgolKeyStore.RESULT_ACTION_LIST_KEY,
                actionHandler.getActionList("EXPORT"));
        return TgolKeyStore.RESULT_SITE_VIEW_NAME;
    }

    /**
     * This methods handles audit data in case of page type audit
     *
     * @param page
     * @param audit
     * @param model
     * @param displayScope
     * @param isManualAudit
     * @param manualAuditCommand
     * @return the success audit page result view name
     */
    protected String prepareSuccessfullPageData(
            Page page,
            Audit audit,
            Model model,
            String displayScope,
            boolean isManualAudit,
            ManualAuditCommand manualAuditCommand) {

        Contract contract = retrieveContractFromAudit(audit);

        if (!audit.getStatus().equals(AuditStatus.COMPLETED)
                && !audit.getStatus().equals(
                        AuditStatus.MANUAL_ANALYSE_IN_PROGRESS)
                && !audit.getStatus().equals(AuditStatus.MANUAL_COMPLETED)
                && !audit.getStatus().equals(AuditStatus.MANUAL_INITIALIZING)) {
            return prepareFailedAuditData(audit, model);
        }

        model.addAttribute(TgolKeyStore.STATUS_KEY, computeAuditStatus(audit));
        model.addAttribute(TgolKeyStore.RESULT_ACTION_LIST_KEY,
                actionHandler.getActionList("EXPORT"));
        // Attributes for breadcrumb
        model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, contract.getId());
        model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
        model.addAttribute(TgolKeyStore.AUDIT_ID_KEY, audit.getId());

        // Add a boolean used to display the breadcrumb.
        model.addAttribute(TgolKeyStore.AUTHORIZED_SCOPE_FOR_PAGE_LIST,
                isAuthorizedScopeForPageList(audit));

        // Add a command to relaunch the audit from the result page
        model.addAttribute(
                TgolKeyStore.AUDIT_SET_UP_COMMAND_KEY,
                AuditSetUpCommandFactory.getInstance()
                .getPageAuditSetUpCommand(
                        contract,
                        page.getURL(),
                        getParameterDataService()
                        .getParameterSetFromAudit(audit)));

        if (StringUtils.equalsIgnoreCase(displayScope,
                TgolKeyStore.TEST_DISPLAY_SCOPE_VALUE)) {
            AuditResultSortCommand asuc = ((AuditResultSortCommand) model
                    .asMap().get(TgolKeyStore.AUDIT_RESULT_SORT_COMMAND_KEY));

            model.addAttribute(
                    TgolKeyStore.TEST_RESULT_LIST_KEY,
                    TestResultFactory.getInstance()
                    .getTestResultSortedByThemeMap(
                            page,
                            getPageScope(),
                            asuc.getSortOptionMap().get(themeSortKey)
                            .toString(),
                            getTestResultSortSelection(asuc)));
            if (isManualAudit) {
                if (manualAuditCommand == null) {
                    manualAuditCommand = new ManualAuditCommand();
                }
                manualAuditCommand.setModifedManualResultMap(TestResultFactory
                        .getInstance().getTestResultMap(
                                page,
                                getPageScope(),
                                asuc.getSortOptionMap().get(themeSortKey)
                                .toString(),
                                getTestResultSortSelection(asuc)));
                model.addAttribute(TgolKeyStore.MANUAL_AUDIT_COMMAND_KEY,
                        manualAuditCommand);
            }

            return TgolKeyStore.RESULT_PAGE_VIEW_NAME;
        } else {
            AuditResultSortCommand asuc = ((AuditResultSortCommand) model
                    .asMap().get(TgolKeyStore.AUDIT_RESULT_SORT_COMMAND_KEY));
            model.addAttribute(
                    TgolKeyStore.CRITERION_RESULT_LIST_KEY,
                    CriterionResultFactory.getInstance()
                    .getCriterionResultSortedByThemeMap(
                            page,
                            asuc.getSortOptionMap().get(themeSortKey)
                            .toString(),
                            getTestResultSortSelection(asuc)));
            return TgolKeyStore.RESULT_PAGE_BY_CRITERION_VIEW_NAME;
        }
    }

    private Collection<String> getTestResultSortSelection(
            AuditResultSortCommand asuc) {
        Collection<String> selectedValues = new HashSet<>();
        if ((asuc.getSortOptionMap().get(testResultSortKey)) instanceof Object[]) {
            CollectionUtils.addAll(selectedValues, ((Object[]) asuc
                    .getSortOptionMap().get(testResultSortKey)));
        } else if ((asuc.getSortOptionMap().get(testResultSortKey)) instanceof String) {
            selectedValues.add((String) asuc.getSortOptionMap().get(
                    testResultSortKey));
        }
        return selectedValues;
    }

    /**
     * This methods enforces the call of the Garbarge collector.
     *
     * @param webresource
     */
    private void callGc(WebResource webresource) {
        if (webresource != null && (webresource.getId() % 10) == 0) {
            System.gc();
        }
    }

    /**
     * Extract from the audit parameter the referential. Regarding this value,
     * the returned page displays results sorted by tests or criterion
     *
     * @param request
     * @param arsc
     * @return
     */
    private String computeDisplayScope(
            HttpServletRequest request,
            AuditResultSortCommand arsc) {
        if (StringUtils.contains(request.getHeader(TgolKeyStore.REFERER_HEADER_KEY),
                TgolKeyStore.CRITERION_RESULT_PAGE_KEY)) {
            return TgolKeyStore.CRITERION_DISPLAY_SCOPE_VALUE;
        }
        if (arsc != null) {
            return arsc.getDisplayScope();
        }
        return TgolKeyStore.TEST_DISPLAY_SCOPE_VALUE;
    }

}
