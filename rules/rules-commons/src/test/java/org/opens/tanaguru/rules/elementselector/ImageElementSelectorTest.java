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
package org.opens.tanaguru.rules.elementselector;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.*;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;

/**
 *
 * @author jkowalczyk
 */
public class ImageElementSelectorTest extends TestCase{
    
    private SSP ssp;
    private SSPHandler sspHandler;
    
    public ImageElementSelectorTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    @Override
    public void setUp() {
    }
    
    @After
    @Override
    public void tearDown() {
    }

    /**
     * Test of selectElements method, of class ImageElementSelector.
     */
    @Test
    public void testSelectElements1_1() {
        System.out.println("selectElements1-1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements1_2() {
        System.out.println("selectElements1-2");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-2.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements1_3() {
        System.out.println("selectElements1-3");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-3.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements1_4() {
        System.out.println("selectElements1-4");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements2_1() {
        System.out.println("selectElements2-1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements2_2() {
        System.out.println("selectElements2-2");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-2.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements2_3() {
        System.out.println("selectElements2-3");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-3.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements2_4() {
        System.out.println("selectElements2-4");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements3_1() {
        System.out.println("selectElements3-1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements3_2() {
        System.out.println("selectElements3-2");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-2.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements3_3() {
        System.out.println("selectElements3-3");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-3.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements3_4() {
        System.out.println("selectElements3-4");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements4_1() {
        System.out.println("selectElements4-1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements4_2() {
        System.out.println("selectElements4-2");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-2.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements4_3() {
        System.out.println("selectElements4-3");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-3.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements4_4() {
        System.out.println("selectElements4-4");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements5_1() {
        System.out.println("selectElements5-1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements5_2() {
        System.out.println("selectElements5-2");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-2.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements5_3() {
        System.out.println("selectElements5-3");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-3.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements5_4() {
        System.out.println("selectElements5-4");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements5_5() {
        System.out.println("selectElements5-5");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-5.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements6_1() {
        System.out.println("selectElements6-1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements6_2() {
        System.out.println("selectElements6-2");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-2.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements6_3() {
        System.out.println("selectElements6-3");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-3.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements6_4() {
        System.out.println("selectElements6-4");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements6_5() {
        System.out.println("selectElements6-5");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-5.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements7_1() {
        System.out.println("selectElements7-1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements7_2() {
        System.out.println("selectElements7-2");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-2.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements7_3() {
        System.out.println("selectElements7-3");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-3.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements7_4() {
        System.out.println("selectElements7-4");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements7_5() {
        System.out.println("selectElements7-5");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements8_1() {
        System.out.println("selectElements8-1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements8_2() {
        System.out.println("selectElements8-2");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-2.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements8_3() {
        System.out.println("selectElements8-3");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-3.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements8_4() {
        System.out.println("selectElements8-4");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElements8_5() {
        System.out.println("selectElements8-5");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-composite-5.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        
        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElementsWithSelectorAsConstructorArgument() {
        System.out.println("selectElementsWithSelectorAsConstructorArguement");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElementsWithSelectorAsConstructorArgumentAndNoExclusionSpecification() {
        System.out.println("selectElementsWithSelectorAsConstructorArguement");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
    @Test
    public void testSelectElementsWithNoExclusionSpecification() {
        System.out.println("selectElementsWithSelectorAsConstructorArguement");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/images/image-link-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler elementHandler = new ElementHandlerImpl();
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
