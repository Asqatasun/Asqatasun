package org.opens.tanaguru.contentadapter.css;

import com.thoughtworks.xstream.XStream;
import java.util.Date;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.httpclient.URIException;
import org.archive.net.UURIFactory;

import org.opens.tanaguru.contentadapter.ContentParser;
import org.opens.tanaguru.contentadapter.Resource;
import org.opens.tanaguru.contentadapter.js.AbstractContentAdapter;
import org.opens.tanaguru.contentadapter.util.ExternalRsrc;
import org.opens.tanaguru.contentadapter.util.HtmlNodeAttr;
import org.opens.tanaguru.contentadapter.util.HtmlTags;
import org.opens.tanaguru.contentadapter.util.InlineRsrc;
import org.opens.tanaguru.contentadapter.util.LocalRsrc;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.audit.StylesheetContent;

import org.w3c.css.sac.SACMediaList;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.LocatorImpl;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

/**
 * External resources are adapted on the fly, and inline and local css are
 * adapted at the end of the document parse
 * @author jkowalczyk
 */

public class CSSContentAdapterImpl extends AbstractContentAdapter implements
        CSSContentAdapter, ContentHandler {

    private static final String HTTP_PREFIX = "http";
    private static final String WWW_PREFIX = "www";
    private static final String CSS_ON_ERROR = "CSS_ON_ERROR";
    private static final String URI_PREFIX = "#tanaguru-css-";
    
    private StringBuffer buffer;
    private Set<CSSOMStyleSheet> cssSet;
    private Set cssVector;
    private boolean isInlineCSS = false;
    private boolean isLocalCSS = false;
    private Locator locator;
    private CSSParser parser;
    private String currentLocalResourcePath;
    
    private boolean cssOnError = false;
    

    private Set<StylesheetContent> relatedCssSet =
            new HashSet<StylesheetContent>();

    private int internalCssCounter = 1;
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
    @Override
    public void characters(char[] ch, int start, int end) throws SAXException {

        if (isLocalCSS) {
            buffer.append(new String(ch, start, end).trim());
        }
    }

    /**
     * Event fired at the end of the document parse
     *
     * @throws SAXException
     * @see org.xml.sax.ContentHandler#endDocument()
     */
    @Override
    @SuppressWarnings("element-type-mismatch")
    public void endDocument() throws SAXException {
        if (resource != null) {
            resource.addAllResource(cssVector);

            // At the end of the document, parse and get the result for each
            // inline or local resource
            for (Object object : resource.getResourceSet()) {
                Resource r = (Resource) object;
                StylesheetContent cssContent =
                        getStylesheetFromResource(r.getResource());
                adaptContent(cssContent, r);
                cssContent.setAudit(ssp.getAudit());
                ssp.addRelatedContent(cssContent);
            }
            for (StylesheetContent cssContent : relatedCssSet) {
                if (cssContent.getAdaptedContent() == null){
                    cssContent.setAdaptedContent(CSS_ON_ERROR);
                }
            }
        }
    }

    /**
     * @see org.xml.sax.ContentHandler#endElement(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String nameSpaceURI, String localName, String rawName)
            throws SAXException {

        if (isLocalCSS && resource != null){
            resource.setResource(buffer.toString());
        }

        if ((isLocalCSS) || isInlineCSS) {
            if (resource != null) {
                cssVector.add(resource);
                // search imported resource from the resource
                getImportedResources(resource, currentLocalResourcePath);
            }
            isLocalCSS = false;
            isInlineCSS = false;
        }
    }

    /**
     * @param prefixe
     * @see org.xml.sax.ContentHandler#endPrefixMapping(java.lang.String)
     */
    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        // Not used
    }

    @Override
    public String getAdaptation() {
        if (cssOnError) {
            return (CSS_ON_ERROR);
        } else {
            return new XStream().toXML(cssSet);
        }
    }

    /**
     * @param ch
     * @param start
     * @param end
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
     * @see org.xml.sax.ContentHandler#processingInstruction(java.lang.String,
     *      java.lang.String)
     */
    @Override
    public void processingInstruction(String target, String data)
            throws SAXException {
        // Not used
    }

    /**
     * 
     * @param locator
     */
    @Override
    public void setDocumentLocator(Locator locator) {
        this.locator = locator;
    }

    /**
     * 
     * @param parser
     */
    @Override
    public void setParser(ContentParser parser) {
        if (parser instanceof CSSParser) {
            this.parser = (CSSParser) parser;
        }
    }

    /**
     * @see org.xml.sax.ContentHandler#skippedEntity(java.lang.String)
     */
    @Override
    public void skippedEntity(String arg0) throws SAXException {
        // Not used
    }

    /**
     * Event fired when the parse starts
     *
     * @throws SAXException
     * @see org.xml.sax.ContentHandler#startDocument()
     */
    @Override
    public void startDocument() throws SAXException {
        locator = new LocatorImpl();
        cssVector = new HashSet();
        currentLocalResourcePath = null;
        buffer = new StringBuffer();
        cssSet = new HashSet<CSSOMStyleSheet>();
        cssOnError = false;
        resource = null;
        relatedCssSet.clear();
        for (RelatedContent relatedContent : ssp.getRelatedContentSet()) {
            if (relatedContent instanceof StylesheetContent) {
                relatedCssSet.add((StylesheetContent) relatedContent);
            }
        }
    }

    /**
     * @param nameSpaceURI
     * @param localName
     * @param rawName
     * @throws SAXException
     * @see org.xml.sax.ContentHandler#startElement(java.lang.String,
     *      java.lang.String, java.lang.String, org.xml.sax.Attributes)
     */
    @Override
    public void startElement(String nameSpaceURI, String localName,
            String rawName, Attributes attributs) throws SAXException {

        buffer.setLength(0);

        // localCSS
        if (HtmlTags.STYLE.equalsIgnoreCase(rawName)) {
            isLocalCSS = true;
            resource = new CSSResourceImpl(null, locator.getLineNumber(),
                    new LocalRsrc());
            buffer.delete(0, buffer.capacity());
            currentLocalResourcePath = urlIdentifier.resolve(".").toExternalForm();
        }
        // externalCSS
        if (HtmlNodeAttr.LINK.equalsIgnoreCase(rawName)) {

            String rel = attributs.getValue(HtmlNodeAttr.REL);
            String type = attributs.getValue(HtmlNodeAttr.TYPE);
            //do nothing

            if ((rel != null && rel.contains("stylesheet")) ||
                    (type != null && type.contains("text/css"))) {
                // resolve the css relative path to its absolute path and add it
                // to the external css links collection
                String cssRelativePath = attributs.getValue(HtmlNodeAttr.HREF);
                if (cssRelativePath != null) {
                    currentLocalResourcePath = urlIdentifier.resolve(".").toExternalForm();
                    getExternalResourceAndAdapt(cssRelativePath,
                        getListOfMediaFromAttributeValue(attributs.getValue(HtmlNodeAttr.MEDIA)),
                        false);
                }
            }
        }

        // look up for inlineCSS
        for (int i = 0; i < attributs.getLength(); i++) {
            String attrValue = attributs.getValue(i);
            String attrName = attributs.getQName(i);
            if (HtmlNodeAttr.STYLE.equalsIgnoreCase(attrName)) {
                isInlineCSS = true;
                if (!attrValue.isEmpty()) {
                    String cssString = rawName + "{" + attrValue + "}";
                    resource = new CSSResourceImpl(cssString, locator.getLineNumber(), new InlineRsrc());
                    cssVector.add(resource);
                    currentLocalResourcePath = urlIdentifier.resolve(".").toExternalForm();
                } else {
                    resource = null;
                }
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
    @Override
    public void startPrefixMapping(String prefix, String URI)
            throws SAXException {
        // Not used
    }

    /**
     * Get the list of media from the media attribute content
     * @param mediaAttribute
     * @return
     */
    private SACMediaList getListOfMediaFromAttributeValue(String mediaAttribute) {
        if (mediaAttribute == null) {
            return null;
        } else {
            String localMedia = mediaAttribute.replaceAll("\\s", "");
            SACMediaList mediaList = new SACMediaListImpl();
            for(String media : localMedia.split(",")) {
                ((SACMediaListImpl)mediaList).addItem(media);
            }
            return mediaList;
        }
    }

    /**
     * Get the current resource path (needed for relative file access)
     * @param absolutePath
     * @return
     */
    private String getCurrentResourcePath(String absolutePath){
        int endIndex = absolutePath.lastIndexOf('/');
        String path = absolutePath.substring(0, endIndex+1);
        return path;
    }

    /**
     * Downloads an external resource and returns a Resource instance or null
     * if the download has failed
     * @param cssRelativePath
     * @param sacMediaList
     * @param importedFromCss
     * @return
     */
    private boolean getExternalResourceAndAdapt(String cssRelativePath,
            SACMediaList sacMediaList, boolean importedFromCss) {

        String cssAbsolutePath = null;
        StringBuffer rawCss = new StringBuffer();
        cssAbsolutePath = urlIdentifier.resolve(cssRelativePath).toExternalForm();

        try {
            cssAbsolutePath = UURIFactory.getInstance(cssAbsolutePath).toString();
        } catch (URIException ex) {
            Logger.getLogger(CSSContentAdapterImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (StylesheetContent stylesheetContent : relatedCssSet){
            if (stylesheetContent.getAdaptedContent()==null &&
                    stylesheetContent.getURI().contains(cssAbsolutePath) ){

                rawCss.append(stylesheetContent.getSource());
                Resource localResource = null;
                if (sacMediaList != null) {
                    localResource = new CSSResourceImpl(rawCss.toString(),
                    locator.getLineNumber(), new ExternalRsrc(),sacMediaList);
                } else {
                    localResource = new CSSResourceImpl(rawCss.toString(),
                    locator.getLineNumber(), new ExternalRsrc());
                }
//                if if(resourcePath.startsWith("/")(!importedFromCss) {
                    // In case of external resource found when parsing the html,
                    // the path of the resource is saved for imports with relative path
                currentLocalResourcePath = getCurrentResourcePath(cssAbsolutePath);
//                }
                getImportedResources(localResource, currentLocalResourcePath);
                adaptContent(stylesheetContent, localResource);
                return true;
            }
        }
        return false;
    }

    /**
     * Search and download imported resources from resources found in the html
     * Can be call recursively if an imported stylesheet is defined within an
     * imported stylesheet
     * @param resource
     * @param path
     *          The resource path
     */
    private void getImportedResources(Resource resource, String path) {
        Set<CSSImportedStyle> importedStyles =
                new HashSet<CSSImportedStyle>();
        do {
            if (resource != null) {

                //Get all the @import refences found in the resource
                parser.setResource(resource);
                importedStyles = parser.searchImportedStyles();

                if(importedStyles == null) {
                    return;
                }

                if (!importedStyles.isEmpty())
                    // for each imported resource found within an inline resource
                    for (CSSImportedStyle cssImportedStyle : importedStyles) {

                        //build the resource path
                        //If the path is relative, build in it from the path of the
                        // current resource
                        String resourcePath = cssImportedStyle.getPath();
                        if (!resourcePath.startsWith(HTTP_PREFIX) &&
                              !resourcePath.startsWith(WWW_PREFIX) &&
                              !resourcePath.startsWith("/")) {

                              resourcePath = path +
                                    cssImportedStyle.getPath();
                        }

                        // create an instance of resource and download the content
                        getExternalResourceAndAdapt(
                                resourcePath,
                                cssImportedStyle.getSACMediaList(), true);
                    }
                importedStyles.clear();
            }
        } while (!importedStyles.isEmpty());
    }

    private StylesheetContent getStylesheetFromResource(String resource){
        StylesheetContent cssContent = contentFactory.createStylesheetContent(
                new Date(),
                ssp.getURI()+URI_PREFIX+internalCssCounter,
                ssp,
                resource,
                200);
        cssContent.setAudit(ssp.getAudit());
        internalCssCounter++;
        ssp.addRelatedContent(cssContent);
        return cssContent;
    }

    private void adaptContent(StylesheetContent stylesheetContent, Resource resource){
        if (stylesheetContent.getAdaptedContent() == null &&
                resource.getResource() != null) {
            parser.setResource(resource);
            parser.run();
            if (parser.getResult() != null) {
                stylesheetContent.setAdaptedContent(new XStream().toXML(parser.getResult()));
            } else {
                stylesheetContent.setAdaptedContent(CSS_ON_ERROR);
            }
        }
    }

}

