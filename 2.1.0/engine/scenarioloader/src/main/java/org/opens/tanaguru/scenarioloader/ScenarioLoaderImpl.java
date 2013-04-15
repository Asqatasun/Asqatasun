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
package org.opens.tanaguru.scenarioloader;

import com.sebuilder.interpreter.IO;
import com.sebuilder.interpreter.NewPageListener;
import com.sebuilder.interpreter.Script;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.apache.tools.ant.filters.StringInputStream;
import org.json.JSONException;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.opens.tanaguru.contentloader.HarFileContentLoaderFactory;
import org.opens.tanaguru.crawler.util.CrawlUtils;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.util.factory.DateFactory;
import org.opens.webdriver.tools.ProfileFactory;

/**
 *
 * @author jkowalczyk
 */
public class ScenarioLoaderImpl implements ScenarioLoader, NewPageListener {

    private static final Logger LOGGER = Logger.getLogger(ScenarioLoaderImpl.class);
    private static final String UFT8 = "UTF-8";
    private List<Content> result = new ArrayList<Content>();
    private int pageRank = 1;

    @Override
    public List<Content> getResult() {
        return result;
    }
    
    private WebResource webResource;
    public WebResource getWebResource() {
        return webResource;
    }

    @Override
    public void setWebResource(WebResource webResource) {
        this.webResource = webResource;
    }
    
//    private HarFileContentLoaderFactory harFileContentLoaderFactory;
//    public void setHarFileContentLoaderFactory(HarFileContentLoaderFactory harFileContentLoaderFactory) {
//        this.harFileContentLoaderFactory = harFileContentLoaderFactory;
//    }
    
    /**
     * 
     */
    private ProfileFactory profileFactory;
    
    private WebResourceDataService webResourceDataService;
    public void setWebResourceDataService(WebResourceDataService webResourceDataService) {
        this.webResourceDataService = webResourceDataService;
    }
    
    private ContentDataService contentDataService;
    public void setContentDataService(ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }
    
    private ContentFactory contentFactory;
    public void setContentFactory(ContentFactory contentFactory) {
        this.contentFactory = contentFactory;
    }
    
    private DateFactory dateFactory;
    public void setDateFactory(DateFactory dateFactory) {
        this.dateFactory = dateFactory;
    }
    
    List<SSPInfo> sspInfoList = new LinkedList<SSPInfo>();
    
    /**
     * The scenario
     */
    private String scenario;

    ScenarioLoaderImpl(
            WebResource webResource,
            String scenario) {
        super();
        this.scenario = scenario;
        this.profileFactory = ProfileFactory.getInstance();
        this.webResource = webResource;
    }
    
    ScenarioLoaderImpl(
            WebResource webResource,
            String scenario, 
            HarFileContentLoaderFactory harFileContentLoaderFactory) {
        super();
        this.scenario = scenario;
//        this.harFileContentLoaderFactory = harFileContentLoaderFactory;
        this.profileFactory = ProfileFactory.getInstance();
        this.webResource = webResource;
    }

    @Override
    public void run() {
        try {
            LOGGER.info("Launch Scenario "   + scenario );
            FirefoxProfile firefoxProfile = profileFactory.getScenarioProfile();
            Script script = IO.read(scenario, firefoxProfile);
            script.addNewPageListener(this);
            try {
                if (script.run()) {
                    LOGGER.info(webResource.getURL()  + " succeeded");
                } else {
                    LOGGER.info(webResource.getURL() + " failed");
                }
            } catch (RuntimeException re) {
                LOGGER.warn(re.getMessage());
            }    
//            for (SSPInfo sspInfo : sspInfoList) {
//                LOGGER.info(" retrieved  in order " +sspInfo.getUrl());
//            }
            // Call to the harFileContentLoader to retrieve fetched data
//            HarFileContentLoader harFileContentLoader = 
//                    harFileContentLoaderFactory.create(
//                        webResource, 
//                        profileFactory.getNetExportPath(firefoxProfile));
//            harFileContentLoader.run();
//            result.addAll(harFileContentLoader.getResult());
//            addAdaptedSourceToSSP(harFileContentLoader.getResult());
            // The profileFactory maintains an internal to associate a profile 
            // with a generated folder on which the result Har files are written.
            // When the scenario is terminated and all data have been retrieved
            // the entry into this map has to be removed.
            profileFactory.shutdownFirefoxProfile(firefoxProfile);
        } catch (IOException ex) {
            LOGGER.warn(ex.getMessage());
        } catch (JSONException ex) {
            LOGGER.warn(ex.getMessage());
        }
    }

    /**
     * 
     * @param url
     * @param sourceCode 
     */
    private void fireNewSSP(String url, String sourceCode) {
        LOGGER.info("fire New SSP " + url);
        if (StringUtils.isEmpty(sourceCode)) {
            LOGGER.info("Emtpy SSP " + url + " not saved");
            return;
        }
        String charset = UFT8;
        try {
            charset = CrawlUtils.extractCharset(new StringInputStream(sourceCode));
        } catch (IOException ex) {
            Logger.getLogger(this.getClass()).warn(ex);
        }
        SSP ssp = contentFactory.createSSP(
                dateFactory.createDate(),
                url,
                sourceCode,
                getWebResource(url),
                HttpStatus.SC_OK);
        ssp.setCharset(charset);
        contentDataService.saveOrUpdate(ssp);
        result.add(ssp);
        
        // XXX : To do, remove these lines if the har file reader is never used
//        try {
//            SSPInfo sspInfo = new SSPInfo(url, new String(sourceCode.getBytes(), charset));
//            sspInfoList.add(sspInfo);
//            LOGGER.info("afeter encode " + sspInfo.getSourceCode());
    //        Page page = null;
    //        if (webResource instanceof Page) {
    //            if (!StringUtils.equals(url, webResource.getURL())) {
    //                webResource.setURL(url);
    //            }
    //            page = (Page)webResource;
    //        } else if (webResource instanceof Site) {
    //            page = webResourceDataService.createPage(url);
    //            ((Site) webResource).addChild(page);
    //        }
    //        page = (Page) webResourceDataService.saveOrUpdate(page);
    //        Content content = contentFactory.createSSP(
    //                dateFactory.createDate(),
    //                page.getURL(),
    //                sourceCode,
    //                page,
    //                HttpStatus.SC_OK);
    //        contentDataService.saveOrUpdate(content);
    //        if (result == null) {
    //            result = new ArrayList<Content>();
    //        }
    //        result.add(content);
//        } catch (UnsupportedEncodingException ex) {
//            java.util.logging.Logger.getLogger(ScenarioLoaderImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }
    
    @Override
    public void fireNewPage(String url, String sourceCode) {
        fireNewSSP(url, sourceCode);
    }

    /**
     * All the SSP are initialised with the raw html extracted from the Har file.
     * The interpreted HTML has been precedently retrieved from the scenario 
     * executor. This method is supposed to reassociate the SSP with their 
     * interpreted HTML. 
     */
//    private void addAdaptedSourceToSSP (List<Content> contentList) {
//        Iterator<SSPInfo> iter = sspInfoList.iterator();
//        for (Content content : contentList) {
//            if (content instanceof SSP && iter.hasNext()) {
//                SSP ssp = (SSP) content;
//                String uri = ssp.getURI();
//                LOGGER.info(uri);
//                if (ssp.getHttpStatusCode() == HttpStatus.SC_OK) {
//                    Page page = getWebResource(uri);
//                    String sourceCode = iter.next().getSourceCode();
//                    try {
//                        ssp.setCharset(CrawlUtils.extractCharset(new StringInputStream(sourceCode)));
//                        Logger.getLogger(this.getClass()).info("charset " + ssp.getCharset());
//                    } catch (IOException ex) {
//                        ssp.setCharset(UFT8);
//                    }
//                    ssp.setSource(encodeSourceCodeToUtf8(sourceCode, ssp.getCharset()));
//                    ssp.setPage(page);
//                }
//            }
//            Logger.getLogger(this.getClass()).debug("new content added " + content.getURI());
//            contentDataService.saveOrUpdate(content);
//            result.add(content);
//        }
//    }

    private Page getWebResource(String url) {
        Page page = null;
        if (webResource instanceof Page) {
            if (!StringUtils.equals(url, webResource.getURL())) {
                webResource.setURL(url);
            }
            page = (Page)webResource;
            page.setRank(1);
        } else if (webResource instanceof Site) {
            page = webResourceDataService.createPage(url);
            page.setRank(pageRank);
            pageRank++;
            ((Site) webResource).addChild(page);
        }
        page = (Page) webResourceDataService.saveOrUpdate(page);
        return page;
    }
    
    /**
     * Inner class that handle an Url and its source code
     */
    private class SSPInfo {
        
        private String url;
        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        
        private String sourceCode;
        public String getSourceCode() {
            return sourceCode;
        }

        public void setSourceCode(String sourceCode) {
            this.sourceCode = sourceCode;
        }
        
        public SSPInfo(String url, String sourceCode) {
            this.url = url;
            this.sourceCode = sourceCode;
        }
    }
 
    private String encodeSourceCodeToUtf8(String sourceCode, String charset) {
        Charset incoming = Charset.forName(charset);
        ByteBuffer inputBuffer = ByteBuffer.wrap(sourceCode.getBytes());
        CharBuffer data = incoming.decode(inputBuffer);
        Charset utf8charset = Charset.forName(UFT8);
        ByteBuffer outputBuffer = utf8charset.encode(data);
        byte[] outputData = outputBuffer.array();
        return new String(outputData);
    }

}