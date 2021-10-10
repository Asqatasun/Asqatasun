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

package org.asqatasun.service.command;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.asqatasun.entity.audit.Tag;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.service.audit.AuditDataService;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.util.FileNaming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.asqatasun.entity.contract.ScopeEnum.GROUPOFPAGES;

/**
 *
 * @author jkowalczyk
 */
public class GroupOfPagesAuditCommandImpl extends AuditCommandImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupOfPagesAuditCommandImpl.class);
    List<URL> urlList = new ArrayList<>();

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
                List<Tag> tagList,
                AuditDataService auditDataService) {
        
        super(paramSet, tagList, auditDataService, GROUPOFPAGES);
        try {
            for (String url : pageUrlList) {
                urlList.add(new URL(FileNaming.addProtocolToUrl(url)));
            }
            URL baseURL = new URL(FileNaming.addProtocolToUrl(siteUrl));
            this.targetUrl = baseURL.getProtocol()+"://"+baseURL.getHost();
        } catch (MalformedURLException e) {
            LOGGER.warn("Malformed URL encountered : " + e.getMessage());
        }
    }

    @Override
    protected void callScenarioLoadService(WebResource webResource) {
        scenarioLoaderService.loadScenario(webResource, urlList, 1);
    }

}
