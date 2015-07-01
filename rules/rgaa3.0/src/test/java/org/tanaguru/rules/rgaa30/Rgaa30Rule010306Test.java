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
import static org.tanaguru.entity.audit.TestSolution.FAILED;
import static org.tanaguru.entity.audit.TestSolution.NEED_MORE_INFO;
import static org.tanaguru.rules.keystore.AttributeStore.ABSENT_ATTRIBUTE_VALUE;
import static org.tanaguru.rules.keystore.AttributeStore.ARIA_LABEL_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.ROLE_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.tanaguru.rules.keystore.HtmlElementStore.SVG_ELEMENT;
import static org.tanaguru.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_ALT_PERTINENCE_OF_INFORMATIVE_SVG_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_SVG_AND_ALT_PERTINENCE_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_SVG_WITH_NOT_PERTINENT_ALT_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.INFORMATIVE_SVG_WITH_NOT_PERTINENT_ALT_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.SVG_WITHOUT_ROLE_IMAGE_MSG;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 1-3-6 of the referential Rgaa 3.0.
 *
 * @author
 */
public class Rgaa30Rule010306Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule010306Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa30.Rgaa30Rule010306");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.1.3.6-2Failed-01",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-2Failed-02",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "class-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-2Failed-03",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image;class-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-2Failed-04",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image;class-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-2Failed-05");
        addWebResource("Rgaa30.Test.1.3.6-2Failed-06");
        addWebResource("Rgaa30.Test.1.3.6-2Failed-07");
        addWebResource("Rgaa30.Test.1.3.6-2Failed-08");
        addWebResource("Rgaa30.Test.1.3.6-2Failed-09");
        addWebResource("Rgaa30.Test.1.3.6-2Failed-10");
        addWebResource("Rgaa30.Test.1.3.6-2Failed-11");
        addWebResource("Rgaa30.Test.1.3.6-2Failed-12");
        addWebResource("Rgaa30.Test.1.3.6-2Failed-13");
        addWebResource("Rgaa30.Test.1.3.6-2Failed-14");
        addWebResource("Rgaa30.Test.1.3.6-2Failed-15");
        addWebResource("Rgaa30.Test.1.3.6-2Failed-16");
        addWebResource("Rgaa30.Test.1.3.6-3NMI-01",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-3NMI-02",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-3NMI-03",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-3NMI-04",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-3NMI-05",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-3NMI-06",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-3NMI-07",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-3NMI-08",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-3NMI-08",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-3NMI-09",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-3NMI-10",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-3NMI-11",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-3NMI-12",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-3NMI-13",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-3NMI-14",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-3NMI-15",
                createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.1.3.6-3NMI-16");
        addWebResource("Rgaa30.Test.1.3.6-3NMI-17");
        addWebResource("Rgaa30.Test.1.3.6-3NMI-18");
        addWebResource("Rgaa30.Test.1.3.6-3NMI-19");
        addWebResource("Rgaa30.Test.1.3.6-4NA-01");
        addWebResource("Rgaa30.Test.1.3.6-4NA-02");
        addWebResource("Rgaa30.Test.1.3.6-4NA-03",
                createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-informative-embed"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.1.3.6-2Failed-01");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                1);
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-2Failed-02");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                1);
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-2Failed-03");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                2);

        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-2Failed-04");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_SVG_MSG,
                SVG_ELEMENT,
                2,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));

        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-2Failed-05");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                2);
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-2Failed-06");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_SVG_AND_ALT_PERTINENCE_MSG,
                SVG_ELEMENT,
                2,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-2Failed-07");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_SVG_AND_ALT_PERTINENCE_MSG,
                SVG_ELEMENT,
                2,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un titre"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-2Failed-08");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_SVG_AND_ALT_PERTINENCE_MSG,
                SVG_ELEMENT,
                2,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-09------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-2Failed-09");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_SVG_AND_ALT_PERTINENCE_MSG,
                SVG_ELEMENT,
                2,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un titre"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-10------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-2Failed-10");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_SVG_WITH_NOT_PERTINENT_ALT_MSG,
                SVG_ELEMENT,
                2,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, ""),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-11------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-2Failed-11");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_SVG_AND_ALT_PERTINENCE_MSG,
                SVG_ELEMENT,
                2,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Une description"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-12------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-2Failed-12");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_SVG_AND_ALT_PERTINENCE_MSG,
                SVG_ELEMENT,
                2,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un titre"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-13------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-2Failed-13");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_SVG_AND_ALT_PERTINENCE_MSG,
                SVG_ELEMENT,
                2,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-14------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-2Failed-14");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_SVG_AND_ALT_PERTINENCE_MSG,
                SVG_ELEMENT,
                2,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(TITLE_ATTR, "Un titre"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-15------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-2Failed-15");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_SVG_AND_ALT_PERTINENCE_MSG,
                SVG_ELEMENT,
                2,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un titre"),
                new ImmutablePair(TITLE_ATTR, "Un titre"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-16------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-2Failed-16");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                SVG_WITHOUT_ROLE_IMAGE_MSG,
                SVG_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_SVG_WITH_NOT_PERTINENT_ALT_MSG,
                SVG_ELEMENT,
                2,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un autre titre"),
                new ImmutablePair(TITLE_ATTR, "Un titre"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-01");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_SVG_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));

        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-02");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_SVG_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un titre"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));

        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-03");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_SVG_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));

        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-04");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_SVG_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));

        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-05");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_SVG_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un titre"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));

        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-06");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                INFORMATIVE_SVG_WITH_NOT_PERTINENT_ALT_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, ""),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));

        //----------------------------------------------------------------------
        //------------------------------3NMI-07---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-07");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_SVG_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Une description"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));

        //----------------------------------------------------------------------
        //------------------------------3NMI-08---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-08");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_SVG_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un titre"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));

        //----------------------------------------------------------------------
        //------------------------------3NMI-09---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-09");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_SVG_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));

        //----------------------------------------------------------------------
        //------------------------------3NMI-10---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-10");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_SVG_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE));

        //----------------------------------------------------------------------
        //------------------------------3NMI-11---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-11");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_SVG_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(TITLE_ATTR, "Un titre"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-12---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-12");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_SVG_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un titre"),
                new ImmutablePair(TITLE_ATTR, "Un titre"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-13---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-13");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                INFORMATIVE_SVG_WITH_NOT_PERTINENT_ALT_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un autre titre"),
                new ImmutablePair(TITLE_ATTR, "Un titre"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-14---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-14");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_SVG_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(TITLE_ATTR, "Un titre"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-15---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-15");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                INFORMATIVE_SVG_WITH_NOT_PERTINENT_ALT_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, ""),
                new ImmutablePair(TITLE_ATTR, "Un titre"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-16---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-16");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_SVG_AND_ALT_PERTINENCE_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un titre"),
                new ImmutablePair(TITLE_ATTR, "Un titre"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-17---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-17");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_SVG_WITH_NOT_PERTINENT_ALT_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, "Un autre titre"),
                new ImmutablePair(TITLE_ATTR, "Un titre"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-18---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-18");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_SVG_WITH_NOT_PERTINENT_ALT_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(TITLE_ATTR, "Un titre"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-19---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.6-3NMI-19");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_SVG_WITH_NOT_PERTINENT_ALT_MSG,
                SVG_ELEMENT,
                1,
                new ImmutablePair(ROLE_ATTR, "img"),
                new ImmutablePair(ARIA_LABEL_ATTR, ""),
                new ImmutablePair(TITLE_ATTR, "Un titre"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.3.6-4NA-01"));

        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.3.6-4NA-02"));

        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.3.6-4NA-03"));
    }

}