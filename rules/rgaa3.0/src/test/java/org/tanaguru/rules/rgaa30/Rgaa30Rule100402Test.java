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
package org.tanaguru.rules.rgaa30;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.tanaguru.rules.keystore.EvidenceStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 10-4-2 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule100402Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule100402Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule100402");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.10.04.02-4NA-01");
        addWebResource("Rgaa30.Test.10.04.02-4NA-02");
        addWebResource("Rgaa30.Test.10.04.02-4NA-03");
        addWebResource("Rgaa30.Test.10.04.02-1Passed-01");
        addWebResource("Rgaa30.Test.10.04.02-1Passed-02");
        addWebResource("Rgaa30.Test.10.04.02-1Passed-03");
        addWebResource("Rgaa30.Test.10.04.02-1Passed-04");
        addWebResource("Rgaa30.Test.10.04.02-1Passed-05");
        addWebResource("Rgaa30.Test.10.04.02-1Passed-06");
        addWebResource("Rgaa30.Test.10.04.02-1Passed-07");
        addWebResource("Rgaa30.Test.10.04.02-1Passed-08");
        addWebResource("Rgaa30.Test.10.04.02-1Passed-09");
        addWebResource("Rgaa30.Test.10.04.02-1Passed-10");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-01_1");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-01_2");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-01_3");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-01_4");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-02_1");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-02_2");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-02_3");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-02_4");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-02_5");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-03_1");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-03_2");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-03_3");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-03_4");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-04_1");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-04_4");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-05_1");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-05_2");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-05_3");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-05_4");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-05_5");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-06_1");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-06_2");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-06_3");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-06_4");
        addWebResource("Rgaa30.Test.10.04.02-2Failed-06_5");
        
        setUpRelatedContentMap();
    }
    
    private void setUpRelatedContentMap(){
        List<String> relatedContent1 = new ArrayList<>();
        relatedContent1.add("css/Rgaa30.Test.10.04.02-2Failed-01_1.css");
        getRelatedContentMap().put(getWebResourceMap().get("Rgaa30.Test.10.04.02-2Failed-01_1"), relatedContent1);

        List<String> relatedContent2 = new ArrayList<>();
        relatedContent2.add("css/Rgaa30.Test.10.04.02-2Failed-01_2.css");
        relatedContent2.add("css/Rgaa30.Test.10.04.02-2Failed-01_2_1.css");
        getRelatedContentMap().put(getWebResourceMap().get("Rgaa30.Test.10.04.02-2Failed-01_2"), relatedContent2);

        List<String> relatedContent3 = new ArrayList<>();
        relatedContent3.add("css/Rgaa30.Test.10.04.02-2Failed-01_3.css");
        getRelatedContentMap().put(getWebResourceMap().get("Rgaa30.Test.10.04.02-2Failed-01_3"), relatedContent3);
        
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.10.04.02-1Passed-01"),1);
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.10.04.02-1Passed-02"),1);
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.10.04.02-1Passed-03"),2);
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.10.04.02-1Passed-04"),2);
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.10.04.02-1Passed-05"),2);

        
        //----------------------------------------------------------------------
        //------------------------------1Passed-06------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.10.04.02-1Passed-06"),2);
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-07------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.10.04.02-1Passed-07"),2);
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-08------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.10.04.02-1Passed-08"),1);
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-09------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.10.04.02-1Passed-09"),1);
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-10------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.10.04.02-1Passed-10"),1);
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01_1----------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-01_1");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "pt",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "Rgaa30.Test.10.04.02-2Failed-01_1.css"));
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01_2----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-01_2");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "pt",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "Rgaa30.Test.10.04.02-2Failed-01_2_1.css"));
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01_3----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-01_3");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "pt",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "Rgaa30.Test.10.04.02-2Failed-01_3.css"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-01_4----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-01_4");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "pt",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "locale"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-02_1----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-02_1");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "pc",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "locale"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-02_2----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-02_2");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "pc",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "locale"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-02_3----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-02_3");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "pc",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "locale"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-02_4----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-02_4");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "pc",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "locale"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-02_5----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-02_5");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "pc",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "locale"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-03_1----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-03_1");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "mm",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "locale"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-03_2----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-03_2");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "mm",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "locale"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-03_3----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-03_3");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "mm",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "locale"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-03_4----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-03_4");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "mm",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "locale"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-04_1----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-04_1");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "cm",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "locale"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-04_4----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-04_4");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "cm",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "locale"));



        //----------------------------------------------------------------------
        //------------------------------2Failed-05_1----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-05_1");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "in",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "locale"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-05_2----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-05_2");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "in",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "locale"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-05_3----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-05_3");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "in",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "locale"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-05_4----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-05_4");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "in",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "locale"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-05_5----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-05_5");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "in",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "h1"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "locale"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-06_1----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-06_1");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "in",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "div"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "inline"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-06_2----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-06_2");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "pt",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "div"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "inline"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-06_3----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-06_3");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "mm",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "div"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "inline"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-06_4----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-06_4");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "cm",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "div"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "inline"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-06_5----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.10.04.02-2Failed-06_5");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.BAD_UNIT_TYPE_MSG,
                "pc",
                1,
                true,
                new ImmutablePair(EvidenceStore.CSS_SELECTOR_EE, "div"),
                new ImmutablePair(EvidenceStore.CSS_FILENAME_EE, "inline"));

        //----------------------------------------------------------------------
        //------------------------------4NA-1-----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.10.04.02-4NA-01"));


        //----------------------------------------------------------------------
        //------------------------------4NA-2-----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.10.04.02-4NA-02"));


        //----------------------------------------------------------------------
        //------------------------------4NA-3-----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.10.04.02-4NA-03"));
    }

}
