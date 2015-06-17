/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.rules.rgaa30;

import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 5-6-1 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule050601Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule050601Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule050601");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.05.06.01-3NMI-01");
        addWebResource("Rgaa30.Test.05.06.01-3NMI-02",
                    createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));
        addWebResource("Rgaa30.Test.05.06.01-3NMI-03",
                    createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));
        addWebResource("Rgaa30.Test.05.06.01-4NA-01");
        addWebResource("Rgaa30.Test.05.06.01-4NA-02",
                    createParameter("Rules", "PRESENTATION_TABLE_MARKER", "id-presentation-table"));

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01--------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.05.06.01-3NMI-01");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_HEADERS_USAGE_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.06.01-3NMI-02");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_USAGE_OF_HEADERS_FOR_DATA_TABLE_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.06.01-3NMI-03");
        checkResultIsPreQualified(processResult, 2,  2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_USAGE_OF_HEADERS_FOR_DATA_TABLE_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_HEADERS_USAGE_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                2);        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.05.06.01-4NA-01"));        

        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.05.06.01-4NA-02"));
    }

}
