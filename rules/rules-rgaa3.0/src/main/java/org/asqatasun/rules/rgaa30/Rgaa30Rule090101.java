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
package org.asqatasun.rules.rgaa30;

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.HtmlElementStore.H1_ELEMENT;
import static org.asqatasun.rules.keystore.RemarkMessageStore.H1_TAG_MISSING_MSG;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.ARIA_LEVEL1_HEADINGS_CSS_LIKE_QUERY;

/**
 * Implementation of the rule 9.1.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.0/09.Structure_of_information/Rule-9-1-1.html">the rule 9.1.1 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-9-1-1"> 9.1.1 rule specification</a>
 *
 */
public class Rgaa30Rule090101 extends AbstractDetectionPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule090101 () {
        super(
                new SimpleElementSelector(H1_ELEMENT + ", " + ARIA_LEVEL1_HEADINGS_CSS_LIKE_QUERY),
                // solution when at least one element is found
                TestSolution.PASSED,
                // solution when no element is found
                TestSolution.FAILED,
                null,
                // manual check message
                H1_TAG_MISSING_MSG
            );
    }

}
