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
 * Implementation of the rule 5.1.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-5-1-1">the rule 5.1.1 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-5-1-1"> 5.1.1 rule specification</a>
 *
 */
public class Rgaa30Rule050101 extends AbstractNotTestedRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule050101 () {
        super();
    }
//public class Rgaa30Rule050101 extends AbstractMarkerPageRuleImplementation {
//
//    /**
//     * Default constructor
//     */
//    public Rgaa30Rule050101() {
//        super(
//                new SimpleElementSelector(TABLE_ELEMENT), 
//
//                // the data tables are part of the scope
//                DATA_TABLE_MARKER,
//
//                // the presentation tables are not part of the scope
//                PRESENTATION_TABLE_MARKER,
//
//                // checker for elements identified by marker
//                new AttributePresenceChecker(
//                    SUMMARY_ATTR, 
//                    // passed when attribute is found
//                    TestSolution.PASSED, 
//                    // failed when attribute is not found
//                    TestSolution.FAILED, 
//                    // no message created when attribute is found
//                    null, 
//                    // message associated with element when attribute is not found
//                    SUMMARY_MISSING_MSG),
//
//                // checker for elements not identified by marker
//                new AttributePresenceChecker(
//                    SUMMARY_ATTR, 
//                    // nmi when attribute is found
//                    TestSolution.NEED_MORE_INFO, 
//                    // nmi when attribute is not found
//                    TestSolution.NEED_MORE_INFO, 
//                    // message associated with element when attribute is found
//                    CHECK_NATURE_OF_TABLE_WITH_SUMMARY_MSG, 
//                    // message associated with element when attribute is not found
//                    CHECK_NATURE_OF_TABLE_WITHOUT_SUMMARY_MSG)
//            );
//    }

}