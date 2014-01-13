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
package org.opens.tanaguru.crawler;

import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.text.html.HTML;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.archive.io.GzipHeader;
import org.archive.io.RecordingInputStream;
import org.archive.modules.CrawlURI;
import org.archive.modules.deciderules.MatchesFilePatternDecideRule;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.opens.tanaguru.crawler.framework.TanaguruCrawlJob;
import org.opens.tanaguru.crawler.util.CrawlUtils;
import org.opens.tanaguru.entity.audit.*;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author jkowalczyk
 */
public class CrawlerImpl implements Crawler, ContentWriter {

    private static final Logger LOGGER = Logger.getLogger(CrawlerImpl.class);
    private static final int RETRIEVE_WINDOW = 1000;
    private static final int REL_CANONICAL_PAGE_FAKE_HTTP_STATUS = 900;
    private static final String REL_CANONICAL_CSS_LIKE_QUERY = 
                    "head link[rel=canonical][href]";
    private static final String UNREACHABLE_RESOURCE_STR =
            "Unreachable resource ";
    private static final String HERITRIX_SITE_FILE_NAME = "tanaguru-crawler-beans-site.xml";
    private static final String HERITRIX_PAGE_FILE_NAME = "tanaguru-crawler-beans-page.xml";

    /**
     * This boolean is used to determine whether a page has already been fetched
     * in case of 1 page audit.
     */
    private boolean isPageAlreadyFetched = false;
    /**
     * This webResource is the main webResource of the audit. In case of site
     * audit, this webresource is the Site instance from which all the Page instances
     * are linked.
     */
    private WebResource mainWebResource;
    private SSP lastFetchedSSP;

    private TanaguruCrawlJob crawlJob;
    private Set<Long> relatedContentSetTemp = new HashSet<Long>();
    
    private Pattern cssFilePattern = null;
    public Pattern getCssFilePattern() {
        if (cssFilePattern == null && crawlJob != null) {
            cssFilePattern = crawlJob.getCssFilePattern();
        }
        return cssFilePattern;
    }

    private Pattern htmlFilePattern = null;
    public Pattern getHtmlFilePattern() {
        if (htmlFilePattern == null && crawlJob != null) {
            htmlFilePattern = crawlJob.getHtmlFilePattern();
        }
        return htmlFilePattern;
    }

    private ContentDataService contentDataService;
    public ContentDataService getContentDataService() {
        return contentDataService;
    }

    @Override
    public void setContentDataService(ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }

    private WebResourceDataService webResourceDataService;
    public WebResourceDataService getWebResourceDataService() {
        return webResourceDataService;
    }

    @Override
    public void setWebResourceDataService(WebResourceDataService webResourceDataService) {
        this.webResourceDataService = webResourceDataService;
    }

    private ContentFactory contentFactory;
    @Override
    public void setContentFactory(ContentFactory contentFactory) {
        this.contentFactory = contentFactory;
    }

    private String crawlConfigFilePath = null;
    public String getCrawlConfigFilePath() {
        return this.crawlConfigFilePath;
    }

    @Override
    public void setCrawlConfigFilePath(String crawlConfigFilePath) {
        this.crawlConfigFilePath = crawlConfigFilePath;
    }

    private String outputDir = System.getenv("PWD") + "/output";
    public String getOutputDir() {
        return this.outputDir;
    }

    @Override
    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    private Set<Parameter> paramSet = null;
    @Override
    public Set<Parameter> getParameterSet() {
        return paramSet;
    }

    @Override
    public void setParameterSet(Set<Parameter> paramSet) {
        if (this.paramSet == null) {
            this.paramSet = paramSet;
        } else {
            this.paramSet.addAll(paramSet);
        }
    }

    private boolean persistOnTheFly = true;
    public boolean isPersistOnTheFly() {
        return persistOnTheFly;
    }

    @Override
    public void setPersistOnTheFly(boolean persistOnTheFly) {
        this.persistOnTheFly = persistOnTheFly;
    }
    
    private GzipHeader gzipHeader = new GzipHeader();
    public GzipHeader getGzipHeader() {
        return gzipHeader;
    }

    private boolean treatRelCanonical = true;
    
    int pageRankCounter = 1; // a counter to determine the rank a page is fetched

    public CrawlerImpl() {
        super();
    }

    public String getSiteURL() {
        return mainWebResource.getURL();
    }

    @Override
    public void setSiteURL(String siteURL) {
        mainWebResource = webResourceDataService.createSite(siteURL);
        mainWebResource = webResourceDataService.saveOrUpdate(mainWebResource);
        Collection<String> urlList = new ArrayList<String>();
        urlList.add(siteURL);
        this.crawlJob = new TanaguruCrawlJob(
                urlList,
                HERITRIX_SITE_FILE_NAME,
                getOutputDir(),
                getCrawlConfigFilePath(),
                paramSet);
        if (crawlJob.isLaunchable()) {
            crawlJob.checkXML();
        }
    }

    /**
     *
     * @param siteUrl
     */
    @Override
    public void setSiteURL(String siteName, Collection<String> siteURL) {
        mainWebResource = webResourceDataService.createSite(siteName);
        mainWebResource = webResourceDataService.saveOrUpdate(mainWebResource);
        this.crawlJob = new TanaguruCrawlJob(
                siteURL,
                HERITRIX_PAGE_FILE_NAME,
                outputDir,
                crawlConfigFilePath,
                paramSet);
        if (crawlJob.isLaunchable()) {
            crawlJob.checkXML();
        }
    }

    /**
     *
     * @param siteUrl
     */
    @Override
    public void setPageURL(String pageURL) {
        mainWebResource = webResourceDataService.createPage(pageURL);
        mainWebResource = webResourceDataService.saveOrUpdate(mainWebResource);
        Collection<String> urlList = new ArrayList<String>();
        urlList.add(pageURL);
        this.crawlJob = new TanaguruCrawlJob(
                urlList,
                HERITRIX_PAGE_FILE_NAME,
                outputDir,
                crawlConfigFilePath,
                paramSet);
        if (crawlJob.isLaunchable()) {
            crawlJob.checkXML();
        }
        isPageAlreadyFetched = false;
    }

    @Override
    public WebResource getResult() {
        crawlJob = null;
        cssFilePattern = null;
        htmlFilePattern = null;
        isPageAlreadyFetched = false;
        return mainWebResource;
    }

    @Override
    public void run() {
        pageRankCounter = 1;
        this.crawlJob.setContentWriter(this);
        this.crawlJob.launchCrawlJob();
        removeOrphanContent();
    }

    @Override
    public void computeAndPersistSuccessfullFetchedResource(
            CrawlURI curi,
            RecordingInputStream recis) throws IOException {
        LOGGER.debug("Writing " + curi.getURI() + " : "
                + curi.getFetchStatus() + " " + curi.getContentType() + " " + curi.getFetchDuration() + "ms");
        if (curi.getContentType().contains(ContentType.html.getType())
                && !curi.getURI().contains("robots.txt")) {
            LOGGER.debug("Found Html " + curi.getURI());

            // extract data from fetched content and record it to SSP object
//            String charset = CrawlUtils.extractCharset(recis.getMessageBodyReplayInputStream());
            String charset = curi.getContentType().substring(curi.getContentType().indexOf("=")+1);
            LOGGER.debug(charset);
            String sourceCode = CrawlUtils.convertSourceCodeIntoUtf8(recis, charset);
            lastFetchedSSP = saveWebResourceFromFetchedPage(curi, charset, sourceCode, true);
        } else if (curi.getContentType().contains(ContentType.css.getType())) {
            LOGGER.debug("Found css " + curi.getURI() + " last fetched ssp " + lastFetchedSSP.getURI());
            
            boolean compressed = gzipHeader.testGzipMagic(recis.getMessageBodyReplayInputStream());
            String cssCode;
            if (compressed) {
                cssCode = "";
            } else {
//                String charset = CrawlUtils.extractCharset(recis.getMessageBodyReplayInputStream());
                String charset = curi.getContentType().substring(curi.getContentType().indexOf("=")+1);
                cssCode = CrawlUtils.convertSourceCodeIntoUtf8(recis, charset);
            }
            saveStylesheetFromFetchedCss(curi, cssCode);
            
        } else if (curi.getContentType().contains(ContentType.img.getType())) {
            LOGGER.debug("Found Image" + curi.getURI());

            byte[] rawImage = CrawlUtils.getImageContent(recis.getMessageBodyReplayInputStream(),
                    CrawlUtils.getImageExtension(curi.getURI()));
            saveRawImageFromFetchedImage(curi, rawImage);
            
        } else {
            LOGGER.debug("Trashed content " + curi.getURI() + " of type " + curi.getContentType());
            // do nothing, we ignore the fetched content when we cannot
            // categorize it
        }
    }

    @Override
    public void computeAndPersistUnsuccessfullFetchedResource(CrawlURI curi) {
        ContentType resourceContentType =
                getContentTypeFromUnreacheableResource(curi.getCanonicalString());
        switch (resourceContentType) {
            case misc:
            case html:
                LOGGER.debug(
                        UNREACHABLE_RESOURCE_STR + curi.getURI() + " : "
                        + curi.getFetchStatus());
                
                saveWebResourceFromFetchedPage(curi, null, null, false);
                break;
                
            case css:
                LOGGER.debug(
                        UNREACHABLE_RESOURCE_STR + curi.getURI() + " : "
                        + curi.getFetchStatus());
                
                saveStylesheetFromFetchedCss(curi, null);
                break;

            case img:
                LOGGER.debug(UNREACHABLE_RESOURCE_STR + curi.getURI() + " : "
                        + curi.getFetchStatus());

                saveRawImageFromFetchedImage(curi, null);
                break;

            default:
                LOGGER.debug("UNKNOWN_CONTENT" + UNREACHABLE_RESOURCE_STR + curi.getURI() + " : "
                        + curi.getFetchStatus());
                break;
        }
    }

    /**
     * 
     * @param curi
     * @param charset
     * @param sourceCode
     * @param successfullFetch
     * @return
     */
    private SSP saveWebResourceFromFetchedPage(
            CrawlURI curi,
            String charset,
            String sourceCode,
            boolean successfullFetch) {

        Page page;
        if (mainWebResource instanceof Page) {
            if (!isPageAlreadyFetched) {
                page = (Page) mainWebResource;
                // in case of redirection, we modify the URI of the webresource
                // to ensure the webresource and its SSP have the same URI.
                page.setURL(curi.getURI());
                if (successfullFetch) {
                    isPageAlreadyFetched = true;
                    SSP ssp = createSSPFromPage(curi, charset, page, sourceCode);
                    if (persistOnTheFly) {
                        persistSSP(ssp, curi, page);
                    }
                    return ssp;
                } else {
                    return lastFetchedSSP;
                }
            } else {
                // in case of one page audit, when a SSP have already been fetched
                // we don't create SSP anymore. 
                return lastFetchedSSP;
            }
        } else {
            page = webResourceDataService.createPage(curi.getURI());
            page.setParent((Site) mainWebResource);
            page.setRank(pageRankCounter);
            pageRankCounter++;
            SSP ssp = createSSPFromPage(curi, charset, page, sourceCode);
            if (persistOnTheFly) {
                persistSSP(ssp, curi, page);
            }
            return ssp;
        }
    }

    /**
     * 
     * @param curi
     * @param charset
     * @param page
     * @param sourceCode
     * @return
     */
    private SSP createSSPFromPage(
            CrawlURI curi,
            String charset,
            Page page,
            String sourceCode) {
        SSP ssp = contentFactory.createSSP(curi.getURI());
        ssp.setPage(page);
        ssp.setCharset(charset);
        ssp.setSource(sourceCode);
        return ssp;
    }
    
    /**
     * 
     * @param curi
     * @param charset
     * @param page
     * @param sourceCode
     * @return 
     */
    private void persistSSP(
            SSP ssp,
            CrawlURI curi,
            Page page) {
        webResourceDataService.saveOrUpdate(page);
        saveAndPersistFetchDataToContent(ssp, curi);
    }

    /**
     *
     * @param curi
     * @param charset
     * @param cssCode
     */
    private void saveStylesheetFromFetchedCss(CrawlURI curi, String cssCode) {
        StylesheetContent newCssContent = contentFactory.createStylesheetContent(
                null,
                curi.getURI(),
                null,
                cssCode,
                curi.getFetchStatus());
        // A relatedContent has to be linked to a SSP.
        // At this step, we don't know the relation between
        // SSP and relatedContent but we have to link this relatedContent to any
        // (the last) ssp to associate this relatedContent with the current
        // crawl
        StylesheetContent returnedCssContent = (StylesheetContent) saveAndPersistFetchDataToContent((Content) newCssContent, curi);
        persistContentRelationShip(lastFetchedSSP, returnedCssContent);
    }

    private void saveRawImageFromFetchedImage(CrawlURI curi, byte[] rawImage) {
        ImageContent newImgContent = contentFactory.createImageContent(
                    null,
                    curi.getURI(),
                    null,
                    rawImage,
                    curi.getFetchStatus());
        ImageContent returnedImgContent =
                    (ImageContent) saveAndPersistFetchDataToContent(newImgContent, curi);
        persistContentRelationShip(lastFetchedSSP, returnedImgContent);
    }

    /**
     * This methods enables to get the type of resource from its uri.
     * In case of unreachable resource (404/403 errors), the return content is
     * a html page. So we can't use the content type of the returned page to
     * determine the type of the content we try to reach. In this case, we use
     * the uri extension, based-on regular expressions.
     * @param uri
     * @return
     */
    private ContentType getContentTypeFromUnreacheableResource(String uri) {
        if (MatchesFilePatternDecideRule.Preset.IMAGES.getPattern().
                matcher(uri).matches()) {
            return ContentType.img;
        } else if (getHtmlFilePattern().matcher(uri).matches()) {
            return ContentType.html;
        } else if (getCssFilePattern().matcher(uri).matches()) {
            return ContentType.css;
        }
        return ContentType.misc;
    }

    // Bug #154 fix
    /**
     * Some resources may have been downloaded by the crawler component but they
     * are not linked with any webresource. They have to be removed from the
     * contentList.
     */
    @SuppressWarnings("element-type-mismatch")
    private void removeOrphanContent() {
        List<Content> emptyContentSet;
        Integer nbOfContent = contentDataService.getNumberOfOrphanRelatedContent(mainWebResource).intValue();
        Integer i = 0;
        Logger.getLogger(CrawlerImpl.class.getName()).debug("remove Orphan related contents  " + nbOfContent + " elements");
        while (i.compareTo(nbOfContent) < 0) {
            emptyContentSet = contentDataService.getOrphanRelatedContentList(mainWebResource, 0, RETRIEVE_WINDOW);
            for (Content content : emptyContentSet) {
                Logger.getLogger(CrawlerImpl.class.getName()).debug("Removing " + content.getURI());
                contentDataService.delete(content.getId());
            }
            i = i + RETRIEVE_WINDOW;
        }

        nbOfContent = contentDataService.getNumberOfOrphanContent(mainWebResource).intValue();
        i = 0;
        Logger.getLogger(CrawlerImpl.class.getName()).debug("remove Orphan SSPs  " + nbOfContent + " elements");
        while (i.compareTo(nbOfContent) < 0) {
            emptyContentSet = contentDataService.getOrphanContentList(mainWebResource, i, RETRIEVE_WINDOW);
            for (Content content : emptyContentSet) {
                contentDataService.delete(content.getId());
            }
            i = i + RETRIEVE_WINDOW;
        }
    }

    /**
     * This methods add the fetch date and the fetch status to a content and
     * persist it
     * @param content
     * @param curi
     */
    private Content saveAndPersistFetchDataToContent(Content content, CrawlURI curi) {
        // Waiting for a better implementation, we parse here the html content
        // to detect the presence of the rel=canonical property.
        // If true, the HttpStatusCode is set arbitrarely to 900 and thus the
        // page won't be tested while processing
        if (isRelCanonicalPage(content)) {
            LOGGER.info("Fetching page with rel canonical " + content.getURI() + ". Set Http status to 900");
            content.setHttpStatusCode(REL_CANONICAL_PAGE_FAKE_HTTP_STATUS);
        } else {
            content.setHttpStatusCode(curi.getFetchStatus());
        }
        content.setDateOfLoading(new Date(curi.getFetchCompletedTime()));
        if (persistOnTheFly) {
            content = contentDataService.saveOrUpdate(content);
        }
        return content;
    }

    /**
     * 
     * @param content
     * @return whether the current page defines a rel canonical Url and whether
     * this url is different from the current url.
     */
    private boolean isRelCanonicalPage(Content content) {
        // @TODO make this implementation cleaner
        if (! treatRelCanonical) {
            return false;
        }
        if (! (content instanceof SSP)) {
            return false;
        }
        if (StringUtils.isBlank(((SSP)content).getSource())) {
            return false;
        }
        Elements relCanonical = Jsoup.parse(((SSP)content).getSource()).select(REL_CANONICAL_CSS_LIKE_QUERY);
        if (relCanonical.isEmpty() || relCanonical.size() > 1) {
            return false;
        }
        // At this step, we are sure that the rel canonical is defined and 
        // is unique
        String href = relCanonical.first().attr("href");
        if (href.equals(".")) {
            return false;
        }
        if (href.contains("//")) {
            href = href.substring(href.indexOf("//") +2 );
        }
        if (href.endsWith("/")) {
            href = href.substring(0, href.length() -1 );
        }
        String currentUrl = content.getURI();
        if (currentUrl.endsWith("/")) {
            currentUrl = currentUrl.substring(0, currentUrl.length() -1 );
        }
        if (currentUrl.contains("//")) {
            currentUrl = currentUrl.substring(currentUrl.indexOf("//") +2 );
        }
        if (currentUrl.equals(href)) {
            LOGGER.info("rel canonical present but points to itself " + content.getURI());
            return false;
        }
        return true;
    }
    
    /**
     * 
     * @param ssp
     * @param relatedContent
     */
    private void persistContentRelationShip(SSP ssp, RelatedContent relatedContent) {
        if (!persistOnTheFly) {
            ssp.addRelatedContent(relatedContent);
            return;
        }
        relatedContentSetTemp.clear();
        relatedContentSetTemp.add(((Content) relatedContent).getId());
        contentDataService.saveContentRelationShip(ssp, relatedContentSetTemp);
    }

}