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

package org.tanaguru.rules.rgaa22;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.tanaguru.rules.elementselector.CaptchaElementSelector;
import org.tanaguru.rules.elementselector.MultipleElementSelector;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.*;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_CAPTCHA_ALTERNATIVE_ACCES_MSG;

/**
 * Implementation of the rule 4.10 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-4-10">the rule 4.10 design page.</a>
 * @see <a href="http://rgaa.net/Presence-d-une-information-de.html"> 4.10 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule04101 extends AbstractDetectionPageRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa22Rule04101 () {
        super(
                new CaptchaElementSelector(
                        new MultipleElementSelector(
                                IMG_NOT_IN_LINK_CSS_LIKE_QUERY,
                                AREA_NOT_IN_LINK_CSS_LIKE_QUERY,
                                APPLET_NOT_IN_LINK_CSS_LIKE_QUERY,
                                OBJECT_TYPE_IMG_NOT_IN_LINK_CSS_LIKE_QUERY,
                                EMBED_TYPE_IMG_NOT_IN_LINK_CSS_LIKE_QUERY
                        )
                ),

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