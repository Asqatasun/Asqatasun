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
package org.opens.tanaguru.rules.rgaa30;

import java.util.Iterator;
import java.util.LinkedHashSet;
import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 * Unit test class for the implementation of the rule 09.01.04 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule090104Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule090104Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.opens.tanaguru.rules.rgaa30.Rgaa30Rule090104");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Rgaa30.Test.09.01.04-2Failed-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa30/Rgaa30Rule090104/Rgaa30.Test.09.01.04-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.09.01.04-2Failed-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa30/Rgaa30Rule090104/Rgaa30.Test.09.01.04-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.09.01.04-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule090104/Rgaa30.Test.09.01.04-3NMI-01.html"));
        getWebResourceMap().put("Rgaa30.Test.09.01.04-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule090104/Rgaa30.Test.09.01.04-3NMI-02.html"));
        getWebResourceMap().put("Rgaa30.Test.09.01.04-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa30/Rgaa30Rule090104/Rgaa30.Test.09.01.04-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.09.01.04-2Failed-01");
        // check number of elements in the page
        assertEquals(19, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(19, processResult.getRemarkSet().size());
        Iterator<SourceCodeRemark> iter =  ((LinkedHashSet)processResult.getRemarkSet()).iterator();
        //----------------------------------------------------------------------
        SourceCodeRemark processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.H1_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        Iterator<EvidenceElement> eIter =  processRemark.getElementList().iterator();
        EvidenceElement ee = eIter.next();
        assertEquals("Rgaa30 Test.9.1.4 Failed 01", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H1_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H1_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("!:;,&~$*/-*/-*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H1_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H2_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H2_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("!:;,&~$*/-*/-*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H2_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H3_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H3_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("!:;,&~$*/-*/-*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H3_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H4_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H4_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("!:;,&~$*/-*/-*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H4_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H5_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H5_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("!:;,&~$*/-*/-*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H5_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H6_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H6_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("!:;,&~$*/-*/-*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H6_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.09.01.04-2Failed-02");
        // check number of elements in the page
        assertEquals(22, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(22, processResult.getRemarkSet().size());
        iter =  ((LinkedHashSet)processResult.getRemarkSet()).iterator();
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.H1_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Rgaa30 Test.9.1.4 Failed 02", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.SPAN_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("!:;,&~$*/-*/-*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.SPAN_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("!:;,&~$*/-*/-*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.SPAN_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("!:;,&~$*/-*/-*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.SPAN_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("!:;,&~$*/-*/-*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.SPAN_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("!:;,&~$*/-*/-*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.SPAN_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("!:;,&~$*/-*/-*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.SPAN_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("!:;,&~$*/-*/-*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertTrue(StringUtils.isEmpty(ee.getValue()));
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.09.01.04-3NMI-01");
        // check number of elements in the page
        assertEquals(13, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(13, processResult.getRemarkSet().size());
        iter =  ((LinkedHashSet)processResult.getRemarkSet()).iterator();
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.H1_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Rgaa30 Test.9.1.4 NMI 01", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.H1_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 1-1", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.H1_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 1-2", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.H2_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 2-1", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.H2_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 2-2", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.H3_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 3-1", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.H3_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 3-2", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.H4_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 4-1", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.H4_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 4-2", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.H5_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 5-1", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.H5_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 5-2", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.H6_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 6-1", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.H6_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 6-2", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.09.01.04-3NMI-02");
        // check number of elements in the page
        assertEquals(15, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(15, processResult.getRemarkSet().size());
        iter =  ((LinkedHashSet)processResult.getRemarkSet()).iterator();
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.H1_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Rgaa30 Test.9.1.4 NMI 02", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.SPAN_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 1-1", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 1-2", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.SPAN_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 2-1", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 2-2", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.SPAN_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 3-1", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 3-2", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.SPAN_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 4-1", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 4-2", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.SPAN_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 5-1", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 5-2", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.SPAN_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 6-1", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 6-2", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.SPAN_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 7-1", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        //----------------------------------------------------------------------
        processRemark = iter.next();
        assertEquals(RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2,processRemark.getElementList().size());
        eIter =  processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Title level 7-2", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("headings", ee.getValue());
        assertEquals(ProcessRemarkService.DEFAULT_EVIDENCE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.09.01.04-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.09.01.04-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.09.01.04-2Failed-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.09.01.04-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.09.01.04-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.09.01.04-4NA-01").getValue());
    }

}
