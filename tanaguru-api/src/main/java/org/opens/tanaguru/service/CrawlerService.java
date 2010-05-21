package org.opens.tanaguru.service;

import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.crawler.Crawler;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;

/**
 * 
 * @author ADEX
 */
@XmlTransient
public interface CrawlerService {

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
     * @param crawler the crawler component to set
     */
    void setCrawler(Crawler crawler);
}
