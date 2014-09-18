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

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule100102Test extends Rgaa30RuleImplementationTestCase {

    public Rgaa30Rule100102Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName( "org.opens.tanaguru.rules.rgaa30.Rgaa30Rule100102");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Rgaa30.Test.10.01.02-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100102/Rgaa30.Test.10.01.02-1Passed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.10.01.02-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100102/Rgaa30.Test.10.01.02-1Passed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.10.01.02-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100102/Rgaa30.Test.10.01.02-1Passed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.10.01.02-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100102/Rgaa30.Test.10.01.02-1Passed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.10.01.02-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100102/Rgaa30.Test.10.01.02-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.10.01.02-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100102/Rgaa30.Test.10.01.02-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.10.01.02-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100102/Rgaa30.Test.10.01.02-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.10.01.02-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100102/Rgaa30.Test.10.01.02-2Failed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.10.01.02-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100102/Rgaa30.Test.10.01.02-2Failed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.10.01.02-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100102/Rgaa30.Test.10.01.02-2Failed-06.html"));
        getWebResourceMap().put("Rgaa30.Test.10.01.02-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100102/Rgaa30.Test.10.01.02-2Failed-07.html"));
        getWebResourceMap().put("Rgaa30.Test.10.01.02-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100102/Rgaa30.Test.10.01.02-2Failed-08.html"));
        getWebResourceMap().put("Rgaa30.Test.10.01.02-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100102/Rgaa30.Test.10.01.02-2Failed-09.html"));
        getWebResourceMap().put("Rgaa30.Test.10.01.02-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule100102/Rgaa30.Test.10.01.02-2Failed-10.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult = processPageTest("Rgaa30.Test.10.01.02-1Passed-01");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        
        processResult = processPageTest("Rgaa30.Test.10.01.02-1Passed-02");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        
        processResult = processPageTest("Rgaa30.Test.10.01.02-1Passed-03");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        
        processResult = processPageTest("Rgaa30.Test.10.01.02-1Passed-04");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        
        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-01");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-02");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-03");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-04");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-05");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-06");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-07");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-08");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-09");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-10");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.10.01.02-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.10.01.02-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.10.01.02-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.10.01.02-1Passed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.10.01.02-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.10.01.02-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.10.01.02-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.10.01.02-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.10.01.02-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.10.01.02-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.10.01.02-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.10.01.02-2Failed-08").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.10.01.02-2Failed-09").getValue());
    }

}
