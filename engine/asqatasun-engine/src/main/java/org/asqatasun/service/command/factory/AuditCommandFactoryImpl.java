/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2019  Asqatasun.org
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
import org.asqatasun.service.*;
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

    @Autowired
    private AuditDataService auditDataService;
    @Autowired
    private WebResourceDataService webResourceDataService;
    @Autowired
    private TestDataService testDataService;
    @Autowired
    private ParameterDataService parameterDataService;
    @Autowired
    private ContentDataService contentDataService;
    @Autowired
    private ProcessResultDataService processResultDataService;
    @Autowired
    private PreProcessResultDataService preProcessResultDataService;
//    @Autowired
//    private CrawlerService crawlerService;
    @Autowired
    private ContentLoaderService contentLoaderService;
    @Autowired
    private ScenarioLoaderService scenarioLoaderService;
    @Autowired
    private ContentAdapterService contentAdapterService;
    @Autowired
    private ProcessorService processorService;
    @Autowired
    private ConsolidatorService consolidatorService;
    @Autowired
    private AnalyserService analyserService;
    @Autowired
    private AdaptationListener adaptationListener;

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
//            auditCommand.setCrawlerService(crawlerService);
            return auditCommand;
        } else if (auditPageWithCrawler) {
            PageAuditCrawlerCommandImpl auditCommand = 
                    new PageAuditCrawlerCommandImpl(url, paramSet, auditDataService);
            initCommandServices(auditCommand);
//            auditCommand.setCrawlerService(crawlerService);
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
//            auditCommand.setCrawlerService(crawlerService);
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
