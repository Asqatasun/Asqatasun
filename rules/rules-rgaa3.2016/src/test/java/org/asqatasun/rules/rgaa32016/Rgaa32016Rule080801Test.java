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

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.asqatasun.entity.audit.*;
import org.asqatasun.rules.rgaa32016.test.Rgaa32016RuleImplementationTestCase;
import org.asqatasun.rules.keystore.EvidenceStore;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 8.8.1 of the referential RGAA 3.2016
 *
 * @author jkowalczyk
 */
public class Rgaa32016Rule080801Test extends Rgaa32016RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa32016Rule080801Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa32016.Rgaa32016Rule080801");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa32016.Test.08.08.01-1Passed-01");
        addWebResource("Rgaa32016.Test.08.08.01-1Passed-02");
        addWebResource("Rgaa32016.Test.08.08.01-1Passed-03");
        addWebResource("Rgaa32016.Test.08.08.01-1Passed-04");
        addWebResource("Rgaa32016.Test.08.08.01-2Failed-01");
        addWebResource("Rgaa32016.Test.08.08.01-2Failed-02");
        addWebResource("Rgaa32016.Test.08.08.01-2Failed-03");
        addWebResource("Rgaa32016.Test.08.08.01-2Failed-04");
        addWebResource("Rgaa32016.Test.08.08.01-2Failed-05");
        addWebResource("Rgaa32016.Test.08.08.01-4NA-01");
        addWebResource("Rgaa32016.Test.08.08.01-4NA-02");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //---------------------------1Passed-01---------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa32016.Test.08.08.01-1Passed-01"),4);
        
        //----------------------------------------------------------------------
        //---------------------------1Passed-02---------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa32016.Test.08.08.01-1Passed-02"),4);
        
        //----------------------------------------------------------------------
        //---------------------------1Passed-03---------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa32016.Test.08.08.01-1Passed-03"),4);
        
        //----------------------------------------------------------------------
        //---------------------------1Passed-04---------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa32016.Test.08.08.01-1Passed-04"),4);
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa32016.Test.08.08.01-2Failed-01");
        checkResultIsFailed(processResult, 4, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.MALFORMED_LANGUAGE_DECLARATION_MSG,
                HtmlElementStore.DIV_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.LANGUAGE_EE, "fren-FR"));        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.08.08.01-2Failed-02");
        checkResultIsFailed(processResult, 4, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.WRONG_LANGUAGE_DECLARATION_MSG,
                HtmlElementStore.DIV_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.LANGUAGE_EE, "aq"));        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.08.08.01-2Failed-03");
        checkResultIsFailed(processResult, 4, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.MALFORMED_LANGUAGE_DECLARATION_MSG,
                HtmlElementStore.DIV_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.LANGUAGE_EE, "french"));        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.08.08.01-2Failed-04");
        checkResultIsFailed(processResult, 4, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.MALFORMED_LANGUAGE_DECLARATION_MSG,
                HtmlElementStore.DIV_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.LANGUAGE_EE, "fr-F"));        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.08.08.01-2Failed-05");
        checkResultIsFailed(processResult, 4, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.MALFORMED_LANGUAGE_DECLARATION_MSG,
                HtmlElementStore.DIV_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.LANGUAGE_EE, "fr/FR"));        

        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.08.08.01-4NA-01"));        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.08.08.01-4NA-02"));
    }

}
