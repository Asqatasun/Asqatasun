/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.rules.rgaa30;

import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 6-3-4 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule060304Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule060304Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule060304");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.06.03.04-2Failed-01");
        addWebResource("Rgaa30.Test.06.03.04-2Failed-02");
        addWebResource("Rgaa30.Test.06.03.04-2Failed-03");
        addWebResource("Rgaa30.Test.06.03.04-2Failed-04");
        addWebResource("Rgaa30.Test.06.03.04-2Failed-05");
        addWebResource("Rgaa30.Test.06.03.04-2Failed-06");
        addWebResource("Rgaa30.Test.06.03.04-2Failed-07");
        addWebResource("Rgaa30.Test.06.03.04-2Failed-08");
        addWebResource("Rgaa30.Test.06.03.04-2Failed-09");
        addWebResource("Rgaa30.Test.06.03.04-2Failed-10");
        addWebResource("Rgaa30.Test.06.03.04-2Failed-11");
        addWebResource("Rgaa30.Test.06.03.04-2Failed-12");
        addWebResource("Rgaa30.Test.06.03.04-3NMI-01");
        addWebResource("Rgaa30.Test.06.03.04-3NMI-02");
        addWebResource("Rgaa30.Test.06.03.04-3NMI-03");
        addWebResource("Rgaa30.Test.06.03.04-3NMI-04");
        addWebResource("Rgaa30.Test.06.03.04-3NMI-05");
        addWebResource("Rgaa30.Test.06.03.04-3NMI-06");
        addWebResource("Rgaa30.Test.06.03.04-3NMI-07");
        addWebResource("Rgaa30.Test.06.03.04-4NA-01");
        addWebResource("Rgaa30.Test.06.03.04-4NA-02");
        addWebResource("Rgaa30.Test.06.03.04-4NA-03");
        addWebResource("Rgaa30.Test.06.03.04-4NA-04");
        addWebResource("Rgaa30.Test.06.03.04-4NA-05");
        
        // 06.03.01 testcases
        getWebResourceMap().put("Rgaa30.Test.06.03.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060301/Rgaa30.Test.06.03.01-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060301/Rgaa30.Test.06.03.01-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060301/Rgaa30.Test.06.03.01-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060301/Rgaa30.Test.06.03.01-2Failed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.01-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060301/Rgaa30.Test.06.03.01-2Failed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.01-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060301/Rgaa30.Test.06.03.01-2Failed-06.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.01-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060301/Rgaa30.Test.06.03.01-2Failed-07.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060301/Rgaa30.Test.06.03.01-3NMI-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.01-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060301/Rgaa30.Test.06.03.01-3NMI-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.01-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060301/Rgaa30.Test.06.03.01-3NMI-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.01-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060301/Rgaa30.Test.06.03.01-3NMI-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.01-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060301/Rgaa30.Test.06.03.01-3NMI-05.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.01-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060301/Rgaa30.Test.06.03.01-3NMI-06.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.01-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060301/Rgaa30.Test.06.03.01-3NMI-07.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.01-3NMI-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060301/Rgaa30.Test.06.03.01-3NMI-08.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.01-3NMI-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060301/Rgaa30.Test.06.03.01-3NMI-09.html"));

        //06.03.02 testcases
        getWebResourceMap().put("Rgaa30.Test.06.03.02-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.02-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.02-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.02-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-2Failed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.02-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-2Failed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.02-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-2Failed-06.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.02-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-2Failed-07.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.02-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-2Failed-08.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.02-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-2Failed-09.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.02-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-2Failed-10.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.02-2Failed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-2Failed-11.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.02-2Failed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-2Failed-12.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.02-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-3NMI-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.02-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-3NMI-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.02-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-3NMI-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.02-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-3NMI-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.02-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-3NMI-05.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.02-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060302/Rgaa30.Test.06.03.02-3NMI-06.html"));

        //06.03.03 testcases
        getWebResourceMap().put("Rgaa30.Test.06.03.03-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060303/Rgaa30.Test.06.03.03-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.03-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060303/Rgaa30.Test.06.03.03-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.03-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060303/Rgaa30.Test.06.03.03-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.03-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060303/Rgaa30.Test.06.03.03-2Failed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.03-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060303/Rgaa30.Test.06.03.03-2Failed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.03-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060303/Rgaa30.Test.06.03.03-2Failed-06.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.03-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060303/Rgaa30.Test.06.03.03-2Failed-07.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.03-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060303/Rgaa30.Test.06.03.03-2Failed-08.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.03-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060303/Rgaa30.Test.06.03.03-3NMI-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.03-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060303/Rgaa30.Test.06.03.03-3NMI-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.03-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060303/Rgaa30.Test.06.03.03-3NMI-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.03-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060303/Rgaa30.Test.06.03.03-3NMI-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.03-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060303/Rgaa30.Test.06.03.03-3NMI-05.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.03-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060303/Rgaa30.Test.06.03.03-3NMI-06.html"));
        getWebResourceMap().put("Rgaa30.Test.06.03.03-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060303/Rgaa30.Test.06.03.03-3NMI-07.html"));

        
        //06.03.05 testcases
        getWebResourceMap().put("Rgaa30.Test.6.3.5-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060305/Rgaa30.Test.6.3.5-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.6.3.5-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060305/Rgaa30.Test.6.3.5-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.6.3.5-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060305/Rgaa30.Test.6.3.5-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.6.3.5-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060305/Rgaa30.Test.6.3.5-2Failed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.6.3.5-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060305/Rgaa30.Test.6.3.5-2Failed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.6.3.5-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060305/Rgaa30.Test.6.3.5-3NMI-01.html"));
        
        //06.05.01 testcases -> empty links
        getWebResourceMap().put("Rgaa30.Test.06.05.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060501/Rgaa30.Test.06.05.01-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.05.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060501/Rgaa30.Test.06.05.01-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.05.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060501/Rgaa30.Test.06.05.01-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.05.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060501/Rgaa30.Test.06.05.01-2Failed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.05.01-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060501/Rgaa30.Test.06.05.01-2Failed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.06.05.01-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060501/Rgaa30.Test.06.05.01-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("Rgaa30.Test.06.03.04-2Failed-01");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        processResult = processPageTest("Rgaa30.Test.06.03.04-2Failed-02");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        processResult = processPageTest("Rgaa30.Test.06.03.04-2Failed-03");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        processResult = processPageTest("Rgaa30.Test.06.03.04-2Failed-04");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        processResult = processPageTest("Rgaa30.Test.06.03.04-2Failed-05");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(3, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[2]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[2]).getIssue());

        processResult = processPageTest("Rgaa30.Test.06.03.04-2Failed-06");
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

        processResult = processPageTest("Rgaa30.Test.06.03.04-2Failed-07");
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

        processResult = processPageTest("Rgaa30.Test.06.03.04-2Failed-08");
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

        processResult = processPageTest("Rgaa30.Test.06.03.04-2Failed-09");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        processResult = processPageTest("Rgaa30.Test.06.03.04-2Failed-10");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        

        processResult = processPageTest("Rgaa30.Test.06.03.04-2Failed-11");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        processResult = processPageTest("Rgaa30.Test.06.03.04-2Failed-12");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        processResult = processPageTest("Rgaa30.Test.06.03.04-3NMI-01");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getIssue());

        processResult = processPageTest("Rgaa30.Test.06.03.04-3NMI-02");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getIssue());

        processResult = processPageTest("Rgaa30.Test.06.03.04-3NMI-03");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        processResult = processPageTest("Rgaa30.Test.06.03.04-3NMI-04");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getIssue());

        processResult = processPageTest("Rgaa30.Test.06.03.04-3NMI-05");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        processResult = processPageTest("Rgaa30.Test.06.03.04-3NMI-06");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        processResult = processPageTest("Rgaa30.Test.06.03.04-3NMI-07");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        processResult = processPageTest("Rgaa30.Test.06.03.04-4NA-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("Rgaa30.Test.06.03.04-4NA-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Rgaa30.Test.06.03.04-4NA-03");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Rgaa30.Test.06.03.04-4NA-04");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Rgaa30.Test.06.03.04-4NA-05");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        // 06.03.01 testcases : All is Not Applicable
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.01-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.01-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.01-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.01-2Failed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.01-2Failed-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.01-2Failed-06"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.01-2Failed-07"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.01-3NMI-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.01-3NMI-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.01-3NMI-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.01-3NMI-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.01-3NMI-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.01-3NMI-06"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.01-3NMI-07"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.01-3NMI-08"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.01-3NMI-09"));
        
        // 06.03.02 testcases : All is Not Applicable
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-2Failed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-2Failed-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-2Failed-06"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-2Failed-07"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-2Failed-08"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-2Failed-09"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-2Failed-10"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-2Failed-11"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-2Failed-12"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-3NMI-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-3NMI-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-3NMI-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-3NMI-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-3NMI-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.02-3NMI-06"));

        // 06.03.03 testcases : All is Not Applicable
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.03-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.03-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.03-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.03-2Failed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.03-2Failed-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.03-2Failed-06"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.03-2Failed-07"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.03-2Failed-08"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.03-3NMI-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.03-3NMI-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.03-3NMI-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.03-3NMI-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.03-3NMI-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.03-3NMI-06"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.03.03-3NMI-07"));
        
        // 6.3.5 testcases : All is Not Applicable
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.6.3.5-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.6.3.5-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.6.3.5-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.6.3.5-2Failed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.6.3.5-2Failed-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.6.3.5-3NMI-01"));
        
        // 06.05.01 testcases : All is Not Applicable
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.05.01-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.05.01-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.05.01-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.05.01-2Failed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.05.01-2Failed-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.05.01-4NA-01"));
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.03.04-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.03.04-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.03.04-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.03.04-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.03.04-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.03.04-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.03.04-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.03.04-2Failed-08").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.03.04-2Failed-09").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.03.04-2Failed-10").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.03.04-2Failed-11").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.03.04-2Failed-12").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.06.03.04-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.06.03.04-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.06.03.04-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.06.03.04-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.06.03.04-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.06.03.04-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.06.03.04-3NMI-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.04-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.04-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.04-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.04-4NA-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.04-4NA-05").getValue());
        
        // 06.03.01 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.01-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.01-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.01-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.01-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.01-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.01-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.01-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.01-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.01-3NMI-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.01-3NMI-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.01-3NMI-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.01-3NMI-09").getValue());
        
        // 06.03.02 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.02-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.02-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.02-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.03-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.02-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.02-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.02-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.02-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.02-2Failed-09").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.02-2Failed-10").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.02-2Failed-11").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.02-2Failed-12").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.02-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.02-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.02-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.02-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.02-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.02-3NMI-06").getValue());

        // 06.03.03 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.03-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.03-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.03-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.03-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.03-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.03-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.03-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.03-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.03-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.03-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.03-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.03-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.03-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.03-3NMI-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.03.03-3NMI-07").getValue());

        // 6.3.5 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.6.3.5-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.6.3.5-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.6.3.5-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.6.3.5-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.6.3.5-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.6.3.5-3NMI-01").getValue());

        // 06.05.01 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.05.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.05.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.05.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.05.01-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.05.01-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.05.01-4NA-01").getValue());
    }

}