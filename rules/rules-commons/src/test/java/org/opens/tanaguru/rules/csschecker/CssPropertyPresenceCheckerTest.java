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
package org.opens.tanaguru.rules.csschecker;

import com.phloc.css.ECSSVersion;
import com.phloc.css.decl.CascadingStyleSheet;
import com.phloc.css.reader.CSSReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import junit.framework.TestCase;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.easymock.EasyMock;
import static org.easymock.EasyMock.*;
import org.opens.tanaguru.contentadapter.css.CSSParserExceptionHandlerImpl;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.StylesheetContent;
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
public class CssPropertyPresenceCheckerTest extends TestCase {
    
    private TestSolutionHandler mockTestSolutionHandler;
    private SSPHandler mockSSPHandler;
    private ProcessRemarkService mockProcessRemarkService;
    private StylesheetContent stylesheetContent;
    private String contentAttributeDectected = "mockDetectionMessage";
    
    public CssPropertyPresenceCheckerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mockTestSolutionHandler = createMock(TestSolutionHandler.class);
        mockProcessRemarkService = createMock(ProcessRemarkService.class);
        stylesheetContent = createMock(StylesheetContent.class);
        mockProcessRemarkService = EasyMock.createMock(ProcessRemarkService.class);

        mockSSPHandler = EasyMock.createMock(SSPHandler.class);
        EasyMock.expect(mockSSPHandler.getProcessRemarkService()).andReturn(mockProcessRemarkService).once();
        EasyMock.replay(mockSSPHandler);
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of check method, of class CssPropertyPresenceChecker.
     */
    public void testCheckButNotFound() {
        System.out.println("check but not found");
        initCheckerAndLaunch("src/test/resources/css/test1.css", null, TestSolution.NOT_APPLICABLE, null);
    }
    
    /**
     * Test of check method, of class CssPropertyPresenceChecker.
     */
    public void testCheckFoundButEmpty() {
        System.out.println("check found but empty");
        initCheckerAndLaunch("src/test/resources/css/test2.css", null,TestSolution.PASSED, null);
    }
    
    /**
     * Test of check method, of class CssPropertyPresenceChecker.
     */
    public void testCheckFoundWithResultAsNMI() {
        System.out.println("check found with result as nmi");
        initCheckerAndLaunch("src/test/resources/css/test3.css", null,TestSolution.NEED_MORE_INFO, ".selector");
    }
    
    /**
     * Test of check method, of class CssPropertyPresenceChecker.
     */
    public void testCheckFoundWithResultAsFailed() {
        System.out.println("check found with result as failed");
        initCheckerAndLaunch("src/test/resources/css/test3.css", null,TestSolution.FAILED, ".selector");
    }
    
    /**
     * Test of check method, of class CssPropertyPresenceChecker.
     */
    public void testCheckNotFoundWithPseudoSelectorAndWithResultAsNMI() {
        System.out.println("check not found with pseudoSelector");
        String[] pseudoSelectors = {":before", ":after"};
        initCheckerAndLaunch("src/test/resources/css/test3.css", pseudoSelectors, TestSolution.NOT_APPLICABLE, null);
    }
    
    /**
     * Test of check method, of class CssPropertyPresenceChecker.
     */
    public void testCheckFoundWithPseudoSelectorAndWithResultAsNMI() {
        System.out.println("check found with pseudoSelector result as nmi");
        String[] pseudoSelectors = {":before", ":after"};
        initCheckerAndLaunch("src/test/resources/css/test4.css", pseudoSelectors, TestSolution.NEED_MORE_INFO, ".selector:before");
    }
    
    /**
     * Test of check method, of class CssPropertyPresenceChecker.
     */
    public void testCheckFoundWithPseudoSelectorAndWithResultAsFailed() {
        System.out.println("check found with pseudoSelector and result as failed");
        String[] pseudoSelectors = {":before", ":after"};
        initCheckerAndLaunch("src/test/resources/css/test5.css", pseudoSelectors, TestSolution.FAILED, ".selector:after");
    }
    
    /**
     * 
     * @param fileName
     * @param resultOnDetection 
     */
    private void initCheckerAndLaunch(
            String fileName, 
            String[] pseudoSelectors,
            TestSolution resultOnDetection, 
            String selector) {
        try {
            String styleSheetName = fileName.substring(fileName.lastIndexOf("/")+1);

            expect(mockTestSolutionHandler.getTestSolution()).andReturn(resultOnDetection).times(2);
            mockTestSolutionHandler.addTestSolution(resultOnDetection);
            expectLastCall().anyTimes();
            
            if (StringUtils.isNotBlank(selector)) {
                EvidenceElement eElement1 = createMock(EvidenceElement.class);
                EvidenceElement eElement2 = createMock(EvidenceElement.class);
                expect(mockProcessRemarkService.getEvidenceElement(EvidenceStore.CSS_SELECTOR_EE, selector)).andReturn(eElement1);
                expect(mockProcessRemarkService.getEvidenceElement(EvidenceStore.CSS_FILENAME_EE, styleSheetName)).andReturn(eElement2);
                Collection<EvidenceElement> eeList = new ArrayList<EvidenceElement>();
                eeList.add(eElement1);
                eeList.add(eElement2);
                mockProcessRemarkService.addSourceCodeRemark(
                        resultOnDetection, 
                        "content attribute not empty", 
                        contentAttributeDectected, 
                        eElement1, 
                        eElement2);
                expectLastCall().once();
                EasyMock.replay(mockProcessRemarkService);   
            }
            EasyMock.replay(mockTestSolutionHandler);
            
            CascadingStyleSheet cascadingStyleSheet = CSSReader.readFromString (
                            FileUtils.readFileToString(new File(fileName)), 
                            Charset.defaultCharset(), 
                            ECSSVersion.CSS30,
                            new CSSParserExceptionHandlerImpl(stylesheetContent));
            
            Collection<String> pseudoSelectorList;
            if (pseudoSelectors == null) {
                pseudoSelectorList = new ArrayList<String>();
            } else {
                pseudoSelectorList = Arrays.asList(pseudoSelectors);
            }
            
            CssPropertyPresenceChecker instance = 
                    new CssPropertyPresenceChecker(
                        AttributeStore.CONTENT_ATTR, 
                        pseudoSelectorList,
                        resultOnDetection, 
                        contentAttributeDectected);
                        
            
            instance.check(
                    mockSSPHandler,
                    styleSheetName, 
                    cascadingStyleSheet, 
                    mockTestSolutionHandler);
            
        } catch (IOException ex) {
            Logger.getLogger(CssPropertyPresenceCheckerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
