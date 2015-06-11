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
package org.opens.tanaguru.rules.rgaa30;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.keystore.EvidenceStore;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 10-1-2 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule100102Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule100102Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.rgaa30.Rgaa30Rule100102");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.10.01.02-1Passed-01");
        addWebResource("Rgaa30.Test.10.01.02-1Passed-02");
        addWebResource("Rgaa30.Test.10.01.02-1Passed-03");
        addWebResource("Rgaa30.Test.10.01.02-1Passed-04");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-01");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-02");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-03");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-04");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-05");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-06");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-07");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-08");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-09");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-10");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.10.01.02-1Passed-01"),12);
       
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.10.01.02-1Passed-02"),13);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.10.01.02-1Passed-03"),13);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.10.01.02-1Passed-04"),18);
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-01");
        checkResultIsFailed(processResult, 12, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                HtmlElementStore.H1_ELEMENT,
                1, 
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "align"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-02");
        checkResultIsFailed(processResult, 12, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                HtmlElementStore.BODY_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "background"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                HtmlElementStore.DIV_ELEMENT,
                2,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "background"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-03");
        checkResultIsFailed(processResult, 11, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                HtmlElementStore.BODY_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "bgcolor"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-04");
        checkResultIsFailed(processResult, 12, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                HtmlElementStore.IMG_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "border"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-05");
        checkResultIsFailed(processResult, 13, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                "font",
                1,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "color"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-06");
        checkResultIsFailed(processResult, 11, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                HtmlElementStore.BODY_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "link"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-07");
        checkResultIsFailed(processResult, 11, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                HtmlElementStore. BODY_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "alink"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-08");
        checkResultIsFailed(processResult, 11, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                HtmlElementStore.BODY_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "vlink"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-09------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-09");
        checkResultIsFailed(processResult, 11, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                HtmlElementStore.BODY_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "text"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-10------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-10");
        checkResultIsFailed(processResult, 14, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                "hr",
                1,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "height"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                "hr",
                1,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "height"));
    }

}