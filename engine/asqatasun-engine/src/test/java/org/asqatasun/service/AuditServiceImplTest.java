/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2019  Asqatasun.org
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
package org.asqatasun.service;

import java.util.*;
import java.util.concurrent.Callable;

import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.service.command.AuditCommand;
import org.asqatasun.service.command.factory.AuditCommandFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jkowalczyk
 */
public class AuditServiceImplTest extends TestCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuditServiceImplTest.class);

    private AuditServiceThreadFactory mockAuditServiceThreadFactory;
    private AuditServiceThreadQueue mockAuditServiceThreadQueue;
    private Audit mockAuditCreateByAuditCommand;
    private AuditCommand mockAuditCommand;
    private AuditCommandFactory mockAuditCommandFactory;

    public AuditServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockAuditServiceThreadFactory = EasyMock.createMock(AuditServiceThreadFactory.class);
        mockAuditServiceThreadQueue = EasyMock.createMock(AuditServiceThreadQueue.class);
        mockAuditCreateByAuditCommand = EasyMock.createMock(Audit.class);
        mockAuditCommand = EasyMock.createMock(AuditCommand.class);
        mockAuditCommandFactory = EasyMock.createMock(AuditCommandFactory.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of remove method, of class AuditServiceImpl.
     */
    public void testAddAndRemove() {
        AuditServiceImpl instance = initialiseAuditService(null);
        AuditServiceListener mockAuditServiceListener = 
                EasyMock.createMock(AuditServiceListener.class);
        // when try to remove a listener not recorded, nothing happened
        instance.remove(mockAuditServiceListener);
        
        instance.add(mockAuditServiceListener);
        assertTrue(instance.getListeners().contains(mockAuditServiceListener));
        instance.remove(mockAuditServiceListener);
        assertTrue(instance.getListeners().isEmpty());
    }

    /**
     * Test of auditPage method, of class AuditServiceImpl.
     */
    public void testAuditPage() throws Exception {
        String pageUrl = "http://My.testUrl.org";

        checkCreatedAuditAndVerifyMockExecution(
                initCommand(() -> mockAuditCommandFactory.create(pageUrl, null, null,false),
                            () -> mockAuditServiceThreadQueue.addPageAudit(mockAuditCommand))
                    .auditPage(pageUrl, null, null));
    }
    
    /**
     * Test of auditPage method, of class AuditServiceImpl.
     */
    public void testAuditSite() throws Exception {
        String siteUrl = "http://My.testUrl.org";

        checkCreatedAuditAndVerifyMockExecution(
            initCommand(() ->mockAuditCommandFactory.create(siteUrl, null, null, true),
                        () -> mockAuditServiceThreadQueue.addSiteAudit(mockAuditCommand))
                .auditSite(siteUrl, null, null));
    }
    
    /**
     * Test of auditPage method, of class AuditServiceImpl.
     */
    public void testAuditGroupOfPages() throws Exception {
        String siteUrl = "http://My.testUrl.org";
        List<String> urlList = Arrays.asList("http://My.testUrlPage1.org");

        checkCreatedAuditAndVerifyMockExecution(
            initCommand(() -> mockAuditCommandFactory.create(siteUrl, urlList, null, null),
                        () -> mockAuditServiceThreadQueue.addPageAudit(mockAuditCommand))
                .auditSite(siteUrl, urlList, null, null));
    }

    /**
     * Test of auditPageUpload method, of class AuditServiceImpl.
     */
    public void testAuditPageUploadWithOneFile() throws Exception {
        // test we only one file is passed as argument to be tested
        Map<String, String> fileMap = new HashMap<String, String>() {{ put("file://test1", ""); }};

        checkCreatedAuditAndVerifyMockExecution(
            initCommand(() -> mockAuditCommandFactory.create(fileMap, null, null),
                        () -> mockAuditServiceThreadQueue.addPageUploadAudit(mockAuditCommand))
                .auditPageUpload(fileMap, null, null));
    }
    
    /**
     * Test of auditPage method, of class AuditServiceImpl.
     */
    public void testAuditScenario() throws Exception {
        System.out.println("auditScenario");
        String scenarioName = "MyScenario";
        String scenario = "";

        checkCreatedAuditAndVerifyMockExecution(
            initCommand(() -> mockAuditCommandFactory.create(scenarioName, scenario,null,  null),
                        () -> mockAuditServiceThreadQueue.addScenarioAudit(mockAuditCommand))
                .auditScenario(scenarioName, scenario, null, null));
    }

    /**
     * Test of audit method, of class AuditServiceImpl.
     */
    public void testAudit() {
        AuditServiceImpl instance = initialiseAuditService(null);
        
        Audit audit = EasyMock.createMock(Audit.class);
        Audit auditReturnedByAuditMethodOfAuditServiceThread = EasyMock.createMock(Audit.class);
        
        AuditServiceThread mockAuditServiceThread = EasyMock.createMock(AuditServiceThread.class);
        
        mockAuditServiceThread.run();
        EasyMock.expectLastCall();
        EasyMock.expect(mockAuditServiceThread.getAudit()).
                andReturn(auditReturnedByAuditMethodOfAuditServiceThread).anyTimes();
        EasyMock.replay(mockAuditServiceThread);
        
        EasyMock.expect(mockAuditServiceThreadFactory.create(audit)).andReturn(mockAuditServiceThread).anyTimes();
        EasyMock.replay(mockAuditServiceThreadFactory);
        
        assertEquals(auditReturnedByAuditMethodOfAuditServiceThread, instance.audit(audit));
        
        EasyMock.verify(mockAuditServiceThread);
        EasyMock.verify(mockAuditServiceThreadFactory);
    }

    /**
     * Test of auditCompleted method, of class AuditServiceImpl.
     */
    public void testAuditCompleted() {
        AuditServiceImpl instance = initialiseAuditService(null);
        
        Audit mockAudit = EasyMock.createMock(Audit.class);
        AuditServiceListener mockAuditServiceListener = EasyMock.createMock(AuditServiceListener.class);
        
        mockAuditServiceListener.auditCompleted(mockAudit);
        EasyMock.expectLastCall().anyTimes();
        
        EasyMock.replay(mockAuditServiceListener);
        EasyMock.replay(mockAudit);
        
        instance.add(mockAuditServiceListener);
        instance.auditCompleted(mockAudit);
        
        EasyMock.verify(mockAudit);
        EasyMock.verify(mockAuditServiceListener);
    }
    
    /**
     * Test of auditCompleted method, of class AuditServiceImpl.
     */
    public void testAuditCrashed() {
        AuditServiceImpl instance = initialiseAuditService(null);
        
        Audit mockAudit = EasyMock.createMock(Audit.class);
        Exception mockException = EasyMock.createMock(Exception.class);
        AuditServiceListener mockAuditServiceListener = EasyMock.createMock(AuditServiceListener.class);
        
        mockAuditServiceListener.auditCrashed(mockAudit, mockException);
        EasyMock.expectLastCall().anyTimes();
        
        EasyMock.replay(mockAuditServiceListener);
        EasyMock.replay(mockAudit);
        EasyMock.replay(mockException);
        
        instance.add(mockAuditServiceListener);
        instance.auditCrashed(mockAudit, mockException);
        
        EasyMock.verify(mockAudit);
        EasyMock.verify(mockAuditServiceListener);
        EasyMock.verify(mockException);
    }
    
    private AuditServiceImpl initialiseAuditService(AuditCommandFactory auditCommandFactory) {
        return new AuditServiceImpl(
            mockAuditServiceThreadFactory,
            auditCommandFactory,
            mockAuditServiceThreadQueue);
    }


    private AuditService initCommand(
        Callable<AuditCommand> createMockFunction,
        Runnable addAuditToQueueFunction) throws Exception {
        EasyMock.expect(mockAuditCommand.getAudit()).andReturn(mockAuditCreateByAuditCommand).anyTimes();
        EasyMock.replay(mockAuditCommand);

        EasyMock.expect(createMockFunction.call()).andReturn(mockAuditCommand).anyTimes();
        EasyMock.replay(mockAuditCommandFactory);

        AuditServiceImpl instance = initialiseAuditService(mockAuditCommandFactory);

        // anyTimes the audit is created and set-up, the auditServiceThreadQueue is
        // called to effectively launch the page audit
        mockAuditServiceThreadQueue.add(instance);
        EasyMock.expectLastCall().anyTimes();
        addAuditToQueueFunction.run();
        EasyMock.expectLastCall().anyTimes();
        EasyMock.replay(mockAuditServiceThreadQueue);

        return instance;
    }

    private void checkCreatedAuditAndVerifyMockExecution(Audit audit) {
        assertEquals(mockAuditCreateByAuditCommand, audit);

        EasyMock.verify(mockAuditServiceThreadQueue);
        EasyMock.verify(mockAuditCommand);
        EasyMock.verify(mockAuditCommandFactory);
    }

}
