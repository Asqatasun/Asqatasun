/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.opens.tanaguru.crawler;

import java.util.Set;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author enzolalay
 */
public class CrawlerFactoryImpl implements CrawlerFactory {

    private ContentDataService contentDataService;
    @Autowired
    public void setContentDataService(ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }

    private WebResourceDataService webResourceDataService;
    @Autowired
    public void setWebResourceDataService(WebResourceDataService webResourceDataService) {
        this.webResourceDataService = webResourceDataService;
    }
    
    /**
     * The path of the crawl configuration file
     */
    private String crawlConfigFilePath;
    @Override
    public void setCrawlConfigFilePath(String crawlConfigFilePath) {
        this.crawlConfigFilePath = crawlConfigFilePath;
    }

    /**
     * The output directory needed by heritrix to create temporary files
     * during the crawl.
     */
    private String outputDir;
    @Override
    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }
    
    @Override
    public Crawler create(Set<Parameter> paramSet, boolean persistOnTheFly) {
        Crawler crawler = new CrawlerImpl();
        crawler.setWebResourceDataService(webResourceDataService);
        crawler.setContentDataService(contentDataService);
        crawler.setOutputDir(outputDir);
        crawler.setParameterSet(paramSet);
        crawler.setPersistOnTheFly(persistOnTheFly);
        crawler.setCrawlConfigFilePath(crawlConfigFilePath);
        return crawler;
    }

}