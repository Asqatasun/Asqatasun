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
 * Unit test class for implementation of rule 4.1.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/04.Multimedia/Rule-4-1-1.md">rule 4.1.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-4-1-1">4.1.1 rule specification</a>
 */
public class Rgaa40Rule040101Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa40Rule040101Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
            "org.asqatasun.rules.rgaa40.Rgaa40Rule040101");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.4.1.1-3NMI-01");
        addWebResource("Rgaa40.Test.4.1.1-3NMI-02");
        addWebResource("Rgaa40.Test.4.1.1-4NA-01");
        addWebResource("Rgaa40.Test.4.1.1-4NA-02");
    }

    @Override
    protected void setProcess() {

        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa40.Test.4.1.1-3NMI-01");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.AUDIO_ELEMENT,
            1);

        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa40.Test.4.1.1-3NMI-02");
        checkResultIsPreQualified(processResult, 16, 16);
        HashMap<Integer, String> mapTag = new HashMap<Integer, String>();
        mapTag.put(1, HtmlElementStore.BGSOUND_ELEMENT);
        mapTag.put(2, HtmlElementStore.AUDIO_ELEMENT);
        mapTag.put(3, HtmlElementStore.AUDIO_ELEMENT);
        mapTag.put(4, HtmlElementStore.A_ELEMENT); // Audio file link
        mapTag.put(5, HtmlElementStore.VIDEO_ELEMENT);
        mapTag.put(6, HtmlElementStore.VIDEO_ELEMENT);
        mapTag.put(7, HtmlElementStore.EMBED_ELEMENT);
        mapTag.put(8, HtmlElementStore.EMBED_ELEMENT);
        mapTag.put(9, HtmlElementStore.OBJECT_ELEMENT);
        mapTag.put(10, HtmlElementStore.OBJECT_ELEMENT);
        mapTag.put(11, HtmlElementStore.OBJECT_ELEMENT);
        mapTag.put(12, HtmlElementStore.A_ELEMENT); // Audio file link
        mapTag.put(13, HtmlElementStore.A_ELEMENT); // Audio file link
        mapTag.put(14, HtmlElementStore.A_ELEMENT); // Audio file link
        mapTag.put(15, HtmlElementStore.A_ELEMENT); // Audio file link
        mapTag.put(16, HtmlElementStore.A_ELEMENT); // Audio file link
        for (Map.Entry item : mapTag.entrySet()) {
            int position = ((int) item.getKey());
            String htmlElement = item.getValue().toString();
            checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
                htmlElement,
                position);
        }


        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.4.1.1-4NA-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.4.1.1-4NA-02"));
    }


}
