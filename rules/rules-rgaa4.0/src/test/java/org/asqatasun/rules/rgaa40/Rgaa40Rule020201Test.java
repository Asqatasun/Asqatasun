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
import org.asqatasun.rules.rgaa40.test.Rgaa40RuleImplementationTestCase;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for implementation of rule 2.2.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/documentation/en/90_Rules/rgaa4.0/02.Frames/Rule-2-2-1.md">rule 2.2.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-2-2-1">2.2.1 rule specification</a>
 */
public class Rgaa40Rule020201Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa40Rule020201Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
            "org.asqatasun.rules.rgaa40.Rgaa40Rule020201");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.2.2.1-2Failed-01");
        addWebResource("Rgaa40.Test.2.2.1-2Failed-02");
        addWebResource("Rgaa40.Test.2.2.1-2Failed-03");
        addWebResource("Rgaa40.Test.2.2.1-2Failed-04");
        addWebResource("Rgaa40.Test.2.2.1-3NMI-01");
        addWebResource("Rgaa40.Test.2.2.1-4NA-01");
        addWebResource("Rgaa40.Test.2.2.1-4NA-02");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa40.Test.2.2.1-2Failed-01");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_TITLE_OF_IFRAME_MSG,
                HtmlElementStore.IFRAME_ELEMENT,
                1,
                new ImmutablePair(TITLE_ATTR, ""));
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.2.2.1-2Failed-02");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_TITLE_OF_IFRAME_MSG,
                HtmlElementStore.IFRAME_ELEMENT,
                1,
                new ImmutablePair(TITLE_ATTR, ""));
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.2.2.1-2Failed-03");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_TITLE_OF_IFRAME_MSG,
                HtmlElementStore.IFRAME_ELEMENT,
                1,
                new ImmutablePair(TITLE_ATTR, "!§:;.,?%*\\~/@()[]^_°=+-"));

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.2.2.1-2Failed-04");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_TITLE_OF_IFRAME_MSG,
                HtmlElementStore.IFRAME_ELEMENT,
                1,
                new ImmutablePair(TITLE_ATTR, "mock-iframe.html"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.2.2.1-3NMI-01");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_TITLE_OF_IFRAME_PERTINENCE_MSG,
                HtmlElementStore.IFRAME_ELEMENT,
                1,
                new ImmutablePair(TITLE_ATTR, "title of iframe"));

        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.2.2.1-4NA-01"));
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------        
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.2.2.1-4NA-02"));
    }
    
}
