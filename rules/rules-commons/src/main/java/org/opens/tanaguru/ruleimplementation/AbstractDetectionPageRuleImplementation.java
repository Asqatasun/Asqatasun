/*
 * @(#)AbstractTagDetectionPageRuleImplementation.java
 *
 * Copyright  2010 SAS OPEN-S. All rights reserved.
 * OPEN-S PROPRIETARY/CONFIDENTIAL.  Use is subject to license terms.
 *
 * This file is  protected by the  intellectual  property rights
 * in  France  and  other  countries, any  applicable  copyrights  or
 * patent rights, and international treaty provisions. No part may be
 * reproduced  in  any  form  by  any  mean  without   prior  written
 * authorization of OPEN-S.
 */
package org.opens.tanaguru.ruleimplementation;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.rules.elementchecker.element.ElementPresenceChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;

/**
 * This class should be overridden by concrete {@link RuleImplementation} 
 * classes which implement tests with page scope.
 * <p> It deals with the detection of elements.</p>
 * Elements are retrieved through the {@link ElementSelector} set by constructor
 * argument. 
 * Once retrieved, the {@link ElementPresenceChecker} is called and returns 
 * by default PASSED when no element has been detected and FAILED when at least
 * one element has been detected. The solution of the detection and the no detection
 * can be overriden by constructor argument.
 */
public class AbstractDetectionPageRuleImplementation
            extends AbstractPageRuleDefaultImplementation {

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
     * the collection of attributes name used to collect evidenceElement
     */
    private String[] eeAttributeNameList;
    
    /**
     * The elementSelector used by the rule
     */
    private ElementSelector elementSelector;
    
    /**
     * Constructor
     * 
     * @param elementSelector
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     */
    public AbstractDetectionPageRuleImplementation(
            ElementSelector elementSelector, 
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected) {
        super();
        this.elementSelector = elementSelector;
        this.messageCodeOnElementDetected = messageCodeOnElementDetected;
        this.messageCodeOnElementNotDetected = messageCodeOnElementNotDetected;
    }
    
    /**
     * Constructor
     * 
     * @param elementSelector
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     * @param eeAttributeNameList 
     */
    public AbstractDetectionPageRuleImplementation(
            ElementSelector elementSelector, 
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected,
            String... eeAttributeNameList) {
        super();
        this.elementSelector = elementSelector;
        this.messageCodeOnElementDetected = messageCodeOnElementDetected;
        this.messageCodeOnElementNotDetected = messageCodeOnElementNotDetected;
        this.eeAttributeNameList = eeAttributeNameList;
    }
    
    /**
     * Constructor
     * 
     * @param elementSelector
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     */
    public AbstractDetectionPageRuleImplementation(
            ElementSelector elementSelector, 
            TestSolution detectedSolution, 
            TestSolution notDetectedSolution, 
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected) {
        super();
        this.elementSelector = elementSelector;
        this.detectedSolution = detectedSolution;
        this.messageCodeOnElementDetected = messageCodeOnElementDetected;
        this.notDetectedSolution = notDetectedSolution;
        this.messageCodeOnElementNotDetected = messageCodeOnElementNotDetected;
    }
    
    /**
     * Constructor
     * 
     * @param elementSelector
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     * @param eeAttributeNameList 
     */
    public AbstractDetectionPageRuleImplementation(
            ElementSelector elementSelector, 
            TestSolution detectedSolution, 
            TestSolution notDetectedSolution, 
            String messageCodeOnElementDetected, 
            String messageCodeOnElementNotDetected,
            String... eeAttributeNameList) {
        super();
        this.elementSelector = elementSelector;
        this.detectedSolution = detectedSolution;
        this.messageCodeOnElementDetected = messageCodeOnElementDetected;
        this.notDetectedSolution = notDetectedSolution;
        this.messageCodeOnElementNotDetected = messageCodeOnElementNotDetected;
        this.eeAttributeNameList = eeAttributeNameList;
    }
    
    @Override
    protected void select(SSPHandler sspHandler, ElementHandler selectionHandler) {
        elementSelector.selectElements(sspHandler, selectionHandler);
    }

    @Override
    protected void check(SSPHandler sspHandler, ElementHandler selectionHandler, TestSolutionHandler testSolutionHandler) {
        ElementPresenceChecker epc = getElementPresenceChecker();
        epc.check(sspHandler, selectionHandler, testSolutionHandler);
    }

    /**
     * 
     * @return 
     */
    private ElementPresenceChecker getElementPresenceChecker() {
        if (eeAttributeNameList != null && eeAttributeNameList.length > 0) {
            return new ElementPresenceChecker(
                            detectedSolution, 
                            notDetectedSolution, 
                            messageCodeOnElementDetected,
                            messageCodeOnElementNotDetected,
                            eeAttributeNameList);
        } else {
            return new ElementPresenceChecker(
                            detectedSolution, 
                            notDetectedSolution, 
                            messageCodeOnElementDetected,
                            messageCodeOnElementNotDetected);
        }
    }

}