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

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.tanaguru.rules.keystore.EvidenceStore;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 9-1-2 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule090102Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule090102Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule090102");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.09.01.02-1Passed-01");
        addWebResource("Rgaa30.Test.09.01.02-1Passed-02");
        addWebResource("Rgaa30.Test.09.01.02-1Passed-03");
        addWebResource("Rgaa30.Test.09.01.02-1Passed-04");
        addWebResource("Rgaa30.Test.09.01.02-1Passed-05");
        addWebResource("Rgaa30.Test.09.01.02-1Passed-06");
        addWebResource("Rgaa30.Test.09.01.02-1Passed-07");
        addWebResource("Rgaa30.Test.09.01.02-1Passed-08");
        addWebResource("Rgaa30.Test.09.01.02-1Passed-09");
        addWebResource("Rgaa30.Test.09.01.02-1Passed-A01");
        addWebResource("Rgaa30.Test.09.01.02-1Passed-A02");
        addWebResource("Rgaa30.Test.09.01.02-1Passed-A03");
        addWebResource("Rgaa30.Test.09.01.02-2Failed-01");
        addWebResource("Rgaa30.Test.09.01.02-2Failed-02");
        addWebResource("Rgaa30.Test.09.01.02-2Failed-03");
        addWebResource("Rgaa30.Test.09.01.02-2Failed-04");
        addWebResource("Rgaa30.Test.09.01.02-2Failed-05");
        addWebResource("Rgaa30.Test.09.01.02-2Failed-06");
        addWebResource("Rgaa30.Test.09.01.02-2Failed-A01");
        addWebResource("Rgaa30.Test.09.01.02-2Failed-A02");
        addWebResource("Rgaa30.Test.09.01.02-2Failed-A03");
        addWebResource("Rgaa30.Test.09.01.02-4NA-01");
        addWebResource("Rgaa30.Test.09.01.02-4NA-A01");
        addWebResource("Rgaa30.Test.09.01.02-4NA-A02");
        addWebResource("Rgaa30.Test.09.01.02-4NA-A03");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.09.01.02-1Passed-01"),1);

        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.09.01.02-1Passed-02"),3);

        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.09.01.02-1Passed-03"),3);

        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.09.01.02-1Passed-04"),4);

        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.09.01.02-1Passed-05"),5);

        //----------------------------------------------------------------------
        //------------------------------1Passed-06------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.09.01.02-1Passed-06"),6);

        //----------------------------------------------------------------------
        //------------------------------1Passed-07------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.09.01.02-1Passed-07"),1);

        //----------------------------------------------------------------------
        //------------------------------1Passed-08------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.09.01.02-1Passed-08"),2);

        //----------------------------------------------------------------------
        //------------------------------1Passed-09------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.09.01.02-1Passed-09"),2);

        //----------------------------------------------------------------------
        //------------------------------1Passed-A01-----------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.09.01.02-1Passed-A01"),3);

        //----------------------------------------------------------------------
        //------------------------------1Passed-A02-----------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.09.01.02-1Passed-A02"),6);

        //----------------------------------------------------------------------
        //------------------------------1Passed-A03-----------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.09.01.02-1Passed-A03"),8);

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.09.01.02-2Failed-01");
        checkResultIsFailed(processResult, 3, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG,
                HtmlElementStore.H3_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.PREVIOUS_H_TAG_INDEX_EE, "h1"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.09.01.02-2Failed-02");
        checkResultIsFailed(processResult, 5, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG,
                HtmlElementStore.H1_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.FIRST_H_TAG_INDEX_EE, "h2"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.09.01.02-2Failed-03");
        checkResultIsFailed(processResult, 3, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG,
                HtmlElementStore.H4_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.PREVIOUS_H_TAG_INDEX_EE, "h2"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.09.01.02-2Failed-04");
        checkResultIsFailed(processResult, 4, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG,
                HtmlElementStore.H5_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.PREVIOUS_H_TAG_INDEX_EE, "h3"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.09.01.02-2Failed-05");
        checkResultIsFailed(processResult, 5, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG,
                HtmlElementStore.H6_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.PREVIOUS_H_TAG_INDEX_EE, "h4"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.09.01.02-2Failed-06");
        checkResultIsFailed(processResult, 10, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG,
                HtmlElementStore.H6_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.PREVIOUS_H_TAG_INDEX_EE, "h4"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-A01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.09.01.02-2Failed-A01");
        checkResultIsFailed(processResult, 3, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG,
                HtmlElementStore.DIV_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.PREVIOUS_H_TAG_INDEX_EE, "h1"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-A02-----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.09.01.02-2Failed-A02");
        checkResultIsFailed(processResult, 10, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG,
                HtmlElementStore.DIV_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.PREVIOUS_H_TAG_INDEX_EE, "div role=\"heading\" aria-level=\"4\""));

        //----------------------------------------------------------------------
        //------------------------------2Failed-A03-----------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.09.01.02-2Failed-A03");
        checkResultIsFailed(processResult, 5, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG,
                HtmlElementStore.DIV_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.PREVIOUS_H_TAG_INDEX_EE, "div role=\"heading\" aria-level=\"1\""));

        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.09.01.02-4NA-01"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-A01---------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.09.01.02-4NA-A01"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-A02---------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.09.01.02-4NA-A02"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-A03---------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.09.01.02-4NA-A03"));
    }

}
