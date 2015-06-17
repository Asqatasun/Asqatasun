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
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;
import org.tanaguru.entity.audit.TestSolution;
import static org.tanaguru.rules.keystore.RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG;
import org.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 5.29 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule05291Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule05291Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa22.Rgaa22Rule05291");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("RGAA22.Test.5.29-3NMI-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule05291/RGAA22.Test.5.29-3NMI-01.html"));
        getWebResourceMap().put("RGAA22.Test.5.29-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule05291/RGAA22.Test.5.29-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("RGAA22.Test.5.29-3NMI-01");
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check test has no remark
        assertNotNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(3, processResult.getElementCounter());
         // check type of remarks and their value
        assertNotNull(processResult.getRemarkSet());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(MANUAL_CHECK_ON_ELEMENTS_MSG, processRemark.getMessageCode());
        // check number, type and value of remarks
        assertNotNull(processResult.getRemarkSet());
        assertEquals(3, processResult.getRemarkSet().size());
       
        for (ProcessRemark remark : processResult.getRemarkSet()) {
            processRemark = (SourceCodeRemark)remark;
            assertEquals(MANUAL_CHECK_ON_ELEMENTS_MSG, processRemark.getMessageCode());
        }
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.5.29-4NA-01");
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.5.29-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.5.29-4NA-01").getValue());
    }

}