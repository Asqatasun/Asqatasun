/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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

package org.tanaguru.rules.rgaa30;

import java.util.Collection;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jsoup.nodes.Element;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleFromPreProcessImplementation;
import org.tanaguru.rules.domelement.DomElement;
import org.tanaguru.rules.domelement.extractor.DomElementExtractor;
import org.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import static org.tanaguru.rules.keystore.RemarkMessageStore.HIDDEN_TEXT_DETECTED_MSG;

/**
 * Implementation of the rule 10.13.1 of the referential Rgaa 3.0.
 *
 * For more details about the implementation, refer to <a href="http://tanaguru-rules-rgaa3.readthedocs.org/en/latest/Rule-10-13-1">the rule 10.13.1 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/referentiel-technique-0#test-10-13-1"> 10.13.1 rule specification</a>
 */
public class Rgaa30Rule101301 extends AbstractPageRuleFromPreProcessImplementation {

    /**
     * Default constructor
     */
    public Rgaa30Rule101301 () {
        super(
                new ElementPresenceChecker(
                    // if some elements are found
                    new ImmutablePair(TestSolution.NEED_MORE_INFO, HIDDEN_TEXT_DETECTED_MSG),
                    // if no found element
                    new ImmutablePair(TestSolution.NOT_APPLICABLE, "")
                )
            );
    }

    @Override
    protected void doSelect(
            Collection<DomElement> domElements, 
            SSPHandler sspHandler) {
        for (DomElement element : domElements) {
            if (element.isHidden() && element.isTextNode()) {
                Element el = DomElementExtractor.getElementFromDomElement(element, sspHandler);
                if (el != null) {
                    getElements().add(el);
                }
            }
        }
    }   

}