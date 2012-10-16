/*
 * Tanaguru - Automated webpage assessment
 * Copyright (C) 2008-2011  Open-S Company
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
 * Contact us by mail: open-s AT open-s DOT com
 */
package org.opens.tgol.form.parameterization.builder;

import org.opens.tgol.entity.option.Option;
import org.opens.tgol.form.FormField;
import org.opens.tgol.form.builder.AbstractGenericFormFieldBuilder;
import org.opens.tgol.form.parameterization.ContractOptionFormField;

/**
 *
 * @author jkowalczyk
 */
public interface ContractOptionFormFieldBuilder {

    /**
     *
     * @return
     *      the formField that has to be set to each instance of AuditSetUpFormField
     */
    FormField getFormField();

    /**
     * Sets the FormFieldBuilder used to create the instance of FormField
     * associated with each instance of AuditSetUpFormField
     * 
     * @param formFieldBuilder
     */
    void setFormFieldBuilder(AbstractGenericFormFieldBuilder<? extends FormField> formFieldBuilder);

    /**
     *
     * @return
     *      the restriction code that has to be set to each instance of AuditSetUpFormField
     */
    Option getOption();

    /**
     * Set the restriction code that has to be set to each instance of AuditSetUpFormField
     * @param restrictionCode
     */
    void setOptionCode(String optionCode);

    /**
     *
     * @return
     *      a set instance of AuditSetUpFormField
     */
    ContractOptionFormField build();

}