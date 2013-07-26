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
package org.opens.tanaguru.rules.seo.detection;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.opens.tanaguru.rules.seo.util.UniqueElementChecker;

/**
 * This abstract class checks nodes from a xpath selection expression
 * and creates a sourceCodeRemark for each selected element.
 * If the selection set is empty, this rule returns true, false instead. 
 * 
 * @author jkowalczyk
 */
public class AbstractTagDetectionPageRuleImplementation
        extends AbstractPageRuleImplementation {

    private static final String NON_ALPHANUMERIC_PATTERN_STR ="[^\\p{L}]*";
    private static final Pattern NON_ALPHANUMERIC_PATTERN =
              Pattern.compile(NON_ALPHANUMERIC_PATTERN_STR);
    
    private String messageCode;
    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
    
    private String checkElementMessageCode;
    public String getCheckElementMessageCode() {
        return checkElementMessageCode;
    }

    public void setCheckElementMessageCode(String checkElementMessageCode) {
        this.checkElementMessageCode = checkElementMessageCode;
    }

    private String selectionExpression;
    public String getSelectionExpression() {
        return selectionExpression;
    }

    public void setSelectionExpression(String xPathExpr) {
        this.selectionExpression = xPathExpr;
    }

    private TestSolution notDetectedSolution = TestSolution.PASSED;
    public TestSolution getNotDetectedSolution() {
        return notDetectedSolution;
    }

    public void setNotDetectedSolution(TestSolution notDetectedSolution) {
        this.notDetectedSolution = notDetectedSolution;
    }

    private TestSolution detectedSolution = TestSolution.FAILED;
    public TestSolution getDetectedSolution() {
        return detectedSolution;
    }

    public void setDetectedSolution(TestSolution detectedSolution) {
        this.detectedSolution = detectedSolution;
    }

    private boolean isRemarkCreatedOnDetection = true;
    public boolean isIsRemarkCreatedOnDetection() {
        return isRemarkCreatedOnDetection;
    }

    public void setIsRemarkCreatedOnDetection(boolean isRemarkCreatedOnDetection) {
        this.isRemarkCreatedOnDetection = isRemarkCreatedOnDetection;
    }

    private boolean hasElementToBeUnique = false;
    public boolean hasElementToBeUnique() {
        return hasElementToBeUnique;
    }

    public void setHasElementToBeUnique(boolean hasElementToBeUnique) {
        this.hasElementToBeUnique = hasElementToBeUnique;
    }
    
    private String elementName = null;
    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    private boolean hasElementToBeNotNullAndContainsAlphanumericalContent = false;
    public boolean hasElementToBeNotNullAndContainsAlphanumericalContent() {
        return hasElementToBeNotNullAndContainsAlphanumericalContent;
    }
    
    public void setHasElementToBeNotNullAndContainsAlphanumericalContent(
            boolean hasElementToBeNotNullAndContainsAlphanumericalContent) {
        this.hasElementToBeNotNullAndContainsAlphanumericalContent = 
                hasElementToBeNotNullAndContainsAlphanumericalContent;
    }
    
    private String notUniqueMessage;
    public String getNotUniqueMessage() {
        return notUniqueMessage;
    }

    public void setNotUniqueMessage(String notUniqueMessage) {
        this.notUniqueMessage = notUniqueMessage;
    }

    private String notUniqueEvidenceElement;
    public String getNotUniqueEvidenceElement() {
        return notUniqueEvidenceElement;
    }

    public void setNotUniqueEvidenceElement(String notUniqueEvidenceElement) {
        this.notUniqueEvidenceElement = notUniqueEvidenceElement;
    }
    
    public AbstractTagDetectionPageRuleImplementation() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        
        Elements elements = sspHandler.beginCssLikeSelection().
                domCssLikeSelectNodeSet(getSelectionExpression()).
                getSelectedElements();

        Collection<ProcessRemark> processRemarkSet = new HashSet<ProcessRemark>();
        TestSolution checkResult = notDetectedSolution;

        if (!elements.isEmpty()) {
            if (hasElementToBeUnique() && elements.size() > 1) {
                processRemarkSet.addAll(UniqueElementChecker.getNotUniqueElementProcessRemarkCollection(
                        sspHandler,
                        elements,
                        getNotUniqueMessage(),
                        notUniqueEvidenceElement));
            } else {
                checkResult = detectedSolution;
                if (hasElementToBeNotNullAndContainsAlphanumericalContent) {
                    Iterator<Element> iter = elements.iterator();
                    while (iter.hasNext()) {
                        Element element = iter.next();
                        String attributeContent = retrieveAttributeContent(elementName, element);
                        TestSolution solution = checkTextElementOnlyContainsNonAlphanumericCharacters(
                                element, 
                                attributeContent, 
                                getMessageCode(), 
                                sspHandler);
                        TestSolution solution2 = checkAttributeValueNotEmpty(
                                element,
                                attributeContent,
                                sspHandler, 
                                processRemarkSet);
                        if (solution2.equals(TestSolution.FAILED)) {
                            checkResult = TestSolution.FAILED;
                            iter.remove();
                        }
                        if (solution.equals(TestSolution.FAILED)) {
                            checkResult = TestSolution.FAILED;
                        }
                    }
                }
                if (isRemarkCreatedOnDetection) {
                    if (detectedSolution.equals(TestSolution.NEED_MORE_INFO) &&
                            !checkResult.equals(TestSolution.FAILED)) {
                        createRemarkForAllNodes(
                            elements, 
                            TestSolution.NEED_MORE_INFO, 
                            getCheckElementMessageCode(),
                            processRemarkSet,
                            sspHandler);
                    } else {
                        createRemarkForAllNodes(
                            elements, 
                            checkResult, 
                            getMessageCode(),
                            processRemarkSet,
                            sspHandler);
                    }
                }
            }
        } else if (!isRemarkCreatedOnDetection) {
            processRemarkSet.add(
                sspHandler.getProcessRemarkService().createProcessRemark(
                        checkResult,
                        getMessageCode()));
        }

        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                checkResult,
                processRemarkSet);

        return result;
    }

    /**
     * 
     * @param node
     * @param contentToTest
     * @param sspHandler
     * @param processRemarkSet
     * @return 
     */
    private TestSolution checkAttributeValueNotEmpty(
            Element element,
            String contentToTest, 
            SSPHandler sspHandler,
            Collection<ProcessRemark> processRemarkSet) {

        TestSolution result = TestSolution.PASSED;

        if (contentToTest != null) {
            if (StringUtils.isEmpty(contentToTest)) {
                result = TestSolution.FAILED;
                processRemarkSet.add(
                        sspHandler.getProcessRemarkService().createSourceCodeRemark(
                        result,
                        element,
                        getMessageCode()));
            }
        }
        return result;
    }
    
    /**
     * 
     * @param attributeName
     * @param node
     * @return 
     */
    private String retrieveAttributeContent(String attributeName, Element element) {
        String contentToTest;
        if (attributeName != null && element.hasAttr(attributeName))   {
            contentToTest= element.attr(attributeName).trim();
        } else {
            contentToTest=element.ownText().trim();
        }
        return contentToTest;
    }
    
    /**
     * 
     * @param elements
     * @param checkResult
     * @param messageCode
     * @param processRemarkSet
     * @param sspHandler 
     */
    private void createRemarkForAllNodes(
            Elements elements, 
            TestSolution checkResult, 
            String messageCode, 
            Collection<ProcessRemark> processRemarkSet,
            SSPHandler sspHandler) {
        for (Element element : elements) {
            processRemarkSet.add(
                sspHandler.getProcessRemarkService().createSourceCodeRemark(
                    checkResult,
                    element,
                    messageCode));
        }
    }

    /**
     * 
     * @param element
     * @param elementText
     * @param notPertinentMessage
     * @param manualCheckMessage
     * @param eeList
     * @return 
     */
    private TestSolution checkTextElementOnlyContainsNonAlphanumericCharacters(
            Element element,
            String elementText,
            String notPertinentMessage,
            SSPHandler sspHandler) {
        if (NON_ALPHANUMERIC_PATTERN.matcher(elementText).matches()) {
            sspHandler.getProcessRemarkService().addSourceCodeRemarkOnElement(
                TestSolution.FAILED,
                element,
                notPertinentMessage);
            return TestSolution.FAILED;
        } else {
            return TestSolution.NEED_MORE_INFO;
        }
    }
    
}