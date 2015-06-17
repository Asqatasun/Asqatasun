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
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.service.ProcessRemarkService;

/**
 * Unit test class for the implementation of the rule 9-1-4 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule090104Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule090104Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule090104");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.09.01.04-2Failed-01");
        addWebResource("Rgaa30.Test.09.01.04-2Failed-02");
        addWebResource("Rgaa30.Test.09.01.04-3NMI-01");
        addWebResource("Rgaa30.Test.09.01.04-3NMI-02");
        addWebResource("Rgaa30.Test.09.01.04-4NA-01");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.09.01.04-2Failed-01");
        checkResultIsFailed(processResult, 19, 19);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.H1_ELEMENT, 
                1, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Rgaa30 Test.9.1.4 Failed 01"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H1_ELEMENT, 
                2, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H1_ELEMENT, 
                3, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"!:;,&~$*/-*/-*"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H1_ELEMENT, 
                4, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H2_ELEMENT, 
                5, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H2_ELEMENT, 
                6, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"!:;,&~$*/-*/-*"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H2_ELEMENT, 
                7, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H3_ELEMENT, 
                8, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H3_ELEMENT, 
                9, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"!:;,&~$*/-*/-*"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H3_ELEMENT, 
                10, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H4_ELEMENT, 
                11, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H4_ELEMENT, 
                12, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"!:;,&~$*/-*/-*"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H4_ELEMENT, 
                13, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H5_ELEMENT, 
                14, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H5_ELEMENT, 
                15, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"!:;,&~$*/-*/-*"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H5_ELEMENT, 
                16, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H6_ELEMENT, 
                17, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H6_ELEMENT, 
                18, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"!:;,&~$*/-*/-*"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.H6_ELEMENT, 
                19, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.09.01.04-2Failed-02");
        checkResultIsFailed(processResult, 22, 22);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.H1_ELEMENT, 
                1, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Rgaa30 Test.9.1.4 Failed 02"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.SPAN_ELEMENT, 
                2, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                3, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"!:;,&~$*/-*/-*"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                4, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.SPAN_ELEMENT, 
                5, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                6, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"!:;,&~$*/-*/-*"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                7, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.SPAN_ELEMENT, 
                8, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                9, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"!:;,&~$*/-*/-*"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                10, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.SPAN_ELEMENT, 
                11, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                12, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"!:;,&~$*/-*/-*"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                13, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.SPAN_ELEMENT, 
                14, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                15, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"!:;,&~$*/-*/-*"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                16, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.SPAN_ELEMENT, 
                17, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                18, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"!:;,&~$*/-*/-*"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                19, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.SPAN_ELEMENT, 
                20, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                21, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"!:;,&~$*/-*/-*"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                RemarkMessageStore.NOT_PERTINENT_HEADING_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                22, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,""),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.09.01.04-3NMI-01");
        checkResultIsPreQualified(processResult, 13, 13);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.H1_ELEMENT, 
                1, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Rgaa30 Test.9.1.4 NMI 01"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.H1_ELEMENT, 
                2, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 1-1"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.H1_ELEMENT, 
                3, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 1-2"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.H2_ELEMENT, 
                4, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 2-1"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.H2_ELEMENT, 
                5, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 2-2"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.H3_ELEMENT, 
                6, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 3-1"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.H3_ELEMENT, 
               7, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 3-2"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.H4_ELEMENT, 
                8, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 4-1"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.H4_ELEMENT, 
                9, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 4-2"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.H5_ELEMENT, 
                10, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 5-1"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.H5_ELEMENT, 
                11, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 5-2"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.H6_ELEMENT, 
                12, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 6-1"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.H6_ELEMENT, 
                13, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 6-2"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.09.01.04-3NMI-02");
        checkResultIsPreQualified(processResult, 15, 15);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.H1_ELEMENT, 
                1, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Rgaa30 Test.9.1.4 NMI 02"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.SPAN_ELEMENT, 
                2, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 1-1"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                3, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 1-2"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.SPAN_ELEMENT, 
                4, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 2-1"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                5, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 2-2"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.SPAN_ELEMENT, 
                6, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 3-1"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
               7, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 3-2"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.SPAN_ELEMENT, 
                8, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 4-1"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                9, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 4-2"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.SPAN_ELEMENT, 
                10, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 5-1"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                11, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 5-2"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.SPAN_ELEMENT, 
                12, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 6-1"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                13, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 6-2"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.SPAN_ELEMENT, 
                14, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 7-1"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.CHECK_HEADING_PERTINENCE_MSG, 
                HtmlElementStore.DIV_ELEMENT, 
                15, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Title level 7-2"),
                new ImmutablePair(ProcessRemarkService.DEFAULT_EVIDENCE,"headings"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.09.01.04-4NA-01"));
    }

}
