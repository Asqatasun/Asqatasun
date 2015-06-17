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
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author jkowalczyk
 */
public class HeritrixParameterValueModifier extends DefaultHeritrixConfigurationModifier {

    public HeritrixParameterValueModifier(){
        super();
    }

    @Override
    public Document modifyDocument(Document document, String value) {
        if (value == null || value.isEmpty()) {
            return document;
        }
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