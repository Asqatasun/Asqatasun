/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2012  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.rules.accessiweb21;

import java.util.ArrayList;
import java.util.List;
import org.opens.tanaguru.entity.audit.DefiniteResult;
import org.opens.tanaguru.entity.audit.ProcessRemark;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.w3c.dom.Node;

/**
 * This rule tests if all <a> tags have a text content
 * @author jkowalczyk
 */
public class Aw21Rule06061 extends AbstractPageRuleImplementation {

    public static final String LINK_NODE =
            "//A";

    public static final String EMPTY_LINK_NODE =
            "//A[not(@name or @id) "
            + "and not(normalize-space(.)) "
            + "and not(descendant::IMG[normalize-space(@alt)]) "
            + "and not(descendant::*[normalize-space(.) and name()!='IMG'])]";

    public Aw21Rule06061() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        TestSolution testSolution = TestSolution.NOT_APPLICABLE;
        int elementCounter = 0;
        List<ProcessRemark> processRemarkList = new ArrayList<ProcessRemark>();
        if (!sspHandler.beginSelection().domXPathSelectNodeSet(LINK_NODE).getSelectedElementList().isEmpty()) {
            elementCounter = sspHandler.getSelectedElementNumber();
            List<Node> emptyLinkNode = sspHandler.beginSelection().
                    domXPathSelectNodeSet(EMPTY_LINK_NODE).getSelectedElementList();
            sspHandler.getProcessRemarkService().
                addEvidenceElement(EvidenceKeyStore.HREF_EE);
            if (emptyLinkNode.isEmpty()) {
                testSolution = TestSolution.PASSED;
            } else {
                testSolution = TestSolution.FAILED;
                for (Node node : emptyLinkNode) {
                    sspHandler.getProcessRemarkService().addSourceCodeRemark(
                            testSolution,
                            node,
                            "ValueEmpty",
                            node.getNodeName());
                }
            }
        }
        
        processRemarkList.addAll(sspHandler.getRemarkList());
        DefiniteResult result = definiteResultFactory.create(
                test,
                sspHandler.getSSP().getPage(),
                testSolution,
                processRemarkList);
        result.setElementCounter(elementCounter);
        return result;
    }

}