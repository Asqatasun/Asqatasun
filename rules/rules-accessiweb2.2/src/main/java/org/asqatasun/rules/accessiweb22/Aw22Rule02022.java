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
import org.asqatasun.rules.elementchecker.pertinence.AttributePertinenceChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.SRC_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.IFRAME_WITH_TITLE_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_TITLE_OF_IFRAME_PERTINENCE_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.NOT_PERTINENT_TITLE_OF_IFRAME_MSG;
import org.asqatasun.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 2.2.2 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/accessiweb2.2/02.Frames/Rule-2.2.2.html">the rule 2.2.2 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-2-2-2"> 2.2.2 rule specification</a>
 *
 */
public class Aw22Rule02022 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /**
     * Default constructor
     */
    public Aw22Rule02022() {
        super(
                new SimpleElementSelector(IFRAME_WITH_TITLE_CSS_LIKE_QUERY), 
                
                new AttributePertinenceChecker(
                    TITLE_ATTR, 
                    // tests the emptiness of the attribute
                    true, 
                    // compare title with src attribute
                    new TextAttributeOfElementBuilder(SRC_ATTR), 
                    // no comparison by extension
                    null, 
                    //  message associated with element when title is not pertinent
                    NOT_PERTINENT_TITLE_OF_IFRAME_MSG, 
                    // message associated with element when pertinence cannot be determined
                    CHECK_TITLE_OF_IFRAME_PERTINENCE_MSG, 
                    //evidence elements
                    TITLE_ATTR)
            );
    }


}