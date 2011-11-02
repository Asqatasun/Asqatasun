/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
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