package org.opens.tanaguru.service;

import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.crawler.CrawlerFactory;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;

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

    void setCrawlerFactory(CrawlerFactory crawlerFactory);

    void setWebResourceFactory(WebResourceFactory webResourceFactory);

    void setContentFactory(ContentFactory contentFactory);

    void setContentDataService(ContentDataService contentDataService);

    void setWebResourceDataService(WebResourceDataService webResourceDataService);

    void setAuditDataService(AuditDataService auditDataService);

    void setOutputDir(String outputDir);

    WebResourceDataService getWebResourceDataService();

    ContentFactory getContentFactory();

    String getOutputDir();

    String getCrawlConfigFilePath();

    void setCrawlConfigFilePath(String crawlConfigFilePath);
}
