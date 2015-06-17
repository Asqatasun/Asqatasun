/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
package org.tanaguru.rules.accessiweb21;

import java.util.ArrayList;
import java.util.List;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.accessiweb21.test.Aw21RuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class Aw21Rule01021Test extends Aw21RuleImplementationTestCase {

    public Aw21Rule01021Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb21.Aw21Rule01021");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW21.Test.01.02.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule01021/AW21.Test.01.02.01-3NMI-01.html"));
        getWebResourceMap().put("AW21.Test.01.02.01-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule01021/AW21.Test.01.02.01-3NMI-02.html"));
        getWebResourceMap().put("AW21.Test.01.02.01-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule01021/AW21.Test.01.02.01-3NMI-03.html"));
        getWebResourceMap().put("AW21.Test.01.02.01-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule01021/AW21.Test.01.02.01-3NMI-04.html"));
        getWebResourceMap().put("AW21.Test.01.02.01-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule01021/AW21.Test.01.02.01-3NMI-05.html"));
        getWebResourceMap().put("AW21.Test.01.02.01-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule01021/AW21.Test.01.02.01-3NMI-06.html"));
        getWebResourceMap().put("AW21.Test.01.02.01-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule01021/AW21.Test.01.02.01-3NMI-07.html"));
        getWebResourceMap().put("AW21.Test.01.02.01-3NMI-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule01021/AW21.Test.01.02.01-3NMI-08.html"));
        getWebResourceMap().put("AW21.Test.01.02.01-3NMI-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule01021/AW21.Test.01.02.01-3NMI-09.html"));
        getWebResourceMap().put("AW21.Test.01.02.01-3NMI-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule01021/AW21.Test.01.02.01-3NMI-10.html"));
//        getWebResourceMap().put("AW21.Test.01.02.01-3NMI-11",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "AW21/Aw21Rule01021/AW21.Test.01.02.01-3NMI-11.html"));
        getWebResourceMap().put("AW21.Test.01.02.01-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule01021/AW21.Test.01.02.01-4NA-01.html"));
        getWebResourceMap().put("AW21.Test.01.02.01-4NA-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule01021/AW21.Test.01.02.01-4NA-02.html"));
        getWebResourceMap().put("AW21.Test.01.02.01-4NA-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule01021/AW21.Test.01.02.01-4NA-03.html"));

        setUpRelatedContentMap();
    }

    private void setUpRelatedContentMap(){

        List<String> relatedContent1 = new ArrayList<String>();
        relatedContent1.add("../images/decorative-black.jpg");
        relatedContent1.add("../images/decorative-white.jpg");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.01.02.01-3NMI-01"), relatedContent1);

        List<String> relatedContent2 = new ArrayList<String>();
        relatedContent2.add("../images/mono-dimension-heigth.jpg");
        relatedContent2.add("../images/mono-dimension-width.jpg");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.01.02.01-3NMI-02"), relatedContent2);

        List<String> relatedContent3 = new ArrayList<String>();
        relatedContent3.add("../images/decorative-black.jpg");
        relatedContent3.add("../images/decorative-red.jpg");
        relatedContent3.add("../images/informative1.jpg");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.01.02.01-3NMI-03"), relatedContent3);

        List<String> relatedContent4 = new ArrayList<String>();
        relatedContent4.add("../images/decorative-black.jpg");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.01.02.01-3NMI-04"), relatedContent4);

        List<String> relatedContent5 = new ArrayList<String>();
        relatedContent5.add("../images/decorative-black.jpg");
        relatedContent5.add("../images/decorative-red.jpg");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.01.02.01-3NMI-05"), relatedContent5);

        List<String> relatedContent6 = new ArrayList<String>();
        relatedContent6.add("../images/mono-dimension-heigth.jpg");
        relatedContent6.add("../images/mono-dimension-width.jpg");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.01.02.01-3NMI-06"), relatedContent6);

        List<String> relatedContent7 = new ArrayList<String>();
        relatedContent7.add("../images/mono-dimension-heigth.jpg");
        relatedContent7.add("../images/mono-dimension-width.jpg");
        relatedContent7.add("http://www.tanaguru.org/sites/default/files/pictures/picture-1.jpg");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.01.02.01-3NMI-09"), relatedContent7);

        List<String> relatedContent8 = new ArrayList<String>();
        relatedContent8.add("../images/decorative-black.jpg");
        relatedContent8.add("../images/informative1.jpg");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.01.02.01-3NMI-07"), relatedContent8);

        List<String> relatedContent9 = new ArrayList<String>();
        relatedContent9.add("../images/informative1.jpg");
        relatedContent9.add("../images/informative2.gif");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.01.02.01-3NMI-08"), relatedContent9);
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult =
                processPageTest("AW21.Test.01.02.01-3NMI-01");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());

        processResult = processPageTest("AW21.Test.01.02.01-3NMI-02");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());

        processResult = processPageTest("AW21.Test.01.02.01-3NMI-03");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());

        processResult = processPageTest("AW21.Test.01.02.01-3NMI-04");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());

        processResult = processPageTest("AW21.Test.01.02.01-3NMI-05");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals("SuspectedDecorativeImageWithNotEmptyAltAttribute",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW21.Test.01.02.01-3NMI-06");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals("SuspectedDecorativeImageWithNotEmptyAltAttribute",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("AW21.Test.01.02.01-3NMI-07");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());

        processResult = processPageTest("AW21.Test.01.02.01-3NMI-08");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());

        processResult = processPageTest("AW21.Test.01.02.01-3NMI-09");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());

        processResult = processPageTest("AW21.Test.01.02.01-3NMI-10");
        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
        assertEquals("SuspectedDecorativeImageWithNotEmptyAltAttribute",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        
//        processResult = processPageTest("AW21.Test.01.02.01-3NMI-11");
//        assertEquals(TestSolution.NEED_MORE_INFO,processResult.getValue());
//        assertTrue("SuspectedDecorativeImageWithNotEmptyAltAttribute",
//                (processResult.getRemarkSet().isEmpty()));
        
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.01.02.01-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.01.02.01-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW21.Test.01.02.01-4NA-03").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.01.02.01-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.01.02.01-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.01.02.01-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.01.02.01-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.01.02.01-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.01.02.01-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.01.02.01-3NMI-07").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.01.02.01-3NMI-08").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.01.02.01-3NMI-09").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW21.Test.01.02.01-3NMI-10").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.01.02.01-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.01.02.01-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW21.Test.01.02.01-4NA-03").getValue());
    }
}
