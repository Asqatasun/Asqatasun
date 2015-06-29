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
import org.tanaguru.entity.audit.*;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.tanaguru.rules.keystore.HtmlElementStore;
import static org.tanaguru.rules.keystore.MarkerStore.COMPLEX_TABLE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.DATA_TABLE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.PRESENTATION_TABLE_MARKER;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 05.05.01 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule050501Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule050501Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa30.Rgaa30Rule050501");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.5.5.1-2Failed-01", createParameter("Rules", DATA_TABLE_MARKER, "class-data-table"));
        addWebResource("Rgaa30.Test.5.5.1-2Failed-02", createParameter("Rules", DATA_TABLE_MARKER, "id-data-table"));
        addWebResource("Rgaa30.Test.5.5.1-2Failed-03", createParameter("Rules", DATA_TABLE_MARKER, "class-data-table"));
        addWebResource("Rgaa30.Test.5.5.1-3NMI-01");
        addWebResource("Rgaa30.Test.5.5.1-3NMI-02", createParameter("Rules", DATA_TABLE_MARKER, "class-data-table"));
        addWebResource("Rgaa30.Test.5.5.1-3NMI-03");
        addWebResource("Rgaa30.Test.5.5.1-3NMI-04");
        addWebResource("Rgaa30.Test.5.5.1-3NMI-05", createParameter("Rules", DATA_TABLE_MARKER, "class-data-table"));
        addWebResource("Rgaa30.Test.5.5.1-4NA-01");
        addWebResource("Rgaa30.Test.5.5.1-4NA-02");
        addWebResource("Rgaa30.Test.5.5.1-4NA-03", 
                createParameter("Rules", PRESENTATION_TABLE_MARKER, "class-presentation-table"),
                createParameter("Rules", COMPLEX_TABLE_MARKER, "class-complex-table"));
        addWebResource("Rgaa30.Test.5.5.1-4NA-04", createParameter("Rules", DATA_TABLE_MARKER, "class-data-table"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.5.5.1-2Failed-01");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_CAPTION_MSG,
                HtmlElementStore.CAPTION_ELEMENT,
                1, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, ""));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.5.5.1-2Failed-02");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_CAPTION_MSG,
                HtmlElementStore.CAPTION_ELEMENT,
                1, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "!:;,--><--"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.5.5.1-2Failed-03");
        checkResultIsFailed(processResult, 4, 4);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_CAPTION_MSG,
                HtmlElementStore.CAPTION_ELEMENT,
                1, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "!:;,--><--"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_CAPTION_PERTINENCE_FOR_DATA_TABLE_MSG,
                HtmlElementStore.CAPTION_ELEMENT,
                2, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "Caption of data table"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_FOR_NOT_PERTINENT_CAPTION_MSG,
                HtmlElementStore.CAPTION_ELEMENT,
                3, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, ""));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_CAPTION_PERTINENCE_MSG,
                HtmlElementStore.CAPTION_ELEMENT,
                4, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "Caption of unidentified table"));
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.5.5.1-3NMI-01");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_CAPTION_PERTINENCE_MSG,
                HtmlElementStore.CAPTION_ELEMENT,
                1, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "Title of the test table"));
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.5.5.1-3NMI-02");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_CAPTION_PERTINENCE_FOR_DATA_TABLE_MSG,
                HtmlElementStore.CAPTION_ELEMENT,
                1, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "Caption of data table"));
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.5.5.1-3NMI-03");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_FOR_NOT_PERTINENT_CAPTION_MSG,
                HtmlElementStore.CAPTION_ELEMENT,
                1, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, ""));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.5.5.1-3NMI-04");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_CAPTION_PERTINENCE_MSG,
                HtmlElementStore.CAPTION_ELEMENT,
                1, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "Caption of unidentified table"));
  
        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.5.5.1-3NMI-05");
        checkResultIsPreQualified(processResult, 3, 3);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_CAPTION_PERTINENCE_FOR_DATA_TABLE_MSG,
                HtmlElementStore.CAPTION_ELEMENT,
                1, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "Caption of data table"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_FOR_NOT_PERTINENT_CAPTION_MSG,
                HtmlElementStore.CAPTION_ELEMENT,
                2, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, ""));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_CAPTION_PERTINENCE_MSG,
                HtmlElementStore.CAPTION_ELEMENT,
                3, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "Caption of unidentified table"));
  
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.5.5.1-4NA-01"));
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.5.5.1-4NA-02"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.5.5.1-4NA-03"));
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.5.5.1-4NA-04"));
    }

}
