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

import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;
import org.asqatasun.rules.rgaa40.test.Rgaa40RuleImplementationTestCase;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit test class for implementation of rule 8.9.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/08.Mandatory_elements/Rule-8-9-1.md">rule 8.9.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-9-1">8.9.1 rule specification</a>
 */
public class Rgaa40Rule080901Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     *
     * @param testName
     */
    public Rgaa40Rule080901Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa40.Rgaa40Rule080901");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.8.9.1-2Failed-01");
        addWebResource("Rgaa40.Test.8.9.1-2Failed-02");
        addWebResource("Rgaa40.Test.8.9.1-2Failed-03");
        addWebResource("Rgaa40.Test.8.9.1-2Failed-04");
        addWebResource("Rgaa40.Test.8.9.1-2Failed-05");

        addWebResource("Rgaa40.Test.8.9.1-3NMI-01");
        addWebResource("Rgaa40.Test.8.9.1-3NMI-02");
        addWebResource("Rgaa40.Test.8.9.1-3NMI-03");
        addWebResource("Rgaa40.Test.8.9.1-3NMI-04");
        addWebResource("Rgaa40.Test.8.9.1-3NMI-05");
        addWebResource("Rgaa40.Test.8.9.1-3NMI-06");
        addWebResource("Rgaa40.Test.8.9.1-3NMI-07");
        addWebResource("Rgaa40.Test.8.9.1-3NMI-08");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //---------------------------2Failed-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa40.Test.8.9.1-2Failed-01");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.LINK_WITHOUT_TARGET_MSG,
            HtmlElementStore.A_ELEMENT,
            1);

        //----------------------------------------------------------------------
        //---------------------------2Failed-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.9.1-2Failed-02");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.LINK_WITHOUT_TARGET_MSG,
            HtmlElementStore.A_ELEMENT,
            1);

        //----------------------------------------------------------------------
        //---------------------------2Failed-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.9.1-2Failed-03");
        checkResultIsFailed(processResult, 8, 8);
        HashMap<Integer, String> mapTag = new HashMap<Integer, String>();
        mapTag.put(1, HtmlElementStore.P_ELEMENT);
        mapTag.put(2, HtmlElementStore.P_ELEMENT);
        mapTag.put(3, HtmlElementStore.P_ELEMENT);
        mapTag.put(4, HtmlElementStore.P_ELEMENT);
        mapTag.put(5, HtmlElementStore.LI_ELEMENT);
        mapTag.put(6, HtmlElementStore.LI_ELEMENT);
        mapTag.put(7, HtmlElementStore.LI_ELEMENT);
        mapTag.put(8, HtmlElementStore.LI_ELEMENT);
        for (Map.Entry item : mapTag.entrySet()) {
            int position = ((int) item.getKey());
            String htmlElement = item.getValue().toString();
            checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.TAGS_WITHOUT_CONTENT_USED_FOR_LAYOUT_PURPOSE_MSG,
                htmlElement,
                position);
        }

        //----------------------------------------------------------------------
        //---------------------------2Failed-04---------------------------------
        //----------------------------------------------------------------------
        int numberOfRemarksFailed04 = 4;
        processResult = processPageTest("Rgaa40.Test.8.9.1-2Failed-04");
        checkResultIsFailed(processResult,  numberOfRemarksFailed04, numberOfRemarksFailed04);
        for (int i=1; i< (numberOfRemarksFailed04 + 1) ;i++) {
            checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.CONSECUTIVE_TAGS_USED_FOR_LAYOUT_PURPOSE_MSG,
                HtmlElementStore.BR_ELEMENT,
                i);
        }

        //----------------------------------------------------------------------
        //---------------------------2Failed-05---------------------------------
        //----------------------------------------------------------------------
        int numberOfRemarksFailed05 = 3;
        processResult = processPageTest("Rgaa40.Test.8.9.1-2Failed-05");
        checkResultIsFailed(processResult, numberOfRemarksFailed05, numberOfRemarksFailed05);
        for (int i=1; i< (numberOfRemarksFailed05 + 1) ;i++) {
            checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.CONSECUTIVE_TAGS_USED_FOR_LAYOUT_PURPOSE_MSG,
                HtmlElementStore.BR_ELEMENT,
                i);
        }

        //----------------------------------------------------------------------
        //---------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.9.1-3NMI-01");
        checkResultIsPreQualified(processResult, 0, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
            "",
            1);

        //----------------------------------------------------------------------
        //---------------------------3NMI-02------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.9.1-3NMI-02");
        checkResultIsPreQualified(processResult, 0, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
            "",
            1);

        //----------------------------------------------------------------------
        //---------------------------3NMI-03------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.9.1-3NMI-03");
        checkResultIsPreQualified(processResult, 0, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
            "",
            1);

        //----------------------------------------------------------------------
        //---------------------------3NMI-04------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.9.1-3NMI-04");
        checkResultIsPreQualified(processResult, 0, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
            "",
            1);

        //----------------------------------------------------------------------
        //---------------------------3NMI-05------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.9.1-3NMI-05");
        checkResultIsPreQualified(processResult, 0, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
            "",
            1);

        //----------------------------------------------------------------------
        //---------------------------3NMI-06------------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.9.1-3NMI-06");
        checkResultIsPreQualified(processResult, 0, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
            "",
            1);

        //----------------------------------------------------------------------
        //---------------------------3NMI-07-----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.9.1-3NMI-07");
        checkResultIsPreQualified(processResult, 0, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
            "",
            1);

        //----------------------------------------------------------------------
        //---------------------------3NMI-08-----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.8.9.1-3NMI-08");
        checkResultIsPreQualified(processResult, 0, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            getMessageKey(RemarkMessageStore.NO_PATTERN_DETECTED_MSG),
            "",
            1);
    }

    /**
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
