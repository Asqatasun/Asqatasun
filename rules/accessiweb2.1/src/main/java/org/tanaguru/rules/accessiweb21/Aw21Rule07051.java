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
import org.w3c.dom.Node;

/**
 * Does each script initiating a change of context pass one of the conditions below?
 *  <ul>
 *      <li> The user is warned by a text about the script action and the kind of change before it is activated</li>
 * 	<li> The change of context is initiated by an explicit button (input of type submit or button) </li>
 * 	<li> The change of context is initiated by an explicit link</li>
 *  </ul>
 * @author jkowalczyk
 */
public class Aw21Rule07051 extends AbstractPageRuleImplementation {

    public static final String ERROR_MESSAGE_CODE = "ContextChangedScriptDetected";
    private static final String XPATH_EXPR = 
            "//SELECT[@onchange] | " +
            "//SELECT[ancestor::FORM[not (descendant::INPUT[@type='submit']) " +
            "and not (descendant::BUTTON)]] ";
    /**
     *
     */
    public Aw21Rule07051() {
        super();
    }

    /**
     *
     * @param sspHandler
     * @return
     */
    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        TestSolution testSolution = TestSolution.NEED_MORE_INFO;
        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR);
        for (Node node : sspHandler.getSelectedElementList()) {
            sspHandler.getProcessRemarkService().addSourceCodeRemark(
                    testSolution,
                    node,
                    ERROR_MESSAGE_CODE,
                    node.getNodeName());
        }

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                sspHandler.getRemarkList());

        return processResult;
    }
}
