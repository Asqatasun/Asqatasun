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

import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 9-1-1 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule090101Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule090101Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule090101");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.09.01.01-1Passed-01");
        addWebResource("Rgaa30.Test.09.01.01-1Passed-02");
        addWebResource("Rgaa30.Test.09.01.01-1Passed-A01");
        addWebResource("Rgaa30.Test.09.01.01-2Failed-01");
        addWebResource("Rgaa30.Test.09.01.01-2Failed-02");
        addWebResource("Rgaa30.Test.09.01.01-2Failed-A01");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.09.01.01-1Passed-01"),1);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.09.01.01-1Passed-02"),2);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-A01-----------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.09.01.01-1Passed-A01"),1);        

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.09.01.01-2Failed-01");
        checkResultIsFailed(processResult, 0, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.H1_TAG_MISSING_MSG,
                "",
                1);        

        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.09.01.01-2Failed-02");
        checkResultIsFailed(processResult, 0, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.H1_TAG_MISSING_MSG,
                "",
                1);        

        //----------------------------------------------------------------------
        //------------------------------2Failed-A01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.09.01.01-2Failed-A01");
        checkResultIsFailed(processResult, 0, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.H1_TAG_MISSING_MSG,
                "",
                1);
    }

}