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
package org.opens.tanaguru.service;

import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.opens.tanaguru.contentadapter.AdaptationListener;
import org.opens.tanaguru.entity.audit.Audit;
import org.opens.tanaguru.entity.audit.AuditImpl;
import org.opens.tanaguru.entity.audit.AuditStatus;
import org.opens.tanaguru.entity.reference.Test;
import org.opens.tanaguru.entity.reference.TestImpl;
import org.opens.tanaguru.entity.service.audit.AuditDataService;
import org.opens.tanaguru.entity.service.audit.ContentDataService;
import org.opens.tanaguru.entity.service.audit.ProcessResultDataService;
import org.opens.tanaguru.entity.service.subject.WebResourceDataService;
import org.opens.tanaguru.entity.subject.PageImpl;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.service.command.AuditCommand;

/**
 *
 * @author jkowalczyk
 */
public class AuditServiceThreadImplTest extends TestCase {
    
    private AuditDataService mockAuditDataService;
    
    public AuditServiceThreadImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockAuditDataService = EasyMock.createMock(AuditDataService.class);
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
        
        Audit audit = EasyMock.createMock(Audit.class);
        Audit auditReturnedByAuditCommand = EasyMock.createMock(Audit.class);
        AuditCommand mockAuditCommand = EasyMock.createMock(AuditCommand.class);
        EasyMock.expect(mockAuditCommand.getAudit()).andReturn(auditReturnedByAuditCommand);
        EasyMock.replay(mockAuditCommand);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(audit);
        assertEquals(audit, instance.getAudit());
        
        instance = initialiseAuditServiceThread(mockAuditCommand);
        assertEquals(auditReturnedByAuditCommand, instance.getAudit());
    }

    /**
     * Test of add/remove AuditServiceListener method of class AuditServiceImpl.
     */
    public void testAddAndRemove() {
        
        Audit audit = EasyMock.createMock(Audit.class);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(audit);
        
        AuditServiceThreadListener mockAuditServiceThreadListener = 
                EasyMock.createMock(AuditServiceThreadListener.class);
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

        AuditCommand mockAuditCommand = EasyMock.createMock(AuditCommand.class);
        mockAuditCommand.init();
        EasyMock.expectLastCall().once();
        mockAuditCommand.loadContent();
        EasyMock.expectLastCall().once();
        mockAuditCommand.adaptContent();
        EasyMock.expectLastCall().once();
        mockAuditCommand.process();
        EasyMock.expectLastCall().once();
        mockAuditCommand.consolidate();
        EasyMock.expectLastCall().once();
        mockAuditCommand.analyse();
        EasyMock.expectLastCall().once();
        EasyMock.replay(mockAuditCommand);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(mockAuditCommand);
        
        AuditServiceThreadListener auditServiceThreadListener = 
                EasyMock.createMock(AuditServiceThreadListener.class);
        auditServiceThreadListener.auditCompleted(instance);
        EasyMock.expectLastCall().once();
        EasyMock.replay(auditServiceThreadListener);
        
        instance.add(auditServiceThreadListener);
        instance.run();
        
        EasyMock.verify(mockAuditCommand);
        EasyMock.verify(auditServiceThreadListener);
    }

    public void testInit() {
        System.out.println("init");
        
        AuditCommand mockAuditCommand = EasyMock.createMock(AuditCommand.class);
        mockAuditCommand.init();
        EasyMock.expectLastCall().once();
        EasyMock.replay(mockAuditCommand);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(mockAuditCommand);
        instance.init();
        
        EasyMock.verify(mockAuditCommand);
    }
    
    /**
     * Test of loadContent method, of class AuditServiceThreadImpl.
     */
    public void testLoadContent() {
        System.out.println("loadContent");
        
        AuditCommand mockAuditCommand = EasyMock.createMock(AuditCommand.class);
        mockAuditCommand.loadContent();
        EasyMock.expectLastCall().once();
        EasyMock.replay(mockAuditCommand);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(mockAuditCommand);
        instance.loadContent();
        
        EasyMock.verify(mockAuditCommand);
    }

    /**
     * Test of adaptContent method, of class AuditServiceThreadImpl.
     */
    public void testAdaptContent() {
        System.out.println("adaptContent");
        
        AuditCommand mockAuditCommand = EasyMock.createMock(AuditCommand.class);
        mockAuditCommand.adaptContent();
        EasyMock.expectLastCall().once();
        EasyMock.replay(mockAuditCommand);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(mockAuditCommand);
        instance.adaptContent();
        
        EasyMock.verify(mockAuditCommand);
    }

    /**
     * Test of process method, of class AuditServiceThreadImpl.
     */
    public void testProcess() {
        System.out.println("process");
        
        AuditCommand mockAuditCommand = EasyMock.createMock(AuditCommand.class);
        mockAuditCommand.process();
        EasyMock.expectLastCall().once();
        EasyMock.replay(mockAuditCommand);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(mockAuditCommand);
        instance.process();
        
        EasyMock.verify(mockAuditCommand);
    }

    /**
     * Test of consolidate method, of class AuditServiceThreadImpl.
     */
    public void testConsolidate() {
        System.out.println("consolidate");
        
        AuditCommand mockAuditCommand = EasyMock.createMock(AuditCommand.class);
        mockAuditCommand.consolidate();
        EasyMock.expectLastCall().once();
        EasyMock.replay(mockAuditCommand);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(mockAuditCommand);
        instance.consolidate();
        
        EasyMock.verify(mockAuditCommand);
    }

    /**
     * Test of analyse method, of class AuditServiceThreadImpl.
     */
    public void testAnalyse() {
        System.out.println("analyse");
        
        AuditCommand mockAuditCommand = EasyMock.createMock(AuditCommand.class);
        mockAuditCommand.analyse();
        EasyMock.expectLastCall().once();
        EasyMock.replay(mockAuditCommand);
        
        AuditServiceThreadImpl instance = initialiseAuditServiceThread(mockAuditCommand);
        instance.analyse();
        
        EasyMock.verify(mockAuditCommand);
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