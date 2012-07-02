/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2011  Open-S Company
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.service.command.factory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.opens.tanaguru.contentadapter.AdaptationListener;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.entity.service.reference.TestDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.service.*;
import org.opens.tanaguru.service.command.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jkowalczyk
 */
public class AuditCommandFactoryImpl implements AuditCommandFactory {

    private AuditDataService auditDataService;
    @Autowired
    public void setAuditDataService(AuditDataService auditDataService) {
        this.auditDataService = auditDataService;
    }
    
    private WebResourceDataService webResourceDataService;
    @Autowired
    public void setWebResourceDataService(WebResourceDataService webResourceDataService) {
        this.webResourceDataService = webResourceDataService;
    }
    
    private TestDataService testDataService;
    @Autowired
    public void setTestDataService(TestDataService testDataService) {
        this.testDataService = testDataService;
    }
    
    private ParameterDataService parameterDataService;
    @Autowired
    public void setParameterDataService(ParameterDataService parameterDataService) {
        this.parameterDataService = parameterDataService;
    }
    
    private ContentDataService contentDataService;
    @Autowired
    public void setContentDataService(ContentDataService contentDataService) {
        this.contentDataService = contentDataService;
    }
    
    private ProcessResultDataService processResultDataService;
    @Autowired
    public void setProcessResultDataService(ProcessResultDataService processResultDataService) {
        this.processResultDataService = processResultDataService;
    }
    
    private CrawlerService crawlerService;
    @Autowired
    public void setCrawlerService(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }
    
    private ContentLoaderService contentLoaderService;
    @Autowired
    public void setContentLoaderService(ContentLoaderService contentLoaderService) {
        this.contentLoaderService = contentLoaderService;
    }
    
    private ScenarioLoaderService scenarioLoaderService;
    @Autowired
    public void setScenarioLoaderService(ScenarioLoaderService scenarioLoaderService) {
        this.scenarioLoaderService = scenarioLoaderService;
    }
    
    private ContentAdapterService contentAdapterService;
    @Autowired
    public void setContentAdapterService(ContentAdapterService contentAdapterService) {
        this.contentAdapterService = contentAdapterService;
    }
    
    private ProcessorService processorService;
    @Autowired
    public void setProcessorService(ProcessorService processorService) {
        this.processorService = processorService;
    }
    
    private ConsolidatorService consolidatorService;
    @Autowired
    public void setConsolidatorService(ConsolidatorService consolidatorService) {
        this.consolidatorService = consolidatorService;
    }

    private AnalyserService analyserService;
    @Autowired
    public void setAnalyserService(AnalyserService analyserService) {
        this.analyserService = analyserService;
    }
    
    private AdaptationListener adaptationListener;
    @Autowired
    public void setAdaptationListener(AdaptationListener adaptationListener) {
        this.adaptationListener = adaptationListener;
    }
    
    private boolean auditPageWithCrawler = false;
    public void setAuditPageWithCrawler(boolean auditPageWithCrawler) {
        Logger.getLogger(this.getClass()).info("AuditPageWithCrawler " + auditPageWithCrawler);
        this.auditPageWithCrawler = auditPageWithCrawler;
    }
    
    @Override
    public AuditCommand create(String url, Set<Parameter> paramSet, boolean isSite) {
        if (isSite) {
            return new SiteAuditCommandImpl(
                    url, 
                    paramSet, 
                    auditDataService, 
                    testDataService, 
                    parameterDataService, 
                    webResourceDataService, 
                    contentDataService, 
                    processResultDataService, 
                    crawlerService, 
                    contentAdapterService, 
                    processorService, 
                    consolidatorService, 
                    analyserService, 
                    adaptationListener);
        } else if (auditPageWithCrawler) {
            return new PageAuditCrawlerCommandImpl(
                    url, 
                    paramSet, 
                    auditDataService, 
                    testDataService, 
                    parameterDataService, 
                    webResourceDataService, 
                    contentDataService, 
                    processResultDataService, 
                    crawlerService,
                    contentAdapterService, 
                    processorService, 
                    consolidatorService, 
                    analyserService, 
                    adaptationListener);
        } else {
            return new PageAuditCommandImpl(
                    url, 
                    paramSet, 
                    auditDataService, 
                    testDataService, 
                    parameterDataService, 
                    webResourceDataService, 
                    contentDataService, 
                    processResultDataService, 
                    scenarioLoaderService,
                    contentAdapterService, 
                    processorService, 
                    consolidatorService, 
                    analyserService, 
                    adaptationListener);
        }
    }

    @Override
    public AuditCommand create(Map<String, String> fileMap, Set<Parameter> paramSet) {
        return new UploadAuditCommandImpl(
                fileMap, 
                paramSet, 
                auditDataService, 
                testDataService, 
                parameterDataService, 
                webResourceDataService, 
                contentDataService, 
                processResultDataService, 
                contentLoaderService, 
                contentAdapterService, 
                processorService, 
                consolidatorService, 
                analyserService, 
                adaptationListener);
    }

    @Override
    public AuditCommand create(String siteUrl, List<String> pageUrlList, Set<Parameter> paramSet) {
        if (auditPageWithCrawler) {
            return new GroupOfPagesCrawlerAuditCommandImpl(
                siteUrl, 
                pageUrlList, 
                paramSet, 
                auditDataService, 
                testDataService, 
                parameterDataService, 
                webResourceDataService, 
                contentDataService, 
                processResultDataService, 
                crawlerService,
                contentAdapterService, 
                processorService, 
                consolidatorService, 
                analyserService, 
                adaptationListener);
        } else {
            return new GroupOfPagesAuditCommandImpl(
                siteUrl, 
                pageUrlList, 
                paramSet, 
                auditDataService, 
                testDataService, 
                parameterDataService, 
                webResourceDataService, 
                contentDataService, 
                processResultDataService, 
                scenarioLoaderService,
                contentAdapterService, 
                processorService, 
                consolidatorService, 
                analyserService, 
                adaptationListener);
        }
    }

    @Override
    public AuditCommand create(String scenarioName, String scenario, Set<Parameter> paramSet) {
        return new ScenarioAuditCommandImpl(
                scenarioName, 
                scenario, 
                paramSet, 
                auditDataService, 
                testDataService, 
                parameterDataService, 
                webResourceDataService, 
                contentDataService, 
                processResultDataService, 
                scenarioLoaderService,
                contentAdapterService, 
                processorService, 
                consolidatorService, 
                analyserService, 
                adaptationListener);
    }

}