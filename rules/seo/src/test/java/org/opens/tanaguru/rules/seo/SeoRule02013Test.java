/*
 * @(#)SeoRule02013Test.java
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
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;
import org.opens.tanaguru.rules.seo.test.SeoRuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class SeoRule02013Test extends SeoRuleImplementationTestCase {

    public SeoRule02013Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.opens.tanaguru.rules.seo.SeoRule02013");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Seo.Test.2.1.3-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule02013/Seo.Test.2.1.3-1Passed-01.html"));
        getWebResourceMap().put("Seo.Test.2.1.3-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule02013/Seo.Test.2.1.3-1Passed-02.html"));
        getWebResourceMap().put("Seo.Test.2.1.3-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule02013/Seo.Test.2.1.3-2Failed-01.html"));
        getWebResourceMap().put("Seo.Test.2.1.3-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule02013/Seo.Test.2.1.3-2Failed-02.html"));
        getWebResourceMap().put("Seo.Test.2.1.3-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule02013/Seo.Test.2.1.3-4NA-01.html"));
        getWebResourceMap().put("Seo.Test.2.1.3-4NA-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule02013/Seo.Test.2.1.3-4NA-02.html"));
    }

    @Override
    protected void setProcess() {
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.2.1.3-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.2.1.3-1Passed-02").getValue());

        ProcessResult processResult =
                processPageTest("Seo.Test.2.1.3-2Failed-01");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(RemarkMessageStore.ALT_MISSING_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.2.1.3-2Failed-02");
        assertEquals(TestSolution.FAILED,
                processResult.getValue());
        assertEquals(RemarkMessageStore.ALT_MISSING_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.2.1.3-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.2.1.3-4NA-02").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.2.1.3-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.2.1.3-1Passed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.2.1.3-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.2.1.3-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.2.1.3-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.2.1.3-4NA-02").getValue());
    }
}
