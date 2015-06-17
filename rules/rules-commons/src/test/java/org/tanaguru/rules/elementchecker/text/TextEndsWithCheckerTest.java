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

import java.util.Arrays;
import junit.framework.TestCase;
import org.apache.log4j.Logger;
import static org.easymock.EasyMock.*;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.reference.Nomenclature;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.textbuilder.TextElementBuilder;
import org.tanaguru.service.NomenclatureLoaderService;
import org.tanaguru.service.ProcessRemarkService;


/**
 *
 * @author jkowalczyk
 */
public class TextEndsWithCheckerTest extends TestCase {
    
    private static final Logger LOGGER = 
            Logger.getLogger(TextEndsWithCheckerTest.class);
    
    private static final String EXTENSION_NOM_NAME = "extension";
    private static final String DETECTION_MSG = "detected";
    
    TextElementBuilder mockTextElementBuilder;
    SSPHandler mockSSPHandler;
    Element element;
    Elements elements;
    TestSolutionHandler mockTestSolutionHandler;
    Nomenclature mockNomenclature;
    NomenclatureLoaderService mockNomenclatureLoaderService;
    ProcessRemarkService mockProcessRemarkService;
    
    public TextEndsWithCheckerTest(String testName) {
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
        mockNomenclature = createMock(Nomenclature.class);
        mockNomenclatureLoaderService = createMock(NomenclatureLoaderService.class);
        expect(mockNomenclatureLoaderService.loadByCode(EXTENSION_NOM_NAME)).
                andReturn(mockNomenclature).once();
        mockProcessRemarkService = createMock(ProcessRemarkService.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of doCheck method, of class TextEndsWithChecker.
     */
    public void testDoCheckWithEmptyElements() {
        LOGGER.debug("doCheckWithEmptyElements");
        
        /* Prepare test context */
        TextEndsWithChecker instance = 
                new TextEndsWithChecker(
                    mockTextElementBuilder, 
                    EXTENSION_NOM_NAME, 
                    DETECTION_MSG);

        replay(mockTextElementBuilder, mockSSPHandler, mockTestSolutionHandler);
        
        /* test */
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify(mockTextElementBuilder, mockSSPHandler, mockTestSolutionHandler);
    }

    /**
     * Test of doCheck method, of class TextEndsWithChecker.
     */
    public void testDoCheckWithNullTextToCheck() {
        LOGGER.debug("doCheckWithNullTextToCheck");
        
        /* Prepare test context */
        
        elements.add(element);

        mockTestSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
        expectLastCall().once();
        
        expect(mockTextElementBuilder.buildTextFromElement(element)).andReturn(null);
        
        TextEndsWithChecker instance = 
                new TextEndsWithChecker(
                    mockTextElementBuilder, 
                    EXTENSION_NOM_NAME, 
                    DETECTION_MSG);
        replay(mockTextElementBuilder,
               mockSSPHandler, 
               mockTestSolutionHandler);
        
        /* test */
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify(mockTextElementBuilder,
               mockSSPHandler, 
               mockTestSolutionHandler);
    }
    
    /**
     * Test of doCheck method, of class TextEndsWithChecker.
     */
    public void testDoCheckWithDefaultDetectionResult() {
        LOGGER.debug("doCheckWithDefaultDetectionResult");
        
        /* Prepare test context */
        elements.add(element);

        expect(mockTextElementBuilder.buildTextFromElement(element)).andReturn("test.html");
        
        mockTestSolutionHandler.addTestSolution(TestSolution.FAILED);
        expectLastCall().once();
        expect(mockNomenclature.getValueList()).andReturn(Arrays.asList("html"));
        
        mockProcessRemarkService.addSourceCodeRemarkOnElement(
                    TestSolution.FAILED,
                    element,
                    DETECTION_MSG);
        expectLastCall().once();

        TextEndsWithChecker instance = 
                new TextEndsWithChecker(
                    mockTextElementBuilder, 
                    EXTENSION_NOM_NAME, 
                    DETECTION_MSG);
        instance.setNomenclatureLoaderService(mockNomenclatureLoaderService);
        instance.setProcessRemarkService(mockProcessRemarkService);
        
        replay(mockTextElementBuilder,
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockNomenclature, 
               mockNomenclatureLoaderService, 
               mockProcessRemarkService);
        
        /* test */
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify(mockTextElementBuilder,
               mockSSPHandler, 
               mockTestSolutionHandler,
               mockNomenclature, 
               mockNomenclatureLoaderService, 
               mockProcessRemarkService);
    }
    
    /**
     * Test of doCheck method, of class TextEndsWithChecker.
     */
    public void testDoCheckWithDefaultNotDetectionResult() {
        LOGGER.debug("doCheckWithDefaultNotDetectionResult");
        
        /* Prepare test context */
        elements.add(element);
        
        expect(mockTextElementBuilder.buildTextFromElement(element)).andReturn("test.php");
        
        mockTestSolutionHandler.addTestSolution(TestSolution.PASSED);
        expectLastCall().once();

        expect(mockNomenclature.getValueList()).andReturn(Arrays.asList("html"));
                
        TextEndsWithChecker instance = 
                new TextEndsWithChecker(
                    mockTextElementBuilder, 
                    EXTENSION_NOM_NAME, 
                    DETECTION_MSG);
        instance.setNomenclatureLoaderService(mockNomenclatureLoaderService);
        
        replay(mockTextElementBuilder,
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockNomenclature, 
               mockNomenclatureLoaderService);
        
        /* test */
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify(mockTextElementBuilder,
               mockSSPHandler, 
               mockTestSolutionHandler,
               mockNomenclature, 
               mockNomenclatureLoaderService);
    }
    
    /**
     * Test of doCheck method, of class TextEndsWithChecker.
     */
    public void testDoCheckWithDefaultDetectionResultAndDetectionResultOverridenByConstructor() {
        LOGGER.debug("doCheckWithDefaultDetectionResultAndDetectionResultOverridenByConstructor");
        
        /* Prepare test context */
        elements.add(element);

        expect(mockTextElementBuilder.buildTextFromElement(element)).andReturn("test.html");
        
        mockTestSolutionHandler.addTestSolution(TestSolution.NEED_MORE_INFO);
        expectLastCall().once();
        expect(mockNomenclature.getValueList()).andReturn(Arrays.asList("html"));
        
        mockProcessRemarkService.addSourceCodeRemarkOnElement(
                    TestSolution.NEED_MORE_INFO,
                    element,
                    DETECTION_MSG);
        expectLastCall().once();

        TextEndsWithChecker instance = 
                new TextEndsWithChecker(
                    mockTextElementBuilder, 
                    EXTENSION_NOM_NAME, 
                    TestSolution.NEED_MORE_INFO,
                    DETECTION_MSG);
        instance.setNomenclatureLoaderService(mockNomenclatureLoaderService);
        instance.setProcessRemarkService(mockProcessRemarkService);
        
        replay(mockTextElementBuilder,
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockNomenclature, 
               mockNomenclatureLoaderService, 
               mockProcessRemarkService);
        
        /* test */
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify(mockTextElementBuilder,
               mockSSPHandler, 
               mockTestSolutionHandler,
               mockNomenclature, 
               mockNomenclatureLoaderService, 
               mockProcessRemarkService);
    }
    
    /**
     * Test of doCheck method, of class TextEndsWithChecker.
     */
    public void testDoCheckWithDefaultNotDetectionResultAndDetectionResultOverridenByConstructor() {
        LOGGER.debug("doCheckWithDefaultNotDetectionResultAndDetectionResultOverridenByConstructor");
        
        /* Prepare test context */
        elements.add(element);
        
        expect(mockTextElementBuilder.buildTextFromElement(element)).andReturn("test.php");
        
        mockTestSolutionHandler.addTestSolution(TestSolution.PASSED);
        expectLastCall().once();

        expect(mockNomenclature.getValueList()).andReturn(Arrays.asList("html"));
                
        TextEndsWithChecker instance = 
                new TextEndsWithChecker(
                    mockTextElementBuilder, 
                    EXTENSION_NOM_NAME, 
                    TestSolution.NEED_MORE_INFO,
                    DETECTION_MSG);
        instance.setNomenclatureLoaderService(mockNomenclatureLoaderService);
        
        replay(mockTextElementBuilder,
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockNomenclature, 
               mockNomenclatureLoaderService);
        
        /* test */
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify(mockTextElementBuilder,
               mockSSPHandler, 
               mockTestSolutionHandler,
               mockNomenclature, 
               mockNomenclatureLoaderService);
    }

}