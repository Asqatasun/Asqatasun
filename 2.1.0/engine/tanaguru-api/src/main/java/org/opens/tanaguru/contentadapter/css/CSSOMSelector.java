/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
package org.opens.tanaguru.contentadapter.css;

import java.util.List;

import org.w3c.css.sac.Condition;
import org.w3c.css.sac.Selector;

/**
 * 
 * @author jkowalczyk
 */
public interface CSSOMSelector {

    /**
     *
     * @param declaration
     *            the declaration to add
     * @return true if the declaration was successfully added, false otherwise
     */
    boolean addCSSOMDeclaration(CSSOMDeclaration declaration);

    /**
     *
     * @param condition
     *            a w3c condition interface
     * @return the string representation of the w3c condition
     */
    String conditionToString(Condition condition);

    /**
     *
     * @return a list of owner declaration
     */
    List<CSSOMDeclaration> getOwnerDeclaration();

    /**
     *
     * @return the selector object
     */
    Selector getSelector();

    /**
     *
     * @return the selector text
     */
    String getSelectorTxt();

    /**
     *
     * @param selector
     *            a w3c selector interface
     * @return the string representation of the w3c selector
     */
    String selectorToString(Selector selector);

    /**
     *
     * @param selector
     *            the selector object to set
     */
    void setSelector(Selector selector);

    /**
     *
     * @param selectorDeclaration
     *            the list of the owner declarations to set
     */
    void setSelectorDeclaration(List<CSSOMDeclaration> selectorDeclaration);

    /**
     *
     * @param selectorTxt
     *            the selector text to set
     */
    void setSelectorTxt(String selectorTxt);
}
