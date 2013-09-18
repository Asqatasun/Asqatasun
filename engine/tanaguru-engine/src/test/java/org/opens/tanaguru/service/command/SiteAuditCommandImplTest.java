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

import org.easymock.EasyMock;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.service.CrawlerService;

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

        mockInitialisationCalls(true, AuditStatus.CRAWLING);
        
        mockCrawlerService = EasyMock.createMock(CrawlerService.class);
        EasyMock.expect(mockCrawlerService.crawlSite(mockAudit, siteUrl)).
                andReturn(EasyMock.createMock(WebResource.class))
                .once();
        EasyMock.replay(mockCrawlerService);
        
        SiteAuditCommandImpl siteAuditCommand = new SiteAuditCommandImpl(
                siteUrl, 
                null);
        siteAuditCommand.setAuditDataService(mockAuditDataService);
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

        siteAuditCommand.callCrawlerService();
        
        EasyMock.verify(mockCrawlerService);
        EasyMock.verify(mockAudit);
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockTestDataService);
        EasyMock.verify(mockParameterDataService);
    }
}
