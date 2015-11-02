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
package org.tanaguru.rules.rgaa30;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.tanaguru.entity.audit.*;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import static org.tanaguru.rules.keystore.AttributeStore.ALT_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.SRC_ATTR;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;


/**
 * Unit test class for the implementation of the rule 1.3.3 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule010303Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule010303Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.tanaguru.rules.rgaa30.Rgaa30Rule010303");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.1.3.3-2Failed-01");
        addWebResource("Rgaa30.Test.1.3.3-2Failed-02");
        addWebResource("Rgaa30.Test.1.3.3-2Failed-03");
        addWebResource("Rgaa30.Test.1.3.3-2Failed-04");
        addWebResource("Rgaa30.Test.1.3.3-3NMI-01");
        addWebResource("Rgaa30.Test.1.3.3-4NA-01");
        addWebResource("Rgaa30.Test.1.3.3-4NA-02");
        addWebResource("Rgaa30.Test.1.3.3-4NA-03");
        addWebResource("Rgaa30.Test.1.3.3-4NA-04");
        addWebResource("Rgaa30.Test.1.3.3-4NA-05");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.1.3.3-2Failed-01");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_ALT_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "mock-image"),
                new ImmutablePair(SRC_ATTR, "mock-image"));
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.3-2Failed-02");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_ALT_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "my-image.png"),
                new ImmutablePair(SRC_ATTR, "mock-image"));
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.3-2Failed-03");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_ALT_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "--><--"),
                new ImmutablePair(SRC_ATTR, "mock-image"));
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.3-2Failed-04");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_ALT_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, ""),
                new ImmutablePair(SRC_ATTR, "mock-image"));
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.3.3-3NMI-01");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_ALT_PERTINENCE_OF_INFORMATIVE_IMG_MSG,
                HtmlElementStore.INPUT_ELEMENT,
                1,
                new ImmutablePair(ALT_ATTR, "Informative input alternative"),
                new ImmutablePair(SRC_ATTR, "mock-image"));

        
        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.3.3-4NA-01"));
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.3.3-4NA-02"));
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-03----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.3.3-4NA-03"));
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-04----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.3.3-4NA-04"));
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-05----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.3.3-4NA-05"));
        
    }

}