/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
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

package org.opens.tanaguru.rules.rgaa30;

import org.opens.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.opens.tanaguru.rules.elementchecker.pertinence.AttributePertinenceChecker;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.SRC_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.FRAME_WITH_TITLE_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_TITLE_OF_FRAME_PERTINENCE_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.NOT_PERTINENT_TITLE_OF_FRAME_MSG;
import org.opens.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 2.2.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="https://github.com/Tanaguru/Tanaguru-rules-RGAA-3-doc/wiki/Rule-2-2-1">the rule 2.2.1 design page.</a>
 * @see <a href="https://references.modernisation.gouv.fr/sites/default/files/RGAA3/referentiel_technique.htm#test-2-2-1"> 2.2.1 rule specification</a>
 *
 */
public class Rgaa30Rule020201 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule020201() {
        super(
                new SimpleElementSelector(FRAME_WITH_TITLE_CSS_LIKE_QUERY), 
                
                new AttributePertinenceChecker(
                    TITLE_ATTR, 
                    // the selection keep elements with not empty title
                    true, 
                    // compare title with src attribute
                    new TextAttributeOfElementBuilder(SRC_ATTR), 
                    // no comparison by extension
                    null, 
                    //  message associated with element when title is not pertinent
                    NOT_PERTINENT_TITLE_OF_FRAME_MSG, 
                    // message associated with element when pertinence cannot be determined
                    CHECK_TITLE_OF_FRAME_PERTINENCE_MSG, 
                    //evidence elements 
                    TITLE_ATTR)
            );
    }

}
