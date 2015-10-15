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
package org.tanaguru.rules.seo;

import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import static org.tanaguru.rules.keystore.RemarkMessageStore.FLASH_CONTENT_DETECTED_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.SUSPECTED_FLASH_CONTENT_DETECTED_MSG;
import org.tanaguru.rules.seo.test.SeoRuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class SeoRule08011Test extends SeoRuleImplementationTestCase {

    public SeoRule08011Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.seo.SeoRule08011");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Seo.Test.8.1-1Passed-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule08011/Seo.Test.8.1-1Passed-01.html"));

        getWebResourceMap().put("Seo.Test.8.1-1Passed-02",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule08011/Seo.Test.8.1-1Passed-02.html"));

        getWebResourceMap().put("Seo.Test.8.1-1Passed-03",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule08011/Seo.Test.8.1-1Passed-03.html"));

        getWebResourceMap().put("Seo.Test.8.1-1Passed-04",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule08011/Seo.Test.8.1-1Passed-04.html"));

        getWebResourceMap().put("Seo.Test.8.1-2Failed-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule08011/Seo.Test.8.1-2Failed-01.html"));

        getWebResourceMap().put("Seo.Test.8.1-2Failed-02",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule08011/Seo.Test.8.1-2Failed-02.html"));

        getWebResourceMap().put("Seo.Test.8.1-2Failed-03",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule08011/Seo.Test.8.1-2Failed-03.html"));

        getWebResourceMap().put("Seo.Test.8.1-2Failed-04",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule08011/Seo.Test.8.1-2Failed-04.html"));

        getWebResourceMap().put("Seo.Test.8.1-3NMI-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule08011/Seo.Test.8.1-3NMI-01.html"));

        getWebResourceMap().put("Seo.Test.8.1-3NMI-02",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule08011/Seo.Test.8.1-3NMI-02.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("Seo.Test.8.1-1Passed-01");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Seo.Test.8.1-1Passed-02");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Seo.Test.8.1-1Passed-03");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Seo.Test.8.1-1Passed-04");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Seo.Test.8.1-2Failed-01");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(FLASH_CONTENT_DETECTED_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.8.1-2Failed-02");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(FLASH_CONTENT_DETECTED_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.8.1-2Failed-03");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(FLASH_CONTENT_DETECTED_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.8.1-2Failed-04");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(2,processResult.getRemarkSet().size());
        assertEquals(FLASH_CONTENT_DETECTED_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(SUSPECTED_FLASH_CONTENT_DETECTED_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Seo.Test.8.1-3NMI-01");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(SUSPECTED_FLASH_CONTENT_DETECTED_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.8.1-3NMI-02");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(SUSPECTED_FLASH_CONTENT_DETECTED_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
    }

    @Override
    protected void setConsolidate() {
        ProcessResult processResult =
                consolidate("Seo.Test.8.1-1Passed-01");
        assertEquals(TestSolution.PASSED, processResult.getValue());

        processResult = consolidate("Seo.Test.8.1-1Passed-02");
        assertEquals(TestSolution.PASSED, processResult.getValue());

        processResult = consolidate("Seo.Test.8.1-1Passed-03");
        assertEquals(TestSolution.PASSED, processResult.getValue());

        processResult = consolidate("Seo.Test.8.1-1Passed-04");
        assertEquals(TestSolution.PASSED, processResult.getValue());

        processResult = consolidate("Seo.Test.8.1-2Failed-01");
        assertEquals(TestSolution.FAILED, processResult.getValue());

        processResult = consolidate("Seo.Test.8.1-2Failed-02");
        assertEquals(TestSolution.FAILED, processResult.getValue());

        processResult = consolidate("Seo.Test.8.1-2Failed-03");
        assertEquals(TestSolution.FAILED, processResult.getValue());

        processResult = consolidate("Seo.Test.8.1-2Failed-04");
        assertEquals(TestSolution.FAILED, processResult.getValue());

        processResult = consolidate("Seo.Test.8.1-3NMI-01");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());

        processResult = consolidate("Seo.Test.8.1-3NMI-02");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
    }

}