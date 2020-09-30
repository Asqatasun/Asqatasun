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
import org.asqatasun.rules.rgaa40.test.Rgaa40RuleImplementationTestCase;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit test class for implementation of rule 11.2.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/11.Forms/Rule-11-2-1.md">rule 11.2.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-11-2-1">11.2.1 rule specification</a>
 */
public class Rgaa40Rule110201Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa40Rule110201Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa40.Rgaa40Rule110201");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.11.2.1-3NMI-01");
        addWebResource("Rgaa40.Test.11.2.1-3NMI-02");
        addWebResource("Rgaa40.Test.11.2.1-3NMI-03");
        addWebResource("Rgaa40.Test.11.2.1-3NMI-04");
        addWebResource("Rgaa40.Test.11.2.1-3NMI-05");

        addWebResource("Rgaa40.Test.11.2.1-4NA-01");
        addWebResource("Rgaa40.Test.11.2.1-4NA-02");
        addWebResource("Rgaa40.Test.11.2.1-4NA-03");
        addWebResource("Rgaa40.Test.11.2.1-4NA-04");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa40.Test.11.2.1-3NMI-01");
        checkResultIsPreQualified(processResult, 3, 3);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
                HtmlElementStore.LABEL_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Form 1, label 1 for input without type attribute"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.LABEL_ELEMENT,
            2,
            new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Form 2, label 2 for input without type attribute"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.LABEL_ELEMENT,
            3,
            new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Form 2, label 3 for textarea"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.11.2.1-3NMI-02");
        checkResultIsPreQualified(processResult, 17, 17);
        HashMap<Integer, String> mapTypeAttribute = new HashMap<Integer, String>();
        mapTypeAttribute.put(1, "checkbox");
        mapTypeAttribute.put(2, "color");
        mapTypeAttribute.put(3, "date");
        mapTypeAttribute.put(4, "datetime-local");
        mapTypeAttribute.put(5, "email");
        mapTypeAttribute.put(6, "file");
        mapTypeAttribute.put(7, "month");
        mapTypeAttribute.put(8, "number");
        mapTypeAttribute.put(9, "password");
        mapTypeAttribute.put(10, "radio");
        mapTypeAttribute.put(11, "range");
        mapTypeAttribute.put(12, "search");
        mapTypeAttribute.put(13, "tel");
        mapTypeAttribute.put(14, "text");
        mapTypeAttribute.put(15, "time");
        mapTypeAttribute.put(16, "url");
        mapTypeAttribute.put(17, "week");
        for (Map.Entry item : mapTypeAttribute.entrySet()) {
            int position = ((int) item.getKey());
            checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
                HtmlElementStore.LABEL_ELEMENT,
                position,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "Label for input with type equal to " + item.getValue()));
        }
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.11.2.1-3NMI-03");
        checkResultIsPreQualified(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
                HtmlElementStore.LABEL_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Form 1, label for select"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.LABEL_ELEMENT,
            2,
            new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Form 2, label for datalist"));

        //----------------------------------------------------------------------
        //------------------------------3NMI-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.11.2.1-3NMI-04");
        checkResultIsPreQualified(processResult, 11, 11);
        HashMap<Integer, String> mapRoleAttribute = new HashMap<Integer, String>();
        mapRoleAttribute.put(1, "checkbox");
        mapRoleAttribute.put(2, "combobox");
        mapRoleAttribute.put(3, "listbox");
        mapRoleAttribute.put(4, "progressbar");
        mapRoleAttribute.put(5, "option");
        mapRoleAttribute.put(6, "radio");
        mapRoleAttribute.put(7, "searchbox");
        mapRoleAttribute.put(8, "slider");
        mapRoleAttribute.put(9, "spinbutton");
        mapRoleAttribute.put(10, "switch");
        mapRoleAttribute.put(11, "textbox");
        for (Map.Entry item : mapRoleAttribute.entrySet()) {
            int position = ((int) item.getKey());
            checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
                HtmlElementStore.LABEL_ELEMENT,
                position,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "Label value for ROLE " + item.getValue()));
        }

        //----------------------------------------------------------------------
        //------------------------------3NMI-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.11.2.1-3NMI-05");
        checkResultIsPreQualified(processResult, 3, 3);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.LABEL_ELEMENT,
            1,
            new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Label value for METER"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.LABEL_ELEMENT,
            2,
            new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Label value for PROGRESS"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.LABEL_ELEMENT,
            3,
            new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2,"Label value for OUTPUT"));
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.11.2.1-4NA-01"));

        
        //----------------------------------------------------------------------
        //------------------------------4NA-02------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.11.2.1-4NA-02"));
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.11.2.1-4NA-03"));

        //----------------------------------------------------------------------
        //------------------------------4NA-04------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.11.2.1-4NA-04"));
    }

}
