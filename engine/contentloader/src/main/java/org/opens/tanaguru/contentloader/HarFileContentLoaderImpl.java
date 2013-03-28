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
package org.opens.tanaguru.contentloader;

//import edu.umass.cs.benchlab.har.*;
//import edu.umass.cs.benchlab.har.tools.HarFileReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.util.factory.DateFactory;

/**
 * Work in progress, this class is not used for the moment but kept for further
 * development.
 * @author jkowalczyk
 */
public class HarFileContentLoaderImpl implements HarFileContentLoader {

    private static final Logger LOGGER = Logger.getLogger(HarFileContentLoaderImpl.class);
    
    private static final Pattern HAR_EXTENSION_PATTERN = Pattern.compile(".*(?i)(\\.(har))$");
    
    private static final String HTML_MIME_TYPE = "text/html";
    private static final String CSS_MIME_TYPE = "text/css";
    
//    private static final String GET_METHOD_KEY = "GET";
    
    /**
     * THe content factory instance needed to create the SSP
     */
    private ContentFactory contentFactory;
    
    /**
     * The list of created content
     */
    private List<Content> result = new LinkedList<Content>();
    
    private WebResource webResource;
    public WebResource getWebResource() {
        return this.webResource;
    }
    
    @Override
    public void setWebResource(WebResource webResource) {
        this.webResource = webResource;
    }
    
    /**
     * The dateFactory instance
     */
    private DateFactory dateFactory;
    
    /**
     * The path of the folder containing the har file to read
     */
    private String harFolderPath;
    
    /**
     * 
     */
    private Set<String> relatedContentUrlSet = new HashSet<String>();
    
    private SSP currentSSP = null;
    
    private boolean foundSSP = false;
    
    /**
     * Constructor
     * 
     * @param contentFactory
     * @param fileMap
     * @param contentDataService
     */
    HarFileContentLoaderImpl(
            ContentFactory contentFactory,
            DateFactory dateFactory,
            WebResource webResource, 
            String harFolderPath) {
        super();
        this.contentFactory = contentFactory;
        this.dateFactory = dateFactory;
        this.harFolderPath = harFolderPath;
        this.webResource = webResource;
        LOGGER.info("this.harFolderPath    " + this.harFolderPath);
    }

    @Override
    public List<Content> getResult() {
        return result;
    }

    @Override
    public void run() {
        try {
            readHarFolderResultAndExtractData(harFolderPath);
        } catch (IOException ioe) {
            LOGGER.warn(ioe);
        }
    }

    /**
     * Parse a folder to retrieve all the Har file and then extract data from
     * these files.
     * 
     * @param resultPath
     * @throws IOException 
     */
    private void readHarFolderResultAndExtractData(String resultPath) throws IOException {
        File folder = new File(resultPath);
        FilenameFilter ff = new RegexFileFilter(HAR_EXTENSION_PATTERN);
        File[] listOfFiles = folder.listFiles(ff);
        Arrays.sort(listOfFiles, new Comparator<File>() {
            @Override
            public int compare(File f1, File f2) {
                return f1.getName().substring(0, f1.getName().length() - 4)
                        .compareTo(
                        f2.getName().substring(0, f2.getName().length() - 4));
            }
        });

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                LOGGER.debug("File " + listOfFiles[i].getName());
                foundSSP = false;
                currentSSP = null;
//                parse(listOfFiles[i]);
            } else if (listOfFiles[i].isDirectory()) {
                LOGGER.debug("Directory " + listOfFiles[i].getName());
            }
        }
    }

    /**
     * Only one ssp can be found by har file. As soon as a ssp has been found
     * all the others are ignored.
     * 
     * The ssp is inialised with the raw source code. It can be overidden later 
     * (in the scenarioloader component for example)
     * 
     * @param url
     * @param sourceCode 
     */
//    private void fireNewSSP(String url, HarContent content) {
//        LOGGER.info("fire New SSP " + url);
//        if (foundSSP) {
//            LOGGER.info("SSP already found, " + url + " is ignored");
//            return;
//        }
//        int httpStatus = HttpStatus.SC_OK;
//        if (content.getSize() == 0) {
//            LOGGER.info("Emtpy SSP " + url + " saved as 404");
//            httpStatus = HttpStatus.SC_NOT_FOUND;
//        }
//        String sourceCode="";
//        if (!StringUtils.isEmpty(content.getText())) {
//            sourceCode=content.getText();
//        }
//        currentSSP = contentFactory.createSSP(
//                dateFactory.createDate(),
//                url,
//                sourceCode,
//                null,
//                httpStatus);
//        foundSSP = true;
//    }

    /**
     * 
     * @param url
     * @param sourceCode 
     */
    private void fireNewRelatedContent(String url, String sourceCode) {
        if (relatedContentUrlSet.contains(url)) {
            LOGGER.info("Already encountered Related content " + url);
            return; 
        } else {
            LOGGER.info("fire New RelatedContent " + url);
        }
        Content content = contentFactory.createStylesheetContent(
                dateFactory.createDate(),
                url,
                null,
                sourceCode,
                HttpStatus.SC_OK);
        result.add(content);
        relatedContentUrlSet.add(url);
    }

    private void fireNewErrorPage(int httpStatus, String url) {
        if (foundSSP) {
            return;
        }
        currentSSP = contentFactory.createSSP(
                dateFactory.createDate(),
                url,
                null,
                null,
                httpStatus);
    }
    
    /**
     * Parse the Har file to extract usefull data (CSS and HTML files).
     * Only resources retrieved from a GET with a 200 Http status are treated.
     * 
     * @param file a Http Archive file.
     * @return A script, ready to run.
     * @throws IOException If anything goes wrong with interpreting the JSON.
     */
//    private void parse(File file) throws IOException {
//        HarFileReader r = new HarFileReader();
//        HarLog log = r.readHarFile(file);
//        HarEntries entries = log.getEntries();
//        // first of all we check whether the pageTiming values are not null or 
//        // negative which would mean that no content has been retrieved
//        if (!checkPageTiming(log)) {
//            return;
//        }
//        for (HarEntry he : entries.getEntries()) {
//            LOGGER.debug(he.getRequest().getUrl());
//            LOGGER.debug(he.getResponse().getContent().getMimeType());
//            LOGGER.debug(he.getRequest().getMethod());
//            LOGGER.debug(he.getResponse().getStatus());
//            int status = he.getResponse().getStatus();
//            if (status == HttpStatus.SC_OK || status == HttpStatus.SC_NOT_MODIFIED) {
//                if (isEntryHtml(he)) {
//                    fireNewSSP(he.getRequest().getUrl(), he.getResponse().getContent());
//                } else if (isEntryCss(he)) {
//                    fireNewRelatedContent(he.getRequest().getUrl(), he.getResponse().getContent().getText());
//                }
//            } else if (isEntryHtml(he)) {
//                fireNewErrorPage(status, he.getRequest().getUrl());
//            }
//        }
//        if (currentSSP != null) {
//            result.add(currentSSP);
//        }
//    }

    /**
     * 
     * @param he
     * @return 
     *      whether the current HarEntry is a Html entry
     */
//    private boolean isEntryHtml(HarEntry he) {
//        return StringUtils.equalsIgnoreCase(he.getResponse().getContent().getMimeType(), HTML_MIME_TYPE);
//    }
    
    /**
     * 
     * @param he
     * @return 
     *      whether the current HarEntry is a Css entry
     */
//    private boolean isEntryCss(HarEntry he) {
//        return StringUtils.equalsIgnoreCase(he.getResponse().getContent().getMimeType(), CSS_MIME_TYPE);
//    }
    
    /**
     * This method determines whether some content has been retrieved. To do so
     * we check whether the pageTiming values are not null or negative which 
     * would mean that no content has been retrieved.
     * @param log
     * @return
     *      whether some content has been retrieved
     */
//    private boolean checkPageTiming(HarLog log) {
//        if (log.getPages().getPages().size() == 1) {
//            HarPage hp = log.getPages().getPages().iterator().next();
//            if (hp.getPageTimings().getOnContentLoad() <=0 && 
//                    hp.getPageTimings().getOnLoad() <=0) {
//                return false;
//            }
//        }
//        return true;
//    }
}