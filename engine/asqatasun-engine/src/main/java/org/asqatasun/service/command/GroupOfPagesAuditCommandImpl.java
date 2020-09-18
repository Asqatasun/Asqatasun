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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.service.audit.AuditDataService;
import org.asqatasun.scenarioloader.model.SeleniumIdeScenarioBuilder;
import org.asqatasun.util.FileNaming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jkowalczyk
 */
public class GroupOfPagesAuditCommandImpl extends AbstractScenarioAuditCommandImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupOfPagesAuditCommandImpl.class);

    /**
     * 
     * @param siteUrl
     * @param pageUrlList
     * @param paramSet
     * @param auditDataService 
     */
    public GroupOfPagesAuditCommandImpl(
                String siteUrl, 
                List<String> pageUrlList,
                Set<Parameter> paramSet,
                AuditDataService auditDataService) {
        
        super(paramSet,auditDataService);
        try {
            List<URL> localUrlList = new ArrayList<>();
            for (String url : pageUrlList) {
                localUrlList.add(new URL(FileNaming.addProtocolToUrl(url)));
            }
            try {
                setScenario(new ObjectMapper().writeValueAsString(new SeleniumIdeScenarioBuilder().build(siteUrl, localUrlList)));
            } catch (JsonProcessingException e) {
                LoggerFactory.getLogger(this.getClass()).error("Could not parse scenario " + e.getMessage());
            }
            URL baseURL = new URL(FileNaming.addProtocolToUrl(siteUrl));
            setScenarioName(baseURL.getProtocol()+"://"+baseURL.getHost());
            setIsPage(false);
        } catch (MalformedURLException e) {
            LOGGER.warn("Malformed URL encountered : " + e.getMessage());
        }
    }
    
}
