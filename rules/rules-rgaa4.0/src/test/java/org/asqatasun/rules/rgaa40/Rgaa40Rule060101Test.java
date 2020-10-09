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
import org.asqatasun.rules.keystore.AttributeStore;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;
import org.asqatasun.rules.rgaa40.test.Rgaa40RuleImplementationTestCase;

import static org.asqatasun.rules.keystore.AttributeStore.ARIA_LABEL_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.asqatasun.rules.keystore.EvidenceStore.COMPUTED_LINK_TITLE;
import static org.asqatasun.rules.keystore.HtmlElementStore.*;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG;

/**
 * Unit test class for implementation of rule 6.1.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/06.Links/Rule-6-1-1.md">rule 6.1.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-6-1-1">6.1.1 rule specification</a>
 */
public class Rgaa40Rule060101Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa40Rule060101Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
            "org.asqatasun.rules.rgaa40.Rgaa40Rule060101");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.6.1.1-2Failed-01");
        addWebResource("Rgaa40.Test.6.1.1-3NMI-01");
        addWebResource("Rgaa40.Test.6.1.1-4NA-01");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //---------------------------2Failed-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa40.Test.6.1.1-2Failed-01");
        checkResultIsFailed(processResult, 28, 28);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.UNEXPLICIT_LINK_MSG,
            HtmlElementStore.A_ELEMENT,
            1,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "cliquez ici"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "cliquez ici"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.UNEXPLICIT_LINK_MSG,
            HtmlElementStore.A_ELEMENT,
            2,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "cliquez ici"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "cliquez ici"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.UNEXPLICIT_LINK_MSG,
            HtmlElementStore.A_ELEMENT,
            3,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "cliquez ici"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.UNEXPLICIT_LINK_MSG,
            HtmlElementStore.A_ELEMENT,
            4,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "cliquez ici"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.UNEXPLICIT_LINK_MSG,
            HtmlElementStore.A_ELEMENT,
            5,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "-->;*"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "-->;*"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "-->;*"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.UNEXPLICIT_LINK_MSG,
            HtmlElementStore.A_ELEMENT,
            6,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "-->;*"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "-->;*"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.UNEXPLICIT_LINK_MSG,
            HtmlElementStore.A_ELEMENT,
            7,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "-->;*"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "-->;*"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.UNEXPLICIT_LINK_MSG,
            HtmlElementStore.A_ELEMENT,
            8,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "cliquez ici"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "cliquez ici"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.UNEXPLICIT_LINK_MSG,
            HtmlElementStore.A_ELEMENT,
            9,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, ""),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "-->;*"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "-->;*"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.UNEXPLICIT_LINK_MSG,
            HtmlElementStore.A_ELEMENT,
            10,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, ""),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "-->;*-->;*"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.UNEXPLICIT_LINK_MSG,
            HtmlElementStore.DIV_ELEMENT,
            11,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, ""),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "cliquez ici"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "cliquez ici"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.UNEXPLICIT_LINK_MSG,
            HtmlElementStore.DIV_ELEMENT,
            12,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, ""),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "cliquez ici"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.UNEXPLICIT_LINK_MSG,
            HtmlElementStore.DIV_ELEMENT,
            13,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "cliquez ici"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.UNEXPLICIT_LINK_MSG,
            HtmlElementStore.DIV_ELEMENT,
            14,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, ""),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "cliquez ici"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "cliquez ici"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            HtmlElementStore.A_ELEMENT,
            15,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "lien texte"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "lien texte"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            HtmlElementStore.A_ELEMENT,
            16,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "intitulé de lien"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "intitulé de lien"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            HtmlElementStore.A_ELEMENT,
            17,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "intitulé de lien"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "intitulé de lien"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            HtmlElementStore.A_ELEMENT,
            18,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "contexte du lien"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "contexte du lien"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
            HtmlElementStore.A_ELEMENT,
            19,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "cliquez ici"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "cliquez ici"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
            HtmlElementStore.A_ELEMENT,
            20,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "cliquez ici"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
            HtmlElementStore.A_ELEMENT,
            21,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "-->;*"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "-->;*"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "-->;*"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
            HtmlElementStore.A_ELEMENT,
            22,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "-->;*"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "-->;*"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
            HtmlElementStore.A_ELEMENT,
            23,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, ""),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "-->;*"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "-->;*"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
            HtmlElementStore.A_ELEMENT,
            24,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, ""),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "-->;*-->;*"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
            HtmlElementStore.DIV_ELEMENT,
            25,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, ""),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "cliquez ici"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "cliquez ici"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
            HtmlElementStore.DIV_ELEMENT,
            26,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, ""),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "cliquez ici"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
            HtmlElementStore.DIV_ELEMENT,
            27,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "cliquez ici"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
            HtmlElementStore.DIV_ELEMENT,
            28,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, ""),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "cliquez ici"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "cliquez ici"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.6.1.1-3NMI-01");
        checkResultIsPreQualified(processResult, 25, 25);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            HtmlElementStore.A_ELEMENT,
            1,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "lien texte"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "lien texte"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            HtmlElementStore.A_ELEMENT,
            2,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "intitulé de lien"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "intitulé de lien"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            HtmlElementStore.A_ELEMENT,
            3,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "intitulé de lien"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "intitulé de lien"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            HtmlElementStore.A_ELEMENT,
            4,
            new ImmutablePair<>(HtmlElementStore.TEXT_ELEMENT2, "contexte du lien"),
            new ImmutablePair<>(AttributeStore.TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "contexte du lien"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            5,
            new ImmutablePair<>(TEXT_ELEMENT2, "Page 2"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Page 2"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            6,
            new ImmutablePair<>(TEXT_ELEMENT2, "Page 3"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Page 3"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            7,
            new ImmutablePair<>(TEXT_ELEMENT2, "Jump to next page"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Jump to next page"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            8,
            new ImmutablePair<>(TEXT_ELEMENT2, "Tomb Raider: Legend"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Tomb Raider: Legend"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            9,
            new ImmutablePair<>(TEXT_ELEMENT2, "See Images"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "See Images"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            10,
            new ImmutablePair<>(TEXT_ELEMENT2, "(Download Demo)"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "(Download Demo)"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            11,
            new ImmutablePair<>(TEXT_ELEMENT2, "F.E.A.R. Extraction Point"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "F.E.A.R. Extraction Point"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            12,
            new ImmutablePair<>(TEXT_ELEMENT2, "See Images"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "See Images"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            13,
            new ImmutablePair<>(TEXT_ELEMENT2, "(Download Demo)"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "(Download Demo)"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            14,
            new ImmutablePair<>(TEXT_ELEMENT2, "[Read more...]"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "[Read more...]"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            15,
            new ImmutablePair<>(TEXT_ELEMENT2, "$67/day"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "$67/day"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            16,
            new ImmutablePair<>(TEXT_ELEMENT2, "$68/day"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "$68/day"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            17,
            new ImmutablePair<>(TEXT_ELEMENT2, "$72/day"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "$72/day"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            18,
            new ImmutablePair<>(TEXT_ELEMENT2, "$74/day"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "$74/day"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            19,
            new ImmutablePair<>(TEXT_ELEMENT2, "$74/day"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "$74/day"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            20,
            new ImmutablePair<>(TEXT_ELEMENT2, "$68/day"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "$68/day"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            21,
            new ImmutablePair<>(TEXT_ELEMENT2, "$69/day"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "$69/day"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            22,
            new ImmutablePair<>(TEXT_ELEMENT2, "$74/day"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "$74/day"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            23,
            new ImmutablePair<>(TEXT_ELEMENT2, "$76/day"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "$76/day"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            24,
            new ImmutablePair<>(TEXT_ELEMENT2, "$76/day"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "$76/day"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
            A_ELEMENT,
            25,
            new ImmutablePair<>(TEXT_ELEMENT2, "Accessible Name and Description Computation (from W3C)"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Accessible Name and Description Computation (from W3C)"));
        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.6.1.1-4NA-01"));
    }
}
