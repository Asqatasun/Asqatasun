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
import static org.tanaguru.rules.keystore.CssLikeQueryStore.LINK_WITH_CHILDREN_CSS_LIKE_QUERY;
import org.tanaguru.rules.keystore.HtmlElementStore;

/**
 * Element selector implementation that select svg links. 
 * An svg link is a composite link that only contains one svg tag. 
 * 
 * @author jkowalczyk
 */
public class SvgLinkElementSelector extends LinkElementSelector {

    /**
     * Constructor
     * @param considerContext
     */
    public SvgLinkElementSelector(boolean considerContext) {
        super(considerContext);
    }
    
    /**
     * Constructor
     * @param considerTitleAsContext
     * @param considerContext
     */
    public SvgLinkElementSelector(
            boolean considerTitleAsContext, 
            boolean considerContext) {
        super(considerTitleAsContext, considerContext);
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
        return isSvgLink(linkElement);
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