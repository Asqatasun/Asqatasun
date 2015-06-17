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
package org.tanaguru.rules.accessiweb21.marker;

import java.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.tanaguru.ruleimplementation.RuleHelper;
import org.tanaguru.rules.accessiweb21.NodeAndAttributeKeyStore;
import org.w3c.dom.Node;

/**
 * This abstract class deals with the selection of elements identified by markers.
 * These markers correspond to a specific value of the "id" attribute or of the "class"
 * attribute for a given node.
 * The implementation of the process on the selected elements is done on the
 * concrete class.
 *
 * @author jkowalczyk
 */
public abstract class AbstractPageRuleWithMarkerImplementation
        extends AbstractPageRuleImplementation {

    private static final char SLASH = '/';

    /**
     * The HTML element
     */
    private String htmlElement;

    /**
     * The parameter element Code.
     */
    private String parameterElementCode;

    /**
     * The failed remark message when the test is applied on elements identified
     * by a marker. In this case, rules are decidable.
     */
    private String decidableFailedRemarkMsg;

    /**
     * The passed remark message when the test is applied on unidentified elements.
     * In this case, rules are not decidable.
     */
    private String notDecidablePassedRemarkMsg;

    /**
     * The failed remark message when the test is applied on unidentified elements.
     * In this case, rules are not decidable.
     */
    private String notDecidableFailedRemarkMsg;

    /**
     * The list of markers used to identify elements.
     */
    private List<String> markerList;
    
    /**
     * The list of nodes identified with the markers.
     */
    private List<Node> nodeWithMarkerList = new ArrayList<Node>();
    
    /**
     * The list of nodes not identified with the markers.
     */
    private List<Node> nodeWithoutMarkerList = new ArrayList<Node>();

    public AbstractPageRuleWithMarkerImplementation(
            String htmlElement,
            String parameterElementCode,
            String decidableFailedRemarkMsg,
            String notDecidableFailedRemarkMsg,
            String notDecidablePassedRemarkMsg) {
        super();
        this.htmlElement = htmlElement;
        this.parameterElementCode = parameterElementCode;
        this.decidableFailedRemarkMsg = decidableFailedRemarkMsg;
        this.notDecidableFailedRemarkMsg = notDecidableFailedRemarkMsg;
        this.notDecidablePassedRemarkMsg = notDecidablePassedRemarkMsg;
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();
        
        markerList = extractMarkerListFromParameter(sspHandler);
        
        processRemarkList.addAll(testNodeSet(sspHandler, resultSet));
        
        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                RuleHelper.synthesizeTestSolutionCollection(resultSet),
                processRemarkList);
        return processResult;
    }

    public abstract boolean doProcess(Node node);

    /**
     * 
     * @param sspHandler
     * @param resultSet
     * @return
     *      the list of process remarks
     */
    protected Collection<ProcessRemark>  testNodeSet(
            SSPHandler sspHandler,
            Set<TestSolution> resultSet) {
        //selection of elements
        executeXpathRequest(sspHandler);
        // test the nodes that are characterized (belongs to nodeWithMarkerList)
        test(nodeWithMarkerList, sspHandler, resultSet, true);
        // test the nodes that are not characterized (belongs to nodeWithoutMarkerList)
        test(nodeWithoutMarkerList, sspHandler, resultSet, false);
        return sspHandler.getRemarkList();
    }

    private void test(List<Node> nodeList, SSPHandler sspHandler, Set<TestSolution> resultSet, boolean isDecidable) {
        if (!nodeList.isEmpty()) {
            // for each select node, we apply the process, and create the appropriate
            // result and process remarks.
            for (Node node : nodeList) {
                TestSolution testSolution;
                
                if (isDecidable) {
                    // if the test is set as decidable, we initialize the solution
                    // with PASSED value. 
                    testSolution = TestSolution.PASSED;
                } else {
                    // if the test is set as not decidable, we initialize the solution
                    // with NEED_MORE_INFO value. 
                    testSolution = TestSolution.NEED_MORE_INFO;
                }
                if (doProcess(node)) {
                    if (!isDecidable) {
                        addSourceCodeRemark(sspHandler, testSolution, node, notDecidablePassedRemarkMsg);
                    }
                } else {
                    if (isDecidable) {
                        testSolution = TestSolution.FAILED;
                        addSourceCodeRemark(sspHandler, testSolution, node, decidableFailedRemarkMsg);
                    } else {
                        addSourceCodeRemark(sspHandler, testSolution, node, notDecidableFailedRemarkMsg);
                    }
                }
                resultSet.add(testSolution);
            }
        }
    }
    
    /**
     * 
     * @param sspHandler
     * @param testSolution
     * @param node
     * @param remarkMessage
     */
    protected void addSourceCodeRemark(
            SSPHandler sspHandler,
            TestSolution testSolution,
            Node node,
            String remarkMessage) {
        sspHandler.getProcessRemarkService().addSourceCodeRemark(
                            testSolution,
                            node,
                            remarkMessage,
                            htmlElement);
    }

    /**
     * Retrieves the parameter value and return the marker list
     *
     * @param sspHandler
     */
    protected List<String> extractMarkerListFromParameter(SSPHandler sspHandler) {
        for (Parameter parameter : sspHandler.getSSP().getAudit().getParameterSet()){
            if (parameter.getParameterElement().getParameterElementCode().equalsIgnoreCase(parameterElementCode)) {
                return Arrays.asList(parameter.getValue().split(";"));
            }
        }
        return null;
    }

    /**
     * Retrieves all the nodes for a given html element from a xpath request.
     * Once retrieved, these nodes are sorted, depending on the marker values 
     * set by the user.
     * 
     * @param sspHandler
     * @return
     */
    protected void executeXpathRequest(SSPHandler sspHandler){
        StringBuilder strb = new StringBuilder();
        strb.append(SLASH);
        strb.append(SLASH);
        strb.append(htmlElement);
        List<Node> nodeList = sspHandler.beginSelection().domXPathSelectNodeSet(strb.toString()).getSelectedElementList();
        if (!CollectionUtils.isEmpty(markerList)) {
            sortNodes(nodeList);
        } else {
            nodeWithoutMarkerList.addAll(nodeList);
        }
    }

    /**
     * To sort nodes, we extract for each of them the value of the "id" attribute
     * and the "class" attribute. If one of these two values belongs to the 
     * marker value list set by the user, we consider that the element is 
     * characterized and we add it to the "nodeWithMarkerList". If not, we add it 
     * to the "nodeWithoutMarkerList"
     * 
     * @param nodeList 
     */
    private void sortNodes(List<Node> nodeList) {
        for (Node node :nodeList) {
            Node idNode = node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.ID_ATTR);
            Node classNode = node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.CLASS_ATTR);
            // if the node doesn't contain an "id" or a "class" attribute, 
            // it cannot be characterized.
            if (idNode == null && classNode == null) {
                nodeWithoutMarkerList.add(node);
            } else {
                // if the node contains an "id" or a "class" attribute, we check 
                // whether its value is found in the marker values set by the 
                // user.
                if (!checkAttributeBelongsToMarkerList(idNode) && 
                        !checkAttributeBelongsToMarkerList(classNode)) {
                    nodeWithoutMarkerList.add(node);
                } else {
                    nodeWithMarkerList.add(node);
                }
            }
        }
    }
    
    /**
     * To check an attribute node, we tokenize the value of the attribute (to deal 
     * with the multiple values definition) and check whether each value belongs
     * to the marker list set by the user.
     * 
     * @param classNode
     * @return 
     */
    private boolean checkAttributeBelongsToMarkerList (Node attributeNode) {
        boolean isPresent = false;
        if (attributeNode != null) {
            String[] attributeValue = attributeNode.getNodeValue().split(" ");
            for (int i=0 ; i<attributeValue.length ; i++) {
                if (markerList.contains(attributeValue[i])) {
                    isPresent = true;
                }
            }
        }
        return isPresent;
    }
    
}