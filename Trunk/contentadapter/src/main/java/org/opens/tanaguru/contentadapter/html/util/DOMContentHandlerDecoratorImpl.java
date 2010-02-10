package org.opens.tanaguru.contentadapter.html.util;

import java.util.HashSet;
import java.util.Set;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * 
 * @author ADEX
 */
public class DOMContentHandlerDecoratorImpl implements
        DOMContentHandlerDecorator {

    private Set<ContentHandler> contentHandlerSet;

    public DOMContentHandlerDecoratorImpl() {
        super();
        this.contentHandlerSet = new HashSet<ContentHandler>();
    }

    public void addContentHandler(ContentHandler contentHandler) {
        contentHandlerSet.add(contentHandler);
    }

    public void characters(char[] ch, int start, int length)
            throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.characters(ch, start, length);
        }
    }

    public void endDocument() throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.endDocument();
        }
    }

    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.endElement(uri, localName, qName);
        }
    }

    public void endPrefixMapping(String prefix) throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.endPrefixMapping(prefix);
        }
    }

    public Set<ContentHandler> getContentHandlerSet() {
        return contentHandlerSet;
    }

    public void ignorableWhitespace(char[] ch, int start, int length)
            throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.ignorableWhitespace(ch, start, length);
        }
    }

    public void processingInstruction(String target, String data)
            throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.processingInstruction(target, data);
        }
    }

    public void removeContentHandler(ContentHandler contentHandler) {
        contentHandlerSet.remove(contentHandler);
    }

    public void setContentHandlerSet(Set<ContentHandler> contentHandlerSet) {
        this.contentHandlerSet = contentHandlerSet;
    }

    public void setDocumentLocator(Locator locator) {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.setDocumentLocator(locator);
        }
    }

    public void skippedEntity(String name) throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.skippedEntity(name);
        }
    }

    public void startDocument() throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.startDocument();
        }
    }

    public void startElement(String uri, String localName, String qName,
            Attributes atts) throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.startElement(uri, localName, qName, atts);
        }
    }

    public void startPrefixMapping(String prefix, String uri)
            throws SAXException {
        for (ContentHandler contentHandler : contentHandlerSet) {
            contentHandler.startPrefixMapping(prefix, uri);
        }
    }
}
