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

package org.asqatasun.rules.rgaa32016;

import java.util.Collection;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jsoup.nodes.Element;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractPageRuleFromPreProcessImplementation;
import org.asqatasun.rules.domelement.DomElement;
import org.asqatasun.rules.domelement.extractor.DomElementExtractor;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import static org.asqatasun.rules.keystore.RemarkMessageStore.HIDDEN_TEXT_DETECTED_MSG;

/**
 * Implementation of the rule 10.13.1 of the referential RGAA 3.2016
 *
 * For more details about the implementation, refer to <a href="http://doc.asqatasun.org/en/90_Rules/rgaa3.2016/10.Presentation_of_information/Rule-10-13-1.html">the rule 10.13.1 design page.</a>
 * @see <a href="http://references.modernisation.gouv.fr/rgaa-accessibilite/criteres.html#test-10-13-1">10.13.1 rule specification</a>
 */
public class Rgaa32016Rule101301 extends AbstractPageRuleFromPreProcessImplementation {

    /**
     * Default constructor
     */
    public Rgaa32016Rule101301  () {
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