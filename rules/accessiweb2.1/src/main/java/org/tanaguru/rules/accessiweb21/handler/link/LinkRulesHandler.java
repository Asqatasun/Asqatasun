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
package org.tanaguru.rules.accessiweb21.handler.link;

import java.util.*;
import org.tanaguru.entity.audit.EvidenceElement;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.RuleHelper;
import org.tanaguru.rules.accessiweb21.EvidenceKeyStore;
import org.tanaguru.rules.accessiweb21.NodeAndAttributeKeyStore;
import org.tanaguru.service.NomenclatureLoaderService;
import org.tanaguru.service.ProcessRemarkService;
import org.w3c.dom.Node;

/**
 *
 * @author jkowalczyk
 */
public final class LinkRulesHandler {

    public static final String LINK_TEXT_BL_NOM_NAME="LinkTextBlacklist";
    public static final String ATTRIBUTE_STR="Attribute";

    public static final String IDENTICAL_LINK_WITH_DIFFERENT_TARGET=
            "IdenticalLinkWithDifferentTarget";
    public static final String SUSPECTED_IDENTICAL_LINK_WITH_DIFFERENT_TARGET=
            "SuspectedIdenticalLinkWithDifferentTarget";
    public static final String SUSPECTED_NOT_PERTINENT_STR="SuspectedNotPertinent";
    public static final String SUSPECTED_PERTINENT_STR="SuspectedPertinent";
    public static final String NOT_PERTINENT_STR="NotPertinent";
    public static final String UNEXPLICIT_LINK_OUT_OF_CONTEXT=
            "UnexplicitLinkOutOfContext";
    public static final String UNEXPLICIT_CONTEXT="UnexplicitContext";
    public static final String SUSPECTED_UNEXPLICIT_CONTEXT=
            "SuspectedUnexplicitContext";

    public static final String A_SELECTOR = "//A[";
    public static final String AREA_SELECTOR = "//AREA[";
    public static final String END_BRACKET = "]";

    /*
     * xpath expression to extract a link with a context
     */
    public static final String LINK_WITH_CONTEXT =
        "(" +
        "ancestor::LI or " +
        "ancestor::P or " +
        "ancestor::TD or " +
        "ancestor::*[contains(name(),'H') and string-length(name())=2] or " +
        "preceding::*[contains(name(),'H') and string-length(name())=2])";

    /*
     * xpath expression to extract a link without a context
     */
    public static final String LINK_WITHOUT_CONTEXT =
        "(not(ancestor::P) and " +
        "not(ancestor::LI) and " +
        "not(ancestor::TD) and " +
        "not(ancestor::*[contains(name(),'H') and string-length(name())=2]) and " +
        "not(preceding::*[contains(name(),'H') and string-length(name())=2]))";

    /*
     * xpath expression to extract simple links
     */
    public static final String SIMPLE_LINK =
        "@href and normalize-space() and not(descendant::OBJECT) and " +
        "not(descendant::IMG) ";

    /*
     * xpath expression to extract image links with <img> nodes
     */
    public static final String IMAGE_LINK_IMG_NODE =
        "descendant::IMG[normalize-space(@alt)] and @href and count(descendant::*)=1 and not(normalize-space())";

    /*
     * xpath expression to extract image links with <object> nodes
     */
    public static final String IMAGE_LINK_OBJECT_NODE =
        "descendant::OBJECT[normalize-space(string(.))] and @href";

    /*
     * xpath expression to extract image links with <object> nodes
     */
    public static final String CLICKABLE_AREA =
        "@href and normalize-space(@alt)";

    /*
     * xpath expression to extract composite links with <img> nodes
     */
    public static final String COMPOSITE_LINK_IMG_NODE =
        "descendant::IMG[normalize-space(@alt)] and @href";

    /*
     * xpath expression to extract composite links with <object> nodes
     */
    public static final String COMPOSITE_LINK_OBJECT_NODE =
        "descendant::OBJECT[normalize-space(string(.))] and @href";

    /**
     * The unique instance of LinkRulesHandler (singleton pattern)
     */

    private int elementCounter = 0;
    public int getElementCounter() {
        return elementCounter;
    }

    public void setElementCounter(int elementCounter) {
        this.elementCounter = elementCounter;
    }

    /**
     *
     */
    private Collection<String> linkTextBlacklist;
    public Collection<String> getLinkTextBlacklist() {
        if (linkTextBlacklist == null) {
            linkTextBlacklist = nomenclatureLoaderService.
                loadByCode(LINK_TEXT_BL_NOM_NAME).getValueList();
        }
        return linkTextBlacklist;
    }

    public void setLinkTextBlacklist(Collection<String> linkTextBlacklist) {
        this.linkTextBlacklist = linkTextBlacklist;
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
     * The NomenclatureLoaderService
     */
    private NomenclatureLoaderService nomenclatureLoaderService;
    public NomenclatureLoaderService getNomenclatureLoaderService() {
        return nomenclatureLoaderService;
    }

    public void setNomenclatureLoaderService(
            NomenclatureLoaderService nomenclatureLoaderService) {
        this.nomenclatureLoaderService = nomenclatureLoaderService;
    }

    /**
     * Default private constructor
     */
    public LinkRulesHandler(){}

    /**
     *
     * @param xPathExpression
     * @param expectedTestSolution
     * @param remarkMessage
     * @param processRemarkList
     * @return
     */
    public TestSolution checkContextPertinence(
            String xPathExpression,
            TestSolution expectedTestSolution,
            String remarkMessage,
            List<ProcessRemark> processRemarkList) {

        if (xPathExpression != null) {
            sspHandler.beginSelection().domXPathSelectNodeSet(xPathExpression);
        }
        elementCounter += sspHandler.getSelectedElementNumber();
        // We first check whether the content of the tested node is not empty
        TestSolution testSolution = sspHandler.checkNodeValue(
                 getLinkTextBlacklist(),
                 null,
                 expectedTestSolution,
                 remarkMessage);

        if (!testSolution.equals(expectedTestSolution) || sspHandler.getRemarkList().isEmpty() ){
            Set<TestSolution> resultSet = new HashSet<TestSolution>();
            for (Node workingElement : sspHandler.getSelectedElementList()) {
                TestSolution result = TestSolution.NEED_MORE_INFO;
                String nodeContent = buildTextContentFromNodeElements(workingElement);
                if (checkNodeValue(nodeContent, linkTextBlacklist, expectedTestSolution, remarkMessage, workingElement).equals(expectedTestSolution)) {
                    result = expectedTestSolution;
                }

                if (sspHandler.checkAttributeOnlyContainsNonAlphanumericCharacters(
                        nodeContent, workingElement, expectedTestSolution, remarkMessage).
                            equals(expectedTestSolution)) {
                    result = expectedTestSolution;
                }
                resultSet.add(result);
            }
            testSolution = RuleHelper.synthesizeTestSolutionCollection(resultSet);
        }
        processRemarkList.addAll(sspHandler.getRemarkList());
        return testSolution;
    }

    /**
     *
     * @param attributeName
     * @param isEqualContentAuthorized
     * @return
     */
    public TestSolution checkAttributePertinence(
            String attributeName,
            boolean isEqualContentAuthorized) {

        // We first check whether the content of the tested node is not empty
        TestSolution testSolution = checkAttributeValueNotEmpty(attributeName);
        elementCounter += sspHandler.getSelectedElementNumber();
        // If the content of the tested attribute is not empty
        if (testSolution != TestSolution.FAILED){
            Set<TestSolution> resultSet = new HashSet<TestSolution>();
            for (Node workingElement : sspHandler.getSelectedElementList()) {
                TestSolution result = TestSolution.NEED_MORE_INFO;
                Node testedAttribute = workingElement.getAttributes().
                        getNamedItem(attributeName);
                // to avoid the selection of non wished elements due to namespace
                // awareness of xpath, we have to test if the element contains
                // effectively the attribute
                if (testedAttribute != null) {
                    // We check whether the content of the tested attribute is
                    // different from each element of the blacklist passed as argument
                    if (getLinkTextBlacklist() != null) {
                        for (String text : getLinkTextBlacklist()) {
                            if (testedAttribute.getNodeValue() != null &&
                                    testedAttribute.getNodeValue().equalsIgnoreCase(text)) {
                                result = TestSolution.FAILED;
                                addSourceCodeRemark(
                                        result,
                                        workingElement,
                                        NOT_PERTINENT_STR+testedAttribute.getNodeName()+ATTRIBUTE_STR,
                                        testedAttribute.getNodeValue().trim().toLowerCase(),
                                        buildTextContentFromNodeElements(workingElement));
                                break;
                            }
                        }
                    }
                    if (result != TestSolution.FAILED) {
                        if (sspHandler.checkAttributeOnlyContainsNonAlphanumericCharacters(
                                testedAttribute,
                                workingElement,
                                TestSolution.FAILED,
                                NOT_PERTINENT_STR+testedAttribute.getNodeName()+ATTRIBUTE_STR).
                                    equals(TestSolution.FAILED)) {
                            result = TestSolution.FAILED;
                        }

                        if (compareAttributeContentAndNodeContent(
                                testedAttribute,workingElement, isEqualContentAuthorized).
                                equals(TestSolution.FAILED)) {
                            result = TestSolution.FAILED;
                        }
                    }
                    resultSet.add(result);
                }
            }
            testSolution = RuleHelper.synthesizeTestSolutionCollection(resultSet);
        }
        return testSolution;
    }

    /**
     * We check whether the content of the tested attribute is equal, quite
     * equal or different from the text content of the current node to determine
     * the pertinence of the attribute.
     * @param testedAttribute
     * @param workingElement
     * @param isEqualContentAuthorized
     * @return
     */
    private TestSolution compareAttributeContentAndNodeContent(
            Node testedAttribute,
            Node workingElement,
            boolean isEqualContentAuthorized) {

        TestSolution result = TestSolution.NEED_MORE_INFO;
        String nodeContent = buildTextContentFromNodeElements(workingElement);
        String attributeContent = testedAttribute.getNodeValue().trim().toLowerCase();
        if (attributeContent.
                equalsIgnoreCase(nodeContent)) {
            if (isEqualContentAuthorized) {
                addSourceCodeRemark(
                    result,
                    workingElement,
                    SUSPECTED_PERTINENT_STR+testedAttribute.getNodeName()+ATTRIBUTE_STR,
                    attributeContent,
                    nodeContent);
            } else {
                result = TestSolution.FAILED;
                addSourceCodeRemark(
                    result,
                    workingElement,
                    NOT_PERTINENT_STR+testedAttribute.getNodeName()+ATTRIBUTE_STR,
                    attributeContent,
                    nodeContent);
            }
        } else if (attributeContent.
                contains(nodeContent)) {
            addSourceCodeRemark(
                    result,
                    workingElement,
                    SUSPECTED_PERTINENT_STR+testedAttribute.getNodeName()+ATTRIBUTE_STR,
                    attributeContent,
                    nodeContent);
        } else {
            addSourceCodeRemark(
                    result,
                    workingElement,
                    SUSPECTED_NOT_PERTINENT_STR+testedAttribute.getNodeName()+ATTRIBUTE_STR,
                    attributeContent,
                    nodeContent);
        }
        return result;
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
            String linkTitleAttrValue,
            String linkTextValue) {

        List<EvidenceElement> evidenceElementList =
                new ArrayList<EvidenceElement>();

        evidenceElementList.add(processRemarkService.getEvidenceElement(
                processRemarkService.getEvidenceDataService().findByCode(EvidenceKeyStore.LINK_TITLE_ATTR_EE).getCode(),
                linkTitleAttrValue));

        evidenceElementList.add(processRemarkService.getEvidenceElement(
                processRemarkService.getEvidenceDataService().findByCode(EvidenceKeyStore.LINK_TEXT_EE).getCode(),
                linkTextValue));

        processRemarkService.addSourceCodeRemark(
                testSolution,
                node,
                message,
                evidenceElementList);
    }

    /**
     *
     * @param node
     * @return
     */
    public String buildTextContentFromNodeElements(Node node) {
        StringBuilder strBuffer = new StringBuilder();
        for (int i=0;i<node.getChildNodes().getLength();i++){
            if (node.getChildNodes().item(i).getNodeName().equalsIgnoreCase(NodeAndAttributeKeyStore.TEXT_NODE) &&
                    !node.getChildNodes().item(i).getNodeValue().trim().isEmpty()) {
                strBuffer.append(node.getChildNodes().item(i).getNodeValue().trim());
                strBuffer.append(NodeAndAttributeKeyStore.SPACE_CHAR);
            } else if (node.getChildNodes().item(i).getNodeName().equalsIgnoreCase(NodeAndAttributeKeyStore.IMG_NODE) &&
                    node.getChildNodes().item(i).getAttributes().getNamedItem(NodeAndAttributeKeyStore.ALT_ATTR) != null &&
                    !node.getChildNodes().item(i).getAttributes().getNamedItem(NodeAndAttributeKeyStore.ALT_ATTR).getNodeValue().trim().isEmpty()) {
                strBuffer.append(node.getChildNodes().item(i).getAttributes().getNamedItem(NodeAndAttributeKeyStore.ALT_ATTR).getNodeValue().trim());
                strBuffer.append(NodeAndAttributeKeyStore.SPACE_CHAR);
            } else if (node.getChildNodes().item(i).getNodeName().equalsIgnoreCase(NodeAndAttributeKeyStore.OBJECT_NODE) &&
                    !node.getChildNodes().item(i).getTextContent().trim().isEmpty()) {
                strBuffer.append(node.getChildNodes().item(i).getTextContent().trim());
                strBuffer.append(NodeAndAttributeKeyStore.SPACE_CHAR);
            } else if (node.getChildNodes().item(i).getNodeName().equalsIgnoreCase(NodeAndAttributeKeyStore.AREA_NODE) &&
                    node.getChildNodes().item(i).getAttributes().getNamedItem(NodeAndAttributeKeyStore.ALT_ATTR) != null &&
                    !node.getChildNodes().item(i).getAttributes().getNamedItem(NodeAndAttributeKeyStore.ALT_ATTR).getNodeValue().trim().isEmpty()) {
                strBuffer.append(node.getChildNodes().item(i).getAttributes().getNamedItem(NodeAndAttributeKeyStore.ALT_ATTR).getNodeValue().trim());
                strBuffer.append(NodeAndAttributeKeyStore.SPACE_CHAR);
            }  else if (node.getChildNodes().item(i).getNodeName().equalsIgnoreCase(NodeAndAttributeKeyStore.STRONG_NODE) &&
                    !node.getChildNodes().item(i).getTextContent().trim().isEmpty()) {
                strBuffer.append(node.getChildNodes().item(i).getTextContent().trim());
                strBuffer.append(NodeAndAttributeKeyStore.SPACE_CHAR);
            }  else if (node.getChildNodes().item(i).getNodeName().equalsIgnoreCase(NodeAndAttributeKeyStore.SPAN_NODE) &&
                    !node.getChildNodes().item(i).getTextContent().trim().isEmpty()) {
                strBuffer.append(node.getChildNodes().item(i).getTextContent().trim());
                strBuffer.append(NodeAndAttributeKeyStore.SPACE_CHAR);
            }
        }
        for (int i=0;i<node.getAttributes().getLength();i++){
            if (node.getAttributes().item(i).getNodeName().equalsIgnoreCase(NodeAndAttributeKeyStore.ALT_ATTR) &&
                    !node.getAttributes().item(i).getNodeValue().trim().isEmpty()) {
                strBuffer.append(node.getAttributes().item(i).getNodeValue().trim());
                strBuffer.append(NodeAndAttributeKeyStore.SPACE_CHAR);
            }
        }
        return strBuffer.toString().trim().toLowerCase();
    }

    /**
     * It is impossible to extract through xpath the &lt;a&gt; nodes that only
     * contains object nodes with text. This method removes the &lt;a&gt; nodes
     * that contain text (in this case, these links are regarded as composite)
     * or that have child nodes that are not an object tag filled-in with text
     * @param nodeList
     * @return
     */
    public List<Node> removeTagsWithNotEmptyContent(List<Node> nodeList) {
        List<Node> myNodeList = new ArrayList<Node>();
        for (Node node : nodeList) {
            boolean hasToBeRemoved = false;
            for (int i =0 ; i<node.getChildNodes().getLength();i++) {
                if (doesNodeContainsOtherTagThanObjectAndText
                        (node.getChildNodes().item(i))) {
                    hasToBeRemoved = true;
                    break;
                }
                if (node.getChildNodes().item(i).getNodeName().equalsIgnoreCase(NodeAndAttributeKeyStore.TEXT_NODE) &&
                    !node.getChildNodes().item(i).getTextContent().trim().isEmpty()) {
                    hasToBeRemoved = true;
                    break;
                }
            }
            if (!hasToBeRemoved){
                myNodeList.add(node);
            }
        }
        return myNodeList;
    }

/**
     * This methods checks whether the current node contains other tags than
     * object or text (and comment).
     * @param node
     * @return
     */
    private boolean doesNodeContainsOtherTagThanObjectAndText(Node node){
        boolean result = false;
        // if the current node is not an object, a text or a comment
        if (!node.getNodeName().equalsIgnoreCase(NodeAndAttributeKeyStore.TEXT_NODE) &&
               !node.getNodeName().equalsIgnoreCase(NodeAndAttributeKeyStore.OBJECT_NODE) &&
               !node.getNodeName().equalsIgnoreCase(NodeAndAttributeKeyStore.COMMENT_NODE)) {
            result = true;
        // if the current node is not object, a text or a comment, we test
        // recursively its child nodes
        } else if (node.getChildNodes().getLength()>0) {
            for (int i =0 ; i<node.getChildNodes().getLength();i++) {
                if (doesNodeContainsOtherTagThanObjectAndText
                        (node.getChildNodes().item(i))) {
                    result = true;
                    break;
                }
            }
        } else {
            result = false;
        }
        return result;
    }

    /**
     * It is impossible to extract through xpath the &lt;a&gt; nodes that only
     * contains
     * @param nodeList
     * @return
     */
    public List<Node> keepTagsWithContent(List<Node> nodeList) {
        List<Node> myNodeList = new ArrayList<Node>();
        int textCounter = 0;
        for (Node node : nodeList) {
            textCounter=0;
            for (int i =0 ; i<node.getChildNodes().getLength();i++) {
                if (doesNodeContainsOtherTagWithText
                        (node.getChildNodes().item(i))) {
                    textCounter++;
                }
                if (node.getChildNodes().item(i).getNodeName().equalsIgnoreCase(NodeAndAttributeKeyStore.TEXT_NODE) &&
                    !node.getChildNodes().item(i).getTextContent().trim().isEmpty()) {
                    textCounter++;
                }
            }
            if (textCounter>1){
                myNodeList.add(node);
            }
        }
        return myNodeList;
    }

    /**
     * This methods checks whether the current node contains other tags than
     * object or text (and comment).
     * @param node
     * @return
     */
    private boolean doesNodeContainsOtherTagWithText(Node node){
        boolean result = false;
        // if the current node is not an object, a text or a comment
        if (node.getNodeName().equalsIgnoreCase(NodeAndAttributeKeyStore.TEXT_NODE) &&
            !node.getNodeValue().trim().isEmpty()){
            result = true;
        // if the current node is not object, a text or a comment, we test
        // recursively its child nodes
        } else if (node.getChildNodes().getLength()>0) {
            for (int i =0 ; i<node.getChildNodes().getLength();i++) {
                if (doesNodeContainsOtherTagWithText
                        (node.getChildNodes().item(i))) {
                    result = true;
                    break;
                }
            }
        } else {
            result = false;
        }
        return result;
    }

    /**
     * 
     * @param nodeText
     * @param blacklist
     * @param testSolution
     * @param errorMessageCode
     * @param workingElement
     * @return
     */
    public TestSolution checkNodeValue(
            String nodeText,
            Collection<String> blacklist,
            TestSolution testSolution,
            String errorMessageCode,
            Node workingElement) {
        processRemarkService.addEvidenceElement("href");
        if (blacklist == null) {
            blacklist = new ArrayList<String>();
        }

        TestSolution result = TestSolution.NEED_MORE_INFO;
        boolean isInBlackList = false;

        for (String text : blacklist) {
            if (nodeText.trim().toLowerCase().equals(text.toLowerCase())) {
                isInBlackList = true;
                break;
            }
        }
        if (isInBlackList) {
            result = testSolution;
            processRemarkService.addSourceCodeRemark(
                    result,
                    workingElement,
                    errorMessageCode,
                    nodeText);
        }
        return result;
    }

    /**
     * 
     * @param xPathExpression (nullable)
     * @param expectedTestSolution
     * @param remarkMessage
     * @param processRemarkList
     * @return
     */
    public TestSolution searchIdenticalLinkWithDifferentTarget (
            String xPathExpression,
            TestSolution expectedTestSolution,
            String remarkMessage,
            List<ProcessRemark> processRemarkList,
            boolean hasTitleAttribute){

        if (xPathExpression != null) {
            sspHandler.beginSelection().domXPathSelectNodeSet(xPathExpression);
        }

        Collection<TestSolution> testSolutions = new ArrayList<TestSolution>();
        // We first search the identical links in the node selected set
        Set<List<Node>> identicalLinkSet = searchIdenticalNodes(
                sspHandler.getSelectedElementList(), hasTitleAttribute);

        // If identical link have been found
        if (identicalLinkSet!= null && !identicalLinkSet.isEmpty()) {
            // for each list of identical links
            for (List<Node> nodeList : identicalLinkSet){
                boolean identicalTarget = true;
                for (int i=1 ; i<nodeList.size() ;i++){
                    // we check whether the href value of each node is different
                    // from the href value of the previous one
                    if (!nodeList.get(i-1).getAttributes().getNamedItem(NodeAndAttributeKeyStore.HREF_ATTR).getTextContent().trim().
                            equalsIgnoreCase(
                            nodeList.get(i).getAttributes().getNamedItem(NodeAndAttributeKeyStore.HREF_ATTR).getTextContent().trim())) {
                        identicalTarget = false;
                        break;
                    }
                }
                // if two nodes with a different href value are found, a
                // source code remark is thrown for each link of the list
                if (!identicalTarget) {
                    for (Node node : nodeList){
                        addSourceCodeRemarkForIdenticalLinks(
                                expectedTestSolution,
                                node,
                                remarkMessage,
                                buildTextContentFromNodeElements(node));
                    }
                    testSolutions.add(expectedTestSolution);
                // if the expected solution is failed, the result is decidable
                // so the inverse solution of failed is passed
                } else if (expectedTestSolution.equals(TestSolution.FAILED)) {
                    testSolutions.add(TestSolution.PASSED);
                // if the expected solution is nmi, the result is not decidable
                // so the inverse solution of failed is still nmi
                } else if (expectedTestSolution.equals(TestSolution.NEED_MORE_INFO)) {
                    testSolutions.add(TestSolution.NEED_MORE_INFO);
                }
            }
        }
        processRemarkList.addAll(sspHandler.getRemarkList());
        return RuleHelper.synthesizeTestSolutionCollection(testSolutions);
    }

    /**
     * This method adds a sourceCodeRemark with the text link, the value of
     * the title attribute and the value of the href attribute
     * @param testSolution
     * @param node
     * @param message
     * @param linkTextValue
     */
    private void addSourceCodeRemarkForIdenticalLinks(
            TestSolution testSolution,
            Node node,
            String message,
            String linkTextValue) {

        List<EvidenceElement> evidenceElementList =
                new ArrayList<EvidenceElement>();

        String linkTitleAttrValue;
        if (node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.TITLE_ATTR) != null) {
            linkTitleAttrValue=node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.TITLE_ATTR).getTextContent().trim();
        } else {
            linkTitleAttrValue="";
        }
        evidenceElementList.add(processRemarkService.getEvidenceElement(
                processRemarkService.getEvidenceDataService().findByCode(EvidenceKeyStore.LINK_TITLE_ATTR_EE).getCode(),
                linkTitleAttrValue));

        evidenceElementList.add(processRemarkService.getEvidenceElement(
                processRemarkService.getEvidenceDataService().findByCode(EvidenceKeyStore.LINK_TEXT_EE).getCode(),
                linkTextValue));

        String linkHrefAttrValue;
        if (node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.HREF_ATTR) != null) {
            linkHrefAttrValue=node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.HREF_ATTR).getTextContent().trim();
        } else {
            linkHrefAttrValue="";
        }
        evidenceElementList.add(processRemarkService.getEvidenceElement(
                processRemarkService.getEvidenceDataService().findByCode(EvidenceKeyStore.LINK_HREF_ATTR_EE).getCode(),
                linkHrefAttrValue));

        // We add a "fake" evidence element (as default evidence) which is the
        // concatenation of the title attribute and the text link.
        // This way, we can distinguish couples of sourceCodeRemark, knowing that
        // the couple messageCode/defaultEvidence value is used to do so.

        evidenceElementList.add(processRemarkService.getEvidenceElement(
                processRemarkService.getEvidenceDataService().findByCode(processRemarkService.DEFAULT_EVIDENCE).getCode(),
                linkTextValue+linkTitleAttrValue));

        processRemarkService.addSourceCodeRemark(
                testSolution,
                node,
                message,
                evidenceElementList);
    }

    /**
     * This methods search the identical links (link text + title attribute value
     * if it exists) and return a set of identical links
     * @param nodeList
     * @param hasTitleAttribute
     * @return
     */
    private Set<List<Node>> searchIdenticalNodes(
            List<Node> nodeList,
            boolean hasTitleAttribute) {

        if (nodeList.isEmpty()) {
            return null;
        }

        Map<String, List<Node>> nodeMap = new HashMap<String, List<Node>>();
        // For each node of the selection set
        for (Node node : nodeList){
            // if the node contains a title attribute
            if (hasTitleAttribute &&
                    node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.TITLE_ATTR) != null) {

                //we build a key from the node content and the title attribute
                StringBuilder nodeContent = 
                        new StringBuilder(buildTextContentFromNodeElements(node));

                nodeContent.append(node.getAttributes().getNamedItem(NodeAndAttributeKeyStore.TITLE_ATTR).
                        getTextContent().trim().toLowerCase());

                // if the current map contains this key, two identical links
                // have been found
                if (nodeMap.containsKey(nodeContent.toString())) {
                    nodeMap.get(nodeContent.toString()).add(node);
                } else {
                    List<Node> nList = new ArrayList<Node>();
                    nList.add(node);
                    nodeMap.put(nodeContent.toString(), nList);
                }
            } else {
                //we build a key from the node content
                String nodeContent = buildTextContentFromNodeElements(node);
                // if the current map contains this key, two identical links
                // have been found
                if (nodeMap.containsKey(nodeContent)) {
                    nodeMap.get(nodeContent).add(node);
                } else {
                    List<Node> nList = new ArrayList<Node>();
                    nList.add(node);
                    nodeMap.put(nodeContent, nList);
                }
            }
        }
        // We finally parse the map to only keep identical links (list of links
        // with more than 1 element)
        Set<List<Node>> nodeListSet = null;
        for (List<Node> nodeListFromMap : nodeMap.values()){
            if (nodeListFromMap.size()>1){
                if (nodeListSet == null){
                    nodeListSet = new HashSet<List<Node>>();
                }
                nodeListSet.add(nodeListFromMap);
            }
        }
        return nodeListSet;
    }

    private TestSolution checkAttributeValueNotEmpty(String attributeName) {
        Collection<TestSolution> resultSet = new HashSet<TestSolution>();
        for (Node workingElement : sspHandler.getSelectedElementList()) {
            TestSolution result = TestSolution.PASSED;
            Node attribute = workingElement.getAttributes().getNamedItem(
                    attributeName);
            if (attribute != null) {
                if (attribute.getNodeValue().length() == 0) {
                    result = TestSolution.FAILED;
                    addSourceCodeRemark(
                            result,
                            workingElement,
                            "TitleAttributeEmpty",
                            "",
                            buildTextContentFromNodeElements(workingElement));
                }
            } else {
                result = TestSolution.NOT_APPLICABLE;
            }
            resultSet.add(result);
        }
        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

}