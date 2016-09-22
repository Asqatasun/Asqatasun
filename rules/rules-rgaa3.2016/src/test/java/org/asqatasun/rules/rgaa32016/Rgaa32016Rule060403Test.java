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
package org.asqatasun.rules.rgaa32016;

import org.asqatasun.entity.audit.ProcessRemark;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.rules.rgaa32016.test.Rgaa32016RuleImplementationTestCase;
import org.asqatasun.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 6.4.3 of the referential RGAA 3.2016
 *
 * @author jkowalczyk
 */
public class Rgaa32016Rule060403Test extends Rgaa32016RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa32016Rule060403Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa32016.Rgaa32016Rule060403");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa32016.Test.06.04.04-1Passed-01");
        addWebResource("Rgaa32016.Test.06.04.04-1Passed-02");
        addWebResource("Rgaa32016.Test.06.04.04-1Passed-03");
        addWebResource("Rgaa32016.Test.06.04.04-1Passed-04");
        addWebResource("Rgaa32016.Test.06.04.04-2Failed-01");
        addWebResource("Rgaa32016.Test.06.04.04-2Failed-02");
        addWebResource("Rgaa32016.Test.06.04.04-2Failed-03");
        addWebResource("Rgaa32016.Test.06.04.04-2Failed-04");
        addWebResource("Rgaa32016.Test.06.04.04-2Failed-05");
        addWebResource("Rgaa32016.Test.06.04.04-2Failed-06");
        addWebResource("Rgaa32016.Test.06.04.04-2Failed-07");
        addWebResource("Rgaa32016.Test.06.04.04-2Failed-08");
        addWebResource("Rgaa32016.Test.06.04.04-3NMI-01");
        addWebResource("Rgaa32016.Test.06.04.04-3NMI-02");
        addWebResource("Rgaa32016.Test.06.04.04-3NMI-03");
        addWebResource("Rgaa32016.Test.06.04.04-4NA-01");
        addWebResource("Rgaa32016.Test.06.04.04-4NA-02");
        addWebResource("Rgaa32016.Test.06.04.04-4NA-03");
        addWebResource("Rgaa32016.Test.06.04.04-4NA-04");
        addWebResource("Rgaa32016.Test.06.04.04-4NA-05");
        addWebResource("Rgaa32016.Test.06.04.04-4NA-06");
        
        // 06.04.01 testcases
        getWebResourceMap().put("Rgaa32016.Test.06.04.01-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060401/Rgaa32016.Test.06.04.01-1Passed-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.01-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060401/Rgaa32016.Test.06.04.01-1Passed-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060401/Rgaa32016.Test.06.04.01-2Failed-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060401/Rgaa32016.Test.06.04.01-2Failed-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060401/Rgaa32016.Test.06.04.01-2Failed-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060401/Rgaa32016.Test.06.04.01-3NMI-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.01-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060401/Rgaa32016.Test.06.04.01-3NMI-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.01-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060401/Rgaa32016.Test.06.04.01-3NMI-03.html"));
        
        //06.04.02 testcases
        getWebResourceMap().put("Rgaa32016.Test.06.04.02-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060402/Rgaa32016.Test.06.04.02-1Passed-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.02-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060402/Rgaa32016.Test.06.04.02-1Passed-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.02-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060402/Rgaa32016.Test.06.04.02-1Passed-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.02-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060402/Rgaa32016.Test.06.04.02-1Passed-04.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.02-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060402/Rgaa32016.Test.06.04.02-2Failed-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.02-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060402/Rgaa32016.Test.06.04.02-2Failed-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.02-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060402/Rgaa32016.Test.06.04.02-2Failed-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.02-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060402/Rgaa32016.Test.06.04.02-2Failed-04.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.02-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060402/Rgaa32016.Test.06.04.02-2Failed-05.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.02-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060402/Rgaa32016.Test.06.04.02-2Failed-06.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.02-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060402/Rgaa32016.Test.06.04.02-2Failed-07.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.02-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060402/Rgaa32016.Test.06.04.02-2Failed-08.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.02-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060402/Rgaa32016.Test.06.04.02-3NMI-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.02-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060402/Rgaa32016.Test.06.04.02-3NMI-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.02-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060402/Rgaa32016.Test.06.04.02-3NMI-03.html"));

        // 06.04.03 testcases
        getWebResourceMap().put("Rgaa32016.Test.06.04.03-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060403/Rgaa32016.Test.06.04.03-1Passed-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.03-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060403/Rgaa32016.Test.06.04.03-1Passed-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.03-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060403/Rgaa32016.Test.06.04.03-2Failed-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.03-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060403/Rgaa32016.Test.06.04.03-2Failed-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.03-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060403/Rgaa32016.Test.06.04.03-2Failed-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.03-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060403/Rgaa32016.Test.06.04.03-3NMI-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.03-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060403/Rgaa32016.Test.06.04.03-3NMI-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.04.03-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060403/Rgaa32016.Test.06.04.03-3NMI-03.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("Rgaa32016.Test.06.04.04-1Passed-01");
        assertEquals(TestSolution.PASSED,processResult.getValue());

        processResult = processPageTest("Rgaa32016.Test.06.04.04-1Passed-02");
        assertEquals(TestSolution.PASSED,processResult.getValue());

        processResult = processPageTest("Rgaa32016.Test.06.04.04-1Passed-03");
        assertEquals(TestSolution.PASSED,processResult.getValue());

        processResult = processPageTest("Rgaa32016.Test.06.04.04-1Passed-04");
        assertEquals(TestSolution.PASSED,processResult.getValue());

        processResult = processPageTest("Rgaa32016.Test.06.04.04-2Failed-01");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Rgaa32016.Test.06.04.04-2Failed-02");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Rgaa32016.Test.06.04.04-2Failed-03");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Rgaa32016.Test.06.04.04-2Failed-04");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Rgaa32016.Test.06.04.04-2Failed-05");
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

        processResult = processPageTest("Rgaa32016.Test.06.04.04-2Failed-06");
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

        processResult = processPageTest("Rgaa32016.Test.06.04.04-2Failed-07");
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

        processResult = processPageTest("Rgaa32016.Test.06.04.04-2Failed-08");
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

        processResult = processPageTest("Rgaa32016.Test.06.04.04-3NMI-01");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Rgaa32016.Test.06.04.04-3NMI-02");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Rgaa32016.Test.06.04.04-3NMI-03");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Rgaa32016.Test.06.04.04-4NA-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Rgaa32016.Test.06.04.04-4NA-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Rgaa32016.Test.06.04.04-4NA-03");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Rgaa32016.Test.06.04.04-4NA-04");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Rgaa32016.Test.06.04.04-4NA-05");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Rgaa32016.Test.06.04.04-4NA-06");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        // 06.04.01 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.01-1Passed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.01-1Passed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.01-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.01-3NMI-03").getValue());

        // 06.04.02 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.02-1Passed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.02-1Passed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.02-1Passed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.02-1Passed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.02-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.02-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.02-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.02-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.02-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.02-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.02-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.02-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.02-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.02-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.02-3NMI-03").getValue());

        // 06.04.03 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.03-1Passed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.03-1Passed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.03-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.03-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.03-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.03-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.03-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Rgaa32016.Test.06.04.03-3NMI-03").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa32016.Test.06.04.04-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa32016.Test.06.04.04-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa32016.Test.06.04.04-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa32016.Test.06.04.04-1Passed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.04.04-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.04.04-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.04.04-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.04.04-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.04.04-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.04.04-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.04.04-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.04.04-2Failed-08").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.04.04-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.04.04-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.04.04-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.04-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.04-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.04-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.04-4NA-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.04-4NA-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.04-4NA-06").getValue());

        // 06.04.01 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.01-1Passed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.01-1Passed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.01-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.01-3NMI-03").getValue());

        // 06.04.02 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.02-1Passed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.02-1Passed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.02-1Passed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.02-1Passed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.02-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.02-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.02-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.02-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.02-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.02-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.02-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.02-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.02-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.02-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.02-3NMI-03").getValue());
        
        // 06.04.03 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.03-1Passed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.03-1Passed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.03-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.03-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.03-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.03-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.03-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.04.03-3NMI-03").getValue());
    }

}
