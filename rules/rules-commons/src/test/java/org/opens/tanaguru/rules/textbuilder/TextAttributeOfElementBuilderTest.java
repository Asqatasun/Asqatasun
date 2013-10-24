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
import org.opens.tanaguru.rules.keystore.AttributeStore;

/**
 *
 * @author jkowalczyk
 */
public class TextAttributeOfElementBuilderTest extends TestCase {
    
    public TextAttributeOfElementBuilderTest(String testName) {
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
     * Test of buildTextFromElement method, of class TextAttributeOfElementBuilder.
     */
    public void testBuildTextFromElementWithTargettedAttributeNotSet() {
        System.out.println("buildTextFromElementWithTargettedAttributeNotSet");
        Element element = new Element(Tag.valueOf("div"), "");
        element.attr(AttributeStore.ALT_ATTR, "test");
        TextAttributeOfElementBuilder instance = new TextAttributeOfElementBuilder();
        String result = instance.buildTextFromElement(element);
        assertNull(result);
        assertNull(instance.getAttributeName());
    }
    
    /**
     * Test of buildTextFromElement method, of class TextAttributeOfElementBuilder.
     */
    public void testBuildTextFromElementWithAttribute() {
        System.out.println("buildTextFromElementWithAttribute");
        Element element = new Element(Tag.valueOf("div"), "");
        element.attr(AttributeStore.ALT_ATTR, "test");
        TextAttributeOfElementBuilder instance = new TextAttributeOfElementBuilder(AttributeStore.ALT_ATTR);
        String result = instance.buildTextFromElement(element);
        assertEquals("test", result);
        assertEquals(AttributeStore.ALT_ATTR, instance.getAttributeName());
    }
    
    /**
     * Test of buildTextFromElement method, of class TextAttributeOfElementBuilder.
     */
    public void testBuildTextFromElementWithAttributeTrim() {
        System.out.println("buildTextFromElementWithAttributeTrim");
        Element element = new Element(Tag.valueOf("div"), "");
        element.attr(AttributeStore.ALT_ATTR,  "    test    ");
        TextAttributeOfElementBuilder instance = new TextAttributeOfElementBuilder();
        instance.setAttributeName(AttributeStore.ALT_ATTR);
        String result = instance.buildTextFromElement(element);
        assertEquals("test", result);
        assertEquals(AttributeStore.ALT_ATTR, instance.getAttributeName());
    }
    
    /**
     * Test of buildTextFromElement method, of class TextAttributeOfElementBuilder.
     */
    public void testBuildTextFromElementWithAttributeMissing() {
        System.out.println("buildTextFromElementWithAttributeMissing");
        Element element = new Element(Tag.valueOf("div"), "");
        TextAttributeOfElementBuilder instance = new TextAttributeOfElementBuilder(AttributeStore.ALT_ATTR);
        String result = instance.buildTextFromElement(element);
        assertNull(result);
        assertEquals(AttributeStore.ALT_ATTR, instance.getAttributeName());
    }
    
}