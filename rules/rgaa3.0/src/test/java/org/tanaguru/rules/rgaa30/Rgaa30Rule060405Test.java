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
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.rules.keystore.AttributeStore;
import static org.tanaguru.rules.keystore.AttributeStore.HREF_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import org.tanaguru.rules.keystore.EvidenceStore;
import org.tanaguru.rules.keystore.HtmlElementStore;
import static org.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 6-4-5 of the referential Rgaa 3.0.
 *
 * @author
 */
public class Rgaa30Rule060405Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule060405Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa30.Rgaa30Rule060405");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.6.4.5-1Passed-01");
        addWebResource("Rgaa30.Test.6.4.5-2Failed-01");
        addWebResource("Rgaa30.Test.6.4.5-2Failed-02");
        addWebResource("Rgaa30.Test.6.4.5-2Failed-03");
        addWebResource("Rgaa30.Test.6.4.5-2Failed-04");
        addWebResource("Rgaa30.Test.6.4.5-3NMI-01");
        addWebResource("Rgaa30.Test.6.4.5-3NMI-02");
        addWebResource("Rgaa30.Test.6.4.5-4NA-01");
        addWebResource("Rgaa30.Test.6.4.5-4NA-02");
        addWebResource("Rgaa30.Test.6.4.5-4NA-03");
        
        // 06.04.01 testcases
        getWebResourceMap().put("Rgaa30.Test.06.04.01-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060401/Rgaa30.Test.06.04.01-1Passed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.01-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060401/Rgaa30.Test.06.04.01-1Passed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060401/Rgaa30.Test.06.04.01-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060401/Rgaa30.Test.06.04.01-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060401/Rgaa30.Test.06.04.01-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060401/Rgaa30.Test.06.04.01-3NMI-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.01-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060401/Rgaa30.Test.06.04.01-3NMI-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.01-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060401/Rgaa30.Test.06.04.01-3NMI-03.html"));
        
        //06.04.02 testcases
        getWebResourceMap().put("Rgaa30.Test.06.04.02-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060402/Rgaa30.Test.06.04.02-1Passed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.02-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060402/Rgaa30.Test.06.04.02-1Passed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.02-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060402/Rgaa30.Test.06.04.02-1Passed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.02-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060402/Rgaa30.Test.06.04.02-1Passed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.02-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060402/Rgaa30.Test.06.04.02-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.02-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060402/Rgaa30.Test.06.04.02-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.02-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060402/Rgaa30.Test.06.04.02-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.02-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060402/Rgaa30.Test.06.04.02-2Failed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.02-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060402/Rgaa30.Test.06.04.02-2Failed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.02-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060402/Rgaa30.Test.06.04.02-2Failed-06.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.02-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060402/Rgaa30.Test.06.04.02-2Failed-07.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.02-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060402/Rgaa30.Test.06.04.02-2Failed-08.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.02-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060402/Rgaa30.Test.06.04.02-3NMI-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.02-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060402/Rgaa30.Test.06.04.02-3NMI-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.02-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060402/Rgaa30.Test.06.04.02-3NMI-03.html"));

        // 06.04.03 testcases
        getWebResourceMap().put("Rgaa30.Test.06.04.03-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060403/Rgaa30.Test.06.04.03-1Passed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.03-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060403/Rgaa30.Test.06.04.03-1Passed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.03-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060403/Rgaa30.Test.06.04.03-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.03-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060403/Rgaa30.Test.06.04.03-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.03-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060403/Rgaa30.Test.06.04.03-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.03-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060403/Rgaa30.Test.06.04.03-3NMI-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.03-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060403/Rgaa30.Test.06.04.03-3NMI-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.03-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060403/Rgaa30.Test.06.04.03-3NMI-03.html"));
        
        // 06.04.04 testcases
        getWebResourceMap().put("Rgaa30.Test.06.04.04-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060404/Rgaa30.Test.06.04.04-1Passed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.04-1Passed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060404/Rgaa30.Test.06.04.04-1Passed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.04-1Passed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060404/Rgaa30.Test.06.04.04-1Passed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.04-1Passed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060404/Rgaa30.Test.06.04.04-1Passed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.04-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060404/Rgaa30.Test.06.04.04-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.04-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060404/Rgaa30.Test.06.04.04-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.04-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060404/Rgaa30.Test.06.04.04-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.04-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060404/Rgaa30.Test.06.04.04-2Failed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.04-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060404/Rgaa30.Test.06.04.04-2Failed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.04-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060404/Rgaa30.Test.06.04.04-2Failed-06.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.04-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060404/Rgaa30.Test.06.04.04-2Failed-07.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.04-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060404/Rgaa30.Test.06.04.04-2Failed-08.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.04-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060404/Rgaa30.Test.06.04.04-3NMI-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.04-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060404/Rgaa30.Test.06.04.04-3NMI-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.04.04-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060404/Rgaa30.Test.06.04.04-3NMI-03.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.6.4.5-1Passed-01"), 2);

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.6.4.5-2Failed-01");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                HtmlElementStore.A_ELEMENT,
                1,
                new ImmutablePair(TEXT_ELEMENT2, "CLIQUEZ ICI"),
                new ImmutablePair(TITLE_ATTR, "This is a link"),
                new ImmutablePair(HREF_ATTR, "my-link.html"),
                new ImmutablePair(EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, "cliquez ici this is a link"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                HtmlElementStore.A_ELEMENT,
                2,
                new ImmutablePair(TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(TITLE_ATTR, "This is a link"),
                new ImmutablePair(HREF_ATTR, "my-link2.html"),
                new ImmutablePair(EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, "cliquez ici this is a link"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.6.4.5-2Failed-02");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                HtmlElementStore.A_ELEMENT,
                1,
                new ImmutablePair(TEXT_ELEMENT2, "CLIQUEZ ICI"),
                new ImmutablePair(TITLE_ATTR, AttributeStore.ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(HREF_ATTR, "my-link.html"),
                new ImmutablePair(EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, "cliquez ici"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                HtmlElementStore.A_ELEMENT,
                2,
                new ImmutablePair(TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(TITLE_ATTR, AttributeStore.ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(HREF_ATTR, "my-link2.html"),
                new ImmutablePair(EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, "cliquez ici"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.6.4.5-2Failed-03");
        checkResultIsFailed(processResult, 4, 4);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                HtmlElementStore.A_ELEMENT,
                1,
                new ImmutablePair(TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(TITLE_ATTR, "This is a link"),
                new ImmutablePair(HREF_ATTR, "my-link.html"),
                new ImmutablePair(EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, "cliquez ici this is a link"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                HtmlElementStore.A_ELEMENT,
                2,
                new ImmutablePair(TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(TITLE_ATTR, "This is a link"),
                new ImmutablePair(HREF_ATTR, "my-link2.html"),
                new ImmutablePair(EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, "cliquez ici this is a link"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                HtmlElementStore.A_ELEMENT,
                3,
                new ImmutablePair(TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(TITLE_ATTR, AttributeStore.ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(HREF_ATTR, "my-link.html"),
                new ImmutablePair(EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, "cliquez ici"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                HtmlElementStore.A_ELEMENT,
                4,
                new ImmutablePair(TEXT_ELEMENT2, "CLIQUEZ ICI"),
                new ImmutablePair(TITLE_ATTR, AttributeStore.ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(HREF_ATTR, "my-link2.html"),
                new ImmutablePair(EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, "cliquez ici"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.6.4.5-2Failed-04");
        checkResultIsFailed(processResult, 4, 4);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                HtmlElementStore.A_ELEMENT,
                1,
                new ImmutablePair(TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(TITLE_ATTR, AttributeStore.ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(HREF_ATTR, "my-link.html"),
                new ImmutablePair(EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, "cliquez ici"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                HtmlElementStore.A_ELEMENT,
                2,
                new ImmutablePair(TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(TITLE_ATTR, AttributeStore.ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(HREF_ATTR, "my-link2.html"),
                new ImmutablePair(EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, "cliquez ici"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                HtmlElementStore.A_ELEMENT,
                3,
                new ImmutablePair(TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(TITLE_ATTR, "This is a link"),
                new ImmutablePair(HREF_ATTR, "my-link.html"),
                new ImmutablePair(EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, "cliquez ici this is a link"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                HtmlElementStore.A_ELEMENT,
                4,
                new ImmutablePair(TEXT_ELEMENT2, "CLIQUEZ ICI"),
                new ImmutablePair(TITLE_ATTR, "This is a link"),
                new ImmutablePair(HREF_ATTR, "my-link2.html"),
                new ImmutablePair(EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, "cliquez ici this is a link"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.6.4.5-3NMI-01");
        checkResultIsPreQualified(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                HtmlElementStore.A_ELEMENT,
                1,
                new ImmutablePair(TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(TITLE_ATTR, "This is a link"),
                new ImmutablePair(HREF_ATTR, "my-link.html"),
                new ImmutablePair(EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, "cliquez ici this is a link"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                HtmlElementStore.A_ELEMENT,
                2,
                new ImmutablePair(TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(TITLE_ATTR, "This is a link"),
                new ImmutablePair(HREF_ATTR, "my-link2.html"),
                new ImmutablePair(EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, "cliquez ici this is a link"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.6.4.5-3NMI-02");
        checkResultIsPreQualified(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                HtmlElementStore.A_ELEMENT,
                1,
                new ImmutablePair(TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(TITLE_ATTR, AttributeStore.ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(HREF_ATTR, "my-link.html"),
                new ImmutablePair(EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, "cliquez ici"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET,
                HtmlElementStore.A_ELEMENT,
                2,
                new ImmutablePair(TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(TITLE_ATTR, AttributeStore.ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(HREF_ATTR, "my-link2.html"),
                new ImmutablePair(EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, "cliquez ici"));


        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.6.4.5-4NA-01"));

        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.6.4.5-4NA-02"), 1);

        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.6.4.5-4NA-03"));
        
        
        // 06.04.01 testcases : All is Not Applicable
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.01-1Passed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.01-1Passed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.01-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.01-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.01-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.01-3NMI-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.01-3NMI-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.01-3NMI-03"));
        
        // 06.04.02 testcases : All is Not Applicable
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.02-1Passed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.02-1Passed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.02-1Passed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.02-1Passed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.02-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.02-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.02-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.02-2Failed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.02-2Failed-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.02-2Failed-06"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.02-2Failed-07"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.02-2Failed-08"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.02-3NMI-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.02-3NMI-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.02-3NMI-03"));

        // 06.04.03 testcases : All is Not Applicable
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.03-1Passed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.03-1Passed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.03-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.03-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.03-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.03-3NMI-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.03-3NMI-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.03-3NMI-03"));
        
        // 06.04.04 testcases : All is Not Applicable
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.04-1Passed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.04-1Passed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.04-1Passed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.04-1Passed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.04-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.04-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.04-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.04-2Failed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.04-2Failed-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.04-2Failed-06"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.04-2Failed-07"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.04-2Failed-08"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.04-3NMI-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.04-3NMI-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.04.04-3NMI-03"));
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa30.Test.6.4.5-1Passed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.6.4.5-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.6.4.5-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.6.4.5-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.6.4.5-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.6.4.5-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.6.4.5-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.6.4.5-4NA-03").getValue());
                
        // 06.04.01 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.01-1Passed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.01-1Passed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.01-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.01-3NMI-03").getValue());
        
        // 06.04.02 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.02-1Passed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.02-1Passed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.02-1Passed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.02-1Passed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.02-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.02-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.02-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.02-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.02-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.02-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.02-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.02-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.02-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.02-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.02-3NMI-03").getValue());

        // 06.04.03 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.03-1Passed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.03-1Passed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.03-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.03-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.03-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.03-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.03-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.03-3NMI-03").getValue());
        
        // 06.04.04 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.04-1Passed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.04-1Passed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.04-1Passed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.04-1Passed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.04-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.04-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.04-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.04-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.04-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.04-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.04-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.04-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.04-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.04-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.04.04-3NMI-03").getValue());
    }

}
