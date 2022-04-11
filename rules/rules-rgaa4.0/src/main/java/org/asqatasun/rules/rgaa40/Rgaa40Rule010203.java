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

import static org.asqatasun.rules.keystore.AttributeStore.*;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.OBJECT_TYPE_IMG_WITHOUT_LEGEND_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.OBJECT_WITH_EMPTY_ALTERNATIVE_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.EvidenceStore.COMPUTED_LINK_TITLE;
import static org.asqatasun.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;

/**
 * Implementation of rule 1.2.3 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://doc.asqatasun.org/v5/en/Business-rules/RGAA-v4/01.Images/Rule-1-2-3/">rule 1.2.3 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-2-3">1.2.3 rule specification</a>
 */
public class Rgaa40Rule010203 extends AbstractDecorativeImageEmptyAlternativePageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa40Rule010203() {
        super(
            OBJECT_WITH_EMPTY_ALTERNATIVE_CSS_LIKE_QUERY,
            OBJECT_TYPE_IMG_WITHOUT_LEGEND_CSS_LIKE_QUERY,
            TITLE_ATTR,
            ARIA_LABEL_ATTR,
            COMPUTED_LINK_TITLE,
            TEXT_ELEMENT2,
            DATA_ATTR
        );
    }

}
