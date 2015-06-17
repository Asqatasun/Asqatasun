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
package org.tanaguru.rules.accessiweb22;

import java.util.Iterator;
import java.util.LinkedHashSet;
import org.tanaguru.entity.audit.*;
import org.tanaguru.rules.accessiweb22.test.Aw22RuleImplementationTestCase;
import org.tanaguru.rules.keystore.EvidenceStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 3.3.1 of the referential Accessiweb 2.2.
 *
 * @author jkowalczyk
 */
public class Aw22Rule03031Test extends Aw22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Aw22Rule03031Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb22.Aw22Rule03031");
    }

    @Override
    protected void setUpWebResourceMap() {
 /*------------------------------------1Passed------------------------------------------*/
        getWebResourceMap().put("AW22.Test.3.3.1-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-1Passed-01.html"));
        getWebResourceMap().put("AW22.Test.3.3.1-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-1Passed-02.html"));
        getWebResourceMap().put("AW22.Test.3.3.1-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-1Passed-03.html"));
        getWebResourceMap().put("AW22.Test.3.3.1-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-1Passed-04.html"));
        getWebResourceMap().put("AW22.Test.3.3.1-1Passed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-1Passed-05.html"));
        getWebResourceMap().put("AW22.Test.3.3.1-1Passed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-1Passed-06.html"));

        /*------------------------------------2Failed------------------------------------------*/
        getWebResourceMap().put("AW22.Test.3.3.1-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.3.3.1-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.3.3.1-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.3.3.1-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.3.3.1-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-2Failed-05.html"));

        /*------------------------------------3NMI------------------------------------------*/
        getWebResourceMap().put("AW22.Test.3.3.1-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-3NMI-01.html"));
        getWebResourceMap().put("AW22.Test.3.3.1-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-3NMI-02.html"));
        getWebResourceMap().put("AW22.Test.3.3.1-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-3NMI-03.html"));
        getWebResourceMap().put("AW22.Test.3.3.1-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-3NMI-04.html"));
        getWebResourceMap().put("AW22.Test.3.3.1-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-3NMI-05.html"));
        getWebResourceMap().put("AW22.Test.3.3.1-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-3NMI-06.html"));
        getWebResourceMap().put("AW22.Test.3.3.1-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-3NMI-07.html"));
        getWebResourceMap().put("AW22.Test.3.3.1-3NMI-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-3NMI-08.html"));

        /*------------------------------------4NA------------------------------------------*/
        getWebResourceMap().put("AW22.Test.3.3.1-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule03031/AW22.Test.3.3.1-4NA-01.html"));

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("AW22.Test.3.3.1-1Passed-01");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-1Passed-02");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-1Passed-03");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-1Passed-04");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-1Passed-05");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------1Passed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-1Passed-06");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-2Failed-01");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.BAD_CONTRAST_MSG, sourceCodeRemark.getMessageCode());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        Iterator<EvidenceElement> iter = sourceCodeRemark.getElementList().iterator();
        EvidenceElement ee = iter.next();
        assertEquals("rgb(255; 0; 0)", ee.getValue());
        assertEquals(EvidenceStore.FG_COLOR_EE, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("rgb(255; 255; 255)", ee.getValue());
        assertEquals(EvidenceStore.BG_COLOR_EE, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("4.00", ee.getValue().replaceAll(",", "."));
        assertEquals(EvidenceStore.CONTRAST_EE, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-2Failed-02");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.BAD_CONTRAST_MSG, sourceCodeRemark.getMessageCode());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("rgb(255; 165; 0)", ee.getValue());
        assertEquals(EvidenceStore.FG_COLOR_EE, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("rgb(255; 255; 255)", ee.getValue());
        assertEquals(EvidenceStore.BG_COLOR_EE, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("1.97", ee.getValue().replaceAll(",", "."));
        assertEquals(EvidenceStore.CONTRAST_EE, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-2Failed-03");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        Iterator<ProcessRemark> pIter = processResult.getRemarkSet().iterator();
        
        sourceCodeRemark = (SourceCodeRemark)pIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.NOT_TREATED_BACKGROUND_COLOR_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(-1, sourceCodeRemark.getLineNumber());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(ee.getValue().contains("transparent"));
        assertEquals(EvidenceStore.ELEMENT_NAME_EE, ee.getEvidence().getCode());
        
        sourceCodeRemark = (SourceCodeRemark)pIter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.BAD_CONTRAST_MSG, sourceCodeRemark.getMessageCode());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("rgb(255; 255; 255)", ee.getValue());
        assertEquals(EvidenceStore.FG_COLOR_EE, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("rgb(255; 165; 0)", ee.getValue());
        assertEquals(EvidenceStore.BG_COLOR_EE, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("1.97", ee.getValue().replaceAll(",", "."));
        assertEquals(EvidenceStore.CONTRAST_EE, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-2Failed-04");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        pIter = processResult.getRemarkSet().iterator();
        
        sourceCodeRemark = (SourceCodeRemark)pIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.NOT_TREATED_BACKGROUND_COLOR_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(-1, sourceCodeRemark.getLineNumber());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(ee.getValue().contains("transparent"));
        assertEquals(EvidenceStore.ELEMENT_NAME_EE, ee.getEvidence().getCode());
        
        sourceCodeRemark = (SourceCodeRemark)pIter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.BAD_CONTRAST_MSG, sourceCodeRemark.getMessageCode());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("rgb(223; 240; 216)", ee.getValue());
        assertEquals(EvidenceStore.FG_COLOR_EE, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("rgb(98; 111; 83)", ee.getValue());
        assertEquals(EvidenceStore.BG_COLOR_EE, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("4.49", ee.getValue().replaceAll(",", "."));
        assertEquals(EvidenceStore.CONTRAST_EE, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-2Failed-05");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        pIter = processResult.getRemarkSet().iterator();
        
        sourceCodeRemark = (SourceCodeRemark)pIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.NOT_TREATED_BACKGROUND_COLOR_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(-1, sourceCodeRemark.getLineNumber());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(ee.getValue().contains("transparent"));
        assertEquals(EvidenceStore.ELEMENT_NAME_EE, ee.getEvidence().getCode());
        
        sourceCodeRemark = (SourceCodeRemark)pIter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.BAD_CONTRAST_MSG, sourceCodeRemark.getMessageCode());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("rgb(70; 136; 71)", ee.getValue());
        assertEquals(EvidenceStore.FG_COLOR_EE, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("rgb(223; 240; 216)", ee.getValue());
        assertEquals(EvidenceStore.BG_COLOR_EE, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("3.61", ee.getValue().replaceAll(",", "."));
        assertEquals(EvidenceStore.CONTRAST_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-3NMI-01");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        ProcessRemark processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_CONTRAST_OF_IMAGE_MSG, processRemark.getMessageCode());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-3NMI-02");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_CONTRAST_OF_IMAGE_MSG, processRemark.getMessageCode());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-3NMI-03");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_CONTRAST_OF_IMAGE_MSG, processRemark.getMessageCode());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-3NMI-04");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_CONTRAST_OF_IMAGE_MSG, processRemark.getMessageCode());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-3NMI-05");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.NOT_TREATED_BACKGROUND_COLOR_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(-1, sourceCodeRemark.getLineNumber());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("linear-gradient(rgb(244, 244, 244) 55px, rgb(248, 248, 248) 100%)", ee.getValue());
        assertEquals(EvidenceStore.ELEMENT_NAME_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-3NMI-06");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.NOT_TREATED_BACKGROUND_COLOR_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(-1, sourceCodeRemark.getLineNumber());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(ee.getValue().contains("my-image.jpg')"));
        assertEquals(EvidenceStore.ELEMENT_NAME_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-07---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-3NMI-07");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.BAD_CONTRAST_HIDDEN_ELEMENT_MSG, sourceCodeRemark.getMessageCode());
        assertNotNull(sourceCodeRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("rgb(70; 136; 71)", ee.getValue());
        assertEquals(EvidenceStore.FG_COLOR_EE, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("rgb(223; 240; 216)", ee.getValue());
        assertEquals(EvidenceStore.BG_COLOR_EE, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("3.61", ee.getValue().replaceAll(",", "."));
        assertEquals(EvidenceStore.CONTRAST_EE, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-08--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-3NMI-08");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.NOT_TREATED_BACKGROUND_COLOR_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(-1, sourceCodeRemark.getLineNumber());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(ee.getValue().contains("transparent"));
        assertEquals(EvidenceStore.ELEMENT_NAME_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.3.3.1-4NA-01");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.3.3.1-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.3.3.1-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.3.3.1-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.3.3.1-1Passed-04").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.3.3.1-1Passed-05").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.3.3.1-1Passed-06").getValue());

        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.3.3.1-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.3.3.1-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.3.3.1-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.3.3.1-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.3.3.1-2Failed-05").getValue());

        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.3.3.1-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.3.3.1-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.3.3.1-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.3.3.1-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.3.3.1-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.3.3.1-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.3.3.1-3NMI-07").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.3.3.1-3NMI-08").getValue());

        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.3.3.1-4NA-01").getValue());

    }

}