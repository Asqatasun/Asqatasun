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
public class CaptchaElementSelectorTest extends TestCase{
    
    private static final Logger LOGGER = 
            Logger.getLogger(CaptchaElementSelectorTest.class);
    
    private SSP ssp;
    private SSPHandler sspHandler;
    
    public CaptchaElementSelectorTest() {
    }

    @Override
    public void setUp() {
    }
    
    @Override
    public void tearDown() {
    }

    /**
     * Test of selectElements method, of class CaptchaElementSelector.
     * @throws java.io.IOException
     */
    public void testSelectElementsAttrElement() throws IOException{
        LOGGER.debug("selectElementsAttrElement");
        Document doc = Jsoup.parse(new File("src/test/resources/captcha/captcha1.html"), 
                     Charset.defaultCharset().displayName());
        initMockContext(doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        elementHandler.addAll(doc.select(HtmlElementStore.IMG_ELEMENT));
        CaptchaElementSelector instance = 
                new CaptchaElementSelector(elementHandler);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(
                HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class CaptchaElementSelector.
     * @throws java.io.IOException
     */
    public void testSelectElementsTextSibling1() throws IOException{
        LOGGER.debug("selectElementsTextSibling1");
        Document doc = Jsoup.parse(new File("src/test/resources/captcha/captcha2.html"), 
                     Charset.defaultCharset().displayName());

        initMockContext(doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        elementHandler.addAll(doc.select(HtmlElementStore.IMG_ELEMENT));
        CaptchaElementSelector instance = 
                new CaptchaElementSelector(elementHandler);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(
                HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class CaptchaElementSelector.
     * @throws java.io.IOException
     */
    public void testSelectElementsTextSibling2() throws IOException{
        LOGGER.debug("selectElementsTextSibling2");
        Document doc = Jsoup.parse(new File("src/test/resources/captcha/captcha3.html"), 
                     Charset.defaultCharset().displayName());

        initMockContext(doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        elementHandler.addAll(doc.select(HtmlElementStore.IMG_ELEMENT));
        CaptchaElementSelector instance = 
                new CaptchaElementSelector(elementHandler);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(
                HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class CaptchaElementSelector.
     */
    public void testSelectElementsTextParent() {
        LOGGER.debug("selectElementsTextParent");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/captcha/captcha4.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        CaptchaElementSelector instance = 
                new CaptchaElementSelector(
                    new SimpleElementSelector(HtmlElementStore.IMG_ELEMENT));
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(
                HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class CaptchaElementSelector.
     */
    public void testSelectElementsAttrParent() {
        LOGGER.debug("selectElementsAttrParent");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/captcha/captcha5.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        CaptchaElementSelector instance = 
                new CaptchaElementSelector(
                    new SimpleElementSelector(HtmlElementStore.IMG_ELEMENT));
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.get().size() == 1);
        assertTrue(elementHandler.get().iterator().next().nodeName().equals(
                HtmlElementStore.IMG_ELEMENT));
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class CaptchaElementSelector.
     */
    public void testNoCaptcha1() {
        LOGGER.debug("noCaptcha1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/captcha/no-captcha1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        initMockContext(HtmlElementStore.IMG_ELEMENT, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        CaptchaElementSelector instance = 
                new CaptchaElementSelector(
                    new SimpleElementSelector(HtmlElementStore.IMG_ELEMENT));
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class CaptchaElementSelector.
     */
    public void testNoCaptcha2() {
        LOGGER.debug("noCaptcha1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/captcha/no-captcha2.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        initMockContext(null, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        CaptchaElementSelector instance = 
                new CaptchaElementSelector(
                    new SimpleElementSelector(HtmlElementStore.IMG_ELEMENT));
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }
    
    /**
     * Test of selectElements method, of class CaptchaElementSelector.
     */
    public void testEmptyPreSelection() {
        LOGGER.debug("EmptyPreSelection");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/captcha/captcha1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        initMockContext(null, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        CaptchaElementSelector instance = 
                new CaptchaElementSelector(elementHandler);
        instance.selectElements(sspHandler, elementHandler);

        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }

    /**
     * 
     * @param selector
     * @param doc 
     */
    private void initMockContext(Document doc) {
        ssp = EasyMock.createMock(SSP.class);
        EasyMock.expect(ssp.getDOM()).andReturn(doc.outerHtml()).once();
        EasyMock.replay(ssp);
        sspHandler = EasyMock.createMock(SSPHandler.class);
        EasyMock.expect(sspHandler.getSSP()).andReturn(ssp);
        EasyMock.replay(sspHandler);
    }
    
    /**
     * 
     * @param selector
     * @param doc 
     */
    private void initMockContext(String selector, Document doc) {
        ssp = EasyMock.createMock(SSP.class);
        EasyMock.expect(ssp.getDOM()).andReturn(doc.outerHtml()).once();
        EasyMock.replay(ssp);
        sspHandler = EasyMock.createMock(SSPHandler.class);
        if (selector != null) {
            EasyMock.expect(sspHandler.beginCssLikeSelection()).andReturn(sspHandler).once();
            EasyMock.expect(sspHandler.domCssLikeSelectNodeSet(selector)).andReturn(sspHandler).once();
            EasyMock.expect(sspHandler.getSelectedElements()).andReturn(doc.select(selector)).once();
        }
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
