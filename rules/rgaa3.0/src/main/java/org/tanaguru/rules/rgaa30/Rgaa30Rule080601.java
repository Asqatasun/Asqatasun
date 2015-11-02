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

package org.tanaguru.rules.rgaa30;

import org.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.tanaguru.rules.elementchecker.pertinence.TextPertinenceChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.TITLE_WITHIN_HEAD_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_TITLE_PERTINENCE_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.NOT_PERTINENT_TITLE_MSG;

/**
 * Implementation of the rule 8.6.1 of the referential Accessiweb 4.1.
 * <br/>
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-8-6-1">the rule 8.6.1 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-8-6-2"> 8.6.1 rule specification</a>
 *
 */
public class Rgaa30Rule080601 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /* Title blacklisted nomenclature */
    private static final String TITLE_BLACKLIST_NOM = "UnexplicitPageTitle";
    
    /**
     * Default constructor
     */
    public Rgaa30Rule080601 () {
        super(
                new SimpleElementSelector(TITLE_WITHIN_HEAD_CSS_LIKE_QUERY),
                new TextPertinenceChecker(
                    // check emptiness
                    true, 
                    // no comparison with other attribute
                    null, 
                    // blacklist nomenclature name
                    TITLE_BLACKLIST_NOM, 
                    // not pertinent message
                    NOT_PERTINENT_TITLE_MSG, 
                    // manual check message
                    CHECK_TITLE_PERTINENCE_MSG,
                    // evidence elements
                    TEXT_ELEMENT2
                )
            );
    }

}
