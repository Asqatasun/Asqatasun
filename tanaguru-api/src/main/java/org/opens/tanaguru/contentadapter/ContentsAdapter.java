package org.opens.tanaguru.contentadapter;

import java.util.List;
import org.opens.tanaguru.entity.audit.Content;
import java.util.Set;

/**
 * 
 * @author ADEX
 */
public interface ContentsAdapter {// TODO Write javadoc

    /**
     *
     * @return the result
     */
    List<Content> getResult();

    /**
     *
     */
    void run();

    /**
     *
     * @param contentAdapterSet
     */
    void setContentAdapterSet(Set<ContentAdapter> contentAdapterSet);

    /**
     *
     * @param contentList
     */
    void setContentList(List<Content> contentList);

    /**
     *
     * @param htmlCleaner
     */
    void setHTMLCleaner(HTMLCleaner htmlCleaner);

    /**
     * 
     * @param htmlParser
     */
    void setHTMLParser(HTMLParser htmlParser);

    /**
     *
     * @param writeCleanHtmlInFile
     */
    void setWriteCleanHtmlInFile(Boolean writeCleanHtmlInFile);
}
