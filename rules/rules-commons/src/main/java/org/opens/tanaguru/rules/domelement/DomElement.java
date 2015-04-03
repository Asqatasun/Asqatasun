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

package org.opens.tanaguru.rules.domelement;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;


/**
 * POJO that handles all the extracted properties from the js script
 * while fetching the DOM.
 * 
 * @author jkowalczyk
 */
public class DomElement {

    private static final String PIXEL_KEY = "px";
    private static final float PIXEL_TO_POINT_MULTIPLIER = 0.75f;
    
    private static final String NORMAL_WEIGHT_KEY = "normal";
    private static final String BOLD_WEIGHT_KEY = "bold";
    private static final String LIGHTER_WEIGHT_KEY = "lighter";
    private static final String BOLDER_WEIGHT_KEY = "bolder";
    
    private static final int NORMAL_FONT_WEIGHT = 400;
    private static final int BOLD_FONT_WEIGHT = 700;
    /* */
    private static final String BACKGROUND_IMAGE_KEY="background-image:";
    private static final String ALPHA_COLOR_KEY="rgba";
    
    public static final String IS_HIDDEN_KEY = "isHidden";
    public static final String IS_TEXT_NODE_KEY = "isTextNode";
    public static final String ELEMENT_PATH_KEY = "path";
    public static final String FONT_SIZE_KEY = "fontSize";
    public static final String FONT_WEIGHT_KEY = "fontWeight";
    public static final String FG_COLOR_KEY = "color";
    public static final String BG_COLOR_KEY = "bgColor";
    public static final String IS_FOCUSABLE_KEY = "isFocusable";
    public static final String TEXT_ALIGN_KEY = "textAlign";
    public static final String OUTLINE_COLOR_FOCUS_KEY = 
            "outlineColorFocus";
    public static final String OUTLINE_WIDTH_FOCUS_KEY = 
            "outlineWidthFocus";
    public static final String OUTLINE_STYLE_FOCUS_KEY = 
            "outlineStyleFocus";
    public static final String NOT_EXISTING_PROPERTY = "notExisting";
    
    /**
     * All the values handled by the element identified by a key that 
     * corresponds to the json object element key.
     */
    Map<String, String> extractedProperties=new HashMap<>(); 
    
    public void addProperty(String key, String value) {
        extractedProperties.put(key, value);
    }
    
    public String getProperty(String key) {
        if (!extractedProperties.containsKey(key)) {
            return NOT_EXISTING_PROPERTY;
        }
        return extractedProperties.get(key);
    }
    
    /**
     * 
     * @return whether the current element is hidden 
     */
    public boolean isHidden() {
        return Boolean.valueOf(getProperty(IS_HIDDEN_KEY));
    }

    /**
     * 
     * @return whether the current element is a text node
     */
    public boolean isTextNode() {
        return Boolean.valueOf(getProperty(IS_TEXT_NODE_KEY));
    }
    
    /**
     * 
     * @return the current element path
     */
    public String getPath() {
        return getProperty(ELEMENT_PATH_KEY);
    }

    /**
     * 
     * @return whether the current element is bold
     */
    public boolean isBold() {
        return isBoldFromWeightValue(getWeight());
    }
    
    /**
     * 
     * @return the current element weight raw value
     */
    public String getWeight() {
        return getProperty(FONT_WEIGHT_KEY);
    }

    /**
     * 
     * @return whether the current element is focusable
     */
    public boolean isFocusable() {
        return Boolean.valueOf(getProperty(IS_FOCUSABLE_KEY));
    }

    /**
     * 
     * @return the current element weight raw value
     */
    public String getFontSize() {
        return getProperty(FONT_SIZE_KEY);
    }

    /**
     * 
     * @return the value of the textAlign property
     */
    public String getTextAlignValue() {
        return getProperty(TEXT_ALIGN_KEY);
    }

    public float getFontSizeInPt() {
        return Float.valueOf(getFontSize().substring(0,getFontSize().indexOf(PIXEL_KEY))) 
               * PIXEL_TO_POINT_MULTIPLIER;
    }

    /**
     * 
     * @return the current element raw foreground color
     */
    public String getFgColor() {
        return getProperty(FG_COLOR_KEY);
    }
    
    /**
     * 
     * @return the current element raw background color
     */
    public String getBgColor() {
        String bgColor = getProperty(BG_COLOR_KEY);
        if (StringUtils.startsWith(bgColor, ALPHA_COLOR_KEY)) {
            int alpha = getAlpha(bgColor);
            if (alpha == 0) {
                return "rgb(255; 255; 255)";
            } else if (alpha ==1) {
                return StringUtils.replace(bgColor, ", 0)", ")");
            }
        }
        return bgColor;
    }

    public String getDisplayableBgColor() {
        if (StringUtils.startsWith(getBgColor(), BACKGROUND_IMAGE_KEY)) {
            return StringUtils.substring(getBgColor(), getBgColor().indexOf(":")+1, getBgColor().length());
        }
        return getBgColor();
    }
    
    /**
     * 
     * @return the current element outline width (when focusable)
     */
    public String getOutlineWidth() {
        return getProperty(OUTLINE_WIDTH_FOCUS_KEY);
    }
    public float getOutlineWidthValue() {
        return Float.valueOf(getOutlineWidth().substring(0,getOutlineWidth().indexOf(PIXEL_KEY))); 
    }
    
    /**
     * 
     * @return the current element outline style (when focusable)
     */
    public String getOutlineStyle() {
        return getProperty(OUTLINE_STYLE_FOCUS_KEY);
    }
    
    /**
     * 
     * @return the current element outline color (when focusable)
     */
    public String getOutlineColor() {
        return getProperty(OUTLINE_COLOR_FOCUS_KEY);
    }

    /**
     * 
     * @param weight
     * @return
     */
    private boolean isBoldFromWeightValue (String weight) {
        int fontWeight;
        try {
            fontWeight = Integer.valueOf(weight);
        } catch (NumberFormatException nbe) {
            if (StringUtils.equalsIgnoreCase(weight, NORMAL_WEIGHT_KEY)) {
                fontWeight = NORMAL_FONT_WEIGHT;
            } else if (StringUtils.equalsIgnoreCase(weight, BOLD_WEIGHT_KEY)) {
                fontWeight = BOLD_FONT_WEIGHT;
            } else if (StringUtils.equalsIgnoreCase(weight, LIGHTER_WEIGHT_KEY)) {
                fontWeight = NORMAL_FONT_WEIGHT;
            } else if (StringUtils.equalsIgnoreCase(weight, BOLDER_WEIGHT_KEY)) {
                fontWeight = BOLD_FONT_WEIGHT;
            } else {
                fontWeight = NORMAL_FONT_WEIGHT;
            }
        }
        return fontWeight >= BOLD_FONT_WEIGHT;
    }

    private int getAlpha(String color) {
        String lColor = StringUtils.remove(StringUtils.remove(StringUtils.remove(color, ALPHA_COLOR_KEY),"("),")");
        String[] components = lColor.split(";");
        if (components.length !=4) {
            return -1;
        } else {
            return Integer.valueOf(components[3].trim());
        }
    }
    
}