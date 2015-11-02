/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.contentadapter.css;

import com.phloc.commons.charset.CCharset;
import com.phloc.commons.io.streamprovider.ByteArrayInputStreamProvider;
import com.phloc.commons.url.URLUtils;
import com.phloc.css.ECSSVersion;
import com.phloc.css.decl.CSSImportRule;
import com.phloc.css.decl.CSSMediaQuery;
import com.phloc.css.decl.CascadingStyleSheet;
import com.phloc.css.parser.TokenMgrError;
import com.phloc.css.reader.CSSReader;
import com.phloc.css.tools.MediaQueryTools;
import com.thoughtworks.xstream.XStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.*;
import javax.annotation.Nullable;
import javax.persistence.PersistenceException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.DataException;
import org.jsoup.nodes.Element;
import org.tanaguru.contentadapter.ContentParser;
import org.tanaguru.contentadapter.Resource;
import org.tanaguru.contentadapter.js.AbstractContentAdapter;
import org.tanaguru.contentadapter.util.ExternalRsrc;
import org.tanaguru.contentadapter.util.InlineRsrc;
import org.tanaguru.contentadapter.util.LocalRsrc;
import org.tanaguru.contentadapter.util.URLIdentifier;
import org.tanaguru.contentloader.Downloader;
import org.tanaguru.entity.audit.StylesheetContent;
import org.tanaguru.entity.service.audit.ContentDataService;
import org.tanaguru.util.http.HttpRequestHandler;

/**
 * External resources are adapted on the fly, and inline and local css are
 * adapted at the end of the document parse.
 * 
 * @author jkowalczyk
 */
public class CSSJsoupPhlocContentAdapterImpl extends AbstractContentAdapter implements
        CSSContentAdapter {

    private static final Logger LOGGER = Logger.getLogger(CSSJsoupPhlocContentAdapterImpl.class);
    private static final String HTTP_PREFIX = "http";
    
    private String currentLocalResourcePath;
    private final Set<StylesheetContent> relatedExternalCssSet = new HashSet<>();
    private final ExternalCSSRetriever externalCSSRetriever;
    private final Set<StylesheetContent> externalCssSet = new HashSet<>();

    private int inlineCssCounter;
    private int localeCssCounter;
    
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
    
    private final URLIdentifier urlIdentifier;
    
    /**
     * Constructor
     * @param urlIdentifier
     * @param downloader
     * @param contentDataService
     * @param externalCSSRetriever
     */
    public CSSJsoupPhlocContentAdapterImpl(
            URLIdentifier urlIdentifier,
            Downloader downloader,
            ContentDataService contentDataService,
            ExternalCSSRetriever externalCSSRetriever) {
        super(urlIdentifier, downloader, contentDataService);
        this.externalCSSRetriever = externalCSSRetriever;
        this.urlIdentifier = urlIdentifier;
    }

    /**
     * 
     * @param parser
     */
    @Override
    public void setParser(ContentParser parser) {}
    
    /**
     * 
     */
    public void adaptContent() {
        LOGGER.debug("initContext()");
        initContext();
        LOGGER.debug("adaptInlineCSS()");
        adaptInlineCSS();
        LOGGER.debug("adaptLocaleCSS()");
        adaptLocaleCSS();
        LOGGER.debug("adaptExternalCss()");
        adaptExternalCss();
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
    
    /**
     * Retrieve css content and adapt it for each locale resource 
     */
    private void adaptLocaleCSS() {
        Set<Long> relatedCssIdSet = new HashSet<>();

        for (Element el : localeCssElements) {
            Resource cssResource;
            String rawCss = el.data();
            if (!StringUtils.isBlank(rawCss)) {
                cssResource = new CSSResourceImpl(
                        rawCss, 
                        0, 
                        new LocalRsrc());
                StylesheetContent cssContent =
                        getStylesheetFromLocaleResource(cssResource.getResource());
                adaptContent(
                        cssContent, 
                        cssResource, 
                        getCurrentResourcePath(el.baseUri()), 
                        getListOfMediaFromAttributeValue(el));
                relatedCssIdSet.add(getContentDataService().saveOrUpdate(cssContent).getId());
            }
        }
        getContentDataService().saveContentRelationShip(getSSP(), relatedCssIdSet);
    }
    
    /**
     * Retrieve css content and adapt it for each inline resource
     */
    private void adaptInlineCSS() {
        Set<Long> relatedCssIdSet = new HashSet<>();

        for (Element el : inlineCssElements) {
            String attributeValue = el.attr("style");
            if (StringUtils.isNotBlank(attributeValue)) {
                Resource cssResource = new CSSResourceImpl(
                        el.nodeName()+"{"+attributeValue +"}", 
                        0, 
                        new InlineRsrc());
                StylesheetContent cssContent =
                        getStylesheetFromInlineResource(cssResource.getResource());
                adaptContent(cssContent, cssResource, getCurrentResourcePath(el.baseUri()), null);
                relatedCssIdSet.add(getContentDataService().saveOrUpdate(cssContent).getId());
            }
        }
        getContentDataService().saveContentRelationShip(getSSP(), relatedCssIdSet);
    }
    
    /**
     * Adapt the external css. 
     */
    private void adaptExternalCss() {
        for (Element el : externalCssElements) {
            List<CSSMediaQuery> mediaList = getListOfMediaFromAttributeValue(el);
            String resourcePath = el.attr("abs:href");
            getExternalResourceAndAdapt(resourcePath, mediaList);
        }
        Set<Long> relatedCssIdSet = new HashSet<>();
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
                // the content is saved only when the id is null which means 
                // that the content hasn't been persisted yet. Otherwise, the
                // save is uneeded and the id is used to create the relation 
                // with the current SSP
                if (cssContent.getId() == null) {
                    cssContent = (StylesheetContent)getContentDataService().saveOrUpdate(cssContent);
                }
                relatedCssIdSet.add(cssContent.getId());
            } catch (PersistenceException | DataException pe) {
                adaptedContentOnError(cssContent, relatedCssIdSet);
            }
        }
        getContentDataService().saveContentRelationShip(getSSP(), relatedCssIdSet);
    }
    
    /**
     * Get the list of media from the media attribute content
     * @param mediaAttribute
     * @return
     */
    private List<CSSMediaQuery> getListOfMediaFromAttributeValue(Element element) {
        String mediaAttribute = element.attr("media");
        List<CSSMediaQuery> mediaTypeList = new ArrayList<>();
        if (mediaAttribute == null || StringUtils.isBlank(mediaAttribute) ) {
            return mediaTypeList;
        } else {
            mediaTypeList.addAll(MediaQueryTools.parseToMediaQuery (
                    mediaAttribute, 
                    CCharset.CHARSET_UTF_8_OBJ, 
                    ECSSVersion.CSS30));
        }
        return mediaTypeList;
    }

    /**
     * Downloads an external resource and returns a Resource instance or null
     * if the download has failed
     * @param path
     * @param mediaAttributeValue
     * @return
     */
    private boolean getExternalResourceAndAdapt(
            String path, 
            @Nullable List<CSSMediaQuery> mediaList) {
        if (StringUtils.isBlank(path)) {
            return false;
        }
        // When an external css is found on the html, we start by getting the
        // associated resource from the fetched Stylesheet and we populate the
        // set of relatedExternalCssSet (needed to create the relation between the
        // SSP and the css at the end of the adaptation)
        StylesheetContent stylesheetContent = getExternalStylesheet(path);
        if (stylesheetContent != null) {
            if (stylesheetContent.getAdaptedContent() == null) {
                Resource localResource;
                localResource = new CSSResourceImpl(
                            stylesheetContent.getSource(),
                            0, 
                            new ExternalRsrc());
                currentLocalResourcePath = getCurrentResourcePath(path);
                adaptContent(
                        stylesheetContent, 
                        localResource, 
                        currentLocalResourcePath,
                        mediaList);
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
        cssAbsolutePath = urlIdentifier.resolve(cssAbsolutePath).toExternalForm();
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
        LOGGER.debug("createNewExternalStyleSheet "  +cssAbsolutePath);
        String cssSourceCode;
        try {
            cssSourceCode = HttpRequestHandler.getInstance().getHttpContent(cssAbsolutePath);
        } catch (URISyntaxException ex) {
            LOGGER.debug("the resource " + cssAbsolutePath + " can't be retrieved : URISyntaxException");
            cssSourceCode = CSS_ON_ERROR;
        } catch (UnknownHostException uhe) {
            LOGGER.debug("the resource " + cssAbsolutePath + " can't be retrieved : UnknownHostException");
            cssSourceCode = CSS_ON_ERROR;
        } catch (IOException ioe) {
            LOGGER.debug("the resource " + cssAbsolutePath + " can't be retrieved : IOException");
            try {
                cssSourceCode = FileUtils.readFileToString(new File(cssAbsolutePath));
            } catch (IOException ioe2) {
                LOGGER.debug("the resource " + cssAbsolutePath + " can't be retrieved : IOException");
                cssSourceCode = CSS_ON_ERROR;
            }
        } catch (IllegalCharsetNameException icne) {
            LOGGER.debug("the resource " + cssAbsolutePath + " can't be retrieved : IllegalCharsetNameException");
            cssSourceCode = CSS_ON_ERROR;
        } catch (IllegalStateException ise) {
            LOGGER.debug("the resource " + cssAbsolutePath + " can't be retrieved : IllegalStateException");
            cssSourceCode = CSS_ON_ERROR;
        }
        if (StringUtils.isBlank(cssSourceCode)) {
            LOGGER.debug("the resource " + cssAbsolutePath + " has an empty content");
            cssSourceCode = CSS_ON_ERROR;
        }

        StylesheetContent cssContent = getContentDataService().getStylesheetContent(
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
    private void getImportedResources(CSSImportRule cssImportRule, String currentLocalResourcePath) {
        String resourcePath = cssImportRule.getLocation().getURI();
        if (resourcePath.startsWith("/") || !resourcePath.startsWith(HTTP_PREFIX)) {
            resourcePath = currentLocalResourcePath + resourcePath;
        }
        getExternalResourceAndAdapt(resourcePath, cssImportRule.getAllMediaQueries());
    }

    /**
     * 
     * @param resource
     * @return 
     */
    private StylesheetContent getStylesheetFromLocaleResource(String resource) {
        localeCssCounter++;
        StylesheetContent cssContent = getContentDataService().getStylesheetContent(
                new Date(),
                getSSP().getURI() + LOCALE_CSS_PREFIX + localeCssCounter,
                getSSP(),
                resource,
                200);
        cssContent.setAudit(getSSP().getAudit());
        return cssContent;
    }
    
    /**
     * 
     * @param resource
     * @return 
     */
    private StylesheetContent getStylesheetFromInlineResource(String resource) {
        inlineCssCounter++;
        StylesheetContent cssContent = getContentDataService().getStylesheetContent(
                new Date(),
                getSSP().getURI() + INLINE_CSS_PREFIX + inlineCssCounter,
                getSSP(),
                resource,
                200);
        cssContent.setAudit(getSSP().getAudit());
        return cssContent;
    }

    /**
     * For shared css, the adaptation is only made the first time the css is
     * encountered.
     * 
     * @param stylesheetContent
     * @param resource
     * @param currentLocalResourcePath
     * @param mediaAttributeValue
     * 
     */
    private void adaptContent(
            StylesheetContent stylesheetContent, 
            Resource resource, 
            String currentLocalResourcePath, 
            @Nullable List<CSSMediaQuery> mediaList) {
        if (stylesheetContent.getAdaptedContent() == null
                && resource.getResource() != null && StringUtils.isNotBlank(resource.getResource())) {
            Charset charset = null;
            try {
                charset = CSSReader.getCharsetDeclaredInCSS(new ByteArrayInputStreamProvider(resource.getResource().getBytes()));
                LOGGER.debug("is css valid CSS2 " + 
                        CSSReader.isValidCSS(resource.getResource(), ECSSVersion.CSS21) + " " + 
                        stylesheetContent.getURI());
                LOGGER.debug("is css valid CSS3 " + 
                        CSSReader.isValidCSS(resource.getResource(), ECSSVersion.CSS30) + " " + 
                        stylesheetContent.getURI());
            } catch (TokenMgrError tme) {
                LOGGER.debug(resource.getResource() +" is on error, so invalid"); 
                LOGGER.debug(tme.getMessage()); 
            }
            if (charset == null) {
                charset = Charset.forName("utf-8");
            }
            try {
                CascadingStyleSheet aCSS = CSSReader.readFromString (
                        resource.getResource(), 
                        charset, 
                        ECSSVersion.CSS30,
                        new CSSParserExceptionHandlerImpl(stylesheetContent));
                // if an exception has been caught, the adapted content attribute 
                // has been set to "on error" and so is not null
                if (stylesheetContent.getAdaptedContent() == null) {
                    if (CollectionUtils.isNotEmpty(mediaList) && MediaQueryTools.canWrapInMediaQuery(aCSS)) {
                        if (MediaQueryTools.canWrapInMediaQuery(aCSS)) {
                            aCSS = MediaQueryTools.getWrappedInMediaQuery(aCSS,mediaList);
                        } else {
                            LOGGER.warn(stylesheetContent.getURI() + " should be"
                                    + "wrapped into "+mediaList + " but it "
                                    + "is impossible");
                        }
                    }
                    
                    stylesheetContent.setAdaptedContent(new XStream().toXML(aCSS));
                    
                    if (aCSS.hasImportRules()) {
                        for (CSSImportRule cssImportRule : aCSS.getAllImportRules()) {
                            getImportedResources(cssImportRule, currentLocalResourcePath);
                        }
                    }
                } 
            } catch (IllegalArgumentException | TokenMgrError iae) {
                stylesheetContent.setAdaptedContent(CSS_ON_ERROR);
            }
        }
    }

    /**
     * 
     * @param cssContent
     * @param relatedCssIdSet 
     */
    private void adaptedContentOnError(StylesheetContent cssContent, Set<Long> relatedCssIdSet) {
        cssContent.setSource(CSS_ON_ERROR);
        cssContent.setAdaptedContent(CSS_ON_ERROR);
        cssContent = (StylesheetContent)getContentDataService().saveOrUpdate(cssContent);
        relatedCssIdSet.add(cssContent.getId());
        LOGGER.info("Problem with "+ cssContent.getURI() +
            ". Persist it with content set as on error");
    }
    
    /**
     * 
     * @param base
     * @return 
     */
    public String setBaseAsRootOfSite(String base) {
        try {
            URI uri = URLUtils.getAsURI(base);
            return uri.getScheme()+"://"+uri.getHost();
        } catch (NullPointerException ex) {
            LOGGER.error(ex);
        }
        return base;
    }
    
}