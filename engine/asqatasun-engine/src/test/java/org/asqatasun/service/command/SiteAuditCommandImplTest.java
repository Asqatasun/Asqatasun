/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2021  Asqatasun.org
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

import org.asqatasun.entity.subject.*;
import org.easymock.EasyMock;
import org.asqatasun.entity.audit.AuditStatus;
import org.asqatasun.service.CrawlerService;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.EMPTY_LIST;
import static org.easymock.EasyMock.expect;

/**
 *
 * @author jkowalczyk
 */
public class SiteAuditCommandImplTest extends AuditCommandTestCase {
    
    private String siteUrl = "My Site URL";
    private CrawlerService mockCrawlerService;
    
    public SiteAuditCommandImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockCrawlerService = EasyMock.createMock(CrawlerService.class);
        mockConstructorCalls();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of callCrawlerService method, of class PageAuditCommandImpl.
     */
    public void testCrawl() {
        System.out.println("callCrawlerService");

        Site site = new SiteImpl("https://test.org");
        List<WebResource> pageList = new ArrayList<>();
        pageList.add(new PageImpl("https://test.org/page1.html"));
        pageList.add(new PageImpl("https://test.org/page2.html"));
        pageList.add(new PageImpl("https://test.org/page3.html"));
        pageList.add(new PageImpl("https://test.org/page4.html"));
        mockInitialisationCalls(false, AuditStatus.CRAWLING);

        expect(mockAudit.getStatus()).andReturn(AuditStatus.CRAWLING).once();
        expect(mockAudit.getSubject()).andReturn(site).once();
        expect(mockWebResourceDataService.getChildWebResourceCount(site)).andReturn(4L).times(1);

        mockAudit.setStatus(AuditStatus.SCENARIO_LOADING);
        EasyMock.expectLastCall().once();

        EasyMock.expect(mockCrawlerService.crawlSite(mockAudit, "http://"+siteUrl)).
                andReturn(EasyMock.createMock(WebResource.class))
                .once();

        EasyMock.expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();
        setReplayMode();
        
        SiteAuditCommandImpl siteAuditCommand = getInstance();

        siteAuditCommand.crawl();
        setVerifyMode();
    }

    /**
     * Test of init method, of class CrawlAuditCommandImpl.
     */
    public void testInit() {
        System.out.println("init");

        mockInitialisationCalls(false, null);

        mockAudit.setStatus(AuditStatus.CRAWLING);
        EasyMock.expectLastCall().once();

        EasyMock.expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();

        setReplayMode();

        // init call is done when instantiated
        getInstance();

        setVerifyMode();
    }

    @Override
    protected void setReplayModeOfLocalMocks() {
        EasyMock.replay(mockCrawlerService);
        EasyMock.replay(mockContentDataService);
    }

    @Override
    protected void setVerifyModeOfLocalMocks() {
        EasyMock.verify(mockCrawlerService);
        EasyMock.verify(mockContentDataService);
    }

    /**
     * 
     * @return an instance of SiteAuditCommandImpl class
     */
    private SiteAuditCommandImpl getInstance() {
         SiteAuditCommandImpl siteAuditCommand = new SiteAuditCommandImpl(
                siteUrl, 
                null,
                EMPTY_LIST,
                mockCrawlerService,
                mockAuditDataService);

        siteAuditCommand.setTestDataService(mockTestDataService);
        siteAuditCommand.setParameterDataService(mockParameterDataService);
        siteAuditCommand.setWebResourceDataService(mockWebResourceDataService);
        siteAuditCommand.setContentDataService(mockContentDataService);
        siteAuditCommand.setProcessResultDataService(mockProcessResultDataService);
        siteAuditCommand.setTagDataService(mockTagDataService);
        siteAuditCommand.setPreProcessResultDataService(mockPreProcessResultDataService);
        siteAuditCommand.setContentAdapterService(mockContentAdapterService);
        siteAuditCommand.setProcessorService(mockProcessorService);
        siteAuditCommand.setConsolidatorService(mockConsolidatorService);
        siteAuditCommand.setAnalyserService(mockAnalyserService);
        siteAuditCommand.setAdaptationListener(mockAdaptationListener);
        siteAuditCommand.setAdaptationTreatmentWindow(5);
        siteAuditCommand.setProcessingTreatmentWindow(5);
        siteAuditCommand.setConsolidationTreatmentWindow(5);
        siteAuditCommand.setAnalyseTreatmentWindow(5); 
        
        siteAuditCommand.init();

        return siteAuditCommand;
    }

}
