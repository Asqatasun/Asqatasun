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
import org.tanaguru.rules.keystore.AttributeStore;
import org.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;
import org.tanaguru.rules.textbuilder.TextElementBuilder;
import org.tanaguru.service.ProcessRemarkService;


/**
 *
 * @author jkowalczyk
 */
public class TextNotIdenticalToAttributeCheckerTest extends TestCase {
    
    private static final Logger LOGGER = 
            Logger.getLogger(TextNotIdenticalToAttributeCheckerTest.class);
    
    private static final String DETECTION_MSG = "detected";
    
    TextElementBuilder mockTextElementBuilder;
    SSPHandler mockSSPHandler;
    Element element;
    Elements elements;
    TestSolutionHandler mockTestSolutionHandler;
    ProcessRemarkService mockProcessRemarkService;
    
    public TextNotIdenticalToAttributeCheckerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockTextElementBuilder = createMock(TextElementBuilder.class);
        mockSSPHandler = createMock(SSPHandler.class);
        elements = new Elements();
        element = new Element(Tag.valueOf("div"), "");
        element.attr(AttributeStore.ALT_ATTR, "test");
        mockTestSolutionHandler = createMock(TestSolutionHandler.class);
        mockProcessRemarkService = createMock(ProcessRemarkService.class);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of doCheck method, of class TextNotIdenticalToAttributeChecker.
     */
    public void testDoCheckWithNullTextToCheckAndDetectionResultOverridenByConstructor() {
        LOGGER.debug("doCheckWithNullTextToCheckAndDetectionResultOverridenByConstructor");
        
        /* Prepare test context */
        
        elements.add(element);

        mockTestSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
        expectLastCall().once();
        
        expect(mockTextElementBuilder.buildTextFromElement(element)).andReturn(null);
        
        TextNotIdenticalToAttributeChecker instance = 
                new TextNotIdenticalToAttributeChecker(
                    mockTextElementBuilder, 
                    new TextAttributeOfElementBuilder(AttributeStore.ALT_ATTR), 
                    new ImmutablePair(TestSolution.NOT_APPLICABLE,DETECTION_MSG),
                    new ImmutablePair(TestSolution.PASSED,"")
                );
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
     * Test of doCheck method, of class TextNotIdenticalToAttributeChecker.
     */
    public void testDoCheckWithDefaultNotDetectionResultAndDetectionResultOverridenByConstructor() {
        LOGGER.debug("doCheckWithDefaultNotDetectionResultAndDetectionResultOverridenByConstructor");
        
        /* Prepare test context */
        elements.add(element);
        
        expect(mockTextElementBuilder.buildTextFromElement(element)).andReturn("testNotIdentical");
        
        mockTestSolutionHandler.addTestSolution(TestSolution.PASSED);
        expectLastCall().once();

        TextNotIdenticalToAttributeChecker instance = 
                new TextNotIdenticalToAttributeChecker(
                    mockTextElementBuilder, 
                    new TextAttributeOfElementBuilder(AttributeStore.ALT_ATTR), 
                    new ImmutablePair(TestSolution.NEED_MORE_INFO,DETECTION_MSG),
                    new ImmutablePair(TestSolution.PASSED,"")
                );

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

}