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
import org.tanaguru.entity.audit.EvidenceElement;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.accessiweb22.test.Aw22RuleImplementationTestCase;
import org.tanaguru.rules.keystore.EvidenceStore;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 8.8.2 of the referential Accessiweb 2.2.
 *
 * @author jkowalczyk
 */
public class Aw22Rule08082Test extends Aw22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Aw22Rule08082Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb22.Aw22Rule08082");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW22.Test.8.8.2-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08082/AW22.Test.8.8.2-1Passed-01.html"));
        getWebResourceMap().put("AW22.Test.8.8.2-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08082/AW22.Test.8.8.2-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.8.8.2-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08082/AW22.Test.8.8.2-3NMI-01.html"));
        getWebResourceMap().put("AW22.Test.8.8.2-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08082/AW22.Test.8.8.2-3NMI-02.html"));
        getWebResourceMap().put("AW22.Test.8.8.2-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08082/AW22.Test.8.8.2-3NMI-03.html"));
        getWebResourceMap().put("AW22.Test.8.8.2-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule08082/AW22.Test.8.8.2-4NA-01.html"));
        getWebResourceMap().put("AW22.Test.8.8.2-4NA-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule08082/AW22.Test.8.8.2-4NA-02.html"));
        getWebResourceMap().put("AW22.Test.8.8.2-4NA-03",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule08082/AW22.Test.8.8.2-4NA-03.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //---------------------------1Passed-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("AW22.Test.8.8.2-1Passed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.8.8.2-2Failed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.IRRELEVANT_LANG_DECL_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, processRemark.getElementList().size());
        Iterator<EvidenceElement> pIter = processRemark.getElementList().iterator();
        EvidenceElement ee = pIter.next();
        assertEquals("fr",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertEquals("en",ee.getValue());
        assertEquals(EvidenceStore.DETECTED_LANGUAGE_EE, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(ee.getValue().contains("Some text is written here in english"));
        assertEquals(EvidenceStore.EXTRACTED_TEXT_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.8.8.2-3NMI-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_RELEVANT_LANG_DECL_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, processRemark.getElementList().size());
        pIter = processRemark.getElementList().iterator();
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
        //------------------------------3NMI-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.8.8.2-3NMI-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_RELEVANT_LANG_DECL_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, processRemark.getElementList().size());
        pIter = processRemark.getElementList().iterator();
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
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.8.8.2-3NMI-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.SUSPECTED_IRRELEVANT_LANG_DECL_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(3, processRemark.getElementList().size());
        pIter = processRemark.getElementList().iterator();
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
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.8.8.2-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.8.8.2-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.8.8.2-4NA-03");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.8.8.2-1Passed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.8.8.2-2Failed-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.8.8.2-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.8.8.2-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.8.8.2-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.8.8.2-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.8.8.2-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.8.8.2-4NA-03").getValue());
    }

}