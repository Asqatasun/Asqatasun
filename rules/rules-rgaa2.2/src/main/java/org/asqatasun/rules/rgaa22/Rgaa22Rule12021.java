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

package org.asqatasun.rules.rgaa22;

import org.asqatasun.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.asqatasun.rules.elementchecker.lang.LangChangeChecker;
import org.asqatasun.rules.elementchecker.lang.LangChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import static org.asqatasun.rules.keystore.AttributeStore.*;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.HTML_WITH_LANG_CSS_LIKE_QUERY;
import org.asqatasun.rules.textbuilder.TextAttributeOfElementBuilder;

/**
 * Implementation of the rule 12.2 of the referential RGAA 2.2.
 * <br/>
 * For more details about the implementation, refer to <a href="http://www.tanaguru.org/en/content/rgaa22-rule-12-2">the rule 12.2 design page.</a>
 * @see <a href="http://rgaa.net/Presence-de-l-indication-des,179.html"> 12.2 rule specification </a>
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule12021 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    private final LangChecker ec = 
            new LangChangeChecker(
                    new TextAttributeOfElementBuilder(
                        ALT_ATTR, 
                        SUMMARY_ATTR, 
                        CONTENT_ATTR, 
                        LABEL_ATTR, 
                        VALUE_ATTR, 
                        NAME_ATTR, 
                        TITLE_ATTR));
    
    /**
     * Default constructor
     */
    public Rgaa22Rule12021 () {
        super();
        setElementSelector(new SimpleElementSelector(HTML_WITH_LANG_CSS_LIKE_QUERY));
        setElementChecker(ec);
    }
    
    @Override
    public int getSelectionSize() {
        return ec.getNbOfElementsTested();
    }

}