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

import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 11-1-2 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule110102Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule110102Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule110102");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.11.01.02-1Passed-01");
        addWebResource("Rgaa30.Test.11.01.02-1Passed-02");
        addWebResource("Rgaa30.Test.11.01.02-1Passed-03");
        addWebResource("Rgaa30.Test.11.01.02-1Passed-04");
        addWebResource("Rgaa30.Test.11.01.02-1Passed-05");
        addWebResource("Rgaa30.Test.11.01.02-1Passed-06");
        addWebResource("Rgaa30.Test.11.01.02-1Passed-07");
//        addWebResource("Rgaa30.Test.11.01.02-1Passed-08");
//        addWebResource("Rgaa30.Test.11.01.02-1Passed-09");
//        addWebResource("Rgaa30.Test.11.01.02-1Passed-10");
//        addWebResource("Rgaa30.Test.11.01.02-1Passed-11");
//        addWebResource("Rgaa30.Test.11.01.02-1Passed-12");
//        addWebResource("Rgaa30.Test.11.01.02-1Passed-13");
//        addWebResource("Rgaa30.Test.11.01.02-1Passed-14");
        addWebResource("Rgaa30.Test.11.01.02-2Failed-01");
        addWebResource("Rgaa30.Test.11.01.02-2Failed-02");
        addWebResource("Rgaa30.Test.11.01.02-2Failed-03");
        addWebResource("Rgaa30.Test.11.01.02-2Failed-04");
        addWebResource("Rgaa30.Test.11.01.02-2Failed-05");
        addWebResource("Rgaa30.Test.11.01.02-2Failed-06");
        addWebResource("Rgaa30.Test.11.01.02-2Failed-07");
        addWebResource("Rgaa30.Test.11.01.02-2Failed-08");
        addWebResource("Rgaa30.Test.11.01.02-2Failed-09");
        addWebResource("Rgaa30.Test.11.01.02-2Failed-10");
        addWebResource("Rgaa30.Test.11.01.02-2Failed-11");
        addWebResource("Rgaa30.Test.11.01.02-2Failed-12");
        addWebResource("Rgaa30.Test.11.01.02-2Failed-13");
        addWebResource("Rgaa30.Test.11.01.02-2Failed-14");
        addWebResource("Rgaa30.Test.11.01.02-2Failed-A01");
        addWebResource("Rgaa30.Test.11.01.02-2Failed-A02");
        addWebResource("Rgaa30.Test.11.01.02-4NA-01");
        addWebResource("Rgaa30.Test.11.01.02-4NA-02");
        addWebResource("Rgaa30.Test.11.01.02-4NA-03");
        addWebResource("Rgaa30.Test.11.01.02-4NA-04");
        addWebResource("Rgaa30.Test.11.01.02-4NA-05");
        addWebResource("Rgaa30.Test.11.01.02-4NA-06");
        addWebResource("Rgaa30.Test.11.01.02-4NA-07");
        addWebResource("Rgaa30.Test.11.01.02-4NA-08");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.02-1Passed-01"),1);

        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.02-1Passed-02"),1);

        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.02-1Passed-03"),1);

        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.02-1Passed-04"),1);

        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.02-1Passed-05"),1);

        //----------------------------------------------------------------------
        //------------------------------1Passed-06------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.02-1Passed-06"),1);

        //----------------------------------------------------------------------
        //------------------------------1Passed-07------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.02-1Passed-07"),1);

//        //----------------------------------------------------------------------
//        //------------------------------1Passed-08------------------------------ NA A01
//        //----------------------------------------------------------------------
//        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.02-1Passed-08"),1);
        
//        //----------------------------------------------------------------------
//        //------------------------------1Passed-09------------------------------ NA A02
//        //----------------------------------------------------------------------
//        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.02-1Passed-09"),1);
//        
//        //----------------------------------------------------------------------
//        //------------------------------1Passed-10------------------------------ NA A03
//        //----------------------------------------------------------------------
//        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.02-1Passed-10"),1);
        
//        //----------------------------------------------------------------------
//        //------------------------------1Passed-11------------------------------ NA A04
//        //----------------------------------------------------------------------
//        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.02-1Passed-11"),1);
        
//        //----------------------------------------------------------------------
//        //------------------------------1Passed-12------------------------------ NA A05
//        //----------------------------------------------------------------------
//        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.02-1Passed-12"),1);

//        //----------------------------------------------------------------------
//        //------------------------------1Passed-13------------------------------ NA A06
//        //----------------------------------------------------------------------
//        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.02-1Passed-13"),1);
        
//        //----------------------------------------------------------------------
//        //------------------------------1Passed-14------------------------------ NA A07
//        //----------------------------------------------------------------------
//        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.02-1Passed-14"),1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-01");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ID_MISSING_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-02");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ID_MISSING_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-03");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ID_MISSING_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-04");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ID_MISSING_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-05");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ID_MISSING_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-06");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ID_MISSING_MSG,
                HtmlElementStore.TEXTAREA_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-07");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ID_MISSING_MSG,
                HtmlElementStore.SELECT_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-08");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ID_NOT_UNIQUE_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1);
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-09------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-09");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ID_NOT_UNIQUE_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-10------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-10");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ID_NOT_UNIQUE_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-11------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-11");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ID_NOT_UNIQUE_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-12------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-12");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ID_NOT_UNIQUE_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-13------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-13");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ID_NOT_UNIQUE_MSG,
                HtmlElementStore.TEXTAREA_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-14------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-14");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ID_NOT_UNIQUE_MSG,
                HtmlElementStore.SELECT_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-A01-----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-A01");
        checkResultIsFailed(processResult, 3, 3);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.FOR_MISSING_MSG,
                HtmlElementStore.LABEL_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.FOR_MISSING_MSG,
                HtmlElementStore.LABEL_ELEMENT,
                2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.FOR_MISSING_MSG,
                HtmlElementStore.LABEL_ELEMENT,
                3);

        //----------------------------------------------------------------------
        //------------------------------2Failed-A02-----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-A02");
        checkResultIsFailed(processResult, 5, 4);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.INVALID_INPUT_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.INVALID_INPUT_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.INVALID_LABEL_MSG,
                HtmlElementStore.LABEL_ELEMENT,
                3);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.INVALID_LABEL_MSG,
                HtmlElementStore.LABEL_ELEMENT,
                4);
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.11.01.02-4NA-01"));

        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-4NA-03");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-4NA-04");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-05----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-4NA-05");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-06----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-4NA-06");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-07----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-4NA-07");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-08----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-4NA-08");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
    }

}
