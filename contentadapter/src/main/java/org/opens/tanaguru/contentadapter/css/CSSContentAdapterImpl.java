package org.opens.tanaguru.contentadapter.css;

import com.thoughtworks.xstream.XStream;
import java.util.Date;

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.httpclient.URIException;
import org.apache.log4j.Logger;
import org.archive.net.UURIFactory;

import org.opens.tanaguru.contentadapter.ContentParser;
import org.opens.tanaguru.contentadapter.Resource;
import org.opens.tanaguru.contentadapter.js.AbstractContentAdapter;
import org.opens.tanaguru.contentadapter.util.ExternalRsrc;
import org.opens.tanaguru.contentadapter.util.HtmlNodeAttr;
import org.opens.tanaguru.contentadapter.util.HtmlTags;
import org.opens.tanaguru.contentadapter.util.InlineRsrc;
import org.opens.tanaguru.contentadapter.util.LocalRsrc;
import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.contentloader.Downloader;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
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

    private static final Logger LOGGER = Logger.getLogger(CSSContentAdapterImpl.class);
    private static final String HTTP_PREFIX = "http";
    private static final String WWW_PREFIX = "www";
    private static final String CSS_ON_ERROR = "CSS_ON_ERROR";
    private static final String URI_PREFIX = "#tanaguru-css-";
    private StringBuffer buffer;
    private Set<CSSOMStyleSheet> cssSet;
    private Set<Resource> cssVector;
    private boolean isInlineCSS = false;
    private boolean isLocalCSS = false;
    private Locator locator;
    private CSSParser parser;
    private String currentLocalResourcePath;
    private boolean cssOnError = false;
    private Set<StylesheetContent> relatedExternalCssSet =
            new HashSet<StylesheetContent>();
    private Set<StylesheetContent> externalCssSet =
            new HashSet<StylesheetContent>();
    private int internalCssCounter = 1;

    /**
     * Constructor
     * @param contentFactory
     * @param urlIdentifier
     * @param downloader
     * @param cssParser
     * @param contentDataService
     */
    public CSSContentAdapterImpl(
            ContentFactory contentFactory,
            URLIdentifier urlIdentifier,
            Downloader downloader,
            CSSParser cssParser,
            ContentDataService contentDataService) {
        super(contentFactory, urlIdentifier, downloader, contentDataService);
        this.parser = cssParser;
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
                cssContent.setAudit(getSSP().getAudit());
                getSSP().addRelatedContent(cssContent);
            }
        }
        // At the end of the document we link each external css that are
        // already fetched and that have been encountered in the SSP to the SSP.
        LOGGER.debug("Found " + relatedExternalCssSet.size() + 
                " external css in "+ getSSP().getURI());
        for (StylesheetContent cssContent : relatedExternalCssSet) {
            if (cssContent.getAdaptedContent() == null) {
                cssContent.setAdaptedContent(CSS_ON_ERROR);
            }
            LOGGER.debug("Create relation between "+getSSP().getURI() +
                    " and " + cssContent.getURI());
            getSSP().addRelatedContent(cssContent);
        }
    }

    /**
     * @see org.xml.sax.ContentHandler#endElement(java.lang.String,
     *      java.lang.String, java.lang.String)
     */
    @Override
    public void endElement(String nameSpaceURI, String localName, String rawName)
            throws SAXException {

        if (isLocalCSS && resource != null) {
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
        cssVector = new HashSet<Resource>();
        currentLocalResourcePath = null;
        buffer = new StringBuffer();
        cssSet = new HashSet<CSSOMStyleSheet>();
        cssOnError = false;
        resource = null;
        relatedExternalCssSet.clear();
        // The list of external resources for a given audit is retrieved once.
        // This list is not supposed to increase at this step.
        if (externalCssSet.isEmpty()) {
            externalCssSet.addAll(getContentDataService().getExternalStylesheetFromAudit(getSSP().getAudit()));
            for (StylesheetContent sc : externalCssSet) {
                LOGGER.debug("The external stylesheet " + sc.getURI() + " has been retrieved");
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
            currentLocalResourcePath = getUrlIdentifier().resolve(".").toExternalForm();
        }
        // externalCSS
        if (HtmlNodeAttr.LINK.equalsIgnoreCase(rawName)) {

            String rel = attributs.getValue(HtmlNodeAttr.REL);
            String type = attributs.getValue(HtmlNodeAttr.TYPE);
            //do nothing

            if ((rel != null && rel.contains("stylesheet"))
                    || (type != null && type.contains("text/css"))) {
                // resolve the css relative path to its absolute path and add it
                // to the external css links collection
                String cssRelativePath = attributs.getValue(HtmlNodeAttr.HREF);
                if (cssRelativePath != null) {
                    currentLocalResourcePath = getUrlIdentifier().resolve(".").toExternalForm();
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
                    currentLocalResourcePath = getUrlIdentifier().resolve(".").toExternalForm();
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
            for (String media : localMedia.split(",")) {
                ((SACMediaListImpl) mediaList).addItem(media);
            }
            return mediaList;
        }
    }

    /**
     * Get the current resource path (needed for relative file access)
     * @param absolutePath
     * @return
     */
    private String getCurrentResourcePath(String absolutePath) {
        int endIndex = absolutePath.lastIndexOf('/');
        String path = absolutePath.substring(0, endIndex + 1);
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
        StringBuilder rawCss = new StringBuilder();
        cssAbsolutePath = getUrlIdentifier().resolve(cssRelativePath).toExternalForm();

        try {
            cssAbsolutePath = UURIFactory.getInstance(cssAbsolutePath).toString();
        } catch (URIException ex) {
            LOGGER.error(ex.getMessage());
        }
        // When an external css is found on the html, we start by getting the
        // associated resource from the fetched Stylesheet and we populate the
        // set of relatedExternalCssSet (needed to create the relation between the
        // SSP and the css at the end of the adaptation)
        for (StylesheetContent stylesheetContent : externalCssSet) {
            if (stylesheetContent.getURI().contains(cssAbsolutePath)) {
                if (stylesheetContent.getAdaptedContent() == null) {
                    rawCss.append(stylesheetContent.getSource());
                    Resource localResource = null;
                    if (sacMediaList != null) {
                        localResource = new CSSResourceImpl(rawCss.toString(),
                                locator.getLineNumber(), new ExternalRsrc(), sacMediaList);
                    } else {
                        localResource = new CSSResourceImpl(rawCss.toString(),
                                locator.getLineNumber(), new ExternalRsrc());
                    }
                    currentLocalResourcePath = getCurrentResourcePath(cssAbsolutePath);
                    getImportedResources(localResource, currentLocalResourcePath);
                    adaptContent(stylesheetContent, localResource);
                }
                relatedExternalCssSet.add(stylesheetContent);
                LOGGER.debug("encountered external css :  " + 
                        cssAbsolutePath  + " " +
                        relatedExternalCssSet.size() + " in " +getSSP().getURI());
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

                if (importedStyles == null) {
                    return;
                }

                if (!importedStyles.isEmpty()) // for each imported resource found within an inline resource
                {
                    for (CSSImportedStyle cssImportedStyle : importedStyles) {

                        //build the resource path
                        //If the path is relative, build in it from the path of the
                        // current resource
                        String resourcePath = cssImportedStyle.getPath();
                        if (!resourcePath.startsWith(HTTP_PREFIX)
                                && !resourcePath.startsWith(WWW_PREFIX)
                                && !resourcePath.startsWith("/")) {

                            resourcePath = path
                                    + cssImportedStyle.getPath();
                        }

                        // create an instance of resource and download the content
                        getExternalResourceAndAdapt(
                                resourcePath,
                                cssImportedStyle.getSACMediaList(), true);
                    }
                }
                importedStyles.clear();
            }
        } while (!importedStyles.isEmpty());
    }

    private StylesheetContent getStylesheetFromResource(String resource) {
        StylesheetContent cssContent = getContentFactory().createStylesheetContent(
                new Date(),
                getSSP().getURI() + URI_PREFIX + internalCssCounter,
                getSSP(),
                resource,
                200);
        cssContent.setAudit(getSSP().getAudit());
        internalCssCounter++;
        getSSP().addRelatedContent(cssContent);
        return cssContent;
    }

    /**
     * For shared css, the adaptation is only made the first time the css is
     * encountered.
     * 
     * @param stylesheetContent
     * @param resource
     */
    private void adaptContent(StylesheetContent stylesheetContent, Resource resource) {
        if (stylesheetContent.getAdaptedContent() == null
                && resource.getResource() != null) {
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