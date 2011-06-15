package org.opens.tanaguru.service;

import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.crawler.CrawlerFactory;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 */
@XmlTransient
public interface CrawlerService {// TODO Write javadoc

    /**
     *
     * @param site the site to crawl
     * @return the site crawled
     */
    Site crawl(Site site);

    /**
     * 
     * @param page  the page to crawl
     * @return a crawled site from the URL
     */
    Page crawl(Page page);

    /**
     * 
     * @param audit
     * @param crawlParameters
     * @return
     *      a crawled webResource (page, site or group of pages) from the url
     */
    WebResource crawl(Audit audit);

    /**
     *
     * @param crawlerFactory
     */
    void setCrawlerFactory(CrawlerFactory crawlerFactory);

    /**
     *
     * @param webResourceFactory
     */
    void setWebResourceFactory(WebResourceFactory webResourceFactory);

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
     * @param auditDataService
     */
    void setAuditDataService(AuditDataService auditDataService);

    /**
     *
     * @param outputDir
     */
    void setOutputDir(String outputDir);

    /**
     *
     * @return
     *      the webResourceDataService instance
     */
    WebResourceDataService getWebResourceDataService();

    /**
     *
     * @return
     *      the content factory instance
     */
    ContentFactory getContentFactory();

    /**
     *
     * @return
     *      the output directory
     */
    String getOutputDir();

    /**
     *
     * @return
     *      the crawl config file path
     */
    String getCrawlConfigFilePath();

    /**
     *
     * @param crawlConfigFilePath
     */
    void setCrawlConfigFilePath(String crawlConfigFilePath);

    /**
     * 
     * @return
     *      the auditDataService instance
     */
    AuditDataService getAuditDataService();
}
