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

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.tanaguru.entity.audit.*;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.tanaguru.rules.keystore.EvidenceStore;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 8-7-1 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule080701Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule080701Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule080701");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.08.07.01-1Passed-01");
        addWebResource("Rgaa30.Test.08.07.01-1Passed-02");
        addWebResource("Rgaa30.Test.08.07.01-1Passed-03");
        addWebResource("Rgaa30.Test.08.07.01-1Passed-04");
        addWebResource("Rgaa30.Test.08.07.01-1Passed-05");
        addWebResource("Rgaa30.Test.08.07.01-2Failed-01");
        addWebResource("Rgaa30.Test.08.07.01-2Failed-02");
        addWebResource("Rgaa30.Test.08.07.01-2Failed-03");
        addWebResource("Rgaa30.Test.08.07.01-2Failed-04");
        addWebResource("Rgaa30.Test.08.07.01-2Failed-05");
        addWebResource("Rgaa30.Test.08.07.01-2Failed-06");
        addWebResource("Rgaa30.Test.08.07.01-2Failed-07");
        addWebResource("Rgaa30.Test.08.07.01-3NMI-01");
        addWebResource("Rgaa30.Test.08.07.01-3NMI-02");
        addWebResource("Rgaa30.Test.08.07.01-3NMI-03");
        addWebResource("Rgaa30.Test.08.07.01-3NMI-04");
        addWebResource("Rgaa30.Test.08.07.01-4NA-01");
        addWebResource("Rgaa30.Test.08.07.01-4NA-02");
        addWebResource("Rgaa30.Test.08.07.01-4NA-03");
        addWebResource("Rgaa30.Test.08.07.01-4NA-04");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //---------------------------1Passed-01---------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.07.01-1Passed-01"),6);        
        
        //----------------------------------------------------------------------
        //---------------------------1Passed-02---------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.07.01-1Passed-02"),6);
        
        //----------------------------------------------------------------------
        //---------------------------1Passed-03---------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.07.01-1Passed-03"),6);
        
        //----------------------------------------------------------------------
        //---------------------------1Passed-04---------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.07.01-1Passed-04"),6);
        
        //----------------------------------------------------------------------
        //---------------------------1Passed-05---------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.07.01-1Passed-05"),6);
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.08.07.01-2Failed-01");
        checkResultIsFailed(processResult, 6, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.LANGUAGE_CHANGE_MISSING_MSG,
                HtmlElementStore.P_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.LANGUAGE_EE,"en"),
                new ImmutablePair(EvidenceStore.DETECTED_LANGUAGE_EE,"fr"),
                new ImmutablePair(EvidenceStore.EXTRACTED_TEXT_EE,"L'accessibilité du web est la problématique de l'accès aux"));
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.07.01-2Failed-02");
        checkResultIsFailed(processResult, 6, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.LANGUAGE_CHANGE_MISSING_MSG,
                HtmlElementStore.P_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.DEFAULT_LANGUAGE_EE,"en"),
                new ImmutablePair(EvidenceStore.CURRENT_LANGUAGE_EE,"fr"),
                new ImmutablePair(EvidenceStore.DETECTED_LANGUAGE_EE,"en"),
                new ImmutablePair(EvidenceStore.EXTRACTED_TEXT_EE,"Web accessibility refers to the inclusive practice of making"));
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.07.01-2Failed-03");
        checkResultIsFailed(processResult, 6, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.LANGUAGE_CHANGE_MISSING_MSG,
                HtmlElementStore.SPAN_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.DEFAULT_LANGUAGE_EE,"en"),
                new ImmutablePair(EvidenceStore.CURRENT_LANGUAGE_EE,"fr"),
                new ImmutablePair(EvidenceStore.DETECTED_LANGUAGE_EE,"en"),
                new ImmutablePair(EvidenceStore.EXTRACTED_TEXT_EE,"Web accessibility refers to the inclusive practice of making"));
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.07.01-2Failed-04");
        checkResultIsFailed(processResult, 6, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.LANGUAGE_CHANGE_MISSING_MSG,
                HtmlElementStore.P_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.DEFAULT_LANGUAGE_EE,"en"),
                new ImmutablePair(EvidenceStore.CURRENT_LANGUAGE_EE,"dummy"),
                new ImmutablePair(EvidenceStore.DETECTED_LANGUAGE_EE,"en"),
                new ImmutablePair(EvidenceStore.EXTRACTED_TEXT_EE,"Web accessibility refers to the inclusive practice of making"));
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.07.01-2Failed-05");
        checkResultIsFailed(processResult, 6, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.LANGUAGE_CHANGE_MISSING_MSG,
                HtmlElementStore.P_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.DEFAULT_LANGUAGE_EE,"en"),
                new ImmutablePair(EvidenceStore.CURRENT_LANGUAGE_EE,"dummy"),
                new ImmutablePair(EvidenceStore.DETECTED_LANGUAGE_EE,"en"),
                new ImmutablePair(EvidenceStore.EXTRACTED_TEXT_EE,"Web accessibility refers to the inclusive practice of making"));
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.07.01-2Failed-06");
        checkResultIsFailed(processResult, 6, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.LANGUAGE_CHANGE_MISSING_MSG,
                HtmlElementStore.P_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.DEFAULT_LANGUAGE_EE,"en"),
                new ImmutablePair(EvidenceStore.CURRENT_LANGUAGE_EE,"dummy"),
                new ImmutablePair(EvidenceStore.DETECTED_LANGUAGE_EE,"en"),
                new ImmutablePair(EvidenceStore.EXTRACTED_TEXT_EE,"Web accessibility refers to the inclusive practice of making"));
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-07---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.07.01-2Failed-07");
        checkResultIsFailed(processResult, 6, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.LANGUAGE_CHANGE_MISSING_MSG,
                HtmlElementStore.SPAN_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.DEFAULT_LANGUAGE_EE,"en"),
                new ImmutablePair(EvidenceStore.CURRENT_LANGUAGE_EE,"dummy"),
                new ImmutablePair(EvidenceStore.DETECTED_LANGUAGE_EE,"en"),
                new ImmutablePair(EvidenceStore.EXTRACTED_TEXT_EE,"Web accessibility refers to the inclusive practice of making"));
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-01------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.07.01-3NMI-01");
        checkResultIsPreQualified(processResult, 6,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_SHORT_TEST_MSG,
                "",
                1);        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-02------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.07.01-3NMI-02");
        checkResultIsPreQualified(processResult, 5,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_SHORT_TEST_MSG,
                "",
                1);        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-03------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.07.01-3NMI-03");
        checkResultIsPreQualified(processResult, 5,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_SHORT_TEST_MSG,
                "",
                1);        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-04------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.07.01-3NMI-04");
        checkResultIsPreQualified(processResult, 5,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_SHORT_TEST_MSG,
                "",
                1);        
        
        //----------------------------------------------------------------------
        //---------------------------4NA-01-------------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.08.07.01-4NA-01"));
        
        //----------------------------------------------------------------------
        //---------------------------4NA-02-------------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.08.07.01-4NA-02"));
        
        //----------------------------------------------------------------------
        //---------------------------4NA-03-------------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.08.07.01-4NA-03"),7);
        
        //----------------------------------------------------------------------
        //---------------------------4NA-04-------------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.08.07.01-4NA-04"),7);
    }

}