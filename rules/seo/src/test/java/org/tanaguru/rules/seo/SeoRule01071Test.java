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
package org.tanaguru.rules.seo;

import static junit.framework.Assert.assertEquals;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.rules.keystore.RemarkMessageStore;
import org.tanaguru.rules.test.AbstractUrlRuleImplementationTestCase;

/**
 *
 * @author alingua
 */
public class SeoRule01071Test extends AbstractUrlRuleImplementationTestCase {

    public SeoRule01071Test(String testName) {
        super(testName);
    }

    /**
     * Test to validate a simple url without parameter.
     */
    public void testUrlValid() {
        // start by initialising the mock context
        setParameterToInitMockMethod("http://myurl.com", TestSolution.PASSED);
        SeoRule01071 test = new SeoRule01071();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);

    }

    /**
     * Test to validate an url with a specified page after the slash character
     */
    public void testUrlValidWithStringAfterSlash() {
        setParameterToInitMockMethod("http://myurl.com/myPage", TestSolution.PASSED);
        SeoRule01071 test = new SeoRule01071();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);
    }

    /**
     * Test to validate a wrong url with a bad parameter. The '?' is not in the
     * url.
     *
     * This is a special "weird" testcase. Having an "&" without a "?" is not
     * legal according to RFC 3986 http://www.ietf.org/rfc/rfc3986.txt. Thus the
     * string "&myPage" is understood as page, and without parameter.
     */
    public void testUrlWithWrongParameter() {
        setParameterToInitMockMethod("http://myurl.com/&myPage", TestSolution.PASSED);
        SeoRule01071 test = new SeoRule01071();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);
    }

    /**
     * Test to invalidate a simple url with parameter
     */
    public void testUrlInvalid() {
        // start by initialising the mock context
        setParameterToInitMockMethod("http://myurl.com/?firstParam=myParam", TestSolution.FAILED);
        SeoRule01071 test = new SeoRule01071();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);
    }

    /**
     * Test to invalidate a complexe url with parameter
     */
    public void testComplexUrlInvalid() {
        // start by initialising the mock context
        setParameterToInitMockMethod("http://myurl.com/a/b/c/index.html?firstParam=myParam", TestSolution.FAILED);
        SeoRule01071 test = new SeoRule01071();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);
    }

    /**
     * Test to invalidate a complexe url with parameter
     *
     * This kind of URL is frequently used by Javascript frameworks (such as Ext
     * Sensha JS). This URL does *not* have a parameter. This is a fragment,
     * that will be parsed client-side by the JS framework.
     */
    public void testComplexUrlInvalidWithAnchor() {
        // start by initialising the mock context
        setParameterToInitMockMethod("http://myurl.com/#/a/b/c/index.html?firstParam=myParam", TestSolution.PASSED);

        SeoRule01071 test = new SeoRule01071();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);
    }

    /**
     * Test to invalidate a complexe url with parameter
     *
     * URL with anchor AND parameter
     */
    public void testComplexUrlInvalidWithAnchor2() {
        // start by initialising the mock context
        setParameterToInitMockMethod("http://myurl.com/a/b/c/index.html?first#Param=myParam", TestSolution.FAILED);

        SeoRule01071 test = new SeoRule01071();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);
    }

    /**
     * Test to validate a complexe url with the '?' character withou information
     * after
     */
    public void testUrlValidWithJustParameterCharacter() {
        // start by initialising the mock context
        setParameterToInitMockMethod("http://myurl.com/a/b/c/index.html?", TestSolution.PASSED);

        SeoRule01071 test = new SeoRule01071();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);
    }

    /**
     * Test to invalidate an url with a simple parameter without value
     */
    public void testUrlInValidWithASimpleParameter() {
        // start by initialising the mock context
        setParameterToInitMockMethod("http://myurl.com/a/b/c/index.html?myParam", TestSolution.FAILED);

        SeoRule01071 test = new SeoRule01071();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);
    }

    private void setParameterToInitMockMethod(String url, TestSolution solution) {
        initMocks(url,
                RemarkMessageStore.URL_PARAMETERS_DETECTED,
                solution);
    }
}