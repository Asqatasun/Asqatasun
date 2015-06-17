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
import org.jsoup.parser.Tag;
import org.tanaguru.rules.keystore.AttributeStore;

/**
 *
 * @author jkowalczyk
 */
public class TextAttributeOfElementBuilderTest extends TestCase {
    
    private static final Logger LOGGER = 
            Logger.getLogger(TextAttributeOfElementBuilderTest.class);
    
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
        LOGGER.debug("buildTextFromElementWithTargettedAttributeNotSet");
        Element element = new Element(Tag.valueOf("div"), "");
        element.attr(AttributeStore.ALT_ATTR, "test");
        TextAttributeOfElementBuilder instance = new TextAttributeOfElementBuilder();
        String result = instance.buildTextFromElement(element);
        assertNull(result);
//        assertNull(instance.getAttributeName());
    }
    
    /**
     * Test of buildTextFromElement method, of class TextAttributeOfElementBuilder.
     */
    public void testBuildTextFromElementWithAttribute() {
        LOGGER.debug("buildTextFromElementWithAttribute");
        Element element = new Element(Tag.valueOf("div"), "");
        element.attr(AttributeStore.ALT_ATTR, "test");
        TextAttributeOfElementBuilder instance = new TextAttributeOfElementBuilder(AttributeStore.ALT_ATTR);
        String result = instance.buildTextFromElement(element);
        assertEquals("test", result);
//        assertEquals(AttributeStore.ALT_ATTR, instance.getAttributeName());
    }
    
    /**
     * Test of buildTextFromElement method, of class TextAttributeOfElementBuilder.
     */
    public void testBuildTextFromElementWithAttributeTrim() {
        LOGGER.debug("buildTextFromElementWithAttributeTrim");
        Element element = new Element(Tag.valueOf("div"), "");
        element.attr(AttributeStore.ALT_ATTR,  "    test    ");
        TextAttributeOfElementBuilder instance = new TextAttributeOfElementBuilder();
        instance.setAttributeName(AttributeStore.ALT_ATTR);
        String result = instance.buildTextFromElement(element);
        assertEquals("test", result);
//        assertEquals(AttributeStore.ALT_ATTR, instance.getAttributeName());
    }
    
    /**
     * Test of buildTextFromElement method, of class TextAttributeOfElementBuilder.
     */
    public void testBuildTextFromElementWithAttributeMissing() {
        LOGGER.debug("buildTextFromElementWithAttributeMissing");
        Element element = new Element(Tag.valueOf("div"), "");
        TextAttributeOfElementBuilder instance = new TextAttributeOfElementBuilder(AttributeStore.ALT_ATTR);
        String result = instance.buildTextFromElement(element);
        assertNull(result);
//        assertEquals(AttributeStore.ALT_ATTR, instance.getAttributeName());
    }
    
}