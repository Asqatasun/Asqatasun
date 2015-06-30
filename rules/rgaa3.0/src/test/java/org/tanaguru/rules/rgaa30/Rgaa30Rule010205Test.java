/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This program is free software: you can redistribute it and/or modify
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
package org.tanaguru.rules.rgaa30;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.rules.keystore.HtmlElementStore;
import static org.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.tanaguru.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_ELEMENT_WITH_EMPTY_ALT_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 1-2-5 of the referential Rgaa 3.0.
 *
 * @author
 */
public class Rgaa30Rule010205Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule010205Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa30.Rgaa30Rule010205");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.1.2.5-1Passed-01",
                createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.5-1Passed-02",
                createParameter("Rules", DECORATIVE_IMAGE_MARKER, "class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.5-1Passed-03",
                createParameter("Rules", DECORATIVE_IMAGE_MARKER, "role-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.5-1Passed-04",
                createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image;role-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.5-1Passed-05",
                createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.5-2Failed-01",
                createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.5-2Failed-02",
                createParameter("Rules", DECORATIVE_IMAGE_MARKER, "class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.5-2Failed-03",
                createParameter("Rules", DECORATIVE_IMAGE_MARKER, "role-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.5-2Failed-04",
                createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image;role-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.5-3NMI-01");
        addWebResource("Rgaa30.Test.1.2.5-3NMI-02");
        addWebResource("Rgaa30.Test.1.2.5-4NA-01");
        addWebResource("Rgaa30.Test.1.2.5-4NA-02",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "informative-image"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.2.5-1Passed-01"), 1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.2.5-1Passed-02"), 1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.2.5-1Passed-03"), 1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.2.5-1Passed-04"), 3);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.2.5-1Passed-05"), 2);

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.1.2.5-2Failed-01");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG,
                HtmlElementStore.CANVAS_ELEMENT,
                1,
                new ImmutablePair(TEXT_ELEMENT2, "Un text"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.5-2Failed-02");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG,
                HtmlElementStore.CANVAS_ELEMENT,
                1,
                new ImmutablePair(TEXT_ELEMENT2, "Un text"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.5-2Failed-03");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG,
                HtmlElementStore.CANVAS_ELEMENT,
                1,
                new ImmutablePair(TEXT_ELEMENT2, "Un text"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.5-2Failed-04");
        checkResultIsFailed(processResult, 3, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG,
                HtmlElementStore.CANVAS_ELEMENT,
                1,
                new ImmutablePair(TEXT_ELEMENT2, "Un text"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.5-3NMI-01");
        checkResultIsPreQualified(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG,
                HtmlElementStore.CANVAS_ELEMENT,
                1,
                new ImmutablePair(TEXT_ELEMENT2, "Un text"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                CHECK_ELEMENT_WITH_EMPTY_ALT_MSG,
                HtmlElementStore.CANVAS_ELEMENT,
                2,
                new ImmutablePair(TEXT_ELEMENT2, ""));

        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.5-3NMI-02");
        checkResultIsPreQualified(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                CHECK_ELEMENT_WITH_EMPTY_ALT_MSG,
                HtmlElementStore.CANVAS_ELEMENT,
                1,
                new ImmutablePair(TEXT_ELEMENT2, ""));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                CHECK_ELEMENT_WITH_EMPTY_ALT_MSG,
                HtmlElementStore.CANVAS_ELEMENT,
                2,
                new ImmutablePair(TEXT_ELEMENT2, ""));


        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.2.5-4NA-01"));

        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.2.5-4NA-02"));
    }

}