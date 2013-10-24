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

/**
 * This implementation of the {@link TextualElementBuilder} extracts the text
 * content of a given attribute of an element. The attribute type is settable by
 * contructor argument
 */
public class TextAttributeOfElementBuilder implements TextElementBuilder {

    /* the attribute name*/
    private String attributeName;
    public String getAttributeName() {
        return attributeName;
    }
    
    public final void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    /**
     * Constructor
     */
    public TextAttributeOfElementBuilder() {
        super();
    }
    
    /**
     * Constructor
     * @param attributeName 
     */
    public TextAttributeOfElementBuilder(String attributeName) {
        setAttributeName(attributeName);
    }

    /**
     * 
     * @param element
     * @return the content of the attribute when it exits, null instead.
     */
    @Override
    public String buildTextFromElement(Element element) {
        if (attributeName == null) {
            return null;
        }
        if (element.hasAttr(attributeName)) {
            return StringUtils.trim(element.attr(attributeName));
        } else {
            return null;
        }
    }

}