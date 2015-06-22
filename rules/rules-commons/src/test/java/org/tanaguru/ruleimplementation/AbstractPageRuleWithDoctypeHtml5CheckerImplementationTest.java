/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tanaguru.ruleimplementation;

import junit.framework.TestCase;
import org.tanaguru.rules.elementchecker.doctype.DoctypeHtml5Checker;

/**
 *
 * @author jkowalczyk
 */
public class AbstractPageRuleWithDoctypeHtml5CheckerImplementationTest extends TestCase {
    
    public AbstractPageRuleWithDoctypeHtml5CheckerImplementationTest(String testName) {
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

    public void testElementCheckerIsDoctypeHtml5CheckerAndElementCheckerIsNull() {
        
        AbstractPageRuleWithDoctypeHtml5CheckerImplementation instance = 
                new MyConcreteClassToTest();
        
        assertTrue(instance.getElementChecker() instanceof DoctypeHtml5Checker);
        assertNull(instance.getElementSelector());
    }
    
    /**
     * Inner class that represents a concrete implementation of the 
     * AbstractPageRuleWithDoctypeHtml5CheckerImplementationTest abstract class
     */
    private class MyConcreteClassToTest extends AbstractPageRuleWithDoctypeHtml5CheckerImplementation {

        public MyConcreteClassToTest() {
            super();
        }
        
        
    }
    
}
