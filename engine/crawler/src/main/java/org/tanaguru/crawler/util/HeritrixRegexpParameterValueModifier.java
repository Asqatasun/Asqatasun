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
package org.tanaguru.crawler.util;

import java.util.regex.Pattern;
import javax.xml.xpath.XPathExpressionException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * Extends this abstract class to override the buildRegexp method
 * Format your regexp and return it
 *
 * @author jkowalczyk
 */
public abstract class HeritrixRegexpParameterValueModifier extends HeritrixParameterValueModifier {

    /*Add some comment here anthony please*/
    protected static final String END_REGEXP = ".*)$";

    public HeritrixRegexpParameterValueModifier() {
        super();
    }

    @Override
    Document modifyDocument(Document document, String value, String url) {
        try {
            Node node = getNodeFromXpath(document);
            Logger.getLogger(HeritrixParameterValueModifier.class.getName()).debug(node + " value " + value);
            String[] regexpTab = value.split(";");
            for (String regexpTab1 : regexpTab) {
                addRegexpAsParameter(regexpTab1, node, document, url);
            }
        } catch (XPathExpressionException xee) {
            Logger.getLogger(HeritrixParameterValueModifier.class.getName()).warn(xee);
        }
        return document;
    }

    private void addRegexpAsParameter(String regexp, Node node, Document document, String url) {
        if (StringUtils.isBlank(regexp)) {
            return;
        }
        String builtRegexp;
        builtRegexp = buildRegexp(regexp, url);
        Logger.getLogger(HeritrixParameterValueModifier.class.getName()).debug(" builtRegexp " + builtRegexp);
        if (StringUtils.isNotBlank(builtRegexp) && compileRegexp(regexp)) {
            Element element = document.createElement(getElementName());
            element.appendChild(document.createTextNode(builtRegexp));
            node.appendChild(element);
        }
    }

    private boolean compileRegexp(String regexp) {
        Pattern pattern = Pattern.compile(regexp);
        return (pattern != null);
    }

    /**
     * Add some comment here anthony please
     *
     * @param rawRegexp
     * @param url
     * @return
     */
    protected abstract String buildRegexp(String rawRegexp, String url);
}