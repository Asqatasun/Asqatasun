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

import org.asqatasun.ruleimplementation.link.AbstractLinkRuleRgaav4Implementation;
import org.asqatasun.rules.elementselector.LinkElementSelector;
import org.asqatasun.rules.textbuilder.AccessibleNameElementBuilder;
import org.asqatasun.rules.textbuilder.TextElementBuilder;

import static org.asqatasun.rules.keystore.CssLikeQueryStore.TEXT_LINK_RGAAv4_CSS_LIKE_QUERY;


/**
 * Implementation of rule 6.1.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://doc.asqatasun.org/v5/en/Business-rules/RGAA-v4/06.Links/Rule-6-1-1/">rule 6.1.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-6-1-1">6.1.1 rule specification</a>
 */
public class Rgaa40Rule060101 extends AbstractLinkRuleRgaav4Implementation {

    /**
     * Default constructor
     */
    public Rgaa40Rule060101() {
        super(new LinkElementSelector(
            true,
            false,
            false,
            TEXT_LINK_RGAAv4_CSS_LIKE_QUERY,
            new AccessibleNameElementBuilder())
        );
    }

}
