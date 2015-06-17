/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.accessiweb22.test.Aw22RuleImplementationTestCase;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 *
 * @author jkowalczyk
 */
public class Aw22Rule10011Test extends Aw22RuleImplementationTestCase {

    public Aw22Rule10011Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName( "org.tanaguru.rules.accessiweb22.Aw22Rule10011");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW22.Test.10.1.1-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10011/AW22.Test.10.1.1-1Passed-01.html"));
        getWebResourceMap().put("AW22.Test.10.1.1-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10011/AW22.Test.10.1.1-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.10.1.1-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10011/AW22.Test.10.1.1-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.10.1.1-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10011/AW22.Test.10.1.1-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.10.1.1-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10011/AW22.Test.10.1.1-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.10.1.1-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10011/AW22.Test.10.1.1-2Failed-05.html"));
        getWebResourceMap().put("AW22.Test.10.1.1-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10011/AW22.Test.10.1.1-2Failed-06.html"));
        getWebResourceMap().put("AW22.Test.10.1.1-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10011/AW22.Test.10.1.1-2Failed-07.html"));
        getWebResourceMap().put("AW22.Test.10.1.1-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10011/AW22.Test.10.1.1-2Failed-08.html"));
        getWebResourceMap().put("AW22.Test.10.1.1-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10011/AW22.Test.10.1.1-2Failed-09.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("AW22.Test.10.1.1-1Passed-01");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(12, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.1.1-2Failed-01");
        // check number of elements in the page
        assertEquals(13, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("basefont", processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.1.1-2Failed-02");
        // check number of elements in the page
        assertEquals(13, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("center", processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.1.1-2Failed-03");
        // check number of elements in the page
        assertEquals(13, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("blink", processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.1.1-2Failed-04");
        // check number of elements in the page
        assertEquals(13, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("font", processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.1.1-2Failed-05");
        // check number of elements in the page
        assertEquals(13, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("tt", processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.1.1-2Failed-06");
        // check number of elements in the page
        assertEquals(13, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("marquee", processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.1.1-2Failed-07");
        // check number of elements in the page
        assertEquals(13, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("s", processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.1.1-2Failed-08");
        // check number of elements in the page
        assertEquals(13, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("strike", processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-09------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.1.1-2Failed-09");
        // check number of elements in the page
        assertEquals(13, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("u", processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.10.1.1-1Passed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.1.1-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.1.1-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.1.1-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.1.1-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.1.1-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.1.1-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.1.1-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.1.1-2Failed-08").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.1.1-2Failed-09").getValue());
    }

}
