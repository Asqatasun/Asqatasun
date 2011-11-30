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
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    
    private static final String PAGE_LIST_H1_XXX_KEY = "pageList.f2xx.h1";
    private static final String PAGE_LIST_H1_XXX_WITH_EXT_KEY = "pageList.f2xx.forTheAudit.h1";

    private ActionHandler actionHandler;
    public ActionHandler getActionHandler() {
        return actionHandler;
    }

    public void setActionHandler(ActionHandler contractActionHandler) {
        this.actionHandler = contractActionHandler;
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
            HttpServletResponse response,
            Model model) {
        User user = getCurrentUser();
        model.addAttribute(TgolKeyStore.AUTHENTICATED_USER_KEY,user);
        //We first check that the current user is allowed to display the result
        //of this audit
        Long wrId = Long.valueOf(webresourceId);
        WebResource webResource = getWebResourceDataService().ligthRead(wrId);
        if (isUserAllowedToDisplayResult(user,webResource)) {
            if (webResource != null) {
                this.callGc(webResource);
                // If the Id given in argument correspond to a webResource,
                // data are retrieved to be prepared and displayed
                return prepareSuccessfullAuditData(
                        webResource,
                        model,
                        getLocaleResolver().resolveLocale(request));
            } else {
                // If the Id given in argument does not correspond to any
                // webresource, a 404 page is returned
                try {
                    response.sendError(HttpStatus.SC_NOT_FOUND);
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(
                        AuditResultController.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            }
        } else {
            return TgolKeyStore.ACCESS_DENIED_VIEW_NAME;
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
            addAuditStatisticsToModel(webResource, model);
            return prepareSuccessfullSiteData((Site)webResource, model);
        } else if (webResource instanceof Page) {
            addAuditStatisticsToModel(webResource, model);
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
        model.addAttribute(TgolKeyStore.TEST_RESULT_LIST_KEY,
                TestResultFactory.getInstance().getTestResultSortedByThemeMap(site, getSiteScope(), hasSourceCodeWithDoctype, true));
        
        model.addAttribute(TgolKeyStore.BREAD_CRUMB_KEY,
                buildSiteResultBreadCrumb(
                    null,
                    site.getId(),
                    retrieveContractFromWebResource(site)));
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
        if (!audit.getStatus().equals(AuditStatus.COMPLETED)) {
            String errorViewName = prepareFailedAuditData(audit, model);
            if (!isGuestUser()) {
                model.addAttribute(TgolKeyStore.BREAD_CRUMB_KEY,
                    buildPageResultBreadCrumb(
                        null,
                        page.getId(),
                        retrieveContractFromWebResource(page)));
            }
            return errorViewName;
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
            model.addAttribute(TgolKeyStore.TEST_RESULT_LIST_KEY,
                TestResultFactory.getInstance().getTestResultSortedByThemeMap(page, getPageScope(), hasSourceCodeWithDoctype, true));
        }
        model.addAttribute(TgolKeyStore.STATUS_KEY, computeAuditStatus(audit));
        model.addAttribute(TgolKeyStore.RESULT_ACTION_LIST_KEY, actionHandler.getActionList("EXPORT"));
        model.addAttribute(TgolKeyStore.BREAD_CRUMB_KEY,
                buildPageResultBreadCrumb(
                    null,
                    page.getId(),
                    retrieveContractFromWebResource(page)));
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

    /**
     * This methods builds the breadcrumb for one result page of site audit.
     * My Project - Project Name -Synthesis - Page List - auditResult
     * @param contractId
     * @param webResourceId
     * @return
     */
    private Map<String, String> buildPageResultBreadCrumb(Long contractId, Long webResourceId, Contract contract) {
        Map<String, String> breadCrumb = null;
        Long parentWrId= getWebResourceDataService().getParentWebResourceId(webResourceId);
        if (parentWrId != null) {
            boolean isAuthorizedScopeForPageList= isAuthorizedScopeForPageList(getWebResourceDataService().ligthRead(parentWrId));
            breadCrumb = PageListController.buildPageListxxxBreadCrumb(
                    contractId,
                    parentWrId,
                    contract,
                    isAuthorizedScopeForPageList);
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(TgolKeyStore.PAGE_LIST_CONTRACT_URL);
            urlBuilder.append(TgolKeyStore.HTML_EXTENSION_KEY);
            urlBuilder.append("?");
            urlBuilder.append(TgolKeyStore.WEBRESOURCE_ID_KEY);
            urlBuilder.append("=");
            urlBuilder.append(parentWrId);
            urlBuilder.append("&amp;");
            urlBuilder.append(TgolKeyStore.STATUS_KEY);
            urlBuilder.append("=f2xx");
            if (isAuthorizedScopeForPageList) {
                breadCrumb.put(urlBuilder.toString(),PAGE_LIST_H1_XXX_KEY);
            } else {
                breadCrumb.put(urlBuilder.toString(),PAGE_LIST_H1_XXX_WITH_EXT_KEY+";"+parentWrId);
            }
        } else {
            breadCrumb = HomeController.buildBreadCrumb();
            StringBuilder urlBuilder = new StringBuilder();
            urlBuilder.append(TgolKeyStore.CONTRACT_URL);
            urlBuilder.append(TgolKeyStore.HTML_EXTENSION_KEY);
            urlBuilder.append("?");
            urlBuilder.append(TgolKeyStore.CONTRACT_ID_KEY);
            urlBuilder.append("=");
            urlBuilder.append(contract.getId());
            breadCrumb.put(urlBuilder.toString(),contract.getLabel());
        }
        return breadCrumb;
    }

    /**
     * This methods builds the breadcrumb for one result page of site audit.
     * My Project - Project Name -Synthesis - auditResult
     * @param contractId
     * @param webResourceId
     * @return
     */
    private Map<String, String> buildSiteResultBreadCrumb(
            Long contractId,
            Long webResourceId,
            Contract contract) {
        return PageListController.buildPageListBreadCrumb(
                contractId,
                webResourceId,
                contract);
    }

}