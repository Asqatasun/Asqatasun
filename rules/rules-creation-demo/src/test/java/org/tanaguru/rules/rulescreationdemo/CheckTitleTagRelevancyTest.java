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
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;
import org.tanaguru.entity.audit.SourceCodeRemarkImpl;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.rulescreationdemo.test.RulescreationdemoRuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule CheckTitleTagRelevancy of 
 * the referential Rules creation demo.
 *
 * @author
 */
public class CheckTitleTagRelevancyTest extends RulescreationdemoRuleImplementationTestCase {

    private static final String INPUT_FILE_DATA_NAME = "src/test/resources/dataSets/nomenclatureFlatXmlDataSet.xml";
    
    /**
     * Default constructor
     */
    public CheckTitleTagRelevancyTest (String testName){
        super(testName, INPUT_FILE_DATA_NAME);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rulescreationdemo.CheckTitleTagRelevancy");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Rulescreationdemo.Test.4-1-1-1Passed-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rulescreationdemo/CheckTitleTagRelevancy/Rulescreationdemo.Test.4.1.1-1Passed-01.html"));
        getWebResourceMap().put("Rulescreationdemo.Test.4-1-1-2Failed-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rulescreationdemo/CheckTitleTagRelevancy/Rulescreationdemo.Test.4.1.1-2Failed-01.html"));
        getWebResourceMap().put("Rulescreationdemo.Test.4-1-1-2Failed-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rulescreationdemo/CheckTitleTagRelevancy/Rulescreationdemo.Test.4.1.1-2Failed-02.html"));
        getWebResourceMap().put("Rulescreationdemo.Test.4-1-1-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rulescreationdemo/CheckTitleTagRelevancy/Rulescreationdemo.Test.4.1.1-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
         /*
          PASSED : testcase 1 
        */
        ProcessResult pr = processPageTest("Rulescreationdemo.Test.4-1-1-1Passed-01");
        // solution is passed
        assertEquals(TestSolution.PASSED, pr.getValue());
        // 1 element of the scope has been found
        assertEquals(1, pr.getElementCounter());
        // no remark has been created
        assertNull(pr.getRemarkSet());
        
        
        /*
          FAILED : testcase 1 
        */
        pr = processPageTest("Rulescreationdemo.Test.4-1-1-2Failed-01");
        // final solution is failed
        assertEquals(TestSolution.FAILED, pr.getValue());
        // 1 element of the scope has been found
        assertEquals(1, pr.getElementCounter());
        // one remark is created 
        assertEquals(1, pr.getRemarkSet().size());
        // the remark is of SourceCodeRemark type
        assertTrue(pr.getRemarkSet().iterator().next() instanceof SourceCodeRemarkImpl);
        // the remark concerns an title element
        assertEquals("title", ((SourceCodeRemark)pr.getRemarkSet().iterator().next()).getTarget());
        // this remark handles the failed solution
        assertEquals(TestSolution.FAILED, pr.getRemarkSet().iterator().next().getIssue());
        // this remark handles the "IframeDetected" message
        assertEquals("IrrelevantTitle", pr.getRemarkSet().iterator().next().getMessageCode());
        // on evidence element is extracted to be help qualification
        assertEquals(1, pr.getRemarkSet().iterator().next().getElementList().size());
        // the evidence element is of text type
        assertEquals("text", pr.getRemarkSet().iterator().next().getElementList().iterator().next().getEvidence().getCode());
        // the value of the evidence element is "document
        assertEquals("document", pr.getRemarkSet().iterator().next().getElementList().iterator().next().getValue());
        
        
        /*
          FAILED : testcase 2 
        */
        pr = processPageTest("Rulescreationdemo.Test.4-1-1-2Failed-02");
        // final solution is failed
        assertEquals(TestSolution.FAILED, pr.getValue());
        // 1 element of the scope has been found
        assertEquals(1, pr.getElementCounter());
        // one remark is created 
        assertEquals(1, pr.getRemarkSet().size());
        // the remark is of SourceCodeRemark type
        assertTrue(pr.getRemarkSet().iterator().next() instanceof SourceCodeRemarkImpl);
        // the remark concerns an title element
        assertEquals("title", ((SourceCodeRemark)pr.getRemarkSet().iterator().next()).getTarget());
        // this remark handles the failed solution
        assertEquals(TestSolution.FAILED, pr.getRemarkSet().iterator().next().getIssue());
        // this remark handles the "IframeDetected" message
        assertEquals("IrrelevantTitle", pr.getRemarkSet().iterator().next().getMessageCode());
        // on evidence element is extracted to be help qualification
        assertEquals(1, pr.getRemarkSet().iterator().next().getElementList().size());
        // the evidence element is of text type
        assertEquals("text", pr.getRemarkSet().iterator().next().getElementList().iterator().next().getEvidence().getCode());
        // the value of the evidence element is "document
        assertEquals("UNTITLED", pr.getRemarkSet().iterator().next().getElementList().iterator().next().getValue());
        
        
        /*
          NOT_APPLICABLE : testcase 1 
        */
        pr = processPageTest("Rulescreationdemo.Test.4-1-1-4NA-01");
        // solution is passed
        assertEquals(TestSolution.NOT_APPLICABLE, pr.getValue());
        // 0 element of the scope has been found
        assertEquals(0, pr.getElementCounter());
        // no remark has been created
        assertNull(pr.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Rulescreationdemo.Test.4-1-1-1Passed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rulescreationdemo.Test.4-1-1-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rulescreationdemo.Test.4-1-1-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rulescreationdemo.Test.4-1-1-4NA-01").getValue());
    }

}