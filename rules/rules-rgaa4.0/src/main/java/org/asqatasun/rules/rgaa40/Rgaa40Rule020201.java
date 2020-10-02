/**
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.rules.rgaa40;

import org.asqatasun.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.asqatasun.rules.elementchecker.pertinence.AttributePertinenceChecker;
import org.asqatasun.rules.elementselector.MultipleElementSelector;
import org.asqatasun.rules.textbuilder.TextAttributeOfElementBuilder;

import static org.asqatasun.rules.keystore.AttributeStore.SRC_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.FRAME_WITH_TITLE_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.IFRAME_WITH_TITLE_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_TITLE_OF_FRAME_OR_IFRAME_PERTINENCE_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.NOT_PERTINENT_TITLE_OF_FRAME_OR_IFRAME_MSG;

/**
 * Implementation of rule 2.2.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/02.Frames/Rule-2-2-1.md">rule 2.2.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-2-2-1">2.2.1 rule specification</a>
 */
public class Rgaa40Rule020201 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /**
     * Default constructor
     */
    public Rgaa40Rule020201() {
        super(
            new MultipleElementSelector(IFRAME_WITH_TITLE_CSS_LIKE_QUERY, FRAME_WITH_TITLE_CSS_LIKE_QUERY),

            new AttributePertinenceChecker(
                TITLE_ATTR,
                // tests the emptiness of the attribute
                true,
                // compare title with src attribute
                new TextAttributeOfElementBuilder(SRC_ATTR),
                // no comparison by extension
                null,
                //  message associated with element when title is not pertinent
                NOT_PERTINENT_TITLE_OF_FRAME_OR_IFRAME_MSG,
                // message associated with element when pertinence cannot be determined
                CHECK_TITLE_OF_FRAME_OR_IFRAME_PERTINENCE_MSG,
                //evidence elements
                TITLE_ATTR)
        );
    }

}
