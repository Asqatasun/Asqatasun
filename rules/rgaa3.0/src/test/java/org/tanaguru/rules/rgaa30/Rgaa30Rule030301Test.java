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
import org.tanaguru.entity.audit.*;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import static org.tanaguru.rules.keystore.EvidenceStore.*;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 03.03.01 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule030301Test extends Rgaa30RuleImplementationTestCase {

    static String EMPTY_TARGET = "EMPTY_TARGET";
    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule030301Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa30.Rgaa30Rule030301");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.3.3.1-1Passed-01");
        addWebResource("Rgaa30.Test.3.3.1-1Passed-02");
        addWebResource("Rgaa30.Test.3.3.1-1Passed-03");
        addWebResource("Rgaa30.Test.3.3.1-1Passed-04");
        addWebResource("Rgaa30.Test.3.3.1-1Passed-05");
        addWebResource("Rgaa30.Test.3.3.1-1Passed-06");
        addWebResource("Rgaa30.Test.3.3.1-2Failed-01");
        addWebResource("Rgaa30.Test.3.3.1-2Failed-02");
        addWebResource("Rgaa30.Test.3.3.1-2Failed-03");
        addWebResource("Rgaa30.Test.3.3.1-2Failed-04");
        addWebResource("Rgaa30.Test.3.3.1-2Failed-05");
        addWebResource("Rgaa30.Test.3.3.1-3NMI-01");
        addWebResource("Rgaa30.Test.3.3.1-3NMI-02");
        addWebResource("Rgaa30.Test.3.3.1-3NMI-03");
        addWebResource("Rgaa30.Test.3.3.1-3NMI-04");
        addWebResource("Rgaa30.Test.3.3.1-3NMI-05");
        addWebResource("Rgaa30.Test.3.3.1-3NMI-06");
        addWebResource("Rgaa30.Test.3.3.1-3NMI-07");
        addWebResource("Rgaa30.Test.3.3.1-4NA-01");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.3.3.1-1Passed-01"), 7);

        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.3.3.1-1Passed-02"), 7);

        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.3.3.1-1Passed-03"), 7);

        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.3.3.1-1Passed-04"), 5);

        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.3.3.1-1Passed-05"), 7);

        //----------------------------------------------------------------------
        //------------------------------1Passed-06------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.3.3.1-1Passed-06"), 7);

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.3.3.1-2Failed-01");
        checkResultIsFailed(processResult, 7, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_CONTRAST_MSG,
                HtmlElementStore.SPAN_ELEMENT,
                1,
                new ImmutablePair(FG_COLOR_EE, "rgb(255; 0; 0)"),
                new ImmutablePair(BG_COLOR_EE, "rgb(255; 255; 255)"),
                new ImmutablePair(CONTRAST_EE, "4.00"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.3.3.1-2Failed-02");
        checkResultIsFailed(processResult, 7, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_CONTRAST_MSG,
                HtmlElementStore.SPAN_ELEMENT,
                1,
                new ImmutablePair(FG_COLOR_EE, "rgb(255; 165; 0)"),
                new ImmutablePair(BG_COLOR_EE, "rgb(255; 255; 255)"),
                new ImmutablePair(CONTRAST_EE, "1.97"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.3.3.1-2Failed-03");
        checkResultIsFailed(processResult, 7, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_CONTRAST_MSG,
                HtmlElementStore.SPAN_ELEMENT,
                1,
                new ImmutablePair(FG_COLOR_EE, "rgb(255; 255; 255)"),
                new ImmutablePair(BG_COLOR_EE, "rgb(255; 165; 0)"),
                new ImmutablePair(CONTRAST_EE, "1.97"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.3.3.1-2Failed-04");
        checkResultIsFailed(processResult, 7, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_CONTRAST_MSG,
                HtmlElementStore.SPAN_ELEMENT,
                1,
                new ImmutablePair(FG_COLOR_EE, "rgb(223; 240; 216)"),
                new ImmutablePair(BG_COLOR_EE, "rgb(98; 111; 83)"),
                new ImmutablePair(CONTRAST_EE, "4.49"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.3.3.1-2Failed-05");
        checkResultIsFailed(processResult, 7, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_CONTRAST_MSG,
                HtmlElementStore.SPAN_ELEMENT,
                1,
                new ImmutablePair(FG_COLOR_EE, "rgb(70; 136; 71)"),
                new ImmutablePair(BG_COLOR_EE, "rgb(223; 240; 216)"),
                new ImmutablePair(CONTRAST_EE, "3.61"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.3.3.1-3NMI-01");
        checkResultIsPreQualified(processResult, 7, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_CONTRAST_OF_IMAGE_MSG,
                1);
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.3.3.1-3NMI-02");
        checkResultIsPreQualified(processResult, 7, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_CONTRAST_OF_IMAGE_MSG,
                1);
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.3.3.1-3NMI-03");
        checkResultIsPreQualified(processResult, 7, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_CONTRAST_OF_IMAGE_MSG,
                1);
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.3.3.1-3NMI-04");
        checkResultIsPreQualified(processResult, 5, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_CONTRAST_OF_IMAGE_MSG,
                1);
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.3.3.1-3NMI-05");
        checkResultIsPreQualified(processResult, 7, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.NOT_TREATED_BACKGROUND_COLOR_MSG,
                EMPTY_TARGET,
                1,
                new ImmutablePair(ELEMENT_NAME_EE, "linear-gradient(rgb(244, 244, 244) 55px, rgb(248, 248, 248) 100%)"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.3.3.1-3NMI-06");
        checkResultIsPreQualified(processResult, 7, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.NOT_TREATED_BACKGROUND_COLOR_MSG,
                EMPTY_TARGET,
                1,
                new ImmutablePair(ELEMENT_NAME_EE, "my-image.jpg"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-07---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.3.3.1-3NMI-07");
        checkResultIsPreQualified(processResult, 7, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.BAD_CONTRAST_HIDDEN_ELEMENT_MSG,
                HtmlElementStore.SPAN_ELEMENT,
                1,
                new ImmutablePair(FG_COLOR_EE, "rgb(70; 136; 71)"),
                new ImmutablePair(BG_COLOR_EE, "rgb(223; 240; 216)"),
                new ImmutablePair(CONTRAST_EE, "3.61"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.3.3.1-4NA-01"));
    }

}