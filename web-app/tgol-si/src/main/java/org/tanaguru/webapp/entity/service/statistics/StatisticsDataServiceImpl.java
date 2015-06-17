/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.webapp.entity.service.statistics;

import java.util.Collection;
import org.displaytag.properties.SortOrderEnum;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.reference.Theme;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.webapp.entity.dao.statistics.StatisticsDAO;
import org.tanaguru.webapp.presentation.data.FailedPageInfo;
import org.tanaguru.webapp.presentation.data.FailedTestInfo;
import org.tanaguru.webapp.presentation.data.FailedThemeInfo;
import org.tanaguru.webapp.presentation.data.PageResult;
import org.tanaguru.webapp.util.HttpStatusCodeFamily;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class StatisticsDataServiceImpl implements StatisticsDataService {

    private StatisticsDAO statisticsDAO;
    @Autowired
    public void setStatisticsDAO(StatisticsDAO statisticsDAO) {
        this.statisticsDAO = statisticsDAO;
    }
    
    public StatisticsDataServiceImpl() {
        super();
    } 
    
    @Override
    public Collection<FailedTestInfo> getFailedTestByOccurrence(
            WebResource webResource,
            Audit audit,
            int nbOfResult) {
        return statisticsDAO.findFailedTestByOccurrence(
                    webResource,
                    audit, 
                    nbOfResult);
    }
    
    @Override
    public Long getResultCountByResultType(
            WebResource webResource,
            Audit audit, 
            TestSolution testSolution) {
        return statisticsDAO.findResultCountByResultType(
                webResource,
                audit, 
                testSolution);
    }
    
    @Override
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

    @Override
    public Long getResultCountByResultTypeAndTheme(
            WebResource webResource,
            Audit audit,
            TestSolution testSolution,
            Theme theme,
            boolean manualAudit) {
        return statisticsDAO.findResultCountByResultTypeAndTheme(
            webResource,audit,testSolution,theme, manualAudit);
    }
    
    @Override
    public Float getMarkByWebResourceAndAudit(
            WebResource webResource,
            boolean isRawMark,
            boolean isManual) {
        return this.getMarkByWebResourceAndAudit(webResource.getId(), isRawMark, isManual);
    }

    /**
     * 
     * @param idWebResource
     * @param audit
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
    
    @Override
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

    @Override
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

    @Override
    public Collection<FailedPageInfo> getFailedWebResourceSortedByTest(WebResource webResource, Audit audit, int nbOfResult) {
        return statisticsDAO.findFailedWebResourceSortedByTest(
                    webResource, 
                    audit,
                    nbOfResult);
    }

    @Override
    public Collection<FailedPageInfo> getFailedWebResourceSortedByOccurrence(WebResource webResource, Audit audit, int nbOfResult) {
        return statisticsDAO.findFailedWebResourceSortedByOccurrence(
                    webResource, 
                    audit,
                    nbOfResult);
    }
    
}
