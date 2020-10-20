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
package org.asqatasun.rules.textbuilder;

import org.apache.commons.lang3.StringUtils;
import org.asqatasun.processor.SSPHandler;
import org.jsoup.nodes.Element;

import static org.asqatasun.rules.keystore.AttributeStore.*;

/**
 * This implementation of the {@link TextualElementBuilder} interface extracts the
 * accessible name regarding the following definition respecting the order, for all the elements :
 *   - text associated via the `aria-labelledby` WAI-ARIA attribute
 *   - presence of an `aria-label` WAI-ARIA attribute
 *
 * If these both elements are missing, the accessible name then is extracted as follows (depending on the type of the tag):
 *   - for the "a", "svg", "canvas" tags and tags with "role=img", extract the text of the element and its children
 *   - for the "img" and the "input tag=img" tags, extract the content of the alt attribute,
 *     then the content of the title attribute if the alt attribute is missing
 *   - for the "area" tags, extract the content of the alt attribute
 *   - for the "object" and "embed" tags, extract the content of the title attribute
 *
 */
public class AccessibleNameElementBuilder extends DeepTextElementBuilder{

    private SSPHandler sspHandler;
    public void setSspHandler(SSPHandler sspHandler) {
        this.sspHandler = sspHandler;
    }

    public AccessibleNameElementBuilder() {
        super();
    }

    /**
     * Most of the logical of the extraction of an accessible name is handled by this method
     * (cf https://www.numerique.gouv.fr/publications/rgaa-accessibilite/methode/glossaire/#intitule-ou-nom-accessible-de-lien)
     * @param element
     * @return
     */
    @Override
    public String buildTextFromElement(Element element) {
        if (StringUtils.isNotBlank(element.attr(ARIA_LABELLEDBY_ATTR))) {
            StringBuilder elementText = new StringBuilder();
            for (Element el:
                    sspHandler.beginCssLikeSelection().domCssLikeSelectNodeSet(buildCssQueryFromAriaLabelledByAttr(element.attr(ARIA_LABELLEDBY_ATTR)))
                        .getSelectedElements()) {
                elementText.append(new DeepTextElementBuilder().buildTextFromElement(el));
            }
            if (StringUtils.isNotBlank(elementText)) {
                return elementText.toString();
            }
        }
        if (StringUtils.isNotBlank(element.attr(ARIA_LABEL_ATTR))) {
            return element.attr(ARIA_LABEL_ATTR);
        }
        String text = getElementBuilderFromElementType(element).buildTextFromElement(element);
        return StringUtils.isNotBlank(text)? text : ABSENT_ATTRIBUTE_VALUE;
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

    private TextElementBuilder getElementBuilderFromElementType(Element element) {
        switch (element.tagName()) {
            case "a":
            case "svg":
            case "canvas":
                return new DeepTextWithoutAltElementBuilder();
            case "img":
                return new TextAttributeOfElementBuilder(true, ALT_ATTR, TITLE_ATTR);
            case "area":
                return new TextAttributeOfElementBuilder(ALT_ATTR);
            case "input":
                if (hasTypeImageAttribute(element)) {
                    return new TextAttributeOfElementBuilder(true, ALT_ATTR, TITLE_ATTR);
                }
            case "object":
            case "embed":
                if (hasTypeImageAttribute(element)) {
                    return new TextAttributeOfElementBuilder(TITLE_ATTR);
                }
            default:
                if (hasRoleAttrWithValue(element, "link")) {
                    return new DeepTextWithoutAltElementBuilder();
                }
                return new EmptyStringElementBuilder();
        }
    }

    private boolean hasTypeImageAttribute(Element element) {
        return element.hasAttr(TYPE_ATTR) && element.attr(TYPE_ATTR).equals("image");
    }

    private boolean hasRoleAttrWithValue(Element element, String roleValue) {
        return element.hasAttr(ROLE_ATTR) && element.attr(ROLE_ATTR).equals(roleValue);
    }
}
