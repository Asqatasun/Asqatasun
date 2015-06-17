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

import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.rgaa22.test.Rgaa22RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 2.15 of the referential RGAA 2.2.
 *
 * @author jkowalczyk
 */
public class Rgaa22Rule02151Test extends Rgaa22RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa22Rule02151Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa22.Rgaa22Rule02151");
    }

    @Override
    protected void setUpWebResourceMap() {
//        getWebResourceMap().put("Rgaa22.Test.2.15-1Passed-01",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "rgaa22/Rgaa22Rule02151/RGAA22.Test.2.15-1Passed-01.html"));
//        getWebResourceMap().put("Rgaa22.Test.2.15-2Failed-01",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "rgaa22/Rgaa22Rule02151/RGAA22.Test.2.15-2Failed-01.html"));
//        getWebResourceMap().put("Rgaa22.Test.2.15-3NMI-01",
//                getWebResourceFactory().createPage(
//                getTestcasesFilePath() + "rgaa22/Rgaa22Rule02151/RGAA22.Test.2.15-3NMI-01.html"));
//        getWebResourceMap().put("Rgaa22.Test.2.15-4NA-01",
//              getWebResourceFactory().createPage(
//              getTestcasesFilePath() + "rgaa22/Rgaa22Rule02151/RGAA22.Test.2.15-4NA-01.html"));
        getWebResourceMap().put("Rgaa22.Test.2.15-5NT-01",
                getWebResourceFactory().createPage(
                getTestcasesFilePath() + "rgaa22/Rgaa22Rule02151/RGAA22.Test.2.15-5NT-01.html"));
    }

    @Override
    protected void setProcess() {
//        assertEquals(TestSolution.PASSED,
//                processPageTest("Rgaa22.Test.2.15-1Passed-01").getValue());
//        assertEquals(TestSolution.FAILED,
//                processPageTest("Rgaa22.Test.2.15-2Failed-01").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                processPageTest("Rgaa22.Test.2.15-3NMI-01").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                processPageTest("Rgaa22.Test.2.15-4NA-01").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                processPageTest("Rgaa22.Test.2.15-5NT-01").getValue());
    }

    @Override
    protected void setConsolidate() {
//        assertEquals(TestSolution.PASSED,
//                consolidate("Rgaa22.Test.2.15-1Passed-01").getValue());
//        assertEquals(TestSolution.FAILED,
//                consolidate("Rgaa22.Test.2.15-2Failed-01").getValue());
//        assertEquals(TestSolution.NEED_MORE_INFO,
//                consolidate("Rgaa22.Test.2.15-3NMI-01").getValue());
//        assertEquals(TestSolution.NOT_APPLICABLE,
//                consolidate("Rgaa22.Test.2.15-4NA-01").getValue());
        assertEquals(TestSolution.NOT_TESTED,
                consolidate("Rgaa22.Test.2.15-5NT-01").getValue());
    }

}