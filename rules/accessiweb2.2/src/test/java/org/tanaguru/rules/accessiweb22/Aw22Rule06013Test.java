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
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 *
 * @author jkowalczyk
 */
public class Aw22Rule06013Test extends Aw22RuleImplementationTestCase {

    public Aw22Rule06013Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb22.Aw22Rule06013");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW22.Test.06.01.03-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.06.01.03-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.06.01.03-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.06.01.03-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.06.01.03-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-2Failed-05.html"));
        getWebResourceMap().put("AW22.Test.06.01.03-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-2Failed-06.html"));
        getWebResourceMap().put("AW22.Test.06.01.03-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-2Failed-07.html"));
        getWebResourceMap().put("AW22.Test.06.01.03-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-2Failed-08.html"));
        getWebResourceMap().put("AW22.Test.06.01.03-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-3NMI-01.html"));
        getWebResourceMap().put("AW22.Test.06.01.03-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-3NMI-02.html"));
        getWebResourceMap().put("AW22.Test.06.01.03-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-3NMI-03.html"));
        getWebResourceMap().put("AW22.Test.06.01.03-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-3NMI-05.html"));
        getWebResourceMap().put("AW22.Test.06.01.03-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-3NMI-06.html"));
        getWebResourceMap().put("AW22.Test.06.01.03-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-3NMI-07.html"));
        getWebResourceMap().put("AW22.Test.06.01.03-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-4NA-01.html"));
        getWebResourceMap().put("AW22.Test.06.01.03-4NA-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-4NA-02.html"));
        getWebResourceMap().put("AW22.Test.06.01.03-4NA-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-4NA-03.html"));
        getWebResourceMap().put("AW22.Test.06.01.03-4NA-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06013/AW22.Test.06.01.03-4NA-04.html"));

        // 06.01.01 testcases
        getWebResourceMap().put("AW22.Test.06.01.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06011/AW22.Test.06.01.01-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.06.01.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06011/AW22.Test.06.01.01-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.06.01.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06011/AW22.Test.06.01.01-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.06.01.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06011/AW22.Test.06.01.01-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.06.01.01-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06011/AW22.Test.06.01.01-2Failed-05.html"));
        getWebResourceMap().put("AW22.Test.06.01.01-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06011/AW22.Test.06.01.01-2Failed-06.html"));
        getWebResourceMap().put("AW22.Test.06.01.01-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06011/AW22.Test.06.01.01-2Failed-07.html"));
        getWebResourceMap().put("AW22.Test.06.01.01-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06011/AW22.Test.06.01.01-2Failed-08.html"));
        getWebResourceMap().put("AW22.Test.06.01.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06011/AW22.Test.06.01.01-3NMI-01.html"));
        getWebResourceMap().put("AW22.Test.06.01.01-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06011/AW22.Test.06.01.01-3NMI-02.html"));
        getWebResourceMap().put("AW22.Test.06.01.01-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06011/AW22.Test.06.01.01-3NMI-03.html"));
        getWebResourceMap().put("AW22.Test.06.01.01-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06011/AW22.Test.06.01.01-3NMI-05.html"));
        getWebResourceMap().put("AW22.Test.06.01.01-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06011/AW22.Test.06.01.01-3NMI-06.html"));
        getWebResourceMap().put("AW22.Test.06.01.01-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06011/AW22.Test.06.01.01-3NMI-07.html"));

        //06.01.02 testcases
        getWebResourceMap().put("AW22.Test.06.01.02-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06012/AW22.Test.06.01.02-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.06.01.02-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06012/AW22.Test.06.01.02-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.06.01.02-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06012/AW22.Test.06.01.02-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.06.01.02-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06012/AW22.Test.06.01.02-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.06.01.02-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06012/AW22.Test.06.01.02-2Failed-05.html"));
        getWebResourceMap().put("AW22.Test.06.01.02-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06012/AW22.Test.06.01.02-2Failed-06.html"));
        getWebResourceMap().put("AW22.Test.06.01.02-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06012/AW22.Test.06.01.02-2Failed-07.html"));
        getWebResourceMap().put("AW22.Test.06.01.02-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06012/AW22.Test.06.01.02-2Failed-08.html"));
        getWebResourceMap().put("AW22.Test.06.01.02-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06012/AW22.Test.06.01.02-2Failed-09.html"));
        getWebResourceMap().put("AW22.Test.06.01.02-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06012/AW22.Test.06.01.02-2Failed-10.html"));
        getWebResourceMap().put("AW22.Test.06.01.02-2Failed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06012/AW22.Test.06.01.02-2Failed-11.html"));
        getWebResourceMap().put("AW22.Test.06.01.02-2Failed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06012/AW22.Test.06.01.02-2Failed-12.html"));
        getWebResourceMap().put("AW22.Test.06.01.02-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06012/AW22.Test.06.01.02-3NMI-01.html"));
        getWebResourceMap().put("AW22.Test.06.01.02-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06012/AW22.Test.06.01.02-3NMI-02.html"));
        getWebResourceMap().put("AW22.Test.06.01.02-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06012/AW22.Test.06.01.02-3NMI-03.html"));
        getWebResourceMap().put("AW22.Test.06.01.02-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06012/AW22.Test.06.01.02-3NMI-05.html"));
        getWebResourceMap().put("AW22.Test.06.01.02-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06012/AW22.Test.06.01.02-3NMI-06.html"));
        
        //06.01.04 testcases
        getWebResourceMap().put("AW22.Test.06.01.04-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06014/AW22.Test.06.01.04-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.06.01.04-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06014/AW22.Test.06.01.04-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.06.01.04-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06014/AW22.Test.06.01.04-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.06.01.04-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06014/AW22.Test.06.01.04-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.06.01.04-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06014/AW22.Test.06.01.04-2Failed-05.html"));
        getWebResourceMap().put("AW22.Test.06.01.04-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06014/AW22.Test.06.01.04-2Failed-06.html"));
        getWebResourceMap().put("AW22.Test.06.01.04-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06014/AW22.Test.06.01.04-2Failed-07.html"));
        getWebResourceMap().put("AW22.Test.06.01.04-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06014/AW22.Test.06.01.04-2Failed-08.html"));
        getWebResourceMap().put("AW22.Test.06.01.04-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06014/AW22.Test.06.01.04-2Failed-09.html"));
        getWebResourceMap().put("AW22.Test.06.01.04-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06014/AW22.Test.06.01.04-2Failed-10.html"));
        getWebResourceMap().put("AW22.Test.06.01.04-2Failed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06014/AW22.Test.06.01.04-2Failed-11.html"));
        getWebResourceMap().put("AW22.Test.06.01.04-2Failed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06014/AW22.Test.06.01.04-2Failed-12.html"));
        getWebResourceMap().put("AW22.Test.06.01.04-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06014/AW22.Test.06.01.04-3NMI-01.html"));
        getWebResourceMap().put("AW22.Test.06.01.04-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06014/AW22.Test.06.01.04-3NMI-02.html"));
        getWebResourceMap().put("AW22.Test.06.01.04-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06014/AW22.Test.06.01.04-3NMI-03.html"));
        getWebResourceMap().put("AW22.Test.06.01.04-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06014/AW22.Test.06.01.04-3NMI-04.html"));
        getWebResourceMap().put("AW22.Test.06.01.04-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06014/AW22.Test.06.01.04-3NMI-05.html"));
        
        //06.06.01 testcases -> empty links
        getWebResourceMap().put("AW22.Test.06.06.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-2Failed-05.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("AW22.Test.06.01.03-2Failed-01");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getIssue());

        processResult = processPageTest("AW22.Test.06.01.03-2Failed-02");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getIssue());

        processResult = processPageTest("AW22.Test.06.01.03-2Failed-03");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getIssue());

        processResult = processPageTest("AW22.Test.06.01.03-2Failed-04");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getIssue());

        processResult = processPageTest("AW22.Test.06.01.03-2Failed-05");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getIssue());

        processResult = processPageTest("AW22.Test.06.01.03-2Failed-06");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getIssue());

        processResult = processPageTest("AW22.Test.06.01.03-2Failed-07");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getIssue());

        processResult = processPageTest("AW22.Test.06.01.03-2Failed-08");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getIssue());

        processResult = processPageTest("AW22.Test.06.01.03-3NMI-01");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getIssue());

        processResult = processPageTest("AW22.Test.06.01.03-3NMI-02");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getIssue());

        processResult = processPageTest("AW22.Test.06.01.03-3NMI-03");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        processResult = processPageTest("AW22.Test.06.01.03-3NMI-05");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        processResult = processPageTest("AW22.Test.06.01.03-3NMI-06");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        processResult = processPageTest("AW22.Test.06.01.03-3NMI-07");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        
        processResult = processPageTest("AW22.Test.06.01.03-4NA-01");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.03-4NA-02");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.03-4NA-03");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.03-4NA-04");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        // 06.01.01 testcases : All is Not Applicable
        processResult = processPageTest("AW22.Test.06.01.01-2Failed-01");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.01-2Failed-02");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.01-2Failed-03");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.01-2Failed-04");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.01-2Failed-05");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.01-2Failed-06");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.01-2Failed-07");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.01-2Failed-08");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.01-3NMI-01");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.01-3NMI-02");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.01-3NMI-03");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.01-3NMI-05");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.01-3NMI-06");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.01-3NMI-07");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        // 06.01.02 testcases : All is Not Applicable
        processResult = processPageTest("AW22.Test.06.01.02-2Failed-01");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.02-2Failed-02");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.02-2Failed-03");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.02-2Failed-04");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.02-2Failed-05");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.02-2Failed-06");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.02-2Failed-07");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.02-2Failed-08");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.02-2Failed-09");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.02-2Failed-10");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.02-2Failed-11");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.02-2Failed-12");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.02-3NMI-01");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.02-3NMI-02");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.02-3NMI-03");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.02-3NMI-05");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.02-3NMI-06");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        // 06.01.04 testcases : All is Not Applicable
        processResult = processPageTest("AW22.Test.06.01.04-2Failed-01");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.04-2Failed-02");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.04-2Failed-03");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.04-2Failed-04");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.04-2Failed-05");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.04-2Failed-06");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.04-2Failed-07");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.04-2Failed-08");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.04-2Failed-09");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.04-2Failed-10");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.04-2Failed-11");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.04-2Failed-12");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.04-3NMI-01");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.04-3NMI-02");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.04-3NMI-03");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.04-3NMI-04");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.01.04-3NMI-05");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        // 06.06.01 testcases : All is Not Applicable
        processResult = processPageTest("AW22.Test.06.06.01-2Failed-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.06.01-2Failed-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.06.01-2Failed-03");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.06.01-2Failed-04");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.06.01-2Failed-05");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.06.01-4NA-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.01.03-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.01.03-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.01.03-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.01.03-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.01.03-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.01.03-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.01.03-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.01.03-2Failed-08").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.06.01.03-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.06.01.03-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.06.01.03-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.06.01.03-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.06.01.03-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.06.01.03-3NMI-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.03-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.03-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.03-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.03-4NA-04").getValue());

        // 06.01.01 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.01-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.01-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.01-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.01-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.01-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.01-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.01-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.01-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.01-3NMI-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.01-3NMI-07").getValue());

        // 06.01.02 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.02-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.02-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.02-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.02-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.02-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.02-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.02-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.02-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.02-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.02-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.02-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.02-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.02-3NMI-06").getValue());

        // 06.01.04 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.04-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.04-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.04-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.04-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.04-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.04-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.04-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.04-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.04-2Failed-09").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.04-2Failed-10").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.04-2Failed-11").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.04-2Failed-12").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.04-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.04-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.04-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.04-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.01.04-3NMI-05").getValue());
                
        // 06.06.01 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.06.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.06.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.06.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.06.01-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.06.01-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.06.01-4NA-01").getValue());
    }
}
