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
package org.tanaguru.webapp.form.parameterization.builder;

import org.apache.log4j.Logger;
import org.tanaguru.webapp.entity.option.Option;
import org.tanaguru.webapp.entity.service.option.OptionDataService;
import org.tanaguru.webapp.form.FormField;
import org.tanaguru.webapp.form.builder.AbstractGenericFormFieldBuilder;
import org.tanaguru.webapp.form.parameterization.ContractOptionFormField;
import org.tanaguru.webapp.form.parameterization.ContractOptionFormFieldImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * That builder is a specific FormField Builder dedicated to create the
 * audit set-up forms and bind them with audit parameters. 
 * 
 * @author jkowalczyk
 */
public class ContractOptionFormFieldBuilderImpl implements ContractOptionFormFieldBuilder {

    private AbstractGenericFormFieldBuilder<? extends FormField> formFieldBuilder;
    @Override
    public FormField getFormField() {
        return formFieldBuilder.build();
    }

    @Override
    public void setFormFieldBuilder(AbstractGenericFormFieldBuilder<? extends FormField> formFieldBuilder) {
        this.formFieldBuilder = formFieldBuilder;
    }

    private OptionDataService optionDataService;
    @Autowired
    public void setOptionDataService(OptionDataService optionDataService) {
        this.optionDataService = optionDataService;
    }

    private Option option;
    @Override
    public Option getOption() {
        return this.option;
    }

    @Override
    public void setOptionCode(String optionCode) {
        option = optionDataService.getOption(optionCode);
        if (option == null) {
            Logger.getLogger(this.getClass()).fatal("The option Code "
                    + optionCode
                    + " does not exist in database");
            try {
              System.exit(0);
            } finally {
                System.exit(1);
            }
        }
    }
    
    @Override
    public ContractOptionFormField build() {
        return new ContractOptionFormFieldImpl(this);
    }

}