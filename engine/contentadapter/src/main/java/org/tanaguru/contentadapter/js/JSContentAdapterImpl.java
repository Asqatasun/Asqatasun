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
package org.tanaguru.contentadapter.js;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.tanaguru.contentadapter.ContentParser;
import org.tanaguru.contentadapter.Resource;
import org.tanaguru.contentadapter.util.*;
import org.tanaguru.contentloader.Downloader;
import org.tanaguru.entity.service.audit.ContentDataService;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.LocatorImpl;

/**
 * @author jkowalczyk
 * 
 *         XML SAX ContentHandler implementation for Resource Adaptation.
 * 
 */
public class JSContentAdapterImpl extends AbstractContentAdapter implements
        JSContentAdapter, ContentHandler {

    private static final String[] EVENTS = {"onabort", "onblur", "onchange",
        "onclick", "ondblclick", "onerror", "onfocus", "onkeydown",
        "onkeypress", "onkeyup", "onload", "onmousedown", "onmousemove",
        "onmouseout", "onmouseover", "onmouseup ", "onreset", "onresize",
        "onselect", "onsubmit", "onunload"};
    private StringBuffer buffer;
    private List<Resource> inlineResourceList;
    private boolean isExternalJS = false;
    private boolean isInlineJS = false;
    private boolean isLocalJS = false;
    private Set<JSResource> jsSet;
    private Set jsVector;
    private Locator locator;
    private JSParser parser;

    /**
     * Default constructor.
     * @param urlIdentifier
     * @param downloader
     * @param contentDataService
     */
    public JSContentAdapterImpl(URLIdentifier urlIdentifier, Downloader downloader, ContentDataService contentDataService) {
        super(urlIdentifier, downloader, contentDataService);
    }

    /**
     * @param ch
     * @param start
     * @param end
     * @throws org.xml.sax.SAXException
     * @see org.xml.sax.ContentHandler#characters(char[], int, int)
     */
    @Override
    public void characters(char[] ch, int start, int end) throws SAXException {

        if (isLocalJS) {
            buffer.append(new String(ch, start, end).trim());
            resource.setResource(buffer.toString());
        }
    }

    /**
     * Event fired at the end of the document parse
     *
     * @throws SAXException
     * @see org.xml.sax.ContentHandler#endDocument()
     */
    @Override
    public void endDocument() throws SAXException {

        if (resource != null) {
            resource.addAllResource(jsVector);

            for (Object object : resource.getResourceSet()) {
                Resource r = (Resource) object;
                if (r.getResource() != null) {
                    parser.setResource((Resource) r);
                    parser.run();
                    jsSet.add(parser.getResult());
                }
            }
        }
    }

    /**
     * @param nameSpaceURI
     * @param localName
     * @param rawName
     * @throws org.xml.sax.SAXException
     * @see org.xml.sax.ContentHandler#endElement(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String nameSpaceURI, String localName, String rawName)
            throws SAXException {

        if ((isLocalJS) || (isExternalJS)) {
            if (resource != null) {
                jsVector.add(resource);
                isLocalJS = false;
                isExternalJS = false;
            }
        } else if (isInlineJS) {
            jsVector.addAll(inlineResourceList);
            isInlineJS = false;
            inlineResourceList.clear();
        }
    }

    /**
     * @param prefix
     * @throws org.xml.sax.SAXException
     * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
     */
    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        // Not used
    }

//    @Override
//    public String getAdaptation() {
//        return new XStream().toXML(jsSet);
//    }

    /**
     * @param ch
     * @param start
     * @param end
     * @throws org.xml.sax.SAXException
     * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
     */
    @Override
    public void ignorableWhitespace(char[] ch, int start, int end)
            throws SAXException {
        // Not used
    }

    /**
     * @param target
     * @param data
     *            d'une
     * @throws org.xml.sax.SAXException
     * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void processingInstruction(String target, String data)
            throws SAXException {
        // XXX Not used
    }

    @Override
    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    @Override
    public void setParser(ContentParser parser) {
        if (parser instanceof JSParser) {
            this.parser = (JSParser) parser;
        }
    }

    /**
     * @param arg0
     * @throws org.xml.sax.SAXException
     * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
     */
    @Override
    public void skippedEntity(String arg0) throws SAXException {
        // XXX Not used
    }

    /**
     * Event fired when the parse starts
     *
     * @throws SAXException
     * @see org.xml.sax.ContentHandler#startDocument()
     */
    @Override
    public void startDocument() throws SAXException {
        buffer = new StringBuffer();
        jsVector = new HashSet();
        locator = new LocatorImpl();
        inlineResourceList = new ArrayList<>();
        jsSet = new HashSet<>();
    }

    /**
     * @param nameSpaceURI
     * @param localName
     * @param rawName
     * @param attributs
     * @throws SAXException
     * @see org.xml.sax.ContentHandler#startElement(java.lang.String,
     *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String nameSpaceURI, String localName,
            String rawName, Attributes attributs) throws SAXException {

        buffer.setLength(0);

        if (HtmlTags.SCRIPT.equalsIgnoreCase(localName)) {
            String src = attributs.getValue("src");
            // externalJS
            if (src != null) {
                isExternalJS = true;

                String externalResourceAbsolutePath = getUrlIdentifier().resolve(src).toExternalForm();

                getDownloader().setURL(externalResourceAbsolutePath);
                getDownloader().run();

                // TODO Enlever ce commentaire qui ajoute ressource script pour l'association à l'audit et ainsi la persister.
//                contentList.add(contentFactory.createStylesheetContent(new Date(), externalResourceAbsolutePath, ssp, downloader.getResult()));

                resource = new JSResourceImpl(getDownloader().getResult(), locator.getLineNumber(),
                        new ExternalRsrc());
                // localJS
            } else {
                isLocalJS = true;
                resource = new JSResourceImpl(null, locator.getLineNumber(),
                        new LocalRsrc());
            }
        }

        // inlineJS
        for (int index = 0; index < attributs.getLength(); index++) {
            String attrName = attributs.getLocalName(index);
            String attrValue = attributs.getValue(index);
            for (String s : EVENTS) {
                if (s.equalsIgnoreCase(attrName)) {
                    isInlineJS = true;

                    resource = new JSResourceImpl(attrValue, locator.getLineNumber(), new InlineRsrc());
                    inlineResourceList.add(resource);
                    break;
                }
            }
            if (HtmlNodeAttr.HREF.equalsIgnoreCase(attrName) && (attrValue.contains("javascript"))) {
                isInlineJS = true;
                resource = new JSResourceImpl(attrValue, locator.getLineNumber(), new InlineRsrc());
                inlineResourceList.add(resource);
            }
        }

    }

    /**
     * @param prefix
     *            .
     * @param URI
     * @throws org.xml.sax.SAXException
     * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void startPrefixMapping(String prefix, String URI)
            throws SAXException {
        // Not used
    }

}