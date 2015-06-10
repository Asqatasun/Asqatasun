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
import org.opens.tanaguru.entity.audit.*;
import org.opens.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.opens.tanaguru.rules.keystore.AttributeStore;
import org.opens.tanaguru.rules.keystore.EvidenceStore;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 8-8-1 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule080801Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule080801Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.rgaa30.Rgaa30Rule080801");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.08.08.01-1Passed-01");
        addWebResource("Rgaa30.Test.08.08.01-1Passed-02");
        addWebResource("Rgaa30.Test.08.08.01-1Passed-03");
        addWebResource("Rgaa30.Test.08.08.01-1Passed-04");
        addWebResource("Rgaa30.Test.08.08.01-2Failed-01");
        addWebResource("Rgaa30.Test.08.08.01-2Failed-02");
        addWebResource("Rgaa30.Test.08.08.01-2Failed-03");
        addWebResource("Rgaa30.Test.08.08.01-2Failed-04");
        addWebResource("Rgaa30.Test.08.08.01-2Failed-05");
        addWebResource("Rgaa30.Test.08.08.01-4NA-01");
        addWebResource("Rgaa30.Test.08.08.01-4NA-02");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //---------------------------1Passed-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.08.08.01-1Passed-01");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //---------------------------1Passed-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.08.01-1Passed-02");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //---------------------------1Passed-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.08.01-1Passed-03");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //---------------------------1Passed-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.08.01-1Passed-04");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.08.01-2Failed-01");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.MALFORMED_LANGUAGE_DECLARATION_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        Iterator<EvidenceElement> pIter = processRemark.getElementList().iterator();
        EvidenceElement ee = pIter.next();
        assertEquals("fren-FR",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.08.01-2Failed-02");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.WRONG_LANGUAGE_DECLARATION_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        pIter = processRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("aq",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.08.01-2Failed-03");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.MALFORMED_LANGUAGE_DECLARATION_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        pIter = processRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("french",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.08.01-2Failed-04");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.MALFORMED_LANGUAGE_DECLARATION_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        pIter = processRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("fr-F",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.08.01-2Failed-05");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.MALFORMED_LANGUAGE_DECLARATION_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.DIV_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        pIter = processRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("fr/FR",ee.getValue());
        assertEquals(EvidenceStore.LANGUAGE_EE, ee.getEvidence().getCode());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.08.01-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.08.01-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());

    }

}
