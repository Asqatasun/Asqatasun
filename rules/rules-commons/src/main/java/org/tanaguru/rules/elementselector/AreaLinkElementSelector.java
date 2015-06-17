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
import org.tanaguru.rules.keystore.AttributeStore;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.CLICKABLE_AREA_CSS_LIKE_QUERY;
import org.tanaguru.rules.textbuilder.TextAttributeOfElementBuilder;
import org.tanaguru.rules.textbuilder.TextElementBuilder;

/**
 * Element selector implementation that selects area links. 
 * 
 * @author jkowalczyk
 */
public class AreaLinkElementSelector extends LinkElementSelector {

    /*
     * The element builder needed to build the link text
     */
    private final TextElementBuilder areaTextElementBuilder =
            new TextAttributeOfElementBuilder(AttributeStore.ALT_ATTR);

    /**
     * Constructor
     *
     * @param considerContext
     */
    public AreaLinkElementSelector(boolean considerContext) {
        super(considerContext);
    }

    /**
     * Constructor
     *
     * @param considerTitleAsContext
     * @param considerContext
     */
    public AreaLinkElementSelector(boolean considerTitleAsContext, boolean considerContext) {
        super(considerTitleAsContext, considerContext);
    }

    @Override
    protected String getCssLikeQuery() {
        return CLICKABLE_AREA_CSS_LIKE_QUERY;
    }

    @Override
    protected String getLinkText(Element linkElement) {
        return areaTextElementBuilder.buildTextFromElement(linkElement);
    }
}