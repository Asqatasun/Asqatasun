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
import org.tanaguru.rules.keystore.AttributeStore;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 13-6-3 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule130603Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule130603Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule130603");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.13.06.03-3NMI-01");
        addWebResource("Rgaa30.Test.13.06.03-3NMI-02");
        addWebResource("Rgaa30.Test.13.06.03-3NMI-03");
        addWebResource("Rgaa30.Test.13.06.03-3NMI-04");
        addWebResource("Rgaa30.Test.13.06.03-3NMI-05");
        addWebResource("Rgaa30.Test.13.06.03-3NMI-06");
        addWebResource("Rgaa30.Test.13.06.03-4NA-01");
        addWebResource("Rgaa30.Test.13.06.03-4NA-02");
        addWebResource("Rgaa30.Test.13.06.03-4NA-03");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.13.06.03-3NMI-01");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                1,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.odt"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));


        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.13.06.03-3NMI-02");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                getMessageKey(RemarkMessageStore.CHECK_DOWNLOADABLE_DOCUMENT_FROM_FORM_MSG),
                HtmlElementStore.A_ELEMENT,
                1);
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.13.06.03-3NMI-03");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                getMessageKey(RemarkMessageStore.CHECK_MANUALLY_LINK_WITHOUT_EXT_MSG),
                HtmlElementStore.A_ELEMENT,
                1);
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.13.06.03-3NMI-04");
        checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                getMessageKey(RemarkMessageStore.CHECK_MANUALLY_LINK_WITHOUT_EXT_MSG),
                HtmlElementStore.A_ELEMENT,
                1);
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.13.06.03-3NMI-05");
        checkResultIsPreQualified(processResult, 177, 177);
        
       //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                1,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.ods"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                2,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.fods"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                3,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.odt"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                4,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.fodt"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                5,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.odp"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                6,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.fodp"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                7,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.odg"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                8,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.fodg"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                9,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.pdf"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                10,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.doc"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                11,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.docx"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                12,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.docm"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                13,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.dot"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                14,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.dotm"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                15,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xls"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                16,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xlsx"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                17,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xlsm"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                18,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xlt"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                19,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xltx"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                20,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xltm"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                21,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xlc"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                22,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xlr"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                23,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xlam"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                24,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.csv"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                25,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.ppt"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                26,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.pptx"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                27,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.pps"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                28,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.vsd"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                29,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.vst"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                30,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.vss"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                31,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sxc"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                32,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sxd"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                33,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sxi"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                34,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sxm"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                35,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sxw"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                36,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sda"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                37,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sdc"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                38,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sdd"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                39,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sdf"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                40,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sdp"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                41,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sds"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                42,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sdw"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                43,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.oth"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                44,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.otg"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                45,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.ots"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                46,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.ott"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                47,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.cwk"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                48,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.cws"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                49,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.tar"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                50,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.tgz"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                51,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.bz"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                52,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.bz2"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                53,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.zip"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                54,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.gzip"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                55,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.gz"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                56,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.Z"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                57,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.7z"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                58,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.rar"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                59,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r00"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                60,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.rpm"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                61,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.deb"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                62,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.msi"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                63,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.exe"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                64,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.bat"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                65,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.pif"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                66,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.class"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                67,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.torrent"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                68,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.dmg"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                69,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.apk"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                70,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.bin"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                71,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.bak"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                72,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.dat"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                73,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.jar"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                74,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.mdk"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                75,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.dsk"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                76,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.vmdk"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                77,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r00"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                78,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r01"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                79,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r02"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                80,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r03"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                81,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r04"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                82,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r05"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                83,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r06"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                84,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r07"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                85,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r08"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                86,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r09"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                87,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r10"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                88,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r11"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                89,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r12"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                90,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r13"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                91,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r14"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                92,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r15"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                93,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r16"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                94,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r17"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                95,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r18"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                96,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r19"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                97,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r20"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                98,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r21"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                99,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r22"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                100,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r23"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                101,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r24"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                102,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r25"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                103,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r26"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                104,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r27"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                105,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r28"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                106,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r29"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                107,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r30"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                108,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r31"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                109,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r32"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                110,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r33"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                111,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r34"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                112,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r35"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                113,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r36"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                114,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r37"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                115,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r38"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                116,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r39"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                117,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r40"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                118,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r41"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                119,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r42"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                120,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r43"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                121,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r44"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                122,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r45"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                123,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r46"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                124,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r47"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                125,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r48"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                126,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r49"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                127,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r50"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                128,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r51"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                129,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r52"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                130,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r53"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                131,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r54"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                132,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r55"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                133,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r56"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                134,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r57"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                135,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r58"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                136,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r59"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                137,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r60"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                138,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r61"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                139,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r62"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                140,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r63"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                141,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r64"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                142,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r65"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                143,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r66"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                144,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r67"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                145,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r68"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                146,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r69"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                147,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r70"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                148,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r71"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                149,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r72"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                150,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r73"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                151,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r74"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                152,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r75"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                153,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r76"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                154,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r77"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                155,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r78"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                156,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r79"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                157,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r80"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                158,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r81"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                159,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r82"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                160,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r83"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                161,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r84"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                162,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r85"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                163,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r86"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                164,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r87"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                165,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r88"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                166,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r89"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                167,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r90"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                168,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r91"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                169,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r92"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                170,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r93"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                171,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r94"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                172,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r95"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                173,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r96"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                174,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r97"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                175,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r98"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                176,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.r99"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.DOWNLOADABLE_FILE_DETECTED_CHECK_LANG_MSG,
                HtmlElementStore.A_ELEMENT,
                177,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.taz"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent")); 
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.13.06.03-3NMI-06");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                getMessageKey(RemarkMessageStore.CHECK_MANUALLY_LINK_WITHOUT_EXT_MSG),
                HtmlElementStore.A_ELEMENT,
                1); 
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA--01---------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.13.06.03-4NA-01"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.13.06.03-4NA-02"), 26);
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.13.06.03-4NA-03"));
    }

    /**
     * 
     * @param msg
     * @return the message suffixed with the test key identifier
     */
    private String getMessageKey(String msg) {
        StringBuilder strb = new StringBuilder(msg);
        strb.append("_");
        strb.append(getName());
        return strb.toString();
    }
    
}
