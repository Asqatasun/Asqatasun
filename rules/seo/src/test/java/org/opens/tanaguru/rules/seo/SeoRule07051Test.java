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

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;
import org.opens.tanaguru.rules.seo.test.SeoRuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class SeoRule07051Test extends SeoRuleImplementationTestCase {

    public SeoRule07051Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.seo.SeoRule07051");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Seo.Test.7.5.1-1Passed-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07051/Seo.Test.7.5.1-1Passed-01.html"));

        getWebResourceMap().put("Seo.Test.7.5.1-2Failed-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07051/Seo.Test.7.5.1-2Failed-01.html"));

        getWebResourceMap().put("Seo.Test.7.5.1-2Failed-02",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07051/Seo.Test.7.5.1-2Failed-02.html"));

        getWebResourceMap().put("Seo.Test.7.5.1-2Failed-03",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07051/Seo.Test.7.5.1-2Failed-03.html"));

        getWebResourceMap().put("Seo.Test.7.5.1-2Failed-04",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07051/Seo.Test.7.5.1-2Failed-04.html"));

        getWebResourceMap().put("Seo.Test.7.5.1-2Failed-05",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07051/Seo.Test.7.5.1-2Failed-05.html"));

        getWebResourceMap().put("Seo.Test.7.5.1-4NA-01",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07051/Seo.Test.7.5.1-4NA-01.html"));

        getWebResourceMap().put("Seo.Test.7.5.1-4NA-02",
                getWebResourceFactory().createPage(getTestcasesFilePath() +
                "SEO/SeoRule07051/Seo.Test.7.5.1-4NA-02.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("Seo.Test.7.5.1-1Passed-01");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Seo.Test.7.5.1-2Failed-01");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_H1_AND_TITLE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.7.5.1-2Failed-02");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_H1_AND_TITLE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.7.5.1-2Failed-03");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("Seo.Test.7.5.1-2Failed-04");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_H1_AND_TITLE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.7.5.1-2Failed-05");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.IDENTICAL_H1_AND_TITLE_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Seo.Test.7.5.1-4NA-01");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        processResult = processPageTest("Seo.Test.7.5.1-4NA-02");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        ProcessResult processResult =
                consolidate("Seo.Test.7.5.1-1Passed-01");
        assertEquals(TestSolution.PASSED, processResult.getValue());

        processResult = consolidate("Seo.Test.7.5.1-2Failed-01");
        assertEquals(TestSolution.FAILED, processResult.getValue());

        processResult = consolidate("Seo.Test.7.5.1-2Failed-02");
        assertEquals(TestSolution.FAILED, processResult.getValue());

        processResult = consolidate("Seo.Test.7.5.1-2Failed-03");
        assertEquals(TestSolution.PASSED, processResult.getValue());

        processResult = consolidate("Seo.Test.7.5.1-2Failed-04");
        assertEquals(TestSolution.FAILED, processResult.getValue());

        processResult = consolidate("Seo.Test.7.5.1-2Failed-05");
        assertEquals(TestSolution.FAILED, processResult.getValue());

        processResult = consolidate("Seo.Test.7.5.1-4NA-01");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());

        processResult = consolidate("Seo.Test.7.5.1-4NA-02");
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
    }

}
