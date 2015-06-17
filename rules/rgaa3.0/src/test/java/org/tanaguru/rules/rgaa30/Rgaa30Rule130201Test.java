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
package org.tanaguru.rules.rgaa30;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.tanaguru.entity.audit.*;
import org.tanaguru.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.tanaguru.rules.keystore.AttributeStore;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 13-2-1 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule130201Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule130201Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.tanaguru.rules.rgaa30.Rgaa30Rule130201");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.13.02.01-3NMI-01");
        addWebResource("Rgaa30.Test.13.02.01-3NMI-02");
        addWebResource("Rgaa30.Test.13.02.01-3NMI-03");
        addWebResource("Rgaa30.Test.13.02.01-3NMI-04");
        addWebResource("Rgaa30.Test.13.02.01-3NMI-05");
        addWebResource("Rgaa30.Test.13.02.01-3NMI-06");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.13.02.01-3NMI-01");
        checkResultIsPreQualified(processResult, 0, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_JS_PROMPT_A_NEW_WINDOW_MSG,
                HtmlElementStore.EMBED_ELEMENT,
                1);
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.13.02.01-3NMI-02");
        checkResultIsPreQualified(processResult, 0, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_JS_PROMPT_A_NEW_WINDOW_MSG,
                HtmlElementStore.EMBED_ELEMENT,
                1);
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-03---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.13.02.01-3NMI-03");
        checkResultIsPreQualified(processResult, 0, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_JS_PROMPT_A_NEW_WINDOW_MSG,
                HtmlElementStore.EMBED_ELEMENT,
                1);
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-04---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.13.02.01-3NMI-04");
        checkResultIsPreQualified(processResult, 1, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG,
                HtmlElementStore.A_ELEMENT,
                1,
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "My link"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-05---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.13.02.01-3NMI-05");
        checkResultIsPreQualified(processResult, 4, 4);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG,
                HtmlElementStore.A_ELEMENT,
                1, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "My link 1"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG,
                HtmlElementStore.A_ELEMENT,
                2, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "My link 2"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG,
                HtmlElementStore.A_ELEMENT,
                3, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "My link 3"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "Link 3 title"));
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_USER_IS_WARNED_WHEN_NEW_WINDOW_OPEN_MSG,
                HtmlElementStore.A_ELEMENT,
                4, 
                new ImmutablePair(HtmlElementStore.TEXT_ELEMENT2, "My link 4"),
                new ImmutablePair(AttributeStore.TITLE_ATTR, "attribute-absent"));
        
        //----------------------------------------------------------------------
        //------------------------------3NMI-06---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.13.02.01-3NMI-06");
        checkResultIsPreQualified(processResult, 0, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.NEED_MORE_INFO,
                RemarkMessageStore.CHECK_JS_PROMPT_A_NEW_WINDOW_MSG,
                HtmlElementStore.EMBED_ELEMENT,
                1);
    }

}
