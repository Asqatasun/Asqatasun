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

package org.tanaguru.rules.keystore;

/**
 *
 * Utility class that stores evidence keys that are not attribute names
 */
public final class EvidenceStore {

    public static final String PREVIOUS_H_TAG_INDEX_EE = "Previous-H-Tag-Index2";
    public static final String FIRST_H_TAG_INDEX_EE = "First-H-Tag-Index2";
    public static final String LANGUAGE_EE = "Language";
    public static final String DEFAULT_LANGUAGE_EE = "Default-Language";
    public static final String CURRENT_LANGUAGE_EE = "Current-Language";
    public static final String DETECTED_LANGUAGE_EE = "Detected-Language";
    public static final String EXTRACTED_TEXT_EE = "Extracted-Text";
    public static final String LINK_TITLE_ATTR_EE = "Link-Title-Attribut";
    public static final String LINK_TEXT_EE = "Link-Text";
    public static final String LINK_HREF_ATTR_EE = "Link-Href-Attribut";
    public static final String ELEMENT_NAME_EE = "Element-Name";
    public static final String CSS_SELECTOR_EE = "Css-Selector";
    public static final String CSS_FILENAME_EE = "Css-Filename";
    public static final String TARGETTED_ELEMENT_FROM_SCOPE_EE = "Element-Name";
    public static final String FG_COLOR_EE = "Foreground-Color";
    public static final String BG_COLOR_EE = "Background-Color";
    public static final String CONTRAST_EE = "Contrast";
    
    /**
     * private constructor. This class handles keys and must not be instanciated
     */
    private EvidenceStore() {}

}