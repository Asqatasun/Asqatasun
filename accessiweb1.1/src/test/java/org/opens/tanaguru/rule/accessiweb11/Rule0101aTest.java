/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.accessiweb11;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rule.test.AbstractRuleImplementationTestCase;

/**
 *
 * @author lralambomanana
 */
public class Rule0101aTest extends AbstractRuleImplementationTestCase {

    public Rule0101aTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        ruleImplementationClassName = "org.opens.tanaguru.rule.accessiweb11.Rule0101a";
    }

    @Override
    protected void setUpWebResourceMap() {
        webResourceMap.put("http://www.open-s.com/index.php", webResourceFactory.createPage("http://www.open-s.com/index.php"));
    }

    @Override
    protected void setProcess() {
        assertEquals(TestSolution.PASSED, processPageTest("http://www.open-s.com/index.php").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED, consolidate("http://www.open-s.com/index.php").getValue());
    }
}
