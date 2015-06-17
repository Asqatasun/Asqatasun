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
import org.tanaguru.webapp.presentation.data.FailedPageInfo;
import org.tanaguru.webapp.presentation.data.FailedTestInfo;
import org.tanaguru.webapp.presentation.data.FailedThemeInfo;
import org.tanaguru.webapp.presentation.data.PageResult;
import org.tanaguru.webapp.util.HttpStatusCodeFamily;

/**
 *
 * @author jkowalczyk
 */
public interface StatisticsDataService{

    /**
     * 
     * @param webResource
     * @param audit
     * @param nbOfResult
     * @return 
     */
    Collection<FailedTestInfo> getFailedTestByOccurrence(
            WebResource webResource,
            Audit audit,
            int nbOfResult);
    
    /**
     * 
     * @param webResource
     * @param audit
     * @param testSolution
     * @return 
     */
    Long getResultCountByResultType(
            WebResource webResource,
            Audit audit, 
            TestSolution testSolution);
    
    /**
     * 
     * @param webResource
     * @param audit
     * @param testSolution
     * @param nb0fResult
     * @return 
     */
    Collection<FailedThemeInfo> getResultCountByResultTypeAndTheme(
            WebResource webResource,
            Audit audit,
            TestSolution testSolution,
            int nb0fResult);

    /**
     * 
     * @param webResource
     * @param audit
     * @param testSolution
     * @param theme
     * @param manualAudit
     * @return 
     */
    Long getResultCountByResultTypeAndTheme(
            WebResource webResource,
            Audit audit,
            TestSolution testSolution,
            Theme theme,
            boolean manualAudit);

    /**
     * 
     * @param webResource
     * @param isRawMark
     * @param isManual
     * @return 
     */
    Float getMarkByWebResourceAndAudit(
            WebResource webResource,
            boolean isRawMark,
            boolean isManual);

    /**
     * 
     * @param idAudit
     * @param httpStatusCode
     * @param invalidTestLabel
     * @param containingValue
     * @return 
     */
    Long getWebResourceCountByAuditAndHttpStatusCode(
            Long idAudit, 
            HttpStatusCodeFamily httpStatusCode, 
            String invalidTestLabel,
            String containingValue);

    /**
     * 
     * @param idAudit
     * @param httpStatusCode
     * @param invalidTestLabel
     * @param nbOfElements
     * @param window
     * @param sortDirection
     * @param sortCriterion
     * @param containingValue
     * @return 
     */
    Collection<PageResult> getPageListByAuditAndHttpStatusCode(
            Long idAudit,
            HttpStatusCodeFamily httpStatusCode,
            String invalidTestLabel,
            int nbOfElements,
            int window,
            SortOrderEnum sortDirection,
            String sortCriterion,
            String containingValue);
    
        /**
     * 
     * @param webResource
     * @param audit
     * @param nbOfResult
     * @return 
     */
    Collection<FailedPageInfo> getFailedWebResourceSortedByTest(
            WebResource webResource, 
            Audit audit,
            int nbOfResult);

    /**
     * 
     * @param webResource
     * @param audit
     * @param nbOfResult
     * @return 
     */
    Collection<FailedPageInfo> getFailedWebResourceSortedByOccurrence(
            WebResource webResource, 
            Audit audit,
            int nbOfResult);

}