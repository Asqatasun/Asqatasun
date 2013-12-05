/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2013  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.ruleimplementation;

import javax.annotation.Nonnull;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;
import org.opens.tanaguru.rules.elementselector.ElementSelector;

/**
 * <p>
 * This class should be overridden by concrete {@link RuleImplementation} 
 * classes which implement tests with page scope.
 * </p>
 * <p>It embeds a {@link ElementSelector} and a {@link ElementChecker} 
 * set by constructor arguments to perform the selection and the check.
 * </p>
 * 
 */
public abstract class AbstractPageRuleWithSelectorAndCheckerImplementation 
        extends AbstractPageRuleMarkupImplementation {

    /** The elementChecker used by the rule */
    private ElementChecker elementChecker;
    public ElementChecker getElementChecker() {
        return elementChecker;
    }
    
    /** The elementSelector used by the rule */
    private ElementSelector elementSelector;
    public ElementSelector getElementSelector() {
        return elementSelector;
    }
    
    /**
     * The constructor
     * 
     * @param elementSelectorKey
     * @param elementCheckerKey 
     */
    public AbstractPageRuleWithSelectorAndCheckerImplementation(
            @Nonnull ElementSelector elementSelector, 
            @Nonnull ElementChecker elementChecker) {
        super();
        this.elementChecker = elementChecker;
        this.elementSelector = elementSelector;
    }   

    /**
     * Perform the selection using the {@link ElementSelector}
     * 
     * @param sspHandler
     * @param elementHandler 
     */
    @Override
    protected void select(SSPHandler sspHandler, ElementHandler elementHandler) {
        elementSelector.selectElements(sspHandler, elementHandler);
    }

    /**
     * Perform the check using the {@link ElementChecker}
     * 
     * @param sspHandler
     * @param elementHandler 
     * @param testSolutionHandler
     */
    @Override
    protected void check(
            SSPHandler sspHandler, 
            ElementHandler elementHandler, 
            TestSolutionHandler testSolutionHandler) {
        if (elementChecker instanceof NomenclatureBasedElementChecker) {
            ((NomenclatureBasedElementChecker)elementChecker).
                setNomenclatureLoaderService(nomenclatureLoaderService);
        }
        elementChecker.check(
            sspHandler, 
            elementHandler, 
            testSolutionHandler);
    }

}