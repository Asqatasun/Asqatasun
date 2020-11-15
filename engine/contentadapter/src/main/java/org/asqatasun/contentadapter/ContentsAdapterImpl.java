/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.contentadapter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.apache.commons.collections.CollectionUtils;
import org.asqatasun.contentadapter.html.AbstractHTMLCleaner;
import org.asqatasun.contentadapter.html.HTMLCleanerImpl;
import org.asqatasun.contentadapter.util.AdaptationActionVoter;
import org.asqatasun.contentadapter.util.DocumentCaseInsensitiveAdapter;
import org.asqatasun.entity.audit.Content;
import org.asqatasun.entity.audit.SSP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author jkowalczyk
 */
public class ContentsAdapterImpl implements ContentsAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentsAdapterImpl.class);

    private Collection<Content> contentList;
    private HTMLCleaner htmlCleaner;
    private HTMLParser htmlParser;
    private Collection<Content> result;
    private String tempFolderRootPath;
    private boolean xmlizeContent = false;
    private boolean parseAndRetrievelRelatedContent = true;

    ContentsAdapterImpl(
            Collection<Content> contentList,
            String tempFolderRootPath, 
            HTMLCleaner htmlCleaner, 
            HTMLParser htmlParser) {
        super();
        this.contentList = contentList;
        this.tempFolderRootPath = tempFolderRootPath;
        this.htmlCleaner = htmlCleaner;
        this.htmlParser = htmlParser;
    }
    
    public void setTempFolderRootPath(String tempFolderRootPath) {
        this.tempFolderRootPath = tempFolderRootPath;
    }

    @Override
    public Collection<Content> getResult() {
        return result;
    }

    @Override
    public void run() {
        result = run(contentList);
    }

    private Collection<Content> run(Collection<Content> contentList) {
        Collection<Content> localResult = new ArrayList<>();
        for (Content content : contentList) {
            // Unreachable resources (404 error) are saved in the list for reports
            // We only handle here the fetched content (HttpStatus=200)
            if (content instanceof SSP && content.getHttpStatusCode() == 200) {
                LOGGER.debug("Adapting " + content.getURI());
                SSP ssp = (SSP) content;
                htmlCleaner.setDirtyHTML(ssp.getSource());
                htmlCleaner.run();
                ssp.setAdaptedContent(htmlCleaner.getResult());
                htmlCleaner.setDirtyHTML(null);
                writeCleanDomInFile(ssp);
                
                if (parseAndRetrievelRelatedContent) {
                    htmlParser.setSSP(ssp);
                    htmlParser.run();
                } else {
                    LOGGER.debug("no Html parse executed for the current audit");
                }
                
                if (xmlizeContent){
                    AbstractHTMLCleaner cleaner = new HTMLCleanerImpl();
                    cleaner.setDirtyHTML(ssp.getAdaptedContent());
                    cleaner.run();
                    ssp.setAdaptedContent(DocumentCaseInsensitiveAdapter.removeLowerCaseTags(cleaner.getResult()));
                }
                localResult.add(ssp);
            }
        }
        return localResult;
    }

    @Override
    public void setContentAdapterSet(Set<ContentAdapter> contentAdapterSet) {
//        this.contentAdapterSet = contentAdapterSet;
    }

    @Override
    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    @Override
    public void setHTMLCleaner(HTMLCleaner htmlCleaner) {
        this.htmlCleaner = htmlCleaner;
    }

    @Override
    public void setHTMLParser(HTMLParser htmlParser) {
        this.htmlParser = htmlParser;
    }

    private void writeCleanDomInFile(SSP ssp) {
        if (LOGGER.isDebugEnabled()) {
            // @debug
            String fileName;
            int lastIndexOfSlash = ssp.getURI().lastIndexOf("/");
            if (lastIndexOfSlash == ssp.getURI().length() - 1) {
                fileName = "index.html";
            } else {
                fileName = ssp.getURI().substring(lastIndexOfSlash + 1);
            }
            try {
                FileWriter fw = new FileWriter(tempFolderRootPath + File.separator + htmlCleaner.getCorrectorName()
                        + '-' + new Date().getTime() + '-' + fileName);
                fw.write(ssp.getDOM());
                fw.close();
            } catch (IOException ex) {
                LOGGER.warn(ex.getMessage());
            }
        }
    }

    /**
     * 
     * @param parseHtmlVoter 
     */
    public void setParseHtmlVoter(AdaptationActionVoter parseHtmlVoter) {
        if (!CollectionUtils.isEmpty(contentList) && parseHtmlVoter != null){
            this.parseAndRetrievelRelatedContent = 
                    parseHtmlVoter.doesExecute(contentList.iterator().next().getAudit());
            return;
        }
        this.parseAndRetrievelRelatedContent = true;
    }
    
    /**
     * 
     * @param xmlizeVoter 
     */
    public void setXmlizeVoter(AdaptationActionVoter xmlizeVoter) {
        if (!CollectionUtils.isEmpty(contentList) && xmlizeVoter != null){
            this.xmlizeContent = xmlizeVoter.doesExecute(contentList.iterator().next().getAudit());
            return;
        }
        this.xmlizeContent = false;
    }


}
