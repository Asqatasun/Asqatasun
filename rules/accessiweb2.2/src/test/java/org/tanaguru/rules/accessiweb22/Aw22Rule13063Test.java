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
import org.tanaguru.rules.keystore.AttributeStore;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 13.6.3 of the referential Accessiweb 2.2.
 *
 * @author jkowalczyk
 */
public class Aw22Rule13063Test extends Aw22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Aw22Rule13063Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb22.Aw22Rule13063");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW22.Test.13.6.3-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule13063/AW22.Test.13.6.3-3NMI-01.html"));
        getWebResourceMap().put("AW22.Test.13.6.3-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule13063/AW22.Test.13.6.3-3NMI-02.html"));
        getWebResourceMap().put("AW22.Test.13.6.3-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule13063/AW22.Test.13.6.3-3NMI-03.html"));
        getWebResourceMap().put("AW22.Test.13.6.3-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule13063/AW22.Test.13.6.3-3NMI-04.html"));
        getWebResourceMap().put("AW22.Test.13.6.3-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule13063/AW22.Test.13.6.3-3NMI-05.html"));
        getWebResourceMap().put("AW22.Test.13.6.3-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule13063/AW22.Test.13.6.3-3NMI-06.html"));
        getWebResourceMap().put("AW22.Test.13.6.3-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule13063/AW22.Test.13.6.3-4NA-01.html"));
        getWebResourceMap().put("AW22.Test.13.6.3-4NA-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule13063/AW22.Test.13.6.3-4NA-02.html"));
        getWebResourceMap().put("AW22.Test.13.6.3-4NA-03",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule13063/AW22.Test.13.6.3-4NA-03.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("AW22.Test.13.6.3-3NMI-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, 
                     sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        Iterator<EvidenceElement> iter = sourceCodeRemark.getElementList().iterator();
        EvidenceElement ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.odt"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.13.6.3-3NMI-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        ProcessRemark processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(getMessageKey(RemarkMessageStore.CHECK_DOWNLOADABLE_DOCUMENT_FROM_FORM_MSG), 
                     processRemark.getMessageCode());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.13.6.3-3NMI-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(getMessageKey(RemarkMessageStore.CHECK_MANUALLY_LINK_WITHOUT_EXT_MSG), 
                     processRemark.getMessageCode());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.13.6.3-3NMI-04");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(getMessageKey(RemarkMessageStore.CHECK_MANUALLY_LINK_WITHOUT_EXT_MSG), 
                     processRemark.getMessageCode());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.13.6.3-3NMI-05");
        // check number of elements in the page
        assertEquals(177, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(177, processResult.getRemarkSet().size());
        Iterator<ProcessRemark> sIter = processResult.getRemarkSet().iterator();
        
	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.ods"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.fods"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.odt"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.fodt"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.odp"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.fodp"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.odg"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.fodg"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.pdf"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.doc"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.docx"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.docm"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.dot"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.dotm"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xls"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xlsx"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xlsm"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xlt"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xltx"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xltm"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xlc"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xlr"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xlam"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.csv"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.ppt"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.pptx"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.pps"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.vsd"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.vst"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.vss"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sxc"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sxd"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sxi"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sxm"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sxw"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sda"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sdc"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sdd"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sdf"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sdp"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sds"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sdw"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.oth"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.otg"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.ots"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.ott"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.cwk"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.cws"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.tar"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.tgz"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.bz"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.bz2"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.zip"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.gzip"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.gz"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.Z"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.7z"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.rar"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r00"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.rpm"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.deb"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.msi"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.exe"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.bat"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.pif"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.class"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.torrent"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.dmg"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.apk"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.bin"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.bak"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.dat"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.jar"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.mdk"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.dsk"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.vmdk"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r00"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r01"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r02"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r03"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r04"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r05"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r06"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r07"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r08"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r09"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r10"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r11"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r12"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r13"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r14"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r15"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r16"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r17"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r18"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r19"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r20"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r21"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r22"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r23"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r24"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r25"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r26"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r27"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r28"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r29"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r30"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r31"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r32"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r33"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r34"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r35"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r36"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r37"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r38"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r39"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r40"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r41"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r42"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r43"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r44"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r45"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r46"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r47"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r48"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r49"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r50"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r51"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r52"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r53"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r54"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r55"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r56"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r57"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r58"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r59"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r60"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r61"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r62"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r63"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r64"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r65"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r66"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r67"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r68"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r69"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r70"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r71"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r72"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r73"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r74"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r75"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r76"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r77"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r78"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r79"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r80"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r81"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r82"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r83"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r84"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r85"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r86"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r87"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r88"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r89"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r90"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r91"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r92"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r93"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r94"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r95"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r96"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r97"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r98"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.r99"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, sourceCodeRemark.getElementList().size());
        iter = sourceCodeRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.taz"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("attribute-absent",ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.13.6.3-3NMI-06");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(getMessageKey(RemarkMessageStore.CHECK_MANUALLY_LINK_WITHOUT_EXT_MSG), 
                     processRemark.getMessageCode());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA--01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.13.6.3-4NA-01");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.13.6.3-4NA-02");
        // check number of elements in the page
        assertEquals(26, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.13.6.3-4NA-03");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.13.6.3-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.13.6.3-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.13.6.3-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.13.6.3-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.13.6.3-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.13.6.3-3NMI-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.13.6.3-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.13.6.3-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.13.6.3-4NA-03").getValue());
    }

    /**
     * 
     * @param msg
     * @return the message suffixed with the test key identifier
     */
    private String getMessageKey(String msg) {
        StringBuilder strb = new StringBuilder(msg);
        strb.append("_");
        strb.append(getName());
        return strb.toString();
    }
    
}