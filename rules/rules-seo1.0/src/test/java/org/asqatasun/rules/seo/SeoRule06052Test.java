/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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

import org.asqatasun.entity.audit.SSP;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.rules.seo.test.SeoRuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class SeoRule06052Test extends SeoRuleImplementationTestCase {

    public SeoRule06052Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName (
                "org.asqatasun.rules.seo.SeoRule06052");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Seo.Test.6.5.2-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06052/Seo.Test.6.5.2-1Passed-01.html"));
        getWebResourceMap().put("Seo.Test.6.5.2-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06052/Seo.Test.6.5.2-1Passed-02.html"));
        getWebResourceMap().put("Seo.Test.6.5.2-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06052/Seo.Test.6.5.2-1Passed-03.html"));
        getWebResourceMap().put("Seo.Test.6.5.2-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06052/Seo.Test.6.5.2-1Passed-04.html"));
        getWebResourceMap().put("Seo.Test.6.5.2-1Passed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06052/Seo.Test.6.5.2-1Passed-05.html"));
        getWebResourceMap().put("Seo.Test.6.5.2-1Passed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06052/Seo.Test.6.5.2-1Passed-06.html"));
        getWebResourceMap().put("Seo.Test.6.5.2-1Passed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06052/Seo.Test.6.5.2-1Passed-07.html"));
        getWebResourceMap().put("Seo.Test.6.5.2-1Passed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06052/Seo.Test.6.5.2-1Passed-08.html"));
        getWebResourceMap().put("Seo.Test.6.5.2-1Passed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06052/Seo.Test.6.5.2-1Passed-09.html"));
        getWebResourceMap().put("Seo.Test.6.5.2-1Passed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06052/Seo.Test.6.5.2-1Passed-10.html"));
        getWebResourceMap().put("Seo.Test.6.5.2-1Passed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06052/Seo.Test.6.5.2-1Passed-11.html"));
        getWebResourceMap().put("Seo.Test.6.5.2-1Passed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06052/Seo.Test.6.5.2-1Passed-12.html"));
        getWebResourceMap().put("Seo.Test.6.5.2-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06052/Seo.Test.6.5.2-2Failed-01.html"));
        getWebResourceMap().put("Seo.Test.6.5.2-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule06052/Seo.Test.6.5.2-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        WebResource webResource = webResourceMap.get("Seo.Test.6.5.2-1Passed-01");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" " +
            "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.6.5.2-1Passed-01").getValue());
        webResource = webResourceMap.get("Seo.Test.6.5.2-1Passed-02");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE HTML PUBLIC " +
            "\"-//W3C//DTD HTML 4.01 Transitional//EN\" " +
            "\"http://www.w3.org/TR/html4/loose.dtd\">");
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.6.5.2-1Passed-02").getValue());
        webResource = webResourceMap.get("Seo.Test.6.5.2-1Passed-03");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\" \"http://www.w3.org/TR/html4/frameset.dtd\">");
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.6.5.2-1Passed-03").getValue());
        webResource = webResourceMap.get("Seo.Test.6.5.2-1Passed-04");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.6.5.2-1Passed-04").getValue());
        webResource = webResourceMap.get("Seo.Test.6.5.2-1Passed-05");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.6.5.2-1Passed-05").getValue());
        webResource = webResourceMap.get("Seo.Test.6.5.2-1Passed-06");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Frameset//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd\">");
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.6.5.2-1Passed-06").getValue());
        webResource = webResourceMap.get("Seo.Test.6.5.2-1Passed-07");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.6.5.2-1Passed-07").getValue());
        webResource = webResourceMap.get("Seo.Test.6.5.2-1Passed-08");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE " +
            "html " +
            "PUBLIC " +
            "\"-//W3C//DTD XHTML Basic 1.1//EN\" " +
            "\"http://www.w3.org/TR/xhtml-basic/xhtml-basic11.dtd\">");
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.6.5.2-1Passed-08").getValue());
        webResource = webResourceMap.get("Seo.Test.6.5.2-1Passed-09");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE math PUBLIC \"-//W3C//DTD MathML 2.0//EN\" \"http://www.w3.org/TR/MathML2/dtd/mathml2.dtd\">");
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.6.5.2-1Passed-09").getValue());
        webResource = webResourceMap.get("Seo.Test.6.5.2-1Passed-10");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE math SYSTEM \"http://www.w3.org/Math/DTD/mathml1/mathml.dtd\">");
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.6.5.2-1Passed-10").getValue());
        webResource = webResourceMap.get("Seo.Test.6.5.2-1Passed-11");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1 plus MathML 2.0 plus SVG 1.1//EN\" \"http://www.w3.org/2002/04/xhtml-math-svg/xhtml-math-svg.dtd\">");
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.6.5.2-1Passed-11").getValue());
        webResource = webResourceMap.get("Seo.Test.6.5.2-1Passed-12");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE svg:svg PUBLIC \"-//W3C//DTD XHTML 1.1 plus MathML 2.0 plus SVG 1.1//EN\" \"http://www.w3.org/2002/04/xhtml-math-svg/xhtml-math-svg.dtd\">");
        assertEquals(TestSolution.PASSED,
                processPageTest("Seo.Test.6.5.2-1Passed-12").getValue());
        webResource = webResourceMap.get("Seo.Test.6.5.2-2Failed-01");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/sxhtml1/DTD/xhtml1-strict.dtd\">");
        assertEquals(TestSolution.FAILED,
                processPageTest("Seo.Test.6.5.2-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Seo.Test.6.5.2-4NA-01").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.6.5.2-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.6.5.2-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.6.5.2-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.6.5.2-1Passed-04").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.6.5.2-1Passed-05").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.6.5.2-1Passed-06").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.6.5.2-1Passed-07").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.6.5.2-1Passed-08").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.6.5.2-1Passed-09").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.6.5.2-1Passed-10").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.6.5.2-1Passed-11").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.6.5.2-1Passed-12").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.6.5.2-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Seo.Test.6.5.2-4NA-01").getValue());
    }

}
