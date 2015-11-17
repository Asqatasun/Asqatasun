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
package org.asqatasun.rules.elementchecker.attribute;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementCheckerImpl;

/**
 * This checker controls the presence of an attribute for a set of elements.
 *
 */
public class AttributeWithValuePresenceChecker extends ElementCheckerImpl {

    /**
     * the attribute the test is about
     */
    private final String attributeName;

    /**
     * the attribute value the test is about
     */
    private final String attributeValue;

    /**
     * This flag determines whether each source code remark have to be related
     * with the element or the attribute itself. Default is false
     */
    private boolean createSourceCodeRemarkOnAttribute = false;

    /**
     *
     * @param attributeName
     * @param attributeValue
     * @param detectedSolutionPair
     * @param notDetectedSolutionPair
     * @param eeAttributeNameList
     */
    public AttributeWithValuePresenceChecker(
            String attributeName,
            String attributeValue,
            Pair<TestSolution,String> detectedSolutionPair,
            Pair<TestSolution,String> notDetectedSolutionPair,
            String... eeAttributeNameList) {
        super(detectedSolutionPair, notDetectedSolutionPair, eeAttributeNameList);
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }
    
    /**
     * @Deprecated 
     * Use constructor with Pair instead
     * @param attributeName
     * @param attributeValue
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnAttrDetected
     * @param messageCodeOnAttrNotDetected
     * @param eeAttributeNameList
     */
    public AttributeWithValuePresenceChecker(
            String attributeName,
            String attributeValue,
            TestSolution detectedSolution,
            TestSolution notDetectedSolution,
            String messageCodeOnAttrDetected,
            String messageCodeOnAttrNotDetected,
            String... eeAttributeNameList) {
        this(
                attributeName,
                attributeValue,
                new ImmutablePair(detectedSolution,messageCodeOnAttrDetected), 
                new ImmutablePair(notDetectedSolution,messageCodeOnAttrNotDetected),
                eeAttributeNameList);
    }

        /**
     *
     * @param attributeName
     * @param attributeValue
     * @param detectedSolutionPair
     * @param notDetectedSolutionPair
     * @param createSourceCodeRemarkOnAttribute
     * @param eeAttributeNameList
     */
    public AttributeWithValuePresenceChecker(
            String attributeName,
            String attributeValue,
            Pair<TestSolution,String> detectedSolutionPair,
            Pair<TestSolution,String> notDetectedSolutionPair,
            boolean createSourceCodeRemarkOnAttribute,
            String... eeAttributeNameList) {
        super(detectedSolutionPair, notDetectedSolutionPair, eeAttributeNameList);
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
        this.createSourceCodeRemarkOnAttribute = createSourceCodeRemarkOnAttribute;
    }
    
    /**
     *
     * @param attributeName
     * @param attributeValue
     * @param detectedSolution
     * @param notDetectedSolution
     * @param messageCodeOnAttrDetected
     * @param messageCodeOnAttrNotDetected
     * @param createSourceCodeRemarkOnAttribute
     * @param eeAttributeNameList
     */
    public AttributeWithValuePresenceChecker(
            String attributeName,
            String attributeValue,
            TestSolution detectedSolution,
            TestSolution notDetectedSolution,
            String messageCodeOnAttrDetected,
            String messageCodeOnAttrNotDetected,
            boolean createSourceCodeRemarkOnAttribute,
            String... eeAttributeNameList) {
        this(
                attributeName,
                attributeValue,
                new ImmutablePair(detectedSolution,messageCodeOnAttrDetected), 
                new ImmutablePair(notDetectedSolution,messageCodeOnAttrNotDetected),
                createSourceCodeRemarkOnAttribute,
                eeAttributeNameList);
    }
    
    @Override
    public void doCheck(
            SSPHandler sspHandler,
            Elements elements,
            TestSolutionHandler testSolutionHandler) {
        checkAttributeWithValuePresence(
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
    private void checkAttributeWithValuePresence(
            Elements elements,
            TestSolutionHandler testSolutionHandler) {

        if (elements.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }

        TestSolution testSolution = TestSolution.PASSED;

        for (Element el : elements) {
            if (!el.hasAttr(attributeName)
                    || (el.hasAttr(attributeName) && !el.attr(attributeName).equals(attributeValue))) {

                testSolution = setTestSolution(testSolution, getFailureSolution());
                createSourceCodeRemark(getFailureSolution(), el, getFailureMsgCode());
                
            } else if (StringUtils.isNotBlank(getSuccessMsgCode())) {
                testSolution = setTestSolution(testSolution, getSuccessSolution());

                createSourceCodeRemark(getSuccessSolution(), el, getSuccessMsgCode());
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
    private void createSourceCodeRemark(TestSolution testSolution, Element element, String message) {
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
