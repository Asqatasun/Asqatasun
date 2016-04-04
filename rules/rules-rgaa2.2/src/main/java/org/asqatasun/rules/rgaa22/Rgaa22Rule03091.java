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

import org.asqatasun.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.pertinence.AttributePertinenceChecker;
import org.asqatasun.rules.elementselector.ElementSelector;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.LABEL_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.OPTGROUP_WITH_LABEL_ATTR_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_OPTGROUP_LABEL_PERTINENCE_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.NOT_PERTINENT_OPTGROUP_LABEL_MSG;

/**
 * Implementation of the rule 3.9 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.old-dot-org.org/en/content/rgaa22-rule-3-9">the rule 3.9 design page.</a>
 * @see <a href="http://rgaa.net/Pertinence-du-contenu-de-l.html"> 3.9 rule specification </a>
 *
 */
public class Rgaa22Rule03091 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /** The element selector */
    private static final ElementSelector ELEMENT_SELECTOR = 
            new SimpleElementSelector(OPTGROUP_WITH_LABEL_ATTR_CSS_LIKE_QUERY);
    
    /** The element checker */
    private static final ElementChecker ELEMENT_CHECKER = 
            new AttributePertinenceChecker(
                // check the label attribute
                LABEL_ATTR, 
                // check emptiness
                true, 
                // no comparison with other attributes
                null, 
                // no extension comparison with blacklist
                null, 
                //  message associated with element when not pertinent
                NOT_PERTINENT_OPTGROUP_LABEL_MSG, 
                //  message associated with element when pertinence cannot be determined
                CHECK_OPTGROUP_LABEL_PERTINENCE_MSG, 
                // evidence elements
                LABEL_ATTR);
    
    /**
     * Constructor
     */
    public Rgaa22Rule03091() {
        super(ELEMENT_SELECTOR, ELEMENT_CHECKER);
    }
    
}