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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.rules.rgaa30;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.ruleimplementation.link.AbstractLinkRuleImplementation;
import org.opens.tanaguru.rules.elementchecker.link.LinkPertinenceChecker;
import org.opens.tanaguru.rules.elementselector.LinkElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_LINK_WITH_CONTEXT_PERTINENCE_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_LINK_WITHOUT_CONTEXT_PERTINENCE_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.UNEXPLICIT_LINK_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.UNEXPLICIT_LINK_WITH_CONTEXT_MSG;

/**
 * Implementation of the rule 6.1.1 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a href="https://github.com/Tanaguru/Tanaguru-rules-RGAA-3-doc/wiki/Rule-6-1-1">the rule 6.1.1 design page.</a>
 * @see <a href="https://references.modernisation.gouv.fr/sites/default/files/RGAA3/referentiel_technique.htm#test-6-1-1"> 6.1.1 rule specification</a>
 *
 */
public class Rgaa30Rule060101 extends AbstractLinkRuleImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule060101 () {
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
