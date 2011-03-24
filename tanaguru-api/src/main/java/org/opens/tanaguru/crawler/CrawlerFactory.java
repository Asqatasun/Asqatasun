/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.crawler;

import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;

/**
 *
 * @author enzolalay
 */
public interface CrawlerFactory {// TODO Write javadoc

    Crawler create(WebResourceFactory webResourceFactory, WebResourceDataService webResourceDataService, ContentFactory contentFactory, ContentDataService contentDataService, String outputDir, String crawlConfigFilePath);
}
