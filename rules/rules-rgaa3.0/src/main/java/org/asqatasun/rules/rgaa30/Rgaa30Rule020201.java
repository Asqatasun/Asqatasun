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

package org.asqatasun.rules.rgaa30;

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
 * Implementation of the rule 2.2.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.0/02.Frames/Rule-2-2-1.html">the rule 2.2.1 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-2-2-1"> 2.2.1 rule specification</a>
 *
 */
public class Rgaa30Rule020201 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule020201() {
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