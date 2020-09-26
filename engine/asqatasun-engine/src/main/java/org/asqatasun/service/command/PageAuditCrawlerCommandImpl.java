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

import org.asqatasun.entity.audit.Tag;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.service.audit.AuditDataService;
import org.asqatasun.util.FileNaming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jkowalczyk
 */
public class PageAuditCrawlerCommandImpl extends CrawlAuditCommandImpl {

     /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PageAuditCrawlerCommandImpl.class);
    
    /**
     * 
     * @param pageUrl
     * @param paramSet
     * @param auditDataService 
     */
    public PageAuditCrawlerCommandImpl(
                String pageUrl,
                Set<Parameter> paramSet,
                List<Tag> tagList,
                AuditDataService auditDataService) {
        super(paramSet, tagList, auditDataService);
        setUrl(FileNaming.addProtocolToUrl(pageUrl));
    }
    
    @Override
    public void callCrawlerService() {
        LOGGER.info("Launching crawler for page " + getUrl());
        getCrawlerService().crawlPage(getAudit(), getUrl());
    }

    @Override
    void createEmptyWebResource() {
        createEmptyPageResource(getUrl());
    }

}
