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

package org.tanaguru.rules.rgaa22;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.tanaguru.rules.elementselector.ElementSelector;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.HtmlElementStore.H1_ELEMENT;
import static org.tanaguru.rules.keystore.RemarkMessageStore.H1_TAG_MISSING_MSG;

/**
 * Implementation of the rule 10.1 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-10-1">the rule 10.1 design page.</a>
 * @see <a href="http://rgaa.net/Presence-d-au-moins-un-titre-de.html"> 10.1 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule10011 extends AbstractDetectionPageRuleImplementation {

    /** The element selector */
    private static final ElementSelector ELEMENT_SELECTOR = 
            new SimpleElementSelector(H1_ELEMENT);
    
    /**
     * Default constructor
     */
    public Rgaa22Rule10011 () {
        super(
                ELEMENT_SELECTOR,
                // solution when at least one element is found
                TestSolution.PASSED,
                // solution when no element is found
                TestSolution.FAILED,
                // detected tag message
                null,
                // manual check message
                H1_TAG_MISSING_MSG
            );
    }

}