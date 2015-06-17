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

package org.tanaguru.rules.rgaa22;

import org.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.tanaguru.rules.elementchecker.text.TextLengthChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.AttributeStore.ALT_ATTR;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.NOT_EMPTY_ALT_ATTR_NOT_IN_LINK_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.RemarkMessageStore.ALTERNATIVE_TOO_LONG_MSG;
import org.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 4.6 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-4-6">the rule 4.6 design page.</a>
 * @see <a href="http://rgaa.net/Longueur-du-contenu-des.html"> 4.6 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule04061 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    private static final int MAX_ALT_LENGTH=119;
    
    /**
     * Default constructor
     */
    public Rgaa22Rule04061 () {
        super(
                new SimpleElementSelector(
                    NOT_EMPTY_ALT_ATTR_NOT_IN_LINK_CSS_LIKE_QUERY),
                new TextLengthChecker(
                    new TextAttributeOfElementBuilder(ALT_ATTR), 
                    MAX_ALT_LENGTH, 
                    ALTERNATIVE_TOO_LONG_MSG, 
                    ALT_ATTR)  
             );
    }

}