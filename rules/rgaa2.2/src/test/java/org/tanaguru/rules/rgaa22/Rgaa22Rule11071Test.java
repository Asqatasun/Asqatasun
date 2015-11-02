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

import java.util.Iterator;
import java.util.LinkedHashSet;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 11.7 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule11071Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule11071Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa22.Rgaa22Rule11071");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Rgaa22.Test.11.7-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11071/RGAA22.Test.11.7-1Passed-01.html"));
        addParameterToParameterMap("Rgaa22.Test.11.7-1Passed-01", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));

        getWebResourceMap().put("Rgaa22.Test.11.7-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11071/RGAA22.Test.11.7-1Passed-02.html"));
        addParameterToParameterMap("Rgaa22.Test.11.7-1Passed-02", createParameter("Rules", "DATA_TABLE_MARKER", "id-data-table"));

        getWebResourceMap().put("Rgaa22.Test.11.7-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11071/RGAA22.Test.11.7-1Passed-03.html"));
        addParameterToParameterMap("Rgaa22.Test.11.7-1Passed-03", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table;id-data-table"));
        
        getWebResourceMap().put("Rgaa22.Test.11.7-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11071/RGAA22.Test.11.7-1Passed-04.html"));
        addParameterToParameterMap("Rgaa22.Test.11.7-1Passed-04", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));

        getWebResourceMap().put("Rgaa22.Test.11.7-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11071/RGAA22.Test.11.7-2Failed-01.html"));
        addParameterToParameterMap("Rgaa22.Test.11.7-2Failed-01", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));

        getWebResourceMap().put("Rgaa22.Test.11.7-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11071/RGAA22.Test.11.7-2Failed-02.html"));
        addParameterToParameterMap("Rgaa22.Test.11.7-2Failed-02", createParameter("Rules", "DATA_TABLE_MARKER", "id-data-table"));
        
        getWebResourceMap().put("Rgaa22.Test.11.7-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11071/RGAA22.Test.11.7-2Failed-03.html"));
        addParameterToParameterMap("Rgaa22.Test.11.7-2Failed-03", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table;id-data-table"));

        getWebResourceMap().put("Rgaa22.Test.11.7-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11071/RGAA22.Test.11.7-2Failed-04.html"));
        addParameterToParameterMap("Rgaa22.Test.11.7-2Failed-04", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));

        getWebResourceMap().put("Rgaa22.Test.11.7-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11071/RGAA22.Test.11.7-2Failed-05.html"));
        addParameterToParameterMap("Rgaa22.Test.11.7-2Failed-05", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));

        getWebResourceMap().put("Rgaa22.Test.11.7-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11071/RGAA22.Test.11.7-2Failed-06.html"));
        addParameterToParameterMap("Rgaa22.Test.11.7-2Failed-06", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));

        getWebResourceMap().put("Rgaa22.Test.11.7-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11071/RGAA22.Test.11.7-3NMI-01.html"));

        getWebResourceMap().put("Rgaa22.Test.11.7-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11071/RGAA22.Test.11.7-3NMI-02.html"));

        getWebResourceMap().put("Rgaa22.Test.11.7-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11071/RGAA22.Test.11.7-3NMI-03.html"));
        addParameterToParameterMap("Rgaa22.Test.11.7-3NMI-03", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));
        
        getWebResourceMap().put("Rgaa22.Test.11.7-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11071/RGAA22.Test.11.7-3NMI-04.html"));
        addParameterToParameterMap("Rgaa22.Test.11.7-3NMI-04", createParameter("Rules", "DATA_TABLE_MARKER", "id-data-table"));

        getWebResourceMap().put("Rgaa22.Test.11.7-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11071/RGAA22.Test.11.7-4NA-01.html"));
        
        getWebResourceMap().put("Rgaa22.Test.11.7-4NA-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11071/RGAA22.Test.11.7-4NA-02.html"));
        addParameterToParameterMap("Rgaa22.Test.11.7-4NA-02", createParameter("Rules", "PRESENTATION_TABLE_MARKER", "id-presentation-table"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa22.Test.11.7-1Passed-01");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());

        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.7-1Passed-02");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());

        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.7-1Passed-03");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());

        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.7-1Passed-04");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());

               
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.7-2Failed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CAPTION_MISSING_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

               
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.7-2Failed-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CAPTION_MISSING_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

               
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.7-2Failed-03");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        Iterator<ProcessRemark> iter = processResult.getRemarkSet().iterator();
        
        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CAPTION_MISSING_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CAPTION_MISSING_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

               
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.7-2Failed-04");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        
        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CAPTION_MISSING_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_WITH_CAPTION_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

               
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.7-2Failed-05");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        
        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CAPTION_MISSING_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_WITHOUT_CAPTION_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());


        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.7-2Failed-06");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CAPTION_MISSING_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.7-3NMI-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_WITHOUT_CAPTION_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.7-3NMI-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_WITH_CAPTION_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.7-3NMI-03");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_WITH_CAPTION_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.7-3NMI-04");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_WITHOUT_CAPTION_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.7-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.7-4NA-02");
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
                consolidate("Rgaa22.Test.11.7-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa22.Test.11.7-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa22.Test.11.7-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa22.Test.11.7-1Passed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa22.Test.11.7-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa22.Test.11.7-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa22.Test.11.7-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa22.Test.11.7-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa22.Test.11.7-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa22.Test.11.7-2Failed-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa22.Test.11.7-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa22.Test.11.7-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa22.Test.11.7-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa22.Test.11.7-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa22.Test.11.7-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa22.Test.11.7-4NA-02").getValue());
    }


}