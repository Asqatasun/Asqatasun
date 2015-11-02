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
package org.tanaguru.rules.accessiweb21;

import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.accessiweb21.test.Aw21RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 5.8.1 of the referential Accessiweb 2.1.
 *
 * @author jkowalczyk
 */
public class Aw21Rule05081Test extends Aw21RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Aw21Rule05081Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.accessiweb21.Aw21Rule05081");
    }

    @Override
    protected void setUpWebResourceMap() {
        getWebResourceMap().put("Aw21.Test.5.8.1-3NMI-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "AW21/Aw21Rule05081/Aw21.Test.5.8.1-3NMI-01.html"));
        getWebResourceMap().put("Aw21.Test.5.8.1-4NA-01",
              getWebResourceFactory().createPage(
              getTestcasesFilePath() + "AW21/Aw21Rule05081/Aw21.Test.5.8.1-4NA-01.html"));
    }

    @Override
    protected void setProcess() {
        ProcessResult processResult = processPageTest("Aw21.Test.5.8.1-3NMI-01");
        assertEquals(TestSolution.NEED_MORE_INFO, processResult.getValue());
        assertEquals("ManualCheckOnElements",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals(TestSolution.NOT_APPLICABLE,
                processPageTest("Aw21.Test.5.8.1-4NA-01").getValue());
    }

    @Override
    protected void setConsolidate() {
        assertEquals(TestSolution.NEED_MORE_INFO,
                consolidate("Aw21.Test.5.8.1-3NMI-01").getValue());
        assertEquals(TestSolution.NOT_APPLICABLE,
                consolidate("Aw21.Test.5.8.1-4NA-01").getValue());
    }

}