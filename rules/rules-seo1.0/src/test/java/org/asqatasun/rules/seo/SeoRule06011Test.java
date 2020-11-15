/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.rules.seo;

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.rules.seo.test.SeoRuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class SeoRule06011Test extends SeoRuleImplementationTestCase {

    public SeoRule06011Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.asqatasun.rules.seo.SeoRule06011");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Seo.Test.6.1.1-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06011/Seo.Test.6.1.1-1Passed-01.html"));
        getWebResourceMap().put("Seo.Test.6.1.1-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06011/Seo.Test.6.1.1-2Failed-01.html"));
        getWebResourceMap().put("Seo.Test.6.1.1-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06011/Seo.Test.6.1.1-2Failed-02.html"));
        getWebResourceMap().put("Seo.Test.6.1.1-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06011/Seo.Test.6.1.1-2Failed-03.html"));
    }

    @Override
    protected void setProcess() {
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.6.1.1-1Passed-01").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("Seo.Test.6.1.1-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("Seo.Test.6.1.1-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("Seo.Test.6.1.1-2Failed-03").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.6.1.1-1Passed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.6.1.1-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.6.1.1-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.6.1.1-2Failed-03").getValue());
    }
}
