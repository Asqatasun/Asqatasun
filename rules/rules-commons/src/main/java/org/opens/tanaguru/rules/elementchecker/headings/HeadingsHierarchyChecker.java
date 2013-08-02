/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2013  Open-S Company
 * 
 *  This file is part of Tanaguru.
 * 
 *  Tanaguru is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 * 
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 *  Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.rules.elementchecker.headings;

import java.util.Iterator;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementCheckerImpl;
import static org.opens.tanaguru.rules.keystore.EvidenceStore.FIRST_H_TAG_INDEX_EE;
import static org.opens.tanaguru.rules.keystore.EvidenceStore.PREVIOUS_H_TAG_INDEX_EE;
import static org.opens.tanaguru.rules.keystore.RemarkMessageStore.HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG;

/**
 * This class checks whether the headings hierarchy is well-formed.
 * 
 */
public class HeadingsHierarchyChecker extends ElementCheckerImpl {

    private static final int HEADER_INDEX_IN_TAG = 1;

    /**
     * Constructor.
     * 
     */
    public HeadingsHierarchyChecker() {
        super();
    }
    
    @Override
    public void doCheck(
            SSPHandler sspHandler, 
            Elements elements, 
            TestSolutionHandler testSolutionHandler) {
        checkHeadingsHierarchy(elements, testSolutionHandler);
    }

    /**
     * This methods checks whether the headings hierarchy is well-structured
     * 
     * @param elements
     * @param testSolutionHandler 
     */
    private void checkHeadingsHierarchy (
            Elements elements, 
            TestSolutionHandler testSolutionHandler) {
        if (elements.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        
        TestSolution checkResult = TestSolution.PASSED;
        
        Iterator<Element> iter = elements.iterator();

        // we get the index of the first element for further test
        Element element = iter.next();
        int indexOfReference = getHeaderIndex(element);
        int currentIndex;
        int previousIndex = indexOfReference;

        while (iter.hasNext()) {
            element = iter.next();
            currentIndex = getHeaderIndex(element);
            if (currentIndex != -1) {
                if (currentIndex - previousIndex >= 2) {
                    checkResult = TestSolution.FAILED;
                    addSourceCodeRemark(
                            TestSolution.FAILED,
                            element,
                            HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG,
                            getEvidenceElement(PREVIOUS_H_TAG_INDEX_EE, "h"+String.valueOf(previousIndex)));
                } else if (currentIndex < indexOfReference) {
                    checkResult = TestSolution.FAILED;
                    addSourceCodeRemark(
                            TestSolution.FAILED,
                            element,
                            HEADER_NOT_HIERARCHICALLY_WELL_DEFINED_MSG,
                            getEvidenceElement(FIRST_H_TAG_INDEX_EE, "h"+String.valueOf(indexOfReference)));
                }
                previousIndex = currentIndex;
            }
        }
        testSolutionHandler.addTestSolution(checkResult);
    }

    /**
     * 
     * @param header
     * @return the index of the given heading element
     */
    private int getHeaderIndex(Element element) {
        try {
            int index = Integer.parseInt(element.tagName().substring(HEADER_INDEX_IN_TAG));
            if ( index>0 && index<=6) {
                return index;
            } else {
                return -1;
            }
        } catch (NumberFormatException ex) {
            return -1;
        }
    }

}