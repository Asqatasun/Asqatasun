/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
package org.opens.tanaguru.rules.accessiweb22;

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.accessiweb22.test.Aw22RuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class Aw22Rule06024Test extends Aw22RuleImplementationTestCase {
    
    public Aw22Rule06024Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.opens.tanaguru.rules.accessiweb22.Aw22Rule06024");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-01.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-02",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-02.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-03",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-03.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-04",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-04.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-05",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-05.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-06",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-06.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-07",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-07.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-08",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-08.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-3NMI-01",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-3NMI-01.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-3NMI-02",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-3NMI-02.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-3NMI-03",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-3NMI-03.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-3NMI-04",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-3NMI-04.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-3NMI-05",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-3NMI-05.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-3NMI-06",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-3NMI-06.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-4NA-01",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-4NA-01.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-4NA-02",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-4NA-02.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-4NA-03",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-4NA-03.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-4NA-04",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-4NA-04.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-4NA-05",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-4NA-05.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-4NA-06",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-4NA-06.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-4NA-07",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-4NA-07.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-4NA-08",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-4NA-08.html"));
//        getWebResourceMap().put("AW22.Test.06.02.04-4NA-09",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW22/Aw22Rule06024/AW22.Test.06.02.04-4NA-09.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("AW22.Test.06.02.04-2Failed-01");
        assertEquals(TestSolution.NOT_TESTED,processResult.getValue());
//        assertEquals("NotPertinenttitleAttribute",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
//
//        processResult = processPageTest("AW22.Test.06.02.04-2Failed-02");
//        assertEquals(TestSolution.FAILED,processResult.getValue());
//        assertEquals("NotPertinenttitleAttribute",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
//
//        processResult = processPageTest("AW22.Test.06.02.04-2Failed-03");
//        assertEquals(TestSolution.FAILED,processResult.getValue());
//        assertEquals("TitleAttributeEmpty",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
//
//        processResult = processPageTest("AW22.Test.06.02.04-2Failed-04");
//        assertEquals(TestSolution.FAILED,processResult.getValue());
//        assertEquals("TitleAttributeEmpty",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
//
//        processResult = processPageTest("AW22.Test.06.02.04-2Failed-05");
//        assertEquals(TestSolution.FAILED,processResult.getValue());
//        assertEquals("NotPertinenttitleAttribute",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
//
//        processResult = processPageTest("AW22.Test.06.02.04-2Failed-06");
//        assertEquals(TestSolution.FAILED,processResult.getValue());
//        assertEquals("NotPertinenttitleAttribute",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
//
//        processResult = processPageTest("AW22.Test.06.02.04-2Failed-07");
//        assertEquals(TestSolution.FAILED,processResult.getValue());
//        assertEquals("NotPertinenttitleAttribute",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
//
//        processResult = processPageTest("AW22.Test.06.02.04-2Failed-08");
//        assertEquals(TestSolution.FAILED,processResult.getValue());
//        assertEquals("NotPertinenttitleAttribute",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
//
//        processResult = processPageTest("AW22.Test.06.02.04-3NMI-01");
//        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
//        assertEquals("SuspectedPertinenttitleAttribute",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
//
//        processResult = processPageTest("AW22.Test.06.02.04-3NMI-02");
//        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
//        assertEquals("SuspectedPertinenttitleAttribute",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
//
//        processResult = processPageTest("AW22.Test.06.02.04-3NMI-03");
//        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
//        assertEquals("SuspectedPertinenttitleAttribute",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
//
//        processResult = processPageTest("AW22.Test.06.02.04-3NMI-04");
//        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
//        assertEquals("SuspectedPertinenttitleAttribute",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
//
//        processResult = processPageTest("AW22.Test.06.02.04-3NMI-05");
//        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
//        assertEquals("SuspectedNotPertinenttitleAttribute",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
//
//        processResult = processPageTest("AW22.Test.06.02.04-3NMI-06");
//        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
//        assertEquals("SuspectedNotPertinenttitleAttribute",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
//
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                processPageTest("AW22.Test.06.02.04-4NA-01").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                processPageTest("AW22.Test.06.02.04-4NA-02").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                processPageTest("AW22.Test.06.02.04-4NA-03").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                processPageTest("AW22.Test.06.02.04-4NA-04").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                processPageTest("AW22.Test.06.02.04-4NA-05").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                processPageTest("AW22.Test.06.02.04-4NA-06").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                processPageTest("AW22.Test.06.02.04-4NA-07").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                processPageTest("AW22.Test.06.02.04-4NA-08").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                processPageTest("AW22.Test.06.02.04-4NA-09").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW22.Test.06.02.04-2Failed-01").getValue());
//        assertEquals(TestSolution.FAILED,
//                consolidate("AW22.Test.06.02.04-2Failed-02").getValue());
//        assertEquals(TestSolution.FAILED,
//                consolidate("AW22.Test.06.02.04-2Failed-03").getValue());
//        assertEquals(TestSolution.FAILED,
//                consolidate("AW22.Test.06.02.04-2Failed-04").getValue());
//        assertEquals(TestSolution.FAILED,
//                consolidate("AW22.Test.06.02.04-2Failed-05").getValue());
//        assertEquals(TestSolution.FAILED,
//                consolidate("AW22.Test.06.02.04-2Failed-06").getValue());
//        assertEquals(TestSolution.FAILED,
//                consolidate("AW22.Test.06.02.04-2Failed-07").getValue());
//        assertEquals(TestSolution.FAILED,
//                consolidate("AW22.Test.06.02.04-2Failed-08").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("AW22.Test.06.02.04-3NMI-01").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("AW22.Test.06.02.04-3NMI-02").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("AW22.Test.06.02.04-3NMI-03").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("AW22.Test.06.02.04-3NMI-04").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("AW22.Test.06.02.04-3NMI-05").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("AW22.Test.06.02.04-3NMI-06").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                consolidate("AW22.Test.06.02.04-4NA-01").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                consolidate("AW22.Test.06.02.04-4NA-02").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                consolidate("AW22.Test.06.02.04-4NA-03").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                consolidate("AW22.Test.06.02.04-4NA-04").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                consolidate("AW22.Test.06.02.04-4NA-05").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                consolidate("AW22.Test.06.02.04-4NA-06").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                consolidate("AW22.Test.06.02.04-4NA-07").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                consolidate("AW22.Test.06.02.04-4NA-08").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                consolidate("AW22.Test.06.02.04-4NA-09").getValue());
    }

}
