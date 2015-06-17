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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author jkowalczyk
 */
public class LinkTextElementBuilderTest extends TestCase {
    
    private static final Logger LOGGER = 
            Logger.getLogger(LinkTextElementBuilderTest.class);
    
    public LinkTextElementBuilderTest(String testName) {
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
     * Test of buildTextFromElement method, of class LinkTextElementBuilder.
     */
    public void testCompositeLink() {
        LOGGER.debug("buildTextFromElement of Composite link");
        Document document = Jsoup.parse("<a href=\"\">Text1<img alt=\"Image Alt \" src=\"\"/> Text3</a>");
        Element el = document.select("a").first();
        LinkTextElementBuilder instance = new LinkTextElementBuilder();
        String expResult = "Text1 Image Alt Text3";
        String result = instance.buildTextFromElement(el);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of buildTextFromElement method, of class LinkTextElementBuilder.
     */
    public void testCompositeLinkWithNonAlphanumericalCharacters() {
        LOGGER.debug("buildTextFromElement of Composite link");
        Document document = Jsoup.parse("<a href=\"my-link.html\" title=\"-+*-->;*&!§\">"
            +"    -+*"
            +"    <img src=\"image.jpg\" alt=\"-->;*\"/>"
            +"    &!§"
            +"</a>");
        Element el = document.select("a").first();
        LinkTextElementBuilder instance = new LinkTextElementBuilder();
        String expResult = "-+* -->;* &!§";
        String result = instance.buildTextFromElement(el);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of buildTextFromElement method, of class LinkTextElementBuilder.
     */
    public void testImageLink() {
        LOGGER.debug("buildTextFromElement of Image link");
        Document document = Jsoup.parse("<a href=\"\">   <img alt=\" Image Alt \" src=\"\"/>  </a>");
        Element el = document.select("a").first();
        LinkTextElementBuilder instance = new LinkTextElementBuilder();
        String expResult = "Image Alt";
        String result = instance.buildTextFromElement(el);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of buildTextFromElement method, of class LinkTextElementBuilder.
     */
    public void testImageObjectLink() {
        LOGGER.debug("buildTextFromElement of Image object link");
        Document document = Jsoup.parse("<a href=\"fake-link.htm\"> <object type=\"image\" "
                + "data=\"fake-data.png\">"
                
                + "        cliquez ici"
                + "    </object>"
                +"</a>");
        Element el = document.select("a").first();
        LinkTextElementBuilder instance = new LinkTextElementBuilder();
        String expResult = "cliquez ici";
        String result = instance.buildTextFromElement(el);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of buildTextFromElement method, of class LinkTextElementBuilder.
     */
    public void testSimpleLink() {
        LOGGER.debug("buildTextFromElement of simple link");
        Document document = Jsoup.parse("<a href=\"\">    Text1 Text3    </a>");
        Element el = document.select("a").first();
        LinkTextElementBuilder instance = new LinkTextElementBuilder();
        String expResult = "Text1 Text3";
        String result = instance.buildTextFromElement(el);
        assertEquals(expResult, result);
    }
}
