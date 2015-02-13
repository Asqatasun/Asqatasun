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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.util.FileNaming;

/**
 *
 * @author jkowalczyk
 */
public class GroupOfPagesCrawlerAuditCommandImpl extends CrawlAuditCommandImpl {
    
    /**
     * The URL that identifies the list of pages to test
     */
    private String siteUrl;
    
    /**
     * The list of URLs to test
     */
    private List<String> pageUrlList = new ArrayList<String>();
    
    /**
     * 
     * @param siteUrl
     * @param pageUrlList
     * @param paramSet
     * @param auditDataService 
     */
    public GroupOfPagesCrawlerAuditCommandImpl(
            String siteUrl, 
            List<String> pageUrlList,
            Set<Parameter> paramSet,
            AuditDataService auditDataService) {
        
        super(paramSet, auditDataService);

        this.siteUrl = siteUrl;
        for (String url : pageUrlList) {
            this.pageUrlList.add(FileNaming.addProtocolToUrl(url));
        }
    }
    
    @Override
    public void callCrawlerService() {
        getCrawlerService().crawlGroupOfPages(getAudit(), siteUrl, pageUrlList);
    }

}