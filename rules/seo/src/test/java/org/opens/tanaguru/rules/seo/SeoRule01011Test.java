/*
 * @(#)SeoRule01011Test.java
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

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.seo.test.SeoRuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class SeoRule01011Test extends SeoRuleImplementationTestCase {

    public SeoRule01011Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.seo.SeoRule01011");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Seo.Test.1.1.1-1Passed-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01011/Seo.Test.1.1.1-1Passed-01.html"));

        getWebResourceMap().put("Seo.Test.1.1.1-1Passed-02",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01011/Seo.Test.1.1.1-1Passed-02.html"));

        getWebResourceMap().put("Seo.Test.1.1.1-2Failed-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01011/Seo.Test.1.1.1-2Failed-01.html"));

        getWebResourceMap().put("Seo.Test.1.1.1-2Failed-02",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01011/Seo.Test.1.1.1-2Failed-02.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("Seo.Test.1.1.1-1Passed-01");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Seo.Test.1.1.1-1Passed-02");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Seo.Test.1.1.1-2Failed-01");
        assertEquals(TestSolution.FAILED, processResult.getValue());

        assertEquals(1,processResult.getRemarkSet().size());
        
        assertEquals(SeoRule01011.ERROR_MESSAGE_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.1.1.1-2Failed-02");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(2,processResult.getRemarkSet().size());
        assertEquals(SeoRule01011.MORE_THAN_ONE_META_DESC_MSG_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(SeoRule01011.MORE_THAN_ONE_META_DESC_MSG_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
    }

    @Override
    protected void setConsolidate() {
        ProcessResult processResult =
                consolidate("Seo.Test.1.1.1-1Passed-01");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        
        processResult = consolidate("Seo.Test.1.1.1-1Passed-02");
        assertEquals(TestSolution.PASSED, processResult.getValue());

        processResult = consolidate("Seo.Test.1.1.1-2Failed-01");
        assertEquals(TestSolution.FAILED, processResult.getValue());

        processResult = consolidate("Seo.Test.1.1.1-2Failed-02");
        assertEquals(TestSolution.FAILED, processResult.getValue());
    }

}