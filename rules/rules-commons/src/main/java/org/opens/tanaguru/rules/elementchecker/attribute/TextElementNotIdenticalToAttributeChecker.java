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
import org.opens.tanaguru.rules.elementchecker.TextualElementBuilder;

/**
 *
 */
public class TextElementNotIdenticalToAttributeChecker extends ElementCheckerImpl implements 
        TextualElementBuilder {

    /**
     * The message thrown when an element belongs to the black list.
     */
    private String textElementIdenticalToAttributeValueMessageCode;
    
    /**
     * The attribute name to compare with the current text element
     */
    private String attributeNameToCompareWith;
    
    /**
     * Detected solution. Default is FAILED.
     */
    private TestSolution detectedSolution = TestSolution.FAILED;
    
    /**
     * Constructor.
     * 
     * @param attributeNameToCompareWith
     * @param textElementIdenticalToAttributeValueMessageCode
     */
    public TextElementNotIdenticalToAttributeChecker(
            String attributeNameToCompareWith, 
            String textElementIdenticalToAttributeValueMessageCode) {
        super();
        
        this.attributeNameToCompareWith = attributeNameToCompareWith;
        this.textElementIdenticalToAttributeValueMessageCode = 
                textElementIdenticalToAttributeValueMessageCode;
    }
    
    /**
     * Constructor.
     * 
     * @param attributeNameToCompareWith
     * @param textElementIdenticalToAttributeValueMessageCode
     * @param eeAttributeNameList 
     */
    public TextElementNotIdenticalToAttributeChecker(
            String attributeNameToCompareWith, 
            String textElementIdenticalToAttributeValueMessageCode, 
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        
        this.attributeNameToCompareWith = attributeNameToCompareWith;
        this.textElementIdenticalToAttributeValueMessageCode = 
                textElementIdenticalToAttributeValueMessageCode;
    }
    
    /**
     * Constructor.
     * 
     * @param attributeNameToCompareWith
     * @param textElementIdenticalToAttributeValueMessageCode
     */
    public TextElementNotIdenticalToAttributeChecker(
            String attributeNameToCompareWith, 
            TestSolution detectedSolution,
            String textElementIdenticalToAttributeValueMessageCode) {
        super();
        this.attributeNameToCompareWith = attributeNameToCompareWith;
        this.detectedSolution = detectedSolution;
        this.textElementIdenticalToAttributeValueMessageCode = 
                textElementIdenticalToAttributeValueMessageCode;
    }
    
    /**
     * Constructor.
     * 
     * @param attributeNameToCompareWith
     * @param textElementIdenticalToAttributeValueMessageCode
     * @param eeAttributeNameList 
     */
    public TextElementNotIdenticalToAttributeChecker(
            String attributeNameToCompareWith, 
            TestSolution detectedSolution,
            String textElementIdenticalToAttributeValueMessageCode, 
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.attributeNameToCompareWith = attributeNameToCompareWith;
        this.detectedSolution = detectedSolution;
        this.textElementIdenticalToAttributeValueMessageCode = 
                textElementIdenticalToAttributeValueMessageCode;
    }
    
    @Override
    public String buildTextFromElement(Element element) {
        return element.text().trim();
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
                        buildTextFromElement(el)));
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

        String otherAttributeContent = getAttributeContent(element, attributeNameToCompareWith);
        
        if (StringUtils.contains(otherAttributeContent, elementText)) {
            
            addSourceCodeRemark(
                detectedSolution,
                element,
                textElementIdenticalToAttributeValueMessageCode);

            return detectedSolution;
            
        } else {
            return TestSolution.PASSED;
        }
    }

}