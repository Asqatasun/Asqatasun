/*
 *  Asqatasun - Automated webpage assessment
 *  Copyright (C) 2008-2020  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
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
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.asqatasun.rules.elementchecker.element;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import junit.framework.TestCase;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.easymock.EasyMock;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.ElementHandler;
import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.ruleimplementation.TestSolutionHandlerImpl;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.service.ProcessRemarkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jkowalczyk
 */
public class ElementPresenceCheckerTest extends TestCase{
    
    private static final Logger LOGGER =
            LoggerFactory.getLogger(ElementPresenceCheckerTest.class);
    
    private SSPHandler mockSspHandler;
    private ProcessRemarkService mockProcessRemarkService;
    
    public ElementPresenceCheckerTest() {
    }

    @Override
    public void setUp() {
    }
    
    @Override
    public void tearDown() {
    }

    /**
     * Test of doCheck method, of class ElementPresenceChecker.
     * @throws java.io.IOException
     */
    public void testCheckDefaultDetectedUnicityFailedOverrideSolutionResult1() throws IOException{
        LOGGER.debug("elementPresenceChecker-check-default-detection-unicity-failed-override-solution-result1");
        
        Document doc = Jsoup.parse(new File("src/test/resources/presenceChecker/presence-checker-unique-1.html"), 
                     Charset.defaultCharset().displayName());

        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        elementHandler.addAll(doc.select(HtmlElementStore.DIV_ELEMENT));
        
        initMockContext((Elements)elementHandler.get(), TestSolution.NEED_MORE_INFO, "detected");

        TestSolutionHandler testSolutionHandler = new TestSolutionHandlerImpl();
        
        ElementPresenceChecker instance = 
                new ElementPresenceChecker(
                    true,
                    TestSolution.NEED_MORE_INFO,
                    TestSolution.NOT_APPLICABLE,
                    "detected", 
                    "notDetected", 
                    null);
        
        instance.check(mockSspHandler, elementHandler, testSolutionHandler);

        assertEquals(TestSolution.NEED_MORE_INFO,testSolutionHandler.getTestSolution());
        
        verifyMockContext();
    }
    
    /**
     * Test of doCheck method, of class ElementPresenceChecker.
     * @throws java.io.IOException
     */
    public void testCheckDefaultDetectedUnicityFailedOverrideSolutionResult2() throws IOException{
        LOGGER.debug("elementPresenceChecker-check-default-detection-unicity-failed-override-solution-result2");
        
        Document doc = Jsoup.parse(new File("src/test/resources/presenceChecker/presence-checker-unique-1.html"), 
                     Charset.defaultCharset().displayName());

        ElementHandler<Element> elementHandler = new ElementHandlerImpl();
        elementHandler.addAll(doc.select(HtmlElementStore.A_ELEMENT));
        
        initMockContext(TestSolution.NOT_APPLICABLE, null);

        TestSolutionHandler testSolutionHandler = new TestSolutionHandlerImpl();
        
        ElementPresenceChecker instance = 
                new ElementPresenceChecker(
                    new ImmutablePair(TestSolution.NEED_MORE_INFO,"detected"),
                    new ImmutablePair(TestSolution.NOT_APPLICABLE,"")
                );
        
        instance.check(mockSspHandler, elementHandler, testSolutionHandler);

        assertEquals(TestSolution.NOT_APPLICABLE,testSolutionHandler.getTestSolution());
        
        verifyMockContext();
    }
    
    /**
     * 
     * @param elements
     * @param solution
     * @param message
     */
    private void initMockContext(Elements elements, TestSolution solution, String message) {
        mockProcessRemarkService = EasyMock.createMock(ProcessRemarkService.class);
        for (Element el : elements) {
            mockProcessRemarkService.addSourceCodeRemarkOnElement(
                    solution, 
                    el, 
                    message);
            EasyMock.expectLastCall().once();
        }
        EasyMock.replay(mockProcessRemarkService);
        
        mockSspHandler = EasyMock.createMock(SSPHandler.class);
        EasyMock.expect(mockSspHandler.getProcessRemarkService()).andReturn(mockProcessRemarkService).once();
        EasyMock.replay(mockSspHandler);
    }
    
    /**
     * 
     * @param solution
     * @param message
     */
    private void initMockContext(TestSolution solution, String message) {
        mockProcessRemarkService = EasyMock.createMock(ProcessRemarkService.class);
        if (message != null) {
            mockProcessRemarkService.addProcessRemark(
                    solution, 
                    message);
            EasyMock.expectLastCall().once();
        }

        EasyMock.replay(mockProcessRemarkService);
        
        mockSspHandler = EasyMock.createMock(SSPHandler.class);
        EasyMock.expect(mockSspHandler.getProcessRemarkService()).andReturn(mockProcessRemarkService).once();
        EasyMock.replay(mockSspHandler);
    }
    
    /**
     * 
     */
    private void verifyMockContext() {
        EasyMock.verify(mockSspHandler);
        EasyMock.verify(mockProcessRemarkService);
    }

}
