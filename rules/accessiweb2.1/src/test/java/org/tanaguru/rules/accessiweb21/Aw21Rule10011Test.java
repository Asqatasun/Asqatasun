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
public class Aw21Rule10011Test extends Aw21RuleImplementationTestCase {

    public Aw21Rule10011Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName( "org.tanaguru.rules.accessiweb21.Aw21Rule10011");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW21.Test.10.01.01-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10011/AW21.Test.10.01.01-1Passed-01.html"));
        getWebResourceMap().put("AW21.Test.10.01.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10011/AW21.Test.10.01.01-2Failed-01.html"));
        getWebResourceMap().put("AW21.Test.10.01.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10011/AW21.Test.10.01.01-2Failed-02.html"));
        getWebResourceMap().put("AW21.Test.10.01.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10011/AW21.Test.10.01.01-2Failed-03.html"));
        getWebResourceMap().put("AW21.Test.10.01.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10011/AW21.Test.10.01.01-2Failed-04.html"));
        getWebResourceMap().put("AW21.Test.10.01.01-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10011/AW21.Test.10.01.01-2Failed-05.html"));
        getWebResourceMap().put("AW21.Test.10.01.01-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10011/AW21.Test.10.01.01-2Failed-06.html"));
        getWebResourceMap().put("AW21.Test.10.01.01-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10011/AW21.Test.10.01.01-2Failed-07.html"));
        getWebResourceMap().put("AW21.Test.10.01.01-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10011/AW21.Test.10.01.01-2Failed-08.html"));
        getWebResourceMap().put("AW21.Test.10.01.01-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10011/AW21.Test.10.01.01-2Failed-09.html"));
    }

    @Override
    protected void setProcess() {
        assertEquals(TestSolution.PASSED,
                processPageTest("AW21.Test.10.01.01-1Passed-01").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW21.Test.10.01.01-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW21.Test.10.01.01-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW21.Test.10.01.01-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW21.Test.10.01.01-2Failed-04").getValue());
        // This testcase checks the presence of the isindex tag. Jsoup rewrite 
        // this tag as any browser would do, so this tag cannot be detected.
        // Thus, the solution of this test is PASSED
        assertEquals(TestSolution.FAILED,
                processPageTest("AW21.Test.10.01.01-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW21.Test.10.01.01-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW21.Test.10.01.01-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW21.Test.10.01.01-2Failed-08").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW21.Test.10.01.01-2Failed-09").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("AW21.Test.10.01.01-1Passed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.10.01.01-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.10.01.01-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.10.01.01-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.10.01.01-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.10.01.01-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.10.01.01-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.10.01.01-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.10.01.01-2Failed-08").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.10.01.01-2Failed-09").getValue());
    }

}
