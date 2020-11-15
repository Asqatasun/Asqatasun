/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.rules.rgaa30;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import static org.asqatasun.rules.keystore.AttributeStore.ABSENT_ATTRIBUTE_VALUE;
import static org.asqatasun.rules.keystore.AttributeStore.SRC_ATTR;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 1.1.3 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule010103Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule010103Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.asqatasun.rules.rgaa30.Rgaa30Rule010103");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.1.1.3-1Passed-01");
        addWebResource("Rgaa30.Test.1.1.3-1Passed-02");
        addWebResource("Rgaa30.Test.1.1.3-2Failed-01");
        addWebResource("Rgaa30.Test.1.1.3-2Failed-02");
        addWebResource("Rgaa30.Test.1.1.3-4NA-01");
        addWebResource("Rgaa30.Test.1.1.3-4NA-02");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.1.3-1Passed-01"), 1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.1.3-1Passed-02"), 1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.1.1.3-2Failed-01");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ALT_MISSING_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1,
                new ImmutablePair(SRC_ATTR, ABSENT_ATTRIBUTE_VALUE));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.1.3-2Failed-02");
        checkResultIsFailed(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.ALT_MISSING_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1,
                new ImmutablePair(SRC_ATTR, "mock-input-src.jpg"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.1.3-4NA-01"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.1.3-4NA-02"));
    }

}
