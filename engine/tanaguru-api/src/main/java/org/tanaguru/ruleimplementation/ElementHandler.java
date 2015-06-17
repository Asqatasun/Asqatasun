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

import java.util.Collection;

/**
 * Common interface to deal with selected DOM elements
 * 
 * @param <E>
 */
public interface ElementHandler<E> {
    
    /**
     * Add an element to the collection
     * 
     * @param element 
     */
    void add(E element);
    
    /**
     * remove an element from the collection
     * 
     * @param element 
     */
    void remove(E element);
    
    /**
     * Add elements to the collection
     * 
     * @param elements 
     */
    void addAll(Collection<E> elements);
    
    /**
     * Remove elements from the collection
     * 
     * @param elements 
     */
    void removeAll(Collection<E> elements);
    
    /**
     * Remove elements from the collection
     * 
     * @param elementHandler
     */
    void removeAll(ElementHandler<E> elementHandler);
    
    /**
     * Clean the collection of element
     * @return the current instance of ElementHandler
     */
    ElementHandler<E> clean();
    
    /**
     * 
     * @return all the collected elements
     * 
     */
    Collection<E> get();
    
    /**
     * 
     * @return whether the collection of elements is empty
     * 
     */
    boolean isEmpty();
    
    /**
     * 
     * @return the size of the current collection
     * 
     */
    int size();
    
}