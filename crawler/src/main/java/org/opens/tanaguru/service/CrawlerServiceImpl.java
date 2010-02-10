package org.opens.tanaguru.service;

import java.util.Collection;
import org.opens.tanaguru.crawler.Crawler;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * This is a mock implementation.
 *
 * @author ADEX
 */
public class CrawlerServiceImpl implements CrawlerService {

    private Crawler crawler;

    public CrawlerServiceImpl() {
        super();
    }

    public Site crawl(String siteURL) {
        crawler.setSiteURL(siteURL);
        return (Site) crawler.getResult();
    }

    public void setCrawler(Crawler crawler) {
        this.crawler = crawler;
    }

    public Site crawl(Site site) {
        crawler.setSiteURL(site.getURL());
        crawler.run();
        Site result = (Site) crawler.getResult();
        site.addAllChild((Collection<WebResource>) result.getComponentList());
        return site;
    }
}
