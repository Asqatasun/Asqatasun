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
import org.tanaguru.entity.audit.*;
import org.tanaguru.rules.keystore.AttributeStore;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 6.3 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule06031Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule06031Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa22.Rgaa22Rule06031");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("RGAA22.Test.6.3-3NMI-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule06031/RGAA22.Test.6.3-3NMI-01.html"));
        getWebResourceMap().put("RGAA22.Test.6.3-3NMI-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule06031/RGAA22.Test.6.3-3NMI-02.html"));
        getWebResourceMap().put("RGAA22.Test.6.3-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule06031/RGAA22.Test.6.3-4NA-01.html"));
        getWebResourceMap().put("RGAA22.Test.6.3-4NA-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule06031/RGAA22.Test.6.3-4NA-02.html"));
        getWebResourceMap().put("RGAA22.Test.6.3-4NA-03",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule06031/RGAA22.Test.6.3-4NA-03.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01----------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("RGAA22.Test.6.3-3NMI-01");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        Iterator<ProcessRemark> iter = processResult.getRemarkSet().iterator();
        
        SourceCodeRemark sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        Iterator<EvidenceElement> eIter = sourceCodeRemark.getElementList().iterator();
        EvidenceElement ee = eIter.next();
        assertEquals("area4", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("area5", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("New page", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.3-3NMI-02");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("My image link 1", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("My image link 2", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("New page", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.3-4NA-01");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.3-4NA-02");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.3-4NA-03");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.3-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.3-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.3-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.3-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.3-4NA-03").getValue());
    }

}