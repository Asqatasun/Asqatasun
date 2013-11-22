/*
 * @(#)SeoRule01021Test.java
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


import org.opens.tanaguru.rules.seo.SeoRule01021;
import org.opens.tanaguru.rules.seo.test.SeoSiteRuleWithPageResultImplementationTestCase;
import java.util.Collection;
import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;


/**
 *
 * @author jkowalczyk
 */
public class SeoRule01021Test extends SeoSiteRuleWithPageResultImplementationTestCase {

    public SeoRule01021Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.seo.SeoRule01021");
    }

    @Override
    protected void setUpWebResourceMap() {
        Site site = getWebResourceFactory().createSite("file:Site-Passed1");
        getWebResourceMap().put("Seo.Test.1.2.1-1Passed-01", site);

        Page page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01021/Seo.Test.1.2.1-1Passed-01.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.1.2.1-page01",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01021/Seo.Test.1.2.1-1Passed-02.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.1.2.1-page02",page);

        site = getWebResourceFactory().createSite("file:Site-Failed1");
        getWebResourceMap().put("Seo.Test.1.2.1-2Failed-01", site);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01021/Seo.Test.1.2.1-2Failed-01.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.1.2.1-page03",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01021/Seo.Test.1.2.1-2Failed-02.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.1.2.1-page04",page);

        site = getWebResourceFactory().createSite("file:Site-NA1");
        getWebResourceMap().put("Seo.Test.1.2.1-4NA-01", site);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01021/Seo.Test.1.2.1-4NA-01.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.1.2.1-page05",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01021/Seo.Test.1.2.1-4NA-02.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.1.2.1-page06",page);

        site = getWebResourceFactory().createSite("file:Site-Passed2");
        getWebResourceMap().put("Seo.Test.1.2.1-1Passed-02", site);
        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01021/Seo.Test.1.2.1-4NA-02.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.1.2.1-page07",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule01021/Seo.Test.1.2.1-1Passed-01.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.1.2.1-page08",page);
    }

    @Override
    protected void setProcess() {
        process("Seo.Test.1.2.1-1Passed-01");
        process("Seo.Test.1.2.1-1Passed-02");
        process("Seo.Test.1.2.1-2Failed-01");
        process("Seo.Test.1.2.1-4NA-01");
    }

    @Override
    protected void setConsolidate() {
        Collection<ProcessResult> processResult = consolidateWithListAsReturn("Seo.Test.1.2.1-1Passed-01");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(2).getValue());

        processResult = consolidateWithListAsReturn("Seo.Test.1.2.1-1Passed-02");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.NOT_APPLICABLE, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(1).getValue());

        processResult = consolidateWithListAsReturn("Seo.Test.1.2.1-2Failed-01");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(2).getValue());

        assertEquals(1, ((List<ProcessResult>)processResult).get(0).getRemarkSet().size());
         assertEquals(RemarkMessageStore.META_DESC_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(0).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(1, ((List<ProcessResult>)processResult).get(1).getRemarkSet().size());
        assertEquals(RemarkMessageStore.META_DESC_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(1).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(2, ((List<ProcessResult>)processResult).get(2).getRemarkSet().size());
        assertEquals(RemarkMessageStore.META_DESC_NOT_UNIQUE_MSG,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(2).getRemarkSet().toArray()[0]).getMessageCode());

        processResult = consolidateWithListAsReturn("Seo.Test.1.2.1-4NA-01");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.NOT_APPLICABLE, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.NOT_APPLICABLE, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.NOT_APPLICABLE, ((List<ProcessResult>)processResult).get(2).getValue());
    }

}