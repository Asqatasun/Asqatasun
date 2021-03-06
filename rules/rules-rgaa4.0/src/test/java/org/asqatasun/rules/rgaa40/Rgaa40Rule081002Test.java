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
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;
import org.asqatasun.rules.rgaa40.test.Rgaa40RuleImplementationTestCase;

import static org.asqatasun.rules.keystore.AttributeStore.DIR_ATTR;
import static org.asqatasun.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;

/**
 * Unit test class for implementation of rule 8.10.2 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/08.Mandatory_elements/Rule-8-10-2.md">rule 8.10.2 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-10-2">8.10.2 rule specification</a>
 */
public class Rgaa40Rule081002Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa40Rule081002Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
            "org.asqatasun.rules.rgaa40.Rgaa40Rule081002");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.8.10.2-2Failed-01");
        addWebResource("Rgaa40.Test.8.10.2-3NMI-01");
        addWebResource("Rgaa40.Test.8.10.2-4NA-01");
    }

    @Override
    protected void setProcess() {

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa40.Test.8.10.2-2Failed-01");
        checkResultIsFailed(processResult, 24, 3);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.DIR_ATTRIBUTE_WITH_NOT_ALLOWED_VALUE_MSG,
            HtmlElementStore.P_ELEMENT,
            1,
            new ImmutablePair(DIR_ATTR, "rightToLeft"),
            new ImmutablePair(TEXT_ELEMENT2, "بعض الكلمات"));

        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.DIR_ATTRIBUTE_WITH_NOT_ALLOWED_VALUE_MSG,
            HtmlElementStore.P_ELEMENT,
            2,
            new ImmutablePair(DIR_ATTR, "leftToRight"),
            new ImmutablePair(TEXT_ELEMENT2, "quelques mots"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.DIR_ATTRIBUTE_WITH_NOT_ALLOWED_VALUE_MSG,
            HtmlElementStore.P_ELEMENT,
            3,
            new ImmutablePair(DIR_ATTR, ""),
            new ImmutablePair(TEXT_ELEMENT2, "כמה מילים"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.10.2-3NMI-01");
        checkResultIsPreQualified(processResult, 25, 4);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.DIR_ATTRIBUTE_WITH_ALLOWED_VALUE_MSG,
            HtmlElementStore.DIV_ELEMENT,
            1,
            new ImmutablePair(DIR_ATTR, "rtl"),
            new ImmutablePair(TEXT_ELEMENT2, "بعض الكلمات"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.DIR_ATTRIBUTE_WITH_ALLOWED_VALUE_MSG,
            HtmlElementStore.P_ELEMENT,
            2,
            new ImmutablePair(DIR_ATTR, "ltr"),
            new ImmutablePair(TEXT_ELEMENT2, "quelques mots"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.DIR_ATTRIBUTE_WITH_ALLOWED_VALUE_MSG,
            HtmlElementStore.SPAN_ELEMENT,
            3,
            new ImmutablePair(DIR_ATTR, "ltr"),
            new ImmutablePair(TEXT_ELEMENT2, "quelques mots en français"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.DIR_ATTRIBUTE_WITH_ALLOWED_VALUE_MSG,
            HtmlElementStore.BLOCKQUOTE_ELEMENT,
            4,
            new ImmutablePair(DIR_ATTR, "rtl"),
            new ImmutablePair(TEXT_ELEMENT2, "כמה מילים"));

        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.8.10.2-4NA-01"));
    }

}
