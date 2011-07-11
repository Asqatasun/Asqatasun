/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.crawler.util;

import java.util.regex.Pattern;
import javax.xml.xpath.XPathExpressionException;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author jkowalczyk
 */
public class HeritrixRegexpParameterValueModifier extends HeritrixConfigurationModifier {

    private static final String BEGIN_REGEXP = "(?i)(.*";
    private static final String END_REGEXP = ".*)$";

    public HeritrixRegexpParameterValueModifier() {
        super();
    }

    @Override
    Document modifyDocument(Document document, String value) {
        try {
            Node node = getNodeFromXpath(document);
            String[] regexpTab = value.split(";");
            for (int i = 0; i < regexpTab.length; i++) {
                addRegexpAsParameter(regexpTab[i], node, document);
            }
        } catch (XPathExpressionException xee) {
            Logger.getLogger(HeritrixParameterValueModifier.class.getName()).warn(xee);
        }
        return document;
    }

    private void addRegexpAsParameter(String regexp, Node node, Document document) {
        StringBuilder strb = new StringBuilder();
        if (regexp != null && !regexp.isEmpty()) {
            strb.append(BEGIN_REGEXP);
            strb.append(regexp);
            strb.append(END_REGEXP);
        }
        String builtRegexp = strb.toString();
        if (builtRegexp != null && !builtRegexp.isEmpty() && compileRegexp(regexp)) {
            Element element = document.createElement(getElementName());
            element.appendChild(document.createTextNode(builtRegexp));
            node.appendChild(element);
        }
    }

    private boolean compileRegexp(String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        return (pattern != null) ? true : false;
    }

}