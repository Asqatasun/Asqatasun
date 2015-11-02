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

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.accessiweb21.test.Aw21RuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class Aw21Rule08012Test extends Aw21RuleImplementationTestCase {

    public Aw21Rule08012Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb21.Aw21Rule08012");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW21.Test.08.01.02-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-1Passed-01.html"));
        getWebResourceMap().put("AW21.Test.08.01.02-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-1Passed-02.html"));
        getWebResourceMap().put("AW21.Test.08.01.02-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-1Passed-03.html"));
        getWebResourceMap().put("AW21.Test.08.01.02-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-1Passed-04.html"));
        getWebResourceMap().put("AW21.Test.08.01.02-1Passed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-1Passed-05.html"));
        getWebResourceMap().put("AW21.Test.08.01.02-1Passed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-1Passed-06.html"));
        getWebResourceMap().put("AW21.Test.08.01.02-1Passed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-1Passed-07.html"));
        getWebResourceMap().put("AW21.Test.08.01.02-1Passed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-1Passed-08.html"));
        getWebResourceMap().put("AW21.Test.08.01.02-1Passed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-1Passed-09.html"));
        getWebResourceMap().put("AW21.Test.08.01.02-1Passed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-1Passed-10.html"));
        getWebResourceMap().put("AW21.Test.08.01.02-1Passed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-1Passed-11.html"));
        getWebResourceMap().put("AW21.Test.08.01.02-1Passed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-1Passed-12.html"));
        getWebResourceMap().put("AW21.Test.08.01.02-1Passed-13",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-1Passed-13.html"));
        getWebResourceMap().put("AW21.Test.08.01.02-1Passed-14",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-1Passed-14.html"));
        getWebResourceMap().put("AW21.Test.08.01.02-1Passed-15",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-1Passed-15.html"));
        getWebResourceMap().put("AW21.Test.08.01.02-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-2Failed-01.html"));
        getWebResourceMap().put("AW21.Test.08.01.02-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-2Failed-02.html"));
        getWebResourceMap().put("AW21.Test.08.01.02-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08012/AW21.Test.08.01.02-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        assertEquals(TestSolution.PASSED,
                processPageTest("AW21.Test.08.01.02-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("AW21.Test.08.01.02-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("AW21.Test.08.01.02-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("AW21.Test.08.01.02-1Passed-04").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("AW21.Test.08.01.02-1Passed-05").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("AW21.Test.08.01.02-1Passed-06").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("AW21.Test.08.01.02-1Passed-07").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("AW21.Test.08.01.02-1Passed-08").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("AW21.Test.08.01.02-1Passed-09").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("AW21.Test.08.01.02-1Passed-10").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("AW21.Test.08.01.02-1Passed-11").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("AW21.Test.08.01.02-1Passed-12").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("AW21.Test.08.01.02-1Passed-13").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("AW21.Test.08.01.02-1Passed-14").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("AW21.Test.08.01.02-1Passed-15").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW21.Test.08.01.02-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW21.Test.08.01.02-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.08.01.02-4NA-01").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.08.01.02-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.08.01.02-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.08.01.02-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.08.01.02-1Passed-04").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.08.01.02-1Passed-05").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.08.01.02-1Passed-06").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.08.01.02-1Passed-07").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.08.01.02-1Passed-08").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.08.01.02-1Passed-09").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.08.01.02-1Passed-10").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.08.01.02-1Passed-11").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.08.01.02-1Passed-12").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.08.01.02-1Passed-13").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.08.01.02-1Passed-14").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.08.01.02-1Passed-15").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.08.01.02-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.08.01.02-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.08.01.02-4NA-01").getValue());
    }

}
