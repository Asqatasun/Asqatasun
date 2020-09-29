/**
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.rules.rgaa40;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.rules.keystore.EvidenceStore;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;
import org.asqatasun.rules.rgaa40.test.Rgaa40RuleImplementationTestCase;

import java.util.HashMap;
import java.util.Map;

import static org.asqatasun.rules.keystore.AttributeStore.ARIA_LABEL_ATTR;

/**
 * Unit test class for implementation of rule 10.1.2 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/10.Presentation_of_information/Rule-10-1-2.md">rule 10.1.2 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-10-1-2">10.1.2 rule specification</a>
 */
public class Rgaa40Rule100102Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa40Rule100102Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa40.Rgaa40Rule100102");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.10.1.2-1Passed-01");
        addWebResource("Rgaa40.Test.10.1.2-1Passed-02");
        addWebResource("Rgaa40.Test.10.1.2-1Passed-03");
        addWebResource("Rgaa40.Test.10.1.2-1Passed-04");
        addWebResource("Rgaa40.Test.10.1.2-1Passed-05");

        addWebResource("Rgaa40.Test.10.1.2-2Failed-01");
        addWebResource("Rgaa40.Test.10.1.2-2Failed-02");
        addWebResource("Rgaa40.Test.10.1.2-2Failed-03");
        addWebResource("Rgaa40.Test.10.1.2-2Failed-04");
        addWebResource("Rgaa40.Test.10.1.2-2Failed-05");
        addWebResource("Rgaa40.Test.10.1.2-2Failed-06");
        addWebResource("Rgaa40.Test.10.1.2-2Failed-07");
        addWebResource("Rgaa40.Test.10.1.2-2Failed-08");
        addWebResource("Rgaa40.Test.10.1.2-2Failed-09");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa40.Test.10.1.2-1Passed-01"),13);
       
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa40.Test.10.1.2-1Passed-02"),14);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa40.Test.10.1.2-1Passed-03"),14);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa40.Test.10.1.2-1Passed-04"),19);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        checkResultIsPassed(processPageTest("Rgaa40.Test.10.1.2-1Passed-05"),16);
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa40.Test.10.1.2-2Failed-01");
        checkResultIsFailed(processResult, 14, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                HtmlElementStore.P_ELEMENT,
                1, 
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "align"));
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.10.1.2-2Failed-02");
        checkResultIsFailed(processResult, 13, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                HtmlElementStore.BODY_ELEMENT,
                1,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "background"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                HtmlElementStore.DIV_ELEMENT,
                2,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "background"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.10.1.2-2Failed-03");
        checkResultIsFailed(processResult, 12, 5);
        HashMap<Integer, String> mapBodyAttributes = new HashMap<Integer, String>();
        mapBodyAttributes.put(1, "vlink");
        mapBodyAttributes.put(2, "link");
        mapBodyAttributes.put(3, "alink");
        mapBodyAttributes.put(4, "bgcolor");
        mapBodyAttributes.put(5, "text");
        for (Map.Entry item : mapBodyAttributes.entrySet()) {
            int position = ((int) item.getKey());
            checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                HtmlElementStore.BODY_ELEMENT,
                position,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, item.getValue()));
        }
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.10.1.2-2Failed-04");
        checkResultIsFailed(processResult, 14, 3);
        HashMap<Integer, String> mapImageAttributes = new HashMap<Integer, String>();
        mapImageAttributes.put(1, "border");
        mapImageAttributes.put(2, "vspace");
        mapImageAttributes.put(3, "hspace");
        for (Map.Entry item : mapImageAttributes.entrySet()) {
            int position = ((int) item.getKey());
            checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                HtmlElementStore.IMG_ELEMENT,
                position,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, item.getValue()));
        }
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.10.1.2-2Failed-05");
        checkResultIsFailed(processResult, 14, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                "font",
                1,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "color"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            "PresentationAttrFound",
            "font",
            2,
            new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "size"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.10.1.2-2Failed-06");
        checkResultIsFailed(processResult, 15, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                "hr",
                1,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "width"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                "hr",
                2,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "height"));

        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.10.1.2-2Failed-07");
        checkResultIsFailed(processResult, 9, 3);
        HashMap<Integer, String> mapFrameAttributes = new HashMap<Integer, String>();
        mapFrameAttributes.put(1, "marginwidth");
        mapFrameAttributes.put(2, "frameborder");
        mapFrameAttributes.put(3, "marginheight");
        for (Map.Entry item : mapFrameAttributes.entrySet()) {
            int position = ((int) item.getKey());
            checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                HtmlElementStore.FRAME_ELEMENT,
                position,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, item.getValue()));
        }


        //----------------------------------------------------------------------
        //------------------------------2Failed-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.10.1.2-2Failed-08");
        checkResultIsFailed(processResult, 22, 3);
        HashMap<Integer, String> mapTableAttributes = new HashMap<Integer, String>();
        mapTableAttributes.put(1, "border");
        mapTableAttributes.put(2, "cellpadding");
        mapTableAttributes.put(3, "cellspacing");
        for (Map.Entry item : mapTableAttributes.entrySet()) {
            int position = ((int) item.getKey());
            checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                "PresentationAttrFound",
                HtmlElementStore.TABLE_ELEMENT,
                position,
                new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, item.getValue()));
        }

        //----------------------------------------------------------------------
        //------------------------------2Failed-09------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.10.1.2-2Failed-09");
        checkResultIsFailed(processResult, 14, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            "PresentationAttrFound",
            HtmlElementStore.INPUT_ELEMENT,
            1,
            new ImmutablePair(EvidenceStore.ELEMENT_NAME_EE, "size"));
    }

}
