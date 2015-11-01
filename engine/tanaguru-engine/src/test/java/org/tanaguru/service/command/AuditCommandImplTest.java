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
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.service.command;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import static org.easymock.EasyMock.*;
import org.tanaguru.contentadapter.AdaptationListener;
import org.tanaguru.entity.audit.AuditStatus;
import org.tanaguru.entity.audit.Content;
import org.tanaguru.entity.audit.SSP;
import org.tanaguru.entity.service.audit.AuditDataService;
import org.tanaguru.entity.service.audit.ContentDataService;
import org.tanaguru.entity.service.audit.ProcessResultDataService;
import org.tanaguru.entity.service.parameterization.ParameterDataService;
import org.tanaguru.entity.service.reference.TestDataService;
import org.tanaguru.entity.service.subject.WebResourceDataService;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.service.AnalyserService;
import org.tanaguru.service.ConsolidatorService;
import org.tanaguru.service.ContentAdapterService;
import org.tanaguru.service.ProcessorService;
import org.tanaguru.util.MD5Encoder;

/**
 *
 * @author jkowalczyk
 */
public class AuditCommandImplTest extends AuditCommandTestCase {

    public AuditCommandImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockConstructorCalls();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of getAudit method, of class AuditCommandImpl.
     */
    public void testSetGetAudit() {
        System.out.println("getAudit");
        
        mockInitialisationCalls(true, null);
        
        AuditCommandImpl instance = new TestAuditCommandImpl();

        instance.setAudit(mockAudit);
        assertEquals(mockAudit, instance.getAudit());
    }

    /**
     * Test of getAuditDataService method, of class AuditCommandImpl.
     */
    public void testGetAuditDataService() {
        System.out.println("getAuditDataService");
        
        mockInitialisationCalls(true, null);
        
        AuditCommandImpl instance = new TestAuditCommandImpl();
        
        AuditDataService result = instance.getAuditDataService();
        assertEquals(mockAuditDataService, result);
        
        setVerifyMode();
    }

    /**
     * Test of getTestDataService method, of class AuditCommandImpl.
     */
    public void testGetTestDataService() {
        System.out.println("getTestDataService");
        
        mockInitialisationCalls(true, null);
        
        AuditCommandImpl instance = new TestAuditCommandImpl();
        
        TestDataService result = instance.getTestDataService();
        assertEquals(mockTestDataService, result);
        
        setVerifyMode();
    }

    /**
     * Test of getParameterDataService method, of class AuditCommandImpl.
     */
    public void testGetParameterDataService() {
        System.out.println("getParameterDataService");
        
        mockInitialisationCalls(true, null);
        
        AuditCommandImpl instance = new TestAuditCommandImpl();

        ParameterDataService result = instance.getParameterDataService();
        assertEquals(mockParameterDataService, result);
        
        setVerifyMode();
    }

    /**
     * Test of getWebResourceDataService method, of class AuditCommandImpl.
     */
    public void testGetWebResourceDataService() {
        System.out.println("getWebResourceDataService");
        
        mockInitialisationCalls(true, null);
        
        AuditCommandImpl instance = new TestAuditCommandImpl();

        WebResourceDataService result = instance.getWebResourceDataService();
        assertEquals(mockWebResourceDataService, result);
        
        setVerifyMode();
    }

    /**
     * Test of getContentDataService method, of class AuditCommandImpl.
     */
    public void testGetContentDataService() {
        System.out.println("getContentDataService");
        
        mockInitialisationCalls(true, null);
        
        AuditCommandImpl instance = new TestAuditCommandImpl();

        ContentDataService result = instance.getContentDataService();
        assertEquals(mockContentDataService, result);
        
        setVerifyMode();
    }

    /**
     * Test of getProcessResultDataService method, of class AuditCommandImpl.
     */
    public void testGetProcessResultDataService() {
        System.out.println("getProcessResultDataService");
        
        mockInitialisationCalls(true, null);
        
        AuditCommandImpl instance = new TestAuditCommandImpl();

        ProcessResultDataService result = instance.getProcessResultDataService();
        assertEquals(mockProcessResultDataService, result);
        
        setVerifyMode();
    }

    /**
     * Test of getContentAdapterService method, of class AuditCommandImpl.
     */
    public void testGetContentAdapterService() {
        System.out.println("getContentAdapterService");
        
        mockInitialisationCalls(true, null);
        
        AuditCommandImpl instance = new TestAuditCommandImpl();

        ContentAdapterService result = instance.getContentAdapterService();
        assertEquals(mockContentAdapterService, result);
        
        setVerifyMode();
    }

    /**
     * Test of getProcessorService method, of class AuditCommandImpl.
     */
    public void testGetProcessorService() {
        System.out.println("getProcessorService");
        
        mockInitialisationCalls(true, null);
        
        AuditCommandImpl instance = new TestAuditCommandImpl();

        ProcessorService result = instance.getProcessorService();
        assertEquals(mockProcessorService, result);
        
        setVerifyMode();
    }

    /**
     * Test of getConsolidatorService method, of class AuditCommandImpl.
     */
    public void testGetConsolidatorService() {
        System.out.println("getConsolidatorService");
        
        mockInitialisationCalls(true, null);
        
        AuditCommandImpl instance = new TestAuditCommandImpl();

        ConsolidatorService result = instance.getConsolidatorService();
        assertEquals(mockConsolidatorService, result);
        
        setVerifyMode();
    }

    /**
     * Test of getAnalyserService method, of class AuditCommandImpl.
     */
    public void testGetAnalyserService() {
        System.out.println("getAnalyserService");
        
        mockInitialisationCalls(true, null);

        AuditCommandImpl instance = new TestAuditCommandImpl();

        AnalyserService result = instance.getAnalyserService();
        assertEquals(mockAnalyserService, result);
        
        setVerifyMode();
    }

    /**
     * Test of getAdaptationListener method, of class AuditCommandImpl.
     */
    public void testGetAdaptationListener() {
        System.out.println("getAdaptationListener");
        
        mockInitialisationCalls(true, null);
        
        AuditCommandImpl instance = new TestAuditCommandImpl();

        AdaptationListener result = instance.getAdaptationListener();
        assertEquals(mockAdaptationListener, result);
        
        setVerifyMode();
    }

    /**
     * Test of adaptContent method, of class AuditCommandImpl.
     */
    public void testAdaptContent() {
        System.out.println("adaptContent");
        
        mockInitialisationCalls(false, null);

        WebResource mockWr = createMock(WebResource.class);
        
        expect(mockAudit.getId()).andReturn(Long.valueOf(1)).once();
        expect(mockAuditDataService.read(Long.valueOf(1))).andReturn(mockAudit).once();
        expect(mockAudit.getStatus()).andReturn(AuditStatus.CONTENT_ADAPTING).once();
        expect(mockAudit.getSubject()).andReturn(mockWr).anyTimes();
        expect(mockWr.getURL()).andReturn("").anyTimes();
        expect(mockWr.getId()).andReturn(Long.valueOf(1)).once();
        
        expect(mockContentDataService.getNumberOfSSPFromWebResource(
                mockWr, 
                HttpStatus.SC_OK)).andReturn(Long.valueOf(49)).once();
        
        expect(mockContentDataService.getSSPFromWebResource(
                Long.valueOf(1), 
                Long.valueOf(0), 
                25,
                true)).andReturn(new ArrayList<Content>()).once();
        
        expect(mockContentDataService.getSSPFromWebResource(
                Long.valueOf(1), 
                Long.valueOf(25), 
                25,
                true)).andReturn(new ArrayList<Content>()).once();
        
        // the adaptContent must return at least one non empty SSP
        SSP mockSSP = createMock(SSP.class);
        expect(mockSSP.getDOM()).andReturn("Not Empty String").times(3);
        expect(mockSSP.getSource()).andReturn("Not Empty String").times(2);
        try {
            mockSSP.setSource(MD5Encoder.MD5("Not Empty String"));
            expectLastCall().times(2);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass()).error(ex);
        }
        List<Content> mockAdaptedContentList = new ArrayList<>();
        mockAdaptedContentList.add(mockSSP);
        
        expect(mockContentAdapterService.adaptContent(new ArrayList<Content>())).
                andReturn(mockAdaptedContentList).times(2);
        
        expect(mockContentDataService.saveOrUpdate(mockSSP)).
                andReturn(mockSSP).times(2);
        
        mockAudit.setStatus(AuditStatus.PROCESSING);
        expectLastCall().once();
        
        expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();
        
        replay(mockWr);
        replay(mockContentDataService);
        replay(mockContentAdapterService);
        replay(mockSSP);
        
        setReplayMode();
        
        AuditCommandImpl instance = new TestAuditCommandImpl();
        
        instance.adaptContent();

        verify(mockContentDataService);
        verify(mockContentAdapterService);
        verify(mockWr);
        verify(mockSSP);
        setVerifyMode();
    }

    /**
     * Test of process method, of class AuditCommandImpl.
     */
    public void testProcess() {
        System.out.println("process");
        AuditCommandImpl instance = null;
//        instance.process();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of consolidate method, of class AuditCommandImpl.
     */
    public void testConsolidate() {
        System.out.println("consolidate");
        AuditCommandImpl instance = null;
//        instance.consolidate();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of analyse method, of class AuditCommandImpl.
     */
    public void testAnalyse() {
        System.out.println("analyse");
        AuditCommandImpl instance = null;
//        instance.analyse();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatusToAudit method, of class AuditCommandImpl.
     */
    public void testSetStatusToAudit() {
        System.out.println("setStatusToAudit");
        AuditStatus auditStatus = null;
        AuditCommandImpl instance = null;
//        instance.setStatusToAudit(auditStatus);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    @Override
    protected void setReplayModeOfLocalMocks() {
        
    }

    @Override
    protected void setVerifyModeOfLocalMocks() {
        
    }

    public class TestAuditCommandImpl extends AuditCommandImpl {

        public TestAuditCommandImpl() {
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
            setAdaptationTreatmentWindow(25);
            setProcessingTreatmentWindow(25);
            setConsolidationTreatmentWindow(1000);
            setAnalyseTreatmentWindow(200);
            init();
        }

//        @Override
//        public void init() {
//            throw new UnsupportedOperationException("Not supported yet.");
//        }

        @Override
        public void loadContent() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
