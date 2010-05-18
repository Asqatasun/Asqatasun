package org.opens.tanaguru.crawler;

import java.util.logging.Level;
import org.opens.tanaguru.crawler.processor.TanaguruWriterProcessor;
import java.io.BufferedReader;
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
import org.apache.log4j.Logger;
import org.archive.crawler.framework.CrawlJob;
import org.archive.modules.Processor;
import org.archive.net.UURIFactory;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.RelatedContent;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 */
public class CrawlerImpl implements Crawler {

    private WebResource webResource;
    private File currentJobOutputDir;
    protected String heritrixFileName = "tanaguru-crawler-beans.cxml";
    protected CrawlJob crawlJob;
    private final String urlStrToReplace = "# URLS HERE";
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

    /**
     *
     * @param siteUrl
     */
    @Override
    public void setSiteURL(String webResourceURL) {
        webResource = webResourceFactory.createSite(webResourceURL);
        this.crawlJob = new CrawlJob(initializeCrawlContext());
        if (crawlJob.isLaunchable()) {
            crawlJob.checkXML();
        }
    }

    public WebResource getResult() {
        return webResource;
    }

    public void run() {
        if (crawlJob.isLaunchable()) {
            Logger.getLogger(CrawlerImpl.class.getName()).info(
                    "crawljob is launchable");
            crawlJob.launch();
            if (!crawlJob.isRunning()){
                try {
                    Thread.sleep(1000);
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
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // do nothing
            }
        }
        computeResult();
        crawlJob.terminate();
        if (crawlJob.teardown()) {
            removeConfigFile(currentJobOutputDir);
        }
    }

    /**
     * This method initialize the heritrix context before starting the crawl
     * @return
     */
    private File initializeCrawlContext() {
        // Create one directory
        currentJobOutputDir = new File(outputDir + "/" + "crawl" + "-" + new Date().getTime());
        if (!currentJobOutputDir.exists()) {
            boolean success = currentJobOutputDir.mkdir();
            if (success) {
                Logger.getLogger(CrawlerImpl.class.getName()).info(
                        "Directory: " + currentJobOutputDir + " created");
            }
        }
        try {
            BufferedReader in = new BufferedReader(
                    new FileReader(crawlConfigFilePath + "/" + heritrixFileName));

            String c;
            StringBuffer newContextFile = new StringBuffer();
            while ((c = in.readLine()) != null) {
                if (c.equalsIgnoreCase(urlStrToReplace)) {
                    UURIFactory.getInstance(webResource.getURL()).toString();
                } else {
                    newContextFile.append(c);
                }
                newContextFile.append("\r");
            }
            FileWriter fw = new FileWriter(currentJobOutputDir.getPath() + "/" + heritrixFileName);
            fw.write(newContextFile.toString());
            fw.close();
            in.close();
        } catch (IOException ex) {
            Logger.getLogger(CrawlerImpl.class.getName()).error(ex);
        }
        return new File(currentJobOutputDir.getPath() + "/" + heritrixFileName);
    }

    /**
     * 
     */
    private void computeResult() {
        for (Processor processor : crawlJob.getCrawlController().getDispositionChain().getProcessors()) {
            if (processor instanceof TanaguruWriterProcessor) {
                List<WebResource> webResourceList =
                        ((TanaguruWriterProcessor) processor).getWebResourceList();
                if (webResource instanceof Site) {
                    ((Site) webResource).addAllChild(webResourceList);
                } else if (webResourceList.size() == 1) {
                    webResource = webResourceList.get(0);
                }
                contentList =
                        ((TanaguruWriterProcessor) processor).getContentList();
                computeContentRelationShip(
                        ((TanaguruWriterProcessor) processor).getContentRelationShipMap());
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

}
