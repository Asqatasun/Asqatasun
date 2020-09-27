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

package org.asqatasun.service.command;

import java.util.List;
import java.util.Set;
import org.asqatasun.entity.audit.AuditStatus;
import org.asqatasun.entity.audit.Tag;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.service.audit.AuditDataService;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.service.ScenarioLoaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jkowalczyk
 */
public abstract class AbstractScenarioAuditCommandImpl extends AuditCommandImpl {
    
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractScenarioAuditCommandImpl.class);
    
    /**
     * The scenario loader Service instance
     */
    private ScenarioLoaderService scenarioLoaderService;
    public void setScenarioLoaderService(ScenarioLoaderService scenarioLoaderService) {
        this.scenarioLoaderService = scenarioLoaderService;
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
    
    /**
     * 
     * @param paramSet
     * @param auditDataService 
     */
    public AbstractScenarioAuditCommandImpl(
                Set<Parameter> paramSet,
                List<Tag> tagList,
                AuditDataService auditDataService) {
        super(paramSet, tagList, auditDataService);
    }

    @Override
    public void init() {
        super.init();
        setStatusToAudit(AuditStatus.SCENARIO_LOADING);
    }
    
    @Override
    public void loadContent() {
        LOGGER.info("Loading content for " + scenarioName);
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

        LOGGER.info(scenarioName +" has been loaded");
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
