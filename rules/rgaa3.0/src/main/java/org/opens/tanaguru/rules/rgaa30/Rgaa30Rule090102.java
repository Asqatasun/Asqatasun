/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
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

import java.util.Iterator;
import java.util.regex.Pattern;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.rules.elementchecker.headings.HeadingsHierarchyChecker;
import org.opens.tanaguru.rules.elementselector.SimpleElementSelector;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.HEADINGS_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.ARIA_HEADINGS_CSS_LIKE_QUERY;

/**
 * Implementation of the rule 9.1.2 of the referential Rgaa 3.0.
 * <br/>
 * For more details about the implementation, refer to <a
 * href="https://github.com/Tanaguru/Tanaguru-rules-RGAA-3-doc/wiki/Rule-9-1-2">the rule 9.1.2
 * design page.</a>
 *
 * @see <a
 * href="https://references.modernisation.gouv.fr/sites/default/files/RGAA3/referentiel_technique.htm#test-9-1-2">
 * 9.1.2 rule specification</a>
 *
 */
public class Rgaa30Rule090102 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    private static final Pattern PATTERN = Pattern.compile("[1-9][0-9]?");

    /**
     * Default constructor
     */
    public Rgaa30Rule090102() {
        super(
                new SimpleElementSelector(
                HEADINGS_CSS_LIKE_QUERY + ", " + ARIA_HEADINGS_CSS_LIKE_QUERY),
                new HeadingsHierarchyChecker());
    }

    @Override
    protected void select(SSPHandler sspHandler, ElementHandler elementHandler) {
        super.select(sspHandler, elementHandler);
        Iterator<Element> elementsIterator = elementHandler.get().iterator();
        while (elementsIterator.hasNext()) {
            Element element = elementsIterator.next();
            if (element.hasAttr("aria-level")) {
                if (!PATTERN.matcher(element.attr("aria-level")).matches()) {
                    elementsIterator.remove();
                }
            }
        }
    }
    
}
