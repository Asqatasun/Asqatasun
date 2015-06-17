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

import org.tanaguru.entity.audit.*;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 8-3-1 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule080301Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule080301Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule080301");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.08.03.01-1Passed-01");
        addWebResource("Rgaa30.Test.08.03.01-1Passed-02");
        addWebResource("Rgaa30.Test.08.03.01-1Passed-03");
        addWebResource("Rgaa30.Test.08.03.01-1Passed-04");
        addWebResource("Rgaa30.Test.08.03.01-2Failed-01");
        addWebResource("Rgaa30.Test.08.03.01-2Failed-02");
        addWebResource("Rgaa30.Test.08.03.01-2Failed-03");
        addWebResource("Rgaa30.Test.08.03.01-2Failed-04");
        addWebResource("Rgaa30.Test.08.03.01-2Failed-05");
        addWebResource("Rgaa30.Test.08.03.01-2Failed-06");
        addWebResource("Rgaa30.Test.08.03.01-2Failed-07");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.03.01-1Passed-01"),1);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.03.01-1Passed-02"),13);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.03.01-1Passed-03"),14);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.03.01-1Passed-04"),12);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.08.03.01-2Failed-01");
        checkResultIsFailed(processResult, 12, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_WHOLE_PAGE_MSG,
                "",
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.03.01-2Failed-02");
        checkResultIsFailed(processResult, 12, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG,
                "",
                1);
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.03.01-2Failed-03");
        checkResultIsFailed(processResult, 13, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG,
                "",
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.03.01-2Failed-04");
        checkResultIsFailed(processResult, 14, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG,
                "",
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.03.01-2Failed-05");
        checkResultIsFailed(processResult, 14, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG,
                "",
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.03.01-2Failed-06");
        checkResultIsFailed(processResult, 14, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG,
                "",
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.03.01-2Failed-07");
        checkResultIsFailed(processResult, 14, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG,
                "",
                1);
    }

}