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


import java.util.Iterator;
import org.opens.tanaguru.entity.audit.*;
import org.opens.tanaguru.rules.keystore.AttributeStore;
import org.opens.tanaguru.rules.seo.test.SeoRuleImplementationTestCase;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;


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

        getWebResourceMap().put("Seo.Test.1.1.3-4NA-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01013/Seo.Test.1.1.3-4NA-01.html"));

        getWebResourceMap().put("Seo.Test.1.1.3-4NA-02",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01013/Seo.Test.1.1.3-4NA-02.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Seo.Test.1.1.3-1Passed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Seo.Test.1.1.3-1Passed-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());


        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Seo.Test.1.1.3-2Failed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        Iterator<ProcessRemark> iter = processResult.getRemarkSet().iterator();
        SourceCodeRemark sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.META_DESC_EXCEEDS_LIMIT_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.META_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1,sourceCodeRemark.getElementList().size());
        EvidenceElement ee = sourceCodeRemark.getElementList().iterator().next();
        assertTrue(ee.getValue().contains("Seo10 Test.06.03.1 Failed 01"));
        assertEquals(AttributeStore.CONTENT_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Seo.Test.1.1.3-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Seo.Test.1.1.3-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
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

        processResult = consolidate("Seo.Test.1.1.3-4NA-01");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        
        processResult = consolidate("Seo.Test.1.1.3-4NA-02");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
    }

}