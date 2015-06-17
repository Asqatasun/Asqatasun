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
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 7-4-1 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule070401Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule070401Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule070401");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.07.04.01-3NMI-01");
        addWebResource("Rgaa30.Test.07.04.01-3NMI-02");
        addWebResource("Rgaa30.Test.07.04.01-3NMI-03");
        addWebResource("Rgaa30.Test.07.04.01-3NMI-04");
        addWebResource("Rgaa30.Test.07.04.01-3NMI-05");
        addWebResource("Rgaa30.Test.07.04.01-3NMI-06");
        addWebResource("Rgaa30.Test.07.04.01-3NMI-07");
        addWebResource("Rgaa30.Test.07.04.01-3NMI-08");
        addWebResource("Rgaa30.Test.07.04.01-3NMI-09");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.07.04.01-3NMI-01");
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
        processResult = processPageTest("Rgaa30.Test.07.04.01-3NMI-02");
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
        processResult = processPageTest("Rgaa30.Test.07.04.01-3NMI-03");
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
        processResult = processPageTest("Rgaa30.Test.07.04.01-3NMI-04");
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
        processResult = processPageTest("Rgaa30.Test.07.04.01-3NMI-05");
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
        processResult = processPageTest("Rgaa30.Test.07.04.01-3NMI-06");
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
        processResult = processPageTest("Rgaa30.Test.07.04.01-3NMI-07");
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
        processResult = processPageTest("Rgaa30.Test.07.04.01-3NMI-08");
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
        processResult = processPageTest("Rgaa30.Test.07.04.01-3NMI-09");
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
