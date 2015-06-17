/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
package org.tanaguru.entity.service.statistics;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.entity.statistics.WebResourceStatistics;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.sdk.entity.service.GenericDataService;

/**
 *
 * @author jkowalczyk
 */
public interface WebResourceStatisticsDataService
        extends GenericDataService<WebResourceStatistics, Long>{

    /**
     * 
     * @param webresourceId
     * @param testSolution
     * @return
     */
    Long getResultCountByResultType(
            Long webresourceId,
            TestSolution testSolution);
    
    /**
     * 
     * @param webresourceId
     * @param paramSet
     * @param testSolution
     * @param isManualAudit
     * @return
     */
    BigDecimal getWeightedResultByResultType(
            Long webresourceId,
            Collection<Parameter> paramSet,
            TestSolution testSolution,
            boolean isManualAudit);

    /**
     * 
     * @param webresourceId
     * @param testSolution
     * @param isManualAudit
     * @return
     */
    Long getNumberOfOccurrencesByWebResourceAndResultType(
            Long webresourceId,
            TestSolution testSolution,
            boolean isManualAudit);

    /**
     *
     * @param webresourceId
     * @return
     */
    Integer getHttpStatusCodeByWebResource(Long webresourceId);
    
    /**
     *
     * @param webResource
     * @return
     */
    WebResourceStatistics getWebResourceStatisticsByWebResource(WebResource webResource);
    
    /**
     * 
     * @param audit
     * @param webResource
     */
    WebResourceStatistics createWebResourceStatisticsForManualAudit(Audit audit, WebResource webResource, List<ProcessResult> netResultList);
}
