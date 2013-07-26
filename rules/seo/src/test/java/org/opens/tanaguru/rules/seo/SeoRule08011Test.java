/*
 * @(#)SeoRule08011Test.java
 *
 * Copyright  2010 SAS OPEN-S. All rights reserved.
 * OPEN-S PROPRIETARY/CONFIDENTIAL.  Use is subject to license terms.
 *
 * This file is  protected by the  intellectual  property rights
 * in  France  and  other  countries, any  applicable  copyrights  or
 * patent rights, and international treaty provisions. No part may be
 * reproduced  in  any  form  by  any  mean  without   prior  written
 * authorization of OPEN-S.
 */
package org.opens.tanaguru.rules.seo;

import org.opens.tanaguru.rules.seo.SeoRule08011;
import org.opens.tanaguru.rules.seo.test.SeoRuleImplementationTestCase;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;

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
        setRuleImplementationClassName("org.opens.tanaguru.rules.seo.SeoRule08011");
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
        assertEquals(SeoRule08011.FLASH_CONTENT_DETECTED_MESSAGE_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.8.1-2Failed-02");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(SeoRule08011.FLASH_CONTENT_DETECTED_MESSAGE_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.8.1-2Failed-03");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(SeoRule08011.FLASH_CONTENT_DETECTED_MESSAGE_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.8.1-2Failed-04");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(2,processResult.getRemarkSet().size());
        assertEquals(SeoRule08011.FLASH_CONTENT_DETECTED_MESSAGE_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(SeoRule08011.SUSPECTED_FLASH_CONTENT_DETECTED_MESSAGE_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Seo.Test.8.1-3NMI-01");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(SeoRule08011.SUSPECTED_FLASH_CONTENT_DETECTED_MESSAGE_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.8.1-3NMI-02");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(SeoRule08011.SUSPECTED_FLASH_CONTENT_DETECTED_MESSAGE_CODE,
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