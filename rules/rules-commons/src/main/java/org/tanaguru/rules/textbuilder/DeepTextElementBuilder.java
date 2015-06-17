/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
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
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.rules.textbuilder;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import static org.tanaguru.rules.keystore.AttributeStore.ALT_ATTR;

/**
 * This implementation of the {@link TextualElementBuilder} extracts the 
 * text of an element by calling recursively the tag children and by adding
 * the content of the alt attribute of tags when they exists.
 */
public class DeepTextElementBuilder implements TextElementBuilder{

    private TextElementBuilder altAttrTextBuilder = 
            new TextAttributeOfElementBuilder(ALT_ATTR);
    
    @Override
    public String buildTextFromElement(Element element) {
        StringBuilder elementText = new StringBuilder();
        if (element.hasAttr(ALT_ATTR)) {
            elementText.append(SPACER);
            elementText.append(altAttrTextBuilder.buildTextFromElement(element));
        }
        for (Node child : element.childNodes()) {
            if (child instanceof TextNode && !((TextNode)child).isBlank()) {
               elementText.append(SPACER);
               elementText.append(StringUtils.trim(((TextNode)child).text()));
            } else if (child instanceof Element){
                elementText.append(SPACER);
                elementText.append(buildTextFromElement((Element)child));
            }
        }
        return StringUtils.trim(elementText.toString());
    }
    
}