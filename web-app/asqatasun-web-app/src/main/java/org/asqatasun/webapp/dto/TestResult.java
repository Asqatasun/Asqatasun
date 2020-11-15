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
package org.asqatasun.webapp.dto;

import java.util.ArrayList;
import java.util.List;
import org.asqatasun.entity.audit.DefiniteResult;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.reference.Test;

/**
 * This class processes raw results and handles displayable remarks data
 * @author jkowalczyk
 */
public class TestResult {

    public static String ELEMENT_NAME_KEY = "Element-Name";
    public static String TAG_KEY = "Tag";
    public static String SNIPPET = "Snippet";
    public static String LINE_NUMBER_KEY = "Line-Number";
    public static String REPRESENTATION_SUFFIX_KEY = "-representation-index";
    public static String REPRESENTATION_ORDER_SUFFIX_KEY = "-representation-order";
    public static String CONTRAST_RATIO_SUFFIX_KEY = "-contrast-ratio";

    public static String FAILED_LOWER = "failed";
    public static String PASSED_LOWER = "passed";
    public static String NEED_MORE_INFO_LOWER = "nmi";
    public static String NOT_APPLICABLE_LOWER = "na";
    public static String NOT_TESTED_LOWER = "nt";

    public static int TABULAR_REPRESENTATION = 2;

    public static String REPRESENTATION_BUNDLE_NAME = "representation";

    public static String REPRESENTATION_FILE_PREFIX = "data-representation/data-representation-";
    public static String REPRESENTATION_FILE_SUFFIX = ".jsp";

    public static int MAX_REMARK_INFO = 10;

    private final List<RemarkInfos> remarkInfosList = new ArrayList<>();

    /**
     *
     * @return
     *          the list of remark info for the given test
     */

    public List<RemarkInfos> getRemarkInfosList() {
        return remarkInfosList;
    }

    private String[] testEvidenceRepresentationOrder;

    public void setTestEvidenceRepresentationOrder(String testEvidenceRepresentationOrder) {
        this.testEvidenceRepresentationOrder = testEvidenceRepresentationOrder.split(";");
    }
    
    private final ManualResult manualResult = new ManualResult();

    public String getManualResult() {
        return manualResult.getResult();
    }


    public void setManualResult(String manualResult) {
        this.manualResult.setResult(manualResult);
    }


    public String getComment() {
        return manualResult.getComment();
    }


    public void setComment(String comment) {
        this.manualResult.setComment(comment);
    }


    public String[] getTestEvidenceRepresentationOrder() {
        return testEvidenceRepresentationOrder;
    }

    private int testRepresentationType;

    public int getTestRepresentationType() {
        return testRepresentationType;
    }

    private String testRepresentation;
    /**
     *
     * @return
     *          the test representation needed to display test results
     */

    public String getTestRepresentation() {
        return testRepresentation;
    }

    /**
     *
     * @param testRepresentationType
     */

    public void setTestRepresentation(int testRepresentationType) {
        this.testRepresentation = REPRESENTATION_FILE_PREFIX +
                testRepresentationType +
                REPRESENTATION_FILE_SUFFIX;
        this.testRepresentationType = testRepresentationType;
    }

    private Test test;
    /**
     *
     * @return
     *          The test 
     */

    public Test getTest() {
        return test;
    }

    /**
     *
     * @param test
     */

    public void setTest(Test test) {
        this.test = test;
    }

    private String testShortLabel;
    /**
     *
     * @return
     *          The test short label
     */

    public String getTestShortLabel() {
        return testShortLabel;
    }

    /**
     *
     * @param testShortLabel
     */

    public void setTestShortLabel(String testShortLabel) {
        this.testShortLabel = testShortLabel;
    }

    private String testCode;
    /**
     *
     * @return
     *      the test key 
     */

    public String getTestCode() {
        return testCode;
    }

    /**
     *
     * @param testCode
     */

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    private String testUrl;
    /**
     *
     * @return
     *      the tested url
     */

    public String getTestUrl() {
        return testUrl;
    }

    /**
     *
     * @param testUrl
     */

    public void setTestUrl(String testUrl) {
        this.testUrl = testUrl;
    }

    private int elementCounter = 0;
    /**
     *
     * @return
     *          the number of encountered elements
     */

    public int getElementCounter() {
        return elementCounter;
    }

    /**
     *
     * @param elementCounter
     */

    public void setElementCounter(int elementCounter) {
        this.elementCounter = elementCounter;
    }

    private ResultCounter resultCounter;

    public ResultCounter getResultCounter() {
        return resultCounter;
    }


    public void setResultCounter(ResultCounter resultCounter) {
        this.resultCounter = resultCounter;
    }

    private String resultCode;
    /**
     *
     * @return
     * The lower-case-formatted result value. This data is usefull to apply the
     * correct css on the displayed result String.
     */

    public String getResultCode() {
        return resultCode;
    }


    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    private String result;
    /**
     *
     * @return
     *          the displayable result data
     */

    public String getResult() {
        return result;
    }

    /**
     *
     * @param result
     */

    public void setResult(String result) {
        this.result = result;
    }

    /**
     * The test level
     */
    private String levelCode;
    /**
     *
     * @return
     *          the code of the level for the current test
     */

    public String getLevelCode() {
        return levelCode;
    }

    /**
     *
     * @param levelCode
     */

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    private String ruleDesignUrl;
    /**
     *
     * @return
     *          the rule design url that explains the algorithm for the current
     *          test
     */

    public String getRuleDesignUrl() {
        return ruleDesignUrl;
    }

    /**
     *
     * @param ruleDesignUrl
     */

    public void setRuleDesignUrl(String ruleDesignUrl) {
        this.ruleDesignUrl = ruleDesignUrl;
    }

    boolean isTruncated = false;

    public boolean getIsTruncated() {
        return isTruncated;
    }


    public void setTruncated(boolean isTruncated) {
        this.isTruncated = isTruncated;
    }

    private String colorTestContrastRatio;

    public void setColorTestContrastRatio(String colorTestContrastRatio) {
        this.colorTestContrastRatio = colorTestContrastRatio;
    }


    public String getColorTestContrastRatio() {
        return colorTestContrastRatio;
    }

    /**
     * Default constructor
     */
    public TestResult() {
    }

    private List<DefiniteResult> history;


    public List<DefiniteResult> getHistory() {
        return history;
    }


    public void setHistory(List<DefiniteResult> history) {
        this.history = history;
    }


    public List<ProcessResult> getHistory(ProcessResult processResult) {
        //TODO :YNE: afficher l'historique pour chaque processResult
        //TODO :YNE: essayer de déporter ce code sur un service et/ou module dédier à hibernate-envers
//		AuditReader auditReader = AuditReaderFactory.get(ProcessResultImpl);
        return null;
    }

}
