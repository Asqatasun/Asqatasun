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

import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.persistence.PersistenceException;
import org.asqatasun.entity.audit.AuditStatus;
import org.asqatasun.entity.audit.Content;
import org.asqatasun.entity.audit.Tag;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.service.audit.AuditDataService;
import org.asqatasun.entity.subject.Page;
import org.asqatasun.entity.subject.Site;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.service.ContentLoaderService;
import org.asqatasun.util.FileNaming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.asqatasun.entity.contract.ScopeEnum.FILE;

/**
 *
 * @author jkowalczyk
 */
public class UploadAuditCommandImpl extends AuditCommandImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadAuditCommandImpl.class);
    
    /**
     * The map that contains the files to test identified by 
     */
    private Map<String, String> fileMap;

    /**
     * The contentLoaderService
     */
    private ContentLoaderService contentLoaderService;
    public void setContentLoaderService(ContentLoaderService contentLoaderService) {
        this.contentLoaderService = contentLoaderService;
    }
    
    /**
     * 
     * @param fileMap
     * @param paramSet
     * @param auditDataService 
     */
    public UploadAuditCommandImpl(
            Map<String, String> fileMap, 
            Set<Parameter> paramSet,
            List<Tag> tagList,
            AuditDataService auditDataService) {
        super(paramSet, tagList, auditDataService, FILE);
        
        this.fileMap = fileMap;
    }

    @Override
    public void init() {
        super.init();
        setStatusToAudit(AuditStatus.CONTENT_LOADING);
    }

    @Override
    public void loadContent() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Loading files content " + fileMap);
        }
        if (!getAudit().getStatus().equals(AuditStatus.CONTENT_LOADING) || fileMap.isEmpty()) {
            LOGGER.warn(
                    new StringBuilder("Audit Status is ")
                    .append(getAudit().getStatus())
                    .append(" while ")
                    .append(AuditStatus.CONTENT_LOADING)
                    .append(" was required ").toString());
            setStatusToAudit(AuditStatus.ERROR);
            return;
        }

        createWebResources();
        //call the load content service to convert files into SSP and link it
        //to the appropriate webResource
        List<Content> contentList = contentLoaderService.loadContent(getAudit().getSubject(), fileMap);

        for (Content content : contentList) {

            content.setAudit(getAudit());
            try {
                contentDataService.saveOrUpdate(content);
            } catch (PersistenceException pe) {
                getAudit().setStatus(AuditStatus.ERROR);
                break;
            }
        }

        setStatusToAudit(AuditStatus.CONTENT_ADAPTING);
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info(fileMap +" has been loaded");
        }
    }
    
    private void createWebResources() {
        WebResource webResource;
        if (fileMap.size() > 1) {
            webResource = webResourceDataService.createSite(
                    FileNaming.addProtocolToUrl(fileMap.keySet().iterator().next()));
            webResourceDataService.saveOrUpdate(webResource);
            for (String pageUrl : fileMap.keySet()) {
                Page page = webResourceDataService.createPage(pageUrl);
                ((Site) webResource).addChild(page);
                webResourceDataService.saveOrUpdate(page);
            }
        } else {
            webResource = webResourceDataService.createPage(fileMap.keySet().iterator().next());
        }
        // the webresource needs to be persisted a second time because of the
        // relation with the audit
        webResource.setAudit(getAudit());
        webResourceDataService.saveOrUpdate(webResource);
        getAudit().setSubject(webResource);
    }

}
