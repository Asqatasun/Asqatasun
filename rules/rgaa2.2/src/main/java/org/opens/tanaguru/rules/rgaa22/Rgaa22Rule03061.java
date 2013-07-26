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

import org.opens.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.pertinence.TextPertinenceChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.LEGEND_WITHIN_FIELDSET_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_LEGEND_PERTINENCE_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.NOT_PERTINENT_LEGEND_MSG;

/**
 * Implementation of the rule 3.6 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-3-6">the rule 3.6 design page.</a>
 * @see <a href="http://rgaa.net/Pertinence-du-contenu-de-l-element.html"> 3.6 rule specification
 *
 */
public class Rgaa22Rule03061 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /** The element selector */
    private static final ElementSelector ELEMENT_SELECTOR = 
            new SimpleElementSelector(LEGEND_WITHIN_FIELDSET_CSS_LIKE_QUERY);
    
    /** The element checker */
    private static final ElementChecker ELEMENT_CHECKER = 
            new TextPertinenceChecker(
                // the emptiness is tested
                true, 
                // no comparison with other attributes
                null, 
                // no comparison with blacklist
                null, 
                //  message associated with element when not pertinent
                NOT_PERTINENT_LEGEND_MSG, 
                //  message associated with element when pertinence cannot be determined
                CHECK_LEGEND_PERTINENCE_MSG, 
                TEXT_ELEMENT2);

    public Rgaa22Rule03061() {
        super(ELEMENT_SELECTOR, ELEMENT_CHECKER);
    }
    
}