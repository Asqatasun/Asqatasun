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

package org.tanaguru.rules.elementchecker.lang;

import javax.annotation.Nullable;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandlerImpl;
import static org.tanaguru.rules.keystore.RemarkMessageStore.LANGUAGE_CHANGE_MISSING_MSG;
import org.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * 
 * This class checks whether a lang change occurs on the page but is not
 * identified by some markup. To do so, we parse all the textual nodes of the 
 * page, determine the lang through the language detection library and checks
 * whether it corresponds with the set language by the current node or one of
 * its parents.
 *
 */
public class LangChangeChecker extends LangChecker {

    /** the default lang of the page **/
    private String defaultLang;
    
    /**
     * Default constructor
     */
    public LangChangeChecker() {
        super();
    }
    
    /**
     * Default constructor
     * @param textElementBuilder
     */
    public LangChangeChecker(TextElementBuilder textElementBuilder) {
        super();
        setTestableTextElementBuilder(textElementBuilder);
    }
    
    @Override
    protected TestSolution doCheckLanguage(Element element, SSPHandler sspHandler) {
        return checkLanguageChange(element, sspHandler);
    }

    /**
     * 
     * @param element
     * @param sspHandler
     * @return 
     */
    private TestSolution checkLanguageChange(Element element, SSPHandler sspHandler) {
        String langDefinition = extractLangDefinitionFromElement(element, sspHandler);
        defaultLang = extractEffectiveLang(langDefinition);
        TestSolution declarationValidity = 
                checkLanguageDeclarationValidity(
                    element, 
                    langDefinition, 
                    defaultLang,
                    false);
        if (declarationValidity.equals(TestSolution.FAILED)) {
            return TestSolution.NOT_APPLICABLE;
        }
        // the handler may contain the html element or nothing
        return checkLanguageRelevancyRecursively(sspHandler, element, null);
    }
    
    /**
     * 
     * @param sspHandler
     * @param element
     * @param checkCurrentElement
     * @return 
     */
    private TestSolution checkLanguageRelevancyRecursively(
            SSPHandler sspHandler,
            Element element, 
            @Nullable String currentLangRedefinitionValue) {
        
        String currentLangRedefinition = currentLangRedefinitionValue;
        
        TestSolutionHandler tsh = new TestSolutionHandlerImpl();
        // if the current element defines a lang, we extract its value and check
        // it is different from the default one. If it is different, we start
        // the check considering that the lang of the current node and all its 
        // children have to be different from the default one.
        // If it is identical or if the current element does not define a lang, 
        // we start the check considering that the lang of the current node and all its 
        // children have to be identical to the default one.
        if (isLangDefinedForElement(element)) {
            String langDefinition = extractLangDefinitionFromElement(element, sspHandler);
            String currentLang = extractEffectiveLang(langDefinition);
            if (!StringUtils.equalsIgnoreCase(defaultLang, currentLang)) {
                currentLangRedefinition = currentLang;
            } else {
                currentLangRedefinition = null;
            }
        }
      
        // we extract the textual content of the current element. If not empty 
        // we check the language relevancy 
        String extractedText = extractTextFromElement(element, false);
        if (isTextTestable(extractedText)) {
            newElementTested();
            if (StringUtils.isNotBlank(currentLangRedefinition)) {
                tsh.addTestSolution(
                    checkLanguageDifferentFromDefault(element, extractedText, currentLangRedefinition));
            } else {
                tsh.addTestSolution(
                    checkLanguageIdenticalToDefault(element, extractedText));
            }
        }
        
        for (Element el : element.children()) {
            tsh.addTestSolution(
                checkLanguageRelevancyRecursively(
                    sspHandler, 
                    el, 
                    currentLangRedefinition)
                );
        }
        return tsh.getTestSolution();
    }
    
    /**
     * 
     * @param element
     * @param text
     * @return 
     */
    private TestSolution checkLanguageIdenticalToDefault(Element element, String text) {
        return checkLanguageRelevancy(
                element,
                text,
                defaultLang,
                null, // no current lang, set to null
                TestSolution.NOT_APPLICABLE,
                TestSolution.FAILED,
                null,
                LANGUAGE_CHANGE_MISSING_MSG,
                null,
                null);
    }
    
    /**
     * 
     * @param element
     * @param text
     * @return 
     */
    private TestSolution checkLanguageDifferentFromDefault(
            Element element, 
            String text, 
            String currentLangDefinition) {
        return checkLanguageRelevancy(
                element,
                text,
                defaultLang,
                currentLangDefinition,
                TestSolution.FAILED,
                TestSolution.PASSED,
                LANGUAGE_CHANGE_MISSING_MSG,
                null,
                null,
                null);
    }
    
    /**
     * 
     * @param element
     * @param lang
     * @param text
     * @param defaultLang
     * @param solutionOnIdentical
     * @param solutionOnDifferent
     * @param identicalLangMessage
     * @param differentLangMessage
     * @param suspectedIdenticalLangMessage
     * @param suspectedDifferentLangMessage
     * @return 
     */
    private TestSolution checkLanguageRelevancy(
            Element element, 
            String text, 
            String defaultLang,
            @Nullable String currentLang,
            TestSolution solutionOnIdentical, 
            TestSolution solutionOnDifferent,
            String identicalLangMsg,
            String differentLangMsg,
            String suspectedIdenticalLangMsg,
            String suspectedDifferentLangMsg) {
        setDifferentLangMsg(differentLangMsg);
        setIdenticalLangMsg(identicalLangMsg);
        setSuspectedDifferentLangMsg(suspectedDifferentLangMsg);
        setSuspectedIdenticalLangMsg(suspectedIdenticalLangMsg);
        return checkLanguageRelevancy(
                element, 
                defaultLang, 
                currentLang,
                text, 
                solutionOnIdentical, 
                solutionOnDifferent);
    }
    
}