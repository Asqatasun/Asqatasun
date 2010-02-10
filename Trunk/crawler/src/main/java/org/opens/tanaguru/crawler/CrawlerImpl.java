package org.opens.tanaguru.crawler;

import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 */
public class CrawlerImpl implements Crawler {

    private WebResource result;
    private WebResourceFactory webResourceFactory;
    private String siteURL;

    public CrawlerImpl() {
        super();
    }

    public WebResource getResult() {
        return result;
    }

    public String getSiteURL() {
        return siteURL;
    }

    public void run() {
        result = webResourceFactory.createSite(siteURL);
    }

    public void setWebResourceFactory(WebResourceFactory webResourceFactory) {
        this.webResourceFactory = webResourceFactory;
    }

    public void setSiteURL(String webResourceURL) {
        this.siteURL = webResourceURL;
    }
}
