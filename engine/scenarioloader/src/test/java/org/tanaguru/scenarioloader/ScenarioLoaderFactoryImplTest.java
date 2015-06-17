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
package org.tanaguru.scenarioloader;

import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.tanaguru.entity.subject.WebResource;

/**
 *
 * @author jkowalczyk
 */
public class ScenarioLoaderFactoryImplTest extends TestCase {
    
    public ScenarioLoaderFactoryImplTest(String testName) {
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
     * Test of create method, of class ScenarioLoaderFactoryImpl.
     */
    public void testCreate() {
        System.out.println("create");
        String scenario = "";
        WebResource mockWebResource = createMock(WebResource.class);
        ScenarioLoaderFactoryImpl instance = new ScenarioLoaderFactoryImpl();
        
        ScenarioLoader scenarioLoader = instance.create(mockWebResource, scenario);
        assertTrue(scenarioLoader instanceof ScenarioLoaderImpl);
    }
    
}