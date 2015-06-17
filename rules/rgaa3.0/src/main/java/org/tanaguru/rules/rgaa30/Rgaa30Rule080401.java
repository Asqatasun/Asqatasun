/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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

package org.tanaguru.rules.rgaa30;

import org.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.tanaguru.rules.elementchecker.lang.LangDeclarationValidityChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.HTML_WITH_LANG_CSS_LIKE_QUERY;

/**
 * Implementation of the rule 8.4.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-8-4-1">the rule 8.4.1 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-8-4-1"> 8.4.1 rule specification</a>
 *
 */
public class Rgaa30Rule080401 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

     /**
     * Default constructor
     */
    public Rgaa30Rule080401 () {
        super(
                new SimpleElementSelector(HTML_WITH_LANG_CSS_LIKE_QUERY),
                new LangDeclarationValidityChecker(true, true)
            );
    }

}
