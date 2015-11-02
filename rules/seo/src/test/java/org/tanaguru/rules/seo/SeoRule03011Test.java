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
package org.tanaguru.rules.seo;

import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.seo.test.SeoRuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public class SeoRule03011Test extends SeoRuleImplementationTestCase {

    public SeoRule03011Test(String testName) {
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.seo.SeoRule03011");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Seo.Test.3.1.1-1Passed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule03011/Seo.Test.3.1.1-1Passed-01.html"));
        getWebResourceMap().put("Seo.Test.3.1.1-2Failed-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule03011/Seo.Test.3.1.1-2Failed-01.html"));
        getWebResourceMap().put("Seo.Test.3.1.1-2Failed-02",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "SEO/SeoRule03011/Seo.Test.3.1.1-2Failed-02.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult = processPageTest("Seo.Test.3.1.1-1Passed-01");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        processResult = processPageTest("Seo.Test.3.1.1-2Failed-01");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.FRAMESET_DETECTED_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        
        processResult = processPageTest("Seo.Test.3.1.1-2Failed-02");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1,processResult.getRemarkSet().size());
        assertEquals(RemarkMessageStore.FRAMESET_DETECTED_MSG,
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.PASSED,
                consolidate("Seo.Test.3.1.1-1Passed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.3.1.1-2Failed-01").getValue());
        assertEquals(TestSolution.FAILED,
                consolidate("Seo.Test.3.1.1-2Failed-02").getValue());
    }

}
