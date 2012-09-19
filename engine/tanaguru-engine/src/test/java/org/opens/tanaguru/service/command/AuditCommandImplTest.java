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

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;
import static org.easymock.EasyMock.*;
import org.opens.tanaguru.contentadapter.AdaptationListener;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
import org.opens.tanaguru.entity.service.parameterization.ParameterDataService;
import org.opens.tanaguru.entity.service.reference.TestDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.service.AnalyserService;
import org.opens.tanaguru.service.ConsolidatorService;
import org.opens.tanaguru.service.ContentAdapterService;
import org.opens.tanaguru.service.ProcessorService;
import org.opens.tanaguru.util.MD5Encoder;

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
        
        mockInitialisationCalls(true);
        
        AuditCommandImpl instance = new AuditCommandImplTest.TestAuditCommandImpl();

        instance.setAudit(mockAudit);
        assertEquals(mockAudit, instance.getAudit());
    }

    /**
     * Test of getAuditDataService method, of class AuditCommandImpl.
     */
    public void testGetAuditDataService() {
        System.out.println("getAuditDataService");
        
        mockInitialisationCalls(true);
        
        AuditCommandImpl instance = new AuditCommandImplTest.TestAuditCommandImpl();
        
        AuditDataService result = instance.getAuditDataService();
        assertEquals(mockAuditDataService, result);
        
        verify(mockAuditDataService);
        verify(mockTestDataService);
        verify(mockParameterDataService);
        verify(mockAudit);
    }

    /**
     * Test of getTestDataService method, of class AuditCommandImpl.
     */
    public void testGetTestDataService() {
        System.out.println("getTestDataService");
        
        mockInitialisationCalls(true);
        
        AuditCommandImpl instance = new AuditCommandImplTest.TestAuditCommandImpl();
        
        TestDataService result = instance.getTestDataService();
        assertEquals(mockTestDataService, result);
        
        verify(mockAuditDataService);
        verify(mockTestDataService);
        verify(mockParameterDataService);
        verify(mockAudit);
    }

    /**
     * Test of getParameterDataService method, of class AuditCommandImpl.
     */
    public void testGetParameterDataService() {
        System.out.println("getParameterDataService");
        
        mockInitialisationCalls(true);
        
        AuditCommandImpl instance = new AuditCommandImplTest.TestAuditCommandImpl();

        ParameterDataService result = instance.getParameterDataService();
        assertEquals(mockParameterDataService, result);
        
        verify(mockAuditDataService);
        verify(mockTestDataService);
        verify(mockParameterDataService);
        verify(mockAudit);
    }

    /**
     * Test of getWebResourceDataService method, of class AuditCommandImpl.
     */
    public void testGetWebResourceDataService() {
        System.out.println("getWebResourceDataService");
        
        mockInitialisationCalls(true);
        
        AuditCommandImpl instance = new AuditCommandImplTest.TestAuditCommandImpl();

        WebResourceDataService result = instance.getWebResourceDataService();
        assertEquals(mockWebResourceDataService, result);
        
        verify(mockAuditDataService);
        verify(mockTestDataService);
        verify(mockParameterDataService);
        verify(mockAudit);
    }

    /**
     * Test of getContentDataService method, of class AuditCommandImpl.
     */
    public void testGetContentDataService() {
        System.out.println("getContentDataService");
        
        mockInitialisationCalls(true);
        
        AuditCommandImpl instance = new AuditCommandImplTest.TestAuditCommandImpl();

        ContentDataService result = instance.getContentDataService();
        assertEquals(mockContentDataService, result);
        
        verify(mockAuditDataService);
        verify(mockTestDataService);
        verify(mockParameterDataService);
        verify(mockAudit);
    }

    /**
     * Test of getProcessResultDataService method, of class AuditCommandImpl.
     */
    public void testGetProcessResultDataService() {
        System.out.println("getProcessResultDataService");
        
        mockInitialisationCalls(true);
        
        AuditCommandImpl instance = new AuditCommandImplTest.TestAuditCommandImpl();

        ProcessResultDataService result = instance.getProcessResultDataService();
        assertEquals(mockProcessResultDataService, result);
        
        verify(mockAuditDataService);
        verify(mockTestDataService);
        verify(mockParameterDataService);
        verify(mockAudit);
    }

    /**
     * Test of getContentAdapterService method, of class AuditCommandImpl.
     */
    public void testGetContentAdapterService() {
        System.out.println("getContentAdapterService");
        
        mockInitialisationCalls(true);
        
        AuditCommandImpl instance = new AuditCommandImplTest.TestAuditCommandImpl();

        ContentAdapterService result = instance.getContentAdapterService();
        assertEquals(mockContentAdapterService, result);
        
        verify(mockAuditDataService);
        verify(mockTestDataService);
        verify(mockParameterDataService);
        verify(mockAudit);
    }

    /**
     * Test of getProcessorService method, of class AuditCommandImpl.
     */
    public void testGetProcessorService() {
        System.out.println("getProcessorService");
        
        mockInitialisationCalls(true);
        
        AuditCommandImpl instance = new AuditCommandImplTest.TestAuditCommandImpl();

        ProcessorService result = instance.getProcessorService();
        assertEquals(mockProcessorService, result);
        
        verify(mockAuditDataService);
        verify(mockTestDataService);
        verify(mockParameterDataService);
        verify(mockAudit);
    }

    /**
     * Test of getConsolidatorService method, of class AuditCommandImpl.
     */
    public void testGetConsolidatorService() {
        System.out.println("getConsolidatorService");
        
        mockInitialisationCalls(true);
        
        AuditCommandImpl instance = new AuditCommandImplTest.TestAuditCommandImpl();

        ConsolidatorService result = instance.getConsolidatorService();
        assertEquals(mockConsolidatorService, result);
        
        verify(mockAuditDataService);
        verify(mockTestDataService);
        verify(mockParameterDataService);
        verify(mockAudit);
    }

    /**
     * Test of getAnalyserService method, of class AuditCommandImpl.
     */
    public void testGetAnalyserService() {
        System.out.println("getAnalyserService");
        
        mockInitialisationCalls(true);

        AuditCommandImpl instance = new AuditCommandImplTest.TestAuditCommandImpl();

        AnalyserService result = instance.getAnalyserService();
        assertEquals(mockAnalyserService, result);
        
        verify(mockAuditDataService);
        verify(mockTestDataService);
        verify(mockParameterDataService);
        verify(mockAudit);
    }

    /**
     * Test of getAdaptationListener method, of class AuditCommandImpl.
     */
    public void testGetAdaptationListener() {
        System.out.println("getAdaptationListener");
        
        mockInitialisationCalls(true);
        
        AuditCommandImpl instance = new AuditCommandImplTest.TestAuditCommandImpl();

        AdaptationListener result = instance.getAdaptationListener();
        assertEquals(mockAdaptationListener, result);
        
        verify(mockAuditDataService);
        verify(mockTestDataService);
        verify(mockParameterDataService);
        verify(mockAudit);
    }

    /**
     * Test of adaptContent method, of class AuditCommandImpl.
     */
    public void testAdaptContent() {
        System.out.println("adaptContent");
        
        mockInitialisationCalls(false);

        WebResource mockWr = createMock(WebResource.class);
        
        expect(mockAudit.getId()).andReturn(Long.valueOf(1)).once();
        expect(mockAuditDataService.getAuditWithWebResource(Long.valueOf(1))).andReturn(mockAudit).once();
        expect(mockAudit.getStatus()).andReturn(AuditStatus.CONTENT_ADAPTING).once();
        expect(mockAudit.getSubject()).andReturn(mockWr).times(2);
        expect(mockWr.getId()).andReturn(Long.valueOf(1)).once();
        
        expect(mockContentDataService.getNumberOfSSPFromWebResource(
                mockWr, 
                HttpStatus.SC_OK)).andReturn(Long.valueOf(49)).once();
        
        List<Long> longList = new ArrayList<Long>(25);
        expect(mockContentDataService.getSSPFromWebResource(
                Long.valueOf(1), 
                HttpStatus.SC_OK,
                0, 
                25)).andReturn(longList).once();
        
        expect(mockContentDataService.getSSPFromWebResource(
                Long.valueOf(1), 
                HttpStatus.SC_OK,
                25, 
                25)).andReturn(longList).once();
        
        // the adaptContent must return at least one non empty SSP
        SSP mockSSP = createMock(SSP.class);
        expect(mockSSP.getDOM()).andReturn("Not Empty String").times(3);
        expect(mockSSP.getSource()).andReturn("Not Empty String").times(2);
        try {
            mockSSP.setSource(MD5Encoder.MD5("Not Empty String"));
            expectLastCall().times(2);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(this.getClass()).error(ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(this.getClass()).error(ex);
        }
        List<Content> mockAdaptedContentList = new ArrayList<Content>();
        mockAdaptedContentList.add(mockSSP);
        
        expect(mockContentAdapterService.adaptContent(new ArrayList<Content>())).
                andReturn(mockAdaptedContentList).times(2);
        
        expect(mockContentDataService.saveOrUpdate(mockSSP)).
                andReturn(mockSSP).times(2);
        
        mockAudit.setStatus(AuditStatus.PROCESSING);
        expectLastCall().once();
        
        expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();
        
        replay(mockAudit);
        replay(mockWr);
        replay(mockAuditDataService);
        replay(mockContentDataService);
        replay(mockContentAdapterService);
        replay(mockTestDataService);
        replay(mockParameterDataService);
        replay(mockSSP);
        
        AuditCommandImpl instance = new AuditCommandImplTest.TestAuditCommandImpl();
        
        instance.adaptContent();

        verify(mockAuditDataService);
        verify(mockTestDataService);
        verify(mockContentDataService);
        verify(mockContentAdapterService);
        verify(mockParameterDataService);
        verify(mockAudit);
        verify(mockWr);
        verify(mockSSP);
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

    public class TestAuditCommandImpl extends AuditCommandImpl {

        public TestAuditCommandImpl() {
            super(
                    null, 
                    mockAuditDataService, 
                    mockTestDataService, 
                    mockParameterDataService, 
                    mockWebResourceDataService, 
                    mockContentDataService, 
                    mockProcessResultDataService, 
                    mockContentAdapterService, 
                    mockProcessorService, 
                    mockConsolidatorService, 
                    mockAnalyserService, 
                    mockAdaptationListener,
                    25,
                    25,
                    1000,
                    200);
        }

        @Override
        public void init() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void loadContent() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
