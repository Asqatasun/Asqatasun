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

import java.util.Iterator;
import java.util.LinkedHashSet;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.audit.*;
import static org.tanaguru.rules.keystore.AttributeStore.*;
import org.tanaguru.rules.keystore.HtmlElementStore;
import static org.tanaguru.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 4.5 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule04051Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule04051Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa22.Rgaa22Rule04051");
    }
    
    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("RGAA22.Test.4.5-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-1Passed-01.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-1Passed-01", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image"));
        getWebResourceMap().put("RGAA22.Test.4.5-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-1Passed-02.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-1Passed-02", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "class-decorative-image"));
        getWebResourceMap().put("RGAA22.Test.4.5-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-1Passed-03.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-1Passed-03", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "role-decorative-image"));
        getWebResourceMap().put("RGAA22.Test.4.5-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-1Passed-04.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-1Passed-04", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image; class-decorative-image; role-decorative-image"));
        getWebResourceMap().put("RGAA22.Test.4.5-1Passed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-1Passed-05.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-1Passed-05", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image"));
        addParameterToParameterMap("RGAA22.Test.4.5-1Passed-05", createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "class-informative-image"));
        getWebResourceMap().put("RGAA22.Test.4.5-1Passed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-1Passed-06.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-1Passed-06", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-applet"));
        getWebResourceMap().put("RGAA22.Test.4.5-1Passed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-1Passed-07.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-1Passed-07", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "class-decorative-applet"));
        getWebResourceMap().put("RGAA22.Test.4.5-1Passed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-1Passed-08.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-1Passed-08", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "role-decorative-applet"));
        getWebResourceMap().put("RGAA22.Test.4.5-1Passed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-1Passed-09.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-1Passed-09", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-applet; class-decorative-applet; role-decorative-applet"));
        getWebResourceMap().put("RGAA22.Test.4.5-1Passed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-1Passed-10.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-1Passed-10", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-applet"));
        addParameterToParameterMap("RGAA22.Test.4.5-1Passed-10", createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "class-informative-applet"));

        getWebResourceMap().put("RGAA22.Test.4.5-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-2Failed-01.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-2Failed-01", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image"));
        getWebResourceMap().put("RGAA22.Test.4.5-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-2Failed-02.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-2Failed-02", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "class-decorative-image"));
        getWebResourceMap().put("RGAA22.Test.4.5-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-2Failed-03.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-2Failed-03", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "role-decorative-image"));
        getWebResourceMap().put("RGAA22.Test.4.5-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-2Failed-04.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-2Failed-04", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image; class-decorative-image; role-decorative-image"));
        getWebResourceMap().put("RGAA22.Test.4.5-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-2Failed-05.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-2Failed-05", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "class-decorative-image"));
        addParameterToParameterMap("RGAA22.Test.4.5-2Failed-05", createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "role-informative-image"));
        
        getWebResourceMap().put("RGAA22.Test.4.5-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-2Failed-06.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-2Failed-06", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-applet"));
        
        getWebResourceMap().put("RGAA22.Test.4.5-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-2Failed-07.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-2Failed-07", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "class-decorative-applet"));
        getWebResourceMap().put("RGAA22.Test.4.5-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-2Failed-08.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-2Failed-08", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "role-decorative-applet"));
        getWebResourceMap().put("RGAA22.Test.4.5-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-2Failed-09.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-2Failed-09", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-applet; class-decorative-applet; role-decorative-applet"));
        getWebResourceMap().put("RGAA22.Test.4.5-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-2Failed-10.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-2Failed-10", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-applet"));
        addParameterToParameterMap("RGAA22.Test.4.5-2Failed-10", createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "class-informative-applet"));
        
        getWebResourceMap().put("RGAA22.Test.4.5-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-3NMI-01.html"));
        getWebResourceMap().put("RGAA22.Test.4.5-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-3NMI-02.html"));
        getWebResourceMap().put("RGAA22.Test.4.5-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-3NMI-03.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-3NMI-03", createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "role-informative-image"));
        getWebResourceMap().put("RGAA22.Test.4.5-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-3NMI-04.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-3NMI-04", createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "role-informative-image"));
        getWebResourceMap().put("RGAA22.Test.4.5-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-3NMI-05.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-3NMI-05", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image"));
        getWebResourceMap().put("RGAA22.Test.4.5-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-3NMI-06.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-3NMI-06", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "class-decorative-image"));
        getWebResourceMap().put("RGAA22.Test.4.5-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-3NMI-07.html"));
        getWebResourceMap().put("RGAA22.Test.4.5-3NMI-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-3NMI-08.html"));
        getWebResourceMap().put("RGAA22.Test.4.5-3NMI-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-3NMI-09.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-3NMI-09", createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "class-informative-applet"));
        getWebResourceMap().put("RGAA22.Test.4.5-3NMI-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-3NMI-10.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-3NMI-10", createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "class-informative-applet"));
        getWebResourceMap().put("RGAA22.Test.4.5-3NMI-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-3NMI-11.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-3NMI-11", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-applet"));
        getWebResourceMap().put("RGAA22.Test.4.5-3NMI-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-3NMI-12.html"));
        addParameterToParameterMap("RGAA22.Test.4.5-3NMI-12", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "class-decorative-applet"));
        
        getWebResourceMap().put("RGAA22.Test.4.5-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-4NA-01.html"));
        getWebResourceMap().put("RGAA22.Test.4.5-4NA-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-4NA-02.html"));
        getWebResourceMap().put("RGAA22.Test.4.5-4NA-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-4NA-03.html"));
        getWebResourceMap().put("RGAA22.Test.4.5-4NA-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-4NA-04.html"));
        getWebResourceMap().put("RGAA22.Test.4.5-4NA-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-4NA-05.html"));
        getWebResourceMap().put("RGAA22.Test.4.5-4NA-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-4NA-06.html"));
        getWebResourceMap().put("RGAA22.Test.4.5-4NA-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-4NA-07.html"));
        getWebResourceMap().put("RGAA22.Test.4.5-4NA-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-4NA-08.html"));
        getWebResourceMap().put("RGAA22.Test.4.5-4NA-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-4NA-09.html"));
        getWebResourceMap().put("RGAA22.Test.4.5-4NA-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule04051/RGAA22.Test.4.5-4NA-10.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("RGAA22.Test.4.5-1Passed-01");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-1Passed-02");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-1Passed-03");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-1Passed-04");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(3, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-1Passed-05");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-1Passed-06");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-1Passed-07");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-1Passed-08");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-09------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-1Passed-09");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(3, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-10------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-1Passed-10");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-2Failed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.IMG_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        Iterator<EvidenceElement> iter = processRemark.getElementList().iterator();
        EvidenceElement ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "Not empty alt"));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-decorative-image.jpg"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-2Failed-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.IMG_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "Not empty alt"));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-decorative-image.jpg"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-2Failed-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.IMG_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "Not empty alt"));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-decorative-image.jpg"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-2Failed-04");
        // check number of elements in the page
        assertEquals(3, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(3, processResult.getRemarkSet().size());
        Iterator<ProcessRemark> pIter = processResult.getRemarkSet().iterator();
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.IMG_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "Not empty alt"));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-decorative-image1.jpg"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.IMG_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "Not empty alt"));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-decorative-image2.jpg"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.IMG_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "Not empty alt"));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-decorative-image3.jpg"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-2Failed-05");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.IMG_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "Not empty alt"));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-decorative-image.jpg"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-2Failed-06");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.APPLET_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "Not empty alt"));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("mock.class", ee.getValue());
        assertEquals(CODE_ATTR, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-2Failed-07");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.APPLET_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "Not empty alt"));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("mock.class", ee.getValue());
        assertEquals(CODE_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-2Failed-08");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.APPLET_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "Not empty alt"));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("mock.class", ee.getValue());
        assertEquals(CODE_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-09------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-2Failed-09");
        // check number of elements in the page
        assertEquals(3, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(3, processResult.getRemarkSet().size());
        pIter = processResult.getRemarkSet().iterator();
        
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.APPLET_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "Not empty alt"));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("mock1.class", ee.getValue());
        assertEquals(CODE_ATTR, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.APPLET_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "Not empty alt"));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("mock2.class", ee.getValue());
        assertEquals(CODE_ATTR, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.APPLET_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "Not empty alt"));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("mock3.class", ee.getValue());
        assertEquals(CODE_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-10------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-2Failed-10");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.APPLET_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "Not empty alt"));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("mock1.class", ee.getValue());
        assertEquals(CODE_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-3NMI-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.IMG_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), ""));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image.jpg"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-3NMI-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_ELEMENT_WITH_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.IMG_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), ""));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image.jpg"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-3NMI-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.IMG_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), ""));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image.jpg"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-3NMI-04");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_ELEMENT_WITH_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.IMG_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), ""));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image.jpg"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-3NMI-05");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.IMG_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), ""));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image.jpg"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-3NMI-06");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_ELEMENT_WITH_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.IMG_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), ""));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-image.jpg"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-07---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-3NMI-07");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.APPLET_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), ""));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("mock.class", ee.getValue());
        assertEquals(CODE_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-08---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-3NMI-08");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_ELEMENT_WITH_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.APPLET_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), ""));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("mock.class", ee.getValue());
        assertEquals(CODE_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-09---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-3NMI-09");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.APPLET_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), ""));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("mock1.class", ee.getValue());
        assertEquals(CODE_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-10---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-3NMI-10");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_ELEMENT_WITH_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.APPLET_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), ""));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("mock1.class", ee.getValue());
        assertEquals(CODE_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-11---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-3NMI-11");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.APPLET_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), ""));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("mock1.class", ee.getValue());
        assertEquals(CODE_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-12---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-3NMI-12");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_ELEMENT_WITH_EMPTY_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.APPLET_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), ""));
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertEquals("mock1.class", ee.getValue());
        assertEquals(CODE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-4NA-03");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-4NA-04");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-05----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-4NA-05");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-06----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-4NA-06");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-07----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-4NA-07");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-08----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-4NA-08");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-09----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-4NA-09");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-10----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.4.5-4NA-10");
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
                consolidate("RGAA22.Test.4.5-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.4.5-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.4.5-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.4.5-1Passed-04").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.4.5-1Passed-05").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.4.5-1Passed-06").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.4.5-1Passed-07").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.4.5-1Passed-08").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.4.5-1Passed-09").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.4.5-1Passed-10").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.4.5-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.4.5-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.4.5-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.4.5-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.4.5-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.4.5-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.4.5-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.4.5-2Failed-08").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.4.5-2Failed-09").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.4.5-2Failed-10").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.4.5-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.4.5-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.4.5-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.4.5-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.4.5-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.4.5-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.4.5-3NMI-07").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.4.5-3NMI-08").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.4.5-3NMI-09").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.4.5-3NMI-10").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.4.5-3NMI-11").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.4.5-3NMI-12").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.4.5-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.4.5-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.4.5-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.4.5-4NA-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.4.5-4NA-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.4.5-4NA-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.4.5-4NA-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.4.5-4NA-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.4.5-4NA-09").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.4.5-4NA-10").getValue());
    }

}