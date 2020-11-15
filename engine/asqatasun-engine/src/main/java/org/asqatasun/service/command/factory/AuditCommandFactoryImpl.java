/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2020  Asqatasun.org
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

import org.asqatasun.analyser.AnalyserService;
import org.asqatasun.consolidator.ConsolidatorService;
import org.asqatasun.contentadapter.AdaptationListener;
import org.asqatasun.entity.audit.Tag;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.service.audit.*;
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
import org.springframework.stereotype.Component;

/**
 *
 * @author jkowalczyk
 */
@Component
public class AuditCommandFactoryImpl implements AuditCommandFactory {

    public AuditCommandFactoryImpl(
        AuditDataService auditDataService,
        WebResourceDataService webResourceDataService,
        AdaptationListener adaptationListener,
        TestDataService testDataService,
        ParameterDataService parameterDataService,
        TagDataService tagDataService,
        ContentDataService contentDataService,
        ProcessResultDataService processResultDataService,
        PreProcessResultDataService preProcessResultDataService,
        AnalyserService analyserService,
        ContentLoaderService contentLoaderService,
        ConsolidatorService consolidatorService,
        ScenarioLoaderService scenarioLoaderService,
        ContentAdapterService contentAdapterService,
        ProcessorService processorService) {
        this.auditDataService = auditDataService;
        this.webResourceDataService = webResourceDataService;
        this.adaptationListener = adaptationListener;
        this.testDataService = testDataService;
        this.parameterDataService = parameterDataService;
        this.tagDataService = tagDataService;
        this.contentDataService = contentDataService;
        this.processResultDataService = processResultDataService;
        this.preProcessResultDataService = preProcessResultDataService;
        this.analyserService = analyserService;
        this.contentLoaderService = contentLoaderService;
        this.consolidatorService = consolidatorService;
        this.scenarioLoaderService = scenarioLoaderService;
        this.contentAdapterService = contentAdapterService;
        this.processorService = processorService;
    }

    private final AuditDataService auditDataService;
    private final WebResourceDataService webResourceDataService;
    private final TestDataService testDataService;
    private final ParameterDataService parameterDataService;
    private final TagDataService tagDataService;
    private final ContentDataService contentDataService;
    private final ProcessResultDataService processResultDataService;
    private final PreProcessResultDataService preProcessResultDataService;
    private final ContentLoaderService contentLoaderService;
    private final ScenarioLoaderService scenarioLoaderService;
    private final ContentAdapterService contentAdapterService;
    private final ProcessorService processorService;
    private final ConsolidatorService consolidatorService;
    private final AnalyserService analyserService;
    private final AdaptationListener adaptationListener;

    private boolean auditPageWithCrawler = false;
    public void setAuditPageWithCrawler(boolean auditPageWithCrawler) {
        this.auditPageWithCrawler = auditPageWithCrawler;
    }

    private boolean cleanUpRelatedContent = true;
    public void setCleanUpRelatedContent(boolean cleanUpRelatedContent) {
        this.cleanUpRelatedContent = cleanUpRelatedContent;
    }

    public static final int ANALYSE_TREATMENT_WINDOW = 10;
    public static final int PROCESSING_TREATMENT_WINDOW = 4;
    public static final int ADAPTATION_TREATMENT_WINDOW = 4;
    public static final int CONSOLIDATION_TREATMENT_WINDOW = 200;

    private int adaptationTreatmentWindow = ADAPTATION_TREATMENT_WINDOW;
    private int analyseTreatmentWindow = ANALYSE_TREATMENT_WINDOW;
    private int consolidationTreatmentWindow = CONSOLIDATION_TREATMENT_WINDOW;
    private int processingTreatmentWindow = PROCESSING_TREATMENT_WINDOW;

    @Override
    public AuditCommand create(String url, Set<Parameter> paramSet, List<Tag> tagList, boolean isSite) {
        if (isSite) {
            SiteAuditCommandImpl auditCommand = 
                    new SiteAuditCommandImpl(url, paramSet, tagList, auditDataService);
            initCommandServices(auditCommand);
            return auditCommand;
        } else if (auditPageWithCrawler) {
            PageAuditCrawlerCommandImpl auditCommand = 
                    new PageAuditCrawlerCommandImpl(url, paramSet, tagList, auditDataService);
            initCommandServices(auditCommand);
            return auditCommand;
        } else {
            PageAuditCommandImpl auditCommand = 
                    new PageAuditCommandImpl(url, paramSet, tagList, auditDataService);
            initCommandServices(auditCommand);
            auditCommand.setScenarioLoaderService(scenarioLoaderService);
            return auditCommand;
        }
    }

    @Override
    public AuditCommand create(Map<String, String> fileMap, Set<Parameter> paramSet, List<Tag> tagList) {
        UploadAuditCommandImpl auditCommand = 
                new UploadAuditCommandImpl(fileMap, paramSet, tagList, auditDataService);
        initCommandServices(auditCommand);
        auditCommand.setContentLoaderService(contentLoaderService);
        return auditCommand;
    }

    @Override
    public AuditCommand create(String siteUrl, List<String> pageUrlList, Set<Parameter> paramSet, List<Tag> tagList) {

        if (auditPageWithCrawler) {
            GroupOfPagesCrawlerAuditCommandImpl auditCommand = 
                new GroupOfPagesCrawlerAuditCommandImpl(
                            siteUrl,
                            pageUrlList,
                            paramSet,
                            tagList,
                            auditDataService);
            initCommandServices(auditCommand);
            return auditCommand;
        } else {
            GroupOfPagesAuditCommandImpl auditCommand = 
                new GroupOfPagesAuditCommandImpl(
                            siteUrl,
                            pageUrlList,
                            paramSet,
                            tagList,
                            auditDataService);
            initCommandServices(auditCommand);
            auditCommand.setScenarioLoaderService(scenarioLoaderService);
            return auditCommand;
        }
    }

    @Override
    public AuditCommand create(String scenarioName, String scenario, Set<Parameter> paramSet, List<Tag> tagList) {
        ScenarioAuditCommandImpl auditCommand = 
                new ScenarioAuditCommandImpl(scenarioName, scenario, paramSet, tagList, auditDataService);
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
        auditCommand.setCleanUpRelatedContent(cleanUpRelatedContent);
        auditCommand.setConsolidationTreatmentWindow(consolidationTreatmentWindow);
        auditCommand.setConsolidatorService(consolidatorService);
        auditCommand.setContentAdapterService(contentAdapterService);
        auditCommand.setContentDataService(contentDataService);
        auditCommand.setParameterDataService(parameterDataService);
        auditCommand.setPreProcessResultDataService(preProcessResultDataService);
        auditCommand.setProcessResultDataService(processResultDataService);
        auditCommand.setTagDataService(tagDataService);
        auditCommand.setProcessingTreatmentWindow(processingTreatmentWindow);
        auditCommand.setProcessorService(processorService);
        auditCommand.setTestDataService(testDataService);
        auditCommand.setWebResourceDataService(webResourceDataService);
    }
}
