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

import java.util.Collection;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.entity.subject.Page;
import org.tanaguru.entity.subject.Site;
import org.tanaguru.rules.test.AbstractSiteRuleWithPageResultImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 
 * CheckTitleContentUnicityAtSiteLevel of the referential Rules creation demo.
 *
 * @author
 */
public class CheckTitleContentUnicityAtSiteLevelTest extends AbstractSiteRuleWithPageResultImplementationTestCase {

    private static final String TESTCASE_FILE_PATH = "src/test/resources/testcases/";
    private static final String INPUT_FILE_DATA_NAME = "src/test/resources/dataSets/nomenclatureFlatXmlDataSet.xml";
    private static final String REFERENTIAL = "Rulescreationdemo";
    
    /**
     * Default constructor
     */
    public CheckTitleContentUnicityAtSiteLevelTest (String testName){
        super(testName, INPUT_FILE_DATA_NAME, TESTCASE_FILE_PATH, REFERENTIAL);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rulescreationdemo.CheckTitleContentUnicityAtSiteLevel");
    }

    @Override
    protected void setUpWebResourceMap() {
        // file:Site-Passed1
        Site site = getWebResourceFactory().createSite("file:Site-Passed1");
        getWebResourceMap().put("Rulescreationdemo.Test.3.1.1-1Passed-01", site);

        Page page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "rulescreationdemo/CheckTitleContentUnicityAtSiteLevel/Rulescreationdemo.Test.3.1.1-1Passed-01.html");
        site.addChild(page);
        getWebResourceMap().put("Rulescreationdemo.Test.3.1.1-page01",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "rulescreationdemo/CheckTitleContentUnicityAtSiteLevel/Rulescreationdemo.Test.3.1.1-1Passed-02.html");
        site.addChild(page);
        getWebResourceMap().put("Rulescreationdemo.Test.3.1.1-page02",page);
        
        // file:Site-Failed1
        site = getWebResourceFactory().createSite("file:Site-Failed1");
        getWebResourceMap().put("Rulescreationdemo.Test.3.1.1-2Failed-01", site);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "rulescreationdemo/CheckTitleContentUnicityAtSiteLevel/Rulescreationdemo.Test.3.1.1-2Failed-01.html");
        site.addChild(page);
        getWebResourceMap().put("Rulescreationdemo.Test.3.1.1-page03",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "rulescreationdemo/CheckTitleContentUnicityAtSiteLevel/Rulescreationdemo.Test.3.1.1-2Failed-02.html");
        site.addChild(page);
        getWebResourceMap().put("Rulescreationdemo.Test.3.1.1-page04",page);

        // file:Site-NA1
        site = getWebResourceFactory().createSite("file:Site-NA1");
        getWebResourceMap().put("Rulescreationdemo.Test.3.1.1-4NA-01", site);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "rulescreationdemo/CheckTitleContentUnicityAtSiteLevel/Rulescreationdemo.Test.3.1.1-4NA-01.html");
        site.addChild(page);
        getWebResourceMap().put("Rulescreationdemo.Test.3.1.1-page05",page);

        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "rulescreationdemo/CheckTitleContentUnicityAtSiteLevel/Rulescreationdemo.Test.3.1.1-4NA-02.html");
        site.addChild(page);
        getWebResourceMap().put("Rulescreationdemo.Test.3.1.1-page06",page);

        // file:Site-Passed2
        site = getWebResourceFactory().createSite("file:Site-Passed2");
        getWebResourceMap().put("Rulescreationdemo.Test.3.1.1-1Passed-02", site);
        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "rulescreationdemo/CheckTitleContentUnicityAtSiteLevel/Rulescreationdemo.Test.3.1.1-4NA-02.html");
        site.addChild(page);
        getWebResourceMap().put("Rulescreationdemo.Test.3.1.1-page07",page);
        page = getWebResourceFactory().createPage(getTestcasesFilePath() +
                "rulescreationdemo/CheckTitleContentUnicityAtSiteLevel/Rulescreationdemo.Test.3.1.1-1Passed-01.html");
        site.addChild(page);
        getWebResourceMap().put("Rulescreationdemo.Test.3.1.1-page08",page);
        
    }

    @Override
    protected void setProcess() {
        process("Rulescreationdemo.Test.3.1.1-1Passed-01");
        process("Rulescreationdemo.Test.3.1.1-1Passed-02");
        process("Rulescreationdemo.Test.3.1.1-2Failed-01");
        process("Rulescreationdemo.Test.3.1.1-4NA-01");
    }

    @Override
    protected void setConsolidate() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        Collection<ProcessResult> processResult = consolidateWithListAsReturn("Rulescreationdemo.Test.3.1.1-1Passed-01");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(2).getValue());

        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        processResult = consolidateWithListAsReturn("Rulescreationdemo.Test.3.1.1-1Passed-02");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.NOT_APPLICABLE, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.PASSED, ((List<ProcessResult>)processResult).get(2).getValue());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = consolidateWithListAsReturn("Rulescreationdemo.Test.3.1.1-2Failed-01");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.FAILED, ((List<ProcessResult>)processResult).get(2).getValue());

        assertEquals(2, ((List<ProcessResult>)processResult).get(0).getRemarkSet().size());
        assertEquals("TitleIdenticalToAnotherPage",
                ((ProcessRemark)((List<ProcessResult>)processResult).get(0).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("TitleIdenticalToAnotherPage",
                ((ProcessRemark)((List<ProcessResult>)processResult).get(0).getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(2, ((List<ProcessResult>)processResult).get(1).getRemarkSet().size());
        assertEquals("TitleIdenticalToAnotherPage",
                ((ProcessRemark)((List<ProcessResult>)processResult).get(1).getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("TitleIdenticalToAnotherPage",
                ((ProcessRemark)((List<ProcessResult>)processResult).get(1).getRemarkSet().toArray()[1]).getMessageCode());
        assertEquals(2, ((List<ProcessResult>)processResult).get(2).getRemarkSet().size());
        assertEquals("TitleNotUnique",
                ((ProcessRemark)((List<ProcessResult>)processResult).get(2).getRemarkSet().toArray()[0]).getMessageCode());
        
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        processResult = consolidateWithListAsReturn("Rulescreationdemo.Test.3.1.1-4NA-01");
        assertEquals(3, processResult.size());
        assertEquals(TestSolution.NOT_APPLICABLE, ((List<ProcessResult>)processResult).get(0).getValue());
        assertEquals(TestSolution.NOT_APPLICABLE, ((List<ProcessResult>)processResult).get(1).getValue());
        assertEquals(TestSolution.NOT_APPLICABLE, ((List<ProcessResult>)processResult).get(2).getValue());
    }

}