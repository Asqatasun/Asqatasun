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
package org.tanaguru.rules.accessiweb22;

import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.accessiweb22.test.Aw22RuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class Aw22Rule09022Test extends Aw22RuleImplementationTestCase {
    
    public Aw22Rule09022Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb22.Aw22Rule09022");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW22.Test.9.2.2-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09022/AW22.Test.9.2.2-3NMI-01.html"));
//        getWebResourceMap().put("AW22.Test.9.2.2-3NMI-02",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "accessiweb22/Aw22Rule09022/AW22.Test.9.2.2-3NMI-02.html"));
//        getWebResourceMap().put("AW22.Test.9.2.2-3NMI-03",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "accessiweb22/Aw22Rule09022/AW22.Test.9.2.2-3NMI-03.html"));
//        getWebResourceMap().put("AW22.Test.9.2.2-3NMI-04",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "accessiweb22/Aw22Rule09022/AW22.Test.9.2.2-3NMI-04.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("AW22.Test.9.2.2-3NMI-01");
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
//        assertNull(processResult.getRemarkSet());
//
//        processResult = processPageTest("AW22.Test.9.2.2-3NMI-02");
//        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
//        assertEquals(9,processResult.getRemarkSet().size());
//        assertEquals("SuspectedNotWellFormattedOrdererdList",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
//        assertEquals("SuspectedNotWellFormattedOrdererdList",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
//        assertEquals("SuspectedNotWellFormattedOrdererdList",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[2]).getMessageCode());
//        assertEquals("SuspectedNotWellFormattedOrdererdList",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[3]).getMessageCode());
//        assertEquals("SuspectedNotWellFormattedOrdererdList",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[4]).getMessageCode());
//        assertEquals("SuspectedNotWellFormattedOrdererdList",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[5]).getMessageCode());
//        assertEquals("SuspectedNotWellFormattedOrdererdList",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[6]).getMessageCode());
//        assertEquals("SuspectedNotWellFormattedOrdererdList",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[7]).getMessageCode());
//        assertEquals("SuspectedNotWellFormattedOrdererdList",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[8]).getMessageCode());
//
//        processResult = processPageTest("AW22.Test.9.2.2-3NMI-03");
//        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
//        assertNull(processResult.getRemarkSet());
//
//        processResult = processPageTest("AW22.Test.9.2.2-3NMI-04");
//        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
//        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW22.Test.9.2.2-3NMI-01").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("AW22.Test.9.2.2-3NMI-02").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("AW22.Test.9.2.2-3NMI-03").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("AW22.Test.9.2.2-3NMI-04").getValue());
    }

}
