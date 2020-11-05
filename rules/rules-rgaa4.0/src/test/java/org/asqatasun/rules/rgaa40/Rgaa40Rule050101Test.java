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
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;

import static org.asqatasun.rules.keystore.AttributeStore.ARIA_DESCRIBEDBY_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.SUMMARY_ATTR;
import static org.asqatasun.rules.keystore.MarkerStore.COMPLEX_TABLE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.DATA_TABLE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.PRESENTATION_TABLE_MARKER;
import org.asqatasun.rules.rgaa40.test.Rgaa40RuleImplementationTestCase;

/**
 * Unit test class for implementation of rule 5.1.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/05.Tables/Rule-5-1-1.md">rule 5.1.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-5-1-1">5.1.1 rule specification</a>
 */
public class Rgaa40Rule050101Test extends Rgaa40RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa40Rule050101Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa40.Rgaa40Rule050101");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa40.Test.5.1.1-1Passed-01",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "class-complex-table"));
        addWebResource("Rgaa40.Test.5.1.1-1Passed-02",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "id-complex-table"));
        addWebResource("Rgaa40.Test.5.1.1-1Passed-03",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "class-complex-table;id-complex-table"));
        addWebResource("Rgaa40.Test.5.1.1-1Passed-04",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "complex-table"));
        addWebResource("Rgaa40.Test.5.1.1-1Passed-05",
                    createParameter("Rules", COMPLEX_TABLE_MARKER,"class-complex-role-table;id-complex-role-table;"));
        addWebResource("Rgaa40.Test.5.1.1-1Passed-06",
                    createParameter("Rules", COMPLEX_TABLE_MARKER,"class-complex-role-table;id-complex-role-table;"));
        addWebResource("Rgaa40.Test.5.1.1-1Passed-07",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "class-complex-table;id-complex-table"));

        addWebResource("Rgaa40.Test.5.1.1-2Failed-01",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "class-complex-table"));
        addWebResource("Rgaa40.Test.5.1.1-2Failed-02",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "id-complex-table"));
        addWebResource("Rgaa40.Test.5.1.1-2Failed-03",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "class-complex-table;id-complex-table"));
        addWebResource("Rgaa40.Test.5.1.1-2Failed-04",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "class-complex-table"));
        addWebResource("Rgaa40.Test.5.1.1-2Failed-05",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "class-complex-table"));
        addWebResource("Rgaa40.Test.5.1.1-2Failed-06",
                    createParameter("Rules", COMPLEX_TABLE_MARKER,"class-complex-role-table;id-complex-role-table;"));
        addWebResource("Rgaa40.Test.5.1.1-2Failed-07",
                    createParameter("Rules", COMPLEX_TABLE_MARKER,"class-complex-role-table;id-complex-role-table;"));
        addWebResource("Rgaa40.Test.5.1.1-2Failed-08",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "class-complex-table"));

        addWebResource("Rgaa40.Test.5.1.1-3NMI-01");
        addWebResource("Rgaa40.Test.5.1.1-3NMI-02");
        addWebResource("Rgaa40.Test.5.1.1-3NMI-03",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "class-complex-table"));
        addWebResource("Rgaa40.Test.5.1.1-3NMI-04",
                    createParameter("Rules", COMPLEX_TABLE_MARKER, "id-complex-table"),
                    createParameter("Rules", PRESENTATION_TABLE_MARKER, "class-presentation-table"));
        addWebResource("Rgaa40.Test.5.1.1-3NMI-05");
        addWebResource("Rgaa40.Test.5.1.1-3NMI-06");
        addWebResource("Rgaa40.Test.5.1.1-3NMI-07");

        addWebResource("Rgaa40.Test.5.1.1-4NA-01");
        addWebResource("Rgaa40.Test.5.1.1-4NA-02",
                    createParameter("Rules", PRESENTATION_TABLE_MARKER, "id-presentation-table"),
                    createParameter("Rules", DATA_TABLE_MARKER, "id-data-table"));
        addWebResource("Rgaa40.Test.5.1.1-4NA-03",
            createParameter("Rules", PRESENTATION_TABLE_MARKER, "id-presentation-table"),
            createParameter("Rules", DATA_TABLE_MARKER, "id-data-table"));
        addWebResource("Rgaa40.Test.5.1.1-4NA-04",
            createParameter("Rules", PRESENTATION_TABLE_MARKER, "id-presentation-table"),
            createParameter("Rules", DATA_TABLE_MARKER, "id-data-table"));
        addWebResource("Rgaa40.Test.5.1.1-4NA-05",
            createParameter("Rules", PRESENTATION_TABLE_MARKER, "id-presentation-table"),
            createParameter("Rules", DATA_TABLE_MARKER, "id-data-table"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        WebResource webResource = webResourceMap.get("Rgaa40.Test.5.1.1-1Passed-01");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
                // see: https://gitlab.com/asqatasun/Asqatasun/-/issues/457#note_430153843
                //      The doctype is extracted when the page is loaded through selenium.
                //      But selenium is not used for the test.
                //      The doctype has to be set "manually" in the sspHandler to make the test work.
        checkResultIsPassed(processPageTest("Rgaa40.Test.5.1.1-1Passed-01"), 1);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-1Passed-02");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        checkResultIsPassed(processPageTest("Rgaa40.Test.5.1.1-1Passed-02"), 1);

        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-1Passed-03");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        checkResultIsPassed(processPageTest("Rgaa40.Test.5.1.1-1Passed-03"), 2);
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-1Passed-04");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        checkResultIsPassed(processPageTest("Rgaa40.Test.5.1.1-1Passed-04"), 1);

        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-1Passed-05");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        checkResultIsPassed(processPageTest("Rgaa40.Test.5.1.1-1Passed-05"), 2);

        //----------------------------------------------------------------------
        //------------------------------1Passed-06------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-1Passed-06");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        checkResultIsPassed(processPageTest("Rgaa40.Test.5.1.1-1Passed-06"), 2);

        //----------------------------------------------------------------------
        //------------------------------1Passed-07------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-1Passed-07");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        checkResultIsPassed(processPageTest("Rgaa40.Test.5.1.1-1Passed-07"), 3);


        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-2Failed-01");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        ProcessResult processResult = processPageTest("Rgaa40.Test.5.1.1-2Failed-01");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.CAPTION_MISSING_ON_COMPLEX_TABLE_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-2Failed-02");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        processResult = processPageTest("Rgaa40.Test.5.1.1-2Failed-02");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.CAPTION_MISSING_ON_COMPLEX_TABLE_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-2Failed-03");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        processResult = processPageTest("Rgaa40.Test.5.1.1-2Failed-03");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.CAPTION_MISSING_ON_COMPLEX_TABLE_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.CAPTION_MISSING_ON_COMPLEX_TABLE_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                2);


        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-2Failed-04");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        processResult = processPageTest("Rgaa40.Test.5.1.1-2Failed-04");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_TABLE_WITH_CAPTION_IS_COMPLEX_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.CAPTION_MISSING_ON_COMPLEX_TABLE_MSG,
            HtmlElementStore.TABLE_ELEMENT,
            2);

        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-2Failed-05");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        processResult = processPageTest("Rgaa40.Test.5.1.1-2Failed-05");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.CAPTION_MISSING_ON_COMPLEX_TABLE_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_TABLE_WITHOUT_CAPTION_IS_NOT_COMPLEX_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                2);

        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-2Failed-06");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        processResult = processPageTest("Rgaa40.Test.5.1.1-2Failed-06");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.ARIA_DESCRIBEDBY_MISSING_ON_COMPLEX_TABLE_ROLE_MSG,
            HtmlElementStore.DIV_ELEMENT,
            1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-2Failed-07");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        processResult = processPageTest("Rgaa40.Test.5.1.1-2Failed-07");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.ARIA_DESCRIBEDBY_MISSING_ON_COMPLEX_TABLE_ROLE_MSG,
            HtmlElementStore.DIV_ELEMENT,
            1);

        //----------------------------------------------------------------------
        //------------------------------2Failed-08------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-2Failed-08");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        processResult = processPageTest("Rgaa40.Test.5.1.1-2Failed-08");
        checkResultIsFailed(processResult, 2, 2);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.SUMMARY_MISSING_ON_COMPLEX_TABLE_MSG,
            HtmlElementStore.TABLE_ELEMENT,
            1);
        checkRemarkIsPresent(
            processResult,
            TestSolution.FAILED,
            RemarkMessageStore.SUMMARY_MISSING_ON_COMPLEX_TABLE_MSG,
            HtmlElementStore.TABLE_ELEMENT,
            2);

        //----------------------------------------------------------------------
        //------------------------------3NMI-01------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-3NMI-01");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        processResult = processPageTest("Rgaa40.Test.5.1.1-3NMI-01");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_TABLE_WITHOUT_CAPTION_IS_NOT_COMPLEX_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------3NMI-02------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-3NMI-02");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        processResult = processPageTest("Rgaa40.Test.5.1.1-3NMI-02");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_TABLE_WITH_CAPTION_IS_COMPLEX_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------3NMI-03------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-3NMI-03");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        processResult = processPageTest("Rgaa40.Test.5.1.1-3NMI-03");
        checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_TABLE_WITH_CAPTION_IS_COMPLEX_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------3NMI-04------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-3NMI-04");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        processResult = processPageTest("Rgaa40.Test.5.1.1-3NMI-04");
        checkResultIsPreQualified(processResult, 2, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_TABLE_WITHOUT_CAPTION_IS_NOT_COMPLEX_MSG,
                HtmlElementStore.TABLE_ELEMENT,
                1);

        //----------------------------------------------------------------------
        //------------------------------3NMI-05------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-3NMI-05");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        processResult = processPageTest("Rgaa40.Test.5.1.1-3NMI-05");
        checkResultIsPreQualified(processResult, 2, 2);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.CHECK_TABLE_ROLE_WITH_ARIA_DESCRIBEDBY_IS_COMPLEX_MSG,
            HtmlElementStore.DIV_ELEMENT,
            1,
            new ImmutablePair<>(ARIA_DESCRIBEDBY_ATTR, "students_table_desc"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.CHECK_TABLE_ROLE_WITHOUT_ARIA_DESCRIBEDBY_IS_NOT_COMPLEX_MSG,
            HtmlElementStore.DIV_ELEMENT,
            2);

        //----------------------------------------------------------------------
        //------------------------------3NMI-06------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-3NMI-06");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        processResult = processPageTest("Rgaa40.Test.5.1.1-3NMI-06");
        checkResultIsPreQualified(processResult, 2, 2);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.CHECK_TABLE_ROLE_WITH_ARIA_DESCRIBEDBY_IS_COMPLEX_MSG,
            HtmlElementStore.DIV_ELEMENT,
            1,
            new ImmutablePair<>(ARIA_DESCRIBEDBY_ATTR, "students_table_desc"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.CHECK_TABLE_ROLE_WITHOUT_ARIA_DESCRIBEDBY_IS_NOT_COMPLEX_MSG,
            HtmlElementStore.DIV_ELEMENT,
            2);

        //----------------------------------------------------------------------
        //------------------------------3NMI-07------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-3NMI-07");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        processResult = processPageTest("Rgaa40.Test.5.1.1-3NMI-07");
        checkResultIsPreQualified(processResult, 2, 2);
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
            RemarkMessageStore.CHECK_TABLE_WITH_SUMMARY_IS_COMPLEX_MSG,
            HtmlElementStore.TABLE_ELEMENT,
            1,
            new ImmutablePair<>(SUMMARY_ATTR, "mock summary"));
        checkRemarkIsPresent(
            processResult,
            TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_TABLE_WITHOUT_SUMMARY_IS_NOT_COMPLEX_MSG,
            HtmlElementStore.TABLE_ELEMENT,
            2);

        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-4NA-01");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.5.1.1-4NA-01"));

        //----------------------------------------------------------------------
        //------------------------------4NA-02------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-4NA-02");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.5.1.1-4NA-02"));

        //----------------------------------------------------------------------
        //------------------------------4NA-03------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-4NA-03");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.5.1.1-4NA-03"));

        //----------------------------------------------------------------------
        //------------------------------4NA-04------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-4NA-04");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.5.1.1-4NA-04"));

        //----------------------------------------------------------------------
        //------------------------------4NA-05------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa40.Test.5.1.1-4NA-05");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        checkResultIsNotApplicable(processPageTest("Rgaa40.Test.5.1.1-4NA-05"));
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa40.Test.5.1.1-1Passed-01").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa40.Test.5.1.1-1Passed-02").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa40.Test.5.1.1-1Passed-03").getValue());
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa40.Test.5.1.1-1Passed-04").getValue());
        assertEquals(TestSolution.PASSED,
            consolidate("Rgaa40.Test.5.1.1-1Passed-05").getValue());
        assertEquals(TestSolution.PASSED,
            consolidate("Rgaa40.Test.5.1.1-1Passed-06").getValue());
        assertEquals(TestSolution.PASSED,
            consolidate("Rgaa40.Test.5.1.1-1Passed-07").getValue());

        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa40.Test.5.1.1-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa40.Test.5.1.1-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa40.Test.5.1.1-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa40.Test.5.1.1-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa40.Test.5.1.1-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa40.Test.5.1.1-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa40.Test.5.1.1-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa40.Test.5.1.1-2Failed-08").getValue());

        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa40.Test.5.1.1-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa40.Test.5.1.1-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa40.Test.5.1.1-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa40.Test.5.1.1-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa40.Test.5.1.1-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa40.Test.5.1.1-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa40.Test.5.1.1-3NMI-07").getValue());

        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa40.Test.5.1.1-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa40.Test.5.1.1-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa40.Test.5.1.1-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa40.Test.5.1.1-4NA-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa40.Test.5.1.1-4NA-05").getValue());
    }

}
