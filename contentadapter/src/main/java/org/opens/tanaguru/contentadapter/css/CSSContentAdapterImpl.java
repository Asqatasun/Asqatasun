package org.opens.tanaguru.contentadapter.css;

import org.opens.tanaguru.contentadapter.ContentParser;
import org.opens.tanaguru.contentadapter.Resource;
import org.opens.tanaguru.contentadapter.js.AbstractContentAdapter;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import org.opens.tanaguru.contentadapter.util.ExternalRsrc;
import org.opens.tanaguru.contentadapter.util.HtmlNodeAttr;
import org.opens.tanaguru.contentadapter.util.HtmlTags;
import org.opens.tanaguru.contentadapter.util.InlineRsrc;
import org.opens.tanaguru.contentadapter.util.LocalRsrc;
import org.opens.tanaguru.contentadapter.util.ReferencedRsrc;
import com.thoughtworks.xstream.XStream;
import java.util.HashSet;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.LocatorImpl;

public class CSSContentAdapterImpl extends AbstractContentAdapter implements
        CSSContentAdapter, ContentHandler {

    private StringBuffer buffer;
    private Set<CSSOMStyleSheet> cssSet;
    private Set cssVector;
    private boolean isExternalCSS = false;
    private boolean isInlineCSS = false;
    private boolean isLocalCSS = false;
    private boolean isReferencedCSS = false;
    private Locator locator;
    private CSSParser parser;

    /**
     * Default constructor.
     */
    public CSSContentAdapterImpl() {
        super();
    }

    /**
     * @param ch
     * @param start
     * @param end
     * @see org.xml.sax.ContentHandler#characters(char[], int, int)
     */
    public void characters(char[] ch, int start, int end) throws SAXException {

        if (isLocalCSS) {
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
            resource.addAllResource(cssVector);

            for (Object object : resource.getResourceSet()) {
                Resource r = (Resource) object;
                if (r.getResource() != null) {
                    parser.setResource(r);
                    parser.run();
                    cssSet.add(parser.getResult());
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

        if ((isLocalCSS) || (isExternalCSS)) {
            if (resource != null) {
                cssVector.add(resource);
                isLocalCSS = false;
                isExternalCSS = false;
            }

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
        return new XStream().toXML(cssSet);
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
        // Not used
    }

    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    public void setParser(ContentParser parser) {
        this.parser = (CSSParser) parser;
    }

    /**
     * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
     */
    public void skippedEntity(String arg0) throws SAXException {
        // Not used
    }

    /**
     * Event fired when the parse starts
     *
     * @throws SAXException
     * @see org.xml.sax.ContentHandler#startDocument()
     */
    public void startDocument() throws SAXException {
        locator = new LocatorImpl();
        cssVector = new HashSet();
        buffer = new StringBuffer();
        cssSet = new HashSet<CSSOMStyleSheet>();
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

        // localCSS
        if (HtmlTags.STYLE.equalsIgnoreCase(localName)) {
            isLocalCSS = true;
            resource = new CSSResourceImpl(null, locator.getLineNumber(),
                    new LocalRsrc());
        }
        // externalCSS
        if (HtmlNodeAttr.LINK.equalsIgnoreCase(localName)) {

            String rel = attributs.getValue(HtmlNodeAttr.REL);

            if ("stylesheet".equalsIgnoreCase(rel)) {
                isExternalCSS = true;

                // resolve the css relative path to its absolute path and add it
                // to the external css links collection
                String cssRelativePath = attributs.getValue(HtmlNodeAttr.HREF);

                String cssAbsolutePath = urlIdentifier.resolve(cssRelativePath).toExternalForm();

                downloader.setURL(cssAbsolutePath);
                downloader.run();

                // TODO Enlever ce commentaire qui ajoute ressource feuille de style pour l'association à l'audit et ainsi la persister.
//              contentList.add(contentFactory.createStylesheetContent(new Date(), cssAbsolutePath, ssp, downloader.getResult()));

                resource = new CSSResourceImpl(downloader.getResult(),
                        locator.getLineNumber(), new ExternalRsrc());
            }
        }

        // look up for inlineCSS
        for (int i = 0; i < attributs.getLength(); i++) {
            String attrValue = attributs.getValue(i);
            String attrName = attributs.getLocalName(i);

            if (HtmlNodeAttr.STYLE.equalsIgnoreCase(attrName)) {
                isInlineCSS = true;
                String cssString = localName + "{" + attrValue + "}";
                resource = new CSSResourceImpl(cssString, locator.getLineNumber(), new InlineRsrc());
                cssVector.add(resource);
                break;
            }
        }
        // look up for id attribute ReferencedCSS
        for (int i = 0; i < attributs.getLength(); i++) {
            String attrValue = attributs.getValue(i);
            String attrName = attributs.getLocalName(i);
            if (HtmlNodeAttr.ID.equalsIgnoreCase(attrName)) {
                isReferencedCSS = true;
                String cssString = attrValue + "{property : value}";
                resource = new CSSResourceImpl(cssString, locator.getLineNumber(), new ReferencedRsrc());
                cssVector.add(resource);
                break;
            }
        }
        // look up for class attribute ReferencedCSS
        for (int i = 0; i < attributs.getLength(); i++) {
            String attrValue = attributs.getValue(i);
            String attrName = attributs.getLocalName(i);
            if (HtmlNodeAttr.CLASS.equalsIgnoreCase(attrName)) {
                isReferencedCSS = true;
                String cssString = attrValue + "{property : value}";
                resource = new CSSResourceImpl(cssString, locator.getLineNumber(), new ReferencedRsrc());
                cssVector.add(resource);
                break;
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
