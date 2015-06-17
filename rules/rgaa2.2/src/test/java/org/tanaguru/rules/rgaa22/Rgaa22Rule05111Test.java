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
import org.tanaguru.entity.audit.EvidenceElement;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;
import static org.tanaguru.rules.keystore.HtmlElementStore.TEXT_ELEMENT2;
import static org.tanaguru.rules.keystore.RemarkMessageStore.MANUAL_CHECK_ON_ELEMENTS_MSG;

/**
 * Unit test class for the implementation of the rule 5.11 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule05111Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule05111Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa22.Rgaa22Rule05111");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Rgaa22.Test.5.11-3NMI-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule05111/RGAA22.Test.5.11-3NMI-01.html"));
        getWebResourceMap().put("Rgaa22.Test.5.11-3NMI-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule05111/RGAA22.Test.5.11-3NMI-02.html"));
        getWebResourceMap().put("Rgaa22.Test.5.11-3NMI-03",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule05111/RGAA22.Test.5.11-3NMI-03.html"));
        getWebResourceMap().put("Rgaa22.Test.5.11-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule05111/RGAA22.Test.5.11-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa22.Test.5.11-3NMI-01");
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check test has no remark
        assertNotNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
         // check type of remarks and their value
        assertNotNull(processResult.getRemarkSet());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(MANUAL_CHECK_ON_ELEMENTS_MSG, processRemark.getMessageCode());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        EvidenceElement ee = processRemark.getElementList().iterator().next();
        assertEquals(TEXT_ELEMENT2, ee.getEvidence().getCode());
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.5.11-3NMI-02");
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check test has no remark
        assertNotNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
         // check type of remarks and their value
        assertNotNull(processResult.getRemarkSet());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(MANUAL_CHECK_ON_ELEMENTS_MSG, processRemark.getMessageCode());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertEquals(TEXT_ELEMENT2, ee.getEvidence().getCode());
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.5.11-3NMI-03");
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check test has no remark
        assertNotNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check number, type and value of remarks
        assertNotNull(processResult.getRemarkSet());
        assertEquals(2, processResult.getRemarkSet().size());
       
        for (ProcessRemark remark : processResult.getRemarkSet()) {
            processRemark = (SourceCodeRemark)remark;
            assertEquals(MANUAL_CHECK_ON_ELEMENTS_MSG, processRemark.getMessageCode());
            assertNotNull(processRemark.getElementList());
            assertEquals(1, processRemark.getElementList().size());
            ee = processRemark.getElementList().iterator().next();
            assertEquals(TEXT_ELEMENT2, ee.getEvidence().getCode());
        }
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa22.Test.5.11-4NA-01");
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
                consolidate("Rgaa22.Test.5.11-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa22.Test.5.11-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa22.Test.5.11-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa22.Test.5.11-4NA-01").getValue());
    }

}