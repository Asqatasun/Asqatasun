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
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import static org.tanaguru.rules.keystore.MarkerStore.*;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 5-1-1 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule050101Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule050101Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule050101");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.05.01.01-1Passed-01",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "class-complex-table"));
        addWebResource("Rgaa30.Test.05.01.01-1Passed-02",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "id-complex-table"));
        addWebResource("Rgaa30.Test.05.01.01-1Passed-03",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "class-complex-table;id-complex-table"));
        addWebResource("Rgaa30.Test.05.01.01-1Passed-04",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "complex-table"));
        addWebResource("Rgaa30.Test.05.01.01-2Failed-01",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "class-complex-table"));
        addWebResource("Rgaa30.Test.05.01.01-2Failed-02",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "id-complex-table"));
        addWebResource("Rgaa30.Test.05.01.01-2Failed-03",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "class-complex-table;id-complex-table"));
        addWebResource("Rgaa30.Test.05.01.01-2Failed-04",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "class-complex-table"));
        addWebResource("Rgaa30.Test.05.01.01-2Failed-05",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "class-complex-table"));
        addWebResource("Rgaa30.Test.05.01.01-3NMI-01");
        addWebResource("Rgaa30.Test.05.01.01-3NMI-02");
        addWebResource("Rgaa30.Test.05.01.01-3NMI-03",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "class-complex-table"));
        addWebResource("Rgaa30.Test.05.01.01-3NMI-04",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "id-complex-table"),
                    createParameter("Rules", PRESENTATION_TABLE_MARKER, "class-presentation-table"));
        addWebResource("Rgaa30.Test.05.01.01-4NA-01");
        addWebResource("Rgaa30.Test.05.01.01-4NA-02",
                    createParameter("Rules", PRESENTATION_TABLE_MARKER, "id-presentation-table"),
                    createParameter("Rules", DATA_TABLE_MARKER, "id-data-table"));

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.05.01.01-1Passed-01"), 1);
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.05.01.01-1Passed-02"), 1);
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.05.01.01-1Passed-03"), 2);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.05.01.01-1Passed-04"), 1);

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.05.01.01-2Failed-01");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.CAPTION_MISSING_ON_COMPLEX_TABLE_MSG, 
                HtmlElementStore.TABLE_ELEMENT, 
                1);
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-2Failed-02");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.CAPTION_MISSING_ON_COMPLEX_TABLE_MSG, 
                HtmlElementStore.TABLE_ELEMENT, 
                1);
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-2Failed-03");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.CAPTION_MISSING_ON_COMPLEX_TABLE_MSG, 
                HtmlElementStore.TABLE_ELEMENT, 
                1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.CAPTION_MISSING_ON_COMPLEX_TABLE_MSG, 
                HtmlElementStore.TABLE_ELEMENT, 
                2);
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-2Failed-04");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.CAPTION_MISSING_ON_COMPLEX_TABLE_MSG, 
                HtmlElementStore.TABLE_ELEMENT, 
                1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_TABLE_WITH_CAPTION_IS_COMPLEX_MSG, 
                HtmlElementStore.TABLE_ELEMENT, 
                2);
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-2Failed-05");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.CAPTION_MISSING_ON_COMPLEX_TABLE_MSG, 
                HtmlElementStore.TABLE_ELEMENT, 
                1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_TABLE_WITHOUT_CAPTION_IS_NOT_COMPLEX_MSG, 
                HtmlElementStore.TABLE_ELEMENT, 
                2);
        
                
        //----------------------------------------------------------------------
        //------------------------------3NMI-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-3NMI-01");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_TABLE_WITHOUT_CAPTION_IS_NOT_COMPLEX_MSG, 
                HtmlElementStore.TABLE_ELEMENT, 
                1);

                
        //----------------------------------------------------------------------
        //------------------------------3NMI-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-3NMI-02");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_TABLE_WITH_CAPTION_IS_COMPLEX_MSG, 
                HtmlElementStore.TABLE_ELEMENT, 
                1);
        
                
        //----------------------------------------------------------------------
        //------------------------------3NMI-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-3NMI-03");
        checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_TABLE_WITH_CAPTION_IS_COMPLEX_MSG, 
                HtmlElementStore.TABLE_ELEMENT, 
                1);

                
        //----------------------------------------------------------------------
        //------------------------------3NMI-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-3NMI-04");
        checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_TABLE_WITHOUT_CAPTION_IS_NOT_COMPLEX_MSG, 
                HtmlElementStore.TABLE_ELEMENT, 
                1);
        

        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.05.01.01-4NA-01"));
        

        //----------------------------------------------------------------------
        //------------------------------4NA-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.05.01.01-4NA-02"));
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.05.01.01-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.05.01.01-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.05.01.01-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.05.01.01-1Passed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.05.01.01-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.05.01.01-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.05.01.01-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.05.01.01-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.05.01.01-2Failed-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.05.01.01-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.05.01.01-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.05.01.01-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.05.01.01-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.05.01.01-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.05.01.01-4NA-02").getValue());
    }

}
