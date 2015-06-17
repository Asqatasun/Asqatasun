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

package org.tanaguru.rules.rgaa22;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.tanaguru.rules.elementchecker.attribute.AttributePresenceChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.AttributeStore.SUMMARY_ATTR;
import static org.tanaguru.rules.keystore.HtmlElementStore.TABLE_ELEMENT;
import static org.tanaguru.rules.keystore.MarkerStore.DATA_TABLE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.PRESENTATION_TABLE_MARKER;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_TABLE_WITHOUT_SUMMARY_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_TABLE_WITH_SUMMARY_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.SUMMARY_MISSING_MSG;

/**
 * Implementation of the rule 11.8 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-11-8">the rule 11.8 design page.</a>
 * @see <a href="http://rgaa.net/Presence-d-un-resume-pour-les.html"> 11.8 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule11081 extends AbstractMarkerPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa22Rule11081 () {
        super(
                new SimpleElementSelector(TABLE_ELEMENT), 

                // the data tables are part of the scope
                DATA_TABLE_MARKER,

                // the presentation tables are not part of the scope
                PRESENTATION_TABLE_MARKER,

                // checker for elements identified by marker
                new AttributePresenceChecker(
                    SUMMARY_ATTR, 
                    // passed when attribute is found
                    TestSolution.PASSED, 
                    // failed when attribute is not found
                    TestSolution.FAILED, 
                    // no message created when attribute is found
                    null, 
                    // message associated with element when attribute is not found
                    SUMMARY_MISSING_MSG),

                // checker for elements not identified by marker
                new AttributePresenceChecker(
                    SUMMARY_ATTR, 
                    // nmi when attribute is found
                    TestSolution.NEED_MORE_INFO, 
                    // nmi when attribute is not found
                    TestSolution.NEED_MORE_INFO, 
                    // message associated with element when attribute is found
                    CHECK_NATURE_OF_TABLE_WITH_SUMMARY_MSG, 
                    // message associated with element when attribute is not found
                    CHECK_NATURE_OF_TABLE_WITHOUT_SUMMARY_MSG)
            );
    }

}