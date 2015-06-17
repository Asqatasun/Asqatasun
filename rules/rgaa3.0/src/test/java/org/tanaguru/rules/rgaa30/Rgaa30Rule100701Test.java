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
import static org.tanaguru.rules.keystore.HtmlElementStore.*;
import static org.tanaguru.rules.keystore.RemarkMessageStore.*;

/**
 * Unit test class for the implementation of the rule 10.7.1 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule100701Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule100701Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa30.Rgaa30Rule100701");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.10.7.1-1Passed-01");
        addWebResource("Rgaa30.Test.10.7.1-1Passed-02");
        addWebResource("Rgaa30.Test.10.7.1-2Failed-01");
        addWebResource("Rgaa30.Test.10.7.1-2Failed-02");
        addWebResource("Rgaa30.Test.10.7.1-2Failed-03");
        addWebResource("Rgaa30.Test.10.7.1-2Failed-04");
        addWebResource("Rgaa30.Test.10.7.1-2Failed-05");
        addWebResource("Rgaa30.Test.10.7.1-2Failed-06");
        addWebResource("Rgaa30.Test.10.7.1-2Failed-07");
        addWebResource("Rgaa30.Test.10.7.1-3NMI-01");
        addWebResource("Rgaa30.Test.10.7.1-3NMI-02");
        addWebResource("Rgaa30.Test.10.7.1-3NMI-03");
        addWebResource("Rgaa30.Test.10.7.1-3NMI-04");
        addWebResource("Rgaa30.Test.10.7.1-3NMI-05");
        addWebResource("Rgaa30.Test.10.7.1-3NMI-06");
        addWebResource("Rgaa30.Test.10.7.1-3NMI-07");
        addWebResource("Rgaa30.Test.10.7.1-3NMI-08");
        addWebResource("Rgaa30.Test.10.7.1-3NMI-09");
        addWebResource("Rgaa30.Test.10.7.1-3NMI-10");
        addWebResource("Rgaa30.Test.10.7.1-4NA-01");
        addWebResource("Rgaa30.Test.10.7.1-4NA-02");
        addWebResource("Rgaa30.Test.10.7.1-4NA-03");
        addWebResource("Rgaa30.Test.10.7.1-4NA-04");
        addWebResource("Rgaa30.Test.10.7.1-4NA-05");
        addWebResource("Rgaa30.Test.10.7.1-4NA-06");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.10.7.1-1Passed-01"),1);


        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.10.7.1-1Passed-02"),1);

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult pr = processPageTest("Rgaa30.Test.10.7.1-2Failed-01");
        checkResultIsPreQualified(pr,1,1);
        checkRemarkIsPresent(
                pr, 
                TestSolution.NEED_MORE_INFO, 
                INVISIBLE_OUTLINE_ON_FOCUS_MSG, 
                A_ELEMENT, 
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        pr = processPageTest("Rgaa30.Test.10.7.1-2Failed-02");
        checkResultIsPreQualified(pr,1,1);
        checkRemarkIsPresent(
                pr, 
                TestSolution.NEED_MORE_INFO, 
                INVISIBLE_OUTLINE_ON_FOCUS_MSG, 
                A_ELEMENT, 
                1);


        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        pr = processPageTest("Rgaa30.Test.10.7.1-2Failed-03");
        checkResultIsPreQualified(pr,1,1);
        checkRemarkIsPresent(
                pr, 
                TestSolution.NEED_MORE_INFO, 
                INVISIBLE_OUTLINE_ON_FOCUS_MSG, 
                A_ELEMENT, 
                1);


        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        pr = processPageTest("Rgaa30.Test.10.7.1-2Failed-04");
        checkResultIsPreQualified(pr,1,1);
        checkRemarkIsPresent(
                pr, 
                TestSolution.NEED_MORE_INFO, 
                INVISIBLE_OUTLINE_ON_FOCUS_MSG, 
                DIV_ELEMENT, 
                1);


        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        pr = processPageTest("Rgaa30.Test.10.7.1-2Failed-05");
        checkResultIsPreQualified(pr,1,1);
        checkRemarkIsPresent(
                pr, 
                TestSolution.NEED_MORE_INFO, 
                INVISIBLE_OUTLINE_ON_FOCUS_MSG, 
                A_ELEMENT, 
                1);


        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        pr = processPageTest("Rgaa30.Test.10.7.1-2Failed-06");
        checkResultIsPreQualified(pr,1,1);
        checkRemarkIsPresent(
                pr, 
                TestSolution.NEED_MORE_INFO, 
                INVISIBLE_OUTLINE_ON_FOCUS_MSG, 
                A_ELEMENT, 
                1);


        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        pr = processPageTest("Rgaa30.Test.10.7.1-2Failed-07");
        checkResultIsPreQualified(pr,1,1);
        checkRemarkIsPresent(
                pr, 
                TestSolution.NEED_MORE_INFO, 
                INVISIBLE_OUTLINE_ON_FOCUS_MSG, 
                SPAN_ELEMENT, 
                1);


        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        pr = processPageTest("Rgaa30.Test.10.7.1-3NMI-01");
        checkResultIsPreQualified(pr,1,1);
        checkRemarkIsPresent(
                pr, 
                TestSolution.NEED_MORE_INFO, 
                CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, 
                1);
        
        
        //----------------------------------------------------------------------
        //---------------------------------3NMI-02------------------------------
        //----------------------------------------------------------------------
        pr = processPageTest("Rgaa30.Test.10.7.1-3NMI-02");
        checkResultIsPreQualified(pr,1,1);
        checkRemarkIsPresent(
                pr, 
                TestSolution.NEED_MORE_INFO, 
                CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, 
                1);


	//----------------------------------------------------------------------
        //---------------------------------3NMI-03------------------------------
        //----------------------------------------------------------------------
        pr = processPageTest("Rgaa30.Test.10.7.1-3NMI-03");
        checkResultIsPreQualified(pr,1,1);
        checkRemarkIsPresent(
                pr, 
                TestSolution.NEED_MORE_INFO, 
                CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, 
                1);


	//----------------------------------------------------------------------
        //---------------------------------3NMI-04------------------------------
        //----------------------------------------------------------------------
        pr = processPageTest("Rgaa30.Test.10.7.1-3NMI-04");
        checkResultIsPreQualified(pr,1,1);
        checkRemarkIsPresent(
                pr, 
                TestSolution.NEED_MORE_INFO, 
                CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, 
                1);


	//----------------------------------------------------------------------
        //---------------------------------3NMI-05------------------------------
        //----------------------------------------------------------------------
        pr = processPageTest("Rgaa30.Test.10.7.1-3NMI-05");
        checkResultIsPreQualified(pr,1,1);
        checkRemarkIsPresent(
                pr, 
                TestSolution.NEED_MORE_INFO, 
                CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, 
                1);


	//----------------------------------------------------------------------
        //---------------------------------3NMI-06------------------------------
        //----------------------------------------------------------------------
        pr = processPageTest("Rgaa30.Test.10.7.1-3NMI-06");
        checkResultIsPreQualified(pr,1,1);
        checkRemarkIsPresent(
                pr, 
                TestSolution.NEED_MORE_INFO, 
                CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, 
                1);


	//----------------------------------------------------------------------
        //---------------------------------3NMI-07------------------------------
        //----------------------------------------------------------------------
        pr = processPageTest("Rgaa30.Test.10.7.1-3NMI-07");
        checkResultIsPreQualified(pr,1,1);
        checkRemarkIsPresent(
                pr, 
                TestSolution.NEED_MORE_INFO, 
                CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, 
                1);


	//----------------------------------------------------------------------
        //---------------------------------3NMI-08------------------------------
        //----------------------------------------------------------------------
        pr = processPageTest("Rgaa30.Test.10.7.1-3NMI-08");
        checkResultIsPreQualified(pr,1,1);
        checkRemarkIsPresent(
                pr, 
                TestSolution.NEED_MORE_INFO, 
                CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, 
                1);


	//----------------------------------------------------------------------
        //---------------------------------3NMI-09------------------------------
        //----------------------------------------------------------------------
        pr = processPageTest("Rgaa30.Test.10.7.1-3NMI-09");
        checkResultIsPreQualified(pr,1,1);
        checkRemarkIsPresent(
                pr, 
                TestSolution.NEED_MORE_INFO, 
                CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, 
                1);


	//----------------------------------------------------------------------
        //---------------------------------3NMI-10------------------------------
        //----------------------------------------------------------------------
        pr = processPageTest("Rgaa30.Test.10.7.1-3NMI-10");
        checkResultIsPreQualified(pr,1,1);
        checkRemarkIsPresent(
                pr, 
                TestSolution.NEED_MORE_INFO, 
                CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, 
                1);


        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.10.7.1-4NA-01"));


        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.10.7.1-4NA-02"));


        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.10.7.1-4NA-03"));


        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.10.7.1-4NA-04"));


        //----------------------------------------------------------------------
        //------------------------------4NA-05----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.10.7.1-4NA-05"));


        //----------------------------------------------------------------------
        //------------------------------4NA-06----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.10.7.1-4NA-06"));
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.10.7.1-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.10.7.1-1Passed-02").getValue());

        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.7.1-2Failed-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.7.1-2Failed-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.7.1-2Failed-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.7.1-2Failed-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.7.1-2Failed-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.7.1-2Failed-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.7.1-2Failed-07").getValue());

        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.7.1-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.7.1-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.7.1-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.7.1-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.7.1-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.7.1-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.7.1-3NMI-07").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.7.1-3NMI-08").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.7.1-3NMI-09").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.7.1-3NMI-10").getValue());

        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.10.7.1-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.10.7.1-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.10.7.1-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.10.7.1-4NA-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.10.7.1-4NA-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.10.7.1-4NA-06").getValue());

    }
    
}