/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2020  Asqatasun.org
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
package org.asqatasun.service.command.factory;

import java.util.*;

import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.asqatasun.contentadapter.AdaptationListener;
import org.asqatasun.entity.audit.Audit;
import org.asqatasun.entity.audit.AuditStatus;
import org.asqatasun.entity.parameterization.Parameter;
import org.asqatasun.entity.service.audit.AuditDataService;
import org.asqatasun.entity.service.audit.ContentDataService;
import org.asqatasun.entity.service.audit.ProcessResultDataService;
import org.asqatasun.entity.service.parameterization.ParameterDataService;
import org.asqatasun.entity.service.reference.TestDataService;
import org.asqatasun.entity.service.subject.WebResourceDataService;
import org.asqatasun.service.*;
import org.asqatasun.service.command.*;

/**
 *
 * @author jkowalczyk
 */
public class AuditCommandFactoryImplTest extends TestCase {
    
    private AuditCommandFactoryImpl auditCommandFactory;
    
    private Audit mockAudit;
    
    private AuditDataService mockAuditDataService;
    
    public AuditCommandFactoryImplTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        mockAuditDataService = EasyMock.createMock(AuditDataService.class);
        mockAudit = EasyMock.createMock(Audit.class);
        
        EasyMock.expect(mockAuditDataService.create()).andReturn(mockAudit);
        mockAudit.setStatus(AuditStatus.PENDING);
        EasyMock.expectLastCall().once();
        EasyMock.expect(mockAuditDataService.saveOrUpdate(mockAudit)).andReturn(mockAudit).once();

        EasyMock.replay(mockAuditDataService);
        EasyMock.replay(mockAudit);

        auditCommandFactory = new AuditCommandFactoryImpl(mockAuditDataService, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of create method, of class AuditCommandFactoryImpl.
     */
    public void testCreate_3args_1() {
        System.out.println("create PageAuditCommand with crawler");

        auditCommandFactory.setAuditPageWithCrawler(true);
        AuditCommand result = this.auditCommandFactory.create(
            "",
            Collections.EMPTY_SET,
            Collections.EMPTY_LIST,
            false);
        
        assertTrue(result instanceof PageAuditCrawlerCommandImpl);
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockAudit);
    }
    
    /**
     * Test of create method, of class AuditCommandFactoryImpl.
     */
    public void testCreate_3args_1_2() {
        System.out.println("create PageAuditCommand without crawler");
        auditCommandFactory.setAuditPageWithCrawler(false);
        AuditCommand result = this.auditCommandFactory.create(
            "",
            Collections.EMPTY_SET,
            Collections.EMPTY_LIST,
            false);
        assertTrue(result instanceof PageAuditCommandImpl);
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockAudit);
    }
    
    /**
     * Test of create method, of class AuditCommandFactoryImpl.
     */
    public void testCreate_3args_2() {
        System.out.println("create SiteAuditCommand");

        AuditCommand result = this.auditCommandFactory.create(
            "",
            Collections.EMPTY_SET,
            Collections.EMPTY_LIST,
            true);
        assertTrue(result instanceof SiteAuditCommandImpl);
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockAudit);
    }

    /**
     * Test of create method, of class AuditCommandFactoryImpl.
     */
    public void testCreate_Map_Set() {
        System.out.println("create UploadAuditCommand");

        AuditCommand result = this.auditCommandFactory.create(
            Collections.EMPTY_MAP, Collections.EMPTY_SET, Collections.EMPTY_LIST);
        assertTrue(result instanceof UploadAuditCommandImpl);
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockAudit);
    }

    /**
     * Test of create method, of class AuditCommandFactoryImpl.
     */
    public void testCreate_3args_3_1() {
        System.out.println("create GroupOfPagesAuditCommand with crawler");

        auditCommandFactory.setAuditPageWithCrawler(true);
        AuditCommand result = this.auditCommandFactory.create(
            "",
            Collections.EMPTY_LIST,
            Collections.EMPTY_SET,
            Collections.EMPTY_LIST);
        assertTrue(result instanceof GroupOfPagesCrawlerAuditCommandImpl);
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockAudit);
    }
    
    /**
     * Test of create method, of class AuditCommandFactoryImpl.
     */
    public void testCreate_3args_3_2() {
        System.out.println("create GroupOfPagesAuditCommand without crawler");

        auditCommandFactory.setAuditPageWithCrawler(false);
        AuditCommand result = this.auditCommandFactory.create(
            "https://asqatasun.org",
            Arrays.asList("https://asqatasun.org"),
            Collections.EMPTY_SET,
            Collections.EMPTY_LIST);
        assertTrue(result instanceof GroupOfPagesAuditCommandImpl);
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockAudit);
    }

    /**
     * Test of create method, of class AuditCommandFactoryImpl.
     */
    public void testCreate_3args_4() {
        System.out.println("create ScenarioAuditCommand");

        AuditCommand result = this.auditCommandFactory.create(
            "", "", Collections.EMPTY_SET, Collections.EMPTY_LIST);
        assertTrue(result instanceof ScenarioAuditCommandImpl);
        EasyMock.verify(mockAuditDataService);
        EasyMock.verify(mockAudit);
    }
    
}
