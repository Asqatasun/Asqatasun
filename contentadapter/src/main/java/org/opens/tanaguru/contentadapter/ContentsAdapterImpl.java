package org.opens.tanaguru.contentadapter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.SSP;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.opens.tanaguru.contentadapter.util.DocumentCaseInsensitiveAdapter;

/**
 * 
 * @author ADEX
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

    ContentsAdapterImpl(List<Content> contentList, boolean writeCleanHtmlInFile, String tempFolderRootPath, HTMLCleaner htmlCleaner, HTMLParser htmlParser) {
        super();
        this.contentList = contentList;
        this.writeCleanHtmlInFile = writeCleanHtmlInFile;
        this.tempFolderRootPath = tempFolderRootPath;
        this.htmlCleaner = htmlCleaner;
        this.htmlParser = htmlParser;
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
                htmlCleaner.setDirtyHTML(
                        DocumentCaseInsensitiveAdapter.removeDoctypeDeclaration(ssp.getSource()));

                htmlCleaner.run();

                ssp.setAdaptedContent(
                        DocumentCaseInsensitiveAdapter.removeLowerCaseTags(htmlCleaner.getResult()));
                htmlCleaner.setDirtyHTML(null);
                if (writeCleanHtmlInFile) {
                    writeCleanDomInFile(ssp);
                }

                htmlParser.setSSP(ssp);
                htmlParser.run();

                for (ContentAdapter contentAdapter : contentAdapterSet) {
                    localResult.addAll(contentAdapter.getContentList());
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
                Logger.getLogger(ContentsAdapterImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setWriteCleanHtmlInFile(Boolean writeCleanHtmlInFile) {
        this.writeCleanHtmlInFile = writeCleanHtmlInFile;
    }
}
