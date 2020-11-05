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
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.doctype.DoctypeHtml5Checker;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;

import static org.asqatasun.entity.audit.TestSolution.*;
import static org.asqatasun.rules.keystore.AttributeStore.ARIA_DESCRIBEDBY_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.SUMMARY_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.*;
import static org.asqatasun.rules.keystore.MarkerStore.COMPLEX_TABLE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.DATA_TABLE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.PRESENTATION_TABLE_MARKER;

import static org.asqatasun.rules.keystore.RemarkMessageStore.*;

import org.jsoup.nodes.Element;

/**
 * Implementation of rule 5.1.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/05.Tables/Rule-5-1-1.md">rule 5.1.1 design page</a>.
 *
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-5-1-1">5.1.1 rule specification</a>
 */
public class Rgaa40Rule050101 extends AbstractMarkerPageRuleImplementation {

    /**
     * Current page is HTML5?
     */
    private Boolean isHtml5Page;

    
    // Elements with role="table"
    /////////////////////////////

    /**
     * The elements identified with the markers, with role="table" and aria-describedby attributes
     */
    private final ElementHandler<Element> tableRoleWithAriaDescribedbyWithMarkerHandler = new ElementHandlerImpl();

    /**
     * The elements not identified by the markers, with role="table" and aria-describedby attributes
     */
    private final ElementHandler<Element> tableRoleWithAriaDescribedbyWithoutMarkerHandler = new ElementHandlerImpl();

    /**
     * The elements identified with the markers, with role="table" attribute, but without aria-describedby attribute
     */
    private final ElementHandler<Element> tableRoleWithoutAriaDescribedbyWithMarkerHandler = new ElementHandlerImpl();

    /**
     * The elements not identified by the markers, with role="table" attribute, but without aria-describedby attribute
     */
    private final ElementHandler<Element> tableRoleWithoutAriaDescribedbyWithoutMarkerHandler = new ElementHandlerImpl();

    
    // For HTML5 page, table elements
    /////////////////////////////////
    
    /**
     * For HTML5 page, table elements identified with the markers, with a caption child element
     */
    private final ElementHandler<Element> tableForHtml5PageWithCaptionWithMarkerHandler = new ElementHandlerImpl();

    /**
     * For HTML5 page, table elements not identified by the markers, with a caption child element
     */
    private final ElementHandler<Element> tableForHtml5PageWithCaptionWithoutMarkerHandler = new ElementHandlerImpl();

    /**
     * For HTML5 page, table elements identified with the markers, without a caption child element
     */
    private final ElementHandler<Element> tableForHtml5PageWithoutCaptionWithMarkerHandler = new ElementHandlerImpl();

    /**
     * For HTML5 page, table elements not identified by the markers, without a caption child element
     */
    private final ElementHandler<Element> tableForHtml5PageWithoutCaptionWithoutMarkerHandler = new ElementHandlerImpl();


    // For HTML4 page (a non-HTML5 page: HTML4, XHTML1, ...), table elements
    ////////////////////////////////////////////////////////////////////////

    /**
     * For a HTML4 page, table elements identified with the markers, with a summary attribute
     */
    private final ElementHandler<Element> tableForHtml4PageWithSummaryWithMarkerHandler = new ElementHandlerImpl();

    /**
     * For a HTML4 page, table elements not identified by the markers, with a summary attribute
     */
    private final ElementHandler<Element> tableForHtml4PageWithSummaryWithoutMarkerHandler = new ElementHandlerImpl();

    /**
     * For a HTML4 page, table elements identified with the markers, without a summary attribute
     */
    private final ElementHandler<Element> tableForHtml4PageWithoutSummaryWithMarkerHandler = new ElementHandlerImpl();

    /**
     * For a HTML4 page, table elements not identified by the markers, without a summary attribute
     */
    private final ElementHandler<Element> tableForHtml4PageWithoutSummaryWithoutMarkerHandler = new ElementHandlerImpl();
    
    

    /**
     * Default constructor
     */
    public Rgaa40Rule050101() {
        super(
            // the complex tables are part of the scope
            new String[]{COMPLEX_TABLE_MARKER},

            // the data and presentation tables are not part of the scope
            new String[]{PRESENTATION_TABLE_MARKER, DATA_TABLE_MARKER}
        );
    }

    @Override
    public int getSelectionSize() {
        return this.tableForHtml5PageWithCaptionWithMarkerHandler.get().size()
            + this.tableForHtml5PageWithCaptionWithoutMarkerHandler.get().size()
            + this.tableForHtml5PageWithoutCaptionWithMarkerHandler.get().size()
            + this.tableForHtml5PageWithoutCaptionWithoutMarkerHandler.get().size()
            + this.tableForHtml4PageWithSummaryWithMarkerHandler.get().size()
            + this.tableForHtml4PageWithSummaryWithoutMarkerHandler.get().size()
            + this.tableForHtml4PageWithoutSummaryWithMarkerHandler.get().size()
            + this.tableForHtml4PageWithoutSummaryWithoutMarkerHandler.get().size()
            + this.tableRoleWithAriaDescribedbyWithMarkerHandler.get().size()
            + this.tableRoleWithAriaDescribedbyWithoutMarkerHandler.get().size()
            + this.tableRoleWithoutAriaDescribedbyWithMarkerHandler.get().size()
            + this.tableRoleWithoutAriaDescribedbyWithoutMarkerHandler.get().size();
    }

    @Override
    protected void select(SSPHandler sspHandler) {

        extractMarkerListFromAuditParameter(sspHandler);

        // Elements with role="table" and aria-describedby attributes
        // ---> SET-1 (identified with the markers)
        // ---> SET-2 (not identified by the markers)
        new SimpleElementSelector(TABLE_ROLE_WITH_ARIA_DESCRIBEDBY_CSS_LIKE_QUERY)
            .selectElements(sspHandler, tableRoleWithAriaDescribedbyWithoutMarkerHandler);
        sortMarkerElements(tableRoleWithAriaDescribedbyWithMarkerHandler, tableRoleWithAriaDescribedbyWithoutMarkerHandler);

        // Elements with role="table" attribute, but without aria-describedby attribute
        // ---> SET-3 (identified with the markers)
        // ---> SET-4 (not identified by the markers)
        new SimpleElementSelector(TABLE_ROLE_WITHOUT_ARIA_DESCRIBEDBY_CSS_LIKE_QUERY)
            .selectElements(sspHandler, tableRoleWithoutAriaDescribedbyWithoutMarkerHandler);
        sortMarkerElements(tableRoleWithoutAriaDescribedbyWithMarkerHandler, tableRoleWithoutAriaDescribedbyWithoutMarkerHandler);

        DoctypeHtml5Checker doctypeHtml5Checker = new DoctypeHtml5Checker();
        isHtml5Page = doctypeHtml5Checker.isHtml5(sspHandler);
        if(isHtml5Page) { // HTML 5 page
            // For HTML5 page, table elements with a caption child element
            // ---> SET-5 (identified with the markers)
            // ---> SET-6 (not identified by the markers)
            new SimpleElementSelector(TABLE_WITH_CAPTION_CSS_LIKE_QUERY)
                .selectElements(sspHandler, tableForHtml5PageWithCaptionWithoutMarkerHandler);
            sortMarkerElements(tableForHtml5PageWithCaptionWithMarkerHandler, tableForHtml5PageWithCaptionWithoutMarkerHandler);

            // For HTML5 page, table elements without a caption child element
            // ---> SET-7 (identified with the markers)
            // ---> SET-8 (not identified by the markers)
            new SimpleElementSelector(TABLE_WITHOUT_CAPTION_CSS_LIKE_QUERY)
                .selectElements(sspHandler, tableForHtml5PageWithoutCaptionWithoutMarkerHandler);
            sortMarkerElements(tableForHtml5PageWithoutCaptionWithMarkerHandler, tableForHtml5PageWithoutCaptionWithoutMarkerHandler);
        }
        else { // HTML4 page (a non-HTML5 page: HTML4, XHTML1, ...)

            // For HTML4 page, table elements with a summary attribute
            // ---> SET-9 (identified with the markers)
            // ---> SET-10 (not identified by the markers)
            new SimpleElementSelector(TABLE_WITH_SUMMARY_CSS_LIKE_QUERY)
                .selectElements(sspHandler, tableForHtml4PageWithSummaryWithoutMarkerHandler);
            sortMarkerElements(tableForHtml4PageWithSummaryWithMarkerHandler, tableForHtml4PageWithSummaryWithoutMarkerHandler);

            // For HTML4 page, table elements without a summary attribute
            // ---> SET-11 (identified with the markers)
            // ---> SET-12 (not identified by the markers)
            new SimpleElementSelector(TABLE_WITHOUT_SUMMARY_CSS_LIKE_QUERY)
                .selectElements(sspHandler, tableForHtml4PageWithoutSummaryWithoutMarkerHandler);
            sortMarkerElements(tableForHtml4PageWithoutSummaryWithMarkerHandler, tableForHtml4PageWithoutSummaryWithoutMarkerHandler);
        }
    }

    @Override
    protected void check(
        SSPHandler sspHandler,
        TestSolutionHandler testSolutionHandler) {

        // TEST-1   Elements identified with the markers
        //          with role="table" and aria-describedby attributes
        // - PASSED: SET-1 is not empty
        // - NA:     SET-1 is empty
        ElementChecker tableRoleWithAriaDescribedbyWithMarkerChecker = new ElementPresenceChecker(
            new ImmutablePair<>(PASSED, ""),
            new ImmutablePair<>(NOT_APPLICABLE, ""));
        tableRoleWithAriaDescribedbyWithMarkerChecker.check(
            sspHandler,
            tableRoleWithAriaDescribedbyWithMarkerHandler,
            testSolutionHandler);

        // TEST-2   Elements not identified by the markers
        //          with role="table" and aria-describedby attributes
        // - NMI: SET-2 is not empty  ---> msg "CheckTableRoleWithAriaDescribedbyIsComplex"
        // - NA:  SET-2 is empty
        ElementChecker tableRoleWithAriaDescribedbyWithoutMarkerChecker = new ElementPresenceChecker(
            new ImmutablePair<>(NEED_MORE_INFO, CHECK_TABLE_ROLE_WITH_ARIA_DESCRIBEDBY_IS_COMPLEX_MSG),
            new ImmutablePair<>(NOT_APPLICABLE, ""),
            ARIA_DESCRIBEDBY_ATTR);
        tableRoleWithAriaDescribedbyWithoutMarkerChecker.check(
            sspHandler,
            tableRoleWithAriaDescribedbyWithoutMarkerHandler,
            testSolutionHandler);

        // TEST-3   Elements identified with the markers
        //          with role="table" attribute, but without aria-describedby attribute
        // - FAILED: SET-3 is not empty  ---> msg "AriaDescribedbyMissingOnComplexTableRole"
        // - NA:     SET-3 is empty
        ElementChecker tableRoleWithoutAriaDescribedbyWithMarkerChecker = new ElementPresenceChecker(
            new ImmutablePair<>(FAILED, ARIA_DESCRIBEDBY_MISSING_ON_COMPLEX_TABLE_ROLE_MSG),
            new ImmutablePair<>(NOT_APPLICABLE, ""));
        tableRoleWithoutAriaDescribedbyWithMarkerChecker.check(
            sspHandler,
            tableRoleWithoutAriaDescribedbyWithMarkerHandler,
            testSolutionHandler);

        // TEST-4   Elements not identified by the markers
        //          with role="table" attribute, but without aria-describedby attribute
        // - NMI: SET-4 is not empty  ---> msg "CheckTableRoleWithoutAriaDescribedbyIsNotComplex"
        // - NA:  SET-4 is empty
        ElementChecker tableRoleWithoutAriaDescribedbyWithoutMarkerChecker = new ElementPresenceChecker(
            new ImmutablePair<>(NEED_MORE_INFO, CHECK_TABLE_ROLE_WITHOUT_ARIA_DESCRIBEDBY_IS_NOT_COMPLEX_MSG),
            new ImmutablePair<>(NOT_APPLICABLE, ""));
        tableRoleWithoutAriaDescribedbyWithoutMarkerChecker.check(
            sspHandler,
            tableRoleWithoutAriaDescribedbyWithoutMarkerHandler,
            testSolutionHandler);

        if(isHtml5Page) { // HTML 5 page

            // TEST-5   For HTML5 page, table elements identified with the markers
            //          with a caption child element
            // - PASSED: SET-5 is not empty
            // - NA:     SET-5 is empty
            ElementChecker tableForHtml5PageWithCaptionWithMarkerChecker = new ElementPresenceChecker(
                new ImmutablePair<>(PASSED, ""),
                new ImmutablePair<>(NOT_APPLICABLE, ""));
            tableForHtml5PageWithCaptionWithMarkerChecker.check(
                sspHandler,
                tableForHtml5PageWithCaptionWithMarkerHandler,
                testSolutionHandler); // @@@TODO Add checker: only one <caption>

            // TEST-6   For HTML5 page, table elements not identified by the markers
            //          with a caption child element
            // - NMI: SET-6 is not empty  ---> msg "CheckTableWithCaptionChildElementIsComplex"
            // - NA:  SET-6 is empty
            ElementChecker tableForHtml5PageWithCaptionWithoutMarkerChecker = new ElementPresenceChecker(
                new ImmutablePair<>(NEED_MORE_INFO, CHECK_TABLE_WITH_CAPTION_IS_COMPLEX_MSG),
                new ImmutablePair<>(NOT_APPLICABLE, "")); // @@@TODO add evidence
            tableForHtml5PageWithCaptionWithoutMarkerChecker.check(
                sspHandler,
                tableForHtml5PageWithCaptionWithoutMarkerHandler,
                testSolutionHandler);

            // TEST-7   For HTML5 page, table elements identified with the markers
            //          without a caption child element
            // - FAILED: SET-7 is not empty  ---> msg "CaptionMissingOnComplexTable"
            // - NA:     SET-7 is empty
            ElementChecker tableForHtml5PageWithoutCaptionWithMarkerChecker = new ElementPresenceChecker(
                new ImmutablePair<>(FAILED, CAPTION_MISSING_ON_COMPLEX_TABLE_MSG),
                new ImmutablePair<>(NOT_APPLICABLE, ""));
            tableForHtml5PageWithoutCaptionWithMarkerChecker.check(
                sspHandler,
                tableForHtml5PageWithoutCaptionWithMarkerHandler,
                testSolutionHandler);

            // TEST-8   For HTML5 page, table elements not identified by the markers
            //          without a caption child element
            // - NMI: SET-8 is not empty  ---> msg "CheckTableWithoutCaptionChildElementIsNotComplex"
            // - NA:  SET-8 is empty
            ElementChecker tableForHtml5PageWithoutCaptionWithoutMarkerChecker = new ElementPresenceChecker(
                new ImmutablePair<>(NEED_MORE_INFO, CHECK_TABLE_WITHOUT_CAPTION_IS_NOT_COMPLEX_MSG),
                new ImmutablePair<>(NOT_APPLICABLE, ""));
            tableForHtml5PageWithoutCaptionWithoutMarkerChecker.check(
                sspHandler,
                tableForHtml5PageWithoutCaptionWithoutMarkerHandler,
                testSolutionHandler);
        }
        else { // HTML4 page (a non-HTML5 page: HTML4, XHTML1, ...)

            // TEST-9   For HTML4 page, table elements identified with the markers
            //           with a summary attribute
            // - PASSED: SET-9 is not empty
            // - NA:     SET-9 is empty
            ElementChecker tableForHtml4PageWithSummaryWithMarkerChecker = new ElementPresenceChecker(
                new ImmutablePair<>(PASSED, ""),
                new ImmutablePair<>(NOT_APPLICABLE, "")); // @@@TODO add evidence
            tableForHtml4PageWithSummaryWithMarkerChecker.check(
                sspHandler,
                tableForHtml4PageWithSummaryWithMarkerHandler,
                testSolutionHandler);

            // TEST-10   For HTML4 page, table elements not identified by the markers
            //           with a summary attribute
            // - NMI: SET-10 is not empty  ---> msg "CheckTableWithSummaryIsComplex"
            // - NA:  SET-10 is empty
            ElementChecker tableForHtml4PageWithSummaryWithoutMarkerChecker = new ElementPresenceChecker(
                new ImmutablePair<>(NEED_MORE_INFO, CHECK_TABLE_WITH_SUMMARY_IS_COMPLEX_MSG),
                new ImmutablePair<>(NOT_APPLICABLE, ""),
                SUMMARY_ATTR);
            tableForHtml4PageWithSummaryWithoutMarkerChecker.check(
                sspHandler,
                tableForHtml4PageWithSummaryWithoutMarkerHandler,
                testSolutionHandler);

            // TEST-11   For HTML4 page, table elements identified with the markers
            //           without a summary attribute
            // - FAILED: SET-11 is not empty  ---> msg "SummaryMissingOnComplexTable"
            // - NA:     SET-11 is empty
            ElementChecker tableForHtml4PageWithoutSummaryWithMarkerChecker = new ElementPresenceChecker(
                new ImmutablePair<>(FAILED, SUMMARY_MISSING_ON_COMPLEX_TABLE_MSG),
                new ImmutablePair<>(NOT_APPLICABLE, ""));
            tableForHtml4PageWithoutSummaryWithMarkerChecker.check(
                sspHandler,
                tableForHtml4PageWithoutSummaryWithMarkerHandler,
                testSolutionHandler);

            // TEST-12   For HTML4 page, table elements not identified by the markers
            //           without a summary attribute
            // - NMI: SET-12 is not empty  ---> msg "CheckTableWithoutSummaryIsNotComplex"
            // - NA:  SET-12 is empty
            ElementChecker tableForHtml4PageWithoutSummaryWithoutMarkerChecker = new ElementPresenceChecker(
                new ImmutablePair<>(NEED_MORE_INFO, CHECK_TABLE_WITHOUT_SUMMARY_IS_NOT_COMPLEX_MSG),
                new ImmutablePair<>(NOT_APPLICABLE, ""));
            tableForHtml4PageWithoutSummaryWithoutMarkerChecker.check(
                sspHandler,
                tableForHtml4PageWithoutSummaryWithoutMarkerHandler,
                testSolutionHandler);
        }

    }

}
