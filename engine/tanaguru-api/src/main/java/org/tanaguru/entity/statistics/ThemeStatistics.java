/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General License for more details.
 *
 * You should have received a copy of the GNU Affero General License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.entity.statistics;

import org.tanaguru.entity.reference.Theme;
import org.tanaguru.sdk.entity.Entity;

/**
 *
 * @author jkowalczyk
 */
public interface ThemeStatistics extends Entity, ResultCounter {

    /**
     * 
     * @return
     */
    Theme getTheme();

    /**
     * 
     * @param theme
     */
    void setTheme(Theme theme);

    /**
     *
     * @return
     */
    WebResourceStatistics getWebResourceStatistics();

    /**
     * 
     * @param webResourceStatisticsStatistics
     */
    void setWebResourceStatistics(
            WebResourceStatistics webResourceStatisticsStatistics);
    
}