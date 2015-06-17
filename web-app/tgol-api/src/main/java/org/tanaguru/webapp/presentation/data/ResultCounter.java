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
 *
 * @author jkowalczyk
 */
public interface ResultCounter {


    /**
     *
     * @return
     *      the number of passed results
     */
    Integer getPassedCount();

    /**
     *
     * @param passedCount
     */
    void setPassedCount(Integer passedCount);

    /**
     *
     * @return
     *      the number of failed results
     */
    Integer getFailedCount();

    /**
     *
     * @param failedCount
     */
    void setFailedCount(Integer failedCount);

    /**
     *
     * @return
     *      the number of nmi results
     */
    Integer getNmiCount();

    /**
     *
     * @param nmiCount
     */
    void setNmiCount(Integer nmiCount);

    /**
     *
     * @return
     *      the number of na results
     */
    Integer getNaCount();

    /**
     *
     * @param naCount
     */
    void setNaCount(Integer naCount);
    
    /**
     *
     * @return
     *      the number of na results
     */
    Integer getNtCount();

    /**
     *
     * @param naCount
     */
    void setNtCount(Integer naCount);

    /**
     *
     * @return
     *      the number of suspected passed results
     */
    Integer getSuspectedPassedCount();

    /**
     *
     * @param suspectedPassedCount
     */
    void setSuspectedPassedCount(Integer suspectedPassedCount);

    /**
     *
     * @return
     *      the number of suspected failed results
     */
    Integer getSuspectedFailedCount();

    /**
     *
     * @param suspectedFailedCount
     */
    void setSuspectedFailedCount(Integer suspectedFailedCount);
}
