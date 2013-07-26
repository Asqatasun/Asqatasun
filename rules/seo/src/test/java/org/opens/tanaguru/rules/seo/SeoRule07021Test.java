/*
 * @(#)SeoRule07021Test.java
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

import org.opens.tanaguru.rules.seo.SeoRule07021;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.seo.test.SeoRuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class SeoRule07021Test extends SeoRuleImplementationTestCase {
    
    public SeoRule07021Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.seo.SeoRule07021");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Seo.Test.7.2.1-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07021/Seo.Test.7.2.1-1Passed-01.html"));
        getWebResourceMap().put("Seo.Test.7.2.1-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07021/Seo.Test.7.2.1-2Failed-01.html"));
        getWebResourceMap().put("Seo.Test.7.2.1-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07021/Seo.Test.7.2.1-2Failed-02.html"));
        getWebResourceMap().put("Seo.Test.7.2.1-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07021/Seo.Test.7.2.1-2Failed-03.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("Seo.Test.7.2.1-1Passed-01");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Seo.Test.7.2.1-2Failed-01");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(SeoRule07021.ERROR_MESSAGE_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.7.2.1-2Failed-02");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(SeoRule07021.ERROR_MESSAGE_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.7.2.1-2Failed-03");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(2,processResult.getRemarkSet().size());
        assertEquals(SeoRule07021.MORE_THAN_ONE_H1_MSG_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(SeoRule07021.MORE_THAN_ONE_H1_MSG_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.7.2.1-1Passed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.7.2.1-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.7.2.1-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.7.2.1-2Failed-03").getValue());
    }

}
