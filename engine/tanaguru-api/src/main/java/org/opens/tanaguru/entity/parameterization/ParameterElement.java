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
 *  Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tanaguru.entity.parameterization;


import org.opens.tanaguru.sdk.entity.Entity;

/**
 * 
 * @author jkowalczyk
 */
public interface ParameterElement extends Entity {

    static String SCREEN_HEIGHT_KEY = "SCREEN_HEIGHT";
    static String SCREEN_WIDTH_KEY = "SCREEN_WIDTH";

    /**
     *
     * @return
     */
    String getParameterElementCode();

    /**
     *
     * @param parameterElementCode
     */
    void setParameterElementCode(String parameterElementCode);

    /**
     * 
     * @return
     */
    ParameterFamily getParameterFamily();

    /**
     *
     * @param parameterFamily
     */
    void setParameterFamily(ParameterFamily parameterFamily);

    /**
     *
     * @return
     */
    String getLongLabel();

    /**
     * 
     * @param longLabel
     */
    void setLongLabel(String longLabel);

    /**
     *
     * @return
     */
    String getShortLabel();

    /**
     * 
     * @param shortLabel
     */
    void setShortLabel(String shortLabel);

}