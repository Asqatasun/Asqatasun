/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.rules.rulescreationdemo;

import static junit.framework.Assert.assertEquals;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;
import org.tanaguru.entity.audit.SourceCodeRemarkImpl;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.rulescreationdemo.test.RulescreationdemoRuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 1-2-1 of the referential 
 * Rules creation demo.
 *
 * @author jkowalczyk
 */
public class DetectIframeTest extends RulescreationdemoRuleImplementationTestCase {

    /**
     * Default constructor
     */
    public DetectIframeTest (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rulescreationdemo.DetectIframe");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Rulescreationdemo.Test.1-2-1-1Passed-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rulescreationdemo/DetectIframe/Rulescreationdemo.Test.1.2.1-1Passed-01.html"));
        getWebResourceMap().put("Rulescreationdemo.Test.1-2-1-2Failed-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rulescreationdemo/DetectIframe/Rulescreationdemo.Test.1.2.1-2Failed-01.html"));
    }

    @Override
    protected void setProcess() {
        /*
          PASSED : testcase 1 
        */
        ProcessResult pr = processPageTest("Rulescreationdemo.Test.1-2-1-1Passed-01");
        // solution is passed
        assertEquals(TestSolution.PASSED, pr.getValue());
        // 0 element of the scope has been found
        assertEquals(0, pr.getElementCounter());
        // no remark has been created
        assertNull(pr.getRemarkSet());
        
        
        /*
          FAILED : testcase 1 
        */
        pr = processPageTest("Rulescreationdemo.Test.1-2-1-2Failed-01");
        // final solution is failed
        assertEquals(TestSolution.FAILED, pr.getValue());
        // 1 element of the scope has been found
        assertEquals(1, pr.getElementCounter());
        // one remark is created 
        assertEquals(1, pr.getRemarkSet().size());
        // the remark is of SourceCodeRemark type
        assertTrue(pr.getRemarkSet().iterator().next() instanceof SourceCodeRemarkImpl);
        // the remark concerns an iframe element
        assertEquals("iframe", ((SourceCodeRemark)pr.getRemarkSet().iterator().next()).getTarget());
        // this remark handles the failed solution
        assertEquals(TestSolution.FAILED, pr.getRemarkSet().iterator().next().getIssue());
        // this remark handles the "IframeDetected" message
        assertEquals("IframeDetected", pr.getRemarkSet().iterator().next().getMessageCode());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Rulescreationdemo.Test.1-2-1-1Passed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rulescreationdemo.Test.1-2-1-2Failed-01").getValue());
    }

}