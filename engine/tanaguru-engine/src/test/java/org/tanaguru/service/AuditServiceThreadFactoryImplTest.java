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
import org.easymock.EasyMock;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.service.command.AuditCommand;

/**
 *
 * @author jkowalczyk
 */
public class AuditServiceThreadFactoryImplTest extends TestCase {
    
    public AuditServiceThreadFactoryImplTest(String testName) {
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
     * Test of create method, of class AuditServiceThreadFactoryImpl.
     */
    public void testCreate1() {
        System.out.println("create from Audit");
        Audit audit = EasyMock.createMock(Audit.class);
        AuditServiceThreadFactoryImpl instance = initiliseAuditServiceThreadFactory();
        
//        AuditServiceThread result = instance.create(audit);
        
        // the create method returns a result AuditServiceThreadImpl instance 
        // set-up with all the services and data-services
//        assertTrue(result instanceof AuditServiceThreadImpl);
//        assertEquals(audit, result.getAudit());
    }
    
    /**
     * Test of create method, of class AuditServiceThreadFactoryImpl.
     */
    public void testCreate2() {
        System.out.println("create from AuditCommand");
        AuditCommand auditCommand = EasyMock.createMock(AuditCommand.class);
        AuditServiceThreadFactoryImpl instance = initiliseAuditServiceThreadFactory();
        
//        AuditServiceThread result = instance.create(auditCommand);
        
        // the create method returns a result AuditServiceThreadImpl instance 
        // set-up with all the services and data-services
//        assertTrue(result instanceof AuditServiceThreadImpl);
    }
    
    private AuditServiceThreadFactoryImpl initiliseAuditServiceThreadFactory() {
        AuditServiceThreadFactoryImpl instance = new AuditServiceThreadFactoryImpl();
        return instance;
    }
}
