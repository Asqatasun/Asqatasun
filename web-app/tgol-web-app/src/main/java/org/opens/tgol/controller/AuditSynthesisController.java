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

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tgol.entity.contract.Contract;
import org.opens.tgol.exception.ForbiddenPageException;
import org.opens.tgol.exception.ForbiddenUserException;
import org.opens.tgol.presentation.data.FailedThemeInfo;
import org.opens.tgol.presentation.data.ResultCounter;
import org.opens.tgol.presentation.factory.AuditStatisticsFactory;
import org.opens.tgol.presentation.factory.ResultCounterFactory;
import org.opens.tgol.util.HttpStatusCodeFamily;
import org.opens.tgol.util.TgolKeyStore;
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
public class AuditSynthesisController extends AuditDataHandlerController {

    private static final Logger LOGGER = Logger.getLogger(AuditSynthesisController.class);
    private int nbOfDisplayedFailedTest = 5;

    public void setNbOfDisplayedFailedTest(int nbOfDisplayedFailedTest) {
        this.nbOfDisplayedFailedTest = nbOfDisplayedFailedTest;
    }
    private int nbOfDisplayedFailedPages = 10;

    public void setNbOfDisplayedFailedPages(int nbOfDisplayedFailedPages) {
        this.nbOfDisplayedFailedPages = nbOfDisplayedFailedPages;
    }
    private List<String> authorizedScopeForSynthesis = new ArrayList<String>();

    public void setAuthorizedScopeForSynthesis(List<String> authorizedScopeForSynthesis) {
        this.authorizedScopeForSynthesis = authorizedScopeForSynthesis;
    }

    public List<String> getAuthorizedScopeForSynthesis() {
        return authorizedScopeForSynthesis;
    }

    public boolean isAuthorizedScopeForSynthesis(Audit audit) {
        if (audit.getSubject() instanceof Page) {
            return false;
        }
        String scope = getActDataService().getActFromAudit(audit).getScope().getCode().name();
        return (authorizedScopeForSynthesis.contains(scope)) ? true : false;
    }

    public AuditSynthesisController() {
        super();
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = TgolKeyStore.AUDIT_SYNTHESIS_CONTRACT_URL, method = RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayAuditSynthesisFromContract(
            @RequestParam(TgolKeyStore.AUDIT_ID_KEY) String auditId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        Long aId;
        try {
            aId = Long.valueOf(auditId);
        } catch (NumberFormatException nfe) {
            throw new ForbiddenPageException();
        }
        Audit audit = getAuditDataService().read(aId);
        if (isUserAllowedToDisplayResult(audit)) {
            if (isAuthorizedScopeForSynthesis(audit)) {
                Contract contract = retrieveContractFromAudit(audit);
                model.addAttribute(
                        TgolKeyStore.CONTRACT_ID_KEY, contract.getId());
                model.addAttribute(
                        TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
                model.addAttribute(TgolKeyStore.AUDIT_ID_KEY, auditId);
                model.addAttribute(TgolKeyStore.WEBRESOURCE_ID_KEY, audit.getSubject().getId());
                return prepareSynthesisSiteData(audit, model);

            } else {
                throw new ForbiddenPageException();
            }
        } else {
            throw new ForbiddenUserException();
        }
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = TgolKeyStore.FAILED_TEST_LIST_CONTRACT_URL, method = RequestMethod.GET)
    @Secured({TgolKeyStore.ROLE_USER_KEY, TgolKeyStore.ROLE_ADMIN_KEY})
    public String displayAuditTestSynthesisFromContract(
            @RequestParam(TgolKeyStore.AUDIT_ID_KEY) String auditId,
            HttpServletRequest request,
            HttpServletResponse response,
            Model model) {
        Long aId;
        try {
            aId = Long.valueOf(auditId);
        } catch (NumberFormatException nfe) {
            throw new ForbiddenPageException();
        }
        Audit audit = getAuditDataService().read(aId);
        if (isUserAllowedToDisplayResult(audit)) {
            if (isAuthorizedScopeForSynthesis(audit)) {
                Contract contract = retrieveContractFromAudit(audit);
                model.addAttribute(
                        TgolKeyStore.CONTRACT_ID_KEY, contract.getId());
                model.addAttribute(
                        TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
                model.addAttribute(TgolKeyStore.AUDIT_ID_KEY, auditId);
                model.addAttribute(TgolKeyStore.WEBRESOURCE_ID_KEY, audit.getSubject().getId());
                Site site = (Site) audit.getSubject();
                addAuditStatisticsToModel(site, model, TgolKeyStore.TEST_DISPLAY_SCOPE_VALUE);
                model.addAttribute(TgolKeyStore.FAILED_TEST_INFO_BY_OCCURRENCE_SET_KEY,
                        getWebResourceDataService().getFailedTestByOccurrence(site, audit, -1));
                model.addAttribute(TgolKeyStore.HAS_SITE_SCOPE_TEST_KEY,
                        getWebResourceDataService().hasAuditSiteScopeTest(site, getSiteScope()));
                model.addAttribute(TgolKeyStore.STATUS_KEY, computeAuditStatus(site.getAudit()));
                return TgolKeyStore.FAILED_TEST_LIST_VIEW_NAME;

            } else {
                throw new ForbiddenPageException();
            }
        } else {
            throw new ForbiddenUserException();
        }
    }

    /**
     * This method prepares data for the synthesis page. Only multi pages audit
     * are considered here
     *
     * @param audit
     * @param model
     * @return
     * @throws IOException
     */
    private String prepareSynthesisSiteData(Audit audit, Model model) {
        // Add this step, we are sure that the audit subject 
        // is a site, we can trustly cast it
        Site site = (Site) audit.getSubject();
        addAuditStatisticsToModel(site, model, TgolKeyStore.TEST_DISPLAY_SCOPE_VALUE);

        Map<Theme, ResultCounter> top5SortedThemeMap =
                new LinkedHashMap<Theme, ResultCounter>();
        @SuppressWarnings("unchecked")
        Collection<FailedThemeInfo> tfiCollection =
                (Collection<FailedThemeInfo>) getWebResourceDataService().
                getResultCountByResultTypeAndTheme(site, audit, TestSolution.FAILED, nbOfDisplayedFailedTest);

        for (FailedThemeInfo tfi : tfiCollection) {
            ResultCounter failedCounter = ResultCounterFactory.getInstance().getResultCounter();
            failedCounter.setFailedCount(tfi.getResultCounter().intValue());
            top5SortedThemeMap.put(AuditStatisticsFactory.getInstance().getTheme(tfi.getThemeId()), failedCounter);
        }

        model.addAttribute(
                TgolKeyStore.AUDITED_PAGES_COUNT_KEY,
                getWebResourceDataService().getWebResourceCountByAuditAndHttpStatusCode(
                audit.getId(),
                HttpStatusCodeFamily.f2xx,
                null,
                null).intValue());
        model.addAttribute(TgolKeyStore.TOP5_SORTED_THEME_MAP, top5SortedThemeMap);
        model.addAttribute(TgolKeyStore.FAILED_PAGE_INFO_BY_TEST_SET_KEY,
                getWebResourceDataService().getFailedWebResourceSortedByTest(site, audit, nbOfDisplayedFailedPages));
        model.addAttribute(TgolKeyStore.FAILED_PAGE_INFO_BY_OCCURRENCE_SET_KEY,
                getWebResourceDataService().getFailedWebResourceSortedByOccurrence(site, audit, nbOfDisplayedFailedPages));
        model.addAttribute(TgolKeyStore.FAILED_TEST_INFO_BY_OCCURRENCE_SET_KEY,
                getWebResourceDataService().getFailedTestByOccurrence(site, audit, nbOfDisplayedFailedTest));
        model.addAttribute(TgolKeyStore.HAS_SITE_SCOPE_TEST_KEY,
                getWebResourceDataService().hasAuditSiteScopeTest(site, getSiteScope()));
        model.addAttribute(TgolKeyStore.STATUS_KEY, computeAuditStatus(site.getAudit()));
        return TgolKeyStore.SYNTHESIS_SITE_VIEW_NAME;
    }

}
