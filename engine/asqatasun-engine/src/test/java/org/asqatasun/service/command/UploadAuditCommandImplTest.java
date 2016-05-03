/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2015  Asqatasun.org
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.easymock.EasyMock;
import org.asqatasun.entity.audit.AuditStatus;
import org.asqatasun.entity.audit.Content;
import org.asqatasun.entity.subject.Page;
import org.asqatasun.entity.subject.Site;
import org.asqatasun.service.ContentLoaderService;

/**
 *
 * @author jkowalczyk
 */
public class UploadAuditCommandImplTest extends AuditCommandTestCase {
    
    private ContentLoaderService mockContentLoaderService;
    private Map<String, String> fileMap;
    
    public UploadAuditCommandImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        fileMap = new HashMap<>();
        mockContentLoaderService = EasyMock.createMock(ContentLoaderService.class);
        mockConstructorCalls();
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
        
        mockInitialisationCalls(true, AuditStatus.CONTENT_LOADING);
        
        getInstance();

        setVerifyMode();
    }

    /**
     * Test of loadContent method, of class UploadAuditCommandImpl.
     */
    public void testLoadContent1() {
        System.out.println("LoadContent with 1 file");
        
        mockInitialisationCalls(false, AuditStatus.CONTENT_LOADING);
        
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
        
        EasyMock.replay(mockPage);
        setReplayMode();
        
        UploadAuditCommandImpl instance = getInstance();
        
        instance.loadContent();

        EasyMock.verify(mockPage);
        setVerifyMode();
    }
    
    /**
     * Test of loadContent method, of class UploadAuditCommandImpl.
     */
    public void testLoadContent2() {
        System.out.println("LoadContent with several file");
        
        mockInitialisationCalls(false, AuditStatus.CONTENT_LOADING);
        
        fileMap.put("file:///My_File_Name1", "MyFileContent1");
        fileMap.put("file:///My_File_Name2", "MyFileContent2");
        
        EasyMock.expect(mockAudit.getStatus()).andReturn(AuditStatus.CONTENT_LOADING).once();

        Site mockSite = EasyMock.createMock(Site.class);
        Page mockPage1 = EasyMock.createMock(Page.class);
        Page mockPage2 = EasyMock.createMock(Page.class);
        
        EasyMock.expect(mockWebResourceDataService.createSite("file:///My_File_Name1"))
                .andReturn(mockSite)
                .once();
        EasyMock.expect(mockWebResourceDataService.createPage("file:///My_File_Name1"))
                .andReturn(mockPage1)
                .once();
        EasyMock.expect(mockWebResourceDataService.createPage("file:///My_File_Name2"))
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
        
        EasyMock.replay(mockSite);
        EasyMock.replay(mockPage1);
        EasyMock.replay(mockPage2);
        setReplayMode();
        
        UploadAuditCommandImpl instance = getInstance();

        instance.loadContent();

        EasyMock.verify(mockSite);
        EasyMock.verify(mockPage1);
        EasyMock.verify(mockPage2);
        setVerifyMode();
    }
 
    /**
     * 
     * @param fileMap
     * @param paramSet
     * @return 
     */
    private UploadAuditCommandImpl getInstance() {
        UploadAuditCommandImpl instance = new UploadAuditCommandImpl(
                fileMap, 
                null,
                mockAuditDataService);

        instance.setTestDataService(mockTestDataService);
        instance.setParameterDataService(mockParameterDataService);
        instance.setWebResourceDataService(mockWebResourceDataService);
        instance.setContentDataService(mockContentDataService);
        instance.setProcessResultDataService(mockProcessResultDataService);
        instance.setPreProcessResultDataService(mockPreProcessResultDataService);
        instance.setContentAdapterService(mockContentAdapterService);
        instance.setProcessorService(mockProcessorService);
        instance.setConsolidatorService(mockConsolidatorService);
        instance.setAnalyserService(mockAnalyserService);
        instance.setAdaptationListener(mockAdaptationListener);
        instance.setContentLoaderService(mockContentLoaderService);
        instance.setAdaptationTreatmentWindow(5);
        instance.setProcessingTreatmentWindow(5);
        instance.setConsolidationTreatmentWindow(5);
        instance.setAnalyseTreatmentWindow(5); 
        
        instance.init();

        return instance;
    }

    @Override
    protected void setReplayModeOfLocalMocks() {
        EasyMock.replay(mockContentLoaderService);
    }

    @Override
    protected void setVerifyModeOfLocalMocks() {
        EasyMock.verify(mockContentLoaderService);
    }
}