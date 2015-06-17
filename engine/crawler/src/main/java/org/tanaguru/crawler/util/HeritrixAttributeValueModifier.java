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

import javax.xml.xpath.XPathExpressionException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 *
 * @author jkowalczyk
 */
public class HeritrixAttributeValueModifier extends DefaultHeritrixConfigurationModifier{

    private static final Logger LOGGER = Logger.getLogger(HeritrixAttributeValueModifier.class);
    private static final String DEFAULT_ATTRIBUTE_NAME = "value";

    public HeritrixAttributeValueModifier(){
        super();
    }

    @Override
    public Document modifyDocument(Document document, String value) {
        if (value == null || value.isEmpty()) {
            LOGGER.debug( " value is empty " + this.getClass());
            return document;
        }
        try {
            Node parentNode = getNodeFromXpath(document);
            NamedNodeMap attr = parentNode.getAttributes();
            Node nodeAttr = attr.getNamedItem(DEFAULT_ATTRIBUTE_NAME);
            if (StringUtils.isNotEmpty(value)) {
                nodeAttr.setTextContent(value);
            } else {
                parentNode.getParentNode().removeChild(parentNode);
            }
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Update " + getAttributeValue() +" attribute of bean "+getIdBeanParent()+ " with value "+ value);
            }
        } catch (XPathExpressionException ex) {
            LOGGER.warn(ex);
        }
        return document;
    }

}