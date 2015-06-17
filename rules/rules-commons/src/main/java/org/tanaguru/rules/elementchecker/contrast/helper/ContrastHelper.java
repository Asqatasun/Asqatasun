/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2015 Tanaguru.org
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

package org.tanaguru.rules.elementchecker.contrast.helper;

import java.awt.Color;
import org.apache.commons.lang3.StringUtils;


/**
 * This utility class provides methods to compute contrast between two colors
 * 
 * @author jkowalczyk
 */
public final class ContrastHelper {

    /* */
    private static final String BACKGROUND_IMAGE_KEY="image";
    /* */
    private static final String GRADIENT_KEY="gradient";
    /* */
    private static final String ALPHA_COLOR_KEY="rgba";
    /* */
    private static final String RGB_COLOR_KEY="rgb(";
    /* */
    private static final String TRANSPARENT_KEY="transparent";
    /* */
    private static final char OPEN_PARENTHESE='(';
    /* */
    private static final char CLOSE_PARENTHESE=')';
    /* */
    private static final String COMPOSANT_SEPARATOR=";";
    
    /* */
    private static final double RED_FACTOR = 0.2126;
    /* */
    private static final double GREEN_FACTOR = 0.7152;
    /* */
    private static final double BLUE_FACTOR = 0.0722;
    /* */
    private static final double CONTRAST_FACTOR = 0.05;
    /* */
    private static final double RGB_MAX_VALUE = 255;
    /* */
    private static final double RSGB_FACTOR = 0.03928;
    /* */
    private static final double LUMINANCE_INF = 12.92;
    /* */
    private static final double LUMINANCE_SUP_CONST = 0.055;
    /* */
    private static final double LUMINANCE_SUP_CONST2 = 1.055;
    /* */
    private static final double LUMINANCE_EXP = 2.4;

    /**
     *
     */
    private ContrastHelper() {
    }

    /**
     * 
     * @param color
     * @return whether the color is testable, i.e defined as a rgb color
     */
    public static boolean isColorTestable(final String color) {
        return !StringUtils.contains(color, BACKGROUND_IMAGE_KEY) &&
               !StringUtils.contains(color, GRADIENT_KEY) && 
               !StringUtils.contains(color, ALPHA_COLOR_KEY) && 
               !StringUtils.equalsIgnoreCase(color, TRANSPARENT_KEY) &&
               StringUtils.startsWith(color, RGB_COLOR_KEY);
    }   

    /**
     * 
     * @param color
     * @return 
     */
    public static Color getColorFromString(final String color) {
        if (StringUtils.equalsIgnoreCase(color, TRANSPARENT_KEY)) {
            return Color.WHITE;
        }
        String[] comps = StringUtils.substring(
                             color, 
                             StringUtils.indexOf(color, OPEN_PARENTHESE)+1, 
                             StringUtils.indexOf(color, CLOSE_PARENTHESE))
                         .split(COMPOSANT_SEPARATOR);
        return new Color(Integer.valueOf(comps[0].trim()), 
                         Integer.valueOf(comps[1].trim()), 
                         Integer.valueOf(comps[2].trim()));
    }
    
    /**
     *
     * @param fgColor
     * @param bgColor
     * @param coefficientLevel
     * @return
     */
    public static boolean isContrastValid(final String fgColor, final String bgColor, Float coefficientLevel) {
        return getConstrastRatio(getColorFromString(fgColor), getColorFromString(bgColor)) > coefficientLevel;
    }
    
    /**
     *
     * @param fgColor
     * @param bgColor
     * @param coefficientLevel
     * @return
     */
    public static boolean isContrastValid(final Color fgColor, final Color bgColor, Float coefficientLevel) {
        return getConstrastRatio(fgColor, bgColor) > coefficientLevel;
    }

    /**
     * This method computes the contrast ratio between 2 colors. It needs to
     * determine which one is lighter first.
     *
     * @param fgColor
     * @param bgColor
     * @return the contrast ratio between the 2 colors
     */
    public static double getConstrastRatio(final String fgColor, final String bgColor) {
        return getConstrastRatio(getColorFromString(fgColor), getColorFromString(bgColor));
    }
    
    /**
     * This method computes the contrast ratio between 2 colors. It needs to
     * determine which one is lighter first.
     *
     * @param fgColor
     * @param bgColor
     * @return the contrast ratio between the 2 colors
     */
    public static double getConstrastRatio(final Color fgColor, final Color bgColor) {
        double fgLuminosity = getLuminosity(fgColor);
        double bgLuminosity = getLuminosity(bgColor);
        if (fgLuminosity > bgLuminosity) {
            return computeContrast(fgLuminosity, bgLuminosity);
        } else {
            return computeContrast(bgLuminosity, fgLuminosity);
        }
    }

    /**
     *
     * @param lighter
     * @param darker
     * @return
     */
    private static double computeContrast(double lighter, double darker) {
        return (((lighter + CONTRAST_FACTOR) / (darker + CONTRAST_FACTOR)));
    }

    /**
     *
     * @param color
     * @return
     */
    public static double getLuminosity(Color color) {
        double luminosity =
                getComposantValue(color.getRed()) * RED_FACTOR
                + getComposantValue(color.getGreen()) * GREEN_FACTOR
                + getComposantValue(color.getBlue()) * BLUE_FACTOR;

        return luminosity;
    }

    /**
     *
     * @param composant
     * @return
     */
    private static double getComposantValue(double composant) {
        double rsgb = composant / RGB_MAX_VALUE;
        if (rsgb <= RSGB_FACTOR) {
            return rsgb / LUMINANCE_INF;
        } else {
            return Math.pow(((rsgb + LUMINANCE_SUP_CONST) / LUMINANCE_SUP_CONST2), LUMINANCE_EXP);
        }
    }
}