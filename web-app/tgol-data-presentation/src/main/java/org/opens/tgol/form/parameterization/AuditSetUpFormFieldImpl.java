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
package org.opens.tgol.form.parameterization;

import org.opens.tanaguru.entity.parameterization.ParameterElement;
import org.opens.tgol.entity.option.Option;
import org.opens.tgol.form.FormField;
import org.opens.tgol.form.parameterization.builder.AuditSetUpFormFieldBuilderImpl;

/**
 *
 * @author jkowalczyk
 */
public class AuditSetUpFormFieldImpl implements AuditSetUpFormField {

    public AuditSetUpFormFieldImpl(AuditSetUpFormFieldBuilderImpl auditSetUpFormFieldBuilder) {
        this.parameterElement = auditSetUpFormFieldBuilder.getParameterElement();
        this.formField = auditSetUpFormFieldBuilder.getFormField();
        this.option = auditSetUpFormFieldBuilder.getOption();
    }

    private ParameterElement parameterElement;
    @Override
    public ParameterElement getParameterElement() {
        return this.parameterElement;
    }

    @Override
    public void setParameterElement(ParameterElement parameterElement) {
        this.parameterElement = parameterElement;
    }

    private FormField formField;
    @Override
    public FormField getFormField() {
        return formField;
    }

    @Override
    public void setFormField(FormField formField) {
        this.formField = formField;
    }

    private Option option;
    @Override
    public Option getOption() {
        return option;
    }
    
    @Override
    public void setOption(Option option) {
        this.option = option;
    }

}