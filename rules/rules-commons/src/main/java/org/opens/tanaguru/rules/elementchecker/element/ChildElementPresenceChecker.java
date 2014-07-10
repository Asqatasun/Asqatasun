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

package org.opens.tanaguru.rules.elementchecker.element;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementCheckerImpl;

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
     * Not detected solution. Default is PASSED.
     */
    private TestSolution notDetectedSolution = TestSolution.PASSED;

    /**
     * Detected solution. Default is FAILED.
     */
    private TestSolution detectedSolution = TestSolution.FAILED;

    /**
     * The message code associated with a processRemark when the element is
     * detected on the page
     */
    private final String messageCodeOnElementDetected;
    
    /**
     * The message code associated with a processRemark when the element is
     * not found on the page
     */
    private final String messageCodeOnElementNotDetected;
    
    /**
     * Constructor.
     * 
     * @param childElementName
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     */
    public ChildElementPresenceChecker(
            String childElementName,
            TestSolution detectedSolution,
            TestSolution notDetectedSolution, 
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected) {
        super();
        this.childElementNames.add(childElementName);
        this.detectedSolution = detectedSolution;
        this.messageCodeOnElementDetected = messageCodeOnElementDetected;
        this.notDetectedSolution = notDetectedSolution;
        this.messageCodeOnElementNotDetected = messageCodeOnElementNotDetected;
    }
    
    /**
     * Constructor.
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
        super(eeAttributeNameList);
        this.childElementNames.add(childElementName);
        this.detectedSolution = detectedSolution;
        this.messageCodeOnElementDetected = messageCodeOnElementDetected;
        this.notDetectedSolution = notDetectedSolution;
        this.messageCodeOnElementNotDetected = messageCodeOnElementNotDetected;
    }
    
    /**
     * Constructor.
     * 
     * @param childElementName
     * @param minimumNumberOfChildRequired
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     */
    public ChildElementPresenceChecker(
            String childElementName,
            int minimumNumberOfChildRequired,
            TestSolution detectedSolution,
            TestSolution notDetectedSolution, 
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected) {
        super();
        this.childElementNames.add(childElementName);
        this.minimumNumberOfChildRequired = minimumNumberOfChildRequired;
        this.detectedSolution = detectedSolution;
        this.messageCodeOnElementDetected = messageCodeOnElementDetected;
        this.notDetectedSolution = notDetectedSolution;
        this.messageCodeOnElementNotDetected = messageCodeOnElementNotDetected;
    }
    
    /**
     * Constructor.
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
        super(eeAttributeNameList);
        this.childElementNames.add(childElementName);
        this.minimumNumberOfChildRequired = minimumNumberOfChildRequired;
        this.detectedSolution = detectedSolution;
        this.messageCodeOnElementDetected = messageCodeOnElementDetected;
        this.notDetectedSolution = notDetectedSolution;
        this.messageCodeOnElementNotDetected = messageCodeOnElementNotDetected;
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

                testSolution = setTestSolution(testSolution, detectedSolution);
                
                if (StringUtils.isNotBlank(messageCodeOnElementDetected)) {
                    
                    addSourceCodeRemark(
                            detectedSolution,
                            el, 
                            messageCodeOnElementDetected);
                }
                
            } else {
                
                testSolution = setTestSolution(testSolution, notDetectedSolution);
                
                if (StringUtils.isNotBlank(messageCodeOnElementNotDetected)) {
                    
                    addSourceCodeRemark(
                            notDetectedSolution,
                            el, 
                            messageCodeOnElementNotDetected);
                }

            }
        }

        testSolutionHandler.addTestSolution(testSolution);
    }

}