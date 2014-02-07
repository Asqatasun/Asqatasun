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

package org.opens.tanaguru.rules.rgaa22;

import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractNotTestedRuleImplementation;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.lang.LangChangeChecker;
import org.opens.tanaguru.rules.elementchecker.lang.LangChecker;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.HTML_WITH_LANG_CSS_LIKE_QUERY;
import org.opens.tanaguru.rules.textbuilder.OwnTextElementBuilder;

/**
 * Implementation of the rule 12.1 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-12-1">the rule 12.1 design page.</a>
 * @see <a href="http://rgaa.net/Presence-de-l-indication-des.html"> 12.1 rule specification
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule12011 extends AbstractPageRuleMarkupImplementation {

    private LangChecker ec = new LangChangeChecker();
    
    /**
     * Default constructor
     */
    public Rgaa22Rule12011 () {
        super();
    }
    
    @Override
    protected void select(SSPHandler sspHandler, ElementHandler<Element> elementHandler) {
        SimpleElementSelector selector = new SimpleElementSelector(HTML_WITH_LANG_CSS_LIKE_QUERY);
        selector.selectElements(sspHandler, elementHandler);
   }

    @Override
    protected void check(
            SSPHandler sspHandler, 
            ElementHandler<Element> selectionHandler, 
            TestSolutionHandler testSolutionHandler) {

        if (selectionHandler.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        ec = new LangChangeChecker(new OwnTextElementBuilder());
        ec.setNomenclatureLoaderService(nomenclatureLoaderService);
        ec.check(sspHandler, selectionHandler, testSolutionHandler);
    }
    
    @Override
    public int getSelectionSize() {
        return ec.getNbOfElementsTested();
    }

}