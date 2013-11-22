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
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.pertinence.TextPertinenceChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.CONTENT_ATTR;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.META_DESC_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_META_DESC_RELEVANCY_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.META_DESC_NOT_RELEVANT_MSG;
import org.opens.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Test whether the meta description is relevant on the page
 * 
 * @author jkowalczyk
 */
public class SeoRule01012 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

     /* The selector */
    private static final ElementSelector ELEMENT_SELECTOR = 
            new SimpleElementSelector(META_DESC_CSS_LIKE_QUERY);
    
    /**
     * Default constructor
     */
    public SeoRule01012 () {
        super(
                ELEMENT_SELECTOR,
                new TextPertinenceChecker(
                    new TextAttributeOfElementBuilder(CONTENT_ATTR),
                    // check emptiness
                    true, 
                    // no comparison with other attribute
                    null, 
                    // blacklist nomenclature name
                    null, 
                    // not pertinent message
                    META_DESC_NOT_RELEVANT_MSG, 
                    // manual check message
                    CHECK_META_DESC_RELEVANCY_MSG,
                    // evidence elements
                    CONTENT_ATTR
                )
            );
    }
    
    @Override
    protected void check(
            SSPHandler sspHandler, 
            ElementHandler elementHandler, 
            TestSolutionHandler testSolutionHandler) {
        if (elementHandler.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        if (elementHandler.get().size() > 1)  {
            Element el = (Element)elementHandler.get().iterator().next();
            elementHandler.clean().add(el);
        }
        super.check(sspHandler, elementHandler, testSolutionHandler);
    }

}