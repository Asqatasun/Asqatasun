/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.webapp.controller;

import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.AuditStatus;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.reference.Scope;
import org.asqatasun.entity.service.audit.AuditDataService;
import org.asqatasun.entity.service.audit.ContentDataService;
import org.asqatasun.entity.service.parameterization.ParameterDataService;
import org.asqatasun.entity.service.reference.ScopeDataService;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.webapp.dto.AuditStatistics;
import org.asqatasun.webapp.dto.factory.AuditStatisticsFactory;
import org.asqatasun.webapp.entity.contract.Act;
import org.asqatasun.webapp.entity.contract.Contract;
import org.asqatasun.webapp.entity.service.contract.ActDataService;
import org.asqatasun.webapp.entity.user.User;
import org.asqatasun.webapp.exception.ForbiddenPageException;
import org.asqatasun.webapp.exception.ForbiddenUserException;
import org.asqatasun.webapp.exception.OrphanWebResourceException;
import org.asqatasun.webapp.report.pagination.factory.TgolPaginatedListFactory;
import org.asqatasun.webapp.util.HttpStatusCodeFamily;
import org.asqatasun.webapp.util.TgolKeyStore;
import org.displaytag.pagination.PaginatedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;

import javax.annotation.PostConstruct;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.asqatasun.webapp.util.TgolKeyStore.*;

/**
 * This abstract controller handles methods to retrieve and format audit data
 * @author jkowalczyk
 */
public abstract class AbstractAuditDataHandlerController extends AbstractController {

    private static final int pageScopeId = 1;
    private static final int siteScopeId = 2;
    protected static final String FROM_VALUE = "fromValue";
    protected static final String TO_VALUE = "toValue";

    protected Scope siteScope;
    protected Scope pageScope;

    private List<Integer> authorizedPageSize = new ArrayList<>();
    @Value("${app.webapp.ui.config.authorizedPageSize}")
    private List<String> authorizedPageSizeStr;
    @Value("${app.webapp.ui.config.authorizedScopeForCriterionList}")
    protected List<String> authorizedSortCriterion;
    @Value("${app.webapp.ui.config.authorizedScopeForPageList}")
    private List<String> authorizedScopeForPageList;

    private Set<Parameter> defaultParamSet;
    public Set<Parameter> getDefaultParamSet() {
        return ((Set) ((HashSet) defaultParamSet).clone());
    }

    @Autowired
    protected ScopeDataService scopeDataService;
    @Autowired
    protected ActDataService actDataService;
    @Autowired
    protected ContentDataService contentDataService;
    @Autowired
    protected AuditStatisticsFactory auditStatisticsFactory;
    @Autowired
    protected ParameterDataService parameterDataService;
    @Autowired
    protected AuditDataService auditDataService;
    @Autowired
    protected TgolPaginatedListFactory tgolPaginatedListFactory;

    private static final String INVALID_TEST_VALUE_CHECKER_REGEXP = "\\d\\d?\\.\\d\\d?\\.?\\d?\\d?";

    private static final Pattern invalidTestValueCheckerPattern =
        Pattern.compile(INVALID_TEST_VALUE_CHECKER_REGEXP);

    /**
     * This method initializes the siteScope and the pageScope instances through
     * their persistence Id.
     */
    @PostConstruct
    protected void init() {
        siteScope = scopeDataService.read((long) siteScopeId);
        pageScope = scopeDataService.read((long) pageScopeId);
        defaultParamSet = parameterDataService.getDefaultParameterSet();
        authorizedPageSizeStr.forEach(size -> authorizedPageSize.add(Integer.valueOf(size)));
    }

    public Map<String,String> getParametersToDisplay() {
        return Stream.of(new String[][] {
            { "LEVEL", "level" },
            { "SCREEN_HEIGHT", "screen-height" },
            { "SCREEN_WIDTH", "screen-width" },
            { "ALTERNATIVE_CONTRAST_MECHANISM", "contrast-alternative" },
            { "INFORMATIVE_IMAGE_MARKER", "informative-image" },
            { "DECORATIVE_IMAGE_MARKER", "decorative-image" },
            { "PRESENTATION_TABLE_MARKER", "presentation-table" },
            { "COMPLEX_TABLE_MARKER", "complex-table" },
            { "DATA_TABLE_MARKER", "data-table" },
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    }

    protected boolean isAuthorizedScopeForPageList(Audit audit) {
        String scope = actDataService.getActFromAudit(audit).getScope().getCode().name();
        return authorizedScopeForPageList.contains(scope);
    }

    /**
     * Add a populated auditStatistics instance to the model
     * 
     * @param webResource
     * @param model
     * @param displayScope
     */
    protected void addAuditStatisticsToModel(WebResource webResource, Model model, String displayScope) {
        model.addAttribute(
                STATISTICS_KEY,
                getAuditStatistics(webResource, displayScope, false)); // default is false for manual audit
    }

    /**
     * 
     * @param webResource
     * @param displayScope
     * @param isAuditManual 
     * @return
     */
    protected AuditStatistics getAuditStatistics(WebResource webResource, String displayScope, boolean isAuditManual){
        return auditStatisticsFactory.getAuditStatistics(
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
        Contract contract = actDataService.getActFromAudit(audit).getContract();
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
            act = actDataService.getActFromAudit(audit);
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
            return COMPLETED_KEY;
        } else if (!contentDataService.hasContent(audit)) {
            return ERROR_LOADING_KEY;
        } else if (!contentDataService.hasAdaptedSSP(audit)) {
            return ERROR_ADAPTING_KEY;
        } else {
            return ERROR_UNKNOWN_KEY;
        }
    }

    /**
     * This method determines which page to display when an error occured
     * while processing
     * @param auditId
     * @param model
     * @return
     */
    protected String prepareFailedAuditData(long auditId, Model model) {
        String returnViewName = OUPS_VIEW_NAME;
        Audit audit = auditDataService.read(auditId);
        model.addAttribute(AUDIT_URL_KEY, audit.getSubject().getURL());
        model.addAttribute(AUDIT_DATE_KEY, audit.getDateOfCreation());
        String status = this.computeAuditStatus(audit);
        if (status.equalsIgnoreCase(ERROR_LOADING_KEY)) {
            returnViewName = LOADING_ERROR_VIEW_NAME;
        } else if (status.equalsIgnoreCase(ERROR_ADAPTING_KEY)) {
            returnViewName = ADAPTING_ERROR_VIEW_NAME;
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
        
        String invalidTest =
            ServletRequestUtils.getStringParameter(request, TgolPaginatedListFactory.INVALID_TEST_PARAM);
        
        if (invalidTest != null && !this.invalidTestValueCheckerPattern.matcher(invalidTest).matches()) {
            throw new ForbiddenPageException();
        }

        PaginatedList paginatedList = tgolPaginatedListFactory.getPaginatedList(
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

        model.addAttribute(PAGE_LIST_KEY, paginatedList);
        model.addAttribute(AUTHORIZED_PAGE_SIZE_KEY, authorizedPageSize);
        model.addAttribute(AUTHORIZED_SORT_CRITERION_KEY, authorizedSortCriterion);
        setFromToValues(paginatedList, model);
        
        // don't forge to add audit statistics to model
//        addAuditStatisticsToModel(audit, model, TEST_DISPLAY_SCOPE_VALUE);
        return (returnRedirectView) ? PAGE_LIST_XXX_VIEW_REDIRECT_NAME : PAGE_LIST_XXX_VIEW_NAME;
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
