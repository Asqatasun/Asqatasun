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
import org.w3c.css.sac.SACMediaList;

/**
 *
 * @author jkowalczyk
 */
public interface CSSOMRule {

    /**
     *
     * @param declaration
     *            the declaration to add
     * @return true if the declaration was added, false otherwise
     */
    boolean addCSSOMDeclaration(CSSOMDeclaration declaration);

    /**
     *
     * @param selector
     *            the selector to add
     * @return true if the selector was added, false otherwise
     */
    boolean addCSSOMSelector(CSSOMSelector selector);

    /**
     *
     * @return a list of declarations
     */
    List<CSSOMDeclaration> getDeclarations();

    /**
     *
     * @return the owner style sheet
     */
    CSSOMStyleSheet getOwnerStyle();

    /**
     *
     * @return a list of selectors
     */
    List<CSSOMSelector> getSelectors();

    /**
     *
     * @return a list of selectors
     */
    SACMediaList getMediaList();

    /**
     *
     * @param declarations
     *            the list of declarations to set
     */
    void setDeclarations(List<CSSOMDeclaration> declarations);

    /**
     *
     * @param ownerStyle
     *            the StyleSheet to set
     */
    void setOwnerStyle(CSSOMStyleSheet ownerStyle);

    /**
     *
     * @param selectors
     *            the list of selectors to set
     */
    void setSelectors(List<CSSOMSelector> selectors);

    /**
     *
     * @param mediaList
     *              the list of media to set
     */
    void setMediaList(SACMediaList mediaList);
}
