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
import org.asqatasun.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.asqatasun.rules.elementselector.AreaElementSelector;
import org.asqatasun.rules.elementselector.CaptchaElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.ALT_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.HREF_ATTR;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_CAPTCHA_ALTERNATIVE_MSG;

/**
 * Implementation of the rule 1.4.2 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/40_Rules/accessiweb2.2/01.Images/Rule-1.4.2.html">the rule 1.4.2 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-4-2"> 1.4.2 rule specification</a>
 *
 */
public class Aw22Rule01042 extends AbstractDetectionPageRuleImplementation {
    
    /**
     * Default constructor
     */
    public Aw22Rule01042 () {
        super(
                new CaptchaElementSelector(
                    new AreaElementSelector(true, false, true)),
                // solution when at least one element is found
                TestSolution.NEED_MORE_INFO,
                // solution when no element is found
                TestSolution.NOT_APPLICABLE,
                // manual check message
                CHECK_CAPTCHA_ALTERNATIVE_MSG,
                null,
                // evidence elements
                ALT_ATTR, 
                HREF_ATTR
            );
    }

}