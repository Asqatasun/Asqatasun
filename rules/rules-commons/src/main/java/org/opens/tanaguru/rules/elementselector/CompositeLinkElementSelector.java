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

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.ruleimplementation.ElementHandler;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.IMAGE_LINK_CHILDREN_CSS_LIKE_QUERY;
import static org.opens.tanaguru.rules.keystore.CssLikeQueryStore.LINK_WITH_CHILDREN_CSS_LIKE_QUERY;

/**
 * Element selector implementation that select composite links. 
 * An image link is a composite link that only contains one img tag. In all other
 * cases, the link is seen as a composite link.
 * 
 * @author jkowalczyk
 */
public class CompositeLinkElementSelector extends LinkElementSelector {

    private boolean searchImageLink = false;

    /**
     * Default constructor
     */
    public CompositeLinkElementSelector() {}
    
    /**
     * Constructor
     * @param searchImageLink 
     */
    public CompositeLinkElementSelector(boolean searchImageLink) {
        this.searchImageLink = searchImageLink;
    }
    
    @Override
    public void selectElements(SSPHandler sspHandler, ElementHandler<Element> elementHandler) {
        elementHandler.addAll(
                sspHandler.beginCssLikeSelection().
                    domCssLikeSelectNodeSet(LINK_WITH_CHILDREN_CSS_LIKE_QUERY).
                        getSelectedElements());
        for (Element el : elementHandler.get()) {
            if (isLinkPartOfTheScope(el)) {
                if (doesLinkHaveContext(el)) {
                    getElementsWithContext().add(el);
                } else {
                    getElementsWithoutContext().add(el);
                }
            }
        }
    }
    
    /**
     * 
     * @param linkElement
     * @return 
     */
    protected boolean isLinkPartOfTheScope(Element linkElement) {
        if (searchImageLink) {
            return isImageLink(linkElement);
        }
        return !isImageLink(linkElement);
    }

    /**
     * 
     * @param linkElement
     * @return whether the current link element is an image link
     */
    protected boolean isImageLink(Element linkElement) {
        if (linkElement.children().isEmpty() 
                || linkElement.children().size() > 2 
                || StringUtils.isNotBlank(linkElement.ownText()) ) {
            return false;
        }
        if (!linkElement.children().select(IMAGE_LINK_CHILDREN_CSS_LIKE_QUERY).isEmpty()) {
            return true;
        }
        return false;
    }
}