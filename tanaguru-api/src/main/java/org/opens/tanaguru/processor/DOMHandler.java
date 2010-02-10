package org.opens.tanaguru.processor;

import org.opens.tanaguru.entity.subject.WebResource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.w3c.dom.Node;

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;

/**
 * 
 * 
 * 
 * @author ADEX
 * @version 1.0.0
 */
public interface DOMHandler {

    /**
     *
     * @return the current SSPHandler instance
     */
    DOMHandler beginSelection();

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
     * @return the current DOMHandler instance
     */
    DOMHandler excludeNodesWithAttribute(String attributeName);

    /**
     *
     * @param childNodeNames
     *            the names of the childnodes to filter
     * @return the current DOMHandler instance
     */
    DOMHandler excludeNodesWithChildNode(ArrayList<String> childNodeNames);

    /**
     *
     * @param childNodeName
     *            the name of the childNode to filter
     * @return the current DOMHandler instance
     */
    DOMHandler excludeNodesWithChildNode(String childNodeName);

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
     * @return the remarks
     */
    List<ProcessRemark> getRemarkList();

    /**
     *
     * @return the elements currently selected
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
    DOMHandler keepNodesWithAttribute(String attributeName);

    /**
     *
     * @param attributeName
     *            the name of the attribute to filter
     * @param values
     *            the values of the attribute to filter
     * @return the current DOMHandler instance
     */
    DOMHandler keepNodesWithAttributeValueEquals(String attributeName,
            Collection<String> values);

    /**
     *
     * @param attributeName
     *            the name of the attribute to filter
     * @return the current DOMHandler instance
     */
    DOMHandler keepNodesWithAttributeValueNonEmpty(String attributeName);

    /**
     *
     * @param attributeName
     *            the name of the attribute to be filteterd
     * @param values
     *            the values of the attribute to filter
     * @return the current DOMHandler instance
     */
    DOMHandler keepNodesWithAttributeValueStartingWith(String attributeName,
            Collection<String> values);

    /**
     *
     * @param attributeName
     *            the name of the attribute to filter
     * @param value
     *            the value of the attribute to filter
     * @return the current DOMHandler instance
     */
    DOMHandler keepNodesWithAttributeValueStartingWith(String attributeName,
            String value);

    /**
     *
     * @param childNodeName
     *            the name of the attribute to filter
     * @return the current DOMHandler instance
     */
    DOMHandler keepNodesWithChildNode(String childNodeName);

    /**
     *
     * @param childNodeNames
     *            the names of the childnodes to filter
     * @return the current DOMHandler instance
     */
    DOMHandler keepNodesWithoutChildNode(Collection<String> childNodeNames);

    /**
     *
     * @param childNodeName
     *            the name of the childnode to filter
     * @return the current DOMHandler instance
     */
    DOMHandler keepNodesWithoutChildNode(String childNodeName);

    /**
     *
     * @param attributeName
     *            the name of the atribute to select
     * @return the current DOMHandler instance
     */
    DOMHandler selectAttributeByName(String attributeName);

    /**
     *
     * @param childNodeNames
     *            the names of the childNodes to select
     * @return the current DOMHandler instance
     */
    DOMHandler selectChildNodes(Collection<String> childNodeNames);

    /**
     *
     * @param childNodeName
     *            the name of the childNode to select
     * @return the current DOMHandler instance
     */
    DOMHandler selectChildNodes(String childNodeName);

    /**
     *
     * @param childNodeNames
     *            the names of the childnodes to select recursively
     * @return the current DOMHandler instance
     */
    DOMHandler selectChildNodesRecursively(Collection<String> childNodeNames);

    /**
     *
     * @param childNodeName
     *            the name of the childnode to select recursively
     * @return the current DOMHandler instance
     */
    DOMHandler selectChildNodesRecursively(String childNodeName);

    /**
     *
     * @param nodeNames
     *            the names of the nodes to select
     * @return the current DOMHandler instance
     */
    DOMHandler selectDocumentNodes(Collection<String> nodeNames);

    /**
     *
     * @param nodeName
     *            the name of the nodeto select in all the document
     * @return the current DOMHandler instance
     */
    DOMHandler selectDocumentNodes(String nodeName);

    /**
     *
     * @param attributeName
     *            the name of the attribute to check
     * @return the current DOMHandler
     */
    DOMHandler selectDocumentNodesWithAttribute(String attributeName);

    /**
     * http://www.ibm.com/developerworks/library/x-javaxpathapi.html
     *
     * @param expr
     * @return
     */
    DOMHandler xPathSelectNodeSet(String expr);

    /**
     *
     * @param processRemarkFactory
     *            the process remark factory to set
     */
    void setProcessRemarkFactory(ProcessRemarkFactory processRemarkFactory);

    /**
     *
     * @param selectedElementList
     *            the selected element list to set
     */
    void setSelectedElementList(List<Node> selectedElementList);

    /**
     *
     * @param sourceCodeRemarkFactory
     *            the source code remark factory to set
     */
    void setSourceCodeRemarkFactory(
            SourceCodeRemarkFactory sourceCodeRemarkFactory);

    /**
     *
     * @param ssp
     *            the SSP to set
     */
    void setSSP(SSP ssp);
}
