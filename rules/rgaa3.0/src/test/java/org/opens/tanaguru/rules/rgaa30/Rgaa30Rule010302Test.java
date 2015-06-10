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

import java.util.Iterator;
import java.util.LinkedHashSet;
import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.entity.audit.*;
import org.opens.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import static org.opens.tanaguru.rules.keystore.AttributeStore.ALT_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.HREF_ATTR;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 1-3-2 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule010302Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule010302Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.rgaa30.Rgaa30Rule010302");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.01.03.02-2Failed-01",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-area"));
        addWebResource("Rgaa30.Test.01.03.02-2Failed-02",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-area"));
        addWebResource("Rgaa30.Test.01.03.02-2Failed-03",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-area"));
        addWebResource("Rgaa30.Test.01.03.02-2Failed-04",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-area"));
        addWebResource("Rgaa30.Test.01.03.02-2Failed-05",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-area"),
                    createParameter("Rules", "DECORATIVE_IMAGE_MARKER", "class-decorative-area"));
        addWebResource("Rgaa30.Test.01.03.02-3NMI-01");
        addWebResource("Rgaa30.Test.01.03.02-3NMI-02");
        addWebResource("Rgaa30.Test.01.03.02-3NMI-03");
        addWebResource("Rgaa30.Test.01.03.02-3NMI-04",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-area"));
        addWebResource("Rgaa30.Test.01.03.02-3NMI-05");
        addWebResource("Rgaa30.Test.01.03.02-3NMI-06",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-area"),
                    createParameter("Rules", "DECORATIVE_IMAGE_MARKER", "class-decorative-area"));
        addWebResource("Rgaa30.Test.01.03.02-4NA-01");
        addWebResource("Rgaa30.Test.01.03.02-4NA-02");
        addWebResource("Rgaa30.Test.01.03.02-4NA-03");
        addWebResource("Rgaa30.Test.01.03.02-4NA-04");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.01.03.02-2Failed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.AREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        Iterator<EvidenceElement> iter = processRemark.getElementList().iterator();
        EvidenceElement ee = iter.next();
        assertEquals("mock-area.html", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-area.html"));
        assertEquals(HREF_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-2Failed-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.AREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("mock-image.jpeg", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-area.html"));
        assertEquals(HREF_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-2Failed-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.AREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("--><--", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-area.html"));
        assertEquals(HREF_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-2Failed-04");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.AREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-area.html"));
        assertEquals(HREF_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-2Failed-05");
        // check number of elements in the page
        assertEquals(4, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(4, processResult.getRemarkSet().size());
        Iterator<ProcessRemark> pIter = processResult.getRemarkSet().iterator();
        
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(HtmlElementStore.AREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-area1.html"));
        assertEquals(HREF_ATTR, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.AREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("Informative area alternative", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-area2.html"));
        assertEquals(HREF_ATTR, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.AREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("mock-area3.html", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-area3.html"));
        assertEquals(HREF_ATTR, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_ALT_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.AREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("not identified area alternative", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-area4.html"));
        assertEquals(HREF_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-3NMI-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.AREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("mock-area.html", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-area.html"));
        assertEquals(HREF_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-3NMI-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.AREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("mock-image.jpeg", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-area.html"));
        assertEquals(HREF_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-3NMI-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.AREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("#!/;'(|", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-area.html"));
        assertEquals(HREF_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-3NMI-04");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.AREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("Informative area alternative", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-area.html"));
        assertEquals(HREF_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-3NMI-05");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_ALT_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.AREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("Not identified area alternative", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-area.html"));
        assertEquals(HREF_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-3NMI-06");
        // check number of elements in the page
        assertEquals(3, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(3, processResult.getRemarkSet().size());
        pIter = processResult.getRemarkSet().iterator();
        
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.AREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("Informative area alternative", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-area2.html"));
        assertEquals(HREF_ATTR, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.AREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("mock-area3.html", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-area3.html"));
        assertEquals(HREF_ATTR, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)pIter.next();
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_ALT_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.AREA_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        iter = processRemark.getElementList().iterator();
        ee = iter.next();
        assertEquals("not identified area alternative", ee.getValue());
        assertEquals(ALT_ATTR, ee.getEvidence().getCode());
        ee = iter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-area4.html"));
        assertEquals(HREF_ATTR, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-4NA-03");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-4NA-03");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-4NA-04");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());

    }

}
