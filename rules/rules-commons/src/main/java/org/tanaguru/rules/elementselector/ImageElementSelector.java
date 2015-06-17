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

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.ElementHandler;
import org.tanaguru.ruleimplementation.ElementHandlerImpl;
import static org.tanaguru.rules.keystore.HtmlElementStore.A_ELEMENT;

/**
 * Image elements selector. 
 * 
 */
public class ImageElementSelector extends SimpleElementSelector {

    /* The inner element selector used to retrieve Elements */
    private ElementSelector elementSelector;
    
    /* exclude image Link from the scope. Default is false */
    private boolean excludeImageLink = false;
    
    /* exclude composite Link from the scope. Default is false*/
    private boolean excludeCompositeLink = false;

    /**
     * Constructor
     * @param cssLikeQuery 
     */
    public ImageElementSelector(String cssLikeQuery) {
        super(cssLikeQuery);
    }
    
    /**
     * Constructor
     * @param elementSelector
     */
    public ImageElementSelector(ElementSelector elementSelector) {
        this.elementSelector = elementSelector;
    }
    
    /**
     * Constructor
     * @param cssLikeQuery
     * @param excludeImageLink
     * @param excludeCompositeLink 
     */
    public ImageElementSelector(String cssLikeQuery, 
                                boolean excludeImageLink, 
                                boolean excludeCompositeLink) {
        super(cssLikeQuery);
        this.excludeImageLink = excludeImageLink;
        this.excludeCompositeLink = excludeCompositeLink;
    }
    
    /**
     * Constructor
     * @param elementSelector
     * @param excludeImageLink
     * @param excludeCompositeLink 
     */
    public ImageElementSelector(ElementSelector elementSelector, 
                                boolean excludeImageLink, 
                                boolean excludeCompositeLink) {
        this.elementSelector = elementSelector;
        this.excludeImageLink = excludeImageLink;
        this.excludeCompositeLink = excludeCompositeLink;
    }

    @Override
    public void selectElements(SSPHandler sspHandler, ElementHandler<Element> elementHandler) {
        if (elementSelector != null) {
            elementSelector.selectElements(sspHandler, elementHandler);
        } else {
            super.selectElements(sspHandler, elementHandler);
        }
        // we search the captcha from the selection and remove them from 
        // the selection
        ElementHandler<Element> captchaHandler = new ElementHandlerImpl();
        CaptchaElementSelector captchaElementSelector = 
                new CaptchaElementSelector(elementHandler);
        captchaElementSelector.selectElements(sspHandler, captchaHandler);
        elementHandler.removeAll(captchaHandler);
        
        excludeLinksFromSelection(elementHandler);
    }

    /**
     * 
     * @param elementHandler 
     */
    private void excludeLinksFromSelection(ElementHandler<Element> elementHandler) {
        if (!excludeCompositeLink && !excludeImageLink) {
            return;
        }
        ElementHandler<Element> linkHandler = new ElementHandlerImpl();
        for (Element el: elementHandler.get()) {
            Element link = el.parents().select(A_ELEMENT).first();
            if (excludeImageLink && isImageLink(link, el)){
                linkHandler.add(el);
            } else if (excludeCompositeLink && isCompositeLink(link, el)) {
                linkHandler.add(el);
            }
        }
        elementHandler.removeAll(linkHandler);
    }

    /**
     * 
     * @param imageParent
     * @param image
     * @return whether the current image is an image link
     */
    private boolean isImageLink(Element imageParent, Element image) {
        if (imageParent == null || !StringUtils.equals(imageParent.text(), image.text())) {
            return false;
        }
        if (imageParent.children().size() == 1) {
            return isImageLink(imageParent.child(0), image);
        } else if (imageParent.children().isEmpty() && imageParent.equals(image)) {
            return true;
        }
        return false;
    }

    /**
     * An link is seen as composite when it is composed with more than one 
     * element. The tested element has at least one image. If the text is different
     * from the one of the child element, the link is composite by definition. 
     * It the text is identical, we check whether the current element has more
     * than 1 child.
     * @param imageParent
     * @return whether the current image is a composite link.
     */
    private boolean isCompositeLink(Element imageParent, Element image) {
        if (imageParent == null) {
            return false;
        }
        if (!StringUtils.equals(imageParent.text(), image.text())) {
            return true;
        }
        if (imageParent.children().size() == 1) {
            return isCompositeLink(imageParent.child(0), image);
        } else if (imageParent.children().size() > 1) {
            return true;
        }
        return false;
    }

}