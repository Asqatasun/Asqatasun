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
package org.opens.tgol.presentation.data;

import java.util.List;
import org.opens.tanaguru.entity.reference.Test;

/**
 * This class processes raw results and handles displayable remarks data
 * @author jkowalczyk
 */
public interface TestResult {

    static final String ELEMENT_NAME_KEY = "Element-Name";
    static final String LINE_NUMBER_KEY = "Line-Number";
    static final String REPRESENTATION_SUFFIX_KEY = "-representation-index";
    static final String REPRESENTATION_ORDER_SUFFIX_KEY = "-representation-order";

    static final String FAILED = "FAILED";
    static final String PASSED = "PASSED";
    static final String NEED_MORE_INFO = "NEED_MORE_INFO";
    static final String NOT_APPLICABLE = "NOT_APPLICABLE";
    static final String NOT_TESTED = "NOT_TESTED";

    static final String FAILED_LOWER = "failed";
    static final String PASSED_LOWER = "passed";
    static final String NEED_MORE_INFO_LOWER = "nmi";
    static final String NOT_APPLICABLE_LOWER = "na";
    static final String NOT_TESTED_LOWER = "nt";

    static final int TABULAR_REPRESENTATION = 2;

    static final String REPRESENTATION_BUNDLE_NAME = "representation";

    static final String REPRESENTATION_FILE_PREFIX =
            "data-representation/data-representation-";
    static final String REPRESENTATION_FILE_SUFFIX = ".jsp";

    /**
     * 
     * @return
     */
    boolean hasSourceCodeADoctype();

    /**
     *
     * @return
     *          the list of remark info for the given test
     */
    List<RemarkInfos> getRemarkInfosList();

    /**
     *
     * @param testEvidenceRepresentationOrder
     */
    void setTestEvidenceRepresentationOrder(String testEvidenceRepresentationOrder);

    /**
     * 
     * @return
     */
    String[] getTestEvidenceRepresentationOrder();

    /**
     *
     * @return
     *          the test representation needed to display test results
     */
    String getTestRepresentation();

    /**
     *
     * @param testRepresentation
     */
    void setTestRepresentation(int testRepresentationType);

    /**
     * 
     * @return
     *      the test representation type as integer
     */
    int getTestRepresentationType();

    /**
     *
     * @return
     *          The test 
     */
    Test getTest();

    /**
     * 
     * @param test
     */
    void setTest(Test test);

    /**
     *
     * @return
     *          The test short label
     */
    String getTestShortLabel();

    /**
     *
     * @param testShortLabel
     */
    void setTestShortLabel(String testShortLabel);

    /**
     *
     * @return
     *      the test key 
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
     *      the tested url
     */
    String getTestUrl();

    /**
     *
     * @param testUrl
     */
    void setTestUrl(String testUrl);

    /**
     *
     * @return
     *          the number of encountered elements
     */
    int getElementCounter();

    /**
     *
     * @param elementCounter
     */
    void setElementCounter(int elementCounter);

    /**
     *
     * @return
     */
    ResultCounter getResultCounter();

    /**
     * 
     * @param resultCounter
     */
    void setResultCounter(ResultCounter resultCounter);

    /**
     *
     * @return
     * The lower-case-formatted result value. This data is usefull to apply the
     * correct css on the displayed result String.
     */
    String getResultCode();

    /**
     * 
     * @param resultCode
     */
    void setResultCode(String resultCode);

    /**
     *
     * @return
     *          the displayable result data
     */
    String getResult();

    /**
     *
     * @param result
     */
    void setResult(String result);

    /**
     * 
     * @return
     *          the code of the level for the current test
     */
    String getLevelCode();

    /**
     *
     * @param levelCode
     */
    void setLevelCode(String levelCode);

    /**
     *
     * @return
     *          the rule design url that explains the algorithm for the current
     *          test
     */
    String getRuleDesignUrl();

    /**
     *
     * @param ruleDesignUrl
     */
    void setRuleDesignUrl(String ruleDesignUrl);
    
}