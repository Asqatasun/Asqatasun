/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.crawler.util;

import javax.xml.xpath.XPathExpressionException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author jkowalczyk
 */
public class HeritrixAttributeValueModifier extends HeritrixConfigurationModifier{

    private static final String DEFAULT_ATTRIBUTE_NAME = "value";

    public HeritrixAttributeValueModifier(){
        super();
    }

    @Override
    public Document modifyDocument(Document document, String value) {
        try {
            NamedNodeMap attr = getNodeFromXpath(document).getAttributes();
            Node nodeAttr = attr.getNamedItem(DEFAULT_ATTRIBUTE_NAME);
            nodeAttr.setTextContent(value);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(HeritrixParameterValueModifier.class.getName()).warn(ex);
        }
        return document;
    }

}