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
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractDetectionPageRuleImplementation;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.LINK_WITH_TARGET_ATTR_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_JS_PROMPT_A_NEW_WINDOW_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG;
import org.asqatasun.rules.textbuilder.LinkTextElementBuilder;

/**
 * Implementation of the rule 13.2.1 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/accessiweb2.2/13.Consultation/Rule-13.2.1.html">the rule 13.2.1 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-13-2-1"> 13.2.1 rule specification</a>
 *
 * @author jkowalczyk
 */

public class Aw22Rule13021 extends AbstractDetectionPageRuleImplementation {

    /**
     * Default constructor
     */
    public Aw22Rule13021 () {
        super(
                new SimpleElementSelector(LINK_WITH_TARGET_ATTR_CSS_LIKE_QUERY),
                // solution when at least one element is found
                TestSolution.NEED_MORE_INFO,
                // solution when no element is found
                TestSolution.NEED_MORE_INFO,
                // message when element is found
                CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG,
                // message when element is not found
                CHECK_JS_PROMPT_A_NEW_WINDOW_MSG,
                // evidence elements
                TEXT_ELEMENT2,
                TITLE_ATTR
                );
    }

    @Override
    protected void check(
                SSPHandler sspHandler, 
                TestSolutionHandler testSolutionHandler) {
        ElementPresenceChecker epc = getElementPresenceChecker();
        epc.setTextElementBuilder(new LinkTextElementBuilder());
        epc.check(sspHandler, getElements(), testSolutionHandler);
    }

}