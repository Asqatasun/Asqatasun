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
 * Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.rules.accessiweb22;

import java.util.Collection;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleFromPreProcessImplementation;
import org.opens.tanaguru.rules.domelement.DomElement;
import org.opens.tanaguru.rules.domelement.extractor.DomElementExtractor;
import org.opens.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.HIDDEN_TEXT_DETECTED_MSG;

/**
 * Implementation of the rule 10.13.1 of the referential Accessiweb 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/aw22-rule-10-13-1">the rule 10.13.1 design page.</a>
 * @see <a href="http://www.accessiweb.org/index.php/accessiweb-22-english-version.html#test-10-13-1"> 10.13.1 rule specification</a>
 *
 * @author jkowalczyk
 */
public class Aw22Rule10131 extends AbstractPageRuleFromPreProcessImplementation {

    /**
     * Default constructor
     */
    public Aw22Rule10131 () {
        super(new ElementPresenceChecker(
                    // if some elements are found
                    TestSolution.NEED_MORE_INFO, 
                    // if no found element
                    TestSolution.NOT_APPLICABLE, 
                    // message for each detected element
                    HIDDEN_TEXT_DETECTED_MSG,
                    null));
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