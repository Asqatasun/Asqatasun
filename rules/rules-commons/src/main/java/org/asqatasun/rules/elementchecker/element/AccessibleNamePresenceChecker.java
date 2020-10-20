/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2019  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.rules.elementchecker.element;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementCheckerImpl;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.stream.Collectors;

import static org.asqatasun.rules.keystore.AttributeStore.*;
import static org.asqatasun.rules.keystore.HtmlElementStore.*;
import static org.asqatasun.rules.keystore.RemarkMessageStore.CHECK_NATURE_OF_IMAGE_WITHOUT_ROLE_IMG_ATTRIBUTE;

/**
 *  This implementation of the {@link ElementChecker} interface checks whether an
 *  accessible name is present regarding the following definition respecting the order, for all the elements:
 *    - text associated via the `aria-labelledby` WAI-ARIA attribute
 *    - presence of an `aria-label` WAI-ARIA attribute
 *
 *  If these both elements are missing, the presence of the accessible name is then checked regarding the following logical
 *  (depending on the type of the tag):
 *    - for the "svg", "canvas" tags and tags with "role=img", presence of text for these elements and their children
 *    - for the "img" and the "input tag=img" tags, presence of the alt attribute,
 *      then the presence of the title attribute if the alt attribute is missing
 *    - for the "area" tags, presence of the alt attribute
 *    - for the "object" and "embed" tags, presence of the title attribute, then the presence of an adjacent link or button
 *      if the title attribute is missing.
 *
 *  Note :
 *  For the svg element, we check that the "role=img" attribute is present before checking anything else.
 *
 */
public class AccessibleNamePresenceChecker extends ElementCheckerImpl {

    private String roleImgAttributeMissingMsgCode = CHECK_NATURE_OF_IMAGE_WITHOUT_ROLE_IMG_ATTRIBUTE;

    /**
     * Constructor.
     *
     * @param detectedSolutionPair
     * @param notDetectedSolutionPair
     * @param eeAttributeNameList
     */
    public AccessibleNamePresenceChecker(
            Pair<TestSolution,String> detectedSolutionPair,
            Pair<TestSolution,String> notDetectedSolutionPair,
            String... eeAttributeNameList) {
        super(detectedSolutionPair, notDetectedSolutionPair, eeAttributeNameList);
    }

    /**
     * Constructor.
     *
     * @param detectedSolutionPair
     * @param notDetectedSolutionPair
     * @param eeAttributeNameList
     */
    public AccessibleNamePresenceChecker(
        Pair<TestSolution,String> detectedSolutionPair,
        Pair<TestSolution,String> notDetectedSolutionPair,
        String roleImgAttributeMissingMsgCode,
        String... eeAttributeNameList) {
        super(detectedSolutionPair, notDetectedSolutionPair, eeAttributeNameList);
        this.roleImgAttributeMissingMsgCode = roleImgAttributeMissingMsgCode;
    }

    @Override
    public void doCheck(SSPHandler sspHandler, Elements elements, TestSolutionHandler testSolutionHandler) {
        for (Element el: elements) {
            testSolutionHandler.addTestSolution(checkAccessibleNamePresence(sspHandler, el));
        }
    }

    /**
     * Most of the logical of the 1.1 criterion is handled by this method.
     * The multiple if are a deliberate choice to fit the the definition of how an accessible name is defined in the
     * glossary of the rgaa4 referential.
     * (cf https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#alternative-textuelle-image)
     *
     * @param sspHandler
     * @param element
     */
    private TestSolution checkAccessibleNamePresence (SSPHandler sspHandler, Element element) {

        if (isElementExpectsAriaRoleImg(element) && !element.attr(ROLE_ATTR).contains("img")) {
            addSourceCodeRemark(getFailureSolution(), element, roleImgAttributeMissingMsgCode);
            return getFailureSolution();
        }

        if (StringUtils.isNotBlank(element.attr(ARIA_LABELLEDBY_ATTR)) &&
            !retrieveElementsAssociatedWithAriaLabelledBy(sspHandler, element).isEmpty()) {
            addSourceCodeRemark(getSuccessSolution(), element, getSuccessMsgCode());
            return getSuccessSolution();
        }
        if (element.hasAttr(ARIA_LABEL_ATTR)) {
            addSourceCodeRemark(getSuccessSolution(), element, getSuccessMsgCode());
            return getSuccessSolution();
        }

        if (isElementExpectsAlt(element) && element.hasAttr(ALT_ATTR)) {
            addSourceCodeRemark(getSuccessSolution(), element, getSuccessMsgCode());
            return getSuccessSolution();
        }

        if (isElementExpectsTitle(element) && element.hasAttr(TITLE_ATTR)) {
            addSourceCodeRemark(getSuccessSolution(), element, getSuccessMsgCode());
            return getSuccessSolution();
        }

        if (isElementExpectsOwnText(element) && StringUtils.isNotBlank(element.ownText())) {
            addSourceCodeRemark(getSuccessSolution(), element, getSuccessMsgCode());
            return getSuccessSolution();
        }

        if (isElementExpectsTextTagChild(element) && StringUtils.isNotBlank(getTextTagChildText(element))) {
            addSourceCodeRemark(getSuccessSolution(), element, getSuccessMsgCode());
            return getSuccessSolution();
        }

        if (isElementExpectsAdjacentLinkOrButton(element) && hasAdjacentElementOfLinkOrButtonType(element)) {
            addSourceCodeRemark(getSuccessSolution(), element, getSuccessMsgCode());
            return getSuccessSolution();
        }
        addSourceCodeRemark(getFailureSolution(), element, getFailureMsgCode());
        return getFailureSolution();
    }

    protected void createSourceCodeRemark(TestSolution testSolution, Element element, String message) {
        addSourceCodeRemark(testSolution, element, message);
    }

    private String buildCssQueryFromAriaLabelledByAttr(String ariaLabelledByAttr) {
        StringBuilder query = new StringBuilder();
        for (String id : ariaLabelledByAttr.split(" ")) {
            query.append("#");
            query.append(id);
            query.append(",");
        }
        return StringUtils.chop(query.toString());
    }

    private Elements retrieveElementsAssociatedWithAriaLabelledBy(SSPHandler sspHandler, Element element) {
        return sspHandler.beginCssLikeSelection().domCssLikeSelectNodeSet(
            buildCssQueryFromAriaLabelledByAttr(element.attr(ARIA_LABELLEDBY_ATTR))).getSelectedElements();
    }

    private boolean isElementExpectsAdjacentLinkOrButton(Element element) {
        return element.tagName().equals(CANVAS_ELEMENT) ||
            (element.tagName().equals(OBJECT_ELEMENT) && hasTypeImageAttribute(element)) ||
            (element.tagName().equals(EMBED_ELEMENT) && hasTypeImageAttribute(element));
    }

    private boolean isElementExpectsAriaRoleImg(Element element) {
        return element.tagName().equals(SVG_ELEMENT);
    }

    private boolean isElementExpectsOwnText(Element element) {
        return element.tagName().equals(CANVAS_ELEMENT);
    }

    private boolean isElementExpectsTextTagChild(Element element) {
        return element.tagName().equals(SVG_ELEMENT);
    }

    private boolean isElementExpectsAlt(Element element) {
        return element.tagName().equals(IMG_ELEMENT) ||
                element.tagName().equals(AREA_ELEMENT) ||
                (element.tagName().equals(INPUT_ELEMENT) && hasTypeImageAttribute(element));
    }

    private boolean isElementExpectsTitle(Element element) {
        return element.tagName().equals(IMG_ELEMENT) ||
            ( element.tagName().equals(INPUT_ELEMENT) && hasTypeImageAttribute(element) ) ||
            ( element.tagName().equals(OBJECT_ELEMENT) && hasTypeImageAttribute(element) ) ||
            ( element.tagName().equals(EMBED_ELEMENT) && hasTypeImageAttribute(element) );
    }

    private boolean hasAdjacentElementOfLinkOrButtonType(Element element) {
        Element previous = element.previousElementSibling();
        Element next = element.nextElementSibling();
        return isElementOfLinkOrButtonType(previous) || isElementOfLinkOrButtonType(next);
    }

    private boolean isElementOfLinkOrButtonType(Element element) {
        return element != null &&
            (element.tagName().equals(A_ELEMENT) ||
            element.tagName().equals(BUTTON_ELEMENT) ||
            element.tagName().equals(INPUT_ELEMENT) && element.attr(TYPE_ATTR).contains("button") ||
            element.attr(ROLE_ATTR).contains("button"));

    }

    private boolean hasTypeImageAttribute(Element element) {
        return element.attr(TYPE_ATTR).contains("image");
    }

    private String getTextTagChildText(Element element) {
        return element.children().stream().filter(e -> e.tagName().equals("text")).map(e -> e.ownText())
            .collect( Collectors.joining( " " ));
    }

}
