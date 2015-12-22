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

package org.asqatasun.rules.rgaa30;

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.asqatasun.rules.elementselector.CaptchaElementSelector;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.FORM_BUTTON_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_CAPTCHA_ALTERNATIVE_ACCES_MSG;

/**
 * Implementation of the rule 1.5.2 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.0/01.Images/Rule-1-5-2.html">the rule 1.5.2 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-1-5-2"> 1.5.2 rule specification</a>
 *
 */
public class Rgaa30Rule010502 extends AbstractDetectionPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule010502 () {
        super(
                new CaptchaElementSelector(
                    new SimpleElementSelector(FORM_BUTTON_CSS_LIKE_QUERY)),
                // solution when at least one element is found
                TestSolution.NEED_MORE_INFO,
                // solution when no element is found
                TestSolution.NOT_APPLICABLE,
                // manual check message
                CHECK_CAPTCHA_ALTERNATIVE_ACCES_MSG,
                null
            );
    }

}
