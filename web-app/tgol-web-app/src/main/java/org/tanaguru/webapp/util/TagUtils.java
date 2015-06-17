/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015 Tanaguru.org
 *
 * This file is part of Tanaguru.
 *
 * Tanaguru is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us by mail: tanaguru AT tanaguru DOT org
 */
package org.tanaguru.webapp.util;

import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import javax.servlet.jsp.PageContext;

/**
 *
 * @author jkowalczyk
 */
public final class TagUtils {

    /**
     * Private constructor.  This class only handles static methods
     */
    private TagUtils(){}

    //Checks to see if Object 'o' is an instance of the class in the string
    public static boolean instanceOf(Object o, String className) {
        boolean returnValue;
        try {
            returnValue = Class.forName(className).isInstance(o);
        } catch (ClassNotFoundException e) {
            returnValue = false;
        }
        return returnValue;
    }
    
    /**
     * 
     * @param pageContext
     * @return 
     */
    public static String getLangFromPageContext(PageContext pageContext) {
        if (pageContext.getResponse().getLocale().getLanguage().
                equalsIgnoreCase(Locale.FRENCH.getLanguage())) {
            return Locale.FRENCH.getLanguage();
        } else if (pageContext.getResponse().getLocale().getLanguage().
                equalsIgnoreCase("es")) {
            return "es";
        }
        return Locale.ENGLISH.getLanguage();
    }
    
    /**
     * 
     * @param url
     * @return 
     */
    public static String getHostFromUrl(String url)  {
        try {
            return new URL(url).getHost();
        } catch (MalformedURLException ex) {
            return url;
        }
    }
    
    /**
     * 
     * @param rgbColor
     * @return 
     */
    public static String getHexColorFromRgb(String rgbColor)  {
        String[] colors = rgbColor.substring(4, rgbColor.length() - 1).split(",");
        Color convertColor = new java.awt.Color(
                Integer.valueOf(colors[0].trim()),
                Integer.valueOf(colors[1].trim()),
                Integer.valueOf(colors[2].trim()));
        return String.format("#%02x%02x%02x", convertColor.getRed(), convertColor.getGreen(), convertColor.getBlue()).toUpperCase();
    }
}