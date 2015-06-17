/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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

package org.tanaguru.rules.rgaa22;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.lang.LangChangeChecker;
import org.tanaguru.rules.elementchecker.lang.LangChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.HTML_WITH_LANG_CSS_LIKE_QUERY;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.textbuilder.OwnTextElementBuilder;

/**
 * Implementation of the rule 12.1 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-12-1">the rule 12.1 design page.</a>
 * @see <a href="http://rgaa.net/Presence-de-l-indication-des.html"> 12.1 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule12011 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    private final LangChecker ec = new LangChangeChecker(new OwnTextElementBuilder());
    
    /**
     * Default constructor
     */
    public Rgaa22Rule12011 () {
        super();
        setElementSelector(new SimpleElementSelector(HTML_WITH_LANG_CSS_LIKE_QUERY));
        setElementChecker(ec);
    }
    
    @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {

        super.check(sspHandler, testSolutionHandler);
        
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