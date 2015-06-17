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

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.tanaguru.entity.audit.EvidenceElement;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.reference.Nomenclature;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;
import org.tanaguru.rules.elementchecker.lang.detector.LanguageDetectionResult;
import org.tanaguru.rules.elementchecker.lang.detector.LanguageDetector;
import org.tanaguru.rules.keystore.AttributeStore;
import static org.tanaguru.rules.keystore.AttributeStore.LANG_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.XML_LANG_ATTR;
import static org.tanaguru.rules.keystore.EvidenceStore.*;
import org.tanaguru.rules.keystore.HtmlElementStore;
import static org.tanaguru.rules.keystore.RemarkMessageStore.*;
import org.tanaguru.rules.textbuilder.CompleteTextElementBuilder;
import org.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * 
 * This class defines basic utility methods to deal with language checks.
 */
public abstract class LangChecker extends NomenclatureBasedElementChecker {

    private static final Logger LOGGER = Logger.getLogger(LangChecker.class);

    private static final String NON_ALPHANUMERIC_PATTERN_STR = "[\\d+\\W+]+?";
    private final Pattern nonAlphanumericPattern = Pattern.compile(NON_ALPHANUMERIC_PATTERN_STR);
    private static final String LANG_DECLARATION_PATTERN_STR = "\\w{2,3}(\\-\\w{2,})?$";
    private final Pattern langDeclarationPattern = Pattern.compile(LANG_DECLARATION_PATTERN_STR);
    private Collection<String> xhtmlDoctypesSet;
    private Collection<String> validLanguagesSet;
    private static final String XHTML_DOCTYPE_NOM = "XhtmlDoctypeDeclarations";
    private static final String LANG_NOM = "ValidLanguageCode";
    private static final int DISPLAYABLE_TEXT_SIZE = 200;
    private static final String[] EXCLUDED_ELEMENTS = {
                HtmlElementStore.SCRIPT_ELEMENT, 
                HtmlElementStore.CODE_ELEMENT, 
                HtmlElementStore.KBD_ELEMENT, 
                HtmlElementStore.SAMP_ELEMENT, 
                HtmlElementStore.TT_ELEMENT, 
                HtmlElementStore.VAR_ELEMENT, 
                HtmlElementStore.NO_FRAMES_ELEMENT, 
                HtmlElementStore.NO_SCRIPT_ELEMENT, 
                };
    private static final Collection<String> EXCLUDED_ELEMENTS_LIST = 
                Arrays.asList(EXCLUDED_ELEMENTS);

    private String suspectedIdenticalLangMsg;
    public void setSuspectedIdenticalLangMsg(String suspectedIdenticalLangMsg) {
        this.suspectedIdenticalLangMsg = suspectedIdenticalLangMsg;
    }
    
    private String suspectedDifferentLangMsg;
    public void setSuspectedDifferentLangMsg(String suspectedDifferentLangMsg) {
        this.suspectedDifferentLangMsg = suspectedDifferentLangMsg;
    }
    
    private String differentLangMsg;
    public void setDifferentLangMsg(String differentLangMsg) {
        this.differentLangMsg = differentLangMsg;
    }
    
    private String identicalLangMsg;
    public void setIdenticalLangMsg(String identicalLangMsg) {
        this.identicalLangMsg = identicalLangMsg;
    }
    
    /** the number of elements tested */
    private int nbOfElementsTested =0;
    public int getNbOfElementsTested() {
        return nbOfElementsTested;
    }
    public void newElementTested() {
        nbOfElementsTested++;
    }

    /* the text Element Builder used to extract text to test */
    private TextElementBuilder testableTextElementBuilder;
    public void setTestableTextElementBuilder(TextElementBuilder testableTextElementBuilder) {
        this.testableTextElementBuilder = testableTextElementBuilder;
    }
    
    /**
     * Default constructor
     */
    public LangChecker() {
        super();
    }
    
    /**
     * constructor
     * @param identicalLangMsg
     * @param differentLangMsg
     * @param suspectedIdenticalLangMsg
     * @param suspectedDifferentLangMsg 
     */
    public LangChecker(
            String identicalLangMsg,
            String differentLangMsg,
            String suspectedDifferentLangMsg,
            String suspectedIdenticalLangMsg) {
        super();
        this.identicalLangMsg = identicalLangMsg;
        this.differentLangMsg = differentLangMsg;
        this.suspectedIdenticalLangMsg = suspectedIdenticalLangMsg;
        this.suspectedDifferentLangMsg = suspectedDifferentLangMsg;
    }
    
    @Override
    protected void doCheck(
             SSPHandler sspHandler, 
             Elements elements, 
             TestSolutionHandler testSolutionHandler) {
         loadXhtmlDoctypes();
         loadValidLanguages();
         // the handler may contain the html element or nothing
         for (Element el : elements) {
             testSolutionHandler.addTestSolution(doCheckLanguage(el, sspHandler));
         }
    }
    
    /**
     * 
     * @param element
     * @param sspHandler 
     * @return  the solution of the check
     */
    protected abstract TestSolution doCheckLanguage(Element element, SSPHandler sspHandler);
    
    /**
     * 
     * @param element
     * @param langDefinition
     * @param effectiveLang
     * @param createProcessRemarkOnFailure
     * @return 
     */
    protected TestSolution checkLanguageDeclarationValidity(
            Element element, 
            String langDefinition, 
            String effectiveLang, 
            boolean createProcessRemarkOnFailure) {
        TestSolution testSolution = TestSolution.PASSED;
        if (!isLangWellDeclared(langDefinition)) {
            testSolution = TestSolution.FAILED;
            if (createProcessRemarkOnFailure) {
                addInvalidDeclarationSourceCodeRemark(
                    element,
                    langDefinition,
                    testSolution,
                    MALFORMED_LANGUAGE_DECLARATION_MSG);
            }
        } else if(!validLanguagesSet.contains(effectiveLang.toLowerCase())) {
            testSolution = TestSolution.FAILED;
            if (createProcessRemarkOnFailure) {
                addInvalidDeclarationSourceCodeRemark(
                    element,
                    effectiveLang,
                    testSolution,
                    WRONG_LANGUAGE_DECLARATION_MSG);
            }
        }
        return testSolution;
    }
    
    /**
     * 
     * @param element
     * @param defaultLang
     * @param currentLang
     * @param text
     * @param solutionOnIdentical
     * @param solutionOnDifferent
     * @return 
     */
    protected TestSolution checkLanguageRelevancy(
            Element element, 
            String defaultLang, 
            String currentLang, 
            String text, 
            TestSolution solutionOnIdentical,
            TestSolution solutionOnDifferent) {

        Long startDetection = null;
        if (LOGGER.isDebugEnabled()) {
            startDetection = Calendar.getInstance().getTime().getTime();
        }
        LanguageDetectionResult ldr = LanguageDetector.getInstance().detectLanguage(text);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Detection took " + 
                    (Calendar.getInstance().getTime().getTime() - startDetection)+
                    " ms on "+
                    text.length() +
                    " characters");
        }
        if (ldr == null) {
            addSourceCodeRemark(
                    TestSolution.NEED_MORE_INFO,
                    element,
                    UNDETECTED_LANG_MSG,
                    defaultLang,
                    currentLang,
                    "",
                    text);
            return TestSolution.NEED_MORE_INFO;
        }  

        boolean isLangIdentical = StringUtils.equalsIgnoreCase(defaultLang, ldr.getDetectedLanguage());

        if (isLangIdentical && ldr.isReliable()) {
            addSourceCodeRemark(
                        solutionOnIdentical,
                        element,
                        identicalLangMsg,
                        defaultLang,
                        currentLang,
                        ldr.getDetectedLanguage(),
                        text);
            return solutionOnIdentical;
        } else if (isLangIdentical && !ldr.isReliable()) {
            addSourceCodeRemark(
                        TestSolution.NEED_MORE_INFO,
                        element,
                        suspectedIdenticalLangMsg,
                        defaultLang,
                        currentLang,
                        ldr.getDetectedLanguage(),
                        text);
                return TestSolution.NEED_MORE_INFO;
        } else if (!isLangIdentical && ldr.isReliable()) {
            addSourceCodeRemark(
                        solutionOnDifferent,
                        element,
                        differentLangMsg,
                        defaultLang,
                        currentLang,
                        ldr.getDetectedLanguage(),
                        text);
            return solutionOnDifferent;
        } else  { //!isLangIdentical && !ldr.isReliable()
            addSourceCodeRemark(
                        TestSolution.NEED_MORE_INFO,
                        element,
                        suspectedDifferentLangMsg,
                        defaultLang,
                        currentLang,
                        ldr.getDetectedLanguage(),
                        text);
            return TestSolution.NEED_MORE_INFO;
        }
    }
    
    /**
     * 
     * @param element
     * @param sspHandler
     * @return 
     */
    protected String extractLangDefinitionFromElement(Element element, SSPHandler sspHandler) {
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
     * @param langDefinition
     * @return the language code (truncate language definition when option is
     * defined)
     */
    protected String extractEffectiveLang(String langDefinition) {
        int separatorIndex = StringUtils.indexOf(langDefinition, '-');
        if (separatorIndex != -1) {
            return StringUtils.substring(langDefinition,0,separatorIndex);
        }
        return langDefinition;
    }
    
    /**
     * 
     * @param element
     * @param extractRecursively
     * @return 
     */
    protected String extractTextFromElement (Element element, boolean extractRecursively) {
        if (EXCLUDED_ELEMENTS_LIST.contains(element.tagName())) {
            return null;
        }
        StringBuilder strb = new StringBuilder();
        if (testableTextElementBuilder == null) {
            testableTextElementBuilder = new CompleteTextElementBuilder();
        }
        strb.append(testableTextElementBuilder.buildTextFromElement(element));

        if (extractRecursively) {
            for (Element el : element.children()) {
                if (!isLangDefinedForElement(el) && 
                        !EXCLUDED_ELEMENTS_LIST.contains(el.tagName())) {
                    strb.append(TextElementBuilder.SPACER);
                    strb.append(extractTextFromElement(el, true));
                }
            }
        }
        return strb.toString().replaceAll(" +", " ");
    }
    
    /**
     * 
     * @param extractedText
     * @return 
     */
    protected boolean isTextTestable(String extractedText) {
        if (StringUtils.isBlank(extractedText)){
            return false;
        }
        String textToTest = StringUtils.trim(extractedText);
        Matcher m = nonAlphanumericPattern.matcher(textToTest);
        return !m.matches() && 
               !GenericValidator.isEmail(textToTest) && 
               !GenericValidator.isUrl(textToTest);
    }
    
    /**
     * 
     * @param element
     * @return whether the current element is defined with a "lang" attribute
     * or a "xml:lang" attribute
     */
    protected boolean isLangDefinedForElement(Element element) {
        return element.hasAttr(AttributeStore.LANG_ATTR) || 
                element.hasAttr(AttributeStore.XML_LANG_ATTR);
    }
    
    /**
     * @param sspHandler
     * @return
     *      true if the ssp embeds a xhtml doctype, false instead.
     */
    protected boolean hasSSPXhtmlDoctype(SSPHandler sspHandler) {
        return xhtmlDoctypesSet.contains(sspHandler.getSSP().getDoctype());
    }

    /**
     * This method loads the allowed xhtml doctypes nomenclature
     */
    protected void loadXhtmlDoctypes() {
        if (xhtmlDoctypesSet == null) {
            Nomenclature xhtmlDoctypes =
                    getNomenclatureLoaderService().loadByCode(XHTML_DOCTYPE_NOM);
            xhtmlDoctypesSet = xhtmlDoctypes.getValueList();
        }
    }

    /**
     * This method loads the valid languages nomenclature
     */
    protected void loadValidLanguages() {
        if (validLanguagesSet == null) {
            Nomenclature validLanguages =
                    getNomenclatureLoaderService().loadByCode(LANG_NOM);
            validLanguagesSet = validLanguages.getValueList();
        }
    }
    
    /**
     * 
     * @param extractedLang
     * @return whether a lang is well-defined regarding the lang declaration 
     * pattern
     */
    protected boolean isLangWellDeclared(String extractedLang) {
        Matcher m = langDeclarationPattern.matcher(extractedLang);
        return m.matches();
    }

    /**
     * Create a sourceCodeRemark with the link text and the value of the
     * title attribute as arguments
     * @param testSolution
     * @param element
     * @param message
     * @param defaultLang
     * @param currentLang
     * @param linkTextValue
     */
    private void addSourceCodeRemark(
            TestSolution testSolution,
            Element element,
            String message,
            String defaultLang,
            String currentLang,
            String detectedLang,
            String testedText) {
        if (testSolution.equals(TestSolution.PASSED) || 
                StringUtils.isBlank(message)) {
            return;
        }
        List<EvidenceElement> evidenceElementList = new ArrayList<>();

        if (StringUtils.isNotBlank(currentLang)) {
            evidenceElementList.add(getEvidenceElement(DEFAULT_LANGUAGE_EE, defaultLang));
            evidenceElementList.add(getEvidenceElement(CURRENT_LANGUAGE_EE, currentLang));
        } else {
            evidenceElementList.add(getEvidenceElement(LANGUAGE_EE, defaultLang));
        }
        evidenceElementList.add(getEvidenceElement(DETECTED_LANGUAGE_EE, detectedLang));
        
        if (testedText.length() > DISPLAYABLE_TEXT_SIZE) {
            testedText = testedText.substring(0, DISPLAYABLE_TEXT_SIZE);
        }
        evidenceElementList.add(getEvidenceElement(EXTRACTED_TEXT_EE, testedText));
        
        addSourceCodeRemark(testSolution, element, message, evidenceElementList);
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
        List<EvidenceElement> evidenceElementList = new ArrayList<>();

        evidenceElementList.add(getEvidenceElement(LANGUAGE_EE, extractedLang));
        
        addSourceCodeRemark(testSolution, element, message, evidenceElementList);
    }
   
}