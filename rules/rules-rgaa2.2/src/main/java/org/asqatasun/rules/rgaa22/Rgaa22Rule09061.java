/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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

package org.asqatasun.rules.rgaa22;

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementselector.ElementSelector;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.TITLE_WITHIN_HEAD_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.TITLE_TAG_MISSING_MSG;


/**
 * Implementation of the rule 9.6 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.old-dot-org.org/en/content/rgaa22-rule-9-6">the rule 9.6 design page.</a>
 * @see <a href="http://rgaa.net/Presence-d-un-titre-dans-la-page.html"> 9.6 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule09061 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /** The element selector */
    private static final ElementSelector ELEMENT_SELECTOR = 
            new SimpleElementSelector(TITLE_WITHIN_HEAD_CSS_LIKE_QUERY);
    
    /** The element checker */
    private static final ElementPresenceChecker ELEMENT_CHECKER = 
            new ElementPresenceChecker(
                    true, 
                    TestSolution.PASSED, 
                    TestSolution.FAILED, 
                    null, 
                    TITLE_TAG_MISSING_MSG,
                    null
            );
    
    /**
     * Default constructor
     */
    public Rgaa22Rule09061 () {
        super(
                ELEMENT_SELECTOR,
                ELEMENT_CHECKER
            );
    }

}
