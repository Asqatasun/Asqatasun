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
public class SeoRule01081Test extends AbstractUrlRuleImplementationTestCase {

    public SeoRule01081Test(String testName) {
        super(testName);
    }

    /**
     * Test to validate a simple url without path.
     */
    public void testUrlValid() {
        // start by initialising the mock context
        setParameterToInitMockMethod("http://myurl.com/", TestSolution.PASSED);

        SeoRule01081 test = new SeoRule01081();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);
    }

    /**
     * Test to validate an url with a specified page after the slash character.
     */
    public void testUrlValidWithSimplePath() {
        setParameterToInitMockMethod("http://myurl.com/myPage", TestSolution.PASSED);

        SeoRule01081 test = new SeoRule01081();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);
    }

    /**
     * Test to validate a wrong url with a parameter with underscore. The
     * parameter do not invalidate the test : it's a parameter, not a path.
     */
    public void testUrlWithUnderscoreInParameter() {
        setParameterToInitMockMethod("http://myurl.com/?my_Page", TestSolution.PASSED);

        SeoRule01081 test = new SeoRule01081();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);
    }

    /**
     * Test to invalidate a simple url with path and underscore character.
     */
    public void testUrlInvalid() {
        setParameterToInitMockMethod("http://myurl.com/my_page", TestSolution.FAILED);

        SeoRule01081 test = new SeoRule01081();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);
    }

    /**
     * Test to invalidate a complexe url with the underscore character in the
     * path.
     */
    public void testComplexeUrlInvalid() {
        // start by initialising the mock context
        setParameterToInitMockMethod("http://myurl.com/a/b/c/index_2.html", TestSolution.FAILED);

        SeoRule01081 test = new SeoRule01081();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);
    }

    /**
     * Test to invalidate a complexe url with the underscore character in the
     * path.
     */
    public void testMalformedUrl() {
        // start by initialising the mock context
        setParameterToInitMockMethod("my_url.com/a/b/c/index2.html", TestSolution.NOT_APPLICABLE);

        SeoRule01081 test = new SeoRule01081();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);
    }

    /**
     * Test to invalidate a complexe url with the underscore character in the
     * middle.
     */
    public void testUrlInValidWithUnderscore() {
        // start by initialising the mock context
        setParameterToInitMockMethod("http://myurl.com/a/b_2/c/index.html", TestSolution.FAILED);

        SeoRule01081 test = new SeoRule01081();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);
    }

    /**
     * Test to invalidate an url with a simple parameter without value
     */
    public void testComplexeUrlValid() {
        // start by initialising the mock context
        setParameterToInitMockMethod("http://myurl.com/a/b/c/index.html?param=myParam", TestSolution.PASSED);

        SeoRule01081 test = new SeoRule01081();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);
    }

    /**
     * Test to invalidate an url with a simple parameter without value
     */
    public void testComplexeInvalidSyntaxUrl() {
        // start by initialising the mock context
        setParameterToInitMockMethod("http://myurl.com/a?param=true/b/c/index.html", TestSolution.PASSED);

        SeoRule01081 test = new SeoRule01081();
        test.setProcessResultDataService(mockProcessResultDataService);
        test.setTest(mockTest);

        ProcessResult processResult = test.processImpl(mockSspHandler);

        assertEquals(mockDefiniteResult, processResult);
    }

    private void setParameterToInitMockMethod(String url, TestSolution solution) {
        initMocks(url,
                RemarkMessageStore.URL_PATH_UNDERSCORE_DETECTED,
                solution);
    }
}