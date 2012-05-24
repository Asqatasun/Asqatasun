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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.opens.tanaguru.contentloader.ContentLoader;
import org.opens.tanaguru.contentloader.ContentLoaderFactory;
import org.opens.tanaguru.contentloader.Downloader;
import org.opens.tanaguru.contentloader.DownloaderFactory;
import org.opens.tanaguru.entity.audit.Content;
import org.opens.tanaguru.entity.factory.audit.ContentFactory;
import org.opens.tanaguru.entity.subject.WebResource;
import org.opens.tanaguru.util.factory.DateFactory;

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
        
        ContentFactory mockContentFactory = EasyMock.createMock(ContentFactory.class);
        
        Downloader mockDownloader = EasyMock.createMock(Downloader.class);
        
        DownloaderFactory mockDownloaderFactory = EasyMock.createMock(DownloaderFactory.class);
        
        ContentLoaderFactory mockContentLoaderFactory = EasyMock.createMock(ContentLoaderFactory.class);
        
        ContentLoader mockContentLoader = EasyMock.createMock(ContentLoader.class);
        
        DateFactory mockDateFactory = EasyMock.createMock(DateFactory.class);
        
        EasyMock.expect(mockDownloaderFactory.create())
                .andReturn(mockDownloader)
                .once();
        EasyMock.expect(mockContentLoaderFactory.create(mockContentFactory, mockDownloader, mockDateFactory,null))
                .andReturn(mockContentLoader)
                .once();
        
        mockContentLoader.setWebResource(mockWebResource);
        EasyMock.expectLastCall().once();
        mockContentLoader.run();
        EasyMock.expectLastCall().once();
        
        List<Content> contentList = new ArrayList<Content>();
        Content mockContent = EasyMock.createMock(Content.class);
        contentList.add(mockContent);
        
        EasyMock.expect(mockContentLoader.getResult())
                .andReturn(contentList)
                .once();
        
        EasyMock.replay(mockContent);
        EasyMock.replay(mockContentFactory);
        EasyMock.replay(mockContentLoader);
        EasyMock.replay(mockContentLoaderFactory);
        EasyMock.replay(mockDownloader);
        EasyMock.replay(mockDownloaderFactory);
        EasyMock.replay(mockDateFactory);
        
        ContentLoaderServiceImpl instance = new ContentLoaderServiceImpl();

        instance.setContentFactory(mockContentFactory);
        instance.setContentLoaderFactory(mockContentLoaderFactory);
        instance.setDownloaderFactory(mockDownloaderFactory);
        instance.setDateFactory(mockDateFactory);

        assertEquals(contentList, instance.loadContent(mockWebResource));
        
        EasyMock.verify(mockContent);
        EasyMock.verify(mockContentFactory);
        EasyMock.verify(mockContentLoader);
        EasyMock.verify(mockContentLoaderFactory);
        EasyMock.verify(mockDownloader);
        EasyMock.verify(mockDownloaderFactory);
        EasyMock.verify(mockDateFactory);
    }

    /**
     * Test of loadContent method, of class ContentLoaderServiceImpl.
     */
    public void testLoadContent_WebResource_Map() {
        System.out.println("loadContent with file Map");
        
        WebResource mockWebResource = EasyMock.createMock(WebResource.class);
        
        ContentFactory mockContentFactory = EasyMock.createMock(ContentFactory.class);
        
        ContentLoaderFactory mockContentLoaderFactory = EasyMock.createMock(ContentLoaderFactory.class);
        
        DateFactory mockDateFactory = EasyMock.createMock(DateFactory.class);
        
        ContentLoader mockContentLoader = EasyMock.createMock(ContentLoader.class);
        
        Map<String, String> fileMap = new HashMap<String, String>();
        
        EasyMock.expect(mockContentLoaderFactory.create(mockContentFactory, null, mockDateFactory, fileMap))
                .andReturn(mockContentLoader)
                .once();
        
        mockContentLoader.setWebResource(mockWebResource);
        EasyMock.expectLastCall().once();
        mockContentLoader.run();
        EasyMock.expectLastCall().once();
        
        List<Content> contentList = new ArrayList<Content>();
        Content mockContent = EasyMock.createMock(Content.class);
        contentList.add(mockContent);
        
        EasyMock.expect(mockContentLoader.getResult())
                .andReturn(contentList)
                .once();
        
        EasyMock.replay(mockContent);
        EasyMock.replay(mockContentFactory);
        EasyMock.replay(mockContentLoader);
        EasyMock.replay(mockContentLoaderFactory);
        EasyMock.replay(mockDateFactory);
        
        ContentLoaderServiceImpl instance = new ContentLoaderServiceImpl();

        instance.setContentFactory(mockContentFactory);
        instance.setContentLoaderFactory(mockContentLoaderFactory);
        instance.setDateFactory(mockDateFactory);
        
        assertEquals(contentList, instance.loadContent(mockWebResource, fileMap));
        
        EasyMock.verify(mockContent);
        EasyMock.verify(mockContentFactory);
        EasyMock.verify(mockContentLoader);
        EasyMock.verify(mockContentLoaderFactory);
        EasyMock.verify(mockDateFactory);
    }

}