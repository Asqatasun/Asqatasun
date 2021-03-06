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
import org.asqatasun.rules.rgaa40.test.Rgaa40RuleImplementationTestCase;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for implementation of rule 7.4.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/07.Scripts/Rule-7-4-1.md">rule 7.4.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-7-4-1">7.4.1 rule specification</a>
 */
public class Rgaa40Rule070401Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa40Rule070401Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa40.Rgaa40Rule070401");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.7.4.1-3NMI-01");
        addWebResource("Rgaa40.Test.7.4.1-3NMI-02");
        addWebResource("Rgaa40.Test.7.4.1-3NMI-03");
        addWebResource("Rgaa40.Test.7.4.1-3NMI-04");
        addWebResource("Rgaa40.Test.7.4.1-3NMI-05");
        addWebResource("Rgaa40.Test.7.4.1-3NMI-06");
        addWebResource("Rgaa40.Test.7.4.1-3NMI-07");
        addWebResource("Rgaa40.Test.7.4.1-3NMI-08");
        addWebResource("Rgaa40.Test.7.4.1-3NMI-09");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa40.Test.7.4.1-3NMI-01");
        checkResultIsPreQualified(processResult, 0,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
                HtmlElementStore.SELECT_ELEMENT,
                1); 
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.7.4.1-3NMI-02");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CONTEXT_CHANGED_SCRIPT_MSG,
                HtmlElementStore.SELECT_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.7.4.1-3NMI-03");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CONTEXT_CHANGED_SCRIPT_MSG,
                HtmlElementStore.FORM_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.7.4.1-3NMI-04");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CONTEXT_CHANGED_SCRIPT_MSG,
                HtmlElementStore.SELECT_ELEMENT,
                1);        
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.7.4.1-3NMI-05");
        checkResultIsPreQualified(processResult, 0,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
                HtmlElementStore.FORM_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.7.4.1-3NMI-06");
        checkResultIsPreQualified(processResult, 0,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
                HtmlElementStore.FORM_ELEMENT,
                1);
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-07---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.7.4.1-3NMI-07");
        checkResultIsPreQualified(processResult, 0,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
                HtmlElementStore.FORM_ELEMENT,
                1);
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-08---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.7.4.1-3NMI-08");
        checkResultIsPreQualified(processResult, 0,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
                HtmlElementStore.FORM_ELEMENT,
                1);
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-09---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.7.4.1-3NMI-09");
        checkResultIsPreQualified(processResult, 2,  2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CONTEXT_CHANGED_SCRIPT_MSG,
                HtmlElementStore.FORM_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CONTEXT_CHANGED_SCRIPT_MSG,
                HtmlElementStore.SELECT_ELEMENT,
                2);        
    }

    /**
     * 
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
