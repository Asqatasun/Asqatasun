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

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.asqatasun.service.command.AuditCommand;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

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
     * Test of add method, of class AuditServiceThreadQueueImpl.
     */
    public void testAdd() {
        AuditServiceListener listener = EasyMock.createMock(AuditServiceListener.class);
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl(3,3,3,3);
        instance.add(listener);
        assertEquals(1, instance.getListeners().size());
    }

    /**
     * Test of remove method, of class AuditServiceThreadQueueImpl.
     */
    public void testRemove() {
        AuditServiceListener listener = EasyMock.createMock(AuditServiceListener.class);
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl(3,3,3,3);
        instance.add(listener);
        assertEquals(1, instance.getListeners().size());
        instance.remove(listener);
        assertEquals(0, instance.getListeners().size());
    }

    /**
     * Test of addPageAudit method, of class AuditServiceThreadQueueImpl.
     */
    public void testAddPageAudit() {
        // the tested instance
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl(3,3,3,3);
        
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
        int numberOfSimultaneousRequestedCommand = 1000;

        // the tested instance
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl(3,3,3,3);
        
        Collection<AuditCommand> auditCommands = new ArrayList<>();
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
        // the tested instance
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl(3,3,3,3);
        
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
        // the tested instance
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl(3,3,3,3);

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
        AuditServiceThreadQueueImpl instance = new AuditServiceThreadQueueImpl(3,3,3,3);
        
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
     * @param auditServiceThreadCallCounter
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
     * @param auditCommand
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
     * @param auditCommandList
     * @param auditServiceThread
     * @return 
     */
    private AuditServiceThreadFactory createMockAuditServiceThreadFactoryWithMultipleCommand (
            Collection<AuditCommand> auditCommandList,
            AuditServiceThread auditServiceThread) {
        
        // Create the mock instance
        AuditServiceThreadFactory auditServiceThreadFactory = 
                EasyMock.createMock(AuditServiceThreadFactory.class);
        
        for (AuditCommand auditCommand : auditCommandList) {
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
