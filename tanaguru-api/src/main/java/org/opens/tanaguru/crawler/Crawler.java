package org.opens.tanaguru.crawler;

import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;

/**
 * 
 * @author ADEX
 */
public interface Crawler {// TODO Write javadoc

    /**
     * 
     * @return
     *          the crawled webresource
     */
    WebResource getResult();

    /**
     * 
     */
    void run();

    /**
     *
     * @param webResourceFactory
     */
    void setWebResourceFactory(WebResourceFactory webResourceFactory);

    /**
     *
     * @param webResourceURL
     */
    void setSiteURL(String webResourceURL);

    /**
     *
     * @param siteName
     * @param webResourceURL
     */
    void setSiteURL(String siteName, String[] webResourceURL);

    /**
     *
     * @param pageURL
     */
    void setPageURL(String pageURL);

    void setContentFactory(ContentFactory contentFactory);

    void setContentDataService(ContentDataService contentDataService);

    void setWebResourceDataService(WebResourceDataService webResourceDataService);

    void setOutputDir(String outputDir);

    void setCrawlConfigFilePath(String crawlConfigFilePath);
}
