/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
 *
 * This program is free software: you can redistribute it and/or modify
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

package org.tanaguru.rules.accessiweb21;

/**
 *
 * @author jkowalczyk
 */
public final class NodeAndAttributeKeyStore {

    public static final char SPACE_CHAR = ' ';
    public static final String TEXT_NODE = "#text";
    public static final String ALT_ATTR = "alt";
    public static final String LANG_ATTR = "lang";
    public static final String XML_LANG_ATTR = "xml:lang";
    public static final String TITLE_ATTR = "title";
    public static final String IMG_NODE = "IMG";
    public static final String SRC_ATTR = "src";
    public static final String EMPTY_STRING = "";
    public static final String HEAD_NODE = "HEAD";
    public static final String TTTLE_NODE = "TITLE";
    public static final String H1_NODE = "H1";
    public static final String BODY_NODE = "BODY";
    public static final String FIELDSET_NODE = "FIELDSET";
    public static final String LEGEND_NODE = "LEGEND";
    public static final String LABEL_ATTR = "label";
    public static final String SELECT_NODE = "SELECT";
    public static final String TEXTAREA_NODE = "TEXTAREA";
    public static final String INPUT_NODE = "INPUT";
    public static final String FOR_ATTR = "for";
    public static final String ID_ATTR = "id";
    public static final String CLASS_ATTR = "class";
    public static final String HREF_ATTR = "href";
    public static final String OBJECT_NODE = "OBJECT";
    public static final String COMMENT_NODE = "#comment";
    public static final String SPAN_NODE = "span";
    public static final String STRONG_NODE = "strong";
    public static final String AREA_NODE = "area";
    public static final String CAPTION_NODE = "caption";
    public static final String A_NODE = "A";
    public static final String NAME_ATTR = "name";
    public static final String SUMMARY_ATTR = "summary";
    public static final String CONTENT_ATTR = "content";
    public static final String VALUE_ATTR = "value";

    /**
     * Private constructor. This class handles keys and must not be instanciated
     */
    private NodeAndAttributeKeyStore(){}

}