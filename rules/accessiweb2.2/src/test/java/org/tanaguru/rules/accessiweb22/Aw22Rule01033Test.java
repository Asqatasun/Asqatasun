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
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.audit.*;
import org.tanaguru.rules.accessiweb22.test.Aw22RuleImplementationTestCase;
import static org.tanaguru.rules.keystore.AttributeStore.ALT_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.SRC_ATTR;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;


/**
 * Unit test class for the implementation of the rule 1.3.3 of the referential Accessiweb 2.2.
 *
 * @author jkowalczyk
 */
public class Aw22Rule01033Test extends Aw22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Aw22Rule01033Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb22.Aw22Rule01033");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW22.Test.1.3.3-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01033/AW22.Test.1.3.3-2Failed-01.html"));
        addParameterToParameterMap("AW22.Test.1.3.3-2Failed-01", createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-input"));

        getWebResourceMap().put("AW22.Test.1.3.3-2Failed-02",
                getWebResourceFactory().createPage(
                    getTestcasesFilePath() + "accessiweb22/Aw22Rule01033/AW22.Test.1.3.3-2Failed-02.html"));
        addParameterToParameterMap("AW22.Test.1.3.3-2Failed-02", createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-input"));
        
        getWebResourceMap().put("AW22.Test.1.3.3-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01033/AW22.Test.1.3.3-2Failed-03.html"));
        addParameterToParameterMap("AW22.Test.1.3.3-2Failed-03", createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-input"));
        
        getWebResourceMap().put("AW22.Test.1.3.3-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01033/AW22.Test.1.3.3-2Failed-04.html"));
        addParameterToParameterMap("AW22.Test.1.3.3-2Failed-04", createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-input"));
        
        getWebResourceMap().put("AW22.Test.1.3.3-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01033/AW22.Test.1.3.3-2Failed-05.html"));
        addParameterToParameterMap("AW22.Test.1.3.3-2Failed-05", createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-input"));
        addParameterToParameterMap("AW22.Test.1.3.3-2Failed-05", createParameter("Rules", "DECORATIVE_IMAGE_MARKER", "class-decorative-input"));
        
        getWebResourceMap().put("AW22.Test.1.3.3-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01033/AW22.Test.1.3.3-3NMI-01.html"));
        
        getWebResourceMap().put("AW22.Test.1.3.3-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01033/AW22.Test.1.3.3-3NMI-02.html"));
        
        getWebResourceMap().put("AW22.Test.1.3.3-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01033/AW22.Test.1.3.3-3NMI-03.html"));
        
        getWebResourceMap().put("AW22.Test.1.3.3-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01033/AW22.Test.1.3.3-3NMI-04.html"));
        addParameterToParameterMap("AW22.Test.1.3.3-3NMI-04", createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-input"));
    
        getWebResourceMap().put("AW22.Test.1.3.3-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01033/AW22.Test.1.3.3-3NMI-05.html"));
        
        getWebResourceMap().put("AW22.Test.1.3.3-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01033/AW22.Test.1.3.3-3NMI-06.html"));
        addParameterToParameterMap("AW22.Test.1.3.3-3NMI-06", createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-input"));
        addParameterToParameterMap("AW22.Test.1.3.3-3NMI-06", createParameter("Rules", "DECORATIVE_IMAGE_MARKER", "class-decorative-input"));
        
        getWebResourceMap().put("AW22.Test.1.3.3-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01033/AW22.Test.1.3.3-4NA-01.html"));
        
        getWebResourceMap().put("AW22.Test.1.3.3-4NA-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01033/AW22.Test.1.3.3-4NA-02.html"));
        
        getWebResourceMap().put("AW22.Test.1.3.3-4NA-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01033/AW22.Test.1.3.3-4NA-03.html"));
        
        getWebResourceMap().put("AW22.Test.1.3.3-4NA-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01033/AW22.Test.1.3.3-4NA-04.html"));
        addParameterToParameterMap("AW22.Test.1.3.3-4NA-04", createParameter("Rules", "DECORATIVE_IMAGE_MARKER", "class-decorative-input"));
        
        getWebResourceMap().put("AW22.Test.1.3.3-4NA-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01033/AW22.Test.1.3.3-4NA-05.html"));
        
        getWebResourceMap().put("AW22.Test.1.3.3-4NA-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01033/AW22.Test.1.3.3-4NA-06.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("AW22.Test.1.3.3-2Failed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        Iterator<EvidenceElement> iter = processRemark.getElementList().iterator();
        EvidenceElement ee = iter.next();
        assertEquals("mock-image", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.3.3-2Failed-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("mock-image.png", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.3.3-2Failed-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("--><--", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.3.3-2Failed-04");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.3.3-2Failed-05");
        // check number of elements in the page
        assertEquals(4, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(4, processResult.getRemarkSet().size());
        Iterator<ProcessRemark> pIter = processResult.getRemarkSet().iterator();
        
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image1"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("Informative input alternative", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image2"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("mock-image3", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image3"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_ALT_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("not identified input alternative", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image4"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.3.3-3NMI-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("mock-image", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.3.3-3NMI-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("mock-image.png", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.3.3-3NMI-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("#!/;'(|", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.3.3-3NMI-04");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("Informative input alternative", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.3.3-3NMI-05");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_ALT_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("Not identified input alternative", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.3.3-3NMI-06");
        // check number of elements in the page
        assertEquals(3, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(3, processResult.getRemarkSet().size());
        pIter = processResult.getRemarkSet().iterator();
        
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("Informative input alternative", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image2"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("mock-image3", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image3"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_ALT_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("not identified input alternative", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image4"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.3.3-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.3.3-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.3.3-4NA-03");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.3.3-4NA-03");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.3.3-4NA-04");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-05----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.3.3-4NA-05");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-06----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.3.3-4NA-06");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.1.3.3-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.1.3.3-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.1.3.3-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.1.3.3-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.1.3.3-2Failed-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.1.3.3-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.1.3.3-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.1.3.3-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.1.3.3-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.1.3.3-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.1.3.3-3NMI-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.1.3.3-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.1.3.3-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.1.3.3-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.1.3.3-4NA-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.1.3.3-4NA-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.1.3.3-4NA-06").getValue());
    }
}