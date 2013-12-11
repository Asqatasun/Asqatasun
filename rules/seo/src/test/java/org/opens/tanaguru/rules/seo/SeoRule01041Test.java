/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
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
public class SeoRule01041Test extends SeoSiteRuleWithPageResultImplementationTestCase {

    public SeoRule01041Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.seo.SeoRule01041");
    }

    @Override
    protected void setUpWebResourceMap() {
        Site site = getWebResourceFactory().createSite("file:Site-Passed1");
        getWebResourceMap().put("Seo.Test.1.4.1-1Passed-01", site);

        Page page = getWebResourceFactory().createPage(getTestcasesFilePath()
                + "SEO/SeoRule01041/Seo.Test.1.4.1-1Passed-01.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.1.4.1-page01", page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath()
                + "SEO/SeoRule01041/Seo.Test.1.4.1-1Passed-02.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.1.4.1-page02", page);

        site = getWebResourceFactory().createSite("file:Site-Failed1");
        getWebResourceMap().put("Seo.Test.1.4.1-2Failed-01", site);

        page = getWebResourceFactory().createPage(getTestcasesFilePath()
                + "SEO/SeoRule01041/Seo.Test.1.4.1-2Failed-01.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.1.4.1-page03", page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath()
                + "SEO/SeoRule01041/Seo.Test.1.4.1-2Failed-02.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.1.4.1-page04", page);

        site = getWebResourceFactory().createSite("file:Site-Passed2");
        getWebResourceMap().put("Seo.Test.1.4.1-1Passed-02", site);
        page = getWebResourceFactory().createPage(getTestcasesFilePath()
                + "SEO/SeoRule01041/Seo.Test.1.4.1-2Failed-02.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.1.4.1-page05", page);
        page = getWebResourceFactory().createPage(getTestcasesFilePath()
                + "SEO/SeoRule01041/Seo.Test.1.4.1-1Passed-01.html");
        site.addChild(page);
        getWebResourceMap().put("Seo.Test.1.4.1-page06", page);
    }

    @Override
    protected void setProcess() {
        process("Seo.Test.1.4.1-1Passed-01");
        process("Seo.Test.1.4.1-1Passed-02");
        process("Seo.Test.1.4.1-2Failed-01");
    }

    @Override
    protected void setConsolidate() {
        Collection<ProcessResult> processResult = consolidateWithListAsReturn("Seo.Test.1.4.1-1Passed-01");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>) processResult).get(0).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>) processResult).get(1).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>) processResult).get(2).getValue());

        processResult = consolidateWithListAsReturn("Seo.Test.1.4.1-1Passed-02");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>) processResult).get(0).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>) processResult).get(1).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>) processResult).get(2).getValue());

        processResult = consolidateWithListAsReturn("Seo.Test.1.4.1-2Failed-01");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>) processResult).get(0).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>) processResult).get(1).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>) processResult).get(2).getValue());

        assertEquals(2, ((List<ProcessResult>) processResult).get(0).getRemarkSet().size());
        assertEquals(RemarkMessageStore.SOURCE_CODE_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                ((ProcessRemark) ((List<ProcessResult>) processResult).get(0).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.SOURCE_CODE_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                ((ProcessRemark) ((List<ProcessResult>) processResult).get(0).getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(2, ((List<ProcessResult>) processResult).get(1).getRemarkSet().size());
        assertEquals(RemarkMessageStore.SOURCE_CODE_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                ((ProcessRemark) ((List<ProcessResult>) processResult).get(1).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(RemarkMessageStore.SOURCE_CODE_IDENTICAL_TO_ANOTHER_PAGE_MSG,
                ((ProcessRemark) ((List<ProcessResult>) processResult).get(1).getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(2, ((List<ProcessResult>) processResult).get(2).getRemarkSet().size());
        assertEquals(RemarkMessageStore.SOURCE_CODE_NOT_UNIQUE_MSG,
                ((ProcessRemark) ((List<ProcessResult>) processResult).get(2).getRemarkSet().toArray()[0]).getMessageCode());
    }
}