/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.rules.rgaa22;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.ruleimplementation.link.AbstractAllLinkAggregateRuleImplementation;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import org.opens.tanaguru.rules.elementchecker.link.LinkPertinenceChecker;
import org.opens.tanaguru.rules.elementselector.AreaLinkElementSelector;
import org.opens.tanaguru.rules.elementselector.CompositeLinkElementSelector;
import org.opens.tanaguru.rules.elementselector.LinkElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.VALUE_ATTR;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_BUTTON_TITLE_PERTINENCE_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.UNEXPLICIT_LINK_MSG;

/**
 * Implementation of the rule 6.14 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-6-14">the rule 6.14 design page.</a>
 * @see <a href="http://rgaa.net/Possibilite-d-identifier-la,93.html"> 6.14 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule06141 extends AbstractAllLinkAggregateRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa22Rule06141 () {
        super(new LinkElementSelector(false),
              new CompositeLinkElementSelector(false, true),
              new AreaLinkElementSelector(false),
              new CompositeLinkElementSelector(false, false),
              new LinkPertinenceChecker(
                        // not pertinent solution 
                        TestSolution.FAILED,
                        // not pertinent message
                        UNEXPLICIT_LINK_MSG,
                        // manual check message
                        CHECK_LINK_PERTINENCE_MSG,
                        // evidence elements
                        TEXT_ELEMENT2,
                        TITLE_ATTR
                ), 
              null
              );
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