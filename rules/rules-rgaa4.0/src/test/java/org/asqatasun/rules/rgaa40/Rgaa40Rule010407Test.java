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

import static org.asqatasun.entity.audit.TestSolution.NEED_MORE_INFO;
import static org.asqatasun.rules.keystore.AttributeStore.ARIA_LABEL_ATTR;
import static org.asqatasun.rules.keystore.EvidenceStore.COMPUTED_LINK_TITLE;
import static org.asqatasun.rules.keystore.HtmlElementStore.CANVAS_ELEMENT;
import static org.asqatasun.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_CAPTCHA_ALTERNATIVE_MSG;

/**
 * Unit test class for implementation of rule 1.4.7 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/01.Images/Rule-1-4-7.md">rule 1.4.7 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-4-7">1.4.7 rule specification</a>
 */
public class Rgaa40Rule010407Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa40Rule010407Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa40.Rgaa40Rule010407");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.1.4.7-3NMI-01");
        addWebResource("Rgaa40.Test.1.4.7-4NA-01");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa40.Test.1.4.7-3NMI-01");
        checkResultIsPreQualified(processResult, 3, 3);
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_CAPTCHA_ALTERNATIVE_MSG,
            CANVAS_ELEMENT,
            1,
            new ImmutablePair<>(TEXT_ELEMENT2, ""),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "meaning of the captcha image"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "meaning of the captcha image"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_CAPTCHA_ALTERNATIVE_MSG,
            CANVAS_ELEMENT,
            2,
            new ImmutablePair<>(TEXT_ELEMENT2, ""),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "Captcha Image description"));
        checkRemarkIsPresent(
            processResult,
            NEED_MORE_INFO,
            CHECK_CAPTCHA_ALTERNATIVE_MSG,
            CANVAS_ELEMENT,
            3,
            new ImmutablePair<>(TEXT_ELEMENT2, "This is the canvas captcha alternative"),
            new ImmutablePair<>(ARIA_LABEL_ATTR, "attribute-absent"),
            new ImmutablePair<>(COMPUTED_LINK_TITLE, "This is the canvas captcha alternative"));


        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.1.4.7-4NA-01"));
    }

}
