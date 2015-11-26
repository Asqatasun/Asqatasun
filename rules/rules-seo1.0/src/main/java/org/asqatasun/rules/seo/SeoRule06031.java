/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.asqatasun.rules.seo;

import org.jsoup.nodes.Element;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.text.TextLengthChecker;
import org.asqatasun.rules.elementselector.ElementSelector;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.TITLE_WITHIN_HEAD_CSS_LIKE_QUERY;
import org.asqatasun.rules.keystore.HtmlElementStore;
import static org.asqatasun.rules.keystore.RemarkMessageStore.TITLE_EXCEEDS_LIMIT_MSG;
import org.asqatasun.rules.textbuilder.SimpleTextElementBuilder;

/**
 * Test whether the Title tag of the page exceeds 70 characters?
 * 
 * @author jkowalczyk
 */
public class SeoRule06031 extends AbstractPageRuleMarkupImplementation {

    /* the max length of the title element */
    private static final int TITLE_MAX_LENGTH = 100;
    private final ElementHandler<Element> elementHandler = new ElementHandlerImpl();
    /*
     * Constructor
     */
    public SeoRule06031() {
        super();
    }

     @Override
    protected void select(SSPHandler sspHandler) {
        ElementSelector es = new SimpleElementSelector(TITLE_WITHIN_HEAD_CSS_LIKE_QUERY);
        es.selectElements(sspHandler, elementHandler);
    }
    
    @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {
        if (elementHandler.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }

        // in case of more than one title, keep the first. 
        if (elementHandler.get().size() > 1) {
            Element element = elementHandler.get().iterator().next();
            elementHandler.clean().add(element);
        }

        ElementChecker checker = new TextLengthChecker(
                new SimpleTextElementBuilder(), 
                TITLE_MAX_LENGTH, 
                TITLE_EXCEEDS_LIMIT_MSG, 
                // evidence elements
                HtmlElementStore.TEXT_ELEMENT2);
        checker.check(sspHandler, elementHandler, testSolutionHandler);
    }

  @Override
  public int getSelectionSize() {
      return elementHandler.size();
  }

}