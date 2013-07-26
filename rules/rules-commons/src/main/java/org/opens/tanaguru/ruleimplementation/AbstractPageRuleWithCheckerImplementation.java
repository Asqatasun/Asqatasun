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

import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;

/**
 * <p>
 * This class should be overridden by concrete {@link RuleImplementation} 
 * classes which implement tests with page scope.
 * </p>
 * <p>It embeds a {@link ElementChecker} set by constructor argument to perform 
 * the check. No selection is expected here.
 * </p>
 */
public abstract class AbstractPageRuleWithCheckerImplementation 
        extends AbstractPageRuleDefaultImplementation {

    /** The elementChecker used by the rule */
    private ElementChecker elementChecker;
    
    /**
     * The constructor
     * 
     * @param elementCheckerKey 
     */
    public AbstractPageRuleWithCheckerImplementation(
            ElementChecker elementChecker) {
        super();
        this.elementChecker = elementChecker;
    }   
    
    /**
     * No selection is performed here.
     * 
     * @param sspHandler
     * @param selectionHandler 
     */
    @Override
    protected void select(SSPHandler sspHandler, ElementHandler selectionHandler) {
        // NO SELECTION REQUIRED HERE, ALL IS ABOUT CHECKING
    }

    /**
     * Perform the check using the {@link ElementChecker}
     * 
     * @param sspHandler
     * @param selectionHandler 
     * @param testSolutionHandler
     */
    @Override
    protected void check(
            SSPHandler sspHandler, 
            ElementHandler selectionHandler, 
            TestSolutionHandler testSolutionHandler) {
        // reseting the service before starting the check.
        // Usually the reset is done with the first selection, but in this
        // case, no selection is done.
        sspHandler.getProcessRemarkService().resetService();
        if (elementChecker instanceof NomenclatureBasedElementChecker) {
            ((NomenclatureBasedElementChecker)elementChecker).
                    setNomenclatureLoaderService(nomenclatureLoaderService);
        }
        elementChecker.check(
                sspHandler, 
                selectionHandler, 
                testSolutionHandler);
    }

}