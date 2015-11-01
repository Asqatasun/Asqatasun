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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.tanaguru.rules.accessiweb22;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractMarkerPageRuleImplementation;
import org.tanaguru.rules.elementchecker.text.TextEmptinessChecker;
import org.tanaguru.rules.elementselector.AreaElementSelector;
import org.tanaguru.rules.elementselector.ImageElementSelector;
import static org.tanaguru.rules.keystore.AttributeStore.ALT_ATTR;
import static org.tanaguru.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.MarkerStore.INFORMATIVE_IMAGE_MARKER;
import static org.tanaguru.rules.keystore.RemarkMessageStore.*;
import org.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 1.2.2 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-1-2-2">the rule 1.2.2 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-2-2"> 1.2.2 rule specification</a>
 *
 */
public class Aw22Rule01022 extends AbstractMarkerPageRuleImplementation {
    
    /**
     * Default constructor
     */
    public Aw22Rule01022() {
        super(
                new ImageElementSelector(new AreaElementSelector(true, true, false), true, false), 
                
                // the decorative images are part of the scope
                DECORATIVE_IMAGE_MARKER, 
                
                // the informative images are not part of the scope
                INFORMATIVE_IMAGE_MARKER, 
                
                // checker for elements identified by marker
                new TextEmptinessChecker(
                    new TextAttributeOfElementBuilder(ALT_ATTR), 
                    // solution when attribute is empty
                    TestSolution.PASSED, 
                    // solution when attribute is not empty
                    TestSolution.FAILED, 
                    null, // no message created when a decorative with empty alt is found
                    DECORATIVE_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, 
                    ALT_ATTR),
                
                // checker for elements not identified by marker
                new TextEmptinessChecker(
                    new TextAttributeOfElementBuilder(ALT_ATTR), 
                    TestSolution.NEED_MORE_INFO, 
                    TestSolution.NEED_MORE_INFO, 
                    CHECK_ELEMENT_WITH_EMPTY_ALT_MSG, 
                    CHECK_ELEMENT_WITH_NOT_EMPTY_ALT_MSG, 
                    ALT_ATTR)
            );
    }
    
}