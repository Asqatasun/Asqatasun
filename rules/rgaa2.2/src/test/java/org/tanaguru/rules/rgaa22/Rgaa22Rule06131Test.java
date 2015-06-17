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
package org.tanaguru.rules.rgaa22;

import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 6.13 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule06131Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule06131Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa22.Rgaa22Rule06131");
    }

    @Override
    protected void setUpWebResourceMap() {
        /*------------------------------------2Failed------------------------------------------*/
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-01.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-02.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-03.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-04.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-05.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-06.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-07.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-08.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-09.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-10.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-11.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-12.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-13",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-13.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-14",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-14.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-15",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-15.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-16",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-16.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-17",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-17.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-18",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-18.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-19",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-19.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-20",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-20.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-21",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-21.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-22",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-22.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-23",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-23.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-24",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-24.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-25",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-25.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-26",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-26.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-27",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-27.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-28",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-28.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-29",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-29.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-30",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-30.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-31",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-31.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-32",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-32.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-33",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-33.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-34",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-34.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-35",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-35.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-36",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-36.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-37",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-37.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-38",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-38.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-39",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-39.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-2Failed-40",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-2Failed-40.html"));

        /*------------------------------------3NMI------------------------------------------*/
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-01.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-02.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-03.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-05.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-06.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-07.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-08.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-09.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-10.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-11.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-12.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-13",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-13.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-14",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-14.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-15",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-15.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-16",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-16.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-17",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-17.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-18",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-18.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-19",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-19.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-20",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-20.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-21",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-21.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-22",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-22.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-3NMI-23",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-3NMI-23.html"));

        /*------------------------------------4NA------------------------------------------*/
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-01.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-02.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-03.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-04.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-05.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-06.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-07.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-08.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-09.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-10.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-11.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-12.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-13",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-13.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-14",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-14.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-15",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-15.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-16",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-16.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-17",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-17.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-18",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-18.html"));
        getWebResourceMap().put("RGAA22.Test.6.13-4NA-19",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06131/RGAA22.Test.6.13-4NA-19.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult =
                processPageTest("RGAA22.Test.6.13-2Failed-01");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-02");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-03");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-04");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-05");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-06");
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
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-07");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-08");
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
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-09------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-09");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-10------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-10");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-11------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-11");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-12------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-12");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-13------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-13");
        assertEquals(3, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[2]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[2]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-14------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-14");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-15------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-15");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-16------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-16");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-17------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-17");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-18------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-18");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-19------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-19");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-20------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-20");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-21------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-21");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-22------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-22");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-23------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-23");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-24------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-24");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-25------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-25");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-26------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-26");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-27------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-27");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-28------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-28");
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
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-29------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-29");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-30------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-30");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-31------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-31");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-32------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-32");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-33------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-33");
        assertEquals(3, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[2]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[2]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-34------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-34");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-35------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-35");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-36------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-36");
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

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-37------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-37");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-38------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-38");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-39------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-39");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-40------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-2Failed-40");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.FAILED,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-01");
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

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-02");
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

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-03");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-05");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-06");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-07---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-07");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-08---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-08");
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

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-09---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-09");
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

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-10---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-10");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-11---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-11");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-12---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-12");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-13---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-13");
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

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-14---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-14");
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

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-15---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-15");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-16---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-16");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-17---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-17");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-18---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-18");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-19---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-19");
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

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-20---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-20");
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

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-21---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-21");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-22---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-22");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-23---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-3NMI-23");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getIssue());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-01");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-02");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-03");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-04");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-05");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-06");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-07");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-08");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-09------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-09");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-10------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-10");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-11------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-11");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-12------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-12");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-13------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-13");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-14------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-14");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-15------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-15");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-16------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-16");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-17------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-17");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-18------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-18");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------4NA-19------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.6.13-4NA-19");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-08").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-09").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-10").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-11").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-12").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-13").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-14").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-15").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-16").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-17").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-18").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-19").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-20").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-21").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-22").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-23").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-24").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-25").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-26").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-27").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-28").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-29").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-30").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-31").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-32").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-33").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-34").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-35").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-36").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-37").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-38").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-39").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.13-2Failed-40").getValue());

        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-07").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-08").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-09").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-10").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-11").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-12").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-13").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-14").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-15").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-16").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-17").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-18").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-19").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-20").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-21").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-22").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.13-3NMI-23").getValue());

        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-09").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-10").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-11").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-12").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-13").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-14").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-15").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-16").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-17").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-18").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.13-4NA-19").getValue());
    }

}