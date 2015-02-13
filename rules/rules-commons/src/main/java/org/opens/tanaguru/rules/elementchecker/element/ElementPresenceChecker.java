/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015 Tanaguru.org
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

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementCheckerImpl;

/**
 * This class checks whether an element is present on the page.
 * The testSolution when at least one element is detected can be overridden 
 * by constructor argument (default is failed).
 * The testSolution when no element is detected can be overridden 
 * by constructor argument (default is passed).
 * The creation of {@link sourceCodeRemark} for each detected element is 
 * optional and may be overridden by constructor argument (default is true).
 *
 */
public class ElementPresenceChecker extends ElementCheckerImpl {

    public static boolean DEFAULT_CHECK_UNICITY = false;
    /**
     * Determine whether the element has to be present and unique.
     */
    private boolean checkUnicity = DEFAULT_CHECK_UNICITY;
    public void setCheckUnicity(boolean checkUnicity) {
        this.checkUnicity = checkUnicity;
    }
    
    public static TestSolution DEFAULT_NOT_DETECTED_SOLUTION = TestSolution.PASSED;
    public static TestSolution DEFAULT_DETECTED_SOLUTION = TestSolution.FAILED;
    
    /**
     * Not detected solution. Default is PASSED.
     */
    private TestSolution notDetectedSolution = DEFAULT_NOT_DETECTED_SOLUTION;
    public void setNotDetectedSolution(TestSolution notDetectedSolution) {
        this.notDetectedSolution = notDetectedSolution;
    }

    /**
     * Detected solution. Default is FAILED.
     */
    private TestSolution detectedSolution = DEFAULT_DETECTED_SOLUTION;
    public void setDetectedSolution(TestSolution detectedSolution) {
        this.detectedSolution = detectedSolution;
    }
        
    /**
     * The message code associated with a processRemark when the element is
     * detected on the page
     */
    private String messageCodeOnElementDetected;
    public void setMessageCodeOnElementDetected(String messageCodeOnElementDetected) {
        this.messageCodeOnElementDetected = messageCodeOnElementDetected;
    }
    
    /**
     * The message code associated with a processRemark when the element is
     * not found on the page
     */
    private String messageCodeOnElementNotDetected;
    public void setMessageCodeOnElementNotDetected(String messageCodeOnElementNotDetected) {
        this.messageCodeOnElementNotDetected = messageCodeOnElementNotDetected;
    }
    
    /**
     * The message code associated with a sourceCodeRemark when several elements
     * are detected and the checkUnicity state is set to true
     */
    private String messageCodeOnMultipleElements;
    public void setMessageCodeOnMultipleElements(String messageCodeOnMultipleElements) {
        this.messageCodeOnMultipleElements = messageCodeOnMultipleElements;
    }

    /**
     * 
     */
    public ElementPresenceChecker(){}
    
    /**
     * Constructor.
     *
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     * @param eeAttributeNameList
     */
    public ElementPresenceChecker(
            String messageCodeOnElementDetected,
            String messageCodeOnElementNotDetected,
            String... eeAttributeNameList) {
        this(DEFAULT_CHECK_UNICITY,
             DEFAULT_DETECTED_SOLUTION, 
             DEFAULT_NOT_DETECTED_SOLUTION,
             messageCodeOnElementDetected,
             messageCodeOnElementNotDetected,
             null,
             eeAttributeNameList);
    }

    /**
     * Constructor.
     *
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     * @param eeAttributeNameList
     */
    public ElementPresenceChecker(
            TestSolution detectedSolution,
            TestSolution notDetectedSolution,
            String messageCodeOnElementDetected,
            String messageCodeOnElementNotDetected,
            String... eeAttributeNameList) {
        this(DEFAULT_CHECK_UNICITY,
             detectedSolution, 
             notDetectedSolution,
             messageCodeOnElementDetected,
             messageCodeOnElementNotDetected,
             null,
             eeAttributeNameList);
    }

    /**
     * Constructor.
     *
     * @param checkUnicity
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     * @param messageCodeOnMultipleElements
     * @param eeAttributeNameList
     */
    public ElementPresenceChecker(
            boolean checkUnicity,
            String messageCodeOnElementDetected,
            String messageCodeOnElementNotDetected,
            String messageCodeOnMultipleElements,
            String... eeAttributeNameList) {
        this(checkUnicity, 
             DEFAULT_DETECTED_SOLUTION, 
             DEFAULT_NOT_DETECTED_SOLUTION,
             messageCodeOnElementDetected,
             messageCodeOnElementNotDetected,
             messageCodeOnMultipleElements,
             eeAttributeNameList);
    }

    /**
     * Constructor.
     *
     * @param checkUnicity
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     * @param messageCodeOnMultipleElements
     * @param eeAttributeNameList
     */
    public ElementPresenceChecker(
            boolean checkUnicity,
            TestSolution detectedSolution,
            TestSolution notDetectedSolution,
            String messageCodeOnElementDetected,
            String messageCodeOnElementNotDetected,
            String messageCodeOnMultipleElements,
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.checkUnicity = checkUnicity;
        this.detectedSolution = detectedSolution;
        this.messageCodeOnElementDetected = messageCodeOnElementDetected;
        this.notDetectedSolution = notDetectedSolution;
        this.messageCodeOnElementNotDetected = messageCodeOnElementNotDetected;
        this.messageCodeOnMultipleElements = messageCodeOnMultipleElements;
    }

    @Override
    public void doCheck(
            SSPHandler sspHandler,
            Elements elements,
            TestSolutionHandler testSolutionHandler) {
        checkElementPresence(elements, testSolutionHandler);
    }

    /**
     * This methods checks whether a given element is present on the page.
     *
     * @param elements
     * @param testSolutionHandler
     */
    private void checkElementPresence (
            Elements elements,
            TestSolutionHandler testSolutionHandler) {

        TestSolution checkResult = notDetectedSolution;

        if (!elements.isEmpty() && ((!checkUnicity)||(checkUnicity && elements.size() == 1))) {
            
            checkResult = detectedSolution;

            if (StringUtils.isNotBlank(messageCodeOnElementDetected)) {

                for (Element el : elements) {
                    createSourceCodeRemark(detectedSolution, el, messageCodeOnElementDetected);
                }

            }
        } else if (checkUnicity && elements.size() > 1 && 
                StringUtils.isNotBlank(messageCodeOnMultipleElements)) {
            for (Element el : elements) {
                addSourceCodeRemark(
                        notDetectedSolution,
                        el,
                        messageCodeOnMultipleElements);
            }

        } else if (StringUtils.isNotBlank(messageCodeOnElementNotDetected)) {

            getProcessRemarkService().addProcessRemark(
                    notDetectedSolution,
                    messageCodeOnElementNotDetected);

        }

        testSolutionHandler.addTestSolution(checkResult);
    }

    protected void createSourceCodeRemark(TestSolution testSolution, Element element, String message) {
        addSourceCodeRemark(testSolution, element, message);
    }

}