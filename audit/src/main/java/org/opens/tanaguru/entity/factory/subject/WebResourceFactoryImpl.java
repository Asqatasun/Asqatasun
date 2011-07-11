package org.opens.tanaguru.entity.factory.subject;

import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.PageImpl;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.SiteImpl;
import org.opens.tanaguru.entity.subject.WebResource;

/**
 * 
 * @author ADEX
 */
public class WebResourceFactoryImpl implements WebResourceFactory {

    public WebResourceFactoryImpl() {
        super();
    }

    @Override
    public WebResource create() {
        return new PageImpl();
    }

    @Override
    public Page createPage(String pageURL) {
        return new PageImpl(pageURL);
    }

    @Override
    public Site createSite(String siteURL) {
        return new SiteImpl(siteURL);
    }

}