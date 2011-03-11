/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.opens.tanaguru.crawler.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.archive.crawler.framework.CrawlController;
import org.archive.crawler.framework.CrawlJob;
import org.archive.crawler.reporting.AlertThreadGroup;
import org.archive.modules.deciderules.DecideRuleSequence;

import org.archive.net.UURIFactory;
import org.archive.spring.PathSharingContext;
import org.opens.tanaguru.crawler.ContentWriter;
import org.opens.tanaguru.crawler.CrawlerImpl;
import org.opens.tanaguru.crawler.extractor.listener.ExtractorCSSListener;
import org.opens.tanaguru.crawler.extractor.listener.ExtractorHTMLListener;
import org.opens.tanaguru.crawler.processor.TanaguruWriterProcessor;
import org.springframework.beans.BeansException;

/**
 * This class embeds a heritrix CrawlJob object and deals with the instanciation,
 * the launch, the stop and the clean-up. Between the instanciation and the launch
 * some listeners have to be set to the TanaguruWriterProcessor component.
 * That explains the rewrite of the CrawlJob launch method.
 * At the end of the process, the files opened by Heritrix while the process
 * are properly closed and the listeners of the TanaguruWriterProcessor are unset.
 *
 * @author jkowalczyk
 */
public class TanaguruCrawlJob {

    private static final Logger LOGGER = Logger.getLogger(CrawlerImpl.class);
    private static final String urlStrToReplace = "# URLS HERE";
    private static final String WRITER_BEAN_NAME = "tanaguruWriter";
    private static final String DECIDE_RULE_SEQUENCE_BEAN_NAME = "scope";
    private File currentJobOutputDir;
    private static final int CRAWL_LAUNCHER_RETRY_TIMEOUT = 1000;
    private static final int CRAWL_LOGGER_TIMEOUT = 2000;
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

    private CrawlJob crawlJob;
    public CrawlJob getCrawlJob() {
        return crawlJob;
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

    private ContentWriter contentWriter;
    public void setContentWriter(ContentWriter contentWriter) {
        this.contentWriter = contentWriter;
    }
    
    private ExtractorCSSListener extractorCSSListener;
    public void setExtractorCSSListener(ExtractorCSSListener extractorCSSListener) {
        this.extractorCSSListener = extractorCSSListener;
    }

    private ExtractorHTMLListener extractorHTMLListener;
    public void setExtractorHTMLListener(ExtractorHTMLListener extractorHTMLListener) {
        this.extractorHTMLListener = extractorHTMLListener;
    }

    TanaguruWriterProcessor tanaguruWriterProcessor;
    DecideRuleSequence decideRuleSequence;

    public TanaguruCrawlJob(String[] url, String heritrixFileName, String outputDir, String crawlConfigFilePath) {
        this.outputDir = outputDir;
        this.crawlConfigFilePath = crawlConfigFilePath;
        File configFile = initializeCrawlContext(url, heritrixFileName);
        crawlJob = new CrawlJob(configFile);
    }

    /**
     * This method is the exposed launch method.
     */
    public void launchCrawlJob() {
        if (crawlJob.isLaunchable()) {
            Logger.getLogger(CrawlerImpl.class.getName()).info(
                    "crawljob is launchable");
            launchHeritrixCrawlJob();
            if (!crawlJob.isRunning()) {
                try {
                    Thread.sleep(CRAWL_LAUNCHER_RETRY_TIMEOUT);
                } catch (InterruptedException ex) {
                    LOGGER.error(ex);
                }
            }
        }
        Logger.getLogger(CrawlerImpl.class.getName()).info(
                "is crawlJob running? " + crawlJob.isRunning());
        while (crawlJob.isRunning()) {
            try {
                if (crawlJob.isUnpausable()) {
                    crawlJob.getCrawlController().getFrontier().run();
                }
                Thread.sleep(CRAWL_LOGGER_TIMEOUT);
            } catch (InterruptedException e) {
                // do nothing
            }
        }
        cleanUpWriterResources(crawlJob.getJobContext());
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
     * 
     * @param crawlJob
     */
    private void launchHeritrixCrawlJob() {
        if (crawlJob.isProfile()) {
            throw new IllegalArgumentException("Can't launch profile" + this);
        }

        CrawlController cc = crawlJob.getCrawlController();
        if (cc != null && cc.hasStarted()) {
            crawlJob.getJobLogger().log(Level.SEVERE, "Can't relaunch previously-launched assembled job");
            return;
        }

        crawlJob.validateConfiguration();
        if (!crawlJob.hasValidApplicationContext()) {
            crawlJob.getJobLogger().log(Level.SEVERE, "Can't launch problem configuration");
            return;
        }
        setListenerToWriter(crawlJob.getJobContext());
        AlertThreadGroup alertThreadGroup = new AlertThreadGroup(crawlJob.getShortName());
        alertThreadGroup.addLogger(crawlJob.getJobLogger());
        Thread launcher = new Thread(alertThreadGroup, crawlJob.getShortName() + " launchthread") {

            public void run() {
                startContext(crawlJob);
                CrawlController cc = crawlJob.getCrawlController();
                if (cc != null) {
                    cc.requestCrawlStart();
                }
            }
        };
        crawlJob.getJobLogger().log(Level.INFO, "Job launched");
        launcher.start();
    }

    /**
     * Start the context, catching and reporting any BeansExceptions.
     */
    private void startContext(CrawlJob crawlJob) {
        PathSharingContext ac = crawlJob.getJobContext();
        try {
            ac.start();
        } catch (BeansException be) {
            ac.close();
            ac = null;
        } catch (Exception e) {
            crawlJob.getJobLogger().log(Level.SEVERE, e.getClass().getSimpleName() + ": " + e.getMessage(), e);
            try {
                ac.close();
            } catch (Exception e2) {
                e2.printStackTrace(System.err);
            } finally {
                ac = null;
            }
        }
    }

    /**
     * This method initialize the heritrix context before starting the crawl
     * @return
     */
    private File initializeCrawlContext(String[] url, String heritrixFileName) {
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
            String uri;
            StringBuilder newContextFile = new StringBuilder();
            while ((c = in.readLine()) != null) {
                if (c.equalsIgnoreCase(urlStrToReplace)) {
                    for (int i = 0; i < url.length; i++) {
                        // first convert the URI in unicode
                        uri = UURIFactory.getInstance(url[i]).getEscapedURI();
                        // then escape the URI to be written in a xml file.
                        newContextFile.append(StringEscapeUtils.escapeXml(uri));
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
                } catch (IOException ex) {
                    LOGGER.error(ex);
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ex) {
                    LOGGER.error(ex);
                }
            }
        }
        return new File(currentJobOutputDir.getPath() + "/" + heritrixFileName);
    }

    /**
     * This methods sets the appropriate listeners to the tanaguruWriterProcessor
     *
     * @param crawljob
     * @param wr
     */
    private void setListenerToWriter(PathSharingContext ac) {
        tanaguruWriterProcessor =
                (TanaguruWriterProcessor) ac.getBean(WRITER_BEAN_NAME);
        tanaguruWriterProcessor.setExtractorHTMLListener(extractorHTMLListener);
        tanaguruWriterProcessor.setExtractorCSSListener(extractorCSSListener);
        tanaguruWriterProcessor.setContentWriter(contentWriter);
        decideRuleSequence =
                (DecideRuleSequence) ac.getBean(DECIDE_RULE_SEQUENCE_BEAN_NAME);
    }

    /**
     * This method returns the DecideRuleSequence instance injected to
     * the tanaguruWriterProcessor.
     * @return
     */
    public DecideRuleSequence getDecideRuleSequence() {
        return decideRuleSequence;
    }

    /**
     * This method returns the HtmlFilePattern instance injected to
     * the tanaguruWriterProcessor.
     * @return
     */
    public Pattern getHtmlFilePattern() {
        return tanaguruWriterProcessor.getHtmlFilePattern();
    }

    /**
     * This method returns the CssFilePattern instance injected to
     * the tanaguruWriterProcessor.
     * @return
     */
    public Pattern getCssFilePattern() {
        return tanaguruWriterProcessor.getCssFilePattern();
    }

    /**
     *
     * @return
     */
    public boolean isLaunchable() {
        return getCrawlJob().isLaunchable();
    }

    /**
     * 
     */
    public void checkXML(){
        getCrawlJob().checkXML();
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
                    boolean isDeleted = files[i].delete();
                    if (!isDeleted) {
                        LOGGER.error(
                                "The file " + files[i].getPath()
                                + " cannot be deleted");
                    }
                }
            }
        }
        return (file.delete());
    }

    /**
     * Heritrix lets its log files opened at the end of the crawl.
     * We have to close them "manually".
     */
    private void closeCrawlerLogFiles() {
        List<FileHandler> loggerHandlerList = new ArrayList<FileHandler>();
        for (Handler handler : crawlJob.getJobLogger().getHandlers()) {
            if (handler instanceof FileHandler) {
                ((FileHandler) handler).close();
                loggerHandlerList.add((FileHandler) handler);
            }
        }
        for (FileHandler fileHandler : loggerHandlerList) {
            crawlJob.getJobLogger().removeHandler(fileHandler);
        }
    }

   /**
     * This method cleans up the resources created by the TanaguruWriterProcessor
     * class to enable the TanaguruWriterProcessor instance to be garbaged
     * @param processor
     */
    private void cleanUpWriterResources(PathSharingContext ac) {
        tanaguruWriterProcessor.setExtractorCSSListener(null);
        tanaguruWriterProcessor.setContentWriter(null);
        tanaguruWriterProcessor.setCssRegexp(null);
        tanaguruWriterProcessor.setExtractorHTMLListener(null);
        tanaguruWriterProcessor.setHtmlRegexp(null);
        tanaguruWriterProcessor = null;
    }

}