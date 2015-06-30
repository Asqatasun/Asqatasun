/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
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
 *  Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.rules.elementchecker.element;

import org.apache.commons.lang3.tuple.Pair;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.TestSolutionHandler;
import org.tanaguru.rules.elementchecker.ElementCheckerImpl;

/**
 * This class checks whether an element is present on the page. The testSolution
 * when at least one element is detected can be overridden by constructor
 * argument (default is failed). The testSolution when no element is detected
 * can be overridden by constructor argument (default is passed). 
 *
 */
public class ElementUnicityChecker extends ElementCheckerImpl {

    /**
     * Constructor
     *
     * @param solutionOnElementUniquePair
     * @param solutionOnElementNotUniquePair
     * @param eeAttributeNameList
     */
    public ElementUnicityChecker(
            Pair<TestSolution,String> solutionOnElementUniquePair,
            Pair<TestSolution,String> solutionOnElementNotUniquePair,
            String... eeAttributeNameList) {
        super(solutionOnElementUniquePair, solutionOnElementNotUniquePair, eeAttributeNameList);
    }
    
    @Override
    public void doCheck(
            SSPHandler sspHandler,
            Elements elements,
            TestSolutionHandler testSolutionHandler) {
        if (elements.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        checkElementPresence(elements, testSolutionHandler);
    }

    /**
     * This methods checks whether a given element is present on the page.
     *
     * @param elements
     * @param testSolutionHandler
     */
    private void checkElementPresence(
            Elements elements,
            TestSolutionHandler testSolutionHandler) {

        TestSolution checkResult = getSuccessSolution();
        if (elements.size() == 1) {
            for (Element el : elements) {
                addSourceCodeRemark(getSuccessSolution(),el,getSuccessMsgCode());
            }
        } else {
            checkResult = getFailureSolution();
            for (Element el : elements) {
                addSourceCodeRemark(getFailureSolution(),el,getFailureMsgCode());
            }
        }
        testSolutionHandler.addTestSolution(checkResult);
    }
}