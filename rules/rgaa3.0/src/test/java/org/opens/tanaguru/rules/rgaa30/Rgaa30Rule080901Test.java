/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
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
 * Unit test class for the implementation of the rule 08.09.01 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule080901Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa30Rule080901Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.opens.tanaguru.rules.rgaa30.Rgaa30Rule080901");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Rgaa30.Test.08.09.01-2Failed-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa30/Rgaa30Rule080901/Rgaa30.Test.08.09.01-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.08.09.01-2Failed-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa30/Rgaa30Rule080901/Rgaa30.Test.08.09.01-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.08.09.01-2Failed-03",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa30/Rgaa30Rule080901/Rgaa30.Test.08.09.01-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.08.09.01-2Failed-04",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa30/Rgaa30Rule080901/Rgaa30.Test.08.09.01-2Failed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.08.09.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule080901/Rgaa30.Test.08.09.01-3NMI-01.html"));
        getWebResourceMap().put("Rgaa30.Test.08.09.01-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule080901/Rgaa30.Test.08.09.01-3NMI-02.html"));
        getWebResourceMap().put("Rgaa30.Test.08.09.01-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule080901/Rgaa30.Test.08.09.01-3NMI-03.html"));
        getWebResourceMap().put("Rgaa30.Test.08.09.01-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule080901/Rgaa30.Test.08.09.01-3NMI-04.html"));
        getWebResourceMap().put("Rgaa30.Test.08.09.01-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule080901/Rgaa30.Test.08.09.01-3NMI-05.html"));
        getWebResourceMap().put("Rgaa30.Test.08.09.01-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule080901/Rgaa30.Test.08.09.01-3NMI-06.html"));
        getWebResourceMap().put("Rgaa30.Test.08.09.01-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule080901/Rgaa30.Test.08.09.01-3NMI-07.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //---------------------------2Failed-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.08.09.01-2Failed-01");
        // check number of elements in the page
        assertEquals(12, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.LINK_WITHOUT_TARGET_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        assertNull(sourceCodeRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.09.01-2Failed-02");
        // check number of elements in the page
        assertEquals(12, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.LINK_WITHOUT_TARGET_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        assertNull(sourceCodeRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.09.01-2Failed-03");
        // check number of elements in the page
        assertEquals(12, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.FIELDSET_NOT_WITHIN_FORM_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.FIELDSET_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        assertNull(sourceCodeRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.09.01-2Failed-04");
        // check number of elements in the page
        assertEquals(13, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        sourceCodeRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.FIELDSET_NOT_WITHIN_FORM_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.FIELDSET_ELEMENT, sourceCodeRemark.getTarget());
        assertNotNull(sourceCodeRemark.getSnippet());
        assertNull(sourceCodeRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.09.01-3NMI-01");
        // check number of elements in the page
        assertEquals(11, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        ProcessRemark processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG), processRemark.getMessageCode());
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-02------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.09.01-3NMI-02");
        // check number of elements in the page
        assertEquals(12, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG), processRemark.getMessageCode());
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-03------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.09.01-3NMI-03");
        // check number of elements in the page
        assertEquals(12, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG), processRemark.getMessageCode());
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-04------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.09.01-3NMI-04");
        // check number of elements in the page
        assertEquals(12, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG), processRemark.getMessageCode());
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-05------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.09.01-3NMI-05");
        // check number of elements in the page
        assertEquals(13, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG), processRemark.getMessageCode());
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-06------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.09.01-3NMI-06");
        // check number of elements in the page
        assertEquals(13, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG), processRemark.getMessageCode());
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-07------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.09.01-3NMI-07");
        // check number of elements in the page
        assertEquals(13, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = processResult.getRemarkSet().iterator().next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG), processRemark.getMessageCode());
        assertNull(processRemark.getElementList());
        

    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.08.09.01-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.08.09.01-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.08.09.01-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.08.09.01-2Failed-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.08.09.01-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.08.09.01-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.08.09.01-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.08.09.01-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.08.09.01-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.08.09.01-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.08.09.01-3NMI-07").getValue());
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
