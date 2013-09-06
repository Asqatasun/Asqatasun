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

package org.opens.tanaguru.rules.keystore;

import javax.swing.text.html.HTML;

/**
 * Utility class that stores html attributes as static String
 */
public final class AttributeStore {

    /**
     * Returned key when an attribute requested as evidence element is missing
     */
    public static final String ABSENT_ATTRIBUTE_VALUE = "attribute-absent";
    
    public static final String ALT_ATTR = HTML.Attribute.ALT.toString();
    public static final String CLASS_ATTR = HTML.Attribute.CLASS.toString();
    public static final String CODE_ATTR = HTML.Attribute.CODE.toString();
    public static final String CONTENT_ATTR = HTML.Attribute.CONTENT.toString();
    public static final String DATA_ATTR = HTML.Attribute.DATA.toString();
    public static final String FOR_ATTR = "for";
    public static final String HREF_ATTR = HTML.Attribute.HREF.toString();
    public static final String ID_ATTR = HTML.Attribute.ID.toString();
    public static final String LABEL_ATTR = "label";
    public static final String LANG_ATTR = HTML.Attribute.LANG.toString();
    public static final String LONGDESC_ATTR = "longdesc";
    public static final String NAME_ATTR = HTML.Attribute.NAME.toString();
    public static final String ROLE_ATTR = "role";
    public static final String SRC_ATTR = HTML.Attribute.SRC.toString();
    public static final String SUMMARY_ATTR = "summary";
    public static final String TITLE_ATTR = HTML.Attribute.TITLE.toString();
    public static final String USEMAP_ATTR = HTML.Attribute.USEMAP.toString();
    public static final String VALUE_ATTR = HTML.Attribute.VALUE.toString();
    public static final String WIDTH_ATTR = HTML.Attribute.WIDTH.toString();
    public static final String HEIGHT_ATTR = HTML.Attribute.HEIGHT.toString();
    public static final String XML_LANG_ATTR = "xml:lang";
    
    /**
     * Private constructor. This class handles keys and must not be instantiated
     */
    private AttributeStore() {}
    
}