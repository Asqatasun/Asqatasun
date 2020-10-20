/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.asqatasun.rules.elementchecker.doctype;

import junit.framework.TestCase;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.select.Elements;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.TestSolutionHandler;
import org.asqatasun.ruleimplementation.TestSolutionHandlerImpl;
import static org.easymock.EasyMock.*;
import org.asqatasun.entity.audit.SSP;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.rules.keystore.RemarkMessageStore;
import org.asqatasun.service.ProcessRemarkService;

/**
 *
 * @author jkowalczyk
 */
public class DoctypeHtml5CheckerTest extends TestCase {
    
    public DoctypeHtml5CheckerTest(String testName) {
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
     * Test of doCheck method, of class DoctypeHtml5Checker.
     */
    public void testDoCheckWithHtml5Doctype() {
        System.out.println("doCheck with Html5 doctype");
        testChecker("<!doctype html>", TestSolution.PASSED, "");
        testChecker("<!DOCTYPE HTML>", TestSolution.PASSED, "");
        testChecker("<!DOCTYPE html>", TestSolution.PASSED, "");
        testChecker("<!doctype HTML>", TestSolution.PASSED, "");
        testChecker("<!DoCtYpE HtMl>", TestSolution.PASSED, "");
    }
    
    /**
     * Test of doCheck method, of class DoctypeHtml5Checker.
     */
    public void testDoCheckWithoutDoctype() {
        System.out.println("doCheck withoutDoctype");
        testChecker("", TestSolution.PASSED, "");
        testChecker("         ", TestSolution.PASSED,"");;
        testChecker(null, TestSolution.PASSED, "");
    }
    
    /**
     * Test of doCheck method, of class DoctypeHtml5Checker.
     */
    public void testDoCheckWithHtml4Doctype() {
        System.out.println("doCheck with html4 doctype");
        testChecker("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">", 
                    TestSolution.FAILED,
                    "");
        // TO DO : tokenize the doctype to deal with this kind of HTML5 doctype
        // definition
        testChecker("<!DOCTYPE   html>",
            TestSolution.FAILED,
            "");
   }
    
    /**
     * 
     * @param doctype
     * @param result
     * @param remark 
     */
    private void testChecker(String doctype, TestSolution result, String remark) {
        ProcessRemarkService processRemarkService = createMock(ProcessRemarkService.class);
        SSP ssp = createMock(SSP.class);
        SSPHandler sspHandler = createMock(SSPHandler.class);
        
        expect(sspHandler.getSSP()).andReturn(ssp);
        expect(ssp.getDoctype()).andReturn(doctype);
        
        if (StringUtils.isNotBlank(remark)) {
            processRemarkService.addProcessRemark(result, remark);
            expectLastCall();
        }
        
        Elements elements = null; // Elements not used in this implementation
        TestSolutionHandler testSolutionHandler = createMock(TestSolutionHandler.class);
        
        testSolutionHandler.addTestSolution(result);
        expectLastCall();
        
        replay(ssp, sspHandler, processRemarkService, testSolutionHandler);
        
        DoctypeHtml5Checker instance = new DoctypeHtml5Checker();
        instance.setProcessRemarkService(processRemarkService);
        
        instance.doCheck(sspHandler, elements, testSolutionHandler);
        
        verify(ssp, sspHandler, processRemarkService, testSolutionHandler);
    }
    
}
