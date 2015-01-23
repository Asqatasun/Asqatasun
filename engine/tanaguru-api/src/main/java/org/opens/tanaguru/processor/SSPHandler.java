/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
package org.opens.tanaguru.processor;

import com.phloc.css.decl.CascadingStyleSheet;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.jsoup.select.Elements;
import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.service.NomenclatureLoaderService;
import org.opens.tanaguru.service.ProcessRemarkService;
import org.w3c.dom.Node;

/**
 * 
 * @author jkowalczyk
 */
public interface SSPHandler {

    /**
     * Initialise document to allow parsing document with xpath requests
     * @return the current SSPHandler with an empty selection
     */
    SSPHandler beginSelection();
    
    /**
     * Initialise document to allow parsing css selectors
     * @return the current SSPHandler with an empty selection
     */
    SSPHandler beginCssSelection();
    
    /**
     * Initialise document to allow parsing document with css-like requests
     * 
     * @return the current SSPHandler with an empty selection
     */
    SSPHandler beginCssLikeSelection();
    
    /**
     *
     * @param attributeName
     *            the name of the attribute to check
     * @return the result of the check processing
     */
    TestSolution checkAttributeExists(String attributeName);

    /**
     *
     * @param childNodeName
     *            the name of the childNode to check
     * @return the result of the check processing
     */
    TestSolution checkChildNodeExists(String childNodeName);

    /**
     * 
     * @param blacklist
     *            the list of prevented values
     * @param whitelist
     *            the list of granted values
     * @param testSolution
     *            the test solution when the node value belongs to the black list
     * @param erroMessageCode
     *            the error message code
     * @return the result of the check processing
     */
    TestSolution checkNodeValue(
            Collection<String> blacklist,
            Collection<String> whitelist,
            TestSolution testSolution,
            String erroMessageCode);

    /**
     *
     * @param blacklist
     *            the list of prevented values
     * @param whitelist
     *            the list of granted values
     * @return the result of the check processing
     */
    TestSolution checkTextContentValue(Collection<String> blacklist,
            Collection<String> whitelist);

    /**
     *
     * @return the page of the ssp used
     */
    WebResource getPage();

    /**
     *
     * @return the remark list
     */
    Collection<ProcessRemark> getRemarkList();

    /**
     *
     * @return the selected element list
     */
    List<Node> getSelectedElementList();
    
    /**
     *
     * @return the selected element list (regarding jsoup API)
     */
    Elements getSelectedElements();
    
    /**
     *
     * @return the SSP
     */
    SSP getSSP();

    /**
     *
     * @return <tt>true</tt> if there are no elements selected
     */
    boolean isSelectedElementsEmpty();

    /**
     *
     * @param attributeName
     *            the name of the attribute to filter
     * @return the current DOMHandler instance
     */
    SSPHandler keepNodesWithAttribute(String attributeName);

    /**
     *
     * @param childNodeName
     *            the name of the attribute to filter
     * @return the current DOMHandler instance
     */
    SSPHandler keepNodesWithChildNode(String childNodeName);

    /**
     *
     * @param childNodeName
     *            the name of the childNode to select
     * @return the current DOMHandler instance
     */
    SSPHandler selectChildNodes(String childNodeName);

    /**
     *
     * @param childNodeName
     *            the name of the childnode to select recursively
     * @return the current DOMHandler instance
     */
    SSPHandler selectChildNodesRecursively(String childNodeName);

    /**
     *
     * @param nodeNames
     *            the names of the nodes to select
     * @return the current DOMHandler instance
     */
    SSPHandler selectDocumentNodes(Collection<String> nodeNames);

    /**
     *
     * @param nodeName
     *            the name of the nodeto select in all the document
     * @return the current DOMHandler instance
     */
    SSPHandler selectDocumentNodes(String nodeName);

    /**
     * http://www.ibm.com/developerworks/library/x-javaxpathapi.html
     *
     * @param expr
     * @return
     */
    SSPHandler domXPathSelectNodeSet(String expr);
    
    /**
     * 
     * @param expression
     * @return 
     */
    SSPHandler domCssLikeSelectNodeSet(String expression);

    /**
     *
     * @param selectedElementList
     *            the selected element list to set
     */
    void setSelectedElementList(List<Node> selectedElementList);

    /**
     *
     * @param ssp
     *            the SSP to set
     */
    void setSSP(SSP ssp);

    /**
     * Vérifie si la valeur des noeuds sélectionnés existe dans la nomenclature
     * @param nomenclatureCode
     * @param errorMessageCode
     */
    TestSolution domCheckNodeValueInNomenclature(String nomenclatureCode, String errorMessageCode);

    /**
     * return the recorded image associated with an URL
     * @param URL
     * @return
     */
    BufferedImage getImageFromURL(String URL);

    /**
     * This method checks whether an attribute only contains non alphanumeric
     * characters
     * @param attribute
     * @param workingElement
     * @param testSolution
     * @param remarkMessage
     * @return
     */
    TestSolution checkAttributeOnlyContainsNonAlphanumericCharacters(
            Node attribute,
            Node workingElement,
            TestSolution testSolution,
            String remarkMessage);

    /**
     * This method checks whether an attribute only contains non alphanumeric
     * characters
     * @param attributeContent
     * @param workingElement
     * @param testSolution
     * @param remarkMessage
     * @return
     */
    TestSolution checkAttributeOnlyContainsNonAlphanumericCharacters(
            String attributeContent,
            Node workingElement,
            TestSolution testSolution,
            String remarkMessage);

    /**
     * @return the number of selected elements
     */
    int getSelectedElementNumber();
    
    /**
     * @return the total number of elements on the page
     */
    int getTotalNumberOfElements();

    /**
     * 
     * @param processRemarkService
     */
    void setProcessRemarkService(ProcessRemarkService processRemarkService);

    /**
     * 
     * @return the processRemarkService
     */
    ProcessRemarkService getProcessRemarkService();

    /**
     *
     * @param messageCode
     */
    void setMessageCode(String messageCode);

    /**
     *
     * @param nomenclatureLoaderService
     */
    void setNomenclatureLoaderService(NomenclatureLoaderService nomenclatureLoaderService);

    /**
     *
     * @param urlIdentifier
     */
    void setUrlIdentifier(URLIdentifier urlIdentifier);

    /**
     *
     * @param cssHandler
     */
    void setCSSHandler(CSSHandler cssHandler);

    /**
     *
     * @param domHandler
     */
    void setDOMHandler(DOMHandler domHandler);

    /**
     *
     * @param jsHandler
     */
    void setJSHandler(JSHandler jsHandler);
    
    /**
     * 
     * @param key
     * @param page
     * @return 
     */
    String getPreProcessResult(String key, WebResource page);
    
    
    /**
     *
     * @return the selected CSS rules list
     */
    Map<String,CascadingStyleSheet> getStyleSheetMap();
    
    /**
     *
     * @return the selected CSS rules list
     */
    Collection<StylesheetContent> getStyleSheetOnError();
    
    /**
     * @deprecated
     * @return the message code
     */
    @Deprecated
    String getMessageCode();
    
    /**
     * @deprecated
     * @param childNodeNames
     *            the names of the childnodes to select recursively
     * @return the current DOMHandler instance
     */
    @Deprecated
    SSPHandler selectChildNodesRecursively(Collection<String> childNodeNames);

    /**
     * @deprecated    
     * @param attributeName
     *            the name of the atribute to select
     * @return the current DOMHandler instance
     */
    @Deprecated
    SSPHandler selectAttributeByName(String attributeName);

    /**
     * @deprecated
     * @param childNodeNames
     *            the names of the childNodes to select
     * @return the current DOMHandler instance
     */
    @Deprecated
    SSPHandler selectChildNodes(Collection<String> childNodeNames);

    /**
     * @deprecated
     * @param childNodeNames
     *            the names of the childnodes to filter
     * @return the current DOMHandler instance
     */
    @Deprecated
    SSPHandler keepNodesWithoutChildNode(Collection<String> childNodeNames);

    /**
     * @deprecated
     * @param childNodeName
     *            the name of the childnode to filter
     * @return the current DOMHandler instance
     */
    @Deprecated
    SSPHandler keepNodesWithoutChildNode(String childNodeName);

    /**
     * @deprecated    
     * @param attributeName
     *            the name of the attribute to filter
     * @param values
     *            the values of the attribute to filter
     * @return the current DOMHandler instance
     */
    @Deprecated
    SSPHandler keepNodesWithAttributeValueEquals(String attributeName,
            Collection<String> values);

    /**
     * @deprecated    
     * @param attributeName
     *            the name of the attribute to filter
     * @return the current DOMHandler instance
     */
    @Deprecated
    SSPHandler keepNodesWithAttributeValueNonEmpty(String attributeName);

    /**
     * @deprecated    
     * @param attributeName
     *            the name of the attribute to be filteterd
     * @param values
     *            the values of the attribute to filter
     * @return the current DOMHandler instance
     */
    @Deprecated
    SSPHandler keepNodesWithAttributeValueStartingWith(String attributeName,
            Collection<String> values);

    /**
     * @deprecated    
     * @param attributeName
     *            the name of the attribute to filter
     * @param value
     *            the value of the attribute to filter
     * @return the current DOMHandler instance
     */
    @Deprecated
    SSPHandler keepNodesWithAttributeValueStartingWith(String attributeName,
            String value);

    /**
     * @deprecated
     * @return the textual values from all currently selected elements
     */
    @Deprecated
    List<String> getTextContentValues();
    
    /**
     * @deprecated    
     * @param length
     *            the length of the text content to check
     * @param defaultFailResult
     *            the default return value if the check processing fails
     * @return the restult of the check processing
     */
    @Deprecated
    TestSolution checkTextContentValueLengthLower(int length,
            TestSolution defaultFailResult);

    /**
     * @deprecated
     * @return the result of the check processing
     */
    @Deprecated
    TestSolution checkTextContentValueNotEmpty();

    /**
     * @deprecated
     * @param attributeName
     *            the name of the attribute to filter
     * @return the current CSSHandler instance
     */
    @Deprecated
    DOMHandler excludeNodesWithAttribute(String attributeName);

    /**
     * @deprecated
     * @param childNodeNames
     *            the names of the childnodes to filter
     * @return the current DOMHandler instance
     */
    @Deprecated
    SSPHandler excludeNodesWithChildNode(ArrayList<String> childNodeNames);

    /**
     * @deprecated
     * @param childNodeName
     *            the name of the childNode to filter
     * @return the current DOMHandler instance
     */
    @Deprecated
    SSPHandler excludeNodesWithChildNode(String childNodeName);

    /**
     * @deprecated
     * @param attributeName
     *            the name of the attribute targeted
     * @return the textual values from all the attributes found
     */
    @Deprecated
    List<String> getAttributeValues(String attributeName);
    
    /**
     * @deprecated
     * @param attributeName
     *            the name of the attribute to check
     * @param blacklist
     *            the list of prevented values
     * @param whitelist
     *            the list of granted values
     * @return the result of the check processing
     */
    @Deprecated
    TestSolution checkTextContentAndAttributeValue(String attributeName,
            Collection<String> blacklist, Collection<String> whitelist);
    
    /**
     * @deprecated
     * @param childNodeName
     *            the name of the childnode to check
     * @return the result of the check processing
     */
    @Deprecated
    TestSolution checkChildNodeExistsRecursively(String childNodeName);

    /**
     * @deprecated
     * @return the result of the check processing
     */
    @Deprecated
    TestSolution checkContentNotEmpty();

    /**
     * @deprecated
     * @param expr
     * @return
     */
    @Deprecated
    TestSolution checkEachWithXpath(String expr);
    
    /**
     * @deprecated
     * @param blacklist
     *            the list of prevented values
     * @param whitelist
     *            the list of granted values
     * @return the result of the check processing
     */
    @Deprecated
    TestSolution checkNodeValue(Collection<String> blacklist,
            Collection<String> whitelist);
    
    /**
     * @deprecated    
     * @param attributeName
     *            the name of the attribute to check
     * @param length
     *            the length of the attribute value to check
     * @param defaultFailResult
     *            the default return value if the check processing fails
     * @return the result of the check processing
     */
    @Deprecated
    TestSolution checkAttributeValueLengthLower(String attributeName,
            int length, TestSolution defaultFailResult);

    /**
     * @deprecated    
     * @param attributeName
     *            the name of the attribute to check
     * @return the result of the check processing
     */
    @Deprecated
    TestSolution checkAttributeValueNotEmpty(String attributeName);

    /**
     * @deprecated    
     * @param attributeName
     *            the name of the attribute to check
     * @return the result of the check processing
     */
    @Deprecated
    TestSolution checkAttributeValueIsEmpty(String attributeName);
    
    /**
     * @deprecated
     * @param attributeName
     *            the name of the attribute to check
     * @return the current DOMHandler
     */
    @Deprecated
    SSPHandler selectDocumentNodesWithAttribute(String attributeName);
}