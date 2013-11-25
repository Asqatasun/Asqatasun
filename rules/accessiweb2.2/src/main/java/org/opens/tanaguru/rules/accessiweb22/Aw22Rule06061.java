/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.rules.accessiweb22;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.ALT_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.HREF_ATTR;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.NOT_ANCHOR_LINK_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.EMPTY_LINK_MSG;

/**
 * Implementation of the rule 6.6.1 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-6-6-1">the rule 6.6.1 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-6-6-1"> 6.6.1 rule specification</a>
 *
 */
public class Aw22Rule06061 extends AbstractPageRuleMarkupImplementation {
    
    ElementHandler emptyLinksHandler = new ElementHandlerImpl();
    
    /**
     * Default constructor
     */
    public Aw22Rule06061 () {
        super();
    }

    @Override
    protected void select(SSPHandler sspHandler, ElementHandler<Element> elementHandler) {
        ElementSelector elementsSelector = 
                new SimpleElementSelector(NOT_ANCHOR_LINK_CSS_LIKE_QUERY);
        elementsSelector.selectElements(sspHandler, elementHandler);
        for (Element el : elementHandler.get()) {
            if (StringUtils.isBlank(el.text()) && 
                    el.getElementsByAttributeValueMatching(ALT_ATTR, "^(?=\\s*\\S).*$").isEmpty()) {
                emptyLinksHandler.add(el);
            }
        }
    }

    @Override
    protected void check(
            SSPHandler sspHandler, 
            ElementHandler selectionHandler, 
            TestSolutionHandler testSolutionHandler) {

        if (selectionHandler.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        if (emptyLinksHandler.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.PASSED);
            return;
        }
        ElementChecker ec= new ElementPresenceChecker(
                        TestSolution.FAILED,
                        TestSolution.PASSED,
                        EMPTY_LINK_MSG, 
                        null, 
                        HREF_ATTR);
        ec.check(sspHandler, emptyLinksHandler, testSolutionHandler);
    }
}