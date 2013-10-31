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

import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementCheckerImpl;
import org.opens.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 *
 */
public class TextNotIdenticalToAttributeChecker extends ElementCheckerImpl {

    /* The message thrown when the text is identical to another textual element*/
    private String textIdenticalToAttributeValueMessageCode;
    
    /* The message thrown when the text is not identical to another textual element*/
    private String textNotIdenticalToAttributeValueMessageCode;
    
    /* The text element builder. By default, it is a simple Text builder */
    private TextElementBuilder textElementBuilder;
    
    /* The attr element builder needed to retrieve the attribute to compare with*/
    private TextElementBuilder attrElementBuilder;

    /* Boolean that enable to determine the kind of control : contains or equals.
     * Contains is the default 
     */
    private boolean strictEquality = false;
    public void setStrictEquality(boolean strictEquality) {
        this.strictEquality = strictEquality;
    }

    /**
     * Constructor.
     * 
     * @param textElementBuilder
     * @param attributeNameToCompareWith
     * @param textIdenticalToAttributeValueMessageCode
     * @param textNotIdenticalToAttributeValueMessageCode
     */
    public TextNotIdenticalToAttributeChecker(
            TextElementBuilder textElementBuilder,
            TextElementBuilder textElementBuilderToCompareWith, 
            String textIdenticalToAttributeValueMessageCode, 
            @Nullable String textNotIdenticalToAttributeValueMessageCode) {
        super();
        this.textElementBuilder = textElementBuilder;
        this.attrElementBuilder = textElementBuilderToCompareWith;
        this.textIdenticalToAttributeValueMessageCode = 
                textIdenticalToAttributeValueMessageCode;
        this.textNotIdenticalToAttributeValueMessageCode = 
                textNotIdenticalToAttributeValueMessageCode;
    }
    
    /**
     * Constructor.
     * 
     * @param textElementBuilder
     * @param attributeNameToCompareWith
     * @param textIdenticalToAttributeValueMessageCode
     * @param textNotIdenticalToAttributeValueMessageCode
     * @param eeAttributeNameList 
     */
    public TextNotIdenticalToAttributeChecker(
            TextElementBuilder textElementBuilder,
            TextElementBuilder textElementBuilderToCompareWith, 
            String textIdenticalToAttributeValueMessageCode, 
            @Nullable String textNotIdenticalToAttributeValueMessageCode,
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.textElementBuilder = textElementBuilder;
        this.attrElementBuilder = textElementBuilderToCompareWith;
        this.textIdenticalToAttributeValueMessageCode = 
                textIdenticalToAttributeValueMessageCode;
        this.textNotIdenticalToAttributeValueMessageCode = 
                textNotIdenticalToAttributeValueMessageCode;
    }
    
    /**
     * Constructor
     * 
     * @param textElementBuilder
     * @param attributeNameToCompareWith
     * @param detectedSolution
     * @param notDetectedSolution
     * @param textIdenticalToAttributeValueMessageCode 
     * @param textNotIdenticalToAttributeValueMessageCode 
     */
    public TextNotIdenticalToAttributeChecker(
            TextElementBuilder textElementBuilder,
            TextElementBuilder textElementBuilderToCompareWith,
            TestSolution detectedSolution,
            TestSolution notDetectedSolution,
            String textIdenticalToAttributeValueMessageCode, 
            String textNotIdenticalToAttributeValueMessageCode) {
        this(textElementBuilder, 
             textElementBuilderToCompareWith,
             textIdenticalToAttributeValueMessageCode,
             textNotIdenticalToAttributeValueMessageCode);
        setSuccessSolution(notDetectedSolution);
        setFailureSolution(detectedSolution);
    }
    
    /**
     * Constructor.
     * 
     * @param textElementBuilder
     * @param attributeNameToCompareWith
     * @param detectedSolution
     * @param notDetectedSolution
     * @param textIdenticalToAttributeValueMessageCode
     * @param textNotIdenticalToAttributeValueMessageCode
     * @param eeAttributeNameList 
     */
    public TextNotIdenticalToAttributeChecker(
            TextElementBuilder textElementBuilder,
            TextElementBuilder textElementBuilderToCompareWith, 
            TestSolution detectedSolution,
            TestSolution notDetectedSolution,
            String textIdenticalToAttributeValueMessageCode, 
            String textNotIdenticalToAttributeValueMessageCode, 
            String... eeAttributeNameList) {
        this(textElementBuilder, 
             textElementBuilderToCompareWith, 
             textIdenticalToAttributeValueMessageCode, 
             textNotIdenticalToAttributeValueMessageCode, 
             eeAttributeNameList);
        setSuccessSolution(notDetectedSolution);
        setFailureSolution(detectedSolution);
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
            return getFailureSolution();
        }

        String otherAttributeContent = attrElementBuilder.buildTextFromElement(element);
        
        if ( (strictEquality && 
                StringUtils.equalsIgnoreCase(otherAttributeContent, elementText)) || 
             ((!strictEquality && 
                StringUtils.contains(otherAttributeContent, elementText)))   ) {
            
            addSourceCodeRemark(
                getFailureSolution(),
                element,
                textIdenticalToAttributeValueMessageCode);

            return getFailureSolution();
            
        } else {
            if (StringUtils.isNotBlank(textNotIdenticalToAttributeValueMessageCode)) {
                addSourceCodeRemark(
                    getSuccessSolution(),
                    element,
                    textNotIdenticalToAttributeValueMessageCode);
            }
            return getSuccessSolution();
        }
    }

}