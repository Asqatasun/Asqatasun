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
import org.asqatasun.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.asqatasun.rules.elementselector.ElementSelector;
import org.asqatasun.rules.elementselector.MultipleElementSelector;
import static org.asqatasun.rules.keystore.HtmlElementStore.APPLET_ELEMENT;
import static org.asqatasun.rules.keystore.HtmlElementStore.EMBED_ELEMENT;
import static org.asqatasun.rules.keystore.HtmlElementStore.OBJECT_ELEMENT;
import static org.asqatasun.rules.keystore.RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG;

/**
 * Implementation of the rule 5.27 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.old-dot-org.org/en/content/rgaa22-rule-5-27">the rule 5.27 design page.</a>
 * @see <a href="http://rgaa.net/Independance-du-peripherique-d.html"> 5.27 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule05271 extends AbstractDetectionPageRuleImplementation {
    /** The element selector */
    private static final ElementSelector ELEMENT_SELECTOR = 
            new MultipleElementSelector(APPLET_ELEMENT, OBJECT_ELEMENT, EMBED_ELEMENT);
    
    /**
     * Default constructor
     */
    public Rgaa22Rule05271 () {
        super(
                ELEMENT_SELECTOR,
                // solution when at least one element is found
                TestSolution.NEED_MORE_INFO,
                // solution when no element is found
                TestSolution.NOT_APPLICABLE,
                // detected tag message
                MANUAL_CHECK_ON_ELEMENTS_MSG,
                null
            );
    }

}