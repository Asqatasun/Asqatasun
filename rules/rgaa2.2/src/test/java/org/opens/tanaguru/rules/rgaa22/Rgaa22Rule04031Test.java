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

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 4.3 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule04031Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule04031Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.opens.tanaguru.rules.rgaa22.Rgaa22Rule04031");
    }

    @Override
    protected void setUpWebResourceMap() {
//        getWebResourceMap().put("Rgaa22.Test.4.3-2Failed-01",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04031/RGAA22.Test.4.3-2Failed-01.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.3-2Failed-02",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04031/RGAA22.Test.4.3-2Failed-02.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.3-3NMI-01",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04031/RGAA22.Test.4.3-3NMI-01.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.3-3NMI-02",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04031/RGAA22.Test.4.3-3NMI-02.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.3-4NA-01",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04031/RGAA22.Test.4.3-4NA-01.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.3-4NA-02",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04031/RGAA22.Test.4.3-4NA-02.html"));
        getWebResourceMap().put("Rgaa22.Test.4.3-5NT-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04031/RGAA22.Test.4.3-5NT-01.html"));
    }

    @Override
    protected void setProcess() {
////--------------------------------------------------------------------//
//        ProcessResult processResult = processPageTest("Rgaa22.Test.4.3-2Failed-01");
//        assertEquals(TestSolution.FAILED, processResult.getValue());
//        assertEquals(3,processResult.getElementCounter());
//        assertEquals(3,processResult.getRemarkSet().size());
//        
//        Iterator<ProcessRemark> iter = processResult.getRemarkSet().iterator();
//        ProcessRemark pr = iter.next();
//        assertEquals(Rgaa22Rule04031.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        Iterator<EvidenceElement> iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("area",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("",iterEe.next().getValue()); // extracted link text
//        
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04031.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("area",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals(",;!:;-+/=}])°^_`",iterEe.next().getValue()); // extracted link text
//        
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04031.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("area",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("Cliquez ici ...",iterEe.next().getValue()); // extracted link text
//        
//        //--------------------------------------------------------------------//        
//        processResult = processPageTest("Rgaa22.Test.4.3-2Failed-02");
//        assertEquals(TestSolution.FAILED, processResult.getValue());
//        assertEquals(3,processResult.getElementCounter());
//        assertEquals(3,processResult.getRemarkSet().size());
//        
//        iter = processResult.getRemarkSet().iterator();
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04031.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("input",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("",iterEe.next().getValue()); // extracted link text
//        
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04031.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("input",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals(",;!:;-+/=}])°^_`",iterEe.next().getValue()); // extracted link text
//        
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04031.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("input",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("Cliquez ici ...",iterEe.next().getValue()); // extracted link text
//        
//        //--------------------------------------------------------------------//
//        processResult = processPageTest("Rgaa22.Test.4.3-3NMI-01");
//        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
//        assertEquals(1,processResult.getElementCounter());
//        assertEquals(1,processResult.getRemarkSet().size());
//        
//        iter = processResult.getRemarkSet().iterator();
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04031.MANUAL_CHECK_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("area",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("Any alternative text",iterEe.next().getValue()); // extracted link text
//        
//        //--------------------------------------------------------------------//
//        processResult = processPageTest("Rgaa22.Test.4.3-3NMI-02");
//        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
//        assertEquals(1,processResult.getElementCounter());
//        assertEquals(1,processResult.getRemarkSet().size());
//        
//        iter = processResult.getRemarkSet().iterator();
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04031.MANUAL_CHECK_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();// Iterate through evidence element collection
//        assertEquals("input",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("Any alternative text",iterEe.next().getValue()); // extracted link text
//        
//        //--------------------------------------------------------------------//
//        processResult = processPageTest("Rgaa22.Test.4.3-4NA-01");
//        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
//        assertNull(processResult.getRemarkSet());
//
//        //--------------------------------------------------------------------//
//        processResult = processPageTest("Rgaa22.Test.4.3-4NA-02");
//        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
//        assertNull(processResult.getRemarkSet());
        
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("Rgaa22.Test.4.3-5NT-01").getValue());
    }

    @Override
    protected void setConsolidate() {
//        assertEquals(TestSolution.FAILED,
//                consolidate("Rgaa22.Test.4.3-2Failed-01").getValue());
//        assertEquals(TestSolution.FAILED,
//                consolidate("Rgaa22.Test.4.3-2Failed-02").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("Rgaa22.Test.4.3-3NMI-01").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("Rgaa22.Test.4.3-3NMI-02").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                consolidate("Rgaa22.Test.4.3-4NA-01").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                consolidate("Rgaa22.Test.4.3-4NA-02").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa22.Test.4.3-5NT-01").getValue());
    }

}