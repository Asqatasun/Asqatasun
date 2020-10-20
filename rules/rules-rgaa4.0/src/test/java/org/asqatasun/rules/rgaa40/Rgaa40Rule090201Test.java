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
import org.asqatasun.entity.audit.SSP;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.rules.keystore.CssLikeQueryStore;
import org.asqatasun.rules.keystore.EvidenceStore;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;
import org.asqatasun.rules.rgaa40.test.Rgaa40RuleImplementationTestCase;

import static org.asqatasun.rules.keystore.RemarkMessageStore.MAIN_ELEMENT_NOT_UNIQUE;
import static org.asqatasun.rules.keystore.RemarkMessageStore.MAIN_ELEMENT_MISSING;
import static org.asqatasun.rules.keystore.RemarkMessageStore.HEADER_ELEMENT_MISSING;
import static org.asqatasun.rules.keystore.RemarkMessageStore.NAV_ELEMENT_MISSING;
import static org.asqatasun.rules.keystore.RemarkMessageStore.FOOTER_ELEMENT_MISSING;
import static org.asqatasun.rules.keystore.RemarkMessageStore.HTML5_DOCUMENT_STRUCTURE_CHECK_MANUALLY;

/**
 * Unit test class for implementation of rule 9.2.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/09.Structure_of_information/Rule-9-2-1.md">rule 9.2.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-9-2-1">9.2.1 rule specification</a>
 */
public class Rgaa40Rule090201Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa40Rule090201Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
            "org.asqatasun.rules.rgaa40.Rgaa40Rule090201");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.9.2.1-2Failed-01");
        addWebResource("Rgaa40.Test.9.2.1-2Failed-02");
        addWebResource("Rgaa40.Test.9.2.1-2Failed-03");
        addWebResource("Rgaa40.Test.9.2.1-2Failed-04");
        addWebResource("Rgaa40.Test.9.2.1-2Failed-05");
        addWebResource("Rgaa40.Test.9.2.1-2Failed-06");
        addWebResource("Rgaa40.Test.9.2.1-2Failed-07");
        addWebResource("Rgaa40.Test.9.2.1-2Failed-08");

        addWebResource("Rgaa40.Test.9.2.1-3NMI-01");
        addWebResource("Rgaa40.Test.9.2.1-3NMI-02");
        addWebResource("Rgaa40.Test.9.2.1-3NMI-03");

        addWebResource("Rgaa40.Test.9.2.1-4NA-01");
        addWebResource("Rgaa40.Test.9.2.1-4NA-02");
    }

    @Override
    protected void setProcess() {

        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        WebResource webResource = webResourceMap.get("Rgaa40.Test.9.2.1-2Failed-01");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
            // see: https://gitlab.com/asqatasun/Asqatasun/-/issues/457#note_430153843
            //      The doctype is extracted when the page is loaded through selenium.
            //      But selenium is not used for the test.
            //      The doctype has to be set "manually" in the sspHandler to make the test work.
        ProcessResult processResult = processPageTest("Rgaa40.Test.9.2.1-2Failed-01");
        checkResultIsFailed(processResult, 0, 4);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            MAIN_ELEMENT_MISSING,
            1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            HEADER_ELEMENT_MISSING,
            2);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            FOOTER_ELEMENT_MISSING,
            3);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            NAV_ELEMENT_MISSING,
            4);

        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.9.2.1-2Failed-02");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
            // see: https://gitlab.com/asqatasun/Asqatasun/-/issues/457#note_430153843
            //      The doctype is extracted when the page is loaded through selenium.
            //      But selenium is not used for the test.
            //      The doctype has to be set "manually" in the sspHandler to make the test work.
        processResult = processPageTest("Rgaa40.Test.9.2.1-2Failed-02");
        checkResultIsFailed(processResult, 0, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            HEADER_ELEMENT_MISSING,
            1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.9.2.1-2Failed-03");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
            // see: https://gitlab.com/asqatasun/Asqatasun/-/issues/457#note_430153843
            //      The doctype is extracted when the page is loaded through selenium.
            //      But selenium is not used for the test.
            //      The doctype has to be set "manually" in the sspHandler to make the test work.
        processResult = processPageTest("Rgaa40.Test.9.2.1-2Failed-03");
        checkResultIsFailed(processResult, 0, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            NAV_ELEMENT_MISSING,
            1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.9.2.1-2Failed-04");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
            // see: https://gitlab.com/asqatasun/Asqatasun/-/issues/457#note_430153843
            //      The doctype is extracted when the page is loaded through selenium.
            //      But selenium is not used for the test.
            //      The doctype has to be set "manually" in the sspHandler to make the test work.
        processResult = processPageTest("Rgaa40.Test.9.2.1-2Failed-04");
        checkResultIsFailed(processResult, 0, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            MAIN_ELEMENT_MISSING,
            1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.9.2.1-2Failed-05");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
            // see: https://gitlab.com/asqatasun/Asqatasun/-/issues/457#note_430153843
            //      The doctype is extracted when the page is loaded through selenium.
            //      But selenium is not used for the test.
            //      The doctype has to be set "manually" in the sspHandler to make the test work.
        processResult = processPageTest("Rgaa40.Test.9.2.1-2Failed-05");
        checkResultIsFailed(processResult, 0, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            FOOTER_ELEMENT_MISSING,
            1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.9.2.1-2Failed-06");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
            // see: https://gitlab.com/asqatasun/Asqatasun/-/issues/457#note_430153843
            //      The doctype is extracted when the page is loaded through selenium.
            //      But selenium is not used for the test.
            //      The doctype has to be set "manually" in the sspHandler to make the test work.
        processResult = processPageTest("Rgaa40.Test.9.2.1-2Failed-06");
        checkResultIsFailed(processResult, 0, 2);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            MAIN_ELEMENT_NOT_UNIQUE,
            HtmlElementStore.MAIN_ELEMENT,
            1
            );
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            MAIN_ELEMENT_NOT_UNIQUE,
            HtmlElementStore.MAIN_ELEMENT,
            2);

        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.9.2.1-2Failed-07"); // no DOCTYPE
        processResult = processPageTest("Rgaa40.Test.9.2.1-2Failed-07");
        checkResultIsFailed(processResult, 0, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            HEADER_ELEMENT_MISSING,
            1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-08------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.9.2.1-2Failed-08");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
            // see: https://gitlab.com/asqatasun/Asqatasun/-/issues/457#note_430153843
            //      The doctype is extracted when the page is loaded through selenium.
            //      But selenium is not used for the test.
            //      The doctype has to be set "manually" in the sspHandler to make the test work.
        processResult = processPageTest("Rgaa40.Test.9.2.1-2Failed-08");
        checkResultIsFailed(processResult, 0, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            MAIN_ELEMENT_MISSING,
            1);

        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.9.2.1-3NMI-01");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
            // see: https://gitlab.com/asqatasun/Asqatasun/-/issues/457#note_430153843
            //      The doctype is extracted when the page is loaded through selenium.
            //      But selenium is not used for the test.
            //      The doctype has to be set "manually" in the sspHandler to make the test work.
        processResult = processPageTest("Rgaa40.Test.9.2.1-3NMI-01");
        checkResultIsPreQualified(processResult, 0, 5);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            HTML5_DOCUMENT_STRUCTURE_CHECK_MANUALLY,
            1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.MAIN_ELEMENT,
            2);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.HEADER_ELEMENT,
            3);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.FOOTER_ELEMENT,
            4);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.NAV_ELEMENT,
            5);

        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.9.2.1-3NMI-02");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
            // see: https://gitlab.com/asqatasun/Asqatasun/-/issues/457#note_430153843
            //      The doctype is extracted when the page is loaded through selenium.
            //      But selenium is not used for the test.
            //      The doctype has to be set "manually" in the sspHandler to make the test work.
        processResult = processPageTest("Rgaa40.Test.9.2.1-3NMI-02");
        checkResultIsPreQualified(processResult, 0, 8);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            HTML5_DOCUMENT_STRUCTURE_CHECK_MANUALLY,
            1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.MAIN_ELEMENT,
            2);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.HEADER_ELEMENT,
            3);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.HEADER_ELEMENT,
            4);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.FOOTER_ELEMENT,
            5);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.FOOTER_ELEMENT,
            6);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.NAV_ELEMENT,
            7);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.NAV_ELEMENT,
            8);

        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.9.2.1-3NMI-03");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        // see: https://gitlab.com/asqatasun/Asqatasun/-/issues/457#note_430153843
        //      The doctype is extracted when the page is loaded through selenium.
        //      But selenium is not used for the test.
        //      The doctype has to be set "manually" in the sspHandler to make the test work.
        processResult = processPageTest("Rgaa40.Test.9.2.1-3NMI-03");
        checkResultIsPreQualified(processResult, 0, 8);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            HTML5_DOCUMENT_STRUCTURE_CHECK_MANUALLY,
            1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.MAIN_ELEMENT,
            2);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.HEADER_ELEMENT,
            3);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.HEADER_ELEMENT,
            4);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.FOOTER_ELEMENT,
            5);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.FOOTER_ELEMENT,
            6);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.NAV_ELEMENT,
            7);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG,
            HtmlElementStore.NAV_ELEMENT,
            8);


        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.9.2.1-4NA-01");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
            // see: https://gitlab.com/asqatasun/Asqatasun/-/issues/457#note_430153843
            //      The doctype is extracted when the page is loaded through selenium.
            //      But selenium is not used for the test.
            //      The doctype has to be set "manually" in the sspHandler to make the test work.
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.9.2.1-4NA-01"));

        //----------------------------------------------------------------------
        //------------------------------4NA-02------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.9.2.1-4NA-02");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"http://www.w3.org/TR/html4/strict.dtd\">");
            // see: https://gitlab.com/asqatasun/Asqatasun/-/issues/457#note_430153843
            //      The doctype is extracted when the page is loaded through selenium.
            //      But selenium is not used for the test.
            //      The doctype has to be set "manually" in the sspHandler to make the test work.
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.9.2.1-4NA-02"));
    }

}
