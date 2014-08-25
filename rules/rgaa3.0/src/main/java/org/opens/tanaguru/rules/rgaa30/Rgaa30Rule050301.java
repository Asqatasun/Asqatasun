/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.rules.rgaa30;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.opens.tanaguru.rules.elementchecker.CompositeChecker;
import org.opens.tanaguru.rules.elementchecker.attribute.AttributeWithValuePresenceChecker;
import org.opens.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.PRESENTATION_VALUE;
import static org.opens.tanaguru.rules.keystore.AttributeStore.ROLE_ATTR;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.TABLE_ELEMENT;
import static org.opens.tanaguru.rules.keystore.MarkerStore.DATA_TABLE_MARKER;
import static org.opens.tanaguru.rules.keystore.MarkerStore.PRESENTATION_TABLE_MARKER;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_LINEARISED_CONTENT_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_LINEARISED_CONTENT_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.PRESENTATION_TABLE_WITHOUT_ARIA_MARKUP_MSG;

/**
 * Implementation of the rule 5.3.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a
 * href="http://www.tanaguru.org/en/content/aw22-rule-5-3-1">the rule 5.3.1
 * design page.</a>
 *
 * @see <a
 * href="http://www.accessiweb.org/index.php/accessiweb-html5aria-liste-deployee.html#test-5-3-1">
 * 5.3.1 rule specification</a>
 *
 */
public class Rgaa30Rule050301 extends AbstractMarkerPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule050301() {
        super(
                new SimpleElementSelector(TABLE_ELEMENT),
                // the presentation tables are part of the scope
                PRESENTATION_TABLE_MARKER,
                // the data tables are not part of the scope
                DATA_TABLE_MARKER,
                // checker for elements identified by marker
                new CompositeChecker(
                        new ElementPresenceChecker(
                                // nmi when element is found
                                TestSolution.NEED_MORE_INFO,
                                // na when element is not found
                                TestSolution.NOT_APPLICABLE,
                                // message associated with each found element
                                CHECK_LINEARISED_CONTENT_MSG,
                                null),
                        new AttributeWithValuePresenceChecker(
                                ROLE_ATTR,
                                PRESENTATION_VALUE,
                                TestSolution.NEED_MORE_INFO,
                                TestSolution.FAILED,
                                // null beacause the CHECK_LINEARISED_CONTENT_MSG
                                // is already use above in this case.
                                null,
                                PRESENTATION_TABLE_WITHOUT_ARIA_MARKUP_MSG)
                ),
                // checker for elements not identified by marker
                new CompositeChecker(
                        new ElementPresenceChecker(
                                // nmi when element is found
                                TestSolution.NEED_MORE_INFO,
                                // na when element is not found
                                TestSolution.NOT_APPLICABLE,
                                CHECK_NATURE_OF_TABLE_AND_LINEARISED_CONTENT_MSG,
                                // message associated with each found element
                                null),
                        new AttributeWithValuePresenceChecker(
                                ROLE_ATTR,
                                PRESENTATION_VALUE,
                                TestSolution.NEED_MORE_INFO,
                                TestSolution.NEED_MORE_INFO,
                                CHECK_LINEARISED_CONTENT_MSG,
                                null)
                )
        );
    }

}
