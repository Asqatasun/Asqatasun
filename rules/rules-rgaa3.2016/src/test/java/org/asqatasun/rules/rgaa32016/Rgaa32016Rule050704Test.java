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
 * Unit test class for the implementation of the rule 5.7.4 of the referential RGAA 3.2016
 *
 * @author jkowalczyk
 */
public class Rgaa32016Rule050704Test extends Rgaa32016RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa32016Rule050704Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa32016.Rgaa32016Rule050704");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa32016.Test.05.07.04-3NMI-01");
        addWebResource("Rgaa32016.Test.05.07.04-3NMI-02");
        addWebResource("Rgaa32016.Test.05.07.04-3NMI-03");
        addWebResource("Rgaa32016.Test.05.07.04-3NMI-04");
        addWebResource("Rgaa32016.Test.05.07.04-3NMI-05");
        addWebResource("Rgaa32016.Test.05.07.04-3NMI-06",
                    createParameter("Rules", "DATA_TABLE_MARKER", "id-data-table"));
        addWebResource("Rgaa32016.Test.05.07.04-4NA-01");
        addWebResource("Rgaa32016.Test.05.07.04-4NA-02");
        addWebResource("Rgaa32016.Test.05.07.04-4NA-03",
                    createParameter("Rules", "PRESENTATION_TABLE_MARKER", "id-presentation-table"));

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01--------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa32016.Test.05.07.04-3NMI-01");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_HEADERS_DEFINITION_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.05.07.04-3NMI-02");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_HEADERS_DEFINITION_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.05.07.04-3NMI-03");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_HEADERS_DEFINITION_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-04--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.05.07.04-3NMI-04");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_HEADERS_DEFINITION_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-05--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.05.07.04-3NMI-05");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_HEADERS_DEFINITION_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-06--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.05.07.04-3NMI-06");
        checkResultIsPreQualified(processResult, 2,  2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_DEFINITION_OF_HEADERS_FOR_DATA_TABLE_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_HEADERS_DEFINITION_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                2);        

        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.05.07.04-4NA-01"));        

        //----------------------------------------------------------------------
        //------------------------------4NA-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.05.07.04-4NA-02"));        

        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.05.07.04-4NA-03"));
    }

}
