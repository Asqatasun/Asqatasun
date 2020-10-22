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

import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;
import org.asqatasun.rules.rgaa40.test.Rgaa40RuleImplementationTestCase;

/**
 * Unit test class for implementation of rule 8.9.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/08.Mandatory_elements/Rule-8-9-1.md">rule 8.9.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-9-1">8.9.1 rule specification</a>
 */
public class Rgaa40Rule080901Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     *
     * @param testName
     */
    public Rgaa40Rule080901Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa40.Rgaa40Rule080901");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.8.9.1-2Failed-01");
        addWebResource("Rgaa40.Test.8.9.1-2Failed-02");

        addWebResource("Rgaa40.Test.8.9.1-3NMI-01");
        addWebResource("Rgaa40.Test.8.9.1-3NMI-02");
        addWebResource("Rgaa40.Test.8.9.1-3NMI-03");
        addWebResource("Rgaa40.Test.8.9.1-3NMI-04");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //---------------------------2Failed-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa40.Test.8.9.1-2Failed-01");
        checkResultIsFailed(processResult, 17, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.LINK_WITHOUT_TARGET_MSG,
            HtmlElementStore.A_ELEMENT,
            1);

        //----------------------------------------------------------------------
        //---------------------------2Failed-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.9.1-2Failed-02");
        checkResultIsFailed(processResult, 17, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.LINK_WITHOUT_TARGET_MSG,
            HtmlElementStore.A_ELEMENT,
            1);

        //----------------------------------------------------------------------
        //---------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.9.1-3NMI-01");
        checkResultIsPreQualified(processResult, 16, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
            "",
            1);

        //----------------------------------------------------------------------
        //---------------------------3NMI-02------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.9.1-3NMI-02");
        checkResultIsPreQualified(processResult, 17, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
            "",
            1);

        //----------------------------------------------------------------------
        //---------------------------3NMI-03------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.9.1-3NMI-03");
        checkResultIsPreQualified(processResult, 17, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
            "",
            1);

        //----------------------------------------------------------------------
        //---------------------------3NMI-04------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.9.1-3NMI-04");
        checkResultIsPreQualified(processResult, 17, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
            "",
            1);
    }

    /**
     * @param msg
     * @return the message suffixed with the test key identifier
     */
    private String getMessageKey(String msg) {
        StringBuilder strb = new StringBuilder(msg);
        strb.append("_");
        strb.append(getName());
        return strb.toString();
    }
}
