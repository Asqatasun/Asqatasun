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
package org.tanaguru.webapp.form;

import java.util.List;
import java.util.Map;

/**
 *
 * @author jkowalczyk
 */
public interface SelectFormField extends FormField {

    /**
     * 
     * @param selectElementList
     */
    void setSelectElementMap(Map<String, List<SelectElement>> selectElementList);

    /**
     *
     * @return
     *      the list of elements that can be chosen
     */
    Map<String, List<SelectElement>> getSelectElementMap();

    /**
     *
     * @param restrictionCode
     */
    void setRestrictionCode(String restrictionCode);

    /**
     * 
     * @return
     *      the code of the user restriction associated with this selector field.
     *      This restriction can disabled some elements when they are initially 
     *      in an enabled state.
     */
    String getRestrictionCode();
    
    /**
     *
     * @param activationCode
     */
    void setActivationCode(String activationCode);

    /**
     * 
     * @return
     *      the code of the activation option associated with this selector field.
     *      This restriction can enabled some elements when they are initially 
     *      in a disabled state.
     */
    String getActivationCode();

}