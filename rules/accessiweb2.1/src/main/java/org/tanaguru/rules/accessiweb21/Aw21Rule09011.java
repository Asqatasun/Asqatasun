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


import java.util.ArrayList;
import java.util.List;
import org.tanaguru.entity.audit.ProcessRemark;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleImplementation;


/**
 * This rule tests if each page has at least one <h1> tag
 * @author jkowalczyk
 */
public class Aw21Rule09011 extends AbstractPageRuleImplementation {

    private static final String MESSAGE_CODE = "H1TagMissing";

    public Aw21Rule09011() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        sspHandler.beginSelection().selectDocumentNodes(NodeAndAttributeKeyStore.BODY_NODE).
                selectChildNodesRecursively(NodeAndAttributeKeyStore.H1_NODE);
        
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();
        TestSolution testSolution = TestSolution.PASSED;

        if (sspHandler.getSelectedElementList().isEmpty()) {
            testSolution = TestSolution.FAILED;
            processRemarkList.add(sspHandler.getProcessRemarkService().
                    createProcessRemark(TestSolution.FAILED,MESSAGE_CODE));
        }

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                processRemarkList);

        return processResult;

    }


}
