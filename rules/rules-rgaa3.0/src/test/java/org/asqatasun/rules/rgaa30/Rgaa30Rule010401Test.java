/*
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
package org.asqatasun.rules.rgaa30;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import static org.asqatasun.rules.keystore.AttributeStore.ALT_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.SRC_ATTR;
import static org.asqatasun.rules.keystore.HtmlElementStore.IMG_ELEMENT;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_CAPTCHA_ALTERNATIVE_MSG;
import org.asqatasun.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 1.4.1 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule010401Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule010401Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa30.Rgaa30Rule010401");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.01.04.01-3NMI-01");
        addWebResource("Rgaa30.Test.01.04.01-4NA-01");
        addWebResource("Rgaa30.Test.01.04.01-4NA-02");
        addWebResource("Rgaa30.Test.01.04.01-4NA-03");
        addWebResource("Rgaa30.Test.01.04.01-4NA-04");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.01.04.01-3NMI-01");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                CHECK_CAPTCHA_ALTERNATIVE_MSG, 
                IMG_ELEMENT, 
                1, 
                new ImmutablePair(ALT_ATTR, "captcha"),
                new ImmutablePair(SRC_ATTR, "mock_image.jpg"));

        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.04.01-4NA-01"));


        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.04.01-4NA-02"));


        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.04.01-4NA-03"));


        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.04.01-4NA-04"));
    }

}
