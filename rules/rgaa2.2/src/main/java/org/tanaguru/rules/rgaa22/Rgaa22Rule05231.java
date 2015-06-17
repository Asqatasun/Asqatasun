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
import org.tanaguru.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.tanaguru.rules.elementselector.ElementSelector;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.HtmlElementStore.MARQUEE_ELEMENT;
import static org.tanaguru.rules.keystore.RemarkMessageStore.DETECTED_TAG_MSG;

/**
 * Implementation of the rule 5.23 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-5-23">the rule 5.23 design page.</a>
 * @see <a href="http://rgaa.net/Absence-d-element-marquee.html"> 5.23 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule05231 extends AbstractDetectionPageRuleImplementation {

    /** The element selector */
    private static final ElementSelector ELEMENT_SELECTOR = 
            new SimpleElementSelector(MARQUEE_ELEMENT);
    
    /**
     * Default constructor
     */
    public Rgaa22Rule05231 () {
        super(
                ELEMENT_SELECTOR,
                // solution when at least one element is found
                TestSolution.FAILED,
                // solution when no element is found
                TestSolution.PASSED,
                // detected tag message
                DETECTED_TAG_MSG,
                null
            );
    }

}