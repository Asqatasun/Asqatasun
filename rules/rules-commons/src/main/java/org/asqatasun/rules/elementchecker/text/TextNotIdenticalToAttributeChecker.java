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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.rules.elementchecker.ElementCheckerImpl;
import org.asqatasun.rules.textbuilder.TextElementBuilder;

/**
 * This class checks whether the text of a dom element is not strictly or
 * partially identical to the text of an attribute of this element.
 * 
 * @author jkowalczyk
 */
public class TextNotIdenticalToAttributeChecker extends ElementCheckerImpl {

    /* The text element builder. By default, it is a simple Text builder */
    private final TextElementBuilder testableTextBuilder;
    
    /* The attr element builder needed to retrieve the attribute to compare with*/
    private final TextElementBuilder attrElementBuilder;

    /* 
     * Boolean that enables to determine the kind of control : contains or equals.
     * Contains is the default 
     */
    private boolean strictEquality = false;
    public void setStrictEquality(boolean strictEquality) {
        this.strictEquality = strictEquality;
    }

    /**
     * Constructor.
     * 
     * @param testableTextBuilder
     * @param testableTextBuilderToCompareWith
     * @param detectedSolutionPair
     * @param notDetectedSolutionPair
     * @param eeAttributeNameList 
     */
    public TextNotIdenticalToAttributeChecker(
            TextElementBuilder testableTextBuilder,
            TextElementBuilder testableTextBuilderToCompareWith, 
            Pair<TestSolution, String> detectedSolutionPair,
            Pair<TestSolution, String> notDetectedSolutionPair,
            String... eeAttributeNameList) {
        super(
                notDetectedSolutionPair, 
                detectedSolutionPair,
                eeAttributeNameList);
        this.testableTextBuilder = testableTextBuilder;
        this.attrElementBuilder = testableTextBuilderToCompareWith;
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
                        testableTextBuilder.buildTextFromElement(el)));
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
            
            addSourceCodeRemark(getFailureSolution(),element,getFailureMsgCode());
            return getFailureSolution();
            
        } else {
            addSourceCodeRemark(getSuccessSolution(),element,getSuccessMsgCode());
            return getSuccessSolution();
        }
    }

}
