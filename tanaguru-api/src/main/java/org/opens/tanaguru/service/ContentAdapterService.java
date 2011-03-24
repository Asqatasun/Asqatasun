package org.opens.tanaguru.service;

import java.util.List;
import java.util.Set;
import org.opens.tanaguru.entity.audit.Content;
import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.contentadapter.ContentAdapterFactory;
import org.opens.tanaguru.contentadapter.ContentsAdapterFactory;
import org.opens.tanaguru.contentadapter.HTMLCleanerFactory;
import org.opens.tanaguru.contentadapter.HTMLParserFactory;
import org.opens.tanaguru.contentadapter.util.URLIdentifierFactory;
import org.opens.tanaguru.contentloader.DownloaderFactory;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;

/**
 * 
 * @author ADEX
 */
@XmlTransient
public interface ContentAdapterService {// TODO Write javadoc

    /**
     *
     * @param contentList
     * @return
     */
    List<Content> adaptContent(List<Content> contentList);

    /**
     *
     * @param contentFactory the content factory to set
     */
    void setContentFactory(ContentFactory contentFactory);

    /**
     *
     * @param writeCleanHtmlInFile
     */
    void setWriteCleanHtmlInFile(boolean writeCleanHtmlInFile);

    void setTempFolderRootPath(String tempFolderRootPath);

    void setContentsAdapterFactory(ContentsAdapterFactory contentsAdapterFactory);

    void setHtmlCleanerFactory(HTMLCleanerFactory htmlCleanerFactory);

    void setHtmlParserFactory(HTMLParserFactory htmlParserFactory);

    void setContentAdapterFactorySet(Set<ContentAdapterFactory> contentAdapterFactorySet);

    void setUrlIdentifierFactory(URLIdentifierFactory urlIdentifierFactory);

    void setDownloaderFactory(DownloaderFactory downloaderFactory);
}
