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
package org.opens.tanaguru.rules.elementchecker;

import org.jsoup.select.Elements;
import org.junit.*;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.attribute.AttributePresenceChecker;

/**
 *
 * @author jkowalczyk
 */
public class AttributePresenceCheckerTest {
    
    public AttributePresenceCheckerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getNotDetectedSolution method, of class AttributePresenceChecker.
     */
    @Test
    public void testGetNotDetectedSolution() {
        System.out.println("getNotDetectedSolution");
        AttributePresenceChecker instance = null;
        TestSolution expResult = null;
//        TestSolution result = instance.getNotDetectedSolution();
//        assertEquals(expResult, result);
//         TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setNotDetectedSolution method, of class AttributePresenceChecker.
     */
    @Test
    public void testSetNotDetectedSolution() {
        System.out.println("setNotDetectedSolution");
        TestSolution notDetectedSolution = null;
//        AttributePresenceChecker instance = null;
//        instance.setNotDetectedSolution(notDetectedSolution);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getDetectedSolution method, of class AttributePresenceChecker.
     */
    @Test
    public void testGetDetectedSolution() {
        System.out.println("getDetectedSolution");
        AttributePresenceChecker instance = null;
        TestSolution expResult = null;
//        TestSolution result = instance.getDetectedSolution();
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of setDetectedSolution method, of class AttributePresenceChecker.
     */
    @Test
    public void testSetDetectedSolution() {
        System.out.println("setDetectedSolution");
        TestSolution detectedSolution = null;
//        AttributePresenceChecker instance = null;
//        instance.setDetectedSolution(detectedSolution);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of doCheck method, of class AttributePresenceChecker.
     */
    @Test
    public void testDoCheck() {
        System.out.println("doCheck");
        SSPHandler sspHandler = null;
        Elements elements = null;
        TestSolutionHandler testSolutionHandler = null;
//        AttributePresenceChecker instance = null;
//        instance.doCheck(sspHandler, elements, testSolutionHandler);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
