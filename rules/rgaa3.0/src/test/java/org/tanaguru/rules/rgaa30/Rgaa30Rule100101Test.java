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
 * Unit test class for the implementation of the rule 10-1-1 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule100101Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule100101Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule100101");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.10.01.01-1Passed-01");
        addWebResource("Rgaa30.Test.10.01.01-2Failed-01");
        addWebResource("Rgaa30.Test.10.01.01-2Failed-02");
        addWebResource("Rgaa30.Test.10.01.01-2Failed-03");
        addWebResource("Rgaa30.Test.10.01.01-2Failed-04");
        addWebResource("Rgaa30.Test.10.01.01-2Failed-05");
        addWebResource("Rgaa30.Test.10.01.01-2Failed-06");
        addWebResource("Rgaa30.Test.10.01.01-2Failed-07");
        addWebResource("Rgaa30.Test.10.01.01-2Failed-08");
        addWebResource("Rgaa30.Test.10.01.01-2Failed-09");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.10.01.01-1Passed-01"),12);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.10.01.01-2Failed-01");
        checkResultIsFailed(processResult, 13, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG,
                "basefont",
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.01.01-2Failed-02");
        checkResultIsFailed(processResult, 13, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG,
                "center",
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.01.01-2Failed-03");
        checkResultIsFailed(processResult, 13, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG,
                "blink",
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.01.01-2Failed-04");
        checkResultIsFailed(processResult, 13, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG,
                "font",
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.01.01-2Failed-05");
        checkResultIsFailed(processResult, 13, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG,
                "tt",
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.01.01-2Failed-06");
        checkResultIsFailed(processResult, 13, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG,
                "marquee",
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.01.01-2Failed-07");
        checkResultIsFailed(processResult, 13, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG,
                "s",
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.01.01-2Failed-08");
        checkResultIsFailed(processResult, 13, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG,
                "strike",
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-09------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.01.01-2Failed-09");
        checkResultIsFailed(processResult, 13, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG,
                "u",
                1);

    }

}
