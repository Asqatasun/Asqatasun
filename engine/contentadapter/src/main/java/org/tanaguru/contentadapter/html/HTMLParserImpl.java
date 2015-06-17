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
package org.tanaguru.contentadapter.html;

import org.tanaguru.contentadapter.ContentAdapter;
import org.tanaguru.contentadapter.html.util.DOMContentHandlerDecorator;
import org.tanaguru.contentadapter.html.util.DOMContentHandlerDecoratorImpl;
import java.io.IOException;
import java.io.StringReader;

import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Set;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import org.tanaguru.entity.audit.SSP;
import org.tanaguru.contentadapter.HTMLParser;

/**
 * 
 * @author jkowalczyk
 */
public class HTMLParserImpl implements HTMLParser {

    protected Set<ContentAdapter> contentAdapterSet = new HashSet<ContentAdapter>();
    protected boolean initialized = false;
    protected XMLReader saxReader;
    protected SSP ssp;

    public HTMLParserImpl(Set<ContentAdapter> contentAdapterSet) {
        super();
        this.contentAdapterSet = contentAdapterSet;
    }

    @Override
    public Set<ContentAdapter> getContentAdapterSet() {
        return contentAdapterSet;
    }

    @Override
    public SSP getSSP() {
        return ssp;
    }

    private void initialize() throws SAXException {
        if (initialized) {
            return;
        }

        // General features
        saxReader = XMLReaderFactory.createXMLReader("org.apache.xerces.parsers.SAXParser");

        saxReader.setFeature("http://apache.org/xml/features/allow-java-encodings", true);
        saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        saxReader.setFeature("http://xml.org/sax/features/validation", false);
        saxReader.setFeature("http://xml.org/sax/features/namespaces", false);
        saxReader.setFeature("http://apache.org/xml/features/validation/schema", false);
        saxReader.setFeature("http://apache.org/xml/features/continue-after-fatal-error", true);

        DOMContentHandlerDecorator contentHandlerDecorator = new DOMContentHandlerDecoratorImpl();
        for (ContentAdapter contentAdapter : contentAdapterSet) {
            contentHandlerDecorator.addContentHandler((ContentHandler) contentAdapter);
        }
        saxReader.setContentHandler(contentHandlerDecorator);
        initialized = true;
    }

    @Override
    public void run() {
        try {
            initialize();

            for (ContentAdapter handler : contentAdapterSet) {
                handler.setSSP(ssp);
            }

            saxReader.parse(new InputSource(new StringReader(ssp.getDOM())));
        } catch (SAXException ex) {
            Logger.getLogger(HTMLParserImpl.class.getName()).log(Level.WARNING,
                    null, ex);
            // If the corrected dom cannot be parsed, the dom is emptied
            // to avoid parsing errors while proccessing and to provide the user
            // an error message
            // Bug #41 correction
            ssp.setDOM("");
        } catch (IOException ex) {
            Logger.getLogger(HTMLParserImpl.class.getName()).log(Level.WARNING,
                    null, ex);
        }
    }

    @Override
    public void setContentAdapterSet(Set<ContentAdapter> contentAdapterSet) {
        this.contentAdapterSet = contentAdapterSet;
    }

    @Override
    public void setSSP(SSP ssp) {
        this.ssp = ssp;
    }

}