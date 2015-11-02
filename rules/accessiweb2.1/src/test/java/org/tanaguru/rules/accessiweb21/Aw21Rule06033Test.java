/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.tanaguru.rules.accessiweb21;

import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.accessiweb21.test.Aw21RuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class Aw21Rule06033Test extends Aw21RuleImplementationTestCase {

    public Aw21Rule06033Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb21.Aw21Rule06033");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW21.Test.06.03.03-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-2Failed-01.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-2Failed-02.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-2Failed-03.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-2Failed-04.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-2Failed-05.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-2Failed-06.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-2Failed-07.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-2Failed-08.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-3NMI-01.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-3NMI-02.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-3NMI-03.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-3NMI-04.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-3NMI-05.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-3NMI-06.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-3NMI-07.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-4NA-01.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-4NA-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-4NA-02.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-4NA-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-4NA-03.html"));
        getWebResourceMap().put("AW21.Test.06.03.03-4NA-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06033/AW21.Test.06.03.03-4NA-04.html"));

        // 06.03.01 testcases
        getWebResourceMap().put("AW21.Test.06.03.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06031/AW21.Test.06.03.01-2Failed-01.html"));
        getWebResourceMap().put("AW21.Test.06.03.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06031/AW21.Test.06.03.01-2Failed-02.html"));
        getWebResourceMap().put("AW21.Test.06.03.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06031/AW21.Test.06.03.01-2Failed-03.html"));
        getWebResourceMap().put("AW21.Test.06.03.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06031/AW21.Test.06.03.01-2Failed-04.html"));
        getWebResourceMap().put("AW21.Test.06.03.01-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06031/AW21.Test.06.03.01-2Failed-05.html"));
        getWebResourceMap().put("AW21.Test.06.03.01-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06031/AW21.Test.06.03.01-2Failed-06.html"));
        getWebResourceMap().put("AW21.Test.06.03.01-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06031/AW21.Test.06.03.01-2Failed-07.html"));
        getWebResourceMap().put("AW21.Test.06.03.01-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06031/AW21.Test.06.03.01-2Failed-08.html"));
        getWebResourceMap().put("AW21.Test.06.03.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06031/AW21.Test.06.03.01-3NMI-01.html"));
        getWebResourceMap().put("AW21.Test.06.03.01-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06031/AW21.Test.06.03.01-3NMI-02.html"));
        getWebResourceMap().put("AW21.Test.06.03.01-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06031/AW21.Test.06.03.01-3NMI-03.html"));
        getWebResourceMap().put("AW21.Test.06.03.01-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06031/AW21.Test.06.03.01-3NMI-04.html"));
        getWebResourceMap().put("AW21.Test.06.03.01-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06031/AW21.Test.06.03.01-3NMI-05.html"));
        getWebResourceMap().put("AW21.Test.06.03.01-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06031/AW21.Test.06.03.01-3NMI-06.html"));
        getWebResourceMap().put("AW21.Test.06.03.01-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06031/AW21.Test.06.03.01-3NMI-07.html"));

        //06.03.02 testcases
        getWebResourceMap().put("AW21.Test.06.03.02-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-2Failed-01.html"));
        getWebResourceMap().put("AW21.Test.06.03.02-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-2Failed-02.html"));
        getWebResourceMap().put("AW21.Test.06.03.02-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-2Failed-03.html"));
        getWebResourceMap().put("AW21.Test.06.03.02-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-2Failed-04.html"));
        getWebResourceMap().put("AW21.Test.06.03.02-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-2Failed-05.html"));
        getWebResourceMap().put("AW21.Test.06.03.02-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-2Failed-06.html"));
        getWebResourceMap().put("AW21.Test.06.03.02-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-2Failed-07.html"));
        getWebResourceMap().put("AW21.Test.06.03.02-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-2Failed-08.html"));
        getWebResourceMap().put("AW21.Test.06.03.02-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-2Failed-09.html"));
        getWebResourceMap().put("AW21.Test.06.03.02-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-2Failed-10.html"));
        getWebResourceMap().put("AW21.Test.06.03.02-2Failed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-2Failed-11.html"));
        getWebResourceMap().put("AW21.Test.06.03.02-2Failed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-2Failed-12.html"));
        getWebResourceMap().put("AW21.Test.06.03.02-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-3NMI-01.html"));
        getWebResourceMap().put("AW21.Test.06.03.02-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-3NMI-02.html"));
        getWebResourceMap().put("AW21.Test.06.03.02-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-3NMI-03.html"));
        getWebResourceMap().put("AW21.Test.06.03.02-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-3NMI-04.html"));
        getWebResourceMap().put("AW21.Test.06.03.02-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-3NMI-05.html"));
        getWebResourceMap().put("AW21.Test.06.03.02-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule06032/AW21.Test.06.03.02-3NMI-06.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("AW21.Test.06.03.03-2Failed-01");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("AW21.Test.06.03.03-2Failed-02");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("AW21.Test.06.03.03-2Failed-03");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("AW21.Test.06.03.03-2Failed-04");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("AW21.Test.06.03.03-2Failed-05");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("AW21.Test.06.03.03-2Failed-06");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("AW21.Test.06.03.03-2Failed-07");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("AW21.Test.06.03.03-2Failed-08");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("AW21.Test.06.03.03-3NMI-01");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("AW21.Test.06.03.03-3NMI-02");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("AW21.Test.06.03.03-3NMI-03");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("AW21.Test.06.03.03-3NMI-04");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("AW21.Test.06.03.03-3NMI-05");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("AW21.Test.06.03.03-3NMI-06");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("AW21.Test.06.03.03-3NMI-07");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.03-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.03-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.03-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.03-4NA-04").getValue());

        // 06.03.01 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.01-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.01-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.01-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.01-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.01-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.01-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.01-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.01-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.01-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.01-3NMI-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.01-3NMI-07").getValue());

        // 06.03.02 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-2Failed-09").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-2Failed-10").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-2Failed-11").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-2Failed-12").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.06.03.02-3NMI-06").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.06.03.03-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.06.03.03-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.06.03.03-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.06.03.03-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.06.03.03-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.06.03.03-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.06.03.03-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.06.03.03-2Failed-08").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.06.03.03-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.06.03.03-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.06.03.03-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.06.03.03-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.06.03.03-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.06.03.03-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.06.03.03-3NMI-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.03-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.03-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.03-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.03-4NA-04").getValue());

        // 06.03.01 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.01-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.01-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.01-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.01-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.01-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.01-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.01-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.01-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.01-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.01-3NMI-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.01-3NMI-07").getValue());

        // 06.03.02 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.02-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.02-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.02-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.02-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.02-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.02-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.02-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.02-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.02-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.02-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.02-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.02-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.02-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.06.03.02-3NMI-06").getValue());
    }
}
