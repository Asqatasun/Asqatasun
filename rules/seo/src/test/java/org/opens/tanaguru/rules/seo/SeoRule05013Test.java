/*
 * @(#)SeoRule05013Test.java
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
public class SeoRule05013Test extends SeoRuleImplementationTestCase {

    public SeoRule05013Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.opens.tanaguru.rules.seo.SeoRule05013");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Seo.Test.5.1.3-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-2Failed-01.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-2Failed-02.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-2Failed-03.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-2Failed-04.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-2Failed-05.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-2Failed-06.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-2Failed-07.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-2Failed-08.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-3NMI-01.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-3NMI-02.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-3NMI-03.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-3NMI-04.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-3NMI-05.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-3NMI-06.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-3NMI-07.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-4NA-01.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-4NA-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-4NA-02.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-4NA-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-4NA-03.html"));
        getWebResourceMap().put("Seo.Test.5.1.3-4NA-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05013/Seo.Test.5.1.3-4NA-04.html"));

        // 5.1.1 testcases
        getWebResourceMap().put("Seo.Test.5.1.1-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05011/Seo.Test.5.1.1-2Failed-01.html"));
        getWebResourceMap().put("Seo.Test.5.1.1-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05011/Seo.Test.5.1.1-2Failed-02.html"));
        getWebResourceMap().put("Seo.Test.5.1.1-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05011/Seo.Test.5.1.1-2Failed-03.html"));
        getWebResourceMap().put("Seo.Test.5.1.1-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05011/Seo.Test.5.1.1-2Failed-04.html"));
        getWebResourceMap().put("Seo.Test.5.1.1-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05011/Seo.Test.5.1.1-2Failed-05.html"));
        getWebResourceMap().put("Seo.Test.5.1.1-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05011/Seo.Test.5.1.1-2Failed-06.html"));
        getWebResourceMap().put("Seo.Test.5.1.1-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05011/Seo.Test.5.1.1-2Failed-07.html"));
        getWebResourceMap().put("Seo.Test.5.1.1-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05011/Seo.Test.5.1.1-2Failed-08.html"));
        getWebResourceMap().put("Seo.Test.5.1.1-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05011/Seo.Test.5.1.1-3NMI-01.html"));
        getWebResourceMap().put("Seo.Test.5.1.1-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05011/Seo.Test.5.1.1-3NMI-02.html"));
        getWebResourceMap().put("Seo.Test.5.1.1-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05011/Seo.Test.5.1.1-3NMI-03.html"));
        getWebResourceMap().put("Seo.Test.5.1.1-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05011/Seo.Test.5.1.1-3NMI-04.html"));
        getWebResourceMap().put("Seo.Test.5.1.1-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05011/Seo.Test.5.1.1-3NMI-05.html"));
        getWebResourceMap().put("Seo.Test.5.1.1-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05011/Seo.Test.5.1.1-3NMI-06.html"));
        getWebResourceMap().put("Seo.Test.5.1.1-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05011/Seo.Test.5.1.1-3NMI-07.html"));

        //5.1.2 testcases
        getWebResourceMap().put("Seo.Test.5.1.2-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-2Failed-01.html"));
        getWebResourceMap().put("Seo.Test.5.1.2-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-2Failed-02.html"));
        getWebResourceMap().put("Seo.Test.5.1.2-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-2Failed-03.html"));
        getWebResourceMap().put("Seo.Test.5.1.2-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-2Failed-04.html"));
        getWebResourceMap().put("Seo.Test.5.1.2-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-2Failed-05.html"));
        getWebResourceMap().put("Seo.Test.5.1.2-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-2Failed-06.html"));
        getWebResourceMap().put("Seo.Test.5.1.2-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-2Failed-07.html"));
        getWebResourceMap().put("Seo.Test.5.1.2-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-2Failed-08.html"));
        getWebResourceMap().put("Seo.Test.5.1.2-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-2Failed-09.html"));
        getWebResourceMap().put("Seo.Test.5.1.2-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-2Failed-10.html"));
        getWebResourceMap().put("Seo.Test.5.1.2-2Failed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-2Failed-11.html"));
        getWebResourceMap().put("Seo.Test.5.1.2-2Failed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-2Failed-12.html"));
        getWebResourceMap().put("Seo.Test.5.1.2-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-3NMI-01.html"));
        getWebResourceMap().put("Seo.Test.5.1.2-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-3NMI-02.html"));
        getWebResourceMap().put("Seo.Test.5.1.2-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-3NMI-03.html"));
        getWebResourceMap().put("Seo.Test.5.1.2-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-3NMI-04.html"));
        getWebResourceMap().put("Seo.Test.5.1.2-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-3NMI-05.html"));
        getWebResourceMap().put("Seo.Test.5.1.2-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule05012/Seo.Test.5.1.2-3NMI-06.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("Seo.Test.5.1.3-2Failed-01");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Seo.Test.5.1.3-2Failed-02");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Seo.Test.5.1.3-2Failed-03");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Seo.Test.5.1.3-2Failed-04");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Seo.Test.5.1.3-2Failed-05");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Seo.Test.5.1.3-2Failed-06");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Seo.Test.5.1.3-2Failed-07");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Seo.Test.5.1.3-2Failed-08");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("UnexplicitLinkOutOfContext",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Seo.Test.5.1.3-3NMI-01");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Seo.Test.5.1.3-3NMI-02");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Seo.Test.5.1.3-3NMI-03");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Seo.Test.5.1.3-3NMI-04");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Seo.Test.5.1.3-3NMI-05");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Seo.Test.5.1.3-3NMI-06");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Seo.Test.5.1.3-3NMI-07");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.3-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.3-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.3-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.3-4NA-04").getValue());

        // 5.1.1 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.1-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.1-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.1-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.1-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.1-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.1-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.1-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.1-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.1-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.1-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.1-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.1-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.1-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.1-3NMI-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.1-3NMI-07").getValue());

        // 5.1.2 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-2Failed-09").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-2Failed-10").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-2Failed-11").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-2Failed-12").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.5.1.2-3NMI-06").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.5.1.3-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.5.1.3-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.5.1.3-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.5.1.3-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.5.1.3-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.5.1.3-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.5.1.3-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.5.1.3-2Failed-08").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Seo.Test.5.1.3-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Seo.Test.5.1.3-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Seo.Test.5.1.3-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Seo.Test.5.1.3-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Seo.Test.5.1.3-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Seo.Test.5.1.3-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Seo.Test.5.1.3-3NMI-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.3-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.3-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.3-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.3-4NA-04").getValue());

        // 5.1.1 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.1-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.1-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.1-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.1-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.1-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.1-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.1-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.1-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.1-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.1-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.1-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.1-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.1-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.1-3NMI-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.1-3NMI-07").getValue());

        // 5.1.2 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.2-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.2-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.2-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.2-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.2-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.2-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.2-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.2-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.2-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.2-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.2-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.2-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.2-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.5.1.2-3NMI-06").getValue());
    }
}
