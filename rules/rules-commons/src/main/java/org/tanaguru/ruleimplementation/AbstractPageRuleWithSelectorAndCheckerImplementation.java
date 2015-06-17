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
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.rules.elementchecker.ElementChecker;
import org.tanaguru.rules.elementchecker.NomenclatureBasedElementChecker;
import org.tanaguru.rules.elementselector.ElementSelector;

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

    /** The elementHandler used by the rule */
    private ElementHandler<Element> elementHandler = new ElementHandlerImpl();
    public ElementHandler<Element> getElements() {
        return elementHandler;
    }

    public void setElementHandler(ElementHandler<Element> elementHandler) {
        this.elementHandler = elementHandler;
    }
    
    /** The elementSelector used by the rule */
    private ElementSelector elementSelector;
    public ElementSelector getElementSelector() {
        return elementSelector;
    }
    
    public void setElementSelector(ElementSelector elementSelector) {
        this.elementSelector = elementSelector;
    }
    
    /** The elementChecker used by the rule */
    private ElementChecker elementChecker;
    public ElementChecker getElementChecker() {
        return elementChecker;
    }
    
    public void setElementChecker(ElementChecker elementChecker) {
        this.elementChecker = elementChecker;
    }
    
    /**
     * The constructor
     * 
     */
    public AbstractPageRuleWithSelectorAndCheckerImplementation() {
        super();
    }
    
    /**
     * The constructor
     * 
     * @param elementSelector
     * @param elementChecker
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
     */
    @Override
    protected void select(SSPHandler sspHandler) {
        if (elementSelector != null) {
            elementSelector.selectElements(sspHandler, elementHandler);
        }
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
        setServicesToChecker(elementChecker);
        elementChecker.check(
            sspHandler, 
            elementHandler, 
            testSolutionHandler);
    }
    
    /**
     * Set service to elementChecker depending on their nature.
     * @param elementChecker 
     */
    protected void setServicesToChecker(ElementChecker elementChecker) {
        if (elementChecker instanceof NomenclatureBasedElementChecker) {
            ((NomenclatureBasedElementChecker)elementChecker).
                setNomenclatureLoaderService(nomenclatureLoaderService);
        }
    }

    @Override
    public int getSelectionSize() {
        return elementHandler.size();
    }

}