/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2014  Open-S Company
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
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.springframework.util.CollectionUtils;

/**
 * Element selector implementation that uses multiple {@link SimpleElementSelector}
 * instances to retrieve the elements implied by the test
 * 
 * @author jkowalczyk
 */
public class SimpleElementSelectorAggregator implements ElementSelector{

    /* The css queries used to retrieve Elements */
    private Collection<SimpleElementSelector> simpleElementSelectorList;
    public void addSimpleElementSelector(SimpleElementSelector simpleElementSelector) {
        if (simpleElementSelectorList == null ) {
            simpleElementSelectorList = new ArrayList<>();
        }
        simpleElementSelectorList.add(simpleElementSelector);
    }
    
    /**
     * Constructor
     */
    public SimpleElementSelectorAggregator() {
    }
    
    /**
     * constructor
     * @param simpleElementSelectors 
     */
    public SimpleElementSelectorAggregator(SimpleElementSelector... simpleElementSelectors) {
        this.simpleElementSelectorList = 
                CollectionUtils.arrayToList(simpleElementSelectors);
    }

    @Override
    public void selectElements(SSPHandler sspHandler, ElementHandler selectionHandler) {
        for (SimpleElementSelector ses : simpleElementSelectorList) {
            ses.selectElements(sspHandler, selectionHandler);
        }
    }

}