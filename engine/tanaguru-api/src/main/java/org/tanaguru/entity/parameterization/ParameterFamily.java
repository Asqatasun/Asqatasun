/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
 *
 * This file is part of Asqatasun.
 *
 * Asqatasun is free software: you can redistribute it and/or modify
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
 * Contact us by mail: asqatasun AT asqatasun DOT org
 */
package org.tanaguru.entity.parameterization;


import org.tanaguru.sdk.entity.Entity;

/**
 * 
 * @author jkowalczyk
 */
public interface ParameterFamily extends Entity {

    /**
     * 
     * @return
     */
    String getParameterFamilyCode();

    /**
     *
     * @param parameterFamilyCode
     */
    void setParameterFamilyCode(String parameterFamilyCode);

    /**
     *
     * @return
     */
    String getDescription();

    /**
     *
     * @param description
     */
    void setDescription(String description);

    /**
     *
     * @return
     */
    String getLongLabel();

    /**
     *
     * @param shortLabel
     */
    void setLongLabel(String shortLabel);

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