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

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.entity.user.User;
import org.opens.tgol.report.pagination.factory.TgolPaginatedListFactory;
import org.opens.tgol.util.HttpStatusCodeFamily;
import org.opens.tgol.util.TgolKeyStore;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/** 
 *
 * @author jkowalczyk
 */
@Controller
public class PageListController extends AuditDataHandlerController{

    private static final Logger LOGGER = Logger.getLogger(PageListController.class);

    public PageListController() {
        super();
    }

    /**
     *
     * @param webresourceId
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value=TgolKeyStore.PAGE_LIST_CONTRACT_URL, method=RequestMethod.GET)
    @Secured(TgolKeyStore.ROLE_USER_KEY)
    public String displayPageList(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) throws Exception {
        String webResourceId = ServletRequestUtils.getStringParameter(request, TgolKeyStore.WEBRESOURCE_ID_KEY);
        User user = getCurrentUser();
        model.addAttribute(TgolKeyStore.AUTHENTICATED_USER_KEY,user);
        WebResource webResource;
        try {
            webResource =
                getWebResourceDataService().ligthRead(Long.valueOf(webResourceId));
        } catch (Exception e) {
            return TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        }
        if (isUserAllowedToDisplayResult(user,webResource)) {
            return pageLinkDispatcher(request, webResource, model);
        } else {
            // this block can never be reached. the "isUserAllowedToDisplayResult"
            // method returns true or throws an exception
            return TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        }
    }

    /**
     * This method dispatches the result depending on the parameters passed
     * to the request.
     *
     * @param request
     * @param webResource
     * @param model
     * @return
     * @throws Exception
     */
    private String pageLinkDispatcher(
            HttpServletRequest request,
            WebResource webResource,
            Model model) throws Exception {
        if (webResource instanceof Page) {
            return TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
        }
        String status = ServletRequestUtils.getStringParameter(request, TgolKeyStore.STATUS_KEY);
        HttpStatusCodeFamily httpStatusCode = getHttpStatusCodeFamily(status);
        // If the status parameter is absent, we want to display the page with
        // the repartion of the pages regarding the httpStatusCode
        if (httpStatusCode == null) {
            if (!isAuthorizedScopeForPageList(webResource)) {
                return TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
            }
            try {
                Contract currentContract = retrieveContractFromWebResource(webResource);
                model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY,currentContract.getLabel());
                model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY,currentContract.getId());
                return this.preparePageListData((Site)webResource, model);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                return TgolKeyStore.OUPS_VIEW_REDIRECT_NAME;
            }
        } else {
            boolean isAuthorizedScopeForPageList = isAuthorizedScopeForPageList(webResource);
            Contract currentContract = retrieveContractFromWebResource(webResource);
            model.addAttribute(TgolKeyStore.CONTRACT_NAME_KEY,currentContract.getLabel());
            model.addAttribute(TgolKeyStore.CONTRACT_ID_KEY,currentContract.getId());
            // when this page is displayed from a group of pages audit, it has
            // to indicate the audit number. So we add an attribute that can be
            // used in the jsp
            if (!isAuthorizedScopeForPageList) {
                model.addAttribute(
                    TgolKeyStore.AUDIT_NUMBER_KEY,true);
            }
            return this.preparePageListStatsByHttpStatusCode(
                    (Site)webResource,
                    model,
                    httpStatusCode,
                    request,
                    false);
        }
    }

   /**
     * This method prepares data for the page list page
     * @param site
     * @param model
     * @return
     * @throws IOException
     */
    private String preparePageListData(Site site, Model model) {
        model.addAttribute(
                TgolKeyStore.AUDITED_PAGES_COUNT_KEY,
                getWebResourceCount(site.getAudit().getId(), HttpStatusCodeFamily.f2xx));
        model.addAttribute(
                TgolKeyStore.REDIRECTED_PAGES_COUNT_KEY,
                getWebResourceCount(site.getAudit().getId(), HttpStatusCodeFamily.f3xx));
        model.addAttribute(
                TgolKeyStore.ERROR_PAGES_COUNT_KEY,
                getWebResourceCount(site.getAudit().getId(), HttpStatusCodeFamily.f4xx));
        addAuditStatisticsToModel(site, model);
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
        } else {
            return null;
        }
    }

    /**
     * 
     * @param idAudit
     * @param hpcf
     * @return
     *      the number of webresource for a given http status code family
     */
    private int getWebResourceCount(Long idAudit, HttpStatusCodeFamily hpcf) {
        return getWebResourceDataService().getWebResourceCountByAuditAndHttpStatusCode(
                idAudit,
                hpcf,
                null).intValue();
    }

}