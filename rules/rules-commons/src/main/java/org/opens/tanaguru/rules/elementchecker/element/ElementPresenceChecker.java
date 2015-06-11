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
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
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
     * The message code associated with a processRemark when the element is
     * not found on the page
     * @param messageCodeOnElementNotDetected
     */
    public void setMessageCodeOnElementNotDetected(String messageCodeOnElementNotDetected) {
        this.setFailureMsgCode(messageCodeOnElementNotDetected);
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
     * Constructor.
     *
     * @param detectedSolutionPair
     * @param notDetectedSolutionPair
     * @param eeAttributeNameList
     */
    public ElementPresenceChecker(
            Pair<TestSolution,String> detectedSolutionPair,
            Pair<TestSolution,String> notDetectedSolutionPair,
            String... eeAttributeNameList) {
        this(DEFAULT_CHECK_UNICITY,
             detectedSolutionPair, 
             notDetectedSolutionPair,
             null,
             eeAttributeNameList);
    }
    
    /**
     * @Deprecated 
     * Use constructor with Pair instead
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
             new ImmutablePair(detectedSolution,messageCodeOnElementDetected),
             new ImmutablePair(notDetectedSolution,messageCodeOnElementNotDetected),
             null,
             eeAttributeNameList);
    }

    /**
     * Constructor.
     *
     * @param checkUnicity
     * @param detectedSolutionPair
     * @param notDetectedSolutionPair
     * @param messageCodeOnMultipleElements
     * @param eeAttributeNameList
     */
    public ElementPresenceChecker(
            boolean checkUnicity,
            Pair<TestSolution,String> detectedSolutionPair,
            Pair<TestSolution,String> notDetectedSolutionPair,
            String messageCodeOnMultipleElements,
            String... eeAttributeNameList) {
        super(detectedSolutionPair, notDetectedSolutionPair, eeAttributeNameList);
        this.checkUnicity = checkUnicity;
        this.messageCodeOnMultipleElements = messageCodeOnMultipleElements;
    }
    
    /**
     * @Deprecated 
     * Use constructor with Pair instead
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
        this(checkUnicity,
             new ImmutablePair(detectedSolution,messageCodeOnElementDetected),
             new ImmutablePair(notDetectedSolution,messageCodeOnElementNotDetected),
             messageCodeOnMultipleElements,
             eeAttributeNameList);
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

        TestSolution checkResult = getFailureSolution();

        if (!elements.isEmpty() && ((!checkUnicity)||(checkUnicity && elements.size() == 1))) {
            
            checkResult = getSuccessSolution();

            if (StringUtils.isNotBlank(getSuccessMsgCode())) {

                for (Element el : elements) {
                    createSourceCodeRemark(getSuccessSolution(), el, getSuccessMsgCode());
                }

            }
        } else if (checkUnicity && elements.size() > 1 && 
                StringUtils.isNotBlank(messageCodeOnMultipleElements)) {
            for (Element el : elements) {
                addSourceCodeRemark(
                        getFailureSolution(),
                        el,
                        messageCodeOnMultipleElements);
            }

        } else if (StringUtils.isNotBlank(getFailureMsgCode())) {

            getProcessRemarkService().addProcessRemark(
                    getFailureSolution(),
                    getFailureMsgCode());

        }

        testSolutionHandler.addTestSolution(checkResult);
    }

    protected void createSourceCodeRemark(TestSolution testSolution, Element element, String message) {
        addSourceCodeRemark(testSolution, element, message);
    }

}