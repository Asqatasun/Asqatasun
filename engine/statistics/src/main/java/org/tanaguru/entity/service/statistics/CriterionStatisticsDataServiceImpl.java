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

import java.util.Collection;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.dao.statistics.CriterionStatisticsDAO;
import org.tanaguru.entity.reference.Criterion;
import org.tanaguru.entity.reference.Theme;
import org.tanaguru.entity.statistics.CriterionStatistics;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.sdk.entity.service.AbstractGenericDataService;

/**
 *
 * @author jkowalczyk
 */
public class CriterionStatisticsDataServiceImpl extends AbstractGenericDataService<CriterionStatistics, Long>
        implements CriterionStatisticsDataService {

    @Override
    public Long getResultCountByResultTypeAndCriterion(
            WebResource webResource,
            TestSolution testSolution,
            Criterion criterion) {
        return ((CriterionStatisticsDAO) entityDao).
                findResultCountByResultTypeAndCriterion(webResource, testSolution, criterion);
    }
    
    @Override
    public Long getResultCountByResultTypeAndTheme(
            WebResource webResource,
            TestSolution testSolution,
            Theme theme) {
        return ((CriterionStatisticsDAO) entityDao).
                findResultCountByResultTypeAndTheme(webResource, testSolution, theme);
    }

    @Override
    public Collection<CriterionStatistics> getCriterionStatisticsByWebResource(
            WebResource webResource,
            String theme,
            Collection<String> testSolution) {
        return ((CriterionStatisticsDAO) entityDao).
                findCriterionStatisticsByWebResource(
                        webResource,
                        theme, 
                        testSolution);
    }
    
    @Override
    public Long getCriterionStatisticsCountByWebResource(Long webResourceId) {
        return ((CriterionStatisticsDAO) entityDao).findCriterionStatisticsCountByWebResource(webResourceId);
    }

}
