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
package org.opens.tanaguru.rules.elementchecker.attribute;

import java.util.ArrayList;
import java.util.Collection;
import junit.framework.TestCase;
import static org.easymock.EasyMock.*;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.keystore.AttributeStore;
import org.opens.tanaguru.rules.keystore.EvidenceStore;
import org.opens.tanaguru.service.ProcessRemarkService;

/**
 *
 * @author jkowalczyk
 */
public class AttributePresenceCheckerTest extends TestCase{
    
    private static final String ATTR_PRESENT_MSG = "present";
    private static final String ATTR_ABSENT_MSG = "absent";
    
    SSPHandler mockSSPHandler;
    Element element;
    Elements elements;
    TestSolutionHandler mockTestSolutionHandler;
    ProcessRemarkService mockProcessRemarkService;
    
    public AttributePresenceCheckerTest() {
    }

    @Override
    public void setUp() {
        mockSSPHandler = createMock(SSPHandler.class);
        elements = new Elements();
        element = new Element(Tag.valueOf("div"), "");
        element.attr(AttributeStore.ALT_ATTR, "test");
        mockTestSolutionHandler = createMock(TestSolutionHandler.class);
        mockProcessRemarkService = createMock(ProcessRemarkService.class);
    }
    
    @Override
    public void tearDown() {
    }

    /**
     * Test of doCheck method, of class AttributePresenceChecker.
     */
    public void testDoCheckWithEmptyElements() {
        System.out.println("doCheckWithEmptyElements");
        
        /* Prepare test context */
        mockTestSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
        expectLastCall().once();
        
        AttributePresenceChecker instance = new AttributePresenceChecker(
                AttributeStore.ALT_ATTR,
                TestSolution.PASSED,
                TestSolution.FAILED,
                ATTR_PRESENT_MSG, 
                ATTR_ABSENT_MSG);
        
        replay(mockSSPHandler, mockTestSolutionHandler);

        /* test */ 
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify(mockSSPHandler, mockTestSolutionHandler);
    }
    
    /**
     * Test of doCheck method, of class AttributePresenceChecker.
     */
    public void testDoCheckWithDetectionResultOverridenByConstructor() {
        System.out.println("doCheckWithDetectionResultOverridenByConstructor");
        
        /* Prepare test context */
        elements.add(element);
        mockTestSolutionHandler.addTestSolution(TestSolution.FAILED);
        expectLastCall().once();

        mockProcessRemarkService.addSourceCodeRemarkOnElement(
                    TestSolution.FAILED,
                    element,
                    ATTR_PRESENT_MSG);
        expectLastCall().once();
        
        AttributePresenceChecker instance = new AttributePresenceChecker(
                AttributeStore.ALT_ATTR,
                TestSolution.FAILED,
                TestSolution.PASSED,
                ATTR_PRESENT_MSG, 
                ATTR_ABSENT_MSG);
        instance.setProcessRemarkService(mockProcessRemarkService);
        
        replay( 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);

        /* test */ 
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify( 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);
    }
    
    /**
     * Test of doCheck method, of class AttributePresenceChecker.
     */
    public void testDoCheckWithDetectionResultOverridenByConstructorAndProcessRemarkOnAttribute() {
        System.out.println("doCheckWithDetectionResultOverridenByConstructorAndProcessRemarkOnAttribute");
        
        /* Prepare test context */
        elements.add(element);
        mockTestSolutionHandler.addTestSolution(TestSolution.FAILED);
        expectLastCall().once();

        Collection<EvidenceElement> evidenceElementList = new ArrayList<EvidenceElement>();
        EvidenceElement ee = createMock(EvidenceElement.class);
        expect(mockProcessRemarkService.getEvidenceElement(
                EvidenceStore.TARGETTED_ELEMENT_FROM_SCOPE_EE, 
                AttributeStore.ALT_ATTR))
                    .andReturn(ee).once();

        evidenceElementList.add((ee));
        mockProcessRemarkService.addSourceCodeRemarkOnElement(
                    TestSolution.FAILED,
                    element,
                    ATTR_PRESENT_MSG, 
                    evidenceElementList);
        expectLastCall().once();
        
        AttributePresenceChecker instance = new AttributePresenceChecker(
                AttributeStore.ALT_ATTR,
                TestSolution.FAILED,
                TestSolution.PASSED,
                ATTR_PRESENT_MSG, 
                ATTR_ABSENT_MSG, 
                true);
        instance.setProcessRemarkService(mockProcessRemarkService);
        
        replay( 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);

        /* test */ 
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify( 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);
    }
    
    /**
     * Test of doCheck method, of class AttributePresenceChecker.
     */
    public void testDoCheckWithDetectionResultOverridenByConstructorAndNoMessageThrown() {
        System.out.println("doCheckWithDetectionResultOverridenByConstructorAndNoMessageThrown");
        
        /* Prepare test context */
        elements.add(element);
        mockTestSolutionHandler.addTestSolution(TestSolution.PASSED);
        expectLastCall().once();

         AttributePresenceChecker instance = new AttributePresenceChecker(
                    AttributeStore.ALT_ATTR,
                    TestSolution.PASSED,
                    TestSolution.FAILED,
                    null, 
                    ATTR_ABSENT_MSG);
        instance.setProcessRemarkService(mockProcessRemarkService);
        
        replay( 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);

        /* test */ 
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify( 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);
    }
    
    /**
     * Test of doCheck method, of class AttributePresenceChecker.
     */
    public void testDoCheckWithNotDetectionResultOverridenByConstructor() {
        System.out.println("doCheckWithNotDetectionResultOverridenByConstructor");
        
        /* Prepare test context */
        elements.add(element);
        mockTestSolutionHandler.addTestSolution(TestSolution.FAILED);
        expectLastCall().once();

        mockProcessRemarkService.addSourceCodeRemarkOnElement(
                    TestSolution.FAILED,
                    element,
                    ATTR_ABSENT_MSG);
        expectLastCall().once();
        
        AttributePresenceChecker instance = new AttributePresenceChecker(
                AttributeStore.CLASS_ATTR,
                TestSolution.PASSED,
                TestSolution.FAILED,
                ATTR_PRESENT_MSG, 
                ATTR_ABSENT_MSG);
        instance.setProcessRemarkService(mockProcessRemarkService);
        
        replay( 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);

        /* test */ 
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify( 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);
    }
    
    /**
     * Test of doCheck method, of class AttributePresenceChecker.
     */
    public void testDoCheckWithNotDetectionResultOverridenByConstructorAndNoMessageThrown() {
        System.out.println("doCheckWithNotDetectionResultOverridenByConstructorAndNoMessageThrown");
        
        /* Prepare test context */
        elements.add(element);
        mockTestSolutionHandler.addTestSolution(TestSolution.PASSED);
        expectLastCall().once();

        AttributePresenceChecker instance = new AttributePresenceChecker(
                    AttributeStore.CLASS_ATTR,
                    TestSolution.FAILED,
                    TestSolution.PASSED,
                    ATTR_PRESENT_MSG, 
                    null);
        instance.setProcessRemarkService(mockProcessRemarkService);
        
        replay( 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);

        /* test */ 
        instance.doCheck(mockSSPHandler, elements, mockTestSolutionHandler);
        
        /* verification */
        verify( 
               mockSSPHandler, 
               mockTestSolutionHandler, 
               mockProcessRemarkService);
    }
    
}