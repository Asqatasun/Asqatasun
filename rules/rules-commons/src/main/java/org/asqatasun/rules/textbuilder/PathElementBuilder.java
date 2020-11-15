/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2020  Asqatasun.org
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

import org.jsoup.nodes.Element;

import static org.asqatasun.rules.keystore.HtmlElementStore.BODY_ELEMENT;
import static org.asqatasun.rules.keystore.HtmlElementStore.HTML_ELEMENT;

/**
 * This implementation of the {@link TextualElementBuilder} simply extract the element
 * position on the DOM.
 */
public class PathElementBuilder implements TextElementBuilder {

    private boolean isAbsolute;

    /**
     * Default constructor
     *
     * @param isAbsolute
     */
    public PathElementBuilder(boolean isAbsolute) {
        this.isAbsolute = isAbsolute;
    }

    @Override
    public String buildTextFromElement(Element element) {
        if (element == null) {
            return null;
        }
        if (isAbsolute) {
            return getAbsolutePathFromElement(element);
        }
        return "";
    }

    private String getAbsolutePathFromElement(Element element) {
        if (element.tagName().equalsIgnoreCase(HTML_ELEMENT)) {
            return "";
        }
        if (!element.tagName().equalsIgnoreCase(BODY_ELEMENT)) {
            int i = 0;
            Element currentElement = element;
            while (currentElement.previousElementSibling() != null) {
                currentElement = currentElement.previousElementSibling();
                i++;
            }
            return getAbsolutePathFromElement(element.parent()) + " > " + element.tagName() + ":eq(" + i + ")";
        } else {
            return BODY_ELEMENT;
        }
    }

}
