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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.easymock.EasyMock;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.service.ContentLoaderService;

/**
 *
 * @author jkowalczyk
 */
public class UploadAuditCommandImplTest extends AuditCommandTestCase {
    
    private ContentLoaderService mockContentLoaderService;
    
    public UploadAuditCommandImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockContentLoaderService = EasyMock.createMock(ContentLoaderService.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of init method, of class UploadAuditCommandImpl.
     */
    public void testInit() {
        System.out.println("init");
        
        mockInitialisationCalls(false);
        
        mockAudit.setStatus(AuditStatus.CONTENT_LOADING);
        EasyMock.expectLastCall().once();
        
        EasyMock.expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();
        
        EasyMock.replay(mockAudit);
        EasyMock.replay(mockAuditDataService);
        EasyMock.replay(mockTestDataService);
        EasyMock.replay(mockParameterDataService);
        
        UploadAuditCommandImpl instance = new UploadAuditCommandImpl(
                new HashMap<String, String>(), 
                null, 
                mockAuditDataService, 
                mockTestDataService, 
                mockParameterDataService, 
                mockWebResourceDataService, 
                mockContentDataService, 
                mockProcessResultDataService, 
                mockContentLoaderService, 
                mockContentAdapterService, 
                mockProcessorService, 
                mockConsolidatorService, 
                mockAnalyserService, 
                mockAdaptationListener,
                5,
                5,
                5,
                5);
        instance.init();

        EasyMock.verify(mockAudit);
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockTestDataService);
        EasyMock.verify(mockParameterDataService);
    }

    /**
     * Test of loadContent method, of class UploadAuditCommandImpl.
     */
    public void testLoadContent1() {
        System.out.println("LoadContent with 1 file");
        
        mockInitialisationCalls(false);
        
        Map<String, String> fileMap = new HashMap<String, String>();
        fileMap.put("My File Name", "MyFileContent");
        
        EasyMock.expect(mockAudit.getStatus()).andReturn(AuditStatus.CONTENT_LOADING).once();

        Page mockPage = EasyMock.createMock(Page.class);
        
        EasyMock.expect(mockWebResourceDataService.createPage("My File Name"))
                .andReturn(mockPage)
                .once();
        mockPage.setAudit(mockAudit);
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockWebResourceDataService.saveOrUpdate(mockPage))
                .andReturn(mockPage)
                .once();
        mockAudit.setSubject(mockPage);
        EasyMock.expectLastCall().once();
        
        EasyMock.expect(mockAudit.getSubject()).
                andReturn(mockPage).
                once();
        
        EasyMock.expect(mockContentLoaderService.loadContent(mockPage, fileMap))
                .andReturn(new ArrayList<Content>())
                .once();

        mockAudit.setStatus(AuditStatus.CONTENT_ADAPTING);
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockAuditDataService.saveOrUpdate(mockAudit))
                .andReturn(mockAudit)
                .once();
        
        EasyMock.replay(mockAudit);
        EasyMock.replay(mockAuditDataService);
        EasyMock.replay(mockWebResourceDataService);
        EasyMock.replay(mockTestDataService);
        EasyMock.replay(mockParameterDataService);
        EasyMock.replay(mockContentLoaderService);
        EasyMock.replay(mockPage);
        
        UploadAuditCommandImpl instance = new UploadAuditCommandImpl(
                fileMap, 
                null, 
                mockAuditDataService, 
                mockTestDataService, 
                mockParameterDataService, 
                mockWebResourceDataService, 
                mockContentDataService, 
                mockProcessResultDataService, 
                mockContentLoaderService, 
                mockContentAdapterService, 
                mockProcessorService, 
                mockConsolidatorService, 
                mockAnalyserService, 
                mockAdaptationListener,
                5,
                5,
                5,
                5);
        
        instance.loadContent();

        EasyMock.verify(mockAudit);
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockTestDataService);
        EasyMock.verify(mockParameterDataService);
        EasyMock.verify(mockWebResourceDataService);
        EasyMock.verify(mockContentLoaderService);
        EasyMock.verify(mockPage);
    }
    
    /**
     * Test of loadContent method, of class UploadAuditCommandImpl.
     */
    public void testLoadContent2() {
        System.out.println("LoadContent with several file");
        
        mockInitialisationCalls(false);
        
        Map<String, String> fileMap = new LinkedHashMap<String, String>();
        fileMap.put("file:///My File Name1", "MyFileContent1");
        fileMap.put("file:///My File Name2", "MyFileContent2");
        
        EasyMock.expect(mockAudit.getStatus()).andReturn(AuditStatus.CONTENT_LOADING).once();

        Site mockSite = EasyMock.createMock(Site.class);
        Page mockPage1 = EasyMock.createMock(Page.class);
        Page mockPage2 = EasyMock.createMock(Page.class);
        
        EasyMock.expect(mockWebResourceDataService.createSite("file:///My File Name1"))
                .andReturn(mockSite)
                .once();
        EasyMock.expect(mockWebResourceDataService.createPage("file:///My File Name1"))
                .andReturn(mockPage1)
                .once();
        EasyMock.expect(mockWebResourceDataService.createPage("file:///My File Name2"))
                .andReturn(mockPage2)
                .once();
        
        mockSite.addChild(mockPage1);
        EasyMock.expectLastCall().once();
        mockSite.addChild(mockPage2);
        EasyMock.expectLastCall().once();
        mockSite.setAudit(mockAudit);
        EasyMock.expectLastCall().once();
        
        EasyMock.expect(mockWebResourceDataService.saveOrUpdate(mockSite))
                .andReturn(mockSite)
                .times(2);
        EasyMock.expect(mockWebResourceDataService.saveOrUpdate(mockPage1))
                .andReturn(mockSite)
                .once();
        EasyMock.expect(mockWebResourceDataService.saveOrUpdate(mockPage2))
                .andReturn(mockSite)
                .once();
        mockAudit.setSubject(mockSite);
        EasyMock.expectLastCall().once();
        
        EasyMock.expect(mockAudit.getSubject()).
                andReturn(mockSite).
                once();
        
        EasyMock.expect(mockContentLoaderService.loadContent(mockSite, fileMap))
                .andReturn(new ArrayList<Content>())
                .once();

        mockAudit.setStatus(AuditStatus.CONTENT_ADAPTING);
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockAuditDataService.saveOrUpdate(mockAudit))
                .andReturn(mockAudit)
                .once();
        
        EasyMock.replay(mockAudit);
        EasyMock.replay(mockAuditDataService);
        EasyMock.replay(mockWebResourceDataService);
        EasyMock.replay(mockTestDataService);
        EasyMock.replay(mockParameterDataService);
        EasyMock.replay(mockContentLoaderService);
        EasyMock.replay(mockSite);
        EasyMock.replay(mockPage1);
        EasyMock.replay(mockPage2);
        
        UploadAuditCommandImpl instance = new UploadAuditCommandImpl(
                fileMap, 
                null, 
                mockAuditDataService, 
                mockTestDataService, 
                mockParameterDataService, 
                mockWebResourceDataService, 
                mockContentDataService, 
                mockProcessResultDataService, 
                mockContentLoaderService, 
                mockContentAdapterService, 
                mockProcessorService, 
                mockConsolidatorService, 
                mockAnalyserService, 
                mockAdaptationListener,
                5,
                5,
                5,
                5);
        
        instance.loadContent();

        EasyMock.verify(mockAudit);
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockTestDataService);
        EasyMock.verify(mockParameterDataService);
        EasyMock.verify(mockWebResourceDataService);
        EasyMock.verify(mockContentLoaderService);
        EasyMock.verify(mockSite);
        EasyMock.verify(mockPage1);
        EasyMock.verify(mockPage2);
    }
    
}