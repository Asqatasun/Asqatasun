package org.opens.tanaguru.crawler;

import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;

/**
 * 
 * @author ADEX
 */
public interface Crawler {

    WebResource getResult();

    void run();

    void setWebResourceFactory(WebResourceFactory webResourceFactory);

    void setSiteURL(String webResourceURL);
}
