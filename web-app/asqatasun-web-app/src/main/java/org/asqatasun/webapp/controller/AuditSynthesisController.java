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
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.reference.Theme;
import org.asqatasun.entity.service.audit.ProcessResultDataService;
import org.asqatasun.entity.subject.Page;
import org.asqatasun.entity.subject.Site;
import org.asqatasun.webapp.dto.FailedThemeInfo;
import org.asqatasun.webapp.dto.ResultCounter;
import org.asqatasun.webapp.dto.factory.ResultCounterFactory;
import org.asqatasun.webapp.entity.contract.Contract;
import org.asqatasun.webapp.exception.ForbiddenPageException;
import org.asqatasun.webapp.exception.ForbiddenUserException;
import org.asqatasun.webapp.statistics.service.StatisticsDataService;
import org.asqatasun.webapp.util.HttpStatusCodeFamily;
import org.asqatasun.webapp.util.TgolKeyStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** 
 *
 * @author jkowalczyk
 */
@Controller
public class AuditSynthesisController extends AbstractAuditDataHandlerController {

    @Value("${app.webapp.ui.config.nbOfFailedTest}")
    private int nbOfDisplayedFailedTest;
    @Value("${app.webapp.ui.config.nbOfFailedPages}")
    private int nbOfDisplayedFailedPages;
    @Value("${app.webapp.ui.config.authorizedScopeForSynthesis}")
    private List<String> authorizedScopeForSynthesis;

    private final ProcessResultDataService processResultDataService;
    private final StatisticsDataService statisticsDataService;
    private final ResultCounterFactory resultCounterFactory;

    @Autowired
    public AuditSynthesisController(ProcessResultDataService processResultDataService,
                                    StatisticsDataService statisticsDataService,
                                    ResultCounterFactory resultCounterFactory) {
        super();
        this.processResultDataService = processResultDataService;
        this.statisticsDataService = statisticsDataService;
        this.resultCounterFactory = resultCounterFactory;
    }

    @Override
    public Map<String,String> getParametersToDisplay() {
        return Stream.of(new String[][] {
            { "LEVEL", "level" },
            { "DEPTH", "depth" },
            { "MAX_DURATION", "max-duration" },
            { "MAX_DOCUMENTS", "max-documents" },
            { "EXCLUSION_REGEXP", "exclusion-regexp" },
            { "INCLUSION_REGEXP", "inclusion-regexp" },
            { "ALTERNATIVE_CONTRAST_MECHANISM", "contrast-alternative" },
            { "INFORMATIVE_IMAGE_MARKER", "informative-image" },
            { "DECORATIVE_IMAGE_MARKER", "decorative-image" },
            { "PRESENTATION_TABLE_MARKER", "presentation-table" },
            { "COMPLEX_TABLE_MARKER", "complex-table" },
            { "DATA_TABLE_MARKER", "data-table" },
            { "CONSIDER_COOKIES", "consider-cookies" },
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
    }

    public boolean isAuthorizedScopeForSynthesis(Audit audit) {
        if (audit.getSubject() instanceof Page) {
            return false;
        }
        String scope = actDataService.getActFromAudit(audit).getScope().getCode().name();
        return (authorizedScopeForSynthesis.contains(scope));
    }

    /**
     *
     * @param auditId
     * @param request
     * @param response
     * @param model
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
        Audit audit = auditDataService.read(aId);
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
     * @param auditId
     * @param request
     * @param response
     * @param model
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
        Audit audit = auditDataService.read(aId);
        if (isUserAllowedToDisplayResult(audit)) {
            if (isAuthorizedScopeForSynthesis(audit)) {
                Contract contract = retrieveContractFromAudit(audit);
                model.addAttribute(
                        TgolKeyStore.CONTRACT_ID_KEY, contract.getId());
                model.addAttribute(
                        TgolKeyStore.CONTRACT_NAME_KEY, contract.getLabel());
                model.addAttribute(TgolKeyStore.AUDIT_ID_KEY, auditId);
                model.addAttribute(TgolKeyStore.REFERENTIAL_CD_KEY, parameterDataService.getReferentialKeyFromAudit(audit));
                model.addAttribute(TgolKeyStore.WEBRESOURCE_ID_KEY, audit.getSubject().getId());
                Site site = (Site) audit.getSubject();
                addAuditStatisticsToModel(site, model, TgolKeyStore.TEST_DISPLAY_SCOPE_VALUE);//TODO cas manual
                model.addAttribute(TgolKeyStore.FAILED_TEST_INFO_BY_OCCURRENCE_SET_KEY,
                        statisticsDataService.getFailedTestByOccurrence(site, audit, -1));
                model.addAttribute(TgolKeyStore.HAS_SITE_SCOPE_TEST_KEY,
                        processResultDataService.hasAuditSiteScopeResult(site, siteScope));
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
        addAuditStatisticsToModel(site, model, TgolKeyStore.TEST_DISPLAY_SCOPE_VALUE);//TODO cas manual

        Map<Theme, ResultCounter> top5SortedThemeMap =
                new LinkedHashMap<>();
        @SuppressWarnings("unchecked")
        Collection<FailedThemeInfo> tfiCollection =
                (Collection<FailedThemeInfo>) statisticsDataService.
                getResultCountByResultTypeAndTheme(site, audit, TestSolution.FAILED, nbOfDisplayedFailedTest);

        for (FailedThemeInfo tfi : tfiCollection) {
            ResultCounter failedCounter = resultCounterFactory.getResultCounter();
            failedCounter.setFailedCount(tfi.getResultCounter().intValue());
            top5SortedThemeMap.put(auditStatisticsFactory.getTheme(tfi.getThemeId()), failedCounter);
        }
        model.addAttribute(
                TgolKeyStore.AUDITED_PAGES_COUNT_KEY,
                statisticsDataService.getWebResourceCountByAuditAndHttpStatusCode(
                    audit.getId(),
                    HttpStatusCodeFamily.f2xx,
                    null,
                    null).intValue());
        model.addAttribute(TgolKeyStore.TOP5_SORTED_THEME_MAP, top5SortedThemeMap);
        model.addAttribute(TgolKeyStore.FAILED_PAGE_INFO_BY_TEST_SET_KEY,
                statisticsDataService.getFailedWebResourceSortedByTest(site, audit, nbOfDisplayedFailedPages));
        model.addAttribute(TgolKeyStore.FAILED_PAGE_INFO_BY_OCCURRENCE_SET_KEY,
                statisticsDataService.getFailedWebResourceSortedByOccurrence(site, audit, nbOfDisplayedFailedPages));
        model.addAttribute(TgolKeyStore.FAILED_TEST_INFO_BY_OCCURRENCE_SET_KEY,
                statisticsDataService.getFailedTestByOccurrence(site, audit, nbOfDisplayedFailedTest));
        model.addAttribute(TgolKeyStore.HAS_SITE_SCOPE_TEST_KEY,
                processResultDataService.hasAuditSiteScopeResult(site, siteScope));
        model.addAttribute(TgolKeyStore.STATUS_KEY, computeAuditStatus(site.getAudit()));
        return TgolKeyStore.SYNTHESIS_SITE_VIEW_NAME;
    }

}
