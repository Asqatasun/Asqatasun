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

import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.SSP;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.rules.keystore.CssLikeQueryStore;

/**
 *
 * @author jkowalczyk
 */
public class SimpleElementSelectorTest extends TestCase {
    
    private SSP ssp;
    private SSPHandler sspHandler;
    
    public SimpleElementSelectorTest(String testName) {
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
     * Test of selectElements method, of class SimpleElementSelector.
     */
    public void testSelectElements() {
        System.out.println("select fieldset not within form");
        Document doc = Jsoup.parse("<form action=\"action\"><fieldset>FieldSet</fieldset></form>");

        initMockContext(CssLikeQueryStore.FIELDSET_NOT_WITHIN_FORM_CSS_LIKE_QUERY, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        SimpleElementSelector instance = 
                new SimpleElementSelector(
                    CssLikeQueryStore.FIELDSET_NOT_WITHIN_FORM_CSS_LIKE_QUERY);
        
        instance.selectElements(sspHandler, elementHandler);
        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
    }
    
    /**
     * 
     * @param selector
     * @param doc 
     */
    private void initMockContext(String selector, Document doc) {
        ssp = EasyMock.createMock(SSP.class);
        EasyMock.replay(ssp);
        sspHandler = EasyMock.createMock(SSPHandler.class);
        EasyMock.expect(sspHandler.beginCssLikeSelection()).andReturn(sspHandler).once();
        EasyMock.expect(sspHandler.domCssLikeSelectNodeSet(selector)).andReturn(sspHandler).once();
        EasyMock.expect(sspHandler.getSelectedElements()).andReturn(doc.select(selector)).once();
        EasyMock.replay(sspHandler);
    }
    
    /**
     * 
     */
    private void verifyMockContext() {
        EasyMock.verify(ssp);
        EasyMock.verify(sspHandler);
    }
    
    /**
     * Test of selectElements method, of class SimpleElementSelector.
     */
    public void testSelectWidthNotWithinSvg() {
        System.out.println("select width attr not within svg and children");
        Document doc = Jsoup.parse("<svg width=\"\" height=\"\">"
                
            +"</svg>"
            +"<svg text=\"bou\">"
                +"<text width=\"\" height=\"\" text=\"hop\"/>"
                
            +"</svg>");

        initMockContext(CssLikeQueryStore.ELEMENT_WITH_WITDH_ATTR_NOT_IMG, doc);
                        
        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        SimpleElementSelector instance = 
                new SimpleElementSelector(
                    CssLikeQueryStore.ELEMENT_WITH_WITDH_ATTR_NOT_IMG);
        
        instance.selectElements(sspHandler, elementHandler);
        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
        
        initMockContext(":not(svg)[text]:not(svg [text])", doc);
                        
        elementHandler = new ElementHandlerImpl();
        instance = new SimpleElementSelector(
                    ":not(svg)[text]:not(svg [text])");
        
        instance.selectElements(sspHandler, elementHandler);
        assertTrue(elementHandler.isEmpty());
        
        verifyMockContext();
        
    }
}
