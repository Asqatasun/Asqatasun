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
public class AttributeOnlyContainsNonAlphanumericalCharactersChecker 
        extends TextElementOnlyContainsNonAlphanumericalCharactersChecker {

    /**
     * The attribute name to test
     */
    private String attributeName;
    
    /**
     * Constructor
     * @param attributeName
     * @param textOnlyContainsNacMsgCode
     */
    public AttributeOnlyContainsNonAlphanumericalCharactersChecker(
            String attributeName,
            String textOnlyContainsNacMsgCode) {
        super(textOnlyContainsNacMsgCode);
        this.attributeName = attributeName;
    }
    
    /**
     * Constructor
     * @param attributeName
     * @param textOnlyContainsNacMsgCode
     * @param eeAttributeNameList 
     */
    public AttributeOnlyContainsNonAlphanumericalCharactersChecker(
            String attributeName,
            String textOnlyContainsNacMsgCode,
            String... eeAttributeNameList) {
        super(textOnlyContainsNacMsgCode, eeAttributeNameList);
        this.attributeName = attributeName;
    }
    
    /**
     * Constructor
     * @param attributeName
     * @param detectedSolution
     * @param textOnlyContainsNacMsgCode
     */
    public AttributeOnlyContainsNonAlphanumericalCharactersChecker(
            String attributeName,
            TestSolution detectedSolution,
            String textOnlyContainsNacMsgCode) {
        super(detectedSolution, textOnlyContainsNacMsgCode);
        this.attributeName = attributeName;
    }
    
    /**
     * Constructor
     * @param attributeName
     * @param detectedSolution
     * @param textOnlyContainsNacMsgCode
     * @param eeAttributeNameList 
     */
    public AttributeOnlyContainsNonAlphanumericalCharactersChecker(
            String attributeName,
            TestSolution detectedSolution,
            String textOnlyContainsNacMsgCode,
            String... eeAttributeNameList) {
        super(detectedSolution, textOnlyContainsNacMsgCode, eeAttributeNameList);
        this.attributeName = attributeName;
    }
    
    @Override
    public String buildTextFromElement(Element element) {
        return getAttributeContent(element, attributeName);
    }

}