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
import static org.tanaguru.rules.keystore.AttributeStore.DATA_ATTR;
import static org.tanaguru.rules.keystore.HtmlElementStore.OBJECT_ELEMENT;
import static org.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.tanaguru.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_ELEMENT_WITH_EMPTY_ALT_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 1-2-3 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule010203Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule010203Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule010203");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.1.2.3-1Passed-01",
                    createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-object"));
        addWebResource("Rgaa30.Test.1.2.3-1Passed-02",
                    createParameter("Rules", DECORATIVE_IMAGE_MARKER, "class-decorative-object"));
        addWebResource("Rgaa30.Test.1.2.3-1Passed-03",
                    createParameter("Rules", DECORATIVE_IMAGE_MARKER, "role-decorative-object"));
        addWebResource("Rgaa30.Test.1.2.3-1Passed-04",
                    createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-object;class-decorative-object;role-decorative-object"));
        addWebResource("Rgaa30.Test.1.2.3-1Passed-05",
                    createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-object"),
                    createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "class-informative-object"));
        addWebResource("Rgaa30.Test.1.2.3-2Failed-01",
                    createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-object"));
        addWebResource("Rgaa30.Test.1.2.3-2Failed-02",
                    createParameter("Rules", DECORATIVE_IMAGE_MARKER, "class-decorative-object"));
        addWebResource("Rgaa30.Test.1.2.3-2Failed-03",
                    createParameter("Rules", DECORATIVE_IMAGE_MARKER, "role-decorative-object"));
        addWebResource("Rgaa30.Test.1.2.3-2Failed-04",
                    createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-object;class-decorative-object;role-decorative-object"));
        addWebResource("Rgaa30.Test.1.2.3-2Failed-05",
                    createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-object"),
                    createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "class-informative-object"));
        addWebResource("Rgaa30.Test.1.2.3-3NMI-01");
        addWebResource("Rgaa30.Test.1.2.3-3NMI-02");
        addWebResource("Rgaa30.Test.1.2.3-3NMI-03",
                    createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "class-informative-object"));
        addWebResource("Rgaa30.Test.1.2.3-3NMI-04",
                    createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "class-informative-object"));
        addWebResource("Rgaa30.Test.1.2.3-3NMI-05",
                    createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-object"));
        addWebResource("Rgaa30.Test.1.2.3-3NMI-06",
                    createParameter("Rules", DECORATIVE_IMAGE_MARKER, "class-decorative-object"));
        addWebResource("Rgaa30.Test.1.2.3-4NA-01");
        addWebResource("Rgaa30.Test.1.2.3-4NA-02");
        addWebResource("Rgaa30.Test.1.2.3-4NA-03");
        addWebResource("Rgaa30.Test.1.2.3-4NA-04");
        addWebResource("Rgaa30.Test.1.2.3-4NA-05");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.2.3-1Passed-01"),1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.2.3-1Passed-02"),1);
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.2.3-1Passed-03"),1);
        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.2.3-1Passed-04"),3);

        
        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.2.3-1Passed-05"),1);

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.1.2.3-2Failed-01");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, 
                OBJECT_ELEMENT, 
                1, 
                new ImmutablePair(TEXT_ELEMENT2, "Not empty alternative"),
                new ImmutablePair(DATA_ATTR, "mock_image.gif"));


        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.3-2Failed-02");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, 
                OBJECT_ELEMENT, 
                1, 
                new ImmutablePair(TEXT_ELEMENT2, "Not empty alternative"),
                new ImmutablePair(DATA_ATTR, "mock_image.gif"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.3-2Failed-03");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, 
                OBJECT_ELEMENT, 
                1, 
                new ImmutablePair(TEXT_ELEMENT2, "Not empty alternative"),
                new ImmutablePair(DATA_ATTR, "mock_image.gif"));
        

        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.3-2Failed-04");
        checkResultIsFailed(processResult, 3, 3);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, 
                OBJECT_ELEMENT, 
                1, 
                new ImmutablePair(TEXT_ELEMENT2, "Not empty alternative"),
                new ImmutablePair(DATA_ATTR, "mock_image1.gif"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, 
                OBJECT_ELEMENT, 
                2, 
                new ImmutablePair(TEXT_ELEMENT2, "Not empty alternative"),
                new ImmutablePair(DATA_ATTR, "mock_image2.gif"));
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, 
                OBJECT_ELEMENT, 
                3, 
                new ImmutablePair(TEXT_ELEMENT2, "Not empty alternative"),
                new ImmutablePair(DATA_ATTR, "mock_image3.gif"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.3-2Failed-05");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.FAILED, 
                DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, 
                OBJECT_ELEMENT, 
                1, 
                new ImmutablePair(TEXT_ELEMENT2, "Not empty alternative"),
                new ImmutablePair(DATA_ATTR, "mock_image1.gif"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.3-3NMI-01");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, 
                OBJECT_ELEMENT, 
                1,
                new ImmutablePair(TEXT_ELEMENT2, "Some text can be added here"),
                new ImmutablePair(DATA_ATTR, "mock_image.gif"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.3-3NMI-02");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                CHECK_ELEMENT_WITH_EMPTY_ALT_MSG, 
                OBJECT_ELEMENT, 
                1,
                new ImmutablePair(TEXT_ELEMENT2, ""),
                new ImmutablePair(DATA_ATTR, "mock_image.gif"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.3-3NMI-03");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, 
                OBJECT_ELEMENT, 
                1,
                new ImmutablePair(TEXT_ELEMENT2, "Some text can be added here"),
                new ImmutablePair(DATA_ATTR, "mock_image1.gif"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.3-3NMI-04");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                CHECK_ELEMENT_WITH_EMPTY_ALT_MSG, 
                OBJECT_ELEMENT, 
                1,
                new ImmutablePair(TEXT_ELEMENT2, ""),
                new ImmutablePair(DATA_ATTR, "mock_image1.gif"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.3-3NMI-05");
        checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, 
                OBJECT_ELEMENT, 
                1,
                new ImmutablePair(TEXT_ELEMENT2, "Some text can be added here"),
                new ImmutablePair(DATA_ATTR, "mock_image1.gif"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.3-3NMI-06");
        checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                CHECK_ELEMENT_WITH_EMPTY_ALT_MSG, 
                OBJECT_ELEMENT, 
                1,
                new ImmutablePair(TEXT_ELEMENT2, ""),
                new ImmutablePair(DATA_ATTR, "mock_image1.gif"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.2.3-4NA-01"));
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.2.3-4NA-02"));
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.2.3-4NA-03"));
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.2.3-4NA-04"));
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-05----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.2.3-4NA-05"));
    }

}