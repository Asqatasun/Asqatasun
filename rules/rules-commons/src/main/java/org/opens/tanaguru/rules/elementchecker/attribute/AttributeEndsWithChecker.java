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

import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;

/**
 * This checker controls whether the content of an attribute is suffixed by
 * one the values belonging the nomenclature.
 */
public class AttributeEndsWithChecker 
                    extends TextElementEndsWithChecker  {

    /**
     * the attribute the test is about
     */
    private String attributeName;
    
    /**
     * Constructor
     * @param attributeName
     * @param extensionNomName
     * @param textEndsWithMessageCode
     */
    public AttributeEndsWithChecker(
            String attributeName,
            String extensionNomName,
            String textEndsWithMessageCode) {
        super(extensionNomName, textEndsWithMessageCode);
        this.attributeName = attributeName;
    }
    
    /**
     * Constructor
     * @param attributeName
     * @param extensionNomName
     * @param textEndsWithMessageCode
     * @param eeAttributeNameList 
     */
    public AttributeEndsWithChecker(
            String attributeName,
            String extensionNomName,
            String textEndsWithMessageCode,
            String... eeAttributeNameList) {
        super(extensionNomName, textEndsWithMessageCode, eeAttributeNameList);
        this.attributeName = attributeName;
    }
    
    /**
     * Constructor
     * @param attributeName
     * @param extensionNomName
     * @param detectedSolution
     * @param textEndsWithMessageCode
     * @param eeAttributeNameList 
     */
    public AttributeEndsWithChecker(
            String attributeName,
            String extensionNomName,
            TestSolution detectedSolution,
            String textEndsWithMessageCode,
            String... eeAttributeNameList) {
        super(extensionNomName, detectedSolution, textEndsWithMessageCode, eeAttributeNameList);
        this.attributeName = attributeName;
    }

    @Override
    public String buildTextFromElement(Element element) {
        return element.attr(attributeName).trim();
    }
    
}