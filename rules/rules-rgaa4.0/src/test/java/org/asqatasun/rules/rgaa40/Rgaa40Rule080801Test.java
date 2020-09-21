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
import org.asqatasun.rules.keystore.EvidenceStore;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;
import org.asqatasun.rules.rgaa40.test.Rgaa40RuleImplementationTestCase;

/**
 * Unit test class for implementation of rule 8.8.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/v5/documentation/en/90_Rules/rgaa4.0/08.Mandatory_elements/Rule-8-8-1.md">rule 8.8.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-8-1">8.8.1 rule specification</a>
 */
public class Rgaa40Rule080801Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa40Rule080801Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
            "org.asqatasun.rules.rgaa40.Rgaa40Rule080801");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.8.8.1-1Passed-01");

        addWebResource("Rgaa40.Test.8.8.1-2Failed-01");
        addWebResource("Rgaa40.Test.8.8.1-2Failed-02");
        addWebResource("Rgaa40.Test.8.8.1-2Failed-03");
        addWebResource("Rgaa40.Test.8.8.1-2Failed-04");
        addWebResource("Rgaa40.Test.8.8.1-2Failed-05");
        addWebResource("Rgaa40.Test.8.8.1-2Failed-06");

        addWebResource("Rgaa40.Test.8.8.1-3NMI-01");
        addWebResource("Rgaa40.Test.8.8.1-3NMI-02");
        addWebResource("Rgaa40.Test.8.8.1-3NMI-03");
        addWebResource("Rgaa40.Test.8.8.1-3NMI-04");

        addWebResource("Rgaa40.Test.8.8.1-4NA-01");
        addWebResource("Rgaa40.Test.8.8.1-4NA-02");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //---------------------------1Passed-01---------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa40.Test.8.8.1-1Passed-01"),2);

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa40.Test.8.8.1-2Failed-01");
        checkResultIsFailed(processResult, 2, 1);
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
        processResult = processPageTest("Rgaa40.Test.8.8.1-2Failed-02");
        checkResultIsFailed(processResult, 2, 1);
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
        processResult = processPageTest("Rgaa40.Test.8.8.1-2Failed-03");
        checkResultIsFailed(processResult, 2, 1);
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
        processResult = processPageTest("Rgaa40.Test.8.8.1-2Failed-04");
        checkResultIsFailed(processResult, 2, 1);
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
        processResult = processPageTest("Rgaa40.Test.8.8.1-2Failed-05");
        checkResultIsFailed(processResult, 2, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.MALFORMED_LANGUAGE_DECLARATION_MSG,
            HtmlElementStore.DIV_ELEMENT,
            1,
            new ImmutablePair(EvidenceStore.LANGUAGE_EE, "fr/FR"));

        //----------------------------------------------------------------------
        //---------------------------2Failed-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.8.1-2Failed-06");
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
        processResult = processPageTest("Rgaa40.Test.8.8.1-3NMI-01");
        checkResultIsPreQualified(processResult, 2,  1);
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
        processResult = processPageTest("Rgaa40.Test.8.8.1-3NMI-02");
        checkResultIsPreQualified(processResult, 2,  1);
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
        //------------------------------3NMI-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.8.1-3NMI-03");
        checkResultIsPreQualified(processResult, 2,  1);
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
        processResult = processPageTest("Rgaa40.Test.8.8.1-3NMI-04");
        checkResultIsPreQualified(processResult, 2,  1);
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
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.8.8.1-4NA-01"));

        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.8.8.1-4NA-02"));
    }

}
