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
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import static org.asqatasun.rules.keystore.AttributeStore.ALT_ATTR;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;

/**
 * This implementation of the {@link TextualElementBuilder} extracts the 
 * text of an element by calling recursively the tag children and by adding
 * the content of the alt attribute of tags when they exists.
 */
public class DeepTextWithoutAltElementBuilder implements TextElementBuilder{

    @Override
    public String buildTextFromElement(Element element) {
        StringBuilder elementText = new StringBuilder();
        for (Node child : element.childNodes()) {
            if (child instanceof TextNode && !((TextNode)child).isBlank()) {
               elementText.append(SPACER);
               elementText.append(StringUtils.trim(((TextNode)child).text()));
            } else if (child instanceof Element){
                elementText.append(SPACER);
                elementText.append(buildTextFromElement((Element)child));
            }
        }
        if (StringUtils.isBlank(StringUtils.trim(elementText.toString()))) {
            if (StringUtils.isNotBlank(element.attr(TITLE_ATTR))) {
                return element.attr(TITLE_ATTR);
            }
        }
        return elementText.toString();
    }
    
}
