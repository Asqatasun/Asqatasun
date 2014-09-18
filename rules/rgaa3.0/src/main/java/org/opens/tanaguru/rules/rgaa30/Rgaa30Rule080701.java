/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
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

package org.opens.tanaguru.rules.rgaa30;

import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.lang.LangChangeChecker;
import org.opens.tanaguru.rules.elementchecker.lang.LangChecker;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.HTML_WITH_LANG_CSS_LIKE_QUERY;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Implementation of the rule 8.7.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-8-7-1">the rule 8.7.1 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-html5aria-liste-deployee.html#test-8-7-1"> 8.7.1 rule specification</a>
 *
 */
public class Rgaa30Rule080701 extends AbstractPageRuleMarkupImplementation {

    private LangChecker ec = new LangChangeChecker();
    
    /**
     * Default constructor
     */
    public Rgaa30Rule080701 () {
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
        ec = new LangChangeChecker();
        ec.setNomenclatureLoaderService(nomenclatureLoaderService);
        ec.check(sspHandler, selectionHandler, testSolutionHandler);
        
        if (testSolutionHandler.getTestSolution().equals(TestSolution.NEED_MORE_INFO)) {
            sspHandler.getProcessRemarkService().addProcessRemark(
                    TestSolution.NEED_MORE_INFO, 
                    RemarkMessageStore.CHECK_SHORT_TEST_MSG);
        }
    }
    
    @Override
    public int getSelectionSize() {
        return ec.getNbOfElementsTested();
    }

}
