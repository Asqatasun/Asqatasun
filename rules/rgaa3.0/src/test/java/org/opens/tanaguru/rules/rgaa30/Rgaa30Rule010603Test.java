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
import static org.opens.tanaguru.rules.keystore.AttributeStore.SRC_ATTR;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 1-6-3 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule010603Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule010603Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.rgaa30.Rgaa30Rule010603");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.01.06.03-3NMI-01");
        addWebResource("Rgaa30.Test.01.06.03-3NMI-02",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "id-informative-embed"));
        addWebResource("Rgaa30.Test.01.06.03-3NMI-03",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "id-informative-embed"),
                    createParameter("Rules", "DECORATIVE_IMAGE_MARKER", "class-decorative-embed"));
        addWebResource("Rgaa30.Test.01.06.03-4NA-01");
        addWebResource("Rgaa30.Test.01.06.03-4NA-02");
        addWebResource("Rgaa30.Test.01.06.03-4NA-03");
        addWebResource("Rgaa30.Test.01.06.03-4NA-04",
                    createParameter("Rules", "DECORATIVE_IMAGE_MARKER", "class-decorative-embed"));

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //-------------------------------3NMI-01--------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.01.06.03-3NMI-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_DETAILED_DESC_AVAILABILITY_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.EMBED_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        Iterator<EvidenceElement> pIter = processRemark.getElementList().iterator();
        EvidenceElement ee = pIter.next();
        assertEquals("",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-embed-object.png"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //-------------------------------3NMI-02--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.06.03-3NMI-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_DETAILED_DESC_DEFINITION_OF_INFORMATIVE_IMG_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.EMBED_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        pIter = processRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-embed-object.png"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //-------------------------------3NMI-03--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.06.03-3NMI-03");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        Iterator<ProcessRemark> iter = processResult.getRemarkSet().iterator();
        
        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_DETAILED_DESC_DEFINITION_OF_INFORMATIVE_IMG_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.EMBED_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        pIter = processRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-embed-object2.png"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_DETAILED_DESC_AVAILABILITY_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.EMBED_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2, processRemark.getElementList().size());
        pIter = processRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = pIter.next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock-embed-object1.png"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
 

        //----------------------------------------------------------------------
        //------------------------------4NA-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.06.03-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.06.03-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.06.03-4NA-03");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.06.03-4NA-04");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());

    }

}
