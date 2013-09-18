/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2013  Open-S Company
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
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.util.FileNaming;

/**
 *
 * @author jkowalczyk
 */
public class PageAuditCrawlerCommandImpl extends CrawlAuditCommandImpl {

     /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(PageAuditCrawlerCommandImpl.class);
    
    /**
     * The url of the tested page
     */
    private String pageUrl;
    
    /**
     * 
     * @param pageUrl
     * @param paramSet 
     */
    public PageAuditCrawlerCommandImpl(
                String pageUrl,
                Set<Parameter> paramSet) {
        
        super(paramSet);
        
        this.pageUrl = FileNaming.addProtocolToUrl(pageUrl);
    }
    
    @Override
    public void callCrawlerService() {
        if (LOGGER.isInfoEnabled()) {
            LOGGER.info("Launching crawler for page " + pageUrl);
        }
        getCrawlerService().crawlPage(getAudit(), pageUrl);
    }

}