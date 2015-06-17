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

import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.tanaguru.entity.audit.Audit;

import org.tanaguru.service.command.AuditCommand;

/**
 *
 * @author jkowalczyk
 */
public class AuditServiceThreadImplTest extends TestCase {
    
    public AuditServiceThreadImplTest(String testName) {
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
     * Test of getAudit method, of class AuditServiceThreadImpl.
     */
    public void testGetAudit() {
        System.out.println("getAudit");
        
        Audit audit = createMock(Audit.class);
        Audit auditReturnedByAuditCommand = createMock(Audit.class);
        AuditCommand mockAuditCommand = createMock(AuditCommand.class);
        expect(mockAuditCommand.getAudit()).andReturn(auditReturnedByAuditCommand);
        replay(mockAuditCommand);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(audit);
        assertEquals(audit, instance.getAudit());
        
        instance = initialiseAuditServiceThread(mockAuditCommand);
        assertEquals(auditReturnedByAuditCommand, instance.getAudit());
    }

    /**
     * Test of add/remove AuditServiceListener method of class AuditServiceImpl.
     */
    public void testAddAndRemove() {
        
        Audit audit = createMock(Audit.class);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(audit);
        
        AuditServiceThreadListener mockAuditServiceThreadListener = 
                createMock(AuditServiceThreadListener.class);
        // when try to remove a listener not recorded, nothing happened
        instance.remove(mockAuditServiceThreadListener);
        
        instance.add(mockAuditServiceThreadListener);
        assertTrue(instance.getListeners().contains(mockAuditServiceThreadListener));
        instance.remove(mockAuditServiceThreadListener);
        assertTrue(instance.getListeners().isEmpty());
    }
    
    /**
     * Test of run method, of class AuditServiceThreadImpl.
     */
    public void testRun() {
        System.out.println("run");

        Audit mockAudit = createMock(Audit.class);
        expect(mockAudit.getId()).andReturn(1l).anyTimes();
        
        AuditCommand mockAuditCommand = createMock(AuditCommand.class);
        mockAuditCommand.init();
        expectLastCall().once();
        mockAuditCommand.loadContent();
        expectLastCall().once();
        mockAuditCommand.adaptContent();
        expectLastCall().once();
        mockAuditCommand.process();
        expectLastCall().once();
        mockAuditCommand.consolidate();
        expectLastCall().once();
        mockAuditCommand.analyse();
        expectLastCall().once();
        expect(mockAuditCommand.getAudit()).andReturn(mockAudit).anyTimes();
        expect(mockAuditCommand.sendMessageOut("[MSGOUT] AUDIT 1 PENDIG ")).andReturn(Boolean.TRUE);
        expect(mockAuditCommand.sendMessageOut("[MSGOUT] AUDIT 1 FINISHED ")).andReturn(Boolean.TRUE);
        
        replay(mockAudit);
        replay(mockAuditCommand);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(mockAuditCommand);
        
        AuditServiceThreadListener auditServiceThreadListener = 
                createMock(AuditServiceThreadListener.class);
        auditServiceThreadListener.auditCompleted(instance);
        expectLastCall().once();
        replay(auditServiceThreadListener);
        
        instance.add(auditServiceThreadListener);
        instance.run();
        
        verify(mockAudit);
        verify(mockAuditCommand);
        verify(auditServiceThreadListener);
    }

    public void testInit() {
        System.out.println("init");
        
        AuditCommand mockAuditCommand = createMock(AuditCommand.class);
        mockAuditCommand.init();
        expectLastCall().once();
        replay(mockAuditCommand);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(mockAuditCommand);
        instance.init();
        
        verify(mockAuditCommand);
    }
    
    /**
     * Test of loadContent method, of class AuditServiceThreadImpl.
     */
    public void testLoadContent() {
        System.out.println("loadContent");
        
        AuditCommand mockAuditCommand = createMock(AuditCommand.class);
        mockAuditCommand.loadContent();
        expectLastCall().once();
        replay(mockAuditCommand);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(mockAuditCommand);
        instance.loadContent();
        
        verify(mockAuditCommand);
    }

    /**
     * Test of adaptContent method, of class AuditServiceThreadImpl.
     */
    public void testAdaptContent() {
        System.out.println("adaptContent");
        
        AuditCommand mockAuditCommand = createMock(AuditCommand.class);
        mockAuditCommand.adaptContent();
        expectLastCall().once();
        replay(mockAuditCommand);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(mockAuditCommand);
        instance.adaptContent();
        
        verify(mockAuditCommand);
    }

    /**
     * Test of process method, of class AuditServiceThreadImpl.
     */
    public void testProcess() {
        System.out.println("process");
        
        AuditCommand mockAuditCommand = createMock(AuditCommand.class);
        mockAuditCommand.process();
        expectLastCall().once();
        replay(mockAuditCommand);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(mockAuditCommand);
        instance.process();
        
        verify(mockAuditCommand);
    }

    /**
     * Test of consolidate method, of class AuditServiceThreadImpl.
     */
    public void testConsolidate() {
        System.out.println("consolidate");
        
        AuditCommand mockAuditCommand = createMock(AuditCommand.class);
        mockAuditCommand.consolidate();
        expectLastCall().once();
        replay(mockAuditCommand);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(mockAuditCommand);
        instance.consolidate();
        
        verify(mockAuditCommand);
    }

    /**
     * Test of analyse method, of class AuditServiceThreadImpl.
     */
    public void testAnalyse() {
        System.out.println("analyse");
        
        AuditCommand mockAuditCommand = createMock(AuditCommand.class);
        mockAuditCommand.analyse();
        expectLastCall().once();
        replay(mockAuditCommand);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(mockAuditCommand);
        instance.analyse();
        
        verify(mockAuditCommand);
    }

    /**
     * 
     * @return 
     */
    private AuditServiceThreadImpl initialiseAuditServiceThread(Audit audit) {
        AuditServiceThreadImpl auditServiceThread = 
                new AuditServiceThreadImpl(audit);
        return auditServiceThread;
    }
    
    /**
     * 
     * @return 
     */
    private AuditServiceThreadImpl initialiseAuditServiceThread(AuditCommand auditCommand) {
        AuditServiceThreadImpl auditServiceThread = 
                new AuditServiceThreadImpl(auditCommand);
        return auditServiceThread;
    }
    
}