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
import org.asqatasun.rules.elementselector.ImageElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.*;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.*;
import static org.asqatasun.rules.keystore.RemarkMessageStore.*;

/**
 * Implementation of the rule 1.9.6 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/40_Rules/accessiweb2.2/01.Images/Rule-1.9.6.html">the rule 1.9.6 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-1-9-6"> 1.9.6 rule specification</a>
 *
 */
public class Aw22Rule01096 extends AbstractDetectionPageRuleImplementation {

    /**
     * Default constructor
     */
    public Aw22Rule01096 () {
        super(
                new ImageElementSelector(EMBED_TYPE_IMG_CSS_LIKE_QUERY),
                TestSolution.NEED_MORE_INFO,
                TestSolution.NOT_APPLICABLE,
                MANUAL_CHECK_ON_ELEMENTS_MSG,
                null,
                // evidence elements
                SRC_ATTR
            );
    }

}