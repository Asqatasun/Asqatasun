/*
 * Tanaguru - Automated webpage assessment
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

package org.tanaguru.rules.accessiweb22;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.tanaguru.rules.elementchecker.attribute.AttributePresenceChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.tanaguru.rules.keystore.AttributeStore.ALT_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.CODE_ATTR;
import static org.tanaguru.rules.keystore.HtmlElementStore.APPLET_ELEMENT;
import static org.tanaguru.rules.keystore.RemarkMessageStore.ALT_MISSING_MSG;

/**
 * Implementation of the rule 1.1.4 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-1-1-4">the rule 1.1.4 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-1-4"> 1.1.4 rule specification</a>
 *
 */
public class Aw22Rule01014 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /**
     * Default constructor
     */
    public Aw22Rule01014() {
        super(
                new SimpleElementSelector(APPLET_ELEMENT), 
                
                new AttributePresenceChecker(
                        ALT_ATTR, 
                        // passed when attribute is found  
                        TestSolution.PASSED,
                        // failed when attribute is not found
                        TestSolution.FAILED, 
                        // no message created when attribute is found
                        null, 
                        // message associated with element when attribute is not found
                        ALT_MISSING_MSG, 
                        // evidence elements
                        CODE_ATTR)
            );
    }

}