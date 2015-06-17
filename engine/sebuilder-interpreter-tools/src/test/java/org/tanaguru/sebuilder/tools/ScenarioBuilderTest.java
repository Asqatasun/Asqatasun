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
package org.tanaguru.sebuilder.tools;

import java.util.LinkedList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author jkowalczyk
 */
public class ScenarioBuilderTest extends TestCase {
    
   public ScenarioBuilderTest() {
    }

    @Override
    public void setUp() throws Exception {
    }

    @Override
    public void tearDown() throws Exception {
    }
    
    /**
     * Test of buildScenario method, of class ScenarioBuilder.
     */
    public void testBuildScenario_String() {
        System.out.println("buildScenario with 1 Url");
        String url = "http://www.test.org";
        String result = ScenarioBuilder.buildScenario(url);
        String expResult = ""
                + "{"
                + "\"seleniumVersion\":\"2\","
                + "\"formatVersion\":1,"
                + "\"steps\":"
                + "["
                + "{"
                + "\"type\":\"get\","
                + "\"url\":\"http://www.test.org\"}"
                + "]"
                + "}";
        assertEquals(expResult, result);
    }

    /**
     * Test of buildScenario method, of class ScenarioBuilder.
     */
    public void testBuildScenario_List() {
        System.out.println("buildScenario with several Url");
        List<String> urlList = new LinkedList<>();
        String url1 = "http://www.test1.org";
        String url2 = "http://www.test2.org";
        String url3 = "http://www.test3.org";
        String url4 = "http://www.test4.org";
        urlList.add(url1);
        urlList.add(url2);
        urlList.add(url3);
        urlList.add(url4);
        String expResult = ""
                + "{"
                + "\"seleniumVersion\":\"2\","
                + "\"formatVersion\":1,"
                + "\"steps\":"
                + "["
                + "{"
                + "\"type\":\"get\","
                + "\"url\":\"http://www.test1.org\""
                + "}"
                + ","
                + "{"
                + "\"type\":\"get\","
                + "\"url\":\"http://www.test2.org\""
                + "}"
                + ","
                + "{"
                + "\"type\":\"get\","
                + "\"url\":\"http://www.test3.org\""
                + "}"
                + ","
                + "{"
                + "\"type\":\"get\","
                + "\"url\":\"http://www.test4.org\""
                + "}"
                + "]"
                + "}";
        String result = ScenarioBuilder.buildScenario(urlList);
        assertEquals(expResult, result);
    }
}
