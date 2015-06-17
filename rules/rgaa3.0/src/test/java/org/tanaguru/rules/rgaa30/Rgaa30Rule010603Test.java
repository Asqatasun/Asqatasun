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
import static org.tanaguru.rules.keystore.AttributeStore.SRC_ATTR;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 1-6-3 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule010603Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule010603Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule010603");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.01.06.03-3NMI-01");
        addWebResource("Rgaa30.Test.01.06.03-3NMI-02",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "id-informative-embed"));
        addWebResource("Rgaa30.Test.01.06.03-3NMI-03",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "id-informative-embed"),
                    createParameter("Rules", "DECORATIVE_IMAGE_MARKER", "class-decorative-embed"));
        addWebResource("Rgaa30.Test.01.06.03-4NA-01");
        addWebResource("Rgaa30.Test.01.06.03-4NA-02");
        addWebResource("Rgaa30.Test.01.06.03-4NA-03");
        addWebResource("Rgaa30.Test.01.06.03-4NA-04",
                    createParameter("Rules", "DECORATIVE_IMAGE_MARKER", "class-decorative-embed"));

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //-------------------------------3NMI-01--------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.01.06.03-3NMI-01");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_DETAILED_DESC_AVAILABILITY_MSG,
                HtmlElementStore.EMBED_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, ""),
                new ImmutablePair(SRC_ATTR, "mock-embed-object.png"));        
        
        //----------------------------------------------------------------------
        //-------------------------------3NMI-02--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.06.03-3NMI-02");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_DETAILED_DESC_DEFINITION_OF_INFORMATIVE_IMG_MSG,
                HtmlElementStore.EMBED_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, ""),
                new ImmutablePair(SRC_ATTR, "mock-embed-object.png"));        
        
        //----------------------------------------------------------------------
        //-------------------------------3NMI-03--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.06.03-3NMI-03");
        checkResultIsPreQualified(processResult, 2,  2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_DETAILED_DESC_DEFINITION_OF_INFORMATIVE_IMG_MSG,
                HtmlElementStore.EMBED_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, ""),
                new ImmutablePair(SRC_ATTR, "mock-embed-object2.png"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_DETAILED_DESC_AVAILABILITY_MSG,
                HtmlElementStore.EMBED_ELEMENT,
                2,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, ""),
                new ImmutablePair(SRC_ATTR, "mock-embed-object1.png"));        

        //----------------------------------------------------------------------
        //------------------------------4NA-01---------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.06.03-4NA-01"));        

        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.06.03-4NA-02"));        

        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.06.03-4NA-03"));        

        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.06.03-4NA-04"));


    }

}
