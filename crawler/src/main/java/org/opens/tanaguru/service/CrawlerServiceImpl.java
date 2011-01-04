package org.opens.tanaguru.service;

import org.opens.tanaguru.crawler.Crawler;
import org.opens.tanaguru.crawler.CrawlerImpl;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * Implementation of the crawler service.
 *
 * @author ADEX
 */
public class CrawlerServiceImpl implements CrawlerService {

    private Crawler crawler;
    public Crawler getCrawler() {
        return crawler;
    }

    @Override
    public void setCrawler(Crawler crawler) {
        this.crawler = crawler;
    }

    public CrawlerServiceImpl() {
        super();
    }

    @Override
    public Page crawl(Page page) {
        crawler.setPageURL(page.getURL());
        crawler.run();
        ((Page)crawler.getResult()).setAudit(page.getAudit());
        page = (Page) crawler.getResult();

        // The crawler component gets the webResources AND the associated contents
        if (crawler instanceof CrawlerImpl){
            page.getAudit().addAllContent(
                    ((CrawlerImpl)crawler).getContentListResult());
        }
        return page;
    }

    /**
     * Calls the crawler component process then updates the site.
     * @param site the site to crawl
     * @return returns the site after modification
     */
    @Override
    public Site crawl(Site site) {
        int componentListSize = site.getComponentList().size();
        if (componentListSize == 0) {
            crawler.setSiteURL(site.getURL());
        } else {
            String[] urlPage = new String[componentListSize];
            Object[] webresourceTab = site.getComponentList().toArray();
            for (int i=0;i<componentListSize;i++) {
                if (webresourceTab[i] instanceof WebResource) {
                    urlPage[i] = ((WebResource)webresourceTab[i]).getURL();
                }
            }
            crawler.setSiteURL(site.getURL(), urlPage);
        }
        crawler.run();
        ((Site)crawler.getResult()).setAudit(site.getAudit());
        site = (Site) crawler.getResult();
        // The crawler component gets the webResources AND the associated contents
        if (crawler instanceof CrawlerImpl){
            site.getAudit().addAllContent(
                    ((CrawlerImpl)crawler).getContentListResult());
        }
        return site;
    }

}
