package org.opens.tanaguru.rules.seo.test;

import org.opens.tanaguru.rules.test.AbstractRuleImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public abstract class SeoRuleImplementationTestCase extends AbstractRuleImplementationTestCase {

    private static final String TESTCASE_FILE_PATH = "../seo-testcases/src/main/resources/testcases/";
    private static final String INPUT_FILE_DATA_NAME = "../seo-testcases/src/main/resources/dataSets/nomenclatureFlatXmlDataSet.xml";

    public SeoRuleImplementationTestCase(String testName) {
        super(testName, INPUT_FILE_DATA_NAME, TESTCASE_FILE_PATH);
    }
    
    public SeoRuleImplementationTestCase(String testName, String inputFileDataName) {
        super(testName, inputFileDataName, TESTCASE_FILE_PATH);
    }

}