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
package org.asqatasun.rules.rgaa32016;

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.ELEMENT_WITH_DIR_ATTR_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG;

/**
 * Implementation of the rule 8.10.2 of the referential RGAA 3.2016
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.2016/08.Mandatory_elements/Rule-8-10-2.html">the rule 8.10.2 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/2016/criteres.html#test-8-10-2">8.10.2 rule specification</a>
 *
 * @author
 */
public class Rgaa32016Rule081002 extends AbstractDetectionPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa32016Rule081002 () {
        super(
                new SimpleElementSelector(ELEMENT_WITH_DIR_ATTR_CSS_LIKE_QUERY),// the selector implementation that performs the selection
                TestSolution.NEED_MORE_INFO,  // solution when at least one element is found
                TestSolution.NOT_APPLICABLE,  // solution when no element is found
                MANUAL_CHECK_ON_ELEMENTS_MSG, // message associated with each detected element
                null                          // no message created when no element is found because NA doesn't produce message
        );
       // setElementSelector(new SimpleElementSelector(ELEMENT_WITH_DIR_ATTR_CSS_LIKE_QUERY));
    }

}
