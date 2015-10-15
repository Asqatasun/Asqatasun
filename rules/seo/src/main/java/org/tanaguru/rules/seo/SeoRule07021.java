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
package org.tanaguru.rules.seo;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.tanaguru.rules.elementchecker.element.ElementUnicityChecker;
import org.tanaguru.rules.elementselector.SimpleElementSelector;
import org.tanaguru.rules.keystore.HtmlElementStore;
import static org.tanaguru.rules.keystore.HtmlElementStore.H1_ELEMENT;
import static org.tanaguru.rules.keystore.RemarkMessageStore.MORE_THAN_ONE_H1_MSG;

/**
 * This rule tests if each page has one and only one &lt;h1&gt; tag
 *
 * @author jkowalczyk
 */
public class SeoRule07021 extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /**
     * Default constructor
     */
    public SeoRule07021() {
        super(
                new SimpleElementSelector(H1_ELEMENT),
                new ElementUnicityChecker(
                        new ImmutablePair(TestSolution.PASSED,""),// no message when unique cause the result is passed 
                        new ImmutablePair(TestSolution.FAILED,MORE_THAN_ONE_H1_MSG), 
                        HtmlElementStore.TEXT_ELEMENT2));
    }
}