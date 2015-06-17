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
package org.tanaguru.webapp.form.parameterization;

import org.tanaguru.webapp.entity.option.Option;
import org.tanaguru.webapp.form.FormField;
import org.tanaguru.webapp.form.parameterization.builder.ContractOptionFormFieldBuilder;

/**
 *
 * @author jkowalczyk
 */
public class ContractOptionFormFieldImpl implements ContractOptionFormField {

    public ContractOptionFormFieldImpl(ContractOptionFormFieldBuilder contractOptionFormFieldBuilder) {
        this.formField = contractOptionFormFieldBuilder.getFormField();
        this.option = contractOptionFormFieldBuilder.getOption();
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