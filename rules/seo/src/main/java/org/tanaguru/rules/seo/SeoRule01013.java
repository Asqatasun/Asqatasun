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
import org.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.tanaguru.ruleimplementation.ElementHandler;
import org.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.ElementChecker;
import org.tanaguru.rules.elementchecker.text.TextLengthChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import org.tanaguru.rules.keystore.AttributeStore;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.META_DESC_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.RemarkMessageStore.META_DESC_EXCEEDS_LIMIT_MSG;
import org.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Test whether the meta description of the page exceeds 255 characters?
 * 
 * @author jkowalczyk
 */
public class SeoRule01013 extends AbstractPageRuleMarkupImplementation {

    private final static int MAX_META_DESC_LENGTH = 255;
    private final ElementHandler<Element> elementHandler = new ElementHandlerImpl();
    
    /*
     * Constructor
     */
    public SeoRule01013() {
        super();
    }
    
    @Override
    protected void select(final SSPHandler sspHandler) {
        new SimpleElementSelector(META_DESC_CSS_LIKE_QUERY).selectElements(sspHandler, elementHandler);
    }
    
    @Override
    protected void check(
            final SSPHandler sspHandler, 
            final TestSolutionHandler testSolutionHandler) {
        if (elementHandler.isEmpty() || elementHandler.get().size() > 1) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        ElementChecker checker = new TextLengthChecker(
                new TextAttributeOfElementBuilder(AttributeStore.CONTENT_ATTR), 
                MAX_META_DESC_LENGTH, 
                META_DESC_EXCEEDS_LIMIT_MSG, 
                // evidence elements
                AttributeStore.CONTENT_ATTR);
        checker.check(sspHandler, elementHandler, testSolutionHandler);
    }

  @Override
  public int getSelectionSize() {
      return elementHandler.size();
  }

}