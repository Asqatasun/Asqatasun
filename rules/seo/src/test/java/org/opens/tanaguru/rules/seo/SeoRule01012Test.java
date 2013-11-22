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


import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;
import org.opens.tanaguru.rules.seo.test.SeoRuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class SeoRule01012Test extends SeoRuleImplementationTestCase {

    public SeoRule01012Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.seo.SeoRule01012");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Seo.Test.1.1.2-2Failed-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01012/Seo.Test.1.1.2-2Failed-01.html"));

        getWebResourceMap().put("Seo.Test.1.1.2-2Failed-02",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01012/Seo.Test.1.1.2-2Failed-02.html"));
        
        getWebResourceMap().put("Seo.Test.1.1.2-2Failed-03",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01012/Seo.Test.1.1.2-2Failed-03.html"));
        
        getWebResourceMap().put("Seo.Test.1.1.2-2Failed-04",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01012/Seo.Test.1.1.2-2Failed-04.html"));
        
        getWebResourceMap().put("Seo.Test.1.1.2-3NMI-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01012/Seo.Test.1.1.2-3NMI-01.html"));
        
        getWebResourceMap().put("Seo.Test.1.1.2-3NMI-02",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01012/Seo.Test.1.1.2-3NMI-02.html"));
        
        getWebResourceMap().put("Seo.Test.1.1.2-4NA-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01012/Seo.Test.1.1.2-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult = processPageTest("Seo.Test.1.1.2-2Failed-01");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.META_DESC_NOT_RELEVANT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.1.1.2-2Failed-02");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.META_DESC_NOT_RELEVANT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        
        processResult = processPageTest("Seo.Test.1.1.2-2Failed-03");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.META_DESC_NOT_RELEVANT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        
        processResult = processPageTest("Seo.Test.1.1.2-2Failed-04");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.META_DESC_NOT_RELEVANT_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.1.1.2-3NMI-01");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_META_DESC_RELEVANCY_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        
        processResult = processPageTest("Seo.Test.1.1.2-3NMI-02");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.CHECK_META_DESC_RELEVANCY_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        
        processResult = processPageTest("Seo.Test.1.1.2-4NA-01");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        ProcessResult processResult = consolidate("Seo.Test.1.1.2-2Failed-01");
        assertEquals(TestSolution.FAILED, processResult.getValue());

        processResult = consolidate("Seo.Test.1.1.2-2Failed-02");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        
        processResult = consolidate("Seo.Test.1.1.2-2Failed-03");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        
        processResult = consolidate("Seo.Test.1.1.2-2Failed-04");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        
        processResult = consolidate("Seo.Test.1.1.2-3NMI-01");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        
        processResult = consolidate("Seo.Test.1.1.2-3NMI-02");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());

        processResult = consolidate("Seo.Test.1.1.2-4NA-01");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
    }

}