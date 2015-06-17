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
public class Aw21Rule07051Test extends Aw21RuleImplementationTestCase {

    public Aw21Rule07051Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb21.Aw21Rule07051");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW21.Test.07.05.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule07051/AW21.Test.07.05.01-3NMI-01.html"));
        getWebResourceMap().put("AW21.Test.07.05.01-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule07051/AW21.Test.07.05.01-3NMI-02.html"));
       getWebResourceMap().put("AW21.Test.07.05.01-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule07051/AW21.Test.07.05.01-3NMI-03.html"));
       getWebResourceMap().put("AW21.Test.07.05.01-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule07051/AW21.Test.07.05.01-3NMI-04.html"));
       getWebResourceMap().put("AW21.Test.07.05.01-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule07051/AW21.Test.07.05.01-3NMI-05.html"));
       getWebResourceMap().put("AW21.Test.07.05.01-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule07051/AW21.Test.07.05.01-3NMI-06.html"));
       getWebResourceMap().put("AW21.Test.07.05.01-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule07051/AW21.Test.07.05.01-3NMI-07.html"));
       getWebResourceMap().put("AW21.Test.07.05.01-3NMI-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule07051/AW21.Test.07.05.01-3NMI-08.html"));
       getWebResourceMap().put("AW21.Test.07.05.01-3NMI-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule07051/AW21.Test.07.05.01-3NMI-09.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("AW21.Test.07.05.01-3NMI-01");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("AW21.Test.07.05.01-3NMI-02");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        assertEquals(Aw21Rule07051.ERROR_MESSAGE_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(1, processResult.getRemarkSet().size());

        processResult = processPageTest("AW21.Test.07.05.01-3NMI-03");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        assertEquals(Aw21Rule07051.ERROR_MESSAGE_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(1, processResult.getRemarkSet().size());

        processResult = processPageTest("AW21.Test.07.05.01-3NMI-04");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        assertEquals(Aw21Rule07051.ERROR_MESSAGE_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(1, processResult.getRemarkSet().size());

        processResult = processPageTest("AW21.Test.07.05.01-3NMI-05");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("AW21.Test.07.05.01-3NMI-06");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("AW21.Test.07.05.01-3NMI-07");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("AW21.Test.07.05.01-3NMI-08");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("AW21.Test.07.05.01-3NMI-09");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        assertEquals(Aw21Rule07051.ERROR_MESSAGE_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(1, processResult.getRemarkSet().size());


    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.07.05.01-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.07.05.01-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.07.05.01-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.07.05.01-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.07.05.01-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.07.05.01-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.07.05.01-3NMI-07").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.07.05.01-3NMI-08").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.07.05.01-3NMI-09").getValue());
    }

}
