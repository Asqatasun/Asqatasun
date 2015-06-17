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
package org.tanaguru.rules.accessiweb22;

import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.accessiweb22.test.Aw22RuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class Aw22Rule09021Test extends Aw22RuleImplementationTestCase {
    
    public Aw22Rule09021Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb22.Aw22Rule09021");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW22.Test.9.2.1-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule09021/AW22.Test.9.2.1-3NMI-01.html"));
//        getWebResourceMap().put("AW22.Test.9.2.1-3NMI-02",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "accessiweb22/Aw22Rule09021/AW22.Test.9.2.1-3NMI-02.html"));
//        getWebResourceMap().put("AW22.Test.9.2.1-3NMI-03",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "accessiweb22/Aw22Rule09021/AW22.Test.9.2.1-3NMI-03.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("AW22.Test.9.2.1-3NMI-01");
        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
//        assertNull(processResult.getRemarkSet());
//
//        processResult = processPageTest("AW22.Test.9.2.1-3NMI-02");
//        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
//        assertEquals(2,processResult.getRemarkSet().size());
//        assertEquals("SuspectedNotWellFormattedUnordererdList",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
//        assertEquals("SuspectedNotWellFormattedUnordererdList",
//                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
//
//        processResult = processPageTest("AW22.Test.9.2.1-3NMI-03");
//        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
//        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW22.Test.9.2.1-3NMI-01").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("AW22.Test.9.2.1-3NMI-02").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("AW22.Test.9.2.1-3NMI-03").getValue());
    }

}
