/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.tanaguru.rules.rgaa30;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import static org.tanaguru.rules.keystore.AttributeStore.ABSENT_ATTRIBUTE_VALUE;
import static org.tanaguru.rules.keystore.AttributeStore.HREF_ATTR;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule010102Test extends Rgaa30RuleImplementationTestCase {

    public Rgaa30Rule010102Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa30.Rgaa30Rule010102");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.1.1.2-1Passed-01");
        addWebResource("Rgaa30.Test.1.1.2-1Passed-02");
        addWebResource("Rgaa30.Test.1.1.2-1Passed-03");
        addWebResource("Rgaa30.Test.1.1.2-2Failed-01");
        addWebResource("Rgaa30.Test.1.1.2-2Failed-02");
        addWebResource("Rgaa30.Test.1.1.2-2Failed-03");
        addWebResource("Rgaa30.Test.1.1.2-4NA-01");
        addWebResource("Rgaa30.Test.1.1.2-4NA-02");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.1.2-1Passed-01"), 2);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.1.2-1Passed-02"), 2);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.1.2-1Passed-03"), 2);

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.1.1.2-2Failed-01");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ALT_MISSING_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(HREF_ATTR, ABSENT_ATTRIBUTE_VALUE));
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ALT_MISSING_MSG,
                HtmlElementStore.AREA_ELEMENT,
                2,
                new ImmutablePair(HREF_ATTR, ABSENT_ATTRIBUTE_VALUE));

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.1.2-2Failed-02");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ALT_MISSING_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(HREF_ATTR, ABSENT_ATTRIBUTE_VALUE));
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ALT_MISSING_MSG,
                HtmlElementStore.AREA_ELEMENT,
                2,
                new ImmutablePair(HREF_ATTR, ABSENT_ATTRIBUTE_VALUE));
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.1.2-2Failed-03");
        checkResultIsFailed(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ALT_MISSING_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(HREF_ATTR, "mock-area-link.html"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.1.2-4NA-01"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.1.2-4NA-02"));

    }

}
