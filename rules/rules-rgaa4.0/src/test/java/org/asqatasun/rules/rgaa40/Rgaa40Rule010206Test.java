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

import static org.asqatasun.entity.audit.TestSolution.FAILED;
import static org.asqatasun.entity.audit.TestSolution.NEED_MORE_INFO;
import static org.asqatasun.rules.keystore.AttributeStore.*;
import static org.asqatasun.rules.keystore.EvidenceStore.COMPUTED_LINK_TITLE;
import static org.asqatasun.rules.keystore.HtmlElementStore.EMBED_ELEMENT;
import static org.asqatasun.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.asqatasun.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.*;
import static org.asqatasun.rules.keystore.RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_TEXTUAL_ALTERNATIVE_MSG;

/**
 * Unit test class for implementation of rule 1.2.6 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/01.Images/Rule-1-2-6.md">rule 1.2.6 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-2-6">1.2.6 rule specification</a>
 */
public class Rgaa40Rule010206Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa40Rule010206Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
            "org.asqatasun.rules.rgaa40.Rgaa40Rule010206");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.1.2.6-1Passed-01",
            createParameter("Rules", DECORATIVE_IMAGE_MARKER, "decorative-image"));
        addWebResource("Rgaa40.Test.1.2.6-2Failed-01",
            createParameter("Rules", DECORATIVE_IMAGE_MARKER, "decorative-image"));
        addWebResource("Rgaa40.Test.1.2.6-3NMI-01");
        addWebResource("Rgaa40.Test.1.2.6-4NA-01",
            createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "informative-image"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa40.Test.1.2.6-1Passed-01"), 2);

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa40.Test.1.2.6-2Failed-01");
        checkResultIsFailed(processResult, 4, 4);
        checkRemarkIsPresent(
            processResult,
            FAILED,
            DECORATIVE_ELEMENT_WITH_NOT_EMPTY_TEXTUAL_ALTERNATIVE_MSG,
            EMBED_ELEMENT,
            1,
            new ImmutablePair<>(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));
        checkRemarkIsPresent(
            processResult,
            FAILED,
            DECORATIVE_ELEMENT_WITH_NOT_EMPTY_TEXTUAL_ALTERNATIVE_MSG,
            EMBED_ELEMENT,
            2,
            new ImmutablePair<>(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));
        checkRemarkIsPresent(
            processResult,
            FAILED,
            DECORATIVE_ELEMENT_WITH_NOT_EMPTY_TEXTUAL_ALTERNATIVE_MSG,
            EMBED_ELEMENT,
            3,
            new ImmutablePair<>(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ""),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));
        checkRemarkIsPresent(
            processResult,
            FAILED,
            DECORATIVE_ELEMENT_WITH_NOT_EMPTY_TEXTUAL_ALTERNATIVE_MSG,
            EMBED_ELEMENT,
            4,
            new ImmutablePair<>(TITLE_ATTR, ""),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.1.2.6-3NMI-01");
        checkResultIsPreQualified(processResult, 6, 6);
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            EMBED_ELEMENT,
            1,
            new ImmutablePair<>(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            EMBED_ELEMENT,
            2,
            new ImmutablePair<>(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            EMBED_ELEMENT,
            3,
            new ImmutablePair<>(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ""),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            EMBED_ELEMENT,
            4,
            new ImmutablePair<>(TITLE_ATTR, ""),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITHOUT_TEXTUAL_ALTERNATIVE_MSG,
            EMBED_ELEMENT,
            5,
            new ImmutablePair<>(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITHOUT_TEXTUAL_ALTERNATIVE_MSG,
            EMBED_ELEMENT,
            6,
            new ImmutablePair<>(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));


        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.1.2.6-4NA-01"));
    }

}
