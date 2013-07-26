/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
package org.opens.tanaguru.rules.seo;


import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.entity.audit.EvidenceElement;
import org.opens.tanaguru.entity.audit.ProcessResult;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.AbstractPageRuleImplementation;
import org.opens.tanaguru.service.ProcessRemarkService;


/**
 * This rule tests if the hierarchy between titles ("h" tags) is pertinent
 * @author jkowalczyk
 */
public class SeoRule07012 extends AbstractPageRuleImplementation {

    private static final String CSS_EXPR = "h1, h2, h3, h4, h5, h6";
    private static final int HEADER_INDEX_IN_TAG = 1;
    private static final String PREVIOUS_H_TAG_INDEX = "Previous-H-Tag-Index";
    private static final String FIRST_H_TAG_INDEX = "First-H-Tag-Index";
    private static final String REMARK_MESSAGE_CODE =
            "HeaderTagNotHierarchicallyWelldefined";
    private ProcessRemarkService processRemarkService;

    public SeoRule07012() {
        super();
    }

    @Override
    protected ProcessResult processImpl(SSPHandler sspHandler) {
        this.processRemarkService = sspHandler.getProcessRemarkService();
        sspHandler.beginCssLikeSelection().domCssLikeSelectNodeSet(CSS_EXPR);

        TestSolution testSolution = TestSolution.NOT_APPLICABLE;

        int previousNodeIndex = -1;
        int indexOfReference = -1;
        int currentHeaderNodeIndex;
        boolean selectionHasOneElement = false;

        for (Element element : sspHandler.getSelectedElements()) {
            currentHeaderNodeIndex = getHeaderIndex(element.nodeName());

            if (currentHeaderNodeIndex != -1) {
                //if the current node is not the first encountered node and the currentheaderNodeIndex is allowed
                if (previousNodeIndex != -1 ) {
                    if (currentHeaderNodeIndex - previousNodeIndex >= 2) {
                        testSolution = TestSolution.FAILED;
                        addSourceCodeRemark(
                                testSolution,
                                element,
                                processRemarkService.getEvidenceElement(
                                    PREVIOUS_H_TAG_INDEX,
                                    String.valueOf(previousNodeIndex)));
                    }
                    if (currentHeaderNodeIndex < indexOfReference) {
                        testSolution = TestSolution.FAILED;
                        addSourceCodeRemark(
                                testSolution,
                                element,
                                processRemarkService.getEvidenceElement(
                                    FIRST_H_TAG_INDEX,
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
        processResult.setElementCounter(sspHandler.getSelectedElementNumber());
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
            Element element,
            EvidenceElement otherEvidenceElement) {

        List<EvidenceElement> evidenceElementList =
                new ArrayList<EvidenceElement>();
        EvidenceElement defaultEvidenceElement =
                processRemarkService.getEvidenceElementFactory().create();
        defaultEvidenceElement.setValue(element.nodeName());
        defaultEvidenceElement.setEvidence(
                processRemarkService.getEvidenceDataService().
                findByCode(ProcessRemarkService.DEFAULT_EVIDENCE));
        evidenceElementList.add(defaultEvidenceElement);
        evidenceElementList.add(otherEvidenceElement);

        processRemarkService.addSourceCodeRemarkOnElement(
                testSolution,
                element,
                REMARK_MESSAGE_CODE,
                evidenceElementList);
    }

}
