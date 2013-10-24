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
package org.opens.tanaguru.rules.elementchecker.element;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import junit.framework.TestCase;
import org.easymock.EasyMock;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandlerImpl;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 *
 * @author jkowalczyk
 */
public class ElementPresenceCheckerTest extends TestCase{
    
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
     */
    public void testCheckDefaultDetectedResult() {
        System.out.println("elementPresenceChecker-check-default-detected-result");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/presenceChecker/presence-checker-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }
        ElementHandler elementHandler = new ElementHandlerImpl();
        elementHandler.addAll(doc.select(HtmlElementStore.DIV_ELEMENT));
        
        initMockContext((Elements) elementHandler.get(), TestSolution.FAILED, "detected");

        TestSolutionHandler testSolutionHandler = new TestSolutionHandlerImpl();
        
        ElementPresenceChecker instance = 
                new ElementPresenceChecker("detected", "notDetected");
        
        instance.check(mockSspHandler, elementHandler, testSolutionHandler);

        assertEquals(TestSolution.FAILED,testSolutionHandler.getTestSolution());
        
        verifyMockContext();
    }
    
    /**
     * Test of doCheck method, of class ElementPresenceChecker.
     */
    public void testCheckDefaultNotDetectedResult() {
        System.out.println("elementPresenceChecker-check-default-not-detected-result");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/presenceChecker/presence-checker-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        ElementHandler elementHandler = new ElementHandlerImpl();
        elementHandler.addAll(doc.select(HtmlElementStore.A_ELEMENT));
        
        initMockContext(TestSolution.PASSED, "notDetected");

        TestSolutionHandler testSolutionHandler = new TestSolutionHandlerImpl();
        
        ElementPresenceChecker instance = 
                new ElementPresenceChecker("detected", "notDetected");
        
        instance.check(mockSspHandler, elementHandler, testSolutionHandler);

        assertEquals(TestSolution.PASSED,testSolutionHandler.getTestSolution());
        
        verifyMockContext();
    }
    
    /**
     * Test of doCheck method, of class ElementPresenceChecker.
     */
    public void testCheckDefaultDetectedUnicityResult() {
        System.out.println("elementPresenceChecker-check-default-detected-unicity-result");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/presenceChecker/presence-checker-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        ElementHandler elementHandler = new ElementHandlerImpl();
        elementHandler.addAll(doc.select(HtmlElementStore.DIV_ELEMENT));
        
        initMockContext(TestSolution.PASSED, "notDetected");

        TestSolutionHandler testSolutionHandler = new TestSolutionHandlerImpl();
        
        ElementPresenceChecker instance = 
                new ElementPresenceChecker(true,"detected", "notDetected");
        
        instance.check(mockSspHandler, elementHandler, testSolutionHandler);

        assertEquals(TestSolution.PASSED,testSolutionHandler.getTestSolution());
        
        verifyMockContext();
    }
    
    /**
     * Test of doCheck method, of class ElementPresenceChecker.
     */
    public void testCheckDefaultDetectedUnicityFailedResult() {
        System.out.println("elementPresenceChecker-check-default-detection-unicity-failed-result");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/presenceChecker/presence-checker-unique-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        ElementHandler elementHandler = new ElementHandlerImpl();
        elementHandler.addAll(doc.select(HtmlElementStore.DIV_ELEMENT));
        
        initMockContext((Elements)elementHandler.get(), TestSolution.FAILED, "detected");

        TestSolutionHandler testSolutionHandler = new TestSolutionHandlerImpl();
        
        ElementPresenceChecker instance = 
                new ElementPresenceChecker(true,"detected", "notDetected");
        
        instance.check(mockSspHandler, elementHandler, testSolutionHandler);

        assertEquals(TestSolution.FAILED,testSolutionHandler.getTestSolution());
        
        verifyMockContext();
    }
    
    /**
     * Test of doCheck method, of class ElementPresenceChecker.
     */
    public void testCheckDefaultDetectedUnicityFailedOverrideSolutionResult1() {
        System.out.println("elementPresenceChecker-check-default-detection-unicity-failed-override-solution-result1");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/presenceChecker/presence-checker-unique-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        ElementHandler elementHandler = new ElementHandlerImpl();
        elementHandler.addAll(doc.select(HtmlElementStore.DIV_ELEMENT));
        
        initMockContext((Elements)elementHandler.get(), TestSolution.NEED_MORE_INFO, "detected");

        TestSolutionHandler testSolutionHandler = new TestSolutionHandlerImpl();
        
        ElementPresenceChecker instance = 
                new ElementPresenceChecker(
                    true,
                    TestSolution.NEED_MORE_INFO,
                    TestSolution.NOT_APPLICABLE,
                    "detected", 
                    "notDetected");
        
        instance.check(mockSspHandler, elementHandler, testSolutionHandler);

        assertEquals(TestSolution.NEED_MORE_INFO,testSolutionHandler.getTestSolution());
        
        verifyMockContext();
    }
    
    /**
     * Test of doCheck method, of class ElementPresenceChecker.
     */
    public void testCheckDefaultDetectedUnicityFailedOverrideSolutionResult2() {
        System.out.println("elementPresenceChecker-check-default-detection-unicity-failed-override-solution-result2");
        Document doc = null;
        try {
             doc = Jsoup.parse(new File("src/test/resources/presenceChecker/presence-checker-unique-1.html"), 
                     Charset.defaultCharset().displayName());
        } catch (IOException ex) {
        }

        ElementHandler elementHandler = new ElementHandlerImpl();
        elementHandler.addAll(doc.select(HtmlElementStore.A_ELEMENT));
        
        initMockContext(TestSolution.NOT_APPLICABLE, null);

        TestSolutionHandler testSolutionHandler = new TestSolutionHandlerImpl();
        
        ElementPresenceChecker instance = 
                new ElementPresenceChecker(
                    TestSolution.NEED_MORE_INFO,
                    TestSolution.NOT_APPLICABLE,
                    "detected", 
                    null);
        
        instance.check(mockSspHandler, elementHandler, testSolutionHandler);

        assertEquals(TestSolution.NOT_APPLICABLE,testSolutionHandler.getTestSolution());
        
        verifyMockContext();
    }
    
    /**
     * 
     * @param selector
     * @param doc 
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
     * @param selector
     * @param doc 
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
