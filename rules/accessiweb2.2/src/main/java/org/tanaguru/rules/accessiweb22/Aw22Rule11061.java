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
package org.tanaguru.rules.accessiweb22;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.tanaguru.rules.elementchecker.element.ChildElementPresenceChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.HtmlElementStore.FIELDSET_ELEMENT;
import static org.tanaguru.rules.keystore.HtmlElementStore.LEGEND_ELEMENT;
import static org.tanaguru.rules.keystore.RemarkMessageStore.FIELDSET_WITHOUT_LEGEND_MSG;

/**
 * Implementation of the rule 11.6.1 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-11-6-1">the rule 11.6.1 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-11-7-1"> 11.6.1 rule specification</a>
 *
 */
public class Aw22Rule11061 extends AbstractPageRuleWithSelectorAndCheckerImplementation {
    
    /**
     * Constructor
     */
    public Aw22Rule11061() {
        super(
                new SimpleElementSelector(FIELDSET_ELEMENT), 
                
                new ChildElementPresenceChecker(
                    LEGEND_ELEMENT, 
                    // one legend element is expected
                    Integer.valueOf(1),
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