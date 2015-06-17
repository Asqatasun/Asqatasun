/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.rules.accessiweb21;

import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.accessiweb21.test.Aw21RuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class Aw21Rule06061Test extends Aw21RuleImplementationTestCase {

    public Aw21Rule06061Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb21.Aw21Rule06061");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW21.Test.06.06.01-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06061/AW21.Test.06.06.01-1Passed-01.html"));
        getWebResourceMap().put("AW21.Test.06.06.01-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06061/AW21.Test.06.06.01-1Passed-02.html"));
        getWebResourceMap().put("AW21.Test.06.06.01-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06061/AW21.Test.06.06.01-1Passed-03.html"));
        getWebResourceMap().put("AW21.Test.06.06.01-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06061/AW21.Test.06.06.01-1Passed-04.html"));
        getWebResourceMap().put("AW21.Test.06.06.01-1Passed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06061/AW21.Test.06.06.01-1Passed-05.html"));
        getWebResourceMap().put("AW21.Test.06.06.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06061/AW21.Test.06.06.01-2Failed-01.html"));
        getWebResourceMap().put("AW21.Test.06.06.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06061/AW21.Test.06.06.01-2Failed-02.html"));
        getWebResourceMap().put("AW21.Test.06.06.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06061/AW21.Test.06.06.01-2Failed-03.html"));
        getWebResourceMap().put("AW21.Test.06.06.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06061/AW21.Test.06.06.01-2Failed-04.html"));
        getWebResourceMap().put("AW21.Test.06.06.01-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06061/AW21.Test.06.06.01-2Failed-05.html"));
        getWebResourceMap().put("AW21.Test.06.06.01-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06061/AW21.Test.06.06.01-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("AW21.Test.06.06.01-1Passed-01");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("AW21.Test.06.06.01-1Passed-02");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW21.Test.06.06.01-1Passed-03");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("AW21.Test.06.06.01-1Passed-04");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW21.Test.06.06.01-1Passed-05");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("AW21.Test.06.06.01-2Failed-01");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1, (processResult.getRemarkSet().size()));
        assertEquals("ValueEmpty",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW21.Test.06.06.01-2Failed-02");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1, (processResult.getRemarkSet().size()));
        assertEquals("ValueEmpty",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW21.Test.06.06.01-2Failed-03");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1, (processResult.getRemarkSet().size()));
        assertEquals("ValueEmpty",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW21.Test.06.06.01-2Failed-04");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1, (processResult.getRemarkSet().size()));
        assertEquals("ValueEmpty",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW21.Test.06.06.01-2Failed-05");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1, (processResult.getRemarkSet().size()));
        assertEquals("ValueEmpty",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        
        processResult = processPageTest("AW21.Test.06.06.01-4NA-01");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.06.06.01-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.06.06.01-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.06.06.01-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.06.06.01-1Passed-04").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.06.06.01-1Passed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.06.06.01-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.06.06.01-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.06.06.01-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.06.06.01-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.06.06.01-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.06.01-4NA-01").getValue());
    }
}
