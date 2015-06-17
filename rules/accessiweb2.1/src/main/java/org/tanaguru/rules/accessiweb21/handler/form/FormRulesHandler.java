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
package org.tanaguru.rules.accessiweb21.handler.form;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.rules.accessiweb21.NodeAndAttributeKeyStore;
import org.tanaguru.service.ProcessRemarkService;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author jkowalczyk
 */
public final class FormRulesHandler {

    /**
     * invalid form filed error message
     */
    public static final String INVALID_FORM_FIELD_MSG_CODE = "InvalidFormField";

    /**
     * id missing error message
     */
    public static final String ID_MISSING_MSG_CODE = "IdMissing";

    /**
     * for missing error message
     */
    public static final String FOR_MISSING_MSG_CODE = "ForMissing";

    /**
     * invalid for attribute error message
     */
    public static final String INVALID_FOR_ATTRIBUTE_MSG_CODE = "InvalidForAttribute";

    /**
     * Missing corresponding id to error message
     */
    public static final String MISSING_CORRESPONDING_ID_MSG_CODE = "MissingCorrespondingId";

    /**
     * id not unique error message
     */
    public static final String ID_NOT_UNIQUE_MSG_CODE = "IdNotUnique";

    /**
     * xpath expression to extract label tags with a for attribute
     */
    private static final String LABEL_WITH_FOR_ATTRIBUTE = "//LABEL[@for]";

    /**
     * xpath expression to extract form fields that are not implicitly associated
     * with labels
     */
    private static final String FORM_FIELD =
            "//INPUT[(@type='text' or @type='password' or @type='checkbox' or " +
            "@type='radio' or @type='file') and not(ancestor::LABEL)] | " +
            "//TEXTAREA[not(ancestor::LABEL)] | " +
            "//SELECT[not(ancestor::LABEL)]";

    /**
     * xpath expression to extract form fields that are not implicitly associated
     * with labels
     */
    private static final String FORM_FIELD_IMPLICITLY_ASSOCIATED_WITH_LABEL =
            "//INPUT[(@type='text' or @type='password' or @type='checkbox' or " +
            "@type='radio' or @type='file') and ancestor::LABEL] | " +
            "//TEXTAREA[ancestor::LABEL] | " +
            "//SELECT[ancestor::LABEL]";

    /**
     * xpath expression to extract form fields that are not implicitly associated
     * with labels
     */
    private static final String LABEL_IMPLICITLY_ASSOCIATED_WITH_FORM_FIELD =
            "//LABEL[INPUT[(@type='text' or @type='password' or @type='checkbox' or " +
            "@type='radio' or @type='file')]] | " +
            "//LABEL[TEXTAREA] | " +
            "//LABEL[SELECT]";

    private int elementCounter = 0;
    public int getElementCounter() {
        return elementCounter;
    }

    public void setElementCounter(int elementCounter) {
        this.elementCounter = elementCounter;
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
    public FormRulesHandler(){

    }

    /**
     * This method extracts the value of the "for" attribute for each label tag.
     *
     * @return
     */
    public List<String> getForAttributeFromLabelTagsList() {
        List<String> forAttributeFromLabelTagsList = new ArrayList<String>();
        for (Node node : sspHandler.beginSelection().
                domXPathSelectNodeSet(FormRulesHandler.LABEL_WITH_FOR_ATTRIBUTE).getSelectedElementList()) {
            forAttributeFromLabelTagsList.add(
                    node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.FOR_ATTR).getNodeValue());
        }
        return forAttributeFromLabelTagsList;
    }

    /**
     * 
     * @param nodeSet
     * @return
     */
    public TestSolution checkIdUnicity(Set<Node> nodeSet) {
        TestSolution testSolution;
        if (nodeSet.isEmpty()) {
            testSolution = TestSolution.NOT_APPLICABLE;
        } else {
            testSolution = TestSolution.PASSED;
            Set<String> duplicateIdSet = getDuplicateIdSet();
            for (Node node : nodeSet) {
                Node idAttributeNode = node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.ID_ATTR);
                if (idAttributeNode != null &&
                        duplicateIdSet.contains(idAttributeNode.getNodeValue())) {
                    testSolution = TestSolution.FAILED;
                    processRemarkService.addSourceCodeRemark(
                                TestSolution.FAILED,
                                node,
                                ID_NOT_UNIQUE_MSG_CODE,
                                idAttributeNode.getNodeValue());
                }
            }
        }
        return testSolution;
    }

    /**
     * This method returns all the node that are explicitly associated with
     * a label (id attribute value of the form field equals to the label
     * attribute of a label element)
     * @return
     */
    public List<Node> getFormFieldExplicitlyAssociatedWithLabelList(){
        List<Node> nodeList = new ArrayList<Node>();
        List<String> forAttributeList= getForAttributeFromLabelTagsList();
        for (Node node : sspHandler.beginSelection().
                domXPathSelectNodeSet(FORM_FIELD).getSelectedElementList()) {
            Node nodeWithIdAttribute =
                    node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.ID_ATTR);
            if (nodeWithIdAttribute != null &&
                    forAttributeList.contains(node.getAttributes().
                    getNamedItem(NodeAndAttributeKeyStore.ID_ATTR).getNodeValue())) {
                nodeList.add(node);
            }
        }
        return nodeList;
    }

    /**
     * This method returns all the node that are explicitly associated with
     * a label (id attribute value of the form field equals to the label
     * attribute of a label element)
     * @return
     */
    public List<Node> getFormFieldNotAssociatedWithLabelList(){
        List<Node> nodeList = new ArrayList<Node>();
        List<String> forAttributeList= getForAttributeFromLabelTagsList();
        for (Node node : getFormFieldNotImplicitlyAssociatedWithLabelList()) {
            Node nodeWithIdAttribute =
                    node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.ID_ATTR);
            if (nodeWithIdAttribute == null || 
                    !forAttributeList.contains(nodeWithIdAttribute.getNodeValue())) {
                nodeList.add(node);
            }
        }
        return nodeList;
    }

    /**
     * This method returns all the form fields not implicitly associated with a
     * label
     * @return
     */
    public List<Node> getFormFieldNotImplicitlyAssociatedWithLabelList(){
        return sspHandler.beginSelection().
                domXPathSelectNodeSet(FORM_FIELD).getSelectedElementList();
    }

    /**
     * This method returns all the form fields not implicitly associated with a
     * label
     * @return
     */
    public List<Node> getFormFieldImplicitlyAssociatedWithLabelList(){
        return sspHandler.beginSelection().
                domXPathSelectNodeSet(FORM_FIELD_IMPLICITLY_ASSOCIATED_WITH_LABEL).getSelectedElementList();
    }

    /**
     * This method returns all the labels that are implicitly associated with a
     * form field
     * @return
     */
    public List<Node> getLabelImplicitlyAssociatedWithFormField(){
        return sspHandler.beginSelection().
                domXPathSelectNodeSet(LABEL_IMPLICITLY_ASSOCIATED_WITH_FORM_FIELD).getSelectedElementList();
    }

    /**
     * 
     * @return
     */
    private Set<String> getDuplicateIdSet() {
        List<String> idList = null;
        Set<String> duplicateIdList = new HashSet<String>();
        sspHandler.beginSelection().domXPathSelectNodeSet("//*").
                keepNodesWithAttribute(NodeAndAttributeKeyStore.ID_ATTR);
        for (Node node : sspHandler.getSelectedElementList()) {
            if (idList == null) {
                idList = new ArrayList<String>();
            }
            if (idList.contains(node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.ID_ATTR).getNodeValue())) {
                duplicateIdList.add(node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.ID_ATTR).getNodeValue());
            }
            idList.add(node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.ID_ATTR).getNodeValue());
        }
        return duplicateIdList;
    }

    /**
     * 
     * @param nodeSet
     * @param attributeName
     * @param errorMessage
     * @return
     */
    public TestSolution checkAttributeExist (
            Set<Node> nodeSet,
            String attributeName,
            String errorMessage) {
        if (nodeSet.isEmpty()) {
            return TestSolution.NOT_APPLICABLE;
        } else {
        TestSolution testSolution = TestSolution.PASSED;
            for (Node node : nodeSet){
                // Does the current node contains a not empty attribute
                if ((node.getAttributes().getNamedItem(attributeName) == null ||
                        node.getAttributes().getNamedItem(attributeName).getNodeValue().isEmpty())) {
                    testSolution = TestSolution.FAILED;
                    processRemarkService.addSourceCodeRemark(
                                TestSolution.FAILED,
                                node,
                                errorMessage,
                                node.getNodeName());
                }
            }
            return testSolution;
        }
    }

    public TestSolution checkLabelForAttribute(){
        List<Node> nodeSet = getLabelImplicitlyAssociatedWithFormField();
        if (nodeSet.isEmpty()) {
            return TestSolution.NOT_APPLICABLE;
        } else {
            TestSolution testSolution = TestSolution.PASSED;
            for (Node node : nodeSet) {
                Node forAttributeNode = node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.FOR_ATTR);
                // Does the current node contains a not empty "for" attribute
                if ((forAttributeNode == null ||
                        forAttributeNode.getNodeValue().isEmpty())) {
                    testSolution = TestSolution.FAILED;
                    processRemarkService.addSourceCodeRemark(
                                TestSolution.FAILED,
                                node,
                                FOR_MISSING_MSG_CODE,
                                node.getNodeName());
                } else if (!checkLabelForAttributeCorrespondToIdAttributeOfFormField(
                        node.getChildNodes(),
                        forAttributeNode.getNodeValue())){
                    testSolution = TestSolution.FAILED;
                    processRemarkService.addSourceCodeRemark(
                                TestSolution.FAILED,
                                node,
                                INVALID_FOR_ATTRIBUTE_MSG_CODE,
                                node.getNodeName());
                }
            }
            return testSolution;
        }
    }

    /**
     * This methods checks whether the value of the for attribute of a label
     * node corresponds to the value of the id attribute of any child form field.
     * 
     * @param childNodes
     * @param forAttributeValue
     * @return
     */
    private boolean checkLabelForAttributeCorrespondToIdAttributeOfFormField(NodeList childNodes, String forAttributeValue) {
        String nodeName;
        Node idAttributeNode;
        for (int i=0 ; i<childNodes.getLength() ; i++) {
            nodeName = childNodes.item(i).getNodeName();
            if (childNodes.item(i).getAttributes() != null && childNodes.item(i).getAttributes().
                    getNamedItem(NodeAndAttributeKeyStore.ID_ATTR) != null) {
                idAttributeNode = childNodes.item(i).getAttributes().
                        getNamedItem(NodeAndAttributeKeyStore.ID_ATTR);
                if ((StringUtils.equalsIgnoreCase(nodeName, NodeAndAttributeKeyStore.SELECT_NODE) ||
                        StringUtils.equalsIgnoreCase(nodeName, NodeAndAttributeKeyStore.TEXTAREA_NODE) ||
                        StringUtils.equalsIgnoreCase(nodeName, NodeAndAttributeKeyStore.INPUT_NODE)) &&
                        idAttributeNode != null &&
                        StringUtils.equalsIgnoreCase(idAttributeNode.getNodeValue(), forAttributeValue)) {
                    return true;
                }
            }
        }
        return false;
    }

}