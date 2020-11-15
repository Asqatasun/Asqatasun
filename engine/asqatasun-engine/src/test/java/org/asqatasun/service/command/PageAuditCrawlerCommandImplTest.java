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

import org.easymock.EasyMock;
import org.asqatasun.entity.audit.AuditStatus;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.service.CrawlerService;
import org.asqatasun.util.FileNaming;

import java.util.Collections;

/**
 *
 * @author jkowalczyk
 */
public class PageAuditCrawlerCommandImplTest extends AuditCommandTestCase {
    
    private final String pageUrl = "My page";
    private CrawlerService mockCrawlerService;
    
    public PageAuditCrawlerCommandImplTest(String testName) {
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
        
        EasyMock.expect(mockCrawlerService.crawlPage(mockAudit, FileNaming.addProtocolToUrl(pageUrl))).
                andReturn(EasyMock.createMock(WebResource.class))
                .once();
        
        setReplayMode();

        PageAuditCrawlerCommandImpl pageAuditCommand = getInstance();

        pageAuditCommand.callCrawlerService();

        setVerifyMode();
    }
    
    /**
     * 
     * @return an instance of PageAuditCrawlerCommandImpl class
     */
    private PageAuditCrawlerCommandImpl getInstance() {
        PageAuditCrawlerCommandImpl pageAuditCommand = new PageAuditCrawlerCommandImpl(
                pageUrl, 
                null,
                Collections.EMPTY_LIST,
                mockAuditDataService);

        pageAuditCommand.setTestDataService(mockTestDataService);
        pageAuditCommand.setParameterDataService(mockParameterDataService);
        pageAuditCommand.setWebResourceDataService(mockWebResourceDataService);
        pageAuditCommand.setContentDataService(mockContentDataService);
        pageAuditCommand.setProcessResultDataService(mockProcessResultDataService);
        pageAuditCommand.setTagDataService(mockTagDataService);
        pageAuditCommand.setPreProcessResultDataService(mockPreProcessResultDataService);
        pageAuditCommand.setContentAdapterService(mockContentAdapterService);
        pageAuditCommand.setProcessorService(mockProcessorService);
        pageAuditCommand.setConsolidatorService(mockConsolidatorService);
        pageAuditCommand.setAnalyserService(mockAnalyserService);
        pageAuditCommand.setAdaptationListener(mockAdaptationListener);
        pageAuditCommand.setCrawlerService(mockCrawlerService);
        pageAuditCommand.setAdaptationTreatmentWindow(5);
        pageAuditCommand.setProcessingTreatmentWindow(5);
        pageAuditCommand.setConsolidationTreatmentWindow(5);
        pageAuditCommand.setAnalyseTreatmentWindow(5);
        
        pageAuditCommand.init();

        return pageAuditCommand;
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
