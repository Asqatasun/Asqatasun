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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.asqatasun.entity.audit.Tag;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.service.audit.AuditDataService;
import org.asqatasun.util.FileNaming;

/**
 *
 * @author jkowalczyk
 */
public class GroupOfPagesCrawlerAuditCommandImpl extends CrawlAuditCommandImpl {
    
    /**
     * The list of URLs to test
     */
    private final List<String> pageUrlList = new ArrayList<>();
    
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
            List<Tag> tagList,
            AuditDataService auditDataService) {
        
        super(paramSet, tagList, auditDataService);

        setUrl(siteUrl);
        for (String url : pageUrlList) {
            this.pageUrlList.add(FileNaming.addProtocolToUrl(url));
        }
    }
    
    @Override
    public void callCrawlerService() {
        getCrawlerService().crawlGroupOfPages(getAudit(), getUrl(), pageUrlList);
    }

    @Override
    void createEmptyWebResource() {
        createEmptySiteResource(getUrl());
    }

}
