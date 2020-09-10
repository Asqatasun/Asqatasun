/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.rules.accessiweb22;

import java.util.LinkedHashSet;
import org.asqatasun.entity.audit.ProcessRemark;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.SSP;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.rules.accessiweb22.test.Aw22RuleImplementationTestCase;
import org.asqatasun.rules.keystore.RemarkMessageStore;

/**
 *
 * @author jkowalczyk
 */
public class Aw22Rule08012Test extends Aw22RuleImplementationTestCase {

    public Aw22Rule08012Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.asqatasun.rules.accessiweb22.Aw22Rule08012");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW22.Test.8.1.2-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-1Passed-01.html"));
        getWebResourceMap().put("AW22.Test.8.1.2-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-1Passed-02.html"));
        getWebResourceMap().put("AW22.Test.8.1.2-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-1Passed-03.html"));
        getWebResourceMap().put("AW22.Test.8.1.2-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-1Passed-04.html"));
        getWebResourceMap().put("AW22.Test.8.1.2-1Passed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-1Passed-05.html"));
        getWebResourceMap().put("AW22.Test.8.1.2-1Passed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-1Passed-06.html"));
        getWebResourceMap().put("AW22.Test.8.1.2-1Passed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-1Passed-07.html"));
        getWebResourceMap().put("AW22.Test.8.1.2-1Passed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-1Passed-08.html"));
        getWebResourceMap().put("AW22.Test.8.1.2-1Passed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-1Passed-09.html"));
        getWebResourceMap().put("AW22.Test.8.1.2-1Passed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-1Passed-10.html"));
        getWebResourceMap().put("AW22.Test.8.1.2-1Passed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-1Passed-11.html"));
        getWebResourceMap().put("AW22.Test.8.1.2-1Passed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-1Passed-12.html"));
        getWebResourceMap().put("AW22.Test.8.1.2-1Passed-13",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-1Passed-13.html"));
        getWebResourceMap().put("AW22.Test.8.1.2-1Passed-14",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-1Passed-14.html"));
        getWebResourceMap().put("AW22.Test.8.1.2-1Passed-15",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-1Passed-15.html"));
        getWebResourceMap().put("AW22.Test.8.1.2-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.8.1.2-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.8.1.2-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule08012/AW22.Test.8.1.2-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        WebResource webResource = webResourceMap.get("AW22.Test.8.1.2-1Passed-01");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        ProcessResult processResult = processPageTest("AW22.Test.8.1.2-1Passed-01");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("AW22.Test.8.1.2-1Passed-02");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE HTML PUBLIC " +
            "\"-//W3C//DTD HTML 4.01 Transitional//EN\" " +
            "\"http://www.w3.org/TR/html4/loose.dtd\">");
        processResult = processPageTest("AW22.Test.8.1.2-1Passed-02");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("AW22.Test.8.1.2-1Passed-03");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\" \"http://www.w3.org/TR/html4/frameset.dtd\">");
        processResult = processPageTest("AW22.Test.8.1.2-1Passed-03");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("AW22.Test.8.1.2-1Passed-04");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        processResult = processPageTest("AW22.Test.8.1.2-1Passed-04");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("AW22.Test.8.1.2-1Passed-05");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        processResult = processPageTest("AW22.Test.8.1.2-1Passed-05");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-06------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("AW22.Test.8.1.2-1Passed-06");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Frameset//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd\">");
        processResult = processPageTest("AW22.Test.8.1.2-1Passed-06");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-07------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("AW22.Test.8.1.2-1Passed-07");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
        processResult = processPageTest("AW22.Test.8.1.2-1Passed-07");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-08------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("AW22.Test.8.1.2-1Passed-08");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE " +
            "html " +
            "PUBLIC " +
            "\"-//W3C//DTD XHTML Basic 1.1//EN\" " +
            "\"http://www.w3.org/TR/xhtml-basic/xhtml-basic11.dtd\">");
        processResult = processPageTest("AW22.Test.8.1.2-1Passed-08");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-09------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("AW22.Test.8.1.2-1Passed-09");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE math PUBLIC \"-//W3C//DTD MathML 2.0//EN\" \"http://www.w3.org/TR/MathML2/dtd/mathml2.dtd\">");
        processResult = processPageTest("AW22.Test.8.1.2-1Passed-09");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-10------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("AW22.Test.8.1.2-1Passed-10");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE math SYSTEM \"http://www.w3.org/Math/DTD/mathml1/mathml.dtd\">");
        processResult = processPageTest("AW22.Test.8.1.2-1Passed-10");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-11------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("AW22.Test.8.1.2-1Passed-11");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1 plus MathML 2.0 plus SVG 1.1//EN\" \"http://www.w3.org/2002/04/xhtml-math-svg/xhtml-math-svg.dtd\">");
        processResult = processPageTest("AW22.Test.8.1.2-1Passed-11");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-12------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("AW22.Test.8.1.2-1Passed-12");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE svg:svg PUBLIC \"-//W3C//DTD XHTML 1.1 plus MathML 2.0 plus SVG 1.1//EN\" \"http://www.w3.org/2002/04/xhtml-math-svg/xhtml-math-svg.dtd\">");
        processResult = processPageTest("AW22.Test.8.1.2-1Passed-12");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-13------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("AW22.Test.8.1.2-1Passed-13");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        processResult = processPageTest("AW22.Test.8.1.2-1Passed-13");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-14------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("AW22.Test.8.1.2-1Passed-14");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!doctype html>");
        processResult = processPageTest("AW22.Test.8.1.2-1Passed-14");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-15------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("AW22.Test.8.1.2-1Passed-15");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE HTML>");
        processResult = processPageTest("AW22.Test.8.1.2-1Passed-15");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("AW22.Test.8.1.2-2Failed-01");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/sxhtml1/DTD/xhtml1-strict.dtd\">");
        processResult = processPageTest("AW22.Test.8.1.2-2Failed-01");
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        ProcessRemark processRemark = ((ProcessRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.INVALID_DOCTYPE_MSG, processRemark.getMessageCode());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("AW22.Test.8.1.2-2Failed-02");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!doctype html public \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        processResult = processPageTest("AW22.Test.8.1.2-2Failed-02");
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((ProcessRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.INVALID_DOCTYPE_MSG, processRemark.getMessageCode());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.8.1.2-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.8.1.2-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.8.1.2-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.8.1.2-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.8.1.2-1Passed-04").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.8.1.2-1Passed-05").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.8.1.2-1Passed-06").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.8.1.2-1Passed-07").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.8.1.2-1Passed-08").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.8.1.2-1Passed-09").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.8.1.2-1Passed-10").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.8.1.2-1Passed-11").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.8.1.2-1Passed-12").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.8.1.2-1Passed-13").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.8.1.2-1Passed-14").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW22.Test.8.1.2-1Passed-15").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.8.1.2-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.8.1.2-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.8.1.2-4NA-01").getValue());
    }

}
