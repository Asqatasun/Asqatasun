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
package org.opens.tanaguru.rules.domelement.extractor;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.NoResultException;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.opens.tanaguru.processor.SSPHandler;
import org.opens.tanaguru.rules.domelement.DomElement;

/**
 * Utility class that extracts the js Parser results (Pre process result with 
 * the key "colorExtractor") and returns a collection of POJO DomElement
 * @author jkowalczyk
 */
public final class DomElementExtractor {
    
    private static final Logger LOGGER = Logger.getLogger(DomElementExtractor.class);
    private static final String COLOR_EXTRACTOR_PRE_PROCESS_RESULT_KEY = 
            "colorExtractor";
    private static final String JSON_IS_HIDDEN_KEY = "isHidden";
    private static final String JSON_IS_TEXT_NODE_KEY = "isTextNode";
    private static final String JSON_ELEMENT_PATH_KEY = "path";
    private static final String JSON_FONT_SIZE_KEY = "fontSize";
    private static final String JSON_FONT_WEIGHT_KEY = "fontWeight";
    private static final String JSON_FG_COLOR_KEY = "color";
    private static final String JSON_BG_COLOR_KEY = "bgColor";
    private static final String JSON_IS_FOCUSABLE_KEY = "isFocusable";
    private static final String JSON_OUTLINE_COLOR_FOCUS_KEY = 
            "outlineColorFocus";
    private static final String JSON_OUTLINE_WIDTH_FOCUS_KEY = 
            "outlineWidthFocus";
    private static final String JSON_OUTLINE_STYLE_FOCUS_KEY = 
            "outlineStyleFocus";
    
    /**
     * Private constructor for utility class
     */
    private DomElementExtractor(){}
    
    /**
     * 
     * @param sspHandler
     * @return a collection of instanciated DomElement
     * @throws NoResultException 
     */
    public static Collection<DomElement> extractDomElements(SSPHandler sspHandler) throws NoResultException {
        String ppr = sspHandler.getPreProcessResult(
                COLOR_EXTRACTOR_PRE_PROCESS_RESULT_KEY,
                sspHandler.getPage());
        Collection<DomElement> domElements = new ArrayList<DomElement>();
        
        JSONArray json;
        try {
            json = new JSONArray(ppr);
            for (int i = 0; i < json.length(); i++) {
                domElements.add(
                        getDomElementFromJson(
                            new JSONObject(json.get(i).toString())));
            }
        } catch (JSONException ex) {
            LOGGER.warn(ex);
        }
        return domElements;
    }

    /**
     * 
     * @param element
     * @param sspHandler
     * @return the element associated with the domElement
     */
    public static Element getElementFromDomElement(DomElement element, SSPHandler sspHandler) {
        Elements elements = sspHandler.domCssLikeSelectNodeSet(element.getPath()).
                    getSelectedElements();
        if (elements.isEmpty()) {
             LOGGER.warn(" cssPath " + element.getPath()+ " returns no element on " +
                    sspHandler.getSSP().getURI() 
                    + " The result of the test is set to Not tested");
            return null;
        }
        if(elements.size() > 1) {
            // if any element can't be retrieved by jsoup, that means that
            // something is weird with the dom. The check is stopped, and
            // the test returns not_tested to avoid false positive results
            LOGGER.warn(
                    " cssPath " + element.getPath()+ " returns more than one element on " +
                    sspHandler.getSSP().getURI() 
                    + " The result of the test is set to Not tested");
            LOGGER.warn(
                    " cssPath " + element.getPath()+ " returns more than one element on " +
                    sspHandler.getSSP().getURI());
            return null;
        }
        return elements.first();
    }
    
    /**
     * 
     * @param element
     * @return an instance of DomElement
     * @throws JSONException 
     */
    private static DomElement getDomElementFromJson(JSONObject element) throws JSONException{
        DomElement domElement = new DomElement();
        domElement.setBgColor(element.get(JSON_BG_COLOR_KEY).toString());
        domElement.setFgColor(element.get(JSON_FG_COLOR_KEY).toString());
        domElement.setFocusable(
                Boolean.valueOf(element.get(JSON_IS_FOCUSABLE_KEY).toString()));
        domElement.setFontSize(element.get(JSON_FONT_SIZE_KEY).toString());
        domElement.setHidden(
                Boolean.valueOf(element.get(JSON_IS_HIDDEN_KEY).toString()));
        if (domElement.isFocusable()) {
            domElement.setOutlineColor(
                    element.get(JSON_OUTLINE_COLOR_FOCUS_KEY).toString());
            domElement.setOutlineStyle(element.get(
                    JSON_OUTLINE_STYLE_FOCUS_KEY).toString());
            domElement.setOutlineWidth(
                    element.get(JSON_OUTLINE_WIDTH_FOCUS_KEY).toString());
        }
        domElement.setPath(element.get(JSON_ELEMENT_PATH_KEY).toString());
        domElement.setTextNode(
                Boolean.valueOf(element.get(JSON_IS_TEXT_NODE_KEY).toString()));
        domElement.setWeight(element.get(JSON_FONT_WEIGHT_KEY).toString());
        return domElement;
    }

}