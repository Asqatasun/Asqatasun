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

import java.util.Collection;
import javax.annotation.Nonnull;
import javax.persistence.NoResultException;
import org.opens.tanaguru.entity.audit.TestSolution;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.rules.domelement.DomElement;
import org.opens.tanaguru.rules.domelement.extractor.DomElementExtractor;
import org.opens.tanaguru.rules.elementchecker.ElementChecker;
import org.opens.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;

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
        extends AbstractPageRuleMarkupImplementation {

    /** The elementChecker used by the rule */
    private ElementChecker elementChecker;
    public void setElementChecker(ElementChecker elementChecker) {
        this.elementChecker = elementChecker;
    }

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
     * @param elementCheckerKey 
     */
    public AbstractPageRuleFromPreProcessImplementation(
            @Nonnull ElementChecker elementChecker) {
        super();
        this.elementChecker = elementChecker;
    }
    
    /**
     * No selection is performed here.
     * 
     * @param sspHandler
     * @param elementHandler 
     */
    @Override
    protected void select(SSPHandler sspHandler, ElementHandler elementHandler) {
        sspHandler.beginCssLikeSelection();
        
        try {
            domElements = DomElementExtractor.extractDomElements(sspHandler);
        } catch (NoResultException nre) {
            return;
        }
        
        doSelect(domElements, sspHandler, elementHandler);
    }

    /**
     * 
     * @param domElements
     * @param sspHandler
     * @param elementHandler 
     */
    protected abstract void doSelect(
            Collection<DomElement>domElements, 
            SSPHandler sspHandler, 
            ElementHandler elementHandler);
    
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
        if (domElements == null) {
            testSolutionHandler.addTestSolution(TestSolution.NOT_TESTED);
            return;
        }
        
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