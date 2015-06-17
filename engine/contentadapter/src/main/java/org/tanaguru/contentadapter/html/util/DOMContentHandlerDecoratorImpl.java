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
package org.tanaguru.contentadapter.html.util;

import java.util.HashSet;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * 
 * @author jkowalczyk
 */
public class DOMContentHandlerDecoratorImpl implements
        DOMContentHandlerDecorator {

    private Set<ContentHandler> contentHandlerSet;

    public DOMContentHandlerDecoratorImpl() {
        super();
        this.contentHandlerSet = new HashSet<ContentHandler>();
    }

    @Override
    public void addContentHandler(ContentHandler contentHandler) {
        contentHandlerSet.add(contentHandler);
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.characters(ch, start, length);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.endDocument();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.endElement(uri, localName, qName);
        }
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.endPrefixMapping(prefix);
        }
    }

    @Override
    public Set<ContentHandler> getContentHandlerSet() {
        return contentHandlerSet;
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length)
            throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.ignorableWhitespace(ch, start, length);
        }
    }

    @Override
    public void processingInstruction(String target, String data)
            throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.processingInstruction(target, data);
        }
    }

    @Override
    public void removeContentHandler(ContentHandler contentHandler) {
        contentHandlerSet.remove(contentHandler);
    }

    @Override
    public void setContentHandlerSet(Set<ContentHandler> contentHandlerSet) {
        this.contentHandlerSet = contentHandlerSet;
    }

    @Override
    public void setDocumentLocator(Locator locator) {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.setDocumentLocator(locator);
        }
    }

    @Override
    public void skippedEntity(String name) throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.skippedEntity(name);
        }
    }

    @Override
    public void startDocument() throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.startDocument();
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes atts) throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.startElement(uri, localName, qName, atts);
        }
    }

    @Override
    public void startPrefixMapping(String prefix, String uri)
            throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.startPrefixMapping(prefix, uri);
        }
    }

}