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

import java.util.Iterator;
import java.util.LinkedHashSet;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.SourceCodeRemark;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 11.1 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule11011Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule11011Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa22.Rgaa22Rule11011");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("RGAA22.Test.11.1-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11011/RGAA22.Test.11.1-2Failed-01.html"));
        addParameterToParameterMap("RGAA22.Test.11.1-2Failed-01", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));
        getWebResourceMap().put("RGAA22.Test.11.1-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11011/RGAA22.Test.11.1-3NMI-01.html"));
        getWebResourceMap().put("RGAA22.Test.11.1-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11011/RGAA22.Test.11.1-3NMI-02.html"));
        addParameterToParameterMap("RGAA22.Test.11.1-3NMI-02", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));
        getWebResourceMap().put("RGAA22.Test.11.1-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule11011/RGAA22.Test.11.1-3NMI-03.html"));
        addParameterToParameterMap("RGAA22.Test.11.1-3NMI-03", createParameter("Rules", "DATA_TABLE_MARKER", "class-data-table"));
        getWebResourceMap().put("RGAA22.Test.11.1-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule11011/RGAA22.Test.11.1-4NA-01.html"));
        getWebResourceMap().put("RGAA22.Test.11.1-4NA-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "rgaa22/Rgaa22Rule11011/RGAA22.Test.11.1-4NA-02.html"));
        addParameterToParameterMap("RGAA22.Test.11.1-4NA-02", createParameter("Rules", "PRESENTATION_TABLE_MARKER", "id-presentation-table"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //----------------------------2Failed-01--------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("RGAA22.Test.11.1-2Failed-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.FAILED, processRemark.getIssue());
        assertEquals(RemarkMessageStore.DATA_TABLE_WITHOUT_HEADER_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.1-3NMI-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_TABLE_IS_PRESENTATION_TABLE_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.1-3NMI-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.HEADER_DETECTED_CHECK_ALL_HEADERS_ARE_WELL_FORMED_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03--------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.1-3NMI-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        Iterator<ProcessRemark> iter = processResult.getRemarkSet().iterator();
        
        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_TABLE_IS_DATA_TABLE_MSG, processRemark.getMessageCode());
        assertTrue(processRemark.getTarget().equalsIgnoreCase(HtmlElementStore.TABLE_ELEMENT));
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.1-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        

        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("RGAA22.Test.11.1-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.FAILED,
                consolidate("RGAA22.Test.11.1-2Failed-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.11.1-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.11.1-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("RGAA22.Test.11.1-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.11.1-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("RGAA22.Test.11.1-4NA-02").getValue());
    }

}