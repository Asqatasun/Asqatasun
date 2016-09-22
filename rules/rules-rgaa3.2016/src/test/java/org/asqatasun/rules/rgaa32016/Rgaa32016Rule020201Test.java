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

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.rules.rgaa32016.test.Rgaa32016RuleImplementationTestCase;
import static org.asqatasun.rules.keystore.AttributeStore.TITLE_ATTR;
import org.asqatasun.rules.keystore.HtmlElementStore;
import org.asqatasun.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 2.2.1 of the referential RGAA 3.2016
 *
 * @author jkowalczyk
 */
public class Rgaa32016Rule020201Test extends Rgaa32016RuleImplementationTestCase {

    /**
     * Default constructor
     */
    public Rgaa32016Rule020201Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.asqatasun.rules.rgaa32016.Rgaa32016Rule020201");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa32016.Test.2.2.1-2Failed-01");
        addWebResource("Rgaa32016.Test.2.2.1-2Failed-02");
        addWebResource("Rgaa32016.Test.2.2.1-2Failed-03");
        addWebResource("Rgaa32016.Test.2.2.1-2Failed-04");
        addWebResource("Rgaa32016.Test.2.2.1-3NMI-01");
        addWebResource("Rgaa32016.Test.2.2.1-4NA-01");
        addWebResource("Rgaa32016.Test.2.2.1-4NA-02");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa32016.Test.2.2.1-2Failed-01");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_TITLE_OF_IFRAME_MSG,
                HtmlElementStore.IFRAME_ELEMENT,
                1,
                new ImmutablePair(TITLE_ATTR, ""));
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.2.2.1-2Failed-02");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_TITLE_OF_IFRAME_MSG,
                HtmlElementStore.IFRAME_ELEMENT,
                1,
                new ImmutablePair(TITLE_ATTR, ""));
        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-03------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.2.2.1-2Failed-03");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_TITLE_OF_IFRAME_MSG,
                HtmlElementStore.IFRAME_ELEMENT,
                1,
                new ImmutablePair(TITLE_ATTR, "!§:;.,?%*\\~/@()[]^_°=+-"));

        
        //----------------------------------------------------------------------
        //------------------------------2Failed-04------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.2.2.1-2Failed-04");
        checkResultIsFailed(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.NOT_PERTINENT_TITLE_OF_IFRAME_MSG,
                HtmlElementStore.IFRAME_ELEMENT,
                1,
                new ImmutablePair(TITLE_ATTR, "mock-iframe.html"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa32016.Test.2.2.1-3NMI-01");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_TITLE_OF_IFRAME_PERTINENCE_MSG,
                HtmlElementStore.IFRAME_ELEMENT,
                1,
                new ImmutablePair(TITLE_ATTR, "title of iframe"));

        //----------------------------------------------------------------------
        //------------------------------4NA-01----------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.2.2.1-4NA-01"));
        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-02----------------------------------
        //----------------------------------------------------------------------        
        checkResultIsNotApplicable(processPageTest("Rgaa32016.Test.2.2.1-4NA-02"));
    }
    
}