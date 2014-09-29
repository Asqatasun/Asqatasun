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
import org.opens.tanaguru.rules.elementchecker.element.ChildElementPresenceChecker;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.TABLE_ELEMENT;
import static org.opens.tanaguru.rules.keystore.MarkerStore.DATA_TABLE_MARKER;
import static org.opens.tanaguru.rules.keystore.MarkerStore.PRESENTATION_TABLE_MARKER;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.*;

/**
 * Implementation of the rule 5.4.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-5-4-1">the rule 5.4.1 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-html5aria-liste-deployee.html#test-5-4-1"> 5.4.1 rule specification</a>
 *
 */
public class Rgaa30Rule050401 extends AbstractMarkerPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule050401() {
        super(
                new SimpleElementSelector(TABLE_ELEMENT), 

                // the data tables are part of the scope
                DATA_TABLE_MARKER,

                // the presentation tables are not part of the scope
                PRESENTATION_TABLE_MARKER,

                // checker for elements identified by marker
                new ChildElementPresenceChecker(
                    HtmlElementStore.CAPTION_ELEMENT, 
                    1,
                    // passed when child element is found
                    TestSolution.PASSED, 
                    // failed when child element is not found
                    TestSolution.FAILED, 
                    // no message created when child element is found
                    null, 
                    // message associated with element when child element is not found
                    CAPTION_MISSING_MSG),
                
                // checker for elements not identified by marker
                new ChildElementPresenceChecker(
                    HtmlElementStore.CAPTION_ELEMENT, 
                    // the child element is supposed to appear at least once
                    1,
                    // nmi when attribute is found
                    TestSolution.NEED_MORE_INFO, 
                    // nmi when attribute is not found
                    TestSolution.NEED_MORE_INFO, 
                    // message associated with element when child element is found
                    CHECK_NATURE_OF_TABLE_WITH_CAPTION_MSG, 
                    // message associated with element when child element is not found
                    CHECK_NATURE_OF_TABLE_WITHOUT_CAPTION_MSG)
            );
    }

}
