/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.asqatasun.entity.dao.statistics;

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.reference.Test;
import org.asqatasun.entity.statistics.TestStatistics;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.sdk.entity.dao.GenericDAO;

/**
 *
 * @author jkowalczyk
 */
public interface TestStatisticsDAO extends GenericDAO<TestStatistics, Long> {

    /**
     *
     * @return
     */
    Class<? extends WebResource> getWebResourceEntityClass();

    /**
     * 
     * @param webResource
     * @param test
     * @param testSolution
     * @return 
     *      the number of webresources returning the given testSolution 
     *  for the given test
     */
    Long findResultCounterByResultTypeAndTest(
            WebResource webResource,
            Test test,
            TestSolution testSolution);

}
