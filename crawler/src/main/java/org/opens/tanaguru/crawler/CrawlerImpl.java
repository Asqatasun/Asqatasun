package org.opens.tanaguru.crawler;

import java.util.logging.Level;
import org.opens.tanaguru.crawler.processor.TanaguruWriterProcessor;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import org.apache.log4j.Logger;
import org.archive.crawler.framework.CrawlJob;
import org.archive.modules.Processor;
import org.archive.net.UURIFactory;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.ImageContent;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.audit.StylesheetContent;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 */
public class CrawlerImpl implements Crawler {

    private static final String urlStrToReplace = "# URLS HERE";
    private static int CRAWL_LAUNCHER_RETRY_TIMEOUT = 1000;
    private static int CRAWL_LOGGER_TIMEOUT = 2000;

    private WebResource webResource;
    private File currentJobOutputDir;
    private String heritrixFileName = "tanaguru-crawler-beans.cxml";
    private CrawlJob crawlJob;
    private List<Content> contentList = new ArrayList<Content>();

    public CrawlerImpl() {
        super();
    }
    /**
     * 
     */
    private String crawlConfigFilePath = "/etc/tanaguru/context/crawler/";

    /**
     *
     * @return
     */
    public String getCrawlConfigFilePath() {
        return this.crawlConfigFilePath;
    }

    /**
     *
     * @param crawlConfigFilePath
     */
    public void setCrawlConfigFilePath(String crawlConfigFilePath) {
        this.crawlConfigFilePath = crawlConfigFilePath;
    }
    /**
     *
     */
    private String outputDir = System.getenv("PWD") + "/output";

    /**
     *
     * @return
     */
    public String getOutputDir() {
        return this.outputDir;
    }

    /**
     *
     * @param outputDir
     */
    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    /**
     *
     * @return
     */
    public String getSiteURL() {
        return webResource.getURL();
    }

    public List<Content> getContentListResult() {
        return contentList;
    }

    private WebResourceFactory webResourceFactory;
    public void setWebResourceFactory(WebResourceFactory webResourceFactory) {
        this.webResourceFactory = webResourceFactory;
    }

    private ContentFactory contentFactory;
    public void setContentFactory(ContentFactory contentFactory) {
        this.contentFactory = contentFactory;
    }

    /**
     *
     * @param siteUrl
     */
    @Override
    public void setSiteURL(String siteURL) {
        webResource = webResourceFactory.createSite(siteURL);
        String[] siteUrl = {siteURL};
        this.crawlJob = new CrawlJob(initializeCrawlContext(siteUrl));
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
        webResource = webResourceFactory.createSite(siteName);
        this.crawlJob = new CrawlJob(initializeCrawlContext(siteURL));
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
        webResource = webResourceFactory.createPage(pageURL);
        String[] pageUrl = {pageURL};
        this.crawlJob = new CrawlJob(initializeCrawlContext(pageUrl));
        if (crawlJob.isLaunchable()) {
            crawlJob.checkXML();
        }
    }

    public WebResource getResult() {
        crawlJob = null;
        return webResource;
    }

    public void run() {
        if (crawlJob.isLaunchable()) {
            Logger.getLogger(CrawlerImpl.class.getName()).info(
                    "crawljob is launchable");
            crawlJob.launch();
            if (!crawlJob.isRunning()) {
                try {
                    Thread.sleep(CRAWL_LAUNCHER_RETRY_TIMEOUT);
                } catch (InterruptedException ex) {
                    java.util.logging.Logger.getLogger(
                            CrawlerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        Logger.getLogger(CrawlerImpl.class.getName()).info(
                "is crawlJob running? " + crawlJob.isRunning() + " " + this.getSiteURL());
        while (crawlJob.isRunning()) {
            try {
                if (crawlJob.isUnpausable()) {
                    crawlJob.getCrawlController().getFrontier().run();
                }
                Logger.getLogger(CrawlerImpl.class.getName()).info(
                        "crawljob is running");
                Thread.sleep(CRAWL_LOGGER_TIMEOUT);
            } catch (InterruptedException e) {
                // do nothing
            }
        }
        computeResult();
        crawlJob.terminate();
        if (crawlJob.teardown()) {
            closeCrawlerLogFiles();
            if (!removeConfigFile(currentJobOutputDir)) {
            Logger.getLogger(CrawlerImpl.class.getName()).info(
                        "Configuration Heritrix files cannot be deleted");
            }
        } else {
            Logger.getLogger(CrawlerImpl.class.getName()).info(
                        "The crawljob is not teardowned");
        }
    }

    /**
     * This method initialize the heritrix context before starting the crawl
     * @return
     */
    private File initializeCrawlContext(String[] url) {
        // Create one directory
        currentJobOutputDir = new File(outputDir + "/" + "crawl" + "-" + new Date().getTime());
        if (!currentJobOutputDir.exists()) {
            boolean success = currentJobOutputDir.mkdir();
            if (success) {
                Logger.getLogger(CrawlerImpl.class.getName()).info(
                        "Directory: " + currentJobOutputDir + " created");
            }
        }
        BufferedReader in = null;
        FileWriter fw = null;
        try {
            in = new BufferedReader(
                    new FileReader(crawlConfigFilePath + "/" + heritrixFileName));

            String c;
            StringBuffer newContextFile = new StringBuffer();
            while ((c = in.readLine()) != null) {
                if (c.equalsIgnoreCase(urlStrToReplace)) {
                    for (int i=0 ; i<url.length ; i++) {
                        newContextFile.append(UURIFactory.getInstance(url[i]));
                        newContextFile.append("\r");
                    }
                } else {
                    newContextFile.append(c);
                }
                newContextFile.append("\r");
            }
            fw = new FileWriter(currentJobOutputDir.getPath() + "/" + heritrixFileName);
            fw.write(newContextFile.toString());
            fw.close();
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(CrawlerImpl.class.getName()).error(ex);
        } finally {
            if (in != null) {
               try {
                   in.close();
		} catch (IOException e) {
                    e.printStackTrace();
		}
            }
            if (fw != null) {
                try {
                    fw.close();
		} catch (IOException e) {
                    e.printStackTrace();
		}
            }
        }
        return new File(currentJobOutputDir.getPath() + "/" + heritrixFileName);
    }

    /**
     * 
     */
    private void computeResult() {
        for (Processor processor : crawlJob.getCrawlController().getDispositionChain().getProcessors()) {
            if (processor instanceof TanaguruWriterProcessor) {
                contentList =
                        ((TanaguruWriterProcessor) processor).getContentList();
                contentDeepCopy(((TanaguruWriterProcessor) processor).getWebResourceSet(),
                        ((TanaguruWriterProcessor) processor).getContentList());
                computeContentRelationShip(
                        ((TanaguruWriterProcessor) processor).getContentRelationShipMap());
                removeOrphanContent();
                cleanUpWriterResources((TanaguruWriterProcessor) processor);
                break;
            }
        }
    }

    /**
     * This method associates an HTML content with its relative content.
     * We use the Content list and the content relationship map filled-in
     * by the TanaguruWriterProcessor to do so.
     * @param contentRelationShipMap
     */
    private void computeContentRelationShip(Map<String, Collection<String>> contentRelationShipMap) {
        Map<String, Content> tempMap = new HashMap<String, Content>();

        // initialisation of a temporary map to associate an url with its
        // Content
        for (Content content : contentList) {
            tempMap.put(content.getURI(), content);
        }

        // Let's parse the contentRelationShip Map
        for (String parentUrl : contentRelationShipMap.keySet()) {

            // Does my local content list contains the url ?
            // Some types of content (js, swf, pdf) are excluded from the
            // download but are found by the extractor and so belongs to the
            // contentRelationShipMap.
            if (tempMap.containsKey(parentUrl)) {

                // if the content is a SSP, we associate it with its related
                // contents
                if (tempMap.get(parentUrl) instanceof SSP) {
                    SSP localSSP = ((SSP) tempMap.get(parentUrl));
                    for (String childUrl : contentRelationShipMap.get(parentUrl)) {

                        // We check that the content found by the extractor  has been
                        // actually downloaded and we only keep related content
                        // (to avoid to have a relation between 2 SSP)
                        if (tempMap.containsKey(childUrl)
                                && (tempMap.get(childUrl)) instanceof RelatedContent) {
                            localSSP.addRelatedContent(
                                    (RelatedContent) (tempMap.get(childUrl)));
                        }
                    }

                    // if the content is a Related Content (css, images), we
                    // associate it with its parent SSP
                } else if (tempMap.get(parentUrl) instanceof RelatedContent) {
                    RelatedContent localRelatedContent = ((RelatedContent) tempMap.get(parentUrl));
                    for (String childUrl : contentRelationShipMap.get(parentUrl)) {

                        // We check that the content found by the extractor has been
                        // actually downloaded and we only keep related content
                        // (to avoid to have a relation between 2 SSP
                        if (tempMap.containsKey(childUrl)) {
                            if (tempMap.get(childUrl) instanceof SSP) {
                                localRelatedContent.addParentContent(tempMap.get(childUrl));
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Clean-up configuration files before leaving
     * @param file
     * @return
     */
    private boolean removeConfigFile(File file) {
        if (file.exists()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    removeConfigFile(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (file.delete());
    }

    /**
     * This methods realized a deep copy of a content object and add the
     * copied object to the content list.
     * @param webResourceSet
     * @param contentListToCopy
     */
    private void contentDeepCopy(Set<WebResource> webResourceSet,
            List<Content> contentListToCopy){
        if (webResource instanceof Site) {
            for (WebResource wr : webResourceSet) {
                ((Site) webResource).addChild(webResourceDeepCopy(wr));
            }
        } else if (webResourceSet.size() == 1) {
            webResource = webResourceDeepCopy(webResourceSet.iterator().next());
        }
        contentList = contentDeepCopy(contentListToCopy);
    }

    /**
     * This methods realizes a deep copy of a webresource instance
     * @param webResource
     * @return
     */
    private WebResource webResourceDeepCopy(WebResource webResource){
        StringBuilder webResourceUrl = new StringBuilder();
        webResourceUrl.append(webResource.getURL());
        WebResource webResourceCopy =
                webResourceFactory.createPage(webResourceUrl.toString());

        webResourceCopy.setId(webResource.getId());
        if(webResource.getLabel() != null) {
            StringBuilder webResourceLabel = new StringBuilder();
            webResourceLabel.append(webResource.getLabel());
            webResourceCopy.setLabel(new String(webResource.getLabel()));
        }
        return webResourceCopy;
    }

    /**
     * This method realizes a deep copy of a content list and all its elements
     * @param contentListToCopy
     * @return
     */
    private List<Content> contentDeepCopy(List<Content> contentListToCopy){
        List<Content> localContentList = new ArrayList<Content>();
        for(Content contentToCopy : contentListToCopy) {
            if (contentToCopy instanceof SSP) {
                SSP ssp = (SSP)contentToCopy;
                String uri = null;
                if (ssp.getURI() != null) {
                    uri = new String (ssp.getURI());
                }
                String sourceCode = null;
                if (ssp.getSource() != null) {
                    sourceCode = new String (ssp.getSource());
                }
                Content htmlContent = contentFactory.createSSP(
                        new Date(),
                        uri,
                        sourceCode,
                        (Page)retrieveWebResource(ssp.getPage()),
                        ssp.getHttpStatusCode());
                if (ssp.getCharset() != null) {
                    ((SSP)htmlContent).setCharset(new String(ssp.getCharset()));
                }
                localContentList.add(htmlContent);
            } else if (contentToCopy instanceof StylesheetContent) {
                StylesheetContent stylesheetContent = 
                        (StylesheetContent)contentToCopy;
                String uri = null;
                if (stylesheetContent.getURI() != null) {
                    uri = new String (stylesheetContent.getURI());
                }
                String sourceCode = null;
                if (stylesheetContent.getSource() != null) {
                    sourceCode = new String (stylesheetContent.getSource());
                }
                Content cssContent = contentFactory.createStylesheetContent(
                        new Date(),
                        uri,
                        null,
                        sourceCode,
                        stylesheetContent.getHttpStatusCode());
                localContentList.add(cssContent);
            } else if (contentToCopy instanceof ImageContent) {
                ImageContent imageContent = (ImageContent)contentToCopy;
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try {
                    if (imageContent.getContent() != null) {
                        baos.write(imageContent.getContent());
                    }
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(
                            CrawlerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                byte[] data = baos.toByteArray();
                String uri = null;
                if (imageContent.getURI() != null) {
                    uri = new String (imageContent.getURI());
                }
                Content imgContent = contentFactory.createImageContent(
                        new Date(),
                        uri,
                        null,
                        data,
                        imageContent.getHttpStatusCode());
                localContentList.add(imgContent);
            }
        }
        return localContentList;
    }

    /**
     * This method enables to retrieve a instance of webResource among the local
     * set of webResources through its url.s
     * @param wr
     * @return
     */
    private WebResource retrieveWebResource(WebResource wr) {
        if (wr != null) {
            if (webResource instanceof Page){
                if (webResource.getURL().equalsIgnoreCase(wr.getURL())) {
                    return webResource;
                }
            } else if (webResource instanceof Site) {
                for (WebResource childPage : ((Site)webResource).getComponentList()) {
                    if (childPage instanceof Page && childPage.getURL().
                            equalsIgnoreCase(wr.getURL()))  {
                        return childPage;
                    }
                }
            }
        }
        return null;
    }

    /**
     * This method cleans up the resources created by the TanaguruWriterProcessor
     * class. All these resources have been copied (to enable the
     * TanaguruWriterProcessor instance to be garbaged)
     * @param processor
     */
    private void cleanUpWriterResources(TanaguruWriterProcessor processor){
        processor.getContentList().clear();
        processor.setContentList(null);
        processor.getContentRelationShipMap().clear();
        processor.setContentRelationShipMap(null);
        processor.setCssContentRelationShipMap(null);
        processor.getWebResourceSet().clear();
        processor.setWebResourceSet(null);
        processor.setWebResourceFactory(null);
        processor.setContentFactory(null);
    }

    // Bug #154 fix
    /**
     * Some resources may have been downloaded by the crawler component but they
     * are not linked with any webresource. They have to be removed from the
     * contentList. 
     */
    @SuppressWarnings("element-type-mismatch")
    private void removeOrphanContent() {
        List<Content> contentToRemoveList = new ArrayList<Content>();
        List<RelatedContent> relatedContentToRemoveList =
                new ArrayList<RelatedContent>();
        List<RelatedContent> relatedContentList =
                new ArrayList<RelatedContent>();

        // we search the SSP without webresource
        for (Content content :contentList) {
            if (content instanceof SSP && ((SSP)content).getPage() == null) {
                contentToRemoveList.add(content);
            } else if (content instanceof RelatedContent) {
                relatedContentList.add((RelatedContent)content);
            }
        }

        // we remove the orphan SSP from the contentList and we remove the
        // reference of these SSP from the Parent Set of each relatedContent.
        for (Content content : contentToRemoveList) {
            contentList.remove(content);
            for (RelatedContent relatedContent : relatedContentList) {
                if ((relatedContent).getParentContentSet().contains((SSP)content)) {
                    relatedContent.getParentContentSet().remove(content);
                }
            }
        }

        // we search the related contents without parent (whose parents were
        // orphan SSP that are now deleted)
        for (RelatedContent relatedContent : relatedContentList) {
            if (relatedContent.getParentContentSet().isEmpty()) {
                relatedContentToRemoveList.add(relatedContent);
            }
        }

        // we remove from the content list the relatedContent without parent
        for (RelatedContent relatedContent : relatedContentToRemoveList) {
            contentList.remove(relatedContent);
        }

    }

    /**
     * Heritrix lets its log files opened at the end of the crawl.
     * We have to close them "manually".
     */
    private void closeCrawlerLogFiles() {
        List<FileHandler> loggerHandlerList = new ArrayList<FileHandler>();
        for (Handler handler: crawlJob.getJobLogger().getHandlers()) {
            if (handler instanceof FileHandler) {
                ((FileHandler)handler).close();
                loggerHandlerList.add((FileHandler)handler);
            }
        }
        for (FileHandler fileHandler : loggerHandlerList) {
            crawlJob.getJobLogger().removeHandler(fileHandler);
        }
    }

}