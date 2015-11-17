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

package org.asqatasun.rules.accessiweb22;

import org.asqatasun.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.asqatasun.rules.elementchecker.pertinence.TextPertinenceChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.LEGEND_WITHIN_FIELDSET_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_LEGEND_PERTINENCE_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.NOT_PERTINENT_LEGEND_MSG;

/**
 * Implementation of the rule 11.7.1 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-11-7-1">the rule 11.7.1 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-7-1"> 11.7.1 rule specification</a>
 *
 */
public class Aw22Rule11071  extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    public Aw22Rule11071 () {
        super (
                 new SimpleElementSelector(LEGEND_WITHIN_FIELDSET_CSS_LIKE_QUERY), 
                 new TextPertinenceChecker(
                    // check emptiness
                    true, 
                    // no comparison with other attributes
                    null, 
                    // no comparison with blacklist
                    null, 
                    //  message associated with element when not pertinent
                    NOT_PERTINENT_LEGEND_MSG, 
                    //message associated with element when pertinence cannot be determined
                    CHECK_LEGEND_PERTINENCE_MSG,
                    // evidence elements
                    TEXT_ELEMENT2)
            );
    }

}