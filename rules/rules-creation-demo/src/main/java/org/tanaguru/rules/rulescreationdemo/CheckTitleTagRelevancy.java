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
package org.tanaguru.rules.rulescreationdemo;

import org.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.tanaguru.rules.elementchecker.text.TextBelongsToBlackListChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.textbuilder.SimpleTextElementBuilder;

/**
 * Implementation of the rule CheckTitleTagRelevancy of the referential Rules creation demo.
 *
 * @see <a href="https://github.com/Tanaguru/Tanaguru/wiki/Create-a-rule-:-Getting-started"> 
 * Create a rule : Getting started</a>
 *
 * @author jkowalczyk
 */

public class CheckTitleTagRelevancy extends 
                AbstractPageRuleWithSelectorAndCheckerImplementation {
    /**
     * Constructor
     */
    public CheckTitleTagRelevancy() {
        super(new SimpleElementSelector("title"), // The ElementSelector implementation
                new TextBelongsToBlackListChecker( // The ElementChecker implementation
                    new SimpleTextElementBuilder(), // the TextElementBuilder implementation
                    "UnexplicitPageTitle",// the name of the irrelevant titles nomenclature 
                    "IrrelevantTitle",// message created when the title belongs to the blacklist
                    HtmlElementStore.TEXT_ELEMENT2 // the text of the title is extracted and displayed in case of irrelevant detection   
              )
        );
    }

}