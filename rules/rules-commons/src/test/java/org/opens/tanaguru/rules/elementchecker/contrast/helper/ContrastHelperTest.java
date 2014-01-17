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
package org.opens.tanaguru.rules.elementchecker.contrast.helper;

import java.awt.Color;
import junit.framework.TestCase;

/**
 *
 * @author jkowalczyk
 */
public class ContrastHelperTest extends TestCase {
    
    public ContrastHelperTest(String testName) {
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
     * Test of getColorFromString method, of class ContrastHelper.
     */
    public void testGetColorFromString() {
        System.out.println("getColorFromString");
        String color = "rgb(228; 228; 228)";
        Color expResult = new Color(228,228,228);
        Color result = ContrastHelper.getColorFromString(color);
        assertEquals(expResult, result);
        color = "transparent";
        expResult = new Color(255,255,255);
        result = ContrastHelper.getColorFromString(color);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getColorFromString method, of class ContrastHelper.
     */
    public void testIsColorTestable() {
        System.out.println("isColorTestable");
        String color = "rgb(228; 228; 228)";
        assertTrue(ContrastHelper.isColorTestable(color));
        color = "rgba(228; 228; 228; 0)";
        assertFalse(ContrastHelper.isColorTestable(color));
        color = "background-image:url('http://www.myweb.com/images/my-image.jpg')";
        assertFalse(ContrastHelper.isColorTestable(color));
        color = "ackground-image:linear-gradient(rgb(34, 34, 34), rgb(17, 17, 17))";
        assertFalse(ContrastHelper.isColorTestable(color));
    }

}