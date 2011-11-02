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
package org.opens.tanaguru.contentadapter.html;

import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XmlSerializer;

import org.opens.tanaguru.contentadapter.HTMLCleaner;

/**
 * 
 * @author jkowalczyk
 */
public class HTMLCleanerImpl extends AbstractHTMLCleaner implements HTMLCleaner {
    
    static final String CORRECTOR_NAME = "HTMLCleaner";
        private HtmlCleaner cleaner;
    private CleanerProperties props;
    private XmlSerializer serializer;

    public HTMLCleanerImpl() {
        super();
        cleaner = new HtmlCleaner();
        props = cleaner.getProperties();
        props.setOmitComments(true);
        props.setOmitXmlDeclaration(true);
        props.setOmitDoctypeDeclaration(true);
        props.setUseCdataForScriptAndStyle(true);
        props.setNamespacesAware(true);
        serializer = new PrettyXmlSerializer(props);
    }

    @Override
    public void run() {
        try {
            TagNode node = cleaner.clean(dirtyHTML);
            result = serializer.getAsString(node);
            node=null;
        } catch (IOException ex) {
            Logger.getLogger(HTMLCleanerImpl.class.getName()).log(Level.SEVERE,
                    null, ex);
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String getCorrectorName() {
        return CORRECTOR_NAME;
    }

}