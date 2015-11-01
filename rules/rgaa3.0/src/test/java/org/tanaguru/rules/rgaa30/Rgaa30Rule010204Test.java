/*
 * Tanaguru - Automated webpage assessment
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
package org.tanaguru.rules.rgaa30;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.rules.keystore.AttributeStore;
import static org.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import org.tanaguru.rules.keystore.HtmlElementStore;
import static org.tanaguru.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.RemarkMessageStore.DECORATIVE_SVG_OR_CHILD_WITH_TITLE_ATTRIBUTE;
import static org.tanaguru.rules.keystore.RemarkMessageStore.DECORATIVE_SVG_WITH_NOT_EMPTY_TITLE_OR_DESC_TAGS;
import static org.tanaguru.rules.keystore.RemarkMessageStore.SUSPECTED_INFORMATIVE_SVG_WITH_ARIA_ATTRIBUTE_DETECTED_ON_ELEMENT_OR_CHILD;
import static org.tanaguru.rules.keystore.RemarkMessageStore.SUSPECTED_INFORMATIVE_SVG_WITH_DESC_OR_TITLE_CHILD_TAG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.SUSPECTED_INFORMATIVE_SVG_WITH_TITLE_ATTRIBUTE_ON_ELEMENT_OR_CHILD;
import static org.tanaguru.rules.keystore.RemarkMessageStore.SUSPECTED_WELL_FORMATED_DECORATIVE_SVG;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 1-2-4 of the referential
 * Rgaa 3.0.
 *
 * @author
 */
public class Rgaa30Rule010204Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     *
     * @param testName
     */
    public Rgaa30Rule010204Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa30.Rgaa30Rule010204");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.1.2.4-1Passed-01", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-1Passed-02", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-1Passed-03",
                createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-1Passed-04",
                createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-1Passed-05",
                createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-1Passed-06",
                createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-1Passed-07",
                createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        
        addWebResource("Rgaa30.Test.1.2.4-2Failed-01", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-2Failed-02", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-2Failed-03", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-2Failed-04", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-2Failed-05", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-2Failed-06", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-2Failed-07", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-2Failed-08", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-2Failed-09", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-2Failed-10", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-2Failed-11", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-2Failed-12", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-2Failed-13", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-2Failed-14", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-2Failed-15", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));
        addWebResource("Rgaa30.Test.1.2.4-2Failed-16", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-image;class-decorative-image"));

        addWebResource("Rgaa30.Test.1.2.4-3NMI-01");
        addWebResource("Rgaa30.Test.1.2.4-3NMI-02");
        addWebResource("Rgaa30.Test.1.2.4-3NMI-03");
        addWebResource("Rgaa30.Test.1.2.4-3NMI-04");
        addWebResource("Rgaa30.Test.1.2.4-3NMI-05");
        addWebResource("Rgaa30.Test.1.2.4-3NMI-06");
        addWebResource("Rgaa30.Test.1.2.4-3NMI-07");
        addWebResource("Rgaa30.Test.1.2.4-3NMI-08");
        addWebResource("Rgaa30.Test.1.2.4-3NMI-09");
        addWebResource("Rgaa30.Test.1.2.4-3NMI-10");
        addWebResource("Rgaa30.Test.1.2.4-3NMI-11");
        addWebResource("Rgaa30.Test.1.2.4-3NMI-12");
        
        addWebResource("Rgaa30.Test.1.2.4-4NA-01");
        addWebResource("Rgaa30.Test.1.2.4-4NA-02");
        addWebResource("Rgaa30.Test.1.2.4-4NA-03");
    }

    @Override
    protected void setProcess() {
        
       
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.2.4-1Passed-01"), 1);
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.2.4-1Passed-02"), 1);
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.2.4-1Passed-03"), 2);
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.2.4-1Passed-04"), 2);
        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.2.4-1Passed-05"), 2);
        //----------------------------------------------------------------------
        //------------------------------1Passed-06------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.2.4-1Passed-06"), 2);
        //----------------------------------------------------------------------
        //------------------------------1Passed-07------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa30.Test.1.2.4-1Passed-07"), 2);

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.1.2.4-2Failed-01");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "DecorativeSvgWithoutRoleImgAttribute",
                HtmlElementStore.SVG_ELEMENT,
                1);
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.4-2Failed-02");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "DecorativeSvgWithoutRoleImgAttribute",
                HtmlElementStore.SVG_ELEMENT,
                1);
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.4-2Failed-03");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "DecorativeSvgWithoutRoleImgAttribute",
                HtmlElementStore.SVG_ELEMENT,
                1);
         checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "DecorativeSvgWithoutRoleImgAttribute",
                HtmlElementStore.SVG_ELEMENT,
                2);
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.4-2Failed-04");
        checkResultIsFailed(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "DecorativeSvgWithoutRoleImgAttribute",
                HtmlElementStore.SVG_ELEMENT,
                1);
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.4-2Failed-05");
        checkResultIsFailed(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "DecorativeSvgOrChildrenWithAriaAttribute",
                HtmlElementStore.SVG_ELEMENT,
                1);
        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.4-2Failed-06");
        checkResultIsFailed(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "DecorativeSvgOrChildrenWithAriaAttribute",          
                HtmlElementStore.SVG_ELEMENT,
                1);
        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.4-2Failed-07");
        checkResultIsFailed(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "DecorativeSvgOrChildrenWithAriaAttribute",          //DecorativeSvgWithTitleAttribute
                HtmlElementStore.SVG_ELEMENT,
                1);
        //----------------------------------------------------------------------
        //------------------------------2Failed-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.4-2Failed-08");
        checkResultIsFailed(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "DecorativeSvgOrChildrenWithAriaAttribute",          
                HtmlElementStore.SVG_ELEMENT,
                1);
        //----------------------------------------------------------------------
        //------------------------------2Failed-09------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.4-2Failed-09");
        checkResultIsFailed(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "DecorativeSvgOrChildrenWithAriaAttribute",          
                HtmlElementStore.SVG_ELEMENT,
                1);
        //----------------------------------------------------------------------
        //------------------------------2Failed-10------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.4-2Failed-10");
        checkResultIsFailed(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "DecorativeSvgOrChildrenWithAriaAttribute",         
                HtmlElementStore.SVG_ELEMENT,
                1);
               
        //----------------------------------------------------------------------
        //------------------------------2Failed-11------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.4-2Failed-11");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "DecorativeSvgOrChildrenWithAriaAttribute",          
                HtmlElementStore.SVG_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "DecorativeSvgOrChildrenWithAriaAttribute",          
                HtmlElementStore.SVG_ELEMENT,
                2);
        //----------------------------------------------------------------------
        //------------------------------2Failed-12------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.4-2Failed-12");
        checkResultIsFailed(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                DECORATIVE_SVG_WITH_NOT_EMPTY_TITLE_OR_DESC_TAGS,          
                HtmlElementStore.SVG_ELEMENT,
                1);
        //----------------------------------------------------------------------
        //------------------------------2Failed-13------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.4-2Failed-13");
        checkResultIsFailed(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                DECORATIVE_SVG_WITH_NOT_EMPTY_TITLE_OR_DESC_TAGS,          
                HtmlElementStore.SVG_ELEMENT,
                1);
        //----------------------------------------------------------------------
        //------------------------------2Failed-14------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.4-2Failed-14");
        checkResultIsFailed(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                DECORATIVE_SVG_WITH_NOT_EMPTY_TITLE_OR_DESC_TAGS,          
                HtmlElementStore.SVG_ELEMENT,
                1);
        //----------------------------------------------------------------------
        //------------------------------2Failed-15------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.4-2Failed-15");
        checkResultIsFailed(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                DECORATIVE_SVG_OR_CHILD_WITH_TITLE_ATTRIBUTE,          
                HtmlElementStore.SVG_ELEMENT,
                1);
        //----------------------------------------------------------------------
        //------------------------------2Failed-16------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.2.4-2Failed-16");
        checkResultIsFailed(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                DECORATIVE_SVG_OR_CHILD_WITH_TITLE_ATTRIBUTE,          
                HtmlElementStore.SVG_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
         processResult = processPageTest("Rgaa30.Test.1.2.4-3NMI-01");
       // checkResultIsNotTested(processResult); // temporary result to make the result buildable before implementation
          checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                SUSPECTED_INFORMATIVE_SVG_WITH_TITLE_ATTRIBUTE_ON_ELEMENT_OR_CHILD,
                HtmlElementStore.SVG_ELEMENT,
                1
        );
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
         processResult = processPageTest("Rgaa30.Test.1.2.4-3NMI-02");
       // checkResultIsNotTested(processResult); // temporary result to make the result buildable before implementation
          checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                SUSPECTED_WELL_FORMATED_DECORATIVE_SVG,
                HtmlElementStore.SVG_ELEMENT,
                1
        );
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
         processResult = processPageTest("Rgaa30.Test.1.2.4-3NMI-03");
       // checkResultIsNotTested(processResult); // temporary result to make the result buildable before implementation
          checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                SUSPECTED_INFORMATIVE_SVG_WITH_ARIA_ATTRIBUTE_DETECTED_ON_ELEMENT_OR_CHILD,
                HtmlElementStore.SVG_ELEMENT,
                1
        );
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
         processResult = processPageTest("Rgaa30.Test.1.2.4-3NMI-04");
       // checkResultIsNotTested(processResult); // temporary result to make the result buildable before implementation
          checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                SUSPECTED_INFORMATIVE_SVG_WITH_ARIA_ATTRIBUTE_DETECTED_ON_ELEMENT_OR_CHILD,
                HtmlElementStore.SVG_ELEMENT,
                1
        );
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
         processResult = processPageTest("Rgaa30.Test.1.2.4-3NMI-05");
       // checkResultIsNotTested(processResult); // temporary result to make the result buildable before implementation
          checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                SUSPECTED_INFORMATIVE_SVG_WITH_ARIA_ATTRIBUTE_DETECTED_ON_ELEMENT_OR_CHILD,
                HtmlElementStore.SVG_ELEMENT,
                1
        );
        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
         processResult = processPageTest("Rgaa30.Test.1.2.4-3NMI-06");
       // checkResultIsNotTested(processResult); // temporary result to make the result buildable before implementation
          checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                SUSPECTED_INFORMATIVE_SVG_WITH_ARIA_ATTRIBUTE_DETECTED_ON_ELEMENT_OR_CHILD,
                HtmlElementStore.SVG_ELEMENT,
                1
        );
        //----------------------------------------------------------------------
        //------------------------------3NMI-07---------------------------------
        //----------------------------------------------------------------------
         processResult = processPageTest("Rgaa30.Test.1.2.4-3NMI-07");
       // checkResultIsNotTested(processResult); // temporary result to make the result buildable before implementation
          checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                SUSPECTED_INFORMATIVE_SVG_WITH_ARIA_ATTRIBUTE_DETECTED_ON_ELEMENT_OR_CHILD,
                HtmlElementStore.SVG_ELEMENT,
                1
        );
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-07---------------------------------
        //----------------------------------------------------------------------
         processResult = processPageTest("Rgaa30.Test.1.2.4-3NMI-08");
       // checkResultIsNotTested(processResult); // temporary result to make the result buildable before implementation
          checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                SUSPECTED_INFORMATIVE_SVG_WITH_ARIA_ATTRIBUTE_DETECTED_ON_ELEMENT_OR_CHILD,
                HtmlElementStore.SVG_ELEMENT,
                1
        );
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-09---------------------------------
        //----------------------------------------------------------------------
         processResult = processPageTest("Rgaa30.Test.1.2.4-3NMI-09");
       // checkResultIsNotTested(processResult); // temporary result to make the result buildable before implementation
          checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                SUSPECTED_INFORMATIVE_SVG_WITH_DESC_OR_TITLE_CHILD_TAG,
                HtmlElementStore.SVG_ELEMENT,
                1
        );
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-10---------------------------------
        //----------------------------------------------------------------------
         processResult = processPageTest("Rgaa30.Test.1.2.4-3NMI-10");
       // checkResultIsNotTested(processResult); // temporary result to make the result buildable before implementation
          checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                SUSPECTED_INFORMATIVE_SVG_WITH_DESC_OR_TITLE_CHILD_TAG,
                HtmlElementStore.SVG_ELEMENT,
                1
        );
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-11---------------------------------
        //----------------------------------------------------------------------
         processResult = processPageTest("Rgaa30.Test.1.2.4-3NMI-11");
       // checkResultIsNotTested(processResult); // temporary result to make the result buildable before implementation
          checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                SUSPECTED_INFORMATIVE_SVG_WITH_DESC_OR_TITLE_CHILD_TAG,
                HtmlElementStore.SVG_ELEMENT,
                1
        );
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-12---------------------------------
        //----------------------------------------------------------------------
         processResult = processPageTest("Rgaa30.Test.1.2.4-3NMI-12");
       // checkResultIsNotTested(processResult); // temporary result to make the result buildable before implementation
          checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                SUSPECTED_INFORMATIVE_SVG_WITH_TITLE_ATTRIBUTE_ON_ELEMENT_OR_CHILD,
                HtmlElementStore.SVG_ELEMENT,
                1
        );
        
//        //----------------------------------------------------------------------
//        //------------------------------4NA-01----------------------------------
//        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.2.4-4NA-01"));
//        
//        //----------------------------------------------------------------------
//        //------------------------------4NA-02----------------------------------
//        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.2.4-4NA-02"));
//      
//        //----------------------------------------------------------------------
//        //------------------------------4NA-03----------------------------------
//        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.2.4-4NA-03"),2);
//      
    }
     

}
