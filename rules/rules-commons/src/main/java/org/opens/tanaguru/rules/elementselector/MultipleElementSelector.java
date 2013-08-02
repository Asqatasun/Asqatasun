/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2011  Open-S Company
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
 *  Contact us by mail: open-s AT open-s DOT com
 */

package org.opens.tanaguru.rules.elementselector;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.springframework.util.CollectionUtils;

/**
 * Element selector implementation that uses multiple css queries to retrieve
 * elements implied by the test
 * 
 * @author jkowalczyk
 */
public class MultipleElementSelector implements ElementSelector{

    /* The css queries used to retrieve Elements */
    private Collection<String> cssQueryList;
    public void addCssQuery(String cssQuery) {
        if (cssQueryList == null) {
            cssQueryList = new ArrayList<String>();
        }
        cssQueryList.add(cssQuery);
    }

    /**
     * Constructor
     */
    public MultipleElementSelector() {
    }
    
    /**
     * constructor
     * @param cssQuery 
     */
    public MultipleElementSelector(String... cssQueryList) {
        this.cssQueryList = CollectionUtils.arrayToList(cssQueryList);
    }

    @Override
    public void selectElements(SSPHandler sspHandler, ElementHandler selectionHandler) {
        for (String cssQuery : cssQueryList) {
            selectionHandler.addAll(sspHandler.
                    domCssLikeSelectNodeSet(cssQuery).getSelectedElements());
        }
    }

}