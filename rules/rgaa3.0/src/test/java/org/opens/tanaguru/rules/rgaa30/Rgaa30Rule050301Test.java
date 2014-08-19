/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2014  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.rules.rgaa30;

import java.util.LinkedHashSet;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.SourceCodeRemark;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 05.03.01 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule050301Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa30Rule050301Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.opens.tanaguru.rules.rgaa30.Rgaa30Rule050301");
    }

    @Override
    protected void setUpWebResourceMap() {
//        getWebResourceMap().put("Rgaa30.Test.05.03.01-3NMI-01",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "rgaa30/Rgaa30Rule050301/Rgaa30.Test.05.03.01-3NMI-01.html"));
//        addParameterToParameterMap("Rgaa30.Test.05.03.01-3NMI-01", createParameter("Rules", "PRESENTATION_TABLE_MARKER", "class-presentation-table"));
//        getWebResourceMap().put("Rgaa30.Test.05.03.01-3NMI-02",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "rgaa30/Rgaa30Rule050301/Rgaa30.Test.05.03.01-3NMI-02.html"));
        getWebResourceMap().put("Rgaa30.Test.05.03.01-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa30/Rgaa30Rule050301/Rgaa30.Test.05.03.01-4NA-01.html"));
//        getWebResourceMap().put("Rgaa30.Test.05.03.01-4NA-02",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "rgaa30/Rgaa30Rule050301/Rgaa30.Test.05.03.01-4NA-02.html"));
//        addParameterToParameterMap("Rgaa30.Test.05.03.01-4NA-02", createParameter("Rules", "DATA_TABLE_MARKER", "id-data-table"));
    }

    @Override
    protected void setProcess() {
//        //----------------------------------------------------------------------
//        //------------------------------3NMI-01--------------------------------
//        //----------------------------------------------------------------------
//        ProcessResult processResult = processPageTest("Rgaa30.Test.05.03.01-3NMI-01");
//        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
//        // check test result
//        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
//        // check number of remarks and their value
//        assertEquals(1, processResult.getRemarkSet().size());
//        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
//        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
//        assertEquals(RemarkMessageStore.CHECK_LINEARISED_CONTENT_MSG, processRemark.getMessageCode());
//        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
//        assertNotNull(processRemark.getSnippet());
//        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
//        
//        //----------------------------------------------------------------------
//        //------------------------------3NMI-02--------------------------------
//        //----------------------------------------------------------------------
//        processResult = processPageTest("Rgaa30.Test.05.03.01-3NMI-02");
//        // check number of elements in the page
//        assertEquals(1, processResult.getElementCounter());
//        // check test result
//        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
//        // check number of remarks and their value
//        assertEquals(1, processResult.getRemarkSet().size());
//        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
//        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
//        assertEquals(RemarkMessageStore.CHECK_NATURE_OF_TABLE_AND_LINEARISED_CONTENT_MSG, processRemark.getMessageCode());
//        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
//        assertNotNull(processRemark.getSnippet());
//        // check number of evidence elements and their value
//        assertNull(processRemark.getElementList());
//        

        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.05.03.01-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
//
//        //----------------------------------------------------------------------
//        //------------------------------4NA-02------------------------------
//        //----------------------------------------------------------------------
//        processResult = processPageTest("Rgaa30.Test.05.03.01-4NA-02");
//        // check test result
//        assertEquals(TestSolution.NOT_TESTED, processResult.getValue());
//        // check test has no remark
//        assertNull(processResult.getRemarkSet());
//        // check number of elements in the page
//        assertEquals(0, processResult.getElementCounter());
    }

    @Override
    protected void setConsolidate() {
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("Rgaa30.Test.05.03.01-3NMI-01").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("Rgaa30.Test.05.03.01-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa30.Test.05.03.01-4NA-01").getValue());
//        assertEquals(TestSolution.NOT_TESTED,
//                consolidate("Rgaa30.Test.05.03.01-4NA-02").getValue());
    }

}
