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
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.accessiweb21.test.Aw21RuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class Aw21Rule10041Test extends Aw21RuleImplementationTestCase {

    public Aw21Rule10041Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName( "org.tanaguru.rules.accessiweb21.Aw21Rule10041");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW21.Test.10.04.01-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-4NA-01.html"));
        
        getWebResourceMap().put("AW21.Test.10.04.01-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-1Passed-01.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-1Passed-02.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-1Passed-03.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-1Passed-04.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-1Passed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-1Passed-05.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-1Passed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-1Passed-06.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-1Passed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-1Passed-07.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-1Passed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-1Passed-08.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-1Passed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-1Passed-09.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-1Passed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-1Passed-10.html"));

        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-01_1",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-01_1.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-01_2",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-01_2.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-01_3",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-01_3.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-01_4",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-01_4.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-01_5",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-01_5.html"));

        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-02_1",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-02_1.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-02_2",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-02_2.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-02_3",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-02_3.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-02_4",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-02_4.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-02_5",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-02_5.html"));

        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-03_1",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-03_1.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-03_2",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-03_2.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-03_3",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-03_3.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-03_4",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-03_4.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-03_5",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-03_5.html"));

        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-04_1",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-04_1.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-04_2",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-04_2.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-04_3",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-04_3.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-04_4",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-04_4.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-04_5",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-04_5.html"));

        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-05_1",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-05_1.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-05_2",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-05_2.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-05_3",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-05_3.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-05_4",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-05_4.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-05_5",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-05_5.html"));
        
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-06_1",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-06_1.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-06_2",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-06_2.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-06_3",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-06_3.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-06_4",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-06_4.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-06_5",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-06_5.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-06_6",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-06_6.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-06_7",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-06_7.html"));
        getWebResourceMap().put("AW21.Test.10.04.01-2Failed-06_8",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-2Failed-06_8.html"));

        getWebResourceMap().put("AW21.Test.10.04.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule10041/AW21.Test.10.04.01-3NMI-01.html"));

        setUpRelatedContentMap();
    }

    private void setUpRelatedContentMap(){
        List<String> relatedContent1 = new ArrayList<String>();
        relatedContent1.add("css/AW21.Test.10.04.01-2Failed-01_1.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.10.04.01-2Failed-01_1"), relatedContent1);

        List<String> relatedContent2 = new ArrayList<String>();
        relatedContent2.add("css/AW21.Test.10.04.01-2Failed-01_2.css");
        relatedContent2.add("css/AW21.Test.10.04.01-2Failed-01_2_1.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.10.04.01-2Failed-01_2"), relatedContent2);

        List<String> relatedContent3 = new ArrayList<String>();
        relatedContent3.add("css/AW21.Test.10.04.01-2Failed-01_3.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.10.04.01-2Failed-01_3"), relatedContent3);
        
        List<String> relatedContent4 = new ArrayList<String>();
        relatedContent4.add("css/AW21.Test.10.04.01-2Failed-06_1.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.10.04.01-2Failed-06_1"), relatedContent4);
        
        List<String> relatedContent5 = new ArrayList<String>();
        relatedContent5.add("css/AW21.Test.10.04.01-2Failed-06_2.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.10.04.01-2Failed-06_2"), relatedContent5);
        
        List<String> relatedContent6 = new ArrayList<String>();
        relatedContent6.add("css/AW21.Test.10.04.01-2Failed-06_3.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.10.04.01-2Failed-06_3"), relatedContent6);
        
        List<String> relatedContent7 = new ArrayList<String>();
        relatedContent7.add("css/AW21.Test.10.04.01-2Failed-06_4.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.10.04.01-2Failed-06_4"), relatedContent7);
        
        List<String> relatedContent8 = new ArrayList<String>();
        relatedContent8.add("css/AW21.Test.10.04.01-2Failed-06_5.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.10.04.01-2Failed-06_5"), relatedContent8);
        
        List<String> relatedContent9 = new ArrayList<String>();
        relatedContent9.add("css/AW21.Test.10.04.01-2Failed-06_6.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.10.04.01-2Failed-06_6"), relatedContent9);
        
        List<String> relatedContent10 = new ArrayList<String>();
        relatedContent10.add("css/AW21.Test.10.04.01-2Failed-06_7.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.10.04.01-2Failed-06_7"), relatedContent10);
        
        List<String> relatedContent11 = new ArrayList<String>();
        relatedContent11.add("css/AW21.Test.10.04.01-2Failed-06_8.css");
        getRelatedContentMap().put(getWebResourceMap().get("AW21.Test.10.04.01-2Failed-06_8"), relatedContent11);
    }

    @Override
    protected void setProcess() {
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-01_1").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-01_2").getValue());
//        assertEquals(TestSolution.NOT_TESTED,
//                processPageTest("AW21.Test.10.04.01-2Failed-01_3").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-01_4").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-01_5").getValue());

        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-02_1").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-02_2").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-02_3").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-02_4").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-02_5").getValue());

        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-03_1").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-03_2").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-03_3").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-03_4").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-03_5").getValue());

        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-04_1").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-04_2").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-04_3").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-04_4").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-04_5").getValue());

        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-05_1").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-05_2").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-05_3").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-05_4").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-2Failed-05_5").getValue());
        
        ProcessResult processResult = processPageTest("AW21.Test.10.04.01-2Failed-06_1");
        assertEquals(TestSolution.NOT_TESTED,processResult.getValue());
//        assertEquals(Integer.valueOf(1), Integer.valueOf(processResult.getRemarkSet().size()));
        
        processResult = processPageTest("AW21.Test.10.04.01-2Failed-06_2");
        assertEquals(TestSolution.NOT_TESTED,processResult.getValue());
//        assertEquals(Integer.valueOf(1), Integer.valueOf(processResult.getRemarkSet().size()));
        
        processResult = processPageTest("AW21.Test.10.04.01-2Failed-06_3");
        assertEquals(TestSolution.NOT_TESTED,processResult.getValue());
//        assertEquals(Integer.valueOf(1), Integer.valueOf(processResult.getRemarkSet().size()));
        
        processResult = processPageTest("AW21.Test.10.04.01-2Failed-06_4");
        assertEquals(TestSolution.NOT_TESTED,processResult.getValue());
//        assertEquals(Integer.valueOf(1), Integer.valueOf(processResult.getRemarkSet().size()));
        
        processResult = processPageTest("AW21.Test.10.04.01-2Failed-06_5");
        assertEquals(TestSolution.NOT_TESTED,processResult.getValue());
//        assertEquals(Integer.valueOf(1), Integer.valueOf(processResult.getRemarkSet().size()));
        
        processResult = processPageTest("AW21.Test.10.04.01-2Failed-06_6");
        assertEquals(TestSolution.NOT_TESTED,processResult.getValue());
//        assertEquals(Integer.valueOf(1), Integer.valueOf(processResult.getRemarkSet().size()));
        
        processResult = processPageTest("AW21.Test.10.04.01-2Failed-06_7");
        assertEquals(TestSolution.NOT_TESTED,processResult.getValue());
//        assertEquals(Integer.valueOf(1), Integer.valueOf(processResult.getRemarkSet().size()));
        
        processResult = processPageTest("AW21.Test.10.04.01-2Failed-06_8");
        assertEquals(TestSolution.NOT_TESTED,processResult.getValue());
//        assertEquals(Integer.valueOf(4), Integer.valueOf(processResult.getRemarkSet().size()));
        
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-1Passed-01").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-1Passed-02").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-1Passed-03").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-1Passed-04").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-1Passed-05").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-1Passed-06").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-1Passed-07").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-1Passed-08").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-1Passed-09").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-1Passed-10").getValue());

        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-4NA-01").getValue());

        // the error caused by the example is thrown when the used css parser
        // is flute. With other parsers the error is not encountered and the 
        // test is passed
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                processPageTest("AW21.Test.10.04.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("AW21.Test.10.04.01-3NMI-01").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-01_1").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-01_2").getValue());
//        assertEquals(TestSolution.NOT_TESTED,
//                consolidate("AW21.Test.10.04.01-2Failed-01_3").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-01_4").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-01_5").getValue());

        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-02_1").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-02_2").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-02_3").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-02_4").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-02_5").getValue());

        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-03_1").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-03_2").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-03_3").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-03_4").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-03_5").getValue());

        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-04_1").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-04_2").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-04_3").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-04_4").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-04_5").getValue());

        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-05_1").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-05_2").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-05_3").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-05_4").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-05_5").getValue());

        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-06_1").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-06_2").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-06_3").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-06_4").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-06_5").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-06_6").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-06_7").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-2Failed-06_8").getValue());

        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-1Passed-01").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-1Passed-02").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-1Passed-03").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-1Passed-04").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-1Passed-05").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-1Passed-06").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-1Passed-07").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-1Passed-08").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-1Passed-09").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-1Passed-10").getValue());

        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-4NA-01").getValue());

        assertEquals(TestSolution.NOT_TESTED,
                consolidate("AW21.Test.10.04.01-3NMI-01").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("AW21.Test.10.04.01-3NMI-01").getValue());
    }

}
