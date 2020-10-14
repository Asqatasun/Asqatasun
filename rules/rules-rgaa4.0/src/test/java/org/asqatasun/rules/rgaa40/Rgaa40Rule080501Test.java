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
import org.asqatasun.rules.keystore.RemarkMessageStore;
import org.asqatasun.rules.rgaa40.test.Rgaa40RuleImplementationTestCase;

/**
 * Unit test class for implementation of rule 8.5.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/08.Mandatory_elements/Rule-8-5-1.md">rule 8.5.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-5-1">8.5.1 rule specification</a>
 */
public class Rgaa40Rule080501Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa40Rule080501Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa40.Rgaa40Rule080501");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.8.5.1-1Passed-01");
        addWebResource("Rgaa40.Test.8.5.1-2Failed-01");
        addWebResource("Rgaa40.Test.8.5.1-2Failed-02");
        addWebResource("Rgaa40.Test.8.5.1-2Failed-03");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01---------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa40.Test.8.5.1-1Passed-01"),1);
        
        //----------------------------------------------------------------------
        //----------------------------2Failed-01--------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa40.Test.8.5.1-2Failed-01");
        checkResultIsFailed(processResult, 0, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.TITLE_TAG_MISSING_MSG,
                "",
                1);
        
        //----------------------------------------------------------------------
        //----------------------------2Failed-02--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.5.1-2Failed-02");
        checkResultIsFailed(processResult, 0, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.TITLE_TAG_MISSING_MSG,
                "",
                1);
        
        //----------------------------------------------------------------------
        //----------------------------2Failed-03--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.5.1-2Failed-03");
        checkResultIsFailed(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.TITLE_TAG_MISSING_MSG,
                "",
                1);
    }

}
