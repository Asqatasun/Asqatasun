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
package org.opens.tgol.entity.dao.statistics;

import org.opens.tanaguru.sdk.entity.dao.GenericDAO;
import org.opens.tanaguru.entity.statistics.WebResourceStatistics;
import org.opens.tgol.util.HttpStatusCodeFamily;
import java.util.Collection;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.subject.WebResource;


/**
 * This interface adds more primitives to the WebResourceDAO interface defined
 * in the tanaguru API
 *
 * @author jkowalczyk
 */
public interface StatisticsDAO extends GenericDAO<WebResourceStatistics, Long> {

    /**
     *
     * @param parentWebresourceId
     * @param nbOfResult
     * @return
     */
    Collection<? extends Object> findFailedWebResourceSortedByTest(
            WebResource webResource,
            Audit audit,
            int nbOfResult);

    /**
     *
     * @param parentWebresourceId
     * @param nbOfResult
     * @return
     */
    Collection<? extends Object> findFailedWebResourceSortedByOccurrence(
            WebResource webResource,
            Audit audit,
            int nbOfResult);

    /**
     *
     * @param parentWebresourceId
     * @param nbOfResult
     * @return
     */
    Collection<? extends Object> findFailedTestByOccurrence(
            WebResource webResource,
            Audit audit,
            int nbOfResult);

    /**
     *
     * @param parentWebresourceId
     * @param testSolution
     * @return
     */
    Long findResultCountByResultType(
            WebResource webResource,
            Audit audit,
            TestSolution testSolution);

    /**
     *
     * @param parentWebresourceId
     * @param testSolution
     * @param nb0fResult
     * @return
     */
    Collection<? extends Object> findResultCountByResultTypeAndTheme(
            WebResource webResource,
            Audit audit,
            TestSolution testSolution,
            int nb0fResult);

    /**
     *
     * @param parentWebresourceId
     * @param testSolution
     * @param theme
     * @return
     */
    Long findResultCountByResultTypeAndTheme(
            WebResource webResource,
            Audit audit,
            TestSolution testSolution,
            Theme theme);

    /**
     * 
     * @param webResource
     * @param audit
     * @return
     */
    Float findWeightedMarkByWebResourceAndAudit(
            Long idWebResource,
            Audit audit);

    /**
     *
     * @param webResource
     * @param audit
     * @return
     */
    Float findRawMarkByWebResourceAndAudit(
            Long idWebResource,
            Audit audit);

    /**
     * 
     * @param idAudit
     * @param httpStatusCode
     * @param containingValue
     * @return
     */
    Long findWebResourceCountByAuditAndHttpStatusCode(
            Long idAudit,
            HttpStatusCodeFamily httpStatusCode,
            String containingValue);

    /**
     * 
     * @param idAudit
     * @param httpStatusCode
     * @param nbOfElements
     * @param windows
     * @param sortDirection
     * @param sortCriterion
     * @param containingValue
     * @return
     */
    Collection<? extends Object> findWebResourceByAuditAndHttpStatusCode(
            Long idAudit,
            HttpStatusCodeFamily httpStatusCode,
            int nbOfElements,
            int windows,
            int sortDirection,
            String sortCriterion,
            String containingValue);

}