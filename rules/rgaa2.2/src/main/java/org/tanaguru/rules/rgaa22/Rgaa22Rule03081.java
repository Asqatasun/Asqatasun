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
import org.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.tanaguru.rules.elementchecker.ElementChecker;
import org.tanaguru.rules.elementchecker.attribute.AttributePresenceChecker;
import org.tanaguru.rules.elementselector.ElementSelector;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.AttributeStore.LABEL_ATTR;
import static org.tanaguru.rules.keystore.HtmlElementStore.OPTGROUP_ELEMENT;
import static org.tanaguru.rules.keystore.RemarkMessageStore.OPTGROUP_WITHOUT_LABEL_MSG;


/**
 * Implementation of the rule 3.8 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-3-8">the rule 3.8 design page.</a>
 * @see <a href="http://rgaa.net/Presence-d-un-attribut-label-sur-l.html"> 3.8 rule specification </a>
 *
 */
public class Rgaa22Rule03081 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /** The element selector */
    private static final ElementSelector ELEMENT_SELECTOR = 
            new SimpleElementSelector(OPTGROUP_ELEMENT);
    
    /** The element checker */
    private static final ElementChecker ELEMENT_CHECKER = 
            new AttributePresenceChecker(
                // the attribute to search
                LABEL_ATTR, 
                // passed when attribute is found
                TestSolution.PASSED, 
                // failed when attribute is not found
                TestSolution.FAILED, 
                // no message created when attribute is found
                null, 
                // message associated with element when attribute is not found
                OPTGROUP_WITHOUT_LABEL_MSG);
    
    /**
     * Constructor
     */
    public Rgaa22Rule03081() {
        super(ELEMENT_SELECTOR, ELEMENT_CHECKER);
    }
    
}