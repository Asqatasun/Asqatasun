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
package org.tanaguru.webapp.presentation.data;

/**
 * This interface handles displayable data that represent statistics of failure
 * for a given webResource.
 *
 * @author jkowalczyk
 */
public interface FailedPageInfo {

    /**
     *
     * @return
     *          the URL of the current failed page
     */
    String getWebResourceUrl();

    /**
     *
     * @param webResourceUrl
     */
    void setWebResourceUrl(String webResourceUrl);

    /**
     *
     * @return
     *          the id of the web resource associated with the current failed
     *          page
     */
    Long getWebResourceId();

    /**
     *
     * @param webResourceId
     */
    void setWebResourceId(Long webResourceId);

    /**
     *
     * @return
     *          the number of failed tests for the current page
     */
    Long getFailedTestCounter();

    /**
     *
     * @param failedTestCounter
     */
    void setFailedTestCounter(Long failedTestCounter);

    /**
     *
     * @return
     *          the number of failed occurrences for the current page
     */
    Long getFailedOccurrenceCounter();

    /**
     *
     * @param failedOccurrenceCounter
     */
    void setFailedOccurrenceCounter(Long failedOccurrenceCounter);

}