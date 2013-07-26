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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.reference.Nomenclature;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;
import static org.opens.tanaguru.rules.keystore.AttributeStore.LANG_ATTR;
import static org.opens.tanaguru.rules.keystore.AttributeStore.XML_LANG_ATTR;
import static org.opens.tanaguru.rules.keystore.EvidenceStore.*;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.*;
import org.opens.tanaguru.rules.utils.LanguageDetectionResult;
import org.opens.tanaguru.rules.utils.LanguageDetector;

/**
 * 
 * This class checks whether the doctype is valid regarding two nomenclatures
 * of doctypes, the case-sensitive ones and the case-insensitive ones.
 */
public class LangDeclarationValidityChecker extends NomenclatureBasedElementChecker {

    private static final String LANG_PATTERN_STR = "\\w+?";
    private static final String NON_ALPHANUMERIC_PATTERN_STR = "\\W+?";
    private static final String LANG_DECLARATION_PATTERN_STR = "\\w{2,3}(\\-\\w{2,})?$";
    private Pattern langPattern = Pattern.compile(LANG_PATTERN_STR);
    private Pattern nonAlphanumericPattern = Pattern.compile(NON_ALPHANUMERIC_PATTERN_STR);
    private Pattern langDeclarationPattern = Pattern.compile(LANG_DECLARATION_PATTERN_STR);
    private Collection<String> xhtmlDoctypesSet;
    private Collection<String> validLanguagesSet;
    private static final String XHTML_DOCTYPE_NOM = "XhtmlDoctypeDeclarations";
    private static final String LANG_NOM = "ValidLanguageCode";
    private static final int DISPLAYABLE_TEXT_SIZE = 200;

    /**
     * Default constructor
     */
    public LangDeclarationValidityChecker() {
        super();
    }

     @Override
    protected void doCheck(
             SSPHandler sspHandler, 
             Elements elements, 
             TestSolutionHandler testSolutionHandler) {
         loadXhtmlDoctypes();
         loadValidLanguages();
         for (Element el : elements) {
             testSolutionHandler.addTestSolution(
                     checkLanguageDeclarationValidity(el, sspHandler));
         }
    }

    /**
     * 
     * @param element
     * @param sspHandler
     * @return 
     */
    public TestSolution checkLanguageDeclarationValidity(Element element, SSPHandler sspHandler) {
        String lang = extractLangFromElement(element, sspHandler);
        String extractedText = extractTextFromElement(element);
        Matcher m = nonAlphanumericPattern.matcher(extractedText);
        if (!extractedText.isEmpty() && !m.matches()) {
            if (checkLanguageDeclarationValidity(element, lang).equals(TestSolution.FAILED)) {
                if (checkLanguageRelevancy(element, lang, extractedText).equals(TestSolution.FAILED)) {
                    return TestSolution.FAILED;
                }
            } else {
                return TestSolution.FAILED;
            }
        }
        return TestSolution.PASSED;
    }
    
    /**
     * 
     * @param element
     * @param lang
     * @return 
     */
    private TestSolution checkLanguageDeclarationValidity(Element element, String lang) {
        TestSolution testSolution = TestSolution.PASSED;
        if (!isLangWellDeclared(lang)) {
            testSolution = TestSolution.FAILED;
            addInvalidDeclarationSourceCodeRemark(
                    element,
                    lang,
                    testSolution,
                    MALFORMED_LANGUAGE_DECLARATION_MSG);
        }
        else if(!validLanguagesSet.contains(lang)) {
            testSolution = TestSolution.FAILED;
            addInvalidDeclarationSourceCodeRemark(
                    element,
                    lang,
                    testSolution,
                    WRONG_LANGUAGE_DECLARATION_MSG);
        }
        return testSolution;
    }
    
    /**
     * 
     * @param element
     * @param extractedLang
     * @param extractedText
     */
    public TestSolution checkLanguageRelevancy(Element element, String extractedLang, String extractedText) {
        LanguageDetectionResult ldr = LanguageDetector.getInstance().detectLanguage(extractedText);
        if (ldr == null) {
            addSourceCodeRemark(
                    TestSolution.NEED_MORE_INFO,
                    element,
                    UNDETECTED_LANG_MSG,
                    extractedLang,
                    "",
                    extractedText);
            return TestSolution.NEED_MORE_INFO;
        }  else if (!ldr.isReliable()) {
            if (extractedLang.equalsIgnoreCase(ldr.getDetectedLanguage())) {
                addSourceCodeRemark(
                        TestSolution.NEED_MORE_INFO,
                        element,
                        SUSPECTED_RELEVANT_LANG_DECL_MSG,
                        extractedLang,
                        ldr.getDetectedLanguage(),
                        extractedText);
                return TestSolution.NEED_MORE_INFO;
            } else {
                addSourceCodeRemark(
                        TestSolution.NEED_MORE_INFO,
                        element,
                        SUSPECTED_IRRELEVANT_LANG_DECL_MSG,
                        extractedLang,
                        ldr.getDetectedLanguage(),
                        extractedText);
                return TestSolution.NEED_MORE_INFO;
            }
        } else if (!extractedLang.equalsIgnoreCase(ldr.getDetectedLanguage())) {
            addSourceCodeRemark(
                    TestSolution.FAILED,
                    element,
                    IRRELEVANT_LANG_DECL_MSG,
                    extractedLang,
                    ldr.getDetectedLanguage(),
                    extractedText);
            return TestSolution.FAILED;
        } else {
            return TestSolution.PASSED;
        }
    }
    
    /**
     * 
     * @param element
     * @param sspHandler
     * @return 
     */
    private String extractLangFromElement(Element element, SSPHandler sspHandler) {
        String lang = element.attr(LANG_ATTR).trim();
        String xmlLang = element.attr(XML_LANG_ATTR).trim();
        // if the lang attributes are identical, return one of them
        if (xmlLang.isEmpty() && !lang.isEmpty()) {
            return lang;
        } else if (!xmlLang.isEmpty() && lang.isEmpty() ) {
            return xmlLang;
        } else {
            if (xmlLang.equalsIgnoreCase(lang)) {
                return lang;
                // if the doctype defines html document, returns the lang attribute
                // value
            } else if (!hasSSPXhtmlDoctype(sspHandler)) {
                return lang;
            } else {
                // if the doctype defines a xhtml document, returns the xml:lang
                // attribute value
                return xmlLang;
            }
        }
    }
    
    /**
     * 
     * @param element
     * @return 
     */
    private String extractTextFromElement(Element element) {
        return element.text().substring(0, 200);
    }

    /**
     * @return
     *      true if the ssp embeds a xhtml doctype, false instead.
     */
    private boolean hasSSPXhtmlDoctype(SSPHandler sspHandler) {
        return xhtmlDoctypesSet.contains(sspHandler.getSSP().getDoctype()) ? true : false;
    }

    /**
     * This method loads the allowed xhtml doctypes nomenclature
     */
    private void loadXhtmlDoctypes() {
        if (xhtmlDoctypesSet == null) {
            Nomenclature xhtmlDoctypes =
                    getNomenclatureLoaderService().loadByCode(XHTML_DOCTYPE_NOM);
            xhtmlDoctypesSet = xhtmlDoctypes.getValueList();
        }
    }

    /**
     * This method loads the valid languages nomenclature
     */
    private void loadValidLanguages() {
        if (validLanguagesSet == null) {
            Nomenclature validLanguages =
                    getNomenclatureLoaderService().loadByCode(LANG_NOM);
            validLanguagesSet = validLanguages.getValueList();
        }
    }
    
    /**
     * 
     * @param extractedLang
     * @return
     */
    public boolean isLangWellDeclared(String extractedLang) {
        Matcher m = langDeclarationPattern.matcher(extractedLang);
        return m.matches()?true:false;
    }

    /**
     * Create a sourceCodeRemark with the link text and the value of the
     * title attribute as arguments
     * @param testSolution
     * @param element
     * @param message
     * @param linkTitleAttrValue
     * @param linkTextValue
     */
    private void addSourceCodeRemark(
            TestSolution testSolution,
            Element element,
            String message,
            String lang,
            String detectedLang,
            String testedText) {

        List<EvidenceElement> evidenceElementList = new ArrayList<EvidenceElement>();

        evidenceElementList.add(getEvidenceElement(LANGUAGE_EE, lang));
        evidenceElementList.add(getEvidenceElement(DETECTED_LANGUAGE_EE, detectedLang));
        
        if (testedText.length() > DISPLAYABLE_TEXT_SIZE) {
            testedText = testedText.substring(0, DISPLAYABLE_TEXT_SIZE);
        }
        evidenceElementList.add(getEvidenceElement(EXTRACTED_TEXT_EE, testedText));
        
        getProcessRemarkService().addSourceCodeRemarkOnElement(
                testSolution,
                element,
                message,
                evidenceElementList);
    }
    
    /**
     * 
     * @param element
     * @param extractedLang
     * @param testSolution
     * @param message
     */
    private void addInvalidDeclarationSourceCodeRemark(
            Element element,
            String extractedLang,
            TestSolution testSolution,
            String message) {
        List<EvidenceElement> evidenceElementList =
                new ArrayList<EvidenceElement>();

        evidenceElementList.add(getEvidenceElement(LANGUAGE_EE, extractedLang));
        
        getProcessRemarkService().addSourceCodeRemarkOnElement(
                testSolution,
                element,
                message,
                evidenceElementList);
    }
   
}