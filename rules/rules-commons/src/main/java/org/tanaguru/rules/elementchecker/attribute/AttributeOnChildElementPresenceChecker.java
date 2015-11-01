/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2015  Asqatasun.org
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
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.tanaguru.rules.elementchecker.attribute;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.ElementCheckerImpl;

/**
 * This class checks whether an element contains a child element with a given attribute.
 * The testSolution when at least one element is detected can be overridden 
 * by constructor argument (default is FAILED).
 * The testSolution when no element is detected can be overridden 
 * by constructor argument (default is PASSED).
 * The creation of {@link sourceCodeRemark} for each detected element is 
 * optional and may be overridden by constructor argument (default is true).
 * 
 */
public class AttributeOnChildElementPresenceChecker extends ElementCheckerImpl {

    /**
     * the attribute the test is about
     */
    private final String attributeName;
    
    /**
     * 
     * @param attributeName
     * @param detectedSolutionPair
     * @param notDetectedSolutionPair
     * @param eeAttributeNameList 
     */
    public AttributeOnChildElementPresenceChecker(
            String attributeName, 
            Pair<TestSolution,String> detectedSolutionPair,
            Pair<TestSolution,String> notDetectedSolutionPair,
            String...eeAttributeNameList) {
        super(detectedSolutionPair, notDetectedSolutionPair,eeAttributeNameList);
        this.attributeName = attributeName;
    }
    
    @Override
    public void doCheck(
            SSPHandler sspHandler, 
            Elements elements, 
            TestSolutionHandler testSolutionHandler) {
        checkChildElementWithAttributePresence(elements, testSolutionHandler);
        
    }

    /**
     * This methods checks whether elements have a child element of with a given 
     * attribute.
     * 
     * @param elements
     * @param testSolutionHandler 
     */
    private void checkChildElementWithAttributePresence (
            Elements elements, 
            TestSolutionHandler testSolutionHandler) {
        if (elements.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }

        TestSolution testSolution = TestSolution.PASSED;

        for (Element el : elements) {

            if (!el.getElementsByAttribute(attributeName).isEmpty()) {

                testSolution = setTestSolution(testSolution, getSuccessSolution());
                addSourceCodeRemark(getSuccessSolution(),el, getSuccessMsgCode());
                
            } else {
                
                testSolution = setTestSolution(testSolution, getFailureSolution());
                addSourceCodeRemark(getFailureSolution(),el, getFailureMsgCode());

            }
        }

        testSolutionHandler.addTestSolution(testSolution);
    }

}