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
import org.tanaguru.webapp.form.CheckboxElement;

/**
 *
 * @author jkowalczyk
 */
public interface CheckboxFormFieldBuilder extends FormFieldBuilder {

    /**
     * Sets the list of CheckboxElementBuiler to build set instances of
     * CheckboxElements and associate them with each instance of CheckboxFormField
     * 
     * @param checkboxElementBuilderList
     */
    void setCheckboxElementBuilderList(List<CheckboxElementBuilder> checkboxElementBuilderList);

    /**
     *
     * @return
     *      the CheckboxElement list that has to be set to each instance of CheckboxFormField
     */
    List<CheckboxElement> getCheckboxElementList();

    /**
     *
     * @return
     *      the restriction code that has to be set to each instance of CheckboxFormField
     */
    String getRestrictionCode();

    /**
     * Sets the restriction code that has to be set to each instance of CheckboxFormField
     * @param restrictionCode
     */
    void setRestrictionCode(String restrictionCode);
    
    /**
     *
     * @return
     *      the activation option code that has to be set to each instance of CheckboxFormField
     */
    String getActivationCode();
    
    /**
     * Sets the activation option code that has to be set to each instance of CheckboxFormField
     * @param activationCode
     */
    void setActivationCode(String activationCode);
    
    /**
     *
     * @return
     *      the code used to map the result of the selection
     */
    String getCode();
    
    /**
     * Sets the code used to map the result of the selection
     * @param code
     */
    void setCode(String code);

}