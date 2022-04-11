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

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.asqatasun.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.asqatasun.rules.elementchecker.element.AccessibleNamePresenceChecker;
import org.asqatasun.rules.elementselector.ImageElementSelector;
import org.asqatasun.rules.elementselector.SimpleElementSelector;

import static org.asqatasun.entity.audit.TestSolution.*;
import static org.asqatasun.rules.keystore.AttributeStore.ARIA_LABEL_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.ROLE_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.SVG_NOT_IN_LINK_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.EvidenceStore.COMPUTED_LINK_TITLE;
import static org.asqatasun.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.*;

/**
 * Implementation of rule 1.1.5 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://doc.asqatasun.org/v5/en/Business-rules/RGAA-v4/01.Images/Rule-1-1-5/">rule 1.1.5 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-1-5">1.1.5 rule specification</a>
 */
public class Rgaa40Rule010105 extends AbstractMarkerPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa40Rule010105() {
            super(new ImageElementSelector(new SimpleElementSelector(SVG_NOT_IN_LINK_CSS_LIKE_QUERY), true, true),
                // the informative images are part of the scope
                INFORMATIVE_IMAGE_MARKER,
                // the decorative images are not part of the scope
                DECORATIVE_IMAGE_MARKER,
                new AccessibleNamePresenceChecker(
                    new ImmutablePair<>(PASSED, null),
                    new ImmutablePair<>(FAILED, ALT_MISSING_MSG),
                    INFORMATIVE_SVG_WITHOUT_ROLE_IMG_ATTRIBUTE,
                    new String[]{ROLE_ATTR, ARIA_LABEL_ATTR, COMPUTED_LINK_TITLE}),
                new AccessibleNamePresenceChecker(
                    new ImmutablePair<>(NEED_MORE_INFO, CHECK_NATURE_OF_IMAGE_WITH_TEXTUAL_ALTERNATIVE_MSG),
                    new ImmutablePair<>(NEED_MORE_INFO, CHECK_NATURE_OF_IMAGE_WITHOUT_TEXTUAL_ALTERNATIVE_MSG),
                    CHECK_NATURE_OF_IMAGE_WITHOUT_ROLE_IMG_ATTRIBUTE,
                    new String[]{ROLE_ATTR, ARIA_LABEL_ATTR, COMPUTED_LINK_TITLE})
            );

    }

}
