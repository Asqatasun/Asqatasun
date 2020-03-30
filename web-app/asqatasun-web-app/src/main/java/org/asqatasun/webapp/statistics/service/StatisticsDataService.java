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
package org.asqatasun.webapp.statistics.service;

import org.asqatasun.webapp.dto.FailedPageInfo;
import org.asqatasun.webapp.dto.FailedTestInfo;
import org.asqatasun.webapp.dto.FailedThemeInfo;
import org.asqatasun.webapp.dto.PageResult;
import org.asqatasun.webapp.statistics.dao.StatisticsDAO;
import org.displaytag.properties.SortOrderEnum;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.reference.Theme;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.webapp.util.HttpStatusCodeFamily;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 *
 * @author jkowalczyk
 */
@Service("statisticsDataService")
public class StatisticsDataService {

    private final StatisticsDAO statisticsDAO;

    public StatisticsDataService(StatisticsDAO statisticsDAO) {
        super();
        this.statisticsDAO = statisticsDAO;
    }

    public Collection <FailedTestInfo> getFailedTestByOccurrence(
            WebResource webResource,
            Audit audit,
            int nbOfResult) {
        return statisticsDAO.findFailedTestByOccurrence(
                    webResource,
                    audit, 
                    nbOfResult);
    }

    public Long getResultCountByResultType(
            WebResource webResource,
            Audit audit, 
            TestSolution testSolution) {
        return statisticsDAO.findResultCountByResultType(
                webResource,
                audit, 
                testSolution);
    }

    public Collection<FailedThemeInfo> getResultCountByResultTypeAndTheme(
            WebResource webResource,
            Audit audit,
            TestSolution testSolution,
            int nb0fResult) {
        return statisticsDAO.findResultCountByResultTypeAndTheme(
                webResource,
                audit, 
                testSolution,
                nb0fResult);
    }

    public Long getResultCountByResultTypeAndTheme(
            WebResource webResource,
            Audit audit,
            TestSolution testSolution,
            Theme theme,
            boolean manualAudit) {
        return statisticsDAO.findResultCountByResultTypeAndTheme(
            webResource,audit,testSolution,theme, manualAudit);
    }

    public Float getMarkByWebResourceAndAudit(
            WebResource webResource,
            boolean isRawMark,
            boolean isManual) {
        return this.getMarkByWebResourceAndAudit(webResource.getId(), isRawMark, isManual);
    }

    /**
     * 
     * @param idWebResource
     * @param isRawMark
     * @param isManual
     * @return
     */
    private Float getMarkByWebResourceAndAudit(
            Long idWebResource,
            boolean isRawMark,
            boolean isManual) {
        if (isRawMark) {
            return statisticsDAO.findRawMarkByWebResourceAndAudit(idWebResource, isManual);
        } else {
            return statisticsDAO.findWeightedMarkByWebResourceAndAudit(idWebResource, isManual);
        }
    }

    public Long getWebResourceCountByAuditAndHttpStatusCode(
            Long idAudit, 
            HttpStatusCodeFamily httpStatusCode, 
            String invalidTestLabel,
            String containingValue) {
        return statisticsDAO.findWebResourceCountByAuditAndHttpStatusCode(
                idAudit,
                httpStatusCode,
                invalidTestLabel,
                containingValue);
    }

    public Collection<PageResult> getPageListByAuditAndHttpStatusCode(
            Long idAudit,
            HttpStatusCodeFamily httpStatusCode,
            String invalidTestLabel,
            int nbOfElements,
            int window,
            SortOrderEnum sortDirection,
            String sortCriterion,
            String containingValue) {
        return statisticsDAO.findWebResourceByAuditAndHttpStatusCode(
                idAudit,
                httpStatusCode,
                invalidTestLabel,
                nbOfElements,
                window,
                sortDirection,
                sortCriterion,
                containingValue);
    }

    public Collection<FailedPageInfo> getFailedWebResourceSortedByTest(WebResource webResource, Audit audit, int nbOfResult) {
        return statisticsDAO.findFailedWebResourceSortedByTest(
                    webResource, 
                    audit,
                    nbOfResult);
    }

    public Collection<FailedPageInfo> getFailedWebResourceSortedByOccurrence(WebResource webResource, Audit audit, int nbOfResult) {
        return statisticsDAO.findFailedWebResourceSortedByOccurrence(
                    webResource, 
                    audit,
                    nbOfResult);
    }
    
}
