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
public class Aw21Rule05041Test extends Aw21RuleImplementationTestCase {

    public Aw21Rule05041Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb21.Aw21Rule05041");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW21.Test.05.04.01-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-1Passed-01.html"));
        addParameterToParameterMap("AW21.Test.05.04.01-1Passed-01", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));

        getWebResourceMap().put("AW21.Test.05.04.01-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-1Passed-02.html"));
        addParameterToParameterMap("AW21.Test.05.04.01-1Passed-02", createParameter("Rules", "DATA_TABLE_MARKER", "id-data-table"));

        getWebResourceMap().put("AW21.Test.05.04.01-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-1Passed-03.html"));
        addParameterToParameterMap("AW21.Test.05.04.01-1Passed-03", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table;id-data-table"));
        
        getWebResourceMap().put("AW21.Test.05.04.01-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-1Passed-04.html"));
        addParameterToParameterMap("AW21.Test.05.04.01-1Passed-04", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));

        getWebResourceMap().put("AW21.Test.05.04.01-1Passed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-1Passed-05.html"));
        addParameterToParameterMap("AW21.Test.05.04.01-1Passed-05", createParameter("Rules", "DATA_TABLE_MARKER", "id-data-table"));
        
        getWebResourceMap().put("AW21.Test.05.04.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-2Failed-01.html"));
        addParameterToParameterMap("AW21.Test.05.04.01-2Failed-01", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));

        getWebResourceMap().put("AW21.Test.05.04.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-2Failed-02.html"));
        addParameterToParameterMap("AW21.Test.05.04.01-2Failed-02", createParameter("Rules", "DATA_TABLE_MARKER", "id-data-table"));
        
        getWebResourceMap().put("AW21.Test.05.04.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-2Failed-03.html"));
        addParameterToParameterMap("AW21.Test.05.04.01-2Failed-03", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table;id-data-table"));

        getWebResourceMap().put("AW21.Test.05.04.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-2Failed-04.html"));
        addParameterToParameterMap("AW21.Test.05.04.01-2Failed-04", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));

        getWebResourceMap().put("AW21.Test.05.04.01-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-2Failed-05.html"));
        addParameterToParameterMap("AW21.Test.05.04.01-2Failed-05", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));

        getWebResourceMap().put("AW21.Test.05.04.01-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-2Failed-06.html"));
        addParameterToParameterMap("AW21.Test.05.04.01-2Failed-06", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));

        getWebResourceMap().put("AW21.Test.05.04.01-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-2Failed-07.html"));
        addParameterToParameterMap("AW21.Test.05.04.01-2Failed-07", createParameter("Rules", "DATA_TABLE_MARKER", "id-data-table"));
        
        getWebResourceMap().put("AW21.Test.05.04.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-3NMI-01.html"));

        getWebResourceMap().put("AW21.Test.05.04.01-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-3NMI-02.html"));

        getWebResourceMap().put("AW21.Test.05.04.01-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-3NMI-03.html"));
        addParameterToParameterMap("AW21.Test.05.04.01-3NMI-03", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));
        
        getWebResourceMap().put("AW21.Test.05.04.01-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-3NMI-04.html"));
        addParameterToParameterMap("AW21.Test.05.04.01-3NMI-04", createParameter("Rules", "DATA_TABLE_MARKER", "id-data-table"));

        getWebResourceMap().put("AW21.Test.05.04.01-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-3NMI-05.html"));
        addParameterToParameterMap("AW21.Test.05.04.01-3NMI-05", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table otherselector"));
        
        getWebResourceMap().put("AW21.Test.05.04.01-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05041/AW21.Test.05.04.01-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("AW21.Test.05.04.01-1Passed-01");
        assertEquals(TestSolution.PASSED,processResult.getValue());

        processResult = processPageTest("AW21.Test.05.04.01-1Passed-02");
        assertEquals(TestSolution.PASSED,processResult.getValue());

        processResult = processPageTest("AW21.Test.05.04.01-1Passed-03");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        
        processResult = processPageTest("AW21.Test.05.04.01-1Passed-04");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        
        processResult = processPageTest("AW21.Test.05.04.01-1Passed-05");
        assertEquals(TestSolution.PASSED,processResult.getValue());

        processResult = processPageTest("AW21.Test.05.04.01-2Failed-01");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("CaptionMissing",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW21.Test.05.04.01-2Failed-02");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("CaptionMissing",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW21.Test.05.04.01-2Failed-03");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals("CaptionMissing",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("CaptionMissing",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("AW21.Test.05.04.01-2Failed-04");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals("CaptionMissing",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("CaptionPresentCheckDataTable",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("AW21.Test.05.04.01-2Failed-05");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals("CaptionMissing",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("CaptionMissingCheckDataTable",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("AW21.Test.05.04.01-2Failed-06");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("CaptionMissing",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW21.Test.05.04.01-2Failed-07");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("CaptionMissing",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        
        processResult = processPageTest("AW21.Test.05.04.01-3NMI-01");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("CaptionMissingCheckDataTable",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW21.Test.05.04.01-3NMI-02");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("CaptionPresentCheckDataTable",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW21.Test.05.04.01-3NMI-03");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("CaptionPresentCheckDataTable",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW21.Test.05.04.01-3NMI-04");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("CaptionMissingCheckDataTable",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        
        processResult = processPageTest("AW21.Test.05.04.01-3NMI-05");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("CaptionPresentCheckDataTable",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.05.04.01-4NA-01").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.05.04.01-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.05.04.01-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.05.04.01-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.05.04.01-1Passed-04").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.05.04.01-1Passed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.05.04.01-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.05.04.01-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.05.04.01-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.05.04.01-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.05.04.01-2Failed-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.05.04.01-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.05.04.01-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.05.04.01-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.05.04.01-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.05.04.01-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.05.04.01-4NA-01").getValue());
    }

}
