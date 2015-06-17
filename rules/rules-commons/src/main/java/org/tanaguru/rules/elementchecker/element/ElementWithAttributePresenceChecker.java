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

package org.tanaguru.rules.elementchecker.element;

import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.nodes.Element;
import org.tanaguru.entity.audit.TestSolution;

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
     * @param detectedSolutionPair
     * @param notDetectedSolutionPair
     * @param attributeName 
     * @param eeAttributeNameList 
     */
    public ElementWithAttributePresenceChecker(
            Pair<TestSolution,String> detectedSolutionPair,
            Pair<TestSolution,String> notDetectedSolutionPair, 
            String attributeName,
            String... eeAttributeNameList) {
        super(
                detectedSolutionPair, 
                notDetectedSolutionPair, 
                eeAttributeNameList);
        this.attributeName = attributeName;
    }
    
    @Override
    protected void createSourceCodeRemark(TestSolution testSolution, Element element, String message) {
        addSourceCodeRemarkOnAttribute(testSolution, element, message, attributeName);
    }

}