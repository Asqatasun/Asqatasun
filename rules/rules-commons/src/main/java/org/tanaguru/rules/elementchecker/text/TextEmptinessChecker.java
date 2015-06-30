/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
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

package org.tanaguru.rules.elementchecker.text;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.ElementCheckerImpl;
import org.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * This class checks whether the content of a text is empty.
 */
public class TextEmptinessChecker extends ElementCheckerImpl {

    /* The text element builder. By default, it is a simple Text builder */
    private final TextElementBuilder testableTextBuilder;
    
    /**
     * Constructor.
     * Returns FAILED when the text is empty and PASSED when it is not.
     * 
     * @param testableTextBuilder
     * @param messageCodeOnTextEmpty
     * @param messageCodeOnTextNotEmpty
     * @param eeAttributeNameList 
     */
    public TextEmptinessChecker(
            TextElementBuilder testableTextBuilder,
            String messageCodeOnTextEmpty, 
            String messageCodeOnTextNotEmpty, 
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.testableTextBuilder = testableTextBuilder;
        this.setSuccessMsgCode(messageCodeOnTextNotEmpty);
        this.setFailureMsgCode(messageCodeOnTextEmpty);
    }
    
    /**
     * Constructor.
     * 
     * @param testableTextBuilder
     * @param textEmptySolutionPair
     * @param textNotEmptySolutionPair
     * @param eeAttributeNameList
     */
    public TextEmptinessChecker(
            TextElementBuilder testableTextBuilder,
            Pair<TestSolution,String> textEmptySolutionPair,
            Pair<TestSolution,String> textNotEmptySolutionPair,
            String... eeAttributeNameList) {
        super(
                textNotEmptySolutionPair, 
                textEmptySolutionPair, 
                eeAttributeNameList);
        this.testableTextBuilder = testableTextBuilder;
    }
    
    /**
     * Constructor 
     * 
     * @param testableTextBuilder
     * @param textEmptySolution
     * @param textNotEmptySolution
     * @param messageCodeOnTextEmpty
     * @param messageCodeOnTextNotEmpty
     * @param eeAttributeNameList
     */
    public TextEmptinessChecker(
            TextElementBuilder testableTextBuilder,
            TestSolution textEmptySolution,
            TestSolution textNotEmptySolution,
            String messageCodeOnTextEmpty, 
            String messageCodeOnTextNotEmpty, 
            String... eeAttributeNameList) {
        this(testableTextBuilder, 
             new ImmutablePair(textEmptySolution, messageCodeOnTextEmpty), 
             new ImmutablePair(textNotEmptySolution, messageCodeOnTextNotEmpty), 
                eeAttributeNameList);
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
        
        TestSolution testSolution = TestSolution.NOT_APPLICABLE;
        String textElement;
        for (Element el : elements) {
            textElement = testableTextBuilder.buildTextFromElement(el);
            if (textElement == null)  {
                testSolution = setTestSolution(testSolution, 
                                               TestSolution.NOT_APPLICABLE);
            } else if (isElementEmpty(textElement)) {
                testSolution = setTestSolution(testSolution, getFailureSolution());
                addSourceCodeRemark(getFailureSolution(), el, getFailureMsgCode());
            } else {
                testSolution = setTestSolution(testSolution, getSuccessSolution());
                addSourceCodeRemark(getSuccessSolution(), el, getSuccessMsgCode());
            }
        }
        testSolutionHandler.addTestSolution(testSolution);
    }

    /**
     * 
     * @param element
     * @return whether an element is seen as empty
     */
    private boolean isElementEmpty(String textElement) {
        return StringUtils.isBlank(textElement);
    }

}