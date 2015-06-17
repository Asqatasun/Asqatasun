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
package org.tanaguru.webapp.form.builder;

import java.util.List;
import java.util.Map;
import org.tanaguru.webapp.form.SelectElement;

/**
 *
 * @author jkowalczyk
 */
public interface SelectFormFieldBuilder extends FormFieldBuilder {

    /**
     * Sets the map of SelectElementBuiler to build set instances of
     * SelectElements and associate them with each instance of SelectFormField
     * 
     * @param selectElementBuilderMap
     */
    void setSelectElementBuilderMap(Map<String,List<SelectElementBuilder>> selectElementBuilderMap);

    /**
     * Add a unique collection of selectElementBuilder to associate with a 
     * referential
     * 
     * @param refKey
     * @param selectElementBuilderList 
     */
    void addSelectElementBuilder(String refKey, List<SelectElementBuilder> selectElementBuilderList);
    
    /**
     *
     * @return
     *      the SelectElement Map that has to be set to each instance of SelectFormField
     */
    Map<String, List<SelectElement>> getSelectElementMap();

    /**
     *
     * @return
     *      the restriction code that has to be set to each instance of SelectFormField
     */
    String getRestrictionCode();

    /**
     * Sets the restriction code that has to be set to each instance of SelectFormField
     * @param restrictionCode
     */
    void setRestrictionCode(String restrictionCode);
    
    /**
     *
     * @return
     *      the activation option code that has to be set to each instance of SelectFormField
     */
    String getActivationCode();
    
    /**
     * Sets the activation option code that has to be set to each instance of SelectFormField
     * @param activationCode
     */
    void setActivationCode(String activationCode);

}