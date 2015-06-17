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
import org.tanaguru.ruleimplementation.link.AbstractLinkRuleImplementation;
import org.tanaguru.rules.elementchecker.link.LinkPertinenceChecker;
import org.tanaguru.rules.elementselector.CompositeLinkElementSelector;
import static org.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.tanaguru.rules.keystore.RemarkMessageStore.*;

/**
 * Implementation of the rule 4.2 of the referential RGAA 2.2. <br/> For more
 * details about the implementation, refer to <a
 * href="http://www.tanaguru.org/en/content/rgaa22-rule-4-2">the rule 4.2 design
 * page.</a>
 *
 * @see <a href="http://rgaa.net/Pertinence-de-l-alternative.html"> 4.2 rule
 * specification
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule04021 extends AbstractLinkRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa22Rule04021() {
        // context is taken into consideration 
        super(
            new CompositeLinkElementSelector(true, true),
            new LinkPertinenceChecker(
                // not pertinent solution 
                TestSolution.FAILED,
                // not pertinent message
                UNEXPLICIT_LINK_MSG,
                // manual check message
                CHECK_LINK_WITHOUT_CONTEXT_PERTINENCE_MSG,
                // evidence elements
                TEXT_ELEMENT2,
                TITLE_ATTR),
            new LinkPertinenceChecker(
                // not pertinent solution 
                TestSolution.NEED_MORE_INFO,
                // not pertinent message
                UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                // manual check message
                CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                // evidence elements
                TEXT_ELEMENT2,
                TITLE_ATTR)
        );
    }
}