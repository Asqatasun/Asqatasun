/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opens.tanaguru.rule.accessiweb11;

import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.entity.subject.Page;
import org.opens.tanaguru.entity.subject.Site;
import org.opens.tanaguru.rule.test.AbstractRuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class Rule1203cTest extends AbstractRuleImplementationTestCase {

    public Rule1203cTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        ruleImplementationClassName = "org.opens.tanaguru.rule.accessiweb11.Rule1203c";
    }

    @Override
    protected void setUpWebResourceMap() {
        Site site = webResourceFactory.createSite("file:Site-Passed1");
        webResourceMap.put("AW11.Test.12.03.a-1Passed-01", site);

        Page page = webResourceFactory.createPage("file:///home/jkowalczyk/Documents/Sources/Test Cases/Testcases repository/AW11/AW11.Test.12.03.a-1Passed-01-page01.html");
        site.addChild(page);
        webResourceMap.put("AW11.Test.12.03.a-1Passed-01-page01", page);

        page = webResourceFactory.createPage("file:///home/jkowalczyk/Documents/Sources/Test Cases/Testcases repository/AW11/AW11.Test.12.03.a-1Passed-01-page02.html");
        site.addChild(page);
        webResourceMap.put("AW11.Test.12.03.a-1Passed-01-page02", page);

        site = webResourceFactory.createSite("file:Site-Failed1");
        webResourceMap.put("AW11.Test.12.03.a-2Failed-01", site);
        
        page = webResourceFactory.createPage("file:///home/jkowalczyk/Documents/Sources/Test Cases/Testcases repository/AW11/AW11.Test.12.03.a-2Failed-01-page01.html");
        site.addChild(page);
        webResourceMap.put("AW11.Test.12.03.a-2Failed-01-page01", page);

        page = webResourceFactory.createPage("file:///home/jkowalczyk/Documents/Sources/Test Cases/Testcases repository/AW11/AW11.Test.12.03.a-2Failed-01-page02.html");
        site.addChild(page);
        webResourceMap.put("AW11.Test.12.03.a-2Failed-01-page02", page);

        site = webResourceFactory.createSite("file:Site-Failed2");
        webResourceMap.put("AW11.Test.12.03.a-2Failed-02", site);

        page = webResourceFactory.createPage("file:///home/jkowalczyk/Documents/Sources/Test Cases/Testcases repository/AW11/AW11.Test.12.03.a-2Failed-02-page01.html");
        site.addChild(page);
        webResourceMap.put("AW11.Test.12.03.a-2Failed-02-page01", page);

        page = webResourceFactory.createPage("file:///home/jkowalczyk/Documents/Sources/Test Cases/Testcases repository/AW11/AW11.Test.12.03.a-2Failed-02-page02.html");
        site.addChild(page);
        webResourceMap.put("AW11.Test.12.03.a-2Failed-02-page02", page);
    }

    @Override
    protected void setProcess() {
        process("AW11.Test.12.03.a-1Passed-01");
        assertEquals("3:#testcase-sitemap\n", getGrossResult("AW11.Test.12.03.a-1Passed-01-page01", "AW11.Test.12.03.a-1Passed-01").getValue());
        assertEquals("3:#testcase-sitemap\n", getGrossResult("AW11.Test.12.03.a-1Passed-01-page02", "AW11.Test.12.03.a-1Passed-01").getValue());
        process("AW11.Test.12.03.a-2Failed-01");
        assertEquals("3:#testcase-sitemap\n4:#testcase-search-engine\n", getGrossResult("AW11.Test.12.03.a-2Failed-01-page01", "AW11.Test.12.03.a-2Failed-01").getValue());
        assertEquals("3:#testcase-sitemap\n", getGrossResult("AW11.Test.12.03.a-2Failed-01-page02", "AW11.Test.12.03.a-2Failed-01").getValue());
        process("AW11.Test.12.03.a-2Failed-02");
        assertEquals("4:#testcase-search-engine\n", getGrossResult("AW11.Test.12.03.a-2Failed-02-page01", "AW11.Test.12.03.a-2Failed-02").getValue());
        assertEquals("5:#testcase-search-engine\n", getGrossResult("AW11.Test.12.03.a-2Failed-02-page02", "AW11.Test.12.03.a-2Failed-02").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED, consolidate("AW11.Test.12.03.a-1Passed-01").getValue());
//        assertEquals(TestSolution.FAILED, consolidate("AW11.Test.12.03.a-2Failed-01").getValue());
//        assertEquals(TestSolution.FAILED, consolidate("AW11.Test.12.03.a-2Failed-02").getValue());
    }
}