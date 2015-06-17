/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.crawler.framework;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.regex.Pattern;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.log4j.Logger;
import org.archive.crawler.event.CrawlStateEvent;
import org.archive.crawler.framework.CrawlController;
import org.archive.crawler.framework.CrawlController.State;
import org.archive.crawler.framework.CrawlJob;
import org.archive.crawler.reporting.AlertThreadGroup;
import org.archive.modules.deciderules.DecideRuleSequence;
import org.archive.spring.PathSharingContext;
import org.tanaguru.crawler.ContentWriter;
import org.tanaguru.crawler.exception.CrawlerException;
import org.tanaguru.crawler.extractor.listener.ExtractorCSSListener;
import org.tanaguru.crawler.extractor.listener.ExtractorHTMLListener;
import org.tanaguru.crawler.processor.TanaguruWriterProcessor;
import org.tanaguru.crawler.util.CrawlConfigurationUtils;
import org.tanaguru.entity.parameterization.Parameter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationListener;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * <p>
 * This class embeds a heritrix CrawlJob object and deals with the instanciation,
 * the launch, the stop and the clean-up. Between the instanciation and the launch
 * some listeners have to be set to the TanaguruWriterProcessor component.
 * That explains the rewrite of the CrawlJob launch method.
 * At the end of the process, the files opened by Heritrix while the process
 * are properly closed and the listeners of the TanaguruWriterProcessor are unset.
 * </p>
 * 
 * @author jkowalczyk
 */
public class TanaguruCrawlJob implements ApplicationListener<CrawlStateEvent>{

    private static final Logger LOGGER = Logger.getLogger(TanaguruCrawlJob.class);
    private static final String WRITER_BEAN_NAME = "tanaguruWriter";
    private static final String DECIDE_RULE_SEQUENCE_BEAN_NAME = "scope";
    private File currentJobOutputDir;
    private String outputDir = System.getProperty("user.dir")  + "/output";
    private final CrawlJob crawlJob;
    private String crawlConfigFilePath = "/etc/tanaguru/context/crawler/";
    private ContentWriter contentWriter;
    private ExtractorCSSListener extractorCSSListener;
    private ExtractorHTMLListener extractorHTMLListener;
    private TanaguruWriterProcessor tanaguruWriterProcessor;
    private DecideRuleSequence decideRuleSequence;

    /**
     * Default constructor
     * 
     * @param urlList
     * @param heritrixFileName
     * @param outputDir
     * @param crawlConfigFilePath
     * @param paramSet 
     */
    public TanaguruCrawlJob(
            Collection<String> urlList,
            String heritrixFileName,
            String outputDir,
            String crawlConfigFilePath,
            Set<Parameter> paramSet) {
        this.outputDir = outputDir;
        this.crawlConfigFilePath = crawlConfigFilePath;
        File configFile = initializeCrawlContext(urlList, paramSet, heritrixFileName);
        crawlJob = new CrawlJob(configFile);
    }

    public String getOutputDir() {
        return this.outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public CrawlJob getCrawlJob() {
        return crawlJob;
    }

    public String getCrawlConfigFilePath() {
        return this.crawlConfigFilePath;
    }

    public void setCrawlConfigFilePath(String crawlConfigFilePath) {
        this.crawlConfigFilePath = crawlConfigFilePath;
    }

    public void setContentWriter(ContentWriter contentWriter) {
        this.contentWriter = contentWriter;
    }

    public void setExtractorCSSListener(ExtractorCSSListener extractorCSSListener) {
        this.extractorCSSListener = extractorCSSListener;
    }

    public void setExtractorHTMLListener(ExtractorHTMLListener extractorHTMLListener) {
        this.extractorHTMLListener = extractorHTMLListener;
    }

    /**
     * This method is the exposed launch method.
     */
    public void launchCrawlJob() {
        if (crawlJob.isLaunchable()) {
            LOGGER.debug("crawljob is launchable");
            launchHeritrixCrawlJob();
            synchronized (this) {
                try {
                    this.wait();
                } catch (InterruptedException e) {}
                crawlJob.getJobContext().getApplicationListeners().remove(this);
            }
        }

        cleanUpWriterResources(crawlJob.getJobContext());
        crawlJob.terminate();
        if (crawlJob.teardown()) {
            closeCrawlerLogFiles();
            if (!removeConfigFile(currentJobOutputDir)) {
                LOGGER.warn(
                        "Configuration Heritrix files cannot be deleted");
            }
        } else {
            LOGGER.warn(
                    "The crawljob is not teardowned");
        }
    }

    private void launchHeritrixCrawlJob() {
        if (crawlJob.isProfile()) {
            throw new IllegalArgumentException("Can't launch profile" + this);
        }

        CrawlController cc = crawlJob.getCrawlController();
        if (cc != null && cc.hasStarted()) {
            LOGGER.error("Can't relaunch previously-launched assembled job");
            return;
        }

        crawlJob.validateConfiguration();
        if (!crawlJob.hasValidApplicationContext()) {
            LOGGER.error("Can't launch problem configuration");
            return;
        }
        LOGGER.debug("Job validated");
        
        setListenerToWriter(crawlJob.getJobContext());
        AlertThreadGroup alertThreadGroup = new AlertThreadGroup(crawlJob.getShortName());
        alertThreadGroup.addLogger(crawlJob.getJobLogger());
        Thread launcher = new Thread(alertThreadGroup, crawlJob.getShortName() + " launchthread") {

            @Override
            public void run() {
                // the start context has to be done in the thread to access 
                // the logger set to the Runnable object
                startContext(crawlJob);
                CrawlController cc = crawlJob.getCrawlController();
                LOGGER.debug("Request crawl start");
                if (cc != null) {
                    cc.requestCrawlStart();
                    LOGGER.debug(cc.getState());
                    LOGGER.debug("crawl start requested");
                }
            }
        };
        launcher.start();
    }

    /**
     * Start the context, catching and reporting any BeansExceptions.
     */
    private void startContext(CrawlJob crawlJob) {
        LOGGER.debug("Starting context");
        PathSharingContext ac = crawlJob.getJobContext();
        ac.addApplicationListener(this);
        try {
            ac.start();
        } catch (BeansException be) {
            LOGGER.warn(be.getMessage());
            ac.close();
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
            try {
                ac.close();
            } catch (Exception e2) {
                e2.printStackTrace(System.err);
            } finally {}
        }
        LOGGER.debug("Context started");
    }

    /**
     * 
     * @param url
     * @param crawlParameterSet
     * @param heritrixFileName
     * @return
     */
    private File initializeCrawlContext(Collection<String> urlList, Set<Parameter> crawlParameterSet, String heritrixFileName) {
        buildOutputDirectory();
        BufferedReader in = null;
        FileWriter fw = null;
        try {
            LOGGER.debug(
                    "crawlConfigFilePath: " + crawlConfigFilePath + " for copy");
            String filepath = crawlConfigFilePath + "/" + heritrixFileName;
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(filepath);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("filepath : "+filepath);
                for (Parameter param : crawlParameterSet) {
                    LOGGER.debug(param.getParameterElement().getParameterElementCode() + " "+ param.getValue());
                }
            }
            doc = setOptionToDocument(urlList, crawlParameterSet, doc);
            //write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            String resultFileName = currentJobOutputDir.getPath() + "/" + heritrixFileName;
            StreamResult result =  new StreamResult(new File(resultFileName));
            transformer.transform(source, result);

        } catch (IOException | ParserConfigurationException | SAXException ex) {
            LOGGER.error(ex);
            throw new CrawlerException(ex);
        } catch (TransformerConfigurationException ex) {
            LOGGER.error(ex);
            throw new CrawlerException(ex);
        } catch (TransformerException ex) {
            LOGGER.error(ex);
            throw new CrawlerException(ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    LOGGER.error(ex);
                    throw new CrawlerException(ex);
                }
            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException ex) {
                    LOGGER.error(ex);
                    throw new CrawlerException(ex);
                }
            }
        }
        return new File(currentJobOutputDir.getPath() + "/" + heritrixFileName);
    }

    /**
     * Create the output directory used to copy temporary files needed by
     * Heritrix.
     */
    private void buildOutputDirectory() {
        // Create one directory
        currentJobOutputDir = new File(outputDir + "/" + "crawl" + "-" + new Date().getTime());
        if (!currentJobOutputDir.exists()) {
            boolean success = currentJobOutputDir.mkdir();
            if (success) {
                LOGGER.debug("Directory: " + currentJobOutputDir + " created");
            }
        }
    }

    /**
     * 
     * @param url
     * @param crawlParameterSet
     * @param fileContent
     * @return
     * @throws IOException
     */
    private Document setOptionToDocument(Collection<String> urlList, Set<Parameter> crawlParameterSet, Document doc)
            throws IOException{

        doc.getFirstChild();
        return CrawlConfigurationUtils.getInstance().setUpDocument(doc, crawlParameterSet, urlList);
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

    public boolean isLaunchable() {
        return getCrawlJob().isLaunchable();
    }

    public void checkXML() {
        getCrawlJob().checkXML();
    }

    /**
     * Clean-up configuration files before leaving
     * @param file
     * @return
     */
    private boolean removeConfigFile(File file) {
        if (file.exists()) {
            for (File file1 : file.listFiles()) {
                if (file1.isDirectory()) {
                    removeConfigFile(file1);
                } else {
                    boolean isDeleted = file1.delete();
                    if (!isDeleted) {
                        LOGGER.error("The file " + file1.getPath() + " cannot be deleted");
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
        List<FileHandler> loggerHandlerList = new ArrayList<>();
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
        if (tanaguruWriterProcessor != null) {
            tanaguruWriterProcessor.setExtractorCSSListener(null);
            tanaguruWriterProcessor.setContentWriter(null);
            tanaguruWriterProcessor.setCssRegexp(null);
            tanaguruWriterProcessor.setExtractorHTMLListener(null);
            tanaguruWriterProcessor.setHtmlRegexp(null);
            tanaguruWriterProcessor = null;
        }
    }

    @Override
    public void onApplicationEvent(CrawlStateEvent e) {
        LOGGER.debug("CrawlJob changes state to "+e.getState());
        if (e.getState().equals(State.FINISHED)) {
            synchronized (this) {
                notify();
            }
        } else if (e.getState().equals(State.PAUSED)) {
            manuallyRunCrawlJob();
        } else if (e.getState().equals(State.RUNNING)) {
            LOGGER.info("crawljob is running");
        }
    }

    /**
     * In some cases, the crawljob state is set to PAUSE after initialisation.
     * In this case, the run needs to be force
     */
    private void manuallyRunCrawlJob() {
        if (crawlJob.isRunning() && crawlJob.isUnpausable()) {
            crawlJob.getCrawlController().getFrontier().run();
        }
    }

}