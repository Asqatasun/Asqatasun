/*
 * Asqatasun - Automated webpage assessment
 * Copyright (C) 2008-2015  Asqatasun.org
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
package org.asqatasun.webapp.form.builder;

import org.asqatasun.webapp.form.NumericalFormField;
import org.asqatasun.webapp.form.NumericalFormFieldImpl;

/**
 *
 * @author jkowalczyk
 */
public class NumericalFormFieldBuilderImpl extends FormFieldBuilderImpl
        implements AbstractGenericFormFieldBuilder<NumericalFormField>, NumericalFormFieldBuilder {

    public NumericalFormFieldBuilderImpl() {
        super();
    }

    @Override
    public NumericalFormField build() {
        NumericalFormField formField = new NumericalFormFieldImpl();
        formField.setErrorI18nKey(getErrorI18nKey());
        formField.setI18nKey(getI18nKey());
        formField.setValue(getValue());
        formField.setMaxValue(maxValue);
        formField.setMinValue(minValue);
        return formField;
    }

    private String minValue;
    @Override
    public String getMinValue() {
        return this.minValue;
    }

    @Override
    public void setMinValue(String minValue) {
        this.minValue=minValue;
    }

    private String maxValue;
    @Override
    public String getMaxValue() {
        return this.maxValue;
    }

    @Override
    public void setMaxValue(String maxValue) {
        this.maxValue=maxValue;
    }

}