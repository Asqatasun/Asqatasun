/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2011  Open-S Company
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

import java.util.ArrayList;
import java.util.List;
import org.easymock.EasyMock;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.service.CrawlerService;

/**
 *
 * @author jkowalczyk
 */
public class GroupOfPagesAuditCommandImplTest extends AuditCommandTestCase {
    
    private String siteUrl = "My Group of Pages site Name";
    private List<String> urlList = new ArrayList<String>();
    private CrawlerService mockCrawlerService;
    
    public GroupOfPagesAuditCommandImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of callCrawlerService method, of class PageAuditCommandImpl.
     */
    public void testCallCrawlerService() {
        System.out.println("callCrawlerService");

        this.mockInitialisationCalls(true);
        
        mockCrawlerService = EasyMock.createMock(CrawlerService.class);
        EasyMock.expect(mockCrawlerService.crawlGroupOfPages(mockAudit, siteUrl, urlList)).
                andReturn(EasyMock.createMock(WebResource.class))
                .once();
        EasyMock.replay(mockCrawlerService);
        
        GroupOfPagesAuditCommandImpl groupOfPagesAuditCommand = new GroupOfPagesAuditCommandImpl(
                siteUrl, 
                urlList,
                null, 
                mockAuditDataService, 
                mockTestDataService, 
                mockParameterDataService, 
                mockWebResourceDataService, 
                mockContentDataService, 
                mockProcessResultDataService, 
                mockCrawlerService, 
                mockContentAdapterService, 
                mockProcessorService, 
                mockConsolidatorService, 
                mockAnalyserService, 
                mockAdaptationListener);
        
        groupOfPagesAuditCommand.callCrawlerService();
        
        EasyMock.verify(mockCrawlerService);
        EasyMock.verify(mockAudit);
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockTestDataService);
        EasyMock.verify(mockParameterDataService);
    }
}
