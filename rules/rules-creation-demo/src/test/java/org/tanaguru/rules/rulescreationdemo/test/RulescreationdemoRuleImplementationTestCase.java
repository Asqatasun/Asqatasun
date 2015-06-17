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
package org.tanaguru.rules.rulescreationdemo.test;

import org.tanaguru.rules.test.AbstractRuleImplementationTestCase;

/**
 *
 * @author
 */
public abstract class RulescreationdemoRuleImplementationTestCase extends AbstractRuleImplementationTestCase {

    private static final String TESTCASE_FILE_PATH = "src/test/resources/testcases/";
    private static final String INPUT_FILE_DATA_NAME = "src/test/resources/dataSets/emptyFlatXmlDataSet.xml";

    public RulescreationdemoRuleImplementationTestCase(String testName) {
        super(testName, INPUT_FILE_DATA_NAME, TESTCASE_FILE_PATH);
    }
    
    public RulescreationdemoRuleImplementationTestCase(String testName, String inputFileDataName) {
        super(testName, inputFileDataName, TESTCASE_FILE_PATH);
    }

}