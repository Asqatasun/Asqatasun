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

import java.util.LinkedHashSet;
import org.tanaguru.entity.audit.EvidenceElement;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.accessiweb22.test.Aw22RuleImplementationTestCase;
import org.tanaguru.rules.keystore.HtmlElementStore;
import static org.tanaguru.rules.keystore.AttributeStore.*;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 11.2.2 of the referential Accessiweb 2.2.
 */
public class Aw22Rule11022Test extends Aw22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Aw22Rule11022Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb22.Aw22Rule11022");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW22.Test.11.2.2-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule11022/AW22.Test.11.2.2-3NMI-01.html"));
        getWebResourceMap().put("AW22.Test.11.2.2-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule11022/AW22.Test.11.2.2-3NMI-02.html"));
        getWebResourceMap().put("AW22.Test.11.2.2-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule11022/AW22.Test.11.2.2-3NMI-03.html"));
        getWebResourceMap().put("AW22.Test.11.2.2-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule11022/AW22.Test.11.2.2-3NMI-04.html"));
        getWebResourceMap().put("AW22.Test.11.2.2-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule11022/AW22.Test.11.2.2-3NMI-05.html"));
        getWebResourceMap().put("AW22.Test.11.2.2-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule11022/AW22.Test.11.2.2-3NMI-06.html"));
        getWebResourceMap().put("AW22.Test.11.2.2-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule11022/AW22.Test.11.2.2-3NMI-07.html"));
        getWebResourceMap().put("AW22.Test.11.2.2-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule11022/AW22.Test.11.2.2-4NA-01.html"));
        getWebResourceMap().put("AW22.Test.11.2.2-4NA-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule11022/AW22.Test.11.2.2-4NA-02.html"));
        getWebResourceMap().put("AW22.Test.11.2.2-4NA-03",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule11022/AW22.Test.11.2.2-4NA-03.html"));
        getWebResourceMap().put("AW22.Test.11.2.2-4NA-04",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule11022/AW22.Test.11.2.2-4NA-04.html"));
        getWebResourceMap().put("AW22.Test.11.2.2-4NA-05",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule11022/AW22.Test.11.2.2-4NA-05.html"));
        getWebResourceMap().put("AW22.Test.11.2.2-4NA-06",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule11022/AW22.Test.11.2.2-4NA-06.html"));
        getWebResourceMap().put("AW22.Test.11.2.2-4NA-07",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule11022/AW22.Test.11.2.2-4NA-07.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("AW22.Test.11.2.2-3NMI-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        EvidenceElement ee = processRemark.getElementList().iterator().next();
        assertEquals("input title", ee.getValue());
        assertEquals(TITLE_ATTR, ee.getEvidence().getCode());
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.11.2.2-3NMI-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("input title", ee.getValue());
        assertEquals(TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.11.2.2-3NMI-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("input title", ee.getValue());
        assertEquals(TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.11.2.2-3NMI-04");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("input title", ee.getValue());
        assertEquals(TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.11.2.2-3NMI-05");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("input title", ee.getValue());
        assertEquals(TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.11.2.2-3NMI-06");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.TEXTAREA_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("input title", ee.getValue());
        assertEquals(TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-07---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.11.2.2-3NMI-07");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.SELECT_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals("input title", ee.getValue());
        assertEquals(TITLE_ATTR, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.11.2.2-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.11.2.2-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.11.2.2-4NA-03");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.11.2.2-4NA-04");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-05----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.11.2.2-4NA-05");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-06----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.11.2.2-4NA-06");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-07----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.11.2.2-4NA-07");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.11.2.2-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.11.2.2-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.11.2.2-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.11.2.2-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.11.2.2-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.11.2.2-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.11.2.2-3NMI-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.11.2.2-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.11.2.2-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.11.2.2-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.11.2.2-4NA-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.11.2.2-4NA-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.11.2.2-4NA-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.11.2.2-4NA-07").getValue());
    }

}