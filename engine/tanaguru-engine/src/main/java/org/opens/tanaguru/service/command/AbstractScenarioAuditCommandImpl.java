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
import org.apache.log4j.Logger;
import org.opens.tanaguru.contentadapter.AdaptationListener;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.entity.service.reference.TestDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.service.*;

/**
 *
 * @author jkowalczyk
 */
public abstract class AbstractScenarioAuditCommandImpl extends AuditCommandImpl {
    
    /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(AbstractScenarioAuditCommandImpl.class);
    
    /**
     * The scenario loader Service instance
     */
    private ScenarioLoaderService scenarioLoaderService;
    public ScenarioLoaderService getScenarioLoaderService() {
        return scenarioLoaderService;
    }
    
    /**
     * The scenario file
     */
    private String scenario;
    public String getScenario() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario = scenario;
    }
    
    /**
     * The scenario file
     */
    private String scenarioName;
    public String getScenarioName() {
        return scenarioName;
    }

    public void setScenarioName(String scenarioName) {
        this.scenarioName = scenarioName;
    }
    
    private boolean isPage = false;
    public void setIsPage(boolean isPage) {
        this.isPage = isPage;
    }
    
    public AbstractScenarioAuditCommandImpl(
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
              contentAdapterService, 
              processorService, 
              consolidatorService, 
              analyserService, 
              adaptationListener,
              adaptationTreatmentWindow,
              processingTreatmentWindow,
              consolidationTreatmentWindow,
              analysisTreatmentWindow);
        this.scenarioLoaderService = scenarioLoaderService;
    }

    @Override
    public void init() {
        setStatusToAudit(AuditStatus.SCENARIO_LOADING);
    }
    
    @Override
    public void loadContent() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Loading content for " + scenarioName);
        }
        if (!getAudit().getStatus().equals(AuditStatus.SCENARIO_LOADING) || scenario.isEmpty()) {
            LOGGER.warn(
                    new StringBuilder("Audit Status is ")
                    .append(getAudit().getStatus())
                    .append(" while ")
                    .append(AuditStatus.SCENARIO_LOADING)
                    .append(" was required ").toString());
            setStatusToAudit(AuditStatus.ERROR);
            return;
        }
        // the returned content list is already persisted and associated with
        // the current audit
        scenarioLoaderService.loadScenario(createWebResource(), scenario);
        setStatusToAudit(AuditStatus.CONTENT_ADAPTING);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(scenarioName +" has been loaded");
        }
    }
    
    /**
     * Create the main webResource attached to the audit and then
     * passed to the scenario loader service
     * 
     * @return 
     *      a Site instance
     */
    private WebResource createWebResource() {
        WebResource webResource;
        if (isPage) {
            webResource = getWebResourceDataService().createPage(scenarioName);
        } else {
            webResource = getWebResourceDataService().createSite(scenarioName);
        }
        webResource.setAudit(getAudit());
        getWebResourceDataService().saveOrUpdate(webResource);
        getAudit().setSubject(webResource);
        return webResource;
    }

}