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

import org.opens.tanaguru.ruleimplementation.AbstractNotTestedRuleImplementation;

/**
 * Implementation of the rule 1.7.4 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="https://github.com/Tanaguru/Tanaguru-rules-RGAA-3-doc/wiki/Rule-1-7-4">the rule 1.7.4 design page.</a>
 * @see <a href="https://references.modernisation.gouv.fr/sites/default/files/RGAA3/referentiel_technique.htm#test-1-7-4"> 1.7.4 rule specification</a>
 *
 */
public class Rgaa30Rule010704 extends AbstractNotTestedRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule010704 () {
        super();
    } 

//extends AbstractMarkerPageRuleImplementation {
//
//    /**
//     * Default constructor
//     */
//    public Rgaa30Rule010704() {
//        super(
//                new ImageElementSelector(APPLET_NOT_IN_LINK_CSS_LIKE_QUERY),
//
//                // the informative images are part of the scope
//                INFORMATIVE_IMAGE_MARKER, 
//
//                // the decorative images are not part of the scope
//                DECORATIVE_IMAGE_MARKER, 
//                
//                // checker for elements identified by marker
//                new ElementPresenceChecker(
//                    // solution when at least one element is found
//                    TestSolution.NEED_MORE_INFO,
//                    // solution when no element is found
//                    TestSolution.NOT_APPLICABLE,
//                    // manual check message
//                    CHECK_DESC_PERTINENCE_OF_INFORMATIVE_IMG_MSG,
//                    null, 
//                    // evidence elements
//                    ALT_ATTR, 
//                    TEXT_ELEMENT2,
//                    CODE_ATTR), 
//                
//                // checker for elements not identified by marker
//                new ElementPresenceChecker(
//                    // solution when at least one element is found
//                    TestSolution.NEED_MORE_INFO,
//                    // solution when no element is found
//                    TestSolution.NOT_APPLICABLE,
//                    // manual check message
//                    CHECK_NATURE_OF_IMAGE_AND_DESC_PERTINENCE_MSG,
//                    null, 
//                    // evidence elements
//                    ALT_ATTR, 
//                    TEXT_ELEMENT2,
//                    CODE_ATTR)
//            );
//    }

}