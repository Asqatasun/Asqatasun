/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.rules.seo;

import java.util.Collection;
import java.util.List;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.subject.Page;
import org.tanaguru.entity.subject.Site;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.seo.test.SeoSiteRuleWithPageResultImplementationTestCase;

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
        setRuleImplementationClassName("org.tanaguru.rules.seo.SeoRule01021");
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

        assertEquals(2, ((List<ProcessResult>)processResult).get(0).getRemarkSet().size());
        assertEquals(RemarkMessageStore.META_DESC_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(0).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.META_DESC_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(0).getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(2, ((List<ProcessResult>)processResult).get(1).getRemarkSet().size());
        assertEquals(RemarkMessageStore.META_DESC_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(1).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.META_DESC_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                ((ProcessRemark)((List<ProcessResult>)processResult).get(1).getRemarkSet().toArray()[1]).getMessageCode());
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