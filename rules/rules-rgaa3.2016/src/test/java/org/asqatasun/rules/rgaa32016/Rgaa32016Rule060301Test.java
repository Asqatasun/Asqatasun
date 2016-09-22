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
package org.asqatasun.rules.rgaa32016;

import java.util.Iterator;
import org.asqatasun.entity.audit.*;
import org.asqatasun.rules.rgaa32016.test.Rgaa32016RuleImplementationTestCase;
import org.asqatasun.rules.keystore.AttributeStore;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 6.3.1 of the referential RGAA 3.2016
 *
 * @author jkowalczyk
 */
public class Rgaa32016Rule060301Test extends Rgaa32016RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa32016Rule060301Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa32016.Rgaa32016Rule060301");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa32016.Test.06.03.01-2Failed-01");
        addWebResource("Rgaa32016.Test.06.03.01-2Failed-02");
        addWebResource("Rgaa32016.Test.06.03.01-2Failed-03");
        addWebResource("Rgaa32016.Test.06.03.01-2Failed-04");
        addWebResource("Rgaa32016.Test.06.03.01-2Failed-05");
        addWebResource("Rgaa32016.Test.06.03.01-2Failed-06");
        addWebResource("Rgaa32016.Test.06.03.01-2Failed-07");
        addWebResource("Rgaa32016.Test.06.03.01-3NMI-01");
        addWebResource("Rgaa32016.Test.06.03.01-3NMI-02");
        addWebResource("Rgaa32016.Test.06.03.01-3NMI-03");
        addWebResource("Rgaa32016.Test.06.03.01-3NMI-04");
        addWebResource("Rgaa32016.Test.06.03.01-3NMI-05");
        addWebResource("Rgaa32016.Test.06.03.01-3NMI-06");
        addWebResource("Rgaa32016.Test.06.03.01-3NMI-07");
        addWebResource("Rgaa32016.Test.06.03.01-3NMI-08");
        addWebResource("Rgaa32016.Test.06.03.01-3NMI-09");
        addWebResource("Rgaa32016.Test.06.03.01-4NA-01");
        addWebResource("Rgaa32016.Test.06.03.01-4NA-02");
        addWebResource("Rgaa32016.Test.06.03.01-4NA-03");
        addWebResource("Rgaa32016.Test.06.03.01-4NA-04");

        //06.03.02 testcases
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-2Failed-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-2Failed-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-2Failed-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-2Failed-04.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-2Failed-05.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-2Failed-06.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-2Failed-07.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-2Failed-08.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-2Failed-09.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-2Failed-10.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-2Failed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-2Failed-11.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-2Failed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-2Failed-12.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-3NMI-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-3NMI-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-3NMI-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-3NMI-04.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-3NMI-05.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.02-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060302/Rgaa32016.Test.06.03.02-3NMI-06.html"));

        //06.03.03 testcases
        getWebResourceMap().put("Rgaa32016.Test.06.03.03-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060303/Rgaa32016.Test.06.03.03-2Failed-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.03-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060303/Rgaa32016.Test.06.03.03-2Failed-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.03-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060303/Rgaa32016.Test.06.03.03-2Failed-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.03-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060303/Rgaa32016.Test.06.03.03-2Failed-04.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.03-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060303/Rgaa32016.Test.06.03.03-2Failed-05.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.03-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060303/Rgaa32016.Test.06.03.03-2Failed-06.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.03-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060303/Rgaa32016.Test.06.03.03-2Failed-07.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.03-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060303/Rgaa32016.Test.06.03.03-2Failed-08.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.03-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060303/Rgaa32016.Test.06.03.03-3NMI-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.03-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060303/Rgaa32016.Test.06.03.03-3NMI-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.03-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060303/Rgaa32016.Test.06.03.03-3NMI-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.03-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060303/Rgaa32016.Test.06.03.03-3NMI-04.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.03-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060303/Rgaa32016.Test.06.03.03-3NMI-05.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.03-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060303/Rgaa32016.Test.06.03.03-3NMI-06.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.03-3NMI-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060303/Rgaa32016.Test.06.03.03-3NMI-07.html"));
        
        //06.03.04 testcases
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-2Failed-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-2Failed-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-2Failed-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-2Failed-04.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-2Failed-05.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-2Failed-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-2Failed-06.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-2Failed-07",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-2Failed-07.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-2Failed-08",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-2Failed-08.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-2Failed-09",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-2Failed-09.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-2Failed-10",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-2Failed-10.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-2Failed-11",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-2Failed-11.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-2Failed-12",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-2Failed-12.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-3NMI-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-3NMI-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-3NMI-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-3NMI-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-3NMI-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-3NMI-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-3NMI-04.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-3NMI-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-3NMI-05.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.03.04-3NMI-06",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060304/Rgaa32016.Test.06.03.04-3NMI-06.html"));
        
        //06.03.05 testcases
        getWebResourceMap().put("Rgaa32016.Test.6.3.5-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060305/Rgaa32016.Test.6.3.5-2Failed-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.6.3.5-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060305/Rgaa32016.Test.6.3.5-2Failed-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.6.3.5-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060305/Rgaa32016.Test.6.3.5-2Failed-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.6.3.5-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060305/Rgaa32016.Test.6.3.5-2Failed-04.html"));
        getWebResourceMap().put("Rgaa32016.Test.6.3.5-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060305/Rgaa32016.Test.6.3.5-2Failed-05.html"));
        getWebResourceMap().put("Rgaa32016.Test.6.3.5-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060305/Rgaa32016.Test.6.3.5-3NMI-01.html"));
        
        //06.05.01 testcases -> empty links
        getWebResourceMap().put("Rgaa32016.Test.06.05.01-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060501/Rgaa32016.Test.06.05.01-2Failed-01.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.05.01-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060501/Rgaa32016.Test.06.05.01-2Failed-02.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.05.01-2Failed-03",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060501/Rgaa32016.Test.06.05.01-2Failed-03.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.05.01-2Failed-04",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060501/Rgaa32016.Test.06.05.01-2Failed-04.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.05.01-2Failed-05",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060501/Rgaa32016.Test.06.05.01-2Failed-05.html"));
        getWebResourceMap().put("Rgaa32016.Test.06.05.01-4NA-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa32016/Rgaa32016Rule060501/Rgaa32016.Test.06.05.01-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa32016.Test.06.03.01-2Failed-01");
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
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
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
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("cliquez ici", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("or whatever", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.03.01-2Failed-02");
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
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
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
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
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
        processResult = processPageTest("Rgaa32016.Test.06.03.01-2Failed-03");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
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
        processResult = processPageTest("Rgaa32016.Test.06.03.01-2Failed-04");
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
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
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
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
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
        processResult = processPageTest("Rgaa32016.Test.06.03.01-2Failed-05");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
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
        //------------------------------2Failed-06------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.03.01-2Failed-06");
        // check number of elements in the page
        assertEquals(1, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        iter = processResult.getRemarkSet().iterator();
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.FAILED, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.UNEXPLICIT_LINK_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
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
        processResult = processPageTest("Rgaa32016.Test.06.03.01-2Failed-07");
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
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
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
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
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
        processResult = processPageTest("Rgaa32016.Test.06.03.01-3NMI-01");
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
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Unpredictable content", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("Title is different", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("Unpredictable content", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("cliquez ici", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.03.01-3NMI-02");
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
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("unpredictable content", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("unpredictable content", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.03.01-3NMI-03");
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
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("unpredictable content", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.03.01-3NMI-04");
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
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("unpredictable content", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        sourceCodeRemark = (SourceCodeRemark)iter.next();
        assertEquals(TestSolution.NEED_MORE_INFO, sourceCodeRemark.getIssue());
        assertEquals(RemarkMessageStore.CHECK_LINK_PERTINENCE_MSG, sourceCodeRemark.getMessageCode());
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("unpredictable content", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("attribute-absent", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.03.01-3NMI-05");
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
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
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
        processResult = processPageTest("Rgaa32016.Test.06.03.01-3NMI-06");
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
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
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
        processResult = processPageTest("Rgaa32016.Test.06.03.01-3NMI-07");
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
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
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
        //------------------------------3NMI-08---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.03.01-3NMI-08");
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
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("-->;*", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("the title needs to be checked", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());

        
        //----------------------------------------------------------------------
        //------------------------------3NMI-09---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.03.01-3NMI-09");
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
        assertEquals(HtmlElementStore.A_ELEMENT, sourceCodeRemark.getTarget());
        // check number of evidence elements and their value
        assertEquals(2,sourceCodeRemark.getElementList().size());
        eIter = sourceCodeRemark.getElementList().iterator();
        ee = eIter.next();
        assertEquals("cliquez ici", ee.getValue());
        assertEquals(HtmlElementStore.TEXT_ELEMENT2, ee.getEvidence().getCode());
        ee = eIter.next();
        assertEquals("the title needs to be checked", ee.getValue());
        assertEquals(AttributeStore.TITLE_ATTR, ee.getEvidence().getCode());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.03.01-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.03.01-4NA-02");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.03.01-4NA-03");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.06.03.01-4NA-04");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());

        
        // 06.03.02 testcases : All is Not Applicable
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-2Failed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-2Failed-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-2Failed-06"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-2Failed-07"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-2Failed-08"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-2Failed-09"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-2Failed-10"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-2Failed-11"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-2Failed-12"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-3NMI-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-3NMI-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-3NMI-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-3NMI-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-3NMI-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.02-3NMI-06"));

        // 06.03.03 testcases : All is Not Applicable
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.03-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.03-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.03-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.03-2Failed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.03-2Failed-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.03-2Failed-06"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.03-2Failed-07"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.03-2Failed-08"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.03-3NMI-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.03-3NMI-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.03-3NMI-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.03-3NMI-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.03-3NMI-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.03-3NMI-06"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.03-3NMI-07"));
        
        // 06.03.04 testcases : All is Not Applicable
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-2Failed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-2Failed-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-2Failed-06"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-2Failed-07"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-2Failed-08"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-2Failed-09"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-2Failed-10"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-2Failed-11"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-2Failed-12"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-3NMI-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-3NMI-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-3NMI-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-3NMI-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-3NMI-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.03.04-3NMI-06"));
        
        // 6.3.5 testcases : All is Not Applicable
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.6.3.5-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.6.3.5-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.6.3.5-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.6.3.5-2Failed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.6.3.5-2Failed-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.6.3.5-3NMI-01"));
        
        // 06.05.01 testcases : All is Not Applicable
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.05.01-2Failed-01"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.05.01-2Failed-02"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.05.01-2Failed-03"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.05.01-2Failed-04"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.05.01-2Failed-05"));
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.06.05.01-4NA-01"));
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.03.01-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.03.01-2Failed-02").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.03.01-2Failed-03").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.03.01-2Failed-04").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.03.01-2Failed-05").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.03.01-2Failed-06").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Rgaa32016.Test.06.03.01-2Failed-07").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.03.01-3NMI-01").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.03.01-3NMI-02").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.03.01-3NMI-03").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.03.01-3NMI-04").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.03.01-3NMI-05").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.03.01-3NMI-06").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.03.01-3NMI-07").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.03.01-3NMI-08").getValue());
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Rgaa32016.Test.06.03.01-3NMI-09").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.01-4NA-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.01-4NA-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.01-4NA-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.01-4NA-04").getValue());

        // 06.03.02 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-2Failed-09").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-2Failed-10").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-2Failed-11").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-2Failed-12").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.02-3NMI-06").getValue());

        // 06.03.03 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.03-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.03-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.03-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.03-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.03-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.03-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.03-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.03-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.03-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.03-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.03-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.03-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.03-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.03-3NMI-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.03-3NMI-07").getValue());

        // 06.03.04 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-2Failed-06").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-2Failed-07").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-2Failed-08").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-2Failed-09").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-2Failed-10").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-2Failed-11").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-2Failed-12").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-3NMI-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-3NMI-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-3NMI-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-3NMI-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.03.04-3NMI-06").getValue());
        
        // 6.3.5 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.6.3.5-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.6.3.5-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.6.3.5-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.6.3.5-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.6.3.5-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.6.3.5-3NMI-01").getValue());
        
        // 06.05.01 testcases : All is Not Applicable
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.05.01-2Failed-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.05.01-2Failed-02").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.05.01-2Failed-03").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.05.01-2Failed-04").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.05.01-2Failed-05").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Rgaa32016.Test.06.05.01-4NA-01").getValue());
    }

}
