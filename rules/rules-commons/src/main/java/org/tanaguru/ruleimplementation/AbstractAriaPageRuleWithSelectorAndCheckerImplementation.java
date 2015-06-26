/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
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
package org.tanaguru.ruleimplementation;

import javax.annotation.Nonnull;
import org.jsoup.nodes.Element;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.rules.elementchecker.ElementChecker;
import org.tanaguru.rules.elementselector.ElementSelector;
import org.tanaguru.rules.elementselector.SimpleElementSelector;

/**
 * <p>
 * This class should be overridden by concrete {@link RuleImplementation} 
 * classes which implement tests with page scope.
 * </p>
 * <p>It embeds a {@link ElementSelector} and a {@link ElementChecker} 
 * set by constructor arguments to perform the selection and the check only when
 * aria attributes have been detected on the page previously
 * </p>
 * 
 */
public abstract class AbstractAriaPageRuleWithSelectorAndCheckerImplementation 
        extends AbstractPageRuleWithSelectorAndCheckerImplementation {
 
    /** The elementHandler that deals with aria elements */
    private final ElementHandler<Element> ariaElementHandler = new ElementHandlerImpl();
    
    /**
     * The constructor
     * 
     */
    public AbstractAriaPageRuleWithSelectorAndCheckerImplementation() {
        super();
    }
    
    /**
     * The constructor
     * 
     * @param elementSelector
     * @param elementChecker
     */
    public AbstractAriaPageRuleWithSelectorAndCheckerImplementation(
            @Nonnull ElementSelector elementSelector, 
            @Nonnull ElementChecker elementChecker) {
        super(elementSelector, elementChecker);
    }
    
    /**
     * Perform the selection using the {@link ElementSelector}
     * 
     * @param sspHandler
     */
    @Override
    protected void select(SSPHandler sspHandler) {
        ElementSelector es = new SimpleElementSelector("[^aria]");
        es.selectElements(sspHandler, ariaElementHandler);
        if (ariaElementHandler.isEmpty()) {
            return;
        }
        super.select(sspHandler);
    }
    
    /**
     * Perform the check using the {@link ElementChecker}
     * 
     * @param sspHandler
     * @param testSolutionHandler
     */
    @Override
    protected void check(
            SSPHandler sspHandler, 
            TestSolutionHandler testSolutionHandler) {
        if (ariaElementHandler.isEmpty()) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_APPLICABLE);
            return;
        }
        if (getElementChecker() != null) {
            super.check(sspHandler, testSolutionHandler);
        } else {
            testSolutionHandler.addTestSolution(TestSolution.NOT_TESTED);
        }
    }

}