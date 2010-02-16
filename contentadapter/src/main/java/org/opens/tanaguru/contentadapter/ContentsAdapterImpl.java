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

/**
 * 
 * @author ADEX
 */
public class ContentsAdapterImpl implements ContentsAdapter {

    private Set<ContentAdapter> contentAdapterSet = new HashSet<ContentAdapter>();
    private List<Content> contentList;
    private HTMLCleaner htmlCleaner;
    private HTMLParser htmlParser;
    private boolean initialized = false;
    private List<Content> result;

    public ContentsAdapterImpl() {
        super();
    }

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

                ssp.setDOM(removeUpperCaseTags(htmlCleaner.getResult()));
                writeCleanDomInFile(ssp);

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

    public void setContentAdapterSet(Set<ContentAdapter> contentAdapterSet) {
        this.contentAdapterSet = contentAdapterSet;
    }

    public void setContentList(List<Content> contentList) {
        this.contentList = contentList;
    }

    public void setHTMLCleaner(HTMLCleaner htmlCleaner) {
        this.htmlCleaner = htmlCleaner;
    }

    public void setHTMLParser(HTMLParser htmlParser) {
        this.htmlParser = htmlParser;
    }

    private String removeUpperCaseTags(String cleanHtml) {
        StringBuffer newCleanHtml = new StringBuffer();
        int strPtr=0;
        int tmpPtr=0;
        while (strPtr != cleanHtml.length()){
            if (cleanHtml.charAt(strPtr) == '<') {
                if (cleanHtml.charAt(strPtr+1) == '!') { //To ignore the case of <!doctype
                    newCleanHtml.append(cleanHtml.charAt(strPtr));
                    strPtr++;
                } else if (cleanHtml.charAt(strPtr+1) == '/') {
                    tmpPtr = cleanHtml.indexOf('>', strPtr);
                    newCleanHtml.append('<');
                    newCleanHtml.append('/');
                    newCleanHtml.append(cleanHtml.substring(strPtr+2, tmpPtr).toLowerCase());
                    strPtr = tmpPtr;
                } else {
                    if (cleanHtml.indexOf(' ', strPtr) < cleanHtml.indexOf('>', strPtr)) {
                        // case of self-closing tag
                        tmpPtr = cleanHtml.indexOf(' ', strPtr);
                    } else {
                        // case of classical opening tag
                        tmpPtr = cleanHtml.indexOf('>', strPtr);
                    }
                    newCleanHtml.append('<');
                    newCleanHtml.append(cleanHtml.substring(strPtr+1, tmpPtr).toLowerCase());
                    strPtr = tmpPtr;
                }
            } else {
                newCleanHtml.append(cleanHtml.charAt(strPtr));
                strPtr++;
            }
        }
        return newCleanHtml.toString();
    }

    private void writeCleanDomInFile(SSP ssp){
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
}
