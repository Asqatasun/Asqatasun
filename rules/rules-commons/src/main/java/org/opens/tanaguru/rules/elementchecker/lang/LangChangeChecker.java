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

package org.opens.tanaguru.rules.elementchecker.lang;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandlerImpl;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.LANGUAGE_CHANGE_MISSING_MSG;

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
        return checkLanguageRelevancyRecursively(sspHandler, element, false);
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
            boolean langRedefinitionInProgress) {

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
            String lang = extractEffectiveLang(langDefinition);
            if (!StringUtils.equalsIgnoreCase(defaultLang, lang)) {
                langRedefinitionInProgress = true;
            } else {
                langRedefinitionInProgress = false;
            }
        }
      
        // we extract the textual content of the current element. If not empty 
        // we check the language relevancy 
        String extractedText = extractTextFromElement(element, false);
        if (isTextTestable(extractedText)) {
            newElementTested();
            if (langRedefinitionInProgress) {
                tsh.addTestSolution(
                    checkLanguageDifferentFromDefault(element, extractedText));
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
                    langRedefinitionInProgress)
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
    private TestSolution checkLanguageDifferentFromDefault(Element element, String text) {
        return checkLanguageRelevancy(
                element,
                text,
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
                text, 
                solutionOnIdentical, 
                solutionOnDifferent);
    }
    
}