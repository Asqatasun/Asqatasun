/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
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

import org.asqatasun.entity.audit.ProcessResult;
import org.asqatasun.entity.audit.SSP;
import org.asqatasun.entity.audit.TestSolution;
import org.asqatasun.entity.subject.WebResource;
import org.asqatasun.rules.rgaa30.test.Rgaa30RuleImplementationTestCase;
import org.asqatasun.rules.keystore.RemarkMessageStore;

/**
 * Unit test class for the implementation of the rule 8.1.2 of the referential Rgaa 3.0.
 *
 * @author jkowalczyk
 */
public class Rgaa30Rule080102Test extends Rgaa30RuleImplementationTestCase {

    /**
     * Default constructor
     * @param testName
     */
    public Rgaa30Rule080102Test (String testName){
        super(testName);
    }

    @Override
    protected void setUpRuleImplementationClassName() {
        setRuleImplementationClassName("org.asqatasun.rules.rgaa30.Rgaa30Rule080102");
    }

    @Override
    protected void setUpWebResourceMap() {
        addWebResource("Rgaa30.Test.08.01.02-1Passed-01");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-02");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-03");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-04");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-05");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-06");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-07");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-08");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-09");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-10");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-11");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-12");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-13");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-14");
        addWebResource("Rgaa30.Test.08.01.02-1Passed-15");
        addWebResource("Rgaa30.Test.08.01.02-2Failed-01");
        addWebResource("Rgaa30.Test.08.01.02-2Failed-02");
        addWebResource("Rgaa30.Test.08.01.02-4NA-01");
    }

    @Override
    protected void setProcess() {
        //----------------------------------------------------------------------
        //------------------------------1Passed-01------------------------------
        //----------------------------------------------------------------------
        WebResource webResource = webResourceMap.get("Rgaa30.Test.08.01.02-1Passed-01");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-01"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-02------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa30.Test.08.01.02-1Passed-02");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE HTML PUBLIC " +
            "\"-//W3C//DTD HTML 4.01 Transitional//EN\" " +
            "\"http://www.w3.org/TR/html4/loose.dtd\">");
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-02"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-03------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa30.Test.08.01.02-1Passed-03");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\" \"http://www.w3.org/TR/html4/frameset.dtd\">");
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-03"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-04------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa30.Test.08.01.02-1Passed-04");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-04"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-05------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa30.Test.08.01.02-1Passed-05");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-05"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-06------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa30.Test.08.01.02-1Passed-06");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Frameset//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd\">");
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-06"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-07------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa30.Test.08.01.02-1Passed-07");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">");
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-07"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-08------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa30.Test.08.01.02-1Passed-08");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE " +
            "html " +
            "PUBLIC " +
            "\"-//W3C//DTD XHTML Basic 1.1//EN\" " +
            "\"http://www.w3.org/TR/xhtml-basic/xhtml-basic11.dtd\">");
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-08"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-09------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa30.Test.08.01.02-1Passed-09");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE math PUBLIC \"-//W3C//DTD MathML 2.0//EN\" \"http://www.w3.org/TR/MathML2/dtd/mathml2.dtd\">");
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-09"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-10------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa30.Test.08.01.02-1Passed-10");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE math SYSTEM \"http://www.w3.org/Math/DTD/mathml1/mathml.dtd\">");
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-10"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-11------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa30.Test.08.01.02-1Passed-11");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1 plus MathML 2.0 plus SVG 1.1//EN\" \"http://www.w3.org/2002/04/xhtml-math-svg/xhtml-math-svg.dtd\">");
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-11"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-12------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa30.Test.08.01.02-1Passed-12");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE svg:svg PUBLIC \"-//W3C//DTD XHTML 1.1 plus MathML 2.0 plus SVG 1.1//EN\" \"http://www.w3.org/2002/04/xhtml-math-svg/xhtml-math-svg.dtd\">");
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-12"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-13------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa30.Test.08.01.02-1Passed-13");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html>");
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-13"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-14------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa30.Test.08.01.02-1Passed-14");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!doctype html>");
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-14"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------1Passed-15------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa30.Test.08.01.02-1Passed-15");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE HTML>");
        checkResultIsPassed(processPageTest("Rgaa30.Test.08.01.02-1Passed-15"),0);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-01------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa30.Test.08.01.02-2Failed-01");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/sxhtml1/DTD/xhtml1-strict.dtd\">");
        ProcessResult processResult = processPageTest("Rgaa30.Test.08.01.02-2Failed-01");
        checkResultIsFailed(processResult, 0, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.INVALID_DOCTYPE_MSG,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------2Failed-02------------------------------
        //----------------------------------------------------------------------
        webResource = webResourceMap.get("Rgaa30.Test.08.01.02-2Failed-02");
        ((SSP)contentMap.get(webResource).get(0)).setDoctype("<!doctype html public \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">");
        processResult = processPageTest("Rgaa30.Test.08.01.02-2Failed-02");
        checkResultIsFailed(processResult, 0, 1);
        checkRemarkIsPresent(
                processResult,
                TestSolution.FAILED,
                RemarkMessageStore.INVALID_DOCTYPE_MSG,
                1);        
        
        //----------------------------------------------------------------------
        //------------------------------4NA-01------------------------------
        //----------------------------------------------------------------------
        checkResultIsNotApplicable(processPageTest("Rgaa30.Test.08.01.02-4NA-01"));
    }

}
