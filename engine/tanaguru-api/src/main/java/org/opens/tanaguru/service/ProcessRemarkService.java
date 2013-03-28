/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.service;

import java.util.Collection;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.contentadapter.css.CSSOMRule;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.factory.audit.EvidenceElementFactory;
import org.opens.tanaguru.entity.service.audit.EvidenceDataService;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * 
 * @author jkowalczyk
 */
public interface ProcessRemarkService {

    public static final String DEFAULT_EVIDENCE = "Element-Name";
    public static final String URL_EVIDENCE = "Url";
    public static final String SNIPPET_EVIDENCE = "Snippet";
    public static final int SNIPPET_MAX_LENGTH = 200;

    /**
     *
     * @param document
     * @param adaptedContent
     */
    void initializeService(Document document, String adaptedContent);

    /**
     * 
     * @param document
     * @param adaptedContent 
     */
    void initializeJQueryLikeService(org.jsoup.nodes.Document document, String adaptedContent);
    
    /**
     *
     */
    void initializeService();

    /**
     *
     * @param processResult
     * @param node
     * @param messageCode
     * @param elementName
     */
    void addSourceCodeRemark(TestSolution processResult, Node node,
            String messageCode, String elementName);

    /**
     *
     * @param processResult
     * @param node
     * @param messageCode
     * @param elementType
     * @param elementName
     */
    void addSourceCodeRemark(TestSolution processResult, Node node,
            String messageCode, String elementType, String elementName);

    /**
     * 
     * @param processResult
     * @param element
     * @param messageCode
     */
    void addSourceCodeRemarkOnElement(TestSolution processResult, Element element,
            String messageCode);
    
    /**
     * 
     * @param processResult
     * @param element
     * @param messageCode
     * @param evidenceElementList
     */
    void addSourceCodeRemarkOnElement(TestSolution processResult, Element element,
            String messageCode, Collection<EvidenceElement> evidenceElementList);

    /**
     *
     * @param processResult
     * @param node
     * @param messageCode
     * @param evidenceElementList
     */
    void addSourceCodeRemark(TestSolution processResult, Node node,
            String messageCode, Collection<EvidenceElement> evidenceElementList);

    /**
     *
     * @param processResult
     * @param node
     * @param messageCode
     * @param elementName
     * @return
     */
    SourceCodeRemark createSourceCodeRemark(TestSolution processResult,
            Node node, String messageCode, String elementName);
    
    /**
     *
     * @param processResult
     * @param element
     * @param messageCode
     * @return
     */
    SourceCodeRemark createSourceCodeRemark(TestSolution processResult,
            Element element, String messageCode);

    /**
     *
     * @param processResult
     * @param rule
     * @param messageCode
     * @param attrName
     */
    void addCssCodeRemark(TestSolution processResult,
            CSSOMRule rule, String messageCode, String attrName, String fileName);
    
    /**
     *
     * @param processResult
     * @param selectorValue
     * @param messageCode
     * @param attrName
     * @param fileName
     */
    void addCssCodeRemark(
            TestSolution processResult,
            String selectorValue, 
            String messageCode, 
            String attrName, 
            String fileName);

    /**
     *
     * @param processResult
     * @param messageCode
     */
    void addProcessRemark(TestSolution processResult, String messageCode);

    /**
     *
     * @param processResult
     * @param messageCode
     * @param evidenceElementList
     */
    void addProcessRemark(TestSolution processResult,
            String messageCode, Collection<EvidenceElement> evidenceElementList);

    /**
     *
     * @param processResult
     * @param messageCode
     * @return
     */
    ProcessRemark createProcessRemark(TestSolution processResult, String messageCode);

    /**
     *
     * @param processResult
     * @param messageCode
     * @param evidenceElementList
     * @return
     */
    ProcessRemark createProcessRemark(TestSolution processResult,
            String messageCode, Collection<EvidenceElement> evidenceElementList);

    /**
     * 
     * @param processResult
     * @param messageCode
     * @param value
     * @param url
     */
    void addConsolidationRemark(TestSolution processResult,
            String messageCode, String value, String url);

    /**
     *
     * @param processResult
     * @param messageCode
     * @param value
     * @param url
     * @return
     */
    ProcessRemark createConsolidationRemark(TestSolution processResult,
            String messageCode, String value, String url);

    /**
     *
     * @return
     *          the list of remarks created by the service
     */
    Collection<ProcessRemark> getRemarkList();

    /**
     *
     * @param evidenceCode
     * @param evidenceValue
     * @return
     */
    EvidenceElement getEvidenceElement(String evidenceCode, String evidenceValue);

    /**
     *
     * @param element
     */
    void addEvidenceElement(String element);

    /**
     *
     * @param element
     */
    void setEvidenceElementList(Collection<String> element);

    /**
     *
     * @return
     */
    EvidenceElementFactory getEvidenceElementFactory();

    /**
     *
     * @return
     */
    EvidenceDataService getEvidenceDataService();
    
    /**
     * 
     * @param element
     * @return 
     */
    EvidenceElement getSnippetEvidenceElement(Element element);

    /**
     * 
     * @param element
     * @return 
     */
    EvidenceElement getDefaultEvidenceElement(Element element);
    
}