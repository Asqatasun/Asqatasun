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

import static org.easymock.EasyMock.*;
import org.tanaguru.entity.audit.AuditStatus;
import org.tanaguru.entity.subject.Page;
import org.tanaguru.entity.subject.Site;
import org.tanaguru.service.ScenarioLoaderService;

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
        mockConstructorCalls();
        mockInitialisationCalls(false, null);
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
        
        setReplayMode();
        
        AbstractScenarioAuditCommandImpl instance = new TestAbstractScenarioAuditCommandImpl();
        
        instance.init();

        setVerifyMode();
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
        
        mockAudit.setStatus(AuditStatus.SCENARIO_LOADING);
        expectLastCall().once();
        
        expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();
        
        expect(mockAudit.getStatus()).andReturn(AuditStatus.SCENARIO_LOADING).once();
        
        mockAudit.setSubject(mockPage);
        expectLastCall().once();
        
        expect(mockScenarioLoaderService.loadScenario(mockPage, myScenario)).andReturn(null).once();
        
        mockAudit.setStatus(AuditStatus.CONTENT_ADAPTING);
        expectLastCall().once();
        
        expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();
        expect(mockWebResourceDataService.createPage(myUrl)).andReturn(mockPage).once();
        expect(mockWebResourceDataService.saveOrUpdate(mockPage)).andReturn(mockPage).once();
        
        replay(mockPage);
        setReplayMode();
        
        AbstractScenarioAuditCommandImpl instance = new TestAbstractScenarioAuditCommandImpl();
        instance.setScenarioName(myUrl);
        instance.setScenario(myScenario);
        instance.setIsPage(true);
        instance.init();
        instance.loadContent();
        
        verify(mockPage);
        setVerifyMode();
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

        mockAudit.setStatus(AuditStatus.SCENARIO_LOADING);
        expectLastCall().once();
        
        expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();
        
        expect(mockAudit.getStatus()).andReturn(AuditStatus.SCENARIO_LOADING).once();
        
        mockAudit.setSubject(mockSite);
        expectLastCall().once();
        
        expect(mockScenarioLoaderService.loadScenario(mockSite, myScenario)).andReturn(null).once();
        
        mockAudit.setStatus(AuditStatus.CONTENT_ADAPTING);
        expectLastCall().once();
        expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();
        
        expect(mockWebResourceDataService.createSite(myUrl)).andReturn(mockSite).once();
        expect(mockWebResourceDataService.saveOrUpdate(mockSite)).andReturn(mockSite).once();
        
        replay(mockSite);
        setReplayMode();
        
        AbstractScenarioAuditCommandImpl instance = new TestAbstractScenarioAuditCommandImpl();
        instance.setScenarioName(myUrl);
        instance.setScenario(myScenario);
        instance.setIsPage(false);
        instance.init();
        instance.loadContent();
        
        verify(mockSite);
        setVerifyMode();
    }

    @Override
    protected void setReplayModeOfLocalMocks() {
        replay(mockScenarioLoaderService);
    }

    @Override
    protected void setVerifyModeOfLocalMocks() {
        verify(mockScenarioLoaderService);
    }

    public class TestAbstractScenarioAuditCommandImpl extends AbstractScenarioAuditCommandImpl {

        public TestAbstractScenarioAuditCommandImpl() {
            super(null, mockAuditDataService);
            setTestDataService(mockTestDataService);
            setParameterDataService(mockParameterDataService);
            setWebResourceDataService(mockWebResourceDataService);
            setContentDataService(mockContentDataService);
            setProcessResultDataService(mockProcessResultDataService);
            setPreProcessResultDataService(mockPreProcessResultDataService);
            setScenarioLoaderService(mockScenarioLoaderService);
            setContentAdapterService(mockContentAdapterService);
            setProcessorService(mockProcessorService);
            setConsolidatorService(mockConsolidatorService);
            setAnalyserService(mockAnalyserService);
            setAdaptationListener(mockAdaptationListener);
            setAdaptationTreatmentWindow(5);
            setProcessingTreatmentWindow(5);
            setConsolidationTreatmentWindow(5);
            setAnalyseTreatmentWindow(5);
        }
        
    }
}
