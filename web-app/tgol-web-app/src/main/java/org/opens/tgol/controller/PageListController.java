/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.exception.AuditParameterMissingException;
import org.opens.tgol.exception.ForbiddenPageException;
import org.opens.tgol.exception.ForbiddenScopeException;
import org.opens.tgol.report.pagination.factory.TgolPaginatedListFactory;
import org.opens.tgol.util.HttpStatusCodeFamily;
import org.opens.tgol.util.TgolKeyStore;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author jkowalczyk
 */
@Controller
public class PageListController extends AbstractAuditDataHandlerController {

    private static final Logger LOGGER = Logger.getLogger(PageListController.class);

    public PageListController() {
        super();
    }

    /**
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws java.lang.Exception
     */
    @RequestMapping(value = TgolKeyStore.PAGE_LIST_CONTRACT_URL, method = RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayPageList(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) throws Exception {
        String auditId = ServletRequestUtils.getStringParameter(request, TgolKeyStore.AUDIT_ID_KEY);
        if (auditId == null) {
            throw new AuditParameterMissingException();
        }
        Audit audit;
        try {
            audit = getAuditDataService().read(Long.valueOf(auditId));
        } catch (NumberFormatException e) {
            throw new ForbiddenPageException(e);
        }

        if (isUserAllowedToDisplayResult(audit)) {
            return pageLinkDispatcher(request, audit, model);
        } else {
            // this block can never be reached. the "isUserAllowedToDisplayResult"
            // method returns true or throws an exception
            return TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        }
    }

    /**
     * This method dispatches the result depending on the parameters passed to
     * the request. Only multi-pages audit are considered here.
     *
     * @param request
     * @param webResource
     * @param model
     * @return
     * @throws Exception
     */
    private String pageLinkDispatcher(
            HttpServletRequest request,
            Audit audit,
            Model model) throws Exception {

        if (audit.getSubject() instanceof Page) {
            throw new ForbiddenPageException();
        }
        String status = ServletRequestUtils.getStringParameter(request, TgolKeyStore.STATUS_KEY);
        HttpStatusCodeFamily httpStatusCode = getHttpStatusCodeFamily(status);
        // If the status parameter is absent, we want to display the page with
        // the repartion of the pages regarding the httpStatusCode
        if (httpStatusCode == null) {
            if (!isAuthorizedScopeForPageList(audit)) {
                throw new ForbiddenScopeException();
            }
            try {
                Contract currentContract = retrieveContractFromAudit(audit);
                model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, currentContract.getLabel());
                model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, currentContract.getId());
                String testLabel = ServletRequestUtils.getStringParameter(request, TgolKeyStore.TEST_KEY);
                if (StringUtils.isNotBlank(testLabel)) {
                    model.addAttribute(TgolKeyStore.TEST_CODE_KEY, getTestDataService().getTestFromAuditAndLabel(audit, testLabel));
                }
                return this.preparePageListData(audit, model);
            } catch (ServletRequestBindingException e) {
                return TgolKeyStore.OUPS_VIEW_REDIRECT_NAME;
            }
        } else {
            boolean isAuthorizedScopeForPageList = isAuthorizedScopeForPageList(audit);
            Contract currentContract = retrieveContractFromAudit(audit);
            model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY, currentContract.getLabel());
            model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY, currentContract.getId());
            // when this page is displayed from a group of pages audit, it has
            // to indicate the audit number. So we add an attribute that can be
            // used in the jsp
            if (!isAuthorizedScopeForPageList) {
                model.addAttribute(
                        TgolKeyStore.AUDIT_NUMBER_KEY, true);
            }
            String testLabel = ServletRequestUtils.getStringParameter(request, TgolKeyStore.TEST_KEY);
            if (StringUtils.isNotBlank(testLabel)) {
                model.addAttribute(TgolKeyStore.TEST_CODE_KEY, getTestDataService().getTestFromAuditAndLabel(audit, testLabel));
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
                TgolKeyStore.AUDITED_PAGES_COUNT_KEY,
                getWebResourceCount(audit.getId(), HttpStatusCodeFamily.f2xx));
        model.addAttribute(
                TgolKeyStore.REDIRECTED_PAGES_COUNT_KEY,
                getWebResourceCount(audit.getId(), HttpStatusCodeFamily.f3xx));
        model.addAttribute(
                TgolKeyStore.ERROR_PAGES_COUNT_KEY,
                getWebResourceCount(audit.getId(), HttpStatusCodeFamily.f4xx));
        model.addAttribute(
                TgolKeyStore.REL_CANONICAL_PAGES_COUNT_KEY,
                getWebResourceCount(audit.getId(), HttpStatusCodeFamily.f9xx));
        addAuditStatisticsToModel(audit.getSubject(), model, TgolKeyStore.TEST_DISPLAY_SCOPE_VALUE);
        return TgolKeyStore.PAGE_LIST_VIEW_NAME;
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
        getAuthorizedSortCriterion().clear();
        getAuthorizedSortCriterion().add(TgolPaginatedListFactory.getInstance().getUrlSortCriterion());
        if (status.equalsIgnoreCase(HttpStatusCodeFamily.f2xx.name())) {
            getAuthorizedSortCriterion().add(TgolPaginatedListFactory.getInstance().getDefault2xxSortCriterion());
            getAuthorizedSortCriterion().add(TgolPaginatedListFactory.getInstance().getUrlSortCriterion());
            getAuthorizedSortCriterion().add(TgolPaginatedListFactory.getInstance().getRankCriterion());
            return HttpStatusCodeFamily.f2xx;
        } else if (status.equalsIgnoreCase(HttpStatusCodeFamily.f3xx.name())) {
            getAuthorizedSortCriterion().add(TgolPaginatedListFactory.getInstance().getDefault3xxSortCriterion());
            return HttpStatusCodeFamily.f3xx;
        } else if (status.equalsIgnoreCase(HttpStatusCodeFamily.f4xx.name())) {
            getAuthorizedSortCriterion().add(TgolPaginatedListFactory.getInstance().getDefault3xxSortCriterion());
            return HttpStatusCodeFamily.f4xx;
        } else if (status.equalsIgnoreCase(HttpStatusCodeFamily.f9xx.name())) {
            getAuthorizedSortCriterion().add(TgolPaginatedListFactory.getInstance().getDefault3xxSortCriterion());
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
        return getWebResourceDataService().getWebResourceCountByAuditAndHttpStatusCode(
                idAudit,
                hpcf,
                null,
                null).intValue();
    }
}