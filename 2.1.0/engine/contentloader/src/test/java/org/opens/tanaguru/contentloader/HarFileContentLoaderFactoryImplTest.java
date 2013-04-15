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
package org.opens.tanaguru.contentloader;

import junit.framework.TestCase;

/**
 *
 * @author jkowalczyk
 */
public class HarFileContentLoaderFactoryImplTest extends TestCase {
    
    public HarFileContentLoaderFactoryImplTest(String testName) {
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
     * Test of create method, of class HarFileContentLoaderFactoryImpl.
     */
    public void testCreate() {
        System.out.println("create");

        HarFileContentLoaderFactoryImpl instance = new HarFileContentLoaderFactoryImpl();

        HarFileContentLoader result = instance.create(null, "");
        assertTrue(result instanceof HarFileContentLoaderImpl);
    }
    
}