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
package org.opens.tanaguru.contentadapter.css;

import com.thoughtworks.xstream.XStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.PersistenceException;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.archive.net.UURI;
import org.archive.net.UURIFactory;
import org.opens.tanaguru.contentadapter.ContentParser;
import org.opens.tanaguru.contentadapter.Resource;
import org.opens.tanaguru.contentadapter.js.AbstractContentAdapter;
import org.opens.tanaguru.contentadapter.util.*;
import org.opens.tanaguru.contentloader.Downloader;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.util.http.HttpRequestHandler;
import org.w3c.css.sac.SACMediaList;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.LocatorImpl;

/**
 * External resources are adapted on the fly, and inline and local css are
 * adapted at the end of the document parse.
 * 
 * @author jkowalczyk
 * @deprecated 
 */
public class CSSContentAdapterImpl extends AbstractContentAdapter implements
        CSSContentAdapter, ContentHandler {

    private static final Logger LOGGER = Logger.getLogger(CSSContentAdapterImpl.class);
    private static final String HTTP_PREFIX = "http";
    private static final String WWW_PREFIX = "www";
    private static final String FILE_PREFIX = "file:";
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
    private String baseResourcePath;
    private boolean cssOnError = false;
    private Set<StylesheetContent> relatedExternalCssSet =
            new HashSet<StylesheetContent>();
    private ExternalCSSRetriever externalCSSRetriever;
    private Set<StylesheetContent> externalCssSet =
            new HashSet<StylesheetContent>();
    private int internalCssCounter = 1;
    private boolean hasBaseTag = false;
    
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
            ContentDataService contentDataService,
            ExternalCSSRetriever externalCSSRetriever) {
        super(contentFactory, urlIdentifier, downloader, contentDataService);
        this.externalCSSRetriever = externalCSSRetriever;
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
        Set<Long> relatedCssIdSet = new HashSet<Long>();
        if (resource != null) {
            resource.addAllResource(cssVector);

            // At the end of the document, parse and get the result for each
            // inline or local resource
            for (Object object : resource.getResourceSet()) {
                Resource r = (Resource) object;
                StylesheetContent cssContent =
                        getStylesheetFromResource(r.getResource());
                adaptContent(cssContent, r);
                relatedCssIdSet.add(getContentDataService().saveOrUpdate(cssContent).getId());
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
            // to avoid fatal error when persist weird sourceCode
            try {
                cssContent = (StylesheetContent)getContentDataService().saveOrUpdate(cssContent);
                relatedCssIdSet.add(cssContent.getId());
            } catch (PersistenceException pe) {
                cssContent.setSource(CSS_ON_ERROR);
                cssContent.setAdaptedContent(CSS_ON_ERROR);
                cssContent = (StylesheetContent)getContentDataService().saveOrUpdate(cssContent);
                relatedCssIdSet.add(cssContent.getId());
                LOGGER.debug("Problem with "+ cssContent.getURI() +
                    ". Persist it with content set as on error");
            }
        }
        getContentDataService().saveContentRelationShip(getSSP(), relatedCssIdSet);
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
                getImportedResources(resource, currentLocalResourcePath, null);
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
            LOGGER.debug("Starting retrieving external stylesheet " + externalCSSRetriever.getClass() + "  " +getSSP().getURI());
            externalCssSet.addAll(externalCSSRetriever.getExternalCSS(getSSP()));
            for (StylesheetContent sc : externalCssSet) {
                LOGGER.debug("The external stylesheet " + sc.getURI() + " has been retrieved");
            }
        } else {
            for (StylesheetContent sc : externalCssSet) {
                LOGGER.debug("StartDocument : The external stylesheet " + sc.getURI() + " has been retrieved");
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
        
        if (HtmlTags.BASE.equalsIgnoreCase(rawName)) {
            for (int i = 0; i < attributs.getLength(); i++) {
                String attrValue = attributs.getValue(i);
                String attrName = attributs.getQName(i);
                if (HtmlNodeAttr.HREF.equalsIgnoreCase(attrName) && StringUtils.isNotEmpty(attrValue)) {
                    hasBaseTag = true;
                    if (!attrValue.endsWith("/")) {
                        StringBuilder strb = new StringBuilder();
                        strb.append(attrValue);
                        strb.append("/");
                        baseResourcePath = strb.toString();
                    } else {
                        baseResourcePath = attrValue;
                    }
                    LOGGER.debug("base tag found " + baseResourcePath);
                    // we test the found baseResourcePath regarding the UURI interface
                    // to check whether the URL is well-formed. If not, we consider 
                    // as the browser that the base tag has to be ignored and the
                    // base is seen as the base of the page.
                    try {
                        UURIFactory.getInstance(baseResourcePath);
                    } catch (URIException ex) {
                        LOGGER.warn("the base tag " + baseResourcePath + " is malformed. Use base of the URL instead");
                        hasBaseTag = false;
                    }
                }
            }
        }
        
        if (!hasBaseTag) {
            currentLocalResourcePath = getUrlIdentifier().resolve(".").toExternalForm();
        } else {
            currentLocalResourcePath = baseResourcePath;
        }
        
        // localCSS
        if (HtmlTags.STYLE.equalsIgnoreCase(rawName)) {
            isLocalCSS = true;
            resource = new CSSResourceImpl(null, locator.getLineNumber(),
                    new LocalRsrc());
            buffer.delete(0, buffer.capacity());
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
                String path = attributs.getValue(HtmlNodeAttr.HREF);
                if (path != null) {
                    getExternalResourceAndAdapt(path,
                            getListOfMediaFromAttributeValue(attributs.getValue(HtmlNodeAttr.MEDIA)));
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
     * @return
     */
    private boolean getExternalResourceAndAdapt(String cssRelativePath,
            SACMediaList sacMediaList) {

        StringBuilder rawCss = new StringBuilder();
        String cssAbsolutePath = null;

        try {
            cssAbsolutePath = buildPath(cssRelativePath, currentLocalResourcePath);
        } catch (URIException ex) {
            LOGGER.error(ex.getMessage());
        }
        // When an external css is found on the html, we start by getting the
        // associated resource from the fetched Stylesheet and we populate the
        // set of relatedExternalCssSet (needed to create the relation between the
        // SSP and the css at the end of the adaptation)
        StylesheetContent stylesheetContent = getExternalStylesheet(cssAbsolutePath);
        if (stylesheetContent != null) {
            if (stylesheetContent.getAdaptedContent() == null) {
                rawCss.append(stylesheetContent.getSource());
                Resource localResource;
                if (sacMediaList != null) {
                    localResource = new CSSResourceImpl(rawCss.toString(),
                            locator.getLineNumber(), new ExternalRsrc(), sacMediaList);
                } else {
                    localResource = new CSSResourceImpl(rawCss.toString(),
                            locator.getLineNumber(), new ExternalRsrc());
                }
                currentLocalResourcePath = getCurrentResourcePath(cssAbsolutePath);
                getImportedResources(localResource, currentLocalResourcePath, cssAbsolutePath);
                adaptContent(stylesheetContent, localResource);
            }
            relatedExternalCssSet.add(stylesheetContent);
            LOGGER.debug("encountered external css :  " + 
                    cssAbsolutePath  + " " +
                    relatedExternalCssSet.size() + " in " +getSSP().getURI());
            return true;
        }

        return false;
    }

    /**
     * Retrieve an external stylesheet. If it has already been encountered, 
     * return the instance, create a  new one instead.
     * @param cssAbsolutePath
     * @return 
     */
    private StylesheetContent getExternalStylesheet(String cssAbsolutePath) {
        for (StylesheetContent stylesheetContent : externalCssSet) {
            if (stylesheetContent.getURI().equals(cssAbsolutePath)) {
                return stylesheetContent;
            }
        }
        return createNewExternalStyleSheet(cssAbsolutePath);
    }
    
    /**
     * 
     * @param cssAbsolutePath
     * @return 
     */
    private StylesheetContent createNewExternalStyleSheet(String cssAbsolutePath) {
        String cssSourceCode = "";
        try {
            cssSourceCode = HttpRequestHandler.getInstance().getHttpContent(cssAbsolutePath);
        } catch (URISyntaxException ex) {
            LOGGER.debug("the resource " + cssAbsolutePath + " can't be retrieved : URISyntaxException");
            return null;
        } catch (UnknownHostException uhe) {
            LOGGER.debug("the resource " + cssAbsolutePath + " can't be retrieved : UnknownHostException");
            return null;
        } catch (IOException ioe) {
            LOGGER.debug("the resource " + cssAbsolutePath + " can't be retrieved : IOException");
            return null;
        }
        if (StringUtils.isEmpty(cssSourceCode)) {
            LOGGER.info("the resource " + cssAbsolutePath + " has an empty content");
            return null;
        }
        StylesheetContent cssContent = getContentFactory().createStylesheetContent(
                new Date(),
                cssAbsolutePath,
                getSSP(),
                cssSourceCode,
                200);
        cssContent.setAudit(getSSP().getAudit());
        externalCssSet.add(cssContent);
        // Some stylesheet may be retrieved during the adaptation. In this case
        // these new css are added "manually" to the externalCssRetriever which
        // is supposed to request the bdd once at the beginning of the adapting
        // phasis.
        externalCSSRetriever.addNewStylesheetContent(getSSP(), cssContent);
        return cssContent;
    }
    
    /**
     * Search and download imported resources from resources found in the html
     * Can be call recursively if an imported stylesheet is defined within an
     * imported stylesheet
     * @param resource
     * @param path
     *          The resource path
     */
    private void getImportedResources(Resource resource, String path, String currentResourcePath) {
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

                if (!importedStyles.isEmpty()) {// for each imported resource found within an inline resource
                    for (CSSImportedStyle cssImportedStyle : importedStyles) {
                        try {
                            String resourcePath = buildPath(cssImportedStyle.getPath(), path);
                            if (!StringUtils.equalsIgnoreCase(resourcePath, currentResourcePath)) {
                                // create an instance of resource and download the content
                                getExternalResourceAndAdapt(
                                    resourcePath,
                                    cssImportedStyle.getSACMediaList());
                            }
                        } catch (URIException ex) {
                            java.util.logging.Logger.getLogger(CSSContentAdapterImpl.class.getName()).log(Level.SEVERE, null, ex);
                        }
                   }
                }
                importedStyles.clear();
            }
        } while (!importedStyles.isEmpty());
    }

    /**
     * 
     * @param resource
     * @return 
     */
    private StylesheetContent getStylesheetFromResource(String resource) {
        StylesheetContent cssContent = getContentFactory().createStylesheetContent(
                new Date(),
                getSSP().getURI() + URI_PREFIX + internalCssCounter,
                getSSP(),
                resource,
                200);
        cssContent.setAudit(getSSP().getAudit());
        internalCssCounter++;
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
                && resource.getResource() != null && !resource.getResource().isEmpty()) {
            parser.setResource(resource);
            parser.run();
            if (parser.getResult() != null) {
                stylesheetContent.setAdaptedContent(new XStream().toXML(parser.getResult()));
            } else {
                stylesheetContent.setAdaptedContent(CSS_ON_ERROR);
                Logger.getLogger(this.getClass()).warn("An error occured while adapting " + stylesheetContent.getURI());
            }
        }
    }

    /**
     * Build the resource path. If the path is relative, build in it from the 
     * path of the current resource
     * 
     * @param path
     * @param base
     * @return 
     */
    public String buildPath(String path, String base) throws URIException {
        if (path.startsWith(HTTP_PREFIX)
                || path.startsWith(WWW_PREFIX) 
                || path.startsWith(FILE_PREFIX)) {
            return UURIFactory.getInstance(path).toString();
        }
        StringBuilder strb = new StringBuilder();
        if (path.startsWith("/")) {
            base = setBaseAsRootOfSite(base);
        }
        strb.append(base);
        if (!base.endsWith("/") && !path.startsWith("/")) {
            strb.append("/");
        }
        strb.append(path);
        return UURIFactory.getInstance(strb.toString()).toString();
    }

    /**
     * 
     * @param base
     * @return 
     */
    public String setBaseAsRootOfSite(String base) {
        UURI uri;
        try {
            uri = UURIFactory.getInstance(base);
            return uri.getScheme()+"://"+uri.getHost().toString();
        } catch (URIException ex) {
            java.util.logging.Logger.getLogger(CSSContentAdapterImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            java.util.logging.Logger.getLogger(CSSContentAdapterImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return base;
    }
    
}
