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
import org.asqatasun.rules.rgaa40.test.Rgaa40RuleImplementationTestCase;

import static org.asqatasun.entity.audit.TestSolution.FAILED;
import static org.asqatasun.entity.audit.TestSolution.NEED_MORE_INFO;
import static org.asqatasun.rules.keystore.AttributeStore.*;
import static org.asqatasun.rules.keystore.EvidenceStore.COMPUTED_LINK_TITLE;
import static org.asqatasun.rules.keystore.HtmlElementStore.INPUT_ELEMENT;
import static org.asqatasun.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.*;

/**
 * Unit test class for implementation of rule 1.1.3 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/01.Images/Rule-1-1-3.md">rule 1.1.3 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-1-3">1.1.3 rule specification</a>
 */
public class Rgaa40Rule010103Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa40Rule010103Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa40.Rgaa40Rule010103");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.1.1.3-1Passed-01",
            createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "informative-image"));
        addWebResource("Rgaa40.Test.1.1.3-2Failed-01",
            createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "informative-image"));
        addWebResource("Rgaa40.Test.1.1.3-3NMI-01");
        addWebResource("Rgaa40.Test.1.1.3-4NA-01",
            createParameter("Rules", DECORATIVE_IMAGE_MARKER, "decorative-image"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa40.Test.1.1.3-1Passed-01"), 4);

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa40.Test.1.1.3-2Failed-01");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
            processResult,
            FAILED,
            ALT_MISSING_MSG,
            INPUT_ELEMENT,
            1,
            new ImmutablePair<>(ALT_ATTR, "attribute-absent"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "attribute-absent"),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));
        checkRemarkIsPresent(
            processResult,
            FAILED,
            ALT_MISSING_MSG,
            INPUT_ELEMENT,
            2,
            new ImmutablePair<>(ALT_ATTR, "attribute-absent"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "attribute-absent"),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.1.1.3-3NMI-01");
        checkResultIsPreQualified(processResult, 9, 9);
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            INPUT_ELEMENT,
            1,
            new ImmutablePair<>(ALT_ATTR, "attribute-absent"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "meaning of the image"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "meaning of the image"),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            INPUT_ELEMENT,
            2,
            new ImmutablePair<>(ALT_ATTR, "attribute-absent"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Image description"),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            INPUT_ELEMENT,
            3,
            new ImmutablePair<>(ALT_ATTR, "Meaning of image"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Meaning of image"),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            INPUT_ELEMENT,
            4,
            new ImmutablePair<>(ALT_ATTR, "attribute-absent"),
            new ImmutablePair<>(TITLE_ATTR, "Meaning of image"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Meaning of image"),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            INPUT_ELEMENT,
            5,
            new ImmutablePair<>(ALT_ATTR, "Meaning of image from alt"),
            new ImmutablePair<>(TITLE_ATTR, "Meaning of image from title"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "Meaning of image from aria-label"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Image description from aria-labelledby"),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            INPUT_ELEMENT,
            6,
            new ImmutablePair<>(ALT_ATTR, "Meaning of image from alt"),
            new ImmutablePair<>(TITLE_ATTR, "Meaning of image from title"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "Meaning of image from aria-label"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Meaning of image from aria-label"),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            INPUT_ELEMENT,
            7,
            new ImmutablePair<>(ALT_ATTR, "Meaning of image from alt"),
            new ImmutablePair<>(TITLE_ATTR, "Meaning of image from title"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Meaning of image from alt"),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            INPUT_ELEMENT,
            8,
            new ImmutablePair<>(ALT_ATTR, "attribute-absent"),
            new ImmutablePair<>(TITLE_ATTR, "Meaning of image from title"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Meaning of image from title"),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITHOUT_TEXTUAL_ALTERNATIVE_MSG,
            INPUT_ELEMENT,
            9,
            new ImmutablePair<>(ALT_ATTR, "attribute-absent"),
            new ImmutablePair<>(TITLE_ATTR, "attribute-absent"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "attribute-absent"),
            new ImmutablePair<>(SRC_ATTR, "dummy.png"));


        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.1.1.3-4NA-01"));
    }

}
