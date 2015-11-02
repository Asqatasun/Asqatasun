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
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 *
 * @author jkowalczyk
 */
public class Aw22Rule06023Test extends Aw22RuleImplementationTestCase {

    public Aw22Rule06023Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb22.Aw22Rule06023");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW22.Test.06.02.03-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06023/AW22.Test.06.02.03-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.06.02.03-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06023/AW22.Test.06.02.03-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.06.02.03-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06023/AW22.Test.06.02.03-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.06.02.03-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06023/AW22.Test.06.02.03-3NMI-01.html"));
        getWebResourceMap().put("AW22.Test.06.02.03-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06023/AW22.Test.06.02.03-3NMI-02.html"));
        getWebResourceMap().put("AW22.Test.06.02.03-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06023/AW22.Test.06.02.03-3NMI-03.html"));
        getWebResourceMap().put("AW22.Test.06.02.03-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06023/AW22.Test.06.02.03-4NA-01.html"));
        getWebResourceMap().put("AW22.Test.06.02.03-4NA-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06023/AW22.Test.06.02.03-4NA-02.html"));
        getWebResourceMap().put("AW22.Test.06.02.03-4NA-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06023/AW22.Test.06.02.03-4NA-03.html"));
        getWebResourceMap().put("AW22.Test.06.02.03-4NA-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06023/AW22.Test.06.02.03-4NA-04.html"));
        
        //06.02.01 testcases
        getWebResourceMap().put("AW22.Test.06.02.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06021/AW22.Test.06.02.01-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.06.02.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06021/AW22.Test.06.02.01-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.06.02.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06021/AW22.Test.06.02.01-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.06.02.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06021/AW22.Test.06.02.01-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.06.02.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06021/AW22.Test.06.02.01-3NMI-01.html"));
        getWebResourceMap().put("AW22.Test.06.02.01-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06021/AW22.Test.06.02.01-3NMI-02.html"));
        getWebResourceMap().put("AW22.Test.06.02.01-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06021/AW22.Test.06.02.01-4NA-01.html"));
        getWebResourceMap().put("AW22.Test.06.02.01-4NA-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06021/AW22.Test.06.02.01-4NA-02.html"));
        getWebResourceMap().put("AW22.Test.06.02.01-4NA-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06021/AW22.Test.06.02.01-4NA-03.html"));
        getWebResourceMap().put("AW22.Test.06.02.01-4NA-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06021/AW22.Test.06.02.01-4NA-04.html"));
        
        //06.02.02 testcases
        getWebResourceMap().put("AW22.Test.06.02.02-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-3NMI-01.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-3NMI-02.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-3NMI-03.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-3NMI-04.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-3NMI-05.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-3NMI-06.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-4NA-01.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-4NA-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-4NA-02.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-4NA-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-4NA-03.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-4NA-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-4NA-04.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-4NA-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-4NA-05.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-4NA-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-4NA-06.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-4NA-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-4NA-07.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-4NA-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-4NA-08.html"));
        getWebResourceMap().put("AW22.Test.06.02.02-4NA-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06022/AW22.Test.06.02.02-4NA-09.html"));

        //06.02.04 testcases
        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-05.html"));
        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-06.html"));
        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-07.html"));
        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-08.html"));
        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-09.html"));
        getWebResourceMap().put("AW22.Test.06.02.04-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06024/AW22.Test.06.02.04-2Failed-10.html"));
        getWebResourceMap().put("AW22.Test.06.02.04-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06024/AW22.Test.06.02.04-3NMI-01.html"));
        getWebResourceMap().put("AW22.Test.06.02.04-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06024/AW22.Test.06.02.04-3NMI-02.html"));
        getWebResourceMap().put("AW22.Test.06.02.04-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06024/AW22.Test.06.02.04-3NMI-03.html"));
        getWebResourceMap().put("AW22.Test.06.02.04-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06024/AW22.Test.06.02.04-3NMI-04.html"));
        getWebResourceMap().put("AW22.Test.06.02.04-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06024/AW22.Test.06.02.04-3NMI-05.html"));
        getWebResourceMap().put("AW22.Test.06.02.04-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06024/AW22.Test.06.02.04-3NMI-06.html"));
        
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
        ProcessResult processResult = processPageTest("AW22.Test.06.02.03-2Failed-02");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(RemarkMessageStore.EMPTY_LINK_TITLE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW22.Test.06.02.03-2Failed-03");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(RemarkMessageStore.NOT_PERTINENT_LINK_TITLE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW22.Test.06.02.03-2Failed-04");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(RemarkMessageStore.NOT_PERTINENT_LINK_TITLE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW22.Test.06.02.03-3NMI-01");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(RemarkMessageStore.SUSPECTED_NOT_PERTINENT_LINK_TITLE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW22.Test.06.02.03-3NMI-02");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(RemarkMessageStore.SUSPECTED_PERTINENT_LINK_TITLE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW22.Test.06.02.03-3NMI-03");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals(RemarkMessageStore.SUSPECTED_PERTINENT_LINK_TITLE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW22.Test.06.02.03-4NA-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("AW22.Test.06.02.03-4NA-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("AW22.Test.06.02.03-4NA-03");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("AW22.Test.06.02.03-4NA-04");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        // 06.02.01 testcases : All is Not Applicable
        processResult = processPageTest("AW22.Test.06.02.01-2Failed-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.01-2Failed-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.01-2Failed-03");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.01-2Failed-04");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.01-3NMI-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.01-3NMI-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        // 06.02.02 testcases : All is Not Applicable
        processResult = processPageTest("AW22.Test.06.02.02-2Failed-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.02-2Failed-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.02-2Failed-03");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.02-2Failed-04");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.02-3NMI-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.02-3NMI-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.02-3NMI-03");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.02-3NMI-04");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.02-3NMI-05");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.02-3NMI-06");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        // 06.02.04 testcases : All is Not Applicable
        processResult = processPageTest("AW22.Test.06.02.04-2Failed-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.04-2Failed-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.04-2Failed-03");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.04-2Failed-04");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.04-2Failed-05");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.04-2Failed-06");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.04-2Failed-07");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.04-2Failed-08");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
                
        processResult = processPageTest("AW22.Test.06.02.04-2Failed-09");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.04-2Failed-10");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.04-3NMI-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.04-3NMI-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.04-3NMI-03");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.04-3NMI-04");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.04-3NMI-05");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.02.04-3NMI-06");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
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
                consolidate("AW22.Test.06.02.03-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.02.03-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.02.03-2Failed-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.06.02.03-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.06.02.03-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.06.02.03-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.03-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.03-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.03-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.03-4NA-04").getValue());

        // 06.02.01 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.01-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.01-3NMI-02").getValue());
        
        // 06.02.02 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.02-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.02-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.02-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.02-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.02-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.02-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.02-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.02-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.02-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.02-3NMI-06").getValue());
        
        // 06.02.04 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.04-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.04-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.04-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.04-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.04-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.04-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.04-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.04-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.04-2Failed-09").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.04-2Failed-10").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.04-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.04-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.04-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.04-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.04-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.02.04-3NMI-06").getValue());        
        
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
