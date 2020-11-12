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
import static org.asqatasun.rules.keystore.HtmlElementStore.AREA_ELEMENT;
import static org.asqatasun.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.*;

/**
 * Unit test class for implementation of rule 1.2.2 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/01.Images/Rule-1-2-2.md">rule 1.2.2 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-2-2">1.2.2 rule specification</a>
 */
public class Rgaa40Rule010202Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa40Rule010202Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
            "org.asqatasun.rules.rgaa40.Rgaa40Rule010202");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.1.2.2-1Passed-01",
            createParameter("Rules", DECORATIVE_IMAGE_MARKER, "decorative-image"));
        addWebResource("Rgaa40.Test.1.2.2-2Failed-01",
            createParameter("Rules", DECORATIVE_IMAGE_MARKER, "decorative-image"));
        addWebResource("Rgaa40.Test.1.2.2-3NMI-01");
        addWebResource("Rgaa40.Test.1.2.2-4NA-01",
            createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "informative-image"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa40.Test.1.2.2-1Passed-01"), 7);

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa40.Test.1.2.2-2Failed-01");
        checkResultIsFailed(processResult, 7, 7);
        checkRemarkIsPresent(
            processResult,
            FAILED,
            DECORATIVE_ELEMENT_WITH_NOT_EMPTY_TEXTUAL_ALTERNATIVE_MSG,
            AREA_ELEMENT,
            1,
            new ImmutablePair<>(ALT_ATTR, "Mozilla"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Mozilla"),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://mozilla.org"));
        checkRemarkIsPresent(
            processResult,
            FAILED,
            DECORATIVE_ELEMENT_WITH_NOT_EMPTY_TEXTUAL_ALTERNATIVE_MSG,
            AREA_ELEMENT,
            2,
            new ImmutablePair<>(ALT_ATTR, "MDN"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "MDN"),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/"));
        checkRemarkIsPresent(
            processResult,
            FAILED,
            DECORATIVE_ELEMENT_WITH_NOT_EMPTY_TEXTUAL_ALTERNATIVE_MSG,
            AREA_ELEMENT,
            3,
            new ImmutablePair<>(ALT_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "Graphics"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Graphics"),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/docs/Web/Guide/Graphics"));
        checkRemarkIsPresent(
            processResult,
            FAILED,
            DECORATIVE_ELEMENT_WITH_NOT_EMPTY_TEXTUAL_ALTERNATIVE_MSG,
            AREA_ELEMENT,
            4,
            new ImmutablePair<>(ALT_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "HTML"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "HTML"),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/docs/Web/HTML"));
        checkRemarkIsPresent(
            processResult,
            FAILED,
            DECORATIVE_ELEMENT_WITH_NOT_EMPTY_TEXTUAL_ALTERNATIVE_MSG,
            AREA_ELEMENT,
            5,
            new ImmutablePair<>(ALT_ATTR, "JavaScript"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "JavaScript"),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/docs/Web/JavaScript"));
        checkRemarkIsPresent(
            processResult,
            FAILED,
            DECORATIVE_ELEMENT_WITH_NOT_EMPTY_TEXTUAL_ALTERNATIVE_MSG,
            AREA_ELEMENT,
            6,
            new ImmutablePair<>(ALT_ATTR, "API"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "API"),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/docs/Web/API"));
        checkRemarkIsPresent(
            processResult,
            FAILED,
            DECORATIVE_ELEMENT_WITH_NOT_EMPTY_TEXTUAL_ALTERNATIVE_MSG,
            AREA_ELEMENT,
            7,
            new ImmutablePair<>(ALT_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "CSS"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "CSS"),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/docs/Web/CSS"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.1.2.2-3NMI-01");
        checkResultIsPreQualified(processResult, 14, 14);
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            AREA_ELEMENT,
            1,
            new ImmutablePair<>(ALT_ATTR, "Mozilla"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Mozilla"),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://mozilla.org"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            AREA_ELEMENT,
            2,
            new ImmutablePair<>(ALT_ATTR, "MDN"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "MDN"),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            AREA_ELEMENT,
            3,
            new ImmutablePair<>(ALT_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "Graphics"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Graphics"),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/docs/Web/Guide/Graphics"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            AREA_ELEMENT,
            4,
            new ImmutablePair<>(ALT_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "HTML"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "HTML"),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/docs/Web/HTML"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            AREA_ELEMENT,
            5,
            new ImmutablePair<>(ALT_ATTR, "JavaScript"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "JavaScript"),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/docs/Web/JavaScript"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            AREA_ELEMENT,
            6,
            new ImmutablePair<>(ALT_ATTR, "Web APIs"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Web APIs"),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/docs/Web/API"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG,
            AREA_ELEMENT,
            7,
            new ImmutablePair<>(ALT_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "CSS"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "CSS"),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/docs/Web/CSS"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_HIDDEN_WITH_ARIA_MSG,
            AREA_ELEMENT,
            8,
            new ImmutablePair<>(ALT_ATTR, "Mozilla"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Mozilla"),
            new ImmutablePair<>(ROLE_ATTR, "presentation"),
            new ImmutablePair<>(HREF_ATTR, "https://mozilla.org"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_HIDDEN_WITH_ARIA_MSG,
            AREA_ELEMENT,
            9,
            new ImmutablePair<>(ALT_ATTR, "MDN"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "MDN"),
            new ImmutablePair<>(ROLE_ATTR, "presentation"),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_HIDDEN_WITH_ARIA_MSG,
            AREA_ELEMENT,
            10,
            new ImmutablePair<>(ALT_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "Graphics"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Graphics"),
            new ImmutablePair<>(ROLE_ATTR, "presentation"),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/docs/Web/Guide/Graphics"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_HIDDEN_WITH_ARIA_MSG,
            AREA_ELEMENT,
            11,
            new ImmutablePair<>(ALT_ATTR, "HTML"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "HTML"),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/docs/Web/HTML"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_HIDDEN_WITH_ARIA_MSG,
            AREA_ELEMENT,
            12,
            new ImmutablePair<>(ALT_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "JavaScript"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "JavaScript"),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/docs/Web/JavaScript"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITHOUT_TEXTUAL_ALTERNATIVE_MSG,
            AREA_ELEMENT,
            13,
            new ImmutablePair<>(ALT_ATTR, ""),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/docs/Web/API"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_NATURE_OF_IMAGE_WITHOUT_TEXTUAL_ALTERNATIVE_MSG,
            AREA_ELEMENT,
            14,
            new ImmutablePair<>(ALT_ATTR, ""),
            new ImmutablePair<>(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(ROLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
            new ImmutablePair<>(HREF_ATTR, "https://developer.mozilla.org/docs/Web/CSS"));


        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.1.2.2-4NA-01"));
    }


}
