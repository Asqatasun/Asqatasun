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

import org.opens.tanaguru.service.ScenarioLoaderService;
import static org.easymock.EasyMock.*;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;

/**
 *
 * @author jkowalczyk
 */
public class AbstractScenarioAuditCommandImplTest extends AuditCommandTestCase {
    
    private ScenarioLoaderService mockScenarioLoaderService;
    
    public AbstractScenarioAuditCommandImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockScenarioLoaderService = createMock(ScenarioLoaderService.class);
        this.mockInitialisationCalls(false);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of init method, of class AbstractScenarioAuditCommandImpl.
     */
    public void testInit() {
        System.out.println("init");
        
        mockAudit.setStatus(AuditStatus.SCENARIO_LOADING);
        expectLastCall().once();
        
        expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();
        
        replay(mockAudit);
        replay(mockAuditDataService);
        replay(mockTestDataService);
        replay(mockParameterDataService);
        
        AbstractScenarioAuditCommandImpl instance = new TestAbstractScenarioAuditCommandImpl();
        
        instance.init();

        verify(mockAudit);
        verify(mockAuditDataService);
        verify(mockTestDataService);
        verify(mockParameterDataService);
    }

    /**
     * Test of loadContent method, of class AbstractScenarioAuditCommandImpl.
     */
    public void testLoadContent() {
        System.out.println("loadContent with page as main resource");
        
        String myScenario = "My Scenario";
        String myUrl = "My Scenario";
        
        Page mockPage = createMock(Page.class);
        mockPage.setAudit(mockAudit);
        expectLastCall().once();

        expect(mockAudit.getStatus()).andReturn(AuditStatus.SCENARIO_LOADING).once();
        mockAudit.setSubject(mockPage);
        expectLastCall().once();
        expect(mockScenarioLoaderService.loadScenario(mockPage, myScenario)).andReturn(null).once();
        
        mockAudit.setStatus(AuditStatus.CONTENT_ADAPTING);
        expectLastCall().once();
        
        expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();
        expect(mockWebResourceDataService.createPage(myUrl)).andReturn(mockPage).once();
        expect(mockWebResourceDataService.saveOrUpdate(mockPage)).andReturn(mockPage).once();
        
        replay(mockAudit);
        replay(mockAuditDataService);
        replay(mockTestDataService);
        replay(mockParameterDataService);
        replay(mockWebResourceDataService);
        replay(mockScenarioLoaderService);
        replay(mockPage);
        
        AbstractScenarioAuditCommandImpl instance = new TestAbstractScenarioAuditCommandImpl();
        instance.setScenarioName(myUrl);
        instance.setScenario(myScenario);
        instance.setIsPage(true);
        instance.loadContent();
        
        verify(mockAudit);
        verify(mockAuditDataService);
        verify(mockTestDataService);
        verify(mockParameterDataService);
        verify(mockWebResourceDataService);
        verify(mockScenarioLoaderService);
        verify(mockPage);
    }
    
    /**
     * Test of loadContent method, of class AbstractScenarioAuditCommandImpl.
     */
    public void testLoadContent2() {
        System.out.println("loadContent with site as main resource");
        
        String myScenario = "My Scenario";
        String myUrl = "My Scenario";
        
        Site mockSite = createMock(Site.class);
        mockSite.setAudit(mockAudit);
        expectLastCall().once();

        expect(mockAudit.getStatus()).andReturn(AuditStatus.SCENARIO_LOADING).once();
        mockAudit.setSubject(mockSite);
        expectLastCall().once();
        expect(mockScenarioLoaderService.loadScenario(mockSite, myScenario)).andReturn(null).once();
        
        mockAudit.setStatus(AuditStatus.CONTENT_ADAPTING);
        expectLastCall().once();
        
        expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();
        expect(mockWebResourceDataService.createSite(myUrl)).andReturn(mockSite).once();
        expect(mockWebResourceDataService.saveOrUpdate(mockSite)).andReturn(mockSite).once();
        
        replay(mockAudit);
        replay(mockAuditDataService);
        replay(mockTestDataService);
        replay(mockParameterDataService);
        replay(mockWebResourceDataService);
        replay(mockScenarioLoaderService);
        replay(mockSite);
        
        AbstractScenarioAuditCommandImpl instance = new TestAbstractScenarioAuditCommandImpl();
        instance.setScenarioName(myUrl);
        instance.setScenario(myScenario);
        instance.setIsPage(false);
        instance.loadContent();
        
        verify(mockAudit);
        verify(mockAuditDataService);
        verify(mockTestDataService);
        verify(mockParameterDataService);
        verify(mockWebResourceDataService);
        verify(mockScenarioLoaderService);
        verify(mockSite);
    }

    public class TestAbstractScenarioAuditCommandImpl extends AbstractScenarioAuditCommandImpl {

        public TestAbstractScenarioAuditCommandImpl() {
            super(
                    null, 
                    mockAuditDataService, 
                    mockTestDataService, 
                    mockParameterDataService, 
                    mockWebResourceDataService, 
                    mockContentDataService, 
                    mockProcessResultDataService, 
                    mockScenarioLoaderService,
                    mockContentAdapterService, 
                    mockProcessorService, 
                    mockConsolidatorService, 
                    mockAnalyserService, 
                    mockAdaptationListener,
                    5,
                    5,
                    5,
                    5);
        }
    }
}
