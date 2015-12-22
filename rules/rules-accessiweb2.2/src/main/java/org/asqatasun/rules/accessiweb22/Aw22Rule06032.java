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
import org.asqatasun.ruleimplementation.link.AbstractLinkRuleImplementation;
import org.asqatasun.rules.elementchecker.link.LinkPertinenceChecker;
import org.asqatasun.rules.elementselector.CompositeLinkElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.asqatasun.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.UNEXPLICIT_LINK_MSG;

/**
 * Implementation of the rule 6.3.2 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/accessiweb2.2/06.Links/Rule-6.3.2.html">the rule 6.3.2 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-6-3-2"> 6.3.2 rule specification</a>
 *
 */
public class Aw22Rule06032 extends AbstractLinkRuleImplementation {

    /**
     * Default constructor
     */
    public Aw22Rule06032 () {
        // context is not taken into consideration and the composite link 
        // selector only keep image link
        super(new CompositeLinkElementSelector(false, true), 
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
              null);
    }
}