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

package org.asqatasun.rules.rgaa32016;

import org.asqatasun.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.asqatasun.rules.elementchecker.pertinence.AttributePertinenceChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.OPTGROUP_WITHIN_SELECT_WITH_LABEL_ATTR_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.AttributeStore.LABEL_ATTR;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_OPTGROUP_LABEL_PERTINENCE_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.NOT_PERTINENT_OPTGROUP_LABEL_MSG;

/**
 * Implementation of the rule 11.8.3 of the referential RGAA 3.2016
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.2016/11.Forms/Rule-11-8-3.html">the rule 11.8.3 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-11-8-3">11.8.3 rule specification</a>
 *
 */
public class Rgaa32016Rule110803 extends AbstractPageRuleWithSelectorAndCheckerImplementation {
    
    /**
     * Default constructor
     */
    public Rgaa32016Rule110803  () {
        super(
                new SimpleElementSelector(OPTGROUP_WITHIN_SELECT_WITH_LABEL_ATTR_CSS_LIKE_QUERY),
                new AttributePertinenceChecker(
                    LABEL_ATTR,
                    // check emptiness
                    true, 
                    // no comparison with other attributes
                    null, 
                    // no comparison with blacklist
                    null, 
                    //  message associated with element when not pertinent
                    NOT_PERTINENT_OPTGROUP_LABEL_MSG, 
                    //message associated with element when pertinence cannot be determined
                    CHECK_OPTGROUP_LABEL_PERTINENCE_MSG,
                    // evidence elements
                    LABEL_ATTR)
            );
    }

}
