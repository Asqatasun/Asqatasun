/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015  Tanaguru.org
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

package org.tanaguru.rules.elementselector;

import org.jsoup.nodes.Element;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.ElementHandler;

/**
 * Element selector implementation that uses an unique css query to retrieve
 * Html elements implied by the test
 * 
 * @author jkowalczyk
 */
public class SimpleElementSelector implements ElementSelector {

    /* The css-like query used to retrieve Elements */
    private String cssLikeQuery;
    public void setCssLikeQuery(String cssLikeQuery) {
        this.cssLikeQuery = cssLikeQuery;
    }

    /**
     * Default constructor
     */
    public SimpleElementSelector() {}
    
    /**
     *
     * @param cssLikeQuery 
     */
    public SimpleElementSelector(String cssLikeQuery) {
        this.cssLikeQuery = cssLikeQuery;
    }

    @Override
    public void selectElements(SSPHandler sspHandler, ElementHandler<Element> selectionHandler) {
        if (cssLikeQuery != null) {
            selectionHandler.addAll(
                    sspHandler.beginCssLikeSelection().
                        domCssLikeSelectNodeSet(cssLikeQuery).
                            getSelectedElements());
        }
    }
    
}