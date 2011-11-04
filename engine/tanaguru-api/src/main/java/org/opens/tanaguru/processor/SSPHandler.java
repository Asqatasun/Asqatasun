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
package org.opens.tanaguru.processor;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.opens.tanaguru.contentadapter.util.URLIdentifier;

import org.w3c.dom.Node;

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.service.NomenclatureLoaderService;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 * 
 * @author jkowalczyk
 */
public interface SSPHandler {

    /**
     *
     * @return the current SSPHandler with an empty selection
     */
    SSPHandler beginSelection();

    /**
     *
     * @param attributeName
     *            the name of the attribute to check
     * @return the result of the check processing
     */
    TestSolution checkAttributeExists(String attributeName);

    /**
     *
     * @param attributeName
     *            the name of the attribute to check
     * @param regex
     *            the regular expression to check on the value of the attribute
     * @return the result of the check processing
     */
    TestSolution checkAttributeValueExpression(String attributeName,
            String regex);

    /**
     *
     * @param attributeName
     *            the name of the attribute to check
     * @param length
     *            the length of the attribute value to check
     * @param defaultFailResult
     *            the default return value if the check processing fails
     * @return the result of the check processing
     */
    TestSolution checkAttributeValueLengthLower(String attributeName,
            int length, TestSolution defaultFailResult);

    /**
     *
     * @param attributeName
     *            the name of the attribute to check
     * @return the result of the check processing
     */
    TestSolution checkAttributeValueNotEmpty(String attributeName);

    /**
     *
     * @param attributeName
     *            the name of the attribute to check
     * @return the result of the check processing
     */
    TestSolution checkAttributeValueIsEmpty(String attributeName);

    /**
     *
     * @param childNodeName
     *            the name of the childNode to check
     * @return the result of the check processing
     */
    TestSolution checkChildNodeExists(String childNodeName);

    /**
     *
     * @param childNodeName
     *            the name of the childnode to check
     * @return the result of the check processing
     */
    TestSolution checkChildNodeExistsRecursively(String childNodeName);

    /**
     *
     * @return the result of the check processing
     */
    TestSolution checkContentNotEmpty();

    /**
     *
     * @param expr
     * @return
     */
    TestSolution checkEachWithXpath(String expr);

    /**
     *
     * @param blacklist
     *            the list of prevented values
     * @param whitelist
     *            the list of granted values
     * @return the result of the check processing
     */
    TestSolution checkNodeValue(Collection<String> blacklist,
            Collection<String> whitelist);

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
     * @param blackList
     *            the black listed units
     * @return result of the checking
     */
    TestSolution checkRelativeUnitExists(Collection<Integer> blackList);

    /**
     *
     * @param attributeName
     *            the name of the attribute to check
     * @param blacklist
     *            the list of prevented values
     * @param whitelist
     *            the list of granted values
     * @return the result of the check processing
     */
    TestSolution checkTextContentAndAttributeValue(String attributeName,
            Collection<String> blacklist, Collection<String> whitelist);

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
     * @param length
     *            the length of the text content to check
     * @param defaultFailResult
     *            the default return value if the check processing fails
     * @return the restult of the check processing
     */
    TestSolution checkTextContentValueLengthLower(int length,
            TestSolution defaultFailResult);

    /**
     *
     * @return the result of the check processing
     */
    TestSolution checkTextContentValueNotEmpty();

    /**
     *
     * @param attributeName
     *            the name of the attribute to filter
     * @return the current CSSHandler instance
     */
    DOMHandler excludeNodesWithAttribute(String attributeName);

    /**
     *
     * @param childNodeNames
     *            the names of the childnodes to filter
     * @return the current DOMHandler instance
     */
    SSPHandler excludeNodesWithChildNode(ArrayList<String> childNodeNames);

    /**
     *
     * @param childNodeName
     *            the name of the childNode to filter
     * @return the current DOMHandler instance
     */
    SSPHandler excludeNodesWithChildNode(String childNodeName);

    /**
     *
     * @param attributeName
     *            the name of the attribute targeted
     * @return the textual values from all the attributes found
     */
    List<String> getAttributeValues(String attributeName);

    /**
     *
     * @return the page of the ssp used
     */
    public WebResource getPage();

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
     * @return the SSP
     */
    SSP getSSP();

    /**
     *
     * @return the textual values from all currently selected elements
     */
    List<String> getTextContentValues();

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
     * @param attributeName
     *            the name of the attribute to filter
     * @param values
     *            the values of the attribute to filter
     * @return the current DOMHandler instance
     */
    SSPHandler keepNodesWithAttributeValueEquals(String attributeName,
            Collection<String> values);

    /**
     *
     * @param attributeName
     *            the name of the attribute to filter
     * @return the current DOMHandler instance
     */
    SSPHandler keepNodesWithAttributeValueNonEmpty(String attributeName);

    /**
     *
     * @param attributeName
     *            the name of the attribute to be filteterd
     * @param values
     *            the values of the attribute to filter
     * @return the current DOMHandler instance
     */
    SSPHandler keepNodesWithAttributeValueStartingWith(String attributeName,
            Collection<String> values);

    /**
     *
     * @param attributeName
     *            the name of the attribute to filter
     * @param value
     *            the value of the attribute to filter
     * @return the current DOMHandler instance
     */
    SSPHandler keepNodesWithAttributeValueStartingWith(String attributeName,
            String value);

    /**
     *
     * @param childNodeName
     *            the name of the attribute to filter
     * @return the current DOMHandler instance
     */
    SSPHandler keepNodesWithChildNode(String childNodeName);

    /**
     *
     * @param childNodeNames
     *            the names of the childnodes to filter
     * @return the current DOMHandler instance
     */
    SSPHandler keepNodesWithoutChildNode(Collection<String> childNodeNames);

    /**
     *
     * @param childNodeName
     *            the name of the childnode to filter
     * @return the current DOMHandler instance
     */
    SSPHandler keepNodesWithoutChildNode(String childNodeName);

    /**
     *
     * @return the current SSPHandler with all CSS rules
     */
    SSPHandler selectAllRules();

    /**
     *
     * @return the current SSPHandler with a set of CSS rules
     */
    SSPHandler keepRulesWithMedia(Collection<String> mediaNames);

    /**
     *
     * @param attributeName
     *            the name of the atribute to select
     * @return the current DOMHandler instance
     */
    SSPHandler selectAttributeByName(String attributeName);

    /**
     *
     * @param childNodeNames
     *            the names of the childNodes to select
     * @return the current DOMHandler instance
     */
    SSPHandler selectChildNodes(Collection<String> childNodeNames);

    /**
     *
     * @param childNodeName
     *            the name of the childNode to select
     * @return the current DOMHandler instance
     */
    SSPHandler selectChildNodes(String childNodeName);

    /**
     *
     * @param childNodeNames
     *            the names of the childnodes to select recursively
     * @return the current DOMHandler instance
     */
    SSPHandler selectChildNodesRecursively(Collection<String> childNodeNames);

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
     *
     * @param attributeName
     *            the name of the attribute to check
     * @return the current DOMHandler
     */
    SSPHandler selectDocumentNodesWithAttribute(String attributeName);

    /**
     * http://www.ibm.com/developerworks/library/x-javaxpathapi.html
     *
     * @param expr
     * @return
     */
    SSPHandler domXPathSelectNodeSet(String expr);

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
    public BufferedImage getImageFromURL(String URL);

    /**
     * This method checks whether an attribute only contains non alphanumeric
     * characters
     * @param attribute
     * @param workingElement
     * @param testSolution
     * @param remarkMessage
     * @return
     */
    public TestSolution checkAttributeOnlyContainsNonAlphanumericCharacters(
            Node attribute,
            Node workingElement,
            TestSolution testSolution,
            String remarkMessage);

    /**
     * This method checks whether an attribute only contains non alphanumeric
     * characters
     * @param attributeContent
     * @param workingElement
     * @param currentTestSolution
     * @param remarkMessage
     * @return
     */
    public TestSolution checkAttributeOnlyContainsNonAlphanumericCharacters(
            String attributeContent,
            Node workingElement,
            TestSolution testSolution,
            String remarkMessage);

    /**
     * This method return the number of selected elements
     * @return
     */
    public int getSelectedElementNumber();

    /**
     * @return the number of css selectors
     */
    public int getCssSelectorNumber();

    /**
     * 
     * @param processRemarkService
     */
    public void setProcessRemarkService(ProcessRemarkService processRemarkService);

    /**
     * 
     * @return the processRemarkService
     */
    public ProcessRemarkService getProcessRemarkService();

    /**
     *
     * @return the message code
     */
    public String getMessageCode();

    /**
     *
     * @param messageCode
     */
    public void setMessageCode(String messageCode);

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

}