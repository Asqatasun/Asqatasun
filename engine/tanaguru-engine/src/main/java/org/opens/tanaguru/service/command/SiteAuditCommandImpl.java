/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
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
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */

package org.opens.tanaguru.service.command;

import java.util.Set;
import org.apache.log4j.Logger;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.audit.AuditDataService;

/**
 *
 * @author jkowalczyk
 */
public class SiteAuditCommandImpl extends CrawlAuditCommandImpl {

     /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(SiteAuditCommandImpl.class);
    
    /**
     * The url of the tested site
     */
    private String siteUrl;
    
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
        
        this.siteUrl = siteUrl;
    }
    
    @Override
    public void callCrawlerService() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Launching crawler for page " + siteUrl);
        }
        getCrawlerService().crawlSite(getAudit(), siteUrl);
    }

}