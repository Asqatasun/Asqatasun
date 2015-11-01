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

package org.tanaguru.rules.rgaa22;

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.ElementHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.ruleimplementation.link.AbstractAllLinkAggregateRuleImplementation;
import org.tanaguru.rules.elementchecker.ElementChecker;
import org.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import org.tanaguru.rules.elementchecker.link.LinkPertinenceChecker;
import org.tanaguru.rules.elementselector.AreaLinkElementSelector;
import org.tanaguru.rules.elementselector.CompositeLinkElementSelector;
import org.tanaguru.rules.elementselector.LinkElementSelector;
import static org.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.VALUE_ATTR;
import static org.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.tanaguru.rules.keystore.RemarkMessageStore.*;

/**
 * Implementation of the rule 6.13 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-6-13">the rule 6.13 design page.</a>
 * @see <a href="http://rgaa.net/Possibilite-d-identifier-la.html"> 6.13 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule06131 extends AbstractAllLinkAggregateRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa22Rule06131 () {
        super(new LinkElementSelector(true),
              new CompositeLinkElementSelector(true, true),
              new AreaLinkElementSelector(true),
              new CompositeLinkElementSelector(true, false),
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

    @Override
    protected void checkButtonSelection(
            SSPHandler sspHandler, 
            ElementHandler formButtonHandler, 
            TestSolutionHandler testSolutionHandler) {
        if (formButtonHandler.isEmpty()) {
            return;
        }
        // checker used to create remark on form elements
        ElementChecker ec = new ElementPresenceChecker(
                    // nmi when element is found
                    TestSolution.NEED_MORE_INFO, 
                    // na when element is not found
                    TestSolution.NOT_APPLICABLE, 
                    // message associated with each found form element
                    CHECK_BUTTON_TITLE_PERTINENCE_MSG,
                    null, 
                    TEXT_ELEMENT2,
                    VALUE_ATTR);
        ec.check(sspHandler, formButtonHandler, testSolutionHandler);
    }

}