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

import java.util.Map;
import junit.framework.TestCase;
import org.tanaguru.entity.service.audit.ContentDataService;
import org.tanaguru.util.factory.DateFactory;

/**
 *
 * @author jkowalczyk
 */
public class FileContentLoaderFactoryImplTest extends TestCase {
    
    public FileContentLoaderFactoryImplTest(String testName) {
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
     * Test of create method, of class FileContentLoaderFactoryImpl.
     */
    public void testCreate() {
        System.out.println("create");
        
        ContentDataService contentDataService = null;
        Downloader downloader = null;
        Map<String, String> fileMap = null;
        DateFactory dateFactory = null;
        
        FileContentLoaderFactoryImpl instance = new FileContentLoaderFactoryImpl();
        
        ContentLoader contentLoader = instance.create(
                contentDataService, 
                downloader, 
                dateFactory,
                fileMap);
        
        assertTrue(contentLoader instanceof FileContentLoaderImpl);
        
    }

}