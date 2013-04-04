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

import com.phloc.commons.io.streamprovider.ByteArrayInputStreamProvider;
import com.phloc.css.ECSSVersion;
import com.phloc.css.decl.CSSImportRule;
import com.phloc.css.decl.CSSMediaQuery;
import com.phloc.css.decl.CascadingStyleSheet;
import com.phloc.css.handler.ICSSParseExceptionHandler;
import com.phloc.css.parser.ParseException;
import com.phloc.css.parser.TokenMgrError;
import com.phloc.css.reader.CSSReader;
import com.thoughtworks.xstream.XStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.*;
import java.util.logging.Level;
import javax.persistence.PersistenceException;
import org.apache.commons.collections.CollectionUtils;
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

/**
 * External resources are adapted on the fly, and inline and local css are
 * adapted at the end of the document parse.
 * 
 * @author jkowalczyk
 */
public class CSSJsoupPhlocContentAdapterImpl extends AbstractContentAdapter implements
        CSSContentAdapter{

    private static final Logger LOGGER = Logger.getLogger(CSSJsoupPhlocContentAdapterImpl.class);
    private static final String HTTP_PREFIX = "http";
    private static final String WWW_PREFIX = "www";
    private static final String FILE_PREFIX = "file:";
    private static final String CSS_ON_ERROR = "CSS_ON_ERROR";
    private static final String URI_PREFIX = "#tanaguru-css-";
    private Set<CSSOMStyleSheet> cssSet;
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
     * @param contentDataService
     */
    public CSSJsoupPhlocContentAdapterImpl(
            ContentFactory contentFactory,
            URLIdentifier urlIdentifier,
            Downloader downloader,
            ContentDataService contentDataService,
            ExternalCSSRetriever externalCSSRetriever) {
        super(contentFactory, urlIdentifier, downloader, contentDataService);
        this.externalCSSRetriever = externalCSSRetriever;
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
    public void setParser(ContentParser parser) {}
    
    /**
     * 
     */
    public void adaptContent() {
        initContext();
        adaptInlineCSS();
        adaptLocaleCSS();
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
        Set<Long> relatedCssIdSet = new HashSet<Long>();

        for (Element el : localeCssElements) {
            List<String> sacMediaList = getListOfMediaFromAttributeValue(el.attr("media"));
            CSSResource cssResource;
            String rawCss = addMediaToRawCss(el.data(), sacMediaList);
            if (!StringUtils.isEmpty(StringUtils.trim(rawCss))) {
                cssResource = new CSSResourceImpl(
                        rawCss, 
                        0, 
                        new LocalRsrc());
                StylesheetContent cssContent =
                        getStylesheetFromResource(cssResource.getResource());
                adaptContent(cssContent, cssResource, getCurrentResourcePath(el.baseUri()));
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
                adaptContent(cssContent, cssResource, getCurrentResourcePath(el.baseUri()));
                relatedCssIdSet.add(getContentDataService().saveOrUpdate(cssContent).getId());
            }
        }
        getContentDataService().saveContentRelationShip(getSSP(), relatedCssIdSet);
    }
    
    private void adaptExternalCss() {
        for (Element el : externalCssElements) {
            List<String> sacMediaList = getListOfMediaFromAttributeValue(el.attr("media"));
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
    
    /**
     * Get the list of media from the media attribute content
     * @param mediaAttribute
     * @return
     */
    private List<String> getListOfMediaFromAttributeValue(String mediaAttribute) {
        List<String> mediaTypeList = new ArrayList<String>();
        if (mediaAttribute == null || StringUtils.isEmpty(StringUtils.trim(mediaAttribute)) ) {
            return mediaTypeList;
        } else {
            String localMedia = mediaAttribute.replaceAll("\\s", "");
            CollectionUtils.addAll(mediaTypeList, localMedia.split(","));
            return mediaTypeList;
        }
    }

    /**
     * Downloads an external resource and returns a Resource instance or null
     * if the download has failed
     * @param cssRelativePath
     * @param sacMediaList
     * @return
     */
    private boolean getExternalResourceAndAdapt(String path,
            List<String> sacMediaList) {
        if (StringUtils.isEmpty(path.trim())) {
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
                            addMediaToRawCss(stylesheetContent.getSource(), sacMediaList),
                            0, 
                            new ExternalRsrc());
                currentLocalResourcePath = getCurrentResourcePath(path);
                adaptContent(stylesheetContent, localResource, currentLocalResourcePath);
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
            cssSourceCode = CSS_ON_ERROR;
        } catch (UnknownHostException uhe) {
            LOGGER.debug("the resource " + cssAbsolutePath + " can't be retrieved : UnknownHostException");
            cssSourceCode = CSS_ON_ERROR;
        } catch (IOException ioe) {
            LOGGER.debug("the resource " + cssAbsolutePath + " can't be retrieved : IOException");
            cssSourceCode = CSS_ON_ERROR;
        } catch (IllegalCharsetNameException icne) {
            LOGGER.debug("the resource " + cssAbsolutePath + " can't be retrieved : IllegalCharsetNameException");
            cssSourceCode = CSS_ON_ERROR;
        }
        if (StringUtils.isEmpty(cssSourceCode)) {
            LOGGER.debug("the resource " + cssAbsolutePath + " has an empty content");
            cssSourceCode = CSS_ON_ERROR;
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
    private void getImportedResources(CSSImportRule cssImportRule, String currentLocalResourcePath) {
        List<String> sacMediaList = new ArrayList<String>();
        for (CSSMediaQuery md : cssImportRule.getAllMediaQueries()) {
            sacMediaList.add(md.getMedium());
        }
        String resourcePath = cssImportRule.getLocation().getURI();
        if (resourcePath.startsWith("/") || !resourcePath.startsWith(HTTP_PREFIX)) {
            resourcePath = currentLocalResourcePath + resourcePath;
        }
        getExternalResourceAndAdapt(resourcePath, sacMediaList);
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
    private void adaptContent(StylesheetContent stylesheetContent, Resource resource, String currentLocalResourcePath) {
        if (stylesheetContent.getAdaptedContent() == null
                && resource.getResource() != null && !resource.getResource().trim().isEmpty()) {
            Charset charset = null;
            try {
                charset = CSSReader.getCharsetDeclaredInCSS(new ByteArrayInputStreamProvider(resource.getResource().getBytes()));
                Logger.getLogger(this.getClass()).debug("is css valid CSS2 " + 
                        CSSReader.isValidCSS(resource.getResource(), ECSSVersion.CSS21) + " " + 
                        stylesheetContent.getURI());
                Logger.getLogger(this.getClass()).debug("is css valid CSS3 " + 
                        CSSReader.isValidCSS(resource.getResource(), ECSSVersion.CSS30) + " " + 
                        stylesheetContent.getURI());
            } catch (TokenMgrError tme) {
                Logger.getLogger(this.getClass()).debug(resource.getResource() +" is on error, so invalid"); 
                Logger.getLogger(this.getClass()).debug(tme.getMessage()); 
            }
            if (charset == null) {
                charset = Charset.forName("utf-8");
            }
            try {
                CascadingStyleSheet aCSS = CSSReader.readFromString (
                        resource.getResource(), 
                        charset, 
                        ECSSVersion.CSS30,
                        new CSSParseExceptionHandlerImpl(stylesheetContent));
                // if an exception has been caught, the adapted content attribute 
                // has been set to "on error" and so is not null
                if (stylesheetContent.getAdaptedContent() == null) {
                    stylesheetContent.setAdaptedContent(new XStream().toXML(aCSS));
                    for (CSSImportRule cssImportRule : aCSS.getAllImportRules()) {
                        getImportedResources(cssImportRule, currentLocalResourcePath);
                    }
                } 
            } catch (IllegalArgumentException iae) {
                stylesheetContent.setAdaptedContent(CSS_ON_ERROR);
            } catch (TokenMgrError tme) {
                stylesheetContent.setAdaptedContent(CSS_ON_ERROR);
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
            java.util.logging.Logger.getLogger(CSSJsoupPhlocContentAdapterImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex) {
            java.util.logging.Logger.getLogger(CSSJsoupPhlocContentAdapterImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return base;
    }
    
    /**
     * The media value is added as inline media specification as the media cannot
     * be set a priori and add a posteriori
     * 
     * @param css
     * @param mediaList
     * @return 
     */
    private String addMediaToRawCss(String css, List<String> mediaList) {
        // remove the "all" media, in this case, all rules are considered as
        // normal rules
        Iterator<String> iter = mediaList.iterator();
        while (iter.hasNext()) {
            if (StringUtils.equalsIgnoreCase(iter.next().trim(), "all")) {
                iter.remove();
            }
        }
        if (mediaList.isEmpty()) {
            return css;
        }
        StringBuilder strb = new StringBuilder();
        Iterator<String> mediaIter = mediaList.iterator();
        strb.append("@media ");
        while (mediaIter.hasNext()) {
            strb.append(mediaIter.next());
            if (mediaIter.hasNext()) {
                strb.append(", ");
            }
        }
        strb.append(" { ");
        strb.append(css);
        strb.append(" } ");
        return strb.toString();
    }

    /**
     * Inner class to handle exception encountered while parse the css. 
     * The adapted content is set as "ON ERROR" and extra info are added (line 
     * and column position where the problem occured.
     */
    private class CSSParseExceptionHandlerImpl implements ICSSParseExceptionHandler {
        
        private StylesheetContent css;
        public CSSParseExceptionHandlerImpl(StylesheetContent css) {
            this.css = css;
        }
        
        @Override
        public void onException(ParseException extype) {
            int line  = extype.currentToken.next.beginLine;
            int column  = extype.currentToken.next.beginColumn;
            this.css.setAdaptedContent(CSS_ON_ERROR+'l'+line+'c'+column);
            Logger.getLogger(this.getClass()).warn("Error on adaptation " + css.getURI());
            Logger.getLogger(this.getClass()).warn(extype.currentToken.getValue());
            Logger.getLogger(this.getClass()).warn(extype.getMessage());
        }

    };
    
}