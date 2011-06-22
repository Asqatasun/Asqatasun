package org.opens.tanaguru.crawler;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.archive.io.GzippedInputStream;
import org.archive.io.RecordingInputStream;
import org.archive.modules.CrawlURI;
import org.archive.modules.deciderules.MatchesFilePatternDecideRule;
import org.opens.tanaguru.crawler.framework.TanaguruCrawlJob;
import org.opens.tanaguru.crawler.util.CrawlUtils;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ImageContent;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 */
public class CrawlerImpl implements Crawler, ContentWriter {

    private static final Logger LOGGER = Logger.getLogger(CrawlerImpl.class);
    private static final int RETRIEVE_WINDOW = 1000;
    private static final String UNREACHABLE_RESOURCE_STR =
            "Unreachable resource ";
    private boolean isPageAlreadyFetched = false;
    private WebResource mainWebResource;
    private SSP lastFetchedSSP;
    private String heritrixSiteFileName = "tanaguru-crawler-beans-site.xml";
    private String heritrixPageFileName = "tanaguru-crawler-beans-page.xml";
    private String crawlConfigFilePath;
    private String outputDir = System.getenv("PWD") + "/output";
    private Pattern cssFilePattern = null;
    private TanaguruCrawlJob crawlJob;
    private Set<Long> relatedContentSet = new HashSet<Long>();
    private ContentDataService contentDataService;
    private WebResourceDataService webResourceDataService;
    private WebResourceFactory webResourceFactory;
    private ContentFactory contentFactory;

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

    public ContentDataService getContentDataService() {
        return contentDataService;
    }

    @Override
    public void setContentDataService(ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }

    public WebResourceDataService getWebResourceDataService() {
        return webResourceDataService;
    }

    @Override
    public void setWebResourceDataService(WebResourceDataService webResourceDataService) {
        this.webResourceDataService = webResourceDataService;
    }

    @Override
    public void setWebResourceFactory(WebResourceFactory webResourceFactory) {
        this.webResourceFactory = webResourceFactory;
    }

    @Override
    public void setContentFactory(ContentFactory contentFactory) {
        this.contentFactory = contentFactory;
    }

    public CrawlerImpl() {
        super();
    }

    public String getCrawlConfigFilePath() {
        return this.crawlConfigFilePath;
    }

    @Override
    public void setCrawlConfigFilePath(String crawlConfigFilePath) {
        this.crawlConfigFilePath = crawlConfigFilePath;
    }

    public String getOutputDir() {
        return this.outputDir;
    }

    @Override
    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public String getSiteURL() {
        return mainWebResource.getURL();
    }

    @Override
    public void setSiteURL(String siteURL) {
        mainWebResource = webResourceFactory.createSite(siteURL);
        mainWebResource = webResourceDataService.saveOrUpdate(mainWebResource);
        String[] siteUrl = {siteURL};
        this.crawlJob = new TanaguruCrawlJob(siteUrl, heritrixSiteFileName, getOutputDir(), getCrawlConfigFilePath());
        if (crawlJob.isLaunchable()) {
            crawlJob.checkXML();
        }
    }

    /**
     *
     * @param siteUrl
     */
    @Override
    public void setSiteURL(String siteName, String[] siteURL) {
        mainWebResource = webResourceFactory.createSite(siteName);
        mainWebResource = webResourceDataService.saveOrUpdate(mainWebResource);
        this.crawlJob = new TanaguruCrawlJob(siteURL, heritrixPageFileName, outputDir, crawlConfigFilePath);
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
        mainWebResource = webResourceFactory.createPage(pageURL);
        mainWebResource = webResourceDataService.saveOrUpdate(mainWebResource);
        String[] pageUrl = {pageURL};
        this.crawlJob = new TanaguruCrawlJob(pageUrl, heritrixPageFileName, outputDir, crawlConfigFilePath);
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
            String charset = CrawlUtils.extractCharset(recis.getContentReplayInputStream());
            String sourceCode = CrawlUtils.convertSourceCodeIntoUtf8(recis, charset);
            lastFetchedSSP = saveWebResourceFromFetchedPage(curi, charset, sourceCode, true);

        } else if (curi.getContentType().contains(ContentType.css.getType())) {
            LOGGER.debug("Found css " + curi.getURI() + " last fetched ssp " + lastFetchedSSP.getURI());
            
            boolean compressed = GzippedInputStream.isCompressedStream(recis.getContentReplayInputStream());
            String cssCode = null;
            if (compressed) {
                cssCode = "";
            } else {
                String charset = CrawlUtils.extractCharset(recis.getContentReplayInputStream());
                cssCode = CrawlUtils.convertSourceCodeIntoUtf8(recis, charset);
            }
            saveStylesheetFromFetchedCss(curi, cssCode);
            
        } else if (curi.getContentType().contains(ContentType.img.getType())) {
            LOGGER.debug("Found Image" + curi.getURI());

            byte[] rawImage = CrawlUtils.getImageContent(recis.getContentReplayInputStream(),
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
                LOGGER.info(
                        UNREACHABLE_RESOURCE_STR + curi.getURI() + " : "
                        + curi.getFetchStatus());
                
                saveWebResourceFromFetchedPage(curi, null, null, false);
                break;
                
            case css:
                LOGGER.info(
                        UNREACHABLE_RESOURCE_STR + curi.getURI() + " : "
                        + curi.getFetchStatus());
                
                saveStylesheetFromFetchedCss(curi, null);
                break;

            case img:
                LOGGER.info(UNREACHABLE_RESOURCE_STR + curi.getURI() + " : "
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

        Page page = null;
        if (mainWebResource instanceof Page) {
            if (!isPageAlreadyFetched) {
                page = (Page) mainWebResource;
                // in case of redirection, we modify the URI of the webresource
                // to ensure the webresource and its SSP have the same URI.
                page.setURL(curi.getURI());
                if (successfullFetch) {
                    isPageAlreadyFetched = true;
                    return saveAndCreateSSPFromPage(curi, charset, page, sourceCode);
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
            return saveAndCreateSSPFromPage(curi, charset, page, sourceCode);
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
    private SSP saveAndCreateSSPFromPage(
            CrawlURI curi,
            String charset,
            Page page,
            String sourceCode) {
        SSP ssp = contentFactory.createSSP(curi.getURI());
        ssp.setPage(page);
        ssp.setCharset(charset);
        ssp.setSource(sourceCode);
        webResourceDataService.saveOrUpdate(page);
        ssp = (SSP) saveAndPersistFetchDataToContent(ssp, curi);
        return ssp;
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
        List<Content> emptyContentSet = null;
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
        content.setHttpStatusCode(curi.getFetchStatus());
        content.setDateOfLoading(new Date(curi.getFetchCompletedTime()));
        Content returnedContent = contentDataService.saveOrUpdate(content);
        return returnedContent;
    }

    /**
     * 
     * @param ssp
     * @param relatedContent
     */
    private void persistContentRelationShip(SSP ssp, RelatedContent relatedContent) {
        relatedContentSet.clear();
        relatedContentSet.add(((Content) relatedContent).getId());
        contentDataService.saveContentRelationShip(ssp, relatedContentSet);
    }
}
