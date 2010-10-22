package org.opens.tanaguru.contentadapter.js;

import org.opens.tanaguru.contentadapter.ContentParser;
import org.opens.tanaguru.contentadapter.Resource;
import org.opens.tanaguru.contentadapter.util.ExternalRsrc;
import org.opens.tanaguru.contentadapter.util.HtmlNodeAttr;
import org.opens.tanaguru.contentadapter.util.HtmlTags;
import org.opens.tanaguru.contentadapter.util.InlineRsrc;
import org.opens.tanaguru.contentadapter.util.LocalRsrc;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import com.thoughtworks.xstream.XStream;
import java.util.HashSet;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.LocatorImpl;

/**
 * @author ADEX
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
     */
    public JSContentAdapterImpl() {
        super();
    }

    /**
     * @param ch
     * @param start
     * @param end
     * @see org.xml.sax.ContentHandler#characters(char[], int, int)
     */
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
     * @see org.xml.sax.ContentHandler#endElement(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
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
     * @param prefixe
     * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
     */
    public void endPrefixMapping(String prefix) throws SAXException {
        // Not used
    }

    public String getAdaptation() {
        return new XStream().toXML(jsSet);
    }

    /**
     * @param ch
     * @param start
     * @param end
     * @see org.xml.sax.ContentHandler#ignorableWhitespace(char[], int, int)
     */
    public void ignorableWhitespace(char[] ch, int start, int end)
            throws SAXException {
        // Not used
    }

    /**
     * @param target
     * @param data
     *            d'une
     * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String,
     *      java.lang.String)
     */
    public void processingInstruction(String target, String data)
            throws SAXException {
        // XXX Not used
    }

    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    public void setParser(ContentParser parser) {
        if (parser instanceof JSParser) {
            this.parser = (JSParser) parser;
        }
    }

    /**
     * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
     */
    public void skippedEntity(String arg0) throws SAXException {
        // XXX Not used
    }

    /**
     * Event fired when the parse starts
     *
     * @throws SAXException
     * @see org.xml.sax.ContentHandler#startDocument()
     */
    public void startDocument() throws SAXException {
        buffer = new StringBuffer();
        jsVector = new HashSet();
        locator = new LocatorImpl();
        inlineResourceList = new ArrayList<Resource>();
        jsSet = new HashSet<JSResource>();
    }

    /**
     * @param nameSpaceURI
     * @param localName
     * @param rawName
     * @throws SAXException
     * @see org.xml.sax.ContentHandler#startElement(java.lang.String,
     *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    public void startElement(String nameSpaceURI, String localName,
            String rawName, Attributes attributs) throws SAXException {

        buffer.setLength(0);

        if (HtmlTags.SCRIPT.equalsIgnoreCase(localName)) {
            String src = attributs.getValue("src");
            // externalJS
            if (src != null) {
                isExternalJS = true;

                String externalResourceAbsolutePath = urlIdentifier.resolve(src).toExternalForm();

                downloader.setURL(externalResourceAbsolutePath);
                downloader.run();

                // TODO Enlever ce commentaire qui ajoute ressource script pour l'association à l'audit et ainsi la persister.
//                contentList.add(contentFactory.createStylesheetContent(new Date(), externalResourceAbsolutePath, ssp, downloader.getResult()));

                resource = new JSResourceImpl(downloader.getResult(), locator.getLineNumber(),
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
     * @see org.xml.sax.ContentHandler#startPrefixMapping(java.lang.String,
     *      java.lang.String)
     */
    public void startPrefixMapping(String prefix, String URI)
            throws SAXException {
        // Not used
    }
}
