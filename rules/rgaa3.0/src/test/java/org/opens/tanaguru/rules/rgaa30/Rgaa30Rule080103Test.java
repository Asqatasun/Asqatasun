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

import java.util.LinkedHashSet;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.opens.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 8-1-3 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule080103Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule080103Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.opens.tanaguru.rules.rgaa30.Rgaa30Rule080103");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.08.01.03-1Passed-01");
        addWebResource("Rgaa30.Test.08.01.03-2Failed-01");
        addWebResource("Rgaa30.Test.08.01.03-2Failed-02");
        addWebResource("Rgaa30.Test.08.01.03-4NA-01");

    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.08.01.03-1Passed-01");
        // check test result
        assertEquals(TestSolution.PASSED, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.01.03-2Failed-01");
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        ProcessRemark processRemark = ((ProcessRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_DOCTYPE_LOCATION_MSG, processRemark.getMessageCode());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.01.03-2Failed-02");
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());
        // check test result
        assertEquals(TestSolution.FAILED, processResult.getValue());
        // check number of remarks and their value
        assertEquals(1, processResult.getRemarkSet().size());
        processRemark = ((ProcessRemark)((LinkedHashSet)processResult.getRemarkSet()).iterator().next());
        assertEquals(RemarkMessageStore.BAD_DOCTYPE_LOCATION_MSG, processRemark.getMessageCode());
        // check number of evidence elements and their value
        assertNull(processRemark.getElementList());
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.08.01.03-4NA-01");
        // check test result
        assertEquals(TestSolution.NOT_APPLICABLE, processResult.getValue());
        // check test has no remark
        assertNull(processResult.getRemarkSet());
        // check number of elements in the page (no counter management here, 
        // we test the presence of one tag in the page)
        assertEquals(0, processResult.getElementCounter());

    }

}
