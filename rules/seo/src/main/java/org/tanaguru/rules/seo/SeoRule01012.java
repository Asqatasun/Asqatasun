/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.rules.seo;

import org.jsoup.nodes.Element;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.pertinence.TextPertinenceChecker;
import org.tanaguru.rules.elementselector.ElementSelector;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.AttributeStore.CONTENT_ATTR;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.META_DESC_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_META_DESC_RELEVANCY_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.META_DESC_NOT_RELEVANT_MSG;
import org.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

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
            final SSPHandler sspHandler, 
            final TestSolutionHandler testSolutionHandler) {
        if (getElements().isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        if (getElements().get().size() > 1)  {
            Element fisrtEl = (Element)getElements().get().iterator().next();
            getElements().clean().add(fisrtEl);
        }
        super.check(sspHandler, testSolutionHandler);
    }

}