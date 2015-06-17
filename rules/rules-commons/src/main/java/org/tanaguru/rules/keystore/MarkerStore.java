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

package org.tanaguru.rules.keystore;

/**
 * Utility class that stores marker keys as static String
 */
public final class MarkerStore {

    public static final String DATA_TABLE_MARKER = "DATA_TABLE_MARKER";
    public static final String PRESENTATION_TABLE_MARKER = "PRESENTATION_TABLE_MARKER";
    public static final String COMPLEX_TABLE_MARKER = "COMPLEX_TABLE_MARKER";
    public static final String DECORATIVE_IMAGE_MARKER = "DECORATIVE_IMAGE_MARKER";
    public static final String INFORMATIVE_IMAGE_MARKER = "INFORMATIVE_IMAGE_MARKER";

    /**
     * Private constructor. This class handles keys and must not be instantiated
     */
    private MarkerStore() {}
    
}