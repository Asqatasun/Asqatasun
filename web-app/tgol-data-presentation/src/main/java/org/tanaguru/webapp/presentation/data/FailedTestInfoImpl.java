/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.webapp.presentation.data;

/**
 * This class handles displayable data that represent statistics of tests whose
 * result is failed
 * 
 * @author jkowalczyk
 */
public class FailedTestInfoImpl implements FailedTestInfo{

    /**
     * Default constructor
     */
    public FailedTestInfoImpl(){}

    /**
     * Constructor
     * @param testCode
     * @param testLabel
     * @param pageCounter
     * @param testLevelCode
     */
    public FailedTestInfoImpl(String testCode, String testLabel, Long pageCounter, String testLevelCode) {
        this.testCode = testCode;
        this.testLabel = testLabel;
        this.pageCounter = pageCounter;
        this.testLevelCode = testLevelCode;
    }

    private String testLabel;

    /**
     *
     * @return
     *          the label of the test
     */
    @Override
    public String getTestLabel() {
        return testLabel;
    }

    /**
     *
     * @param testLabel
     */
    @Override
    public void setTestLabel(String testLabel) {
        this.testLabel = testLabel;
    }

    private String testCode;

    /**
     *
     * @return
     *          the code of the test
     */
    @Override
    public String getTestCode() {
        return testCode;
    }

    /**
     *
     * @param testCode
     */
    @Override
    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    private Long pageCounter;

    /**
     *
     * @return
     *          the number of pages whose the given test is failed
     */
    @Override
    public Long getPageCounter() {
        return pageCounter;
    }

    /**
     *
     * @param pageCounter
     */
    @Override
    public void setPageCounter(Long pageCounter) {
        this.pageCounter = pageCounter;
    }

    private String testLevelCode;
    
    @Override
    public String getTestLevelCode() {
        return testLevelCode;
    }

    @Override
    public void setTestLevelCode(String testLevelCode) {
        this.testLevelCode = testLevelCode;
    }

}