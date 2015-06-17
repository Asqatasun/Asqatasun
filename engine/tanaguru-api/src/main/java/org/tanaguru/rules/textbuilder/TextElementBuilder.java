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

import org.jsoup.nodes.Element;

/**
 * The builder in charge of creating a textual representation of an HTML element.
 * May be the text of the element, the value of an attribute of the element, 
 * the concatenation of multiple values...
 */
public interface TextElementBuilder {

    /* space needed from string concatenation */
    char SPACER = ' ';
    
    /**
     * @param element
     * @return a textual representation of the element
     */
    String buildTextFromElement(Element element);
    
}
