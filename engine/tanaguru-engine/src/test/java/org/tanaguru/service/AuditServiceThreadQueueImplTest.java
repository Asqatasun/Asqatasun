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

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.tanaguru.service.command.AuditCommand;

/**
 *
 * @author jkowalczyk
 */
public class AuditServiceThreadQueueImplTest extends TestCase {
    
    public AuditServiceThreadQueueImplTest(String testName) {
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
     * Test of setPageAuditExecutionListMax method, of class AuditServiceThreadQueueImpl.
     */
    public void testSetPageAuditExecutionListMax() {
        System.out.println("setPageAuditExecutionListMax");
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl();
        int pageAuditExecutionListMax = 10;
        instance.setPageAuditExecutionListMax(pageAuditExecutionListMax);
        assertEquals(pageAuditExecutionListMax, instance.getPageAuditExecutionListMax());
    }
    
    /**
     * Test of setSiteAuditExecutionListMax method, of class AuditServiceThreadQueueImpl.
     */
    public void testSetSiteAuditExecutionListMax() {
        System.out.println("setSiteAuditExecutionListMax");
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl();
        int siteAuditExecutionListMax = 10;
        instance.setSiteAuditExecutionListMax(siteAuditExecutionListMax);
        assertEquals(siteAuditExecutionListMax, instance.getSiteAuditExecutionListMax());
    }
    
    /**
     * Test of setUploadAuditExecutionListMax method, of class AuditServiceThreadQueueImpl.
     */
    public void testSetUploadAuditExecutionListMax() {
        System.out.println("setUploadAuditExecutionListMax");
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl();
        int uploadAuditExecutionListMax = 10;
        instance.setUploadAuditExecutionListMax(uploadAuditExecutionListMax);
        assertEquals(uploadAuditExecutionListMax, instance.getUploadAuditExecutionListMax());
    }
    
    /**
     * Test of setScenarioAuditExecutionListMax method, of class AuditServiceThreadQueueImpl.
     */
    public void testSetScenarioAuditExecutionListMax() {
        System.out.println("setScenarioAuditExecutionListMax");
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl();
        int scenarioAuditExecutionListMax = 10;
        instance.setScenarioAuditExecutionListMax(scenarioAuditExecutionListMax);
        assertEquals(scenarioAuditExecutionListMax, instance.getScenarioAuditExecutionListMax());
    }
    
    /**
     * Test of add method, of class AuditServiceThreadQueueImpl.
     */
    public void testAdd() {
        System.out.println("add");
        AuditServiceListener listener = EasyMock.createMock(AuditServiceListener.class);
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl();
        instance.add(listener);
        assertEquals(1, instance.getListeners().size());
    }

    /**
     * Test of remove method, of class AuditServiceThreadQueueImpl.
     */
    public void testRemove() {
        System.out.println("remove");
        AuditServiceListener listener = EasyMock.createMock(AuditServiceListener.class);
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl();
        instance.add(listener);
        assertEquals(1, instance.getListeners().size());
        instance.remove(listener);
        assertEquals(0, instance.getListeners().size());
    }

    /**
     * Test of addPageAudit method, of class AuditServiceThreadQueueImpl.
     */
    public void testAddPageAudit() {
        System.out.println("addPageAudit");
        
        // the tested instance
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl();
        
        AuditCommand auditCommand = EasyMock.createMock(AuditCommand.class);
        
        // Create the mock instance
        AuditServiceThread auditServiceThread = createMockAuditServiceThread(instance);
        
        // Create the mock instance
        AuditServiceThreadFactory auditServiceThreadFactory = 
                createMockAuditServiceThreadFactory(auditCommand, auditServiceThread);
        
        instance.setAuditServiceThreadFactory(auditServiceThreadFactory);
        instance.addPageAudit(auditCommand);
        
        // sleep to make sure the auditServiceThread is started and thus avoid
        // an unexpected error
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(AuditServiceThreadQueueImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Verify behavior.
        EasyMock.verify(auditServiceThreadFactory);
        EasyMock.verify(auditServiceThread);
    }
    
    /**
     * Test of addPageAudit method, of class AuditServiceThreadQueueImpl.
     */
    public void testAddPageAuditWithConcurrentAccessAndMaxSizeReached() {
        System.out.println("addPageAuditWithConcurrentAccessAndMaxSizeReached");
        
        int numberOfSimultaneousRequestedCommand = 1000;

        // the tested instance
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl();
        
        Collection<AuditCommand> auditCommands = new ArrayList<AuditCommand>();
        for(int i=0;i<numberOfSimultaneousRequestedCommand;i++) {
            AuditCommand auditCommand = EasyMock.createMock(AuditCommand.class);
            auditCommands.add(auditCommand);
        }

        // Create the mock instance
        AuditServiceThread auditServiceThread = createMockAuditServiceThreadWithFireCompleted(instance, auditCommands.size());
        
        // Create the mock instance
        AuditServiceThreadFactory auditServiceThreadFactory = 
                createMockAuditServiceThreadFactoryWithMultipleCommand(auditCommands, auditServiceThread);
        
        instance.setAuditServiceThreadFactory(auditServiceThreadFactory);
        
        for (AuditCommand auditCommand : auditCommands) {
            instance.addPageAudit(auditCommand);
        }

        // sleep to make sure the auditServiceThread is started and thus avoid
        // an unexpected error
        try {
            Thread.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(AuditServiceThreadQueueImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0;i<numberOfSimultaneousRequestedCommand;i++) {
            instance.auditCompleted(auditServiceThread);
            // sleep for last audit blocked in queue be executed
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(AuditServiceThreadQueueImplTest.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // sleep for last audit blocked in queue be executed
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(AuditServiceThreadQueueImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Verify behavior.
        EasyMock.verify(auditServiceThreadFactory);
        EasyMock.verify(auditServiceThread);
    }
    
    /**
     * Test of addScenarioAudit method, of class AuditServiceThreadQueueImpl.
     */
    public void testAddScenarioAudit() {
        System.out.println("addScenarioAudit");
        
        // the tested instance
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl();
        
        AuditCommand auditCommand = EasyMock.createMock(AuditCommand.class);
        
        // Create the mock instance
        AuditServiceThread auditServiceThread = createMockAuditServiceThread(instance);
        
        // Create the mock instance
        AuditServiceThreadFactory auditServiceThreadFactory = 
                createMockAuditServiceThreadFactory(auditCommand, auditServiceThread);
        
        instance.setAuditServiceThreadFactory(auditServiceThreadFactory);
        instance.addScenarioAudit(auditCommand);

        // sleep to make sure the auditServiceThread is started and thus avoid
        // an unexpected error
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(AuditServiceThreadQueueImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Verify behavior.
        EasyMock.verify(auditServiceThread);
        EasyMock.verify(auditServiceThreadFactory);
    }
    
    /**
     * Test of addPageUploadAudit method, of class AuditServiceThreadQueueImpl.
     */
    public void testAddPageUploadAudit() {
        System.out.println("addPageUploadAudit");

        // the tested instance
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl();

        AuditCommand auditCommand = EasyMock.createMock(AuditCommand.class);
        
        // Create the mock instance
        AuditServiceThread auditServiceThread = createMockAuditServiceThread(instance);
        
        // Create the mock instance
        AuditServiceThreadFactory auditServiceThreadFactory = 
                createMockAuditServiceThreadFactory(auditCommand, auditServiceThread);
        
        instance.setAuditServiceThreadFactory(auditServiceThreadFactory);
        instance.addPageUploadAudit(auditCommand);
        
        // sleep to make sure the auditServiceThread is started and thus avoid
        // an unexpected error
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(AuditServiceThreadQueueImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Verify behavior.
        EasyMock.verify(auditServiceThreadFactory);
        EasyMock.verify(auditServiceThread);
    }

    /**
     * Test of addSiteAudit method, of class AuditServiceThreadQueueImpl.
     */
    public void testAddSiteAudit() {
        System.out.println("addSiteAudit");
        
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl();
        
        AuditCommand auditCommand = EasyMock.createMock(AuditCommand.class);
        
        // Create the mock instance
        AuditServiceThread auditServiceThread = createMockAuditServiceThread(instance);
        
        // Create the mock instance
        AuditServiceThreadFactory auditServiceThreadFactory = 
                createMockAuditServiceThreadFactory(auditCommand, auditServiceThread);
        
        instance.setAuditServiceThreadFactory(auditServiceThreadFactory);
        instance.addSiteAudit(auditCommand);
        
        // sleep to make sure the auditServiceThread is started and thus avoid
        // an unexpected error
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(AuditServiceThreadQueueImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // Verify behavior.
        EasyMock.verify(auditServiceThread);
        EasyMock.verify(auditServiceThreadFactory);
    }

    /**
     * Test of auditCompleted method, of class AuditServiceThreadQueueImpl.
     */
    public void testAuditCompleted() {
        //TODO 
    }

    /**
     * Test of auditCrashed method, of class AuditServiceThreadQueueImpl.
     */
    public void testAuditCrashed() {
        //TODO 
    }

    /**
     * Test of processWaitQueue method, of class AuditServiceThreadQueueImpl.
     */
    public void testProcessWaitQueue() {
        //TODO 
    }
    
    /**
     * Create a set-up AuditServiceThread instance
     * 
     * @param instance
     * @return 
     */
    private AuditServiceThread createMockAuditServiceThread (
            AuditServiceThreadQueueImpl instance) {
        
        // Create the mock instance
        AuditServiceThread auditServiceThread = 
                EasyMock.createMock(AuditServiceThread.class);
        
        // Set expectations on mock AuditServiceThread
        // the current instance of AuditServiceThreadQueueImpl is recorded 
        // as listener
        auditServiceThread.add(instance);
        EasyMock.expectLastCall().once();
        
        // The thread is launched
        auditServiceThread.run();
        EasyMock.expectLastCall().once();
        
        // Set mock AuditServiceThread into testing mode.
        EasyMock.replay(auditServiceThread);
        
        return auditServiceThread;
    }
    
    /**
     * Create a set-up AuditServiceThread instance
     * 
     * @param instance
     * @param mockScenario
     * @param fileMap
     * @return 
     */
    private AuditServiceThread createMockAuditServiceThreadWithFireCompleted (
            AuditServiceThreadQueueImpl instance, 
            int auditServiceThreadCallCounter) {
        
        // Create the mock instance
        AuditServiceThread auditServiceThread = 
                EasyMock.createMock(AuditServiceThread.class);
        
        // Set expectations on mock AuditServiceThread
        // the current instance of AuditServiceThreadQueueImpl is recorded 
        // as listener
        auditServiceThread.add(instance);
        EasyMock.expectLastCall().times(auditServiceThreadCallCounter);
        
        // The thread is launched
        auditServiceThread.run();
        EasyMock.expectLastCall().times(auditServiceThreadCallCounter);
        
        // When first audit finished, fireCompleted called to launch audit 
        // blocked in queue.
        EasyMock.expect(auditServiceThread.getAudit()).andReturn(null)
                .times(auditServiceThreadCallCounter);
        
        auditServiceThread.remove(instance);
        EasyMock.expectLastCall().times(auditServiceThreadCallCounter);
        
        // Set mock AuditServiceThread into testing mode.
        EasyMock.replay(auditServiceThread);
        
        return auditServiceThread;
    }

    /**
     * 
     * @param audit
     * @param auditServiceThread
     * @return 
     */
    private AuditServiceThreadFactory createMockAuditServiceThreadFactory (
            AuditCommand auditCommand, 
            AuditServiceThread auditServiceThread) {
        
        // Create the mock instance
        AuditServiceThreadFactory auditServiceThreadFactory = 
                EasyMock.createMock(AuditServiceThreadFactory.class);
        
        // an auditServiceThread instance is create by the factory with the 
        // audit passed as argument. This thread is then launched
        EasyMock.expect(auditServiceThreadFactory.create(auditCommand)).
                andReturn(auditServiceThread).once();
        
        // Set mock AuditServiceThreadFactory into testing mode.
        EasyMock.replay(auditServiceThreadFactory);
        
        return auditServiceThreadFactory;
    }
    
    /**
     * 
     * @param audit
     * @param auditServiceThread
     * @return 
     */
    private AuditServiceThreadFactory createMockAuditServiceThreadFactoryWithMultipleCommand (
            Collection<AuditCommand> auditCommands, 
            AuditServiceThread auditServiceThread) {
        
        // Create the mock instance
        AuditServiceThreadFactory auditServiceThreadFactory = 
                EasyMock.createMock(AuditServiceThreadFactory.class);
        
        for (AuditCommand auditCommand : auditCommands) {
            // an auditServiceThread instance is create by the factory with the 
            // audit passed as argument. This thread is then launched
            EasyMock.expect(auditServiceThreadFactory.create(auditCommand)).
                    andReturn(auditServiceThread).once();
        }
        // Set mock AuditServiceThreadFactory into testing mode.
        EasyMock.replay(auditServiceThreadFactory);
        
        return auditServiceThreadFactory;
    }
    
}