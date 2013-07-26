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

    /**
     * Determine whether the element has to be present and unique.
     */
    private boolean checkUnicity = false;
    
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
    private String messageCodeOnElementDetected;
    
    /**
     * The message code associated with a processRemark when the element is
     * not found on the page
     */
    private String messageCodeOnElementNotDetected;
    
    /**
     * Constructor.
     * 
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     */
    public ElementPresenceChecker(
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected) {
        super();
        this.messageCodeOnElementDetected = messageCodeOnElementDetected;
        this.messageCodeOnElementNotDetected = messageCodeOnElementNotDetected;
    }
    
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
        super(eeAttributeNameList);
        this.messageCodeOnElementDetected = messageCodeOnElementDetected;
        this.messageCodeOnElementNotDetected = messageCodeOnElementNotDetected;
    }
    
    /**
     * Constructor.
     * 
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     */
    public ElementPresenceChecker(
            TestSolution detectedSolution,
            TestSolution notDetectedSolution, 
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected) {
        this.detectedSolution = detectedSolution;
        this.messageCodeOnElementDetected = messageCodeOnElementDetected;
        this.notDetectedSolution = notDetectedSolution;
        this.messageCodeOnElementNotDetected = messageCodeOnElementNotDetected;
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
        super(eeAttributeNameList);
        this.detectedSolution = detectedSolution;
        this.messageCodeOnElementDetected = messageCodeOnElementDetected;
        this.notDetectedSolution = notDetectedSolution;
        this.messageCodeOnElementNotDetected = messageCodeOnElementNotDetected;
    }

    /**
     * Constructor.
     * 
     * @param checkUnicity
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     */
    public ElementPresenceChecker(
            boolean checkUnicity,
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected) {
        super();
        this.checkUnicity = checkUnicity;
        this.messageCodeOnElementDetected = messageCodeOnElementDetected;
        this.messageCodeOnElementNotDetected = messageCodeOnElementNotDetected;
    }

    /**
     * Constructor.
     * 
     * @param checkUnicity
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     * @param eeAttributeNameList 
     */
    public ElementPresenceChecker(
            boolean checkUnicity,
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected, 
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.checkUnicity = checkUnicity;
        this.messageCodeOnElementDetected = messageCodeOnElementDetected;
        this.messageCodeOnElementNotDetected = messageCodeOnElementNotDetected;
    }
    
    /**
     * Constructor.
     * 
     * @param checkUnicity
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     */
    public ElementPresenceChecker(
            boolean checkUnicity,
            TestSolution detectedSolution,
            TestSolution notDetectedSolution, 
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected) {
        super();
        this.checkUnicity = checkUnicity;
        this.detectedSolution = detectedSolution;
        this.messageCodeOnElementDetected = messageCodeOnElementDetected;
        this.notDetectedSolution = notDetectedSolution;
        this.messageCodeOnElementNotDetected = messageCodeOnElementNotDetected;
    }
    
    /**
     * Constructor.
     * 
     * @param checkUnicity
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     * @param eeAttributeNameList 
     */
    public ElementPresenceChecker(
            boolean checkUnicity,
            TestSolution detectedSolution,
            TestSolution notDetectedSolution, 
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected, 
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.checkUnicity = checkUnicity;
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
                    getProcessRemarkService().addSourceCodeRemarkOnElement(
                        detectedSolution, 
                        el, 
                        messageCodeOnElementDetected,
                        getEvidenceElementCollection(el, getEeAttributeNameList()));
                }
                
            }
            
        } else if (StringUtils.isNotBlank(messageCodeOnElementNotDetected)) {
            
            getProcessRemarkService().addProcessRemark(
                    notDetectedSolution, 
                    messageCodeOnElementNotDetected);
            
        }
        
        testSolutionHandler.addTestSolution(checkResult);
    }

}