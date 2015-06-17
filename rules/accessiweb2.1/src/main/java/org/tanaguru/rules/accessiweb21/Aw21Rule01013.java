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

import org.tanaguru.entity.audit.DefiniteResult;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.w3c.dom.Node;

/**
 * Does each form button (input tag with the type="image" attribute)
 * have an alt attribute?
 * @author jkowalczyk
 */
public class Aw21Rule01013 extends AbstractPageRuleImplementation {

    private static final String MESSAGE_CODE = "AttributeMissing";

    private static final String XPATH_EXPR =
            "//INPUT[(@type = 'image') and @alt]";

    private static final String XPATH_EXPR2 =
            "//INPUT[(@type = 'image') and not(@alt)]";

    public Aw21Rule01013(){
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {

        int testedElementCounter = 0;
        
        TestSolution checkResult = TestSolution.NOT_APPLICABLE;

        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR);
        testedElementCounter += sspHandler.getSelectedElementNumber();
        if (!sspHandler.isSelectedElementsEmpty()) {
            checkResult = TestSolution.PASSED;
        }

        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR2);
        if (!sspHandler.isSelectedElementsEmpty()) {
            testedElementCounter += sspHandler.getSelectedElementNumber();
            checkResult = TestSolution.FAILED;
            for (Node node : sspHandler.getSelectedElementList()){
                sspHandler.getProcessRemarkService().addSourceCodeRemark(
                        checkResult,
                        node,
                        MESSAGE_CODE,
                        NodeAndAttributeKeyStore.ALT_ATTR);
            }
        }

        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                checkResult,
                sspHandler.getRemarkList());

        result.setElementCounter(testedElementCounter);

        return result;
    }
}
