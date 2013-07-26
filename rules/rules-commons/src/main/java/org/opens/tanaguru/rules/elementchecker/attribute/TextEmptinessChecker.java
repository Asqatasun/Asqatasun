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
 * This class checks whether the content of an attribute is empty.
 */
public abstract class TextEmptinessChecker extends ElementCheckerImpl {

    /**
     * Text Not empty solution. Default is PASSED.
     */
    private TestSolution textNotEmptySolution = TestSolution.PASSED;

    /**
     * Text empty solution. Default is FAILED.
     */
    private TestSolution textEmptySolution = TestSolution.FAILED;

    /**
     * The message code associated with a processRemark when the attribute is
     * empty on an element
     */
    private String messageCodeOnTextEmpty;
    
    /**
     * The message code associated with a processRemark when the attribute is
     * empty on an element
     */
    private String messageCodeOnTextNotEmpty;
    
    /**
     * Constructor. 
     * Returns FAILED when the text is empty and PASSED when it is not.
     * 
     * @param messageCodeOnTextEmpty
     * @param messageCodeOnTextNotEmpty
     */
    public TextEmptinessChecker(
            String messageCodeOnTextEmpty, 
            String messageCodeOnAttrNotEmpty) {
        super();
        this.messageCodeOnTextEmpty = messageCodeOnTextEmpty;
        this.messageCodeOnTextNotEmpty = messageCodeOnAttrNotEmpty;
    }
    
    /**
     * Constructor.
     * Returns FAILED when the text is empty and PASSED when it is not.
     * 
     * @param messageCodeOnTextEmpty
     * @param messageCodeOnTextNotEmpty
     * @param eeAttributeNameList 
     */
    public TextEmptinessChecker(
            String messageCodeOnTextEmpty, 
            String messageCodeOnAttrNotEmpty, 
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.messageCodeOnTextEmpty = messageCodeOnTextEmpty;
        this.messageCodeOnTextNotEmpty = messageCodeOnAttrNotEmpty;
    }
    
    /**
     * Constructor.
     * 
     * @param textEmptySolution
     * @param textNotEmptySolution
     * @param messageCodeOnTextEmpty
     * @param messageCodeOnTextNotEmpty
     */
    public TextEmptinessChecker(
            TestSolution textEmptySolution,
            TestSolution textNotEmptySolution,
            String messageCodeOnTextEmpty, 
            String messageCodeOnAttrNotEmpty) {
        super();
        this.textEmptySolution = textEmptySolution;
        this.textNotEmptySolution = textNotEmptySolution;
        this.messageCodeOnTextEmpty = messageCodeOnTextEmpty;
        this.messageCodeOnTextNotEmpty = messageCodeOnAttrNotEmpty;
    }
    
    /**
     * Constructor 
     * 
     * @param textEmptySolution
     * @param textNotEmptySolution
     * @param messageCodeOnTextEmpty
     * @param messageCodeOnTextNotEmpty
     * @param eeAttributeNameList 
     */
    public TextEmptinessChecker(
            TestSolution textEmptySolution,
            TestSolution textNotEmptySolution,
            String messageCodeOnTextEmpty, 
            String messageCodeOnAttrNotEmpty, 
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.textEmptySolution = textEmptySolution;
        this.textNotEmptySolution = textNotEmptySolution;
        this.messageCodeOnTextEmpty = messageCodeOnTextEmpty;
        this.messageCodeOnTextNotEmpty = messageCodeOnAttrNotEmpty;
    }
    
    @Override
    public void doCheck(
            SSPHandler sspHandler, 
            Elements elements, 
            TestSolutionHandler testSolutionHandler) {
        checkEmptiness(
                elements, 
                testSolutionHandler);
    }

    /**
     * This methods checks whether a given attribute is empty for a set of
     * elements
     * 
     * @param elements
     * @param testSolutionHandler
     */
    private void checkEmptiness (
            Elements elements, 
            TestSolutionHandler testSolutionHandler) {
        
        if (elements.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        
        TestSolution testSolution = TestSolution.PASSED;
        
        for (Element el : elements) {
            if (isElementEmpty(el)) {
                
                testSolution = setTestSolution(testSolution, textEmptySolution);

                if (StringUtils.isNotBlank(messageCodeOnTextEmpty)) {
                    
                    getProcessRemarkService().addSourceCodeRemarkOnElement(
                        textEmptySolution, 
                        el, 
                        messageCodeOnTextEmpty,
                        getEvidenceElementCollection(el, getEeAttributeNameList()));
                    
                }
                
            } else if (StringUtils.isNotBlank(messageCodeOnTextNotEmpty)) {

                testSolution = setTestSolution(testSolution, textNotEmptySolution);
                
                getProcessRemarkService().addSourceCodeRemarkOnElement(
                        textNotEmptySolution, 
                        el, 
                        messageCodeOnTextNotEmpty,
                        getEvidenceElementCollection(el, getEeAttributeNameList()));
                
            }
        }
        testSolutionHandler.addTestSolution(testSolution);
    }

    /**
     * 
     * @param element
     * @return whether an element is seen as empty
     */
    public abstract boolean isElementEmpty(Element element);

}