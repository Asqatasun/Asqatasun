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
import static org.asqatasun.rules.keystore.HtmlElementStore.A_ELEMENT;

/**
 * This implementation of the {@link TextualElementBuilder} extracts the 
 * text of a link element by calling recursively the tag children and by adding
 * the content of the alt attribute of tags when they exists.
 */
public class LinkTextRgaa4ElementBuilder extends DeepTextElementBuilder{

    private SSPHandler sspHandler;
    public void setSspHandler(SSPHandler sspHandler) {
        this.sspHandler = sspHandler;
    }

    public LinkTextRgaa4ElementBuilder() {
        super();
    }

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
        return getElementBuilderFromElementType(element).buildTextFromElement(element);
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
        if (element.tagName().equalsIgnoreCase(A_ELEMENT)) {
            return new DeepTextWithoutAltElementBuilder();
        } else {
            return new DeepTextWithoutAltElementBuilder();
        }
    }

}
