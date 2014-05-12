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
package org.opens.tanaguru.entity.dao.statistics;

import java.util.Collection;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Criterion;
import org.opens.tanaguru.entity.reference.Theme;
import org.opens.tanaguru.entity.statistics.CriterionStatistics;
import org.opens.tanaguru.entity.statistics.WebResourceStatistics;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.sdk.entity.dao.GenericDAO;

/**
 *
 * @author jkowalczyk
 */
public interface CriterionStatisticsDAO extends GenericDAO<CriterionStatistics, Long> {

    /**
     *
     * @return
     */
    Class<? extends WebResource> getWebResourceEntityClass();

    /**
     * 
     * @param webResource
     * @param testSolution
     * @param criterion
     * @return
     */
    Long findResultCountByResultTypeAndCriterion(
            WebResource webResource,
            TestSolution testSolution,
            Criterion criterion);
    /**
     * 
     * @param webResource
     * @param testSolution
     * @param theme
     * @return
     */
    Long findResultCountByResultTypeAndTheme(
            WebResource webResource,
            TestSolution testSolution,
            Theme theme);
    
    /**
     * 
     * @param webResource
     * @param theme
     * @param testSolution
     * @return
     */
    Collection<CriterionStatistics> findCriterionStatisticsByWebResource(
            WebResource webResource,
            String theme,
            Collection<String> testSolution);
    
    /**
     * 
     * @param webResourceId
     * @return 
     */
    Long findCriterionStatisticsCountByWebResource(Long webResourceId);
    
    
    
    /**
     * Getting the CriterionStatistics by web resources statistics
     * @param webResourceStatistics The web resources statistics
     * @return List of CriterionStatistics by web resources statistics
     */
    Collection<CriterionStatistics> findCriterionStatisticsByWebResource(WebResourceStatistics webResourceStatistics);



}