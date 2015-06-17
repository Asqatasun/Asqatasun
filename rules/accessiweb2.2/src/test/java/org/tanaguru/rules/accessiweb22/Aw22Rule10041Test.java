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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.audit.EvidenceElement;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.accessiweb22.test.Aw22RuleImplementationTestCase;
import org.tanaguru.rules.keystore.EvidenceStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 *
 * @author jkowalczyk
 */
public class Aw22Rule10041Test extends Aw22RuleImplementationTestCase {

    public Aw22Rule10041Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName( "org.tanaguru.rules.accessiweb22.Aw22Rule10041");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW22.Test.10.4.1-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-4NA-01.html"));
        
        getWebResourceMap().put("AW22.Test.10.4.1-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-1Passed-01.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-1Passed-02.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-1Passed-03.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-1Passed-04.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-1Passed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-1Passed-05.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-1Passed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-1Passed-06.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-1Passed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-1Passed-07.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-1Passed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-1Passed-08.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-1Passed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-1Passed-09.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-1Passed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-1Passed-10.html"));

        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-01_1",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-01_1.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-01_2",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-01_2.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-01_3",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-01_3.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-01_4",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-01_4.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-01_5",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-01_5.html"));

        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-02_1",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-02_1.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-02_2",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-02_2.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-02_3",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-02_3.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-02_4",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-02_4.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-02_5",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-02_5.html"));

        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-03_1",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-03_1.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-03_2",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-03_2.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-03_3",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-03_3.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-03_4",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-03_4.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-03_5",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-03_5.html"));

        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-04_1",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-04_1.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-04_2",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-04_2.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-04_3",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-04_3.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-04_4",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-04_4.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-04_5",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-04_5.html"));

        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-05_1",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-05_1.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-05_2",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-05_2.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-05_3",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-05_3.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-05_4",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-05_4.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-05_5",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-05_5.html"));
        
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-06_1",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-06_1.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-06_2",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-06_2.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-06_3",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-06_3.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-06_4",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-06_4.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-06_5",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-06_5.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-06_6",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-06_6.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-06_7",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-06_7.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-06_8",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-06_8.html"));

        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-07_1",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-07_1.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-07_2",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-07_2.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-07_3",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-07_3.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-07_4",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-07_4.html"));
        getWebResourceMap().put("AW22.Test.10.4.1-2Failed-07_5",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-2Failed-07_5.html"));
        
        getWebResourceMap().put("AW22.Test.10.4.1-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule10041/AW22.Test.10.4.1-3NMI-01.html"));

        setUpRelatedContentMap();
    }

    private void setUpRelatedContentMap(){
        List<String> relatedContent1 = new ArrayList<String>();
        relatedContent1.add("css/AW22.Test.10.4.1-2Failed-01_1.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW22.Test.10.4.1-2Failed-01_1"), relatedContent1);

        List<String> relatedContent2 = new ArrayList<String>();
        relatedContent2.add("css/AW22.Test.10.4.1-2Failed-01_2.css");
        relatedContent2.add("css/AW22.Test.10.4.1-2Failed-01_2_1.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW22.Test.10.4.1-2Failed-01_2"), relatedContent2);

        List<String> relatedContent3 = new ArrayList<String>();
        relatedContent3.add("css/AW22.Test.10.4.1-2Failed-01_3.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW22.Test.10.4.1-2Failed-01_3"), relatedContent3);
        
        List<String> relatedContent4 = new ArrayList<String>();
        relatedContent4.add("css/AW22.Test.10.4.1-2Failed-06_1.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW22.Test.10.4.1-2Failed-06_1"), relatedContent4);
        
        List<String> relatedContent5 = new ArrayList<String>();
        relatedContent5.add("css/AW22.Test.10.4.1-2Failed-06_2.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW22.Test.10.4.1-2Failed-06_2"), relatedContent5);
        
        List<String> relatedContent6 = new ArrayList<String>();
        relatedContent6.add("css/AW22.Test.10.4.1-2Failed-06_3.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW22.Test.10.4.1-2Failed-06_3"), relatedContent6);
        
        List<String> relatedContent7 = new ArrayList<String>();
        relatedContent7.add("css/AW22.Test.10.4.1-2Failed-06_4.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW22.Test.10.4.1-2Failed-06_4"), relatedContent7);
        
        List<String> relatedContent8 = new ArrayList<String>();
        relatedContent8.add("css/AW22.Test.10.4.1-2Failed-06_5.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW22.Test.10.4.1-2Failed-06_5"), relatedContent8);
        
        List<String> relatedContent9 = new ArrayList<String>();
        relatedContent9.add("css/AW22.Test.10.4.1-2Failed-06_6.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW22.Test.10.4.1-2Failed-06_6"), relatedContent9);
        
        List<String> relatedContent10 = new ArrayList<String>();
        relatedContent10.add("css/AW22.Test.10.4.1-2Failed-06_7.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW22.Test.10.4.1-2Failed-06_7"), relatedContent10);
        
        List<String> relatedContent11 = new ArrayList<String>();
        relatedContent11.add("css/AW22.Test.10.4.1-2Failed-06_8.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW22.Test.10.4.1-2Failed-06_8"), relatedContent11);
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("AW22.Test.10.4.1-1Passed-01");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-1Passed-02");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-1Passed-03");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-1Passed-04");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-1Passed-05");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-1Passed-06");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-1Passed-07");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-1Passed-08");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-09------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-1Passed-09");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-10------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-1Passed-10");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01_1----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-01_1");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pt",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        Iterator<EvidenceElement> iter = processRemark.getElementList().iterator();
        EvidenceElement ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "AW22.Test.10.4.1-2Failed-01_1.css"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01_2----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-01_2");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pt",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "AW22.Test.10.4.1-2Failed-01_2_1.css"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01_3----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-01_3");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pt",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "AW22.Test.10.4.1-2Failed-01_3.css"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-01_4----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-01_4");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pt",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-01_5----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-01_5");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pt",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-02_1----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-02_1");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pc",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-02_2----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-02_2");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pc",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-02_3----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-02_3");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pc",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-02_4----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-02_4");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pc",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-02_5----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-02_5");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pc",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-03_1----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-03_1");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("mm",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-03_2----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-03_2");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("mm",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-03_3----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-03_3");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("mm",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-03_4----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-03_4");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("mm",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-03_5----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-03_5");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("mm",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-04_1----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-04_1");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("cm",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-04_2----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-04_2");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("cm",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-04_3----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-04_3");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("cm",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-04_4----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-04_4");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("cm",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-04_5----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-04_5");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("cm",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-05_1----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-05_1");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("in",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-05_2----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-05_2");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("in",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-05_3----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-05_3");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("in",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-05_4----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-05_4");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("in",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-05_5----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-05_5");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("in",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "locale"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-06_1----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-06_1");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pt",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "AW22.Test.10.4.1-2Failed-06_1.css"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-06_2----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-06_2");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pt",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "AW22.Test.10.4.1-2Failed-06_2.css"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-06_3----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-06_3");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pt",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "AW22.Test.10.4.1-2Failed-06_3.css"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-06_4----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-06_4");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pt",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "AW22.Test.10.4.1-2Failed-06_4.css"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-06_5----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-06_5");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pt",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "AW22.Test.10.4.1-2Failed-06_5.css"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-06_6----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-06_6");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pt",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "AW22.Test.10.4.1-2Failed-06_6.css"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-06_7----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-06_7");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pt",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "AW22.Test.10.4.1-2Failed-06_7.css"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-06_8----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-06_8");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(4, processResult.getRemarkSet().size());
        Iterator<SourceCodeRemark> pIter = ((LinkedHashSet)processResult.getRemarkSet()).iterator();
        processRemark = pIter.next();
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("in",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "AW22.Test.10.4.1-2Failed-06_8.css"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());
        processRemark = pIter.next();
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("mm",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "AW22.Test.10.4.1-2Failed-06_8.css"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());
        processRemark = pIter.next();
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("cm",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "AW22.Test.10.4.1-2Failed-06_8.css"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());
        processRemark = pIter.next();
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pt",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("h1",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "AW22.Test.10.4.1-2Failed-06_8.css"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-07_1----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-07_1");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("in",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("div",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "inline"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-07_2----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-07_2");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pt",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("div",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "inline"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-07_3----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-07_3");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("mm",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("div",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "inline"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-07_4----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-07_4");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("cm",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("div",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "inline"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-07_5----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-2Failed-07_5");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_UNIT_TYPE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals("pc",processRemark.getTarget());
        assertNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("div",ee.getValue());
        assertEquals(EvidenceStore.CSS_SELECTOR_EE,ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "inline"));
        assertEquals(EvidenceStore.CSS_FILENAME_EE, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------4NA-1-----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.10.4.1-4NA-01");
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
                consolidate("AW22.Test.10.4.1-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.10.4.1-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.10.4.1-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.10.4.1-1Passed-04").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.10.4.1-1Passed-05").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.10.4.1-1Passed-06").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.10.4.1-1Passed-07").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.10.4.1-1Passed-08").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.10.4.1-1Passed-09").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.10.4.1-1Passed-10").getValue());
        
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-01_1").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-01_2").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-01_3").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-01_4").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-01_5").getValue());

        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-02_1").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-02_2").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-02_3").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-02_4").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-02_5").getValue());

        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-03_1").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-03_2").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-03_3").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-03_4").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-03_5").getValue());

        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-04_1").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-04_2").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-04_3").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-04_4").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-04_5").getValue());

        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-05_1").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-05_2").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-05_3").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-05_4").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-05_5").getValue());

        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-06_1").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-06_2").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-06_3").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-06_4").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-06_5").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-06_6").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-06_7").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-06_8").getValue());

        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-07_1").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-07_2").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-07_3").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-07_4").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.10.4.1-2Failed-07_5").getValue());
        

        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.10.4.1-4NA-01").getValue());
    }

}
