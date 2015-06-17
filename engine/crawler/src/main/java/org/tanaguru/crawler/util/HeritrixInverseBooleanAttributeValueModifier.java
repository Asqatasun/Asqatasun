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
 * This modifier is supposed to work on boolean values. The value extracted from
 * the parameters is the inverse of the value to set (due to users considerations
 * on set-up interface)
 * 
 * @author jkowalczyk
 */
public class HeritrixInverseBooleanAttributeValueModifier extends DefaultHeritrixConfigurationModifier{

    private static final Logger LOGGER = Logger.getLogger(HeritrixInverseBooleanAttributeValueModifier.class);
    private static final String DEFAULT_ATTRIBUTE_NAME = "value";

    public HeritrixInverseBooleanAttributeValueModifier(){
        super();
    }

    @Override
    public Document modifyDocument(Document document, String value) {
        if (StringUtils.isBlank(value) || 
                ( !value.equalsIgnoreCase(Boolean.FALSE.toString()) &&
                    !value.equalsIgnoreCase(Boolean.TRUE.toString())) ) {
            return document;
        }
        try {
            Boolean valueToSet = !Boolean.valueOf(value);
            Node parentNode = getNodeFromXpath(document);
            NamedNodeMap attr = parentNode.getAttributes();
            Node nodeAttr = attr.getNamedItem(DEFAULT_ATTRIBUTE_NAME);
            nodeAttr.setTextContent(valueToSet.toString());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Update " + getAttributeValue() +" attribute of bean "+getIdBeanParent()+ " with value "+ valueToSet.toString());
            }
        } catch (XPathExpressionException ex) {
            LOGGER.warn(ex);
        }
        return document;
    }

}