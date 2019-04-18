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

import java.util.Set;
import org.apache.log4j.Logger;
import org.asqatasun.entity.audit.AuditStatus;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.service.audit.AuditDataService;
import org.asqatasun.service.AuditServiceImpl;
import org.asqatasun.service.CrawlerService;
import org.asqatasun.util.http.HttpRequestHandler;

/**
 *
 * @author jkowalczyk
 */
public abstract class CrawlAuditCommandImpl extends AuditCommandImpl {
    
    /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(CrawlAuditCommandImpl.class);
    
    /**
     * The crawlerService instance
     */
    private CrawlerService crawlerService;
    public CrawlerService getCrawlerService() {
        return crawlerService;
    }
    public void setCrawlerService(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }
    
    private String url;
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * 
     * @param paramSet
     * @param auditDataService 
     */
    public CrawlAuditCommandImpl(
            Set<Parameter> paramSet,
            AuditDataService auditDataService) {
        super(paramSet, auditDataService);
    }
    
    @Override
    public void init() {
        if (HttpRequestHandler.getInstance().isUrlAccessible(url)) {
            super.init();
            setStatusToAudit(AuditStatus.CRAWLING);
        } else {
            super.init();
            createEmptyWebResource();
            setStatusToAudit(AuditStatus.ERROR);
        }
    }
    
    @Override
    public void loadContent() {
        if (!getAudit().getStatus().equals(AuditStatus.CRAWLING)) {
            LOGGER.warn(
                    new StringBuilder("Audit status is ")
                    .append(getAudit().getStatus())
                    .append(" while ")
                    .append(AuditStatus.CRAWLING)
                    .append(" was required.").toString());
            return;
        }

        callCrawlerService();
        
        if (getContentDataService().hasContent(getAudit())) {
            setStatusToAudit(AuditStatus.CONTENT_ADAPTING);
        } else {
            Logger.getLogger(AuditServiceImpl.class).warn("Audit has no content");
            setStatusToAudit(AuditStatus.ERROR);
        }
    }

    /**
     * Call the crawler service in an appropriate way regarding the audit type
     */
    abstract void callCrawlerService();
    
    /**
     *
     */
    abstract void createEmptyWebResource();
    
}
