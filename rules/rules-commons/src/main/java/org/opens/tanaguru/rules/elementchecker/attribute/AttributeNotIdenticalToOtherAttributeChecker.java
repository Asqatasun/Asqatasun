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
 *
 */
public class AttributeNotIdenticalToOtherAttributeChecker 
            extends TextElementNotIdenticalToAttributeChecker {

    /**
     * The attribute name to test
     */
    private String attributeName;
    
    /**
     * Constructor.
     * 
     * @param attributeName
     * @param attributeNameToCompareWith
     * @param textElementIdenticalToAttributeValueMessageCode
     */
    public AttributeNotIdenticalToOtherAttributeChecker(
            String attributeName, 
            String attributeNameToCompareWith, 
            String textElementIdenticalToAttributeValueMessageCode) {
        super(
                attributeNameToCompareWith, 
                textElementIdenticalToAttributeValueMessageCode);
        this.attributeName = attributeName;
    }
    
    /**
     * Constructor.
     * 
     * @param attributeName
     * @param attributeNameToCompareWith
     * @param textElementIdenticalToAttributeValueMessageCode
     * @param eeAttributeNameList 
     */
    public AttributeNotIdenticalToOtherAttributeChecker(
            String attributeName, 
            String attributeNameToCompareWith, 
            String textElementIdenticalToAttributeValueMessageCode, 
            String... eeAttributeNameList) {
        super(
                attributeNameToCompareWith, 
                textElementIdenticalToAttributeValueMessageCode, 
                eeAttributeNameList);
        this.attributeName = attributeName;
    }
    
    /**
     * Constructor.
     * 
     * @param attributeName
     * @param attributeNameToCompareWith
     * @param detectedSolution
     * @param textElementIdenticalToAttributeValueMessageCode
     */
    public AttributeNotIdenticalToOtherAttributeChecker(
            String attributeName, 
            String attributeNameToCompareWith, 
            TestSolution detectedSolution,
            String textElementIdenticalToAttributeValueMessageCode) {
        super(
                attributeNameToCompareWith, 
                detectedSolution,
                textElementIdenticalToAttributeValueMessageCode);
        this.attributeName = attributeName;
    }
    
    /**
     * Constructor.
     * 
     * @param attributeName
     * @param attributeNameToCompareWith
     * @param detectedSolution
     * @param textElementIdenticalToAttributeValueMessageCode
     * @param eeAttributeNameList 
     */
    public AttributeNotIdenticalToOtherAttributeChecker(
            String attributeName, 
            String attributeNameToCompareWith, 
            TestSolution detectedSolution,
            String textElementIdenticalToAttributeValueMessageCode, 
            String... eeAttributeNameList) {
        super(
                attributeNameToCompareWith, 
                detectedSolution,
                textElementIdenticalToAttributeValueMessageCode, 
                eeAttributeNameList);
        this.attributeName = attributeName;
    }
    
    @Override
    public String buildTextFromElement(Element element) {
        return getAttributeContent(element, attributeName);
    }
    
}