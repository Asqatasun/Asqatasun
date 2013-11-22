/*
 * @(#)SeoRule06031Test.java
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
import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.entity.audit.*;
import org.opens.tanaguru.rules.keystore.AttributeStore;
import org.opens.tanaguru.rules.seo.SeoRule06031;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;
import org.opens.tanaguru.rules.seo.test.SeoRuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class SeoRule06031Test extends SeoRuleImplementationTestCase {

    public SeoRule06031Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.seo.SeoRule06031");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Seo.Test.6.3.1-1Passed-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06031/Seo.Test.6.3.1-1Passed-01.html"));

        getWebResourceMap().put("Seo.Test.6.3.1-1Passed-02",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06031/Seo.Test.6.3.1-1Passed-02.html"));
        
        getWebResourceMap().put("Seo.Test.6.3.1-1Passed-03",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06031/Seo.Test.6.3.1-1Passed-03.html"));

        getWebResourceMap().put("Seo.Test.6.3.1-2Failed-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06031/Seo.Test.6.3.1-2Failed-01.html"));

        getWebResourceMap().put("Seo.Test.6.3.1-2Failed-02",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06031/Seo.Test.6.3.1-2Failed-02.html"));

        getWebResourceMap().put("Seo.Test.6.3.1-2Failed-03",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06031/Seo.Test.6.3.1-2Failed-03.html"));

        getWebResourceMap().put("Seo.Test.6.3.1-4NA-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06031/Seo.Test.6.3.1-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Seo.Test.6.3.1-1Passed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Seo.Test.6.3.1-1Passed-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());

        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Seo.Test.6.3.1-1Passed-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check number of remarks and their value
        assertNull(processResult.getRemarkSet());

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Seo.Test.6.3.1-2Failed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        Iterator<ProcessRemark> iter = processResult.getRemarkSet().iterator();
        SourceCodeRemark sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals("TitleTagLengthExceedLimit", sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.TITLE_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1,sourceCodeRemark.getElementList().size());
        EvidenceElement ee = sourceCodeRemark.getElementList().iterator().next();
        String value = StringUtils.trim(ee.getValue()).replaceAll("[\n\r]", "").replaceAll("\\s+", " ");
        System.out.println(ee.getValue());
        System.out.println(StringUtils.trim(ee.getValue()));
        System.out.println(StringUtils.trim(ee.getValue()).replaceAll("[\n\r]", ""));
        System.out.println(value);
        assertEquals("Seo10 Test.06.03.1 Failed 01 with a length superior to 100 characters Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent imperdiet eget risus non", value);
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());


        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Seo.Test.6.3.1-2Failed-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals("TitleTagLengthExceedLimit", sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.TITLE_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1,sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        value = StringUtils.trim(ee.getValue());
        assertEquals("Seo10 Test.06.03.1 Failed 02 n°1 with a length that exceeds 100 characters (------------------------------)", value);
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Seo.Test.6.3.1-2Failed-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals("TitleTagLengthExceedLimit", sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.TITLE_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(1,sourceCodeRemark.getElementList().size());
        ee = sourceCodeRemark.getElementList().iterator().next();
        value = StringUtils.trim(ee.getValue());
        assertEquals("Seo10 Test.06.03.1 Failed 03 n°1 with a length that exceeds 100 characters (------------------------------)", value);
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Seo.Test.6.3.1-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        ProcessResult processResult =
                consolidate("Seo.Test.6.3.1-1Passed-01");
        assertEquals(TestSolution.PASSED, processResult.getValue());

        processResult = consolidate("Seo.Test.6.3.1-1Passed-02");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        
        processResult = consolidate("Seo.Test.6.3.1-1Passed-03");
        assertEquals(TestSolution.PASSED, processResult.getValue());

        processResult = consolidate("Seo.Test.6.3.1-2Failed-01");
        assertEquals(TestSolution.FAILED, processResult.getValue());

        processResult = consolidate("Seo.Test.6.3.1-2Failed-02");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        
        processResult = consolidate("Seo.Test.6.3.1-2Failed-03");
        assertEquals(TestSolution.FAILED, processResult.getValue());

        processResult = consolidate("Seo.Test.6.3.1-4NA-01");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
    }

}