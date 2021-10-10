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
import org.asqatasun.service.command.PageAuditCommandImpl;
import org.asqatasun.service.command.ScenarioAuditCommandImpl;
import org.asqatasun.service.command.SiteAuditCommandImpl;
import org.asqatasun.service.command.UploadAuditCommandImpl;
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
        CrawlerService crawlerService,
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
        this.crawlerService = crawlerService;
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
    private final CrawlerService crawlerService;
    private final ContentAdapterService contentAdapterService;
    private final ProcessorService processorService;
    private final ConsolidatorService consolidatorService;
    private final AnalyserService analyserService;
    private final AdaptationListener adaptationListener;

    private boolean cleanUpRelatedContent = true;

    public static final int ANALYSE_TREATMENT_WINDOW = 10;
    public static final int PROCESSING_TREATMENT_WINDOW = 4;
    public static final int ADAPTATION_TREATMENT_WINDOW = 4;
    public static final int CONSOLIDATION_TREATMENT_WINDOW = 200;

    @Override
    public AuditCommand create(String url, Set<Parameter> paramSet, List<Tag> tagList, boolean isSite) {
        if (isSite) {
            SiteAuditCommandImpl auditCommand = 
                    new SiteAuditCommandImpl(url, paramSet, tagList, crawlerService, auditDataService);
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
        auditCommand.setAdaptationTreatmentWindow(ADAPTATION_TREATMENT_WINDOW);
        auditCommand.setAnalyseTreatmentWindow(ANALYSE_TREATMENT_WINDOW);
        auditCommand.setAnalyserService(analyserService);
        auditCommand.setCleanUpRelatedContent(cleanUpRelatedContent);
        auditCommand.setConsolidationTreatmentWindow(CONSOLIDATION_TREATMENT_WINDOW);
        auditCommand.setConsolidatorService(consolidatorService);
        auditCommand.setContentAdapterService(contentAdapterService);
        auditCommand.setAuditDataService(auditDataService);
        auditCommand.setContentDataService(contentDataService);
        auditCommand.setParameterDataService(parameterDataService);
        auditCommand.setPreProcessResultDataService(preProcessResultDataService);
        auditCommand.setProcessResultDataService(processResultDataService);
        auditCommand.setTagDataService(tagDataService);
        auditCommand.setProcessingTreatmentWindow(PROCESSING_TREATMENT_WINDOW);
        auditCommand.setProcessorService(processorService);
        auditCommand.setScenarioLoaderService(scenarioLoaderService);
        auditCommand.setTestDataService(testDataService);
        auditCommand.setWebResourceDataService(webResourceDataService);
    }
}
