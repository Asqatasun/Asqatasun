/*
 * @(#)SeoRule06051Test.java
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

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.seo.test.SeoRuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class SeoRule06051Test extends SeoRuleImplementationTestCase {

    public SeoRule06051Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.opens.tanaguru.rules.seo.SeoRule06051");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Seo.Test.6.5.1-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06051/Seo.Test.6.5.1-1Passed-01.html"));
        getWebResourceMap().put("Seo.Test.6.5.1-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06051/Seo.Test.6.5.1-2Failed-01.html"));
    }

    @Override
    protected void setProcess() {
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.6.5.1-1Passed-01").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("Seo.Test.6.5.1-2Failed-01").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.6.5.1-1Passed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.6.5.1-2Failed-01").getValue());
    }

}
