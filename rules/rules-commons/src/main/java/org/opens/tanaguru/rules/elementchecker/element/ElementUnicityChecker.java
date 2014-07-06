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
package org.opens.tanaguru.rules.elementchecker.element;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.TestSolutionHandler;
import org.opens.tanaguru.rules.elementchecker.ElementCheckerImpl;

/**
 * This class checks whether an element is present on the page. The testSolution
 * when at least one element is detected can be overridden by constructor
 * argument (default is failed). The testSolution when no element is detected
 * can be overridden by constructor argument (default is passed). 
 *
 */
public class ElementUnicityChecker extends ElementCheckerImpl {

    /**
     * The message code associated with a processRemark when the element is
     * detected on the page
     */
    private final String messageCodeOnElementUnique;
    /**
     * The message code associated with a processRemark when the element is not
     * found on the page
     */
    private final String messageCodeOnElementNotUnique;

    /**
     * Constructor.
     *
     * @param messageCodeOnElementUnique
     * @param messageCodeOnElementNotUnique
     */
    public ElementUnicityChecker(
            String messageCodeOnElementUnique,
            String messageCodeOnElementNotUnique) {
        super();
        this.messageCodeOnElementUnique = messageCodeOnElementUnique;
        this.messageCodeOnElementNotUnique = messageCodeOnElementNotUnique;
    }

    /**
     * Constructor.
     *
     * @param messageCodeOnElementUnique
     * @param messageCodeOnElementNotUnique
     * @param eeAttributeNameList
     */
    public ElementUnicityChecker(
            String messageCodeOnElementUnique,
            String messageCodeOnElementNotUnique,
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.messageCodeOnElementUnique = messageCodeOnElementUnique;
        this.messageCodeOnElementNotUnique = messageCodeOnElementNotUnique;
    }

    /**
     * Constructor.
     *
     * @param messageCodeOnElementUnique
     * @param solutionOnElementUnique
     * @param messageCodeOnElementNotUnique
     * @param solutionOnElementNotUnique
     */
    public ElementUnicityChecker(
            String messageCodeOnElementUnique,
            TestSolution solutionOnElementUnique,
            String messageCodeOnElementNotUnique,
            TestSolution solutionOnElementNotUnique) {
        super();
        this.messageCodeOnElementUnique = messageCodeOnElementUnique;
        setSuccessSolution(solutionOnElementUnique);
        this.messageCodeOnElementNotUnique = messageCodeOnElementNotUnique;
        setFailureSolution(solutionOnElementNotUnique);
    }

    /**
     * Constructor
     *
     * @param messageCodeOnElementUnique
     * @param solutionOnElementUnique
     * @param messageCodeOnElementNotUnique
     * @param solutionOnElementNotUnique
     * @param eeAttributeNameList
     */
    public ElementUnicityChecker(
            String messageCodeOnElementUnique,
            TestSolution solutionOnElementUnique,
            String messageCodeOnElementNotUnique,
            TestSolution solutionOnElementNotUnique,
            String... eeAttributeNameList) {
        super(eeAttributeNameList);
        this.messageCodeOnElementUnique = messageCodeOnElementUnique;
        setSuccessSolution(solutionOnElementUnique);
        this.messageCodeOnElementNotUnique = messageCodeOnElementNotUnique;
        setFailureSolution(solutionOnElementNotUnique);
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
            if (StringUtils.isNotBlank(messageCodeOnElementUnique)) {
                for (Element el : elements) {
                    addSourceCodeRemark(
                            getSuccessSolution(),
                            el,
                            messageCodeOnElementUnique);
                }
            }
        } else {
            checkResult = getFailureSolution();
            if (StringUtils.isNotBlank(messageCodeOnElementNotUnique)) {
                for (Element el : elements) {
                    addSourceCodeRemark(
                            getFailureSolution(),
                            el,
                            messageCodeOnElementNotUnique);
                }
            }
        }
        testSolutionHandler.addTestSolution(checkResult);
    }
}