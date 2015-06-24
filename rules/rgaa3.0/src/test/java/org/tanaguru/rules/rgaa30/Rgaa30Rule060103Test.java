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
import org.tanaguru.rules.keystore.AttributeStore;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 6-1-3 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule060103Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule060103Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule060103");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.06.01.03-2Failed-01");
        addWebResource("Rgaa30.Test.06.01.03-2Failed-02");
        addWebResource("Rgaa30.Test.06.01.03-2Failed-03");
        addWebResource("Rgaa30.Test.06.01.03-2Failed-04");
        addWebResource("Rgaa30.Test.06.01.03-2Failed-05");
        addWebResource("Rgaa30.Test.06.01.03-2Failed-06");
        addWebResource("Rgaa30.Test.06.01.03-2Failed-07");
        addWebResource("Rgaa30.Test.06.01.03-2Failed-08");
        addWebResource("Rgaa30.Test.06.01.03-3NMI-01");
        addWebResource("Rgaa30.Test.06.01.03-3NMI-02");
        addWebResource("Rgaa30.Test.06.01.03-3NMI-03");
        addWebResource("Rgaa30.Test.06.01.03-3NMI-05");
        addWebResource("Rgaa30.Test.06.01.03-3NMI-06");
        addWebResource("Rgaa30.Test.06.01.03-3NMI-07");
        addWebResource("Rgaa30.Test.06.01.03-4NA-01");
        addWebResource("Rgaa30.Test.06.01.03-4NA-02");
        addWebResource("Rgaa30.Test.06.01.03-4NA-03");
        addWebResource("Rgaa30.Test.06.01.03-4NA-04");
        
        // 06.01.01 testcases
        getWebResourceMap().put("Rgaa30.Test.06.01.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060101/Rgaa30.Test.06.01.01-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060101/Rgaa30.Test.06.01.01-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060101/Rgaa30.Test.06.01.01-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060101/Rgaa30.Test.06.01.01-2Failed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.01-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060101/Rgaa30.Test.06.01.01-2Failed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.01-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060101/Rgaa30.Test.06.01.01-2Failed-06.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.01-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060101/Rgaa30.Test.06.01.01-2Failed-07.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.01-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060101/Rgaa30.Test.06.01.01-2Failed-08.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060101/Rgaa30.Test.06.01.01-3NMI-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.01-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060101/Rgaa30.Test.06.01.01-3NMI-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.01-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060101/Rgaa30.Test.06.01.01-3NMI-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.01-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060101/Rgaa30.Test.06.01.01-3NMI-05.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.01-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060101/Rgaa30.Test.06.01.01-3NMI-06.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.01-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060101/Rgaa30.Test.06.01.01-3NMI-07.html"));

        //06.01.02 testcases
        getWebResourceMap().put("Rgaa30.Test.06.01.02-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060102/Rgaa30.Test.06.01.02-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.02-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060102/Rgaa30.Test.06.01.02-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.02-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060102/Rgaa30.Test.06.01.02-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.02-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060102/Rgaa30.Test.06.01.02-2Failed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.02-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060102/Rgaa30.Test.06.01.02-2Failed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.02-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060102/Rgaa30.Test.06.01.02-2Failed-06.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.02-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060102/Rgaa30.Test.06.01.02-2Failed-07.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.02-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060102/Rgaa30.Test.06.01.02-2Failed-08.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.02-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060102/Rgaa30.Test.06.01.02-2Failed-09.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.02-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060102/Rgaa30.Test.06.01.02-2Failed-10.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.02-2Failed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060102/Rgaa30.Test.06.01.02-2Failed-11.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.02-2Failed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060102/Rgaa30.Test.06.01.02-2Failed-12.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.02-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060102/Rgaa30.Test.06.01.02-3NMI-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.02-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060102/Rgaa30.Test.06.01.02-3NMI-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.02-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060102/Rgaa30.Test.06.01.02-3NMI-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.02-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060102/Rgaa30.Test.06.01.02-3NMI-05.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.02-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060102/Rgaa30.Test.06.01.02-3NMI-06.html"));
        
        //06.01.04 testcases
        getWebResourceMap().put("Rgaa30.Test.06.01.04-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060104/Rgaa30.Test.06.01.04-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.04-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060104/Rgaa30.Test.06.01.04-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.04-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060104/Rgaa30.Test.06.01.04-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.04-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060104/Rgaa30.Test.06.01.04-2Failed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.04-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060104/Rgaa30.Test.06.01.04-2Failed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.04-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060104/Rgaa30.Test.06.01.04-2Failed-06.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.04-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060104/Rgaa30.Test.06.01.04-2Failed-07.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.04-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060104/Rgaa30.Test.06.01.04-2Failed-08.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.04-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060104/Rgaa30.Test.06.01.04-2Failed-09.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.04-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060104/Rgaa30.Test.06.01.04-2Failed-10.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.04-2Failed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060104/Rgaa30.Test.06.01.04-2Failed-11.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.04-2Failed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060104/Rgaa30.Test.06.01.04-2Failed-12.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.04-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060104/Rgaa30.Test.06.01.04-3NMI-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.04-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060104/Rgaa30.Test.06.01.04-3NMI-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.04-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060104/Rgaa30.Test.06.01.04-3NMI-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.04-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060104/Rgaa30.Test.06.01.04-3NMI-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.04-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060104/Rgaa30.Test.06.01.04-3NMI-05.html"));
        
        //06.05.01 testcases -> empty links
        getWebResourceMap().put("Rgaa30.Test.06.05.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060501/Rgaa30.Test.06.05.01-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.05.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060501/Rgaa30.Test.06.05.01-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.05.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060501/Rgaa30.Test.06.05.01-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.05.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060501/Rgaa30.Test.06.05.01-2Failed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.05.01-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060501/Rgaa30.Test.06.05.01-2Failed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.06.05.01-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060501/Rgaa30.Test.06.05.01-4NA-01.html"));
        
                //06.01.05 testcases
        getWebResourceMap().put("Rgaa30.Test.06.01.05-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060105/Rgaa30.Test.06.01.05-2Failed-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.05-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060105/Rgaa30.Test.06.01.05-2Failed-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.05-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060105/Rgaa30.Test.06.01.05-2Failed-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.05-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060105/Rgaa30.Test.06.01.05-2Failed-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.05-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060105/Rgaa30.Test.06.01.05-2Failed-05.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.05-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060105/Rgaa30.Test.06.01.05-3NMI-01.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.05-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060105/Rgaa30.Test.06.01.05-3NMI-02.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.05-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060105/Rgaa30.Test.06.01.05-3NMI-03.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.05-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060105/Rgaa30.Test.06.01.05-3NMI-04.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.05-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060105/Rgaa30.Test.06.01.05-3NMI-05.html"));
        getWebResourceMap().put("Rgaa30.Test.06.01.05-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule060105/Rgaa30.Test.06.01.05-3NMI-06.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //---------------------------2Failed-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.06.01.03-2Failed-01");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "cliquez ici"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                HtmlElementStore.AREA_ELEMENT,
                2,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "cliquez ici"));
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.06.01.03-2Failed-02");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                HtmlElementStore.AREA_ELEMENT,
                2,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.06.01.03-2Failed-03");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "-->;*"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "-->;*"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                HtmlElementStore.AREA_ELEMENT,
                2,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "-->;*"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "-->;*"));
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.06.01.03-2Failed-04");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "-->;*"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                HtmlElementStore.AREA_ELEMENT,
                2,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "-->;*"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.06.01.03-2Failed-05");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "-->;*"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "-->;*"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                2,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "-->;*"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "-->;*"));
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.06.01.03-2Failed-06");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                2,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-07---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.06.01.03-2Failed-07");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "-->;*"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "-->;*"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                2,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "-->;*"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "-->;*"));
        
        //----------------------------------------------------------------------
        //---------------------------2Failed-08---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.06.01.03-2Failed-08");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.UNEXPLICIT_LINK_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "-->;*"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                2,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "-->;*"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-01------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.06.01.03-3NMI-01");
        checkResultIsPreQualified(processResult, 2,  2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "cliquez ici"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                2,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "cliquez ici"));
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-02------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.06.01.03-3NMI-02");
        checkResultIsPreQualified(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                2,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "cliquez ici"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-03------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.06.01.03-3NMI-03");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "-->;*"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "-->;*"));
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-05------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.06.01.03-3NMI-05");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "This is a link"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-06------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.06.01.03-3NMI-06");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "This is a link"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "This is a link and more"));
        
        //----------------------------------------------------------------------
        //---------------------------3NMI-07------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.06.01.03-3NMI-07");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "This is a link"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "This is a link"));
        
        //----------------------------------------------------------------------
        //---------------------------4NA-01-------------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.01.03-4NA-01"));
        
        //----------------------------------------------------------------------
        //---------------------------4NA-02-------------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.01.03-4NA-02"));        
        
        //----------------------------------------------------------------------
        //---------------------------4NA-03-------------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.01.03-4NA-03"));        
        
        //----------------------------------------------------------------------
        //---------------------------4NA-04-------------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.01.03-4NA-04"));    
        
        
        // other tests about links, scope of other is disjunted from the scope
        // of the current test : All these scopes are not applicable
        
        //----------------------------------------------------------------------
        //---------------------------4NA-6.1.1----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.06.01.01-2Failed-01");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.01-2Failed-02");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.01-2Failed-03");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.01-2Failed-04");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.01-2Failed-05");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.01-2Failed-06");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.01-2Failed-07");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.01-2Failed-08");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.01-3NMI-01");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.01-3NMI-02");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.01-3NMI-03");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.01-3NMI-05");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.01-3NMI-06");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.01-3NMI-07");
        checkResultIsNotApplicable(processResult);
        
        //----------------------------------------------------------------------
        //---------------------------4NA-6.1.2----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.06.01.02-2Failed-01");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.02-2Failed-02");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.02-2Failed-03");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.02-2Failed-04");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.02-2Failed-05");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.02-2Failed-06");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.02-2Failed-07");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.02-2Failed-08");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.02-2Failed-09");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.02-2Failed-10");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.02-2Failed-11");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.02-2Failed-12");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.02-3NMI-01");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.02-3NMI-02");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.02-3NMI-03");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.02-3NMI-05");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.02-3NMI-06");
        
        //----------------------------------------------------------------------
        //---------------------------4NA-6.1.4----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.04-2Failed-01");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.04-2Failed-02");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.04-2Failed-03");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.04-2Failed-04");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.04-2Failed-05");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.04-2Failed-06");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.04-2Failed-07");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.04-2Failed-08");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.04-2Failed-09");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.04-2Failed-10");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.04-2Failed-11");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.04-2Failed-12");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.04-3NMI-01");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.04-3NMI-02");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.04-3NMI-03");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.04-3NMI-04");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.01.04-3NMI-05");
        
        //----------------------------------------------------------------------
        //---------------------------4NA-6.1.5----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.01.05-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.01.05-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.01.05-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.01.05-2Failed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.01.05-2Failed-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.01.05-3NMI-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.01.05-3NMI-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.01.05-3NMI-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.01.05-3NMI-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.01.05-3NMI-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.01.05-3NMI-06"));
        
        //----------------------------------------------------------------------
        //---------------------------4NA-6.5.1----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.05.01-2Failed-01");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.05.01-2Failed-02");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.05.01-2Failed-03");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.05.01-2Failed-04");
        checkResultIsNotApplicable(processResult);        
        processResult = processPageTest("Rgaa30.Test.06.05.01-2Failed-05");
        checkResultIsNotApplicable(processResult);        
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.06.05.01-4NA-01"));
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.01.03-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.01.03-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.01.03-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.01.03-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.01.03-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.01.03-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.01.03-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa30.Test.06.01.03-2Failed-08").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.06.01.03-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.06.01.03-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.06.01.03-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.06.01.03-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.06.01.03-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa30.Test.06.01.03-3NMI-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.03-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.03-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.03-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.03-4NA-04").getValue());

        // 06.01.01 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.01-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.01-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.01-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.01-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.01-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.01-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.01-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.01-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.01-3NMI-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.01-3NMI-07").getValue());

        // 06.01.02 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.02-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.02-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.02-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.02-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.02-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.02-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.02-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.02-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.02-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.02-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.02-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.02-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.02-3NMI-06").getValue());

        // 06.01.04 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.04-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.04-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.04-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.04-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.04-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.04-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.04-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.04-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.04-2Failed-09").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.04-2Failed-10").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.04-2Failed-11").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.04-2Failed-12").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.04-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.04-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.04-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.04-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.04-3NMI-05").getValue());
                
        // 06.05.01 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.05.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.05.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.05.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.05.01-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.05.01-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.05.01-4NA-01").getValue());
        
        // 06.01.05 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.05-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.05-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.05-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.05-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.05-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.05-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.05-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.05-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.05-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.05-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.06.01.05-3NMI-06").getValue());
    }
    
}
