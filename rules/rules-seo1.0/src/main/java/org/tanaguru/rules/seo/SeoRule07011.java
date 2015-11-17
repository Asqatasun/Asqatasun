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
package org.asqatasun.rules.seo;


import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.HtmlElementStore.H1_ELEMENT;
import static org.asqatasun.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.asqatasun.rules.keystore.RemarkMessageStore.H1_TAG_MISSING_MSG;

/**
 * This rule tests if each page has at least one <h1> tag
 * @author jkowalczyk
 */
public class SeoRule07011 extends AbstractPageRuleWithSelectorAndCheckerImplementation {
    
    /**
     * Default constructor
     */
    public SeoRule07011(){
        super(
                new SimpleElementSelector(H1_ELEMENT), 
                
                new ElementPresenceChecker(
                    // check element has to be unique
                    false,
                    // solution when detected
                    TestSolution.PASSED,
                    // solution when not detected
                    TestSolution.FAILED,
                    // no message on detection
                    null,
                    // message when not detected
                    H1_TAG_MISSING_MSG, 
                    // evidence elements
                    TEXT_ELEMENT2)
            );
    }

}