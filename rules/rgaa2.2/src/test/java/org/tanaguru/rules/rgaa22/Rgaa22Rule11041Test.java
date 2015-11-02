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
package org.tanaguru.rules.rgaa22;

import java.util.LinkedHashSet;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 11.4 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule11041Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule11041Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa22.Rgaa22Rule11041");
    }

    @Override
    protected void setUpWebResourceMap() {
getWebResourceMap().put("RGAA22.Test.11.4-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-1Passed-01.html"));
        addParameterToParameterMap("RGAA22.Test.11.4-1Passed-01", createParameter("Rules", "PRESENTATION_TABLE_MARKER", "id-presentation-table"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-2Failed-01.html"));
        addParameterToParameterMap("RGAA22.Test.11.4-2Failed-01", createParameter("Rules", "PRESENTATION_TABLE_MARKER", "id-presentation-table"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-2Failed-02.html"));
        addParameterToParameterMap("RGAA22.Test.11.4-2Failed-02", createParameter("Rules", "PRESENTATION_TABLE_MARKER", "id-presentation-table"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-2Failed-03.html"));
        addParameterToParameterMap("RGAA22.Test.11.4-2Failed-03", createParameter("Rules", "PRESENTATION_TABLE_MARKER", "id-presentation-table"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-2Failed-04.html"));
        addParameterToParameterMap("RGAA22.Test.11.4-2Failed-04", createParameter("Rules", "PRESENTATION_TABLE_MARKER", "id-presentation-table"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-2Failed-05.html"));
        addParameterToParameterMap("RGAA22.Test.11.4-2Failed-05", createParameter("Rules", "PRESENTATION_TABLE_MARKER", "id-presentation-table"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-2Failed-06.html"));
        addParameterToParameterMap("RGAA22.Test.11.4-2Failed-06", createParameter("Rules", "PRESENTATION_TABLE_MARKER", "id-presentation-table"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-2Failed-07.html"));
        addParameterToParameterMap("RGAA22.Test.11.4-2Failed-07", createParameter("Rules", "PRESENTATION_TABLE_MARKER", "id-presentation-table"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-2Failed-08.html"));
        addParameterToParameterMap("RGAA22.Test.11.4-2Failed-08", createParameter("Rules", "PRESENTATION_TABLE_MARKER", "class-presentation-table"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-3NMI-01.html"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-3NMI-02.html"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-3NMI-03.html"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-3NMI-04.html"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-3NMI-05.html"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-3NMI-06.html"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-3NMI-07.html"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-3NMI-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-3NMI-08.html"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-3NMI-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-3NMI-09.html"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-4NA-01.html"));
        
        getWebResourceMap().put("RGAA22.Test.11.4-4NA-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule11041/RGAA22.Test.11.4-4NA-02.html"));
        addParameterToParameterMap("RGAA22.Test.11.4-4NA-02", createParameter("Rules", "DATA_TABLE_MARKER", "id-data-table"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("RGAA22.Test.11.4-1Passed-01");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-2Failed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.PRESENTATION_TABLE_WITH_FORBIDDEN_MARKUP_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-2Failed-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.PRESENTATION_TABLE_WITH_FORBIDDEN_MARKUP_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-2Failed-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.PRESENTATION_TABLE_WITH_FORBIDDEN_MARKUP_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-2Failed-04");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.PRESENTATION_TABLE_WITH_FORBIDDEN_MARKUP_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-2Failed-05");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.PRESENTATION_TABLE_WITH_FORBIDDEN_MARKUP_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-2Failed-06");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.PRESENTATION_TABLE_WITH_FORBIDDEN_MARKUP_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-2Failed-07");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.PRESENTATION_TABLE_WITH_FORBIDDEN_MARKUP_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-2Failed-08");
        // check number of elements in the page
        assertEquals(16, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(15, processResult.getRemarkSet().size());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-3NMI-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_TABLE_IS_PRESENTATION_TABLE_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-3NMI-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_TABLE_IS_DATA_TABLE_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-3NMI-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_TABLE_IS_DATA_TABLE_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-3NMI-04");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_TABLE_IS_DATA_TABLE_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-3NMI-05");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_TABLE_IS_DATA_TABLE_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-3NMI-06");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_TABLE_IS_DATA_TABLE_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-07---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-3NMI-07");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_TABLE_IS_DATA_TABLE_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-08---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-3NMI-08");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_TABLE_IS_DATA_TABLE_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-09---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-3NMI-09");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.4-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.11.4-1Passed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.11.4-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.11.4-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.11.4-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.11.4-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.11.4-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.11.4-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.11.4-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.11.4-2Failed-08").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.11.4-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.11.4-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.11.4-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.11.4-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.11.4-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.11.4-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.11.4-3NMI-07").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.11.4-3NMI-08").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.11.4-3NMI-09").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.11.4-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.11.4-4NA-02").getValue());
    }

}