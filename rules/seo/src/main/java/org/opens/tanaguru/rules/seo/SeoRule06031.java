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
package org.opens.tanaguru.rules.seo;

import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.text.TextLengthChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.TITLE_WITHIN_HEAD_CSS_LIKE_QUERY;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.TITLE_EXCEEDS_LIMIT_MSG;
import org.opens.tanaguru.rules.textbuilder.SimpleTextElementBuilder;

/**
 * Test whether the Title tag of the page exceeds 70 characters?
 * 
 * @author jkowalczyk
 */
public class SeoRule06031 extends AbstractPageRuleMarkupImplementation {

    /* the max length of the title element */
    private static final int TITLE_MAX_LENGTH = 100;

    /*
     * Constructor
     */
    public SeoRule06031() {
        super();
    }

     @Override
    protected void select(SSPHandler sspHandler, ElementHandler<Element> elementHandler) {
        ElementSelector es = new SimpleElementSelector(TITLE_WITHIN_HEAD_CSS_LIKE_QUERY);
        es.selectElements(sspHandler, elementHandler);
    }
    
    @Override
    protected void check(
            SSPHandler sspHandler, 
            ElementHandler<Element> elementHandler, 
            TestSolutionHandler testSolutionHandler) {
        if (elementHandler.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }

        // in case of more than one title, keep the first. 
        if (elementHandler.get().size() > 1) {
            Element el = elementHandler.get().iterator().next();
            elementHandler.clean().add(el);
        }

        ElementChecker ec = new TextLengthChecker(
                new SimpleTextElementBuilder(), 
                TITLE_MAX_LENGTH, 
                TITLE_EXCEEDS_LIMIT_MSG, 
                // evidence elements
                HtmlElementStore.TEXT_ELEMENT2);
        ec.check(sspHandler, elementHandler, testSolutionHandler);
    }

}