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

import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 4.1 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule04011Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule04011Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.opens.tanaguru.rules.rgaa22.Rgaa22Rule04011");
    }

    @Override
    protected void setUpWebResourceMap() {
//        getWebResourceMap().put("Rgaa22.Test.4.1-1Passed-01",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "RGAA22/Rgaa22Rule04011/RGAA22.Test.4.1-1Passed-01.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.1-1Passed-02",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "RGAA22/Rgaa22Rule04011/RGAA22.Test.4.1-1Passed-02.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.1-2Failed-01",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "RGAA22/Rgaa22Rule04011/RGAA22.Test.4.1-2Failed-01.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.1-2Failed-02",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "RGAA22/Rgaa22Rule04011/RGAA22.Test.4.1-2Failed-02.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.1-3NMI-01",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04011/RGAA22.Test.4.1-3NMI-01.html"));
//        getWebResourceMap().put("Rgaa22.Test.4.1-4NA-01",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04011/RGAA22.Test.4.1-4NA-01.html"));
        getWebResourceMap().put("Rgaa22.Test.4.1-5NT-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "RGAA22/Rgaa22Rule04011/RGAA22.Test.4.1-5NT-01.html"));
    }

    @Override
    protected void setProcess() {
//        //----------------------------------------------------------------------
//        //------------------------------1Passed-01------------------------------
//        //----------------------------------------------------------------------
//        ProcessResult processResult = processPageTest("Rgaa22.Test.4.1-1Passed-01");
//        // check test result
//        assertEquals(TestSolution.PASSED, processResult.getValue());
//        // check test has no remark
//        assertNull(processResult.getRemarkSet());
//        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
//        
//        //----------------------------------------------------------------------
//        //------------------------------1Passed-01------------------------------
//        //----------------------------------------------------------------------
//        processResult = processPageTest("Rgaa22.Test.4.1-1Passed-02");
//        // check test result
//        assertEquals(TestSolution.PASSED, processResult.getValue());
//        // check test has no remark
//        assertNull(processResult.getRemarkSet());
//        // check number of elements in the page
//        assertEquals(5, processResult.getElementCounter());
//        
//        //-------------------------------------------------------------------------//
//        processResult = processPageTest("Rgaa22.Test.4.1-2Failed-01");
//        assertEquals(TestSolution.FAILED, processResult.getValue());
//        assertEquals(5,processResult.getElementCounter());
//        assertEquals(5,processResult.getRemarkSet().size());
//        Iterator<ProcessRemark> iter = processResult.getRemarkSet().iterator();
//        
//        ProcessRemark pr = iter.next();
//        assertEquals(Rgaa22Rule04011.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        Iterator<EvidenceElement> iterEe = pr.getElementList().iterator();
//        assertEquals("img",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertTrue(iterEe.next().getValue().contains("img/mock-img.jpg")); // extract src of image
//        
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04011.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();
//        assertEquals("img",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertTrue(iterEe.next().getValue().contains("img/mock-img2.jpg")); // extract src of image
//        
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04011.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();
//        assertEquals("area",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertTrue(iterEe.next().getValue().contains("attribute-absent")); // extract hreaf of area but absent
//
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04011.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();
//        assertEquals("input",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertTrue(iterEe.next().getValue().contains("attribute-absent")); // extract src of input but absent
//        
//        pr = iter.next();
//        assertEquals(Rgaa22Rule04011.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();
//        assertEquals("applet",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertTrue(iterEe.next().getValue().contains("mock.class")); // extract code of applet
//        
//        //-------------------------------------------------------------------------//
//        processResult = processPageTest("Rgaa22.Test.4.1-2Failed-02");
//        assertEquals(TestSolution.FAILED, processResult.getValue());
//        assertEquals(5,processResult.getElementCounter());
//        assertEquals(5,processResult.getRemarkSet().size());
//        iter = processResult.getRemarkSet().iterator();
//        
//        pr = iter.next(); // 1st encountered failed element is img
//        assertEquals(Rgaa22Rule04011.CHECK_CAPTCHA_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(4,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();
//        assertEquals("img",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("FAILED",iterEe.next().getValue()); // the precomputed result is failed
//        assertTrue(iterEe.next().getValue().contains("img/mock-captcha.jpg")); // extract src of image
//        
//        pr = iter.next(); // 2nd encountered nmi element is img
//        assertEquals(Rgaa22Rule04011.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();
//        assertEquals("img",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertTrue(iterEe.next().getValue().contains("img/mock-img2.jpg")); // extract src of image
//        
//        pr = iter.next(); // 3rd encountered nmi element is area
//        assertEquals(Rgaa22Rule04011.FAILED_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(3,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();
//        assertEquals("area",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertTrue(iterEe.next().getValue().contains("attribute-absent")); // extract href of area
//        
//        pr = iter.next(); // 4th encountered failed element is input
//        assertEquals(Rgaa22Rule04011.CHECK_CAPTCHA_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(4,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();
//        assertEquals("input",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("PASSED",iterEe.next().getValue()); // the precomputed result is passed
//        assertTrue(iterEe.next().getValue().contains("attribute-absent")); // extract src of input
//        
//        pr = iter.next(); // 5th encountered failed element is applet
//        assertEquals(Rgaa22Rule04011.CHECK_CAPTCHA_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(4,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();
//        assertEquals("applet",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("FAILED",iterEe.next().getValue()); // the precomputed result is failed
//        assertTrue(iterEe.next().getValue().contains("mock.class")); // extract code of applet
//        
//        //-------------------------------------------------------------------------//
//        processResult = processPageTest("Rgaa22.Test.4.1-3NMI-01");
//        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
//        assertEquals(5,processResult.getElementCounter());
//        assertEquals(3,processResult.getRemarkSet().size());
//        iter = processResult.getRemarkSet().iterator();
//        
//        pr = iter.next(); // 1st encountered nmi element is img
//        assertEquals(Rgaa22Rule04011.CHECK_CAPTCHA_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(4,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();
//        assertEquals("img",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("FAILED",iterEe.next().getValue()); // the precomputed result is failed
//        assertTrue(iterEe.next().getValue().contains("img/mock-captcha.jpg")); // extract src of image
//        
//        pr = iter.next(); // 2nd encountered failed element is input
//        assertEquals(Rgaa22Rule04011.CHECK_CAPTCHA_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(4,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();
//        assertEquals("input",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("PASSED",iterEe.next().getValue()); // the precomputed result is passed
//        assertTrue(iterEe.next().getValue().contains("attribute-absent")); // extract src of input but absent
//        
//        pr = iter.next(); // 3rd encountered failed element is applet
//        assertEquals(Rgaa22Rule04011.CHECK_CAPTCHA_MESSAGE_CODE,pr.getMessageCode());
//        assertEquals(4,pr.getElementList().size());
//        iterEe = pr.getElementList().iterator();
//        assertEquals("applet",iterEe.next().getValue());
//        iterEe.next(); // the second evidence element is the snippet and not tested here
//        assertEquals("FAILED",iterEe.next().getValue()); // the precomputed result is failed
//        assertTrue(iterEe.next().getValue().contains("mock.class")); // extract code of applet
//
//        //-------------------------------------------------------------------------//
//        processResult = processPageTest("Rgaa22.Test.4.1-4NA-01");
//        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
//        assertNull(processResult.getRemarkSet());
        //-------------------------------------------------------------------------//
        ProcessResult processResult = processPageTest("Rgaa22.Test.4.1-5NT-01");
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
//        assertEquals(TestSolution.PASSED,
//                consolidate("Rgaa22.Test.4.1-1Passed-01").getValue());
//        assertEquals(TestSolution.PASSED,
//                consolidate("Rgaa22.Test.4.1-1Passed-02").getValue());
//        assertEquals(TestSolution.FAILED,
//                consolidate("Rgaa22.Test.4.1-2Failed-01").getValue());
//        assertEquals(TestSolution.FAILED,
//                consolidate("Rgaa22.Test.4.1-2Failed-02").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("Rgaa22.Test.4.1-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa22.Test.4.1-5NT-01").getValue());
    }

}