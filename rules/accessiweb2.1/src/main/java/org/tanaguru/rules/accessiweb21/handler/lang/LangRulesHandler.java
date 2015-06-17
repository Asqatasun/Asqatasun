/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.rules.accessiweb21.handler.lang;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.tanaguru.entity.audit.EvidenceElement;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.reference.Nomenclature;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.rules.accessiweb21.EvidenceKeyStore;
import org.tanaguru.rules.accessiweb21.NodeAndAttributeKeyStore;
import org.tanaguru.rules.elementchecker.lang.detector.LanguageDetectionResult;
import org.tanaguru.rules.elementchecker.lang.detector.LanguageDetector;
import org.tanaguru.service.NomenclatureLoaderService;
import org.tanaguru.service.ProcessRemarkService;
import org.w3c.dom.Node;

/**
 *
 * @author jkowalczyk
 */
public final class LangRulesHandler {

    public static final String ALL_NODE_WITHOUT_LANG_ATTRIBUTE =
            "/HTML/BODY/descendant::*[not(ancestor-or-self::*[(@lang or @xml:lang) and name()!='HTML']) "
            + "and (normalize-space(text()) or normalize-space(@alt) or normalize-space(@title) or normalize-space(@summary) or normalize-space(@content) or normalize-space(@value))]";
    private static final String HTML_WITH_LANG_ATTRIBUTE =
            "/HTML[@lang or @xml:lang]";
     private static final String TAG_DETECTION_XPATH_EXPR ="/HTML//*[@lang or @xml:lang]";
    public static final String NODE_WITH_LANG_ATTRIBUTE =
            "/HTML/BODY/descendant::*[@lang or @xml:lang] ";
    private static final int DISPLAYABLE_TEXT_SIZE = 200;
    private static final String XHTML_DOCTYPE_NOM = "XhtmlDoctypeDeclarations";
    private static final String LANG_NOM = "ValidLanguageCode";
    public static final String LANG_ATTRIBUTE_MISSING_ON_HTML_TAG_MSG_CODE =
            "LangAttributeMissingOnHtml";
    public static final String LANG_ATTRIBUTE_MISSING_ON_WHOLE_PAGE_MSG_CODE =
            "LangAttributeMissingOnWholePage";
    public static final String WRONG_LANGUAGE_DECLARATION_MSG_CODE =
            "WrongLanguageDeclaration";
    public static final String SUSPECTED_RELEVANT_LANG_DECL_MSG_CODE =
            "SuspectedRelevantLanguageDeclaration";
    public static final String SUSPECTED_IRRELEVANT_LANG_DECL_MSG_CODE =
            "SuspectedIrrelevantLanguageDeclaration";
    public static final String IRRELEVANT_LANG_DECL_MSG_CODE =
            "IrrelevantLanguageDeclaration";
    public static final String UNDETECTED_LANG_MSG_CODE =
            "UndetectedLanguage";
    public static final String MALFORMED_LANGUAGE_DECLARATION_MSG_CODE =
            "MalformedLanguage";
    private static final String LANG_PATTERN_STR = "\\w+?";
    private static final String NON_ALPHANUMERIC_PATTERN_STR = "\\W+?";
    private static final String LANG_DECLARATION_PATTERN_STR = "\\w{2,3}(\\-\\w{2,})?$";
    private Pattern langPattern = Pattern.compile(LANG_PATTERN_STR);
    private Pattern nonAlphanumericPattern = Pattern.compile(NON_ALPHANUMERIC_PATTERN_STR);
    private Pattern langDeclarationPattern = Pattern.compile(LANG_DECLARATION_PATTERN_STR);
    public Pattern getNonAlphanumericPattern() {
        return nonAlphanumericPattern;
    }
    
    private int elementCounter = 0;
    public int getElementCounter() {
        return elementCounter;
    }

    public void setElementCounter(int elementCounter) {
        this.elementCounter = elementCounter;
    }

    private Set<String> xhtmlDoctypesSet = new HashSet<String>();
    public Set<String> getXhtmlDoctypesSet() {
        return xhtmlDoctypesSet;
    }

    private Set<String> validLanguagesSet = new HashSet<String>();
    public Set<String> getValidLanguagesSet() {
        return validLanguagesSet;
    }
    /**
     * The SSPHandler
     */
    private SSPHandler sspHandler;
    public SSPHandler getSSPHandler() {
        return sspHandler;
    }

    public void setSSPHandler(SSPHandler sspHandler) {
        elementCounter = 0;
        this.sspHandler = sspHandler;
    }

    private NomenclatureLoaderService nomenclatureLoaderService;
    public void setNomenclatureLoaderService(NomenclatureLoaderService nomenclatureLoaderService) {
        this.nomenclatureLoaderService = nomenclatureLoaderService;
        loadXhtmlDoctypes();
        loadValidLanguages();
    }

    private ProcessRemarkService processRemarkService;
    public void setProcessRemarkService(ProcessRemarkService processRemarkService) {
        this.processRemarkService = processRemarkService;
    }

    public ProcessRemarkService getProcessRemarkService() {
        return processRemarkService;
    }

    /**
     * Default private constructor
     */
    public LangRulesHandler() {}

    /**
     *
     * @return
     *      all the nodes without any lang attribute
     */
    public List<Node> getAllNodeWithoutLangAttribute() {
        return sspHandler.beginSelection().domXPathSelectNodeSet(
                ALL_NODE_WITHOUT_LANG_ATTRIBUTE).getSelectedElementList();
    }

    /**
     *
     * @return
     *     all the nodes with a lang attribute
     */
    public List<Node> getNodeWithLangAttribute() {
        return sspHandler.beginSelection().domXPathSelectNodeSet(
                NODE_WITH_LANG_ATTRIBUTE).getSelectedElementList();
    }

    /**
     * 
     * @return
     */
    public String extractTextContentOfThePage() {
        List<Node> nodeList = getAllNodeWithoutLangAttribute();
        return extractTextContentFromNodeList(nodeList);
    }

    /**
     * This method extracts the text content of a node and recursively of all
     * its child nodes
     * @param node
     * @return
     */
    public String extractTextContentFromNodeAndChildNode(Node node) {
        StringBuilder strb = new StringBuilder();
        strb.append(extractTextContentFromNode(node));
        if (node.getChildNodes().getLength() > 0) {
            for (int i = 0; i < node.getChildNodes().getLength(); i++) {
                if (!node.getChildNodes().item(i).getNodeName().equalsIgnoreCase(NodeAndAttributeKeyStore.TEXT_NODE) &&
                        !doesNodeContainLangAttribute(node.getChildNodes().item(i))) {
                    strb.append(extractTextContentFromNodeAndChildNode(node.getChildNodes().item(i)));
                }
            }
        }
        return strb.toString().trim();
    }

    /**
     * This method extract the text content of a node through its alt attribute,
     * its title attribute and its text content
     * @return
     */
    private String extractTextContentFromNodeList(List<Node> nodeList) {
        StringBuilder strb = new StringBuilder();
        for (Node node : nodeList) {
            strb.append(extractTextContentFromNode(node));
        }
        return strb.toString();
    }

    /**
     * This method extracts the text from a node, which is the association of
     * the textual attributes (alt and title) and the raw text of the node.
     * @param node
     * @return
     */
    public String extractTextContentFromNode(Node node) {
        StringBuilder strb = new StringBuilder();
        strb.append(extractTextContentFromAnAttribute(node, NodeAndAttributeKeyStore.ALT_ATTR));
        strb.append(NodeAndAttributeKeyStore.SPACE_CHAR);
        strb.append(extractTextContentFromAnAttribute(node, NodeAndAttributeKeyStore.TITLE_ATTR));
        strb.append(NodeAndAttributeKeyStore.SPACE_CHAR);
        strb.append(extractTextContentFromAnAttribute(node, NodeAndAttributeKeyStore.SUMMARY_ATTR));
        strb.append(NodeAndAttributeKeyStore.SPACE_CHAR);
        strb.append(extractTextContentFromAnAttribute(node, NodeAndAttributeKeyStore.CONTENT_ATTR));
        strb.append(NodeAndAttributeKeyStore.SPACE_CHAR);
        strb.append(extractTextContentFromAnAttribute(node, NodeAndAttributeKeyStore.VALUE_ATTR));
        strb.append(NodeAndAttributeKeyStore.SPACE_CHAR);
        strb.append(extractRawTextContentFromANode(node));
        strb.append(NodeAndAttributeKeyStore.SPACE_CHAR);
        return strb.toString();
    }

    /**
     * This method extracts the text from an attribute
     *
     * @param strb
     * @param node
     * @param attrName
     */
    private String extractTextContentFromAnAttribute(Node node, String attrName) {
        if (node.getAttributes() != null) {
            Node attrnode = node.getAttributes().getNamedItem(attrName);
            if (attrnode != null && attrnode.getTextContent() != null
                    && !attrnode.getTextContent().isEmpty()) {
                return attrnode.getTextContent().trim();
            }
        }
        return "";
    }

    /**
     * This method extracts the text from a node.
     * @param strb
     * @param node
     * @param attrName
     */
    private String extractRawTextContentFromANode(Node node) {
        for (int i = 0; i < node.getChildNodes().getLength(); i++) {
            if (node.getChildNodes().item(i).getNodeName().equalsIgnoreCase(NodeAndAttributeKeyStore.TEXT_NODE)
                    && !node.getChildNodes().item(i).getNodeValue().trim().isEmpty()) {
                return node.getChildNodes().item(i).getNodeValue().trim();
            }
        }
        return "";
    }

    /**
     * 
     * @return
     *      true if the language is provided by the html tag, false instead.
     */
    public boolean isLanguageProvidedByHtmlTag() {
        return sspHandler.beginSelection().domXPathSelectNodeSet(
                HTML_WITH_LANG_ATTRIBUTE).getSelectedElementList().isEmpty() ? false : true;
    }

    /**
     * 
     * @return
     *      true if no language information is provided on the whole page.
     */
    public boolean isLanguageAbsentOnThePage() {
        return sspHandler.beginSelection().domXPathSelectNodeSet(
                NODE_WITH_LANG_ATTRIBUTE).getSelectedElementList().isEmpty() ? true : false;
    }

    /**
     * 
     * @return
     */
    public Node getHtmlTagWithLangAttribute() {
        List<Node> nodeList = sspHandler.beginSelection().domXPathSelectNodeSet(
                HTML_WITH_LANG_ATTRIBUTE).getSelectedElementList();
        if (!nodeList.isEmpty() && nodeList.size() == 1) {
            return nodeList.iterator().next();
        }
        return null;
    }

    public boolean doesNodeContainLangAttribute(Node node) {
        if (node.getAttributes() == null) {
            return false;
        }
        return (node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.LANG_ATTR) != null ||
                node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.XML_LANG_ATTR) != null) ?
                    true : false;
    }

    /**
     * This method extracts the lang attribute from a node. If the doctype of
     * document is xhtml compliant, the xml:lang attribute is predominant.
     *
     * @param node
     * @return
     */
    public String extractLanguageFromNode(Node node) {
        String lang = extractTextContentFromAnAttribute(node, NodeAndAttributeKeyStore.LANG_ATTR);
        lang = extractLangFromLangAttributeValue(lang);
        String xmlLang = extractTextContentFromAnAttribute(node, NodeAndAttributeKeyStore.XML_LANG_ATTR);
        xmlLang = extractLangFromLangAttributeValue(xmlLang);
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
            } else if (!hasSSPXhtmlDoctype()) {
                return lang;
            } else {
                // if the doctype defines a xhtml document, returns the xml:lang
                // attribute value
                return xmlLang;
            }
        }
    }

    /**
     * This method loads the allowed xhtml doctypes nomenclature
     */
    private void loadXhtmlDoctypes() {
        if (xhtmlDoctypesSet.isEmpty()) {
            Nomenclature xhtmlDoctypes =
                    nomenclatureLoaderService.loadByCode(XHTML_DOCTYPE_NOM);
            xhtmlDoctypesSet.addAll(xhtmlDoctypes.getValueList());
        }
    }

    /**
     * This method loads the valid languages nomenclature
     */
    private void loadValidLanguages() {
        if (validLanguagesSet.isEmpty()) {
            Nomenclature validLanguages =
                    nomenclatureLoaderService.loadByCode(LANG_NOM);
            validLanguagesSet.addAll(validLanguages.getValueList());
        }
    }

    /**
     * @return
     *      true if the ssp embeds a xhtml doctype, false instead.
     */
    private boolean hasSSPXhtmlDoctype() {
        return xhtmlDoctypesSet.contains(sspHandler.getSSP().getDoctype()) ? true : false;
    }

    /**
     * The language attribute can be set on 2 or 3 digits regarding the
     * ISO 639-2.
     * @param attributeValue
     * @return
     */
    private String extractLangFromLangAttributeValue(String attributeValue) {
        int attributeLength = attributeValue.length();
        Matcher m = langPattern.matcher(attributeValue);
        if ((attributeLength == 2 || attributeLength == 3) && m.matches()) {
            return attributeValue;
        } else if (attributeLength > 1 && attributeValue.contains("-")) {
            int separatorIndex = attributeValue.indexOf('-');
            return attributeValue.substring(0, separatorIndex);
        }
        return attributeValue;
    }

    public TestSolution checkLanguageDeclarationValidity(Node node, String extractedLang) {
        TestSolution testSolution = TestSolution.PASSED;
        if (!isLangWellDeclared(extractedLang)) {
            testSolution = TestSolution.FAILED;
            addInvalidDeclarationSourceCodeRemark(
                    node,
                    extractedLang,
                    testSolution,
                    MALFORMED_LANGUAGE_DECLARATION_MSG_CODE);
        }
        else if(!validLanguagesSet.contains(extractedLang.toLowerCase())) {
            testSolution = TestSolution.FAILED;
            addInvalidDeclarationSourceCodeRemark(
                    node,
                    extractedLang,
                    testSolution,
                    WRONG_LANGUAGE_DECLARATION_MSG_CODE);
        }
        return testSolution;
    }

    /**
     * 
     * @param node
     * @param extractedLang
     * @param testSolution
     * @param message
     */
    private void addInvalidDeclarationSourceCodeRemark(
            Node node,
            String extractedLang,
            TestSolution testSolution,
            String message) {
        List<EvidenceElement> evidenceElementList =
                new ArrayList<EvidenceElement>();

        evidenceElementList.add(processRemarkService.getEvidenceElement(
                processRemarkService.getEvidenceDataService().findByCode(EvidenceKeyStore.LANGUAGE_EE).getCode(),
                extractedLang));
        
        processRemarkService.addSourceCodeRemark(
                testSolution,
                node,
                message,
                evidenceElementList);
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
     * 
     * @param node
     * @param extractedLang
     * @param extractedText
     */
    public TestSolution checkLanguageRelevancy(Node node, String extractedLang, String extractedText) {
        LanguageDetectionResult ldr = LanguageDetector.getInstance().detectLanguage(extractedText);
        if (ldr == null) {
            addSourceCodeRemark(
                    TestSolution.NEED_MORE_INFO,
                    node,
                    UNDETECTED_LANG_MSG_CODE,
                    extractedLang,
                    "",
                    extractedText);
            return TestSolution.NEED_MORE_INFO;
        }  else if (!ldr.isReliable()) {
            if (extractedLang.equalsIgnoreCase(ldr.getDetectedLanguage())) {
                addSourceCodeRemark(
                        TestSolution.NEED_MORE_INFO,
                        node,
                        SUSPECTED_RELEVANT_LANG_DECL_MSG_CODE,
                        extractedLang,
                        ldr.getDetectedLanguage(),
                        extractedText);
                return TestSolution.NEED_MORE_INFO;
            } else {
                addSourceCodeRemark(
                        TestSolution.NEED_MORE_INFO,
                        node,
                        SUSPECTED_IRRELEVANT_LANG_DECL_MSG_CODE,
                        extractedLang,
                        ldr.getDetectedLanguage(),
                        extractedText);
                return TestSolution.NEED_MORE_INFO;
            }
        } else if (!extractedLang.equalsIgnoreCase(ldr.getDetectedLanguage())) {
            addSourceCodeRemark(
                    TestSolution.FAILED,
                    node,
                    IRRELEVANT_LANG_DECL_MSG_CODE,
                    extractedLang,
                    ldr.getDetectedLanguage(),
                    extractedText);
            return TestSolution.FAILED;
        } else {
            return TestSolution.PASSED;
        }
    }

    /**
     * Create a sourceCodeRemark with the link text and the value of the
     * title attribute as arguments
     * @param testSolution
     * @param node
     * @param message
     * @param linkTitleAttrValue
     * @param linkTextValue
     */
    private void addSourceCodeRemark(
            TestSolution testSolution,
            Node node,
            String message,
            String lang,
            String detectedLang,
            String testedText) {

        List<EvidenceElement> evidenceElementList =
                new ArrayList<EvidenceElement>();

        evidenceElementList.add(processRemarkService.getEvidenceElement(
                processRemarkService.getEvidenceDataService().findByCode(EvidenceKeyStore.LANGUAGE_EE).getCode(),
                lang));

        evidenceElementList.add(processRemarkService.getEvidenceElement(
                processRemarkService.getEvidenceDataService().findByCode(EvidenceKeyStore.DETECTED_LANGUAGE_EE).getCode(),
                detectedLang));

        if (testedText.length() > DISPLAYABLE_TEXT_SIZE) {
            testedText = testedText.substring(0, DISPLAYABLE_TEXT_SIZE);
        }
        evidenceElementList.add(processRemarkService.getEvidenceElement(
                processRemarkService.getEvidenceDataService().findByCode(EvidenceKeyStore.EXTRACTED_TEXT_EE).getCode(),
                testedText));

        processRemarkService.addSourceCodeRemark(
                testSolution,
                node,
                message,
                evidenceElementList);
    }

}