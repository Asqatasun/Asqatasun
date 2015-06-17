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
import org.tanaguru.service.CrawlerService;

/**
 *
 * @author jkowalczyk
 */
public class CrawlAuditCommandImplTest extends AuditCommandTestCase {
    
    private CrawlerService mockCrawlerService;
    
    public CrawlAuditCommandImplTest(String testName) {
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
     * Test of getCrawlerService method, of class CrawlAuditCommandImpl.
     */
    public void testGetCrawlerService() {
        System.out.println("getCrawlerService");
        
        setReplayMode();
        
        CrawlAuditCommandImpl instance = new TestCrawlAuditCommandImpl();
        
        assertEquals(mockCrawlerService, instance.getCrawlerService());
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
        
        CrawlAuditCommandImpl instance = new TestCrawlAuditCommandImpl();
        
        instance.init();

        setVerifyMode();
    }

    /**
     * Test of loadContent method, of class CrawlAuditCommandImpl.
     */
    public void testLoadContent() {
        System.out.println("loadContent");
        
        mockInitialisationCalls(false, null);
        
        // expecting part
        mockAudit.setStatus(AuditStatus.CRAWLING);
        EasyMock.expectLastCall().once();
        
        EasyMock.expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();
        EasyMock.expect(mockAudit.getStatus()).andReturn(AuditStatus.CRAWLING).once();
        
        EasyMock.expect(mockContentDataService.hasContent(mockAudit)).andReturn(true).once();
        
        mockAudit.setStatus(AuditStatus.CONTENT_ADAPTING);
        EasyMock.expectLastCall().once();
        
        EasyMock.expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();
        
        setReplayMode();
        
        // test
        CrawlAuditCommandImpl instance = new TestCrawlAuditCommandImpl();
        instance.init();
        instance.loadContent();
        
        // verification
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

    public class TestCrawlAuditCommandImpl extends CrawlAuditCommandImpl {

        public TestCrawlAuditCommandImpl() {
            super(null, mockAuditDataService);
            setTestDataService(mockTestDataService);
            setParameterDataService(mockParameterDataService);
            setWebResourceDataService(mockWebResourceDataService);
            setContentDataService(mockContentDataService);
            setProcessResultDataService(mockProcessResultDataService);
            setPreProcessResultDataService(mockPreProcessResultDataService);
            setContentAdapterService(mockContentAdapterService);
            setProcessorService(mockProcessorService);
            setConsolidatorService(mockConsolidatorService);
            setAnalyserService(mockAnalyserService);
            setAdaptationListener(mockAdaptationListener);
            setCrawlerService(mockCrawlerService);
            setAdaptationTreatmentWindow(5);
            setProcessingTreatmentWindow(5);
            setConsolidationTreatmentWindow(5);
            setAnalyseTreatmentWindow(5);
        }

        @Override
        public void callCrawlerService() {
            System.out.println("The abstract callCrawlerService is called");
            assertTrue(true);
        }

        @Override
        void createEmptyWebResource() {}
    }

}