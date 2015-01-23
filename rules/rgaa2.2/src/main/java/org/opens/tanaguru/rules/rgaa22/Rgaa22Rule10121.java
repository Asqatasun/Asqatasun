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

package org.opens.tanaguru.rules.rgaa22;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.opens.tanaguru.rules.elementchecker.text.TextEmptinessChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.ACRONYM_ELEMENT;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG;
import org.opens.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 10.12 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-10-12">the rule 10.12 design page.</a>
 * @see <a href="http://rgaa.net/Pertinence-de-la-version-complete.html"> 10.12 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule10121 extends AbstractPageRuleWithSelectorAndCheckerImplementation {
    /** The element selector */
    private static final ElementSelector ELEMENT_SELECTOR = 
            new SimpleElementSelector(ACRONYM_ELEMENT);
    
    /**
     * Default constructor
     */
    public Rgaa22Rule10121 () {
        super(
                ELEMENT_SELECTOR, 
                new TextEmptinessChecker(
                    new TextAttributeOfElementBuilder(TITLE_ATTR), 
                    TestSolution.NOT_APPLICABLE, 
                    TestSolution.NEED_MORE_INFO, 
                    null, 
                    MANUAL_CHECK_ON_ELEMENTS_MSG, 
                    TEXT_ELEMENT2,
                    TITLE_ATTR
                )
            );
    }

}