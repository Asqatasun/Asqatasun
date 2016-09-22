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

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.asqatasun.entity.audit.ProcessResult;
import static org.asqatasun.entity.audit.TestSolution.NEED_MORE_INFO;
import static org.asqatasun.rules.keystore.AttributeStore.ABSENT_ATTRIBUTE_VALUE;
import static org.asqatasun.rules.keystore.AttributeStore.ARIA_LABEL_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.ROLE_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.asqatasun.rules.keystore.HtmlElementStore.SVG_ELEMENT;
import static org.asqatasun.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_AT_RESTITUTION_OF_ALTERNATIVE_OF_INFORMATIVE_IMAGE_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_AT_RESTITUTION_OF_ALTERNATIVE_MSG;
import org.asqatasun.rules.rgaa32016.test.Rgaa32016RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 1.3.9 of the referential RGAA 3.2016
 *
 * @author
 */
public class Rgaa32016Rule010309Test extends Rgaa32016RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa32016Rule010309Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.asqatasun.rules.rgaa32016.Rgaa32016Rule010309");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa32016.Test.01.03.07-3NMI-01",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa32016.Test.01.03.07-3NMI-02",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa32016.Test.01.03.07-3NMI-03");
        addWebResource("Rgaa32016.Test.01.03.07-3NMI-04",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa32016.Test.01.03.07-3NMI-05",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa32016.Test.01.03.07-3NMI-06");
        addWebResource("Rgaa32016.Test.01.03.07-3NMI-07",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa32016.Test.01.03.07-3NMI-08");
        addWebResource("Rgaa32016.Test.01.03.07-3NMI-09");
        addWebResource("Rgaa32016.Test.01.03.07-3NMI-10");
        addWebResource("Rgaa32016.Test.01.03.07-3NMI-11");
        addWebResource("Rgaa32016.Test.01.03.07-3NMI-12",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa32016.Test.01.03.07-3NMI-13");
        addWebResource("Rgaa32016.Test.01.03.07-3NMI-14");
        addWebResource("Rgaa32016.Test.01.03.07-3NMI-15",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa32016.Test.01.03.07-3NMI-16",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa32016.Test.01.03.07-4NA-01");
        addWebResource("Rgaa32016.Test.01.03.07-4NA-02");
        addWebResource("Rgaa32016.Test.01.03.07-4NA-03");
        addWebResource("Rgaa32016.Test.01.03.07-4NA-04");
        addWebResource("Rgaa32016.Test.01.03.07-4NA-05",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa32016.Test.01.03.07-3NMI-01");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_AT_RESTITUTION_OF_ALTERNATIVE_OF_INFORMATIVE_IMAGE_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(TITLE_ATTR, "Un titre"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un autre titre"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.03.07-3NMI-02");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_AT_RESTITUTION_OF_ALTERNATIVE_OF_INFORMATIVE_IMAGE_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(TITLE_ATTR, "Un titre"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un titre"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.03.07-3NMI-03");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_IMAGE_AND_AT_RESTITUTION_OF_ALTERNATIVE_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un titre"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.03.07-3NMI-04");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_AT_RESTITUTION_OF_ALTERNATIVE_OF_INFORMATIVE_IMAGE_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.03.07-3NMI-05");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_AT_RESTITUTION_OF_ALTERNATIVE_OF_INFORMATIVE_IMAGE_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.03.07-3NMI-06");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_IMAGE_AND_AT_RESTITUTION_OF_ALTERNATIVE_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un titre"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-07---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.03.07-3NMI-07");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_AT_RESTITUTION_OF_ALTERNATIVE_OF_INFORMATIVE_IMAGE_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un titre"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-08---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.03.07-3NMI-08");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_IMAGE_AND_AT_RESTITUTION_OF_ALTERNATIVE_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(ARIA_LABEL_ATTR, "Une description"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-09---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.03.07-3NMI-09");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_IMAGE_AND_AT_RESTITUTION_OF_ALTERNATIVE_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un titre"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-10---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.03.07-3NMI-10");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_IMAGE_AND_AT_RESTITUTION_OF_ALTERNATIVE_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-11---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.03.07-3NMI-11");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_IMAGE_AND_AT_RESTITUTION_OF_ALTERNATIVE_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-12---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.03.07-3NMI-12");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_AT_RESTITUTION_OF_ALTERNATIVE_OF_INFORMATIVE_IMAGE_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(ARIA_LABEL_ATTR, "Une description"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-13---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.03.07-3NMI-13");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_IMAGE_AND_AT_RESTITUTION_OF_ALTERNATIVE_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(TITLE_ATTR, "Un titre"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un titre"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-14---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.03.07-3NMI-14");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_IMAGE_AND_AT_RESTITUTION_OF_ALTERNATIVE_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(TITLE_ATTR, "Un titre"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un autre titre"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-15---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.03.07-3NMI-15");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_AT_RESTITUTION_OF_ALTERNATIVE_OF_INFORMATIVE_IMAGE_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un titre"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-16---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.01.03.07-3NMI-16");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_AT_RESTITUTION_OF_ALTERNATIVE_OF_INFORMATIVE_IMAGE_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un titre"));

        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.01.03.07-4NA-01"));

        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.01.03.07-4NA-02"));

        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.01.03.07-4NA-03"));

        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.01.03.07-4NA-04"));

        //----------------------------------------------------------------------
        //------------------------------4NA-05----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.01.03.07-4NA-05"));
    }

}