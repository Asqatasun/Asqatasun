/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.rules.rgaa30;

import java.util.Iterator;
import java.util.LinkedHashSet;
import static junit.framework.Assert.*;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;

/**
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule080601Test extends Rgaa30RuleImplementationTestCase {

    public Rgaa30Rule080601Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.opens.tanaguru.rules.rgaa30.Rgaa30Rule080601");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Rgaa30.Test.08.06.01-2Failed-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa30/Rgaa30Rule080601/Rgaa30.Test.08.06.01-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.08.06.01-2Failed-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa30/Rgaa30Rule080601/Rgaa30.Test.08.06.01-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.08.06.01-2Failed-03",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa30/Rgaa30Rule080601/Rgaa30.Test.08.06.01-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.08.06.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule080601/Rgaa30.Test.08.06.01-3NMI-01.html"));
        getWebResourceMap().put("Rgaa30.Test.08.06.01-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa30/Rgaa30Rule080601/Rgaa30.Test.08.06.01-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.08.06.01-2Failed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.NOT_PERTINENT_TITLE_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.TITLE_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        Iterator<EvidenceElement> pIter = processRemark.getElementList().iterator();
        EvidenceElement ee = pIter.next();
        assertEquals("Page title",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.06.01-2Failed-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.NOT_PERTINENT_TITLE_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.TITLE_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        pIter = processRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.06.01-2Failed-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.NOT_PERTINENT_TITLE_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.TITLE_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        pIter = processRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("Bienvenue dans Adobe GoLive 6",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.06.01-3NMI-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_TITLE_PERTINENCE_MSG, processRemark.getMessageCode());
        assertEquals(HtmlElementStore.TITLE_ELEMENT, processRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        pIter = processRemark.getElementList().iterator();
        ee = pIter.next();
        assertEquals("Rgaa30 Test.08.06.01 NMI 01",ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.06.01-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.08.06.01-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.08.06.01-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.08.06.01-2Failed-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.08.06.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.08.06.01-4NA-01").getValue());
    }

}
