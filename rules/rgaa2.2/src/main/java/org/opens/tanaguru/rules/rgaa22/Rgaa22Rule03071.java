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

package org.opens.tanaguru.rules.rgaa22;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.element.ChildElementPresenceChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.SELECT_WITHOUT_OPTGROUP_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.OPTION_ELEMENT;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_SELECT_ELEMENT_USAGE_MSG;

/**
 * Implementation of the rule 3.7 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-3-7">the rule 3.7 design page.</a>
 * @see <a href="http://rgaa.net/Regroupement-d-elements-option.html"> 3.7 rule specification </a>
 *
 */
public class Rgaa22Rule03071 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /** The element selector */
    private static final ElementSelector ELEMENT_SELECTOR = 
            new SimpleElementSelector(SELECT_WITHOUT_OPTGROUP_CSS_LIKE_QUERY);
    
    /** The element checker */
    private static final ElementChecker ELEMENT_CHECKER = 
            new ChildElementPresenceChecker(
                    // the element to search
                    OPTION_ELEMENT, 
                    // at least two select elements are expected to justify a group
                    Integer.valueOf(2), 
                    // nmi when the number of expected children is reached
                    TestSolution.NEED_MORE_INFO, 
                    // passed when the number of expected children is reached
                    TestSolution.PASSED, 
                    // message associated with each select with expected number of elements
                    CHECK_SELECT_ELEMENT_USAGE_MSG, 
                    // message associated with each select
                    null);
    
    /**
     * Constructor
     */
    public Rgaa22Rule03071() {
        super(ELEMENT_SELECTOR, ELEMENT_CHECKER);
    }

}