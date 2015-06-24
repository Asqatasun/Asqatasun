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

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.IMAGE_LINK_CHILDREN_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.LINK_WITH_CHILDREN_CSS_LIKE_QUERY;
import org.tanaguru.rules.keystore.HtmlElementStore;

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
     * Constructor
     * @param considerContext
     * @param searchImageLink 
     */
    public CompositeLinkElementSelector(
            boolean considerContext, 
            boolean searchImageLink) {
        super(considerContext);
        this.searchImageLink = searchImageLink;
    }
    
    /**
     * Constructor
     * @param considerTitleAsContext
     * @param considerContext
     * @param searchImageLink 
     */
    public CompositeLinkElementSelector(
            boolean considerTitleAsContext, 
            boolean considerContext, 
            boolean searchImageLink) {
        super(considerTitleAsContext, considerContext);
        this.searchImageLink = searchImageLink;
    }
    
    @Override
    protected String getCssLikeQuery() {
        return LINK_WITH_CHILDREN_CSS_LIKE_QUERY;
    }
    
    @Override
    protected boolean isLinkPartOfTheScope(Element linkElement, String linkText) {
        if (!super.isLinkPartOfTheScope(linkElement, linkText)) {
            return false;
        }
        if (searchImageLink) {
            return isImageLink(linkElement);
        }
        return !isImageLink(linkElement) && !isSvgLink(linkElement);
    }

    /**
     * 
     * @param linkElement
     * @return whether the current link element is an image link
     */
    protected boolean isImageLink(Element linkElement) {
        if (linkElement.children().isEmpty() 
                || linkElement.children().size() > 1
                || StringUtils.isNotBlank(linkElement.ownText()) ) {
            return false;
        }
        return !linkElement.children().select(IMAGE_LINK_CHILDREN_CSS_LIKE_QUERY).isEmpty();
    }

    /**
     * 
     * @param linkElement
     * @return whether the current link element is a svg link
     */
    protected boolean isSvgLink(Element linkElement) {
        if (linkElement.children().isEmpty() 
                || linkElement.children().size() > 1
                || StringUtils.isNotBlank(linkElement.ownText()) ) {
            return false;
        }
        return !linkElement.children().select(HtmlElementStore.SVG_ELEMENT).isEmpty();
    }
}