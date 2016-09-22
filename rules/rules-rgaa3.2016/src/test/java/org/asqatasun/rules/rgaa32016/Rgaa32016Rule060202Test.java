/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.asqatasun.rules.rgaa32016;

import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.rules.rgaa32016.test.Rgaa32016RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 6.2.2 of the referential RGAA 3.2016
 *
 * @author jkowalczyk
 */
public class Rgaa32016Rule060202Test extends Rgaa32016RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa32016Rule060202Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa32016.Rgaa32016Rule060202");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa32016.Test.06.02.02-2Failed-01");
        addWebResource("Rgaa32016.Test.06.02.02-2Failed-02");
        addWebResource("Rgaa32016.Test.06.02.02-2Failed-03");
        addWebResource("Rgaa32016.Test.06.02.02-2Failed-04");
        addWebResource("Rgaa32016.Test.06.02.02-3NMI-01");
        addWebResource("Rgaa32016.Test.06.02.02-3NMI-02");
        addWebResource("Rgaa32016.Test.06.02.02-3NMI-03");
        addWebResource("Rgaa32016.Test.06.02.02-3NMI-04");
        addWebResource("Rgaa32016.Test.06.02.02-3NMI-05");
        addWebResource("Rgaa32016.Test.06.02.02-3NMI-06");
        addWebResource("Rgaa32016.Test.06.02.02-4NA-01");
        addWebResource("Rgaa32016.Test.06.02.02-4NA-02");
        addWebResource("Rgaa32016.Test.06.02.02-4NA-03");
        addWebResource("Rgaa32016.Test.06.02.02-4NA-04");
        addWebResource("Rgaa32016.Test.06.02.02-4NA-05");
        addWebResource("Rgaa32016.Test.06.02.02-4NA-06");
        addWebResource("Rgaa32016.Test.06.02.02-4NA-07");
        addWebResource("Rgaa32016.Test.06.02.02-4NA-08");
        addWebResource("Rgaa32016.Test.06.02.02-4NA-09");

        //06.02.01 testcases
        getWebResourceMap().put("Rgaa32016.Test.06.02.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060201/Rgaa32016.Test.06.02.01-2Failed-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060201/Rgaa32016.Test.06.02.01-2Failed-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060201/Rgaa32016.Test.06.02.01-2Failed-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060201/Rgaa32016.Test.06.02.01-2Failed-04.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060201/Rgaa32016.Test.06.02.01-3NMI-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.01-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060201/Rgaa32016.Test.06.02.01-3NMI-02.html"));
        
        //06.02.03 testcases
        getWebResourceMap().put("Rgaa32016.Test.06.02.03-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060203/Rgaa32016.Test.06.02.03-2Failed-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.03-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060203/Rgaa32016.Test.06.02.03-2Failed-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.03-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060203/Rgaa32016.Test.06.02.03-2Failed-04.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.03-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060203/Rgaa32016.Test.06.02.03-3NMI-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.03-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060203/Rgaa32016.Test.06.02.03-3NMI-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.03-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060203/Rgaa32016.Test.06.02.03-3NMI-03.html"));
        
        //06.02.04 testcases
        getWebResourceMap().put("Rgaa32016.Test.06.02.04-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060204/Rgaa32016.Test.06.02.04-2Failed-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.04-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060204/Rgaa32016.Test.06.02.04-2Failed-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.04-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060204/Rgaa32016.Test.06.02.04-2Failed-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.04-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060204/Rgaa32016.Test.06.02.04-2Failed-04.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.04-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060204/Rgaa32016.Test.06.02.04-2Failed-05.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.04-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060204/Rgaa32016.Test.06.02.04-2Failed-06.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.04-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060204/Rgaa32016.Test.06.02.04-2Failed-07.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.04-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060204/Rgaa32016.Test.06.02.04-2Failed-08.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.04-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060204/Rgaa32016.Test.06.02.04-2Failed-09.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.04-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060204/Rgaa32016.Test.06.02.04-2Failed-10.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.04-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060204/Rgaa32016.Test.06.02.04-3NMI-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.04-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060204/Rgaa32016.Test.06.02.04-3NMI-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.04-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060204/Rgaa32016.Test.06.02.04-3NMI-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.04-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060204/Rgaa32016.Test.06.02.04-3NMI-04.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.04-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060204/Rgaa32016.Test.06.02.04-3NMI-05.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.02.04-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060204/Rgaa32016.Test.06.02.04-3NMI-06.html"));

        //06.02.05 testcases
        getWebResourceMap().put("Rgaa32016.Test.6.2.5-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060205/Rgaa32016.Test.6.2.5-2Failed-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.6.2.5-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060205/Rgaa32016.Test.6.2.5-2Failed-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.6.2.5-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060205/Rgaa32016.Test.6.2.5-2Failed-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.6.2.5-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060205/Rgaa32016.Test.6.2.5-2Failed-04.html"));
        getWebResourceMap().put("Rgaa32016.Test.6.2.5-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060205/Rgaa32016.Test.6.2.5-3NMI-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.6.2.5-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060205/Rgaa32016.Test.6.2.5-3NMI-02.html"));
        
        //06.05.01 testcases -> empty links
        getWebResourceMap().put("Rgaa32016.Test.06.05.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060501/Rgaa32016.Test.06.05.01-2Failed-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.05.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060501/Rgaa32016.Test.06.05.01-2Failed-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.05.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060501/Rgaa32016.Test.06.05.01-2Failed-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.05.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060501/Rgaa32016.Test.06.05.01-2Failed-04.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.05.01-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060501/Rgaa32016.Test.06.05.01-2Failed-05.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.05.01-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060501/Rgaa32016.Test.06.05.01-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //---------------------------2Failed-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa32016.Test.06.02.02-2Failed-01");
        checkResultIsFailed(processResult,1, 1);
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.02.02-2Failed-02");
        checkResultIsFailed(processResult,1, 1);
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.02.02-2Failed-03");
        checkResultIsFailed(processResult,1, 1);
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.02.02-2Failed-04");
        checkResultIsFailed(processResult,1, 1);
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-01------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.02.02-3NMI-01");
        checkResultIsPreQualified(processResult,1, 1);
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-02------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.02.02-3NMI-02");
        checkResultIsPreQualified(processResult,1, 1);
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-03------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.02.02-3NMI-03");
        checkResultIsPreQualified(processResult,1, 1);
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-04------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.02.02-3NMI-04");
        checkResultIsPreQualified(processResult,1, 1);
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-05------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.02.02-3NMI-05");
        checkResultIsPreQualified(processResult,1, 1);
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-06------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.02.02-3NMI-06");
        checkResultIsPreQualified(processResult,1, 1);
        
        //----------------------------------------------------------------------
        //---------------------------4NA-01-------------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.02-4NA-01"));
        
        //----------------------------------------------------------------------
        //---------------------------4NA-02-------------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.02-4NA-02"),1);
        
        //----------------------------------------------------------------------
        //---------------------------4NA-03-------------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.02-4NA-03"),1);
        
        //----------------------------------------------------------------------
        //---------------------------4NA-04-------------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.02-4NA-04"));
        
        //----------------------------------------------------------------------
        //---------------------------4NA-05-------------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.02-4NA-05"));
        
        //----------------------------------------------------------------------
        //---------------------------4NA-06-------------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.02-4NA-06"));
        
        //----------------------------------------------------------------------
        //---------------------------4NA-07-------------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.02-4NA-07"));
        
        //----------------------------------------------------------------------
        //---------------------------4NA-08-------------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.02-4NA-08"));
        
        //----------------------------------------------------------------------
        //---------------------------4NA-09-------------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.02-4NA-09"));     
        
        // other tests about links, scope of other is disjunted from the scope
        // of the current test : All these scopes are not applicable
        
         //----------------------------------------------------------------------
        //---------------------------4NA-6.2.1----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.01-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.01-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.01-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.01-2Failed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.01-3NMI-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.01-3NMI-02"));
        
        //----------------------------------------------------------------------
        //---------------------------4NA-6.2.3----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.03-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.03-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.03-2Failed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.03-3NMI-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.03-3NMI-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.03-3NMI-03"));
        
        //----------------------------------------------------------------------
        //---------------------------4NA-6.2.4----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.04-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.04-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.04-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.04-2Failed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.04-2Failed-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.04-2Failed-06"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.04-2Failed-07"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.04-2Failed-08"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.04-2Failed-09"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.04-2Failed-10"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.04-3NMI-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.04-3NMI-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.04-3NMI-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.04-3NMI-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.04-3NMI-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.02.04-3NMI-06"));
        
        //----------------------------------------------------------------------
        //---------------------------4NA-6.2.4----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.6.2.5-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.6.2.5-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.6.2.5-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.6.2.5-2Failed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.6.2.5-3NMI-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.6.2.5-3NMI-02"));
        
        //----------------------------------------------------------------------
        //---------------------------4NA-6.5.1----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.05.01-2Failed-01"), 0);
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.05.01-2Failed-02"), 0);
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.05.01-2Failed-03"), 0);
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.05.01-2Failed-04"), 0);
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.05.01-2Failed-05"), 0);
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.05.01-4NA-01"));
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.02.02-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.02.02-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.02.02-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.02.02-2Failed-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.02.02-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.02.02-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.02.02-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.02.02-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.02.02-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.02.02-3NMI-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.02-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.02-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.02-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.02-4NA-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.02-4NA-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.02-4NA-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.02-4NA-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.02-4NA-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.02-4NA-09").getValue());
        
        // 06.02.01 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.01-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.01-3NMI-02").getValue());
                
        // 06.02.03 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.03-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.03-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.03-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.03-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.03-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.03-3NMI-03").getValue());
        
        // 06.02.04 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.04-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.04-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.04-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.04-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.04-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.04-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.04-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.04-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.04-2Failed-09").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.04-2Failed-10").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.04-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.04-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.04-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.04-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.04-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.02.04-3NMI-06").getValue());
        
        // 06.02.05 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.6.2.5-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.6.2.5-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.6.2.5-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.6.2.5-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.6.2.5-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.6.2.5-3NMI-02").getValue());
        
        // 06.05.01 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.05.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.05.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.05.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.05.01-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.05.01-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.05.01-4NA-01").getValue());
    }

}
