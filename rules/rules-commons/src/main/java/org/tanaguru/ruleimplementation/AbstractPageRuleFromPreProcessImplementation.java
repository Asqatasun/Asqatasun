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

import java.util.Collection;
import javax.annotation.Nonnull;
import javax.persistence.NoResultException;
import org.tanaguru.entity.audit.TestSolution;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.rules.domelement.DomElement;
import org.tanaguru.rules.domelement.extractor.DomElementExtractor;
import org.tanaguru.rules.elementchecker.ElementChecker;

/**
 * <p>
 * This class should be overridden by concrete {@link RuleImplementation} 
 * classes which implement tests with page scope.
 * </p>
 * <p>It is based from the js extractor results launched while fetching the DOM.</p>
 * <p>The selection consists in parsing the JSON object and create POJO
 * DomElement collection to work on</p>
 * <p>It embeds a {@link ElementChecker} set by constructor argument to perform 
 * the check. 
 * </p>
 */
public abstract class AbstractPageRuleFromPreProcessImplementation 
        extends AbstractPageRuleWithSelectorAndCheckerImplementation {

    /* the extracted domElements*/
    Collection<DomElement> domElements;

    /**
     * The default constructor
     * 
     */
    public AbstractPageRuleFromPreProcessImplementation() {
        super();
    }
    
    /**
     * The constructor
     * 
     * @param elementChecker
     */
    public AbstractPageRuleFromPreProcessImplementation(
            @Nonnull ElementChecker elementChecker) {
        super();
        setElementChecker(elementChecker);
    }
    
    /**
     * No selection is performed here.
     * 
     * @param sspHandler
     */
    @Override
    protected void select(SSPHandler sspHandler) {
        try {
            domElements = DomElementExtractor.extractDomElements(sspHandler);
        } catch (NoResultException nre) {
            return;
        }
        doSelect(domElements, sspHandler);
    }

    /**
     * 
     * @param domElements
     * @param sspHandler
     */
    protected abstract void doSelect(
            Collection<DomElement>domElements, 
            SSPHandler sspHandler);
    
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
        if (domElements == null) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_TESTED);
            return;
        }
        
        super.check(sspHandler, testSolutionHandler);
    }

}