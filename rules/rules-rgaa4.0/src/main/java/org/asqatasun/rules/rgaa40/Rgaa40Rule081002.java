/**
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.rules.rgaa40;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.AbstractPageRuleMarkupImplementation;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementChecker;
import org.asqatasun.rules.elementchecker.element.ElementPresenceChecker;
import org.asqatasun.rules.elementselector.ElementSelector;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import org.jsoup.nodes.Element;

import static org.asqatasun.rules.keystore.CssLikeQueryStore.ELEMENT_WITH_DIR_ATTR_AND_NOT_ALLOWED_VALUE_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.ELEMENT_WITH_DIR_ATTR_AND_ALLOWED_VALUE_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.*;

/**
 * Implementation of rule 8.10.2 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/08.Mandatory_elements/Rule-8-10-2.md">rule 8.10.2 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-10-2">8.10.2 rule specification</a>
 */
public class Rgaa40Rule081002 extends AbstractPageRuleMarkupImplementation {

    // Total number of elements
    private int totalNumberOfElements = 0;

    // Tags with dir attribute (with allowed value)
    private ElementHandler<Element> dirAttributeWithAllowedValue = new ElementHandlerImpl();

    // Tags with dir attribute (with not allowed value)
    private ElementHandler<Element> dirAttributeWithNotAllowedValue = new ElementHandlerImpl();

    /**
     * Default constructor
     */
    public Rgaa40Rule081002() {
        super();
    }


    @Override
    protected void select(SSPHandler sspHandler) {

        // Selection of all tags with dir attribute (with allowed value)
        ElementSelector dirAttributeWithAllowedValueSelector =
            new SimpleElementSelector(ELEMENT_WITH_DIR_ATTR_AND_ALLOWED_VALUE_CSS_LIKE_QUERY);
        dirAttributeWithAllowedValueSelector.selectElements(sspHandler, dirAttributeWithAllowedValue);

        // Selection of all tags with dir attribute (with not allowed value)
        ElementSelector dirAttributeWithNotAllowedValueSelector =
            new SimpleElementSelector(ELEMENT_WITH_DIR_ATTR_AND_NOT_ALLOWED_VALUE_CSS_LIKE_QUERY);
        dirAttributeWithNotAllowedValueSelector.selectElements(sspHandler, dirAttributeWithNotAllowedValue);

        totalNumberOfElements = sspHandler.getTotalNumberOfElements();
    }

    @Override
    protected void check(
        SSPHandler sspHandler,
        TestSolutionHandler testSolutionHandler) {

        if (dirAttributeWithAllowedValue.isEmpty() && dirAttributeWithNotAllowedValue.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            totalNumberOfElements = 0;
            return;
        }

        // Tags with dir attribute (with not allowed value)
        ElementChecker dirAttributeWithNotAllowedValueChecker = new ElementPresenceChecker(
            new ImmutablePair(TestSolution.FAILED, DIR_ATTRIBUTE_WITH_NOT_ALLOWED_VALUE_MSG),
            new ImmutablePair(TestSolution.PASSED, ""));
        dirAttributeWithNotAllowedValueChecker.check(
            sspHandler,
            dirAttributeWithNotAllowedValue,
            testSolutionHandler);

        // Tags with dir attribute (with allowed value)
        ElementChecker dirAttributeWithAllowedValueChecker = new ElementPresenceChecker(
            new ImmutablePair(TestSolution.NEED_MORE_INFO, DIR_ATTRIBUTE_WITH_ALLOWED_VALUE_MSG),
            new ImmutablePair(TestSolution.PASSED, ""));
        dirAttributeWithAllowedValueChecker.check(
            sspHandler,
            dirAttributeWithAllowedValue,
            testSolutionHandler);
    }

    @Override
    public int getSelectionSize() {
        return totalNumberOfElements;
    }

}
