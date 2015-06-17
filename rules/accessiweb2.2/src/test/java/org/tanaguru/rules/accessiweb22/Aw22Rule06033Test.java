/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
package org.tanaguru.rules.accessiweb22;

import java.util.Iterator;
import org.tanaguru.entity.audit.*;
import org.tanaguru.rules.accessiweb22.test.Aw22RuleImplementationTestCase;
import org.tanaguru.rules.keystore.AttributeStore;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 *
 * @author jkowalczyk
 */
public class Aw22Rule06033Test extends Aw22RuleImplementationTestCase {

    public Aw22Rule06033Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb22.Aw22Rule06033");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("AW22.Test.06.03.03-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-2Failed-05.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-2Failed-06.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-2Failed-07.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-2Failed-08.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-3NMI-01.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-3NMI-02.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-3NMI-03.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-3NMI-04.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-3NMI-05.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-3NMI-06.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-3NMI-07.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-4NA-01.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-4NA-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-4NA-02.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-4NA-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-4NA-03.html"));
        getWebResourceMap().put("AW22.Test.06.03.03-4NA-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06033/AW22.Test.06.03.03-4NA-04.html"));

        // 06.03.01 testcases
        getWebResourceMap().put("AW22.Test.06.03.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06031/AW22.Test.06.03.01-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.06.03.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06031/AW22.Test.06.03.01-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.06.03.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06031/AW22.Test.06.03.01-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.06.03.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06031/AW22.Test.06.03.01-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.06.03.01-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06031/AW22.Test.06.03.01-2Failed-05.html"));
        getWebResourceMap().put("AW22.Test.06.03.01-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06031/AW22.Test.06.03.01-2Failed-06.html"));
        getWebResourceMap().put("AW22.Test.06.03.01-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06031/AW22.Test.06.03.01-2Failed-07.html"));
        getWebResourceMap().put("AW22.Test.06.03.01-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06031/AW22.Test.06.03.01-3NMI-01.html"));
        getWebResourceMap().put("AW22.Test.06.03.01-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06031/AW22.Test.06.03.01-3NMI-02.html"));
        getWebResourceMap().put("AW22.Test.06.03.01-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06031/AW22.Test.06.03.01-3NMI-03.html"));
        getWebResourceMap().put("AW22.Test.06.03.01-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06031/AW22.Test.06.03.01-3NMI-04.html"));
        getWebResourceMap().put("AW22.Test.06.03.01-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06031/AW22.Test.06.03.01-3NMI-05.html"));
        getWebResourceMap().put("AW22.Test.06.03.01-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06031/AW22.Test.06.03.01-3NMI-06.html"));
        getWebResourceMap().put("AW22.Test.06.03.01-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06031/AW22.Test.06.03.01-3NMI-07.html"));
        getWebResourceMap().put("AW22.Test.06.03.01-3NMI-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06031/AW22.Test.06.03.01-3NMI-08.html"));
        getWebResourceMap().put("AW22.Test.06.03.01-3NMI-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06031/AW22.Test.06.03.01-3NMI-09.html"));

        //06.03.02 testcases
        getWebResourceMap().put("AW22.Test.06.03.02-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.06.03.02-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.06.03.02-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.06.03.02-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.06.03.02-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-2Failed-05.html"));
        getWebResourceMap().put("AW22.Test.06.03.02-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-2Failed-06.html"));
        getWebResourceMap().put("AW22.Test.06.03.02-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-2Failed-07.html"));
        getWebResourceMap().put("AW22.Test.06.03.02-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-2Failed-08.html"));
        getWebResourceMap().put("AW22.Test.06.03.02-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-2Failed-09.html"));
        getWebResourceMap().put("AW22.Test.06.03.02-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-2Failed-10.html"));
        getWebResourceMap().put("AW22.Test.06.03.02-2Failed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-2Failed-11.html"));
        getWebResourceMap().put("AW22.Test.06.03.02-2Failed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-2Failed-12.html"));
        getWebResourceMap().put("AW22.Test.06.03.02-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-3NMI-01.html"));
        getWebResourceMap().put("AW22.Test.06.03.02-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-3NMI-02.html"));
        getWebResourceMap().put("AW22.Test.06.03.02-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-3NMI-03.html"));
        getWebResourceMap().put("AW22.Test.06.03.02-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-3NMI-04.html"));
        getWebResourceMap().put("AW22.Test.06.03.02-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-3NMI-05.html"));
        getWebResourceMap().put("AW22.Test.06.03.02-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06032/AW22.Test.06.03.02-3NMI-06.html"));
        
        //06.03.04 testcases
        getWebResourceMap().put("AW22.Test.06.03.04-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.06.03.04-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.06.03.04-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.06.03.04-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.06.03.04-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-2Failed-05.html"));
        getWebResourceMap().put("AW22.Test.06.03.04-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-2Failed-06.html"));
        getWebResourceMap().put("AW22.Test.06.03.04-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-2Failed-07.html"));
        getWebResourceMap().put("AW22.Test.06.03.04-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-2Failed-08.html"));
        getWebResourceMap().put("AW22.Test.06.03.04-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-2Failed-09.html"));
        getWebResourceMap().put("AW22.Test.06.03.04-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-2Failed-10.html"));
        getWebResourceMap().put("AW22.Test.06.03.04-2Failed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-2Failed-11.html"));
        getWebResourceMap().put("AW22.Test.06.03.04-2Failed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-2Failed-12.html"));
        getWebResourceMap().put("AW22.Test.06.03.04-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-3NMI-01.html"));
        getWebResourceMap().put("AW22.Test.06.03.04-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-3NMI-02.html"));
        getWebResourceMap().put("AW22.Test.06.03.04-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-3NMI-03.html"));
        getWebResourceMap().put("AW22.Test.06.03.04-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-3NMI-04.html"));
        getWebResourceMap().put("AW22.Test.06.03.04-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-3NMI-05.html"));
        getWebResourceMap().put("AW22.Test.06.03.04-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06034/AW22.Test.06.03.04-3NMI-06.html"));
        
        //06.06.01 testcases -> empty links
        getWebResourceMap().put("AW22.Test.06.06.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-2Failed-01.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-2Failed-02.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-2Failed-03.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-2Failed-04.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-2Failed-05.html"));
        getWebResourceMap().put("AW22.Test.06.06.01-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "accessiweb22/Aw22Rule06061/AW22.Test.06.06.01-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("AW22.Test.06.03.03-2Failed-01");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        Iterator<ProcessRemark> iter = processResult.getRemarkSet().iterator();
        SourceCodeRemark sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        Iterator<EvidenceElement> eIter = sourceCodeRemark.getElementList().iterator();
        EvidenceElement ee = eIter.next();
        assertEquals("cliquez ici", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("cliquez ici", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("cliquez ici", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("cliquez ici", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-2Failed-02");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("cliquez ici", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("cliquez ici", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-2Failed-03");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-2Failed-04");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-05------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-2Failed-05");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-2Failed-06");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("cliquez ici", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("cliquez ici", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-07------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-2Failed-07");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-08------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-2Failed-08");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-3NMI-01");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("unpredictable content pertinence", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("cliquez ici", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("unpredictable content pertinence", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("cliquez ici", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-3NMI-02");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("unpredictable content pertinence", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("unpredictable content pertinence", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-3NMI-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("unpredictable content pertinence", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-3NMI-04");
        // check number of elements in the page
        assertEquals(2, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(2, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("unpredictable content pertinence", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("unpredictable content pertinence", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-3NMI-05");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("This is a link", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-3NMI-06");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("This is a link", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("This is a link and more", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-07---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-3NMI-07");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.AREA_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("This is a link", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("This is a link", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-4NA-03");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("AW22.Test.06.03.03-4NA-04");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        
        // 06.03.01 testcases : All is Not Applicable
        processResult = processPageTest("AW22.Test.06.03.01-2Failed-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.01-2Failed-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.01-2Failed-03");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.01-2Failed-04");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.01-2Failed-05");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.01-2Failed-06");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.01-2Failed-07");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.01-3NMI-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.01-3NMI-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.01-3NMI-03");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.01-3NMI-04");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.01-3NMI-05");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.01-3NMI-06");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.01-3NMI-07");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.01-3NMI-08");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.01-3NMI-09");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());

        // 06.03.02 testcases : All is Not Applicable
        processResult = processPageTest("AW22.Test.06.03.02-2Failed-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.02-2Failed-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.02-2Failed-03");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.02-2Failed-04");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.02-2Failed-05");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.02-2Failed-06");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.02-2Failed-07");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.02-2Failed-08");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.02-2Failed-09");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.02-2Failed-10");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.02-2Failed-11");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.02-2Failed-12");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.02-3NMI-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.02-3NMI-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.02-3NMI-03");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.02-3NMI-04");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.02-3NMI-05");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.02-3NMI-06");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        // 06.03.04 testcases : All is Not Applicable
        processResult = processPageTest("AW22.Test.06.03.04-2Failed-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.04-2Failed-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.04-2Failed-03");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.04-2Failed-04");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.04-2Failed-05");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.04-2Failed-06");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.04-2Failed-07");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.04-2Failed-08");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.04-2Failed-09");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.04-2Failed-10");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.04-2Failed-11");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.04-2Failed-12");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.04-3NMI-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.04-3NMI-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.04-3NMI-03");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.04-3NMI-04");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.04-3NMI-05");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.03.04-3NMI-06");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        // 06.06.01 testcases : All is Not Applicable
        processResult = processPageTest("AW22.Test.06.06.01-2Failed-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.06.01-2Failed-02");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.06.01-2Failed-03");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.06.01-2Failed-04");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.06.01-2Failed-05");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("AW22.Test.06.06.01-4NA-01");
        assertEquals(TestSolution.NOT_APPLICABLE,processResult.getValue());
        assertNull(processResult.getRemarkSet());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.03.03-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.03.03-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.03.03-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.03.03-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.03.03-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.03.03-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.03.03-2Failed-07").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("AW22.Test.06.03.03-2Failed-08").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.06.03.03-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.06.03.03-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.06.03.03-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.06.03.03-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.06.03.03-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.06.03.03-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("AW22.Test.06.03.03-3NMI-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.03-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.03-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.03-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.03-4NA-04").getValue());

        // 06.03.01 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.01-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.01-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.01-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.01-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.01-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.01-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.01-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.01-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.01-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.01-3NMI-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.01-3NMI-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.01-3NMI-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.01-3NMI-09").getValue());

        // 06.03.02 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.02-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.02-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.02-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.02-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.02-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.02-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.02-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.02-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.02-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.02-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.02-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.02-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.02-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.02-3NMI-06").getValue());
        
        // 06.03.04 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-2Failed-09").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-2Failed-10").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-2Failed-11").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-2Failed-12").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.03.04-3NMI-06").getValue());        
                
        // 06.06.01 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.06.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.06.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.06.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.06.01-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.06.01-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("AW22.Test.06.06.01-4NA-01").getValue());
    }
}
