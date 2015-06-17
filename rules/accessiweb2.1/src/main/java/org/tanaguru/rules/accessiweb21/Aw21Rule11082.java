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
package org.tanaguru.rules.accessiweb21;

import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleImplementation;

/**
 * In each selection list (select tag), does each list item grouping
 * (optgroup tag) have a label attribute?
 *
 * @author jkowalczyk
 */

public class Aw21Rule11082 extends AbstractPageRuleImplementation {

    /**
     * The error message code
     */
    public static final String ERROR_MESSAGE_CODE = "OptgroupWithoutLabel";

    /**
     * The xpath expression used to retrieve the elements to test
     */
    private static final String XPATH_EXPR = "//OPTGROUP[ancestor::SELECT]";

    public Aw21Rule11082() {
        super();
    }

    /**
     *
     * @param sspHandler
     * @return
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR);
        sspHandler.setMessageCode(ERROR_MESSAGE_CODE);
        TestSolution testSolution =
                sspHandler.checkAttributeExists(NodeAndAttributeKeyStore.LABEL_ATTR);

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                sspHandler.getRemarkList());

        return processResult;
    }

}