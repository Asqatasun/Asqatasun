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

package org.asqatasun.rules.rgaa22;

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.asqatasun.rules.elementchecker.element.ChildElementPresenceChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import org.asqatasun.rules.keystore.HtmlElementStore;
import static org.asqatasun.rules.keystore.HtmlElementStore.TABLE_ELEMENT;
import static org.asqatasun.rules.keystore.MarkerStore.DATA_TABLE_MARKER;
import static org.asqatasun.rules.keystore.MarkerStore.PRESENTATION_TABLE_MARKER;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CAPTION_MISSING_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_TABLE_WITHOUT_CAPTION_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_TABLE_WITH_CAPTION_MSG;

/**
 * Implementation of the rule 11.7 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-11-7">the rule 11.7 design page.</a>
 * @see <a href="http://rgaa.net/Presence-d-un-titre-pour-les.html"> 11.7 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule11071 extends AbstractMarkerPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa22Rule11071 () {
        super(
                new SimpleElementSelector(TABLE_ELEMENT), 

                // the data tables are part of the scope
                DATA_TABLE_MARKER,

                // the presentation tables are not part of the scope
                PRESENTATION_TABLE_MARKER,

                // checker for elements identified by marker
                new ChildElementPresenceChecker(
                    HtmlElementStore.CAPTION_ELEMENT, 
                    // the child element is supposed to appear at least once
                    Integer.valueOf(1),
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
                    Integer.valueOf(1),
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