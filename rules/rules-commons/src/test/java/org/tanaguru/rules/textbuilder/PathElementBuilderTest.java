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

import java.io.File;
import java.io.IOException;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author alingua
 */
public class PathElementBuilderTest extends TestCase {

    private static final Logger LOGGER =
            Logger.getLogger(PathElementBuilderTest.class);

    public PathElementBuilderTest(String testName) {
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
     * Test of buildTextFromElement method, of class PathElementBuilder.
     */
    public void testBuildTextFromElement() throws IOException{
        LOGGER.info("buildTextFromElement");
        Document document = Jsoup.parse(FileUtils.readFileToString(new File("src/test/resources/pathBuilder/test1.html")));
        Element el = document.select("footer").first();
        PathElementBuilder instance = new PathElementBuilder(true);
        String result = instance.buildTextFromElement(el);
        LOGGER.debug("result = " + result);
        Elements elementsFromBuiltPath = document.select(result);
        assertEquals(1, elementsFromBuiltPath.size());
        assertEquals(el, elementsFromBuiltPath.first());
    }
}
