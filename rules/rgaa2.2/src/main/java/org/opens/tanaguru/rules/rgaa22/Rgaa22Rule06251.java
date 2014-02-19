/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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

package org.opens.tanaguru.rules.rgaa22;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.opens.tanaguru.rules.elementselector.MultipleElementSelector;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.EMBED_ELEMENT;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.OBJECT_ELEMENT;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_USER_IS_WARNED_IN_CASE_OF_NEW_WINDOW_MSG;

/**
 * Implementation of the rule 6.25 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-6-25">the rule 6.25 design page.</a>
 * @see <a href="http://rgaa.net/Presence-d-un-avertissement,104.html"> 6.25 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule06251 extends AbstractDetectionPageRuleImplementation {
    
    /**
     * Default constructor
     */
    public Rgaa22Rule06251 () {
               super(
                new MultipleElementSelector(OBJECT_ELEMENT, EMBED_ELEMENT),
                // solution when at least one element is found
                TestSolution.NEED_MORE_INFO,
                // solution when no element is found
                TestSolution.NOT_APPLICABLE,
                // manual check message
                CHECK_USER_IS_WARNED_IN_CASE_OF_NEW_WINDOW_MSG,
                null 
            );
    }

}