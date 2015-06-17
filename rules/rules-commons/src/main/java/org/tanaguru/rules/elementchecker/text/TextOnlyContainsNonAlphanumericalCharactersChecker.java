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

import java.util.regex.Pattern;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;
import org.tanaguru.rules.textbuilder.TextElementBuilder;

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
    
    /*
     * The message thrown when an element only contains non alphanumerical 
     * characters.
     */
    private String textOnlyContainsNacMsgCode;

    /* The text element builder. By default, it is a simple Text builder */
    private TextElementBuilder testableTextBuilder;
    
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
         for (Element element : elements) {
             testSolutionHandler.addTestSolution(
                     checkTextElementOnlyContainsNonAlphanumericCharacters(
                        element, 
                        this.testableTextBuilder.buildTextFromElement(element)));
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