/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.rules.seo;

import java.util.Iterator;
import java.util.LinkedHashSet;
import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;
import org.opens.tanaguru.rules.seo.test.SeoRuleImplementationTestCase;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 *
 * @author jkowalczyk
 */
public class SeoRule07013Test extends SeoRuleImplementationTestCase {
    
    public SeoRule07013Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.opens.tanaguru.rules.seo.SeoRule07013");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Seo.Test.7.1.3-2Failed-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "SEO/SeoRule07013/Seo.Test.7.1.3-2Failed-01.html"));
        getWebResourceMap().put("Seo.Test.7.1.3-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07013/Seo.Test.7.1.3-3NMI-01.html"));
        getWebResourceMap().put("Seo.Test.7.1.3-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "SEO/SeoRule07013/Seo.Test.7.1.3-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Seo.Test.7.1.3-2Failed-01");
        // check number of elements in the page
        assertEquals(25, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(25, processResult.getRemarkSet().size());
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
        assertEquals("Seo Test.7.1.3 Failed 01", ee.getValue());
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
        assertEquals("1234685423", ee.getValue());
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
        assertEquals("121546842331", ee.getValue());
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
        assertEquals("2111224654348751", ee.getValue());
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
        assertEquals("12125454352321", ee.getValue());
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
        assertEquals("1223415246855", ee.getValue());
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
        assertEquals("12354643321231545", ee.getValue());
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
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Seo.Test.7.1.3-3NMI-01");
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
        assertEquals("Seo Test.7.1.3 NMI 01", ee.getValue());
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
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Seo.Test.7.1.3-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.7.1.3-2Failed-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Seo.Test.7.1.3-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.7.1.3-4NA-01").getValue());
    }

}
