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

package org.opens.tanaguru.service.command;

import java.util.Set;
import org.opens.tanaguru.contentadapter.AdaptationListener;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.entity.service.reference.TestDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.service.*;
import org.opens.tanaguru.util.FileNaming;
import org.opens.tanaguru.util.http.HttpRequestHandler;
import org.opens.webdriver.builder.ScenarioBuilder;

/**
 *
 * @author jkowalczyk
 */
public class PageAuditCommandImpl extends AbstractScenarioAuditCommandImpl {

    private String pageUrl;
    
    /**
     * 
     * @param pageUrl
     * @param paramSet
     * @param auditDataService
     * @param testDataService
     * @param parameterDataService
     * @param webResourceDataService
     * @param contentDataService
     * @param processResultDataService
     * @param contentAdapterService
     * @param processorService
     * @param consolidatorService
     * @param analyserService
     * @param adaptationListener
     * @param crawlerService 
     * @param crawlConfigFilePath
     */
    public PageAuditCommandImpl(
            String pageUrl,
            Set<Parameter> paramSet,
            AuditDataService auditDataService, 
            TestDataService testDataService, 
            ParameterDataService parameterDataService,
            WebResourceDataService webResourceDataService, 
            ContentDataService contentDataService, 
            ProcessResultDataService processResultDataService, 
            ScenarioLoaderService scenarioLoaderService,
            ContentAdapterService contentAdapterService, 
            ProcessorService processorService, 
            ConsolidatorService consolidatorService, 
            AnalyserService analyserService, 
            AdaptationListener adaptationListener,
            int adaptationTreatmentWindow,
            int processingTreatmentWindow,
            int consolidationTreatmentWindow,
            int analysisTreatmentWindow) {
        super(paramSet, 
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
              adaptationListener,
              adaptationTreatmentWindow,
              processingTreatmentWindow,
              consolidationTreatmentWindow,
              analysisTreatmentWindow);
        this.pageUrl = FileNaming.addProtocolToUrl(pageUrl);
    }

    @Override
    public void init() {
        if (HttpRequestHandler.getInstance().isUrlAccessible(pageUrl)) {
            setScenario(ScenarioBuilder.buildScenario(pageUrl));
            setScenarioName(pageUrl);
            setIsPage(true);
            super.init();
        } else {
            createEmptyWebResource(pageUrl);
            setStatusToAudit(AuditStatus.ERROR);
        }
    }
    
    private void createEmptyWebResource(String url) {
        Page page = getWebResourceDataService().createPage(pageUrl);
        getAudit().setSubject(page);
        getWebResourceDataService().saveOrUpdate(page);
    }

}