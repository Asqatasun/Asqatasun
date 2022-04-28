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

import java.util.stream.Collectors;
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
import org.jsoup.nodes.Node;

import static org.asqatasun.rules.keystore.CssLikeQueryStore.TAGS_WITHOUT_CONTENT_USED_FOR_LAYOUT_PURPOSE_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.CssLikeQueryStore.LINK_WITHOUT_TARGET_CSS_LIKE_QUERY;
import static org.asqatasun.rules.keystore.HtmlElementStore.*;
import static org.asqatasun.rules.keystore.RemarkMessageStore.LINK_WITHOUT_TARGET_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.TAGS_WITHOUT_CONTENT_USED_FOR_LAYOUT_PURPOSE_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CONSECUTIVE_TAGS_USED_FOR_LAYOUT_PURPOSE_MSG;
import static org.asqatasun.rules.keystore.RemarkMessageStore.NO_PATTERN_DETECTED_MSG;

/**
 * Implementation of rule 8.9.1 (referential RGAA 4.0)
 *
 * For more details about implementation, refer to <a href="https://doc.asqatasun.org/v5/en/Business-rules/RGAA-v4/08.Mandatory_elements/Rule-8-9-1/">rule 8.9.1 design page</a>.
 * @see <a href="https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/criteres/#test-8-9-1">8.9.1 rule specification</a>
 */
public class Rgaa40Rule080901 extends AbstractPageRuleMarkupImplementation {

    // Links without target
    private ElementHandler<Element> linkWithoutTarget = new ElementHandlerImpl();

    // Tags (p, li, ...) without content
    private ElementHandler<Element> tagWithoutContent = new ElementHandlerImpl();

    // Consecutive <br> tags without text in between
    private ElementHandler<Element> consecutiveBrElement = new ElementHandlerImpl();

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

        // Selection of all tags (p, li, ...) without content
        ElementSelector tagWithoutContentSelector =
            new SimpleElementSelector(TAGS_WITHOUT_CONTENT_USED_FOR_LAYOUT_PURPOSE_CSS_LIKE_QUERY);
        tagWithoutContentSelector.selectElements(sspHandler, tagWithoutContent);

        // Selection of all consecutive <br> tags without text in between
        ElementHandler<Element> brElementHandler = new ElementHandlerImpl();
        ElementSelector brElementSelector = new SimpleElementSelector(BR_ELEMENT);
        brElementSelector.selectElements(sspHandler, brElementHandler);
        consecutiveBrElement.addAll(brElementHandler.get().stream()
            .filter(e -> hasNextElementSiblingWithoutTextInBetween(e, BR_ELEMENT))
            .collect(Collectors.toList()));
    }

    private static boolean hasNextElementSiblingWithoutTextInBetween(Element element, String nextElementSiblingName) {
        Element nextElement = element.nextElementSibling();
        Node nextTextNode = element.nextSibling();
        String nextText = nextTextNode.toString().trim();
        return nextElement != null
            && nextElement.tagName().equals(nextElementSiblingName)
            && nextText.isEmpty();
    }

    @Override
    protected void check(
        SSPHandler sspHandler,
        TestSolutionHandler testSolutionHandler) {

        if (linkWithoutTarget.isEmpty() && tagWithoutContent.isEmpty() && consecutiveBrElement.isEmpty()) {
            sspHandler.getProcessRemarkService().addProcessRemark(
                TestSolution.NEED_MORE_INFO,
                RuleCheckHelper.specifyMessageToRule(
                    NO_PATTERN_DETECTED_MSG,
                    this.getTest().getCode())
            );
            testSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);
            return;
        }

        // Links without target
        ElementChecker linkWithoutTargetChecker = new ElementPresenceChecker(
            new ImmutablePair(TestSolution.FAILED, LINK_WITHOUT_TARGET_MSG),
            new ImmutablePair(TestSolution.PASSED, ""));
        linkWithoutTargetChecker.check(
            sspHandler,
            linkWithoutTarget,
            testSolutionHandler);

        // Tags (p, li, ...) without content
        ElementChecker tagWithoutContentChecker = new ElementPresenceChecker(
            new ImmutablePair(TestSolution.FAILED, TAGS_WITHOUT_CONTENT_USED_FOR_LAYOUT_PURPOSE_MSG),
            new ImmutablePair(TestSolution.PASSED, ""));
        tagWithoutContentChecker.check(
            sspHandler,
            tagWithoutContent,
            testSolutionHandler);

        // Consecutive <br> tags without text in between
        ElementChecker consecutiveBrElementChecker = new ElementPresenceChecker(
            new ImmutablePair(TestSolution.FAILED, CONSECUTIVE_TAGS_USED_FOR_LAYOUT_PURPOSE_MSG),
            new ImmutablePair(TestSolution.PASSED, ""));
        consecutiveBrElementChecker.check(
            sspHandler,
            consecutiveBrElement,
            testSolutionHandler);
    }

    @Override
    public int getSelectionSize() {
        return this.tagWithoutContent.get().size()
            + this.linkWithoutTarget.get().size()
            + this.consecutiveBrElement.get().size();
    }

}
