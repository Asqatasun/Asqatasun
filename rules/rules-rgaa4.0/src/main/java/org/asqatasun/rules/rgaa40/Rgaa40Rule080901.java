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
import org.asqatasun.rules.elementchecker.helper.RuleCheckHelper;
import org.asqatasun.rules.elementselector.ElementSelector;
import org.asqatasun.rules.elementselector.SimpleElementSelector;
import org.jsoup.nodes.Element;

import static org.asqatasun.rules.keystore.CssLikeQueryStore.LINK_WITHOUT_TARGET_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.RemarkMessageStore.LINK_WITHOUT_TARGET_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.NO_PATTERN_DETECTED_MSG;

/**
 * Implementation of rule 8.9.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://gitlab.com/asqatasun/Asqatasun/-/blob/master/documentation/en/90_Rules/rgaa4.0/08.Mandatory_elements/Rule-8-9-1.md">rule 8.9.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-9-1">8.9.1 rule specification</a>
 */
public class Rgaa40Rule080901 extends AbstractPageRuleMarkupImplementation {

    /* the links without target */
    private ElementHandler<Element> linkWithoutTarget = new ElementHandlerImpl();

    /* the total number of elements */
    private int totalNumberOfElements = 0;

    /**
     * Default constructor
     */
    public Rgaa40Rule080901() {
        super();
    }

    @Override
    protected void select(SSPHandler sspHandler) {
        // Selection of all links without target
        ElementSelector linkWithoutTargetSelector =
            new SimpleElementSelector(LINK_WITHOUT_TARGET_CSS_LIKE_QUERY);
        linkWithoutTargetSelector.selectElements(sspHandler, linkWithoutTarget);

        totalNumberOfElements = sspHandler.getTotalNumberOfElements();
    }

    @Override
    protected void check(
        SSPHandler sspHandler,
        TestSolutionHandler testSolutionHandler) {

        if (linkWithoutTarget.isEmpty()) {
            sspHandler.getProcessRemarkService().addProcessRemark(
                TestSolution.NEED_MORE_INFO,
                RuleCheckHelper.specifyMessageToRule(
                    NO_PATTERN_DETECTED_MSG,
                    this.getTest().getCode())
            );
            testSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);
            return;
        }

        ElementChecker linkWithoutTargetChecker = new ElementPresenceChecker(
            new ImmutablePair(TestSolution.FAILED, LINK_WITHOUT_TARGET_MSG),
            new ImmutablePair(TestSolution.PASSED, ""));

        linkWithoutTargetChecker.check(
            sspHandler,
            linkWithoutTarget,
            testSolutionHandler);
    }

    @Override
    public int getSelectionSize() {
        return totalNumberOfElements;
    }

}
