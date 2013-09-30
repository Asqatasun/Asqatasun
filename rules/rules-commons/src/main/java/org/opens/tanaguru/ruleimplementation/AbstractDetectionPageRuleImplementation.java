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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.jsoup.nodes.Element;
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
 * can be overridden by constructor argument.
 */
public class AbstractDetectionPageRuleImplementation
            extends AbstractPageRuleMarkupImplementation {

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
    public ElementSelector getElementSelector() {
        return elementSelector;
    }
    
    /**
     * Constructor
     * 
     * @param elementSelector
     * @param messageCodeOnElementDetected
     * @param messageCodeOnElementNotDetected
     */
    public AbstractDetectionPageRuleImplementation(
            @Nonnull ElementSelector elementSelector, 
            @Nullable String messageCodeOnElementDetected, 
            @Nullable String messageCodeOnElementNotDetected) {
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
            @Nonnull ElementSelector elementSelector, 
            @Nullable String messageCodeOnElementDetected, 
            @Nullable String messageCodeOnElementNotDetected,
            String... eeAttributeNameList) {
        this(elementSelector,messageCodeOnElementDetected,messageCodeOnElementNotDetected);
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
            @Nonnull ElementSelector elementSelector, 
            @Nonnull TestSolution detectedSolution, 
            @Nonnull TestSolution notDetectedSolution, 
            @Nullable String messageCodeOnElementDetected, 
            @Nullable String messageCodeOnElementNotDetected) {
        this(elementSelector, messageCodeOnElementDetected, messageCodeOnElementNotDetected);
        this.detectedSolution = detectedSolution;
        this.notDetectedSolution = notDetectedSolution;
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
            @Nonnull ElementSelector elementSelector, 
            @Nonnull TestSolution detectedSolution, 
            @Nonnull TestSolution notDetectedSolution, 
            @Nullable String messageCodeOnElementDetected, 
            @Nullable String messageCodeOnElementNotDetected,
            String... eeAttributeNameList) {
        this(elementSelector, detectedSolution, notDetectedSolution, messageCodeOnElementDetected, messageCodeOnElementNotDetected);
        this.eeAttributeNameList = eeAttributeNameList;
    }
    
    @Override
    protected void select(SSPHandler sspHandler, ElementHandler<Element> selectionHandler) {
        elementSelector.selectElements(sspHandler, selectionHandler);
    }

    @Override
    protected void check(SSPHandler sspHandler, ElementHandler<Element> selectionHandler, TestSolutionHandler testSolutionHandler) {
        super.check(sspHandler, selectionHandler, testSolutionHandler);
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