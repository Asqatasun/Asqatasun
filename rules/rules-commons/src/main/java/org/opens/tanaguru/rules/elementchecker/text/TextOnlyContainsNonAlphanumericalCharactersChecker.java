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

package org.opens.tanaguru.rules.elementchecker.text;

import java.util.regex.Pattern;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;
import org.opens.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * 
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

    /* Detected solution. Default is FAILED */
    private TestSolution detectedSolution = TestSolution.FAILED;
    
    /* The text element builder. By default, it is a simple Text builder */
    private TextElementBuilder textElementBuilder;
    
    /**
     * Constructor
     * 
     * @param textElementBuilder
     * @param textOnlyContainsNacMsgCode
     */
    public TextOnlyContainsNonAlphanumericalCharactersChecker(
            TextElementBuilder textElementBuilder,
            String textOnlyContainsNacMsgCode) {
        super();
        this.textElementBuilder = textElementBuilder;
        this.textOnlyContainsNacMsgCode = textOnlyContainsNacMsgCode;
    }
    
    /**
     * Constructor
     * @param textElementBuilder
     * @param textOnlyContainsNacMsgCode
     * @param eeAttributeNameList 
     */
    public TextOnlyContainsNonAlphanumericalCharactersChecker(
            TextElementBuilder textElementBuilder,
            String textOnlyContainsNacMsgCode,
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.textElementBuilder = textElementBuilder;
        this.textOnlyContainsNacMsgCode = textOnlyContainsNacMsgCode;
    }
    
    /**
     * Constructor
     * @param textElementBuilder
     * @param detectionSolution
     * @param textOnlyContainsNacMsgCode
     */
    public TextOnlyContainsNonAlphanumericalCharactersChecker(
            TextElementBuilder textElementBuilder,
            TestSolution detectedSolution,
            String textOnlyContainsNacMsgCode) {
        this(textElementBuilder, textOnlyContainsNacMsgCode);
        this.detectedSolution = detectedSolution;
    }
    
    /**
     * Constructor
     * @param textElementBuilder
     * @param detectionSolution
     * @param textOnlyContainsNacMsgCode
     * @param eeAttributeNameList 
     */
    public TextOnlyContainsNonAlphanumericalCharactersChecker(
            TextElementBuilder textElementBuilder,
            TestSolution detectedSolution,
            String textOnlyContainsNacMsgCode,
            String... eeAttributeNameList) {
        this(textElementBuilder, textOnlyContainsNacMsgCode, eeAttributeNameList);
        this.detectedSolution = detectedSolution;
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
                        this.textElementBuilder.buildTextFromElement(element)));
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
                detectedSolution,
                element,
                textOnlyContainsNacMsgCode);
            
            return detectedSolution;
            
        } else {
            
            return TestSolution.PASSED;
            
        }
    }

}