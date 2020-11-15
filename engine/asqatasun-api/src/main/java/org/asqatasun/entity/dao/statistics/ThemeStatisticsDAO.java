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
package org.asqatasun.entity.dao.statistics;

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.reference.Theme;
import org.asqatasun.entity.statistics.ThemeStatistics;
import org.asqatasun.entity.statistics.WebResourceStatistics;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.entity.dao.GenericDAO;

/**
 *
 * @author jkowalczyk
 */
public interface ThemeStatisticsDAO extends GenericDAO<ThemeStatistics, Long> {

    /**
     *
     * @return
     */
    Class<? extends WebResource> getWebResourceEntityClass();

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
	 * Getting the theme statistic by theme and  Web Resource Statistics
	 * @param theme
	 * @param wrStats
	 * @return
	 */
   ThemeStatistics findThemeStatisticsByWebResource(Theme theme,WebResourceStatistics wrStats);

}
