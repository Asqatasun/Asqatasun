/*
 *  Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
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

import java.util.Collection;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.ElementHandler;
import org.tanaguru.rules.elementselector.builder.CssLikeSelectorBuilder;
import static org.tanaguru.rules.keystore.AttributeStore.FOR_ATTR;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.FORM_ELEMENT_CSS_LIKE_QUERY;
import static org.tanaguru.rules.keystore.HtmlElementStore.LABEL_ELEMENT;

/**
 * 
 */
public class InputFormElementWithExplicitLabelSelector implements ElementSelector {

    /** the pre-selected input form elements */
    private ElementHandler<Element> formElementHandler;
    
    /**
     * Default constructor
     */
    public InputFormElementWithExplicitLabelSelector() {}
    
    /**
     * Constructor
     * @param formElementHandler 
     */
    public InputFormElementWithExplicitLabelSelector(ElementHandler<Element> formElementHandler) {
        this.formElementHandler = formElementHandler;
    }

    @Override
    public void selectElements(SSPHandler sspHandler, ElementHandler<Element> selectionHandler) {
        for (Element el : getElements(sspHandler)) {
            if (StringUtils.isNotBlank(el.id()) && 
                    isLabelElementWithForAttributeExists(sspHandler, el.id())) {
                selectionHandler.add(el);
            }
        }
    }

        /**
     * 
     * @param sspHandler
     * @return the elements implied by the test
     */
    private Collection<Element> getElements (SSPHandler sspHandler) {
        if (formElementHandler != null) {
            return formElementHandler.get();
        } else {
            return sspHandler.beginCssLikeSelection().
                domCssLikeSelectNodeSet(FORM_ELEMENT_CSS_LIKE_QUERY).
                getSelectedElements();
        }
    }
    
    /**
     * 
     * @param sspHandler
     * @param id
     * @return whether a label element with the for attribute equals to the id
     * passed as argument is found on the page
     */
    private boolean isLabelElementWithForAttributeExists (SSPHandler sspHandler, String id) {
        String query = CssLikeSelectorBuilder.buildSelectorFromElementsAndAttributeValue(
                        LABEL_ELEMENT,
                        FOR_ATTR, 
                        id);
        return !sspHandler.domCssLikeSelectNodeSet(query).getSelectedElements().isEmpty();
    }

}