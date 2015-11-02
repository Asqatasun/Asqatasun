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
package org.tanaguru.rules.accessiweb22;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.link.AbstractLinkRuleImplementation;
import org.tanaguru.rules.elementchecker.link.LinkPertinenceChecker;
import org.tanaguru.rules.elementselector.LinkElementSelector;
import static org.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.CHECK_LINK_WITHOUT_CONTEXT_PERTINENCE_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.UNEXPLICIT_LINK_MSG;
import static org.tanaguru.rules.keystore.RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG;

/**
 * Implementation of the rule 6.1.1 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-6-1-1">the rule 6.1.1 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-6-1-1"> 6.1.1 rule specification</a>
 *
 */
public class Aw22Rule06011 extends AbstractLinkRuleImplementation {

    /**
     * Default constructor
     */
    public Aw22Rule06011 () {
        // context is taken into consideration 
        super(new LinkElementSelector(true), 
              new LinkPertinenceChecker(
                    // not pertinent solution 
                    TestSolution.FAILED,
                    // not pertinent message
                    UNEXPLICIT_LINK_MSG,
                    // manual check message
                    CHECK_LINK_WITHOUT_CONTEXT_PERTINENCE_MSG,
                    // evidence elements
                    TEXT_ELEMENT2,
                    TITLE_ATTR
              ),
              new LinkPertinenceChecker(
                    // not pertinent solution 
                    TestSolution.NEED_MORE_INFO,
                    // not pertinent message
                    UNEXPLICIT_LINK_WITH_CONTEXT_MSG, 
                    // manual check message
                    CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                    // evidence elements
                    TEXT_ELEMENT2,
                    TITLE_ATTR
                ));
    }

}