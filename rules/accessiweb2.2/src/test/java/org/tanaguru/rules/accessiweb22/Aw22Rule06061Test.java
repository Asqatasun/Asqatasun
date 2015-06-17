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
import org.tanaguru.entity.audit.*;
import org.tanaguru.rules.accessiweb22.test.Aw22RuleImplementationTestCase;
import org.tanaguru.rules.keystore.AttributeStore;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 *
 * @author jkowalczyk
 */
public class Aw22Rule06061Test extends Aw22RuleImplementationTestCase {

    public Aw22Rule06061Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb22.Aw22Rule06061");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW22.Test.06.06.01-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-1Passed-01.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-1Passed-02.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-1Passed-03.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-1Passed-04.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-1Passed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-1Passed-05.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-2Failed-05.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult =
                processPageTest("AW22.Test.06.06.01-1Passed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.06.01-1Passed-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.06.01-1Passed-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.06.01-1Passed-04");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.06.01-1Passed-05");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.06.01-2Failed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.EMPTY_LINK_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1,processRemark.getElementList().size());
        assertTrue(processRemark.getElementList().iterator().next().getValue().contains("my-link.html"));
        assertEquals(AttributeStore.HREF_ATTR, processRemark.getElementList().
                iterator().next().getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.06.01-2Failed-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.EMPTY_LINK_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1,processRemark.getElementList().size());
        assertTrue(processRemark.getElementList().iterator().next().getValue().contains("my-link.html"));
        assertEquals(AttributeStore.HREF_ATTR, processRemark.getElementList().
                iterator().next().getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.06.01-2Failed-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.EMPTY_LINK_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1,processRemark.getElementList().size());
        assertTrue(processRemark.getElementList().iterator().next().getValue().contains("my-link.html"));
        assertEquals(AttributeStore.HREF_ATTR, processRemark.getElementList().
                iterator().next().getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.06.01-2Failed-04");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.EMPTY_LINK_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1,processRemark.getElementList().size());
        assertTrue(processRemark.getElementList().iterator().next().getValue().contains("my-link.html"));
        assertEquals(AttributeStore.HREF_ATTR, processRemark.getElementList().
                iterator().next().getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.06.01-2Failed-05");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.EMPTY_LINK_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1,processRemark.getElementList().size());
        assertTrue(processRemark.getElementList().iterator().next().getValue().contains("my-link.html"));
        assertEquals(AttributeStore.HREF_ATTR, processRemark.getElementList().
                iterator().next().getEvidence().getCode());
        
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.06.01-4NA-01");
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
                consolidate("AW22.Test.06.06.01-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.06.06.01-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.06.06.01-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.06.06.01-1Passed-04").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.06.06.01-1Passed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.06.01-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.06.01-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.06.01-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.06.01-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.06.01-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.06.01-4NA-01").getValue());
    }
}
