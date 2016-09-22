/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.asqatasun.rules.rgaa32016;

import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.rules.rgaa32016.test.Rgaa32016RuleImplementationTestCase;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 1.5.1 of the referential RGAA 3.2016
 *
 * @author jkowalczyk
 */
public class Rgaa32016Rule010501Test extends Rgaa32016RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa32016Rule010501Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa32016.Rgaa32016Rule010501");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa32016.Test.01.05.01-3NMI-01");
        addWebResource("Rgaa32016.Test.01.05.01-3NMI-02");
        addWebResource("Rgaa32016.Test.01.05.01-3NMI-03");
        addWebResource("Rgaa32016.Test.01.05.01-3NMI-04");
        addWebResource("Rgaa32016.Test.01.05.01-3NMI-05");
        addWebResource("Rgaa32016.Test.01.05.01-3NMI-06");
        addWebResource("Rgaa32016.Test.01.05.01-3NMI-07");
        addWebResource("Rgaa32016.Test.01.05.01-3NMI-08");
        addWebResource("Rgaa32016.Test.01.05.01-3NMI-09");
        addWebResource("Rgaa32016.Test.01.05.01-3NMI-10");
        addWebResource("Rgaa32016.Test.01.05.01-4NA-01");
        addWebResource("Rgaa32016.Test.01.05.01-4NA-02");
        addWebResource("Rgaa32016.Test.01.05.01-4NA-03");
        addWebResource("Rgaa32016.Test.01.05.01-4NA-04");
        addWebResource("Rgaa32016.Test.01.05.01-4NA-05");
        addWebResource("Rgaa32016.Test.01.05.01-4NA-06");
        addWebResource("Rgaa32016.Test.01.05.01-4NA-07");
        addWebResource("Rgaa32016.Test.01.05.01-4NA-08");
        addWebResource("Rgaa32016.Test.01.05.01-4NA-09");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa32016.Test.01.05.01-3NMI-01");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_CAPTCHA_ALTERNATIVE_ACCES_MSG,
                HtmlElementStore.IMG_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.05.01-3NMI-02");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_CAPTCHA_ALTERNATIVE_ACCES_MSG,
                HtmlElementStore.IMG_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.05.01-3NMI-03");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_CAPTCHA_ALTERNATIVE_ACCES_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.05.01-3NMI-04");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_CAPTCHA_ALTERNATIVE_ACCES_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.05.01-3NMI-05");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_CAPTCHA_ALTERNATIVE_ACCES_MSG,
                HtmlElementStore.SVG_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.05.01-3NMI-06");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_CAPTCHA_ALTERNATIVE_ACCES_MSG,
                HtmlElementStore.SVG_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-07---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.05.01-3NMI-07");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_CAPTCHA_ALTERNATIVE_ACCES_MSG,
                HtmlElementStore.OBJECT_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-08---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.05.01-3NMI-08");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_CAPTCHA_ALTERNATIVE_ACCES_MSG,
                HtmlElementStore.OBJECT_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-09---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.05.01-3NMI-09");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_CAPTCHA_ALTERNATIVE_ACCES_MSG,
                HtmlElementStore.EMBED_ELEMENT,
                1);
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-10---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.05.01-3NMI-10");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_CAPTCHA_ALTERNATIVE_ACCES_MSG,
                HtmlElementStore.CANVAS_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.01.05.01-4NA-01"));

        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.01.05.01-4NA-02"));

        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.01.05.01-4NA-03"));

        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.01.05.01-4NA-04"));

        //----------------------------------------------------------------------
        //------------------------------4NA-05----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.01.05.01-4NA-05"));

        //----------------------------------------------------------------------
        //------------------------------4NA-06----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.01.05.01-4NA-06"));

        //----------------------------------------------------------------------
        //------------------------------4NA-07----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.01.05.01-4NA-07"));

        //----------------------------------------------------------------------
        //------------------------------4NA-08----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.01.05.01-4NA-08"));

        //----------------------------------------------------------------------
        //------------------------------4NA-09----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.01.05.01-4NA-09"));
    }

}
