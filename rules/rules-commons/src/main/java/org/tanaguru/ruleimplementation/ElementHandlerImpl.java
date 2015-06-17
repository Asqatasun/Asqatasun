/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015 Tanaguru.org
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
package org.tanaguru.ruleimplementation;

import java.util.Arrays;
import java.util.Collection;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Default implementation of the {@link ElementHandler} interface.
 * It enables to handle a collection of {@link Element} and provides basic
 * operations on it.
 * 
 */
public class ElementHandlerImpl implements ElementHandler<Element> {

    /**
     * The elements implied by the test
     */
    private final Elements elements = new Elements();
    
    /**
     * Default constructor
     */
    public ElementHandlerImpl(){}
    
    /**
     * Constructor that initialise locale collection with elements passed as 
     * argument
     * @param els
     */
    public ElementHandlerImpl(Element... els){
        elements.addAll(Arrays.asList(els));
    }

    /**
     * {@inheritDoc}
     * @param element
     */
    @Override
    public void add(Element element) {
        if(!elements.contains(element)){
            elements.add(element);
        }
    }
    
    /**
     * {@inheritDoc}
     * @param element
     */
    @Override
    public void remove(Element element) {
        elements.remove(element);
    }
    
    /**
     * {@inheritDoc}
     * @param elements
     */
    @Override
    public void addAll(Collection<Element> elements) {
        for (Element el : elements) {
            add(el);
        }
    }
    
    /**
     * {@inheritDoc}
     * @param elements
     */
    @Override
    public void removeAll(Collection<Element> elements) {
        this.elements.removeAll(elements);
    }
    
    /**
     * {@inheritDoc}
     * @param elementHandler
     */
    @Override
    public void removeAll(ElementHandler<Element> elementHandler) {
        this.elements.removeAll(elementHandler.get());
    }
    
    /**
     * {@inheritDoc}
     * @return the current ElementHandler emptied
     */
    @Override
    public ElementHandler<Element> clean() {
        elements.clear();
        return this;
    }
    
    /**
     * {@inheritDoc}
     * @return 
     */
    @Override
    public Elements get() {
        return elements;
    }

    /**
     * {@inheritDoc}
     * @return 
     */
    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    /**
     * {@inheritDoc}
     * @return 
     */
    @Override
    public int size() {
        return get().size();
    }

}