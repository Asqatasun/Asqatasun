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
 * This checker controls whether the content of an attribute is empty.
 * 
 */
public class AttributeEmptinessChecker extends TextEmptinessChecker {

    /**
     * the attribute the test is about
     */
    private String attributeName;
    
    /**
     * Default constructor 
     * 
     * @param attributeName
     * @param messageCodeOnAttrDetected
     * @param messageCodeOnAttrNotDetected
     */
    public AttributeEmptinessChecker(
            String attributeName, 
            String messageCodeOnAttrEmpty, 
            String messageCodeOnAttrNotEmpty) {
        super(messageCodeOnAttrEmpty, messageCodeOnAttrNotEmpty);
        this.attributeName = attributeName;
    }
    
    /**
     * Default constructor 
     * 
     * @param attributeName
     * @param messageCodeOnAttrDetected
     * @param messageCodeOnAttrNotDetected
     * @param eeAttributeNameList 
     */
    public AttributeEmptinessChecker(
            String attributeName, 
            String messageCodeOnAttrEmpty, 
            String messageCodeOnAttrNotEmpty, 
            String... eeAttributeNameList) {
        super(messageCodeOnAttrEmpty, messageCodeOnAttrNotEmpty, eeAttributeNameList);
        this.attributeName = attributeName;
    }
    
    /**
     * Default constructor 
     * 
     * @param attributeName
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnAttrDetected
     * @param messageCodeOnAttrNotDetected
     */
    public AttributeEmptinessChecker(
            String attributeName, 
            TestSolution attrEmptySolution,
            TestSolution attrNotEmptySolution,
            String messageCodeOnAttrEmpty, 
            String messageCodeOnAttrNotEmpty) {
        super(
                attrEmptySolution,
                attrNotEmptySolution,
                messageCodeOnAttrEmpty,
                messageCodeOnAttrNotEmpty);
        this.attributeName = attributeName;
    }
    
    /**
     * Default constructor 
     * 
     * @param attributeName
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnAttrDetected
     * @param messageCodeOnAttrNotDetected
     * @param eeAttributeNameList 
     */
    public AttributeEmptinessChecker(
            String attributeName, 
            TestSolution attrEmptySolution,
            TestSolution attrNotEmptySolution,
            String messageCodeOnAttrEmpty, 
            String messageCodeOnAttrNotEmpty, 
            String... eeAttributeNameList) {
        super(
                attrEmptySolution,
                attrNotEmptySolution,
                messageCodeOnAttrEmpty,
                messageCodeOnAttrNotEmpty,
                eeAttributeNameList);
        this.attributeName = attributeName;
    }
    
    @Override
    public boolean isElementEmpty(Element el) {
        return el.hasAttr(attributeName) && StringUtils.isBlank(el.attr(attributeName));
    }
    
}