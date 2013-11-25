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
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.processor.SSPHandler;

/**
 * This class should be overriden by concrete {@link RuleImplementation} classes
 * which implement tests with page scope. The implementation of the selectAndCheck
 * method defines 2 commons actions when testing a page : 
 *  <ul>
 *  <li>selection</li>
 *  <li>check</li>
 *  </ul>
 *  This class implements the {@link ElementHanlder} interface to handle the selected 
 *  elements of the selections. <br/>
 *  The concrete implementation may need to use local instances of {@link ElementHanlder}
 *  implementation when multiple selection is needed.
 * 
 * @author jkowalczyk
 */
public abstract class AbstractPageRuleMarkupImplementation 
            extends AbstractPageRuleDefaultImplementation  
                implements ElementHandler<Element> {

    /**
     * The elements implied by the test
     */
    private Elements elements = new Elements();
    
    /**
     * Default constructor
     */
    public AbstractPageRuleMarkupImplementation(){
        super();
    }
    
    @Override
    protected void selectAndCheck(SSPHandler sspHandler, TestSolutionHandler testSolutionHandler) {
        select(sspHandler, this);
        check(sspHandler, this, testSolutionHandler);
    }

    /**
     * This method defines a select operation . The instance of 
     * {@link SSPHandler} received as a parameter concerns only one page. 
     *
     * @param sspHandler
     *            the SSP handler to use.
     * @param elementHandler
     *            the selectionHandler that handles the selected elements of the DOM.
     */
    abstract protected void select(
                SSPHandler sspHandler, 
                ElementHandler<Element> elementHandler);
    
    /**
     * This method defines a check operation . The instance of 
     * {@link SSPHandler} received as a parameter concerns only one page. 
     * The check operation may create TestSolutions handled by the 
     * testSolutionHandler. By default this method reset the processRemark 
     * service to clean-up local collection of remarks before collecting new one.
     * Classes that override this class HAVE to make an explicit call to 
     * super.check(...) OR reset the service.
     *
     * @param sspHandler
     *            the SSP handler to use.
     * @param elementHandler
     *            the selectionHandler that handles the selected elements of the DOM.
     * @param testSolutionHandler
     *            the testSolutionHandler that handles the computed TestSolutions.
     */
    abstract protected void check(
                SSPHandler sspHandler, 
                ElementHandler<Element> elementHandler, 
                TestSolutionHandler testSolutionHandler);
    
    @Override
    public int getSelectionSize() {
        return elements.size();
    }
    
    @Override
    public void add(Element element) {
        if (!elements.contains(element)) {
            elements.add(element);
        }
    }
    
    @Override
    public void remove(Element element) {
        elements.remove(element);
    }
    
    @Override
    public void addAll(Collection<Element> elements) {
        for (Element el : elements) {
            add(el);
        }
    }

    @Override
    public void removeAll(Collection<Element> elements) {
        this.elements.removeAll(elements);
    }
    
    @Override
    public void removeAll(ElementHandler<Element> elementHandler) {
        elements.removeAll(elementHandler.get());
    }
    
    @Override
    public ElementHandler clean() {
        elements.clear();
        return this;
    }
    
    @Override
    public Elements get() {
        return elements;
    }
    
    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

}