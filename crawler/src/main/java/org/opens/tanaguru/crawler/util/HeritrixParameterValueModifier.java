/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.crawler.util;

import javax.xml.xpath.XPathExpressionException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author jkowalczyk
 */
public class HeritrixParameterValueModifier extends HeritrixConfigurationModifier {

    public HeritrixParameterValueModifier(){
        super();
    }

    @Override
    public Document modifyDocument(Document document, String value) {
        try {
            Node node = getNodeFromXpath(document);
            node.setTextContent(value);
        } catch (XPathExpressionException ex) {
            ex.fillInStackTrace();
            Logger.getLogger(HeritrixParameterValueModifier.class.getName()).warn(ex.getMessage());
        }
        return document;
    }

}