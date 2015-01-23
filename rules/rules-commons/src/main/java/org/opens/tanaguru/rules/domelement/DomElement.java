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
    
    /* whether the current element is hidden*/
    private boolean isHidden;
    public boolean isHidden() {
        return isHidden;
    }
    public void setHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }
    
    /* whether the current element is a text node*/
    private boolean isTextNode;
    public boolean isTextNode() {
        return isTextNode;
    }
    public void setTextNode(boolean isTextNode) {
        this.isTextNode = isTextNode;
    }
    
    /* the current element path*/
    private String path;
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    /* whether the current element is bold*/
    private boolean isBold;
    public boolean isBold() {
        return isBold;
    }
    
    /* the current element weight raw value*/
    private String weight;
    public String getWeight() {
        return weight;
    }
    public void setWeight(String weight) {
        this.weight = weight;
        isBold = isBoldFromWeightValue(weight);
    }

    /* whether the current element is focusable*/
    private boolean isFocusable;
    public boolean isFocusable() {
        return isFocusable;
    }
    public void setFocusable(boolean isFocusable) {
        this.isFocusable = isFocusable;
    }
    
    /* the current element weight raw value*/
    private String fontSize;
    public String getFontSize() {
        return fontSize;
    }
    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    public float getFontSizeInPt() {
        return Float.valueOf(fontSize.substring(0,fontSize.indexOf(PIXEL_KEY))) 
               * PIXEL_TO_POINT_MULTIPLIER;
    }

    /* the current element raw foreground color*/
    private String fgColor;
    public String getFgColor() {
        return fgColor;
    }

    public void setFgColor(String fgColor) {
        this.fgColor = fgColor;
    }
    
    /* the current element raw background color*/
    private String bgColor;
    public String getBgColor() {
        return bgColor;
    }

    public String getDisplayableBgColor() {
        if (StringUtils.startsWith(bgColor, BACKGROUND_IMAGE_KEY)) {
            return StringUtils.substring(bgColor, bgColor.indexOf(":")+1, bgColor.length());
        }
        return bgColor;
    }
    
    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }
    
    /* the current element outline width (when focusable)*/
    private String outlineWidth;
    public String getOutlineWidth() {
        return outlineWidth;
    }
    public float getOutlineWidthValue() {
        return Float.valueOf(outlineWidth.substring(0,outlineWidth.indexOf(PIXEL_KEY))); 
    }

    public void setOutlineWidth(String outlineWidth) {
        this.outlineWidth = outlineWidth;
    }
    
    /* the current element outline style (when focusable)*/
    private String outlineStyle;
    public String getOutlineStyle() {
        return outlineStyle;
    }

    public void setOutlineStyle(String outlineStyle) {
        this.outlineStyle = outlineStyle;
    }
    
    /* the current element outline color (when focusable)*/
    private String outlineColor;
    public String getOutlineColor() {
        return outlineColor;
    }

    public void setOutlineColor(String outlineColor) {
        this.outlineColor = outlineColor;
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

}