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
import org.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.tanaguru.rules.elementchecker.attribute.AttributePresenceChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.AttributeStore.LABEL_ATTR;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.OPTGROUP_WITHIN_SELECT_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.RemarkMessageStore.OPTGROUP_WITHOUT_LABEL_MSG;

/**
 * Implementation of the rule 11.8.2 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-11-8-2">the rule 11.8.2 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-11-8-3"> 11.8.2 rule specification</a>
 *
 */
public class Rgaa30Rule110802 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /**
     * Constructor
     */
    public Rgaa30Rule110802() {
        super(
                new SimpleElementSelector(OPTGROUP_WITHIN_SELECT_CSS_LIKE_QUERY), 
                new AttributePresenceChecker(
                    // the attribute to search
                    LABEL_ATTR, 
                    // passed when attribute is found, empty message
                    new ImmutablePair(TestSolution.PASSED, ""),
                    // failed when attribute is not found
                    new ImmutablePair(TestSolution.FAILED, OPTGROUP_WITHOUT_LABEL_MSG)
                )
            );
    }   

}
