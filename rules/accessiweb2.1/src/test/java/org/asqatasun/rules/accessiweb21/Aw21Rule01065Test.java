/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.rules.accessiweb21;

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.rules.accessiweb21.test.Aw21RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 1.6.5 of the referential Accessiweb 2.1.
 *
 * @author jkowalczyk
 */
public class Aw21Rule01065Test extends Aw21RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Aw21Rule01065Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.asqatasun.rules.accessiweb21.Aw21Rule01065");
    }

    @Override
    protected void setUpWebResourceMap() {
//        getWebResourceMap().put("Aw21.Test.1.6.5-1Passed-01",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "AW21/Aw21Rule01065/Aw21.Test.1.6.5-1Passed-01.html"));
//        getWebResourceMap().put("Aw21.Test.1.6.5-2Failed-01",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "AW21/Aw21Rule01065/Aw21.Test.1.6.5-2Failed-01.html"));
        getWebResourceMap().put("Aw21.Test.1.6.5-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule01065/Aw21.Test.1.6.5-3NMI-01.html"));
        getWebResourceMap().put("Aw21.Test.1.6.5-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "AW21/Aw21Rule01065/Aw21.Test.1.6.5-4NA-01.html"));
        getWebResourceMap().put("Aw21.Test.1.6.5-4NA-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "AW21/Aw21Rule01065/Aw21.Test.1.6.5-4NA-02.html"));
    }

    @Override
    protected void setProcess() {
//        assertEquals(TestSolution.PASSED,
//                processPageTest("Aw21.Test.1.6.5-1Passed-01").getValue());
//        assertEquals(TestSolution.FAILED,
//                processPageTest("Aw21.Test.1.6.5-2Failed-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                processPageTest("Aw21.Test.1.6.5-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Aw21.Test.1.6.5-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Aw21.Test.1.6.5-4NA-02").getValue());
    }

    @Override
    protected void setConsolidate() {
//        assertEquals(TestSolution.PASSED,
//                consolidate("Aw21.Test.1.6.5-1Passed-01").getValue());
//        assertEquals(TestSolution.FAILED,
//                consolidate("Aw21.Test.1.6.5-2Failed-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Aw21.Test.1.6.5-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Aw21.Test.1.6.5-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Aw21.Test.1.6.5-4NA-02").getValue());
    }

}