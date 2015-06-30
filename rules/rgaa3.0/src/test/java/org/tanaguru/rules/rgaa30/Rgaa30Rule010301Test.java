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
import static org.tanaguru.entity.audit.TestSolution.FAILED;
import static org.tanaguru.entity.audit.TestSolution.NEED_MORE_INFO;
import static org.tanaguru.rules.keystore.AttributeStore.ABSENT_ATTRIBUTE_VALUE;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import static org.tanaguru.rules.keystore.AttributeStore.ALT_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.SRC_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.tanaguru.rules.keystore.HtmlElementStore.IMG_ELEMENT;
import static org.tanaguru.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_ALT_PERTINENCE_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.NOT_PERTINENT_ALT_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.TITLE_NOT_IDENTICAL_TO_ALT_MSG;

/**
 * Unit test class for the implementation of the rule 1-3-1 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule010301Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule010301Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule010301");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.01.03.01-2Failed-01");
        addWebResource("Rgaa30.Test.01.03.01-2Failed-02");
        addWebResource("Rgaa30.Test.01.03.01-2Failed-03");
        addWebResource("Rgaa30.Test.01.03.01-2Failed-04",
                    createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "class-informative-image"));
        addWebResource("Rgaa30.Test.01.03.01-2Failed-05",
                    createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "class-informative-image"));
        addWebResource("Rgaa30.Test.01.03.01-2Failed-06",
                    createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.01.03.01-2Failed-07");
        addWebResource("Rgaa30.Test.01.03.01-2Failed-08",
                    createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.01.03.01-2Failed-09",
                    createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "class-informative-image"),
                    createParameter("Rules", "DECORATIVE_IMAGE_MARKER", "class-decorative-image"));
        addWebResource("Rgaa30.Test.01.03.01-2Failed-10",
                    createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.01.03.01-2Failed-11",
                    createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.01.03.01-3NMI-01");
        addWebResource("Rgaa30.Test.01.03.01-3NMI-02");
        addWebResource("Rgaa30.Test.01.03.01-3NMI-03");
        addWebResource("Rgaa30.Test.01.03.01-3NMI-04");
        addWebResource("Rgaa30.Test.01.03.01-3NMI-05",
                    createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "class-informative-image"));
        addWebResource("Rgaa30.Test.01.03.01-3NMI-06");
        addWebResource("Rgaa30.Test.01.03.01-3NMI-07",
                    createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "class-informative-image"));
        addWebResource("Rgaa30.Test.01.03.01-3NMI-08",
                    createParameter("Rules", INFORMATIVE_IMAGE_MARKER, "id-informative-image"));
        addWebResource("Rgaa30.Test.01.03.01-4NA-01");
        addWebResource("Rgaa30.Test.01.03.01-4NA-02");
        addWebResource("Rgaa30.Test.01.03.01-4NA-03");
        addWebResource("Rgaa30.Test.01.03.01-4NA-04");
        addWebResource("Rgaa30.Test.01.03.01-4NA-05",
                    createParameter("Rules", DECORATIVE_IMAGE_MARKER, "class-decorative-image"));

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.01.03.01-2Failed-01");
        checkResultIsFailed(processResult,1,1);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                NOT_PERTINENT_ALT_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "mock-image"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-2Failed-02");
        checkResultIsFailed(processResult,1,1);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                NOT_PERTINENT_ALT_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "image.gif"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-2Failed-03");
        checkResultIsFailed(processResult,1,1);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                NOT_PERTINENT_ALT_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "--><--"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image.jpg"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-2Failed-04");
        checkResultIsFailed(processResult,1,1);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                NOT_PERTINENT_ALT_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "mock-image"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "images/mock-image"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-2Failed-05");
        checkResultIsFailed(processResult,1,1);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                NOT_PERTINENT_ALT_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "image.bmp"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-2Failed-06");
        checkResultIsFailed(processResult,1,1);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                NOT_PERTINENT_ALT_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "#!/;'(|"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image.jpg"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-2Failed-07");
        checkResultIsFailed(processResult,1,1);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                NOT_PERTINENT_ALT_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, ""),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image.jpg"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-2Failed-08");
        checkResultIsFailed(processResult,1,1);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                NOT_PERTINENT_ALT_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, ""),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image.jpg"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-09------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-2Failed-09");
        checkResultIsFailed(processResult,4,4);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                NOT_PERTINENT_ALT_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, ""),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image1.jpg"));
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG,
                IMG_ELEMENT,
                2,
                new ImmutablePair(ALT_ATTR, "Informative image alternative"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image2.jpg"));
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG,
                IMG_ELEMENT,
                3,
                new ImmutablePair(ALT_ATTR, "mock-image3"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image3"));
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_IMAGE_AND_ALT_PERTINENCE_MSG,
                IMG_ELEMENT,
                4,
                new ImmutablePair(ALT_ATTR, "not identified image alternative"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image4.jpg"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-10------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-2Failed-10");
        checkResultIsFailed(processResult,1,2);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "Alternative"),
                new ImmutablePair(TITLE_ATTR, "Title"),
                new ImmutablePair(SRC_ATTR, "mock-image.jpg"));
        checkRemarkIsPresent(
                processResult,
                FAILED,
                TITLE_NOT_IDENTICAL_TO_ALT_MSG,
                IMG_ELEMENT,
                2,
                new ImmutablePair(ALT_ATTR, "Alternative"),
                new ImmutablePair(TITLE_ATTR, "Title"),
                new ImmutablePair(SRC_ATTR, "mock-image.jpg"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-11------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-2Failed-11");
        checkResultIsFailed(processResult,1,1);
        checkRemarkIsPresent(
                processResult,
                FAILED,
                NOT_PERTINENT_ALT_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "+-*/"),
                new ImmutablePair(TITLE_ATTR, "+-*/"),
                new ImmutablePair(SRC_ATTR, "mock-image.jpg"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-3NMI-01");
        checkResultIsPreQualified(processResult,1,1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "mock-image"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "images/mock-image"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-3NMI-02");
        checkResultIsPreQualified(processResult,1,1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "image.bmp"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-3NMI-03");
        checkResultIsPreQualified(processResult,1,1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "#!/;'(|"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image.jpg"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-3NMI-04");
        checkResultIsPreQualified(processResult,1,1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, ""),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image.jpg"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-3NMI-05");
        checkResultIsPreQualified(processResult,1,1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "Informative image alternative"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image.jpg"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-3NMI-06");
        checkResultIsPreQualified(processResult,1,1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_IMAGE_AND_ALT_PERTINENCE_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "Not identified image alternative"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image.jpg"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-07---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-3NMI-07");
        checkResultIsPreQualified(processResult,3,3);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "Informative image alternative"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image2.jpg"));
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG,
                IMG_ELEMENT,
                2,
                new ImmutablePair(ALT_ATTR, "mock-image3"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image3"));
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_NATURE_OF_IMAGE_AND_ALT_PERTINENCE_MSG,
                IMG_ELEMENT,
                3,
                new ImmutablePair(ALT_ATTR, "not identified image alternative"),
                new ImmutablePair(TITLE_ATTR, ABSENT_ATTRIBUTE_VALUE),
                new ImmutablePair(SRC_ATTR, "mock-image4.jpg"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-08---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.01-3NMI-08");
        checkResultIsPreQualified(processResult,1,1);
        checkRemarkIsPresent(
                processResult,
                NEED_MORE_INFO,
                CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG,
                IMG_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "Alternative"),
                new ImmutablePair(TITLE_ATTR,  "Alternative"),
                new ImmutablePair(SRC_ATTR, "mock-image.jpg"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.03.01-4NA-01"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.03.01-4NA-02"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.03.01-4NA-03"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.03.01-4NA-04"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-05----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.03.01-4NA-05"));
    }

}