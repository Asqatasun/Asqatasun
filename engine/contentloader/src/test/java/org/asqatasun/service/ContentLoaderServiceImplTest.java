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
package org.asqatasun.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.asqatasun.contentloader.ContentLoader;
import org.asqatasun.contentloader.ContentLoaderFactory;
import org.asqatasun.entity.audit.Content;
import org.asqatasun.entity.service.audit.ContentDataService;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.util.factory.DateFactory;

/**
 *
 * @author jkowalczyk
 */
public class ContentLoaderServiceImplTest extends TestCase {
    
    public ContentLoaderServiceImplTest(String testName) {
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
     * Test of loadContent method, of class ContentLoaderServiceImpl.
     */
    public void testLoadContent_WebResource() {
        System.out.println("loadContent");
        
        WebResource mockWebResource = EasyMock.createMock(WebResource.class);
        
        ContentDataService mockContentDataService = EasyMock.createMock(ContentDataService.class);

        ContentLoaderFactory mockContentLoaderFactory = EasyMock.createMock(ContentLoaderFactory.class);
        
        ContentLoader mockContentLoader = EasyMock.createMock(ContentLoader.class);
        
        DateFactory mockDateFactory = EasyMock.createMock(DateFactory.class);

        EasyMock.expect(mockContentLoaderFactory.create(null, mockDateFactory))
                .andReturn(mockContentLoader)
                .once();
        
        mockContentLoader.setWebResource(mockWebResource);
        EasyMock.expectLastCall().once();
        mockContentLoader.run();
        EasyMock.expectLastCall().once();
        
        List<Content> contentList = new ArrayList<>();
        Content mockContent = EasyMock.createMock(Content.class);
        contentList.add(mockContent);
        
        EasyMock.expect(mockContentLoader.getResult())
                .andReturn(contentList)
                .once();
        
        EasyMock.replay(mockContent);
        EasyMock.replay(mockContentDataService);
        EasyMock.replay(mockContentLoader);
        EasyMock.replay(mockContentLoaderFactory);
        EasyMock.replay(mockDateFactory);
        
        ContentLoaderServiceImpl instance = new ContentLoaderServiceImpl(mockContentLoaderFactory, mockDateFactory);


        assertEquals(contentList, instance.loadContent(mockWebResource, null));
        
        EasyMock.verify(mockContent);
        EasyMock.verify(mockContentDataService);
        EasyMock.verify(mockContentLoader);
        EasyMock.verify(mockContentLoaderFactory);
        EasyMock.verify(mockDateFactory);
    }

    /**
     * Test of loadContent method, of class ContentLoaderServiceImpl.
     */
    public void testLoadContent_WebResource_Map() {
        System.out.println("loadContent with file Map");
        
        WebResource mockWebResource = EasyMock.createMock(WebResource.class);
        
        ContentDataService mockContentDataService = EasyMock.createMock(ContentDataService.class);
        
        ContentLoaderFactory mockContentLoaderFactory = EasyMock.createMock(ContentLoaderFactory.class);
        
        DateFactory mockDateFactory = EasyMock.createMock(DateFactory.class);
        
        ContentLoader mockContentLoader = EasyMock.createMock(ContentLoader.class);
        
        Map<String, String> fileMap = new HashMap<>();
        
        EasyMock.expect(mockContentLoaderFactory.create(fileMap, mockDateFactory))
                .andReturn(mockContentLoader)
                .once();
        
        mockContentLoader.setWebResource(mockWebResource);
        EasyMock.expectLastCall().once();
        mockContentLoader.run();
        EasyMock.expectLastCall().once();
        
        List<Content> contentList = new ArrayList<>();
        Content mockContent = EasyMock.createMock(Content.class);
        contentList.add(mockContent);
        
        EasyMock.expect(mockContentLoader.getResult())
                .andReturn(contentList)
                .once();
        
        EasyMock.replay(mockContent);
        EasyMock.replay(mockContentDataService);
        EasyMock.replay(mockContentLoader);
        EasyMock.replay(mockContentLoaderFactory);
        EasyMock.replay(mockDateFactory);
        
        ContentLoaderServiceImpl instance = new ContentLoaderServiceImpl(mockContentLoaderFactory, mockDateFactory);
        
        assertEquals(contentList, instance.loadContent(mockWebResource, fileMap));
        
        EasyMock.verify(mockContent);
        EasyMock.verify(mockContentDataService);
        EasyMock.verify(mockContentLoader);
        EasyMock.verify(mockContentLoaderFactory);
        EasyMock.verify(mockDateFactory);
    }

}
