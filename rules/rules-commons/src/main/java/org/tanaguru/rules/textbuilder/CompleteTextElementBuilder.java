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
import org.tanaguru.rules.keystore.AttributeStore;

/**
 * This implementation of the {@link TextualElementBuilder} extracts all
 * the textual info of an element.
 */
public class CompleteTextElementBuilder implements TextElementBuilder{
    
    /**
     * The textual content of an element can be composed with :
     * <ul>
     *     <li> The text of the element</li>
     *     <li> The text of the alt attribute of the element</li>
     *     <li> The text of the title attribute of the element</li>
     *     <li> The text of the summary attribute of the element</li>
     *     <li> The text of the value attribute of the element</li>
     *     <li> The text of the content attribute of the element when 
     *          the value of the name attribute is "description"
     *      </li>
     * </ul>
     * 
     * @param element
     * @return the textual content of an element
     */
    @Override
    public String buildTextFromElement(Element element) {
        StringBuilder strb = new StringBuilder();
        if (StringUtils.isNotBlank(element.ownText())) {
            strb.append(SPACER);
            strb.append(element.ownText().trim());
        }
        
        strb.append(getTextualContentOfAttribute(element, AttributeStore.ALT_ATTR));
        strb.append(getTextualContentOfAttribute(element, AttributeStore.TITLE_ATTR));
        strb.append(getTextualContentOfAttribute(element, AttributeStore.SUMMARY_ATTR));
        strb.append(getTextualContentOfAttribute(element, AttributeStore.VALUE_ATTR));
        
        if (element.hasAttr(AttributeStore.CONTENT_ATTR) && 
                element.hasAttr(AttributeStore.NAME_ATTR) && 
                StringUtils.equalsIgnoreCase(element.attr(AttributeStore.NAME_ATTR), "description") && 
                StringUtils.isNotBlank(element.attr(AttributeStore.CONTENT_ATTR))) {
            strb.append(SPACER);
            strb.append(getTextualContentOfAttribute(element, AttributeStore.CONTENT_ATTR));
        }
        return StringUtils.trim(strb.toString());
    }
    
    /**
     * 
     * @param element
     * @param attributeName
     * @return the textual content of an attribute
     */
    private String getTextualContentOfAttribute(Element element, String attributeName) {
        if (element.hasAttr(attributeName)) {
            return SPACER + StringUtils.trim(element.attr(attributeName));
        }
        return "";
    }

}