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
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.tanaguru.rules.keystore.EvidenceStore;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 8-8-2 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule080802Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule080802Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule080802");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.08.08.02-1Passed-01");
        addWebResource("Rgaa30.Test.08.08.02-2Failed-01");
        addWebResource("Rgaa30.Test.08.08.02-3NMI-01");
        addWebResource("Rgaa30.Test.08.08.02-3NMI-02");
        addWebResource("Rgaa30.Test.08.08.02-3NMI-03");
        addWebResource("Rgaa30.Test.08.08.02-4NA-01");
        addWebResource("Rgaa30.Test.08.08.02-4NA-02");
        addWebResource("Rgaa30.Test.08.08.02-4NA-03");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //---------------------------1Passed-01---------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.08.02-1Passed-01"),1);        
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.08.08.02-2Failed-01");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.IRRELEVANT_LANG_DECL_MSG,
                HtmlElementStore.DIV_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.LANGUAGE_EE, "fr"),
                new ImmutablePair(EvidenceStore.DETECTED_LANGUAGE_EE, "en"),
                new ImmutablePair(EvidenceStore.EXTRACTED_TEXT_EE, "Some text is written here in english"));        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.08.02-3NMI-01");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.SUSPECTED_RELEVANT_LANG_DECL_MSG,
                HtmlElementStore.DIV_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.LANGUAGE_EE, "fr"),
                new ImmutablePair(EvidenceStore.DETECTED_LANGUAGE_EE, "fr"),
                new ImmutablePair(EvidenceStore.EXTRACTED_TEXT_EE, "Texte en francais"));        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.08.02-3NMI-02");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.SUSPECTED_RELEVANT_LANG_DECL_MSG,
                HtmlElementStore.DIV_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.LANGUAGE_EE, "fr"),
                new ImmutablePair(EvidenceStore.DETECTED_LANGUAGE_EE, "fr"),
                new ImmutablePair(EvidenceStore.EXTRACTED_TEXT_EE, "Texte en francais"));        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.08.02-3NMI-03");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.SUSPECTED_IRRELEVANT_LANG_DECL_MSG,
                HtmlElementStore.DIV_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.LANGUAGE_EE, "en"),
                new ImmutablePair(EvidenceStore.DETECTED_LANGUAGE_EE, "fr"),
                new ImmutablePair(EvidenceStore.EXTRACTED_TEXT_EE, "Texte en francais"));        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.08.08.02-4NA-01"));        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.08.08.02-4NA-02"));        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.08.08.02-4NA-03"), 1);
    }

}