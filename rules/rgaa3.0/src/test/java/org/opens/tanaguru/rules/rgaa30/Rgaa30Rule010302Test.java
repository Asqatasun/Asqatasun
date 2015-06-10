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
package org.opens.tanaguru.rules.rgaa30;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.opens.tanaguru.entity.audit.*;
import org.opens.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import static org.opens.tanaguru.rules.keystore.AttributeStore.ALT_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.HREF_ATTR;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 1-3-2 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule010302Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule010302Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.rgaa30.Rgaa30Rule010302");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.01.03.02-2Failed-01",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-area"));
        addWebResource("Rgaa30.Test.01.03.02-2Failed-02",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-area"));
        addWebResource("Rgaa30.Test.01.03.02-2Failed-03",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-area"));
        addWebResource("Rgaa30.Test.01.03.02-2Failed-04",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-area"));
        addWebResource("Rgaa30.Test.01.03.02-2Failed-05",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-area"),
                    createParameter("Rules", "DECORATIVE_IMAGE_MARKER", "class-decorative-area"));
        addWebResource("Rgaa30.Test.01.03.02-3NMI-01");
        addWebResource("Rgaa30.Test.01.03.02-3NMI-02");
        addWebResource("Rgaa30.Test.01.03.02-3NMI-03");
        addWebResource("Rgaa30.Test.01.03.02-3NMI-04",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-area"));
        addWebResource("Rgaa30.Test.01.03.02-3NMI-05");
        addWebResource("Rgaa30.Test.01.03.02-3NMI-06",
                    createParameter("Rules", "INFORMATIVE_IMAGE_MARKER", "class-informative-area"),
                    createParameter("Rules", "DECORATIVE_IMAGE_MARKER", "class-decorative-area"));
        addWebResource("Rgaa30.Test.01.03.02-4NA-01");
        addWebResource("Rgaa30.Test.01.03.02-4NA-02");
        addWebResource("Rgaa30.Test.01.03.02-4NA-03");
        addWebResource("Rgaa30.Test.01.03.02-4NA-04");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.01.03.02-2Failed-01");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_ALT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "mock-area.html"),
                new ImmutablePair(HREF_ATTR, "mock-area.html"));        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-2Failed-02");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_ALT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "mock-image.jpeg"),
                new ImmutablePair(HREF_ATTR, "mock-area.html"));        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-2Failed-03");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_ALT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "--><--"),
                new ImmutablePair(HREF_ATTR, "mock-area.html"));        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-2Failed-04");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_ALT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, ""),
                new ImmutablePair(HREF_ATTR, "mock-area.html"));        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-2Failed-05");
        checkResultIsFailed(processResult, 4, 4);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_ALT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, ""),
                new ImmutablePair(HREF_ATTR, "mock-area1.html"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG,
                HtmlElementStore.AREA_ELEMENT,
                2,
                new ImmutablePair(ALT_ATTR, "Informative area alternative"),
                new ImmutablePair(HREF_ATTR, "mock-area2.html"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                3,
                new ImmutablePair(ALT_ATTR, "mock-area3.html"),
                new ImmutablePair(HREF_ATTR, "mock-area3.html"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_ALT_PERTINENCE_MSG,
                HtmlElementStore.AREA_ELEMENT,
                4,
                new ImmutablePair(ALT_ATTR, "not identified area alternative"),
                new ImmutablePair(HREF_ATTR, "mock-area4.html"));        

        //----------------------------------------------------------------------
        //------------------------------3NMI-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-3NMI-01");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "mock-area.html"),
                new ImmutablePair(HREF_ATTR, "mock-area.html"));        

        //----------------------------------------------------------------------
        //------------------------------3NMI-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-3NMI-02");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "mock-image.jpeg"),
                new ImmutablePair(HREF_ATTR, "mock-area.html"));        

        //----------------------------------------------------------------------
        //------------------------------3NMI-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-3NMI-03");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "#!/;'(|"),
                new ImmutablePair(HREF_ATTR, "mock-area.html"));        

        //----------------------------------------------------------------------
        //------------------------------3NMI-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-3NMI-04");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "Informative area alternative"),
                new ImmutablePair(HREF_ATTR, "mock-area.html"));        

        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-3NMI-05");
        checkResultIsPreQualified(processResult, 1,  1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_ALT_PERTINENCE_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "Not identified area alternative"),
                new ImmutablePair(HREF_ATTR, "mock-area.html"));        

        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.01.03.02-3NMI-06");
        checkResultIsPreQualified(processResult, 3,  3);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG,
                HtmlElementStore.AREA_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "Informative area alternative"),
                new ImmutablePair(HREF_ATTR, "mock-area2.html"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITH_NOT_PERTINENT_ALT_MSG,
                HtmlElementStore.AREA_ELEMENT,
                2,
                new ImmutablePair(ALT_ATTR, "mock-area3.html"),
                new ImmutablePair(HREF_ATTR, "mock-area3.html"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_NATURE_OF_IMAGE_AND_ALT_PERTINENCE_MSG,
                HtmlElementStore.AREA_ELEMENT,
                3,
                new ImmutablePair(ALT_ATTR, "not identified area alternative"),
                new ImmutablePair(HREF_ATTR, "mock-area4.html"));        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.03.02-4NA-01"));        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.03.02-4NA-02"));        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.03.02-4NA-03"));        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.03.02-4NA-03"));        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.01.03.02-4NA-04"));
    }

}
