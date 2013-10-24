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

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementCheckerImpl;
import org.opens.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;
import org.opens.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 *
 */
public class TextNotIdenticalToAttributeChecker extends ElementCheckerImpl {

    /* The message thrown when an element belongs to the black list */
    private String textIdenticalToAttributeValueMessageCode;
    
    /* Detected solution. Default is FAILED. */
    private TestSolution detectedSolution = TestSolution.FAILED;
    
    /* The text element builder. By default, it is a simple Text builder */
    private TextElementBuilder textElementBuilder;
    
    /* The attr element builder needed to retrieve the attribute to compare with*/
    private TextAttributeOfElementBuilder attrElementBuilder = 
                new TextAttributeOfElementBuilder();
    
    /**
     * Constructor.
     * 
     * @param textElementBuilder
     * @param attributeNameToCompareWith
     * @param textIdenticalToAttributeValueMessageCode
     */
    public TextNotIdenticalToAttributeChecker(
            TextElementBuilder textElementBuilder,
            String attributeNameToCompareWith, 
            String textIdenticalToAttributeValueMessageCode) {
        super();
        this.textElementBuilder = textElementBuilder;
        this.attrElementBuilder.setAttributeName(attributeNameToCompareWith);
        this.textIdenticalToAttributeValueMessageCode = 
                textIdenticalToAttributeValueMessageCode;
    }
    
    /**
     * Constructor.
     * 
     * @param textElementBuilder
     * @param attributeNameToCompareWith
     * @param textIdenticalToAttributeValueMessageCode
     * @param eeAttributeNameList 
     */
    public TextNotIdenticalToAttributeChecker(
            TextElementBuilder textElementBuilder,
            String attributeNameToCompareWith, 
            String textIdenticalToAttributeValueMessageCode, 
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.textElementBuilder = textElementBuilder;
        this.attrElementBuilder.setAttributeName(attributeNameToCompareWith);
        this.textIdenticalToAttributeValueMessageCode = 
                textIdenticalToAttributeValueMessageCode;
    }
    
    /**
     * Constructor
     * 
     * @param textElementBuilder
     * @param attributeNameToCompareWith
     * @param detectedSolution
     * @param textElementIdenticalToAttributeValueMessageCode 
     */
    public TextNotIdenticalToAttributeChecker(
            TextElementBuilder textElementBuilder,
            String attributeNameToCompareWith, 
            TestSolution detectedSolution,
            String textElementIdenticalToAttributeValueMessageCode) {
        this(textElementBuilder, 
             attributeNameToCompareWith,
             textElementIdenticalToAttributeValueMessageCode);
        this.detectedSolution = detectedSolution;
    }
    
    /**
     * Constructor.
     * 
     * @param textElementBuilder
     * @param attributeNameToCompareWith
     * @param detectedSolution
     * @param textElementIdenticalToAttributeValueMessageCode
     * @param eeAttributeNameList 
     */
    public TextNotIdenticalToAttributeChecker(
            TextElementBuilder textElementBuilder,
            String attributeNameToCompareWith, 
            TestSolution detectedSolution,
            String textElementIdenticalToAttributeValueMessageCode, 
            String... eeAttributeNameList) {
        this(textElementBuilder, 
             attributeNameToCompareWith, 
             textElementIdenticalToAttributeValueMessageCode, 
             eeAttributeNameList);
        this.detectedSolution = detectedSolution;
    }
    
    @Override
    public void doCheck(
            SSPHandler sspHandler, 
            Elements elements, 
            TestSolutionHandler testSolutionHandler) {
        for (Element el : elements) {
            testSolutionHandler.addTestSolution(
                    checkTextElementNotIdenticalToAnother(
                        el, 
                        textElementBuilder.buildTextFromElement(el)));
        }
    }

    /**
     * 
     * @param element
     * @param elementText
     * @return failed when a given text is identical to an attribute content, 
     * not applicable when the text is seen as null, NMI instead
     * 
     */
    private TestSolution checkTextElementNotIdenticalToAnother(
            Element element,
            String elementText) {
        
        if (elementText == null) {
            return detectedSolution;
        }

        String otherAttributeContent = attrElementBuilder.buildTextFromElement(element);
        
        if (StringUtils.contains(otherAttributeContent, elementText)) {
            
            addSourceCodeRemark(
                detectedSolution,
                element,
                textIdenticalToAttributeValueMessageCode);

            return detectedSolution;
            
        } else {
            return TestSolution.PASSED;
        }
    }

}