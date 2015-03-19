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

package org.opens.tanaguru.rules.rgaa30;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.opens.tanaguru.rules.elementchecker.pertinence.AttributePertinenceChecker;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.SUMMARY_ATTR;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.TABLE_WITH_SUMMARY_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.MarkerStore.DATA_TABLE_MARKER;
import static org.opens.tanaguru.rules.keystore.MarkerStore.PRESENTATION_TABLE_MARKER;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.*;

/**
 * Implementation of the rule 5.2.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-5-2-1">the rule 5.2.1 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-5-2-1"> 5.2.1 rule specification</a>
 *
 */
public class Rgaa30Rule050201 extends AbstractMarkerPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule050201() {
        super(
                new SimpleElementSelector(TABLE_WITH_SUMMARY_CSS_LIKE_QUERY),
            
                // the data tables are part of the scope
                DATA_TABLE_MARKER,

                // the presentation tables are not part of the scope
                PRESENTATION_TABLE_MARKER,

                // checker for elements identified by marker
                new AttributePertinenceChecker(
                    //the attribute to check 
                    SUMMARY_ATTR,
                    // check emptiness
                    true,
                    null,
                    null,
                    // not pertinent message
                    NOT_PERTINENT_SUMMARY_MSG,
                    // manual check message
                    CHECK_SUMMARY_PERTINENCE_FOR_DATA_TABLE_MSG,
                    // evidence elements
                    SUMMARY_ATTR),
                
                // checker for elements not identified by marker
                new AttributePertinenceChecker(
                    //the attribute to check 
                    SUMMARY_ATTR,
                    // no emptiness check 
                    false,
                    null,
                    null,
                    // override not pertinent solution
                    TestSolution.NEED_MORE_INFO, 
                    // not pertinent message
                    CHECK_NATURE_OF_TABLE_FOR_NOT_PERTINENT_SUMMARY_MSG,
                    // manual check message
                    CHECK_NATURE_OF_TABLE_AND_SUMMARY_PERTINENCE_MSG,
                    // evidence elements
                    SUMMARY_ATTR)
            );
    }

}
