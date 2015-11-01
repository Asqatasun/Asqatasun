/*
 * Tanaguru - Automated webpage assessment
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

package org.tanaguru.rules.accessiweb22;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.HtmlElementStore.TABLE_ELEMENT;
import static org.tanaguru.rules.keystore.MarkerStore.DATA_TABLE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.PRESENTATION_TABLE_MARKER;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_HEADERS_USAGE_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_USAGE_OF_HEADERS_FOR_DATA_TABLE_MSG;

/**
 * Implementation of the rule 5.6.1 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-5-6-1">the rule 5.6.1 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-5-6-1"> 5.6.1 rule specification</a>
 *
 */
public class Aw22Rule05061 extends AbstractMarkerPageRuleImplementation {

    /**
     * Default constructor
     */
    public Aw22Rule05061() {
        super(
                new SimpleElementSelector(TABLE_ELEMENT),

                // the data tables are part of the scope
                DATA_TABLE_MARKER,

                // the presentation tables are not part of the scope
                PRESENTATION_TABLE_MARKER,

                // checker for elements identified by marker
                new ElementPresenceChecker(
                    // nmi when element is found
                    TestSolution.NEED_MORE_INFO, 
                    // na when element is not found
                    TestSolution.NOT_APPLICABLE, 
                    // message associated with each found element
                    CHECK_USAGE_OF_HEADERS_FOR_DATA_TABLE_MSG,
                    null),
                
                // checker for elements not identified by marker
                new ElementPresenceChecker(
                    // nmi when element is found
                    TestSolution.NEED_MORE_INFO, 
                    // na when element is not found
                    TestSolution.NOT_APPLICABLE, 
                    // message associated with each found element
                    CHECK_NATURE_OF_TABLE_AND_HEADERS_USAGE_MSG,
                    null)
            );
    }

}