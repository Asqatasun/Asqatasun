/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.service.reference.CriterionDataService;
import org.opens.tanaguru.entity.service.statistics.CriterionStatisticsDataService;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tgol.action.voter.ActionHandler;
import org.opens.tgol.command.AuditResultSortCommand;
import org.opens.tgol.command.factory.AuditResultSortCommandFactory;
import org.opens.tgol.command.factory.AuditSetUpCommandFactory;
import org.opens.tgol.entity.contract.Act;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.contract.ScopeEnum;
import org.opens.tgol.exception.ForbiddenPageException;
import org.opens.tgol.exception.ForbiddenUserException;
import org.opens.tgol.exception.LostInSpaceException;
import org.opens.tgol.form.FormField;
import org.opens.tgol.form.builder.FormFieldBuilder;
import org.opens.tgol.presentation.data.AuditStatistics;
import org.opens.tgol.presentation.factory.CriterionResultFactory;
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
public class AuditResultController extends AuditDataHandlerController {

    private static final Logger LOGGER = Logger.getLogger(AuditResultController.class);
    private static final String CRITERION_RESULT_PAGE_KEY="criterion-result";
    private static final String REFERER_HEADER_KEY="referer";

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

    private String referentialCode = "referential";
    public String getReferentialCode() {
        return referentialCode;
    }

    public void setReferentialCode(String referentialCode) {
        this.referentialCode = referentialCode;
    }
    
    private String levelParameterCode = "LEVEL";
    public String getLevelParameterCode() {
        return levelParameterCode;
    }

    public void setLevelParameterCode(String levelParameterCode) {
        this.levelParameterCode = levelParameterCode;
    }
    
    private List<String> authorizedRefForCriterionViewList;
    public List<String> getAuthorizedRefForCriterionViewList() {
        return authorizedRefForCriterionViewList;
    }

    public void setAuthorizedRefForCriterionViewList(List<String> authorizedRefForCriterionViewList) {
        this.authorizedRefForCriterionViewList = authorizedRefForCriterionViewList;
    }
    
    private CriterionDataService criterionDataService;
    public CriterionDataService getCriterionDataService() {
        return criterionDataService;
    }

    @Autowired
    public void setCriterionDataService(CriterionDataService criterionDataService) {
        this.criterionDataService = criterionDataService;
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
     * General router when receive audit-result request.
     * Regarding the scope of the audit, the returned page may differ.
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value=TgolKeyStore.AUDIT_RESULT_CONTRACT_URL, method=RequestMethod.GET)
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
                    model.addAttribute(TgolKeyStore.WEBRESOURCE_ID_KEY, audit.getSubject().getId());
                    return TgolKeyStore.RESULT_PAGE_VIEW_REDIRECT_NAME;
                case DOMAIN:
                case SCENARIO:
                    model.addAttribute(TgolKeyStore.AUDIT_ID_KEY, auditId);
                    return TgolKeyStore.SYNTHESIS_SITE_VIEW_REDIRECT_NAME;
                case GROUPOFFILES:
                case GROUPOFPAGES:
                    model.addAttribute(TgolKeyStore.AUDIT_ID_KEY, auditId);
                    model.addAttribute(TgolKeyStore.STATUS_KEY, HttpStatusCodeFamily.f2xx.name());
                    return TgolKeyStore.PAGE_LIST_XXX_VIEW_REDIRECT_NAME;
                default :
                    throw new ForbiddenPageException();
            }
        } catch (NumberFormatException nfe) {
            throw new ForbiddenPageException();
        }
    }
    
    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value={TgolKeyStore.PAGE_RESULT_CONTRACT_URL, TgolKeyStore.SITE_RESULT_CONTRACT_URL}, method=RequestMethod.GET)
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
                request);
    }

    @RequestMapping(value=TgolKeyStore.PAGE_RESULT_CONTRACT_URL, method = RequestMethod.POST)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    protected String submitPageResultSorter(
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
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displaySourceCodeFromContract(
            @RequestParam(TgolKeyStore.WEBRESOURCE_ID_KEY) String webresourceId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        WebResource webResource;
        try {
            webResource = getWebResourceDataService().ligthRead(Long.valueOf(webresourceId));
        } catch (NumberFormatException nfe) {
            throw new ForbiddenPageException();
        }
        if (webResource instanceof Site) {
            throw new ForbiddenPageException();
        }
        Audit audit = getAuditFromWebResource(webResource);
        if (isUserAllowedToDisplayResult(audit)) {
            Page page = (Page)webResource;

            SSP ssp =getContentDataService().findSSP(page, page.getURL());
            model.addAttribute(TgolKeyStore.SOURCE_CODE_KEY,highlightSourceCode(ssp));

            ScopeEnum scope = getActDataService().getActFromAudit(audit).getScope().getCode();
            if (scope.equals(ScopeEnum.GROUPOFPAGES) || scope.equals(ScopeEnum.PAGE)) {
                model.addAttribute(TgolKeyStore.IS_GENERATED_HTML_KEY,true);
            }
            return TgolKeyStore.SOURCE_CODE_PAGE_VIEW_NAME;
        } else {
            throw new ForbiddenUserException(getCurrentUser());
        }
    }

    /**
     *
     * @param model
     * @return
     *      the test-result view name
     */
    @RequestMapping(value=TgolKeyStore.CRITERION_RESULT_CONTRACT_URL, method=RequestMethod.GET)
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
        if (isUserAllowedToDisplayResult(audit))  {
                Contract contract = retrieveContractFromAudit(audit);
                // Attributes for breadcrumb
                model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, contract.getId());
                model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
                model.addAttribute(TgolKeyStore.URL_KEY, webResource.getURL());
                Criterion crit = criterionDataService.read(critId);
                model.addAttribute(TgolKeyStore.CRITERION_LABEL_KEY, crit.getLabel());
                model.addAttribute(TgolKeyStore.AUDIT_ID_KEY, audit.getId());

                // Add a boolean used to display the breadcrumb. 
                model.addAttribute(TgolKeyStore.AUTHORIZED_SCOPE_FOR_PAGE_LIST, isAuthorizedScopeForPageList(audit));
            
                model.addAttribute(TgolKeyStore.TEST_RESULT_LIST_KEY,
                    TestResultFactory.getInstance().getTestResultListFromCriterion(webResource, crit));
                return TgolKeyStore.CRITERION_RESULT_VIEW_NAME;
        } else {
            throw new ForbiddenPageException();
        }
    }
    
    /**
     *
     * @param model
     * @return
     *      the test-result view name
     */
    @RequestMapping(value=TgolKeyStore.TEST_RESULT_CONTRACT_URL, method=RequestMethod.GET)
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
        if (isUserAllowedToDisplayResult(audit))  {
                Contract contract = retrieveContractFromAudit(audit);
                // Attributes for breadcrumb
                model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, contract.getId());
                model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
                model.addAttribute(TgolKeyStore.URL_KEY, webResource.getURL());
                Test test = getTestDataService().read(tstId);
                
                model.addAttribute(TgolKeyStore.TEST_LABEL_KEY, test.getLabel());
                model.addAttribute(TgolKeyStore.AUDIT_ID_KEY, audit.getId());

                if (!test.getScope().equals(getPageScope())) {
                    model.addAttribute(TgolKeyStore.SITE_SCOPE_TEST_DETAILS_KEY, true);
                } else {
                    // Add a boolean used to display the breadcrumb. 
                    model.addAttribute(TgolKeyStore.AUTHORIZED_SCOPE_FOR_PAGE_LIST, isAuthorizedScopeForPageList(audit));
                }
            
                model.addAttribute(TgolKeyStore.TEST_RESULT_LIST_KEY,
                    TestResultFactory.getInstance().getTestResultListFromTest(webResource, test));
                return TgolKeyStore.TEST_RESULT_VIEW_NAME;
        } else {
            throw new ForbiddenPageException();
        }
    }
    
    /**
     * Regarding the page type, this method collects data, set them up and 
     * display the appropriate result page.
     * 
     * @param webResourceId
     * @param auditResultSortCommand
     * @param model
     * @param displayScope
     * @param request
     * @return 
     */
    private String dispatchDisplayResultRequest(
            Long webResourceId,
            AuditResultSortCommand auditResultSortCommand,
            Model model,
            HttpServletRequest request) {
        //We first check that the current user is allowed to display the result
        //of this audit
        WebResource webResource = getWebResourceDataService().ligthRead(webResourceId);
        if (webResource == null) {
            throw new ForbiddenPageException();
        }
        Audit audit = getAuditFromWebResource(webResource);
        // If the Id given in argument correspond to a webResource,
            // data are retrieved to be prepared and displayed
        if (isUserAllowedToDisplayResult(audit)) {
            this.callGc(webResource);

            String displayScope = computeDisplayScope(request, auditResultSortCommand);

            // first we add statistics meta-data to model 
            addAuditStatisticsToModel(webResource, model, displayScope);

            // The page is displayed with sort option. Form needs to be set up
            prepareDataForSortConsole(webResourceId, displayScope, auditResultSortCommand, model);

            // Data need to be prepared regarding the audit type
            return prepareSuccessfullAuditData(
                    webResource,
                    audit,
                    model,
                    displayScope,
                    getLocaleResolver().resolveLocale(request));
        } else {
            throw new ForbiddenUserException(getCurrentUser());
        }
    }

    /**
     * This method prepares the data to be displayed in the sort 
     * (referential, theme, result types) console of the result page.
     * 
     * @param webResourceId
     * @param displayScope
     * @param auditResultSortCommand
     * @param model 
     */
    private void prepareDataForSortConsole(
            Long webResourceId, 
            String displayScope,
            AuditResultSortCommand auditResultSortCommand, 
            Model model) {
        // Meta-statistics have been added to the method previously
        String referentialParameter = 
                    ((AuditStatistics)model.asMap().get(TgolKeyStore.STATISTICS_KEY)).
                        getParametersMap().get(referentialCode);
        AuditResultSortCommand asuc;
        List<FormField> formFieldList;
        if (auditResultSortCommand == null) {
            formFieldList = AuditResultSortCommandFactory.getInstance().
                getFormFieldBuilderCopy(referentialParameter, sortFormFieldBuilderList);
            asuc = AuditResultSortCommandFactory.getInstance().getInitialisedAuditResultSortCommand(
                        webResourceId,
                        displayScope,
                        isCriterionViewAccessible(webResourceId, referentialParameter),
                        formFieldList);
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
     * @return whether the criterion view is accessible for the given referential
     */
    private boolean isCriterionViewAccessible(Long webResourceId, String referentialParameter) {
        return authorizedRefForCriterionViewList.contains(referentialParameter) && 
                criterionStatisticsDataService.getCriterionStatisticsCountByWebResource(webResourceId) > 0;
    }
    
    /**
     * Regarding the audit type, collect data needed by the view.
     * 
     * @param webResource
     * @param audit
     * @param model
     * @param locale
     * @param exportFormat
     * @return
     * @throws IOException
     */
    private String prepareSuccessfullAuditData(
            WebResource webResource,
            Audit audit,
            Model model,
            String displayScope,
            Locale locale) {
        model.addAttribute(TgolKeyStore.LOCALE_KEY,locale);
        if (webResource instanceof Site) {
            return prepareSuccessfullSiteData((Site)webResource, audit, model);
        } else if (webResource instanceof Page) {
            //In case of display page result page, we need all data related with
            // the page, that's why a deepRead is needed
            return prepareSuccessfullPageData((Page)webResource, audit, model, displayScope);
        }
        throw new LostInSpaceException();
    }

    /**
     * This methods handles audit data in case of the audit is of site type
     * @param site
     * @param audit
     * @param model
     * @return
     * @throws IOException
     */
    private String prepareSuccessfullSiteData(Site site, Audit audit, Model model) {
        AuditResultSortCommand asuc = ((AuditResultSortCommand)model.asMap().get(TgolKeyStore.AUDIT_RESULT_SORT_COMMAND_KEY));
        model.addAttribute(TgolKeyStore.TEST_RESULT_LIST_KEY,
                TestResultFactory.getInstance().getTestResultSortedByThemeMap(
                            site,
                            getSiteScope(),
                            asuc.getSortOptionMap().get(themeSortKey).toString(),
                            getTestResultSortSelection(asuc)));
        
        // Attributes for breadcrumb
        Contract contract = retrieveContractFromAudit(audit);
        model.addAttribute(TgolKeyStore.AUDIT_ID_KEY, audit.getId());
        model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, contract.getId());
        model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
        model.addAttribute(TgolKeyStore.RESULT_ACTION_LIST_KEY, actionHandler.getActionList("EXPORT"));
        return TgolKeyStore.RESULT_SITE_VIEW_NAME;
    }

    /**
     * This methods handles audit data in case of page type audit 
     * 
     * @param page
     * @param audit
     * @param model
     * @param displayScope
     * @return
     * @throws IOException
     */
    private String prepareSuccessfullPageData(Page page, Audit audit, Model model, String displayScope) {

        Contract contract = retrieveContractFromAudit(audit);

        if (!audit.getStatus().equals(AuditStatus.COMPLETED)) {
            return prepareFailedAuditData(audit, model);
        }

        model.addAttribute(TgolKeyStore.STATUS_KEY, computeAuditStatus(audit));
        model.addAttribute(TgolKeyStore.RESULT_ACTION_LIST_KEY, actionHandler.getActionList("EXPORT"));
        // Attributes for breadcrumb
        model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, contract.getId());
        model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
        model.addAttribute(TgolKeyStore.AUDIT_ID_KEY, audit.getId());
        
        // Add a boolean used to display the breadcrumb. 
        model.addAttribute(TgolKeyStore.AUTHORIZED_SCOPE_FOR_PAGE_LIST, isAuthorizedScopeForPageList(audit));
        
        // Add a command to relaunch the audit from the result page
        model.addAttribute(TgolKeyStore.AUDIT_SET_UP_COMMAND_KEY, 
                AuditSetUpCommandFactory.getInstance().getPageAuditSetUpCommand(
                    contract, 
                    page.getURL(), 
                    getParameterDataService().getParameterSetFromAudit(audit)));
        
        if (StringUtils.equalsIgnoreCase(displayScope, TgolKeyStore.TEST_DISPLAY_SCOPE_VALUE)) {
            AuditResultSortCommand asuc = ((AuditResultSortCommand)model.asMap().get(TgolKeyStore.AUDIT_RESULT_SORT_COMMAND_KEY));
            
            model.addAttribute(TgolKeyStore.TEST_RESULT_LIST_KEY,
                TestResultFactory.getInstance().getTestResultSortedByThemeMap(
                    page,
                    getPageScope(),
                    asuc.getSortOptionMap().get(themeSortKey).toString(),
                    getTestResultSortSelection(asuc)));             
            return TgolKeyStore.RESULT_PAGE_VIEW_NAME;
        } else {
            AuditResultSortCommand asuc = ((AuditResultSortCommand)model.asMap().get(TgolKeyStore.AUDIT_RESULT_SORT_COMMAND_KEY));
            model.addAttribute(TgolKeyStore.CRITERION_RESULT_LIST_KEY,
                CriterionResultFactory.getInstance().getCriterionResultSortedByThemeMap(
                    page,
                    asuc.getSortOptionMap().get(themeSortKey).toString(),
                    getTestResultSortSelection(asuc))); 
            return TgolKeyStore.RESULT_PAGE_BY_CRITERION_VIEW_NAME;
        }
    }

    private Collection<String> getTestResultSortSelection (AuditResultSortCommand asuc) {
        Collection<String> selectedValues = new HashSet<String>();
        if ((asuc.getSortOptionMap().get(testResultSortKey)) instanceof Object[]) {
            CollectionUtils.addAll(selectedValues, ((Object[])asuc.getSortOptionMap().get(testResultSortKey)));
        } else if ((asuc.getSortOptionMap().get(testResultSortKey)) instanceof String) {
            selectedValues.add((String)asuc.getSortOptionMap().get(testResultSortKey));
        }
        return selectedValues;
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
     * This methods call the highlighter service and returns
     * the highlighted code.
     * @param ssp
     * @return
     * @throws IOException
     */
    private String highlightSourceCode(SSP ssp) {
        if (ssp !=null && StringUtils.isNotBlank(ssp.getDoctype()) ) {    
            return highlighter.highlightSourceCode(ssp.getDoctype(),ssp.getAdaptedContent());
        } else {
            return highlighter.highlightSourceCode(ssp.getAdaptedContent());
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
    private String computeDisplayScope(HttpServletRequest request, AuditResultSortCommand arsc) {
        if (StringUtils.contains(request.getHeader(REFERER_HEADER_KEY), CRITERION_RESULT_PAGE_KEY)) {
            return TgolKeyStore.CRITERION_DISPLAY_SCOPE_VALUE;
        }
        if (arsc != null) {
            return arsc.getDisplayScope();
        }
        return TgolKeyStore.TEST_DISPLAY_SCOPE_VALUE;
    }

}