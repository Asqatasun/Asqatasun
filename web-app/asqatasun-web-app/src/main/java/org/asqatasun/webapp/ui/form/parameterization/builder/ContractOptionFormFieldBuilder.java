/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2019  Asqatasun.org
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
package org.asqatasun.webapp.ui.form.parameterization.builder;

import org.asqatasun.entity.option.Option;
import org.asqatasun.entity.service.option.OptionDataService;
import org.asqatasun.webapp.ui.form.FormField;
import org.asqatasun.webapp.ui.form.builder.AbstractGenericFormFieldBuilder;
import org.asqatasun.webapp.ui.form.parameterization.ContractOptionFormField;
import org.slf4j.LoggerFactory;

/**
 * That builder is a specific FormField Builder dedicated to create the
 * audit set-up forms and bind them with audit parameters. 
 * 
 * @author jkowalczyk
 */
public class ContractOptionFormFieldBuilder {

    private OptionDataService optionDataService;
    private AbstractGenericFormFieldBuilder <? extends FormField> formFieldBuilder;

    public FormField getFormField() {
        return formFieldBuilder.build();
    }

        public void setFormFieldBuilder(AbstractGenericFormFieldBuilder<? extends FormField> formFieldBuilder) {
        this.formFieldBuilder = formFieldBuilder;
    }
    private Option option;

    public Option getOption() {
        return this.option;
    }
    public void setOptionCode(String optionCode) {
        option = optionDataService.getOption(optionCode);
        if (option == null) {
            LoggerFactory.getLogger(this.getClass()).warn("The option Code "
                    + optionCode
                    + " does not exist in database");
        }
    }

    public ContractOptionFormFieldBuilder(OptionDataService optionDataService) {
        this.optionDataService = optionDataService;
    }

    public ContractOptionFormField build() {
        return new ContractOptionFormField(this);
    }

}
