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
package org.tanaguru.contentloader;

import java.util.*;
import junit.framework.TestCase;
import org.apache.http.HttpStatus;
import org.easymock.EasyMock;
import org.tanaguru.entity.audit.SSP;
import org.tanaguru.entity.service.audit.ContentDataService;
import org.tanaguru.entity.subject.*;
import org.tanaguru.util.factory.DateFactory;

/**
 *
 * @author jkowalczyk
 */
public class FileContentLoaderImplTest extends TestCase {
    
    public FileContentLoaderImplTest(String testName) {
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
     * Test of getWebResource method, of class FileContentLoaderImpl.
     */
    public void testGetSetWebResource() {
        System.out.println("getWebResource");
        
        WebResource mockWebResource = EasyMock.createMock(WebResource.class);
        
        FileContentLoaderImpl instance = new FileContentLoaderImpl(null, null, null);
        instance.setWebResource(mockWebResource);
        
        assertEquals(mockWebResource, instance.getWebResource());
    }

    /**
     * Test of run method, of class FileContentLoaderImpl.
     */
    public void testRun() {
        System.out.println("run");
        
        Page mockPage = EasyMock.createMock(Page.class);
        EasyMock.expect(mockPage.getURL()).andReturn("http://my.testUrl.org").times(2);
        
        ContentDataService mockContentDataService = EasyMock.createMock(ContentDataService.class);
        SSP mockSSP = EasyMock.createMock(SSP.class);
        
        Date date = new Date();
        DateFactory mockDateFactory = EasyMock.createMock(DateFactory.class);
        EasyMock.expect(mockDateFactory.createDate())
                .andReturn(date)
                .once();
        
        EasyMock.expect(mockContentDataService.getSSP(
                date, 
                "http://my.testUrl.org", 
                "My Page Content", 
                mockPage, 
                HttpStatus.SC_OK)).andReturn(mockSSP).once();
        
        Map<String, String> fileMap = new HashMap<>();
        fileMap.put("http://my.testUrl.org", "My Page Content");
        
        EasyMock.replay(mockPage);
        EasyMock.replay(mockSSP);
        EasyMock.replay(mockContentDataService);
        EasyMock.replay(mockDateFactory);
        
        FileContentLoaderImpl instance = new FileContentLoaderImpl(
                mockContentDataService, 
                fileMap, 
                mockDateFactory);
        instance.setWebResource(mockPage);
        instance.run();
        
        assertTrue(instance.getResult().contains(mockSSP));
        
        EasyMock.verify(mockPage);
        EasyMock.verify(mockSSP);
        EasyMock.verify(mockContentDataService);
        EasyMock.verify(mockDateFactory);
    }
    
    /**
     * Test of run method, of class FileContentLoaderImpl.
     */
    public void testRun1() {
        System.out.println("run");
        
        Site mockSite = new SiteImpl();
        Page mockPage1 = new PageImpl();
        mockPage1.setURL("http://my.testUrl1.org");
        Page mockPage2 = new PageImpl();
        mockPage2.setURL("http://my.testUrl2.org");
        
        mockSite.addChild(mockPage1);
        mockSite.addChild(mockPage2);
        
        ContentDataService mockContentDataService = EasyMock.createMock(ContentDataService.class);
        SSP mockSSP = EasyMock.createMock(SSP.class);
        
        Date date = new Date();
        DateFactory mockDateFactory = EasyMock.createMock(DateFactory.class);
        EasyMock.expect(mockDateFactory.createDate())
                .andReturn(date)
                .times(2);
        
        EasyMock.expect(mockContentDataService.getSSP(
                date, 
                "http://my.testUrl1.org", 
                "My Page Content 1", 
                mockPage1, 
                HttpStatus.SC_OK))
                    .andReturn(mockSSP)
                    .once();
        
        EasyMock.expect(mockContentDataService.getSSP(
                date, 
                "http://my.testUrl2.org", 
                "My Page Content 2", 
                mockPage2, 
                HttpStatus.SC_OK))
                    .andReturn(mockSSP)
                    .once();
        
        Map<String, String> fileMap = new HashMap<>();
        fileMap.put("http://my.testUrl1.org", "My Page Content 1");
        fileMap.put("http://my.testUrl2.org", "My Page Content 2");
        
        EasyMock.replay(mockSSP);
        EasyMock.replay(mockContentDataService);
        EasyMock.replay(mockDateFactory);
        
        FileContentLoaderImpl instance = new FileContentLoaderImpl(
                mockContentDataService, 
                fileMap, 
                mockDateFactory);
        instance.setWebResource(mockSite);
        instance.run();
        
        assertTrue(instance.getResult().contains(mockSSP));
        
        EasyMock.verify(mockSSP);
        EasyMock.verify(mockContentDataService);
        EasyMock.verify(mockDateFactory);
    }

}