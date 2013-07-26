/*
 * @(#)SeoRule07061Test.java
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

import org.opens.tanaguru.rules.seo.SeoRule07061;
import org.opens.tanaguru.rules.seo.test.SeoSiteRuleWithPageResultImplementationTestCase;
import java.util.Collection;
import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;

/**
 *
 * @author jkowalczyk
 */
public class SeoRule07061Test extends SeoSiteRuleWithPageResultImplementationTestCase {

    public SeoRule07061Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.seo.SeoRule07061");
    }

    @Override
    protected void setUpWebResourceMap() {
        Site site = getWebResourceFactory().createSite("file:Site-Passed1");
        getWebResourceMap().put("Seo.Test.7.6.1-1Passed-01", site);

        Page page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07061/Seo.Test.7.6.1-1Passed-01.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.7.6.1-page01",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07061/Seo.Test.7.6.1-1Passed-02.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.7.6.1-page02",page);

        site = getWebResourceFactory().createSite("file:Site-Failed1");
        getWebResourceMap().put("Seo.Test.7.6.1-2Failed-01", site);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07061/Seo.Test.7.6.1-2Failed-01.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.7.6.1-page03",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07061/Seo.Test.7.6.1-2Failed-02.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.7.6.1-page04",page);

        site = getWebResourceFactory().createSite("file:Site-NA1");
        getWebResourceMap().put("Seo.Test.7.6.1-4NA-01", site);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07061/Seo.Test.7.6.1-4NA-01.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.7.6.1-page05",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07061/Seo.Test.7.6.1-4NA-02.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.7.6.1-page06",page);

        site = getWebResourceFactory().createSite("file:Site-Passed2");
        getWebResourceMap().put("Seo.Test.7.6.1-1Passed-02", site);
        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07061/Seo.Test.7.6.1-4NA-02.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.7.6.1-page07",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07061/Seo.Test.7.6.1-1Passed-01.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.7.6.1-page08",page);

        site = getWebResourceFactory().createSite("file:Site-Failed2");
        getWebResourceMap().put("Seo.Test.7.6.1-2Failed-02", site);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07061/Seo.Test.7.6.1-2Failed-03.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.7.6.1-page09",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07061/Seo.Test.7.6.1-2Failed-04.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.7.6.1-page10",page);

        site = getWebResourceFactory().createSite("file:Site-Failed3");
        getWebResourceMap().put("Seo.Test.7.6.1-2Failed-03", site);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07061/Seo.Test.7.6.1-2Failed-05.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.7.6.1-page11",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07061/Seo.Test.7.6.1-2Failed-06.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.7.6.1-page12",page);

        site = getWebResourceFactory().createSite("file:Site-Failed4");
        getWebResourceMap().put("Seo.Test.7.6.1-2Failed-04", site);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07061/Seo.Test.7.6.1-2Failed-07.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.7.6.1-page13",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07061/Seo.Test.7.6.1-2Failed-08.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.7.6.1-page14",page);

        site = getWebResourceFactory().createSite("file:Site-Failed5");
        getWebResourceMap().put("Seo.Test.7.6.1-2Failed-05", site);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07061/Seo.Test.7.6.1-2Failed-09.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.7.6.1-page15",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07061/Seo.Test.7.6.1-2Failed-10.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.7.6.1-page16",page);
    }

    @Override
    protected void setProcess() {
        process("Seo.Test.7.6.1-1Passed-01");
        process("Seo.Test.7.6.1-1Passed-02");
        process("Seo.Test.7.6.1-2Failed-01");
        process("Seo.Test.7.6.1-2Failed-02");
        process("Seo.Test.7.6.1-2Failed-03");
        process("Seo.Test.7.6.1-2Failed-04");
        process("Seo.Test.7.6.1-2Failed-05");
        process("Seo.Test.7.6.1-4NA-01");
    }

    @Override
    protected void setConsolidate() {
        Collection<ProcessResult> processResult = consolidateWithListAsReturn("Seo.Test.7.6.1-1Passed-01");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(2).getValue());

        processResult = consolidateWithListAsReturn("Seo.Test.7.6.1-1Passed-02");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.NOT_APPLICABLE, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(2).getValue());

        processResult = consolidateWithListAsReturn("Seo.Test.7.6.1-2Failed-01");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(2).getValue());

        assertEquals(1, ((List<ProcessResult>)processResult).get(0).getRemarkSet().size());
         assertEquals(SeoRule07061.PAGE_LEVEL_MESSAGE_CODE,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(0).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(1, ((List<ProcessResult>)processResult).get(1).getRemarkSet().size());
        assertEquals(SeoRule07061.PAGE_LEVEL_MESSAGE_CODE,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(1).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(2, ((List<ProcessResult>)processResult).get(2).getRemarkSet().size());
        assertEquals(SeoRule07061.SITE_LEVEL_MESSAGE_CODE,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(2).getRemarkSet().toArray()[0]).getMessageCode());

        processResult = consolidateWithListAsReturn("Seo.Test.7.6.1-2Failed-02");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(2).getValue());

        assertEquals(1, ((List<ProcessResult>)processResult).get(0).getRemarkSet().size());
         assertEquals(SeoRule07061.PAGE_LEVEL_MESSAGE_CODE,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(0).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(1, ((List<ProcessResult>)processResult).get(1).getRemarkSet().size());
        assertEquals(SeoRule07061.PAGE_LEVEL_MESSAGE_CODE,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(1).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(2, ((List<ProcessResult>)processResult).get(2).getRemarkSet().size());
        assertEquals(SeoRule07061.SITE_LEVEL_MESSAGE_CODE,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(2).getRemarkSet().toArray()[0]).getMessageCode());


        processResult = consolidateWithListAsReturn("Seo.Test.7.6.1-2Failed-03");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(2).getValue());

        assertEquals(1, ((List<ProcessResult>)processResult).get(0).getRemarkSet().size());
         assertEquals(SeoRule07061.PAGE_LEVEL_MESSAGE_CODE,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(0).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(1, ((List<ProcessResult>)processResult).get(1).getRemarkSet().size());
        assertEquals(SeoRule07061.PAGE_LEVEL_MESSAGE_CODE,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(1).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(2, ((List<ProcessResult>)processResult).get(2).getRemarkSet().size());
        assertEquals(SeoRule07061.SITE_LEVEL_MESSAGE_CODE,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(2).getRemarkSet().toArray()[0]).getMessageCode());

        processResult = consolidateWithListAsReturn("Seo.Test.7.6.1-2Failed-04");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(2).getValue());

        assertEquals(1, ((List<ProcessResult>)processResult).get(0).getRemarkSet().size());
         assertEquals(SeoRule07061.PAGE_LEVEL_MESSAGE_CODE,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(0).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(1, ((List<ProcessResult>)processResult).get(1).getRemarkSet().size());
        assertEquals(SeoRule07061.PAGE_LEVEL_MESSAGE_CODE,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(1).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(2, ((List<ProcessResult>)processResult).get(2).getRemarkSet().size());
        assertEquals(SeoRule07061.SITE_LEVEL_MESSAGE_CODE,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(2).getRemarkSet().toArray()[0]).getMessageCode());

        processResult = consolidateWithListAsReturn("Seo.Test.7.6.1-2Failed-05");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(2).getValue());

        assertEquals(1, ((List<ProcessResult>)processResult).get(0).getRemarkSet().size());
         assertEquals(SeoRule07061.PAGE_LEVEL_MESSAGE_CODE,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(0).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(1, ((List<ProcessResult>)processResult).get(1).getRemarkSet().size());
        assertEquals(SeoRule07061.PAGE_LEVEL_MESSAGE_CODE,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(1).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(2, ((List<ProcessResult>)processResult).get(2).getRemarkSet().size());
        assertEquals(SeoRule07061.SITE_LEVEL_MESSAGE_CODE,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(2).getRemarkSet().toArray()[0]).getMessageCode());
        
        processResult = consolidateWithListAsReturn("Seo.Test.7.6.1-4NA-01");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.NOT_APPLICABLE, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.NOT_APPLICABLE, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.NOT_APPLICABLE, ((List<ProcessResult>)processResult).get(2).getValue());
    }

}