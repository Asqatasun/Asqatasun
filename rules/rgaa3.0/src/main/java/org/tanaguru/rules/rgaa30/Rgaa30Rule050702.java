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
import org.tanaguru.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.TABLE_WITH_TH_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.MarkerStore.COMPLEX_TABLE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.DATA_TABLE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.PRESENTATION_TABLE_MARKER;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_DEFINITION_OF_HEADERS_FOR_DATA_TABLE_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_HEADERS_DEFINITION_MSG;

/**
 * Implementation of the rule 5.7.2 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-5-7-2">the rule 5.7.2 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-5-7-2"> 5.7.2 rule specification</a>
 *
 */
public class Rgaa30Rule050702 extends AbstractMarkerPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule050702() {
        super(
                new SimpleElementSelector(TABLE_WITH_TH_CSS_LIKE_QUERY),

                // the data and complex tables are part of the scope
                new String[]{DATA_TABLE_MARKER, COMPLEX_TABLE_MARKER},

                // the presentation tables are not part of the scope
                new String[]{PRESENTATION_TABLE_MARKER},

                // checker for elements identified by marker
                new ElementPresenceChecker(
                    // nmi when element is found
                    new ImmutablePair(TestSolution.NEED_MORE_INFO, CHECK_DEFINITION_OF_HEADERS_FOR_DATA_TABLE_MSG),
                    // na when element is not found
                    new ImmutablePair(TestSolution.NOT_APPLICABLE, "")
                ), 
                
                // checker for elements not identified by marker
                new ElementPresenceChecker(
                    // nmi when element is found
                    new ImmutablePair(TestSolution.NEED_MORE_INFO, CHECK_NATURE_OF_TABLE_AND_HEADERS_DEFINITION_MSG),
                    // na when element is not found
                    new ImmutablePair(TestSolution.NOT_APPLICABLE, "")
                )
            );
    }
    
}
