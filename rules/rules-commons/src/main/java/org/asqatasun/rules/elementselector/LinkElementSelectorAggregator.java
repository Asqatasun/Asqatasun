/*
 *  Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2020  Asqatasun.org
 * 
 *  This file is part of Asqatasun.
 * 
 *  Asqatasun is free software: you can redistribute it and/or modify
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
 *  Contact us by mail: asqatasun AT asqatasun DOT org
 */

package org.asqatasun.rules.elementselector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.asqatasun.ruleimplementation.ElementHandlerImpl;
import org.jsoup.nodes.Element;
import org.asqatasun.processor.SSPHandler;
import org.asqatasun.ruleimplementation.ElementHandler;

/**
 * Element selector implementation that uses multiple {@link SimpleElementSelector}
 * instances to retrieve the elements implied by the test
 * 
 * @author jkowalczyk
 */
public class LinkElementSelectorAggregator extends LinkElementSelector {

    /* The css queries used to retrieve Elements */
    private final Collection<LinkElementSelector> elementSelectorList = new ArrayList<>();
    public void addSimpleElementSelector(LinkElementSelector elementSelector) {
        elementSelectorList.add(elementSelector);
    }
    
    /**
     * constructor
     * @param elementSelectors
     */
    public LinkElementSelectorAggregator(LinkElementSelector... elementSelectors) {
        this.elementSelectorList.addAll(Arrays.asList(elementSelectors));
    }

    @Override
    public void selectElements(SSPHandler sspHandler, ElementHandler<Element> selectionHandler) {
        for (LinkElementSelector ses : elementSelectorList) {
            ses.selectElements(sspHandler, selectionHandler);
        }
    }

    /** */
    private final ElementHandler<Element> decidableElements = new ElementHandlerImpl();
    @Override
    public ElementHandler<Element> getDecidableElements() {
        for (LinkElementSelector ses : elementSelectorList) {
            decidableElements.addAll(ses.getDecidableElements().get());
        }
        return decidableElements;
    }

    /** */
    private final ElementHandler<Element> notDecidableElements = new ElementHandlerImpl();
    @Override
    public ElementHandler<Element> getNotDecidableElements() {
        for (LinkElementSelector ses : elementSelectorList) {
            notDecidableElements.addAll(ses.getNotDecidableElements().get());
        }
        return notDecidableElements;
    }

    @Override
    public boolean isEmpty() {
        for (LinkElementSelector ses : elementSelectorList) {
            if (!ses.getNotDecidableElements().isEmpty() || !ses.getDecidableElements().isEmpty()) return false;
        }
        return true;
    }

}
