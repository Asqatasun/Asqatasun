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

package org.opens.tanaguru.rules.elementchecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.keystore.AttributeStore;
import static org.opens.tanaguru.rules.keystore.AttributeStore.*;
import org.opens.tanaguru.rules.keystore.EvidenceStore;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.rules.textbuilder.SimpleTextElementBuilder;
import org.opens.tanaguru.rules.textbuilder.TextElementBuilder;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 * This abstract implementation of element checker handles the basic methods
 * to collect evidence elements using its {@link ProcessRemarkService}
 * instance
 */
public abstract class ElementCheckerImpl implements ElementChecker {

    /** Max size of evidence element of text type */
    private static final int MAX_TEXT_EE_SIZE = 200;
    /** colon character */
    private static final String COLON_CHAR = ":";
    /** minus character */
    private static final String MINUS_CHAR = "-";
    /** Absolute URL prefix */
    private static final String ABS_URL_PREFIX = "abs:";
    
    /* The element builder needed to build the element text */
    private TextElementBuilder textElementBuilder;
    @Override
    public TextElementBuilder getTextElementBuilder() {
        if (textElementBuilder == null) {
            textElementBuilder = new SimpleTextElementBuilder();
        }
        return textElementBuilder;
    }
    @Override
    public void setTextElementBuilder(TextElementBuilder textElementBuilder) {
        this.textElementBuilder = textElementBuilder;
    }
    
    /* Success solution when checker returns success. Default is PASSED*/
    private TestSolution successSolution = TestSolution.PASSED;
    @Override
    public TestSolution getSuccessSolution(){
        return successSolution;
    }
    
    public void setSuccessSolution(TestSolution successSolution){
        this.successSolution = successSolution;
    }
    
    /* Success solution when checker returns failure. Default is FAILED*/
    private TestSolution failureSolution = TestSolution.FAILED;
    @Override
    public TestSolution getFailureSolution(){
        return failureSolution;
    }
    
    public void setFailureSolution(TestSolution failureSolution){
        this.failureSolution = failureSolution;
    }

    /**
     * the collection of attributes name used to collect evidenceElement
     */
    private String[] eeAttributeNames = {};
    public String[] getEeAttributeNames() {
        return eeAttributeNames;
    }
    public Collection<String> getEeAttributeNameAsCollection() {
        return Arrays.asList(eeAttributeNames);
    }

    /**
     * The locale ref to the processRemarkService
     */
    private ProcessRemarkService processRemarkService;
    public ProcessRemarkService getProcessRemarkService() {
        return processRemarkService;
    }
    public void setProcessRemarkService(ProcessRemarkService processRemarkService) {
        this.processRemarkService = processRemarkService;
    }
    
    /**
     * 
     * Constructor
     */
    public ElementCheckerImpl() {}
    
    /**
     * 
     * @param successSolution
     * @param failureSolution 
     */
    public ElementCheckerImpl(TestSolution successSolution, 
                              TestSolution failureSolution) {
        this.successSolution = successSolution;
        this.failureSolution = failureSolution;
    }
    
    /**
     * 
     * @param eeAttributeNameList 
     */
    public ElementCheckerImpl(TestSolution successSolution, 
                              TestSolution failureSolution,
                              String... eeAttributeNameList) {
       this(successSolution,failureSolution);
       this.eeAttributeNames = 
               Arrays.copyOf(eeAttributeNameList, eeAttributeNameList.length); 
    }
    
    /**
     * 
     * @param eeAttributeNameList 
     */
    public ElementCheckerImpl(String... eeAttributeNameList) {
       this.eeAttributeNames = 
               Arrays.copyOf(eeAttributeNameList, eeAttributeNameList.length); 
    }

    @Override
    public void check (
            SSPHandler sspHandler, 
            ElementHandler elementHandler, 
            TestSolutionHandler testSolutionHandler) {
        this.processRemarkService = sspHandler.getProcessRemarkService();
        doCheck(sspHandler, (Elements)elementHandler.get(), testSolutionHandler);
    }
    
    /**
     * 
     * @param sspHandler
     * @param elements
     * @param testSolutionHandler 
     */
    protected abstract void doCheck(
            SSPHandler sspHandler, 
            Elements elements, 
            TestSolutionHandler testSolutionHandler);

    /**
     * Add a sourceCodeRemark on the given element
     * 
     * @param testSolution
     * @param element
     * @param messageCode 
     */
    protected void addSourceCodeRemark (
            TestSolution testSolution, 
            Element element, 
            String messageCode) {
        Collection<EvidenceElement> eeCol = 
                getEvidenceElementCollection(
                    element, 
                    getEeAttributeNameAsCollection());
        if (CollectionUtils.isNotEmpty(eeCol)) {
            processRemarkService.addSourceCodeRemarkOnElement(
                        testSolution, 
                        element, 
                        messageCode,
                        eeCol);
        } else {
            processRemarkService.addSourceCodeRemarkOnElement(
                        testSolution, 
                        element, 
                        messageCode);
        }
    }
    
    /**
     * Add a sourceCodeRemark on the given element with a preset evidence element
     * collection
     * 
     * @param testSolution
     * @param element
     * @param messageCode 
     * @param evidenceElement
     */
    protected void addSourceCodeRemark (
            TestSolution testSolution, 
            Element element, 
            String messageCode, 
            EvidenceElement evidenceElement) {
        
        if (evidenceElement != null) {
            Collection<EvidenceElement> evidenceElementList = 
                    new ArrayList<EvidenceElement>();
            evidenceElementList.add(evidenceElement);
            processRemarkService.addSourceCodeRemarkOnElement(
                        testSolution, 
                        element, 
                        messageCode,
                        evidenceElementList);
        } else {
            processRemarkService.addSourceCodeRemarkOnElement(
                        testSolution, 
                        element, 
                        messageCode);
        }
    }
    
    /**
     * Add a sourceCodeRemark on the given element with a preset evidence element
     * collection
     * 
     * @param testSolution
     * @param element
     * @param messageCode 
     * @param attributeName
     */
    protected void addSourceCodeRemarkOnAttribute (
            TestSolution testSolution, 
            Element element, 
            String messageCode, 
            String attributeName) {
        
        if (attributeName != null) {
            Collection<EvidenceElement> evidenceElementList = 
                    new ArrayList<EvidenceElement>();
            EvidenceElement ee = getEvidenceElement(
                    EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, attributeName);
            evidenceElementList.add(ee);
            processRemarkService.addSourceCodeRemarkOnElement(
                        testSolution, 
                        element, 
                        messageCode,
                        evidenceElementList);
        }
    }
    
    /**
     * Add a sourceCodeRemark on the given element with a preset evidence element
     * collection
     * 
     * @param testSolution
     * @param element
     * @param messageCode 
     * @param evidenceElementList
     */
    protected void addSourceCodeRemark (
            TestSolution testSolution, 
            Element element, 
            String messageCode, 
            Collection<EvidenceElement> evidenceElementList) {
        
        if (CollectionUtils.isNotEmpty(evidenceElementList)) {
            processRemarkService.addSourceCodeRemarkOnElement(
                        testSolution, 
                        element, 
                        messageCode,
                        evidenceElementList);
        } else {
            processRemarkService.addSourceCodeRemarkOnElement(
                        testSolution, 
                        element, 
                        messageCode);
        }
    }
    
    /**
     * Add a processRemark
     * 
     * @param testSolution
     * @param messageCode 
     */
    protected void addProcessRemark (
            TestSolution testSolution, 
            String messageCode) {
        processRemarkService.addProcessRemark(testSolution, messageCode);
    }
    
    /**
     * Returns the default evidence Element Collection with an additional 
     * info when a sourceCodeRemark is created
     * 
     * @param element
     * @param eeListCollection
     * @return 
     */
    protected Collection<EvidenceElement> getEvidenceElementCollection (
            Element element, 
            Collection<String> eeListCollection) {
        if (CollectionUtils.isEmpty(eeListCollection)) {
            return null;
        }
        Collection<EvidenceElement> eeList = new ArrayList<EvidenceElement>();
        for (String eeName : eeListCollection) {
            eeList.add(getEvidenceElement(element, eeName));
        }
        return eeList;
    }
    
    /**
     * @param element
     * @param attr
     * @return an evidenceElement 
     */
    protected EvidenceElement getEvidenceElement(Element element, String attr) {
        EvidenceElement extraEe;
        String attrEE;
        // specific control with xml:lang attribute due to the ':' character
        if (StringUtils.equalsIgnoreCase(attr, AttributeStore.XML_LANG_ATTR)) {
            attrEE = StringUtils.replace(attr, ":", "-");
        } else {
            attrEE = attr;
        }
        if (isElementTextRequested(attr)) {
            extraEe = processRemarkService.getEvidenceElement(
                    attrEE, 
                    StringUtils.substring(
                        getTextElementBuilder().buildTextFromElement(element), 
                        0, 
                        MAX_TEXT_EE_SIZE));
        } else if (isAttributeExternalResource(attr)) {
            extraEe = processRemarkService.getEvidenceElement(
                    attrEE, 
                    buildAttributeContent(element, attr, true));
        } else {
            extraEe = processRemarkService.getEvidenceElement(
                    attrEE, 
                    buildAttributeContent(element, attr, false));
        }
        return extraEe;
    }
    
    /**
     * @param evidenceKey
     * @param evidenceValue
     * @return an evidenceElement 
     */
    protected EvidenceElement getEvidenceElement(String evidenceKey, String evidenceValue) {
        if (StringUtils.equalsIgnoreCase(evidenceKey, AttributeStore.XML_LANG_ATTR)) {
            evidenceKey = StringUtils.replace(evidenceKey, COLON_CHAR, MINUS_CHAR);
        }
        return processRemarkService.getEvidenceElement(evidenceKey, evidenceValue);
    }

    /**
     * Determines whether an attribute defines an external resource regarding
     * the markup type
     * 
     * @param attributeName
     * @return whether a requested attribute defines an external resource
     */
    private boolean isAttributeExternalResource(String attributeName) {
        return (StringUtils.equalsIgnoreCase(attributeName, SRC_ATTR) || 
                StringUtils.equalsIgnoreCase(attributeName, HREF_ATTR)) ? true : false; 
    }  
    
    /**
     * 
     * @param attributeName
     * @return whether a requested attribute is of text type
     */
    private boolean isElementTextRequested(String attributeName) {
        return StringUtils.equalsIgnoreCase(attributeName, HtmlElementStore.TEXT_ELEMENT2) ? true : false; 
    }  

    /**
     * 
     * @param element
     * @param attributeName
     * @param isExternalResource
     * @return the text content of an attribute
     */
    protected String buildAttributeContent(
            Element element, 
            String attributeName, 
            boolean isExternalResource) {
        if (!element.hasAttr(attributeName)) {
            return ABSENT_ATTRIBUTE_VALUE;
        } else if (isExternalResource && 
                !element.attr(ABS_URL_PREFIX + attributeName).isEmpty()) {
            return element.absUrl(attributeName).trim();
        } else {
            return element.attr(attributeName).trim();
        }
    }
    
    /**
     * Each unit check provides a solution. Regarding the result of this unit 
     * check, the final result of the test may be impacted. If the result of the 
     * unit check is PASSED, the global result is not changed. If the result of 
     * the unit check is FAILED and the current global result is NEED_MORE_INFO, 
     * the global result changes to FAILED. If the result of the unit check is
     * NEED_MORE_INFO and the current global result is FAILED, the current global
     * result is still FAILED
     * 
     * @param currentTestSolution
     * @param requestedTestSolution
     * @return the updated TestSolution
     */
    protected TestSolution setTestSolution(
                TestSolution currentTestSolution, 
                TestSolution requestedTestSolution) {
        if (requestedTestSolution.equals(TestSolution.PASSED) && 
                currentTestSolution.equals(TestSolution.NOT_APPLICABLE)) {
            return requestedTestSolution;
        } else 
            if (requestedTestSolution.equals(TestSolution.PASSED)) {
            return currentTestSolution;
        } else if (requestedTestSolution.equals(TestSolution.NEED_MORE_INFO) && 
                currentTestSolution.equals(TestSolution.FAILED)) {
            return currentTestSolution;
        } else {
            return requestedTestSolution;
        }
    }
    
}