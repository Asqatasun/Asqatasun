/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Asqatasun.org
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
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.tanaguru.service.command;

import java.util.Set;
import org.apache.log4j.Logger;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.entity.service.audit.AuditDataService;

/**
 *
 * @author jkowalczyk
 */
public final class SiteAuditCommandImpl extends CrawlAuditCommandImpl {

     /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(SiteAuditCommandImpl.class);
    
    /**
     * 
     * @param siteUrl
     * @param paramSet
     * @param auditDataService 
     */
    public SiteAuditCommandImpl(
            String siteUrl, 
            Set<Parameter> paramSet,
            AuditDataService auditDataService) {
        super(paramSet, auditDataService);
        setUrl(siteUrl);
    }
    
    @Override
    public void callCrawlerService() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Launching crawler for page " + getUrl());
        }
        getCrawlerService().crawlSite(getAudit(), getUrl());
    }

    @Override
    void createEmptyWebResource() {
        createEmptySiteResource(getUrl());
    }

}