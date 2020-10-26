/*
 *  Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
package org.asqatasun.rules.elementselector;

import org.apache.commons.lang3.StringUtils;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.stream.Collectors;

import static org.asqatasun.rules.keystore.AttributeStore.*;
import static org.asqatasun.rules.keystore.HtmlElementStore.*;

/**
 * Element selector implementation that only keeps the elements with an accessible name (also called textual alternative
 * in case of images).
 * 
 */
public class ElementWithAccessibleNameSelector implements ElementSelector {

    private static final String IMG_VALUE_ROLE_ATTR = "img";
    private static final String BUTTON_VALUE_TYPE_ATTR = "button";
    private static final String IMAGE_VALUE_TYPE_ATTR = "image";

    /* The decorated element selector used to retrieve Elements */
    private ElementSelector elementSelector;

    /**
     * @param elementSelector
     */
    public ElementWithAccessibleNameSelector(ElementSelector elementSelector) {
        this.elementSelector = elementSelector;
    }

    @Override
    public void selectElements(SSPHandler sspHandler, ElementHandler<Element> selectionHandler) {
        elementSelector.selectElements(sspHandler, selectionHandler);
        selectionHandler.get().removeIf(el -> !isAccessibleNamePresent(sspHandler, el));
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
    public static boolean isAccessibleNamePresent (SSPHandler sspHandler, Element element) {

        if (isElementExpectsAriaRoleImg(element) && !element.attr(ROLE_ATTR).contains(IMG_VALUE_ROLE_ATTR)) {
            return false;
        }

        if (StringUtils.isNotBlank(element.attr(ARIA_LABELLEDBY_ATTR)) &&
            !retrieveElementsAssociatedWithAriaLabelledBy(sspHandler, element).isEmpty()) {
            return true;
        }
        if (element.hasAttr(ARIA_LABEL_ATTR)) {
            return true;
        }

        if (isElementExpectsAlt(element) && element.hasAttr(ALT_ATTR)) {
            return true;
        }

        if (isElementExpectsTitle(element) && element.hasAttr(TITLE_ATTR)) {
            return true;
        }

        if (isElementExpectsOwnText(element) && StringUtils.isNotBlank(element.ownText())) {
            return true;
        }

        if (isElementExpectsTextTagChild(element) && StringUtils.isNotBlank(getTextTagChildText(element))) {
            return true;
        }

        if (isElementExpectsAdjacentLinkOrButton(element) && hasAdjacentElementOfLinkOrButtonType(element)) {
            return true;
        }

        return false;
    }

    private static final String buildCssQueryFromAriaLabelledByAttr(String ariaLabelledByAttr) {
        StringBuilder query = new StringBuilder();
        for (String id : ariaLabelledByAttr.split(" ")) {
            query.append("#");
            query.append(id);
            query.append(",");
        }
        return StringUtils.chop(query.toString());
    }

    private static Elements retrieveElementsAssociatedWithAriaLabelledBy(SSPHandler sspHandler, Element element) {
        return sspHandler.beginCssLikeSelection().domCssLikeSelectNodeSet(
            buildCssQueryFromAriaLabelledByAttr(element.attr(ARIA_LABELLEDBY_ATTR))).getSelectedElements();
    }

    private static boolean isElementExpectsAdjacentLinkOrButton(Element element) {
        return element.tagName().equals(CANVAS_ELEMENT) ||
            (element.tagName().equals(OBJECT_ELEMENT) && hasTypeImageAttribute(element)) ||
            (element.tagName().equals(EMBED_ELEMENT) && hasTypeImageAttribute(element));
    }

    private static boolean isElementExpectsAriaRoleImg(Element element) {
        return element.tagName().equals(SVG_ELEMENT);
    }

    private static boolean isElementExpectsOwnText(Element element) {
        return element.tagName().equals(CANVAS_ELEMENT);
    }

    private static boolean isElementExpectsTextTagChild(Element element) {
        return element.tagName().equals(SVG_ELEMENT);
    }

    private static boolean isElementExpectsAlt(Element element) {
        return element.tagName().equals(IMG_ELEMENT) ||
            element.tagName().equals(AREA_ELEMENT) ||
            (element.tagName().equals(INPUT_ELEMENT) && hasTypeImageAttribute(element));
    }

    private static boolean isElementExpectsTitle(Element element) {
        return element.tagName().equals(IMG_ELEMENT) ||
            ( element.tagName().equals(INPUT_ELEMENT) && hasTypeImageAttribute(element) ) ||
            ( element.tagName().equals(OBJECT_ELEMENT) && hasTypeImageAttribute(element) ) ||
            ( element.tagName().equals(EMBED_ELEMENT) && hasTypeImageAttribute(element) );
    }

    private static boolean hasAdjacentElementOfLinkOrButtonType(Element element) {
        Element previous = element.previousElementSibling();
        Element next = element.nextElementSibling();
        return isElementOfLinkOrButtonType(previous) || isElementOfLinkOrButtonType(next);
    }

    private static boolean isElementOfLinkOrButtonType(Element element) {
        return element != null &&
            (element.tagName().equals(A_ELEMENT) ||
                element.tagName().equals(BUTTON_ELEMENT) ||
                (element.tagName().equals(INPUT_ELEMENT) && element.attr(TYPE_ATTR).contains(BUTTON_VALUE_TYPE_ATTR)) ||
                element.attr(ROLE_ATTR).contains(BUTTON_VALUE_TYPE_ATTR));

    }

    private static boolean hasTypeImageAttribute(Element element) {
        return element.attr(TYPE_ATTR).contains(IMAGE_VALUE_TYPE_ATTR);
    }

    private  static String getTextTagChildText(Element element) {
        return element.children().stream().filter(e -> e.tagName().equals(TEXT_ELEMENT2)).map(e -> e.ownText())
            .collect( Collectors.joining( " " ));
    }

}
