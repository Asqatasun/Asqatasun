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
package org.opens.tanaguru.rules.rgaa30;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 11.01.02 of the
 * referential Rgaa 3.0.
 */
public class Rgaa30Rule110102Test extends Rgaa30RuleImplementationTestCase {

    public Rgaa30Rule110102Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.rgaa30.Rgaa30Rule110102");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Rgaa30.Test.11.01.02-1Passed-01",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-1Passed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-1Passed-02",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-1Passed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-1Passed-03",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-1Passed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-1Passed-04",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-1Passed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-1Passed-05",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-1Passed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-1Passed-06",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-1Passed-06.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-1Passed-07",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-1Passed-07.html"));
//        getWebResourceMap().put("Rgaa30.Test.11.01.02-1Passed-08",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-1Passed-08.html"));
//        getWebResourceMap().put("Rgaa30.Test.11.01.02-1Passed-09",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-1Passed-09.html"));
//        getWebResourceMap().put("Rgaa30.Test.11.01.02-1Passed-10",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-1Passed-10.html"));
//        getWebResourceMap().put("Rgaa30.Test.11.01.02-1Passed-11",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-1Passed-11.html"));
//        getWebResourceMap().put("Rgaa30.Test.11.01.02-1Passed-12",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-1Passed-12.html"));
//        getWebResourceMap().put("Rgaa30.Test.11.01.02-1Passed-13",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-1Passed-13.html"));
//        getWebResourceMap().put("Rgaa30.Test.11.01.02-1Passed-14",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-1Passed-14.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-2Failed-01",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-2Failed-02",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-2Failed-03",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-2Failed-04",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-2Failed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-2Failed-05",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-2Failed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-2Failed-06",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-2Failed-06.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-2Failed-07",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-2Failed-07.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-2Failed-08",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-2Failed-08.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-2Failed-09",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-2Failed-09.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-2Failed-10",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-2Failed-10.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-2Failed-11",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-2Failed-11.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-2Failed-12",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-2Failed-12.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-2Failed-13",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-2Failed-13.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-2Failed-14",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-2Failed-14.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-2Failed-A01",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-2Failed-A01.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-2Failed-A02",
                getWebResourceFactory().createPage(
                        getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-2Failed-A02.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-4NA-01.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-4NA-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-4NA-02.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-4NA-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-4NA-03.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-4NA-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-4NA-04.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-4NA-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-4NA-05.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-4NA-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-4NA-06.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-4NA-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-4NA-07.html"));
        getWebResourceMap().put("Rgaa30.Test.11.01.02-4NA-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule110102/Rgaa30.Test.11.01.02-4NA-08.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.11.01.02-1Passed-01");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());

        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-1Passed-02");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());

        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-1Passed-03");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());

        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-1Passed-04");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());

        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-1Passed-05");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());

        //----------------------------------------------------------------------
        //------------------------------1Passed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-1Passed-06");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());

        //----------------------------------------------------------------------
        //------------------------------1Passed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-1Passed-07");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());

//        //----------------------------------------------------------------------
//        //------------------------------1Passed-08------------------------------ NA A01
//        //----------------------------------------------------------------------
//        processResult = processPageTest("Rgaa30.Test.11.01.02-1Passed-08");
//        // check test result
//        assertEquals(TestSolution.PASSED, processResult.getValue());
//        // check test has no remark
//        assertNull(processResult.getRemarkSet());
//        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
//        //----------------------------------------------------------------------
//        //------------------------------1Passed-09------------------------------ NA A02
//        //----------------------------------------------------------------------
//        processResult = processPageTest("Rgaa30.Test.11.01.02-1Passed-09");
//        // check test result
//        assertEquals(TestSolution.PASSED, processResult.getValue());
//        // check test has no remark
//        assertNull(processResult.getRemarkSet());
//        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
//        
//        //----------------------------------------------------------------------
//        //------------------------------1Passed-10------------------------------ NA A03
//        //----------------------------------------------------------------------
//        processResult = processPageTest("Rgaa30.Test.11.01.02-1Passed-10");
//        // check test result
//        assertEquals(TestSolution.PASSED, processResult.getValue());
//        // check test has no remark
//        assertNull(processResult.getRemarkSet());
//        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
//        //----------------------------------------------------------------------
//        //------------------------------1Passed-11------------------------------ NA A04
//        //----------------------------------------------------------------------
//        processResult = processPageTest("Rgaa30.Test.11.01.02-1Passed-11");
//        // check test result
//        assertEquals(TestSolution.PASSED, processResult.getValue());
//        // check test has no remark
//        assertNull(processResult.getRemarkSet());
//        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
//        //----------------------------------------------------------------------
//        //------------------------------1Passed-12------------------------------ NA A05
//        //----------------------------------------------------------------------
//        processResult = processPageTest("Rgaa30.Test.11.01.02-1Passed-12");
//        // check test result
//        assertEquals(TestSolution.PASSED, processResult.getValue());
//        // check test has no remark
//        assertNull(processResult.getRemarkSet());
//        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
//        //----------------------------------------------------------------------
//        //------------------------------1Passed-13------------------------------ NA A06
//        //----------------------------------------------------------------------
//        processResult = processPageTest("Rgaa30.Test.11.01.02-1Passed-13");
//        // check test result
//        assertEquals(TestSolution.PASSED, processResult.getValue());
//        // check test has no remark
//        assertNull(processResult.getRemarkSet());
//        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
//        //----------------------------------------------------------------------
//        //------------------------------1Passed-14------------------------------ NA A07
//        //----------------------------------------------------------------------
//        processResult = processPageTest("Rgaa30.Test.11.01.02-1Passed-14");
//        // check test result
//        assertEquals(TestSolution.PASSED, processResult.getValue());
//        // check test has no remark
//        assertNull(processResult.getRemarkSet());
//        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark) ((LinkedHashSet) processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.ID_MISSING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark) ((LinkedHashSet) processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.ID_MISSING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark) ((LinkedHashSet) processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.ID_MISSING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-04");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark) ((LinkedHashSet) processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.ID_MISSING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-05");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark) ((LinkedHashSet) processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.ID_MISSING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-06");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark) ((LinkedHashSet) processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.ID_MISSING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.TEXTAREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-07");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark) ((LinkedHashSet) processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.ID_MISSING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.SELECT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        //----------------------------------------------------------------------
        //------------------------------2Failed-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-08");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark) ((LinkedHashSet) processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.ID_NOT_UNIQUE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        //----------------------------------------------------------------------
        //------------------------------2Failed-09------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-09");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark) ((LinkedHashSet) processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.ID_NOT_UNIQUE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        //----------------------------------------------------------------------
        //------------------------------2Failed-10------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-10");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark) ((LinkedHashSet) processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.ID_NOT_UNIQUE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        //----------------------------------------------------------------------
        //------------------------------2Failed-11------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-11");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark) ((LinkedHashSet) processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.ID_NOT_UNIQUE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        //----------------------------------------------------------------------
        //------------------------------2Failed-12------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-12");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark) ((LinkedHashSet) processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.ID_NOT_UNIQUE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        //----------------------------------------------------------------------
        //------------------------------2Failed-13------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-13");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark) ((LinkedHashSet) processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.ID_NOT_UNIQUE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.TEXTAREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        //----------------------------------------------------------------------
        //------------------------------2Failed-14------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-14");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark) ((LinkedHashSet) processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.ID_NOT_UNIQUE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.SELECT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        //----------------------------------------------------------------------
        //------------------------------2Failed-A01-----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-A01");
        // check number of elements in the page
        assertEquals(3, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(3, processResult.getRemarkSet().size());
        Collection<ProcessRemark> remarkSet = processResult.getRemarkSet();
        Iterator it = remarkSet.iterator();
        processRemark = ((SourceCodeRemark) it.next());
        assertEquals(RemarkMessageStore.FOR_MISSING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.LABEL_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        processRemark = ((SourceCodeRemark) it.next());
        assertEquals(RemarkMessageStore.FOR_MISSING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.LABEL_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        processRemark = ((SourceCodeRemark) it.next());
        assertEquals(RemarkMessageStore.FOR_MISSING_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.LABEL_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());

        //----------------------------------------------------------------------
        //------------------------------2Failed-A02-----------------------------
        //----------------------------------------------------------------------
        System.out.println("A02");
        processResult = processPageTest("Rgaa30.Test.11.01.02-2Failed-A02");
        // check number of elements in the page
        assertEquals(5, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(4, processResult.getRemarkSet().size());
        remarkSet = processResult.getRemarkSet();
        it = remarkSet.iterator();
        processRemark = ((SourceCodeRemark) it.next());
        assertEquals(RemarkMessageStore.INVALID_INPUT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        processRemark = ((SourceCodeRemark) it.next());
        assertEquals(RemarkMessageStore.INVALID_INPUT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.INPUT_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        processRemark = ((SourceCodeRemark) it.next());
        assertEquals(RemarkMessageStore.INVALID_LABEL_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.LABEL_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        processRemark = ((SourceCodeRemark) it.next());
        assertEquals(RemarkMessageStore.INVALID_LABEL_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.LABEL_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-4NA-03");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-4NA-04");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-05----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-4NA-05");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-06----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-4NA-06");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-07----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-4NA-07");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-08----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.11.01.02-4NA-08");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.11.01.02-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.11.01.02-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.11.01.02-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.11.01.02-1Passed-04").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.11.01.02-1Passed-05").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.11.01.02-1Passed-06").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.11.01.02-1Passed-07").getValue());
//        assertEquals(TestSolution.PASSED,
//                consolidate("Rgaa30.Test.11.01.02-1Passed-08").getValue());
//        assertEquals(TestSolution.PASSED,
//                consolidate("Rgaa30.Test.11.01.02-1Passed-09").getValue());
//        assertEquals(TestSolution.PASSED,
//                consolidate("Rgaa30.Test.11.01.02-1Passed-10").getValue());
//        assertEquals(TestSolution.PASSED,
//                consolidate("Rgaa30.Test.11.01.02-1Passed-11").getValue());
//        assertEquals(TestSolution.PASSED,
//                consolidate("Rgaa30.Test.11.01.02-1Passed-12").getValue());
//        assertEquals(TestSolution.PASSED,
//                consolidate("Rgaa30.Test.11.01.02-1Passed-13").getValue());
//        assertEquals(TestSolution.PASSED,
//                consolidate("Rgaa30.Test.11.01.02-1Passed-14").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.11.01.02-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.11.01.02-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.11.01.02-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.11.01.02-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.11.01.02-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.11.01.02-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.11.01.02-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.11.01.02-2Failed-08").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.11.01.02-2Failed-09").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.11.01.02-2Failed-10").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.11.01.02-2Failed-11").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.11.01.02-2Failed-12").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.11.01.02-2Failed-13").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.11.01.02-2Failed-14").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.11.01.02-2Failed-A01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.11.01.02-2Failed-A02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.11.01.02-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.11.01.02-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.11.01.02-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.11.01.02-4NA-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.11.01.02-4NA-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.11.01.02-4NA-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.11.01.02-4NA-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.11.01.02-4NA-08").getValue());
    }

}
