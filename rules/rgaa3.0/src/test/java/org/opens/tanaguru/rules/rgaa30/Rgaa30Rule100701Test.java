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
package org.opens.tanaguru.rules.rgaa30;

import java.util.LinkedHashSet;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 10.07.01 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule100701Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa30Rule100701Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.opens.tanaguru.rules.rgaa30.Rgaa30Rule100701");
    }

    @Override
    protected void setUpWebResourceMap() {
                /*------------------------------------1Passed------------------------------------------*/
        getWebResourceMap().put("Rgaa30.Test.10.07.01-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-1Passed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-1Passed-02.html"));

        /*------------------------------------2Failed------------------------------------------*/
        getWebResourceMap().put("Rgaa30.Test.10.07.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-2Failed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-2Failed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-2Failed-06.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-2Failed-07.html"));

        /*------------------------------------3NMI------------------------------------------*/
        getWebResourceMap().put("Rgaa30.Test.10.07.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-3NMI-01.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-3NMI-02.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-3NMI-03.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-3NMI-04.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-3NMI-05.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-3NMI-06.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-3NMI-07.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-3NMI-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-3NMI-08.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-3NMI-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-3NMI-09.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-3NMI-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-3NMI-10.html"));

        /*------------------------------------4NA------------------------------------------*/
        getWebResourceMap().put("Rgaa30.Test.10.07.01-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-4NA-01.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-4NA-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-4NA-02.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-4NA-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-4NA-03.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-4NA-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-4NA-04.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-4NA-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-4NA-05.html"));
        getWebResourceMap().put("Rgaa30.Test.10.07.01-4NA-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100701/Rgaa30.Test.10.07.01-4NA-06.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.10.07.01-1Passed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-1Passed-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-2Failed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.INVISIBLE_OUTLINE_ON_FOCUS_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());


        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-2Failed-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.INVISIBLE_OUTLINE_ON_FOCUS_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());


        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-2Failed-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.INVISIBLE_OUTLINE_ON_FOCUS_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());


        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-2Failed-04");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.INVISIBLE_OUTLINE_ON_FOCUS_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());


        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-2Failed-05");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.INVISIBLE_OUTLINE_ON_FOCUS_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());


        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-2Failed-06");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.INVISIBLE_OUTLINE_ON_FOCUS_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());


        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-2Failed-07");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.INVISIBLE_OUTLINE_ON_FOCUS_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.SPAN_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());


        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-3NMI-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        ProcessRemark pr = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, pr.getIssue());
        assertEquals(RemarkMessageStore.CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, pr.getMessageCode());
        // check number of evidence elements and their value
        assertNull(pr.getElementList());
        
        
        //----------------------------------------------------------------------
        //---------------------------------3NMI-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-3NMI-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        pr = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, pr.getIssue());
        assertEquals(RemarkMessageStore.CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, pr.getMessageCode());
        // check number of evidence elements and their value
        assertNull(pr.getElementList());


	//----------------------------------------------------------------------
        //---------------------------------3NMI-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-3NMI-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        pr = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, pr.getIssue());
        assertEquals(RemarkMessageStore.CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, pr.getMessageCode());
        // check number of evidence elements and their value
        assertNull(pr.getElementList());


	//----------------------------------------------------------------------
        //---------------------------------3NMI-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-3NMI-04");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        pr = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, pr.getIssue());
        assertEquals(RemarkMessageStore.CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, pr.getMessageCode());
        // check number of evidence elements and their value
        assertNull(pr.getElementList());


	//----------------------------------------------------------------------
        //---------------------------------3NMI-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-3NMI-05");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        pr = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, pr.getIssue());
        assertEquals(RemarkMessageStore.CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, pr.getMessageCode());
        // check number of evidence elements and their value
        assertNull(pr.getElementList());


	//----------------------------------------------------------------------
        //---------------------------------3NMI-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-3NMI-06");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        pr = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, pr.getIssue());
        assertEquals(RemarkMessageStore.CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, pr.getMessageCode());
        // check number of evidence elements and their value
        assertNull(pr.getElementList());


	//----------------------------------------------------------------------
        //---------------------------------3NMI-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-3NMI-07");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        pr = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, pr.getIssue());
        assertEquals(RemarkMessageStore.CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, pr.getMessageCode());
        // check number of evidence elements and their value
        assertNull(pr.getElementList());


	//----------------------------------------------------------------------
        //---------------------------------3NMI-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-3NMI-08");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        pr = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, pr.getIssue());
        assertEquals(RemarkMessageStore.CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, pr.getMessageCode());
        // check number of evidence elements and their value
        assertNull(pr.getElementList());


	//----------------------------------------------------------------------
        //---------------------------------3NMI-09------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-3NMI-09");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        pr = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, pr.getIssue());
        assertEquals(RemarkMessageStore.CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, pr.getMessageCode());
        // check number of evidence elements and their value
        assertNull(pr.getElementList());


	//----------------------------------------------------------------------
        //---------------------------------3NMI-10------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-3NMI-10");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        pr = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, pr.getIssue());
        assertEquals(RemarkMessageStore.CHECK_MANUALLY_OUTLINE_FOR_FORM_ELEMENT_AND_IFRAME_MSG, pr.getMessageCode());
        // check number of evidence elements and their value
        assertNull(pr.getElementList());


        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-4NA-01");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-4NA-02");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-4NA-03");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-4NA-04");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-4NA-05");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.07.01-4NA-06");
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
                consolidate("Rgaa30.Test.10.07.01-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.10.07.01-1Passed-02").getValue());

        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.07.01-2Failed-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.07.01-2Failed-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.07.01-2Failed-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.07.01-2Failed-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.07.01-2Failed-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.07.01-2Failed-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.07.01-2Failed-07").getValue());

        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.07.01-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.07.01-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.07.01-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.07.01-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.07.01-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.07.01-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.07.01-3NMI-07").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.07.01-3NMI-08").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.07.01-3NMI-09").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.10.07.01-3NMI-10").getValue());

        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.10.07.01-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.10.07.01-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.10.07.01-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.10.07.01-4NA-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.10.07.01-4NA-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.10.07.01-4NA-06").getValue());


    }

}
