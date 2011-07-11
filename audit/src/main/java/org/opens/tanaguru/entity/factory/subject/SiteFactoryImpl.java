package org.opens.tanaguru.entity.factory.subject;

import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.entity.subject.SiteImpl;

/**
 * 
 * @author ADEX
 */
public class SiteFactoryImpl implements SiteFactory {

    public SiteFactoryImpl() {
        super();
    }

    @Override
    public Site create() {
        return new SiteImpl();
    }

    @Override
    public Site create(String url) {
        Site site = this.create();
        site.setURL(url);
        return site;
    }

}