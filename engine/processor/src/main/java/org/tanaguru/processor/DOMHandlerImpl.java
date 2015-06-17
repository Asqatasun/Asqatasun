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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.processor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.select.Selector.SelectorParseException;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.SSP;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.exception.IncoherentValueDomainsException;
import org.tanaguru.ruleimplementation.RuleHelper;
import org.tanaguru.service.ProcessRemarkService;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 * @author jkowalczyk
 */
public class DOMHandlerImpl implements DOMHandler {

    private static Logger LOGGER = Logger.getLogger(DOMHandlerImpl.class);
    private static final String ATTRIBUTE_MISSING_MSG_CODE = "AttributeMissing";
    private static final String CHILD_NODE_MISSING_MSG_CODE ="ChildNodeMissing";
    private Document document;
    private org.jsoup.nodes.Document jsoupDocument;
    private boolean docInitialised = false;
    private boolean jsoupDocInitialised = false;
    private List<Node> selectedElementList;
    private Elements selectedElements;
    private SSP ssp;
    private XPath xpath;
    private static final Pattern NON_ALPHANUMERIC_PATTERN =
              Pattern.compile("[^\\p{L}]+");

    private ProcessRemarkService processRemarkService;

    /* the total number of elements of the DOM */
    private int totalNumberOfElement = -1;
    
    /**
     * The message code defined by the user
     */
    private String messageCode;

    @Override
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public DOMHandlerImpl() {
        super();
    }

    public DOMHandlerImpl(SSP ssp) {
        this.ssp = ssp;
    }

    @Override
    public void addSourceCodeRemark(TestSolution processResult, Node node,
            String messageCode, String attributeName) {
        processRemarkService.addSourceCodeRemark(
                processResult,
                node,
                messageCode,
                attributeName);
    }

    @Override
    public DOMHandler beginXpathSelection() {
        initialize();
        messageCode = null;
        selectedElementList = new LinkedList<Node>();
        // reset the processRemark service when beginning a new selection.
        // means the local collection of processRemark are reset
        processRemarkService.resetService();
        return this;
    }
    
    /**
     * This method should be called at each first selection of a RuleImplementation
     * to reset all the local collections.
     * 
     * @return the current instance 
     */
    @Override
    public DOMHandler beginCssLikeSelection() {
        // reset the local collection of elements
        selectedElements = new Elements();
        return this;
    }

    /**
     * The initialisation of the document is done only once when the SSP is set
     * to the instance. The Document is created, ready to be traversed, and
     * the processRemarkService is also initialised with the source. 
     */
    private void initializeJSoupDocument() {
        if (jsoupDocInitialised) {
            return;
        }
        String html = ssp.getDOM();
        Date beginDate = null;
        if (LOGGER.isDebugEnabled()) {
            beginDate = new Date();
            LOGGER.debug("Iinitialising Jsoup Document for " + ssp.getURI());
        }
        jsoupDocument = Jsoup.parse(html, ssp.getURI());
        if (LOGGER.isDebugEnabled()) {
            Date endDate = new Date();
            LOGGER.debug("initialisation of Jsoup Document for " + ssp.getURI() 
                    + "took "+ (endDate.getTime()-beginDate.getTime()) +"ms");
        }
        processRemarkService.initializeService(jsoupDocument, ssp.getDOM());
        jsoupDocInitialised = true;
    }
    
    private void initialize() {
        if (docInitialised) {
            return;
        }
        XPathFactory xPathfactory = XPathFactory.newInstance();
        xpath = xPathfactory.newXPath();

        try {
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(
                    "http://apache.org/xml/features/nonvalidating/load-external-dtd",
                    false);
            //@TODO verify the namespace property is necessary in our context
//            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            document = builder.parse(new ByteArrayInputStream(ssp.getDOM().getBytes("UTF-8")));
            docInitialised = true;
        } catch (IOException ex) {
            LOGGER.error(ssp.getURI() + "cannot be initialised due to "+ex.getMessage());
            throw new RuntimeException(ex);
        } catch (SAXException ex) {
            LOGGER.error(ssp.getURI() + "cannot be initialised due to "+ex.getMessage());
            throw new RuntimeException(ex);
        } catch (ParserConfigurationException ex) {
            LOGGER.error(ssp.getURI() + "cannot be initialised due to "+ex.getMessage());
            throw new RuntimeException(ex);
        }
        processRemarkService.initializeService(document, ssp.getDOM());
    }
    
    @Override
    public TestSolution checkAttributeExists(String attributeName) {
        if (messageCode == null) {
            messageCode = ATTRIBUTE_MISSING_MSG_CODE;
        }
        Collection<TestSolution> resultSet = new ArrayList<TestSolution>();
        for (Node workingElement : selectedElementList) {
            TestSolution processResult = TestSolution.PASSED;
            Node attribute = workingElement.getAttributes().getNamedItem(
                    attributeName);
            if (attribute == null) {
                processResult = TestSolution.FAILED;
                addSourceCodeRemark(processResult, workingElement,
                        messageCode, attributeName); 
           }
            resultSet.add(processResult);
        }
        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

    @Override
    public TestSolution checkChildNodeExists(String childNodeName) {
        if (messageCode == null) {
            messageCode = CHILD_NODE_MISSING_MSG_CODE;
        }
        Collection<TestSolution> resultSet = new ArrayList<TestSolution>();
        for (Node workingElement : selectedElementList) {
            TestSolution result = TestSolution.PASSED;
            NodeList childNodes = workingElement.getChildNodes();
            boolean found = false;
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node childNode = childNodes.item(i);
                if (childNode.getNodeName().equalsIgnoreCase(childNodeName)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                result = TestSolution.FAILED;
                addSourceCodeRemark(result, workingElement, messageCode,
                        childNodeName);
            }
            resultSet.add(result);
        }
        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

    /**
     * 
     * @param childNodeName
     * @param node
     * @return 
     */
    protected boolean checkChildNodeExistsRecursively(String childNodeName,
            Node node) {// XXX
        if (node.getNodeName().equalsIgnoreCase(childNodeName)) {
            return true;
        }
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            if (checkChildNodeExistsRecursively(childNodeName, nodes.item(i))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public TestSolution checkNodeValue(
            Collection<String> blacklist,
            Collection<String> whitelist, 
            TestSolution testSolution,
            String erroMessageCode) {
        if (whitelist == null) {
            whitelist = new ArrayList<String>();
        }
        if (blacklist == null) {
            blacklist = new ArrayList<String>();
        }

        Collection<TestSolution> resultSet = new ArrayList<TestSolution>();
        for (Node workingElement : selectedElementList) {

            TestSolution result = TestSolution.NEED_MORE_INFO;
            boolean isInBlackList = false;
            boolean isInWhiteList = false;
            String nodeValue = workingElement.getTextContent().trim();

            for (String text : blacklist) {
                if (nodeValue.toLowerCase().equals(text.toLowerCase())) {
                    isInBlackList = true;
                    break;
                }
            }
            for (String text : whitelist) {
                if (nodeValue.toLowerCase().equals(text.toLowerCase())) {
                    isInWhiteList = true;
                    break;
                }
            }
            if (isInBlackList && isInWhiteList) {
                throw new RuntimeException(
                        new IncoherentValueDomainsException());
            }
            if (isInBlackList) {
                result = testSolution;
                addSourceCodeRemark(result, workingElement, erroMessageCode,
                        nodeValue);
            }
            if (isInWhiteList) {
                result = TestSolution.PASSED;
            }
//            if (result.equals(TestSolution.NEED_MORE_INFO)) {
//                addSourceCodeRemark(result, workingElement, "VerifyValue",
//                        nodeValue);
//            }
            resultSet.add(result);
        }

        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

    @Override
    public TestSolution checkTextContentValue(Collection<String> blacklist,
            Collection<String> whitelist) {

        if (whitelist == null) {
            whitelist = new ArrayList<String>();
        }
        if (blacklist == null) {
            blacklist = new ArrayList<String>();
        }

        Collection<TestSolution> resultSet = new ArrayList<TestSolution>();
        for (Node workingElement : selectedElementList) {
            TestSolution result = TestSolution.NEED_MORE_INFO;
            boolean isInBlackList = false;
            boolean isInWhiteList = false;
            String textContent = workingElement.getTextContent();
            for (String text : blacklist) {
                if (textContent.toLowerCase().equals(text.toLowerCase())) {
                    isInBlackList = true;
                    break;
                }
            }
            for (String text : whitelist) {
                if (textContent.toLowerCase().equals(text.toLowerCase())) {
                    isInWhiteList = true;
                    break;
                }
            }
            if (isInBlackList && isInWhiteList) {
                throw new RuntimeException(
                        new IncoherentValueDomainsException());
            }
            if (isInBlackList) {
                result = TestSolution.FAILED;
                addSourceCodeRemark(result, workingElement, "BlackListedValue",
                        textContent);
            }
            if (isInWhiteList) {
                result = TestSolution.PASSED;
            }
            if (result.equals(TestSolution.NEED_MORE_INFO)) {
                addSourceCodeRemark(result, workingElement, "VerifyValue",
                        textContent);
            }
            resultSet.add(result);
        }

        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

    protected int getNodeIndex(Node node) {
        NodeList nodeList = document.getElementsByTagName(node.getNodeName());
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node current = nodeList.item(i);
            if (current.equals(node)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public WebResource getPage() {
        return this.ssp.getPage();
    }

    @Override
    public Collection<ProcessRemark> getRemarkList() {
        return processRemarkService.getRemarkList();
    }

    @Override
    public List<Node> getSelectedElementList() {
        return selectedElementList;
    }
    
    @Override
    public Elements getSelectedElements() {
        return selectedElements;
    }

    @Override
    public SSP getSSP() {
        return ssp;
    }

    @Override
    public boolean isSelectedElementsEmpty() {
        return selectedElementList.isEmpty();
    }
    
    @Override
    public DOMHandler keepNodesWithAttribute(String attributeName) {
        List<Node> elements = new ArrayList<Node>();
        for (Node workingElement : selectedElementList) {
            Node attribute = workingElement.getAttributes().getNamedItem(
                    attributeName);
            if (attribute != null) {
                elements.add(workingElement);
            }
        }
        selectedElementList = elements;
        return this;
    }

    @Override
    public DOMHandler selectChildNodes(String childNodeName) {
        List<Node> elements = new ArrayList<Node>();
        for (Node workingElement : selectedElementList) {
            NodeList childNodes = workingElement.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node childNode = childNodes.item(i);
                if (childNode.getNodeName().equalsIgnoreCase(childNodeName)) {
                    elements.add(childNode);
                }
            }
        }
        selectedElementList = elements;
        return this;
    }

    protected Collection<Node> selectChildNodesRecursively(
            Collection<String> childNodeNames, Node node) {// XXX
        List<Node> nodes = new ArrayList<Node>();
        for (String childNodeName : childNodeNames) {
            if (node.getNodeName().equalsIgnoreCase(childNodeName)) {
                nodes.add(node);
                break;
            }
        }
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            nodes.addAll(selectChildNodesRecursively(childNodeNames, childNodes.item(i)));
        }
        return nodes;
    }

    @Override
    public DOMHandler selectChildNodesRecursively(String childNodeName) {
        List<Node> elements = new ArrayList<Node>();
        for (Node workingElement : selectedElementList) {
            NodeList childNodes = workingElement.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                elements.addAll(selectChildNodesRecursively(childNodeName,
                        childNodes.item(i)));
            }
        }
        selectedElementList = elements;
        return this;
    }

    protected Collection<Node> selectChildNodesRecursively(
            String childNodeName, Node node) {// XXX
        Collection<Node> nodes = new ArrayList<Node>();
        if (node.getNodeName().equalsIgnoreCase(childNodeName)) {
            nodes.add(node);
        }
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            nodes.addAll(selectChildNodesRecursively(childNodeName, childNodes.item(i)));
        }
        return nodes;
    }

    protected Collection<Node> selectChildNodeWithAttributeRecursively(
            String attributeName, Node node) {// XXX
        Collection<Node> nodes = new ArrayList<Node>();
        NamedNodeMap attributes = node.getAttributes();
        if (attributes != null) {
            Node attribute = attributes.getNamedItem(attributeName);
            if (attribute != null) {
                nodes.add(node);
            }
        }
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            nodes.addAll(selectChildNodeWithAttributeRecursively(attributeName,
                    childNodes.item(i)));
        }
        return nodes;
    }

    @Override
    public DOMHandler selectDocumentNodes(Collection<String> labels) {
        for (String label : labels) {
            NodeList nodeList = document.getElementsByTagName(label);
            for (int j = 0; j < nodeList.getLength(); j++) {
                selectedElementList.add(nodeList.item(j));
            }
        }
        return this;
    }

    @Override
    public DOMHandler selectDocumentNodes(String tagName) {
        NodeList nodeList = document.getElementsByTagName(tagName);
        for (int j = 0; j < nodeList.getLength(); j++) {
            selectedElementList.add(nodeList.item(j));
        }
        return this;
    }

    /**
     * http://www.ibm.com/developerworks/library/x-javaxpathapi.html
     *
     * @param expr
     * @return
     */
    @Override
    public DOMHandler xPathSelectNodeSet(String expr) {
        try {
            XPathExpression xPathExpression = xpath.compile(expr);
            Object result = xPathExpression.evaluate(document,
                    XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;
            for (int j = 0; j < nodeList.getLength(); j++) {
                selectedElementList.add(nodeList.item(j));
            }
        } catch (XPathExpressionException ex) {
            throw new RuntimeException(ex);
        }
        return this;
    }
    
    /**
     * http://www.ibm.com/developerworks/library/x-javaxpathapi.html
     *
     * @param expr
     * @return
     */
    @Override
    public DOMHandler cssLikeSelectNodeSet(String expr) {
        if (StringUtils.isNotBlank(expr)) {
            try {
            selectedElements = jsoupDocument.select(expr);
            } catch (SelectorParseException spe) {               
            } catch (IllegalArgumentException iae) {
            }
        }
        return this;
    }

    @Override
    public void setSelectedElementList(List<Node> selectedElementList) {
        this.selectedElementList = selectedElementList;
    }

    @Override
    public void setSSP(SSP ssp) {
        this.ssp = ssp;
        // when a new ssp is set to the handler, the doc needs to be 
        // reinitialised.
        jsoupDocInitialised = false;
        initializeJSoupDocument();
    }

    /**
     * This method checks whether an attribute only contains
     * non alphanumeric characters.
     * 
     * @param attribute
     * @param workingElement
     * @param testSolution
     * @param remarkMessage
     * 
     * @return whether the current element only contains non alphanumerical 
     * characters
     */
    @Override
    public  TestSolution checkAttributeOnlyContainsNonAlphanumericCharacters(
            Node attribute, 
            Node workingElement,
            TestSolution testSolution,
            String remarkMessage) {
        String attributeContent;
        if (attribute.getNodeName().equalsIgnoreCase("#text")) {
            attributeContent = attribute.getTextContent().toLowerCase();
        } else {
            attributeContent = attribute.getNodeValue().toLowerCase();
        }
        if (NON_ALPHANUMERIC_PATTERN.matcher(attributeContent).matches()) {
            addSourceCodeRemark(
                testSolution,
                workingElement,
                remarkMessage,
                attribute.getNodeName());
            return testSolution;
        } else {
            return TestSolution.PASSED;
        }
    }

    /**
     * This method checks whether an attribute only contains
     * non alphanumeric characters
     * 
     * @param attributeContent
     * @param workingElement
     * @param testSolution
     * @param remarkMessage
     * 
     * @return whether the current element only contains non alphanumerical 
     * characters
     */
    @Override
    public  TestSolution checkAttributeOnlyContainsNonAlphanumericCharacters(
            String attributeContent,
            Node workingElement,
            TestSolution testSolution,
            String remarkMessage) {
        processRemarkService.addEvidenceElement("href");
        if (NON_ALPHANUMERIC_PATTERN.matcher(attributeContent).matches()) {
            addSourceCodeRemark(
                testSolution,
                workingElement,
                remarkMessage,
                attributeContent);
            return testSolution;
        } else {
            return TestSolution.PASSED;
        }
    }

    @Override
    public int getSelectedElementNumber() {
        if (selectedElementList != null && selectedElementList.size() > 0) {
            return selectedElementList.size();
        } else if (selectedElements != null && selectedElements.size() > 0) {
            return selectedElements.size();
        }
        return 0;
    }

    @Override
    public int getTotalNumberOfElements() {
        if (totalNumberOfElement == -1) {
            totalNumberOfElement = 
                    cssLikeSelectNodeSet("*").getSelectedElementNumber();
            selectedElements = new Elements();
        }
        return totalNumberOfElement;
    }
     
    @Override
    public void setProcessRemarkService(ProcessRemarkService processRemarkService) {
        this.processRemarkService = processRemarkService;
    }

    @Override
    @Deprecated
    public String getMessageCode() {
        return messageCode;
    }
    
    @Override
    @Deprecated
    public DOMHandler selectDocumentNodesWithAttribute(String attributeName) {
        List<Node> elements = new ArrayList<Node>();
        NodeList childNodes = document.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            elements.addAll(selectChildNodeWithAttributeRecursively(
                    attributeName, childNodes.item(i)));
        }
        selectedElementList = elements;
        return this;
    }
    
    @Override
    @Deprecated
    public TestSolution checkAttributeValueIsEmpty(String attributeName) {
        Collection<TestSolution> resultSet = new ArrayList<TestSolution>();
        for (Node workingElement : selectedElementList) {
            TestSolution result = TestSolution.PASSED;
            Node attribute = workingElement.getAttributes().getNamedItem(
                    attributeName);
            if (attribute != null) {
                if (attribute.getNodeValue().length() != 0) {
                    result = TestSolution.FAILED;
                    addSourceCodeRemark(result, workingElement, "ValueNotEmpty",
                            attributeName);
                }
            } else {
                result = TestSolution.NOT_APPLICABLE;
            }
            resultSet.add(result);
        }
        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

    @Override
    @Deprecated
    public DOMHandler selectChildNodesRecursively(
            Collection<String> childNodeNames) {
        List<Node> elements = new ArrayList<Node>();
        for (Node workingElement : selectedElementList) {
            NodeList childNodes = workingElement.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                elements.addAll(selectChildNodesRecursively(childNodeNames,
                        childNodes.item(i)));
            }
        }
        selectedElementList = elements;
        return this;
    }

    @Override
    @Deprecated
    public DOMHandler keepNodesWithoutChildNode(
            Collection<String> childNodeNames) {
        List<Node> elements = new ArrayList<Node>();
        for (Node workingElement : selectedElementList) {
            NodeList nodeList = workingElement.getChildNodes();
            boolean found = false;
            for (int i = 0; i < nodeList.getLength() && !found; i++) {
                Node node = nodeList.item(i);
                for (String childNodeName : childNodeNames) {
                    if (node.getNodeName().equalsIgnoreCase(childNodeName)) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                elements.add(workingElement);
            }
        }
        selectedElementList = elements;
        return this;
    }

    @Override
    @Deprecated
    public DOMHandler keepNodesWithoutChildNode(String childNodeName) {
        List<Node> elements = new ArrayList<Node>();
        for (Node workingElement : selectedElementList) {
            NodeList nodeList = workingElement.getChildNodes();
            boolean found = false;
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeName().equalsIgnoreCase(childNodeName)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                elements.add(workingElement);
            }
        }
        selectedElementList = elements;
        return this;
    }

    @Override
    @Deprecated
    public DOMHandler selectAttributeByName(String name) {
        List<Node> elements = new ArrayList<Node>();
        for (Node workingElement : selectedElementList) {
            Node attribute = workingElement.getAttributes().getNamedItem(name);
            if (attribute != null) {
                elements.add(attribute);
            }
        }
        selectedElementList = elements;
        return this;
    }

    @Override
    @Deprecated
    public DOMHandler selectChildNodes(Collection<String> childNodeNames) {
        List<Node> elements = new ArrayList<Node>();
        for (Node workingElement : selectedElementList) {
            NodeList childNodes = workingElement.getChildNodes();
            boolean found = false;
            for (int i = 0; i < childNodes.getLength() && !found; i++) {
                Node childNode = childNodes.item(i);
                for (String childNodeName : childNodeNames) {
                    if (childNode.getNodeName().equalsIgnoreCase(childNodeName)) {
                        elements.add(childNode);
                        found = true;
                        break;
                    }
                }
            }
        }
        selectedElementList = elements;
        return this;
    }
  
    @Override
    @Deprecated
    public DOMHandler keepNodesWithAttributeValueEquals(String attributeName,
            Collection<String> values) {
        List<Node> elements = new ArrayList<Node>();
        for (Node workingElement : selectedElementList) {
            Node attribute = workingElement.getAttributes().getNamedItem(
                    attributeName);
            if (attribute == null) {
                continue;
            }
            for (String value : values) {
                if (attribute.getNodeValue().equalsIgnoreCase(value)) {
                    elements.add(workingElement);
                    break;
                }
            }
        }
        selectedElementList = elements;
        return this;
    }

    @Override
    @Deprecated
    public DOMHandler keepNodesWithAttributeValueNonEmpty(String attributeName) {
        List<Node> elements = new ArrayList<Node>();
        for (Node workingElement : selectedElementList) {
            Node attribute = workingElement.getAttributes().getNamedItem(
                    attributeName);
            if (attribute != null && attribute.getNodeValue().length() > 0) {
                elements.add(workingElement);
            }
        }
        selectedElementList = elements;
        return this;
    }

    @Override
    @Deprecated
    public DOMHandler keepNodesWithAttributeValueStartingWith(
            String attributeName, Collection<String> values) {
        List<Node> elements = new ArrayList<Node>();
        for (Node workingElement : selectedElementList) {
            Node attribute = workingElement.getAttributes().getNamedItem(
                    attributeName);
            if (attribute == null) {
                continue;
            }
            for (String value : values) {
                if (attribute.getNodeValue().toLowerCase().startsWith(
                        value.toLowerCase())) {
                    elements.add(workingElement);
                    break;
                }
            }
        }
        selectedElementList = elements;
        return this;
    }

    @Override
    @Deprecated
    public DOMHandler keepNodesWithAttributeValueStartingWith(
            String attributeName, String value) {
        List<Node> elements = new ArrayList<Node>();
        for (Node workingElement : selectedElementList) {
            Node attribute = workingElement.getAttributes().getNamedItem(
                    attributeName);
            if (attribute == null) {
                continue;
            }
            if (attribute.getNodeValue().toLowerCase().startsWith(
                    value.toLowerCase())) {
                elements.add(workingElement);
                break;
            }
        }
        selectedElementList = elements;
        return this;
    }

    @Override
    @Deprecated
    public List<String> getTextContentValues() {
        List<String> values = new ArrayList<String>();
        for (Node workingElement : selectedElementList) {
            values.add(workingElement.getTextContent());
        }
        return values;
    }

    @Override
    @Deprecated
    public TestSolution checkTextContentValueLengthLower(int length,
            TestSolution defaultFailResult) {
        Collection<TestSolution> resultSet = new ArrayList<TestSolution>();
        for (Node workingElement : selectedElementList) {
            TestSolution result = TestSolution.PASSED;
            String textContent = workingElement.getTextContent();
            if (textContent.length() > length) {
                result = defaultFailResult;
                addSourceCodeRemark(result, workingElement, "LengthTooLong",
                        textContent);
            }
            resultSet.add(result);
        }

        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

    @Override
    @Deprecated
    public TestSolution checkTextContentValueNotEmpty() {
        Collection<TestSolution> resultSet = new ArrayList<TestSolution>();
        for (Node workingElement : selectedElementList) {
            TestSolution result = TestSolution.PASSED;
            if (workingElement.getTextContent().length() == 0) {
                result = TestSolution.FAILED;
                addSourceCodeRemark(result, workingElement, "ValueEmpty",
                        workingElement.getNodeValue());
            }
            resultSet.add(result);
        }
        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

    @Override
    @Deprecated
    public DOMHandler excludeNodesWithAttribute(String attributeName) {
        List<Node> nodes = new ArrayList<Node>();
        for (Node workingElement : selectedElementList) {
            Node attribute = workingElement.getAttributes().getNamedItem(
                    attributeName);
            if (attribute == null) {
                nodes.add(workingElement);
            }
        }
        selectedElementList = nodes;
        return this;
    }

    @Override
    @Deprecated
    public DOMHandler excludeNodesWithChildNode(ArrayList<String> childNodeNames) {
        List<Node> nodes = new ArrayList<Node>();
        for (Node workingElement : selectedElementList) {
            NodeList nodeList = workingElement.getChildNodes();
            boolean found = false;
            for (int i = 0; i < childNodeNames.size() && !found; i++) {
                String childNodeName = childNodeNames.get(i);
                for (int j = 0; j < nodeList.getLength(); j++) {
                    if (nodeList.item(j).getNodeName().equalsIgnoreCase(
                            childNodeName)) {
                        found = true;
                        break;
                    }
                }
            }
            if (!found) {
                nodes.add(workingElement);
            }
        }
        selectedElementList = nodes;
        return this;
    }

    @Override
    @Deprecated
    public DOMHandler excludeNodesWithChildNode(String childNodeName) {
        List<Node> nodes = new ArrayList<Node>();
        for (Node workingElement : selectedElementList) {
            NodeList nodeList = workingElement.getChildNodes();
            boolean found = false;
            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeName().equalsIgnoreCase(
                        childNodeName)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                nodes.add(workingElement);
            }
        }
        selectedElementList = nodes;
        return this;
    }

    @Override
    @Deprecated
    public List<String> getAttributeValues(String attributeName) {
        List<String> values = new ArrayList<String>();
        for (Node workingElement : selectedElementList) {
            Node attribute = workingElement.getAttributes().getNamedItem(
                    attributeName);
            if (attribute != null) {
                values.add(attribute.getNodeValue());
            }
        }
        return values;
    }

    @Override
    @Deprecated
    public TestSolution checkTextContentAndAttributeValue(String attributeName,
            Collection<String> blacklist, Collection<String> whitelist) {
        if (whitelist == null) {
            whitelist = new ArrayList<String>();
        }
        if (blacklist == null) {
            blacklist = new ArrayList<String>();
        }

        Collection<TestSolution> resultSet = new ArrayList<TestSolution>();
        for (Node workingElement : selectedElementList) {
            TestSolution result = TestSolution.NEED_MORE_INFO;
            boolean isInWhiteList = false;
            boolean isInBlackList = false;
            String textContent = workingElement.getTextContent();
            Node attribute = workingElement.getAttributes().getNamedItem(
                    attributeName);
            for (String text : blacklist) {
                if (textContent.toLowerCase().equals(text.toLowerCase())) {
                    isInBlackList = true;
                    addSourceCodeRemark(result, workingElement,
                            "BlackListedValue", textContent);
                    break;
                }
                if (attribute != null) {
                    if (attribute.getNodeValue().toLowerCase().equals(
                            text.toLowerCase())) {
                        isInBlackList = true;
                        addSourceCodeRemark(result, workingElement,
                                "BlackListedValue", attributeName);
                        break;
                    }
                }
            }
            for (String text : whitelist) {
                if (textContent.toLowerCase().equals(text.toLowerCase())) {
                    isInWhiteList = true;
                    break;
                }
                if (attribute != null) {
                    if (attribute.getNodeValue().toLowerCase().equals(
                            text.toLowerCase())) {
                        isInWhiteList = true;
                        break;
                    }
                }
            }
            if (isInBlackList && isInWhiteList) {
                throw new RuntimeException(
                        new IncoherentValueDomainsException());
            }
            if (isInWhiteList) {
                result = TestSolution.PASSED;
            }
            if (isInBlackList) {
                result = TestSolution.FAILED;
            }
            if (result.equals(TestSolution.NEED_MORE_INFO)) {
                addSourceCodeRemark(result, workingElement, "VerifyValue",
                        attributeName);
            }
            resultSet.add(result);
        }

        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }
    
    @Override
    @Deprecated
    public DOMHandler keepNodesWithChildNode(String childNodeName) {
        List<Node> elements = new ArrayList<Node>();
        for (Node workingElement : selectedElementList) {
            NodeList nodeList = workingElement.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeName().equalsIgnoreCase(childNodeName)) {
                    elements.add(workingElement);
                }
            }
        }
        selectedElementList = elements;
        return this;
    }
    
    @Override
    @Deprecated
    public TestSolution checkChildNodeExistsRecursively(String childNodeName) {
        Collection<TestSolution> resultSet = new ArrayList<TestSolution>();
        for (Node workingElement : selectedElementList) {
            TestSolution result = TestSolution.PASSED;
            boolean found = false;
            NodeList childNodes = workingElement.getChildNodes();
            for (int i = 0; i < childNodes.getLength(); i++) {
                if (checkChildNodeExistsRecursively(childNodeName, childNodes.item(i))) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                result = TestSolution.FAILED;
                addSourceCodeRemark(result, workingElement, "ChildNodeMissing",
                        childNodeName);
            }
            resultSet.add(result);
        }
        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }
    
    @Override
    @Deprecated
    public TestSolution checkContentNotEmpty() {
        Collection<TestSolution> resultSet = new ArrayList<TestSolution>();
        for (Node workingElement : selectedElementList) {
            TestSolution result = TestSolution.PASSED;
            if (workingElement.getTextContent().trim().isEmpty()
                    && (workingElement.getChildNodes().getLength() == 0
                    || (workingElement.getChildNodes().getLength() == 1
                    && workingElement.getChildNodes().item(0).getNodeName().equalsIgnoreCase("#text")))) {
                result = TestSolution.FAILED;
                addSourceCodeRemark(result, workingElement, "ValueEmpty",
                        workingElement.getNodeName());
            }
            resultSet.add(result);
        }
        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }
    
    @Override
    @Deprecated
    public TestSolution checkEachWithXpath(String expr) {
        Collection<TestSolution> resultSet = new ArrayList<TestSolution>();
        for (Node node : selectedElementList) {
            TestSolution tempResult = TestSolution.PASSED;
            try {
                XPathExpression xPathExpression = xpath.compile(expr);
                Boolean check = (Boolean) xPathExpression.evaluate(node,
                        XPathConstants.BOOLEAN);
                if (!check.booleanValue()) {
                    tempResult = TestSolution.FAILED;
                    // addSourceCodeRemark(result, node,
                    // "wrong value, does not respect xpath expression : " +
                    // expr, node.getNodeValue());
                }
            } catch (XPathExpressionException ex) {
                LOGGER.error(ex.getMessage() + " occured requesting " +expr + " on "+ssp.getURI() );
                throw new RuntimeException(ex);
            }
            resultSet.add(tempResult);
        }
        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }
    
    @Override
    @Deprecated
    public TestSolution checkAttributeValueNotEmpty(String attributeName) {
        Collection<TestSolution> resultSet = new ArrayList<TestSolution>();
        for (Node workingElement : selectedElementList) {
            TestSolution result = TestSolution.PASSED;
            Node attribute = workingElement.getAttributes().getNamedItem(
                    attributeName);
            if (attribute != null) {
                if (attribute.getNodeValue().length() == 0) {
                    result = TestSolution.FAILED;
                    addSourceCodeRemark(result, workingElement, "ValueEmpty",
                            attributeName);
                }
            } else {
                result = TestSolution.NOT_APPLICABLE;
            }
            resultSet.add(result);
        }
        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }
    
    @Override
    @Deprecated
    public TestSolution checkNodeValue(Collection<String> blacklist,
            Collection<String> whitelist) {
        return  checkNodeValue(blacklist, whitelist, TestSolution.FAILED,
                "BlackListedValue");
    }

    @Override
    @Deprecated
    public TestSolution checkAttributeValueLengthLower(String attributeName,
            int length, TestSolution defaultFailResult) {
        Collection<TestSolution> resultSet = new ArrayList<TestSolution>();
        for (Node workingElement : selectedElementList) {
            TestSolution result = TestSolution.PASSED;
            String textContent = workingElement.getTextContent();
            if (textContent.length() > length) {
                result = defaultFailResult;
                addSourceCodeRemark(result, workingElement, "LengthTooLong",
                        textContent);
            }
            resultSet.add(result);
        }

        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }
    
}
