package org.opens.tanaguru.processor;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collection;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.exception.IncoherentValueDomainsException;
import org.opens.tanaguru.entity.factory.audit.ProcessRemarkFactory;
import org.opens.tanaguru.entity.factory.audit.SourceCodeRemarkFactory;
import org.opens.tanaguru.ruleimplementation.RuleHelper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.xml.sax.SAXException;

public class DOMHandlerImpl implements DOMHandler {

    protected Document document;
    private boolean initialized = false;
    protected ProcessRemarkFactory processRemarkFactory;
    protected List<ProcessRemark> remarkList;
    protected List<Node> selectedElementList;
    protected SourceCodeRemarkFactory sourceCodeRemarkFactory;
    protected SSP ssp;
    protected XPath xpath;

    public DOMHandlerImpl() {
        super();
    }

    public DOMHandlerImpl(SSP ssp) {
        this.ssp = ssp;
    }

    protected void addSourceCodeRemark(TestSolution processResult, Node node,
            String messageCode, String attributeName) {// XXX
        SourceCodeRemark remark = sourceCodeRemarkFactory.create();
        remark.setIssue(processResult);
        remark.setMessageCode(messageCode);
        int nodeIndex = getNodeIndex(node);
        int lineNumber = 0;
        StringReader sr = new StringReader(ssp.getDOM());
        BufferedReader br = new BufferedReader(sr);
        String line;
        boolean found = false;
        int index = 0;
        try {
            while (((line = br.readLine()) != null) && !found) {
                lineNumber++;
                index = 0;
                while (index != -1) {
                    index = line.toLowerCase().indexOf(
                            "<" + node.getNodeName().toLowerCase(), index);
                    if (index != -1) {
                        if (nodeIndex == 0) {
                            found = true;
                            break;
                        }
                        nodeIndex--;
                        index += node.getNodeName().length();
                    }
                }
            }
            remark.setLineNumber(lineNumber);
            remark.setCharacterPosition(index + 1);
            remark.setTarget(attributeName);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        remarkList.add(remark);
    }

    public DOMHandler beginSelection() {
        initialize();

        selectedElementList = new ArrayList<Node>();
        remarkList = new ArrayList<ProcessRemark>();

        return this;
    }

    public TestSolution checkAttributeExists(String attributeName) {
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
        for (Node workingElement : selectedElementList) {
            TestSolution processResult = TestSolution.PASSED;
            Node attribute = workingElement.getAttributes().getNamedItem(
                    attributeName);
            if (attribute == null) {
                processResult = TestSolution.FAILED;
                addSourceCodeRemark(processResult, workingElement,
                        "AttributeMissing", attributeName);
            }
            resultSet.add(processResult);
        }
        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

    public TestSolution checkAttributeValueExpression(String attributeName,
            String regexp) {
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
        for (Node workingElement : selectedElementList) {
            TestSolution result = TestSolution.PASSED;
            Node attribute = workingElement.getAttributes().getNamedItem(
                    attributeName);
            if (attribute != null) {
                if (attribute.getNodeValue().matches(regexp)) {
                    result = TestSolution.FAILED;
                    addSourceCodeRemark(result, workingElement,
                            "NotMatchExpression", attribute.getNodeValue());
                }
            } else {
                result = TestSolution.NOT_APPLICABLE;
            }
            resultSet.add(result);
        }
        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

    public TestSolution checkAttributeValueLengthLower(String attributeName,
            int length, TestSolution defaultFailResult) {
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
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

    public TestSolution checkAttributeValueNotEmpty(String attributeName) {
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
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

    public TestSolution checkAttributeValueIsEmpty(String attributeName) {
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
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

    public TestSolution checkChildNodeExists(String childNodeName) {
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
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
                addSourceCodeRemark(result, workingElement, "ChildNodeMissing",
                        childNodeName);
            }
            resultSet.add(result);
        }
        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

    public TestSolution checkChildNodeExistsRecursively(String childNodeName) {
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
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

    public TestSolution checkContentNotEmpty() {
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
        for (Node workingElement : selectedElementList) {
            TestSolution result = TestSolution.PASSED;
            if (workingElement.getTextContent().length() == 0 && workingElement.getChildNodes().getLength() == 0) {
                result = TestSolution.FAILED;
                addSourceCodeRemark(result, workingElement, "ValueEmpty",
                        workingElement.getNodeValue());
            }
            resultSet.add(result);
        }
        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

    public TestSolution checkEachWithXpath(String expr) {
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
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
                Logger.getLogger(DOMHandlerImpl.class.getName()).log(
                        Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
            resultSet.add(tempResult);
        }
        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

    public TestSolution checkNodeValue(Collection<String> blacklist,
            Collection<String> whitelist) {
        if (whitelist == null) {
            whitelist = new ArrayList<String>();
        }
        if (blacklist == null) {
            blacklist = new ArrayList<String>();
        }

        Set<TestSolution> resultSet = new HashSet<TestSolution>();
        for (Node workingElement : selectedElementList) {
            TestSolution result = TestSolution.NEED_MORE_INFO;
            boolean isInBlackList = false;
            boolean isInWhiteList = false;
            String nodeValue = workingElement.getNodeValue();
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
                result = TestSolution.FAILED;
                addSourceCodeRemark(result, workingElement, "BlackListedValue",
                        nodeValue);
            }
            if (isInWhiteList) {
                result = TestSolution.PASSED;
            }
            if (result.equals(TestSolution.NEED_MORE_INFO)) {
                addSourceCodeRemark(result, workingElement, "VerifyValue",
                        nodeValue);
            }
            resultSet.add(result);
        }

        return RuleHelper.synthesizeTestSolutionCollection(resultSet);
    }

    public TestSolution checkTextContentAndAttributeValue(String attributeName,
            Collection<String> blacklist, Collection<String> whitelist) {
        if (whitelist == null) {
            whitelist = new ArrayList<String>();
        }
        if (blacklist == null) {
            blacklist = new ArrayList<String>();
        }

        Set<TestSolution> resultSet = new HashSet<TestSolution>();
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

    public TestSolution checkTextContentValue(Collection<String> blacklist,
            Collection<String> whitelist) {

        if (whitelist == null) {
            whitelist = new ArrayList<String>();
        }
        if (blacklist == null) {
            blacklist = new ArrayList<String>();
        }

        Set<TestSolution> resultSet = new HashSet<TestSolution>();
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

    public TestSolution checkTextContentValueLengthLower(int length,
            TestSolution defaultFailResult) {
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
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

    public TestSolution checkTextContentValueNotEmpty() {
        Set<TestSolution> resultSet = new HashSet<TestSolution>();
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

    public WebResource getPage() {
        return this.ssp.getPage();
    }

    public List<ProcessRemark> getRemarkList() {
        return remarkList;
    }

    public List<Node> getSelectedElementList() {
        return selectedElementList;
    }

    public SSP getSSP() {
        return ssp;
    }

    public List<String> getTextContentValues() {
        List<String> values = new ArrayList<String>();
        for (Node workingElement : selectedElementList) {
            values.add(workingElement.getTextContent());
        }
        return values;
    }

    private void initialize() {
        if (initialized) {
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
            initialized = true;
        } catch (IOException ex) {
            Logger.getLogger(DOMHandlerImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
            throw new RuntimeException(ex);
        } catch (SAXException ex) {
            Logger.getLogger(DOMHandlerImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
            throw new RuntimeException(ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(DOMHandlerImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
            throw new RuntimeException(ex);
        }
    }

    public boolean isSelectedElementsEmpty() {
        return !selectedElementList.isEmpty();
    }

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

    public DOMHandler selectDocumentNodes(Collection<String> labels) {
        for (String label : labels) {
            NodeList nodeList = document.getElementsByTagName(label);
            for (int j = 0; j < nodeList.getLength(); j++) {
                selectedElementList.add(nodeList.item(j));
            }
        }
        return this;
    }

    public DOMHandler selectDocumentNodes(String tagName) {
        NodeList nodeList = document.getElementsByTagName(tagName);
        for (int j = 0; j < nodeList.getLength(); j++) {
            selectedElementList.add(nodeList.item(j));
        }
        return this;
    }

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

    /**
     * http://www.ibm.com/developerworks/library/x-javaxpathapi.html
     *
     * @param expr
     * @return
     */
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
            Logger.getLogger(DOMHandlerImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
            throw new RuntimeException(ex);
        }
        return this;
    }

    public void setProcessRemarkFactory(
            ProcessRemarkFactory processRemarkFactory) {
        this.processRemarkFactory = processRemarkFactory;
    }

    public void setSelectedElementList(List<Node> selectedElementList) {
        this.selectedElementList = selectedElementList;
    }

    public void setSourceCodeRemarkFactory(
            SourceCodeRemarkFactory sourceCodeRemarkFactory) {
        this.sourceCodeRemarkFactory = sourceCodeRemarkFactory;
    }

    public void setSSP(SSP ssp) {
        this.ssp = ssp;
        initialized = false;
    }
}
