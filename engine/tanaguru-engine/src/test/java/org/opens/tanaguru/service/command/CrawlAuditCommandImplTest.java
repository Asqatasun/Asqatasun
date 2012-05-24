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

import org.easymock.EasyMock;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.service.CrawlerService;

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
        this.mockInitialisationCalls(false);
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
        
        EasyMock.replay(mockAudit);
        EasyMock.replay(mockAuditDataService);
        EasyMock.replay(mockTestDataService);
        EasyMock.replay(mockParameterDataService);
        
        CrawlAuditCommandImpl instance = new TestCrawlAuditCommandImpl();
        
        assertEquals(mockCrawlerService, instance.getCrawlerService());
        
        EasyMock.verify(mockAudit);
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockTestDataService);
        EasyMock.verify(mockParameterDataService);
    }

    /**
     * Test of init method, of class CrawlAuditCommandImpl.
     */
    public void testInit() {
        System.out.println("init");
        
        mockAudit.setStatus(AuditStatus.CRAWLING);
        EasyMock.expectLastCall().once();
        
        EasyMock.expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();
        
        EasyMock.replay(mockAudit);
        EasyMock.replay(mockAuditDataService);
        EasyMock.replay(mockTestDataService);
        EasyMock.replay(mockParameterDataService);
        
        CrawlAuditCommandImpl instance = new TestCrawlAuditCommandImpl();
        
        instance.init();

        EasyMock.verify(mockAudit);
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockTestDataService);
        EasyMock.verify(mockParameterDataService);
    }

    /**
     * Test of loadContent method, of class CrawlAuditCommandImpl.
     */
    public void testLoadContent() {
        System.out.println("loadContent");
        
        EasyMock.expect(mockAudit.getStatus()).andReturn(AuditStatus.CRAWLING).once();
        
        EasyMock.expect(mockContentDataService.hasContent(mockAudit)).andReturn(true).once();
        
        mockAudit.setStatus(AuditStatus.CONTENT_ADAPTING);
        EasyMock.expectLastCall().once();
        
        EasyMock.expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();
        
        EasyMock.replay(mockAudit);
        EasyMock.replay(mockAuditDataService);
        EasyMock.replay(mockTestDataService);
        EasyMock.replay(mockParameterDataService);
        EasyMock.replay(mockContentDataService);
        
        CrawlAuditCommandImpl instance = new TestCrawlAuditCommandImpl();
        instance.loadContent();
        
        EasyMock.verify(mockAudit);
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockTestDataService);
        EasyMock.verify(mockParameterDataService);
        EasyMock.verify(mockContentDataService);
    }

    public class TestCrawlAuditCommandImpl extends CrawlAuditCommandImpl {

        public TestCrawlAuditCommandImpl() {
            super(
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
        }

        @Override
        public void callCrawlerService() {
            System.out.println("The abstract callCrawlerService is called");
            assertTrue(true);
        }
    }

}