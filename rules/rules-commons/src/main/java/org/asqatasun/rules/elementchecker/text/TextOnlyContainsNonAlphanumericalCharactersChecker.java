/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2020  Asqatasun.org
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

package org.asqatasun.rules.elementchecker.text;

import java.util.regex.Pattern;

import org.asqatasun.rules.textbuilder.AccessibleNameElementBuilder;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.NomenclatureBasedElementChecker;
import org.asqatasun.rules.textbuilder.TextElementBuilder;

/**
 * This class checks whether the text of an dom element doesn't only contains 
 * non-alphanumerical characters. It is particularly used on text pertinence 
 * check.
 * 
 * @author jkowalczyk
 */
public class TextOnlyContainsNonAlphanumericalCharactersChecker 
            extends NomenclatureBasedElementChecker {

    private static final String NON_ALPHANUMERIC_PATTERN_STR ="[^\\p{L}]*";
    private static final Pattern NON_ALPHANUMERIC_PATTERN =
              Pattern.compile(NON_ALPHANUMERIC_PATTERN_STR);

    private TextElementBuilder testableTextBuilder;
    /*
     * The message thrown when an element only contains non alphanumerical 
     * characters.
     */
    private String textOnlyContainsNacMsgCode;

    /**
     * Constructor
     * 
     * @param testableTextBuilder
     * @param textOnlyContainsNacMsgCode
     */
    public TextOnlyContainsNonAlphanumericalCharactersChecker(
            TextElementBuilder testableTextBuilder,
            String textOnlyContainsNacMsgCode) {
        super();
        this.setTextElementBuilder(testableTextBuilder);
        this.testableTextBuilder = testableTextBuilder;
        this.textOnlyContainsNacMsgCode = textOnlyContainsNacMsgCode;
    }
    
    /**
     * Constructor
     * @param testableTextBuilder
     * @param textOnlyContainsNacMsgCode
     * @param eeAttributeNameList 
     */
    public TextOnlyContainsNonAlphanumericalCharactersChecker(
            TextElementBuilder testableTextBuilder,
            String textOnlyContainsNacMsgCode,
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.testableTextBuilder = testableTextBuilder;
        this.textOnlyContainsNacMsgCode = textOnlyContainsNacMsgCode;
    }
    
    /**
     * Constructor
     * @param testableTextBuilder
     * @param detectedSolution
     * @param textOnlyContainsNacMsgCode
     */
    public TextOnlyContainsNonAlphanumericalCharactersChecker(
            TextElementBuilder testableTextBuilder,
            TestSolution detectedSolution,
            String textOnlyContainsNacMsgCode) {
        this(testableTextBuilder, textOnlyContainsNacMsgCode);
        setFailureSolution(detectedSolution);
    }
    
    /**
     * Constructor
     * @param testableTextBuilder
     * @param detectedSolution
     * @param textOnlyContainsNacMsgCode
     * @param eeAttributeNameList 
     */
    public TextOnlyContainsNonAlphanumericalCharactersChecker(
            TextElementBuilder testableTextBuilder,
            TestSolution detectedSolution,
            String textOnlyContainsNacMsgCode,
            String... eeAttributeNameList) {
        this(testableTextBuilder, textOnlyContainsNacMsgCode, eeAttributeNameList);
        setFailureSolution(detectedSolution);
    }

    @Override
    protected void doCheck(
             SSPHandler sspHandler, 
             Elements elements, 
             TestSolutionHandler testSolutionHandler) {
        // if the testableTextBuilder has not been set by constructor, use the parent one
        if (testableTextBuilder == null) {
            testableTextBuilder = getTextElementBuilder();
        }
        if (testableTextBuilder instanceof AccessibleNameElementBuilder) {
            ((AccessibleNameElementBuilder)testableTextBuilder).setSspHandler(sspHandler);
        }
         for (Element element : elements) {
             testSolutionHandler.addTestSolution(
                     checkTextElementOnlyContainsNonAlphanumericCharacters(
                        element,
                         testableTextBuilder.buildTextFromElement(element)));
         }
    }
    
    /**
     * This methods checks whether a given text only contains non alphanumerical
     * characters
     * 
     * @param element
     * @param elementText
     * @return 
     */
    private TestSolution checkTextElementOnlyContainsNonAlphanumericCharacters(
            Element element,
            String elementText) {

        if (elementText == null) {
            return TestSolution.NOT_APPLICABLE;
        }

        if (NON_ALPHANUMERIC_PATTERN.matcher(elementText).matches()) {
            
            addSourceCodeRemark(
                getFailureSolution(),
                element,
                textOnlyContainsNacMsgCode);
            
            return getFailureSolution();
            
        } else {
            
            return getSuccessSolution();
            
        }
    }

}
