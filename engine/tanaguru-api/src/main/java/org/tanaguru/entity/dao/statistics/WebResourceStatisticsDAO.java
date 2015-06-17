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
package org.tanaguru.entity.dao.statistics;

import java.math.BigDecimal;
import java.util.Collection;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.entity.statistics.WebResourceStatistics;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.sdk.entity.dao.GenericDAO;

/**
 *
 * @author jkowalczyk
 */
public interface WebResourceStatisticsDAO extends GenericDAO<WebResourceStatistics, Long>{

    /**
     *
     * @return
     */
    Class<? extends WebResource> getWebResourceEntityClass();

    /**
     * 
     * @param webresourceId
     * @param testSolution
     * @return
     */
    Long findResultCountByResultType(
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
    public BigDecimal findWeightedResultCountByResultType(
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
    Long findNumberOfOccurrencesByWebResourceAndResultType(
            Long webresourceId,
            TestSolution testSolution,
            boolean isManualAudit);

    /**
     * 
     * @param webresourceId
     * @return
     */
    Integer findHttpStatusCodeByWebResource(Long webresourceId);

    /**
     *
     * @param webResource
     * @return
     */
    WebResourceStatistics findWebResourceStatisticsByWebResource(WebResource webResource);
    
    
    
    /**
     * Getting the The list of WebResourceStatistics for either the manual and the automatic audit by webResouce
     * @param webResource 
     * @param manual If incluse olso the manual statistics
     * @return The list of WebResourceStatistics for either the manual and the automatic audit
     */
    WebResourceStatistics findWebResourceStatisticsByWebResource(WebResource webResource, boolean manual);
    
    

}