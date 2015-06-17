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
package org.tanaguru.service.command;

import org.easymock.EasyMock;
import org.tanaguru.entity.audit.AuditStatus;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.service.CrawlerService;

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
    public void testCallCrawlerService() {
        System.out.println("callCrawlerService");

        mockInitialisationCalls(false, AuditStatus.CRAWLING);
        
        EasyMock.expect(mockCrawlerService.crawlSite(mockAudit, siteUrl)).
                andReturn(EasyMock.createMock(WebResource.class))
                .once();
        setReplayMode();
        
        SiteAuditCommandImpl siteAuditCommand = getInstance();

        siteAuditCommand.callCrawlerService();
        
        setVerifyMode();
    }

    /**
     * 
     * @return an instance of SiteAuditCommandImpl class
     */
    private SiteAuditCommandImpl getInstance() {
         SiteAuditCommandImpl siteAuditCommand = new SiteAuditCommandImpl(
                siteUrl, 
                null,
                mockAuditDataService);

        siteAuditCommand.setTestDataService(mockTestDataService);
        siteAuditCommand.setParameterDataService(mockParameterDataService);
        siteAuditCommand.setWebResourceDataService(mockWebResourceDataService);
        siteAuditCommand.setContentDataService(mockContentDataService);
        siteAuditCommand.setProcessResultDataService(mockProcessResultDataService);
        siteAuditCommand.setPreProcessResultDataService(mockPreProcessResultDataService);
        siteAuditCommand.setContentAdapterService(mockContentAdapterService);
        siteAuditCommand.setProcessorService(mockProcessorService);
        siteAuditCommand.setConsolidatorService(mockConsolidatorService);
        siteAuditCommand.setAnalyserService(mockAnalyserService);
        siteAuditCommand.setAdaptationListener(mockAdaptationListener);
        siteAuditCommand.setCrawlerService(mockCrawlerService);
        siteAuditCommand.setAdaptationTreatmentWindow(5);
        siteAuditCommand.setProcessingTreatmentWindow(5);
        siteAuditCommand.setConsolidationTreatmentWindow(5);
        siteAuditCommand.setAnalyseTreatmentWindow(5); 
        
        siteAuditCommand.init();

        return siteAuditCommand;
    }
    
    @Override
    protected void setReplayModeOfLocalMocks() {
        EasyMock.replay(mockCrawlerService);
    }

    @Override
    protected void setVerifyModeOfLocalMocks() {
        EasyMock.verify(mockCrawlerService);
    }
}
