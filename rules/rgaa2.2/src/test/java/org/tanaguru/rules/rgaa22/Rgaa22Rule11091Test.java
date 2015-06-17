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
package org.tanaguru.rules.rgaa22;

import java.util.Iterator;
import java.util.LinkedHashSet;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import org.tanaguru.entity.audit.EvidenceElement;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 11.9 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule11091Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule11091Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa22.Rgaa22Rule11091");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Rgaa22.Test.11.9-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11091/RGAA22.Test.11.9-2Failed-01.html"));
        addParameterToParameterMap("Rgaa22.Test.11.9-2Failed-01", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));
        getWebResourceMap().put("Rgaa22.Test.11.9-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11091/RGAA22.Test.11.9-2Failed-02.html"));
        addParameterToParameterMap("Rgaa22.Test.11.9-2Failed-02", createParameter("Rules", "DATA_TABLE_MARKER", "id-data-table"));
        getWebResourceMap().put("Rgaa22.Test.11.9-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11091/RGAA22.Test.11.9-2Failed-03.html"));
        addParameterToParameterMap("Rgaa22.Test.11.9-2Failed-03", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));
        getWebResourceMap().put("Rgaa22.Test.11.9-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11091/RGAA22.Test.11.9-3NMI-01.html"));
        getWebResourceMap().put("Rgaa22.Test.11.9-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11091/RGAA22.Test.11.9-3NMI-02.html"));
        addParameterToParameterMap("Rgaa22.Test.11.9-3NMI-02", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));
        getWebResourceMap().put("Rgaa22.Test.11.9-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11091/RGAA22.Test.11.9-3NMI-03.html"));
        getWebResourceMap().put("Rgaa22.Test.11.9-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11091/RGAA22.Test.11.9-3NMI-04.html"));
        getWebResourceMap().put("Rgaa22.Test.11.9-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11091/RGAA22.Test.11.9-3NMI-05.html"));
        addParameterToParameterMap("Rgaa22.Test.11.9-3NMI-05", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));
        getWebResourceMap().put("Rgaa22.Test.11.9-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule11091/RGAA22.Test.11.9-4NA-01.html"));
        getWebResourceMap().put("Rgaa22.Test.11.9-4NA-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule11091/RGAA22.Test.11.9-4NA-02.html"));
        getWebResourceMap().put("Rgaa22.Test.11.9-4NA-03",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule11091/RGAA22.Test.11.9-4NA-03.html"));
        addParameterToParameterMap("Rgaa22.Test.11.9-4NA-03", createParameter("Rules", "PRESENTATION_TABLE_MARKER", "class-presentation-table"));
        getWebResourceMap().put("Rgaa22.Test.11.9-4NA-04",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule11091/RGAA22.Test.11.9-4NA-04.html"));
        addParameterToParameterMap("Rgaa22.Test.11.9-4NA-04", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa22.Test.11.9-2Failed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.NOT_PERTINENT_CAPTION_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.CAPTION_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        EvidenceElement ee = processRemark.getElementList().iterator().next();
        assertEquals("",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.9-2Failed-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.NOT_PERTINENT_CAPTION_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.CAPTION_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("!:;,--><--",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.9-2Failed-03");
        // check number of elements in the page
        assertEquals(4, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(4, processResult.getRemarkSet().size());
        Iterator<ProcessRemark> iter = processResult.getRemarkSet().iterator();
        
        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.NOT_PERTINENT_CAPTION_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.CAPTION_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("!:;,--><--",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_CAPTION_PERTINENCE_FOR_DATA_TABLE_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.CAPTION_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("Caption of data table",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_FOR_NOT_PERTINENT_CAPTION_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.CAPTION_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_CAPTION_PERTINENCE_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.CAPTION_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("Caption of unidentified table",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-01--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.9-3NMI-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_CAPTION_PERTINENCE_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.CAPTION_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("Title of the test table",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-02--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.9-3NMI-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_CAPTION_PERTINENCE_FOR_DATA_TABLE_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.CAPTION_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("Caption of data table",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-03--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.9-3NMI-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_FOR_NOT_PERTINENT_CAPTION_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.CAPTION_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.9-3NMI-04");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_CAPTION_PERTINENCE_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.CAPTION_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("Caption of unidentified table",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.9-3NMI-05");
        // check number of elements in the page
        assertEquals(3, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(3, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        
        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_CAPTION_PERTINENCE_FOR_DATA_TABLE_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.CAPTION_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("Caption of data table",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_FOR_NOT_PERTINENT_CAPTION_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.CAPTION_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_CAPTION_PERTINENCE_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.CAPTION_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("Caption of unidentified table",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.9-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.9-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.9-4NA-03");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.11.9-4NA-04");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa22.Test.11.9-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa22.Test.11.9-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa22.Test.11.9-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa22.Test.11.9-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa22.Test.11.9-4NA-04").getValue());
    }
}