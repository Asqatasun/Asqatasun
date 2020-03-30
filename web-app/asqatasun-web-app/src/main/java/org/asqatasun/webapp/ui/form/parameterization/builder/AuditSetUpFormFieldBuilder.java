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

import org.asqatasun.entity.parameterization.ParameterElement;
import org.asqatasun.entity.service.parameterization.ParameterElementDataService;
import org.asqatasun.webapp.ui.form.FormField;
import org.asqatasun.webapp.ui.form.builder.AbstractGenericFormFieldBuilder;
import org.asqatasun.webapp.ui.form.parameterization.AuditSetUpFormField;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * That builder is a specific FormField Builder dedicated to create the
 * audit set-up forms and bind them with audit parameters. 
 * 
 * @author jkowalczyk
 */
@Component("auditSetUpFormFieldBuilder")
public class AuditSetUpFormFieldBuilder {

    private final ParameterElementDataService parameterElementDataService;
    private ParameterElement parameterElement;
    public ParameterElement getParameterElement() {
        return this.parameterElement;
    }

    @Autowired
    public AuditSetUpFormFieldBuilder(ParameterElementDataService parameterElementDataService){
        this.parameterElementDataService = parameterElementDataService;
    }

    public void setParameterCode(String parameterCode) {
        parameterElement = parameterElementDataService.getParameterElement(parameterCode);
        if (parameterElement == null) {
            LoggerFactory.getLogger(this.getClass()).warn("The parameter Code "
                    + parameterCode
                    + " does not exist in database");
        }
    }

    private AbstractGenericFormFieldBuilder <? extends FormField> formFieldBuilder;
    public FormField getFormField() {
        return formFieldBuilder.build();
    }
    public void setFormFieldBuilder(AbstractGenericFormFieldBuilder<? extends FormField> formFieldBuilder) {
        this.formFieldBuilder = formFieldBuilder;
    }

    public AuditSetUpFormField build() {
        return new AuditSetUpFormField(this);
    }

}
