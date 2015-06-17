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
package org.tanaguru.webapp.presentation.data;

import java.util.List;

import org.tanaguru.entity.audit.DefiniteResult;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.reference.Test;

/**
 * This class processes raw results and handles displayable remarks data
 *
 * @author jkowalczyk
 */
public interface TestResult {

    String ELEMENT_NAME_KEY = "Element-Name";
    String TAG_KEY = "Tag";
    String SNIPPET = "Snippet";
    String LINE_NUMBER_KEY = "Line-Number";
    String REPRESENTATION_SUFFIX_KEY = "-representation-index";
    String REPRESENTATION_ORDER_SUFFIX_KEY = "-representation-order";
    String CONTRAST_RATIO_SUFFIX_KEY = "-contrast-ratio";

    String FAILED_LOWER = "failed";
    String PASSED_LOWER = "passed";
    String NEED_MORE_INFO_LOWER = "nmi";
    String NOT_APPLICABLE_LOWER = "na";
    String NOT_TESTED_LOWER = "nt";

    int TABULAR_REPRESENTATION = 2;

    String REPRESENTATION_BUNDLE_NAME = "representation";

    String REPRESENTATION_FILE_PREFIX = "data-representation/data-representation-";
    String REPRESENTATION_FILE_SUFFIX = ".jsp";

    int MAX_REMARK_INFO = 10;

    /**
     *
     * @return the list of remark info for the given test
     */
    List<RemarkInfos> getRemarkInfosList();

    /**
     *
     * @return the manual result
     */
    String getManualResult();
    
    /**
     * 
     * @param manualResult 
     */
    void setManualResult(String manualResult);

    /**
     *
     * @return the manual audit entered comment
     */
    String getComment();
    
    /**
     * 
     * @param comment 
     */
    void setComment(String comment);

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
     * @return the test representation needed to display test results
     */
    String getTestRepresentation();

    /**
     *
     * @param testRepresentationType
     */
    void setTestRepresentation(int testRepresentationType);

    /**
     * In case of tests checking color contrast, we may need to call an external
     * service to find good colors with the contrast value threashold. That
     * value is set through the properties file.
     *
     * @param colorTestContrastRatio
     */
    void setColorTestContrastRatio(String colorTestContrastRatio);

    /**
     *
     * @return the contrast value threashold for the given test
     */
    String getColorTestContrastRatio();

    /**
     *
     * @return the test representation type as integer
     */
    int getTestRepresentationType();

    /**
     *
     * @return The test
     */
    Test getTest();

    /**
     *
     * @param test
     */
    void setTest(Test test);

    /**
     *
     * @return The test short label
     */
    String getTestShortLabel();

    /**
     *
     * @param testShortLabel
     */
    void setTestShortLabel(String testShortLabel);

    /**
     *
     * @return the test key
     */
    String getTestCode();

    /**
     *
     * @param testCode
     */
    void setTestCode(String testCode);

    /**
     *
     * @return the tested url
     */
    String getTestUrl();

    /**
     *
     * @param testUrl
     */
    void setTestUrl(String testUrl);

    /**
     *
     * @return the number of encountered elements
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
     * @return The lower-case-formatted result value. This data is usefull to
     * apply the correct css on the displayed result String.
     */
    String getResultCode();

    /**
     *
     * @param resultCode
     */
    void setResultCode(String resultCode);

    /**
     *
     * @return the displayable result data
     */
    String getResult();

    /**
     *
     * @param result
     */
    void setResult(String result);

    /**
     *
     * @return the code of the level for the current test
     */
    String getLevelCode();

    /**
     *
     * @param levelCode
     */
    void setLevelCode(String levelCode);

    /**
     *
     * @return the rule design url that explains the algorithm for the current
     * test
     */
    String getRuleDesignUrl();

    /**
     *
     * @param ruleDesignUrl
     */
    void setRuleDesignUrl(String ruleDesignUrl);

    /**
     *
     * @return whether the rendered result is truncated
     */
    boolean getIsTruncated();

    /**
     *
     * @param isTruncated
     */
    void setTruncated(boolean isTruncated);

    /**
     * 
     * @param processResult
     * @return the historic of process result manually overridden for a given
     * processResult
     */
    List<ProcessResult> getHistory(ProcessResult processResult);

    /**
     * 
     * @return the historic of process result manually overridden 
     */
    public List<DefiniteResult> getHistory();

    /**
     * 
     * @param list 
     */
    public void setHistory(List<DefiniteResult> list);

}
