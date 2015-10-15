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

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.tanaguru.ruleimplementation.ElementHandler;
import org.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementselector.ElementSelector;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.HtmlElementStore.H1_ELEMENT;
import static org.tanaguru.rules.keystore.HtmlElementStore.TITLE_ELEMENT;
import static org.tanaguru.rules.keystore.RemarkMessageStore.IDENTICAL_H1_AND_TITLE_MSG;
import org.tanaguru.rules.textbuilder.DeepTextElementBuilder;
import org.tanaguru.rules.textbuilder.SimpleTextElementBuilder;
import org.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * Test whether the text content of the Title tag is different from the text
 * content if the H1 tag
 * 
 * @author jkowalczyk
 */
public class SeoRule07051 extends AbstractPageRuleMarkupImplementation {

    private final ElementHandler<Element> h1Elements = new ElementHandlerImpl();
    private final ElementHandler<Element> titleElement = 
				new ElementHandlerImpl();

    public SeoRule07051() {
        super();
    }

    @Override
    protected void select(SSPHandler sspHandler) {
        ElementSelector selector = new SimpleElementSelector(H1_ELEMENT);
	selector.selectElements(sspHandler, h1Elements);
	// only keep one title when more than 2 are encountered
        selector = new SimpleElementSelector(TITLE_ELEMENT);
	selector.selectElements(sspHandler, titleElement);
	if (titleElement.get().size() > 1) {
	    Element title = titleElement.get().iterator().next();
	    titleElement.clean().add(title);
        }
    }

    @Override
    protected void check(SSPHandler sspHandler, 
                         TestSolutionHandler testSolutionHandler) {
	if (titleElement.isEmpty() || h1Elements.isEmpty()) {
	    testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
	    return;
        }
	String titleValue = new SimpleTextElementBuilder().
		buildTextFromElement(titleElement.get().iterator().next());
	TextElementBuilder h1ValueBuilder = new DeepTextElementBuilder();
	for (Element el : h1Elements.get()) {
	    String h1 = h1ValueBuilder.buildTextFromElement(el);
	    if (StringUtils.equalsIgnoreCase(h1, titleValue)) {
		sspHandler.getProcessRemarkService().
                    addProcessRemark(
			TestSolution.FAILED,
			IDENTICAL_H1_AND_TITLE_MSG);
		testSolutionHandler.addTestSolution(TestSolution.FAILED);
		return;
            }
	}
	testSolutionHandler.addTestSolution(TestSolution.PASSED);
    }

  @Override
  public int getSelectionSize() {
      return titleElement.size() + h1Elements.size();
  }

}