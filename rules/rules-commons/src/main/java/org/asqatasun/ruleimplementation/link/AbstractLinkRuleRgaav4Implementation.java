/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
package org.asqatasun.ruleimplementation.link;

import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.link.LinkPertinenceRgaa4Checker;
import org.asqatasun.rules.elementselector.ElementSelector;
import org.asqatasun.rules.elementselector.LinkElementSelector;
import org.asqatasun.rules.textbuilder.AccessibleNameElementBuilder;

import static org.asqatasun.rules.keystore.AttributeStore.ARIA_LABEL_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.asqatasun.rules.keystore.EvidenceStore.COMPUTED_LINK_TITLE;
import static org.asqatasun.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.asqatasun.rules.keystore.RemarkMessageStore.*;

/**
 * This class deals with the tests related with links. Two kinds of links are 
 * tested by a specific checker : the one that are decidable (with no context)
 * and the one that are not decidable (with context).
 * 
 */
public abstract class AbstractLinkRuleRgaav4Implementation extends AbstractLinkRuleImplementation {

    public AbstractLinkRuleRgaav4Implementation(LinkElementSelector elementSelector) {
        super();
        this.setLinkElementSelector(elementSelector);
    }

    @Override
    protected void check(SSPHandler sspHandler, TestSolutionHandler testSolutionHandler) {
        this.setDecidableElementsChecker(
            new LinkPertinenceRgaa4Checker(
                new AccessibleNameElementBuilder(),
                // not pertinent solution
                TestSolution.FAILED,
                // not pertinent message
                UNEXPLICIT_LINK_MSG,
                // manual check message
                CHECK_LINK_WITHOUT_CONTEXT_PERTINENCE_MSG,
                // evidence elements
                TEXT_ELEMENT2,
                TITLE_ATTR,
                ARIA_LABEL_ATTR,
                COMPUTED_LINK_TITLE));
        this.setNotDecidableElementsChecker(
            new LinkPertinenceRgaa4Checker(
                new AccessibleNameElementBuilder(),
                // not pertinent solution
                TestSolution.NEED_MORE_INFO,
                // not pertinent message
                UNEXPLICIT_LINK_WITH_CONTEXT_MSG,
                // manual check message
                CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG,
                // evidence elements
                TEXT_ELEMENT2,
                TITLE_ATTR,
                ARIA_LABEL_ATTR,
                COMPUTED_LINK_TITLE));
        super.check(sspHandler, testSolutionHandler);
    }

}
