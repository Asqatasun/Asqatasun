/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2015  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
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
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.service.command.factory;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.asqatasun.contentadapter.AdaptationListener;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.service.audit.AuditDataService;
import org.asqatasun.entity.service.audit.ContentDataService;
import org.asqatasun.entity.service.audit.PreProcessResultDataService;
import org.asqatasun.entity.service.audit.ProcessResultDataService;
import org.asqatasun.entity.service.parameterization.ParameterDataService;
import org.asqatasun.entity.service.reference.TestDataService;
import org.asqatasun.entity.service.subject.WebResourceDataService;
import org.asqatasun.service.AnalyserService;
import org.asqatasun.service.ConsolidatorService;
import org.asqatasun.service.ContentAdapterService;
import org.asqatasun.service.ContentLoaderService;
import org.asqatasun.service.CrawlerService;
import org.asqatasun.service.ProcessorService;
import org.asqatasun.service.ScenarioLoaderService;
import org.asqatasun.service.command.AuditCommand;
import org.asqatasun.service.command.AuditCommandImpl;
import org.asqatasun.service.command.GroupOfPagesAuditCommandImpl;
import org.asqatasun.service.command.GroupOfPagesCrawlerAuditCommandImpl;
import org.asqatasun.service.command.PageAuditCommandImpl;
import org.asqatasun.service.command.PageAuditCrawlerCommandImpl;
import org.asqatasun.service.command.ScenarioAuditCommandImpl;
import org.asqatasun.service.command.SiteAuditCommandImpl;
import org.asqatasun.service.command.UploadAuditCommandImpl;
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

    private PreProcessResultDataService preProcessResultDataService;
    @Autowired
    public void setPreProcessResultDataService(PreProcessResultDataService preProcessResultDataService) {
        this.preProcessResultDataService = preProcessResultDataService;
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
        Logger.getLogger(this.getClass()).debug("AuditPageWithCrawler " + auditPageWithCrawler);
        this.auditPageWithCrawler = auditPageWithCrawler;
    }

    private boolean cleanUpRelatedContent = true;
    public void setCleanUpRelatedContent(boolean cleanUpRelatedContent) {
        Logger.getLogger(this.getClass()).debug("CleanUpRelatedContent " + cleanUpRelatedContent);
        this.cleanUpRelatedContent = cleanUpRelatedContent;
    }

    public static final int ANALYSE_TREATMENT_WINDOW = 10;
    public static final int PROCESSING_TREATMENT_WINDOW = 4;
    public static final int ADAPTATION_TREATMENT_WINDOW = 4;
    public static final int CONSOLIDATION_TREATMENT_WINDOW = 200;

    private int adaptationTreatmentWindow = ADAPTATION_TREATMENT_WINDOW;
    public void setAdaptationTreatmentWindow(int adaptationTreatmentWindow) {
        this.adaptationTreatmentWindow = adaptationTreatmentWindow;
    }

    private int analyseTreatmentWindow = ANALYSE_TREATMENT_WINDOW;
    public void setAnalyseTreatmentWindow(int analyseTreatmentWindow) {
        this.analyseTreatmentWindow = analyseTreatmentWindow;
    }

    private int consolidationTreatmentWindow = CONSOLIDATION_TREATMENT_WINDOW;
    public void setConsolidationTreatmentWindow(int consolidationTreatmentWindow) {
        this.consolidationTreatmentWindow = consolidationTreatmentWindow;
    }

    private int processingTreatmentWindow = PROCESSING_TREATMENT_WINDOW;
    public void setProcessingTreatmentWindow(int processingTreatmentWindow) {
        this.processingTreatmentWindow = processingTreatmentWindow;
    }

    @Override
    public AuditCommand create(String url, Set<Parameter> paramSet, boolean isSite) {
        if (isSite) {
            SiteAuditCommandImpl auditCommand = 
                    new SiteAuditCommandImpl(url, paramSet, auditDataService);
            initCommandServices(auditCommand);
            auditCommand.setCrawlerService(crawlerService);
            return auditCommand;
        } else if (auditPageWithCrawler) {
            PageAuditCrawlerCommandImpl auditCommand = 
                    new PageAuditCrawlerCommandImpl(url, paramSet, auditDataService);
            initCommandServices(auditCommand);
            auditCommand.setCrawlerService(crawlerService);
            return auditCommand;
        } else {
            PageAuditCommandImpl auditCommand = 
                    new PageAuditCommandImpl(url, paramSet, auditDataService);
            initCommandServices(auditCommand);
            auditCommand.setScenarioLoaderService(scenarioLoaderService);
            return auditCommand;
        }
    }

    @Override
    public AuditCommand create(Map<String, String> fileMap, Set<Parameter> paramSet) {
        UploadAuditCommandImpl auditCommand = 
                new UploadAuditCommandImpl(fileMap, paramSet, auditDataService);
        initCommandServices(auditCommand);
        auditCommand.setContentLoaderService(contentLoaderService);
        return auditCommand;
    }

    @Override
    public AuditCommand create(String siteUrl, List<String> pageUrlList, Set<Parameter> paramSet) {

        if (auditPageWithCrawler) {
            GroupOfPagesCrawlerAuditCommandImpl auditCommand = 
                new GroupOfPagesCrawlerAuditCommandImpl(
                            siteUrl,
                            pageUrlList,
                            paramSet,
                            auditDataService);
            initCommandServices(auditCommand);
            auditCommand.setCrawlerService(crawlerService);
            return auditCommand;
        } else {
            GroupOfPagesAuditCommandImpl auditCommand = 
                new GroupOfPagesAuditCommandImpl(
                            siteUrl,
                            pageUrlList,
                            paramSet,
                            auditDataService);
            initCommandServices(auditCommand);
            auditCommand.setScenarioLoaderService(scenarioLoaderService);
            return auditCommand;
        }
    }

    @Override
    public AuditCommand create(String scenarioName, String scenario, Set<Parameter> paramSet) {
        ScenarioAuditCommandImpl auditCommand = 
                new ScenarioAuditCommandImpl(scenarioName, scenario, paramSet, auditDataService);
        initCommandServices(auditCommand);
        auditCommand.setScenarioLoaderService(scenarioLoaderService);
        return auditCommand;
    }

    /**
     *
     * @param auditCommand
     */
    private void initCommandServices(AuditCommandImpl auditCommand) {
        auditCommand.setAdaptationListener(adaptationListener);
        auditCommand.setAdaptationTreatmentWindow(adaptationTreatmentWindow);
        auditCommand.setAnalyseTreatmentWindow(analyseTreatmentWindow);
        auditCommand.setAnalyserService(analyserService);
//        auditCommand.setAuditDataService(auditDataService);
        auditCommand.setCleanUpRelatedContent(cleanUpRelatedContent);
        auditCommand.setConsolidationTreatmentWindow(consolidationTreatmentWindow);
        auditCommand.setConsolidatorService(consolidatorService);
        auditCommand.setContentAdapterService(contentAdapterService);
        auditCommand.setContentDataService(contentDataService);
        auditCommand.setParameterDataService(parameterDataService);
        auditCommand.setPreProcessResultDataService(preProcessResultDataService);
        auditCommand.setProcessResultDataService(processResultDataService);
        auditCommand.setProcessingTreatmentWindow(processingTreatmentWindow);
        auditCommand.setProcessorService(processorService);
        auditCommand.setTestDataService(testDataService);
        auditCommand.setWebResourceDataService(webResourceDataService);
    }
}
