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
import static org.tanaguru.entity.audit.TestSolution.FAILED;
import static org.tanaguru.rules.keystore.HtmlElementStore.INPUT_ELEMENT;
import static org.tanaguru.rules.keystore.HtmlElementStore.SELECT_ELEMENT;
import static org.tanaguru.rules.keystore.HtmlElementStore.TEXTAREA_ELEMENT;
import static org.tanaguru.rules.keystore.RemarkMessageStore.INVALID_FORM_FIELD_MSG;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 11-1-1 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule110101Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule110101Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule110101");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.11.01.01-1Passed-01");
        addWebResource("Rgaa30.Test.11.01.01-1Passed-02");
        addWebResource("Rgaa30.Test.11.01.01-1Passed-03");
        addWebResource("Rgaa30.Test.11.01.01-1Passed-04");
        addWebResource("Rgaa30.Test.11.01.01-1Passed-05");
        addWebResource("Rgaa30.Test.11.01.01-1Passed-06");
        addWebResource("Rgaa30.Test.11.01.01-1Passed-07");
        addWebResource("Rgaa30.Test.11.01.01-1Passed-08");
        addWebResource("Rgaa30.Test.11.01.01-1Passed-09");
        addWebResource("Rgaa30.Test.11.01.01-1Passed-10");
        addWebResource("Rgaa30.Test.11.01.01-1Passed-11");
        addWebResource("Rgaa30.Test.11.01.01-1Passed-12");
        addWebResource("Rgaa30.Test.11.01.01-1Passed-13");
        addWebResource("Rgaa30.Test.11.01.01-1Passed-14");
        addWebResource("Rgaa30.Test.11.01.01-1Passed-15");
        addWebResource("Rgaa30.Test.11.01.01-1Passed-16");
        addWebResource("Rgaa30.Test.11.01.01-1Passed-17");
        addWebResource("Rgaa30.Test.11.01.01-1Passed-18");
        addWebResource("Rgaa30.Test.11.01.01-2Failed-01");
        addWebResource("Rgaa30.Test.11.01.01-2Failed-02");
        addWebResource("Rgaa30.Test.11.01.01-2Failed-03");
        addWebResource("Rgaa30.Test.11.01.01-2Failed-04");
        addWebResource("Rgaa30.Test.11.01.01-2Failed-05");
        addWebResource("Rgaa30.Test.11.01.01-2Failed-06");
        addWebResource("Rgaa30.Test.11.01.01-2Failed-07");
        addWebResource("Rgaa30.Test.11.01.01-4NA-01");
        addWebResource("Rgaa30.Test.11.01.01-4NA-02");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-01"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-02"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-03"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-04"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-05"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-06------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-06"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-07------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-07"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-08------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-08"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-09------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-09"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-10------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-10"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-11------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-11"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-12------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-12"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-13------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-13"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-14------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-14"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-15------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-15"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-16------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-16"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-17------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-17"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-18------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.11.01.01-1Passed-18"),1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.11.01.01-2Failed-01");
        checkResultIsFailed(processResult,1,1);
        checkRemarkIsPresent(
                processResult, 
                FAILED, 
                INVALID_FORM_FIELD_MSG, 
                INPUT_ELEMENT, 
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-2Failed-02");
        checkResultIsFailed(processResult,1,1);
        checkRemarkIsPresent(
                processResult, 
                FAILED, 
                INVALID_FORM_FIELD_MSG, 
                INPUT_ELEMENT, 
                1);
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-2Failed-03");
        checkResultIsFailed(processResult,1,1);
        checkRemarkIsPresent(
                processResult, 
                FAILED, 
                INVALID_FORM_FIELD_MSG, 
                INPUT_ELEMENT, 
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-2Failed-04");
        checkResultIsFailed(processResult,1,1);
        checkRemarkIsPresent(
                processResult, 
                FAILED, 
                INVALID_FORM_FIELD_MSG, 
                INPUT_ELEMENT, 
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-2Failed-05");
        checkResultIsFailed(processResult,1,1);
        checkRemarkIsPresent(
                processResult, 
                FAILED, 
                INVALID_FORM_FIELD_MSG, 
                INPUT_ELEMENT, 
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-2Failed-06");
        checkResultIsFailed(processResult,1,1);
        checkRemarkIsPresent(
                processResult, 
                FAILED, 
                INVALID_FORM_FIELD_MSG, 
                TEXTAREA_ELEMENT, 
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.01-2Failed-07");
        checkResultIsFailed(processResult,1,1);
        checkRemarkIsPresent(
                processResult, 
                FAILED, 
                INVALID_FORM_FIELD_MSG, 
                SELECT_ELEMENT, 
                1);
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.11.01.01-4NA-01"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.11.01.01-4NA-02"));
    }

}