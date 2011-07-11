/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.crawler;

import java.util.Set;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.factory.subject.WebResourceFactory;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;

/**
 *
 * @author enzolalay
 */
public interface CrawlerFactory {

    /**
     * 
     * @param webResourceFactory
     * @param webResourceDataService
     * @param contentFactory
     * @param contentDataService
     * @param paramSet
     * @param outputDir
     * @param crawlConfigFilePath
     * @return
     *      an initialised implementation of the crawler interface
     */
    Crawler create(
            WebResourceFactory webResourceFactory,
            WebResourceDataService webResourceDataService,
            ContentFactory contentFactory,
            ContentDataService contentDataService,
            Set<Parameter> paramSet,
            String outputDir,
            String crawlConfigFilePath);
}
