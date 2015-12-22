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
package org.asqatasun.rules.accessiweb22;

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.asqatasun.rules.elementchecker.element.ChildElementPresenceChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.HtmlElementStore.FIELDSET_ELEMENT;
import static org.asqatasun.rules.keystore.HtmlElementStore.LEGEND_ELEMENT;
import static org.asqatasun.rules.keystore.RemarkMessageStore.FIELDSET_WITHOUT_LEGEND_MSG;

/**
 * Implementation of the rule 11.6.1 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/accessiweb2.2/11.Forms/Rule-11.6.1.html">the rule 11.6.1 design page.</a>
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