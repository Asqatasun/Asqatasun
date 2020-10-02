/**
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
package org.asqatasun.rules.rgaa40;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;
import org.asqatasun.rules.rgaa40.test.Rgaa40RuleImplementationTestCase;

import static org.asqatasun.rules.keystore.AttributeStore.SRC_ATTR;

/**
 * Unit test class for implementation of rule 2.1.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/02.Frames/Rule-2-1-1.md">rule 2.1.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-2-1-1">2.1.1 rule specification</a>
 */
public class Rgaa40Rule020101Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa40Rule020101Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
            "org.asqatasun.rules.rgaa40.Rgaa40Rule020101");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.2.1.1-1Passed-01"); // IFRAME
        addWebResource("Rgaa40.Test.2.1.1-1Passed-02"); //  FRAME
        addWebResource("Rgaa40.Test.2.1.1-2Failed-01"); // IFRAME
        addWebResource("Rgaa40.Test.2.1.1-2Failed-02"); //  FRAME
        addWebResource("Rgaa40.Test.2.1.1-4NA-01");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa40.Test.2.1.1-1Passed-01"), 1);

        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa40.Test.2.1.1-1Passed-02"), 2);

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResultFailedIframe = processPageTest("Rgaa40.Test.2.1.1-2Failed-01");
        checkResultIsFailed(processResultFailedIframe, 2, 1);
        checkRemarkIsPresent(
            processResultFailedIframe,
            TestSolution.FAILED,
            RemarkMessageStore.TITLE_ATTR_MISSING_MSG,
            HtmlElementStore.IFRAME_ELEMENT,
            1,
            new ImmutablePair(SRC_ATTR, "mock-iframe1.html"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResultFailedFrame = processPageTest("Rgaa40.Test.2.1.1-2Failed-02");
        checkResultIsFailed(processResultFailedFrame, 5, 2);
        checkRemarkIsPresent(
            processResultFailedFrame,
            TestSolution.FAILED,
            RemarkMessageStore.TITLE_ATTR_MISSING_MSG,
            HtmlElementStore.FRAME_ELEMENT,
            1,
            new ImmutablePair(SRC_ATTR, "mock-frame_2.html"));
        checkRemarkIsPresent(
            processResultFailedFrame,
            TestSolution.FAILED,
            RemarkMessageStore.TITLE_ATTR_MISSING_MSG,
            HtmlElementStore.FRAME_ELEMENT,
            2,
            new ImmutablePair(SRC_ATTR, "mock-frame_4.html"));

        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.2.1.1-4NA-01"));
    }

}
