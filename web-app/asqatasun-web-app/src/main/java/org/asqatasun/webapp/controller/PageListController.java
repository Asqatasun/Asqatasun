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

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.service.reference.TestDataService;
import org.asqatasun.entity.subject.Page;
import org.asqatasun.entity.contract.Contract;
import org.asqatasun.webapp.exception.AuditParameterMissingException;
import org.asqatasun.webapp.exception.ForbiddenPageException;
import org.asqatasun.webapp.exception.ForbiddenScopeException;
import org.asqatasun.webapp.report.pagination.factory.TgolPaginatedListFactory;
import org.asqatasun.webapp.statistics.service.StatisticsDataService;
import org.asqatasun.webapp.util.HttpStatusCodeFamily;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.asqatasun.webapp.util.TgolKeyStore.*;

/**
 *
 * @author jkowalczyk
 */
@Controller
public class PageListController extends AbstractAuditDataHandlerController {

    private final TestDataService testDataService;
    private final StatisticsDataService statisticsDataService;
    private final TgolPaginatedListFactory tgolPaginatedListFactory;

    public PageListController(TestDataService testDataService,
                              StatisticsDataService statisticsDataService,
                              TgolPaginatedListFactory tgolPaginatedListFactory) {
        super();
        this.testDataService = testDataService;
        this.statisticsDataService = statisticsDataService;
        this.tgolPaginatedListFactory = tgolPaginatedListFactory;
    }

    /**
     *
     * @param request
     * @param model
     * @return
     * @throws java.lang.Exception
     */
    @RequestMapping(value = PAGE_LIST_CONTRACT_URL, method = RequestMethod.GET)
    @Secured({ROLE_USER_KEY, ROLE_ADMIN_KEY})
    public String displayPageList(HttpServletRequest request, Model model) throws Exception {

        String auditId = ServletRequestUtils.getStringParameter(request, AUDIT_ID_KEY);
        if (auditId == null) {
            throw new AuditParameterMissingException();
        }
        Audit audit;
        try {
            audit = auditDataService.read(Long.valueOf(auditId));
        } catch (NumberFormatException e) {
            throw new ForbiddenPageException(e);
        }
        if (isUserAllowedToDisplayResult(audit)) {
            return pageLinkDispatcher(request, audit, model);
        } else {
            // this block can never be reached. the "isUserAllowedToDisplayResult"
            // method returns true or throws an exception
            return ACCESS_DENIED_VIEW_NAME;
        }
    }

    /**
     * This method dispatches the result depending on the parameters passed to
     * the request. Only multi-pages audit are considered here.
     *
     * @param request
     * @param model
     * @return
     * @throws Exception
     */
    private String pageLinkDispatcher(HttpServletRequest request, Audit audit, Model model) throws Exception {

        if (audit.getSubject() instanceof Page) {
            throw new ForbiddenPageException();
        }
        String status = ServletRequestUtils.getStringParameter(request, STATUS_KEY);
        HttpStatusCodeFamily httpStatusCode = getHttpStatusCodeFamily(status);
        // If the status parameter is absent, we want to display the page with
        // the repartion of the pages regarding the httpStatusCode
        if (httpStatusCode == null) {
            if (!isAuthorizedScopeForPageList(audit)) {
                throw new ForbiddenScopeException();
            }
            try {
                Contract currentContract = retrieveContractFromAudit(audit);
                model.addAttribute(CONTRACT_NAME_KEY, currentContract.getLabel());
                model.addAttribute(CONTRACT_ID_KEY, currentContract.getId());
                String testLabel = ServletRequestUtils.getStringParameter(request, TEST_KEY);
                if (StringUtils.isNotBlank(testLabel)) {
                    model.addAttribute(TEST_CODE_KEY, testDataService.getTestFromAuditAndLabel(audit, testLabel));
                }
                return this.preparePageListData(audit, model);
            } catch (ServletRequestBindingException e) {
                return OUPS_VIEW_REDIRECT_NAME;
            }
        } else {
            boolean isAuthorizedScopeForPageList = isAuthorizedScopeForPageList(audit);
            Contract currentContract = retrieveContractFromAudit(audit);
            model.addAttribute(CONTRACT_NAME_KEY, currentContract.getLabel());
            model.addAttribute(CONTRACT_ID_KEY, currentContract.getId());
            // when this page is displayed from a group of pages audit, it has
            // to indicate the audit number. So we add an attribute that can be
            // used in the jsp
            if (!isAuthorizedScopeForPageList) {
                model.addAttribute(
                        AUDIT_NUMBER_KEY, true);
            }
            String testLabel = ServletRequestUtils.getStringParameter(request, TEST_KEY);
            if (StringUtils.isNotBlank(testLabel)) {
                model.addAttribute(TEST_CODE_KEY, testDataService.getTestFromAuditAndLabel(audit, testLabel));
            }
            return this.preparePageListStatsByHttpStatusCode(
                    audit,
                    model,
                    httpStatusCode,
                    request,
                    false);
        }
    }

    /**
     * This method prepares data for the page list page
     *
     * @param audit
     * @param model
     * @return
     * @throws IOException
     */
    private String preparePageListData(Audit audit, Model model) {
        model.addAttribute(
                AUDITED_PAGES_COUNT_KEY,
                getWebResourceCount(audit.getId(), HttpStatusCodeFamily.f2xx));
        model.addAttribute(
                REDIRECTED_PAGES_COUNT_KEY,
                getWebResourceCount(audit.getId(), HttpStatusCodeFamily.f3xx));
        model.addAttribute(
                ERROR_PAGES_COUNT_KEY,
                getWebResourceCount(audit.getId(), HttpStatusCodeFamily.f4xx));
        model.addAttribute(
                REL_CANONICAL_PAGES_COUNT_KEY,
                getWebResourceCount(audit.getId(), HttpStatusCodeFamily.f9xx));
        addAuditStatisticsToModel(audit.getSubject(), model, TEST_DISPLAY_SCOPE_VALUE);
        return PAGE_LIST_VIEW_NAME;
    }

    /**
     *
     * @param status
     * @return
     */
    private HttpStatusCodeFamily getHttpStatusCodeFamily(String status) {
        if (status == null) {
            return null;
        }
        authorizedSortCriterion.clear();
        authorizedSortCriterion.add(tgolPaginatedListFactory.getUrlSortCriterion());
        if (status.equalsIgnoreCase(HttpStatusCodeFamily.f2xx.name())) {
            authorizedSortCriterion.add(tgolPaginatedListFactory.getDefault2xxSortCriterion());
            authorizedSortCriterion.add(tgolPaginatedListFactory.getUrlSortCriterion());
            authorizedSortCriterion.add(tgolPaginatedListFactory.getRankCriterion());
            return HttpStatusCodeFamily.f2xx;
        } else if (status.equalsIgnoreCase(HttpStatusCodeFamily.f3xx.name())) {
            authorizedSortCriterion.add(tgolPaginatedListFactory.getDefault3xxSortCriterion());
            return HttpStatusCodeFamily.f3xx;
        } else if (status.equalsIgnoreCase(HttpStatusCodeFamily.f4xx.name())) {
            authorizedSortCriterion.add(tgolPaginatedListFactory.getDefault3xxSortCriterion());
            return HttpStatusCodeFamily.f4xx;
        } else if (status.equalsIgnoreCase(HttpStatusCodeFamily.f9xx.name())) {
            authorizedSortCriterion.add(tgolPaginatedListFactory.getDefault3xxSortCriterion());
            return HttpStatusCodeFamily.f9xx;
        } else {
            return null;
        }
    }

    /**
     *
     * @param idAudit
     * @param hpcf
     * @return the number of webresource for a given http status code family
     */
    private int getWebResourceCount(
            Long idAudit,
            HttpStatusCodeFamily hpcf) {
        return statisticsDataService.getWebResourceCountByAuditAndHttpStatusCode(
                idAudit,
                hpcf,
                null,
                null).intValue();
    }
}
