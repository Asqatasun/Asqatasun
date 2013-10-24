/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2013  Open-S Company
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
package org.opens.tanaguru.rules.textbuilder;

import junit.framework.TestCase;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;

/**
 *
 * @author jkowalczyk
 */
public class SimpleTextElementBuilderTest extends TestCase {
    
    public SimpleTextElementBuilderTest(String testName) {
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
     * Test of buildTextFromElement method, of class SimpleTextElementBuilder.
     */
    public void testBuildTextFromElement() {
        System.out.println("buildTextFromElement");
        Element element = new Element(Tag.valueOf("div"), "");
        element.text("test");
        SimpleTextElementBuilder instance = new SimpleTextElementBuilder();
        String expResult = "test";
        String result = instance.buildTextFromElement(element);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of buildTextFromElement method, of class SimpleTextElementBuilder.
     */
    public void testBuildTextFromElementTrim() {
        System.out.println("buildTextFromElementTrim");
        Element element = new Element(Tag.valueOf("div"), "");
        element.text("   test   ");
        SimpleTextElementBuilder instance = new SimpleTextElementBuilder();
        String expResult = "test";
        String result = instance.buildTextFromElement(element);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of buildTextFromElement method, of class SimpleTextElementBuilder.
     */
    public void testBuildTextFromElementWithChildren() {
        System.out.println("buildTextFromElementWithChildren");
        Element element = new Element(Tag.valueOf("div"), "");
        element.appendText("   text1   ");
        
        Element childElement = new Element(Tag.valueOf("div"), "");
        childElement.text("   child element text   ");

        Element childElement2 = new Element(Tag.valueOf("div"), "");
        childElement2.text("   child element text second level  ");
        childElement.appendChild(childElement2);
        
        element.appendChild(childElement);
        element.appendText("   text2   ");

        SimpleTextElementBuilder instance = new SimpleTextElementBuilder();
        String expResult = "text1 child element text child element text second level text2";
        String result = instance.buildTextFromElement(element);
        assertEquals(expResult, result);
    }
    
}