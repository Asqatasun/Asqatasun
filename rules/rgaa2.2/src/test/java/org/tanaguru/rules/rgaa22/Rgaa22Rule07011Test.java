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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.audit.*;
import org.tanaguru.rules.keystore.EvidenceStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 7.1 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule07011Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule07011Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa22.Rgaa22Rule07011");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("RGAA22.Test.7.1-1Passed-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule07011/RGAA22.Test.7.1-1Passed-01.html"));
        getWebResourceMap().put("RGAA22.Test.7.1-1Passed-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule07011/RGAA22.Test.7.1-1Passed-02.html"));
        getWebResourceMap().put("RGAA22.Test.7.1-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule07011/RGAA22.Test.7.1-3NMI-01.html"));
        getWebResourceMap().put("RGAA22.Test.7.1-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule07011/RGAA22.Test.7.1-3NMI-02.html"));
        getWebResourceMap().put("RGAA22.Test.7.1-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule07011/RGAA22.Test.7.1-4NA-01.html"));
        getWebResourceMap().put("RGAA22.Test.7.1-4NA-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule07011/RGAA22.Test.7.1-4NA-02.html"));
        
        setUpRelatedContentMap();
    }

    private void setUpRelatedContentMap(){
        List<String> relatedContent1 = new ArrayList<String>();
        relatedContent1.add("css/RGAA22.Test.7.1-1Passed-02.css");
        getRelatedContentMap().put(getWebResourceMap().get("RGAA22.Test.7.1-1Passed-02"), relatedContent1);
        
        List<String> relatedContent2 = new ArrayList<String>();
        relatedContent2.add("css/RGAA22.Test.7.1-3NMI-02.css");
        getRelatedContentMap().put(getWebResourceMap().get("RGAA22.Test.7.1-3NMI-02"), relatedContent2);
        
        List<String> relatedContent3 = new ArrayList<String>();
        relatedContent3.add("css/RGAA22.Test.7.1-4NA-02.css");
        getRelatedContentMap().put(getWebResourceMap().get("RGAA22.Test.7.1-4NA-02"), relatedContent3);
    }
    
    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("RGAA22.Test.7.1-1Passed-01");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.7.1-1Passed-02");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.7.1-3NMI-01");
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check test has no remark
        assertEquals(2, processResult.getRemarkSet().size());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        
        Iterator<ProcessRemark> iter = processResult.getRemarkSet().iterator();
        
        SourceCodeRemark processRemark = ((SourceCodeRemark)iter.next());
        assertEquals(RemarkMessageStore.CONTENT_CSS_PROPERTY_DETECTED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals("RGAA22",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        Iterator<EvidenceElement> eIter = processRemark.getElementList().iterator();
        EvidenceElement ee = eIter.next();
        assertEquals("h1:before",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = eIter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());
        
        processRemark = ((SourceCodeRemark)iter.next());
        assertEquals(RemarkMessageStore.CONTENT_CSS_PROPERTY_DETECTED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals("and needs to be manually checked",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        eIter = processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals(".test-explanation:after",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = eIter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.7.1-3NMI-02");
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check test has no remark
        assertEquals(2, processResult.getRemarkSet().size());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        
        iter = processResult.getRemarkSet().iterator();
        
        processRemark = ((SourceCodeRemark)iter.next());
        assertEquals(RemarkMessageStore.CONTENT_CSS_PROPERTY_DETECTED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals("RGAA22",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        eIter = processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("h1:before",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = eIter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "RGAA22.Test.7.1-3NMI-02.css"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());
        
        processRemark = ((SourceCodeRemark)iter.next());
        assertEquals(RemarkMessageStore.CONTENT_CSS_PROPERTY_DETECTED_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals("pseudo selectors.",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        eIter = processRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals(".test-explanation:after",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = eIter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "RGAA22.Test.7.1-3NMI-02.css"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-1-----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.7.1-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-2-----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.7.1-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.7.1-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.7.1-1Passed-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.7.1-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.7.1-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.7.1-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.7.1-4NA-02").getValue());
    }

}