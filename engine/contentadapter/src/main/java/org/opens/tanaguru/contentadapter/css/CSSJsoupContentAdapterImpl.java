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
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.PersistenceException;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.archive.net.UURI;
import org.archive.net.UURIFactory;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.contentadapter.ContentParser;
import org.opens.tanaguru.contentadapter.Resource;
import org.opens.tanaguru.contentadapter.js.AbstractContentAdapter;
import org.opens.tanaguru.contentadapter.util.ExternalRsrc;
import org.opens.tanaguru.contentadapter.util.InlineRsrc;
import org.opens.tanaguru.contentadapter.util.LocalRsrc;
import org.opens.tanaguru.contentadapter.util.URLIdentifier;
import org.opens.tanaguru.contentloader.Downloader;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.util.http.HttpRequestHandler;
import org.w3c.css.sac.SACMediaList;

/**
 * External resources are adapted on the fly, and inline and local css are
 * adapted at the end of the document parse.
 * 
 * @author jkowalczyk
 * @deprecated 
 */
public class CSSJsoupContentAdapterImpl extends AbstractContentAdapter implements
        CSSContentAdapter{

    private static final Logger LOGGER = Logger.getLogger(CSSJsoupContentAdapterImpl.class);
    private static final String HTTP_PREFIX = "http";
    private static final String WWW_PREFIX = "www";
    private static final String FILE_PREFIX = "file:";
    private static final String CSS_ON_ERROR = "CSS_ON_ERROR";
    private static final String URI_PREFIX = "#tanaguru-css-";
    private Set<CSSOMStyleSheet> cssSet;
    private CSSParser parser;
    private String currentLocalResourcePath;
    private boolean cssOnError = false;
    private Set<StylesheetContent> relatedExternalCssSet =
            new HashSet<StylesheetContent>();
    private ExternalCSSRetriever externalCSSRetriever;
    private Set<StylesheetContent> externalCssSet =
            new HashSet<StylesheetContent>();
    private int internalCssCounter = 1;
    
    private Collection<Element> inlineCssElements;
    private Collection<Element> localeCssElements;
    private Collection<Element> externalCssElements;
    
    public void setExternalCssElements(Collection<Element> externalCssElements) {
        this.externalCssElements = externalCssElements;
    }

    public void setInlineCssElements(Collection<Element> inlineCssElements) {
        this.inlineCssElements = inlineCssElements;
    }

    public void setLocaleCssElements(Collection<Element> localeCssElements) {
        this.localeCssElements = localeCssElements;
    }
    
    /**
     * Constructor
     * @param contentFactory
     * @param urlIdentifier
     * @param downloader
     * @param cssParser
     * @param contentDataService
     */
    public CSSJsoupContentAdapterImpl(
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

    public void adaptContent() {
        initContext();
        adaptInlineCSS();
        adaptLocaleCSS();
        adaptExternalCss();
    }

    /**
     * Retrieve css content and adapt it for each locale resource 
     */
    private void adaptLocaleCSS() {
        Set<Long> relatedCssIdSet = new HashSet<Long>();

        for (Element el : localeCssElements) {
            SACMediaList sacMediaList = getListOfMediaFromAttributeValue(el.attr("media"));
            CSSResource cssResource;
            if (!StringUtils.isEmpty(StringUtils.trim(el.data()))) {
                if (sacMediaList != null) {
                    cssResource = new CSSResourceImpl(
                        el.data(), 
                        0, 
                        new LocalRsrc(), 
                        sacMediaList);
                } else {
                    cssResource = new CSSResourceImpl(
                        el.data(), 
                        0, 
                        new LocalRsrc());
                }
                getImportedResources(cssResource, el.baseUri(), null);
                StylesheetContent cssContent =
                        getStylesheetFromResource(cssResource.getResource());
                adaptContent(cssContent, cssResource);
                relatedCssIdSet.add(getContentDataService().saveOrUpdate(cssContent).getId());
            }
        }
        getContentDataService().saveContentRelationShip(getSSP(), relatedCssIdSet);
    }
    
    /**
     * Retrieve css content and adapt it for each inline resource
     */
    private void adaptInlineCSS() {
        Set<Long> relatedCssIdSet = new HashSet<Long>();

        for (Element el : inlineCssElements) {
            String attributeValue = el.attr("style");
            if (StringUtils.isEmpty(StringUtils.trim(attributeValue))) {
                CSSResource cssResource = new CSSResourceImpl(
                        el.nodeName()+"{"+attributeValue +"}", 
                        0, 
                        new InlineRsrc());
                StylesheetContent cssContent =
                        getStylesheetFromResource(cssResource.getResource());
                adaptContent(cssContent, cssResource);
                relatedCssIdSet.add(getContentDataService().saveOrUpdate(cssContent).getId());
            }
        }
        getContentDataService().saveContentRelationShip(getSSP(), relatedCssIdSet);
    }
    
    private void adaptExternalCss() {
        for (Element el : externalCssElements) {
            SACMediaList sacMediaList = getListOfMediaFromAttributeValue(el.attr("media"));
            String resourcePath = el.attr("abs:href");
            getExternalResourceAndAdapt(resourcePath, sacMediaList);
        }
        Set<Long> relatedCssIdSet = new HashSet<Long>();
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
    
    public void initContext() {
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
    
    @Override
    public String getAdaptation() {
        if (cssOnError) {
            return (CSS_ON_ERROR);
        } else {
            return new XStream().toXML(cssSet);
        }
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
     * Get the list of media from the media attribute content
     * @param mediaAttribute
     * @return
     */
    private SACMediaList getListOfMediaFromAttributeValue(String mediaAttribute) {
        if (mediaAttribute == null || StringUtils.isEmpty(StringUtils.trim(mediaAttribute)) ) {
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
     * Downloads an external resource and returns a Resource instance or null
     * if the download has failed
     * @param cssRelativePath
     * @param sacMediaList
     * @return
     */
    private boolean getExternalResourceAndAdapt(String path, SACMediaList sacMediaList) {

//        StringBuilder rawCss = new StringBuilder();

        // When an external css is found on the html, we start by getting the
        // associated resource from the fetched Stylesheet and we populate the
        // set of relatedExternalCssSet (needed to create the relation between the
        // SSP and the css at the end of the adaptation)
        StylesheetContent stylesheetContent = getExternalStylesheet(path);
        if (stylesheetContent != null) {
            if (stylesheetContent.getAdaptedContent() == null) {
//                rawCss.append(stylesheetContent.getSource());
                Resource localResource;
                if (sacMediaList != null) {
                    localResource = new CSSResourceImpl(
                            stylesheetContent.getSource(),
                            0, 
                            new ExternalRsrc(), 
                            sacMediaList);
                } else { 
                    localResource = new CSSResourceImpl(
                            stylesheetContent.getSource(),
                            0, 
                            new ExternalRsrc());
                }
                currentLocalResourcePath = getCurrentResourcePath(path);
                getImportedResources(localResource, currentLocalResourcePath, path);
                adaptContent(stylesheetContent, localResource);
            }
            relatedExternalCssSet.add(stylesheetContent);
            LOGGER.debug("encountered external css :  " + 
                    path  + " " +
                    relatedExternalCssSet.size() + " in " +getSSP().getURI());
            return true;
        }

        return false;
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
        System.out.println("qd est ce que je crée une nouvelle? " + cssAbsolutePath);
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
            LOGGER.info("the resource " + cssAbsolutePath + " can't be retrieved");
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
                    System.out.println("---------------------------------------");
                    System.out.println("des imported non nulles dans " + path);
                    for (CSSImportedStyle cssImportedStyle : importedStyles) {
                        try {
                            String resourcePath = buildPath(cssImportedStyle.getPath(), path);
                            System.out.println("resourcePath " + resourcePath);
                            if (!StringUtils.equalsIgnoreCase(resourcePath, currentResourcePath)) {
                                // create an instance of resource and download the content
                                getExternalResourceAndAdapt(
                                    resourcePath,
                                    cssImportedStyle.getSACMediaList());
                            }
                        } catch (URIException ex) {
                            java.util.logging.Logger.getLogger(CSSJsoupContentAdapterImpl.class.getName()).log(Level.SEVERE, null, ex);
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
        System.out.println("est ce que j'adapte des trucs? " +stylesheetContent.getURI() + " " + resource.getResource());
        if (stylesheetContent.getAdaptedContent() == null
                && resource.getResource() != null && !resource.getResource().trim().isEmpty()) {
            parser.setResource(resource);
            parser.run();
            if (parser.getResult() != null) {
                Logger.getLogger(this.getClass()).debug("Adapting result for " + stylesheetContent.getURI()   + " is " + new XStream().toXML(parser.getResult()));
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
            java.util.logging.Logger.getLogger(CSSJsoupContentAdapterImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            java.util.logging.Logger.getLogger(CSSJsoupContentAdapterImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return base;
    }
    
}