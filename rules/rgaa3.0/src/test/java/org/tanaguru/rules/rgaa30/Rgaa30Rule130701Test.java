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
 * Unit test class for the implementation of the rule 13-7-1 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule130701Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule130701Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule130701");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.13.07.01-3NMI-01");
        addWebResource("Rgaa30.Test.13.07.01-3NMI-02");
        addWebResource("Rgaa30.Test.13.07.01-3NMI-03");
        addWebResource("Rgaa30.Test.13.07.01-3NMI-04");
        addWebResource("Rgaa30.Test.13.07.01-3NMI-05");
        addWebResource("Rgaa30.Test.13.07.01-3NMI-06");
        addWebResource("Rgaa30.Test.13.07.01-4NA-01");
        addWebResource("Rgaa30.Test.13.07.01-4NA-02");
        addWebResource("Rgaa30.Test.13.07.01-4NA-03");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.13.07.01-3NMI-01");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                1,
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.odt"));

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.13.07.01-3NMI-02");
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
        processResult = processPageTest("Rgaa30.Test.13.07.01-3NMI-03");
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
        processResult = processPageTest("Rgaa30.Test.13.07.01-3NMI-04");
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
        processResult = processPageTest("Rgaa30.Test.13.07.01-3NMI-05");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                getMessageKey(RemarkMessageStore.CHECK_MANUALLY_LINK_WITHOUT_EXT_MSG),
                HtmlElementStore.A_ELEMENT,
                1);
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.13.07.01-3NMI-06");
        checkResultIsPreQualified(processResult, 48, 48);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                1, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.ods"));
        
	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                2, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.fods"));
        
	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                3, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.odt"));
        
	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                4, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.fodt"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                5, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.odp"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                6, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.fodp"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                7, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.odg"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                8, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.fodg"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                9, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.pdf"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                10, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.doc"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                11, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.docx"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                12, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.docm"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                13, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.dot"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                14, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.dotm"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                15, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xls"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                16, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xlsx"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                17, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xlsm"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                18, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xlt"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                19, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xltx"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                20, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xltm"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                21, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xlc"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                22, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xlr"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                23, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.xlam"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                24, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.csv"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                25, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.ppt"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                26, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.pptx"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                27, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.pps"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                28, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.vsd"));
        
	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                29, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.vst"));
        
	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                30, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.vss"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                31, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sxc"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                32, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sxd"));
        
	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                33, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sxi"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                34, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sxm"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                35, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sxw"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                36, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sda"));
        
	//----------------------------------------------------------------------
       checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                37, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sdc"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                38, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sdd"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                39, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sdf"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                40, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sdp"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                41, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sds"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                42, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.sdw"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                43, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.oth"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                44, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.otg"));
        
	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                45, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.ots"));
        
	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                46, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.ott"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                47, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.cwk"));

	//----------------------------------------------------------------------
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.OFFICE_DOCUMENT_DETECTED_MSG,
                HtmlElementStore.A_ELEMENT,
                48, 
                new ImmutablePair(AttributeStore.HREF_ATTR, "my-link.cws"));
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA--01---------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.13.07.01-4NA-01"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.13.07.01-4NA-02"), 1);
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.13.07.01-4NA-03"));
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