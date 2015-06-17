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
package org.tanaguru.webapp.entity.dao.statistics;

import java.util.Collection;
import org.displaytag.properties.SortOrderEnum;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.reference.Theme;
import org.tanaguru.entity.statistics.WebResourceStatistics;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.sdk.entity.dao.GenericDAO;
import org.tanaguru.webapp.presentation.data.FailedPageInfo;
import org.tanaguru.webapp.presentation.data.FailedTestInfo;
import org.tanaguru.webapp.presentation.data.FailedThemeInfo;
import org.tanaguru.webapp.presentation.data.PageResult;
import org.tanaguru.webapp.util.HttpStatusCodeFamily;


/**
 * This interface adds more primitives to the WebResourceDAO interface defined
 * in the tanaguru API
 *
 * @author jkowalczyk
 */
public interface StatisticsDAO extends GenericDAO<WebResourceStatistics, Long> {

    /**
     * 
     * @param webResource
     * @param audit
     * @param nbOfResult
     * @return 
     */
    Collection<FailedPageInfo> findFailedWebResourceSortedByTest(
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
    Collection<FailedPageInfo> findFailedWebResourceSortedByOccurrence(
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
    Collection<FailedTestInfo> findFailedTestByOccurrence(
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
    Long findResultCountByResultType(
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
    Collection<FailedThemeInfo> findResultCountByResultTypeAndTheme(
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
    Long findResultCountByResultTypeAndTheme(
            WebResource webResource,
            Audit audit,
            TestSolution testSolution,
            Theme theme,
            boolean manualAudit);

    /**
     * 
     * @param idWebResource
     * @param isManual 
     * @return 
     */
    Float findWeightedMarkByWebResourceAndAudit(
            Long idWebResource, boolean isManual);

    /**
     * 
     * @param idWebResource
     * @param isManual
     * @return 
     */
    Float findRawMarkByWebResourceAndAudit(
            Long idWebResource, boolean isManual);

    /**
     * 
     * @param idAudit
     * @param httpStatusCode
     * @param invalidTestLabel
     * @param containingValue
     * @return 
     */
    Long findWebResourceCountByAuditAndHttpStatusCode(
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
     * @param windows
     * @param sortDirection
     * @param sortCriterion
     * @param containingValue
     * @return 
     */
    Collection<PageResult> findWebResourceByAuditAndHttpStatusCode(
            Long idAudit,
            HttpStatusCodeFamily httpStatusCode,
            String invalidTestLabel,
            int nbOfElements,
            int windows,
            SortOrderEnum sortDirection,
            String sortCriterion,
            String containingValue);

}