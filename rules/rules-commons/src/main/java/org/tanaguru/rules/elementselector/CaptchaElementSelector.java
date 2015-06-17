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

import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.tanaguru.processor.SSPHandler;
import org.tanaguru.ruleimplementation.ElementHandler;
import static org.tanaguru.rules.keystore.HtmlElementStore.BODY_ELEMENT;
import static org.tanaguru.rules.keystore.HtmlElementStore.HTML_ELEMENT;

/**
 * Element selector implementation that searches the "captcha" occurence 
 * on the page and determines whether this occurrence is handled by an element
 * implied by the test.
 * 
 */
public class CaptchaElementSelector implements ElementSelector {

    /** the captcha key */
    private static final String CAPTCHA_KEY = "captcha";
    
    /* The css query used to retrieve Elements */
    private ElementSelector elementSelector;

    /** the pre-selected image elements */
    private ElementHandler<Element> imageHandler;
    
    /**
     * @param elementSelector 
     */
    public CaptchaElementSelector(ElementSelector elementSelector) {
        this.elementSelector = elementSelector;
    }

    /**
     * @param imageHandler
     */
    public CaptchaElementSelector(ElementHandler<Element> imageHandler) {
        this.imageHandler = imageHandler;
    }
    
    @Override
    public void selectElements(SSPHandler sspHandler, ElementHandler<Element> selectionHandler) {
        if (!StringUtils.containsIgnoreCase(sspHandler.getSSP().getDOM(), CAPTCHA_KEY)) {
            return;
        }
        if (elementSelector != null) {
            elementSelector.selectElements(sspHandler, selectionHandler);
        } else if (imageHandler != null) {
            selectionHandler.addAll(imageHandler.get());
        }
        extractCaptchaElements(selectionHandler);
    }

    /**
     * This methods parses all the elements retrieved from the scope and extracts
     * the ones where the occurrence "captcha" is found among the attribute values
     *
     * @param selectionHandler
     */
    public void extractCaptchaElements(ElementHandler<Element> selectionHandler) {
        if (selectionHandler.isEmpty()) {
            return;
        }
        Set<Element> captchaElements = new HashSet<>();
        for (Element el : selectionHandler.get()) {
            if (parseAttributeToExtractCaptcha(el)) {
                captchaElements.add(el);
            } else {
                for (Element sel : getSiblingsAndParents(el)){
                    if (!el.nodeName().equalsIgnoreCase(sel.nodeName()) && 
                        parseAttributeToExtractCaptcha(sel)) {
                        captchaElements.add(el);
                        break;
                    }
                }
            }
        }
        selectionHandler.clean();
        for (Element el : captchaElements) {
            selectionHandler.add(el);
        }
    }

    /**
     * 
     * @param el
     * @return all the parents and the siblings of the element
     */
    private Elements getSiblingsAndParents(Element el) {
        Elements siblingsAndParents = new Elements();
        siblingsAndParents.addAll(el.siblingElements());
        siblingsAndParents.addAll(el.parents());
        return siblingsAndParents;
    }
    
    /**
     *
     * @param element
     * @return wheter either one attribute of the current element, either its
     * text, either one attribute of one of its parent or the text of one of
     * its parents contains the "captcha" keyword
     */
    private boolean parseAttributeToExtractCaptcha(Element element) {
        if (element.nodeName().equalsIgnoreCase(HTML_ELEMENT) || 
                element.nodeName().equalsIgnoreCase(BODY_ELEMENT)) {
            return false;
        }
        if (StringUtils.containsIgnoreCase(element.ownText(), CAPTCHA_KEY)) {
            return true;
        } else {
            for (Attribute attr : element.attributes()) {
                if (StringUtils.containsIgnoreCase(attr.getValue(), CAPTCHA_KEY)) {
                    return true;
                }
            }
        }
        return false;
    }

}