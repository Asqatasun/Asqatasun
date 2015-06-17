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
package org.tanaguru.service;

import java.util.*;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.parameterization.Parameter;
import org.tanaguru.entity.service.audit.AuditDataService;
import org.tanaguru.entity.service.parameterization.ParameterDataService;
import org.tanaguru.entity.service.reference.TestDataService;
import org.tanaguru.entity.service.subject.WebResourceDataService;
import org.tanaguru.service.command.AuditCommand;
import org.tanaguru.service.command.factory.AuditCommandFactory;

/**
 *
 * @author jkowalczyk
 */
public class AuditServiceImplTest extends TestCase {
    
    private AuditDataService mockAuditDataService;
    private WebResourceDataService mockWebResourceDataService;
    private TestDataService mockTestDataService;
    private ParameterDataService mockParameterDataService;
    private AuditServiceThreadFactory mockAuditServiceThreadFactory;
    
    public AuditServiceImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockAuditDataService = EasyMock.createMock(AuditDataService.class);
        mockWebResourceDataService = 
                EasyMock.createMock(WebResourceDataService.class);
        mockTestDataService = EasyMock.createMock(TestDataService.class);
        mockParameterDataService = 
                EasyMock.createMock(ParameterDataService.class);
        mockAuditServiceThreadFactory = 
                EasyMock.createMock(AuditServiceThreadFactory.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of remove method, of class AuditServiceImpl.
     */
    public void testAddAndRemove() {
        AuditServiceImpl instance = initialiseAuditService();
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
    public void testAuditPage() {
        System.out.println("auditPage");
        String pageUrl = "http://My.testUrl.org";
        Set<Parameter> paramSet = null;

        AuditServiceImpl instance = initialiseAuditService();

        Audit auditCreateByAuditCommand = EasyMock.createMock(Audit.class);
        
        AuditCommand mockAuditCommand = EasyMock.createMock(AuditCommand.class);
        EasyMock.expect(mockAuditCommand.getAudit()).
                andReturn(auditCreateByAuditCommand).anyTimes();
        EasyMock.replay(mockAuditCommand);
        
        AuditCommandFactory mockAuditCommandFactory = EasyMock.createMock(AuditCommandFactory.class);
        EasyMock.expect(mockAuditCommandFactory.create(pageUrl, paramSet, false)).
                andReturn(mockAuditCommand).anyTimes();
        EasyMock.replay(mockAuditCommandFactory);
        instance.setAuditCommandFactory(mockAuditCommandFactory);
        
        // anyTimes the audit is created and set-up, the auditServiceThreadQueue is 
        // called to effectively launch the page audit
        AuditServiceThreadQueue mockAuditServiceThreadQueue = 
                EasyMock.createMock(AuditServiceThreadQueue.class);
        mockAuditServiceThreadQueue.add(instance);
        EasyMock.expectLastCall().anyTimes();
        mockAuditServiceThreadQueue.addPageAudit(mockAuditCommand);
        EasyMock.expectLastCall().anyTimes();
        EasyMock.replay(mockAuditServiceThreadQueue);
        
        instance.setAuditServiceThreadQueue(mockAuditServiceThreadQueue);

        Audit result = instance.auditPage(pageUrl, paramSet);
        assertEquals(auditCreateByAuditCommand, result);

        EasyMock.verify(mockAuditServiceThreadQueue);
        EasyMock.verify(mockAuditCommand);
        EasyMock.verify(mockAuditCommandFactory);
    }
    
    /**
     * Test of auditPage method, of class AuditServiceImpl.
     */
    public void testAuditSite() {
        System.out.println("auditSite");
        String siteUrl = "http://My.testUrl.org";
        Set<Parameter> paramSet = null;
        AuditServiceImpl instance = initialiseAuditService();
        
        Audit auditCreateByAuditCommand = EasyMock.createMock(Audit.class);
        
        AuditCommand mockAuditCommand = EasyMock.createMock(AuditCommand.class);
        EasyMock.expect(mockAuditCommand.getAudit()).
                andReturn(auditCreateByAuditCommand).anyTimes();
        EasyMock.replay(mockAuditCommand);
        
        AuditCommandFactory mockAuditCommandFactory = EasyMock.createMock(AuditCommandFactory.class);
        EasyMock.expect(mockAuditCommandFactory.create(siteUrl, paramSet, true)).
                andReturn(mockAuditCommand).anyTimes();
        EasyMock.replay(mockAuditCommandFactory);
        instance.setAuditCommandFactory(mockAuditCommandFactory);
        
        // anyTimes the audit is created and set-up, the auditServiceThreadQueue is 
        // called to effectively launch the site audit
        AuditServiceThreadQueue mockAuditServiceThreadQueue = 
                EasyMock.createMock(AuditServiceThreadQueue.class);
        mockAuditServiceThreadQueue.add(instance);
        EasyMock.expectLastCall().anyTimes();
        mockAuditServiceThreadQueue.addSiteAudit(mockAuditCommand);
        EasyMock.expectLastCall().anyTimes();
        EasyMock.replay(mockAuditServiceThreadQueue);
        instance.setAuditServiceThreadQueue(mockAuditServiceThreadQueue);
        
        Audit result = instance.auditSite(siteUrl, paramSet);
        assertEquals(auditCreateByAuditCommand, result);
        
        EasyMock.verify(mockAuditServiceThreadQueue);
        EasyMock.verify(mockAuditCommand);
        EasyMock.verify(mockAuditCommandFactory);
    }
    
    /**
     * Test of auditPage method, of class AuditServiceImpl.
     */
    public void testAuditGroupOfPages() {
        System.out.println("auditGroupOfPages");
        String siteUrl = "http://My.testUrl.org";
        String pageUrl = "http://My.testUrlPage1.org";
        Set<Parameter> paramSet = null;
        List<String> urlList = new ArrayList<>();
        urlList.add(pageUrl);
        
        AuditServiceImpl instance = initialiseAuditService();
        
        Audit auditCreateByAuditCommand = EasyMock.createMock(Audit.class);
        
        AuditCommand mockAuditCommand = EasyMock.createMock(AuditCommand.class);
        EasyMock.expect(mockAuditCommand.getAudit()).
                andReturn(auditCreateByAuditCommand).anyTimes();
        EasyMock.replay(mockAuditCommand);
        
        AuditCommandFactory mockAuditCommandFactory = EasyMock.createMock(AuditCommandFactory.class);
        EasyMock.expect(mockAuditCommandFactory.create(siteUrl, urlList, paramSet)).
                andReturn(mockAuditCommand).anyTimes();
        EasyMock.replay(mockAuditCommandFactory);
        instance.setAuditCommandFactory(mockAuditCommandFactory);
        
        // anyTimes the audit is created and set-up, the auditServiceThreadQueue is 
        // called to effectively launch the site audit
        AuditServiceThreadQueue mockAuditServiceThreadQueue = 
                EasyMock.createMock(AuditServiceThreadQueue.class);
        mockAuditServiceThreadQueue.add(instance);
        EasyMock.expectLastCall().anyTimes();
        mockAuditServiceThreadQueue.addPageAudit(mockAuditCommand);
        EasyMock.expectLastCall().anyTimes();
        EasyMock.replay(mockAuditServiceThreadQueue);
        instance.setAuditServiceThreadQueue(mockAuditServiceThreadQueue);
        
        Audit result = instance.auditSite(siteUrl, urlList, paramSet);
        assertEquals(auditCreateByAuditCommand, result);
        
        EasyMock.verify(mockAuditServiceThreadQueue);
        EasyMock.verify(mockAuditCommand);
        EasyMock.verify(mockAuditCommandFactory);
    }

    /**
     * Test of auditPageUpload method, of class AuditServiceImpl.
     */
    public void testAuditPageUploadWithOneFile() {
        System.out.println("auditPageUploadWithOneFile");

        Set<Parameter> paramSet = null;
        
        // test we only one file is passed as argument to be tested
        String file1Name = "file://test1";
        Map<String, String> fileMap = new HashMap<>();
        fileMap.put(file1Name, "");
        
        AuditServiceImpl instance = initialiseAuditService();
        
        Audit auditCreateByAuditCommand = EasyMock.createMock(Audit.class);
        
        AuditCommand mockAuditCommand = EasyMock.createMock(AuditCommand.class);
        EasyMock.expect(mockAuditCommand.getAudit()).
                andReturn(auditCreateByAuditCommand).anyTimes();
        EasyMock.replay(mockAuditCommand);
        
        AuditCommandFactory mockAuditCommandFactory = EasyMock.createMock(AuditCommandFactory.class);
        EasyMock.expect(mockAuditCommandFactory.create(fileMap, paramSet)).
                andReturn(mockAuditCommand).anyTimes();
        EasyMock.replay(mockAuditCommandFactory);
        instance.setAuditCommandFactory(mockAuditCommandFactory);

        // anyTimes the audit is created and set-up, the auditServiceThreadQueue is 
        // called to effectively launch the pageUpload audit
        AuditServiceThreadQueue mockAuditServiceThreadQueue = 
                EasyMock.createMock(AuditServiceThreadQueue.class);
        mockAuditServiceThreadQueue.add(instance);
        EasyMock.expectLastCall().anyTimes();
        mockAuditServiceThreadQueue.addPageUploadAudit(mockAuditCommand);
        EasyMock.replay(mockAuditServiceThreadQueue);
        instance.setAuditServiceThreadQueue(mockAuditServiceThreadQueue);
        
        Audit result = instance.auditPageUpload(fileMap, paramSet);
        assertEquals(auditCreateByAuditCommand, result);
        
        EasyMock.verify(mockAuditServiceThreadQueue);
        EasyMock.verify(mockAuditCommand);
        EasyMock.verify(mockAuditCommandFactory);
    }
    
    /**
     * Test of auditPage method, of class AuditServiceImpl.
     */
    public void testAuditScenario() {
        System.out.println("auditScenario");
        String scenarioName = "MyScenario";
        String scenario = "";
        
        AuditServiceImpl instance = initialiseAuditService();
        
        Audit auditCreateByAuditCommand = EasyMock.createMock(Audit.class);
        
        AuditCommand mockAuditCommand = EasyMock.createMock(AuditCommand.class);
        EasyMock.expect(mockAuditCommand.getAudit()).
                andReturn(auditCreateByAuditCommand).anyTimes();
        EasyMock.replay(mockAuditCommand);
        
        AuditCommandFactory mockAuditCommandFactory = EasyMock.createMock(AuditCommandFactory.class);
        EasyMock.expect(mockAuditCommandFactory.create(scenarioName, scenario, null)).
                andReturn(mockAuditCommand).anyTimes();
        EasyMock.replay(mockAuditCommandFactory);
        instance.setAuditCommandFactory(mockAuditCommandFactory);
        
        // anyTimes the audit is created and set-up, the auditServiceThreadQueue is 
        // called to effectively launch the pageUpload audit
        AuditServiceThreadQueue mockAuditServiceThreadQueue = 
                EasyMock.createMock(AuditServiceThreadQueue.class);
        mockAuditServiceThreadQueue.add(instance);
        EasyMock.expectLastCall().anyTimes();
        mockAuditServiceThreadQueue.addScenarioAudit(mockAuditCommand);
        EasyMock.expectLastCall().anyTimes();
        EasyMock.replay(mockAuditServiceThreadQueue);
        instance.setAuditServiceThreadQueue(mockAuditServiceThreadQueue);
        
        Audit result = instance.auditScenario(scenarioName, scenario, null);
        assertEquals(auditCreateByAuditCommand, result);
        
        EasyMock.verify(mockAuditServiceThreadQueue);
        EasyMock.verify(mockAuditCommand);
        EasyMock.verify(mockAuditCommandFactory);
    }

    /**
     * Test of audit method, of class AuditServiceImpl.
     */
    public void testAudit() {
        AuditServiceImpl instance = initialiseAuditService();
        
        Audit audit = EasyMock.createMock(Audit.class);
        Audit auditReturnedByAuditMethodOfAuditServiceThread = 
                EasyMock.createMock(Audit.class);
        
        AuditServiceThread mockAuditServiceThread = EasyMock.createMock(AuditServiceThread.class);
        
        mockAuditServiceThread.run();
        EasyMock.expectLastCall();
        EasyMock.expect(mockAuditServiceThread.getAudit()).
                andReturn(auditReturnedByAuditMethodOfAuditServiceThread).anyTimes();
        EasyMock.replay(mockAuditServiceThread);
        
        EasyMock.expect(mockAuditServiceThreadFactory.create(audit)).
                andReturn(mockAuditServiceThread).anyTimes();
        EasyMock.replay(mockAuditServiceThreadFactory);
        
        assertEquals(auditReturnedByAuditMethodOfAuditServiceThread, 
                instance.audit(audit));
        
        EasyMock.verify(mockAuditServiceThread);
        EasyMock.verify(mockAuditServiceThreadFactory);
    }
    
//    /**
//     * Test of init method, of class AuditServiceImpl.
//     */
//    public void testInit() {
//        AuditServiceImpl instance = initialiseAuditService();
//        
//        Audit audit = EasyMock.createMock(Audit.class);
//        Audit auditReturnedByInitMethodOfAuditServiceThread = 
//                EasyMock.createMock(Audit.class);
//        
//        AuditServiceThread mockAuditServiceThread = EasyMock.createMock(AuditServiceThread.class);
//        
//        mockAuditServiceThread.init();
//        EasyMock.expectLastCall();
//        EasyMock.expect(mockAuditServiceThread.getAudit()).
//                andReturn(auditReturnedByInitMethodOfAuditServiceThread).anyTimes();
//        EasyMock.replay(mockAuditServiceThread);
//        
//        EasyMock.expect(mockAuditServiceThreadFactory.create(audit)).
//                andReturn(mockAuditServiceThread).anyTimes();
//        EasyMock.replay(mockAuditServiceThreadFactory);
//        
//        assertEquals(auditReturnedByInitMethodOfAuditServiceThread, 
//                instance.init(audit));
//        
//        EasyMock.verify(mockAuditServiceThread);
//        EasyMock.verify(mockAuditServiceThreadFactory);
//    }
    
//    /**
//     * Test of crawl method, of class AuditServiceImpl.
//     */
//    public void testCrawl() {
//        AuditServiceImpl instance = initialiseAuditService();
//        
//        Audit audit = EasyMock.createMock(Audit.class);
//        Audit auditReturnedByCrawlMethodOfAuditServiceThread = 
//                EasyMock.createMock(Audit.class);
//        
//        AuditServiceThread mockAuditServiceThread = EasyMock.createMock(AuditServiceThread.class);
//        
//        mockAuditServiceThread.loadContent();
//        EasyMock.expectLastCall();
//        EasyMock.expect(mockAuditServiceThread.getAudit()).
//                andReturn(auditReturnedByCrawlMethodOfAuditServiceThread).anyTimes();
//        EasyMock.replay(mockAuditServiceThread);
//        
//        EasyMock.expect(mockAuditServiceThreadFactory.create(audit)).
//                andReturn(mockAuditServiceThread).anyTimes();
//        EasyMock.replay(mockAuditServiceThreadFactory);
//        
//        assertEquals(auditReturnedByCrawlMethodOfAuditServiceThread, 
//                instance.crawl(audit));
//        
//        EasyMock.verify(mockAuditServiceThread);
//        EasyMock.verify(mockAuditServiceThreadFactory);
//    }
    
//    /**
//     * Test of loadContent method, of class AuditServiceImpl.
//     */
//    public void testLoadContent() {
//        AuditServiceImpl instance = initialiseAuditService();
//        
//        Audit audit = EasyMock.createMock(Audit.class);
//        Audit auditReturnedByLoadContentMethodOfAuditServiceThread = 
//                EasyMock.createMock(Audit.class);
//        
//        AuditServiceThread mockAuditServiceThread = EasyMock.createMock(AuditServiceThread.class);
//        
//        mockAuditServiceThread.loadContent();
//        EasyMock.expectLastCall();
//        EasyMock.expect(mockAuditServiceThread.getAudit()).
//                andReturn(auditReturnedByLoadContentMethodOfAuditServiceThread).anyTimes();
//        EasyMock.replay(mockAuditServiceThread);
//        
//        EasyMock.expect(mockAuditServiceThreadFactory.create(audit)).
//                andReturn(mockAuditServiceThread).anyTimes();
//        EasyMock.replay(mockAuditServiceThreadFactory);
//        
//        assertEquals(auditReturnedByLoadContentMethodOfAuditServiceThread, 
//                instance.loadContent(audit));
//        
//        EasyMock.verify(mockAuditServiceThread);
//        EasyMock.verify(mockAuditServiceThreadFactory);
//    }
    
//    /**
//     * Test of loadScenario method, of class AuditServiceImpl.
//     */
//    public void testLoadScenario() {
//        AuditServiceImpl instance = initialiseAuditService();
//        
//        Audit audit = EasyMock.createMock(Audit.class);
//        Audit auditReturnedByLoadScenarioMethodOfAuditServiceThread = 
//                EasyMock.createMock(Audit.class);
//        
//        AuditServiceThread mockAuditServiceThread = EasyMock.createMock(AuditServiceThread.class);
//        
//        mockAuditServiceThread.loadContent();
//        EasyMock.expectLastCall();
//        EasyMock.expect(mockAuditServiceThread.getAudit()).
//                andReturn(auditReturnedByLoadScenarioMethodOfAuditServiceThread).anyTimes();
//        EasyMock.replay(mockAuditServiceThread);
//        
//        EasyMock.expect(mockAuditServiceThreadFactory.create(audit)).
//                andReturn(mockAuditServiceThread).anyTimes();
//        EasyMock.replay(mockAuditServiceThreadFactory);
//        
//        assertEquals(auditReturnedByLoadScenarioMethodOfAuditServiceThread, 
//                instance.loadScenario(audit));
//        
//        EasyMock.verify(mockAuditServiceThread);
//        EasyMock.verify(mockAuditServiceThreadFactory);
//    }
    
//    /**
//     * Test of adaptContent method, of class AuditServiceImpl.
//     */
//    public void testAdaptContent() {
//        AuditServiceImpl instance = initialiseAuditService();
//        
//        Audit audit = EasyMock.createMock(Audit.class);
//        Audit auditReturnedByAdaptContentMethodOfAuditServiceThread = 
//                EasyMock.createMock(Audit.class);
//        
//        AuditServiceThread mockAuditServiceThread = EasyMock.createMock(AuditServiceThread.class);
//        
//        mockAuditServiceThread.adaptContent();
//        EasyMock.expectLastCall();
//        EasyMock.expect(mockAuditServiceThread.getAudit()).
//                andReturn(auditReturnedByAdaptContentMethodOfAuditServiceThread).anyTimes();
//        EasyMock.replay(mockAuditServiceThread);
//        
//        EasyMock.expect(mockAuditServiceThreadFactory.create(audit)).
//                andReturn(mockAuditServiceThread).anyTimes();
//        EasyMock.replay(mockAuditServiceThreadFactory);
//        
//        assertEquals(auditReturnedByAdaptContentMethodOfAuditServiceThread, 
//                instance.adaptContent(audit));
//        
//        EasyMock.verify(mockAuditServiceThread);
//        EasyMock.verify(mockAuditServiceThreadFactory);
//    }
    
//    /**
//     * Test of process method, of class AuditServiceImpl.
//     */
//    public void testProcess() {
//        AuditServiceImpl instance = initialiseAuditService();
//        
//        Audit audit = EasyMock.createMock(Audit.class);
//        Audit auditReturnedByProcessMethodOfAuditServiceThread = 
//                EasyMock.createMock(Audit.class);
//        
//        AuditServiceThread mockAuditServiceThread = EasyMock.createMock(AuditServiceThread.class);
//        
//        mockAuditServiceThread.process();
//        EasyMock.expectLastCall();
//        EasyMock.expect(mockAuditServiceThread.getAudit()).
//                andReturn(auditReturnedByProcessMethodOfAuditServiceThread).anyTimes();
//        EasyMock.replay(mockAuditServiceThread);
//        
//        EasyMock.expect(mockAuditServiceThreadFactory.create(audit)).
//                andReturn(mockAuditServiceThread).anyTimes();
//        EasyMock.replay(mockAuditServiceThreadFactory);
//        
//        assertEquals(auditReturnedByProcessMethodOfAuditServiceThread, 
//                instance.process(audit));
//        
//        EasyMock.verify(mockAuditServiceThread);
//        EasyMock.verify(mockAuditServiceThreadFactory);
//    }
    
//    /**
//     * Test of consolidate method, of class AuditServiceImpl.
//     */
//    public void testConsolidate() {
//        AuditServiceImpl instance = initialiseAuditService();
//        
//        Audit audit = EasyMock.createMock(Audit.class);
//        Audit auditReturnedByConsolidateMethodOfAuditServiceThread = 
//                EasyMock.createMock(Audit.class);
//        
//        AuditServiceThread mockAuditServiceThread = EasyMock.createMock(AuditServiceThread.class);
//        
//        mockAuditServiceThread.consolidate();
//        EasyMock.expectLastCall();
//        EasyMock.expect(mockAuditServiceThread.getAudit()).
//                andReturn(auditReturnedByConsolidateMethodOfAuditServiceThread).anyTimes();
//        EasyMock.replay(mockAuditServiceThread);
//        
//        EasyMock.expect(mockAuditServiceThreadFactory.create(audit)).
//                andReturn(mockAuditServiceThread).anyTimes();
//        EasyMock.replay(mockAuditServiceThreadFactory);
//        
//        assertEquals(auditReturnedByConsolidateMethodOfAuditServiceThread, 
//                instance.consolidate(audit));
//        
//        EasyMock.verify(mockAuditServiceThread);
//        EasyMock.verify(mockAuditServiceThreadFactory);
//    }
    
//    /**
//     * Test of analyse method, of class AuditServiceImpl.
//     */
//    public void testAnalyse() {
//        AuditServiceImpl instance = initialiseAuditService();
//        
//        Audit audit = EasyMock.createMock(Audit.class);
//        Audit auditReturnedByAnalyseMethodOfAuditServiceThread = 
//                EasyMock.createMock(Audit.class);
//        
//        AuditServiceThread mockAuditServiceThread = EasyMock.createMock(AuditServiceThread.class);
//        
//        mockAuditServiceThread.analyse();
//        EasyMock.expectLastCall();
//        EasyMock.expect(mockAuditServiceThread.getAudit()).
//                andReturn(auditReturnedByAnalyseMethodOfAuditServiceThread).anyTimes();
//        EasyMock.replay(mockAuditServiceThread);
//        
//        EasyMock.expect(mockAuditServiceThreadFactory.create(audit)).
//                andReturn(mockAuditServiceThread).anyTimes();
//        EasyMock.replay(mockAuditServiceThreadFactory);
//        
//        assertEquals(auditReturnedByAnalyseMethodOfAuditServiceThread, 
//                instance.analyse(audit));
//        
//        EasyMock.verify(mockAuditServiceThread);
//        EasyMock.verify(mockAuditServiceThreadFactory);
//    }
    
    /**
     * Test of auditCompleted method, of class AuditServiceImpl.
     */
    public void testAuditCompleted() {
        AuditServiceImpl instance = initialiseAuditService();
        
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
        AuditServiceImpl instance = initialiseAuditService();
        
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
    
    /**
     * 
     * @return 
     */
    private AuditServiceImpl initialiseAuditService() {
        AuditServiceImpl auditService = new AuditServiceImpl();
        auditService.setAuditServiceThreadFactory(mockAuditServiceThreadFactory);
        return auditService;
    }

}