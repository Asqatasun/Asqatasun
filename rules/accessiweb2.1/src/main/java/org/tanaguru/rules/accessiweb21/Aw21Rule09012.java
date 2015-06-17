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
import org.tanaguru.entity.audit.EvidenceElement;
import org.tanaguru.entity.audit.ProcessResult;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.tanaguru.service.ProcessRemarkService;
import org.w3c.dom.Node;


/**
 * This rule tests if the hierarchy between titles ("h" tags) is pertinent
 * @author jkowalczyk
 */
public class Aw21Rule09012 extends AbstractPageRuleImplementation {

    private static final String XPATH_EXPR = "//BODY/descendant::*[contains(name(),'H')]";
    private static final int HEADER_INDEX_IN_TAG = 1;
    private static final String REMARK_MESSAGE_CODE =
            "HeaderTagNotHierarchicallyWelldefined";
    private ProcessRemarkService processRemarkService;

    public Aw21Rule09012() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        this.processRemarkService = sspHandler.getProcessRemarkService();
        sspHandler.beginSelection().domXPathSelectNodeSet(XPATH_EXPR);

        TestSolution testSolution = TestSolution.NOT_APPLICABLE;

        int previousNodeIndex = -1;
        int indexOfReference = -1;
        int currentHeaderNodeIndex;
        boolean selectionHasOneElement = false;

        for (Node node : sspHandler.getSelectedElementList()) {
            currentHeaderNodeIndex = getHeaderIndex(node.getNodeName());

            if (currentHeaderNodeIndex != -1) {
                //if the current node is not the first encountered node and the currentheaderNodeIndex is allowed
                if (previousNodeIndex != -1 ) {
                    if (currentHeaderNodeIndex - previousNodeIndex >= 2) {
                        testSolution = testSolution.FAILED;
                        addSourceCodeRemark(
                                testSolution,
                                node,
                                processRemarkService.getEvidenceElement(
                                    EvidenceKeyStore.PREVIOUS_H_TAG_INDEX_EE,
                                    String.valueOf(previousNodeIndex)));
                    }
                    if (currentHeaderNodeIndex < indexOfReference) {
                        testSolution = testSolution.FAILED;
                        addSourceCodeRemark(
                                testSolution,
                                node,
                                processRemarkService.getEvidenceElement(
                                    EvidenceKeyStore.FIRST_H_TAG_INDEX_EE,
                                    String.valueOf(indexOfReference)));
                    }
                // the first encountered header
                } else {
                    indexOfReference = currentHeaderNodeIndex;
                }
                selectionHasOneElement = true;
                previousNodeIndex = currentHeaderNodeIndex;
            }
        }

        if (selectionHasOneElement && testSolution != TestSolution.FAILED) {
            testSolution = TestSolution.PASSED;
        }

        ProcessResult processResult = definiteResultFactory.create(
                test,
                sspHandler.getPage(),
                testSolution,
                sspHandler.getRemarkList());
        processResult.setElementCounter(sspHandler.getSelectedElementList().size());
        return processResult;

    }

    private int getHeaderIndex(String header) {
        try {
            int index = Integer.parseInt(header.substring(HEADER_INDEX_IN_TAG));
            if ( index>0 && index<=6) {
                return index;
            } else {
                return -1;
            }
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

    private void addSourceCodeRemark(
            TestSolution testSolution,
            Node node,
            EvidenceElement otherEvidenceElement) {

        List<EvidenceElement> evidenceElementList =
                new ArrayList<EvidenceElement>();
        EvidenceElement defaultEvidenceElement =
                processRemarkService.getEvidenceElementFactory().create();
        defaultEvidenceElement.setValue(node.getNodeName());
        defaultEvidenceElement.setEvidence(
                processRemarkService.getEvidenceDataService().
                findByCode(ProcessRemarkService.DEFAULT_EVIDENCE));
        evidenceElementList.add(defaultEvidenceElement);
        evidenceElementList.add(otherEvidenceElement);

        processRemarkService.addSourceCodeRemark(
                testSolution,
                node,
                REMARK_MESSAGE_CODE,
                evidenceElementList);
    }

}
