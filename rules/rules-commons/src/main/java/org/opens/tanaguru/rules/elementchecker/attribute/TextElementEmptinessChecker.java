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

package org.opens.tanaguru.rules.elementchecker.attribute;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;

/**
 * This checker controls whether the content of an element is empty.
  */
public class TextElementEmptinessChecker extends TextEmptinessChecker {

    /**
     * Constructor.
     * Returns FAILED when the text is empty and PASSED when it is not.
     * 
     * @param messageCodeOnTextEmpty
     * @param messageCodeOnTextNotEmpty
     * @param eeAttributeNameList 
     */
    public TextElementEmptinessChecker(
            String messageCodeOnTextEmpty, 
            String messageCodeOnTextNotEmpty, 
            String... eeAttributeNameList) {
        super(messageCodeOnTextEmpty, messageCodeOnTextNotEmpty, eeAttributeNameList);
    }
    
    /**
     * Constructor 
     * 
     * @param textEmptySolution
     * @param textNotEmptySolution
     * @param messageCodeOnTextEmpty
     * @param messageCodeOnTextNotEmpty
     * @param eeAttributeNameList 
     */
    public TextElementEmptinessChecker(
            TestSolution textEmptySolution,
            TestSolution textNotEmptySolution,
            String messageCodeOnTextEmpty, 
            String messageCodeOnTextNotEmpty, 
            String... eeAttributeNameList) {
        super(
                textEmptySolution,
                textNotEmptySolution,
                messageCodeOnTextEmpty,
                messageCodeOnTextNotEmpty,
                eeAttributeNameList);
    }
    
    @Override
    public boolean isElementEmpty(Element el) {
        return StringUtils.isBlank(el.text());
    }
    
}