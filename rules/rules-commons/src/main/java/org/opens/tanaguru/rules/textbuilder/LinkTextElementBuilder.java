/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2013  Open-S Company
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
 *  Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.rules.textbuilder;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import static org.opens.tanaguru.rules.keystore.AttributeStore.ALT_ATTR;

/**
 * This implementation of the {@link TextualElementBuilder} simply extracts the 
 * text of an element.
 */
public class LinkTextElementBuilder implements TextElementBuilder{

    private TextElementBuilder altAttrTextBuilder = 
            new TextAttributeOfElementBuilder(ALT_ATTR);
    
    @Override
    public String buildTextFromElement(Element element) {
        StringBuilder linkText = new StringBuilder();
        for (Node child : element.childNodes()) {
            if (child instanceof TextNode && !((TextNode)child).isBlank()) {
               linkText.append(SPACER);
               linkText.append(StringUtils.trim(((TextNode)child).text()));
            } else if (child instanceof Element){
                linkText.append(SPACER);
                linkText.append(altAttrTextBuilder.buildTextFromElement((Element)child));
                linkText.append(buildTextFromElement((Element)child));
            }
        }
        return StringUtils.trim(linkText.toString());
    }
    
}