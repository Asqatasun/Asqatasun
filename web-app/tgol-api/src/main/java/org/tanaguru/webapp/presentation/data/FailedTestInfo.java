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
 * This interface handles displayable data that represent statistics of tests whose
 * result is failed
 * 
 * @author jkowalczyk
 */
public interface FailedTestInfo {

    /**
     *
     * @return
     *          the label of the test
     */
    String getTestLabel();

    /**
     *
     * @param testLabel
     */
    void setTestLabel(String testLabel);

    /**
     *
     * @return
     *          the code of the test
     */
    String getTestCode();

    /**
     *
     * @param testCode
     */
    void setTestCode(String testCode);
    
    /**
     *
     * @return
     *          the level code of the test
     */
    String getTestLevelCode();

    /**
     *
     * @param testCode
     */
    void setTestLevelCode(String testLevelCode);

    /**
     *
     * @return
     *          the number of pages whose the given test is failed
     */
    Long getPageCounter();

    /**
     *
     * @param pageCounter
     */
    void setPageCounter(Long pageCounter);

}