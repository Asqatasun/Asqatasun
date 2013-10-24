/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2013  Open-S Company
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

import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandlerImpl;
import static org.opens.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.TEXT_LINK_CSS_LIKE_QUERY;
import org.opens.tanaguru.rules.keystore.HtmlElementStore;
import org.springframework.util.CollectionUtils;

/**
 * Element selector implementation that select text links (without children tags)
 * 
 * @author jkowalczyk
 */
public class LinkElementSelector implements ElementSelector {

    /** 
     * The list of elements that are considered as context of the link. 
     * The presence of the td element in that list enables to deal with the case
     * where the context is handled by a table header as defined in the rule
     */ 
    private static final String[] PARENT_CONTEXT_ELEMENTS_TAB = {
                HtmlElementStore.P_ELEMENT, 
                HtmlElementStore.H1_ELEMENT, 
                HtmlElementStore.H2_ELEMENT, 
                HtmlElementStore.H3_ELEMENT, 
                HtmlElementStore.H4_ELEMENT, 
                HtmlElementStore.H5_ELEMENT, 
                HtmlElementStore.H6_ELEMENT, 
                HtmlElementStore.LI_ELEMENT, 
                HtmlElementStore.TD_ELEMENT, 
                };
    
    /** */
    private static final String[] PREV_SIBLING_CONTEXT_ELEMENTS_TAB = {
                HtmlElementStore.H1_ELEMENT, 
                HtmlElementStore.H2_ELEMENT, 
                HtmlElementStore.H3_ELEMENT, 
                HtmlElementStore.H4_ELEMENT, 
                HtmlElementStore.H5_ELEMENT, 
                HtmlElementStore.H6_ELEMENT, 
                };

    /** */
    private static Collection<String> PARENT_CONTEXT_ELEMENTS = 
            CollectionUtils.arrayToList(PARENT_CONTEXT_ELEMENTS_TAB);
    
    /** */
    private static Collection<String> PREV_SIBLING_CONTEXT_ELEMENTS = 
            CollectionUtils.arrayToList(PREV_SIBLING_CONTEXT_ELEMENTS_TAB);
    
    /** */
    private ElementHandler<Element> elementsWithContext = new ElementHandlerImpl();
    public ElementHandler<Element> getElementsWithContext() {
        return elementsWithContext;
    }

    /** */
    private ElementHandler<Element> elementsWithoutContext = new ElementHandlerImpl();
    public ElementHandler<Element> getElementsWithoutContext() {
        return elementsWithoutContext;
    }
    
    /**
     * Default constructor
     */
    public LinkElementSelector() {}
    
    @Override
    public void selectElements(SSPHandler sspHandler, ElementHandler<Element> elementHandler) {
        elementHandler.addAll(
                sspHandler.beginCssLikeSelection().
                    domCssLikeSelectNodeSet(TEXT_LINK_CSS_LIKE_QUERY).
                        getSelectedElements());
        for (Element el : elementHandler.get()) {
            if (StringUtils.isNotBlank(el.ownText())) {
                if (doesLinkHaveContext(el)) {
                    elementsWithContext.add(el);
                } else {
                    elementsWithoutContext.add(el);
                }
            }
        }
    }

    /**
     * 
     * @param linkElement
     * @return 
     */
    protected boolean doesLinkHaveContext(Element linkElement) {
        if (linkElement.hasAttr(TITLE_ATTR) && 
                !StringUtils.equals(linkElement.attr(TITLE_ATTR), linkElement.ownText())) {
            return true;
        }
        if (StringUtils.isNotBlank(linkElement.parent().ownText())) {
            return true;
        }
        for (Element el : linkElement.parents()) {
            if (PARENT_CONTEXT_ELEMENTS.contains(el.tagName())) {
                return true;
            }
        }
        Element prevElementSibling = linkElement.previousElementSibling();
        while (prevElementSibling != null) {
            if (PREV_SIBLING_CONTEXT_ELEMENTS.contains(prevElementSibling.tagName())) {
                return true;
            }
            prevElementSibling = prevElementSibling.previousElementSibling();
        }
        return false;
    }

}