/*
 *  Tanaguru - Automated webpage assessment
 *  Copyright (C) 2008-2011  Open-S Company
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

package org.opens.webdriver.builder;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author jkowalczyk
 */
public abstract class ScenarioBuilder {

    private static String SELENIUM_VERSION_KEY="\"seleniumVersion\"";
    private static String SELENIUM_VERSION_VALUE="\"2\"";

    private static String FORMAT_VERSION_KEY="\"formatVersion\"";
    private static String FORMAT_VERSION_VALUE="1";
    
    private static String STEPS_KEY="\"steps\"";
    
    private static String TYPE_KEY="\"type\"";
    private static String URL_KEY="\"url\"";
    private static String GET_TYPE="\"get\"";
    
    private static char LEFT_SQUARE_BRACKET = '[';
    private static char RIGHT_SQUARE_BRACKET = ']';
    private static char COMMA = ',';
    private static char COLON = ':';
    private static char LEFT_BRACE = '{';
    private static char RIGHT_BRACE = '}';
    private static char DOUBLE_QUOTE = '"';
    
    private static String header;
    private static String footer;
    
    public static String buildScenario(String url) {
        if (url == null) {
            return null;
        }
        StringBuilder strb = new StringBuilder();
        strb.append(buildHeader());
        strb.append(buildGetStep(url));
        strb.append(buildFooter());
        return strb.toString();
    }

    public static String buildScenario(List<String> urlList) {
        if (urlList == null || urlList.isEmpty()) {
            return null;
        }
        StringBuilder strb = new StringBuilder();
        strb.append(buildHeader());
        Iterator<String> iter = urlList.iterator();
        while (iter.hasNext()) {
            strb.append(buildGetStep((iter.next())));
            if (iter.hasNext()) {
                strb.append(COMMA);
            }
        }
        strb.append(buildFooter());
        return strb.toString();
    }
        
    private static String buildHeader() {
        if (header == null) {
            StringBuilder strb = new StringBuilder();
            strb.append(LEFT_BRACE);
            strb.append(SELENIUM_VERSION_KEY);
            strb.append(COLON);
            strb.append(SELENIUM_VERSION_VALUE);
            strb.append(COMMA);
            strb.append(FORMAT_VERSION_KEY);
            strb.append(COLON);
            strb.append(FORMAT_VERSION_VALUE);
            strb.append(COMMA);
            strb.append(STEPS_KEY);
            strb.append(COLON);
            strb.append(LEFT_SQUARE_BRACKET);
            header = strb.toString();
        }
        return header;
    }
        
    
    private static String buildFooter() {
        if (footer == null) {
            StringBuilder strb = new StringBuilder();
            strb.append(RIGHT_SQUARE_BRACKET);
            strb.append(RIGHT_BRACE);
            footer = strb.toString();
        }
        return footer;
    }
    
    private static String buildGetStep(String url) {
        StringBuilder strb = new StringBuilder();
        strb.append(LEFT_BRACE);
        strb.append(TYPE_KEY);
        strb.append(COLON);
        strb.append(GET_TYPE);
        strb.append(COMMA);
        strb.append(URL_KEY);
        strb.append(COLON);
        strb.append(DOUBLE_QUOTE);
        strb.append(url);
        strb.append(DOUBLE_QUOTE);
        strb.append(RIGHT_BRACE);
        return strb.toString();
    }

}