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
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 8-1-2 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule080102Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule080102Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule080102");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.08.01.02-1Passed-01");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-02");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-03");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-04");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-05");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-06");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-07");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-08");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-09");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-10");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-11");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-12");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-13");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-14");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-15");
        addWebResource("Rgaa30.Test.08.01.02-2Failed-01");
        addWebResource("Rgaa30.Test.08.01.02-2Failed-02");
        addWebResource("Rgaa30.Test.08.01.02-4NA-01");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-01"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-02"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-03"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-04"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-05"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-06------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-06"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-07------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-07"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-08------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-08"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-09------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-09"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-10------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-10"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-11------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-11"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-12------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-12"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-13------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-13"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-14------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-14"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-15------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-15"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.08.01.02-2Failed-01");
        checkResultIsFailed(processResult, 0, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.INVALID_DOCTYPE_MSG,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.01.02-2Failed-02");
        checkResultIsFailed(processResult, 0, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.INVALID_DOCTYPE_MSG,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.08.01.02-4NA-01"));
    }

}