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
package org.tanaguru.rules.elementchecker.text;

import junit.framework.TestCase;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.log4j.Logger;
import static org.easymock.EasyMock.*;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.textbuilder.TextElementBuilder;
import org.tanaguru.service.ProcessRemarkService;

/**
 *
 * @author jkowalczyk
 */
public class TextEmptinessCheckerTest extends TestCase {
    
    private static final Logger LOGGER = 
            Logger.getLogger(TextEmptinessCheckerTest.class);
    
    private static final String TEXT_EMPTY_MSG = "empty";
    private static final String TEXT_NOT_EMPTY_MSG = "not empty";
    
    TextElementBuilder mockTextElementBuilder;
    SSPHandler mockSSPHandler;
    Element element;
    Elements elements;
    TestSolutionHandler mockTestSolutionHandler;
    ProcessRemarkService mockProcessRemarkService;
    
    public TextEmptinessCheckerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockTextElementBuilder = createMock(TextElementBuilder.class);
        mockSSPHandler = createMock(SSPHandler.class);
        elements = new Elements();
        element = new Element(Tag.valueOf("div"), "");
        mockTestSolutionHandler = createMock(TestSolutionHandler.class);
        mockProcessRemarkService = createMock(ProcessRemarkService.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of doCheck method, of class TextEmptinessChecker.
     */
    public void testDoCheckWithEmptyElements() {
        LOGGER.debug("doCheckWithEmptyElements");
        
        /* Prepare test context */
        mockTestSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
        expectLastCall().once();
        
        TextEmptinessChecker instance = new TextEmptinessChecker(
                mockTextElementBuilder, TEXT_EMPTY_MSG, TEXT_NOT_EMPTY_MSG);
        
        replay(mockTextElementBuilder, mockSSPHandler, mockTestSolutionHandler);

        /* test */ 
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify(mockTextElementBuilder, mockSSPHandler, mockTestSolutionHandler);
    }
    
    /**
     * Test of doCheck method, of class TextEmptinessChecker.
     */
    public void testDoCheckWithNullText() {
        LOGGER.debug("doCheckWithNullText");
        
        /* Prepare test context */
        elements.add(element);
        mockTestSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
        expectLastCall().once();
        expect(mockTextElementBuilder.buildTextFromElement(element)).andReturn(null);
        
        TextEmptinessChecker instance = new TextEmptinessChecker(
                mockTextElementBuilder, TEXT_EMPTY_MSG, TEXT_NOT_EMPTY_MSG);
        
        replay(mockTextElementBuilder, mockSSPHandler, mockTestSolutionHandler);

        /* test */ 
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify(mockTextElementBuilder, mockSSPHandler, mockTestSolutionHandler);
    }
    
    /**
     * Test of doCheck method, of class TextEmptinessChecker.
     */
    public void testDoCheckWithEmptyText() {
        LOGGER.debug("doCheckWithEmptyText");
        
        /* Prepare test context */
        elements.add(element);
        mockTestSolutionHandler.addTestSolution(TestSolution.FAILED);
        expectLastCall().once();
        expect(mockTextElementBuilder.buildTextFromElement(element)).andReturn("");
        
        mockProcessRemarkService.addSourceCodeRemarkOnElement(
                    TestSolution.FAILED,
                    element,
                    TEXT_EMPTY_MSG);
        expectLastCall().once();
        
        TextEmptinessChecker instance = new TextEmptinessChecker(
                mockTextElementBuilder, TEXT_EMPTY_MSG, TEXT_NOT_EMPTY_MSG);
        instance.setProcessRemarkService(mockProcessRemarkService);
        
        replay(mockTextElementBuilder, 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);

        /* test */ 
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify(mockTextElementBuilder, 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);
    }
    
    /**
     * Test of doCheck method, of class TextEmptinessChecker.
     */
    public void testDoCheckWithNotEmptyText() {
        LOGGER.debug("doCheckWithNotEmptyText");
        
        /* Prepare test context */
        elements.add(element);
        mockTestSolutionHandler.addTestSolution(TestSolution.PASSED);
        expectLastCall().once();
        expect(mockTextElementBuilder.buildTextFromElement(element)).andReturn("something");
        
        TextEmptinessChecker instance = new TextEmptinessChecker(
                mockTextElementBuilder, TEXT_EMPTY_MSG, null);
        instance.setProcessRemarkService(mockProcessRemarkService);
        
        replay(mockTextElementBuilder, 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);

        /* test */ 
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify(mockTextElementBuilder, 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);
    }
    
    /**
     * Test of doCheck method, of class TextEmptinessChecker.
     */
    public void testDoCheckWithEmptyTextAndEmptyResultOverridenByConstructor() {
        LOGGER.debug("doCheckWithEmptyTextAndEmptyResultOverridenByConstructor");
        
        /* Prepare test context */
        elements.add(element);
        mockTestSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);
        expectLastCall().once();
        expect(mockTextElementBuilder.buildTextFromElement(element)).andReturn("");
        
        mockProcessRemarkService.addSourceCodeRemarkOnElement(
                    TestSolution.NEED_MORE_INFO,
                    element,
                    TEXT_EMPTY_MSG);
        expectLastCall().once();
        
        TextEmptinessChecker instance = new TextEmptinessChecker(
                    mockTextElementBuilder, 
                    new ImmutablePair(TestSolution.NEED_MORE_INFO, TEXT_EMPTY_MSG),
                    new ImmutablePair(TestSolution.PASSED, TEXT_NOT_EMPTY_MSG));
        instance.setProcessRemarkService(mockProcessRemarkService);
        
        replay(mockTextElementBuilder, 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);

        /* test */ 
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify(mockTextElementBuilder, 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);
    }
    
    /**
     * Test of doCheck method, of class TextEmptinessChecker.
     */
    public void testDoCheckWithNotEmptyTextAndNotEmptyResultOverridenByConstructor() {
        LOGGER.debug("doCheckWithNotEmptyTextAndNotEmptyResultOverridenByConstructor");
        
        /* Prepare test context */
        elements.add(element);
        mockTestSolutionHandler.addTestSolution(TestSolution.FAILED);
        expectLastCall().once();
        expect(mockTextElementBuilder.buildTextFromElement(element)).andReturn("something");
        mockProcessRemarkService.addSourceCodeRemarkOnElement(
                    TestSolution.FAILED,
                    element,
                    TEXT_NOT_EMPTY_MSG);
        expectLastCall().once();
        
        TextEmptinessChecker instance = new TextEmptinessChecker(
                    mockTextElementBuilder, 
                    new ImmutablePair(TestSolution.PASSED, TEXT_EMPTY_MSG),
                    new ImmutablePair(TestSolution.FAILED, TEXT_NOT_EMPTY_MSG)
        );
        instance.setProcessRemarkService(mockProcessRemarkService);
        
        replay(mockTextElementBuilder, 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);

        /* test */ 
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify(mockTextElementBuilder, 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);
    }

}