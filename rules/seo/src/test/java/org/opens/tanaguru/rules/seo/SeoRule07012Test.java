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

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.seo.test.SeoRuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class SeoRule07012Test extends SeoRuleImplementationTestCase {
    
    public SeoRule07012Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.opens.tanaguru.rules.seo.SeoRule07012");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Seo.Test.7.1.2-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07012/Seo.Test.7.1.2-1Passed-01.html"));
        getWebResourceMap().put("Seo.Test.7.1.2-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07012/Seo.Test.7.1.2-1Passed-02.html"));
        getWebResourceMap().put("Seo.Test.7.1.2-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07012/Seo.Test.7.1.2-1Passed-03.html"));
        getWebResourceMap().put("Seo.Test.7.1.2-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07012/Seo.Test.7.1.2-1Passed-04.html"));
        getWebResourceMap().put("Seo.Test.7.1.2-1Passed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07012/Seo.Test.7.1.2-1Passed-05.html"));
        getWebResourceMap().put("Seo.Test.7.1.2-1Passed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07012/Seo.Test.7.1.2-1Passed-06.html"));
        getWebResourceMap().put("Seo.Test.7.1.2-1Passed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07012/Seo.Test.7.1.2-1Passed-07.html"));
        getWebResourceMap().put("Seo.Test.7.1.2-1Passed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07012/Seo.Test.7.1.2-1Passed-08.html"));
        getWebResourceMap().put("Seo.Test.7.1.2-1Passed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07012/Seo.Test.7.1.2-1Passed-09.html"));
        getWebResourceMap().put("Seo.Test.7.1.2-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07012/Seo.Test.7.1.2-2Failed-01.html"));
        getWebResourceMap().put("Seo.Test.7.1.2-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07012/Seo.Test.7.1.2-2Failed-02.html"));
        getWebResourceMap().put("Seo.Test.7.1.2-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07012/Seo.Test.7.1.2-2Failed-03.html"));
        getWebResourceMap().put("Seo.Test.7.1.2-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07012/Seo.Test.7.1.2-2Failed-04.html"));
        getWebResourceMap().put("Seo.Test.7.1.2-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07012/Seo.Test.7.1.2-2Failed-05.html"));
        getWebResourceMap().put("Seo.Test.7.1.2-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07012/Seo.Test.7.1.2-2Failed-06.html"));
        getWebResourceMap().put("Seo.Test.7.1.2-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule07012/Seo.Test.7.1.2-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.7.1.2-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.7.1.2-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.7.1.2-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.7.1.2-1Passed-04").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.7.1.2-1Passed-05").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.7.1.2-1Passed-06").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.7.1.2-1Passed-07").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.7.1.2-1Passed-08").getValue());
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.7.1.2-1Passed-09").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("Seo.Test.7.1.2-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("Seo.Test.7.1.2-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("Seo.Test.7.1.2-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("Seo.Test.7.1.2-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("Seo.Test.7.1.2-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("Seo.Test.7.1.2-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.7.1.2-4NA-01").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.7.1.2-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.7.1.2-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.7.1.2-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.7.1.2-1Passed-04").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.7.1.2-1Passed-05").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.7.1.2-1Passed-06").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.7.1.2-1Passed-07").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.7.1.2-1Passed-08").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.7.1.2-1Passed-09").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.7.1.2-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.7.1.2-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.7.1.2-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.7.1.2-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.7.1.2-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.7.1.2-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.7.1.2-4NA-01").getValue());
    }

}
