/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
package org.asqatasun.rules.rgaa30;

import org.asqatasun.entity.audit.SSP;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.rules.keystore.RemarkMessageStore;
import org.asqatasun.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;

import java.util.stream.Stream;

/**
 * Unit test class for the implementation of the rule 1.10.1 of the referential Rgaa 3.0.
 *
 * @author
 */
public class Rgaa30Rule011001Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule011001Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName(
                "org.asqatasun.rules.rgaa30.Rgaa30Rule011001");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.1.10.1-3NMI-01");
        addWebResource("Rgaa30.Test.1.10.1-3NMI-02");
        addWebResource("Rgaa30.Test.1.10.1-4NA-01");
    }

    @Override
    protected void setProcess() {
        WebResource webResource = webResourceMap.get("Rgaa30.Test.1.10.1-3NMI-01");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");

        //----------------------------------------------------------------------
        //------------------------------3NMI-01---------------------------------
        //----------------------------------------------------------------------
        ProcessResult processResult = processPageTest("Rgaa30.Test.1.10.1-3NMI-01");
        checkResultIsPreQualified(processResult, 0, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.HTML5_DOCTYPE_DETECTED_CHECK_MANUALLY, 
                1);


        //----------------------------------------------------------------------
        //------------------------------3NMI-02---------------------------------
        //----------------------------------------------------------------------
        processResult = processPageTest("Rgaa30.Test.1.10.1-3NMI-02");
        checkResultIsPreQualified(processResult, 0, 1);
        checkRemarkIsPresent(
                processResult, 
                TestSolution.NEED_MORE_INFO, 
                RemarkMessageStore.DOCTYPE_ABSENT_CHECK_HTML5, 
                1);
                
        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa30.Test.1.10.1-4NA-01");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<html xmlns=\"http://www.w3.org/1999/xhtml\" lang=\"en\" xml:lang=\"en\">");
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.1.10.1-4NA-01"));
    }

}
