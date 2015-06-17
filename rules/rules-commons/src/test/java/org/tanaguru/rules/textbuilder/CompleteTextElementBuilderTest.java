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
package org.tanaguru.rules.textbuilder;

import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Element;

/**
 *
 * @author jkowalczyk
 */
public class CompleteTextElementBuilderTest extends TestCase {
    
    private static final Logger LOGGER = 
            Logger.getLogger(CompleteTextElementBuilderTest.class);
    
    public CompleteTextElementBuilderTest(String testName) {
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
     * Test of buildTextFromElement method, of class CompleteTextElementBuilder.
     */
    public void testBuildTextFromElement() {
        LOGGER.debug("buildTextFromElement");
        Element element = null;
        CompleteTextElementBuilder instance = new CompleteTextElementBuilder();
        String expResult = "";
//        String result = instance.buildTextFromElement(element);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
