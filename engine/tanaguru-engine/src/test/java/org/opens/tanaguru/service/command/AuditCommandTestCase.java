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

import java.util.List;
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
import org.opens.tanaguru.service.AnalyserService;
import org.opens.tanaguru.service.ConsolidatorService;
import org.opens.tanaguru.service.ContentAdapterService;
import org.opens.tanaguru.service.ProcessorService;

/**
 *
 * @author jkowalczyk
 */
public abstract class AuditCommandTestCase extends TestCase{

    public Audit mockAudit;
    public AuditDataService mockAuditDataService;
    public TestDataService mockTestDataService;
    public ParameterDataService mockParameterDataService;
    public WebResourceDataService mockWebResourceDataService;
    public ContentDataService mockContentDataService;
    public ProcessResultDataService mockProcessResultDataService;
    public ContentAdapterService mockContentAdapterService;
    public ProcessorService mockProcessorService;
    public ConsolidatorService mockConsolidatorService;
    public AnalyserService mockAnalyserService;
    public AdaptationListener mockAdaptationListener;
    
    public AuditCommandTestCase(String testName) {
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
        mockProcessResultDataService = EasyMock.createMock(ProcessResultDataService.class);
        mockContentAdapterService = EasyMock.createMock(ContentAdapterService.class);
        mockProcessorService = EasyMock.createMock(ProcessorService.class);
        mockConsolidatorService = EasyMock.createMock(ConsolidatorService.class);
        mockAnalyserService = EasyMock.createMock(AnalyserService.class);
        mockAdaptationListener = EasyMock.createMock(AdaptationListener.class);
    }
    
    protected void mockInitialisationCalls(boolean isMockInReplayMode) {
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
        
        if (isMockInReplayMode) {
            EasyMock.replay(mockAudit);
            EasyMock.replay(mockAuditDataService);
            EasyMock.replay(mockTestDataService);
            EasyMock.replay(mockParameterDataService);
        }
    }
    
}