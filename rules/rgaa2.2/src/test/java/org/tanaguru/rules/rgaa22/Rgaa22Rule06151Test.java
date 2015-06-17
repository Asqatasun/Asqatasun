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
 * Unit test class for the implementation of the rule 6.15 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule06151Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule06151Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa22.Rgaa22Rule06151");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("RGAA22.Test.6.15-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-1Passed-01.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-1Passed-02.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-1Passed-03.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-1Passed-04.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-1Passed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-1Passed-05.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-1Passed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-1Passed-06.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-1Passed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-1Passed-07.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-1Passed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-1Passed-08.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-1Passed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-1Passed-09.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-1Passed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-1Passed-10.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-1Passed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-1Passed-11.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-1Passed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-1Passed-12.html"));
        
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-01.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-02.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-03.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-04.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-05.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-06.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-07.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-08.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-09.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-10.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-11.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-12.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-13",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-13.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-14",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-14.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-15",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-15.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-16",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-16.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-17",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-17.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-18",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-18.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-19",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-19.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-20",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-20.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-21",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-21.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-2Failed-22",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-2Failed-22.html"));
        
        
        getWebResourceMap().put("RGAA22.Test.6.15-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-3NMI-01.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-3NMI-02.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-3NMI-03.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-3NMI-04.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-3NMI-05.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-3NMI-06.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-3NMI-07.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-3NMI-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-3NMI-08.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-3NMI-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-3NMI-09.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-3NMI-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-3NMI-10.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-3NMI-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-3NMI-11.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-3NMI-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-3NMI-12.html"));
        
        
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-01.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-02.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-03.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-04.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-05.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-06.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-07.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-08.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-09.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-10.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-11.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-12.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-13",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-13.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-14",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-14.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-15",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-15.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-16",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-16.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-17",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-17.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-18",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-18.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-19",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-19.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-20",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-20.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-21",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-21.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-22",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-22.html"));
        getWebResourceMap().put("RGAA22.Test.6.15-4NA-23",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule06151/RGAA22.Test.6.15-4NA-23.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("RGAA22.Test.6.15-1Passed-01");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-1Passed-02");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-1Passed-03");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-1Passed-04");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-1Passed-05");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-1Passed-06");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-1Passed-07");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-1Passed-08");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-1Passed-09");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-1Passed-10");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-1Passed-11");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("RGAA22.Test.6.15-1Passed-12");
        assertEquals(TestSolution.PASSED,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("RGAA22.Test.6.15-2Failed-01");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-02");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-03");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(4, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[2]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[3]).getMessageCode());
        
        processResult = processPageTest("RGAA22.Test.6.15-2Failed-04");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-05");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-06");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-07");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-08");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(4, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[2]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[3]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-09");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(4, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[2]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[3]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-10");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(4, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[2]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[3]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-11");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(4, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[2]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[3]).getMessageCode());
        
        processResult = processPageTest("RGAA22.Test.6.15-2Failed-12");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-13");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-14");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(4, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[2]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[3]).getMessageCode());
        
        processResult = processPageTest("RGAA22.Test.6.15-2Failed-15");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-16");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-17");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-18");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-19");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(4, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[2]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[3]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-20");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(4, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[2]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[3]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-21");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(4, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[2]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[3]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-2Failed-22");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(4, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[2]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[3]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-3NMI-01");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-3NMI-02");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-3NMI-03");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        
        processResult = processPageTest("RGAA22.Test.6.15-3NMI-04");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-3NMI-05");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-3NMI-06");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        
        processResult = processPageTest("RGAA22.Test.6.15-3NMI-07");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-3NMI-08");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-3NMI-09");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        
        processResult = processPageTest("RGAA22.Test.6.15-3NMI-10");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-3NMI-11");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-3NMI-12");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-03");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-04");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-05");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-06");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-07");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-08");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-09");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-10");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-11");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-12");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-13");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-14");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-15");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-16");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-17");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-18");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-19");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-20");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-21");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-22");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("RGAA22.Test.6.15-4NA-23");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.6.15-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.6.15-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.6.15-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.6.15-1Passed-04").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.6.15-1Passed-05").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.6.15-1Passed-06").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.6.15-1Passed-07").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.6.15-1Passed-08").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.6.15-1Passed-09").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.6.15-1Passed-10").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.6.15-1Passed-11").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("RGAA22.Test.6.15-1Passed-12").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-08").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-09").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-10").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-11").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-12").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-13").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-14").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-15").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-16").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-17").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-18").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-19").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-20").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-21").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.6.15-2Failed-22").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.15-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.15-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.15-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.15-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.15-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.15-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.15-3NMI-07").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.15-3NMI-08").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.15-3NMI-09").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.15-3NMI-10").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.15-3NMI-11").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.6.15-3NMI-12").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-09").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-10").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-11").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-12").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-13").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-14").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-15").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-16").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-17").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-18").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-19").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-20").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-21").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-22").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.6.15-4NA-23").getValue());
    }

}