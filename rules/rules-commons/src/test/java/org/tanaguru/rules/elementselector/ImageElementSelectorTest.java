/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015 Tanaguru.org
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
package org.tanaguru.rules.elementselector;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.easymock.EasyMock;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.tanaguru.entity.audit.SSP;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.ElementHandler;
import org.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.tanaguru.rules.keystore.HtmlElementStore;

/**
 *
 * @author jkowalczyk
 */
public class ImageElementSelectorTest extends TestCase{
    
    private static final Logger LOGGER = 
            Logger.getLogger(ImageElementSelectorTest.class);
    
    private SSP ssp;
    private SSPHandler sspHandler;
    
    public ImageElementSelectorTest() {
    }

    @Override
    public void setUp() {
    }
    
    @Override
    public void tearDown() {
    }

    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements1_1() {
        LOGGER.debug("selectElements1-1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }

    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements1_2() {
        LOGGER.debug("selectElements1-2");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-2.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }

    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements1_3() {
        LOGGER.debug("selectElements1-3");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-3.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }

    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements1_4() {
        LOGGER.debug("selectElements1-4");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements2_1() {
        LOGGER.debug("selectElements2-1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements2_2() {
        LOGGER.debug("selectElements2-2");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-2.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements2_3() {
        LOGGER.debug("selectElements2-3");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-3.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements2_4() {
        LOGGER.debug("selectElements2-4");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements3_1() {
        LOGGER.debug("selectElements3-1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }

    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements3_2() {
        LOGGER.debug("selectElements3-2");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-2.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }

    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements3_3() {
        LOGGER.debug("selectElements3-3");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-3.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }

    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements3_4() {
        LOGGER.debug("selectElements3-4");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements4_1() {
        LOGGER.debug("selectElements4-1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements4_2() {
        LOGGER.debug("selectElements4-2");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-2.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements4_3() {
        LOGGER.debug("selectElements4-3");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-3.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements4_4() {
        LOGGER.debug("selectElements4-4");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements5_1() {
        LOGGER.debug("selectElements5-1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }

    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements5_2() {
        LOGGER.debug("selectElements5-2");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-2.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }

    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements5_3() {
        LOGGER.debug("selectElements5-3");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-3.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }

    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements5_4() {
        LOGGER.debug("selectElements5-4");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }

    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements5_5() {
        LOGGER.debug("selectElements5-5");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-5.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements6_1() {
        LOGGER.debug("selectElements6-1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements6_2() {
        LOGGER.debug("selectElements6-2");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-2.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements6_3() {
        LOGGER.debug("selectElements6-3");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-3.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements6_4() {
        LOGGER.debug("selectElements6-4");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements6_5() {
        LOGGER.debug("selectElements6-5");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-5.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements7_1() {
        LOGGER.debug("selectElements7-1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }

    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements7_2() {
        LOGGER.debug("selectElements7-2");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-2.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }

    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements7_3() {
        LOGGER.debug("selectElements7-3");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-3.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

       assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }

    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements7_4() {
        LOGGER.debug("selectElements7-4");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }

    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements7_5() {
        LOGGER.debug("selectElements7-5");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    true, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements8_1() {
        LOGGER.debug("selectElements8-1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements8_2() {
        LOGGER.debug("selectElements8-2");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-2.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements8_3() {
        LOGGER.debug("selectElements8-3");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-3.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements8_4() {
        LOGGER.debug("selectElements8-4");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }    
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElements8_5() {
        LOGGER.debug("selectElements8-5");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-5.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT, 
                    false, 
                    false);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }    
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElementsWithSelectorAsConstructorArgument() {
        LOGGER.debug("selectElementsWithSelectorAsConstructorArguement");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    new SimpleElementSelector(HtmlElementStore.IMG_ELEMENT), 
                    true, 
                    true);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElementsWithSelectorAsConstructorArgumentAndNoExclusionSpecification() {
        LOGGER.debug("selectElementsWithSelectorAsConstructorArguement");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    new SimpleElementSelector(HtmlElementStore.IMG_ELEMENT));
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    public void testSelectElementsWithNoExclusionSpecification() {
        LOGGER.debug("selectElementsWithSelectorAsConstructorArguement");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        ImageElementSelector instance = 
                new ImageElementSelector(
                    HtmlElementStore.IMG_ELEMENT);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * 
     * @param selector
     * @param doc 
     */
    private void initMockContext(String selector, Document doc) {
        ssp = EasyMock.createMock(SSP.class);
        EasyMock.expect(ssp.getDOM()).andReturn("").once();
        EasyMock.replay(ssp);
        sspHandler = EasyMock.createMock(SSPHandler.class);
        EasyMock.expect(sspHandler.beginCssLikeSelection()).andReturn(sspHandler).once();
        EasyMock.expect(sspHandler.domCssLikeSelectNodeSet(selector)).andReturn(sspHandler).once();
        EasyMock.expect(sspHandler.getSelectedElements()).andReturn(doc.select(selector)).once();
        EasyMock.expect(sspHandler.getSSP()).andReturn(ssp);
        EasyMock.replay(sspHandler);
    }
    
    /**
     * 
     */
    private void verifyMockContext() {
        EasyMock.verify(ssp);
        EasyMock.verify(sspHandler);
    }
}
