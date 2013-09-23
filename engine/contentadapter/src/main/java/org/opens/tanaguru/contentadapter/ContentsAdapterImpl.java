/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
package org.opens.tanaguru.contentadapter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import org.apache.log4j.Logger;
import org.opens.tanaguru.contentadapter.html.AbstractHTMLCleaner;
import org.opens.tanaguru.contentadapter.html.HTMLCleanerImpl;
import org.opens.tanaguru.contentadapter.util.DocumentCaseInsensitiveAdapter;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.SSP;

/**
 * 
 * @author jkowalczyk
 */
public class ContentsAdapterImpl implements ContentsAdapter {

    private Set<ContentAdapter> contentAdapterSet =
            new HashSet<ContentAdapter>();
    private List<Content> contentList;
    private HTMLCleaner htmlCleaner;
    private HTMLParser htmlParser;
    private List<Content> result;
    private Boolean writeCleanHtmlInFile = false;
    private String tempFolderRootPath = "/var/tmp";
    private boolean removeDoctype = false;

    ContentsAdapterImpl(
            List<Content> contentList, 
            boolean writeCleanHtmlInFile, 
            String tempFolderRootPath, 
            HTMLCleaner htmlCleaner, 
            HTMLParser htmlParser) {
        super();
        this.contentList = contentList;
        this.writeCleanHtmlInFile = writeCleanHtmlInFile;
        this.tempFolderRootPath = tempFolderRootPath;
        this.htmlCleaner = htmlCleaner;
        this.htmlParser = htmlParser;
    }
    
    ContentsAdapterImpl(
            List<Content> contentList, 
            boolean writeCleanHtmlInFile, 
            String tempFolderRootPath, 
            HTMLCleaner htmlCleaner, 
            HTMLParser htmlParser, 
            boolean removeDoctype) {
        super();
        this.contentList = contentList;
        this.writeCleanHtmlInFile = writeCleanHtmlInFile;
        this.tempFolderRootPath = tempFolderRootPath;
        this.htmlCleaner = htmlCleaner;
        this.htmlParser = htmlParser;
        this.removeDoctype = removeDoctype;
    }

    public void setTempFolderRootPath(String tempFolderRootPath) {
        this.tempFolderRootPath = tempFolderRootPath;
    }

    @Override
    public List<Content> getResult() {
        return result;
    }

    @Override
    public void run() {
        result = run(contentList);
    }

    private List<Content> run(List<Content> contentList) {
        List<Content> localResult = new ArrayList<Content>();
        for (Content content : contentList) {
            // Unreachable resources (404 error) are saved in the list for reports
            // We only handle here the fetched content (HttpStatus=200)
            if (content instanceof SSP && content.getHttpStatusCode() == 200) {
                SSP ssp = (SSP) content;

                ssp.setDoctype(DocumentCaseInsensitiveAdapter.extractDoctypeDeclaration(ssp.getSource()));
                
                String dirtyHtml;
                if (removeDoctype) {
                    dirtyHtml = DocumentCaseInsensitiveAdapter.removeDoctypeDeclaration(ssp.getSource());
                } else {
                    dirtyHtml = ssp.getSource();
                }
                
                htmlCleaner.setDirtyHTML(dirtyHtml);

                htmlCleaner.run();

                ssp.setAdaptedContent(htmlCleaner.getResult());

                htmlCleaner.setDirtyHTML(null);
                if (writeCleanHtmlInFile) {
                    writeCleanDomInFile(ssp);
                }
                htmlParser.setSSP(ssp);
                htmlParser.run();

                for (ContentAdapter contentAdapter : contentAdapterSet) {
                    localResult.addAll(contentAdapter.getContentList());
                }
                if (removeDoctype){
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
        this.contentAdapterSet = contentAdapterSet;
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
        if (writeCleanHtmlInFile) {
            // @debug
            String fileName = null;
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
                Logger.getLogger(this.getClass()).warn(ex);
            }
        }
    }

    @Override
    public void setWriteCleanHtmlInFile(Boolean writeCleanHtmlInFile) {
        this.writeCleanHtmlInFile = writeCleanHtmlInFile;
    }

    @Override
    public void setRemoveDoctype(Boolean removeDoctype) {
        this.removeDoctype = removeDoctype;
    }

}