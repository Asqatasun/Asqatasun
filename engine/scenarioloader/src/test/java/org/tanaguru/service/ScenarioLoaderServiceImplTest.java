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
import java.util.List;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.tanaguru.entity.audit.Audit;
import org.tanaguru.entity.audit.Content;
import org.tanaguru.entity.service.audit.ContentDataService;
import org.tanaguru.entity.service.subject.WebResourceDataService;
import org.tanaguru.entity.subject.WebResource;
import org.tanaguru.scenarioloader.ScenarioLoader;
import org.tanaguru.scenarioloader.ScenarioLoaderFactory;

/**
 *
 * @author jkowalczyk
 */
public class ScenarioLoaderServiceImplTest extends TestCase {
    
    public ScenarioLoaderServiceImplTest(String testName) {
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
     * Test of loadScenario method, of class ScenarioLoaderServiceImpl.
     */
    public void testLoadScenario() {
        System.out.println("loadScenario");
        
        String scenarioFile = "My Scenario";
        
        WebResource mockWebResource = createMock(WebResource.class);
        Audit mockAudit = createMock(Audit.class);
        expect(mockWebResource.getAudit())
                .andReturn(mockAudit)
                .once();

        ScenarioLoaderFactory mockScenarioLoaderFactory =  
                createMock(ScenarioLoaderFactory.class);
        
        ScenarioLoader mockScenarioLoader =  
                createMock(ScenarioLoader.class);
        
        expect(mockScenarioLoaderFactory.create(mockWebResource,scenarioFile))
                .andReturn(mockScenarioLoader)
                .once();

        mockScenarioLoader.run();
        expectLastCall().once();
        
        List<Content> contentList = new ArrayList<Content>();
        
        expect(mockScenarioLoader.getResult())
                .andReturn(contentList)
                .once();
        
        ContentDataService mockContentDataService =  
                createMock(ContentDataService.class);
        
        WebResourceDataService mockWebResourceDataService =  
                createMock(WebResourceDataService.class);
        expect(mockWebResourceDataService.saveOrUpdate(mockWebResource))
                .andReturn(mockWebResource)
                .once();
        
        replay(mockAudit);
        replay(mockWebResource);
        replay(mockWebResourceDataService);
        replay(mockContentDataService);
        replay(mockScenarioLoader);
        replay(mockScenarioLoaderFactory);
        
        ScenarioLoaderServiceImpl instance = new ScenarioLoaderServiceImpl();
        instance.setContentDataService(mockContentDataService);
        instance.setScenarioLoaderFactory(mockScenarioLoaderFactory);
        instance.setWebResourceDataService(mockWebResourceDataService);
        
        instance.loadScenario(mockWebResource, scenarioFile);
        
        verify(mockAudit);
        verify(mockWebResource);
        verify(mockWebResourceDataService);
        verify(mockContentDataService);
        verify(mockScenarioLoader);
        verify(mockScenarioLoaderFactory);
    }
    
}