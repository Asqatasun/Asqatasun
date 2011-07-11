/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.crawler.util;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author jkowalczyk
 */
public abstract class HeritrixConfigurationModifier {

    private static final String XPATH_START = "//" ;
    private static final String ANCESTOR = "ancestor::bean/@id" ;
    private static final String AND = " and " ;
    private static final char AROBASE = '@' ;
    private static final char OPEN_BRACKET = '[' ;
    private static final char END_BRACKET = ']' ;
    private static final char EQUALS = '=' ;
    private static final char SIMPLE_QUOTE = '\'' ;

    private String attributeName = null;
    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }
    
    private String attributeValue = null;
    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    private String idBeanParent = null;
    public void setIdBeanParent(String idBeanParent) {
        this.idBeanParent = idBeanParent;
    }

    private String elementName = null;
    public String getElementName() {
        return elementName;
    }
    
    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    private String xpathExpression = null;
    public String getXpathExpression() {
        return xpathExpression;
    }

    public void setXpathExpression(String xpathExpression) {
        this.xpathExpression = xpathExpression;
    }

    private XPath xpath;
    public XPath getXpath() {
        return xpath;
    }

    public HeritrixConfigurationModifier() {
        initXpathCompiler();
    }

    protected Node getNodeFromXpath (Document document) throws XPathExpressionException {
        if (this.xpathExpression == null) {
            this.xpathExpression = buildXpathExpression();
        }
        XPathExpression xPathExpression = xpath.compile(this.xpathExpression);
        Object result = xPathExpression.evaluate(document,
                    XPathConstants.NODESET);
        NodeList nodeList = (NodeList) result;
        if (nodeList.getLength() == 1) {
            return nodeList.item(0);
        }
        return null;
    }

    private String buildXpathExpression() {
        StringBuilder strb = new StringBuilder();
        strb.append(XPATH_START);
        strb.append(elementName);
        strb.append(OPEN_BRACKET);
        strb.append(AROBASE);
        strb.append(attributeName);
        strb.append(EQUALS);
        strb.append(SIMPLE_QUOTE);
        strb.append(attributeValue);
        strb.append(SIMPLE_QUOTE);
        strb.append(AND);
        strb.append(ANCESTOR);
        strb.append(EQUALS);
        strb.append(SIMPLE_QUOTE);
        strb.append(idBeanParent);
        strb.append(SIMPLE_QUOTE);
        strb.append(END_BRACKET);
        return strb.toString();
    }

    private void initXpathCompiler(){
        XPathFactory xPathfactory = XPathFactory.newInstance();
        xpath = xPathfactory.newXPath();
    }

    /**
     *
     * @param document
     * @param value
     * @return
     */
    abstract Document modifyDocument (Document document, String value);

}