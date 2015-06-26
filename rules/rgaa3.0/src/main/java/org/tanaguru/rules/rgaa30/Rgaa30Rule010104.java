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
package org.tanaguru.rules.rgaa30;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.AttributeStore.ALT_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.SRC_ATTR;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.IMG_WITH_ISMAP_ATTR_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_LINK_ASSO_WITH_SERVER_SIDED_IMG_MAP;

/**
 * Implementation of the rule 1-1-4 of the referential Rgaa 3.0.
 *
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-1-1-4">the rule 1.1.4 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-1-4"> 1.1.4 rule specification</a>
 */

public class Rgaa30Rule010104 extends AbstractDetectionPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule010104 () {
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