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
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.element.ChildElementPresenceChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.FIELDSET_ELEMENT;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.FIELDSET_WITHOUT_LEGEND_MSG;

/**
 * Implementation of the rule 3.5 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-3-5">the rule 3.5 design page.</a>
 * @see <a href="http://rgaa.net/Absence-d-element-fieldset-sans.html"> 3.5 rule specification
 *
 */
public class Rgaa22Rule03051 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /** The element selector */
    private static final ElementSelector ELEMENT_SELECTOR = 
            new SimpleElementSelector(FIELDSET_ELEMENT);
    
    /** The element checker */
    private static final ElementChecker ELEMENT_CHECKER = 
            new ChildElementPresenceChecker(
                // the element to search
                HtmlElementStore.LEGEND_ELEMENT, 
                // one legend element is expected
                Integer.valueOf(1),
                // passed when a child element is found
                TestSolution.PASSED, 
                // failed when no child element is found
                TestSolution.FAILED, 
                // no message when child element is found
                null, 
                // message associated with each element without child element
                FIELDSET_WITHOUT_LEGEND_MSG); 
    
    /**
     * Constructor
     */
    public Rgaa22Rule03051() {
        super(ELEMENT_SELECTOR, ELEMENT_CHECKER);
    }
    
}