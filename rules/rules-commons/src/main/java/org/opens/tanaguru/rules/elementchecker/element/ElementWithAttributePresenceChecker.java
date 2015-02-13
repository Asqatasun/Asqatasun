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

package org.opens.tanaguru.rules.elementchecker.element;

import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;

/**
 * This class checks whether an element is present on the page.
 * The testSolution when at least one element is detected can be overridden 
 * by constructor argument (default is failed).
 * The testSolution when no element is detected can be overridden 
 * by constructor argument (default is passed).
 * 
 */
public class ElementWithAttributePresenceChecker extends ElementPresenceChecker {

    /**
     * The name of the attribute handled by the element
     */
    private final String attributeName;

    /**
     * Constructor.
     * 
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     * @param attributeName
     */
    public ElementWithAttributePresenceChecker(
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected, 
            String attributeName) {
        super(messageCodeOnElementDetected, messageCodeOnElementNotDetected);
        this.attributeName = attributeName;
    }
    
    /**
     * Constructor.
     * 
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     * @param attributeName
     * @param eeAttributeNameList 
     */
    public ElementWithAttributePresenceChecker(
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected, 
            String attributeName,
            String... eeAttributeNameList) {
        super(
                messageCodeOnElementDetected, 
                messageCodeOnElementNotDetected, 
                eeAttributeNameList);
        this.attributeName = attributeName;
    }
    
    /**
     * Constructor.
     * 
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     * @param attributeName
     */
    public ElementWithAttributePresenceChecker(
            TestSolution detectedSolution,
            TestSolution notDetectedSolution, 
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected, 
            String attributeName) {
        super(
                detectedSolution, 
                notDetectedSolution, 
                messageCodeOnElementDetected, 
                messageCodeOnElementNotDetected);
        this.attributeName = attributeName;
    }
    
    /**
     * Constructor.
     * 
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     * @param attributeName 
     * @param eeAttributeNameList 
     */
    public ElementWithAttributePresenceChecker(
            TestSolution detectedSolution,
            TestSolution notDetectedSolution, 
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected, 
            String attributeName,
            String... eeAttributeNameList) {
        super(
                detectedSolution, 
                notDetectedSolution, 
                messageCodeOnElementDetected, 
                messageCodeOnElementNotDetected, 
                eeAttributeNameList);
        this.attributeName = attributeName;
    }

    @Override
    protected void createSourceCodeRemark(TestSolution testSolution, Element element, String message) {
        addSourceCodeRemarkOnAttribute(testSolution, element, message, attributeName);
    }

}