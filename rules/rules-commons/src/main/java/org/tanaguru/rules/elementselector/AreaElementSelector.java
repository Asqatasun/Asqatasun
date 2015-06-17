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
import org.jsoup.select.Elements;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.ElementHandler;
import org.tanaguru.rules.elementselector.builder.CssLikeSelectorBuilder;
import static org.tanaguru.rules.keystore.AttributeStore.NAME_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.USEMAP_ATTR;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.*;
import static org.tanaguru.rules.keystore.HtmlElementStore.AREA_ELEMENT;

/**
 * Area selector implementation. This implementation is quite specific due to 
 * area definition. An area is linked to a map that is linked through its name
 * attribute to an image through a usemap attribute. 
 */
public class AreaElementSelector extends SimpleElementSelector {

    private String areaSelectionKey = AREA_ELEMENT;
    
    /**
     * Default constructor
     */
    public AreaElementSelector() {
        super(MAP_WITH_AREA_CHILD_AND_NAME_ATTR_CSS_LIKE_QUERY);
    }
    
    /**
     * Constructor
     * @param keepAreaWithAlt
     * @param keepAreaWithoutHref 
     * @param keepAreaNotInLink 
     */
    public AreaElementSelector(
                boolean keepAreaWithAlt, 
                boolean keepAreaWithoutHref, 
                boolean keepAreaNotInLink) {
        super(MAP_WITH_AREA_CHILD_AND_NAME_ATTR_CSS_LIKE_QUERY);
        if (keepAreaWithAlt && keepAreaWithoutHref) {
            areaSelectionKey = AREA_WITH_ALT_WITHOUT_HREF_ATTR_CSS_LIKE_QUERY;
        } else if (keepAreaWithAlt && keepAreaNotInLink) {
            areaSelectionKey = AREA_WITH_ALT_NOT_IN_LINK_CSS_LIKE_QUERY;
        } else if (keepAreaWithAlt){
            areaSelectionKey = AREA_WITH_ALT_CSS_LIKE_QUERY;
        }
    }
    
    @Override
    public void selectElements(SSPHandler sspHandler, ElementHandler<Element> elementHandler) {
        super.selectElements(sspHandler, elementHandler);
        if (elementHandler.isEmpty()) {
            return;
        }
        Elements mapElementsAssociatedWithImg = new Elements();
        // for each map element, search the associated image and store the 
        // element in a new collection
        for (Element map : elementHandler.get()) {
            if (isMapAssociatedWithImage(sspHandler, map)) {
                mapElementsAssociatedWithImg.add(map);
            }
        }

        elementHandler.clean();
        
        // for all well-formed maps, keep all the area children and return them
        for (Element map : mapElementsAssociatedWithImg) {
            elementHandler.addAll(map.select(areaSelectionKey));
        }
    }

    /**
     * 
     * @param sspHandler
     * @param element
     * @return 
     */
    private boolean isMapAssociatedWithImage(SSPHandler sspHandler, Element element) {
        if (StringUtils.isBlank(element.attr(NAME_ATTR))) {
            return false;
        }
        String query = CssLikeSelectorBuilder.buildSelectorFromAttributeTypeAndValue(
                USEMAP_ATTR, 
                element.attr(NAME_ATTR));
        if (CssLikeSelectorBuilder.getNumberOfElements(sspHandler, query)> 0) {
            return true;
        }
        query = CssLikeSelectorBuilder.buildSelectorFromAttributeTypeAndValue(
                USEMAP_ATTR, 
                '#'+element.attr(NAME_ATTR));
        return CssLikeSelectorBuilder.getNumberOfElements(sspHandler, query)> 0;
    }

}