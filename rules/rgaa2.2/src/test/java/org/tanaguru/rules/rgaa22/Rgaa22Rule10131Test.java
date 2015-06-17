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
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.audit.*;
import org.tanaguru.rules.keystore.AttributeStore;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 10.13 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule10131Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule10131Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa22.Rgaa22Rule10131");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("RGAA22.Test.10.13-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule10131/RGAA22.Test.10.13-3NMI-01.html"));
        getWebResourceMap().put("RGAA22.Test.10.13-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule10131/RGAA22.Test.10.13-3NMI-02.html"));
        getWebResourceMap().put("RGAA22.Test.10.13-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule10131/RGAA22.Test.10.13-3NMI-03.html"));
        getWebResourceMap().put("RGAA22.Test.10.13-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule10131/RGAA22.Test.10.13-3NMI-04.html"));
        getWebResourceMap().put("RGAA22.Test.10.13-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule10131/RGAA22.Test.10.13-3NMI-05.html"));
        getWebResourceMap().put("RGAA22.Test.10.13-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule10131/RGAA22.Test.10.13-3NMI-06.html"));
        getWebResourceMap().put("RGAA22.Test.10.13-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule10131/RGAA22.Test.10.13-4NA-01.html"));
        getWebResourceMap().put("RGAA22.Test.10.13-4NA-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule10131/RGAA22.Test.10.13-4NA-02.html"));
        getWebResourceMap().put("RGAA22.Test.10.13-4NA-03",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule10131/RGAA22.Test.10.13-4NA-03.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("RGAA22.Test.10.13-3NMI-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        EvidenceElement ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.odt"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.10.13-3NMI-02");
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
        processResult = processPageTest("RGAA22.Test.10.13-3NMI-03");
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
        processResult = processPageTest("RGAA22.Test.10.13-3NMI-04");
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
        processResult = processPageTest("RGAA22.Test.10.13-3NMI-05");
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
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.10.13-3NMI-06");
        // check number of elements in the page
        assertEquals(48, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(48, processResult.getRemarkSet().size());
        Iterator<ProcessRemark> sIter = processResult.getRemarkSet().iterator();
        
        //----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.ods"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        
	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.fods"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        
	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.odt"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        
	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.fodt"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.odp"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.fodp"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.odg"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.fodg"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.pdf"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.doc"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.docx"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.docm"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.dot"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.dotm"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xls"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xlsx"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xlsm"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xlt"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xltx"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xltm"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xlc"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xlr"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.xlam"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.csv"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.ppt"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.pptx"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.pps"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.vsd"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        
	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.vst"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        
	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.vss"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sxc"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sxd"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        
	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sxi"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sxm"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sxw"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sda"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        
	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sdc"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sdd"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sdf"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sdp"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sds"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.sdw"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.oth"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.otg"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        
	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.ots"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        
	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.ott"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.cwk"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());

	//----------------------------------------------------------------------
        sourceCodeRemark = (SourceCodeRemark)sIter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "my-link.cws"));
        assertEquals(AttributeStore.HREF_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA--01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.10.13-4NA-01");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.10.13-4NA-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.10.13-4NA-03");
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
                consolidate("RGAA22.Test.10.13-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.10.13-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.10.13-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.10.13-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.10.13-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.10.13-3NMI-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.10.13-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.10.13-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.10.13-4NA-03").getValue());
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