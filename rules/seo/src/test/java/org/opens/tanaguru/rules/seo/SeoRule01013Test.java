/*
 * @(#)SeoRule01012Test.java
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


import org.opens.tanaguru.rules.seo.SeoRule01013;
import org.opens.tanaguru.rules.seo.test.SeoRuleImplementationTestCase;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;


/**
 * Test whether the meta description of the page exceeds 255 characters
 * 
 * @author jkowalczyk
 */
public class SeoRule01013Test extends SeoRuleImplementationTestCase {

    public SeoRule01013Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.seo.SeoRule01013");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Seo.Test.1.1.3-1Passed-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01013/Seo.Test.1.1.3-1Passed-01.html"));

        getWebResourceMap().put("Seo.Test.1.1.3-1Passed-02",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01013/Seo.Test.1.1.3-1Passed-02.html"));

        getWebResourceMap().put("Seo.Test.1.1.3-2Failed-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01013/Seo.Test.1.1.3-2Failed-01.html"));

        getWebResourceMap().put("Seo.Test.1.1.3-2Failed-02",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01013/Seo.Test.1.1.3-2Failed-02.html"));

        getWebResourceMap().put("Seo.Test.1.1.3-4NA-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01013/Seo.Test.1.1.3-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("Seo.Test.1.1.3-1Passed-01");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Seo.Test.1.1.3-1Passed-02");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Seo.Test.1.1.3-2Failed-01");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(SeoRule01013.MESSAGE_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.1.1.3-2Failed-02");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(2,processResult.getRemarkSet().size());
        assertEquals(SeoRule01013.MORE_THAN_ONE_META_MESSAGE_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(2,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getElementList().size());
        assertEquals(SeoRule01013.MORE_THAN_ONE_META_MESSAGE_CODE,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(2,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getElementList().size());
        processResult = processPageTest("Seo.Test.1.1.3-4NA-01");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        ProcessResult processResult =
                consolidate("Seo.Test.1.1.3-1Passed-01");
        assertEquals(TestSolution.PASSED, processResult.getValue());

        processResult = consolidate("Seo.Test.1.1.3-1Passed-02");
        assertEquals(TestSolution.PASSED, processResult.getValue());

        processResult = consolidate("Seo.Test.1.1.3-2Failed-01");
        assertEquals(TestSolution.FAILED, processResult.getValue());

        processResult = consolidate("Seo.Test.1.1.3-2Failed-02");
        assertEquals(TestSolution.FAILED, processResult.getValue());

        processResult = consolidate("Seo.Test.1.1.3-4NA-01");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
    }

}