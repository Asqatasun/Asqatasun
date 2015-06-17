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
                    createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));
        addWebResource("Rgaa30.Test.05.01.01-1Passed-02",
                    createParameter("Rules", "DATA_TABLE_MARKER", "id-data-table"));
        addWebResource("Rgaa30.Test.05.01.01-1Passed-03",
                    createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table;id-data-table"));
        addWebResource("Rgaa30.Test.05.01.01-1Passed-04",
                    createParameter("Rules", "DATA_TABLE_MARKER", "data-table"));
        addWebResource("Rgaa30.Test.05.01.01-2Failed-01",
                    createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));
        addWebResource("Rgaa30.Test.05.01.01-2Failed-02",
                    createParameter("Rules", "DATA_TABLE_MARKER", "id-data-table"));
        addWebResource("Rgaa30.Test.05.01.01-2Failed-03",
                    createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table;id-data-table"));
        addWebResource("Rgaa30.Test.05.01.01-2Failed-04",
                    createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));
        addWebResource("Rgaa30.Test.05.01.01-2Failed-05",
                    createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));
        addWebResource("Rgaa30.Test.05.01.01-3NMI-01");
        addWebResource("Rgaa30.Test.05.01.01-3NMI-02");
        addWebResource("Rgaa30.Test.05.01.01-3NMI-03",
                    createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));
        addWebResource("Rgaa30.Test.05.01.01-3NMI-04",
                    createParameter("Rules", "DATA_TABLE_MARKER", "id-data-table"),
                    createParameter("Rules", "PRESENTATION_TABLE_MARKER", "class-presentation-table"));
        addWebResource("Rgaa30.Test.05.01.01-4NA-01");
        addWebResource("Rgaa30.Test.05.01.01-4NA-02",
                    createParameter("Rules", "PRESENTATION_TABLE_MARKER", "id-presentation-table"));

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.05.01.01-1Passed-01");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
//        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-1Passed-02");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
//        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-1Passed-03");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
//        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(2, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-1Passed-04");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
//        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());


        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-2Failed-01");
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check number of remarks and their value
//        assertEquals(1, processResult.getRemarkSet().size());
//        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
//        assertEquals(TestSolution.FAILED, processRemark.getIssue());
//        assertEquals(RemarkMessageStore.SUMMARY_MISSING_MSG, processRemark.getMessageCode());
//        assertEquals(HtmlElementStore.TABLE_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-2Failed-02");
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check number of remarks and their value
//        assertEquals(1, processResult.getRemarkSet().size());
//        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
//        assertEquals(TestSolution.FAILED, processRemark.getIssue());
//        assertEquals(RemarkMessageStore.SUMMARY_MISSING_MSG, processRemark.getMessageCode());
//        assertEquals(HtmlElementStore.TABLE_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-2Failed-03");
        // check number of elements in the page
//        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check number of remarks and their value
//        assertEquals(2, processResult.getRemarkSet().size());
//        Iterator<ProcessRemark> pIter = processResult.getRemarkSet().iterator();
//        
//        processRemark = (SourceCodeRemark)pIter.next();
//        assertEquals(TestSolution.FAILED, processRemark.getIssue());
//        assertEquals(RemarkMessageStore.SUMMARY_MISSING_MSG, processRemark.getMessageCode());
//        assertEquals(HtmlElementStore.TABLE_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
        
//        processRemark = (SourceCodeRemark)pIter.next();
//        assertEquals(TestSolution.FAILED, processRemark.getIssue());
//        assertEquals(RemarkMessageStore.SUMMARY_MISSING_MSG, processRemark.getMessageCode());
//        assertEquals(HtmlElementStore.TABLE_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-2Failed-04");
        // check number of elements in the page
//        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check number of remarks and their value
//        assertEquals(2, processResult.getRemarkSet().size());
//        pIter = processResult.getRemarkSet().iterator();
        
//        processRemark = (SourceCodeRemark)pIter.next();
//        assertEquals(RemarkMessageStore.SUMMARY_MISSING_MSG, processRemark.getMessageCode());
        // check processRemarkResult
//        assertEquals(TestSolution.FAILED, processRemark.getIssue());
//        assertEquals(HtmlElementStore.TABLE_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
        
//        processRemark = (SourceCodeRemark)pIter.next();
//        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
//        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_WITH_SUMMARY_MSG, processRemark.getMessageCode());
//        assertEquals(HtmlElementStore.TABLE_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-2Failed-05");
        // check number of elements in the page
//        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check number of remarks and their value
//        assertEquals(2, processResult.getRemarkSet().size());
//        pIter = processResult.getRemarkSet().iterator();

//        processRemark = (SourceCodeRemark)pIter.next();
        // check processRemarkResult
//        assertEquals(TestSolution.FAILED, processRemark.getIssue());
//        assertEquals(RemarkMessageStore.SUMMARY_MISSING_MSG, processRemark.getMessageCode());
//        assertEquals(HtmlElementStore.TABLE_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
        
//        processRemark = (SourceCodeRemark)pIter.next();
//         assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
//        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_WITHOUT_SUMMARY_MSG, processRemark.getMessageCode());
//        assertEquals(HtmlElementStore.TABLE_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
        
                
        //----------------------------------------------------------------------
        //------------------------------3NMI-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-3NMI-01");
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check number of remarks and their value
//        assertEquals(1, processResult.getRemarkSet().size());
//        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
//        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
//        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_WITHOUT_SUMMARY_MSG, processRemark.getMessageCode());
//        assertEquals(HtmlElementStore.TABLE_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());

                
        //----------------------------------------------------------------------
        //------------------------------3NMI-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-3NMI-02");
        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check number of remarks and their value
//        assertEquals(1, processResult.getRemarkSet().size());
//        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
//        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
//        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_WITH_SUMMARY_MSG, processRemark.getMessageCode());
//        assertEquals(HtmlElementStore.TABLE_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
        
                
        //----------------------------------------------------------------------
        //------------------------------3NMI-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-3NMI-03");
        // check number of elements in the page
//        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check number of remarks and their value
//        assertEquals(1, processResult.getRemarkSet().size());
//        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
//        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
//        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_WITH_SUMMARY_MSG, processRemark.getMessageCode());
//        assertEquals(HtmlElementStore.TABLE_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());

                
        //----------------------------------------------------------------------
        //------------------------------3NMI-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-3NMI-04");
        // check number of elements in the page
//        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check number of remarks and their value
//        assertEquals(1, processResult.getRemarkSet().size());
//        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
//        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
//        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_WITHOUT_SUMMARY_MSG, processRemark.getMessageCode());
//        assertEquals(HtmlElementStore.TABLE_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
//        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(0, processResult.getElementCounter());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.05.01.01-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        // check test has no remark
//        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
//        assertEquals(0, processResult.getElementCounter());

    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.05.01.01-1Passed-01").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.05.01.01-1Passed-02").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.05.01.01-1Passed-03").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.05.01.01-1Passed-04").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.05.01.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.05.01.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.05.01.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.05.01.01-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.05.01.01-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.05.01.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.05.01.01-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.05.01.01-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.05.01.01-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.05.01.01-4NA-01").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa30.Test.05.01.01-4NA-02").getValue());
    }

}
