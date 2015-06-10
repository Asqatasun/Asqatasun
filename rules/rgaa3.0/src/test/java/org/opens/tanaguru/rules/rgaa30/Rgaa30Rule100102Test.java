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
package org.opens.tanaguru.rules.rgaa30;

import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;

/**
 * Unit test class for the implementation of the rule 10-1-2 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule100102Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule100102Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.rgaa30.Rgaa30Rule100102");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.10.01.02-1Passed-01");
        addWebResource("Rgaa30.Test.10.01.02-1Passed-02");
        addWebResource("Rgaa30.Test.10.01.02-1Passed-03");
        addWebResource("Rgaa30.Test.10.01.02-1Passed-04");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-01");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-02");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-03");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-04");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-05");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-06");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-07");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-08");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-09");
        addWebResource("Rgaa30.Test.10.01.02-2Failed-10");

    }

    @Override
    protected void setProcess() {
        ProcessResult processResult = processPageTest("Rgaa30.Test.10.01.02-1Passed-01");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        
        processResult = processPageTest("Rgaa30.Test.10.01.02-1Passed-02");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        
        processResult = processPageTest("Rgaa30.Test.10.01.02-1Passed-03");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());
        
        
        processResult = processPageTest("Rgaa30.Test.10.01.02-1Passed-04");
        assertEquals(TestSolution.PASSED, processResult.getValue());
        assertNull(processResult.getRemarkSet());

        
        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-01");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-02");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-03");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-04");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-05");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-06");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-07");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-08");
        assertEquals(TestSolution.FAILED,processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-09");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(1, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());

        processResult = processPageTest("Rgaa30.Test.10.01.02-2Failed-10");
        assertEquals(TestSolution.FAILED, processResult.getValue());
        assertEquals(2, processResult.getRemarkSet().size());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[0]).getMessageCode());
        assertEquals("PresentationAttrFound",
                ((ProcessRemark)processResult.getRemarkSet().toArray()[1]).getMessageCode());


    }

}
