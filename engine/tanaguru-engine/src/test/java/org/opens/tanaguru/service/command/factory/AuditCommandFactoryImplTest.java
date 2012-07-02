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
package org.opens.tanaguru.service.command.factory;

import java.util.List;
import java.util.Map;
import java.util.Set;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.opens.tanaguru.contentadapter.AdaptationListener;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.parameterization.Parameter;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.entity.service.reference.TestDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.service.*;
import org.opens.tanaguru.service.command.*;

/**
 *
 * @author jkowalczyk
 */
public class AuditCommandFactoryImplTest extends TestCase {
    
    private AuditCommandFactoryImpl auditCommandFactory;
    
    private Audit mockAudit;
    
    private AuditDataService mockAuditDataService;
    private TestDataService mockTestDataService;
    private ParameterDataService mockParameterDataService;
    private WebResourceDataService mockWebResourceDataService;
    private ProcessResultDataService mockProcessResultDataService;
    
    private ContentDataService mockContentDataService;
    private ContentLoaderService mockContentLoaderService;
    private ScenarioLoaderService mockScenarioLoaderService;
    private CrawlerService mockCrawlerService;
    private ContentAdapterService mockContentAdapterService;
    private ProcessorService mockProcessorService;
    private ConsolidatorService mockConsolidatorService;
    private AnalyserService mockAnalyserService;
    private AdaptationListener mockAdaptationListener;
    
    public AuditCommandFactoryImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mockAuditDataService = EasyMock.createMock(AuditDataService.class);
        mockTestDataService = EasyMock.createMock(TestDataService.class);
        mockParameterDataService = EasyMock.createMock(ParameterDataService.class);
        mockWebResourceDataService = EasyMock.createMock(WebResourceDataService.class); 
        mockContentDataService = EasyMock.createMock(ContentDataService.class);
        mockContentLoaderService = EasyMock.createMock(ContentLoaderService.class);
        mockScenarioLoaderService = EasyMock.createMock(ScenarioLoaderService.class);
        mockProcessResultDataService = EasyMock.createMock(ProcessResultDataService.class);
        mockContentAdapterService = EasyMock.createMock(ContentAdapterService.class);
        mockProcessorService = EasyMock.createMock(ProcessorService.class);
        mockCrawlerService = EasyMock.createMock(CrawlerService.class);
        mockConsolidatorService = EasyMock.createMock(ConsolidatorService.class);
        mockAnalyserService = EasyMock.createMock(AnalyserService.class);
        mockAdaptationListener = EasyMock.createMock(AdaptationListener.class);
        
        auditCommandFactory = new AuditCommandFactoryImpl();
        
        auditCommandFactory.setAdaptationListener(mockAdaptationListener);
        auditCommandFactory.setAnalyserService(mockAnalyserService);
        auditCommandFactory.setAuditDataService(mockAuditDataService);
        auditCommandFactory.setConsolidatorService(mockConsolidatorService);
        auditCommandFactory.setContentDataService(mockContentDataService);
        auditCommandFactory.setContentLoaderService(mockContentLoaderService);
        auditCommandFactory.setCrawlerService(mockCrawlerService);
        auditCommandFactory.setParameterDataService(mockParameterDataService);
        auditCommandFactory.setProcessResultDataService(mockProcessResultDataService);
        auditCommandFactory.setProcessorService(mockProcessorService);
        auditCommandFactory.setContentAdapterService(mockContentAdapterService);
        auditCommandFactory.setScenarioLoaderService(mockScenarioLoaderService);
        auditCommandFactory.setTestDataService(mockTestDataService);
        auditCommandFactory.setWebResourceDataService(mockWebResourceDataService);
        
        mockInitialisationCalls();
    }
    
    private void mockInitialisationCalls() {
        Set<Parameter> nullParameterSet = null ;
        EasyMock.expect(mockParameterDataService.saveOrUpdate(nullParameterSet)).andReturn(nullParameterSet).once();
        
        List<Test> testList = null;
        EasyMock.expect(mockTestDataService.getTestListFromParamSet(nullParameterSet)).andReturn(testList).once();
        
        mockAudit = EasyMock.createMock(Audit.class);

        EasyMock.expect(mockAuditDataService.create()).andReturn(mockAudit).once();

        mockAudit.setTestList(testList);
        EasyMock.expectLastCall().once();
        mockAudit.setParameterSet(nullParameterSet);
        EasyMock.expectLastCall().once();
        mockAudit.setStatus(AuditStatus.INITIALISATION);
        EasyMock.expectLastCall().once();
        
        EasyMock.expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();
        
        EasyMock.replay(mockAudit);
        EasyMock.replay(mockAuditDataService);
        EasyMock.replay(mockTestDataService);
        EasyMock.replay(mockParameterDataService);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of create method, of class AuditCommandFactoryImpl.
     */
    public void testCreate_3args_1() {
        System.out.println("create PageAuditCommand with crawler");
        String url = "";
        Set<Parameter> paramSet = null;
        boolean isSite = false;
        auditCommandFactory.setAuditPageWithCrawler(true);
        AuditCommand result = this.auditCommandFactory.create(url, paramSet, isSite);
        assertTrue(result instanceof PageAuditCrawlerCommandImpl);
        
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockTestDataService);
        EasyMock.verify(mockParameterDataService);
        EasyMock.verify(mockAudit);
    }
    
    /**
     * Test of create method, of class AuditCommandFactoryImpl.
     */
    public void testCreate_3args_1_2() {
        System.out.println("create PageAuditCommand without crawler");
        String url = "";
        Set<Parameter> paramSet = null;
        boolean isSite = false;
        auditCommandFactory.setAuditPageWithCrawler(false);
        AuditCommand result = this.auditCommandFactory.create(url, paramSet, isSite);
        assertTrue(result instanceof PageAuditCommandImpl);
        
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockTestDataService);
        EasyMock.verify(mockParameterDataService);
        EasyMock.verify(mockAudit);
    }
    
    /**
     * Test of create method, of class AuditCommandFactoryImpl.
     */
    public void testCreate_3args_2() {
        System.out.println("create SiteAuditCommand");
        
        String url = "";
        Set<Parameter> paramSet = null;
        boolean isSite = true;

        AuditCommand result = this.auditCommandFactory.create(url, paramSet, isSite);
        assertTrue(result instanceof SiteAuditCommandImpl);
        
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockTestDataService);
        EasyMock.verify(mockParameterDataService);
        EasyMock.verify(mockAudit);
    }

    /**
     * Test of create method, of class AuditCommandFactoryImpl.
     */
    public void testCreate_Map_Set() {
        System.out.println("create UploadAuditCommand");
        Map<String, String> fileMap = null;
        Set<Parameter> paramSet = null;
        
        AuditCommand result = this.auditCommandFactory.create(fileMap, paramSet);
        assertTrue(result instanceof UploadAuditCommandImpl);
        
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockTestDataService);
        EasyMock.verify(mockParameterDataService);
        EasyMock.verify(mockAudit);
    }

    /**
     * Test of create method, of class AuditCommandFactoryImpl.
     */
    public void testCreate_3args_3_1() {
        System.out.println("create GroupOfPagesAuditCommand with crawler");
        String siteUrl = "";
        List<String> pageUrlList = null;
        Set<Parameter> paramSet = null;
        
        auditCommandFactory.setAuditPageWithCrawler(true);
        AuditCommand result = this.auditCommandFactory.create(siteUrl, pageUrlList, paramSet);
        assertTrue(result instanceof GroupOfPagesCrawlerAuditCommandImpl);
        
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockTestDataService);
        EasyMock.verify(mockParameterDataService);
        EasyMock.verify(mockAudit);
    }
    
    /**
     * Test of create method, of class AuditCommandFactoryImpl.
     */
    public void testCreate_3args_3_2() {
        System.out.println("create GroupOfPagesAuditCommand without crawler");
        String siteUrl = "";
        List<String> pageUrlList = null;
        Set<Parameter> paramSet = null;
        
        auditCommandFactory.setAuditPageWithCrawler(false);
        AuditCommand result = this.auditCommandFactory.create(siteUrl, pageUrlList, paramSet);
        assertTrue(result instanceof GroupOfPagesAuditCommandImpl);
        
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockTestDataService);
        EasyMock.verify(mockParameterDataService);
        EasyMock.verify(mockAudit);
    }

    /**
     * Test of create method, of class AuditCommandFactoryImpl.
     */
    public void testCreate_3args_4() {
        System.out.println("create ScenarioAuditCommand");
        String scenarioName = "";
        String scenario = "";
        Set<Parameter> paramSet = null;
        
        AuditCommand result = this.auditCommandFactory.create(scenarioName, scenario, paramSet);
        assertTrue(result instanceof ScenarioAuditCommandImpl);
        
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockTestDataService);
        EasyMock.verify(mockParameterDataService);
        EasyMock.verify(mockAudit);
    }
}
