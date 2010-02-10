package org.opens.tanaguru.service;

import javax.xml.bind.annotation.XmlTransient;
import org.opens.tanaguru.crawler.Crawler;
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
     * @param siteURL ths URL of the site to crawl
     * @return a crawled site from the URL
     */
    Site crawl(String siteURL);

    /**
     * 
     * @param crawler the crawler component to set
     */
    void setCrawler(Crawler crawler);
}
