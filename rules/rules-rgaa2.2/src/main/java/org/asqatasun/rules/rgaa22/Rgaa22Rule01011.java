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

package org.asqatasun.rules.rgaa22;

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.text.TextEmptinessChecker;
import org.asqatasun.rules.elementselector.ElementSelector;
import org.asqatasun.rules.elementselector.MultipleElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.FRAME_WITH_TITLE_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.IFRAME_WITH_TITLE_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.EMPTY_TITLE_OF_FRAME_MSG;
import org.asqatasun.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 1.1 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-1-1">the rule 1.1 design page.</a>
 * @see <a href="http://rgaa.net/1-1-Absence-de-cadres-non-titres.html"> 1.1 rule specification </a>
 *
 */
public class Rgaa22Rule01011 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /** The element selector */
    private static final ElementSelector ELEMENT_SELECTOR = 
            new MultipleElementSelector(
                    FRAME_WITH_TITLE_CSS_LIKE_QUERY, 
                    IFRAME_WITH_TITLE_CSS_LIKE_QUERY);
    
    /** The element checker */
    private static final ElementChecker ELEMENT_CHECKER = 
            new TextEmptinessChecker(
                // the attribute to check
                new TextAttributeOfElementBuilder(TITLE_ATTR), 
                // failed when the attribute is empty
                TestSolution.FAILED, 
                // passed when the attribute is not empty
                TestSolution.PASSED, 
                //  message associated with element when title is empty
                EMPTY_TITLE_OF_FRAME_MSG, 
                // no message when not empty
                null);
    
    public Rgaa22Rule01011() {
        super(ELEMENT_SELECTOR, ELEMENT_CHECKER);
    }

}