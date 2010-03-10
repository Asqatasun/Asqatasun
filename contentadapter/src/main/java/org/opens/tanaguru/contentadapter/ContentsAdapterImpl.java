package org.opens.tanaguru.contentadapter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.opens.tanaguru.contentadapter.css.CSSContentAdapter;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.SSP;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.opens.tanaguru.contentadapter.js.JSContentAdapter;
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
    private boolean initialized = false;
    private List<Content> result;
    private Boolean writeCleanHtmlInFile = false;

    public ContentsAdapterImpl() {
        super();
    }

    @Override
    public List<Content> getResult() {
        return result;
    }

    private void initialize() {
        if (initialized) {
            return;
        }

        htmlParser.setContentAdapterSet(contentAdapterSet);
        initialized = true;
    }

    @Override
    public void run() {
        initialize();

        result = run(contentList);
    }

    private List<Content> run(List<Content> contentList) {
        List<Content> localResult = new ArrayList<Content>();
        for (Content content : contentList) {
            if (content instanceof SSP) {
                SSP ssp = (SSP) content;

                htmlCleaner.setDirtyHTML(ssp.getSource());
                htmlCleaner.run();

                ssp.setDOM(DocumentCaseInsensitiveAdapter.removeLowerCaseTags(htmlCleaner.getResult()));

                if (writeCleanHtmlInFile) {
                    writeCleanDomInFile(ssp);
                }

                htmlParser.setSSP(ssp);
                htmlParser.run();

                for (ContentAdapter contentAdapter : contentAdapterSet) {
                    if (contentAdapter instanceof CSSContentAdapter) {
                        ssp.setStylesheet(contentAdapter.getAdaptation());
                    }
                    if (contentAdapter instanceof JSContentAdapter) {
                        ssp.setJavascript(contentAdapter.getAdaptation());
                    }

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
        if (true) {
            // @debug
            String fileName = null;
            int lastIndexOfSlash = ssp.getURI().lastIndexOf("/");
            if (lastIndexOfSlash == ssp.getURI().length() - 1) {
                fileName = "index.html";
            } else {
                fileName = ssp.getURI().substring(lastIndexOfSlash + 1);
            }
            try {
                FileWriter fw = new FileWriter("/var/tmp/" + htmlCleaner.getCorrectorName()
                        + '-' + new Date().getTime() + '-' + fileName);
                fw.write(ssp.getDOM());
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(ContentsAdapterImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void setWriteCleanHtmlInFile(Boolean writeCleanHtmlInFile) {
        this.writeCleanHtmlInFile = writeCleanHtmlInFile;
    }
}