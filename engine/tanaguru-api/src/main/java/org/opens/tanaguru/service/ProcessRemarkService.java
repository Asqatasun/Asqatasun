/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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

    String DEFAULT_EVIDENCE = "Element-Name";
    String URL_EVIDENCE = "Url";
    String SNIPPET_EVIDENCE = "Snippet";
    int SNIPPET_MAX_LENGTH = 200;

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
    void initializeService(org.jsoup.nodes.Document document, String adaptedContent);
    
    /**
     *
     */
    void resetService();

    /**
     * @deprecated
     * 
     * @param processResult
     * @param node
     * @param messageCode
     * @param elementName
     */
    @Deprecated
    void addSourceCodeRemark(
            TestSolution processResult, 
            Node node,
            String messageCode, 
            String elementName);

    /**
     * @deprecated
     * 
     * @param processResult
     * @param node
     * @param messageCode
     * @param elementType
     * @param elementName
     */
    @Deprecated
    void addSourceCodeRemark(
            TestSolution processResult, 
            Node node,
            String messageCode, 
            String elementType, 
            String elementName);

    /**
     * 
     * @param processResult
     * @param element
     * @param messageCode
     */
    void addSourceCodeRemarkOnElement(
            TestSolution processResult, 
            Element element,
            String messageCode);
    
    /**
     * 
     * @param processResult
     * @param element
     * @param messageCode
     * @param evidenceElementList
     */
    void addSourceCodeRemarkOnElement(
            TestSolution processResult, 
            Element element,
            String messageCode, 
            Collection<EvidenceElement> evidenceElementList);

    /**
     * 
     * @param processResult
     * @param targetValue
     * @param messageCode
     * @param evidenceElementList 
     */
    void addSourceCodeRemark(
            TestSolution processResult, 
            String targetValue,
            String messageCode, 
            Collection<EvidenceElement> evidenceElementList);
    
    /**
     * @deprecated 
     * 
     * @param processResult
     * @param node
     * @param messageCode
     * @param evidenceElementList
     */
    @Deprecated
    void addSourceCodeRemark(
            TestSolution processResult, 
            Node node,
            String messageCode, 
            Collection<EvidenceElement> evidenceElementList);

    /**
     * @deprecated
     * @param processResult
     * @param node
     * @param messageCode
     * @param elementName
     * @return
     */
    @Deprecated
    SourceCodeRemark createSourceCodeRemark(
            TestSolution processResult,
            Node node, 
            String messageCode, 
            String elementName);
    
    /**
     *
     * @param processResult
     * @param element
     * @param messageCode
     * @return
     */
    SourceCodeRemark createSourceCodeRemark(
            TestSolution processResult,
            Element element, 
            String messageCode);
    
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
    void addProcessRemark(
            TestSolution processResult,
            String messageCode, 
            Collection<EvidenceElement> evidenceElementList);

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
    ProcessRemark createProcessRemark(
            TestSolution processResult,
            String messageCode, 
            Collection<EvidenceElement> evidenceElementList);

    /**
     * 
     * @param processResult
     * @param messageCode
     * @param value
     * @param url
     */
    void addConsolidationRemark(
            TestSolution processResult,
            String messageCode, 
            String value, 
            String url);
    
    /**
     * 
     * @param processResult
     * @param messageCode
     */
    void addConsolidationRemark(TestSolution processResult, String messageCode);

    /**
     *
     * @param processResult
     * @param messageCode
     * @param value
     * @param url
     * @return
     */
    ProcessRemark createConsolidationRemark(
            TestSolution processResult,
            String messageCode, 
            String value, 
            String url);
    
    /**
     *
     * @param processResult
     * @param messageCode
     * @return
     */
    ProcessRemark createConsolidationRemark(
            TestSolution processResult,
            String messageCode);

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
    
}