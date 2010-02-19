/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.accessiweb20;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rule.test.AbstractRuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class Aw20Rule10011Test extends AbstractRuleImplementationTestCase {

    public Aw20Rule10011Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        ruleImplementationClassName = "org.opens.tanaguru.rule.accessiweb20.Aw20Rule10011";
    }

    @Override
    protected void setUpWebResourceMap() {
        webResourceMap.put("AW20.Test.10.01.01-1Passed-01", 
                webResourceFactory.createPage(
                TESTCASES_FILES_PATH + "AW22/AW20.Test.10.01.01-1Passed-01.html"));
        webResourceMap.put("AW20.Test.10.01.01-2Failed-01", 
                webResourceFactory.createPage(
                TESTCASES_FILES_PATH + "AW22/AW20.Test.10.01.01-2Failed-01.html"));
        webResourceMap.put("AW20.Test.10.01.01-2Failed-02",
                webResourceFactory.createPage(
                TESTCASES_FILES_PATH + "AW22/AW20.Test.10.01.01-2Failed-02.html"));
        webResourceMap.put("AW20.Test.10.01.01-2Failed-03", 
                webResourceFactory.createPage(
                TESTCASES_FILES_PATH + "AW22/AW20.Test.10.01.01-2Failed-03.html"));
        webResourceMap.put("AW20.Test.10.01.01-2Failed-04", 
                webResourceFactory.createPage(
                TESTCASES_FILES_PATH + "AW22/AW20.Test.10.01.01-2Failed-04.html"));
        webResourceMap.put("AW20.Test.10.01.01-2Failed-05", 
                webResourceFactory.createPage(
                TESTCASES_FILES_PATH + "AW22/AW20.Test.10.01.01-2Failed-05.html"));
        webResourceMap.put("AW20.Test.10.01.01-2Failed-06", 
                webResourceFactory.createPage(
                TESTCASES_FILES_PATH + "AW22/AW20.Test.10.01.01-2Failed-06.html"));
        webResourceMap.put("AW20.Test.10.01.01-2Failed-07", 
                webResourceFactory.createPage(
                TESTCASES_FILES_PATH + "AW22/AW20.Test.10.01.01-2Failed-07.html"));
        webResourceMap.put("AW20.Test.10.01.01-2Failed-08", 
                webResourceFactory.createPage(
                TESTCASES_FILES_PATH + "AW22/AW20.Test.10.01.01-2Failed-08.html"));
        webResourceMap.put("AW20.Test.10.01.01-2Failed-09", 
                webResourceFactory.createPage(
                TESTCASES_FILES_PATH + "AW22/AW20.Test.10.01.01-2Failed-09.html"));
    }

    @Override
    protected void setProcess() {
        assertEquals(TestSolution.PASSED,
                processPageTest("AW20.Test.10.01.01-1Passed-01").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW20.Test.10.01.01-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW20.Test.10.01.01-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW20.Test.10.01.01-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW20.Test.10.01.01-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW20.Test.10.01.01-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW20.Test.10.01.01-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW20.Test.10.01.01-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW20.Test.10.01.01-2Failed-08").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW20.Test.10.01.01-2Failed-09").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("AW20.Test.10.01.01-1Passed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW20.Test.10.01.01-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW20.Test.10.01.01-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW20.Test.10.01.01-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW20.Test.10.01.01-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW20.Test.10.01.01-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW20.Test.10.01.01-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW20.Test.10.01.01-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW20.Test.10.01.01-2Failed-08").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW20.Test.10.01.01-2Failed-09").getValue());
    }

}
