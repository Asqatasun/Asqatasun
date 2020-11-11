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
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.*;
import org.asqatasun.rules.elementchecker.doctype.DoctypeHtml5Checker;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.jsoup.nodes.Element;

import static org.asqatasun.rules.keystore.CssLikeQueryStore.HTML5_PAGE_FOOTER_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.HTML5_PAGE_HEADER_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.HTML5_PAGE_MAIN_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.MAIN_ELEMENT_NOT_UNIQUE;
import static org.asqatasun.rules.keystore.RemarkMessageStore.MAIN_ELEMENT_MISSING;
import static org.asqatasun.rules.keystore.RemarkMessageStore.HEADER_ELEMENT_MISSING;
import static org.asqatasun.rules.keystore.RemarkMessageStore.NAV_ELEMENT_MISSING;
import static org.asqatasun.rules.keystore.RemarkMessageStore.FOOTER_ELEMENT_MISSING;
import static org.asqatasun.rules.keystore.RemarkMessageStore.HTML5_DOCUMENT_STRUCTURE_CHECK_MANUALLY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG;

/**
 * Implementation of rule 9.2.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/09.Structure_of_information/Rule-9-2-1.md">rule 9.2.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-9-2-1">9.2.1 rule specification</a>
 */
public class Rgaa40Rule090201 extends AbstractPageRuleMarkupImplementation {

    /**
     * Default constructor
     */
    public Rgaa40Rule090201() {
        super();
    }

    @Override
    protected void select(SSPHandler sspHandler) {

    }

    @Override
    protected void check(SSPHandler sspHandler, TestSolutionHandler testSolutionHandler) {

        // Is it an HTML5 page?
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        DoctypeHtml5Checker doctypeHtml5Checker = new DoctypeHtml5Checker();
        doctypeHtml5Checker.check(sspHandler, elementHandler, testSolutionHandler);
        if (testSolutionHandler.getTestSolution().equals(TestSolution.FAILED)) {
            testSolutionHandler.cleanTestSolutions();
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        testSolutionHandler.cleanTestSolutions();

        // <main> tag: required, only one
        SimpleElementSelector ses = new SimpleElementSelector(HTML5_PAGE_MAIN_CSS_LIKE_QUERY);
        ElementHandler mainElementHandler = new ElementHandlerImpl();
        ses.selectElements(sspHandler, mainElementHandler);
        ElementPresenceChecker epc = new ElementPresenceChecker(
            true,
            new ImmutablePair<>(TestSolution.PASSED, ""),
            new ImmutablePair<>(TestSolution.FAILED, MAIN_ELEMENT_MISSING),
            MAIN_ELEMENT_NOT_UNIQUE);
        epc.check(sspHandler, mainElementHandler, testSolutionHandler);

        // <header> tag: required, one or more
        ses = new SimpleElementSelector(HTML5_PAGE_HEADER_CSS_LIKE_QUERY);
        ElementHandler headerElementHandler = new ElementHandlerImpl();
        ses.selectElements(sspHandler, headerElementHandler);
        epc = new ElementPresenceChecker(
            false,
            new ImmutablePair<>(TestSolution.PASSED, ""),
            new ImmutablePair<>(TestSolution.FAILED, HEADER_ELEMENT_MISSING),
            "");
        epc.check(sspHandler, headerElementHandler, testSolutionHandler);

        // <footer> tag: required, one or more
        ses = new SimpleElementSelector(HTML5_PAGE_FOOTER_CSS_LIKE_QUERY);
        ElementHandler footerElementHandler = new ElementHandlerImpl();
        ses.selectElements(sspHandler, footerElementHandler);
        epc = new ElementPresenceChecker(
            false,
            new ImmutablePair<>(TestSolution.PASSED, ""),
            new ImmutablePair<>(TestSolution.FAILED, FOOTER_ELEMENT_MISSING),
            "");
        epc.check(sspHandler, footerElementHandler, testSolutionHandler);

        // <nav> tag: required, one or more
        ses = new SimpleElementSelector(HtmlElementStore.NAV_ELEMENT);
        ElementHandler navElementHandler = new ElementHandlerImpl();
        ses.selectElements(sspHandler, navElementHandler);
        epc = new ElementPresenceChecker(
            false,
            new ImmutablePair<>(TestSolution.PASSED, ""),
            new ImmutablePair<>(TestSolution.FAILED, NAV_ELEMENT_MISSING),
            "");
        epc.check(sspHandler, navElementHandler, testSolutionHandler);


        // No fail = prequalified
        if (testSolutionHandler.getTestSolution().equals(TestSolution.PASSED)) {
            // Global prequalified message
            sspHandler.getProcessRemarkService().addProcessRemark(
                TestSolution.NEED_MORE_INFO,
                HTML5_DOCUMENT_STRUCTURE_CHECK_MANUALLY
            );
            testSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);

            // Grouped elements message
            epc = new ElementPresenceChecker(
                false,
                new ImmutablePair<>(TestSolution.NEED_MORE_INFO, MANUAL_CHECK_ON_ELEMENTS_MSG),
                new ImmutablePair<>(TestSolution.FAILED, ""),
                "");
            epc.check(sspHandler, mainElementHandler, testSolutionHandler);
            epc.check(sspHandler, headerElementHandler, testSolutionHandler);
            epc.check(sspHandler, footerElementHandler, testSolutionHandler);
            epc.check(sspHandler, navElementHandler, testSolutionHandler);
        }
    }

    @Override
    public int getSelectionSize() {
        return 0;
    }

}
