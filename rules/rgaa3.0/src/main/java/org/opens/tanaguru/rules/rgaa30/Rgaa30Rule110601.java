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
package org.opens.tanaguru.rules.rgaa30;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.opens.tanaguru.rules.elementchecker.element.ChildElementPresenceChecker;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.FIELDSET_ELEMENT;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.LEGEND_ELEMENT;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.FIELDSET_WITHOUT_LEGEND_MSG;

/**
 * Implementation of the rule 11.6.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-11-6-1">the rule 11.6.1 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/sites/default/files/RGAA3_RC2-1/referentiel_technique.htm#test-11-7-1"> 11.6.1 rule specification</a>
 *
 */
public class Rgaa30Rule110601 extends AbstractPageRuleWithSelectorAndCheckerImplementation {
    
    /**
     * Constructor
     */
    public Rgaa30Rule110601() {
        super(
                new SimpleElementSelector(FIELDSET_ELEMENT), 
                
                new ChildElementPresenceChecker(
                    LEGEND_ELEMENT, 
                    1,
                    // passed when a child element is found
                    TestSolution.PASSED,    
                    // failed when no child element is found
                    TestSolution.FAILED, 
                    // no message when child element is found
                    null, 
                    // message associated with each element without child element
                    FIELDSET_WITHOUT_LEGEND_MSG)
        );
    }
    
}
