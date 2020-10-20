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
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.asqatasun.rules.elementselector.SimpleElementSelector;

import static org.asqatasun.rules.keystore.AttributeStore.ALT_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.SRC_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.IMG_WITH_ISMAP_ATTR_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_LINK_ASSO_WITH_SERVER_SIDED_IMG_MAP;

/**
 * Implementation of rule 1.1.4 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/01.Images/Rule-1-1-4.md">rule 1.1.4 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-1-1-4">1.1.4 rule specification</a>
 */
public class Rgaa40Rule010104 extends AbstractDetectionPageRuleImplementation {

    public Rgaa40Rule010104 () {
        super(
            new SimpleElementSelector(IMG_WITH_ISMAP_ATTR_CSS_LIKE_QUERY),
            // solution when at least one element is found
            new ImmutablePair(TestSolution.NEED_MORE_INFO,CHECK_LINK_ASSO_WITH_SERVER_SIDED_IMG_MAP),
            // solution when no element is found
            new ImmutablePair(TestSolution.NOT_APPLICABLE,""),
            // evidence elements
            ALT_ATTR,
            SRC_ATTR
        );
    }

}
