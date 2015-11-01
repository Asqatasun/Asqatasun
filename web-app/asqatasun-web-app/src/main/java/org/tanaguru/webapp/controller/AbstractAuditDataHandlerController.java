/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.webapp.controller;

import java.util.*;
import java.util.regex.Pattern;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import org.displaytag.pagination.PaginatedList;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.AuditStatus;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.entity.reference.Scope;
import org.tanaguru.entity.service.audit.AuditDataService;
import org.tanaguru.entity.service.audit.ContentDataService;
import org.tanaguru.entity.service.parameterization.ParameterDataService;
import org.tanaguru.entity.service.reference.ScopeDataService;
import org.tanaguru.entity.service.reference.TestDataService;
import org.tanaguru.entity.service.subject.WebResourceDataService;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.webapp.entity.contract.Act;
import org.tanaguru.webapp.entity.contract.Contract;
import org.tanaguru.webapp.entity.decorator.tanaguru.parameterization.ParameterDataServiceDecorator;
import org.tanaguru.webapp.entity.service.contract.ActDataService;
import org.tanaguru.webapp.entity.service.statistics.StatisticsDataService;
import org.tanaguru.webapp.entity.user.User;
import org.tanaguru.webapp.exception.ForbiddenPageException;
import org.tanaguru.webapp.exception.ForbiddenUserException;
import org.tanaguru.webapp.exception.OrphanWebResourceException;
import org.tanaguru.webapp.presentation.data.AuditStatistics;
import org.tanaguru.webapp.presentation.factory.AuditStatisticsFactory;
import org.tanaguru.webapp.report.pagination.factory.TgolPaginatedListFactory;
import org.tanaguru.webapp.util.HttpStatusCodeFamily;
import org.tanaguru.webapp.util.TgolKeyStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.LocaleResolver;


/**
 * This abstract controller handles methods to retrieve and format audit data
 * @author jkowalczyk
 */
@Controller
public abstract class AbstractAuditDataHandlerController extends AbstractController {

    private int pageScopeId = 1;
    public void setPageScopeId(int pageScopeId) {
        this.pageScopeId = pageScopeId;
    }

    private int siteScopeId = 2;
    public void setSiteScopeId(int siteScopeId) {
        this.siteScopeId = siteScopeId;
    }

    private Scope siteScope;
    /**
     *
     * @return the scope instance
     */
    public Scope getSiteScope() {
        return siteScope;
    }

    private Scope pageScope;
    public Scope getPageScope() {
        return pageScope;
    }

    /*
     * Displaying bounds
     */
    protected static final String FROM_VALUE = "fromValue";
    protected static final String TO_VALUE = "toValue";

    /*
     * Authorized elements depending on the context.
     */
    private final Set<Integer> authorizedPageSize = new LinkedHashSet<>();
    public Set<Integer> getAuthorizedPageSize() {
        return authorizedPageSize;
    }
    
    public final void setAuthorizedPageSizeList(Set<String> authorizedPageSizeList) {
        for (String size : authorizedPageSizeList) {
            this.authorizedPageSize.add(Integer.valueOf(size));
        }
    }

    private final Set<String> authorizedSortCriterion = new LinkedHashSet<>();
    public Set<String> getAuthorizedSortCriterion() {
        return authorizedSortCriterion;
    }

    /**
     * This method initializes the siteScope and the pageScope instances through
     * their persistence Id.
     * @param scopeDataService
     */
    @Autowired
    public final void setScopeDataService(ScopeDataService scopeDataService) {
        siteScope = scopeDataService.read(Long.valueOf(siteScopeId));
        pageScope = scopeDataService.read(Long.valueOf(pageScopeId));
    }

    private WebResourceDataService webResourceDataService;
    public WebResourceDataService getWebResourceDataService() {
        return webResourceDataService;
    }

    @Autowired
    public final void setWebResourceDataService(WebResourceDataService webResourceDataService) {
        this.webResourceDataService = webResourceDataService;
    }
    
    private StatisticsDataService statisticsDataService;
    public StatisticsDataService getStatisticsDataService() {
        return statisticsDataService;
    }

    @Autowired
    public final void setStatisticsDataService(StatisticsDataService statisticsDataService) {
        this.statisticsDataService = statisticsDataService;
    }

    /**
     * The AuditDataService
     */
    private AuditDataService auditDataService;
    public AuditDataService getAuditDataService() {
        return auditDataService;
    }
    
    @Autowired
    public void setAuditDataService(AuditDataService auditDataService) {
        this.auditDataService = auditDataService;
    }
    
    private ActDataService actDataService;
    public ActDataService getActDataService() {
        return actDataService;
    }

    @Autowired
    public final void setActDataService(ActDataService actDataService) {
        this.actDataService = actDataService;
    }

    private ContentDataService contentDataService;
    public ContentDataService getContentDataService() {
        return contentDataService;
    }

    @Autowired
    public final void setContentDataService(ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }

    private TestDataService testDataService;
    public TestDataService getTestDataService() {
        return testDataService;
    }

    @Autowired
    public final void setTestDataService(TestDataService testDataService) {
        this.testDataService = testDataService;
    }

    private ParameterDataServiceDecorator parameterDataService;
    public ParameterDataServiceDecorator getParameterDataService() {
        return parameterDataService;
    }

    @Autowired
    public final void setParameterDataService(ParameterDataServiceDecorator parameterDataService) {
        this.parameterDataService = parameterDataService;
        // the audit Set up factory needs to be initialised with the unique instance
        // of ParameterDataServiceDecorator
        setDefaultParamSet(parameterDataService);
    }

    private Set<Parameter> defaultParamSet;
    public Set<Parameter> getDefaultParamSet() {
        return ((Set) ((HashSet) defaultParamSet).clone());
    }

    public final void setDefaultParamSet(ParameterDataService parameterDataService) {
        this.defaultParamSet = parameterDataService.getDefaultParameterSet();
    }

    private final Map<String, String> parametersToDisplay = new LinkedHashMap<>();
    public Map<String, String> getParametersToDisplay() {
        return parametersToDisplay;
    }

    public void setParametersToDisplay(Map<String, String> parametersToDisplay) {
        this.parametersToDisplay.putAll(parametersToDisplay);
    }

    private LocaleResolver localeResolver;
    public LocaleResolver getLocaleResolver() {
        return localeResolver;
    }

    private final List<String> authorizedScopeForPageList = new ArrayList<>();
    public void setAuthorizedScopeForPageList(List<String> authorizedScopeForPageList) {
        this.authorizedScopeForPageList.addAll(authorizedScopeForPageList);
    }

    public List<String> getAuthorizedScopeForPageList() {
        return authorizedScopeForPageList;
    }

    protected boolean isAuthorizedScopeForPageList(Audit audit) {
        String scope = getActDataService().getActFromAudit(audit).getScope().getCode().name();
        return authorizedScopeForPageList.contains(scope);
    }

    @Autowired
    public final void setLocaleResolver(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }

    private static final String INVALID_TEST_VALUE_CHECKER_REGEXP = "\\d\\d?\\.\\d\\d?\\.?\\d?\\d?";
    private final Pattern invalidTestValueCheckerPattern = Pattern.compile(INVALID_TEST_VALUE_CHECKER_REGEXP);
    
    public AbstractAuditDataHandlerController() {}

    /**
     * Add a populated auditStatistics instance to the model
     * 
     * @param webResource
     * @param model
     * @param displayScope
     */
    protected void addAuditStatisticsToModel(WebResource webResource, Model model, String displayScope) {
        model.addAttribute(
                TgolKeyStore.STATISTICS_KEY,
                getAuditStatistics(webResource, model, displayScope, false)); // default is false for manual audit
    }

    /**
     * 
     * @param webResource
     * @param model
     * @param displayScope
     * @param isAuditManual 
     * @return
     */
    protected AuditStatistics getAuditStatistics(WebResource webResource, Model model, String displayScope, boolean isAuditManual){
        return AuditStatisticsFactory.getInstance().getAuditStatistics(
                webResource, 
                getParametersToDisplay(),
                displayScope,
                isAuditManual);
    }

    /**
     * This methods checks whether the current user is allowed to display the
     * audit result of a given audit. To do so, we verify that the act
     * associated with the audit belongs to the current user and
     * that the current contract is not expired
     * 
     * @param audit
     * @return
     *      true if the user is allowed to display the result, false otherwise.
     */
        protected boolean isUserAllowedToDisplayResult(Audit audit) {
        if (audit == null) {
            throw new ForbiddenPageException();
        }
        User user = getCurrentUser();
        Contract contract = getActDataService().getActFromAudit(audit).getContract();
        if (isAdminUser() || (!isContractExpired(contract) && user.getId().compareTo(
                contract.getUser().getId()) == 0)) {
            return true;
        }
        throw new ForbiddenUserException();
    }
        
    /**
     * 
     * @param webResource
     * @return an audit for a given webResource
     */
    protected Audit getAuditFromWebResource(WebResource webResource) {
        if (webResource.getAudit() != null) {
            return webResource.getAudit();
        } else if (webResource.getParent() != null) {
            return webResource.getParent().getAudit();
        }
        throw new OrphanWebResourceException();
    }
    
    /**
     * @param audit
     * @return The Contract associated with the given audit (through the 
     * Act associated with the given audit).
     *
     */
    protected Contract retrieveContractFromAudit(Audit audit) {
        Act act = null;
        try {
            act = getActDataService().getActFromAudit(audit);
        } catch (NoResultException e) {}
        if (act!= null && act.getContract() != null) {
            return act.getContract();
        }
        return null;
    }

    /**
     * 
     * @param audit
     * @return
     */
    protected String computeAuditStatus(Audit audit) {
        if (audit.getStatus().equals(AuditStatus.COMPLETED)) {
            return TgolKeyStore.COMPLETED_KEY;
        } else if (!contentDataService.hasContent(audit)) {
            return TgolKeyStore.ERROR_LOADING_KEY;
        } else if (!contentDataService.hasAdaptedSSP(audit)) {
            return TgolKeyStore.ERROR_ADAPTING_KEY;
        } else {
            return TgolKeyStore.ERROR_UNKNOWN_KEY;
        }
    }

    /**
     * This method determines which page to display when an error occured
     * while processing
     * @param audit
     * @param model
     * @return
     */
    protected String prepareFailedAuditData(Audit audit, Model model) {
        String returnViewName = TgolKeyStore.OUPS_VIEW_NAME;
        model.addAttribute(TgolKeyStore.AUDIT_URL_KEY,
                audit.getSubject().getURL());
        model.addAttribute(TgolKeyStore.AUDIT_DATE_KEY,
                audit.getDateOfCreation());
        String status = this.computeAuditStatus(audit);
        if (status.equalsIgnoreCase(TgolKeyStore.ERROR_LOADING_KEY)) {
            returnViewName = TgolKeyStore.LOADING_ERROR_VIEW_NAME;
        } else if (status.equalsIgnoreCase(TgolKeyStore.ERROR_ADAPTING_KEY)) {
            returnViewName = TgolKeyStore.ADAPTING_ERROR_VIEW_NAME;
        }
        return returnViewName;
    }

    /**
     * 
     * @param audit
     * @param model
     * @param httpStatusCode
     * @param request
     * @param returnRedirectView
     * @return
     * @throws ServletRequestBindingException 
     */
    protected String preparePageListStatsByHttpStatusCode(
            Audit audit,
            Model model,
            HttpStatusCodeFamily httpStatusCode,
            HttpServletRequest request,
            boolean returnRedirectView) throws ServletRequestBindingException {
        
        String invalidTest = ServletRequestUtils.getStringParameter(request, TgolPaginatedListFactory.INVALID_TEST_PARAM);
        
        if (invalidTest != null && !this.invalidTestValueCheckerPattern.matcher(invalidTest).matches()) {
            throw new ForbiddenPageException();
        }

        PaginatedList paginatedList = TgolPaginatedListFactory.getInstance().getPaginatedList(
                httpStatusCode,
                ServletRequestUtils.getStringParameter(request, TgolPaginatedListFactory.PAGE_SIZE_PARAM),
                ServletRequestUtils.getStringParameter(request, TgolPaginatedListFactory.SORT_DIRECTION_PARAM),
                ServletRequestUtils.getStringParameter(request, TgolPaginatedListFactory.SORT_CRITERION_PARAM),
                ServletRequestUtils.getStringParameter(request, TgolPaginatedListFactory.PAGE_PARAM),
                ServletRequestUtils.getStringParameter(request, TgolPaginatedListFactory.SORT_CONTAINING_URL_PARAM),
                invalidTest,
                authorizedPageSize,
                authorizedSortCriterion,
                audit.getId());

        model.addAttribute(TgolKeyStore.PAGE_LIST_KEY, paginatedList);
        model.addAttribute(TgolKeyStore.AUTHORIZED_PAGE_SIZE_KEY, authorizedPageSize);
        model.addAttribute(TgolKeyStore.AUTHORIZED_SORT_CRITERION_KEY, authorizedSortCriterion);
        setFromToValues(paginatedList, model);
        
        // don't forge to add audit statistics to model
//        addAuditStatisticsToModel(audit, model, TgolKeyStore.TEST_DISPLAY_SCOPE_VALUE);
        return (returnRedirectView) ? TgolKeyStore.PAGE_LIST_XXX_VIEW_REDIRECT_NAME : TgolKeyStore.PAGE_LIST_XXX_VIEW_NAME;
    }

    /**
     *
     * @param pageResultList
     * @param model
     * @return
     */
    private void setFromToValues(PaginatedList pageResultList, Model model) {
        model.addAttribute(FROM_VALUE,
                (pageResultList.getPageNumber()-1) * pageResultList.getObjectsPerPage() +1);
        if (pageResultList.getList().size() < pageResultList.getObjectsPerPage()) {
            model.addAttribute(TO_VALUE,
                    (pageResultList.getPageNumber()-1) * pageResultList.getObjectsPerPage() + pageResultList.getList().size());
        } else {
            model.addAttribute(TO_VALUE,
                    (pageResultList.getPageNumber()) * pageResultList.getObjectsPerPage());
        }
    }

}