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
package org.asqatasun.rules.seo;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.ruleimplementation.AbstractPageRuleWithSelectorAndCheckerImplementation;
import org.asqatasun.rules.elementchecker.element.ElementUnicityChecker;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import org.asqatasun.rules.keystore.HtmlElementStore;
import static org.asqatasun.rules.keystore.HtmlElementStore.H1_ELEMENT;
import static org.asqatasun.rules.keystore.RemarkMessageStore.MORE_THAN_ONE_H1_MSG;

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