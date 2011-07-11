package org.opens.tanaguru.crawler;

import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.parameterization.Parametrable;

/**
 * 
 * @author ADEX
 */
public interface Crawler extends Parametrable {

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


    /**
     *
     * @param contentFactory
     */
    void setContentFactory(ContentFactory contentFactory);

    /**
     *
     * @param contentDataService
     */
    void setContentDataService(ContentDataService contentDataService);

    /**
     *
     * @param webResourceDataService
     */
    void setWebResourceDataService(WebResourceDataService webResourceDataService);

    /**
     *
     * @param outputDir
     */
    void setOutputDir(String outputDir);

    /**
     * 
     * @param crawlConfigFilePath
     */
    void setCrawlConfigFilePath(String crawlConfigFilePath);
    
}