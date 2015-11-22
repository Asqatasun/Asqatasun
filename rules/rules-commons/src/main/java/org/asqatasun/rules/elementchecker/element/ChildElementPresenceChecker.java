/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2015  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
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

package org.asqatasun.rules.elementchecker.element;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementCheckerImpl;

/**
 * This class checks whether an element contains a child element of a given type.
 * The testSolution when at least one element is detected can be overridden 
 * by constructor argument (default is FAILED).
 * The testSolution when no element is detected can be overridden 
 * by constructor argument (default is PASSED).
 * The creation of {@link sourceCodeRemark} for each detected element is 
 * optional and may be overridden by constructor argument (default is true).
 * 
 */
public class ChildElementPresenceChecker extends ElementCheckerImpl {

    /**
     * The child elements to search
     */
    private final Collection<String> childElementNames = new ArrayList<>();
    
    /**
     * The minimum number of children required. Default is 8
     */
    private int minimumNumberOfChildRequired = 1;
    
    /**
     * Constructor.
     * 
     * @param childElementName
     * @param detectedSolutionPair
     * @param notDetectedSolutionPair
     * @param eeAttributeNameList
     */
    public ChildElementPresenceChecker(
            String childElementName,
            Pair<TestSolution, String> detectedSolutionPair,
            Pair<TestSolution, String> notDetectedSolutionPair, 
            String... eeAttributeNameList) {
        super(detectedSolutionPair, notDetectedSolutionPair, eeAttributeNameList);
        this.childElementNames.add(childElementName);
    }
    
    /**
     * @Deprecated 
     * Use constructor with Pair instead
     * 
     * @param childElementName
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     * @param eeAttributeNameList
     */
    public ChildElementPresenceChecker(
            String childElementName,
            TestSolution detectedSolution,
            TestSolution notDetectedSolution, 
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected,
            String... eeAttributeNameList) {
        this(childElementName,
             new ImmutablePair(detectedSolution,messageCodeOnElementDetected),
             new ImmutablePair(notDetectedSolution,messageCodeOnElementNotDetected),
             eeAttributeNameList);
    }
    
    /**
     * Constructor.
     * 
     * @param childElementName
     * @param minimumNumberOfChildRequired
     * @param detectedSolutionPair
     * @param notDetectedSolutionPair
     * @param eeAttributeNameList 
     */
    public ChildElementPresenceChecker(
            String childElementName,
            int minimumNumberOfChildRequired,
            Pair<TestSolution,String> detectedSolutionPair,
            Pair<TestSolution,String> notDetectedSolutionPair, 
            String... eeAttributeNameList) {
        this(childElementName, detectedSolutionPair, notDetectedSolutionPair, eeAttributeNameList);
        this.minimumNumberOfChildRequired = minimumNumberOfChildRequired;
    }
    
    /**
     * @Deprecated 
     * Use constructor with Pair instead
     * 
     * @param childElementName
     * @param minimumNumberOfChildRequired
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     * @param eeAttributeNameList 
     */
    public ChildElementPresenceChecker(
            String childElementName,
            int minimumNumberOfChildRequired,
            TestSolution detectedSolution,
            TestSolution notDetectedSolution, 
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected, 
            String... eeAttributeNameList) {
        this(childElementName,
             minimumNumberOfChildRequired,
             new ImmutablePair(detectedSolution,messageCodeOnElementDetected),
             new ImmutablePair(notDetectedSolution,messageCodeOnElementNotDetected),
             eeAttributeNameList);
    }
    
    @Override
    public void doCheck(
            SSPHandler sspHandler, 
            Elements elements, 
            TestSolutionHandler testSolutionHandler) {
        for(String elementToTest : childElementNames) {
            checkChildElementPresence(elementToTest, elements, testSolutionHandler);
        }
    }

    /**
     * This methods checks whether elements have a child element of a given 
     * type.
     * 
     * @param elementToTest
     * @param elements
     * @param testSolutionHandler 
     */
    private void checkChildElementPresence (
            String elementToTest,
            Elements elements, 
            TestSolutionHandler testSolutionHandler) {
        if (elements.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }

        TestSolution testSolution = TestSolution.PASSED;

        for (Element el : elements) {

            if (el.getElementsByTag(elementToTest).size()>=minimumNumberOfChildRequired) {
                
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