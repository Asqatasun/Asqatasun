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

import org.apache.commons.lang3.StringUtils;
import org.tanaguru.webapp.form.TextualFormField;
import org.tanaguru.webapp.form.TextualFormFieldImpl;

/**
 *
 * @author jkowalczyk
 */
public class TextualFormFieldBuilderImpl extends FormFieldBuilderImpl
        implements AbstractGenericFormFieldBuilder<TextualFormField>, TextualFormFieldBuilder {

    public TextualFormFieldBuilderImpl() {
        super();
    }

    @Override
    public TextualFormField build() {
        TextualFormField formField = new TextualFormFieldImpl();
        formField.setErrorI18nKey(getErrorI18nKey());
        formField.setI18nKey(getI18nKey());
        formField.setValue(getValue());
        if (StringUtils.isNotBlank(validationRegexp)) {
            formField.setValidationRegexp(validationRegexp);
        }
        return formField;
    }

    private String validationRegexp;
    @Override
    public void setValidationRegexp(String validationRegexp) {
        this.validationRegexp = validationRegexp;
    }

    @Override
    public String getValidationRegexp() {
        return validationRegexp;
    }

}