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
import org.opens.tanaguru.rules.keystore.AttributeStore;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.META_DESC_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.META_DESC_EXCEEDS_LIMIT_MSG;
import org.opens.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Test whether the meta description of the page exceeds 255 characters?
 * 
 * @author jkowalczyk
 */
public class SeoRule01013 extends AbstractPageRuleMarkupImplementation {

    private static int MAX_META_DESC_LENGTH = 255;
    
    /*
     * Constructor
     */
    public SeoRule01013() {
        super();
    }
    
    @Override
    protected void select(SSPHandler sspHandler, ElementHandler<Element> elementHandler) {
        ElementSelector es = new SimpleElementSelector(META_DESC_CSS_LIKE_QUERY);
        es.selectElements(sspHandler, elementHandler);
    }
    
    @Override
    protected void check(
            SSPHandler sspHandler, 
            ElementHandler<Element> elementHandler, 
            TestSolutionHandler testSolutionHandler) {
        if (elementHandler.isEmpty() || elementHandler.get().size() > 1) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        ElementChecker ec = new TextLengthChecker(
                new TextAttributeOfElementBuilder(AttributeStore.CONTENT_ATTR), 
                MAX_META_DESC_LENGTH, 
                META_DESC_EXCEEDS_LIMIT_MSG, 
                // evidence elements
                AttributeStore.CONTENT_ATTR);
        ec.check(sspHandler, elementHandler, testSolutionHandler);
    }

}