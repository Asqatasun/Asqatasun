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
package org.opens.tanaguru.rules.seo;


import org.opens.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.opens.tanaguru.rules.elementchecker.pertinence.TextPertinenceChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.H1_ELEMENT;
import static org.opens.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.CHECK_H1_RELEVANCY_MSG;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.NOT_RELEVANT_H1_MSG;

/**
 * Test whether a not empty H1 tag is present on the page
 * 
 * @author jkowalczyk
 */
public class SeoRule07013 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /* The selector */
    private static final ElementSelector ELEMENT_SELECTOR = 
                    new SimpleElementSelector(H1_ELEMENT);
    
    /**
     * Default constructor
     */
    public SeoRule07013 () {
        super(
                ELEMENT_SELECTOR,
                new TextPertinenceChecker(
                    // check emptiness
                    true, 
                    // no comparison with other attribute
                    null, 
                    // blacklist nomenclature name
                    null, 
                    // not pertinent message
                    NOT_RELEVANT_H1_MSG, 
                    // manual check message
                    CHECK_H1_RELEVANCY_MSG,
                    // evidence elements
                    TEXT_ELEMENT2
                )
            );
    }

}