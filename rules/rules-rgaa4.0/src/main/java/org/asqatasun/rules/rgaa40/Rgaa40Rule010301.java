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

import org.asqatasun.rules.elementselector.ElementWithAccessibleNameSelector;
import org.asqatasun.rules.elementselector.ImageElementSelector;

import static org.asqatasun.rules.keystore.AttributeStore.*;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.BASIC_IMG_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.EvidenceStore.COMPUTED_LINK_TITLE;

/**
 * Implementation of rule 1.3.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/01.Images/Rule-1-3-1.md">rule 1.3.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-3-1">1.3.1 rule specification</a>
 */
public class Rgaa40Rule010301 extends AbstractInformativeImagePertinenceAlternativePageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa40Rule010301() {
        super(
            new ElementWithAccessibleNameSelector(new ImageElementSelector(BASIC_IMG_CSS_LIKE_QUERY, true, true)),
            true,
            ALT_ATTR,
            TITLE_ATTR,
            ARIA_LABEL_ATTR,
            COMPUTED_LINK_TITLE,
            SRC_ATTR);
    }

}
