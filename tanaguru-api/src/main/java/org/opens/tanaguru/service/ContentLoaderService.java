package org.opens.tanaguru.service;

import java.util.List;
import java.util.Map;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.subject.WebResource;
import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.contentloader.ContentLoaderFactory;
import org.opens.tanaguru.contentloader.DownloaderFactory;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;

/**
 * 
 * @author ADEX
 */
@XmlTransient
public interface ContentLoaderService {// TODO Write javadoc

    /**
     *
     * @param webResource
     * @return
     */
    List<Content> loadContent(WebResource webResource);

    /**
     *
     * @param webResource
     * @param fileMap
     * @return
     */
    List<Content> loadContent(WebResource webResource, Map<String, String> fileMap);

    /**
     *
     * @param contentFactory
     */
    void setContentFactory(ContentFactory contentFactory);

    /**
     *
     * @param contentLoaderFactory
     */
    void setContentLoaderFactory(ContentLoaderFactory contentLoaderFactory);

    /**
     *
     * @param downloaderFactory
     */
    void setDownloaderFactory(DownloaderFactory downloaderFactory);
}
