/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.processing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import javax.annotation.Nullable;
import javax.xml.xpath.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.asqatasun.entity.audit.*;
import org.asqatasun.entity.service.audit.EvidenceDataService;
import org.asqatasun.entity.service.audit.EvidenceElementDataService;
import org.asqatasun.entity.service.audit.ProcessRemarkDataService;
import org.asqatasun.service.ProcessRemarkService;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 * @author jkowalczyk
 */
public class ProcessRemarkServiceImpl implements ProcessRemarkService {

    private static final Logger LOGGER = Logger.getLogger(ProcessRemarkServiceImpl.class);

    private static final String ESCAPED_OPEN_TAG = "&lt;";
    private static final String ESCAPED_CLOSE_TAG = "&gt;";
    private static final String CLOSURE_TAG_OCCUR = "/";
    private static final String AUTO_CLOSE_TAG_OCCUR = 
                CLOSURE_TAG_OCCUR+ESCAPED_CLOSE_TAG;
    private static final String CLOSE_TAG_OCCUR = 
                ESCAPED_OPEN_TAG + CLOSURE_TAG_OCCUR;
    
    private static final String CSS_SELECTOR_EVIDENCE = "Css-Selector";
    private static final String CSS_FILENAME_EVIDENCE = "Css-Filename";
    private static final String START_COMMENT_OCCURENCE = "<!--";
    private static final String END_COMMENT_OCCURENCE = "-->";
    private final XPath xpath = XPathFactory.newInstance().newXPath();
    private Document document;
    private org.jsoup.nodes.Document jsoupDocument;

    /**
     * 
     * @param processRemarkDataService
     * @param evidenceElementDataService
     * @param evidenceDataService
     */
    public ProcessRemarkServiceImpl(
            ProcessRemarkDataService processRemarkDataService,
            EvidenceElementDataService evidenceElementDataService,
            EvidenceDataService evidenceDataService) {
        super();
        this.processRemarkDataService = processRemarkDataService;
        this.evidenceElementDataService = evidenceElementDataService;
        this.evidenceDataService = evidenceDataService;
    }

    /**
     * 
     * @param document
     */
    public void setDocument(Document document) {
        this.document = document;
    }
    
    public void setJsoupDocument(org.jsoup.nodes.Document document) {
        this.jsoupDocument = document;
    }
    
    private Collection<ProcessRemark> remarkSet;
    @Override
    public Collection<ProcessRemark> getRemarkList() {
        return this.remarkSet;
    }
    
    private List<String> evidenceElementList = new ArrayList<>();
    @Override
    public void addEvidenceElement(String element) {
        if (!evidenceElementList.contains(element)) {
            evidenceElementList.add(element);
        }
    }

    @Override
    public void setEvidenceElementList(Collection<String> element) {
        evidenceElementList.addAll(element);
    }
    
    private ProcessRemarkDataService processRemarkDataService;
    public ProcessRemarkDataService getProcessRemarkDataService() {
        return processRemarkDataService;
    }

    public void setProcessRemarkFactory(ProcessRemarkDataService processRemarkDataService) {
        this.processRemarkDataService = processRemarkDataService;
    }
    
    private EvidenceElementDataService evidenceElementDataService;

    @Override
    public EvidenceElementDataService getEvidenceElementDataService() {
        return evidenceElementDataService;
    }

    public void setEvidenceElementFactory(EvidenceElementDataService evidenceElementDataService) {
        this.evidenceElementDataService = evidenceElementDataService;
    }
    private EvidenceDataService evidenceDataService;

    @Override
    public EvidenceDataService getEvidenceDataService() {
        return evidenceDataService;
    }

    public void setEvidenceDataService(EvidenceDataService evidenceDataService) {
        this.evidenceDataService = evidenceDataService;
    }
    private Map<Integer, String> sourceCodeWithLine = null;
            
    private Map<Integer, String> rawSourceCodeWithLine = null;
            
    /**
     * Local map of evidence to avoid multiple access to database
     */
    private final Map<String, Evidence> evidenceMap = new HashMap<>();

    @Override
    public void initializeService(Document document, String adaptedContent) {
        if (document != null) {
            this.document = document;
        }
        if (adaptedContent != null && sourceCodeWithLine == null) {
            initializeSourceCodeMap(adaptedContent);
        }
        // call the reset service to instanciated local remarks collection
        // and evidence elements collection
        resetService();
    }
    
    /**
     * The initialisation of the jquery like should be called once for each 
     * tested SSP. It stores the current document and initialised a map with the
     * source where each key is the line number. This map is then used to 
     * retrieve the line number of an element.
     * 
     * @param document
     * @param adaptedContent 
     */
    @Override
    public void initializeService(org.jsoup.nodes.Document document, String adaptedContent) {
        Date beginDate = null;

        if (document != null) {
            this.jsoupDocument = document;
        }
        if (LOGGER.isDebugEnabled()) {
            beginDate = new Date();
            LOGGER.debug("Initialising source Map by line");
        }
        if (adaptedContent != null) {
            initializeRawSourceCodeMap(adaptedContent);
        }
        if (LOGGER.isDebugEnabled()) {
            Date endDate = new Date();
            LOGGER.debug("initialisation of source map by line took " 
                    + (endDate.getTime()-beginDate.getTime()) +"ms");
        }
        // call the reset service to instanciated local remarks collection
        // and evidence elements collection
        resetService();
    }

    @Override
    public void resetService() {
        LOGGER.debug("Service is reset");
        remarkSet = new LinkedHashSet<>();
        evidenceElementList = new LinkedList<>();
    }

    @Override
    public ProcessRemark createProcessRemark(
            TestSolution processResult,
            String messageCode) {
        return processRemarkDataService.getProcessRemark(processResult, messageCode);
    }

    @Override
    public void addProcessRemark(TestSolution processResult, String messageCode) {
        remarkSet.add(processRemarkDataService.getProcessRemark(processResult, messageCode));
    }

    @Override
    public void addSourceCodeRemark(TestSolution processResult, Node node,
            String messageCode, String attributeName) {
        remarkSet.add(createSourceCodeRemark(
                processResult,
                node,
                messageCode,
                attributeName));
    }
    
    @Override
    public void addSourceCodeRemarkOnElement(TestSolution processResult, Element element,
            String messageCode) {
        remarkSet.add(createSourceCodeRemark(
                processResult,
                element,
                messageCode));
    }

    @Override
    public void addSourceCodeRemarkOnElement(
            TestSolution processResult, 
            Element element,
            String messageCode, 
            Collection<EvidenceElement> evidenceElementList) {
        SourceCodeRemark remark = 
                processRemarkDataService.getSourceCodeRemark(
                        processResult, 
                        messageCode);
        
        if (element != null) {
            remark.setLineNumber(searchElementLineNumber(element));
            remark.setTarget(element.nodeName());
            remark.setSnippet(getSnippetFromElement(element));
        } else {
            remark.setLineNumber(-1);
        }
        if (CollectionUtils.isNotEmpty(evidenceElementList)) {
            for (EvidenceElement ee : evidenceElementList) {
                remark.addElement(ee);
                ee.setProcessRemark(remark);
            }
        }
        remarkSet.add(remark);
    }
    
    @Override
    public void addSourceCodeRemark(
            TestSolution processResult, 
            String targetValue,
            String messageCode, 
            EvidenceElement... evidenceElementList) {
        
        SourceCodeRemark remark = 
                processRemarkDataService.getSourceCodeRemark(
                        targetValue, 
                        processResult, 
                        messageCode, 
                        -1);

        for (EvidenceElement ee : evidenceElementList) {
            remark.addElement(ee);
            ee.setProcessRemark(remark);
        }
        remarkSet.add(remark);
    }
    
    @Override
    public void addSourceCodeRemark(
            TestSolution processResult, 
            Node node,
            String messageCode, 
            Collection<EvidenceElement> evidenceElementList) {
        SourceCodeRemark remark = 
                processRemarkDataService.getSourceCodeRemark(
                        processResult, 
                        messageCode);
        
        if (node != null) {
            remark.setLineNumber(searchNodeLineNumber(node));
        } else {
            remark.setLineNumber(-1);
        }
        for (EvidenceElement element : evidenceElementList) {
            remark.addElement(element);
            element.setProcessRemark(remark);
        }
        remarkSet.add(remark);
    }

    @Override
    public void addCssCodeRemark(TestSolution processResult,
            String selectorValue, 
            String messageCode, 
            String attrName, 
            String fileName) {// XXX
        SourceCodeRemark remark = 
                processRemarkDataService.getSourceCodeRemark(
                        processResult, 
                        messageCode);
        // This a fake sourceCode Remark, the line number cannot be found
        // we use a sourceCodeRemark here to add evidence elements
        remark.setLineNumber(-1);
        
        EvidenceElement evidenceElement = 
                evidenceElementDataService.getEvidenceElement(
                        remark, 
                        attrName, 
                        getEvidence(DEFAULT_EVIDENCE));

        remark.addElement(evidenceElement);
        try {
            if (selectorValue != null) {
                EvidenceElement cssSelectorEvidenceElement = 
                        evidenceElementDataService.getEvidenceElement(
                                remark, 
                                selectorValue, 
                                getEvidence(CSS_SELECTOR_EVIDENCE));

                remark.addElement(cssSelectorEvidenceElement);
            }
            if (fileName != null) {
                EvidenceElement fileNameEvidenceElement = 
                        evidenceElementDataService.getEvidenceElement(
                                remark, 
                                fileName, 
                                getEvidence(CSS_FILENAME_EVIDENCE));
                remark.addElement(fileNameEvidenceElement);
            }
        } catch (ClassCastException ex) {
            LOGGER.error(ex.getMessage());
        }
        remarkSet.add(remark);
    }

    @Override
    public void addSourceCodeRemark(
            TestSolution processResult,
            Node node,
            String messageCode,
            String elementType,
            String elementName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * 
     * @param node
     * @return
     */
    private int searchNodeLineNumber(Node node) {
        int nodeIndex = getNodeIndex(node);
        int lineNumber = 0;
        boolean found = false;
        boolean isWithinComment = false;
        Iterator<Map.Entry<Integer, String>> iter = sourceCodeWithLine.entrySet().iterator();
        String codeLine;
        while (iter.hasNext() && !found) {
            Map.Entry<Integer, String> entry = iter.next();
            int myLineNumber = entry.getKey();
            int index = 0;
            while (index != -1) {
                codeLine = entry.getValue().toLowerCase();
                int characterPositionOri = index;
                index = codeLine.indexOf("<" + node.getNodeName().toLowerCase() + ">",
                        index);
                if (index == -1) {
                    index = codeLine.indexOf("<" + node.getNodeName().toLowerCase() + " ",
                            characterPositionOri);
                }
                int startCommentIndex = codeLine.indexOf(
                        START_COMMENT_OCCURENCE, characterPositionOri);
                int endCommentIndex = codeLine.indexOf(
                        END_COMMENT_OCCURENCE, characterPositionOri);
                if (index != -1) { // if an occurence of the tag is found
                    if (!isWithinComment
                            && !(startCommentIndex != -1 && index > startCommentIndex)
                            && !(endCommentIndex != -1 && index < endCommentIndex)) { // if a comment is not currently opened or a comment is found on the current line and the occurence is not within
                        if (nodeIndex == 0) {
                            found = true;
                            lineNumber = myLineNumber;
                            break;
                        }
                        nodeIndex--;
                    }
                    index += node.getNodeName().length();
                }
                // if a "start comment" occurence is found on the line,
                // the boolean isWithinComment is set to true. Thus, while a
                // "end comment" is not found, all the occurences of the
                // wanted node will be ignored
                if (!isWithinComment && startCommentIndex != -1 && endCommentIndex == -1) {
                    isWithinComment = true;
                } else if (isWithinComment && endCommentIndex != -1 && startCommentIndex < endCommentIndex) {
                    isWithinComment = false;
                }
            }
        }
        return lineNumber;
    }
    
    /**
     * 
     * @param element
     * @return
     */
    private int searchElementLineNumber(Element element) {
        int nodeIndex = getElementIndex(element);
        int lineNumber = 0;
        boolean found = false;
        boolean isWithinComment = false;
        Iterator<Map.Entry<Integer, String>> iter = rawSourceCodeWithLine.entrySet().iterator();
        String codeLine;
        while (iter.hasNext() && !found) {
            Map.Entry<Integer, String> entry = iter.next();
            int myLineNumber = entry.getKey();
            int index = 0;
            while (index != -1) {
                codeLine = entry.getValue().toLowerCase();
                int characterPositionOri = index;
                index = codeLine.indexOf("<" + element.nodeName() + ">",
                        index);
                if (index == -1) {
                    index = codeLine.indexOf("<" + element.nodeName() + " ",
                            characterPositionOri);
                }
                int startCommentIndex = codeLine.indexOf(
                        START_COMMENT_OCCURENCE, characterPositionOri);
                int endCommentIndex = codeLine.indexOf(
                        END_COMMENT_OCCURENCE, characterPositionOri);
                if (index != -1) { // if an occurence of the tag is found
                    if (!isWithinComment
                            && !(startCommentIndex != -1 && index > startCommentIndex)
                            && !(endCommentIndex != -1 && index < endCommentIndex)) { // if a comment is not currently opened or a comment is found on the current line and the occurence is not within
                        if (nodeIndex == 0) {
                            found = true;
                            lineNumber = myLineNumber;
                            break;
                        }
                        nodeIndex--;
                    }
                    index += element.nodeName().length();
                }
                // if a "start comment" occurence is found on the line,
                // the boolean isWithinComment is set to true. Thus, while a
                // "end comment" is not found, all the occurences of the
                // wanted node will be ignored
                if (!isWithinComment && startCommentIndex != -1 && endCommentIndex == -1) {
                    isWithinComment = true;
                } else if (isWithinComment && endCommentIndex != -1 && startCommentIndex < endCommentIndex) {
                    isWithinComment = false;
                }
            }
        }
        return lineNumber;
    }

    /**
     * This methods search the line where the current node is present in
     * the source code
     * @param node
     * @return
     */
    private int getNodeIndex(Node node) {
        try {
            XPathExpression xPathExpression = xpath.compile("//" + node.getNodeName().toUpperCase());
            Object result = xPathExpression.evaluate(document,
                    XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node current = nodeList.item(i);
                if (current.equals(node)) {
                    return i;
                }
            }
        } catch (XPathExpressionException ex) {
            LOGGER.error("Error occured while searching index of a node "+
                    ex.getMessage());
            throw new RuntimeException(ex);
        }
        return -1;
    }
    
    /**
     * This methods search the line where the current node is present in
     * the source code
     * @param element
     * @return
     */
    private int getElementIndex(Element element) {
        Elements elements = jsoupDocument.getElementsByTag(element.tagName());
        for (int i = 0; i < elements.size(); i++) {
            Element current = elements.get(i);
            if (current.equals(element)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Initialisation of a local map that handles each source code line, 
     * keyed by the line number
     * 
     * @param adaptedContent
     */
    private void initializeSourceCodeMap(String adaptedContent) {
        sourceCodeWithLine = new LinkedHashMap<>();
        int lineNumber = 1;
        StringReader sr = new StringReader(adaptedContent);
        BufferedReader br = new BufferedReader(sr);
        String line;
        try {
            while ((line = br.readLine()) != null) {
                sourceCodeWithLine.put(lineNumber, line);
                lineNumber++;
            }
        } catch (IOException ex) {
            LOGGER.error("Error occured while initialize source code map "+
                    ex.getMessage());
        }
    }
    
    /**
     * Initialisation of a local map that handles each source code line, 
     * keyed by the line number
     * 
     * @param rawSource
     */
    private void initializeRawSourceCodeMap(String rawSource) {
        rawSourceCodeWithLine = new LinkedHashMap<>();
        int lineNumber = 1;
        StringReader sr = new StringReader(rawSource);
        BufferedReader br = new BufferedReader(sr);
        String line;
        try {
            while ((line = br.readLine()) != null) {
                if (StringUtils.isNotBlank(line)) {
                    rawSourceCodeWithLine.put(lineNumber, line);
                    lineNumber++;
                }
            }
        } catch (IOException ex) {
            LOGGER.error("Error occured while initialize raw source code map "+
                    ex.getMessage());
        }
    }

    @Override
    public EvidenceElement getEvidenceElement(String evidenceCode, String evidenceValue) {
        return evidenceElementDataService.getEvidenceElement(
                StringUtils.trim(evidenceValue), 
                getEvidence(evidenceCode));
    }

    @Override
    public SourceCodeRemark createSourceCodeRemark(
            TestSolution processResult,
            Node node,
            String messageCode,
            String elementName) {
        SourceCodeRemark remark = 
                processRemarkDataService.getSourceCodeRemark(
                        processResult, 
                        messageCode);

        remark.setLineNumber(searchNodeLineNumber(node));

        EvidenceElement evidenceElement = 
                evidenceElementDataService.getEvidenceElement(
                        remark, 
                        elementName, 
                        getEvidence(DEFAULT_EVIDENCE));
        
        for (String attr : evidenceElementList) {
            if (node.getAttributes().getNamedItem(attr) != null) {
                EvidenceElement evidenceElementSup = 
                    evidenceElementDataService.getEvidenceElement(
                            remark, 
                            node.getAttributes().getNamedItem(attr).getNodeValue(), 
                            getEvidence(attr));
                remark.addElement(evidenceElementSup);
            }
        }
        remark.addElement(evidenceElement);
        return remark;
    }
    
    @Override
    public SourceCodeRemark createSourceCodeRemark(
            TestSolution processResult,
            Element element,
            String messageCode) {
        
        SourceCodeRemark remark = 
                processRemarkDataService.getSourceCodeRemark(
                        element.nodeName(), 
                        processResult, 
                        messageCode, 
                        searchElementLineNumber(element));
        
        remark.setSnippet(getSnippetFromElement(element));
        for (String attr : evidenceElementList) {
            EvidenceElement evidenceElementSup;
            if (StringUtils.equalsIgnoreCase(attr, "text")) {
                evidenceElementSup = getEvidenceElement(attr, element.text());
            } else {
                evidenceElementSup = getEvidenceElement(attr, element.attr(attr));
            }
            remark.addElement(evidenceElementSup);
        }
        return remark;
    }

    @Override
    public void addConsolidationRemark(
            TestSolution processResult,
            String messageCode,
            String value,
            String url) {
        remarkSet.add(createConsolidationRemark(
                processResult,
                messageCode,
                value,
                url));
    }

    @Override
    public void addConsolidationRemark(
            TestSolution processResult,
            String messageCode) {
        remarkSet.add(createConsolidationRemark(
                processResult,
                messageCode));
    }

    @Override
    public void addProcessRemark(
            TestSolution processResult,
            String messageCode,
            Collection<EvidenceElement> evidenceElementList) {
        remarkSet.add(createProcessRemark(
                processResult,
                messageCode,
                evidenceElementList));
    }

    @Override
    public ProcessRemark createProcessRemark(
            TestSolution processResult,
            String messageCode,
            Collection<EvidenceElement> evidenceElementList) {
        
        ProcessRemark remark = processRemarkDataService.getProcessRemark(processResult,messageCode);
        
        for (EvidenceElement element : evidenceElementList) {
            remark.addElement(element);
            element.setProcessRemark(remark);
        }
        return remark;
    }

    @Override
    public ProcessRemark createConsolidationRemark(
            TestSolution processResult,
            String messageCode) {
        return processRemarkDataService.getProcessRemark(processResult, messageCode);
    }

    @Override
    public ProcessRemark createConsolidationRemark(
            TestSolution processResult,
            String messageCode,
            @Nullable String value,
            @Nullable String url) {
        ProcessRemark remark = createConsolidationRemark(processResult, messageCode);

        if (value != null) {
            EvidenceElement evidenceElement = 
                evidenceElementDataService.getEvidenceElement(
                        remark, 
                        value, 
                        getEvidence(DEFAULT_EVIDENCE));
            remark.addElement(evidenceElement);
        }
        if (url != null) {
            EvidenceElement evidenceElement = 
                evidenceElementDataService.getEvidenceElement(
                        remark, 
                        url, 
                        getEvidence(URL_EVIDENCE));
            remark.addElement(evidenceElement);
        }
        return remark;
    }

    /**
     * Return an evidence instance for a given code. This method avoids multiple
     * access to mysql databases, by maintaining a map. 
     * @param code
     * @return
     */
    public Evidence getEvidence(String code) {
        if (evidenceMap.containsKey(code)) {
            return evidenceMap.get(code);
        } else {
            Evidence evidence = evidenceDataService.findByCode(code);
            evidenceMap.put(code, evidence);
            return evidence;
        }
    }
    
    /**
     * 
     * @param element
     * @return 
     */
    public String getSnippetFromElement(Element element) {
        String elementHtml = StringEscapeUtils.escapeHtml4(
                StringUtil.normaliseWhitespace(element.outerHtml())).trim();
        if (element.children().isEmpty() || elementHtml.length() <= SNIPPET_MAX_LENGTH) {
            return elementHtml;
        }
        return properlyCloseSnippet(
                    element,
                    elementHtml,
                    elementHtml.substring(0, SNIPPET_MAX_LENGTH));
        }

    /**
     * 
     * @param element
     * @param originalElementHtml
     * @param truncatedElementHtml
     * @return 
     */
    private String properlyCloseSnippet (
            Element element, 
            String originalElementHtml, 
            String truncatedElementHtml) {
        if (isElementAutoClose(originalElementHtml)) {
            return originalElementHtml;
        }

        if (getElementCurrentlyOpenCount(truncatedElementHtml) == 1) {
            return closeInnerElement(originalElementHtml, truncatedElementHtml);
        } else if (getElementCurrentlyOpenCount(truncatedElementHtml) > 1) {
            truncatedElementHtml = 
                    closeInnerElement(originalElementHtml, truncatedElementHtml);
            return closeElement(truncatedElementHtml, element.tagName());
        } else {
            return closeElement(truncatedElementHtml, element.tagName());
        }
    }
    
    /**
    * 
    * @param elementHtml
    * @return 
    */
    private boolean isElementAutoClose(String elementHtml) {
        return StringUtils.endsWith(elementHtml, AUTO_CLOSE_TAG_OCCUR);
    }
    
    /**
    * 
    * @param elementHtml
    * @return 
    */
    private int getElementCurrentlyOpenCount(String elementHtml) {
        int openTagCounter = 
                StringUtils.countMatches(elementHtml, ESCAPED_OPEN_TAG)
                -
                StringUtils.countMatches(elementHtml, CLOSE_TAG_OCCUR);
        int closureTagCounter = 
                StringUtils.countMatches(elementHtml, AUTO_CLOSE_TAG_OCCUR) 
                + 
                StringUtils.countMatches(elementHtml, CLOSE_TAG_OCCUR) ;
        return (openTagCounter - closureTagCounter);
    }
    
    /**
     * 
     * @param originalElementHtml
     * @return 
     */
    private String closeElement(String elementHtml, String elementName) {
        StringBuilder strb =  new StringBuilder();
        strb.append(elementHtml);
        strb.append("[...]");
        strb.append(ESCAPED_OPEN_TAG);
        strb.append(CLOSURE_TAG_OCCUR);
        strb.append(elementName);
        strb.append(ESCAPED_CLOSE_TAG);
        return strb.toString();
    }
    
        /**
     * 
     * @param originalElementHtml
     * @param truncatedElementHtml
     * @param indexOfElementToClose
     * @return 
     */
    private String closeInnerElement (
            String originalElementHtml, 
            String truncatedElementHtml) {
        
        int startPos = truncatedElementHtml.length();
        
        int indexOfElementCloser = 
                StringUtils.indexOf(originalElementHtml, ESCAPED_CLOSE_TAG, startPos);
        int indexOfElementAutoCloser = 
                StringUtils.indexOf(originalElementHtml, AUTO_CLOSE_TAG_OCCUR, startPos);
        
        
        String innerClosedElement = StringUtils.substring(
                originalElementHtml, 
                0, 
                (indexOfElementCloser+ESCAPED_CLOSE_TAG.length()));
        
        // if the current element is auto-close, return current well-closed element
        if (indexOfElementAutoCloser == (indexOfElementCloser-1) ) {
            return innerClosedElement;
        }
        
        // if the current element is not auto-close, get the name of it and 
        // and properly close it
        int indexOfElementOpenTagOpener = 
                StringUtils.lastIndexOf(originalElementHtml, ESCAPED_OPEN_TAG, startPos);
        
        int indexOfElementOpenTagClose = 
                StringUtils.indexOf(originalElementHtml, ' ', indexOfElementOpenTagOpener);
        
        String elementName = 
                StringUtils.substring(
                    originalElementHtml, 
                    indexOfElementOpenTagOpener + ESCAPED_OPEN_TAG.length(), 
                    indexOfElementOpenTagClose);

        return closeElement(innerClosedElement, elementName);
    }

}
