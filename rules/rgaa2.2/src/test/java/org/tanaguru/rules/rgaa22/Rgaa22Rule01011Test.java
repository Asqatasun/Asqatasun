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

import java.util.LinkedHashSet;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 1.1.1 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule01011Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule01011Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa22.Rgaa22Rule01011");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Rgaa22.Test.1.1-1Passed-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule01011/RGAA22.Test.1.1-1Passed-01.html"));
        getWebResourceMap().put("Rgaa22.Test.1.1-1Passed-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule01011/RGAA22.Test.1.1-1Passed-02.html"));
        getWebResourceMap().put("Rgaa22.Test.1.1-1Passed-03",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule01011/RGAA22.Test.1.1-1Passed-03.html"));
        getWebResourceMap().put("Rgaa22.Test.1.1-2Failed-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule01011/RGAA22.Test.1.1-2Failed-01.html"));
        getWebResourceMap().put("Rgaa22.Test.1.1-2Failed-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule01011/RGAA22.Test.1.1-2Failed-02.html"));
        getWebResourceMap().put("Rgaa22.Test.1.1-2Failed-03",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule01011/RGAA22.Test.1.1-2Failed-03.html"));
        getWebResourceMap().put("Rgaa22.Test.1.1-2Failed-04",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule01011/RGAA22.Test.1.1-2Failed-04.html"));
        getWebResourceMap().put("Rgaa22.Test.1.1-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule01011/RGAA22.Test.1.1-4NA-01.html"));
        getWebResourceMap().put("Rgaa22.Test.1.1-4NA-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule01011/RGAA22.Test.1.1-4NA-02.html"));
        getWebResourceMap().put("Rgaa22.Test.1.1-4NA-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule01011/RGAA22.Test.1.1-4NA-03.html"));
        getWebResourceMap().put("Rgaa22.Test.1.1-4NA-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule01011/RGAA22.Test.1.1-4NA-04.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa22.Test.1.1-1Passed-01");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());


        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.1.1-1Passed-02");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        

        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.1.1-1Passed-03");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.1.1-2Failed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.EMPTY_TITLE_OF_FRAME_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.FRAME_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.1.1-2Failed-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.EMPTY_TITLE_OF_FRAME_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.IFRAME_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.1.1-2Failed-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.EMPTY_TITLE_OF_FRAME_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.FRAME_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.1.1-2Failed-04");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.EMPTY_TITLE_OF_FRAME_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.FRAME_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());


        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.1.1-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.1.1-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.1.1-4NA-03");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.1.1-4NA-04");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa22.Test.1.1-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa22.Test.1.1-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa22.Test.1.1-1Passed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa22.Test.1.1-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa22.Test.1.1-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa22.Test.1.1-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa22.Test.1.1-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa22.Test.1.1-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa22.Test.1.1-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa22.Test.1.1-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa22.Test.1.1-4NA-04").getValue());
    }

}