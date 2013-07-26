/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
package org.opens.tanaguru.rules.rgaa22;

import org.opens.tanaguru.entity.audit.*;
import org.opens.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 4.2 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule04021Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule04021Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.opens.tanaguru.rules.rgaa22.Rgaa22Rule04021");
    }

    @Override
    protected void setUpWebResourceMap() {
//        getWebResourceMap().put("Rgaa22.Test.4.2-2Failed-01",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04021/RGAA22.Test.4.2-2Failed-01.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.2-2Failed-02",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04021/RGAA22.Test.4.2-2Failed-02.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.2-2Failed-03",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04021/RGAA22.Test.4.2-2Failed-03.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.2-2Failed-04",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04021/RGAA22.Test.4.2-2Failed-04.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.2-3NMI-01",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04021/RGAA22.Test.4.2-3NMI-01.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.2-3NMI-02",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04021/RGAA22.Test.4.2-3NMI-02.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.2-3NMI-03",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04021/RGAA22.Test.4.2-3NMI-03.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.2-3NMI-04",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04021/RGAA22.Test.4.2-3NMI-04.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.2-4NA-01",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04021/RGAA22.Test.4.2-4NA-01.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.2-4NA-02",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04021/RGAA22.Test.4.2-4NA-02.html"));
        getWebResourceMap().put("Rgaa22.Test.4.2-5NT-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04031/RGAA22.Test.4.3-5NT-01.html"));
    }

    @Override
    protected void setProcess() {
//        //----------------------------------------------------------------------
//        //------------------------------2Failed-02------------------------------
//        //----------------------------------------------------------------------
//        ProcessResult processResult = processPageTest("Rgaa22.Test.3.10-2Failed-02");
//        // check number of elements in the page
//        assertEquals(3, processResult.getElementCounter());
//        // check test result
//        assertEquals(TestSolution.FAILED, processResult.getValue());
//        // check number of remarks and their value
//        assertEquals(3, processResult.getRemarkSet().size());
//        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
//        assertEquals(NOT_PERTINENT_LINK_TEXT_MSG, processRemark.getMessageCode());
//        assertEquals(TestSolution.FAILED, processRemark.getIssue());
//        assertEquals(HtmlElementKeyStore.A_ELEMENT, processRemark.getTarget());
//        assertNotNull(processRemark.getSnippet());
//        // check number of evidence elements and their value
//        assertEquals(1, processRemark.getElementList().size());
//        EvidenceElement ee = processRemark.getElementList().iterator().next();
//        assertEquals("",ee.getValue()); // extracted link text
//        assertEquals(HtmlElementKeyStore.TEXT_ELEMENT2,ee.getEvidence().getCode()); // extracted link text
//        
//        //--------------------------------------------------------------------//
//        ProcessResult processResult = processPageTest("Rgaa22.Test.4.2-2Failed-01");
//        assertEquals(TestSolution.FAILED, processResult.getValue());
//        assertEquals(3,processResult.getElementCounter());
//        assertEquals(3,processResult.getRemarkSet().size());
//        Iterator<ProcessRemark> iter = processResult.getRemarkSet().iterator();
//        ProcessRemark pr = iter.next();
//        assertEquals(Rgaa22Rule04021.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        Iterator<EvidenceElement> iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("a",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("",iterEe.next().getValue()); // extracted link text
//        
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04021.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("a",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("!<<-(-_",iterEe.next().getValue()); // extracted link text
//        
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04021.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("a",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("-- en savoir plus -->",iterEe.next().getValue()); // extracted link text
//        
//        //--------------------------------------------------------------------//        
//        processResult = processPageTest("Rgaa22.Test.4.2-2Failed-02");
//        assertEquals(TestSolution.FAILED, processResult.getValue());
//        assertEquals(3,processResult.getElementCounter());
//        assertEquals(3,processResult.getRemarkSet().size());
//        iter = processResult.getRemarkSet().iterator();
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04021.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());// Iterate through evidence element collection
//        iterEe = pr.getElementList().iterator();
//        assertEquals("button",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("",iterEe.next().getValue()); // extracted link text
//                
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04021.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("button",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("!<<-(-_",iterEe.next().getValue()); // extracted link text
//        
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04021.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("button",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("En savoir plus",iterEe.next().getValue()); // extracted link text
//        
//        //--------------------------------------------------------------------//        
//        processResult = processPageTest("Rgaa22.Test.4.2-2Failed-03");
//        assertEquals(TestSolution.FAILED, processResult.getValue());
//        assertEquals(4,processResult.getElementCounter());
//        assertEquals(4,processResult.getRemarkSet().size());
//        iter = processResult.getRemarkSet().iterator();
//        
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04021.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("a",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("",iterEe.next().getValue()); // extracted link text
//        
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04021.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("a",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("*-+''{} !<<-(-_ +",iterEe.next().getValue()); // extracted link text
//        
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04021.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("a",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("En savoir plus",iterEe.next().getValue()); // extracted link text
//        
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04021.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("a",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("En savoir plus",iterEe.next().getValue()); // extracted link text
//
//        //--------------------------------------------------------------------//      
//        processResult = processPageTest("Rgaa22.Test.4.2-2Failed-04");
//        assertEquals(TestSolution.FAILED, processResult.getValue());
//        assertEquals(4,processResult.getElementCounter());
//        assertEquals(4,processResult.getRemarkSet().size());
//        iter = processResult.getRemarkSet().iterator();
//                
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04021.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("button",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("",iterEe.next().getValue()); // extracted link text
//
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04021.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("button",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("*-+''{} !<<-(-_ +",iterEe.next().getValue()); // extracted link text
//        
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04021.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("button",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("En savoir plus",iterEe.next().getValue()); // extracted link text
//
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04021.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("button",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("En savoir plus",iterEe.next().getValue()); // extracted link text
//
//        //--------------------------------------------------------------------//
//        processResult = processPageTest("Rgaa22.Test.4.2-3NMI-01");
//        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
//        assertEquals(1,processResult.getElementCounter());
//        assertEquals(1,processResult.getRemarkSet().size());
//        iter = processResult.getRemarkSet().iterator();
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04021.MANUAL_CHECK_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("a",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("Any alternative text",iterEe.next().getValue()); // extracted link text
//        
//        //--------------------------------------------------------------------//
//        processResult = processPageTest("Rgaa22.Test.4.2-3NMI-02");
//        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
//        assertEquals(1,processResult.getElementCounter());
//        assertEquals(1,processResult.getRemarkSet().size());
//        iter = processResult.getRemarkSet().iterator();
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04021.MANUAL_CHECK_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("button",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("Any alternative text",iterEe.next().getValue()); // extracted link text
//        
//        //--------------------------------------------------------------------//
//        processResult = processPageTest("Rgaa22.Test.4.2-3NMI-03");
//        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
//        assertEquals(1,processResult.getElementCounter());
//        assertEquals(1,processResult.getRemarkSet().size());
//        iter = processResult.getRemarkSet().iterator();
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04021.MANUAL_CHECK_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("a",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("Any alternative text",iterEe.next().getValue()); // extracted link text
//        
//        //--------------------------------------------------------------------//
//        processResult = processPageTest("Rgaa22.Test.4.2-3NMI-04");
//        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
//        assertEquals(1,processResult.getElementCounter());
//        assertEquals(1,processResult.getRemarkSet().size());
//        iter = processResult.getRemarkSet().iterator();
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04021.MANUAL_CHECK_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("button",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("Any alternative text",iterEe.next().getValue()); // extracted link text
//        
//        //--------------------------------------------------------------------//
//        processResult = processPageTest("Rgaa22.Test.4.2-4NA-01");
//        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
//        assertNull(processResult.getRemarkSet());
//
//        //--------------------------------------------------------------------//
//        processResult = processPageTest("Rgaa22.Test.4.2-4NA-02");
//        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
//        assertNull(processResult.getRemarkSet());
        
        //--------------------------------------------------------------------//
        ProcessResult processResult = processPageTest("Rgaa22.Test.4.2-5NT-01");
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
    }

    @Override
    protected void setConsolidate() {
//        assertEquals(TestSolution.FAILED,
//                consolidate("Rgaa22.Test.4.2-2Failed-01").getValue());
//        assertEquals(TestSolution.FAILED,
//                consolidate("Rgaa22.Test.4.2-2Failed-02").getValue());
//        assertEquals(TestSolution.FAILED,
//                consolidate("Rgaa22.Test.4.2-2Failed-03").getValue());
//        assertEquals(TestSolution.FAILED,
//                consolidate("Rgaa22.Test.4.2-2Failed-04").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("Rgaa22.Test.4.2-3NMI-01").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("Rgaa22.Test.4.2-3NMI-02").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("Rgaa22.Test.4.2-3NMI-03").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("Rgaa22.Test.4.2-3NMI-04").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                consolidate("Rgaa22.Test.4.2-4NA-01").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                consolidate("Rgaa22.Test.4.2-4NA-02").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa22.Test.4.2-5NT-01").getValue());
    }

}