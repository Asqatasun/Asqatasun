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
public final class EvidenceKeyStore {

    public static final String SRC_EE = "src";
    public static final String HREF_EE = "href";
    public static final String PREVIOUS_H_TAG_INDEX_EE = "Previous-H-Tag-Index";
    public static final String FIRST_H_TAG_INDEX_EE = "First-H-Tag-Index";
    public static final String NODE_EE = "Node";
    public static final String LANGUAGE_EE = "Language";
    public static final String DETECTED_LANGUAGE_EE = "Detected-Language";
    public static final String EXTRACTED_TEXT_EE = "Extracted-Text";
    public static final String LINK_TITLE_ATTR_EE = "Link-Title-Attribut";
    public static final String LINK_TEXT_EE = "Link-Text";
    public static final String LINK_HREF_ATTR_EE = "Link-Href-Attribut";
    
    /**
     * private constructor. This class handles keys and must not be instanciated
     */
    private EvidenceKeyStore() {}

}