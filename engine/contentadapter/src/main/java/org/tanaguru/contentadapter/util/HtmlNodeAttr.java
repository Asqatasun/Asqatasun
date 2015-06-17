/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2015  Tanaguru.org
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
package org.tanaguru.contentadapter.util;

/**
 * 
 * @author jkowalczyk
 */
public abstract class HtmlNodeAttr {

    /**
     * Private constructor  : Hide Utility Class Constructor
     */
    private HtmlNodeAttr() {}
    
    public final static String CLASS = "class";
    public final static String HREF = "href";
    public final static String ID = "id";
    public final static String LINK = "link";
    public final static String REL = "rel";
    public final static String SRC = "src";
    public final static String STYLE = "style";
    public final static String MEDIA = "media";
    public final static String TYPE = "type";

}