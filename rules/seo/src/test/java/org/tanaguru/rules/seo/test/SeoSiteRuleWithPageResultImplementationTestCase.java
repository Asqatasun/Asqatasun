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
package org.tanaguru.rules.seo.test;

import org.tanaguru.rules.test.AbstractSiteRuleWithPageResultImplementationTestCase;

/**
 *
 * @author jkowalczyk
 */
public abstract class SeoSiteRuleWithPageResultImplementationTestCase extends AbstractSiteRuleWithPageResultImplementationTestCase {

    private static final String TESTCASE_FILE_PATH = "src/test/resources/testcases/";
    private static final String INPUT_FILE_DATA_NAME = "src/test/resources/dataSets/nomenclatureFlatXmlDataSet.xml";
    private static final String REFERENTIAL = "Seo";
    
    public SeoSiteRuleWithPageResultImplementationTestCase(String testName) {
        super(testName, INPUT_FILE_DATA_NAME, TESTCASE_FILE_PATH, REFERENTIAL);
    }
    
    public SeoSiteRuleWithPageResultImplementationTestCase(String testName, String inputFileDataName) {
        super(testName, inputFileDataName, TESTCASE_FILE_PATH, REFERENTIAL);
    }

}