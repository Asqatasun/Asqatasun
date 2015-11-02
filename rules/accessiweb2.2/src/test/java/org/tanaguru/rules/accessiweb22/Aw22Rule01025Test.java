/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.rules.accessiweb22;

import java.util.Iterator;
import java.util.LinkedHashSet;
import org.apache.commons.lang3.StringUtils;
import org.tanaguru.entity.audit.*;
import org.tanaguru.rules.accessiweb22.test.Aw22RuleImplementationTestCase;
import static org.tanaguru.rules.keystore.AttributeStore.SRC_ATTR;
import org.tanaguru.rules.keystore.HtmlElementStore;
import static org.tanaguru.rules.keystore.MarkerStore.DECORATIVE_IMAGE_MARKER;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 1.2.5 of the referential Accessiweb 2.2.
 *
 * @author jkowalczyk
 */
public class Aw22Rule01025Test extends Aw22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Aw22Rule01025Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb22.Aw22Rule01025");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW22.Test.1.2.5-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01025/AW22.Test.1.2.5-3NMI-01.html"));
        addParameterToParameterMap("AW22.Test.1.2.5-3NMI-01", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "class-decorative-embed"));
        getWebResourceMap().put("AW22.Test.1.2.5-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01025/AW22.Test.1.2.5-3NMI-02.html"));
        getWebResourceMap().put("AW22.Test.1.2.5-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule01025/AW22.Test.1.2.5-3NMI-03.html"));
        addParameterToParameterMap("AW22.Test.1.2.5-3NMI-03", createParameter("Rules", DECORATIVE_IMAGE_MARKER, "id-decorative-embed"));
        getWebResourceMap().put("AW22.Test.1.2.5-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule01025/AW22.Test.1.2.5-4NA-01.html"));
        getWebResourceMap().put("AW22.Test.1.2.5-4NA-02",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule01025/AW22.Test.1.2.5-4NA-02.html"));
        getWebResourceMap().put("AW22.Test.1.2.5-4NA-03",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule01025/AW22.Test.1.2.5-4NA-03.html"));
        getWebResourceMap().put("AW22.Test.1.2.5-4NA-04",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule01025/AW22.Test.1.2.5-4NA-04.html"));
        getWebResourceMap().put("AW22.Test.1.2.5-4NA-05",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "accessiweb22/Aw22Rule01025/AW22.Test.1.2.5-4NA-05.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("AW22.Test.1.2.5-3NMI-01");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        SourceCodeRemark processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_ALTERNATIVE_OF_DECORATIVE_ELEMENT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.EMBED_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        EvidenceElement ee = processRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock_embed_object"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.2.5-3NMI-02");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((SourceCodeRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.CHECK_NATURE_AND_ALTERNATIVE_OF_ELEMENT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.EMBED_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock_embed_object"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.2.5-3NMI-03");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        Iterator<ProcessRemark> iter = processResult.getRemarkSet().iterator();
        
        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(RemarkMessageStore.CHECK_ALTERNATIVE_OF_DECORATIVE_ELEMENT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.EMBED_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock_embed_object1"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        
        processRemark = (SourceCodeRemark)iter.next();
        assertEquals(RemarkMessageStore.CHECK_NATURE_AND_ALTERNATIVE_OF_ELEMENT_MSG, processRemark.getMessageCode());
        assertEquals(TestSolution.NEED_MORE_INFO, processRemark.getIssue());
        assertEquals(HtmlElementStore.EMBED_ELEMENT, processRemark.getTarget());
        assertNotNull(processRemark.getSnippet());
        // check number of evidence elements and their value
        assertEquals(1, processRemark.getElementList().size());
        ee = processRemark.getElementList().iterator().next();
        assertTrue(StringUtils.contains(ee.getValue(), "mock_embed_object2"));
        assertEquals(SRC_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.2.5-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.2.5-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.2.5-4NA-03");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.2.5-4NA-04");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-05----------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.1.2.5-4NA-05");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page
        assertEquals(0, processResult.getElementCounter());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.1.2.5-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.1.2.5-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.1.2.5-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.1.2.5-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.1.2.5-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.1.2.5-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.1.2.5-4NA-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.1.2.5-4NA-05").getValue());
    }

}