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

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.ElementCheckerImpl;
import org.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * This class checks whether the size of a given textual element doesn't exceed
 * a given size.
 * 
 * @author jkowalczyk
 */
public class TextLengthChecker extends ElementCheckerImpl {

    /* the length to test */
    private int lengthLimit;
    /**
     * The message thrown when the text element is too long
     */
    private String textTooLongMessageCode;
    
    /** The text element builder. By default, it is a simple Text builder*/
    private TextElementBuilder testableTextBuilder;
    
    /**
     * Constructor
     * @param testableTextBuilder
     * @param lengthLimit
     * @param textTooLongMessageCode 
     */
    public TextLengthChecker(
            TextElementBuilder testableTextBuilder,
            int lengthLimit, 
            String textTooLongMessageCode) {
        super();
        this.testableTextBuilder = testableTextBuilder;
        this.lengthLimit = lengthLimit;
        this.textTooLongMessageCode = textTooLongMessageCode;
    }
    
    /**
     * Constructor
     * @param testableTextBuilder
     * @param lengthLimit
     * @param textTooLongMessageCode
     * @param eeAttributeNameList 
     */
    public TextLengthChecker(
            TextElementBuilder testableTextBuilder,
            int lengthLimit,
            String textTooLongMessageCode,
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.testableTextBuilder = testableTextBuilder;
        this.lengthLimit = lengthLimit;
        this.textTooLongMessageCode = textTooLongMessageCode;
    }
    
    /**
     * Constructor
     * @param testableTextBuilder
     * @param lengthLimit
     * @param textTooLongSolution
     * @param textTooLongMessageCode
     */
    public TextLengthChecker(
            TextElementBuilder testableTextBuilder,
            int lengthLimit,
            TestSolution textTooLongSolution,
            String textTooLongMessageCode) {
        this(testableTextBuilder, lengthLimit, textTooLongMessageCode);
        setFailureSolution(textTooLongSolution);
    }
    
    /**
     * Constructor
     * @param testableTextBuilder
     * @param lengthLimit
     * @param textTooLongSolution
     * @param textTooLongMessageCode
     * @param eeAttributeNameList 
     */
    public TextLengthChecker(
            TextElementBuilder testableTextBuilder,
            int lengthLimit,
            TestSolution textTooLongSolution,
            String textTooLongMessageCode,
            String... eeAttributeNameList) {
        this(testableTextBuilder, lengthLimit, textTooLongMessageCode, eeAttributeNameList);
        setFailureSolution(textTooLongSolution);
    }

    @Override
    protected void doCheck(
             SSPHandler sspHandler, 
             Elements elements, 
             TestSolutionHandler testSolutionHandler) {
         for (Element element : elements) {
             testSolutionHandler.addTestSolution(
                     checkSizeOfTextDoesNotExceedLengthLimit(
                        element, 
                        testableTextBuilder.buildTextFromElement(element)));
         }
    }
    
    /**
     * 
     * @param element
     * @param elementText
     * @return whether the text exceeds a given size
     */
    private TestSolution checkSizeOfTextDoesNotExceedLengthLimit(
            Element element,
            String elementText) {
        // the test is made through the getter to force the initialisation
        if (element == null || 
                elementText == null) {
            return TestSolution.NOT_APPLICABLE;
        }
        if (elementText.length() > lengthLimit) {
            
            addSourceCodeRemark(getFailureSolution(), element, textTooLongMessageCode);
            return getFailureSolution();
        }
        return getSuccessSolution();
    }

}