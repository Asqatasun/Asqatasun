/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.asqatasun.rules.rgaa32016;

import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.rules.rgaa32016.test.Rgaa32016RuleImplementationTestCase;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 8.9.1 of the referential RGAA 3.2016
 *
 * @author jkowalczyk
 */
public class Rgaa32016Rule080901Test extends Rgaa32016RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa32016Rule080901Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa32016.Rgaa32016Rule080901");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa32016.Test.08.09.01-2Failed-01");
        addWebResource("Rgaa32016.Test.08.09.01-2Failed-02");
        addWebResource("Rgaa32016.Test.08.09.01-2Failed-03");
        addWebResource("Rgaa32016.Test.08.09.01-2Failed-04");
        addWebResource("Rgaa32016.Test.08.09.01-3NMI-01");
        addWebResource("Rgaa32016.Test.08.09.01-3NMI-02");
        addWebResource("Rgaa32016.Test.08.09.01-3NMI-03");
        addWebResource("Rgaa32016.Test.08.09.01-3NMI-04");
        addWebResource("Rgaa32016.Test.08.09.01-3NMI-05");
        addWebResource("Rgaa32016.Test.08.09.01-3NMI-06");
        addWebResource("Rgaa32016.Test.08.09.01-3NMI-07");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //---------------------------2Failed-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa32016.Test.08.09.01-2Failed-01");
        checkResultIsFailed(processResult, 16, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.LINK_WITHOUT_TARGET_MSG,
                HtmlElementStore.A_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.08.09.01-2Failed-02");
        checkResultIsFailed(processResult, 16, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.LINK_WITHOUT_TARGET_MSG,
                HtmlElementStore.A_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.08.09.01-2Failed-03");
        checkResultIsFailed(processResult, 16, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.FIELDSET_NOT_WITHIN_FORM_MSG,
                HtmlElementStore.FIELDSET_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.08.09.01-2Failed-04");
        checkResultIsFailed(processResult, 17, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.FIELDSET_NOT_WITHIN_FORM_MSG,
                HtmlElementStore.FIELDSET_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.08.09.01-3NMI-01");
        checkResultIsPreQualified(processResult, 15,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
                "",
                1);        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-02------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.08.09.01-3NMI-02");
        checkResultIsPreQualified(processResult, 16,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
                "",
                1);        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-03------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.08.09.01-3NMI-03");
        checkResultIsPreQualified(processResult, 16,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
                "",
                1);        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-04------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.08.09.01-3NMI-04");
        checkResultIsPreQualified(processResult, 16,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
                "",
                1);        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-05------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.08.09.01-3NMI-05");
        checkResultIsPreQualified(processResult, 17,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
                "",
                1);        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-06------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.08.09.01-3NMI-06");
        checkResultIsPreQualified(processResult, 17,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
                "",
                1);        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-07------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.08.09.01-3NMI-07");
        checkResultIsPreQualified(processResult, 17,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
                "",
                1);

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
