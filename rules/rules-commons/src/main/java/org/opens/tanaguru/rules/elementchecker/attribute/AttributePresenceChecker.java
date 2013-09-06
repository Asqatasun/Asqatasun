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

package org.opens.tanaguru.rules.elementchecker.attribute;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementCheckerImpl;

/**
 * This checker controls the presence of an attribute for a set of elements.
 * 
 */
public class AttributePresenceChecker extends ElementCheckerImpl {

    /**
     * the attribute the test is about
     */
    private String attributeName;
    
    /**
     * Not detected solution. Default is FAILED.
     */
    private TestSolution notDetectedSolution = TestSolution.FAILED;

    /**
     * Detected solution. Default is PASSED.
     */
    private TestSolution detectedSolution = TestSolution.PASSED;

    /**
     * The message code associated with a processRemark when the attribute is
     * detected on an element
     */
    private String messageCodeOnAttrDetected;
    
    /**
     * The message code associated with a processRemark when the attribute is
     * not detected on an element
     */
    private String messageCodeOnAttrNotDetected;
    
    /**
     * This flag determines whether each source code remark have to be related 
     * with the element or the attribute itself. Default is false
     */
    private boolean createSourceCodeRemarkOnAttribute = false;
    
    /**
     * 
     * @param attributeName
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnAttrDetected
     * @param messageCodeOnAttrNotDetected
     * @param eeAttributeNameList 
     */
    public AttributePresenceChecker(
            String attributeName, 
            TestSolution detectedSolution,
            TestSolution notDetectedSolution,
            String messageCodeOnAttrDetected, 
            String messageCodeOnAttrNotDetected, 
            String...eeAttributeNameList) {
        super(eeAttributeNameList);
        this.attributeName = attributeName;
        
        this.detectedSolution = detectedSolution;
        this.messageCodeOnAttrDetected = messageCodeOnAttrDetected;
        
        this.notDetectedSolution = notDetectedSolution;
        this.messageCodeOnAttrNotDetected = messageCodeOnAttrNotDetected;
    }
    
    /**
     * 
     * @param attributeName
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnAttrDetected
     * @param messageCodeOnAttrNotDetected
     * @param createSourceCodeRemarkOnAttribute
     * @param eeAttributeNameList 
     */
    public AttributePresenceChecker(
            String attributeName, 
            TestSolution detectedSolution,
            TestSolution notDetectedSolution,
            String messageCodeOnAttrDetected, 
            String messageCodeOnAttrNotDetected, 
            boolean createSourceCodeRemarkOnAttribute, 
            String...eeAttributeNameList) {
        super(eeAttributeNameList);
        this.attributeName = attributeName;
        
        this.detectedSolution = detectedSolution;
        this.messageCodeOnAttrDetected = messageCodeOnAttrDetected;
        
        this.notDetectedSolution = notDetectedSolution;
        this.messageCodeOnAttrNotDetected = messageCodeOnAttrNotDetected;
        
        this.createSourceCodeRemarkOnAttribute = createSourceCodeRemarkOnAttribute;
    }
    
    @Override
    public void doCheck(
            SSPHandler sspHandler, 
            Elements elements, 
            TestSolutionHandler testSolutionHandler) {
        checkAttributePresence(
                elements, 
                testSolutionHandler);
    }

    /**
     * This methods checks whether a given attribute is present for a set of 
     * elements
     * 
     * @param elements
     * @param testSolutionHandler
     */
    private void checkAttributePresence (
            Elements elements, 
            TestSolutionHandler testSolutionHandler) {
        
        if (elements.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        
        TestSolution testSolution = TestSolution.PASSED;
        
        for (Element el : elements) {
            if (!el.hasAttr(attributeName)) {
                
                testSolution = setTestSolution(testSolution, notDetectedSolution);
                
                if (StringUtils.isNotBlank(messageCodeOnAttrNotDetected)) {
                    createSourceCodeRemark(notDetectedSolution, el, messageCodeOnAttrNotDetected);
                    
                }
                
            } else if (StringUtils.isNotBlank(messageCodeOnAttrDetected)) {
                
                testSolution = setTestSolution(testSolution, detectedSolution);
                
                createSourceCodeRemark(detectedSolution, el, messageCodeOnAttrDetected);

            }
        }
        
        testSolutionHandler.addTestSolution(testSolution);
        
    }

    /**
     * 
     * @param testSolution
     * @param element
     * @param message 
     */
    private void createSourceCodeRemark (TestSolution testSolution, Element element, String message) {
        if (createSourceCodeRemarkOnAttribute) {
            addSourceCodeRemarkOnAttribute(
                    testSolution, 
                    element, 
                    message, 
                    attributeName);
        } else {
            addSourceCodeRemark(
                    testSolution, 
                    element, 
                    message);
        }
    }
    
}
