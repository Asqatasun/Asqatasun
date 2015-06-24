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
import org.jsoup.select.Elements;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.ElementHandler;
import org.tanaguru.ruleimplementation.ElementHandlerImpl;
import static org.tanaguru.rules.keystore.AttributeStore.TITLE_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.ARIA_LABEL_ATTR;
import static org.tanaguru.rules.keystore.AttributeStore.ARIA_LABELLEDBY_ATTR;
import org.tanaguru.rules.keystore.CssLikeQueryStore;
import static org.tanaguru.rules.keystore.CssLikeQueryStore.TEXT_LINK_CSS_LIKE_QUERY;
import org.tanaguru.rules.keystore.HtmlElementStore;
import org.tanaguru.rules.textbuilder.LinkTextElementBuilder;
import org.springframework.util.CollectionUtils;

/**
 * Element selector implementation that select text links.
 * The initial selection is split between results that have a context 
 * and results that have not. Each selection is then exposed
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
    private static Collection PARENT_CONTEXT_ELEMENTS = 
            CollectionUtils.arrayToList(PARENT_CONTEXT_ELEMENTS_TAB);
    
    /** */
    private static Collection PREV_SIBLING_CONTEXT_ELEMENTS = 
            CollectionUtils.arrayToList(PREV_SIBLING_CONTEXT_ELEMENTS_TAB);
    
    /** */
    private final ElementHandler<Element> decidableElements = new ElementHandlerImpl();
    public ElementHandler<Element> getDecidableElements() {
        return decidableElements;
    }

    /** */
    private final ElementHandler<Element> notDecidableElements = new ElementHandlerImpl();
    public ElementHandler<Element> getNotDecidableElements() {
        return notDecidableElements;
    }
    
    /* 
     * does the selection split results between the one that have a context 
     * and the one that have not
     */
    private boolean considerContext = true;
    public boolean considerContext() {
        return considerContext;
    }
    /* 
     * does the selection split results between the one that have a context 
     and the one that have not
     */
    private boolean considerTitleAsContext = true;
    public boolean considerTitleAsContext() {
        return considerTitleAsContext;
    }
    
    /* The element builder needed to build the link text */
    private final LinkTextElementBuilder linkTextElementBuilder = 
            new LinkTextElementBuilder();
    
    /**
     * 
     * @param considerContext 
     */
    public LinkElementSelector(boolean considerContext) {
        this.considerContext = considerContext;
    }
    
    /**
     * Constructor
     * @param considerTitleAsContext
     * @param considerContext 
     */
    public LinkElementSelector(boolean considerTitleAsContext, boolean considerContext) {
        this.considerContext = considerContext;
        this.considerTitleAsContext = considerTitleAsContext;
    }

    /**
     * 
     * @return 
     */
    protected String getCssLikeQuery() {
        return TEXT_LINK_CSS_LIKE_QUERY;
    }
    
    @Override
    public void selectElements(SSPHandler sspHandler, ElementHandler<Element> elementHandler) {
        // the elementHandler is ignored, the selection is handled by two 
        // local collections 
        Elements elements = sspHandler.beginCssLikeSelection().
                               domCssLikeSelectNodeSet(getCssLikeQuery()).
                               getSelectedElements();
        characteriseElements(elements);
        if (elementHandler != null) {
            elementHandler.addAll(notDecidableElements.get());
            elementHandler.addAll(decidableElements.get());
        }
    }

    /**
     * Expose the selectElement without ElementHandler argument to delegate 
     * the null value usage responsibility to the current class
     * @param sspHandler 
     */
    public void selectElements(SSPHandler sspHandler) {
        this.selectElements(sspHandler, null);
    }

    public boolean isEmpty() {
        return notDecidableElements.isEmpty() && decidableElements.isEmpty();
    }
    
    /**
     * 
     * @param elements 
     */
    protected void characteriseElements(Elements elements) {
        for (Element el : elements) {
            characteriseElement(el);
        }
    }
    
    /**
     * 
     * @param element 
     */
    protected void characteriseElement(Element element) {
        String linkText = getLinkText(element);
        if (!isLinkPartOfTheScope(element, linkText)) {
            return;
        }
        if (considerContext) {
            if (doesLinkHaveContext(element, linkText)) {
                notDecidableElements.add(element);
            } else {
                decidableElements.add(element);
            }
        } else {
            decidableElements.add(element);   
        }
    }

        /**
     * 
     * @param linkElement
     * @return the link text
     */
    protected String getLinkText(Element linkElement) {
        return linkTextElementBuilder.buildTextFromElement(linkElement);
    }
    
    /**
     * 
     * @param linkElement
     * @param linkText
     * @return whether the link is part o the scope, i.e the link text is not 
     * empty
     */
    protected boolean isLinkPartOfTheScope(Element linkElement, String linkText) {
        return StringUtils.isNotBlank(linkText);
    }
    
    /**
     * 
     * @param linkElement
     * @param linkText
     * @return whether the current link have a context
     */
    protected boolean doesLinkHaveContext(Element linkElement, String linkText) {
        // does the current link have a title attribute? 
        if (considerTitleAsContext && 
                linkElement.hasAttr(TITLE_ATTR) && 
                !StringUtils.equalsIgnoreCase(linkElement.attr(TITLE_ATTR), linkText)) {
            return true;
        }
        if (linkElement.hasAttr(ARIA_LABEL_ATTR) && 
                StringUtils.isNotBlank(linkElement.attr(ARIA_LABEL_ATTR))) {
            return true;
        }
        if (linkElement.hasAttr(ARIA_LABELLEDBY_ATTR) && 
                StringUtils.isNotBlank(linkElement.attr(ARIA_LABELLEDBY_ATTR))) {
            return true;
        }
        // does the parent of the current link have some text?
        if (StringUtils.isNotBlank(linkElement.parent().ownText())) {
            return true;
        }
        // does the current element have a previous sibling of heading type?
        if (isOneOfPrecedingSiblingofHeadingType(linkElement)) {
            return true;
        }
        // does one of the parent of the current element have a previous sibling 
        // of heading type or is found in the PARENT_CONTEXT_ELEMENTS list?
        for (Element parent : linkElement.parents()) {
            if (PARENT_CONTEXT_ELEMENTS.contains(parent.tagName()) || 
                    isOneOfPrecedingSiblingofHeadingType(parent)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param element
     * @return whether one of the preceding sibling is of heading type
     */
    private boolean isOneOfPrecedingSiblingofHeadingType(Element element) {
        Element prevElementSibling = element.previousElementSibling();
        while (prevElementSibling != null) {
            if (PREV_SIBLING_CONTEXT_ELEMENTS.contains(prevElementSibling.tagName()) || 
                    !prevElementSibling.select(CssLikeQueryStore.HEADINGS_CSS_LIKE_QUERY).isEmpty()) {
                return true;
            }
            prevElementSibling = prevElementSibling.previousElementSibling();
        }
        return false;
    }
    
}