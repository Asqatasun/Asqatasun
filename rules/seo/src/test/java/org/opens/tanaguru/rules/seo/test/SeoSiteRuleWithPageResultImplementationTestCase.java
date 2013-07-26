package org.opens.tanaguru.rules.seo.test;

import org.opens.tanaguru.rules.test.AbstractSiteRuleWithPageResultImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public abstract class SeoSiteRuleWithPageResultImplementationTestCase extends AbstractSiteRuleWithPageResultImplementationTestCase {

    private static final String TESTCASE_FILE_PATH = "../seo-testcases/src/main/resources/testcases/";
    private static final String INPUT_FILE_DATA_NAME = "../seo-testcases/src/main/resources/dataSets/nomenclatureFlatXmlDataSet.xml";
    private static final String REFERENTIAL = "Seo";
    
    public SeoSiteRuleWithPageResultImplementationTestCase(String testName) {
        super(testName, INPUT_FILE_DATA_NAME, TESTCASE_FILE_PATH, REFERENTIAL);
    }
    
    public SeoSiteRuleWithPageResultImplementationTestCase(String testName, String inputFileDataName) {
        super(testName, inputFileDataName, TESTCASE_FILE_PATH, REFERENTIAL);
    }

}