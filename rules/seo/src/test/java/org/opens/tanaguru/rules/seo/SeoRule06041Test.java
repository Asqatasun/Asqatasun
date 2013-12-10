/*
 * @(#)SeoRule06041Test.java
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

import java.util.Collection;
import java.util.List;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;
import org.opens.tanaguru.rules.seo.test.SeoSiteRuleWithPageResultImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class SeoRule06041Test extends SeoSiteRuleWithPageResultImplementationTestCase {

    public SeoRule06041Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.seo.SeoRule06041");
    }

    @Override
    protected void setUpWebResourceMap() {
        // file:Site-Passed1
        Site site = getWebResourceFactory().createSite("file:Site-Passed1");
        getWebResourceMap().put("Seo.Test.6.4.1-1Passed-01", site);

        Page page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-1Passed-01.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page01",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-1Passed-02.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page02",page);
        
        // file:Site-Failed1
        site = getWebResourceFactory().createSite("file:Site-Failed1");
        getWebResourceMap().put("Seo.Test.6.4.1-2Failed-01", site);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-2Failed-01.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page03",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-2Failed-02.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page04",page);

        // file:Site-NA1
        site = getWebResourceFactory().createSite("file:Site-NA1");
        getWebResourceMap().put("Seo.Test.6.4.1-4NA-01", site);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-4NA-01.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page05",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-4NA-02.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page06",page);

        // file:Site-Passed2
        site = getWebResourceFactory().createSite("file:Site-Passed2");
        getWebResourceMap().put("Seo.Test.6.4.1-1Passed-02", site);
        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-4NA-02.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page07",page);
        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-1Passed-01.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page08",page);
        
        // file:Site-Passed3
        site = getWebResourceFactory().createSite("file:Site-Passed3");
        getWebResourceMap().put("Seo.Test.6.4.1-1Passed-03", site);
        
        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-1Passed-03.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page09",page);
        
        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-1Passed-04.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page10",page);
        
        // file:Site-Failed2
        site = getWebResourceFactory().createSite("file:Site-Failed2");
        getWebResourceMap().put("Seo.Test.6.4.1-2Failed-02", site);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-2Failed-03.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page11",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-2Failed-04.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page12",page);
        
        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-2Failed-05.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page13",page);
        
        // file:Site-Failed3
        site = getWebResourceFactory().createSite("file:Site-Failed3");
        getWebResourceMap().put("Seo.Test.6.4.1-2Failed-03", site);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-2Failed-06.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page14",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-2Failed-07.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page15",page);
        
        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-2Failed-08.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page16",page);
        
        // file:Site-Failed4
        site = getWebResourceFactory().createSite("file:Site-Failed4");
        getWebResourceMap().put("Seo.Test.6.4.1-2Failed-04", site);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-2Failed-09.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page17",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-2Failed-10.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page18",page);
        
        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-2Failed-11.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page19",page);
        
        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule06041/Seo.Test.6.4.1-2Failed-12.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.6.4.1-page20",page);
    }

    @Override
    protected void setProcess() {
        process("Seo.Test.6.4.1-1Passed-01");
        process("Seo.Test.6.4.1-1Passed-02");
        process("Seo.Test.6.4.1-1Passed-03");
        process("Seo.Test.6.4.1-2Failed-01");
        process("Seo.Test.6.4.1-2Failed-02");
        process("Seo.Test.6.4.1-2Failed-03");
        process("Seo.Test.6.4.1-2Failed-04");
        process("Seo.Test.6.4.1-4NA-01");
    }

    @Override
    protected void setConsolidate() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        Collection<ProcessResult> processResult = consolidateWithListAsReturn("Seo.Test.6.4.1-1Passed-01");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(2).getValue());

        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        processResult = consolidateWithListAsReturn("Seo.Test.6.4.1-1Passed-02");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.NOT_APPLICABLE, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(2).getValue());
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        processResult = consolidateWithListAsReturn("Seo.Test.6.4.1-1Passed-03");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(2).getValue());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = consolidateWithListAsReturn("Seo.Test.6.4.1-2Failed-01");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(2).getValue());

        assertEquals(2, ((List<ProcessResult>)processResult).get(0).getRemarkSet().size());
        assertEquals(RemarkMessageStore.TITLE_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(0).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.TITLE_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(0).getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(2, ((List<ProcessResult>)processResult).get(1).getRemarkSet().size());
        assertEquals(RemarkMessageStore.TITLE_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(1).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.TITLE_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(1).getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(2, ((List<ProcessResult>)processResult).get(2).getRemarkSet().size());
        assertEquals(RemarkMessageStore.TITLE_NOT_UNIQUE_MSG,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(2).getRemarkSet().toArray()[0]).getMessageCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = consolidateWithListAsReturn("Seo.Test.6.4.1-2Failed-02");
        assertEquals(4, processResult.size());
        for (ProcessResult pr : processResult) {
            System.out.println(pr.getSubject().getURL());
            if (pr.getSubject().getURL().contains("2Failed-05")) {
                assertEquals(TestSolution.PASSED, pr.getValue());
            } else {
                assertEquals(TestSolution.FAILED, pr.getValue());
                assertEquals(2, pr.getRemarkSet().size());
                if (pr.getSubject() instanceof Page) {
                    assertEquals(RemarkMessageStore.TITLE_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                            ((ProcessRemark)pr.getRemarkSet().toArray()[0]).getMessageCode());
                    assertEquals(RemarkMessageStore.TITLE_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                            ((ProcessRemark)pr.getRemarkSet().toArray()[1]).getMessageCode());
                } else {
                    assertEquals(RemarkMessageStore.TITLE_NOT_UNIQUE_MSG,
                            ((ProcessRemark)pr.getRemarkSet().toArray()[0]).getMessageCode());
                    assertEquals(RemarkMessageStore.TITLE_NOT_UNIQUE_MSG,
                            ((ProcessRemark)pr.getRemarkSet().toArray()[1]).getMessageCode());
                }
            } 
        }
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = consolidateWithListAsReturn("Seo.Test.6.4.1-2Failed-03");
        assertEquals(4, processResult.size());
        for (ProcessResult pr : processResult) {
            System.out.println(pr.getSubject().getURL());
            if (pr.getSubject().getURL().contains("2Failed-07")) {
                assertEquals(TestSolution.PASSED, pr.getValue());
            } else {
                assertEquals(TestSolution.FAILED, pr.getValue());
                assertEquals(2, pr.getRemarkSet().size());
                if (pr.getSubject() instanceof Page) {
                    assertEquals(RemarkMessageStore.TITLE_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                            ((ProcessRemark)pr.getRemarkSet().toArray()[0]).getMessageCode());
                    assertEquals(RemarkMessageStore.TITLE_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                            ((ProcessRemark)pr.getRemarkSet().toArray()[1]).getMessageCode());
                } else {
                    assertEquals(RemarkMessageStore.TITLE_NOT_UNIQUE_MSG,
                            ((ProcessRemark)pr.getRemarkSet().toArray()[0]).getMessageCode());
                    assertEquals(RemarkMessageStore.TITLE_NOT_UNIQUE_MSG,
                            ((ProcessRemark)pr.getRemarkSet().toArray()[1]).getMessageCode());
                }
            } 
        }
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = consolidateWithListAsReturn("Seo.Test.6.4.1-2Failed-04");
        assertEquals(5, processResult.size());
        for (ProcessResult pr : processResult) {
            System.out.println(pr.getSubject().getURL());
            if (pr.getSubject().getURL().contains("2Failed-11") || 
                    pr.getSubject().getURL().contains("2Failed-12")) {
                assertEquals(TestSolution.PASSED, pr.getValue());
            } else {
                assertEquals(TestSolution.FAILED, pr.getValue());
                assertEquals(2, pr.getRemarkSet().size());
                if (pr.getSubject() instanceof Page) {
                    assertEquals(RemarkMessageStore.TITLE_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                            ((ProcessRemark)pr.getRemarkSet().toArray()[0]).getMessageCode());
                    assertEquals(RemarkMessageStore.TITLE_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                            ((ProcessRemark)pr.getRemarkSet().toArray()[1]).getMessageCode());
                } else {
                    assertEquals(RemarkMessageStore.TITLE_NOT_UNIQUE_MSG,
                            ((ProcessRemark)pr.getRemarkSet().toArray()[0]).getMessageCode());
                    assertEquals(RemarkMessageStore.TITLE_NOT_UNIQUE_MSG,
                            ((ProcessRemark)pr.getRemarkSet().toArray()[1]).getMessageCode());
                }
            } 
        }

        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = consolidateWithListAsReturn("Seo.Test.6.4.1-4NA-01");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.NOT_APPLICABLE, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.NOT_APPLICABLE, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.NOT_APPLICABLE, ((List<ProcessResult>)processResult).get(2).getValue());
    }

}