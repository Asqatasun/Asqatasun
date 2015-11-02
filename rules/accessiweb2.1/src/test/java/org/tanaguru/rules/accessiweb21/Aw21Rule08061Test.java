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
public class Aw21Rule08061Test extends Aw21RuleImplementationTestCase {

    public Aw21Rule08061Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb21.Aw21Rule08061");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW21.Test.08.06.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08061/AW21.Test.08.06.01-2Failed-01.html"));

        getWebResourceMap().put("AW21.Test.08.06.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08061/AW21.Test.08.06.01-2Failed-02.html"));

        getWebResourceMap().put("AW21.Test.08.06.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08061/AW21.Test.08.06.01-2Failed-03.html"));

        getWebResourceMap().put("AW21.Test.08.06.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08061/AW21.Test.08.06.01-3NMI-01.html"));

        getWebResourceMap().put("AW21.Test.08.06.01-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule08061/AW21.Test.08.06.01-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        assertEquals(TestSolution.FAILED,
                processPageTest("AW21.Test.08.06.01-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW21.Test.08.06.01-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW21.Test.08.06.01-2Failed-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                processPageTest("AW21.Test.08.06.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.08.06.01-4NA-01").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.08.06.01-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.08.06.01-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW21.Test.08.06.01-2Failed-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.08.06.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.08.06.01-4NA-01").getValue());
    }
}
