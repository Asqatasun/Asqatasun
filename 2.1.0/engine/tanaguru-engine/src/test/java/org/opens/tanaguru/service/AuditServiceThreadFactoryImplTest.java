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
package org.opens.tanaguru.service;

import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.opens.tanaguru.contentadapter.AdaptationListener;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.service.command.AuditCommand;

/**
 *
 * @author jkowalczyk
 */
public class AuditServiceThreadFactoryImplTest extends TestCase {
    
    private AuditDataService mockAuditDataService;
    private ContentDataService mockContentDataService;
    private ProcessResultDataService mockProcessResultDataService;
    private WebResourceDataService mockWebResourceDataService;
    private CrawlerService mockCrawlerService;
    private ContentLoaderService mockContentLoaderService;
    private ScenarioLoaderService mockScenarioLoaderService;
    private ContentAdapterService mockContentAdapterService;
    private ProcessorService mockProcessorService;
    private ConsolidatorService mockConsolidatorService;
    private AnalyserService mockAnalyserService;
    private AdaptationListener mockAdaptationListener;
    
    public AuditServiceThreadFactoryImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockAuditDataService = EasyMock.createMock(AuditDataService.class);
        mockContentDataService = EasyMock.createMock(ContentDataService.class);
        mockProcessResultDataService = EasyMock.createMock(ProcessResultDataService.class);
        mockWebResourceDataService = EasyMock.createMock(WebResourceDataService.class);
        mockCrawlerService = EasyMock.createMock(CrawlerService.class);
        mockContentLoaderService = EasyMock.createMock(ContentLoaderService.class);
        mockScenarioLoaderService = EasyMock.createMock(ScenarioLoaderService.class);
        mockContentAdapterService = EasyMock.createMock(ContentAdapterService.class);
        mockProcessorService = EasyMock.createMock(ProcessorService.class);
        mockConsolidatorService = EasyMock.createMock(ConsolidatorService.class);
        mockAnalyserService = EasyMock.createMock(AnalyserService.class);
        mockAdaptationListener = EasyMock.createMock(AdaptationListener.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of create method, of class AuditServiceThreadFactoryImpl.
     */
    public void testCreate1() {
        System.out.println("create from Audit");
        Audit audit = EasyMock.createMock(Audit.class);
        AuditServiceThreadFactoryImpl instance = initiliseAuditServiceThreadFactory();
        
        AuditServiceThread result = instance.create(audit);
        
        // the create method returns a result AuditServiceThreadImpl instance 
        // set-up with all the services and data-services
        assertTrue(result instanceof AuditServiceThreadImpl);
        assertEquals(audit, result.getAudit());
    }
    
    /**
     * Test of create method, of class AuditServiceThreadFactoryImpl.
     */
    public void testCreate2() {
        System.out.println("create from AuditCommand");
        AuditCommand auditCommand = EasyMock.createMock(AuditCommand.class);
        AuditServiceThreadFactoryImpl instance = initiliseAuditServiceThreadFactory();
        
        AuditServiceThread result = instance.create(auditCommand);
        
        // the create method returns a result AuditServiceThreadImpl instance 
        // set-up with all the services and data-services
        assertTrue(result instanceof AuditServiceThreadImpl);
    }
    
    private AuditServiceThreadFactoryImpl initiliseAuditServiceThreadFactory() {
        AuditServiceThreadFactoryImpl instance = new AuditServiceThreadFactoryImpl();
//        instance.setAdaptationListener(mockAdaptationListener);
//        instance.setAnalyserService(mockAnalyserService);
//        instance.setAuditDataService(mockAuditDataService);
//        instance.setConsolidatorService(mockConsolidatorService);
//        instance.setContentAdapterService(mockContentAdapterService);
//        instance.setContentDataService(mockContentDataService);
//        instance.setContentLoaderService(mockContentLoaderService);
//        instance.setCrawlerService(mockCrawlerService);
//        instance.setProcessResultDataService(mockProcessResultDataService);
//        instance.setProcessorService(mockProcessorService);
//        instance.setScenarioLoaderService(mockScenarioLoaderService);
//        instance.setWebResourceDataService(mockWebResourceDataService);
        return instance;
    }
}
