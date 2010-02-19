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
public class Aw20Rule08061Test extends AbstractRuleImplementationTestCase {

    public Aw20Rule08061Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        ruleImplementationClassName =
                "org.opens.tanaguru.rule.accessiweb20.Aw20Rule08061";
    }

    @Override
    protected void setUpWebResourceMap() {
        webResourceMap.put("AW20.Test.08.06.01-2Failed-01",
                webResourceFactory.createPage(
                "file:///home/jkowalczyk/Documents/Sources/TanaguruEngine/Trunk/tanaguru-testing-tools/resources/testcases/AW22/AW20.Test.08.06.01-2Failed-01.html"));

        webResourceMap.put("AW20.Test.08.06.01-2Failed-02",
                webResourceFactory.createPage(
                "file:///home/jkowalczyk/Documents/Sources/TanaguruEngine/Trunk/tanaguru-testing-tools/resources/testcases/AW22/AW20.Test.08.06.01-2Failed-02.html"));

        webResourceMap.put("AW20.Test.08.06.01-2Failed-03",
                webResourceFactory.createPage(
                "file:///home/jkowalczyk/Documents/Sources/TanaguruEngine/Trunk/tanaguru-testing-tools/resources/testcases/AW22/AW20.Test.08.06.01-2Failed-03.html"));

        webResourceMap.put("AW20.Test.08.06.01-3NMI-01",
                webResourceFactory.createPage(
                "file:///home/jkowalczyk/Documents/Sources/TanaguruEngine/Trunk/tanaguru-testing-tools/resources/testcases/AW22/AW20.Test.08.06.01-3NMI-01.html"));

        webResourceMap.put("AW20.Test.08.06.01-4NA-01",
                webResourceFactory.createPage(
                "file:///home/jkowalczyk/Documents/Sources/TanaguruEngine/Trunk/tanaguru-testing-tools/resources/testcases/AW22/AW20.Test.08.06.01-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        assertEquals(TestSolution.FAILED,
                processPageTest("AW20.Test.08.06.01-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW20.Test.08.06.01-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                processPageTest("AW20.Test.08.06.01-2Failed-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                processPageTest("AW20.Test.08.06.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("AW20.Test.08.06.01-4NA-01").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.FAILED,
                consolidate("AW20.Test.08.06.01-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW20.Test.08.06.01-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW20.Test.08.06.01-2Failed-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW20.Test.08.06.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW20.Test.08.06.01-4NA-01").getValue());
    }
}
