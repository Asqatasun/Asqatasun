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
import org.tanaguru.entity.audit.*;
import org.tanaguru.rules.keystore.EvidenceStore;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 9.8 of the referential
 * RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule09081Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule09081Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa22.Rgaa22Rule09081");
    }

    @Override
    protected void setUpWebResourceMap() {
        /*
         * ------------------------------------1Passed------------------------------------------
         */
        getWebResourceMap().put("RGAA22.Test.9.8-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-1Passed-01.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-1Passed-02.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-1Passed-03.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-1Passed-04.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-1Passed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-1Passed-05.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-1Passed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-1Passed-06.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-1Passed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-1Passed-07.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-1Passed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-1Passed-08.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-1Passed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-1Passed-09.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-1Passed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-1Passed-10.html"));

        /*
         * ------------------------------------2Failed------------------------------------------
         */
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-01.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-02.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-03.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-04.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-05.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-06.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-07.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-08.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-09.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-10.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-11.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-12.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-13",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-13.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-14",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-14.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-15",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-15.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-16",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-16.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-17",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-17.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-18",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-18.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-19",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-19.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-20",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-20.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-2Failed-21",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-2Failed-21.html"));

        /*
         * ------------------------------------3NMI------------------------------------------
         */
        getWebResourceMap().put("RGAA22.Test.9.8-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-3NMI-01.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-3NMI-02.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-3NMI-03.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-3NMI-04.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-3NMI-05.html"));
        getWebResourceMap().put("RGAA22.Test.9.8-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule09081/RGAA22.Test.9.8-3NMI-06.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("RGAA22.Test.9.8-1Passed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-1Passed-02");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-1Passed-03");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-1Passed-04");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //---------------------------1Passed-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-1Passed-05");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //---------------------------1Passed-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-1Passed-06");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //---------------------------1Passed-07---------------------------------
        //----------------------------------------------------------------------
        processResult =  processPageTest("RGAA22.Test.9.8-1Passed-07");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        

        //----------------------------------------------------------------------
        //---------------------------1Passed-08---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-1Passed-08");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //---------------------------1Passed-09---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-1Passed-09");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //---------------------------1Passed-10---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-1Passed-10");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-01");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        Iterator <ProcessRemark> iter = processResult.getRemarkSet().iterator();
        ProcessRemark processRemark = iter.next();
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_WHOLE_PAGE_MSG, processRemark.getMessageCode());
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-02");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        processRemark = iter.next();
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG, processRemark.getMessageCode());
        assertNull(processRemark.getElementList());
        
        processRemark = iter.next();
        assertTrue(processRemark instanceof SourceCodeRemark);
        SourceCodeRemark sourceCodeRemark = (SourceCodeRemark) processRemark;
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_RELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        Iterator<EvidenceElement> pIter = processRemark.getElementList().iterator();
        EvidenceElement ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Présence d’une langue de traitement"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-03");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(3, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        processRemark = iter.next();
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG, processRemark.getMessageCode());
        assertNull(processRemark.getElementList());
        
        processRemark = iter.next();
        assertTrue(processRemark instanceof SourceCodeRemark);
        sourceCodeRemark = (SourceCodeRemark) processRemark;
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_IRRELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.HEAD_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = processRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("en",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("et",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Rgaa22 Test.9.8 Failed 03"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());
        
        processRemark = iter.next();
        assertTrue(processRemark instanceof SourceCodeRemark);
        sourceCodeRemark = (SourceCodeRemark) processRemark;
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_RELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = processRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Présence d’une langue de traitement"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-04");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG, processRemark.getMessageCode());
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-05");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG, processRemark.getMessageCode());
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-06");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG, processRemark.getMessageCode());
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-07");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG, processRemark.getMessageCode());
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-08---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-08");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.WRONG_LANGUAGE_DECLARATION_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.HTML_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("aq",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //---------------------------2Failed-09---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-09");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.WRONG_LANGUAGE_DECLARATION_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.HTML_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("aq",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-10---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-10");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.MALFORMED_LANGUAGE_DECLARATION_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.HTML_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("en-U",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-11---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-11");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.MALFORMED_LANGUAGE_DECLARATION_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.HTML_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("english",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-12---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-12");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.IRRELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.HTML_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("ro",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("en",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Rgaa22 Test.9.8 Failed 12 Rgaa22 Test.9.8 Failed 12 Failed : the default language is defined on the html tag but its"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //---------------------------2Failed-13---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-13");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.MALFORMED_LANGUAGE_DECLARATION_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.HTML_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("en;US",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-14------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-14");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.MALFORMED_LANGUAGE_DECLARATION_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("fren-FR",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-15------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-15");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.WRONG_LANGUAGE_DECLARATION_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("aq",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-16------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-16");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.MALFORMED_LANGUAGE_DECLARATION_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("french",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-17------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-17");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.MALFORMED_LANGUAGE_DECLARATION_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("fr-F",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-18------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-18");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.MALFORMED_LANGUAGE_DECLARATION_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("fr/FR",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-19---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-19");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.IRRELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("en",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Some text is written here in english"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-20------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-20");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG, processRemark.getMessageCode());
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-21------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-2Failed-21");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG, processRemark.getMessageCode());
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-01------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-3NMI-01");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        
        sourceCodeRemark = ((SourceCodeRemark)iter.next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_IRRELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.HTML_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("en",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("et",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Rgaa22 Test.9.8 NMI 01 Rgaa22 Test.9.8 NMI 01"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());
        
        processRemark = iter.next();
        assertTrue(processRemark instanceof SourceCodeRemark);
        sourceCodeRemark = (SourceCodeRemark) processRemark;
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_RELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Présence d’une langue de traitement"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //---------------------------3NMI-02------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-3NMI-02");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(4, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        
        sourceCodeRemark = ((SourceCodeRemark)iter.next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_RELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.HTML_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("en",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("en",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Some short text in english"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());
        
        sourceCodeRemark = ((SourceCodeRemark)iter.next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_IRRELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.TITLE_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("en",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("et",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Rgaa22 Test.9.8 NMI 02"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());
        
        sourceCodeRemark = ((SourceCodeRemark)iter.next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_IRRELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.H1_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("en",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("et",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Rgaa22 Test.9.8 NMI 02"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());
        
        sourceCodeRemark = ((SourceCodeRemark)iter.next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_RELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Présence d’une langue de traitement"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-3NMI-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        
        sourceCodeRemark = ((SourceCodeRemark)iter.next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_RELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Présence d’une langue de traitement"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());
        
        sourceCodeRemark = ((SourceCodeRemark)iter.next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_RELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Texte en francais"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-3NMI-04");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        
        sourceCodeRemark = ((SourceCodeRemark)iter.next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_RELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Présence d’une langue de traitement"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());
        
        sourceCodeRemark = ((SourceCodeRemark)iter.next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_RELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Texte en francais"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-3NMI-05");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(3, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        
        sourceCodeRemark = ((SourceCodeRemark)iter.next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_RELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.HTML_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("en",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("en",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Rgaa22 Test.9.8 NMI 05 Rgaa22 Test.9.8 NMI 05 NMI : A language change occurs twice, seems to be irrelevant but the text is not long enough to decide surely"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());
        
        sourceCodeRemark = ((SourceCodeRemark)iter.next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_RELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Présence d’une langue de traitement"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());
        
        sourceCodeRemark = ((SourceCodeRemark)iter.next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_IRRELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("en",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Texte en francais"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.9.8-3NMI-06");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_RELEVANT_LANG_DECL_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        pIter = sourceCodeRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Présence d’une langue de traitement"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());
    }

    @Override
    protected void setConsolidate() {
        /*------------------------------------1Passed------------------------------------------*/
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.9.8-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.9.8-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.9.8-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.9.8-1Passed-04").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.9.8-1Passed-05").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.9.8-1Passed-06").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.9.8-1Passed-07").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.9.8-1Passed-08").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.9.8-1Passed-09").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.9.8-1Passed-10").getValue());

        /*------------------------------------2Failed------------------------------------------*/
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-08").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-09").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-10").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-11").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-12").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-13").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-14").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-15").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-16").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-17").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-18").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-19").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-20").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.9.8-2Failed-21").getValue());

        /*------------------------------------3NMI------------------------------------------*/
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.9.8-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.9.8-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.9.8-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.9.8-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.9.8-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.9.8-3NMI-06").getValue());
    }
}