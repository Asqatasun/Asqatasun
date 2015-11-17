/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.rules.accessiweb22;

import java.util.LinkedHashSet;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.SourceCodeRemark;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.rules.accessiweb22.test.Aw22RuleImplementationTestCase;
import org.asqatasun.rules.keystore.EvidenceStore;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;

/**
 *
 * @author jkowalczyk
 */
public class Aw22Rule09012Test extends Aw22RuleImplementationTestCase {
    
    public Aw22Rule09012Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.asqatasun.rules.accessiweb22.Aw22Rule09012");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW22.Test.9.1.2-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09012/AW22.Test.9.1.2-1Passed-01.html"));
        getWebResourceMap().put("AW22.Test.9.1.2-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09012/AW22.Test.9.1.2-1Passed-02.html"));
        getWebResourceMap().put("AW22.Test.9.1.2-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09012/AW22.Test.9.1.2-1Passed-03.html"));
        getWebResourceMap().put("AW22.Test.9.1.2-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09012/AW22.Test.9.1.2-1Passed-04.html"));
        getWebResourceMap().put("AW22.Test.9.1.2-1Passed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09012/AW22.Test.9.1.2-1Passed-05.html"));
        getWebResourceMap().put("AW22.Test.9.1.2-1Passed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09012/AW22.Test.9.1.2-1Passed-06.html"));
        getWebResourceMap().put("AW22.Test.9.1.2-1Passed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09012/AW22.Test.9.1.2-1Passed-07.html"));
        getWebResourceMap().put("AW22.Test.9.1.2-1Passed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09012/AW22.Test.9.1.2-1Passed-08.html"));
        getWebResourceMap().put("AW22.Test.9.1.2-1Passed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09012/AW22.Test.9.1.2-1Passed-09.html"));
        getWebResourceMap().put("AW22.Test.9.1.2-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09012/AW22.Test.9.1.2-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.9.1.2-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09012/AW22.Test.9.1.2-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.9.1.2-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09012/AW22.Test.9.1.2-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.9.1.2-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09012/AW22.Test.9.1.2-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.9.1.2-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09012/AW22.Test.9.1.2-2Failed-05.html"));
        getWebResourceMap().put("AW22.Test.9.1.2-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09012/AW22.Test.9.1.2-2Failed-06.html"));
        getWebResourceMap().put("AW22.Test.9.1.2-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09012/AW22.Test.9.1.2-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("AW22.Test.9.1.2-1Passed-01");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.9.1.2-1Passed-02");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(3, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.9.1.2-1Passed-03");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(3, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.9.1.2-1Passed-04");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(4, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.9.1.2-1Passed-05");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.9.1.2-1Passed-06");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(6, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.9.1.2-1Passed-07");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.9.1.2-1Passed-08");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-09------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.9.1.2-1Passed-09");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.9.1.2-2Failed-01");
        // check number of elements in the page
        assertEquals(3, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H3_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1,processRemark.getElementList().size());
        assertEquals("h1", processRemark.getElementList().iterator().next().getValue());
        assertEquals(EvidenceStore.PREVIOUS_H_TAG_INDEX_EE, processRemark.getElementList().
                iterator().next().getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.9.1.2-2Failed-02");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H1_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1,processRemark.getElementList().size());
        assertEquals("h2", processRemark.getElementList().iterator().next().getValue());
        assertEquals(EvidenceStore.FIRST_H_TAG_INDEX_EE, processRemark.getElementList().
                iterator().next().getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.9.1.2-2Failed-03");
        // check number of elements in the page
        assertEquals(3, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H4_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1,processRemark.getElementList().size());
        assertEquals("h2", processRemark.getElementList().iterator().next().getValue());
        assertEquals(EvidenceStore.PREVIOUS_H_TAG_INDEX_EE, processRemark.getElementList().
                iterator().next().getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.9.1.2-2Failed-04");
        // check number of elements in the page
        assertEquals(4, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H5_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1,processRemark.getElementList().size());
        assertEquals("h3", processRemark.getElementList().iterator().next().getValue());
        assertEquals(EvidenceStore.PREVIOUS_H_TAG_INDEX_EE, processRemark.getElementList().
                iterator().next().getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.9.1.2-2Failed-05");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H6_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1,processRemark.getElementList().size());
        assertEquals("h4", processRemark.getElementList().iterator().next().getValue());
        assertEquals(EvidenceStore.PREVIOUS_H_TAG_INDEX_EE, processRemark.getElementList().
                iterator().next().getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.9.1.2-2Failed-06");
        // check number of elements in the page
        assertEquals(10, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.H6_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1,processRemark.getElementList().size());
        assertEquals("h4", processRemark.getElementList().iterator().next().getValue());
        assertEquals(EvidenceStore.PREVIOUS_H_TAG_INDEX_EE, processRemark.getElementList().
                iterator().next().getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.9.1.2-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.9.1.2-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.9.1.2-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.9.1.2-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.9.1.2-1Passed-04").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.9.1.2-1Passed-05").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.9.1.2-1Passed-06").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.9.1.2-1Passed-07").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.9.1.2-1Passed-08").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.9.1.2-1Passed-09").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.9.1.2-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.9.1.2-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.9.1.2-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.9.1.2-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.9.1.2-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.9.1.2-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.9.1.2-4NA-01").getValue());
    }

}
