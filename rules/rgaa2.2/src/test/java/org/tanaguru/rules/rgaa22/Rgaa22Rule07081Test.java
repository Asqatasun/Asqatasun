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
package org.tanaguru.rules.rgaa22;

import java.util.LinkedHashSet;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 7.8 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule07081Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule07081Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa22.Rgaa22Rule07081");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Rgaa22.Test.7.8-1Passed-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule07081/RGAA22.Test.7.8-1Passed-01.html"));
        
        for (int i = 1; i < 10; i++) {
            getWebResourceMap().put("Rgaa22.Test.7.8-2Failed-0" + i,
                  getWebResourceFactory().createPage(
                  getTestcasesFilePath() + "rgaa22/Rgaa22Rule07081/RGAA22.Test.7.8-2Failed-0" + i +".html"));
        }
        
        for (int i = 10; i < 19; i++) {
            getWebResourceMap().put("Rgaa22.Test.7.8-2Failed-" + i,
                  getWebResourceFactory().createPage(
                  getTestcasesFilePath() + "rgaa22/Rgaa22Rule07081/RGAA22.Test.7.8-2Failed-" + i +".html"));
        }
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa22.Test.7.8-1Passed-01");
        // check test result 
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());

        //----------------------------------------------------------------------
        //------------------------------2Failed-01/09------------------------------
        //----------------------------------------------------------------------
        for (int i = 1; i < 10; i++) {
            processResult = processPageTest("Rgaa22.Test.7.8-2Failed-0" + i);
            // check test result
            assertEquals(TestSolution.FAILED, processResult.getValue());
            // check number of remarks and their value
            assertEquals(1, processResult.getRemarkSet().size());
            SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
            assertEquals(RemarkMessageStore.PRESENTATION_TAG_DETECTED_MSG, processRemark.getMessageCode());
            assertNotNull(processRemark.getSnippet());
        }
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-10/19------------------------------
        //----------------------------------------------------------------------
        for (int i = 10; i < 19; i++) {
            processResult = processPageTest("Rgaa22.Test.7.8-2Failed-" + i);
            // check test result
            assertEquals(TestSolution.FAILED, processResult.getValue());
            // check number of remarks and their value
            assertEquals(1, processResult.getRemarkSet().size());
            SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
            assertEquals(RemarkMessageStore.PRESENTATION_ATTR_DETECTED_MSG, processRemark.getMessageCode());
            assertNotNull(processRemark.getSnippet());
        }
    }
    
    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Rgaa22.Test.7.8-1Passed-01").getValue());
        for (int i = 1; i < 10; i++) {
            assertEquals(TestSolution.FAILED,
                consolidate("Rgaa22.Test.7.8-2Failed-0" + i).getValue());
        }
        
        for (int i = 10; i < 19; i++) {
            assertEquals(TestSolution.FAILED,
                consolidate("Rgaa22.Test.7.8-2Failed-" + i).getValue());
        }
    }

}